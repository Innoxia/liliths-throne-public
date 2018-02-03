package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.universal.dom.DomCowgirl;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.97
 * @author Innoxia
 */
public class PartnerPenisAnus {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {
		@Override
		public String getActionTitle() {
			return "Tease [pc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of your [npc.cock] over [pc.name]'s [pc.asshole+].";
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
							"Lining [npc.her] [npc.cock+] up to your [pc.ass+], [npc.name] starts slowly teasing the [npc.cockHead+] up and down over your [pc.asshole+], ready to penetrate you at any moment.",
							"With a soft [npc.moan], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.ass+], before starting to gently slide the [npc.cockHead] up and down over your [pc.asshole+].",
							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.asshole+], [npc.name] lets out a soft [npc.moan] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining [npc.her] [npc.cock+] up to your [pc.ass+], [npc.name] starts eagerly sliding the [npc.cockHead+] up and down over your [pc.asshole+], ready to penetrate you at any moment.",
							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.ass+], before starting to eagerly slide the [npc.cockHead] up and down over your [pc.asshole+].",
							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.asshole+], [npc.name] lets out [npc.a_moan+] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against your [pc.ass+], [npc.name] pulls back a little before starting to slide the [npc.cockHead+] up and down over your [pc.asshole+],"
									+ " ready to start fucking you at any moment.",
							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.ass+], before starting to roughly grind the [npc.cockHead] up and down over your [pc.asshole+].",
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.asshole+], [npc.name] lets out [npc.a_moan+] at the thought of being able to start fucking you whenever [npc.she] feels like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Fuck me! I need your cock inside of me!)]",
							" You let out a desperate [pc.moan], before pleading, [pc.speech(Go on! Please! Fuck me already!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Fuck my ass! I need you inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(That's right, fuck me!)]",
							" You let out a [pc.moan], before pleading, [pc.speech(Please! Fuck me already!)]",
							" You [pc.moan] out loud as you beg, [pc.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Main.game.getPlayer().isAssVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [pc.A_sob+] bursts out from between your [pc.lips+] at the thought of what's about to happen, [pc.speech(No! Don't! Please! I-I've never done anal before! You can't do this!)]",
								" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! I've never done anal before!)]",
								" You [pc.sob] in distress at the thought of what's about to happen, before desperately begging, [pc.speech(No! Stop! I don't want to lose my anal virginity!)]"));
						
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
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.ANUS);
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
			OrificeType.ANUS,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Anal tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.name]'s [npc.cock] over your [pc.ass+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you line [npc.name]'s [npc.cock+] up to your [pc.ass+],"
									+ " slowly pushing the [npc.cockHead+] up and down over your [pc.asshole+] as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With a soft [pc.moan], you line [npc.name]'s [npc.cock+] up to your [pc.ass+], before starting to gently slide the [npc.cockHead] up and down over your [pc.asshole+].",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you gently slide the [npc.cockHead+] over your [pc.asshole+],"
									+ " letting out a soft [pc.moan] as you tease [npc.herHim] with the promise of penetrating you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you grind [npc.name]'s [npc.cock+] against your [pc.ass+],"
									+ " rubbing the [npc.cockHead+] up and down over your [pc.asshole+] as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you grind [npc.name]'s [npc.cock+] against your [pc.ass+], before starting to roughly force the [npc.cockHead] up and down over your [pc.asshole+].",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you roughly grind the [npc.cockHead+] over your [pc.asshole+],"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating you."));
					break;
				case DOM_NORMAL: default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you line [npc.name]'s [npc.cock+] up to your [pc.ass+],"
									+ " eagerly pushing the [npc.cockHead+] up and down over your [pc.asshole+] as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you line [npc.name]'s [npc.cock+] up to your [pc.ass+], before starting to eagerly slide the [npc.cockHead] up and down over your [pc.asshole+].",
							"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you eagerly slide the [npc.cockHead+] over your [pc.asshole+],"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating you."));
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck your ass, hmm?)]",
							" [npc.Name] lets out [npc.a_moan+], [npc.speech(You want me to use your ass?)]",
							" [npc.Name] [npc.moansVerb] in delight, [npc.speech(Your ass sure feels good!)]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck your ass?)]",
							" [npc.Name] lets out [npc.a_moan+], [npc.speech(You want me to use your ass?)]",
							" [npc.Name] [npc.moansVerb] in delight, [npc.speech(Your ass sure feels good!)]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck your ass, slut?)]",
							" [npc.Name] lets out [npc.a_moan+], [npc.speech(You want to be my buttslut fuck-toy, huh?)]",
							" [npc.Name] [npc.moansVerb] in response to your movements, [npc.speech(Your ass sure feels good, buttslut!)]"));
					break;
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
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getActivePartner(), PenetrationType.PENIS, OrificeType.ANUS);
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
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), PenetrationType.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Start anal";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [pc.name]'s [pc.asshole+] and start fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks,"
										+ " [npc.she] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"Keeping a gentle grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks, and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks,"
										+ " [npc.she] lets out [npc.a_moan+] before eagerly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"Keeping a firm grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks, and with a determined thrust forwards, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to roughly grind the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks,"
										+ " [npc.she] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"Keeping a strong grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your ass cheeks, and with a forceful thrust forwards, [npc.she] roughly slams [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly pushing back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start eagerly pushing back into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, pushing back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start pushing back into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly try, and fail, to crawl away on all fours.",
								" With [pc.a_sob+], you try, in vain, to crawl away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.asshole+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over your [pc.ass], [npc.name] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
										+ " sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
										+ " forcing [npc.her] [npc.cock+] deep into your [pc.asshole+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass], and with a forceful thrust, [npc.she] roughly slams [npc.her] [npc.cock+] deep into your [pc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] over your [pc.ass], [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into your [pc.asshole+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against your [pc.ass], and with a thrust of [npc.her] [npc.hips], [npc.she] sinks [npc.her] [npc.cock+] into your [pc.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] as [npc.she] enters you, gently bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With a soft [pc.moan], you start gently bucking your [pc.ass] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start eagerly bucking your [pc.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, violently thrusting your [pc.ass] back against [npc.herHim] as you roughly force [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start violently bucking your [pc.ass] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start eagerly bucking your [pc.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, bucking your [pc.ass] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+].",
								" With [pc.a_moan+], you start bucking your [pc.ass] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
								" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.asshole+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [pc.name]'s [pc.asshole+].";
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
							"Gently sinking [npc.her] [npc.cock+] into your [pc.asshole+], a soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] gently thrusts into your [pc.ass+].",
							"Sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to gently buck [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently sinks [npc.her] [npc.cock+] into your [pc.asshole+], causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to pull out of your [pc.ass+].",
							"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.asshole],"
									+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to pull out of your [pc.ass+].",
							"Slowly sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] gently bucks [npc.her] [npc.hips] into your [pc.ass] as you weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] gently thrusts into your [pc.ass+].",
							"Sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to gently buck [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.asshole+].";
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
							"Desperately sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] eagerly pushes [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] rapidly thrusts into your [pc.ass+].",
							"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to eagerly buck [npc.her] [npc.hips] against your [pc.ass],"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly sinks [npc.her] [npc.cock+] into your [pc.asshole+], causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to pull out of your [pc.ass+].",
							"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] into your [pc.asshole],"
									+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to pull out of your [pc.ass+].",
							"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] eagerly bucks [npc.her] [npc.hips] into your [pc.ass] as you weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
							"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] eagerly pushes [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] rapidly thrusts into your [pc.ass+].",
							"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to rapidly buck [npc.her] [npc.hips] against your [pc.ass],"
									+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [pc.name]'s [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) { // Doggy-style descriptions:
				
				String tailSpecial="",  hairSpecial="";
				
				// Tail special:
				if(Main.game.getPlayer().getTailType()!=TailType.NONE){
						tailSpecial = "Grabbing the base of your [pc.tail+], [npc.name] starts roughly pulling you back onto [npc.her] [npc.cock+] in time with [npc.her] thrusts,"
								+ " letting out [npc.moans+] as [npc.she] violently pounds away at your [pc.asshole+].";
				}
				
				// Hair special:
				if(Main.game.getPlayer().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, [npc.name] reaches down and grabs a fistful of your [pc.hair+],"
							+ " using [npc.her] new leverage to pull you back onto [npc.her] [npc.cock+] as [npc.she] starts roughly fucking your [pc.asshole+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, [npc.name] knocks your [pc.arms] out from under you, causing your [pc.face+] to be pressed down into the floor as [npc.she] stoops over you,"
									+ " furiously slamming [npc.her] [npc.cock+] in and out of your [pc.asshole+] as [npc.she] holds your [pc.ass+] up in the air.",
							hairSpecial,
							"With a menacing growl, [npc.name] roughly grabs your [pc.hips+], shuffling forwards a little and burying [npc.her] [npc.cock+] deep in your [pc.asshole+],"
									+ " before starting to furiously pump [npc.her] hips back and forth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks you like an animal.",
							"Roughly grabbing hold of your waist, [npc.name] starts to rapidly pound [npc.her] [npc.cock+] in and out of your [pc.asshole+],"
									+ " letting out a series of [npc.moans+] as [npc.she] slams into your [pc.ass] over and over again."));
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly push your [pc.ass] back against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly pushing your [pc.ass] back into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.ass+] back against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from [npc.name], you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to stop abusing your [pc.asshole+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to crawl away from [npc.name], tears streaming down your [pc.face] as you plead for [npc.herHim] to stop abusing your [pc.asshole+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly try to crawl away from [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.asshole+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.ass] back against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.ass] back into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.ass+] back against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+]"));
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] into your [pc.asshole+], [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts violently bucking [npc.her] [npc.hips],"
										+ " grinning as [npc.she] feels you greedily pushing your [pc.ass] back against [npc.herHim].",
								"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] into your [pc.asshole+],"
										+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] brutally thrusts into your [pc.ass+].",
								"Ruthlessly thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to roughly slam [npc.her] [npc.hips] against your [pc.ass],"
										+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] into your [pc.asshole+], causing you to let out [pc.a_sob+]. With tears streaming down your [pc.face], you weakly beg for [npc.herHim] to stop abusing your [pc.ass+].",
								"[pc.A_sob+] bursts out from between your [pc.lips] as [npc.name] roughly slams [npc.her] [npc.cock+] into your [pc.asshole],"
										+ " and with tears streaming down your [pc.face], you desperately plead for [npc.herHim] to stop abusing your [pc.ass+].",
								"Ruthlessly thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] roughly slams [npc.her] [npc.hips] into your [pc.ass] as you weakly struggle against [npc.herHim],"
										+ " pleading and crying for [npc.herHim] to pull out."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] into your [pc.asshole+], [npc.a_moan+] drifts out from between [npc.name]'s [npc.lips+] as [npc.she] starts violently bucking [npc.her] [npc.hips],"
										+ " grinning as [npc.she] feels you pushing your [pc.ass] back against [npc.herHim].",
								"[pc.A_moan+] bursts out from between your [pc.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] into your [pc.asshole+],"
										+ " and you find yourself pushing your [pc.hips] back against [npc.herHim] as [npc.she] brutally thrusts into your [pc.ass+].",
								"Ruthlessly thrusting [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to roughly slam [npc.her] [npc.hips] against your [pc.ass],"
										+ " breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
						break;
				}

			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [pc.name]'s [pc.asshole+].";
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
							"Sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws a soft [pc.moan] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start gently pushing your [pc.ass] back against [npc.her] groin.",
							"A soft [pc.moan] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you can't help but gently push your [pc.ass] back against [npc.herHim] as [npc.she] carries on fucking you.",
							"Sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you roughly command [pc.herHim] to continue fucking your [pc.ass].",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and, roughly thrusting your [pc.ass] back into [npc.her] groin, you order [npc.herHim] to continue fucking you.",
							"Sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start greedily pushing your [pc.ass] back against [npc.her] groin.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you can't help but greedily push your [pc.ass] back against [npc.herHim] as [npc.she] carries on fucking you.",
							"Sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [pc.name]'s [pc.asshole+].";
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
							"Desperately sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws a soft [pc.moan] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start gently pushing your [pc.ass] back against [npc.her] groin.",
							"A soft [pc.moan] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you can't help but gently push your [pc.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking you.",
							"Eagerly pushing [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to rapidly thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you roughly command [pc.herHim] to continue fucking your [pc.ass].",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and, roughly thrusting your [pc.ass] back into [npc.her] groin, you order [npc.herHim] to continue fucking you.",
							"Eagerly sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to rapidly thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you [pc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] draws [pc.a_moan+] from your [pc.lips+] as [npc.she] starts eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as you start greedily pushing your [pc.ass] back against [npc.her] groin.",
							"[pc.A_moan+] drifts out from between your [pc.lips+] as [npc.name] eagerly thrusts [npc.her] [npc.cock+] into your [pc.asshole+],"
									+ " and you can't help but greedily push your [pc.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking you.",
							"Eagerly sliding [npc.her] [npc.cock+] into your [pc.asshole+], [npc.name] starts to rapidly thrust [npc.her] [npc.hips] against your [pc.ass], breathing in your [pc.scent] as you softly [pc.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist anal";
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
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from your [pc.asshole+], [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you slowly thrust your [pc.ass] back against [npc.herHim] and continue gently fucking yourself on [npc.her] [npc.cock+].",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you firmly hold [npc.herHim] in place, gently pushing your [pc.ass] out against [npc.her] groin as you fuck your [pc.asshole+] on [npc.her] [npc.cock+].",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, slowly grinding yourself against [npc.herHim] as you sink [npc.her] [npc.cock+] deep into your [pc.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from your [pc.asshole+], [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you violently thrust your [pc.ass] back against [npc.herHim] and continue roughly fucking yourself on [npc.her] [npc.cock+].",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you dominantly hold [npc.herHim] in place, violently pushing your [pc.ass] out against [npc.her] groin as you roughly fuck your [pc.asshole+] on [npc.her] [npc.cock+].",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, roughly grinding yourself against [npc.herHim] as you force [npc.her] [npc.cock+] deep into your [pc.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from your [pc.asshole+], [npc.name] lets out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " you eagerly thrust your [pc.ass] back against [npc.herHim] and continue rapidly fucking yourself on [npc.her] [npc.cock+].",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, but, totally ignoring [npc.her] protests,"
									+ " you firmly hold [npc.herHim] in place, eagerly pushing your [pc.ass] out against [npc.her] groin as you desperately fuck your [pc.asshole+] on [npc.her] [npc.cock+].",
							"[npc.Sobbing] in distress, [npc.name] weakly struggles against you as [npc.she] pleads for you to let go of [npc.her] [npc.cock]."
									+ " [pc.Moaning] in delight, you totally ignore [npc.her] protests, eagerly grinding yourself against [npc.herHim] as you happily force [npc.her] [npc.cock+] deep into your [pc.asshole+]"));
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
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [pc.name]'s [pc.asshole+] and stop fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of your [pc.ass+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.asshole+] one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of your [pc.asshole+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of your [pc.ass+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over your [pc.asshole+] one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of your [pc.asshole+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] pulls out of your [pc.ass], and you continue crying and protesting as you continue to weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] withdraws from your [pc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] out of your [pc.asshole+], eager for more of [npc.her] attention.",
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
			OrificeType.ANUS,
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
			return "Ride [npc.her] cock anally";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking your [pc.asshole+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerStartingAnalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] over your [pc.ass+],"
										+ " letting out a little [pc.moan] before gently bucking your [pc.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate your [pc.asshole+].",
								"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you slowly push your [pc.hips] into [npc.her] groin, letting out a soft [pc.moan] as you penetrate your [pc.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to your [pc.ass+],"
										+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate your [pc.asshole+].",
								"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you eagerly push your [pc.hips] into [npc.her] groin, letting out [pc.a_moan+] as you penetrate your [pc.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding yourself against [npc.name], you guide [npc.her] [npc.cock+] up to your [pc.ass+],"
										+ " letting out [pc.a_moan+] before roughly slamming your [pc.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate your [pc.asshole+].",
								"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you violently slam your [pc.hips] into [npc.her] groin, letting out [pc.a_moan+] as you roughly start fucking your [pc.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to your [pc.ass+],"
										+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate your [pc.asshole+].",
								"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you eagerly push your [pc.hips] into [npc.her] groin, letting out [pc.a_moan+] as you penetrate your [pc.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you guide [npc.her] [npc.cock+] up to your [pc.ass+],"
										+ " letting out [pc.a_moan+] before bucking your [pc.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate your [pc.asshole+].",
								"Lining your [pc.ass+] up to [npc.name]'s [npc.cock+], you push your [pc.hips] into [npc.her] groin, letting out [pc.a_moan+] as you penetrate your [pc.asshole+] on [npc.her] [npc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out a soft [npc.moan] as [npc.she] enters you, gently bucking [npc.her] [npc.hips] as [npc.she] starts to fuck your [pc.asshole+].",
								" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your [pc.ass], sinking [npc.her] [npc.cock+] into your [pc.asshole+] as [npc.she] starts fucking you."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.asshole+].",
								" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your [pc.ass], sinking [npc.her] [npc.cock+] into your [pc.asshole+] as [npc.she] starts energetically fucking you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge,"
										+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck your [pc.asshole+].",
								" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into your [pc.ass], seeking to remind you who's in charge as [npc.she] starts ruthlessly fucking your [pc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.asshole+].",
								" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your [pc.ass], sinking [npc.her] [npc.cock+] into your [pc.asshole+] as [npc.she] starts energetically fucking you."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] into your [pc.ass] as [npc.she] starts fucking your [pc.asshole+].",
								" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.ass], sinking [npc.her] [npc.cock+] into your [pc.asshole+] as [npc.she] starts fucking you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of you, and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from your [pc.asshole+].",
								" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.asshole+]."));
						break;
					default:
						break;
				}
			
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
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [pc.ass+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockAnallyGentle();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Gently pushing your [pc.ass] into [npc.name]'s groin, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"With a soft [pc.moan], you gently start gyrating your [pc.hips] into [npc.name]'s [npc.ass], forcing [npc.her] [npc.cock+] ever deeper into your [pc.asshole+].",
						"Slowly thrusting your [pc.ass] into [npc.name]'s groin, a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.asshole+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [pc.asshole+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockAnallyNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing your [pc.ass] into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"With [pc.a_moan+], you energetically start gyrating your [pc.hips] into [npc.name]'s [npc.ass], forcing [npc.her] [npc.cock+] ever deeper into your [pc.asshole+].",
						"Enthusiastically thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.asshole+].");
			
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [pc.asshole+] on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockAnallyRough();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Violently slamming your [pc.ass] into [npc.name]'s groin, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] into [npc.name]'s [npc.ass], roughly forcing [npc.her] [npc.cock+] ever deeper into your [pc.asshole+].",
						"Roughly thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.cock+] deep into your [pc.asshole+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Push your [pc.ass+] out against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing yourself with both hands flat on the floor, you start to push back against [npc.name] in time with [npc.her] thrusts,"
								+ " letting out a series of [pc.moans+] as you force [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"Letting out [pc.a_moan+], you start pushing yourself back in time with [npc.name]'s thrusts, helping to slam [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"With a series of [pc.moans+], you start bucking back against [npc.name], helping to slam [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"Each time [npc.name] thrusts forwards, you push yourself back, letting out moan after desperate moan as you repeatedly force [npc.her] [npc.cock+] deep into your [pc.asshole+]."));
				
			} else {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Pushing your [pc.ass] out into [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
						"With [pc.a_moan+], you start gyrating your [pc.ass] into [npc.name]'s groin, forcing [npc.her] [npc.cock+] ever deeper into your [pc.asshole+].",
						"Thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.asshole+]."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly your [pc.ass+] out against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.ass] out into [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.asshole+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.ass] into [npc.name]'s groin, forcing [npc.her] [npc.cock+] ever deeper into your [pc.asshole+].",
					"Enthusiastically thrusting your [pc.ass] into [npc.name]'s groin, [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist anal";
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
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.asshole+] as [npc.she] continues gently fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] away from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues slowly sliding in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues gently sliding deep into your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.asshole+] as [npc.she] continues eagerly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues eagerly sliding in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues eagerly sliding deep into your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.asshole+] as [npc.she] continues roughly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.ass] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues roughly slamming in and out of your [pc.asshole+].",
							"Trying desperately to pull your [pc.ass] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues roughly slamming deep into your [pc.asshole+]."));
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
			OrificeType.ANUS,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerStoppingAnalPenetrationDescription();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc.name]'s [npc.cock] out of your [pc.asshole+], you let out a menacing growl as you command [npc.herHim] to stop fucking you.",
								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of your [pc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.name]'s [npc.cock] out of your [pc.asshole+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of your [pc.asshole+]."));
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
								" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your [pc.asshole+].",
								" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.asshole+]."));
						break;
				}
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
	};
	
}
