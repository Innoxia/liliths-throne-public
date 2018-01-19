package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

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
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class PlayerTailAnus {
	
	public static final SexAction PLAYER_TEASE_TAIL_OVER_ANUS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {
		@Override
		public String getActionTitle() {
			return "Anal tail tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [pc.tail+] over [npc.name]'s [npc.asshole+].";
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
							"Snaking your [pc.tail+] up to [npc.name]'s [npc.ass+], you start slowly teasing the [pc.tailTip] up and down over [npc.her] [npc.asshole+], ready to penetrate [npc.herHim] at any moment.",
							"With a soft [pc.moan], you snake the [pc.tailTip] of your [pc.tail+] up to [npc.name]'s [npc.ass+], before starting to gently slide it up and down over [npc.her] [npc.asshole+].",
							"Gently sliding the [pc.tailTip+] of your [pc.tail] up and down over [npc.name]'s [npc.asshole+], you let out a soft [pc.moan] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking your [pc.tail+] up to [npc.name]'s [npc.ass+], you start eagerly sliding the [pc.tailTip+] up and down over [npc.her] [npc.asshole+], ready to penetrate [npc.herHim] at any moment.",
							"With [pc.a_moan+], you bring your [pc.tail+] up to [npc.name]'s [npc.ass+], before starting to eagerly slide the [pc.tailTip] up and down over [npc.her] [npc.asshole+].",
							"Eagerly sliding the [pc.tailTip+] of your [pc.tail] up and down over [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.tail+] up against [npc.name]'s [npc.ass+], you pull it back a little before starting to slide the [pc.tailTip+] up and down over [npc.her] [npc.asshole+],"
									+ " ready to start fucking [npc.herHim] at any moment.",
							"With [pc.a_moan+], you bring your [pc.tail+] up to [npc.name]'s [npc.ass+], before starting to roughly grind the [pc.tailTip] up and down over [npc.her] [npc.asshole+].",
							"Roughly grinding the [pc.tailTip+] of your [pc.tail] up and down over [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] at the thought of being able to start fucking [npc.herHim] whenever you feel like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(Please! Fuck me! I need you inside of me!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading, [npc.speech(Go on! Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Fuck my ass! I need you inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+], [npc.speech(That's right, fuck me!)]",
							" [npc.Name] lets out a [npc.moan], before addressing you, [npc.speech(Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getActivePartner().isAssVirgin()) {
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
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getActivePartner(), PenetrationType.TAIL, OrificeType.ANUS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PARTNER_FORCE_TAIL_OVER_ANUS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Anal tail tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the tip of [pc.name]'s [pc.tail] over your [npc.asshole+].";
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
							"[npc.Name] grabs your [pc.tail+], before guiding it up to [npc.her] [npc.ass+],"
									+ " slowly pushing the [pc.tailTip+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With a soft [npc.moan], [npc.name] grabs your [pc.tail+] and guides it up to [npc.her] [npc.ass+], before starting to gently slide the [pc.tailTip] up and down over [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] gently slides the [pc.tailTip+] up against [npc.her] [npc.asshole+],"
									+ " letting out a soft [npc.moan] as [npc.she] teases you with the promise of penetration."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] grabs your [pc.tail+], before guiding it up to [npc.her] [npc.ass+],"
									+ " eagerly pushing the [pc.tailTip+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] grabs your [pc.tail+] and guides it up to [npc.her] [npc.ass+], before starting to eagerly slide the [pc.tailTip] up and down over [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] eagerly slides the [pc.tailTip+] up against [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] grabs your [pc.tail], before yanking it up to [npc.her] [npc.ass+],"
									+ " rubbing the [pc.tailTip+] up and down over [npc.her] [npc.asshole+] as [npc.she] teases you with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] grabs your [pc.tail+] and yanks it up against [npc.her] [npc.ass+], before starting to roughly force the [pc.tailTip] up and down over [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] roughly grinds the [pc.tailTip+] up against [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetration."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Let me fuck you already!)]",
							" You let out a desperate [pc.moan], before pleading with [npc.herHim], [pc.speech(Yes! Please, let me fuck you!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Let me fuck your ass! Please!)]"));
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
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.TAIL, OrificeType.ANUS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	
	public static final SexAction PLAYER_FUCKING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {
		
		@Override
		public String getActionTitle() {
			return "Tail-peg [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.tail+] into [npc.name]'s [npc.asshole+] and start fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
				
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [pc.tailTip+] of your [pc.tail] over [npc.name]'s [npc.ass+], you let out a little [pc.moan] before slowly pushing it forwards, sinking deep into [npc.her] [npc.asshole+].",
							"You position the [pc.tailTip+] of your [pc.tail] between [npc.name]'s cheeks, and with a slow, steady pressure, you gently sink it deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.tailTip+] of your [pc.tail] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] as you thrust it forwards, greedily sinking deep into [npc.her] [npc.asshole+].",
							"You position the [pc.tailTip+] of your [pc.tail] between [npc.name]'s cheeks, and with a determined thrust, you eagerly sink it deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [pc.tailTip+] of your [pc.tail] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] before violently slamming it forwards, forcing it deep into [npc.her] [npc.asshole+].",
							"You position the [pc.tailTip+] of your [pc.tail] between [npc.name]'s cheeks, and with a forceful thrust, you roughly slam it deep into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.tailTip+] of your [pc.tail] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] as you thrust it forwards, greedily sinking deep into [npc.her] [npc.asshole+].",
							"You position the [pc.tailTip+] of your [pc.tail] between [npc.name]'s cheeks, and with a determined thrust, you eagerly sink it deep into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [pc.tailTip+] of your [pc.tail] over [npc.name]'s [npc.ass+], you let out [pc.a_moan+] as you thrust it forwards, sinking deep into [npc.her] [npc.asshole+].",
							"You position the [pc.tailTip+] of your [pc.tail] between [npc.name]'s cheeks, and with a determined thrust, you sink it deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as you enter [npc.herHim], gently bucking [npc.her] [npc.hips] back as [npc.she] helps to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+].",
							" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] back, sinking your [pc.tail+] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back as [npc.she] helps to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] back, desperately helping to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], violently thrusting [npc.her] [npc.hips] back as [npc.she] roughly forces your [pc.tail+] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] back, roughly forcing you to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly bucking [npc.her] [npc.hips] back as [npc.she] helps to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] back, desperately helping to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], bucking [npc.her] [npc.hips] back as [npc.she] helps to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+].",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] back, helping to sink your [pc.tail+] even deeper into [npc.her] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.tail] pushes deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s [npc.asshole+] with your [pc.tail].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.tail+] into [npc.name]'s [npc.asshole+], you then start sliding it in and out, letting out a little [pc.moan] with every thrust as you slowly tail-fuck [npc.herHim].",
					"Slowly pushing your [pc.tail+] into [npc.name]'s [npc.asshole+], you gently thrust it in and out, letting out a little [pc.moan] as you feel the [pc.tailTip+] driving deep inside of [npc.herHim].",
					"Sliding your [pc.tail+] into [npc.name]'s [npc.asshole+], you let out a little [pc.moan] before starting to gently pump it in and out, breathing in [npc.her] [npc.scent] as you slowly tail-fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly bucks [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your [pc.tail], [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.asshole+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly starts bucking [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.asshole+] using your [pc.tail].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.tail+] into [npc.name]'s [npc.asshole+], you then start sliding it in and out, letting out [pc.a_moan+] with every thrust as you enthusiastically tail-fuck [npc.herHim].",
					"Eagerly pushing your [pc.tail+] into [npc.name]'s [npc.asshole+], you start thrusting it in and out, letting out [pc.a_moan+] as you feel the [pc.tailTip+] driving deep inside of [npc.herHim].",
					"Eagerly thrusting your [pc.tail+] into [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] as you start frantically pumping it in and out, breathing in [npc.her] [npc.scent] as you tail-fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly bucks [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your [pc.tail], [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.asshole+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly starts bucking [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
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
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s [npc.asshole+] using your [pc.tail+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming your [pc.tail+] deep into [npc.name]'s [npc.asshole+], you then start violently pumping it in and out, letting out [pc.a_moan+] with every thrust as you brutally tail-fuck [npc.herHim].",
					"Violently thrusting your [pc.tail+] deep into [npc.name]'s [npc.asshole+], you start slamming it in and out, letting out [pc.a_moan+] as you feel the [pc.tailTip+] driving deep inside of [npc.herHim].",
					"Ruthlessly thrusting your [pc.tail+] deep into [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] as you start violently slamming it in and out, breathing in [npc.her] [npc.scent] as you roughly tail-fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly bucks [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your [pc.tail], [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.asshole+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.asshole+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] [npc.hips] back into your [pc.tail], [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly starts bucking [npc.her] [npc.hips+] back, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.asshole+] using your [pc.tail+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.tail+] into [npc.name]'s [npc.asshole+], you then start sliding it in and out, letting out [pc.a_moan+] with every thrust as you tail-fuck [npc.herHim].",
					"Pushing your [pc.tail+] into [npc.name]'s [npc.asshole+], you start thrusting it in and out, letting out [pc.a_moan+] as you feel the [pc.tailTip+] driving deep inside of [npc.herHim].",
					"Thrusting your [pc.tail+] into [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] as you start pumping it in and out, breathing in [npc.her] [npc.scent] as you tail-fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] back in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] back, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly pushing [npc.her] [npc.hips] back, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] back, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly bucks [npc.her] [npc.hips] back in response, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] back, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] pushes [npc.her] [npc.hips] back, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
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
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.asshole+] using your [pc.tail+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.tail+] into [npc.name]'s [npc.asshole+], you then start eagerly sliding it in and out, letting out [pc.a_moan+] with every thrust as you enthusiastically tail-fuck [npc.herHim].",
					"Eagerly pushing your [pc.tail+] into [npc.name]'s [npc.asshole+], you start thrusting it in and out, letting out [pc.a_moan+] as you feel the [pc.tailTip+] driving deep inside of [npc.herHim].",
					"Eagerly thrusting your [pc.tail+] into [npc.name]'s [npc.asshole+], you let out [pc.a_moan+] as you start frantically pumping it in and out, breathing in [npc.her] [npc.scent] as you tail-fuck [npc.herHim]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] [npc.hips] back in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] [npc.hips] back, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly pushing [npc.her] [npc.hips] back, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] [npc.hips] back, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly bucks [npc.her] [npc.hips] back in response, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] [npc.hips] back in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] [npc.hips] back, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] pushes [npc.her] [npc.hips] back, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
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
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.tail] away from [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately trying, and failing, to pull your [pc.tail] free from [npc.name]'s [npc.asshole+], you can't help but let out [pc.a_sob+], pushing against [npc.herHim] as you weakly beg to be released.",
					"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, pleading for [npc.herHim] to get [npc.her] [npc.asshole+] off your [pc.tail].",
					"[pc.Sobbing] in distress, you weakly struggle against [npc.name], pleading for [npc.herHim] to let go of your [pc.tail]."));
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] slowly thrusts [npc.her] [npc.hips] back, letting out a soft [npc.moan] as [npc.she] continues gently fucking [npc.herself] on your [pc.tail+].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, totally ignoring your protests, [npc.she] gently pushes [npc.her] [npc.hips] back, continuing to slowly fuck [npc.herself] on your [pc.tail+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.her] [npc.hips] back and softly [npc.moaning] as [npc.she] sinks your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] roughly slams [npc.her] [npc.hips] back, letting out [npc.a_moan+] as [npc.she] continues violently fucking [npc.herself] on your [pc.tail+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests, [npc.she] forcefully thrusts [npc.her] [npc.hips] back, continuing to roughly fuck [npc.herself] on your [pc.tail+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, roughly grinding [npc.her] [npc.hips] back and [npc.moaning+] as [npc.she] violently forces your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] eagerly thrusts [npc.her] [npc.hips] back, letting out [npc.a_moan+] as [npc.she] continues happily fucking [npc.herself] on your [pc.tail+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests, [npc.she] eagerly thrusts [npc.her] [npc.hips] back, continuing to energetically fuck [npc.herself] on your [pc.tail+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.her] [npc.hips] back and [npc.moaning+] as [npc.she] forces your [pc.tail+] deep into [npc.her] [npc.asshole+]"));
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
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop tail-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.tail+] out of [npc.name]'s [npc.asshole+] and stop fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.tail+] out of [npc.name]'s [npc.ass+], you dominantly slide the [pc.tailTip] up and down over [npc.her] [npc.asshole+] one last time before pulling back.",
							"Thrusting deep inside [npc.name] one last time, you then yank your [pc.tail] back out of [npc.her] [npc.asshole+], putting an end to your rough tail-fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tail] out of [npc.name]'s [npc.ass+], you slide the [pc.tailTip] up and down over [npc.her] [npc.asshole+] one last time before pulling back.",
							"Pushing deep inside [npc.name] one last time, you then slide your [pc.tail] back out of [npc.her] [npc.asshole+], putting an end to your tail-fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull out of [npc.her] [npc.asshole+], and continues crying as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you withdraw from [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.tail+] out of [npc.her] [npc.asshole+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for more of your [pc.tail+]'s attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_TAIL_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Get tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking your [npc.asshole+] using [pc.name]'s [pc.tail+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing your [pc.tail+], [npc.name] guides it up to [npc.her] [npc.ass+], letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] back and forcing you to penetrate [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] lines it up to [npc.her] [npc.asshole+] before pushing [npc.her] [npc.hips] back,"
									+ " drawing a soft [npc.moan] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.tail+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing your [pc.tail+], [npc.name] eagerly guides it up to [npc.her] [npc.ass+], letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] back and forcing you to penetrate [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] lines it up to [npc.her] [npc.asshole+] before eagerly pushing [npc.her] [npc.hips] back,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.tail+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grabbing your [pc.tail+], [npc.name] forcefully yanks it up to [npc.her] [npc.ass+], letting out [npc.a_moan+] before roughly slamming [npc.her] [npc.hips] back and forcing you to penetrate [npc.her] [npc.asshole+].",
							"Forcefully grabbing your [pc.tail+], [npc.name] lines it up to [npc.her] [npc.asshole+] before violently slamming [npc.her] [npc.hips] back,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] roughly penetrates [npc.herself] on your [pc.tail+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing your [pc.tail+], [npc.name] eagerly guides it up to [npc.her] [npc.ass+], letting out [npc.a_moan+] before suddenly bucking [npc.her] [npc.hips] back and forcing you to penetrate [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] lines it up to [npc.her] [npc.asshole+] before eagerly pushing [npc.her] [npc.hips] back,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.tail+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing your [pc.tail+], [npc.name] guides it up to [npc.her] [npc.ass+], letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] back and forcing you to penetrate [npc.her] [npc.asshole+].",
							"Grabbing your [pc.tail+], [npc.name] lines it up to [npc.her] [npc.asshole+] before pushing [npc.her] [npc.hips] back,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.tail+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as you enter [npc.herHim], gently pushing your [pc.tail] forwards as you start to tail-fuck [npc.her] [npc.asshole+].",
							" With a soft [pc.moan], you gently push your [pc.tail] forwards, sinking it deep into [npc.her] [npc.asshole+] as you start tail-fucking [npc.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly pushing your [pc.tail] forwards as you start to tail-fuck [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.tail] forwards, sinking it deep into [npc.her] [npc.asshole+] as you start energetically tail-fucking [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.tail] forwards as you start to ruthlessly tail-fuck [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you roughly slam your [pc.tail] forwards, seeking to remind [npc.name] who's in charge as you start ruthlessly tail-fucking [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly pushing your [pc.tail] forwards as you start to tail-fuck [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.tail] forwards, sinking it deep into [npc.her] [npc.asshole+] as you start energetically tail-fucking [npc.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], pushing your [pc.tail] forwards as you start to tail-fuck [npc.her] [npc.asshole+].",
							" With [pc.a_moan+], you thrust your [pc.tail] forwards, sinking it deep into [npc.her] [npc.asshole+] as you start tail-fucking [npc.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.tail] inside of [npc.herHim], and, struggling against [npc.herHim], you desperately try to pull your [pc.tail] free from [npc.her] [npc.asshole+].",
							" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.tail] deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RIDING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [pc.name]'s [pc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a gentle backwards thrust of [npc.her] [npc.hips], [npc.name] lets out a soft [npc.moan] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
					"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.hips] back, forcing your [pc.tail+] ever deeper into [npc.her] [npc.asshole+].",
					"Slowly thrusting [npc.her] [npc.hips] back, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.tail+] deep into [npc.her] [npc.asshole+]."));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [pc.name]'s [pc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager backwards thrust of [npc.her] [npc.hips], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] back, forcing your [pc.tail+] ever deeper into [npc.her] [npc.asshole+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] back, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.tail+] deep into [npc.her] [npc.asshole+]."));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [pc.name]'s [pc.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a violent backwards thrust of [npc.her] [npc.hips], [npc.name] lets out [npc.a_moan+] as [npc.she] roughly slams your [pc.tail+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.hips] back, roughly forcing your [pc.tail+] ever deeper into [npc.her] [npc.asshole+].",
					"Roughly thrusting [npc.her] [npc.hips] back, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.tail+] deep into [npc.her] [npc.asshole+]."));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips back to help thrust [pc.name]'s [pc.tail] deep into your [npc.asshole].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a backwards thrust of [npc.her] [npc.hips], [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts bucking [npc.her] [npc.hips] back, forcing your [pc.tail+] ever deeper into [npc.her] [npc.asshole+].",
					"Thrusting [npc.her] [npc.hips] back, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.tail+] deep into [npc.her] [npc.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips back to help thrust [pc.name]'s [pc.tail] deep into your [npc.asshole].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager backwards thrust of [npc.her] [npc.hips], [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.tail+] deep into [npc.her] [npc.asshole+].",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.hips] back, forcing your [pc.tail+] ever deeper into [npc.her] [npc.asshole+].",
					"Enthusiastically thrusting [npc.her] [npc.hips] back, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.tail+] deep into [npc.her] [npc.asshole+]."));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist being tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [pc.name]'s [pc.tail+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.tail] out of [npc.her] [npc.asshole+] as you continue gently tail-fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] away from your unwanted penetration,"
									+ " struggling in desperation as your [pc.tail+] continues sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.tail+] continues gently sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.tail] out of [npc.her] [npc.asshole+] as you continue eagerly tail-fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.tail+] continues eagerly sliding in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.tail+] continues eagerly sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.tail] out of [npc.her] [npc.asshole+] as you continue roughly tail-fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.hips] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.tail+] continues roughly slamming in and out of [npc.her] [npc.asshole+].",
							"Trying desperately to pull [npc.her] [npc.hips] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.tail+] continues roughly slamming deep into [npc.her] [npc.asshole+]."));
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
			PenetrationType.TAIL,
			OrificeType.ANUS,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer()); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop being tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.tail] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.tail] out of [npc.her] [npc.asshole+], [npc.name] growls at you as [npc.she] commands you to stop tail-pegging [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.tail] out of [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tail] out of [npc.her] [npc.asshole+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop tail-pegging [npc.herHim].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.tail] out of [npc.her] [npc.asshole+]."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
							" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] stops you from tail-fucking [npc.her] [npc.asshole+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue tail-fucking [npc.her] [npc.asshole+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
