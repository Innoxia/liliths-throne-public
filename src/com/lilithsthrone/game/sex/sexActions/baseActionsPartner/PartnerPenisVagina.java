package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
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
 * @version 0.1.84
 * @author Innoxia
 */
public class PartnerPenisVagina {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tease [npc2.her] pussy";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.pussy+].";
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
							"Lining [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] slowly teasing the [npc.cockHead+] up and down between [npc2.namePos] pussy lips, ready to penetrate [npc2.name] at any moment.",

							"With a soft [npc.moan], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to gently slide the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Gently sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.cockHead+] up and down between [npc2.namePos] pussy lips,"
									+ " ready to start fucking [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to roughly [npc2.verb(grind)] the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.name] whenever [npc.she] feels like it."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Lining [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead+] up and down between [npc2.namePos] pussy lips, ready to penetrate [npc2.name] at any moment.",

							"With [npc.a_moan+], [npc.name] lines [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to eagerly slide the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Eagerly sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.name] whenever [npc.she] feels like it."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Please! Fuck me! I need [npc2.namePos] cock inside of me!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Fuck me already!)]",

							" [npc2.Name] [npc2.moan] in delight as [npc2.name] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my little pussy! I need [npc2.name] inside of me!)]"));
					break;
				case SUB_RESISTING:
					if(Sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Don't! Please! I-I'm a virgin! [npc2.name] can't do this!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I'm still a virgin!)]",

								" [npc2.Name] [npc2.sob] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my virginity!)]"));
						
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
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Tease [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] over [npc2.namePos] [npc2.pussy+].";
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
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " slowly pushing the [npc.cockHead+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With a soft [npc2.moan], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to gently slide the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] gently slide the [npc.cockHead+] over [npc2.namePos] folds,"
									+ " letting out a soft [npc2.moan] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] [npc2.verb(grind)] [npc.namePos] [npc.cock+] against [npc2.namePos] [npc2.pussy+],"
									+ " rubbing the [npc.cockHead+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(grind)] [npc.namePos] [npc.cock+] against [npc2.namePos] [npc2.pussy+], before starting to roughly [npc2.verb(force)] the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] roughly [npc2.verb(grind)] the [npc.cockHead+] over [npc2.namePos] folds,"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " eagerly pushing the [npc.cockHead+] up and down between [npc2.namePos] pussy lips as [npc2.name] tease [npc.herHim] with the promise of penetration at any moment.",

							"With [npc2.a_moan+], [npc2.name] line [npc.namePos] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to eagerly slide the [npc.cockHead] up and down between [npc2.namePos] folds.",

							"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly slide the [npc.cockHead+] over [npc2.namePos] folds,"
									+ " letting out [npc2.a_moan+] as [npc2.name] tease [npc.herHim] with the promise of penetrating [npc2.namePos] [npc2.pussy+]."));
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
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
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
			return "Fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.cock+] into [npc2.namePos] [npc2.pussy+] and [npc2.verb(start)] fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently gripping [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia,"
										+ " [npc.she] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"Keeping a gentle grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Firmly gripping [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to tease the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia,"
										+ " [npc.she] [npc.verb(let)] out [npc.a_moan+] before eagerly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"Keeping a firm grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)] forwards, [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Keeping a strong grip on [npc2.namePos] [npc2.hips+], [npc.name] shuffles forwards, and, after taking a moment to roughly [npc2.verb(grind)] the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia,"
										+ " [npc.she] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"Keeping a strong grip on [npc2.namePos] [npc2.hips+],"
										+ " [npc.name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a forceful [npc2.verb(thrust)] forwards, [npc.she] roughly slams [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly pushing back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly pushing back into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], pushing back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing back into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly [npc2.verb(try)], and fail, to crawl away on all fours.",

								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to crawl away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall penetration descriptions:
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] leans into [npc2.herHim], pushing [npc2.name] back against the wall before slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia,"
										+ " letting out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards and sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] leans into [npc2.herHim], pushing [npc2.name] back against the wall as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+],"
										+ " and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] leans into [npc2.herHim], pushing [npc2.name] back against the wall before eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia,"
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards and greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] leans into [npc2.herHim], pushing [npc2.name] back against the wall as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+],"
										+ " and with a determined [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slamming [npc2.name] back against the wall, [npc.name] roughly grinds the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] outer labia,"
										+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								"Aggressively pushing [npc2.name] back against the wall, [npc.name] looks down as [npc.she] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+],"
										+ " and with a forceful [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she] roughly slams [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				
			} else { // Default penetration descriptions:
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
										+ " sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
										+ " forcing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a forceful [npc2.verb(thrust)], [npc.she] roughly slams [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
										+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+].",

								"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she]  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], violently thrusting [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+].",

								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
					barbedSpecial="With a soft [npc.moan], [npc.name] [npc.verb(reach)] down and takes hold of [npc2.namePos] [npc2.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."
										+ " [npc.She] stays in that position for a moment, pressing [npc.herself] up against [npc2.herHim], before gently pulling back,"
										+ " clearly aware that the barbs that line the sides of [npc.her] cock could be painful if [npc.she] [npc2.verb(pull)]ed out too fast, before starting to steadily fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					flaredSpecial="With a soft [npc.moan], [npc.name] [npc.verb(reach)] down and takes hold of [npc2.namePos] [npc2.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] flared head lewdly spreading out [npc2.namePos] inner walls as [npc.she] carries on gently pressing [npc.herself] against [npc2.name] for a moment,"
										+ " before pulling back and starting to steadily fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					knottedSpecial="With a soft [npc.moan], [npc.name] [npc.verb(reach)] down and takes hold of [npc2.namePos] [npc2.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] fat knot lewdly pressing against [npc2.namePos] pussy lips as [npc.she] carries on gently pressing [npc.herself] against [npc2.name] for a moment,"
										+ " before pulling back and starting to steadily fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.RIBBED)) {
					ribbedSpecial="With a soft [npc.moan], [npc.name] [npc.verb(reach)] down and takes hold of [npc2.namePos] [npc2.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] ribbed shaft bumping up against [npc2.namePos] [npc2.clit] as [npc.she] penetrates [npc2.herHim], and, after gently pressing [npc.herself] against [npc2.name] for a moment,"
										+ " [npc.she] [npc2.verb(pull)]s back and [npc.verb(start)] to steadily fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.TENTACLED)) {
					tentacledSpecial="With a soft [npc.moan], [npc.name] [npc.verb(reach)] down and takes hold of [npc2.namePos] [npc2.hips+], before slowly pushing [npc.herself] forwards and sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] the little squirming tentacles lining [npc.her] shaft eagerly massaging the inner walls of [npc2.namePos] pussy, and, after gently pressing [npc.herself] against [npc2.name] for a moment,"
										+ " [npc.she] [npc2.verb(pull)]s back and [npc2.verb(start)] to steadily fuck [npc2.name] against the wall.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to take hold of [npc2.namePos] [npc2.hips+], [npc.name] gently [npc.verb(push)] [npc2.name] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc2.namePos] neck before starting to slowly slide [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+].",

						
						"Leaning in and pressing [npc2.name] back against the wall, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.cock+] in and out of [npc2.namePos] exposed [npc2.pussy].",

						
						"Leaning into [npc2.herHim], [npc.name] takes a deep breath of [npc2.namePos] [npc2.scent] as [npc.she] gently [npc.verb(press)] [npc2.name] back against the wall, before starting to slowly slide [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+]."));
				
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] into [npc2.herHim], softly pressing [npc.her] groin against [npc2.namePos]s with every [npc2.verb(thrust)] as [npc.she] slowly fucks [npc2.herHim].",

						"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] softly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out a little [npc.moan] as [npc.she] gently fucks [npc2.herHim].",

						"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently buck [npc.her] [npc.hips], breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly fucks [npc2.herHim]."));
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] enthusiastically fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] greedily [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] fucking [npc2.herHim].",

					"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips],"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] eagerly fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.pussy+].";
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
						tailSpecial = "Grabbing the base of [npc2.namePos] [npc2.tail+], [npc.name] [npc.verb(start)] roughly pulling [npc2.name] back onto [npc.her] [npc.cock+] in time with [npc.her] [npc.verb(thrust)],"
								+ " letting out [npc.moans+] as [npc.she] violently pounds away at [npc2.namePos] [npc2.pussy+].";
				}
				
				// Hair special:
				if(Sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
					hairSpecial = "Letting out an ominous growl, [npc.name] [npc.verb(reach)] down and grabs a fistful of [npc2.namePos] [npc2.hair+],"
							+ " using [npc.her] new leverage to pull [npc2.name] back onto [npc.her] [npc.cock+] as [npc.she] [npc.verb(start)] roughly fucking [npc2.namePos] [npc2.pussy+] at an incredible pace.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							tailSpecial,
							"With a powerful shove, [npc.name] knocks [npc2.namePos] [npc2.arms] out from under [npc2.herHim], causing [npc2.namePos] [npc2.face+] to be pressed down into the floor as [npc.she] stoops over [npc2.herHim],"
									+ " furiously slamming [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+] as [npc.she] holds [npc2.namePos] [npc2.ass+] up in the air.",

							hairSpecial,
							"With a menacing growl, [npc.name] roughly grabs [npc2.namePos] [npc2.hips+], shuffling forwards a little and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+],"
									+ " before starting to furiously pump [npc.her] hips back and forth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks [npc2.name] like an animal.",

							"Roughly grabbing hold of [npc2.namePos] waist, [npc.name] [npc.verb(start)] to rapidly pound [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+],"
									+ " letting out a series of [npc.moans+] as [npc.she] slams into [npc2.namePos] [npc2.ass] over and over again."));
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly pushing [npc2.namePos] [npc2.ass] back into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to crawl away from [npc.name], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to crawl away from [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(try)] to crawl away from [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.ass] back into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
					barbedSpecial="With a wolfish grin, [npc.name] [npc.verb(reach)] down and grabs [npc2.namePos] [npc2.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+]."
										+ " [npc.She] stays in that position for a moment, roughly grinding [npc.herself] up against [npc2.herHim], before violently pulling back,"
										+ " roughly raking the barbs that line the sides of [npc.her] cock against [npc2.namePos] vaginal walls, before starting to furiously fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					flaredSpecial="With a wolfish grin, [npc.name] [npc.verb(reach)] down and grabs [npc2.namePos] [npc2.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] flared head lewdly spreading out [npc2.namePos] inner walls as [npc.she] carries on roughly grinding [npc.herself] against [npc2.name] for a moment,"
										+ " before pulling back and starting to dominantly fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					knottedSpecial="With a wolfish grin, [npc.name] [npc.verb(reach)] down and grabs [npc2.namePos] [npc2.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] fat knot lewdly pressing against [npc2.namePos] pussy lips as [npc.she] carries on roughly grinding [npc.herself] against [npc2.name] for a moment,"
										+ " before pulling back and starting to dominantly fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.RIBBED)) {
					ribbedSpecial="With a wolfish grin, [npc.name] [npc.verb(reach)] down and grabs [npc2.namePos] [npc2.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] ribbed shaft bumping up against [npc2.namePos] [npc2.clit] as [npc.she] penetrates [npc2.herHim], and, after roughly grinding [npc.herself] against [npc2.name] for a moment,"
										+ " [npc.she] [npc2.verb(pull)]s back and [npc.verb(start)] to dominantly fuck [npc2.name] against the wall.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.TENTACLED)) {
					tentacledSpecial="With a wolfish grin, [npc.name] [npc.verb(reach)] down and grabs [npc2.namePos] [npc2.hips+], before slamming forwards and burying [npc.her] [npc.cock+] deep in [npc2.namePos] [npc2.pussy+]."
							+ " [npc2.Name] [npc2.verb(feel)] the little squirming tentacles lining [npc.her] shaft eagerly massaging the inner walls of [npc2.namePos] pussy, and, after roughly grinding [npc.herself] against [npc2.name] for a moment,"
							+ " [npc.she] [npc2.verb(pull)]s back and [npc.verb(start)] to dominantly fuck [npc2.name] against the wall.";
				}
				
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down and grabbing [npc2.namePos] [npc2.hips+], [npc.name] roughly [npc.verb(push)] [npc2.name] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc2.namePos] neck before starting to ruthlessly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+].",

						
						"Reaching down to hook a hand under one of [npc2.namePos] [npc2.legs], [npc.name] hoists it up in the air, giving [npc.herHim] unobstructed access to [npc2.namePos] [npc2.pussy+]."
								+ " Pushing [npc2.name] back against the wall, [npc.she] lifts [npc2.namePos] [npc2.leg] as high as [npc.she] can before starting to roughly pound [npc.her] [npc.cock+] in and out of [npc2.namePos] exposed [npc2.pussy].",

						
						"Leaning into [npc2.herHim], [npc.name] takes a deep breath of [npc2.namePos] [npc2.scent] as [npc.she] pins [npc2.name] back against the wall, before starting to ruthlessly pound [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+]."));
				
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
				}
				
			} else { // Default descriptions:
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Roughly slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] violently pumping [npc.her] [npc.hips], grinding into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] brutally fucks [npc2.herHim].",

						"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] slamming [npc.her] [npc.hips] into [npc2.herHim], letting out [npc.a_moan+] as [npc.she] roughly fucks [npc2.herHim].",

						"Ruthlessly thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] roughly fucks [npc2.herHim]."));
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
										+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.pussy+].",

								" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.pussy+]"));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] fucks [npc2.herHim].",

					"Pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] fucking [npc2.herHim].",

					"thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] enthusiastically fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] desperately fucking [npc2.herHim].",

					"Eagerly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_sob+], pushing against [npc2.name] as [npc.she] weakly [npc2.verb(beg)]s to be released.",

					" [npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, pleading for [npc2.name] to take [npc2.namePos] [npc2.pussy+] off [npc.her] [npc.cock].",

					" [npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.herHim], pleading for [npc2.name] to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.name], letting out a soft [npc2.moan] as [npc2.name] [npc.verb(continue)] gently fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, slowly grinding [npc2.herself] against [npc.herHim] and softly [npc2.moaning] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] roughly slam [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] violently fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to roughly fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, roughly grinding [npc2.herself] against [npc.herHim] and [npc2.moaning+] out loud as [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] happily fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to energetically fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, eagerly grinding [npc2.herself] against [npc.herHim] and [npc2.moaning+] out loud as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
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
			return "Stop fucking [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] [npc2.pussy+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between [npc2.namePos] folds one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.pussy+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between [npc2.namePos] folds one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.pussy+], putting an end to [npc2.namePos] fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.namePos] [npc2.pussy], and [npc2.name] [npc.verb(continue)] crying and protesting as [npc2.name] [npc.verb(continue)] to weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] withdraws from [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.pussy+], eager for more of [npc.her] attention.",

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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
				return false; //(Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this))) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Start fucking [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerStartingVaginalPenetrationDescription();
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] over [npc2.namePos] outer labia,"
										+ " letting out a little [npc2.moan] before gently bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

								"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out a soft [npc2.moan] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
										+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

								"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully grinding [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
										+ " letting out [npc2.a_moan+] before roughly slamming [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

								"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] violently slam [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(start)] fucking [npc2.herself] on [npc.her] [npc.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
										+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

								"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
										+ " letting out [npc2.a_moan+] before bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.pussy+].",

								"Lining [npc2.namePos] [npc2.pussy+] up to [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
						break;
					default:
						break;
				}
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] [npc2.pussy+].",

								" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.pussy+].",

								" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], and, seeking to remind [npc2.name] who's in charge,"
										+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck [npc2.namePos] [npc2.pussy+].",

								" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] groin, seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly fucking [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.pussy+].",

								" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc.her] [npc.hips] into [npc2.namePos] groin as [npc.she] [npc.verb(start)] fucking [npc2.namePos] [npc2.pussy+].",

								" With [npc.a_moan+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] inside of [npc2.herHim], and, struggling against [npc2.herHim], [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.pussy+].",

								" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Gentle ride";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockGentle();
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {// Back-to-wall descriptions:
				
				return UtilText.returnStringAtRandom(
						"Reaching down to gently take hold of [npc.namePos] hips, [npc2.name] [npc2.verb(start)] bucking back and forth, pressing [npc.herHim] against the wall as [npc2.name] repeatedly impale [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+].",

						"Gently pressing [npc.name] back against the wall, [npc2.name] [npc2.verb(start)] to gently buck [npc2.namePos] [npc2.hips] into [npc.her] groin, [npc2.moaning] softly into [npc.her] [npc.ear] as [npc2.name] slowly fuck [npc2.herself] on [npc.her] [npc.cock+].",

						"With a soft [npc2.moan], [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, impaling [npc2.namePos] [npc2.pussy+] on [npc.namePos] [npc.cock+], before gently sliding back and starting to fuck [npc.herHim] against the wall.",

						"Leaning in and breathing hotly down on [npc.namePos] neck, [npc2.name] [npc2.verb(start)] to buck [npc2.namePos] [npc2.hips] back and forth, gently fucking [npc2.herself] on [npc.her] [npc.cock+] as [npc2.name] [npc2.moan] hotly into [npc.her] [npc.ear].");
				
			} else { // Default descriptions:
			
				return UtilText.returnStringAtRandom(
						"Gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.pussy+].",

						"Slowly thrusting [npc2.namePos] [npc2.hips] against [npc.name], a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].");
			
			}
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockNormal();
				
			} else {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.pussy+].",

						"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].");
			}
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerRidingCockRough();
				
			} else if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				
				String barbedSpecial = "", flaredSpecial = "", knottedSpecial = "", ribbedSpecial = "", tentacledSpecial = "";
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
					barbedSpecial = "With a wolfish grin, [npc2.name] reach up and grab [npc.namePos] shoulders, before slamming [npc2.namePos] [npc2.hips] forwards and impaling [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+]."
										+ " Forcefully pressing [npc.herHim] up against the wall and breathing hotly down on [npc.her] neck,"
										+ " [npc2.Name] quickly [npc2.verb(pull)] back, roughly raking [npc.her] cock's barbs against [npc2.namePos] inner walls and letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] to aggressively fuck [npc2.herself] on [npc.her] [npc.cock].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					flaredSpecial = "With a wolfish grin, [npc2.name] reach up and grab [npc.namePos] shoulders, before slamming [npc2.namePos] [npc2.hips] forwards and impaling [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+]."
										+ " [npc2.Name] [npc2.verb(feel)] the wide, flared head of [npc.her] cock spreading [npc2.name] out as [npc2.name] press [npc.herHim] up against the wall, before pulling back and starting to roughly fuck [npc2.herself] on [npc.her] [npc.cock+].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					knottedSpecial = "With a wolfish grin, [npc2.name] reach up and grab [npc.namePos] shoulders, before slamming [npc2.namePos] [npc2.hips] forwards and impaling [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+]."
										+ " [npc2.Name] [npc2.verb(grind)] [npc2.namePos] lips against [npc.her] fat knot, forcefully pressing [npc.herHim] up against the wall and breathing hotly down on [npc.her] neck,"
										+ " before thrusting back and starting to roughly fuck [npc2.herself] on [npc.her] [npc.cock].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.RIBBED)) {
					ribbedSpecial = "With a wolfish grin, [npc2.name] reach up and grab [npc.namePos] shoulders, before slamming [npc2.namePos] [npc2.hips] forwards and impaling [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+]."
										+ " [npc2.Name] [npc2.verb(feel)] [npc.her] ribbed cock bumping over [npc2.namePos] [npc2.clit+] as [npc2.name] press [npc.herHim] up against the wall, before thrusting back and starting to roughly fuck [npc2.herself] on [npc.her] [npc.cock+].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.TENTACLED)) {
					tentacledSpecial = "With a wolfish grin, [npc2.name] reach up and grab [npc.namePos] shoulders, before slamming [npc2.namePos] [npc2.hips] forwards and impaling [npc2.namePos] [npc2.pussy+] on [npc.her] [npc.cock+]."
										+ " [npc2.Name] [npc2.verb(feel)] the little tentacle-like nodules lining [npc.her] cock massage and stroke [npc2.namePos] inner walls as [npc2.name] [npc2.verb(force)] [npc.herHim] up against the wall,"
										+ " before thrusting back and starting to roughly fuck [npc2.herself] on [npc.her] [npc.cock+].";
				}
				
				return UtilText.returnStringAtRandom(
								barbedSpecial, flaredSpecial, knottedSpecial, ribbedSpecial, tentacledSpecial,
						
						"Reaching down to roughly take hold of [npc.namePos] hips, [npc2.name] violently [npc2.verb(push)] [npc.herHim] back against the wall,"
								+ " stepping forwards and breathing hotly down on [npc.her] neck before starting to ruthlessly slam [npc2.namePos] [npc2.pussy+] down over and over again on [npc.her] [npc.cock+].",

						
						"Stepping forwards, [npc2.name] impale [npc2.herself] on [npc.namePos] [npc.cock+], breathing down hotly onto [npc.her] neck as [npc2.name] roughly pin [npc.herHim] against the wall,"
								+ " before starting to aggressively buck [npc2.namePos] [npc2.hips] against [npc.herHim], slamming [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+] as [npc2.name] [npc2.moanVerb+] into [npc.her] [npc.ear].",

						
						"You lean into [npc.name], taking in a deep breath of [npc.her] [npc.scent] as [npc2.name] roughly pin [npc.herHim] against the wall."
								+ " Planting [npc2.namePos] [npc2.feet] on either side of [npc.herHim], [npc2.name] then [npc2.verb(start)] rapidly thrusting away at [npc.her] groin, letting out a series of [npc2.moans+] as [npc2.name] relentlessly ride [npc.her] [npc.cock+].");
				
				
			} else { // Default descriptions:
			
				return UtilText.returnStringAtRandom(
						"Violently slamming [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.pussy+].",

						"Roughly thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].");
			
			}
		}

	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.DOGGY_ON_ALL_FOURS) {// Doggy-style penetration descriptions:
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Bracing [npc2.herself] with both hands flat on the floor, [npc2.name] [npc2.verb(start)] to push back against [npc.name] in time with [npc.her] [npc.verb(thrust)],"
								+ " letting out a series of [npc2.moans+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(start)] pushing [npc2.herself] back in time with [npc.namePos] [npc2.verb(thrust)], helping to slam [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"With a series of [npc2.moans+], [npc2.name] [npc2.verb(start)] bucking back against [npc.name], helping to slam [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"Each time [npc.name] [npc.verb(thrust)] forwards, [npc2.name] [npc2.verb(push)] [npc2.herself] back, letting out moan after desperate moan as [npc2.name] repeatedly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

						"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.pussy+].",

						"thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Eagerly buck hips";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.pussy].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.pussy+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.cock+].";
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
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] gently fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] slowly sliding in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding deep into [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out of [npc2.namePos] [npc2.pussy+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming deep into [npc2.namePos] [npc2.pussy+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Stop getting fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.COWGIRL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.COWGIRL_RIDING) {
				
				return Cowgirl.getPlayerStoppingVaginalPenetrationDescription();
				
			} else {
			
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.pussy+], [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.herHim].",

								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.pussy+], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.herHim].",

								"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+]."));
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
								" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] [npc2.pussy+].",

								" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] [npc2.pussy+]."));
						break;
				}
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
	};
	
	public static final SexAction PLAYER_PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pussy control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze [npc2.namePos] demonic pussy down around [npc.namePos] [npc.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterTargetedForSexAction(this).getVaginaType()==VaginaType.DEMON_COMMON;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc2.a_moan+], [npc2.name] concentrate on squeezing the tentacles and demonic muscles within [npc2.namePos] [npc2.pussy] down around [npc.namePos] [npc.cock+].",

					
					"You [npc2.verb(let)] out a cheeky giggle as [npc2.name] [npc.verb(focus)] on controlling the tentacles lining [npc2.namePos] [npc2.pussy]."
							+ " Wriggling and squeezing them down around [npc.namePos] [npc.cock+], [npc2.name] cause [npc.herHim] to let out an involuntary cry of pleasure.",

					
					"You find [npc2.namePos] [npc2.moans] falling into a steady rhythm as [npc2.name] concentrate on squeezing the tentacles and extra muscles within [npc2.namePos] [npc2.pussy+] down around [npc.namePos] [npc.cock+].",

					
					"With [npc2.a_moan+], [npc2.name] [npc.verb(focus)] on controlling the demonic tentacles deep within [npc2.namePos] [npc2.pussy], gripping them down and massaging [npc.namePos] [npc.cock+] as [npc2.name] squeal in pleasure.");
		}
	};
	
}
