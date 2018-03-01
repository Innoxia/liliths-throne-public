package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
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
 * @version 0.1.84
 * @author Innoxia
 */
public class PartnerPenisVagina {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER) {
		@Override
		public String getActionTitle() {
			return "Tease [pc.her] pussy";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of your [npc.cock] over [pc.name]'s [pc.pussy+].";
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
							"Lining [npc.her] [npc.cock+] up to your [pc.pussy+], [npc.name] starts slowly teasing the [npc.cockHead+] up and down between your pussy lips, ready to penetrate you at any moment.",
							"With a soft [npc.moan], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.pussy+], before starting to gently slide the [npc.cockHead] up and down between your folds.",
							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.pussy+], [npc.name] lets out a soft [npc.moan] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against your [pc.pussy+], [npc.name] pulls back a little before starting to slide the [npc.cockHead+] up and down between your pussy lips,"
									+ " ready to start fucking you at any moment.",
							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.pussy+], before starting to roughly grind the [npc.cockHead] up and down between your folds.",
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] at the thought of being able to start fucking you whenever [npc.she] feels like it."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining [npc.her] [npc.cock+] up to your [pc.pussy+], [npc.name] starts eagerly sliding the [npc.cockHead+] up and down between your pussy lips, ready to penetrate you at any moment.",
							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to your [pc.pussy+], before starting to eagerly slide the [npc.cockHead] up and down between your folds.",
							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over your [pc.pussy+], [npc.name] lets out [npc.a_moan+] at the thought of being able to penetrate you whenever [npc.she] feels like it."));
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Fuck me! I need your cock inside of me!)]",
							" You let out a desperate [pc.moan], before pleading, [pc.speech(Go on! Please! Fuck me already!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Fuck my little pussy! I need you inside of me!)]"));
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
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS, OrificeType.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.name]'s [npc.cock] over your [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING && (Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl());
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you line [npc.name]'s [npc.cock+] up to your [pc.pussy+],"
									+ " slowly pushing the [npc.cockHead+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With a soft [pc.moan], you line [npc.name]'s [npc.cock+] up to your [pc.pussy+], before starting to gently slide the [npc.cockHead] up and down between your folds.",
							"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you gently slide the [npc.cockHead+] over your folds,"
									+ " letting out a soft [pc.moan] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you grind [npc.name]'s [npc.cock+] against your [pc.pussy+],"
									+ " rubbing the [npc.cockHead+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you grind [npc.name]'s [npc.cock+] against your [pc.pussy+], before starting to roughly force the [npc.cockHead] up and down between your folds.",
							"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you roughly grind the [npc.cockHead+] over your folds,"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting your [pc.hips], you line [npc.name]'s [npc.cock+] up to your [pc.pussy+],"
									+ " eagerly pushing the [npc.cockHead+] up and down between your pussy lips as you tease [npc.herHim] with the promise of penetration at any moment.",
							"With [pc.a_moan+], you line [npc.name]'s [npc.cock+] up to your [pc.pussy+], before starting to eagerly slide the [npc.cockHead] up and down between your folds.",
							"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you eagerly slide the [npc.cockHead+] over your folds,"
									+ " letting out [pc.a_moan+] as you tease [npc.herHim] with the promise of penetrating your [pc.pussy+]."));
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
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getActivePartner(), PenetrationType.PENIS, OrificeType.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Main.game.getPlayer(), PenetrationType.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [pc.name]'s [pc.pussy+] and start fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia,"
										+ " [npc.she] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"Keeping a gentle grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia,"
										+ " [npc.she] lets out [npc.a_moan+] before eagerly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"Keeping a firm grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a determined thrust forwards, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on your [pc.hips+], [npc.name] shuffles forwards, and, after taking a moment to roughly grind the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia,"
										+ " [npc.she] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"Keeping a strong grip on your [pc.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a forceful thrust forwards, [npc.she] roughly slams [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly pushing back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start eagerly pushing back into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, pushing back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start pushing back into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly try, and fail, to crawl away on all fours.",
								" With [pc.a_sob+], you try, in vain, to crawl away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] leans into you, pushing you back against the wall before slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia,"
										+ " letting out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] leans into you, pushing you back against the wall as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+],"
										+ " and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] leans into you, pushing you back against the wall before eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia,"
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards and greedily sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] leans into you, pushing you back against the wall as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+],"
										+ " and with a determined thrust of [npc.her] [npc.hips], [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slamming you back against the wall, [npc.name] roughly grinds the [npc.cockHead+] of [npc.her] [npc.cock] against your outer labia,"
										+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								"Aggressively pushing you back against the wall, [npc.name] looks down as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+],"
										+ " and with a forceful thrust of [npc.her] [npc.hips], [npc.she] roughly slams [npc.her] [npc.cock+] deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start bucking your [pc.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
								" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia, [npc.name] lets out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
										+ " sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] against your outer labia, [npc.name] lets out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
										+ " forcing [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a forceful thrust, [npc.she] roughly slams [npc.her] [npc.cock+] deep into your [pc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a determined thrust, [npc.she] eagerly sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] between your outer labia, [npc.name] lets out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into your [pc.pussy+].",
								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between your [pc.legs+], and with a thrust of [npc.her] [npc.hips], [npc.she] sinks [npc.her] [npc.cock+] into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] as [npc.she] enters you, gently bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With a soft [pc.moan], you start gently bucking your [pc.hips] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, violently thrusting your [pc.hips] back against [npc.herHim] as you roughly force [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start violently bucking your [pc.hips] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, eagerly bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start eagerly bucking your [pc.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] as [npc.she] enters you, bucking your [pc.hips] back against [npc.herHim] as you help to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+].",
								" With [pc.a_moan+], you start bucking your [pc.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_sob+] as [npc.she] enters you, and, with tears running down your [pc.face], you beg for [npc.herHim] to pull out as you weakly struggle against [npc.herHim].",
								" With [pc.a_sob+], you try, in vain, to pull away from [npc.name]'s unwanted penetration, tears running down your [pc.face] as [npc.her] unwelcome [npc.cock] pushes deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
			
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle fuck";
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
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial="With a soft [npc.moan], [npc.name] reaches down and takes hold of your [pc.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into your [pc.pussy+]."
										+ " [npc.She] stays in that position for a moment, pressing [npc.herself] up against you, before gently pulling back,"
										+ " clearly aware that the barbs that line the sides of [npc.her] cock could be painful if [npc.she] pulled out too fast, before starting to steadily fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial="With a soft [npc.moan], [npc.name] reaches down and takes hold of your [pc.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into your [pc.pussy+]."
										+ " You feel [npc.her] flared head lewdly spreading out your inner walls as [npc.she] carries on gently pressing [npc.herself] against you for a moment,"
										+ " before pulling back and starting to steadily fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial="With a soft [npc.moan], [npc.name] reaches down and takes hold of your [pc.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into your [pc.pussy+]."
										+ " You feel [npc.her] fat knot lewdly pressing against your pussy lips as [npc.she] carries on gently pressing [npc.herself] against you for a moment,"
										+ " before pulling back and starting to steadily fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial="With a soft [npc.moan], [npc.name] reaches down and takes hold of your [pc.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into your [pc.pussy+]."
										+ " You feel [npc.her] ribbed shaft bumping up against your [pc.clit] as [npc.she] penetrates you, and, after gently pressing [npc.herself] against you for a moment,"
										+ " [npc.she] pulls back and starts to steadily fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial="With a soft [npc.moan], [npc.name] reaches down and takes hold of your [pc.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into your [pc.pussy+]."
										+ " You feel the little squirming tentacles lining [npc.her] shaft eagerly massaging the inner walls of your pussy, and, after gently pressing [npc.herself] against you for a moment,"
										+ " [npc.she] pulls back and start to steadily fuck you against the wall.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to take hold of your [pc.hips+], [npc.name] gently pushes you back against the wall,"
								+ " stepping forwards and breathing hotly down on your neck before starting to slowly slide [npc.her] [npc.cock+] in and out of your [pc.pussy+].",
						
						"Leaning in and pressing you back against the wall, [npc.name] lets out a soft [npc.moan] as [npc.she] starts to gently pump [npc.her] [npc.cock+] in and out of your exposed [pc.pussy].",
						
						"Leaning into you, [npc.name] takes a deep breath of your [pc.scent] as [npc.she] gently presses you back against the wall, before starting to slowly slide [npc.her] [npc.cock+] in and out of your [pc.pussy+]."));
				
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sinking [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] starts bucking [npc.her] [npc.hips] into you, softly pressing [npc.her] groin against yours with every thrust as [npc.she] slowly fucks you.",
						"Slowly pushing [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] softly thrusts [npc.her] [npc.hips] against you, letting out a little [npc.moan] as [npc.she] gently fucks you.",
						"Sliding [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] lets out a little [npc.moan] as [npc.she] starts to gently buck [npc.her] [npc.hips], breathing in your [pc.scent] as [npc.she] slowly fucks you."));
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
				}
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
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
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] enthusiastically fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] greedily thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues fucking you.",
					"Enthusiastically thrusting [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips],"
							+ " breathing in your [pc.scent] as [npc.she] eagerly fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to pull out of your [pc.pussy+].",
							" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
							" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough fuck";
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
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) { // Doggy-style descriptions:
				
				String tailSpecial="",  hairSpecial="";
				
				// Tail special:
				if(Main.game.getPlayer().getTailType()!=TailType.NONE){
						tailSpecial = "Grabbing the base of your [pc.tail+], [npc.name] starts roughly pulling you back onto [npc.her] [npc.cock+] in time with [npc.her] thrusts,"
								+ " letting out [npc.moans+] as [npc.she] violently pounds away at your [pc.pussy+].";
				}
				
				// Hair special:
				if(Main.game.getPlayer().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, [npc.name] reaches down and grabs a fistful of your [pc.hair+],"
							+ " using [npc.her] new leverage to pull you back onto [npc.her] [npc.cock+] as [npc.she] starts roughly fucking your [pc.pussy+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, [npc.name] knocks your [pc.arms] out from under you, causing your [pc.face+] to be pressed down into the floor as [npc.she] stoops over you,"
									+ " furiously slamming [npc.her] [npc.cock+] in and out of your [pc.pussy+] as [npc.she] holds your [pc.ass+] up in the air.",
							hairSpecial,
							"With a menacing growl, [npc.name] roughly grabs your [pc.hips+], shuffling forwards a little and burying [npc.her] [npc.cock+] deep in your [pc.pussy+],"
									+ " before starting to furiously pump [npc.her] hips back and forth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks you like an animal.",
							"Roughly grabbing hold of your waist, [npc.name] starts to rapidly pound [npc.her] [npc.cock+] in and out of your [pc.pussy+],"
									+ " letting out a series of [npc.moans+] as [npc.she] slams into your [pc.ass] over and over again."));
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly push your [pc.ass] back against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly pushing your [pc.ass] back into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.ass+] back against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from [npc.name], you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to crawl away from [npc.name], tears streaming down your [pc.face] as you plead for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly try to crawl away from [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.ass] back against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.ass] back into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.ass+] back against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial="With a wolfish grin, [npc.name] reaches down and grabs your [pc.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in your [pc.pussy+]."
										+ " [npc.She] stays in that position for a moment, roughly grinding [npc.herself] up against you, before violently pulling back,"
										+ " roughly raking the barbs that line the sides of [npc.her] cock against your vaginal walls, before starting to furiously fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial="With a wolfish grin, [npc.name] reaches down and grabs your [pc.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in your [pc.pussy+]."
										+ " You feel [npc.her] flared head lewdly spreading out your inner walls as [npc.she] carries on roughly grinding [npc.herself] against you for a moment,"
										+ " before pulling back and starting to dominantly fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial="With a wolfish grin, [npc.name] reaches down and grabs your [pc.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in your [pc.pussy+]."
										+ " You feel [npc.her] fat knot lewdly pressing against your pussy lips as [npc.she] carries on roughly grinding [npc.herself] against you for a moment,"
										+ " before pulling back and starting to dominantly fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial="With a wolfish grin, [npc.name] reaches down and grabs your [pc.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in your [pc.pussy+]."
										+ " You feel [npc.her] ribbed shaft bumping up against your [pc.clit] as [npc.she] penetrates you, and, after roughly grinding [npc.herself] against you for a moment,"
										+ " [npc.she] pulls back and starts to dominantly fuck you against the wall.";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial="With a wolfish grin, [npc.name] reaches down and grabs your [pc.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in your [pc.pussy+]."
							+ " You feel the little squirming tentacles lining [npc.her] shaft eagerly massaging the inner walls of your pussy, and, after roughly grinding [npc.herself] against you for a moment,"
							+ " [npc.she] pulls back and starts to dominantly fuck you against the wall.";
				}
				
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down and grabbing your [pc.hips+], [npc.name] roughly pushes you back against the wall,"
								+ " stepping forwards and breathing hotly down on your neck before starting to ruthlessly slam [npc.her] [npc.cock+] in and out of your [pc.pussy+].",
						
						"Reaching down to hook a hand under one of your [pc.legs], [npc.name] hoists it up in the air, giving [npc.herHim] unobstructed access to your [pc.pussy+]."
								+ " Pushing you back against the wall, [npc.she] lifts your [pc.leg] as high as [npc.she] can before starting to roughly pound [npc.her] [npc.cock+] in and out of your exposed [pc.pussy].",
						
						"Leaning into you, [npc.name] takes a deep breath of your [pc.scent] as [npc.she] pins you back against the wall, before starting to ruthlessly pound [npc.her] [npc.cock+] in and out of your [pc.pussy+]."));
				
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] deep into your [pc.pussy+], [npc.name] starts violently pumping [npc.her] [npc.hips], grinding into your groin with every thrust as [npc.she] brutally fucks you.",
						"Violently thrusting [npc.her] [npc.cock+] deep into your [pc.pussy+], [npc.name] starts slamming [npc.her] [npc.hips] into you, letting out [npc.a_moan+] as [npc.she] roughly fucks you.",
						"Ruthlessly thrusting [npc.her] [npc.cock+] deep into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts violently thrusting [npc.her] [pc.hips] back and forth,"
								+ " breathing in your [pc.scent] as [npc.she] roughly fucks you."));
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You eagerly thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you enthusiastically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] bursts out from between your [pc.lips+], and, eagerly thrusting your [pc.hips] into [npc.herHim], you beg for [npc.name] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you eagerly grind your [pc.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.name]'s groin, you let out [pc.a_sob+], tears streaming down your [pc.face] as you weakly beg for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, tears streaming down your [pc.face] as you plead for [npc.herHim] to stop abusing your [pc.pussy+].",
								" [pc.Sobbing] in distress, and with tears running down your [pc.face], you weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of your [pc.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You thrust your [pc.hips] against [npc.name], letting out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
								" [pc.A_moan+] drifts out from between your [pc.lips+], and, thrusting your [pc.hips] into [npc.name], you beg for [npc.herHim] to carry on fucking you like this.",
								" [pc.Moaning] in delight, you grind your [pc.hips+] against [npc.name], begging for [npc.herHim] to continue fucking you as your movements help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
						break;
				}
			
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
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
					"Sinking [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] starts bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] fucks you.",
					"Pushing [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues fucking you.",
					"Thrusting [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts bucking [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as [npc.she] fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager fuck";
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
					"Desperately sinking [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] starts eagerly bucking [npc.her] [npc.hips] into you, slamming into your groin with every thrust as [npc.she] enthusiastically fucks you.",
					"Eagerly pushing [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] thrusts [npc.her] [npc.hips] against you, letting out [npc.a_moan+] as [npc.she] continues desperately fucking you.",
					"Eagerly thrusting [npc.her] [npc.cock+] into your [pc.pussy+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bucking [npc.her] [npc.hips] forwards, breathing in your [pc.scent] as [npc.she] fucks you."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly buck your [pc.hips] in response, letting out a soft [pc.moan] as you start gently imploring [npc.name] to continue fucking you.",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, gently pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you slowly grind yourself against [npc.name], softly [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You violently buck your [pc.hips] in response, letting out [pc.a_moan+] as you roughly demand that [npc.name] continues fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, roughly thrusting your [pc.hips] out against [npc.name]'s groin, you order [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you roughly grind yourself against [npc.name], ordering [npc.herHim] to continue as your aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] in response, letting out [pc.a_moan+] as you start imploring [npc.name] to continue fucking you.",
							" [pc.A_moan+] drifts out from between your [pc.lips+], and, pushing your [pc.hips] out against [npc.name]'s groin, you beg for [npc.herHim] to continue fucking you.",
							" [pc.Moaning] in delight, you grind yourself against [npc.name], [pc.moaning] for [npc.herHim] to continue as your movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [pc.name]'s [pc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from your [pc.pussy+], [npc.name] lets out [npc.a_sob+], pushing against you as [npc.she] weakly begs to be released.",
					" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as [npc.she] weakly tries to push you away, pleading for you to take your [pc.pussy+] off [npc.her] [npc.cock].",
					" [npc.Sobbing] in distress, [npc.name] weakly struggles against you, pleading for you to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you slowly thrust your [pc.hips] out against [npc.name], letting out a soft [pc.moan] as you continue gently fucking yourself on [npc.her] [npc.cock+].",
							" A soft [pc.moan] drifts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you gently push your [pc.hips] out against [npc.her] groin, before continuing to fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, slowly grinding yourself against [npc.herHim] and softly [pc.moaning] as you sink [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you roughly slam your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue violently fucking yourself on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you forcefully thrust your [pc.hips] out against [npc.her] groin, before continuing to roughly fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, roughly grinding yourself against [npc.herHim] and [pc.moaning+] out loud as you violently force [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, you eagerly thrust your [pc.hips] out against [npc.name], letting out [pc.a_moan+] as you continue happily fucking yourself on [npc.her] [npc.cock+].",
							" [pc.A_moan+] bursts out from between your [pc.lips+], and, totally ignoring [npc.name]'s protests,"
									+ " you eagerly push your [pc.hips] out against [npc.her] groin, before continuing to energetically fuck yourself on [npc.her] [npc.cock+].",
							" [pc.Moaning] in delight, you totally ignore [npc.name]'s protests, eagerly grinding yourself against [npc.herHim] and [pc.moaning+] out loud as you force [npc.her] [npc.cock+] deep into your [pc.pussy+]"));
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
			OrificeType.VAGINA,
			SexParticipantType.PITCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fucking [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [pc.name]'s [pc.pussy+] and stop fucking [pc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of your [pc.pussy+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between your folds one last time before pulling [npc.her] [npc.hips] back.",
							"Thrusting deep inside of you one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of your [pc.pussy+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of your [pc.pussy+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between your folds one last time before pulling [npc.her] [npc.hips] back.",
							"Pushing deep inside of you one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of your [pc.pussy+], putting an end to your fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You can't help but let out [pc.sob+] as [npc.name] pulls out of your [pc.pussy], and you continue crying and protesting as you continue to weakly struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to struggle against [npc.name], tears streaming down your [pc.face] as [npc.she] withdraws from your [pc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] pulls [npc.her] [npc.cock+] out of your [pc.pussy+], eager for more of [npc.her] attention.",
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
			OrificeType.VAGINA,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only start fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// You can't penetrate if you're already fucking your partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Main.game.getPlayer(), PenetrationType.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()));
			} else {
				return false; //(Sex.isConsensual() || Sex.isDom(Main.game.getPlayer())) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerStartingVaginalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you slowly slide [npc.her] [npc.cock+] over your outer labia,"
										+ " letting out a little [pc.moan] before gently bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
								"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you slowly push your [pc.hips] forwards, letting out a soft [pc.moan] as you penetrate yourself on [npc.her] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to your outer labia,"
										+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
								"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you eagerly push your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding yourself against [npc.name], you guide [npc.her] [npc.cock+] up to your outer labia,"
										+ " letting out [pc.a_moan+] before roughly slamming your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
								"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you violently slam your [pc.hips] forwards, letting out [pc.a_moan+] as you roughly start fucking yourself on [npc.her] [npc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you eagerly guide [npc.her] [npc.cock+] up to your outer labia,"
										+ " letting out [pc.a_moan+] before desperately bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
								"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you eagerly push your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing yourself against [npc.name], you guide [npc.her] [npc.cock+] up to your outer labia,"
										+ " letting out [pc.a_moan+] before bucking your [pc.hips] forwards and forcing [npc.herHim] to penetrate your [pc.pussy+].",
								"Lining your [pc.pussy+] up to [npc.name]'s [npc.cock+], you push your [pc.hips] forwards, letting out [pc.a_moan+] as you penetrate yourself on [npc.her] [npc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out a soft [npc.moan] as [npc.she] enters you, gently bucking [npc.her] [npc.hips] as [npc.she] starts to fuck your [pc.pussy+].",
								" With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts fucking you."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.pussy+].",
								" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts energetically fucking you."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, and, seeking to remind you who's in charge,"
										+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck your [pc.pussy+].",
								" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into your groin, seeking to remind you who's in charge as [npc.she] starts ruthlessly fucking your [pc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, eagerly bucking [npc.her] [npc.hips] as [npc.she] starts enthusiastically fucking your [pc.pussy+].",
								" With [npc.a_moan+], [npc.name] eagerly thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts energetically fucking you."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] as [npc.she] enters you, bucking [npc.her] [npc.hips] into your groin as [npc.she] starts fucking your [pc.pussy+].",
								" With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your groin, sinking [npc.her] [npc.cock+] into your [pc.pussy+] as [npc.she] starts fucking you."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_sob+] as you force [npc.her] [npc.cock] inside of you, and, struggling against you, [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from your [pc.pussy+].",
								" With [npc.a_sob+], [npc.name] struggles against you as you force [npc.her] [npc.cock] deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
			
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
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockGentle();
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				return UtilText.returnStringAtRandom(
						"Reaching down to gently take hold of [npc.name]'s hips, you start bucking back and forth, pressing [npc.herHim] against the wall as you repeatedly impale your [pc.pussy+] on [npc.her] [npc.cock+].",
						"Gently pressing [npc.name] back against the wall, you start to gently buck your [pc.hips] into [npc.her] groin, [pc.moaning] softly into [npc.her] [npc.ear] as you slowly fuck yourself on [npc.her] [pc.cock+].",
						"With a soft [pc.moan], you push your [pc.hips] forwards, impaling your [pc.pussy+] on [npc.name]'s [npc.cock+], before gently sliding back and starting to fuck [npc.herHim] against the wall.",
						"Leaning in and breathing hotly down on [npc.name]'s neck, you start to buck your [pc.hips] back and forth, gently fucking yourself on [npc.her] [npc.cock+] as you [pc.moan] hotly into [npc.her] [npc.ear].");
				
			} else { // Default descriptions:
			
				return UtilText.returnStringAtRandom(
						"Gently pushing your [pc.hips] out against [npc.name]'s groin, you let out a soft [pc.moan] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"With a soft [pc.moan], you gently start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.pussy+].",
						"Slowly thrusting your [pc.hips] against [npc.name], a soft [pc.moan] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.pussy+].");
			
			}
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.pussy+].",
						"Enthusiastically thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.pussy+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck yourself on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerRidingCockRough();
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial = "With a wolfish grin, you reach up and grab [npc.name]'s shoulders, before slamming your [pc.hips] forwards and impaling your [pc.pussy+] on [npc.her] [npc.cock+]."
										+ " Forcefully pressing [npc.herHim] up against the wall and breathing hotly down on [npc.her] neck,"
										+ " you quickly pull back, roughly raking [npc.her] cock's barbs against your inner walls and letting out [pc.a_moan+] as you start to aggressively fuck yourself on [npc.her] [npc.cock].";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial = "With a wolfish grin, you reach up and grab [npc.name]'s shoulders, before slamming your [pc.hips] forwards and impaling your [pc.pussy+] on [npc.her] [npc.cock+]."
										+ " You feel the wide, flared head of [npc.her] cock spreading you out as you press [npc.herHim] up against the wall, before pulling back and starting to roughly fuck yourself on [npc.her] [npc.cock+].";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial = "With a wolfish grin, you reach up and grab [npc.name]'s shoulders, before slamming your [pc.hips] forwards and impaling your [pc.pussy+] on [npc.her] [npc.cock+]."
										+ " You grind your lips against [npc.her] fat knot, forcefully pressing [npc.herHim] up against the wall and breathing hotly down on [npc.her] neck,"
										+ " before thrusting back and starting to roughly fuck yourself on [npc.her] [npc.cock].";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.RIBBED)) {
					ribbedSpecial = "With a wolfish grin, you reach up and grab [npc.name]'s shoulders, before slamming your [pc.hips] forwards and impaling your [pc.pussy+] on [npc.her] [npc.cock+]."
										+ " You feel [npc.her] ribbed cock bumping over your [pc.clit+] as you press [npc.herHim] up against the wall, before thrusting back and starting to roughly fuck yourself on [npc.her] [npc.cock+].";
				}
				if(Sex.getActivePartner().hasPenisModifier(PenisModifier.TENTACLED)) {
					tentacledSpecial = "With a wolfish grin, you reach up and grab [npc.name]'s shoulders, before slamming your [pc.hips] forwards and impaling your [pc.pussy+] on [npc.her] [npc.cock+]."
										+ " You feel the little tentacle-like nodules lining [npc.her] cock massage and stroke your inner walls as you force [npc.herHim] up against the wall,"
										+ " before thrusting back and starting to roughly fuck yourself on [npc.her] [npc.cock+].";
				}
				
				return UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to roughly take hold of [npc.name]'s hips, you violently push [npc.herHim] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc.her] neck before starting to ruthlessly slam your [pc.pussy+] down over and over again on [npc.her] [npc.cock+].",
						
						"Stepping forwards, you impale yourself on [npc.name]'s [npc.cock+], breathing down hotly onto [npc.her] neck as you roughly pin [npc.herHim] against the wall,"
								+ " before starting to aggressively buck your [pc.hips] against [npc.herHim], slamming [npc.her] [npc.cock+] into your [pc.pussy+] as you [pc.moanVerb+] into [npc.her] [npc.ear].",
						
						"You lean into [npc.name], taking in a deep breath of [npc.her] [npc.scent] as you roughly pin [npc.herHim] against the wall."
								+ " Planting your [pc.feet] on either side of [npc.herHim], you then start rapidly thrusting away at [npc.her] groin, letting out a series of [pc.moans+] as you relentlessly ride [npc.her] [npc.cock+].");
				
				
			} else { // Default descriptions:
			
				return UtilText.returnStringAtRandom(
						"Violently slamming your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you roughly force [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"With [pc.a_moan+], you aggressively start gyrating your [pc.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper into your [pc.pussy+].",
						"Roughly thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your forceful movements drive [npc.her] [npc.cock+] deep into your [pc.pussy+].");
			
			}
		}

	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing yourself with both hands flat on the floor, you start to push back against [npc.name] in time with [npc.her] thrusts,"
								+ " letting out a series of [pc.moans+] as you force [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"Letting out [pc.a_moan+], you start pushing yourself back in time with [npc.name]'s thrusts, helping to slam [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"With a series of [pc.moans+], you start bucking back against [npc.name], helping to slam [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"Each time [npc.name] thrusts forwards, you push yourself back, letting out moan after desperate moan as you repeatedly force [npc.her] [npc.cock+] deep into your [pc.pussy+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
						"With [pc.a_moan+], you start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.pussy+].",
						"Thrusting your [pc.hips] against [npc.name], [pc.a_moan+] drifts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.pussy+]."));
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck your hips against [npc.name] as [npc.her] [npc.cock] thrusts into your [pc.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing your [pc.hips] out against [npc.name]'s groin, you let out [pc.a_moan+] as you energetically help to sink [npc.her] [npc.cock+] deep into your [pc.pussy+].",
					"With [pc.a_moan+], you energetically start gyrating your [pc.hips], forcing [npc.name]'s [npc.cock+] ever deeper into your [pc.pussy+].",
					"Enthusiastically thrusting your [pc.hips] against [npc.name], [pc.a_moan+] bursts out from between your [pc.lips+] as your movements force [npc.her] [npc.cock+] deep into your [pc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.pussy+] away from [npc.name]'s [npc.cock+].";
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
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.pussy+] as [npc.she] continues gently fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues slowly sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues gently sliding deep into your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.pussy+] as [npc.she] continues eagerly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues eagerly sliding in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues eagerly sliding deep into your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You feel tears start to well up in your [pc.eyes], and, not being able to hold back any longer, you suddenly let out [pc.a_sob+],"
									+ " weakly trying to pull [npc.name]'s [npc.cock] out of your [pc.pussy+] as [npc.she] continues roughly fucking you.",
							"[pc.A_sob+] bursts out from your mouth as you frantically try to pull your [pc.hips] back from [npc.name]'s unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] continues roughly slamming in and out of your [pc.pussy+].",
							"Trying desperately to pull your [pc.hips] away from [npc.name], you [pc.sob] in distress as [npc.her] [npc.cock+] continues roughly slamming deep into your [pc.pussy+]."));
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
			OrificeType.VAGINA,
			SexParticipantType.CATCHER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop getting fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_RIDING) {
				
				return DomCowgirl.getPlayerStoppingVaginalPenetrationDescription();
				
			} else {
			
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc.name]'s [npc.cock] out of your [pc.pussy+], you let out a menacing growl as you command [npc.herHim] to stop fucking you.",
								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of your [pc.pussy+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.name]'s [npc.cock] out of your [pc.pussy+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of your [pc.pussy+]."));
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
		}
	};
	
	public static final SexAction PLAYER_PUSSY_CONTROL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS,
			OrificeType.VAGINA,
			SexParticipantType.CATCHER) {
		@Override
		public String getActionTitle() {
			return "Pussy control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your demonic pussy down around [npc.name]'s [npc.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getVaginaType()==VaginaType.DEMON_COMMON;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [pc.a_moan+], you concentrate on squeezing the tentacles and demonic muscles within your [pc.pussy] down around [npc.name]'s [npc.cock+].",
					
					"You let out a cheeky giggle as you focus on controlling the tentacles lining your [pc.pussy]."
							+ " Wriggling and squeezing them down around [npc.name]'s [npc.cock+], you cause [npc.herHim] to let out an involuntary cry of pleasure.",
					
					"You find your [pc.moans] falling into a steady rhythm as you concentrate on squeezing the tentacles and extra muscles within your [pc.pussy+] down around [npc.name]'s [npc.cock+].",
					
					"With [pc.a_moan+], you focus on controlling the demonic tentacles deep within your [pc.pussy], gripping them down and massaging [npc.name]'s [npc.cock+] as you squeal in pleasure.");
		}
	};
	
}
