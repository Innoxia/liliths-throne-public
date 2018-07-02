package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
public class PartnerPenisNipple {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Tease [npc2.her] nipple";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual()) && Sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning [npc.her] [npc.cock+] over one of [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(start)] slowly teasing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+], ready to penetrate [npc2.name] at any moment.",

							"With a soft [npc.moan], [npc.name] positions [npc.her] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to gently slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+],"
									+ " ready to start fucking [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] positions [npc.her] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to roughly [npc2.verb(grind)] the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.name] whenever [npc.she] feels like it."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Positioning [npc.her] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+], ready to penetrate [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] positions [npc.her] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to eagerly slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Please! Fuck me! I need [npc2.namePos] cock inside of me!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] in delight as [npc2.name] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my breasts! I need [npc2.name] inside of me!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Don't! Please! I-I've never done this before! [npc2.name] can't do this!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I've never done this before!)]",

								" [npc2.Name] [npc2.sob] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! Don't do this!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

								" [npc2.Name] [npc2.sob] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(That's right, fuck me!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before pleading, [npc2.speech(Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] out loud as [npc2.name] [npc2.verb(beg)], [npc2.speech(Come on, fuck me already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
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
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Nipple-tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual()) && Sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc2.herself], [npc2.name] line [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+],"
									+ " slowly pushing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+] as [npc2.name] tease [npc.herHim] with the promise of penetration.",

							"With a soft [npc2.moan], [npc2.name] position [npc.namePos] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to gently slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Lining [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+], [npc2.name] gently slide the [npc.cockHead+] over [npc2.namePos] [npc2.nipple+],"
									+ " letting out a soft [npc2.moan] as [npc2.name] tease [npc.herHim] with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc2.herself], [npc2.name] line [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+],"
									+ " forcefully grinding the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+] as [npc2.name] tease [npc.herHim] with the promise of penetration.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(grind)] [npc.namePos] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to roughly [npc2.verb(force)] the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Lining [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+], [npc2.name] roughly [npc2.verb(grind)] the [npc.cockHead+] over [npc2.namePos] [npc2.nipple+],"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetration."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Repositioning [npc2.herself], [npc2.name] line [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+],"
									+ " eagerly pushing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.nipple+] as [npc2.name] tease [npc.herHim] with the promise of penetration.",

							"With [npc2.a_moan+], [npc2.name] position [npc.namePos] [npc.cock+] over one of [npc2.namePos] [npc2.breasts+], before starting to eagerly slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.nipple+].",

							"Lining [npc.namePos] [npc.cock+] up to one of [npc2.namePos] [npc2.breasts+], [npc2.name] eagerly slide the [npc.cockHead+] over [npc2.namePos] [npc2.nipple+],"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetration."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! [npc2.verb(let)] me fuck you!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.moan], before pleading with [npc2.herHim], [npc.speech(Yes! Please! I want to fuck you!)]",

							" [npc.Name] [npc.moansVerb] in delight as [npc.she] [npc2.verb(beg)]s, [npc.speech(Yes! [npc2.verb(let)] me fuck you! Please!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",

							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] [npc2.verb(beg)]s, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, [npc2.verb(let)] me fuck you!)]",

							" [npc.Name] [npc.verb(let)] out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck you!)]",

							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to [npc2.herHim], [npc.speech(Come on, [npc2.verb(let)] me fuck [npc2.name] already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
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
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.cock+] into [npc2.namePos] [npc2.nipple+] and [npc2.verb(start)] fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
									+ " sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
									+ " forcing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], and with a forceful [npc2.verb(thrust)], [npc.she] roughly slams [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] over one of [npc2.namePos] [npc2.breasts+], and with a [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she]  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] enters [npc2.herHim], gently pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently pushing [npc2.namePos] [npc2.breast+] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly pushing [npc2.namePos] [npc2.breast+] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], violently thrusting [npc2.namePos] chest out as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently thrusting [npc2.namePos] [npc2.breast+] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly pushing [npc2.namePos] [npc2.breast+] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], pushing [npc2.namePos] chest out as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing [npc2.namePos] [npc2.breast+] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] into [npc2.herHim], softly pressing [npc.her] groin into [npc2.namePos] [npc2.breast+] with every [npc2.verb(thrust)] as [npc.she] slowly nipple-fucks [npc2.herHim].",

					"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] softly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out a little [npc.moan] as [npc.she] gently nipple-fucks [npc2.herHim].",

					"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently buck [npc.her] [npc.hips], breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly nipple-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] [npc2.breasts+] with every [npc2.verb(thrust)] as [npc.she] enthusiastically nipple-fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] greedily [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breasts+], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] nipple-fucking [npc2.herHim].",

					"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips],"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] eagerly nipple-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] violently pumping [npc.her] [npc.hips], grinding into [npc2.namePos] [npc2.breasts+] with every [npc2.verb(thrust)] as [npc.she] brutally nipple-fucks [npc2.herHim].",

					"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.breasts+], letting out [npc.a_moan+] as [npc.she] roughly nipple-fucks [npc2.herHim].",

					"Ruthlessly thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] roughly nipple-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] chest out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] chest out, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.breasts+] out, begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips], slamming into [npc2.namePos] [npc2.breasts+] with every [npc2.verb(thrust)] as [npc.she] nipple-fucks [npc2.herHim].",

					"Pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breasts+], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] nipple-fucking [npc2.herHim].",

					"thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips], breathing in [npc2.namePos] [npc2.scent] as [npc.she] nipple-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(push)] out [npc2.namePos] chest in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] chest out, softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] chest out in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] chest out, ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.namePos] chest out in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] chest out, [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] [npc2.breasts+] with every [npc2.verb(thrust)] as [npc.she] enthusiastically nipple-fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] desperately nipple-fucking [npc2.herHim].",

					"Eagerly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips], breathing in [npc2.namePos] [npc2.scent] as [npc.she] nipple-fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(push)] out [npc2.namePos] chest in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] chest out, softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] chest out in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] chest out, ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.namePos] chest out in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] chest out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(push)] [npc2.namePos] chest out, [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] out [npc.a_sob+], pushing against [npc2.name] as [npc.she] weakly [npc2.verb(beg)]s to be released.",

					" [npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, pleading for [npc2.name] to release [npc.her] [npc.cock].",

					" [npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.herHim], pleading for [npc2.name] to release [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] chest out, letting out a soft [npc2.moan] as [npc2.name] [npc.verb(continue)] gently fucking [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] gently [npc2.verb(push)] [npc2.namePos] chest out, continuing to fuck [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, slowly pushing [npc2.namePos] chest out and softly [npc2.moaning] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] roughly slam [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] violently fucking [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] forcefully [npc2.verb(thrust)] [npc2.namePos] chest out, continuing to roughly fuck [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, roughly thrusting [npc2.namePos] chest out and [npc2.moaning+] out loud as [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] happily fucking [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests, [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] chest out, continuing to energetically fuck [npc2.namePos] [npc2.nipple+] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, eagerly thrusting [npc2.namePos] chest out and [npc2.moaning+] out loud as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] [npc2.nipple+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.breast+], [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.nipple+] one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.nipple+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.breast+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.nipple+] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.nipple+], putting an end to [npc2.namePos] nipple-fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.namePos] [npc2.nipple], continuing to cry and protest as [npc2.name] weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] withdraws from [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.nipple+], eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Get nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc.namePos] [npc.cock+] to fuck [npc2.namePos] [npc2.breast+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+],"
									+ " letting out a little [npc2.moan] before gently pushing [npc2.namePos] chest out and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.nipple+].",

							"Lining one of [npc2.namePos] [npc2.nipples+] up to [npc.namePos] [npc.cock+], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] chest out, letting out a soft [npc2.moan] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+],"
									+ " letting out [npc2.a_moan+] before desperately pushing [npc2.namePos] chest out and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.nipple+].",

							"Lining one of [npc2.namePos] [npc2.nipples+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+],"
									+ " letting out [npc2.a_moan+] before roughly pushing [npc2.namePos] chest out and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.nipple+].",

							"Lining one of [npc2.namePos] [npc2.nipples+] up to [npc.namePos] [npc.cock+], [npc2.name] violently [npc2.verb(thrust)] [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(start)] fucking [npc2.namePos] [npc2.breast+] on [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+],"
									+ " letting out [npc2.a_moan+] before desperately thrusting [npc2.namePos] chest out and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.nipple+].",

							"Lining one of [npc2.namePos] [npc2.nipples+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up against one of [npc2.namePos] [npc2.breasts+],"
									+ " letting out [npc2.a_moan+] before bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.nipple+].",

							"Lining one of [npc2.namePos] [npc2.nipples+] up to [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(push)] [npc2.namePos] chest out, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] [npc2.nipple+].",

							" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breast+], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.nipple+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breast+], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], and, seeking to remind [npc2.name] who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck [npc2.namePos] [npc2.nipple+].",

							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.breast+], seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly fucking [npc2.namePos] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.nipple+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breast+], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc.her] [npc.hips] into [npc2.namePos] groin as [npc.she] [npc.verb(start)] fucking [npc2.namePos] [npc2.nipple+].",

							" With [npc.a_moan+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.breast+], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] inside of [npc2.namePos] [npc2.breast+], and, struggling against [npc2.herHim], [npc.she] desperately tries to pull [npc.her] [npc.cock+] free.",

							" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep into [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
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
			return "Gently nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.nipple+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc2.namePos] chest out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

					"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] pushing [npc2.namePos] chest out, forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.nipple+].",

					"Slowly pushing [npc2.namePos] chest out, a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
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
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc2.namePos] chest out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] thrusting [npc2.namePos] [npc2.hips] out, forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.nipple+].",

					"Enthusiastically thrusting [npc2.namePos] chest out, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
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
			return "Roughly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.nipple+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc2.namePos] chest out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

					"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] thrusting [npc2.namePos] chest out, forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.nipple+].",

					"Roughly thrusting [npc2.namePos] chest out, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.namePos] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
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
			return "Nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] [npc2.namePos] chest out into [npc.namePos] groin as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc2.namePos] chest out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

					"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing [npc2.namePos] chest out, forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.nipple+].",

					"thrusting [npc2.namePos] chest out, [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.namePos] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
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
			return "Eagerly nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.nipple].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc2.namePos] chest out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] thrusting [npc2.namePos] [npc2.hips] out, forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.nipple+].",

					"Enthusiastically thrusting [npc2.namePos] chest out, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.nipple+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.breasts+] away from [npc.namePos] [npc.cock+].";
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
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(continue)] gently fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.breasts+] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] slowly sliding in and out of [npc2.namePos] [npc2.nipple+].",

							"Trying desperately to pull [npc2.namePos] [npc2.breasts+] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding deep into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.breasts+] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out of [npc2.namePos] [npc2.nipple+].",

							"Trying desperately to pull [npc2.namePos] [npc2.breasts+] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding deep into [npc2.namePos] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.nipple+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.breasts+] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out of [npc2.namePos] [npc2.nipple+].",

							"Trying desperately to pull [npc2.namePos] [npc2.breasts+] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming deep into [npc2.namePos] [npc2.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.nipple+], [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before pulling back, yanking [npc.her] [npc.cock] out of [npc2.namePos] [npc2.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.nipple+], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.nipple+]."));
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
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] [npc2.nipple+].",

							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] [npc2.nipple+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
