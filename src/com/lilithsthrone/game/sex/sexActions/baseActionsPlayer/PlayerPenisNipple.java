package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
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
public class PlayerPenisNipple {
	
	public static final SexAction PLAYER_TEASE_COCK_OVER_NIPPLE = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] nipple";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of your [pc.cock] over [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() ||Sex.isConsensual()) && Sex.getPartner().isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning your [pc.cock+] over one of [npc.name]'s [npc.breasts+], you start slowly teasing the [pc.cockHead+] up and down over [npc.her] [npc.nipple+], ready to penetrate [npc.herHim] at any moment.",
							"With a soft [pc.moan], you position your [pc.cock+] over one of [npc.name]'s [npc.breasts+], before starting to gently slide the [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Gently sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.nipple+], you let out a soft [pc.moan] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.cock+] over one of [npc.name]'s [npc.breasts+], you start eagerly sliding the [pc.cockHead+] up and down over [npc.her] [npc.nipple+], ready to start fucking [npc.herHim] at any moment.",
							"With [pc.a_moan+], you position your [pc.cock+] over one of [npc.name]'s [npc.breasts+], before starting to roughly grind the [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Roughly grinding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] at the thought of being able to start fucking [npc.herHim] whenever you feel like it."));
					break;
				default: // DOM_NORMAL and for consensual sub positions
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning your [pc.cock+] over one of [npc.name]'s [npc.breasts+], you start eagerly sliding the [pc.cockHead+] up and down over [npc.her] [npc.nipple+], ready to penetrate [npc.herHim] at any moment.",
							"With [pc.a_moan+], you position your [pc.cock+] over one of [npc.name]'s [npc.breasts+], before starting to eagerly slide the [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Eagerly sliding the [pc.cockHead+] of your [pc.cock] up and down over [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] at the thought of being able to penetrate [npc.herHim] whenever you feel like it."));
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(Please! Fuck me! I need your cock inside of me!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading, [npc.speech(Go on! Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Fuck my tits! I need you inside of me!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getPartner().isNippleVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! I-I've never done this before! You can't do this!)]",
								" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! I've never done this before!)]",
								" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Don't do this!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",
								" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",
								" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default: // SUB_NORMAL and for consensual dom positions
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+], [npc.speech(That's right, fuck me!)]",
							" [npc.Name] lets out a [npc.moan], before addressing you, [npc.speech(Please! Fuck me already!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, fuck me already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getPartner(), PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_FORCE_COCK_OVER_NIPPLE = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Tease [pc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [pc.cockHead] of [pc.name]'s [pc.cock] over your [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Sex.isPlayerDom() ||Sex.isConsensual()) && Sex.getPartner().isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc.her] [npc.breasts], [npc.name] lines your [pc.cock+] up to one of [npc.her] [npc.nipples+],"
									+ " slowly pushing your [pc.cockHead+] up and down over the fuckable orifice as [npc.she] teases you with the promise of penetration.",
							"With a soft [npc.moan], [npc.name] positions your [pc.cock+] over one of [npc.her] [npc.breasts+], before starting to gently slide your [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Positioning your [pc.cock+] over one of [npc.her] [npc.breasts+], [npc.name] gently slides your [pc.cockHead+] over [npc.her] [npc.nipple+],"
									+ " letting out a soft [npc.moan] as [npc.she] teases you with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc.her] [npc.breasts], [npc.name] grinds your [pc.cock+] against of [npc.her] [npc.nipples+],"
									+ " rubbing your [pc.cockHead+] up and down over the fuckable orifice as [npc.she] teases you with the promise of penetration.",
							"With [npc.a_moan+], [npc.name] grinds your [pc.cock+] against one of [npc.her] [npc.breasts+], before starting to roughly force your [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Positioning your [pc.cock+] over one of [npc.her] [npc.breasts+], [npc.name] roughly grinds your [pc.cockHead+] over [npc.her] [npc.nipple+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetration."));
					break;
				default: // DOM_NORMAL and for consensual sub positions
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc.her] [npc.breasts], [npc.name] lines your [pc.cock+] up to one of [npc.her] [npc.nipples+],"
									+ " eagerly pushing your [pc.cockHead+] up and down over the fuckable orifice as [npc.she] teases you with the promise of penetration.",
							"With [npc.a_moan+], [npc.name] positions your [pc.cock+] over one of [npc.her] [npc.breasts+], before starting to eagerly slide your [pc.cockHead] up and down over [npc.her] [npc.nipple+].",
							"Positioning your [pc.cock+] over one of [npc.her] [npc.breasts+], [npc.name] eagerly slides your [pc.cockHead+] over [npc.her] [npc.nipple+],"
									+ " letting out [npc.a_moan+] as [npc.she] teases you with the promise of penetration."));
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Let me fuck you already!)]",
							" You let out a desperate [pc.moan], before pleading with [npc.herHim], [pc.speech(Yes! Please, let me fuck you!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Let me fuck your tits! Please!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
							" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
							" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					break;
				default:// SUB_NORMAL and for consensual dom positions
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(Yes, I want to fuck you!)]",
							" You let out a [pc.moan], before calling out, [pc.speech(Please! Let me fuck you!)]",
							" You [pc.moan] out loud as you speak to [npc.herHim], [pc.speech(Come on, let me fuck you already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getPartner(), Main.game.getPlayer(), PenetrationType.PENIS_PLAYER, OrificeType.NIPPLE_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	
	public static final SexAction PLAYER_FUCKING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc.her] nipple";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.cock+] into [npc.name]'s [npc.nipple+] and start fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], you let out a little [pc.moan] before slowly pushing your [pc.hips] forwards,"
									+ " sinking your [pc.cock+] into [npc.her] [npc.nipple+].",
							"You position the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], and with a slow, steady pressure, you gently sink your [pc.cock+] into [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.nipple+].",
							"You position the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], you let out [pc.a_moan+] before violently slamming your [pc.hips] forwards,"
									+ " forcing your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							"You position the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], and with a forceful thrust of your [pc.hips], you roughly slam your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, greedily sinking your [pc.cock+] into [npc.her] [npc.nipple+].",
							"You position the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], and with a determined thrust of your [pc.hips], you eagerly sink your [pc.cock+] into [npc.her] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], you let out [pc.a_moan+] as you buck your [pc.hips] forwards, sinking your [pc.cock+] into [npc.her] [npc.nipple+].",
							"You position the [pc.cockHead+] of your [pc.cock] over [npc.name]'s [npc.breast+], and with a thrust of your [pc.hips], you sink your [pc.cock+] into [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as you enter [npc.herHim], gently pushing [npc.her] chest back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+].",
							" With a soft [npc.moan], [npc.she] starts gently pushing [npc.her] chest into your crotch, sinking your [pc.cock+] even deeper into [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly pushing [npc.her] chest back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] starts eagerly pushing [npc.her] chest into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], violently thrusting [npc.her] chest out against you as [npc.she] roughly forces your [pc.cock+] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] starts violently pushing [npc.her] chest into your crotch, roughly forcing you to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], eagerly pushing [npc.her] chest back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] starts eagerly pushing [npc.her] chest into your crotch, desperately helping to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you enter [npc.herHim], pushing [npc.her] chest back against you as [npc.she] helps to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] starts pushing [npc.her] chest into your crotch, helping to sink your [pc.cock+] even deeper into [npc.her] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you enter [npc.herHim], and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to pull out as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from your unwanted penetration, tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep into [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.cock+] into [npc.name]'s [npc.nipple+], you then start bucking your [pc.hips], softly pressing your groin into [npc.her] [npc.breast+] with every thrust as you slowly nipple-fuck [npc.herHim].",
					"Slowly pushing your [pc.cock+] into [npc.name]'s [npc.nipple+], you gently thrust your [pc.hips] against [npc.her] [npc.breast+], letting out a little [pc.moan] as you nipple-fuck [npc.herHim].",
					"Sliding your [pc.cock+] into [npc.name]'s [npc.nipple+], you let out a little [pc.moan] before starting to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as you slowly nipple-fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly thrusts [npc.her] chest out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] pushes [npc.her] chest out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.nipple+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] [npc.breast+] with every thrust as you enthusiastically nipple-fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.nipple+], you start thrusting your [pc.hips] into [npc.her] [npc.breast+], letting out [pc.a_moan+] as you nipple-fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you nipple-fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly thrusts [npc.her] chest out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] pushes [npc.her] chest out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you then start violently pumping your [pc.hips], grinding into [npc.her] [npc.breast+] with every thrust as you brutally nipple-fuck [npc.herHim].",
					"Violently thrusting your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you start slamming your [pc.hips] into [npc.her] [npc.breast+], letting out [pc.a_moan+] as you roughly nipple-fuck [npc.herHim].",
					"Ruthlessly slamming your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] as you start violently thrusting your [pc.hips], breathing in [npc.her] [npc.scent] as you roughly nipple-fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] eagerly thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] enthusiastically helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, eagerly thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] eagerly thrusts [npc.her] chest out against you,"
									+ " eagerly begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from your groin, [npc.name] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips] as [npc.she] weakly tries to push you away, tears streaming down [npc.her] face as [npc.she] pleads for you to pull out of [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, and with tears running down [npc.her] cheeks, [npc.name] weakly struggles against you, pleading and crying for you to pull out of [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] thrusts [npc.her] chest against you, letting out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+], and, thrusting [npc.her] chest out against you, [npc.she] begs for you to carry on fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.name] pushes [npc.her] chest out against you, begging for you to continue fucking [npc.herHim] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.cock+] into [npc.name]'s [npc.nipple+], you then start bucking your [pc.hips], slamming into [npc.her] [npc.breast+] with every thrust as you nipple-fuck [npc.herHim].",
					"Pushing your [pc.cock+] into [npc.name]'s [npc.nipple+], you start thrusting your [pc.hips] into [npc.her] [npc.breast+], letting out [pc.a_moan+] as you nipple-fuck [npc.herHim].",
					"Thrusting your [pc.cock+] into [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] as you start bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you nipple-fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] chest out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] chest out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly pushes [npc.her] chest out, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] chest out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] chest out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly thrusts [npc.her] chest out, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] chest out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] chest out into your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] pushes [npc.her] ches out, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking your [pc.cock+] into [npc.name]'s [npc.nipple+], you then start eagerly bucking your [pc.hips], slamming into [npc.her] [npc.breast+] with every thrust as you enthusiastically nipple-fuck [npc.herHim].",
					"Eagerly pushing your [pc.cock+] into [npc.name]'s [npc.nipple+], you start thrusting your [pc.hips] into [npc.her] [npc.breast+], letting out [pc.a_moan+] as you nipple-fuck [npc.herHim].",
					"Eagerly thrusting your [pc.cock+] into [npc.name]'s [npc.nipple+], you let out [pc.a_moan+] as you start frantically bucking your [pc.hips], breathing in [npc.her] [npc.scent] as you nipple-fuck [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] slowly thrusts [npc.her] chest out in response, letting out a soft [npc.moan] as [npc.she] starts gently imploring you to continue fucking [npc.herHim].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pushing [npc.her] chest out against your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] slowly pushes [npc.her] chest out, softly [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] violently thrusts [npc.her] chest out in response, letting out [npc.a_moan+] as [npc.she] roughly demands that you continue fucking [npc.herHim].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, roughly thrusting [npc.her] chest out into your groin, [npc.she] orders you to continue fucking [npc.herHim].",
							" [npc.She] roughly thrusts [npc.her] chest out, ordering you to continue as [npc.her] aggressive movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] thrusts [npc.her] chest out in response, letting out [npc.a_moan+] as [npc.she] implores you to continue fucking [npc.herHim].",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], and, pushing [npc.her] chest out into your groin, [npc.she] begs for you to continue fucking [npc.herHim].",
							" [npc.Moaning] in delight, [npc.she] pushes [npc.her] ches out, [npc.moaning] for you to continue as [npc.her] movements cause you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.nipple+], you can't help but let out [pc.a_sob+], pushing against [npc.herHim] as you weakly beg to be released.",
					" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, pleading for [npc.herHim] to let you go.",
					" [pc.Sobbing] in distress, you weakly struggle against [npc.name], pleading for [npc.herHim] to let go of your [pc.cock]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] slowly thrusts [npc.her] chest out against you, letting out a soft [npc.moan] as [npc.she] continues gently fucking [npc.her] [npc.nipple+] on your [pc.cock+].",
							" A soft [npc.moan] drifts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] gently pushes [npc.her] chest out into your groin, continuing to fuck [npc.her] [npc.nipple+] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you and softly [npc.moaning] as [npc.she] sinks your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] roughly slams [npc.her] chest out against you, letting out [npc.a_moan+] as [npc.she] continues violently fucking [npc.her] [npc.nipple+] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] forcefully thrusts [npc.her] chest out into your groin, continuing to roughly fuck [npc.her] [npc.nipple+] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests,"
									+ " roughly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] violently forces your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring your protests, [npc.she] eagerly thrusts [npc.her] chest out against you, letting out [npc.a_moan+] as [npc.she] continues happily fucking [npc.her] [npc.nipple+] on your [pc.cock+].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], and, totally ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] chest out against your groin, before continuing to energetically fuck [npc.her] [npc.nipple+] on your [pc.cock+].",
							" [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you and [npc.moaning+] out loud as [npc.she] forces your [pc.cock+] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF) ,new ListValue<>(Fetish.FETISH_DOMINANT),new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static final SexAction PLAYER_FUCKING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() ||Sex.isConsensual(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fucking [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out of [npc.name]'s [npc.nipple+] and stop fucking [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out of [npc.name]'s [npc.breast+], you dominantly slide your [pc.cockHead] up and down over [npc.her] [npc.nipple+] one last time before pulling your [pc.hips] back.",
							"Thrusting deep inside [npc.name]'s [npc.breast+] one last time, you then yank your [pc.cock] back out of [npc.her] [npc.nipple+], putting an end to your rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.name]'s [npc.breast+], you slide your [pc.cockHead] up and down over [npc.her] [npc.nipple+] one last time before pulling your [pc.hips] back.",
							"Pushing deep inside [npc.name]'s [npc.breast+] one last time, you then slide your [pc.cock] back out of [npc.her] [npc.nipple+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you pull out of [npc.her] [npc.nipple], and continues to cry as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.name] continues to struggle against you, tears streaming down [npc.her] [npc.face] as you withdraw from [npc.her] [npc.breast+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you pull your [pc.cock+] out of [npc.her] [npc.nipple+].",
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
			OrificeType.NIPPLE_PARTNER) {

		@Override
		public String getActionTitle() {
			return "Get nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Use [pc.name]'s [pc.cock+] to fuck your [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] over one of [npc.her] [npc.breasts+],"
									+ " letting out a little [npc.moan] before gently pushing [npc.her] chest out and forcing you to penetrate [npc.her] [npc.nipple+].",
							"Lining one of [npc.her] [npc.nipples+] up to your [pc.cock+], [npc.name] slowly pushes [npc.her] chest forwards,"
									+ " drawing a soft [npc.moan] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] over one of [npc.her] [npc.breasts+],"
									+ " letting out [npc.a_moan+] before suddenly pushing [npc.her] chest forwards and forcing you to penetrate [npc.her] [npc.nipple+].",
							"Lining one of [npc.her] [npc.nipples+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] chest forwards,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc.herself] against you, [npc.name] guides your [pc.cock+] over one of [npc.her] [npc.breasts+],"
									+ " letting out [npc.a_moan+] before roughly slamming [npc.her] chest forwards, forcing you to penetrate [npc.her] [npc.nipple+].",
							"Lining one of [npc.her] [npc.nipples+] up to your [pc.cock+], [npc.name] violently slams [npc.her] chest forwards,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] roughly starts fucking [npc.herself] on your [pc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] over one of [npc.her] [npc.breasts+],"
									+ " letting out [npc.a_moan+] before suddenly pushing [npc.her] chest forwards and forcing you to penetrate [npc.her] [npc.nipple+].",
							"Lining one of [npc.her] [npc.nipples+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] chest forwards,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] happily penetrates [npc.herself] on your [pc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] guides your [pc.cock+] over one of [npc.her] [npc.breasts+],"
									+ " letting out [npc.a_moan+] before pushing [npc.her] chest forwards and forcing you to penetrate [npc.her] [npc.nipple+].",
							"Lining one of [npc.her] [npc.nipples+] up to your [pc.cock+], [npc.name] pushes [npc.her] chest forwards,"
									+ " drawing [npc.a_moan+] from between [npc.her] [npc.lips] as [npc.she] penetrates [npc.herself] on your [pc.cock+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as you enter [npc.herHim], gently bucking your [pc.hips] as you start to fuck [npc.her] [npc.nipple+].",
							" With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.her] [npc.breast+], sinking your [pc.cock+] into [npc.her] [npc.nipple+] as you start fucking [npc.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.nipple+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] [npc.breast+], sinking your [pc.cock+] into [npc.her] [npc.nipple+] as you start energetically fucking [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] forwards, before starting to ruthlessly fuck [npc.her] [npc.nipple+].",
							" With [pc.a_moan+], you roughly slam your [pc.hips] into [npc.her] [npc.breast+], seeking to remind [npc.name] who's in charge as you start ruthlessly fucking [npc.her] [npc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], eagerly bucking your [pc.hips] as you start fucking [npc.her] [npc.nipple+].",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] into [npc.her] [npc.breast+], sinking your [pc.cock+] into [npc.her] [npc.nipple+] as you start energetically fucking [npc.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you enter [npc.herHim], bucking your [pc.hips] as you start fucking [npc.her] [npc.nipple+].",
							" With [pc.a_moan+], you thrust your [pc.hips] into [npc.her] [npc.breast+], sinking your [pc.cock+] into [npc.her] [npc.nipple+] as you start fucking [npc.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+] as [npc.she] forces your [pc.cock] inside of [npc.herHim], and, struggling against [npc.herHim] in vain, you desperately try to pull your [pc.cock] free from [npc.her] [npc.nipple].",
							" With [pc.a_sob+], you struggle against [npc.name] as [npc.she] forces your [pc.cock] deep into [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gently nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.breast+] out into your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
					"With a soft [npc.moan], [npc.name] gently starts pushing [npc.her] chest out against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.nipple+].",
					"Slowly thrusting [npc.her] chest out, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.breast+] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
					"With [npc.a_moan+], [npc.name] starts energetically pushing [npc.her] chest out against you, forcing your [pc.cock+] ever deeper into [npc.her] [npc.nipple+].",
					"Enthusiastically thrusting [npc.her] chest out, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.breast+] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.cock+] deep into [npc.her] [npc.nipple+].",
					"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] chest out against you, roughly forcing your [pc.cock+] ever deeper into [npc.her] [npc.nipple+].",
					"Roughly thrusting [npc.her] chest out, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
				
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Thrust your chest out against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Thrusting [npc.her] [npc.breast+] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
					"With [npc.a_moan+], [npc.name] starts thrusting [npc.her] chest out, forcing your [pc.cock+] ever deeper into [npc.her] [npc.nipple+].",
					"Thrusting [npc.her] chest out, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your chest out against [pc.name] as [pc.her] [pc.cock] thrusts into your [npc.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.breast+] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep into [npc.her] [npc.nipple+].",
					"With [npc.a_moan+], [npc.name] starts energetically thrusting [npc.her] chest out, forcing your [pc.cock+] ever deeper into [npc.her] [npc.nipple+].",
					"Enthusiastically thrusting [npc.her] chest out, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep into [npc.her] [npc.nipple+]."));
					
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PARTNER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.breasts+] away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.nipple+] as you continue gently fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.breasts+] away from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues sliding in and out of [npc.her] [npc.nipple+].",
							"Trying desperately to pull [npc.her] [npc.breasts+] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding deep into [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.nipple+] as you continue eagerly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.breasts+] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues eagerly sliding in and out of [npc.her] [npc.nipple+].",
							"Trying desperately to pull [npc.her] [npc.breasts+] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding deep into [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to pull your [pc.cock] out of [npc.her] [npc.nipple+] as you continue roughly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.breasts+] back from your unwanted penetration,"
									+ " struggling in desperation as your [pc.cock+] continues roughly slamming in and out of [npc.her] [npc.nipple+].",
							"Trying desperately to pull [npc.her] [npc.breasts+] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming deep into [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_FUCKED_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.NIPPLE_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out of your [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.cock] out of [npc.her] [npc.breast+], [npc.name] growls at you as [npc.she] commands you to stop fucking [npc.her] [npc.nipple+].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.cock] out of [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.her] [npc.breast+], [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fucking [npc.her] [npc.nipple+].",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] out of [npc.her] [npc.nipple+]."));
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
							" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] [npc.nipple+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] [npc.nipple+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
