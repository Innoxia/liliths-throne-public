package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.universal.sub.SubCowgirl;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerPenisVagina {
	
	public static final SexAction PLAYER_TEASE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] pussy";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of your [pc.cock] over [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining your [pc.cock+] up to [npc.name]'s [npc.pussy+], you start slowly teasing the [pc.cockHead+] up and down between [npc.her] pussy lips, ready to penetrate [npc.herHim] at any moment.",
							"With a soft [pc.moan], you line your [pc.cock+] up to [npc.name]'s [npc.pussy+], before starting to gently slide the [pc.cockHead] up and down between [npc.her] folds.",
							"Gently sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.pussy+], you let out a soft [pc.moan] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining your [pc.cock+] up to [npc.name]'s [npc.pussy+], you start eagerly sliding your [pc.cockHead+] up and down between [npc.her] lips, ready to penetrate [npc.herHim] at any moment.",
							"With [pc.a_moan+], you line your [pc.cock+] up to [npc.name]'s [npc.pussy+], before starting to eagerly slide your [pc.cockHead] up and down between [npc.her] folds.",
							"Eagerly sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.cock+] up against [npc.name]'s [npc.pussy+], you pull back a little before starting to slide your [pc.cockHead+] up and down between [npc.her] lips,"
									+ " ready to start fucking [npc.herHim] at any moment.",
							"With [pc.a_moan+], you line your [pc.cock+] up to [npc.name]'s [npc.pussy+], before starting to roughly grind your [pc.cockHead] up and down between [npc.her] folds.",
							"Roughly grinding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] at the thought of being able to start fucking [npc.herHim] whenever you feel like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(Please! Fuck me! I need your cock inside of me!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading, [npc.speech(Go on! Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Fuck my little pussy! I need you inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+], [npc.speech(That's right, fuck me!)]",
							" [npc.Name] lets out a [npc.moan], before addressing you, [npc.speech(Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getPartner().isVaginaVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! I-I'm a virgin! You can't do this!)]",
								" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! I'm still a virgin!)]",
								" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! I don't want to lose my virginity!)]"));
						
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
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getPartner(), PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PARTNER_FORCE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease [pc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of [pc.name]'s [pc.cock] over your [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom() ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.pussy+],"
									+ " slowly pushing your [pc.cockHead+] up and down between [npc.her] lips as [npc.she] teases you with the promise of penetration at any moment.",
							"With a soft [npc.moan], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.pussy+], before starting to gently slide your [pc.cockHead] up and down between [npc.her] folds.",
							"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] gently slides your [pc.cockHead+] over [npc.her] folds,"
									+ " letting out a soft [npc.moan] as [npc.she] teases you with the promise of penetrating [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.pussy+],"
									+ " eagerly pushing your [pc.cockHead+] up and down between [npc.her] lips as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] lines your [pc.cock+] up to [npc.her] [npc.pussy+], before starting to eagerly slide your [pc.cockHead] up and down between [npc.her] folds.",
							"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] eagerly slides your [pc.cockHead+] over [npc.her] folds,"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetrating [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] grinds your [pc.cock+] against [npc.her] [npc.pussy+],"
									+ " rubbing your [pc.cockHead+] up and down between [npc.her] lips as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] grinds your [pc.cock+] against [npc.her] [npc.pussy+], before starting to roughly force your [pc.cockHead] up and down between [npc.her] folds.",
							"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] roughly grinds your [pc.cockHead+] over [npc.her] folds,"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetrating [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Let me fuck you already!)]",
							" You let out a desperate [pc.moan], before pleading with [npc.herHim], [pc.speech(Yes! Please, let me fuck you!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Let me fuck your pussy! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(Yes, I want to fuck you!)]",
							" You let out a [pc.moan], before calling out, [pc.speech(Please! Let me fuck you!)]",
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
			Sex.transferLubrication(Sex.getPartner(), Main.game.getPlayer(), PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	
	public static final SexAction PLAYER_FUCKING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER)==null;
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.cock+] into [npc.name]'s [npc.pussy+] and start fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to tease the [pc.cockHead+] of your [pc.cock] between [npc.her] outer labia,"
										+ " you let out a little [pc.moan] as you slowly push your [pc.hips] forwards and sink your [pc.cock+] into [npc.her] [npc.pussy+].",
								"Keeping a gentle grip on [npc.name]'s [npc.hips+],"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to tease the [pc.cockHead+] of your [pc.cock] between [npc.her] outer labia,"
										+ " you let out [pc.a_moan+] as you eagerly push your [pc.hips] forwards and sink your [pc.cock+] into [npc.her] [npc.pussy+].",
								"Keeping a firm grip on [npc.name]'s [npc.hips+],"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+], and with a determined thrust forwards, you eagerly sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on [npc.name]'s [npc.hips+], you shuffle forwards, and, after taking a moment to roughly grind the [pc.cockHead+] of your [pc.cock] against [npc.her] outer labia,"
										+ " you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards and forcing your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								"Keeping a strong grip on [npc.name]'s [npc.hips+], you look down as you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+],"
										+ " and with a forceful thrust forwards, you roughly slam your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly pushing back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts eagerly pushing back into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], pushing back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts pushing back into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly tries, and fails, to crawl away on all fours.",
								" With [npc.a_sob+], [npc.she] tries, in vain, to crawl away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {// Back-to-wall penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Leaning into [npc.name] as you push [npc.herHim] back against the wall, you slowly tease the [pc.cockHead+] of your [pc.cock] between [npc.her] outer labia,"
										+ " letting out a little [pc.moan] before slowly pushing your [pc.hips] forwards and sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"Leaning into [npc.name] as you push [npc.herHim] back against the wall,"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Leaning into [npc.name] as you push [npc.herHim] back against the wall, you eagerly tease the [pc.cockHead+] of your [pc.cock] between [npc.her] outer labia,"
										+ " letting out [pc.a_moan+] before bucking your [pc.hips] forwards and greedily sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"Leaning into [npc.name] as you push [npc.herHim] back against the wall,"
										+ " you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slamming [npc.name] back against the wall, you roughly grind the [pc.cockHead+] of your [pc.cock] against [npc.her] outer labia,"
										+ " letting out [pc.a_moan+] before violently slamming your [pc.hips] forwards and forcing your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								"Aggressively pushing [npc.name] back against the wall, you look down as you position the [pc.cockHead+] of your [pc.cock] between [npc.her] [npc.legs+],"
										+ " and with a forceful thrust of your [pc.hips], you roughly slam your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
								" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s outer labia, you let out a little [pc.moan] before slowly pushing your [pc.hips] forwards,"
										+ " sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"You position the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.legs+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s outer labia, you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"You position the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.legs+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [pc.cockHead+] of your [pc.cock] against [npc.name]'s outer labia, you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards,"
										+ " forcing your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								"You position the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.legs+], and with a forceful thrust of your [pc.hips], you roughly slam your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s outer labia, you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"You position the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.legs+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [pc.cockHead+] of your [pc.cock] between [npc.name]'s outer labia, you let out [pc.a_moan+] as you buck your [pc.hips] forwards, sinking your [pc.cock+] into [npc.her] [npc.pussy+].",
								"You position the [pc.cockHead+] of your [pc.cock] between [npc.name]'s [npc.legs+], and with a thrust of your [pc.hips], you sink your [pc.cock+] into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a soft [npc.moan] as you enter [npc.herHim], gently bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] into your crotch, sinking your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], violently thrusting [npc.her] [npc.hips] back against you as [npc.she] roughly forces your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] into your crotch, roughly forcing you to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+].",
								" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
								" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial="With a soft [pc.moan], you reach down and take hold of [npc.name]'s [npc.hips+], before slowly pushing yourself forwards and sinking your [pc.cock+] deep into [npc.her] [npc.pussy+]."
										+ " You stay in that position for a moment, pressing yourself up against [npc.herHim], before gently pulling back,"
										+ " aware that the barbs that line the sides of your cock can be painful if you pull out too fast, before starting to steadily fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial="With a soft [pc.moan], you reach down and take hold of [npc.name]'s [npc.hips+], before slowly pushing yourself forwards and sinking your [pc.cock+] deep into [npc.her] [npc.pussy+]."
										+ " You feel your flared head lewdly spreading out [npc.her] inner walls as you carry on gently pressing yourself against [npc.herHim] for a moment,"
										+ " before pulling back and starting to steadily fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial="With a soft [pc.moan], you reach down and take hold of [npc.name]'s [npc.hips+], before slowly pushing yourself forwards and sinking your [pc.cock+] deep into [npc.her] [npc.pussy+]."
										+ " You feel your fat knot lewdly pressing against [npc.her] pussy lips as you carry on gently pressing yourself against [npc.herHim] for a moment,"
										+ " before pulling back and starting to steadily fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial="With a soft [pc.moan], you reach down and take hold of [npc.name]'s [npc.hips+], before slowly pushing yourself forwards and sinking your [pc.cock+] deep into [npc.her] [npc.pussy+]."
										+ " You feel your ribbed shaft bumping up against [npc.her] [npc.clit] as you penetrate [npc.herHim], and, after gently pressing yourself against [npc.herHim] for a moment,"
										+ " you pull back and start to steadily fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial="With a soft [pc.moan], you reach down and take hold of [npc.name]'s [npc.hips+], before slowly pushing yourself forwards and sinking your [pc.cock+] deep into [npc.her] [npc.pussy+]."
							+ " You feel the little squirming tentacles lining your shaft eagerly massaging the inner walls of [npc.her] pussy, and, after gently pressing yourself against [npc.herHim] for a moment,"
							+ " you pull back and start to steadily fuck [npc.herHim] against the wall.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to take hold of [npc.name]'s [npc.hips+], you gently push [npc.herHim] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc.her] neck before starting to slowly slide your [pc.cock+] in and out of [npc.her] [npc.pussy+].",
						
						"Leaning in and pressing [npc.name] back against the wall, you let out a soft [pc.moan] as you start to gently pump your [pc.cock+] in and out of [npc.her] exposed [npc.pussy].",
						
						"Leaning into [npc.name], you take a deep breath of [npc.her] [npc.scent] as you gently press [npc.herHim] back against the wall, before starting to slowly slide your [pc.cock+] in and out of [npc.her] [npc.pussy+]."));
				
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sinking your [pc.cock+] into [npc.name]'s [npc.pussy+], you then start bucking your [pc.hips], softly pressing your groin against [npc.hers] with every thrust as you slowly fuck [npc.herHim].",
						"Slowly pushing your [pc.cock+] into [npc.name]'s [npc.pussy+], you gently thrust your [pc.hips] against [npc.herHim], letting out a little [pc.moan] as you fuck [npc.herHim].",
						"Sliding your [pc.cock+] into [npc.name]'s [npc.pussy+], you let out a little [pc.moan] before starting to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as you slowly fuck [npc.herHim]."));
				
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.pussy+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you enthusiastically fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.pussy+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.pussy+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s [npc.pussy+].";
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
								+ " letting out [pc.moans+] as you violently pound away at [npc.her] [npc.pussy+].";
				}
				
				// Hair special:
				if(Sex.getPartner().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, you reach down and grab a fistful of [npc.name]'s [npc.hair+],"
							+ " using your new leverage to pull [npc.herHim] back onto your [pc.cock+] as you start roughly fucking [npc.her] [npc.pussy+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, you knock [npc.name]'s [npc.arms] out from under [npc.herHim], causing [npc.her] [npc.face+] to be pressed down into the floor as you stoop over [npc.herHim],"
									+ " furiously slamming your [pc.cock+] in and out of [npc.her] [npc.pussy+] as you hold [npc.her] [npc.ass+] up in the air.",
							hairSpecial,
							"With a menacing growl, you roughly grab [npc.name]'s [npc.hips+], shuffling forwards a little and burying your [pc.cock+] deep in [npc.her] [npc.pussy+],"
									+ " before starting to furiously pump your hips back and forth, letting out a series of [pc.moans+] as you ruthlessly fuck [npc.herHim] like an animal.",
							"Roughly grabbing hold of [npc.name]'s waist, you start to rapidly pound your [pc.cock+] in and out of [npc.her] [npc.pussy+],"
									+ " letting out a series of [pc.moans+] as you slam into [npc.herHim] over and over again."));
				
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly pushes [npc.her] [npc.ass] back against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly pushing [npc.her] [npc.ass] back into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.ass+] back against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop abusing [npc.her] [npc.pussy+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to crawl away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly tries crawling away, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] pushes [npc.her] [npc.ass] back against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, pushing [npc.her] [npc.ass] back into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.ass+] back out against you,"
										+ " begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial="With a wolfish grin, you reach down and grab [npc.name]'s [npc.hips+], before slamming yourself forwards and burying your [pc.cock+] deep in [npc.her] [npc.pussy+]."
										+ " You stay in that position for a moment, roughly grinding yourself up against [npc.herHim], before violently pulling back,"
										+ " roughly raking the barbs that line the sides of your cock against [npc.her] inner walls, before starting to furiously fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial="With a wolfish grin, you reach down and grab [npc.name]'s [npc.hips+], before slamming yourself forwards and burying your [pc.cock+] deep in [npc.her] [npc.pussy+]."
										+ " You feel your flared head lewdly spreading out [npc.her] inner walls as you carry on roughly grinding yourself against [npc.herHim] for a moment,"
										+ " before pulling back and starting to dominantly fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial="With a wolfish grin, you reach down and grab [npc.name]'s [npc.hips+], before slamming yourself forwards and burying your [pc.cock+] deep in [npc.her] [npc.pussy+]."
										+ " You feel your fat knot lewdly pressing against [npc.her] pussy lips as you carry on roughly grinding yourself against [npc.herHim] for a moment,"
										+ " before pulling back and starting to dominantly fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial="With a wolfish grin, you reach down and grab [npc.name]'s [npc.hips+], before slamming yourself forwards and burying your [pc.cock+] deep in [npc.her] [npc.pussy+]."
										+ " You feel your ribbed shaft bumping up against [npc.her] [npc.clit] as you penetrate [npc.herHim], and, after roughly grinding yourself against [npc.herHim] for a moment,"
										+ " you pull back and starting to dominantly fuck [npc.herHim] against the wall.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial="With a wolfish grin, you reach down and grab [npc.name]'s [npc.hips+], before slamming yourself forwards and burying your [pc.cock+] deep in [npc.her] [npc.pussy+]."
							+ " You feel the little squirming tentacles lining your shaft eagerly massaging the inner walls of [npc.her] pussy, and, after roughly grinding yourself against [npc.herHim] for a moment,"
							+ " you pull back and start to dominantly fuck [npc.herHim] against the wall.";
				}
				
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down and grabbing [npc.name]'s [npc.hips+], you roughly push [npc.herHim] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc.her] neck before starting to ruthlessly slam your [pc.cock+] in and out of [npc.her] [npc.pussy+].",
						
						"Reaching down to hook a hand under one of [npc.name]'s [npc.legs], you hoist it up in the air, giving you unobstructed access to [npc.her] [npc.pussy+]."
								+ " Pushing [npc.herHim] back against the wall, you lift [npc.her] [npc.leg] as high as you can before starting to roughly pound your [pc.cock+] in and out of [npc.her] exposed [npc.pussy].",
						
						"Leaning into [npc.name], you take a deep breath of [npc.her] [npc.scent] as you pin [npc.herHim] back against the wall, before starting to ruthlessly pound your [pc.cock+] in and out of [npc.her] [npc.pussy+]."));
				
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop abusing [npc.her] [npc.pussy+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you then start violently pumping your [pc.hips], grinding into [npc.her] groin with every thrust as you brutally fuck [npc.herHim].",
						"Violently thrusting your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you start slamming your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you roughly fuck [npc.herHim].",
						"Ruthlessly thrusting your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you start violently thrusting your [pc.hips], breathing in [npc.her] [npc.scent] as you roughly fuck [npc.herHim]."));
				
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] eagerly thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] eagerly grinds [npc.her] [npc.hips+] out against you,"
										+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop abusing [npc.her] [npc.pussy+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.pussy+].",
								" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.pussy+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
								" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] into you, [npc.she] begs for you to carry on fucking [npc.herHim].",
								" [npc.Moaning] in delight, [npc.name] grinds [npc.her] [npc.hips+] out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
						break;
				}
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.cock+] into [npc.name]'s [npc.pussy+], you then start bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you fuck [npc.herHim].",
					"Pushing your [pc.cock+] into [npc.name]'s [npc.pussy+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Thrusting your [pc.cock+] into [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you start bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.pussy+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] groin with every thrust as you enthusiastically fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.pussy+], you start thrusting your [pc.hips] into [npc.herHim], letting out [pc.a_moan+] as you fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.pussy+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly grinds [npc.herself] against you, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly grinds [npc.herself] against you, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] grinds [npc.herself] against you, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.pussy+], you can't help but let out [pc.a_sob+], pushing against [npc.herHim] as you weakly beg to be released.",
					" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, pleading for [npc.herHim] to get [npc.her] [npc.pussy+] off your [pc.cock].",
					" [pc.Sobbing] in distress, you weakly struggle against [npc.name], pleading for [npc.herHim] to let go of your [pc.cock]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] slowly thrusts [npc.her] [npc.hips] out against you, letting out a soft [npc.moan] as [npc.she] continues gently fucking [npc.herself] on your [pc.cock+].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] gently pushes [npc.her] [npc.hips] out against your groin, before continuing to fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you and softly [npc.moaning] as [npc.she] sinks your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] roughly slams [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues violently fucking [npc.herself] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] forcefully thrusts [npc.her] [npc.hips] out against your groin, before continuing to roughly fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests,"
									+ " roughly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] violently forces your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] eagerly thrusts [npc.her] [npc.hips] out against you, letting out [npc.a_moan+] as [npc.she] continues happily fucking [npc.herself] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] [npc.hips] out against your groin, before continuing to energetically fuck [npc.herself] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.pussy+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fucking [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out of [npc.name]'s [npc.pussy+] and stop fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out of [npc.name]'s [npc.pussy+], you dominantly slide your [pc.cockHead] up and down between [npc.her] folds one last time before pulling your [pc.hips] back.",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.cock] back out of [npc.her] [npc.pussy+], putting an end to your rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.name]'s [npc.pussy+], you slide your [pc.cockHead] up and down between [npc.her] folds one last time before pulling your [pc.hips] back.",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.cock] back out of [npc.her] [npc.pussy+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull out of [npc.her] [npc.pussy], but [npc.she] doesn't stop crying as [npc.she] continues to weakly struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you withdraw from [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.cock+] out of [npc.her] [npc.pussy+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_COCK_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can only start fucking themselves on the player's cock in consensual sex or if they're the dom.
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER)==null) {
				return (Sex.isConsensual() || !Sex.isPlayerDom());
			} else {
				return false; //(Sex.isConsensual() || !Sex.isPlayerDom()) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER).contains(OrificeType.VAGINA_PLAYER);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [pc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerStartingVaginalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] over [npc.her] outer labia,"
										+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.pussy+].",
								"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] slowly pushes [npc.her] [npc.hips] forwards,"
										+ " drawing a soft [npc.moan] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] up to [npc.her] outer labia,"
										+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.pussy+].",
								"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] [npc.hips] forwards,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding [npc.herself] against you, [npc.name] guides your [pc.cock+] up to [npc.her] outer labia,"
										+ " letting out [npc.a_moan+] before roughly slamming [npc.her] [npc.hips] forwards, forcing you to penetrate [npc.her] [npc.pussy+].",
								"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] violently slams [npc.her] [npc.hips] forwards,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] roughly starts fucking [npc.herself] on your [pc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] up to [npc.her] outer labia,"
										+ " letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.pussy+].",
								"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] [npc.hips] forwards,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.herself] against you, [npc.name] guides your [pc.cock+] up to [npc.her] outer labia,"
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards and forcing you to penetrate [npc.her] [npc.pussy+].",
								"Lining [npc.her] [npc.pussy+] up to your [pc.cock+], [npc.name] pushes [npc.her] [npc.hips] forwards,"
										+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] as you enter [npc.herHim], gently bucking your [pc.hips] as you start to fuck [npc.her] [npc.pussy+].",
								" With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.pussy+].",
								" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start energetically fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] forwards, before starting to ruthlessly fuck [npc.her] [npc.pussy+].",
								" With [pc.a_moan+], you roughly slam your [pc.hips] into [npc.her] groin, seeking to remind [npc.name] who's in charge as you start ruthlessly fucking [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.pussy+].",
								" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start energetically fucking [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] as you start fucking [npc.her] [npc.pussy+].",
								" With [pc.a_moan+], you thrust your [pc.hips] into [npc.her] groin, sinking your [pc.cock+] into [npc.her] [npc.pussy+] as you start fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to pull your [pc.cock] free from [npc.her] [npc.pussy].",
								" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockGentle();
				
			} else if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {// Back-to-wall descriptions:
				
				return UtilText.returnStringAtRandom(
						"Reaching down to gently take hold of your [pc.hips+], [npc.name] starts bucking back and forth, pressing you against the wall as [npc.she] repeatedly impales [npc.her] [npc.pussy+] on your [pc.cock+].",
						"Gently pressing you back against the wall, [npc.name] starts to gently buck [npc.her] [npc.hips] into your groin, [npc.moaning] softly into your [pc.ear] as [npc.she] slowly fucks [npc.herself] on your [pc.cock+].",
						"With a soft [npc.moan], [npc.name] pushes [npc.her] [npc.hips] forwards, impaling [npc.her] [npc.pussy+] on your [pc.cock+], before gently sliding back and starting to fuck you against the wall.",
						"Leaning in and breathing hotly down on your  neck, [npc.name] starts to buck [npc.her] [npc.hips] back and forth, gently fucking [npc.herself] on your [pc.cock+] as [npc.she] [npc.moansVerb] hotly into your [pc.ear].");
				
			} else { // Default descriptions:
			
				return UtilText.returnStringAtRandom(
						"Gently pushing [npc.her] [npc.hips] out against your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.hips], forcing your [pc.cock+] ever deeper into [npc.her] [npc.pussy+].",
						"Slowly thrusting [npc.her] [npc.hips] against you, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.pussy+].");
			
			}
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Normal ride";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.pussy+].",
						"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.pussy+].");
			}
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerRidingCockRough();
				
			} else if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PLAYER) {
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial = "With a wolfish grin, [npc.name] reaches up and grabs your shoulders, before slamming [npc.her] [npc.hips] forwards and impaling [npc.her] [npc.pussy+] on your [pc.cock+]."
										+ " Forcefully pressing you up against the wall and breathing hotly down on your neck,"
										+ " [npc.she] suddenly thrusts back, roughly raking your cock's barbs against [npc.her] inner walls as [npc.she] starts to aggressively fuck [npc.herself] on your [pc.cock].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial = "With a wolfish grin, [npc.name] reaches up and grabs your shoulders, before slamming [npc.her] [npc.hips] forwards and impaling [npc.her] [npc.pussy+] on your [pc.cock+]."
										+ " You feel the wide, flared head of your cock spreading [npc.herHim] out as [npc.she] presses you up against the wall, before thrusting back and starting to roughly fuck [npc.herself] on your [pc.cock+].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial = "With a wolfish grin, [npc.name] reaches up and grabs your shoulders, before slamming [npc.her] [npc.hips] forwards and impaling [npc.her] [npc.pussy+] on your [pc.cock+]."
										+ " [npc.She] grinds [npc.her] lips against your fat knot, forcefully pressing you up against the wall and breathing hotly down on your neck,"
										+ " before thrusting back and starting to roughly fuck [npc.herself] on your [pc.cock].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial = "With a wolfish grin, [npc.name] reaches up and grabs your shoulders, before slamming [npc.her] [npc.hips] forwards and impaling [npc.her] [npc.pussy+] on your [pc.cock+]."
										+ " You feel your ribbed cock bumping over [npc.her] [npc.clit+] as [npc.she] presses you up against the wall, before thrusting back and starting to roughly fuck [npc.herself] on your [pc.cock+].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial = "With a wolfish grin, [npc.name] reaches up and grabs your shoulders, before slamming [npc.her] [npc.hips] forwards and impaling [npc.her] [npc.pussy+] on your [pc.cock+]."
										+ " You feel the little tentacle-like nodules lining your cock massage and stroke [npc.her] inner walls as [npc.she] forces you up against the wall,"
										+ " before thrusting back and starting to roughly fuck [npc.herself] on your [pc.cock+].";
				}
				
				
				return UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to roughly take hold of your hips, [npc.name] violently pushes you back against the wall,"
								+ " stepping forwards and breathing hotly down on your neck before starting to ruthlessly slam [npc.her] [npc.pussy+] down over and over again on your [pc.cock+].",
						
						"Stepping forwards, [npc.name] impales [npc.herself] on your [pc.cock+], breathing down hotly onto your neck as [npc.she] roughly pins you against the wall,"
								+ " before starting to aggressively buck [npc.her] [npc.hips] against you, slamming your [pc.cock+] into [npc.her] [npc.pussy+] as [npc.she] [npc.moansVerb+] into your [pc.ear].",
						
						"[npc.Name] leans into you, and you find yourself taking in a deep breath of [npc.her] [npc.scent] as [npc.she] roughly pins you against the wall."
								+ " Planting [npc.her] feet on either side of you, [npc.she] then starts rapidly thrusting away at your groin, letting out a series of [npc.moans+] as [npc.she] relentlessly rides your [pc.cock+].");
				
			} else {
			
				return UtilText.returnStringAtRandom(
						"Violently slamming [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.hips] against you, roughly forcing your [pc.cock+] ever deeper into [npc.her] [npc.pussy+].",
						"Roughly thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.cock+] deep into [npc.her] [npc.pussy+].");
					
			}
		}

	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			

			if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing [npc.herself] with both hands flat on the floor, [npc.name] starts to push back against you in time with your thrusts,"
								+ " letting out a series of [npc.moans+] as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"Letting out [npc.a_moan+], [npc.name] starts pushing [npc.herself] back in time with your thrusts, helping to slam your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"With a series of [npc.moans+], [npc.name] starts bucking back against you, helping to slam your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"Each time you thrust forwards, [npc.name] pushes [npc.herself] back, letting out moan after desperate moan as [npc.she] repeatedly forces your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
				
			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
						"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.pussy+].",
						"Thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.hips] out against your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] against you, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.pussy+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.pussy+] away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.pussy+] as you continue gently fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues sliding in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.pussy+] as you continue eagerly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues eagerly sliding in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.pussy+] as you continue roughly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues roughly slamming in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_FUCKED_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			if(Sex.getPosition()==SexPosition.COWGIRL_PARTNER_TOP) {
				
				return SubCowgirl.getPartnerStoppingVaginalPenetrationDescription();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePartner()) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking your [pc.cock] out of [npc.her] [npc.pussy+], [npc.name] growls at you as [npc.she] commands you to stop fucking [npc.herHim].",
								"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.cock] out of [npc.her] [npc.pussy+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding your [pc.cock] out of [npc.her] [npc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fucking [npc.herHim].",
								"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] out of [npc.her] [npc.pussy+]."));
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
								" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] [npc.pussy+].",
								" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.pussy+]."));
						break;
				}
				
				return UtilText.nodeContentSB.toString();
			}
		}
	};
	
	public static final SexAction PARTNER_PUSSY_CONTROL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getVaginaType()==VaginaType.DEMON_COMMON;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"As [npc.name] lets out [npc.a_moan+], you suddenly feel the tentacles and demonic muscles within her [npc.pussy] start to expertly squeeze and massage your [pc.cock+].",
					
					"[npc.Name] lets out a cheeky giggle, and you suddenly feel the tentacles lining [npc.her] [npc.pussy] wriggle and squeeze down on your [pc.cock+], causing you to let out an involuntary cry of pleasure.",
					
					"[npc.Name]'s [npc.moans] fall into a steady rhythm, and you feel the muscles and little writhing tentacles"
							+ " deep within her [npc.pussy+] start squeezing down on your [pc.cock] in time to the lewd sounds that [npc.she]'s making.",
					
					"As you bury your [pc.cock+] deep in [npc.name]'s [npc.pussy], [npc.she] lets out [npc.a_moan+], and you suddenly feel the tentacles deep within [npc.her] [npc.pussy+] gripping down and massaging your throbbing shaft.");
		}
	};
	
}
