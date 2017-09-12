package com.base.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.game.sex.sexActions.universal.sub.SubCowgirl;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerPenisAnus {
	
	public static final SexAction PLAYER_TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of your [pc.cock] over [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining your [pc.cock+] up to [npc.name]'s [npc.ass+], you start slowly teasing the [pc.cockHead+] up and down over [npc.her] [npc.asshole+], ready to penetrate [npc.herHim] at any moment.",
							"With a soft [pc.moan], you line your [pc.cock+] up to [npc.name]'s [npc.ass+], before starting to gently slide the [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Gently sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.asshole+], you let out a soft [pc.moan] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining your [pc.cock+] up to [npc.name]'s [npc.ass+], you start eagerly sliding your [pc.cockHead+] up and down over [npc.her] [npc.asshole+], ready to penetrate [npc.herHim] at any moment.",
							"With [pc.a_moan+], you line your [pc.cock+] up to [npc.name]'s [npc.ass+], before starting to eagerly slide your [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Eagerly sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.cock+] up against [npc.name]'s [npc.ass+], you pull back a little before starting to slide your [pc.cockHead+] up and down over [npc.her] [npc.asshole+],"
									+ " ready to start fucking [npc.herHim] at any moment.",
							"With [pc.a_moan+], you line your [pc.cock+] up to [npc.name]'s [npc.ass+], before starting to roughly grind your [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Roughly grinding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] at the thought of being able to start fucking [npc.herHim] whenever you feel like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(Please! Fuck my ass! I need your cock inside of me!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading, [npc.speech(Go on! Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Fuck my little ass! I need you inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+], [npc.speech(That's right, fuck my ass!)]",
							" [npc.Name] lets out a [npc.moan], before addressing you, [npc.speech(Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getPartner().isAssVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! I-I've never done anal before! You can't do this!)]",
								" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! I've never done anal before!)]",
								" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! I don't want to lose my anal virginity!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",
								" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",
								" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getPartner(), PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DENIAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Anal tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of [pc.name]'s [pc.cock] over your [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.ass+],"
									+ " slowly pushing your [pc.cockHead+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With a soft [npc.moan], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.ass+], before starting to gently slide your [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] gently slides your [pc.cockHead+] over [npc.her] [npc.asshole+],"
									+ " letting out a soft [npc.moan] as [npc.she] teases you with the promise of penetrating [npc.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.ass+],"
									+ " eagerly pushing your [pc.cockHead+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.ass+], before starting to eagerly slide your [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] eagerly slides your [pc.cockHead+] over [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetrating [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] grinds your [pc.cock+] against [npc.her] [npc.ass+],"
									+ " rubbing your [pc.cockHead+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] grinds your [pc.cock+] against [npc.her] [npc.ass+], before starting to roughly force your [pc.cockHead] up and down over [npc.her] [npc.asshole+].",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] roughly grinds your [pc.cockHead+] over [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetrating [npc.herHim]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Let me fuck your ass already!)]",
							" You let out a desperate [pc.moan], before pleading with [npc.herHim], [pc.speech(Yes! Please, let me fuck your ass!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Let me fuck your ass! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(Yes, I want to fuck you!)]",
							" You let out a [pc.moan], before calling out, [pc.speech(Please! Let me fuck your ass!)]",
							" You [pc.moan] out loud as you speak to [npc.herHim], [pc.speech(Come on, let me fuck you already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
							" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
							" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getPartner(), Main.game.getPlayer(), PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	
	public static final SexAction PLAYER_ANAL_FUCKING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER)==null;
		}
		
		@Override
		public String getActionTitle() {
			return "Start anal";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.cock+] into [npc.name]'s [npc.asshole+] and start fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to tease the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks,"
										+ " you let out a little [pc.moan] as you slowly push your [pc.hips] forwards and sink your [pc.cock+] into [npc.her] [npc.asshole+].",
								"Keeping a gentle grip on [npc.name]'s [npc.hips+],"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks, and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to tease the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks,"
										+ " you let out [pc.a_moan+] as you eagerly push your [pc.hips] forwards and sink your [pc.cock+] into [npc.her] [npc.asshole+].",
								"Keeping a firm grip on [npc.name]'s [npc.hips+],"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks, and with a determined thrust forwards, you eagerly sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to roughly grind the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks,"
										+ " you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards and forcing your [pc.cock+] deep into [npc.her] [npc.asshole+].",
								"Keeping a strong grip on [npc.name]'s [npc.hips+], you look down as you position the [pc.cockHead+] of your [pc.cock] between [npc.her] ass cheeks,"
										+ " and with a forceful thrust forwards, you roughly slam your [pc.cock+] deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly pushing back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts eagerly pushing back into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], pushing back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts pushing back into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly tries, and fails, to crawl away on all fours.",
								" With [npc.a_sob+], [npc.she] tries, in vain, to crawl away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.ass+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.ass], you let out a little [pc.moan] before slowly pushing your [pc.hips] forwards,"
										+ " sinking your [pc.cock+] into [npc.her] [npc.asshole+].",
								"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.ass], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.asshole+].",
								"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.ass], you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards,"
										+ " forcing your [pc.cock+] deep into [npc.her] [npc.asshole+].",
								"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a forceful thrust of your [pc.hips], you roughly slam your [pc.cock+] deep into [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.ass], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.asshole+].",
								"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.ass], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, sinking your [pc.cock+] into [npc.her] [npc.asshole+].",
								"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a thrust of your [pc.hips], you sink your [pc.cock+] into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a soft [npc.moan] as you enter [npc.herHim], gently bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] into your crotch, sinking your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], violently thrusting [npc.her] [npc.hips] back against you as [npc.she] roughly forces your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] into your crotch, roughly forcing you to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+].",
								" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.ass+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
								" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.ass+]."));
						break;
					default:
						break;
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you softly fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you slowly push your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] pushing [npc.her] [npc.hips] back against you as you gently thrust into [npc.her] [npc.ass+].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.ass+].",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you slowly push your [pc.cock+] into [npc.her] [npc.asshole],"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to pull out of [npc.her] [npc.ass+].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you gently buck your [pc.hips] into [npc.her] [npc.ass] as [npc.she] weakly struggles against you, pleading and crying for you to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips], grinning to yourself as you softly fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you slowly push your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as you start gently thrusting into [npc.her] [npc.ass+].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you enthusiastically fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] greedily pushing [npc.her] [npc.hips] back against you as you carry on fucking [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.ass+].",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you eagerly push your [pc.cock+] into [npc.her] [npc.asshole],"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to pull out of [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you rapidly buck your [pc.hips] into [npc.her] [npc.ass] as [npc.she] weakly struggles against you, pleading and crying for you to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start rapidly bucking your [pc.hips],"
									+ " grinning to yourself as you greedily fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you eagerly push your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as you start rapidly thrusting into [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to frantically buck your [pc.hips] into [npc.her] [npc.ass], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) { // Doggy-style descriptions:
				
				String tailSpecial="",  hairSpecial="";
				
				// Tail special:
				if(Sex.getPartner().getTailType()!=TailType.NONE){
						tailSpecial = "Grabbing the base of [npc.name]'s [npc.tail+], you start roughly pulling [npc.herHim] back onto your [pc.cock+] in time with your thrusts,"
								+ " letting out [pc.moans+] as you violently pound away at [npc.her] [npc.asshole+].";
				}
				
				// Hair special:
				if(Sex.getPartner().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, you reach down and grab a fistful of [npc.name]'s [npc.hair+],"
							+ " using your new leverage to pull [npc.herHim] back onto your [pc.cock+] as you start roughly fucking [npc.her] [npc.asshole+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, you knock [npc.name]'s [npc.arms] out from under [npc.herHim], causing [npc.her] [npc.face+] to be pressed down into the floor as you stoop over [npc.herHim],"
									+ " furiously slamming your [pc.cock+] in and out of [npc.her] [npc.asshole+] as you hold [npc.her] [npc.ass+] up in the air.",
							hairSpecial,
							"With a menacing growl, you roughly grab [npc.name]'s [npc.hips+], shuffling forwards a little and burying your [pc.cock+] deep in [npc.her] [npc.asshole+],"
									+ " before starting to furiously pump your hips back and forth, letting out a series of [pc.moans+] as you ruthlessly fuck [npc.herHim] like an animal.",
							"Roughly grabbing hold of [npc.name]'s waist, you start to rapidly pound your [pc.cock+] in and out of [npc.her] [npc.asshole+],"
									+ " letting out a series of [pc.moans+] as you slam into [npc.her] [npc.ass] over and over again."));
				
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly pushes [npc.her] [npc.ass] back against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly pushing [npc.her] [npc.ass] back into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.ass+] back against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop abusing [npc.her] [npc.asshole+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to crawl away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.asshole+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly tries crawling away, pleading and crying for you to pull out of [npc.her] [npc.asshole+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] pushes [npc.her] [npc.ass] back against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, pushing [npc.her] [npc.ass] back into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.ass+] back out against you,"
										+ " begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+]"));
						break;
				}
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start violently pumping your [pc.hips],"
										+ " grinning as you feel [npc.herHim] greedily pushing back against you as you brutally fuck [npc.her] [npc.ass].",
								"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you violently thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
										+ " and you feel [npc.herHim] greedily pushing [npc.her] [npc.hips] back against you as you carry on roughly fucking [npc.her] [npc.ass+].",
								"Ruthlessly thrusting your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to violently buck your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] into [npc.name]'s [npc.asshole+], [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.ass+].",
								"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you violently thrust your [pc.cock+] into [npc.her] [npc.asshole],"
										+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to pull out of [npc.her] [npc.ass+].",
								"Ruthlessly thrusting your [pc.cock+] into [npc.name]'s [npc.asshole+],"
										+ " you violently buck your [pc.hips] into [npc.her] [npc.ass] as [npc.she] weakly struggles against you, pleading and crying for you to pull out."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start violently bucking your [pc.hips],"
										+ " grinning to yourself as you brutally fuck [npc.her] [npc.ass].",
								"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you violently thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
										+ " and you soon find your own [pc.moans] joining with [npc.hers] as you start roughly thrusting into [npc.her] [npc.ass+].",
								"Ruthlessly thrusting your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to violently buck your [pc.hips] into [npc.her] [npc.ass],"
										+ " breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
						break;
				}
			
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw a soft [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " letting out a [pc.moan] of your own as you feel [npc.herHim] gently pushing back against your groin.",
							"A soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+] as you thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] gently pushing [npc.her] [npc.hips] back against you as you carry on fucking [npc.her] [npc.ass+].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to softly [npc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " letting out a [pc.moan] of your own as [npc.she] roughly commands you to continue fucking [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you slide your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and, roughly thrusting [npc.her] [npc.ass] back into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] greedily pushing [npc.her] [npc.hips] back against you as you carry on fucking [npc.her] [npc.ass+].",
							"Sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw a soft [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] gently pushing back against you as you enthusiastically fuck [npc.her] [npc.ass].",
							"A soft [npc.moan] drifts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] gently pushing [npc.her] [npc.hips] back against you as you carry on fucking [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to softly [npc.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as [npc.she] roughly commands you to continue fucking [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly slide your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and, roughly thrusting [npc.her] [npc.ass] back into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.asshole+], you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you enthusiastically fuck [npc.her] [npc.ass].",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] into [npc.her] [npc.asshole+],"
									+ " and you feel [npc.herHim] greedily pushing [npc.her] [npc.hips] back against you as you carry on fucking [npc.her] [npc.ass+].",
							"Eagerly sliding your [pc.cock+] into [npc.name]'s [npc.asshole+], you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as you cause [npc.herHim] to [npc.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist anal";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.asshole+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] slowly thrusts [npc.her] [npc.hips] back against you and continues gently fucking [npc.herself] on your [pc.cock+].",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] firmly holds you in place, gently pushing [npc.her] [npc.hips] out against your groin as [npc.she] fucks [npc.her] [npc.asshole+] on your [pc.cock+].",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you as [npc.she] sinks your [pc.cock+] deep into [npc.her] [npc.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.asshole+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] violently thrusts [npc.her] [npc.hips] back against you and continues roughly fucking [npc.herself] on your [pc.cock+].",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] dominantly holds you in place, violently pushing [npc.her] [npc.hips] out against your groin as [npc.she] roughly fucks [npc.her] [npc.asshole+] on your [pc.cock+].",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, roughly grinding [npc.herself] against you as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.asshole+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] [npc.hips] back against you and continues rapidly fucking [npc.herself] on your [pc.cock+].",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] firmly holds you in place, eagerly pushing [npc.her] [npc.hips] out against your groin as [npc.she] desperately fucks [npc.her] [npc.asshole+] on your [pc.cock+].",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you as [npc.she] happily forces your [pc.cock+] deep into [npc.her] [npc.asshole+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DOMINANT),new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static final SexAction PLAYER_ANAL_FUCKING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out of [npc.name]'s [npc.asshole+] and stop fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out of [npc.name]'s [npc.ass+], you dominantly slide your [pc.cockHead] up and down over [npc.her] [npc.asshole+] one last time before pulling your [pc.hips] back.",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.cock] back out of [npc.her] [npc.ass+], putting an end to your rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.name]'s [npc.ass+], you slide your [pc.cockHead] up and down over [npc.her] [npc.asshole+] one last time before pulling your [pc.hips] back.",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.cock] back out of [npc.her] [npc.ass+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull out of [npc.her] [npc.ass], but [npc.she] doesn't stop crying as [npc.she] continues to weakly struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you withdraw from [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.cock+] out of [npc.her] [npc.ass+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_COCK_ANALLY_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can only start fucking themselves on the player's cock in consensual sex or if they're the dom.
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			if(Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER)==null) {
				return (Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom());
			} else {
				return false; //(Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom()) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER).contains(OrificeType.VAGINA_PLAYER);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [pc.her] cock anally";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking your [pc.ass] on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerStartingAnalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] over [npc.her] [npc.ass+],"
										+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] against you, forcing you to penetrate [npc.her] [npc.asshole+].",
								"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] slowly pushes [npc.her] [npc.hips] against your groin,"
										+ " drawing a soft [npc.moan] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.her] [npc.asshole+] on your [pc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] over [npc.her] [npc.ass+],"
										+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] against you, forcing you to penetrate [npc.her] [npc.asshole+].",
								"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] [npc.hips] against your groin,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.her] [npc.asshole+] on your [pc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding [npc.herself] against you, [npc.name] guides your [pc.cock+] over [npc.her] [npc.ass+],"
										+ " letting out [npc.a_moan+] before roughly slamming [npc.her] [npc.hips] against you, forcing you to penetrate [npc.her] [npc.asshole+].",
								"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] violently slams [npc.her] [npc.hips] against your groin,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] roughly penetrates [npc.her] [npc.asshole+] on your [pc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] over [npc.her] [npc.ass+],"
										+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] against you, forcing you to penetrate [npc.her] [npc.asshole+].",
								"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] [npc.hips] against your groin,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.her] [npc.asshole+] on your [pc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] guides your [pc.cock+] over [npc.her] [npc.ass+],"
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] against you, forcing you to penetrate [npc.her] [npc.asshole+].",
								"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] pushes [npc.her] [npc.hips] against your groin,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.her] [npc.asshole+] on your [pc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] as you enter [npc.herHim], gently bucking your [pc.hips] as you start to fuck [npc.her] [npc.asshole+].",
								" With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.asshole+].",
								" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start energetically fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] forwards, before starting to ruthlessly fuck [npc.her] [npc.asshole+].",
								" With [pc.a_moan+], you roughly slam your [pc.hips] into [npc.her] [npc.ass], seeking to remind [npc.name] who's in charge as you start ruthlessly fucking [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.asshole+].",
								" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start energetically fucking [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] as you start fucking [npc.her] [npc.asshole+].",
								" With [pc.a_moan+], you thrust your [pc.hips] into [npc.her] [npc.ass], sinking your [pc.cock+] into [npc.her] [npc.asshole+] as you start fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to pull your [pc.cock] free from [npc.her] [npc.asshole].",
								" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anal ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [pc.ass] on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockAnallyGentle();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Gently pushing [npc.her] [npc.ass] into your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Slowly thrusting [npc.her] [npc.ass] into your groin, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.asshole+].");
			
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Normal anal ride";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [pc.ass] on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockAnallyNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly thrusting [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Enthusiastically thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.asshole+].");
					
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anal ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [npc.ass] on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockAnallyRough();
				
			} else {
				return UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.cock+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.ass] into your groin, roughly forcing your [pc.cock+] ever deeper into [npc.her] [npc.asshole+].",
					"Roughly thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.cock+] deep into [npc.her] [npc.asshole+].");
			
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.ass] out against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.asshole].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing [npc.herself] with both hands flat on the floor, [npc.name] starts to push back against you in time with your thrusts,"
								+ " letting out a series of [npc.moans+] as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"Letting out [npc.a_moan+], [npc.name] starts pushing [npc.herself] back in time with your thrusts, helping to slam your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"With a series of [npc.moans+], [npc.name] starts bucking back against you, helping to slam your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"Each time you thrust forwards, [npc.name] pushes [npc.herself] back, letting out moan after desperate moan as [npc.she] repeatedly forces your [pc.cock+] deep into [npc.her] [npc.asshole+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Thrusting [npc.her] [npc.ass] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] starts pushing [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.asshole+]."));
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.ass] out against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.asshole].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper into [npc.her] [npc.asshole+].",
					"Enthusiastically thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.asshole+]."));
					
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist anal";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.asshole+] as you continue gently fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.asshole+] as you continue eagerly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues eagerly sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.asshole+] as you continue roughly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues roughly slamming in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_ANALLY_FUCKED_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerStoppingAnalPenetrationDescription();
				
			} else {
			
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePartner()) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking your [pc.cock] out of [npc.her] [npc.asshole+], [npc.name] growls at you as [npc.she] commands you to stop fucking [npc.herHim].",
								"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.cock] out of [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding your [pc.cock] out of [npc.her] [npc.asshole+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fucking [npc.herHim].",
								"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] out of [npc.her] [npc.asshole+]."));
						break;
				}
				
				switch(Sex.getSexPacePlayer()) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
								" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] [npc.ass+].",
								" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.ass+]."));
						break;
				}
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
	};
}
