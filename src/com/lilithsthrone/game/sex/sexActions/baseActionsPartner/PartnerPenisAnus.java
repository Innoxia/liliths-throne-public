package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.universal.Cowgirl;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.97
 * @author Innoxia
 */
public class PartnerPenisAnus {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tease [npc2.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] slowly teasing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+], ready to penetrate [npc2.name] at any moment.",

							"With a soft [npc.moan], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+], before starting to gently slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+], ready to penetrate [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+], before starting to eagerly slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+],"
									+ " ready to start fucking [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+], before starting to roughly [npc2.verb(grind)] the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.name] whenever [npc.she] feels like it."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Please! Fuck me! I need [npc2.namePos] cock inside of me!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] in delight as [npc2.name] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my ass! I need [npc2.name] inside of me!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(That's right, fuck me!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before pleading, [npc2.speech(Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] out loud as [npc2.name] [npc2.verb(beg)], [npc2.speech(Come on, fuck me already!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Don't! Please! I-I've never done anal before! [npc2.name] can't do this!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I've never done anal before!)]",

								" [npc2.Name] [npc2.sob] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my anal virginity!)]"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

								" [npc2.Name] [npc2.sob] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					}
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_DENIAL);
			}
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Anal tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
									+ " slowly pushing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+] as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With a soft [npc2.moan], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.ass+], before starting to gently slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] gently slide the [npc.cockHead+] over [npc2.namePos] [npc2.asshole+],"
									+ " letting out a soft [npc2.moan] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] [npc2.verb(grind)] [npc.namePos] [npc.cock+] against [npc2.namePos] [npc2.ass+],"
									+ " rubbing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+] as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(grind)] [npc.namePos] [npc.cock+] against [npc2.namePos] [npc2.ass+], before starting to roughly [npc2.verb(force)] the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] roughly [npc2.verb(grind)] the [npc.cockHead+] over [npc2.namePos] [npc2.asshole+],"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.herHim]."));
					break;
				case DOM_NORMAL: default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
									+ " eagerly pushing the [npc.cockHead+] up and down over [npc2.namePos] [npc2.asshole+] as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.ass+], before starting to eagerly slide the [npc.cockHead] up and down over [npc2.namePos] [npc2.asshole+].",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly slide the [npc.cockHead+] over [npc2.namePos] [npc2.asshole+],"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.herHim]."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck [npc2.namePos] ass, hmm?)]",

							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], [npc.speech(You want me to use [npc2.namePos] ass?)]",

							" [npc.Name] [npc.moansVerb] in delight, [npc.speech([npc2.namePos] ass sure feels good!)]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck [npc2.namePos] ass?)]",

							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], [npc.speech(You want me to use [npc2.namePos] ass?)]",

							" [npc.Name] [npc.moansVerb] in delight, [npc.speech([npc2.namePos] ass sure feels good!)]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(You want me to fuck [npc2.namePos] ass, slut?)]",

							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], [npc.speech(You want to be my buttslut fuck-toy, huh?)]",

							" [npc.Name] [npc.moansVerb] in response to [npc2.namePos] movements, [npc.speech([npc2.namePos] ass sure feels good, buttslut!)]"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! [npc2.verb(let)] me fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.moan], before pleading with [npc2.herHim], [npc.speech(Yes! Please! I want to fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.moansVerb] in delight as [npc.she] [npc2.verb(beg)]s, [npc.speech(Yes! [npc2.verb(let)] me fuck you! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, [npc2.verb(let)] me fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.verb(let)] out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to [npc2.herHim], [npc.speech(Come on, [npc2.verb(let)] me fuck [npc2.name] already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",

							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] [npc2.verb(beg)]s, [npc.speech(No! Stop! Get away from there!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	
	public static final SexAction PARTNER_ANAL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Start anal";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.cock+] into [npc2.namePos] [npc2.asshole+] and [npc2.verb(start)] fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks,"
										+ " [npc.she] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"Keeping a gentle grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks, and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks,"
										+ " [npc.she] [npc.verb(let)] out [npc.a_moan+] before eagerly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"Keeping a firm grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks, and with a determined [npc2.verb(thrust)] forwards, [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to roughly [npc2.verb(grind)] the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks,"
										+ " [npc.she] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"Keeping a strong grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] ass cheeks, and with a forceful [npc2.verb(thrust)] forwards, [npc.she] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly pushing back into [npc.her] crotch, desperately [npc2.verb(help)]ing to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], pushing back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing back into [npc.her] crotch, [npc2.verb(help)]ing to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly [npc2.verb(try)], and fail, to crawl away on all fours.",

								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to crawl away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
										+ " sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
										+ " forcing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass], and with a forceful [npc2.verb(thrust)], [npc.she] roughly slams [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass], and with a [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she]  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, desperately [npc2.verb(help)]ing to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], violently thrusting [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, desperately [npc2.verb(help)]ing to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, [npc2.verb(help)]ing to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] gently [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

							"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently buck [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.ass+].",

							"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole],"
									+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.ass+].",

							"Slowly sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] gently bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass] as [npc2.name] weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] gently [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

							"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently buck [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] rapidly [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

							"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to eagerly buck [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass],"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.ass+].",

							"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole],"
									+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.ass+].",

							"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass] as [npc2.name] weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to pull out."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] rapidly [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

							"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to rapidly buck [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass],"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.DOGGY_ON_ALL_FOURS) { // Doggy-style descriptions:
				
				String tailSpecial="",  hairSpecial="";
				
				// Tail special:
				if(Sex.getCharacterTargetedForSexAction(this).getTailType()!=TailType.NONE){
						tailSpecial = "Grabbing the base of [npc2.namePos] [npc2.tail+], [npc.name] [npc.verb(start)] roughly pulling [npc2.name] back onto [npc.her] [npc.cock+] in time with [npc.her] [npc2.verb(thrust)]s,"
								+ " letting out [npc.moans+] as [npc.she] violently pounds away at [npc2.namePos] [npc2.asshole+].";
				}
				
				// Hair special:
				if(Sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, [npc.name] [npc.verb(reach)] down and grabs a fistful of [npc2.namePos] [npc2.hair+],"
							+ " using [npc.her] new leverage to pull [npc2.name] back onto [npc.her] [npc.cock+] as [npc.she] [npc.verb(start)] roughly fucking [npc2.namePos] [npc2.asshole+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, [npc.name] knocks [npc2.namePos] [npc2.arms] out from under [npc2.herHim], causing [npc2.namePos] [npc2.face+] to be pressed down into the floor as [npc.she] stoops over [npc2.herHim],"
									+ " furiously slamming [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+] as [npc.she] holds [npc2.namePos] [npc2.ass+] up in the air.",

							hairSpecial,
							"With a menacing growl, [npc.name] roughly grabs [npc2.namePos] [npc2.hips+], shuffling forwards a little and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.asshole+],"
									+ " before starting to furiously pump [npc.her] hips back and forth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks [npc2.name] like an animal.",

							"Roughly grabbing hold of [npc2.namePos] waist, [npc.name] [npc.verb(start)] to rapidly pound [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+],"
									+ " letting out a series of [npc.moans+] as [npc.she] slams into [npc2.namePos] [npc2.ass] over and over again."));
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly pushing [npc2.namePos] [npc2.ass] back into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from [npc.name], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.asshole+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to crawl away from [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.asshole+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(try)] to crawl away from [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.asshole+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.ass] back into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]"));
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] violently bucking [npc.her] [npc.hips],"
										+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

								"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
										+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] brutally [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

								"Ruthlessly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass],"
										+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.ass+].",

								"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole],"
										+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.ass+].",

								"Ruthlessly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass] as [npc2.name] weakly struggle against [npc.herHim],"
										+ " pleading and crying for [npc.herHim] to pull out."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] violently bucking [npc.her] [npc.hips],"
										+ " grinning as [npc.she] feels [npc2.name] pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

								"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
										+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] brutally [npc2.verb(thrust)]s into [npc2.namePos] [npc2.ass+].",

								"Ruthlessly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass],"
										+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
						break;
				}

			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] a soft [npc2.moan] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] gently pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] can't [npc2.verb(help)] but gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on fucking [npc2.herHim].",

							"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] roughly [npc.verb(command)] [npc2.herHim] to continue fucking [npc2.namePos] [npc2.ass].",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and, roughly thrusting [npc2.namePos] [npc2.ass] back into [npc.her] groin, [npc2.name] order [npc.herHim] to continue fucking [npc2.herHim].",

							"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] can't [npc2.verb(help)] but greedily [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on fucking [npc2.herHim].",

							"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] a soft [npc2.moan] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] gently pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] can't [npc2.verb(help)] but gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking [npc2.herHim].",

							"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] roughly [npc.verb(command)] [npc2.herHim] to continue fucking [npc2.namePos] [npc2.ass].",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and, roughly thrusting [npc2.namePos] [npc2.ass] back into [npc.her] groin, [npc2.name] order [npc.herHim] to continue fucking [npc2.herHim].",

							"Eagerly sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
									+ " and [npc2.name] can't [npc2.verb(help)] but greedily [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking [npc2.herHim].",

							"Eagerly sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.namePos] [npc2.ass], breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist anal";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] gently fucking [npc2.herself] on [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] firmly hold [npc.herHim] in place, gently pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] fuck [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, slowly grinding [npc2.herself] against [npc.herHim] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] roughly fucking [npc2.herself] on [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] dominantly hold [npc.herHim] in place, violently pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] roughly fuck [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, roughly grinding [npc2.herself] against [npc.herHim] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] rapidly fucking [npc2.herself] on [npc.her] [npc.cock+].",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] firmly hold [npc.herHim] in place, eagerly pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] desperately fuck [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+].",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.name] happily [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] [npc2.asshole+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.ass+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+] one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.asshole+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.ass+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.asshole+], putting an end to [npc2.namePos] fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.namePos] [npc2.ass], and [npc2.name] [npc.verb(continue)] crying and protesting as [npc2.name] [npc.verb(continue)] to weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] withdraws from [npc2.namePos] [npc2.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.asshole+], eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_ANALLY_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only [npc2.verb(start)] fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// [npc2.name] can't penetrate if you're already fucking [npc2.namePos] partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)));
			} else {
				return false;//(Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this))) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock anally";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking [npc2.namePos] [npc2.asshole+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerStartingAnalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] over [npc2.namePos] [npc2.ass+],"
										+ " letting out a little [npc2.moan] before gently bucking [npc2.namePos] [npc2.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.asshole+].",

								"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out a soft [npc2.moan] as [npc2.name] penetrate [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
										+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.asshole+].",

								"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
										+ " letting out [npc2.a_moan+] before roughly slamming [npc2.namePos] [npc2.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.asshole+].",

								"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] violently slam [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(start)] fucking [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
										+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.asshole+].",

								"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.ass+],"
										+ " letting out [npc2.a_moan+] before bucking [npc2.namePos] [npc2.hips] into [npc.her] groin and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.asshole+].",

								"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.namePos] [npc2.asshole+] on [npc.her] [npc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] [npc2.asshole+].",

								" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)]s [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.asshole+].",

								" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], and, seeking to remind [npc2.name] who's in charge,"
										+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck [npc2.namePos] [npc2.asshole+].",

								" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass], seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly fucking [npc2.namePos] [npc2.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.asshole+].",

								" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)]s [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass] as [npc.she] [npc.verb(start)] fucking [npc2.namePos] [npc2.asshole+].",

								" With [npc.a_moan+], [npc.name] [npc.verb(thrust)]s [npc.her] [npc.hips] into [npc2.namePos] [npc2.ass], sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] inside of [npc2.herHim], and, struggling against [npc2.herHim], [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.asshole+].",

								" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep into [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.ass+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockAnallyGentle();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Gently pushing [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.asshole+].",

						"Slowly thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Fuck [npc2.namePos] [npc2.asshole+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockAnallyNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.asshole+].",

						"Enthusiastically thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].");
			
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.asshole+] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockAnallyRough();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Violently slamming [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], roughly forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.asshole+].",

						"Roughly thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "[npc2.verb(push)] out ass";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] [npc2.namePos] [npc2.ass+] out against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)]s into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing [npc2.herself] with both hands flat on the floor, [npc2.name] [npc2.verb(start)] to push back against [npc.name] in time with [npc.her] [npc2.verb(thrust)]s,"
								+ " letting out a series of [npc2.moans+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing [npc2.herself] back in time with [npc.namePos] [npc2.verb(thrust)]s, [npc2.verb(help)]ing to slam [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"With a series of [npc2.moans+], [npc2.name] [npc2.verb(start)] bucking back against [npc.name], [npc2.verb(help)]ing to slam [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"Each time [npc.name] [npc.verb(thrust)]s forwards, [npc2.name] [npc2.verb(push)] [npc2.herself] back, letting out moan after desperate moan as [npc2.name] repeatedly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]."));
				
			} else {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Pushing [npc2.namePos] [npc2.ass] out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

						"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] gyrating [npc2.namePos] [npc2.ass] into [npc.namePos] groin, forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.asshole+].",

						"thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "Eagerly [npc2.verb(push)] out ass";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly [npc2.verb(push)] [npc2.namePos] [npc2.ass+] out against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)]s into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.ass] out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.ass] into [npc.namePos] groin, forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.asshole+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist anal";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.ass+] away from [npc.namePos] [npc.cock+].";
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
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(continue)] gently fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] slowly sliding in and out of [npc2.namePos] [npc2.asshole+].",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding deep into [npc2.namePos] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out of [npc2.namePos] [npc2.asshole+].",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding deep into [npc2.namePos] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out of [npc2.namePos] [npc2.asshole+].",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming deep into [npc2.namePos] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerStoppingAnalPenetrationDescription();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+], [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.herHim].",

								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.herHim].",

								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole+]."));
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
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] [npc2.asshole+].",

								" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] [npc2.asshole+]."));
						break;
				}
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
	};
	
}
