package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.82
 * @version 0.4
 * @author Innoxia
 */
public class TentacleAnus {
	
	public static final SexAction TEASE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anal tentacle tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.tentacle(true)] over [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(start)] slowly teasing the [npc.tentacleTip+] up and down over [npc2.her] [npc2.asshole+], ready to penetrate [npc2.herHim] at any moment.",

							"With a soft [npc.moan], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+], before starting to gently slide the [npc.tentacleTip] up and down over [npc2.her] [npc2.asshole+].",

							"Gently sliding the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] up and down over [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(start)] eagerly sliding the [npc.tentacleTip+] up and down over [npc2.her] [npc2.asshole+], ready to penetrate [npc2.herHim] at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+], before starting to eagerly slide the [npc.tentacleTip] up and down over [npc2.her] [npc2.asshole+].",

							"Eagerly sliding the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] up and down over [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.tentacle+(true)] up against [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.tentacleTip+] up and down over [npc2.her] [npc2.asshole+], ready to start fucking [npc2.herHim] at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+],"
									+ " before starting to roughly [npc2.verb(grind)] the [npc.tentacleTip] up and down over [npc2.her] [npc2.asshole+].",

							"Roughly grinding the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] up and down over [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Snaking [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(start)] sliding the [npc.tentacleTip+] up and down over [npc2.her] [npc2.asshole+], ready to penetrate [npc2.herHim] at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] up to [npc2.namePos] [npc2.assCloaca+], before starting to slide the [npc.tentacleTip] up and down over [npc2.her] [npc2.asshole+].",

							"Sliding the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] up and down over [npc2.namePos] [npc2.asshole+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] gently [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently bucking [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)] stimulating [npc2.her] [npc2.asshole+],"
										+ " and gently [npc2.verb(push)] [npc.her] [npc.hips+] back in response."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] eagerly [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly thrusting [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)] stimulating [npc2.her] [npc2.asshole+],"
										+ " and desperately [npc2.verb(buck)] [npc.her] [npc.hips+] back in response."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before roughly thrusting [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)] stimulating [npc2.her] [npc2.asshole+],"
										+ " and violently [npc2.verb(buck)] [npc.her] [npc.hips+] back in response."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before thrusting [npc2.her] [npc2.assCloaca+] back against [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)] stimulating [npc2.her] [npc2.asshole+], and [npc2.verb(buck)] [npc.her] [npc.hips+] back in response."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of being about to lose [npc2.her] anal virginity.",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(realise)] that [npc2.she] might be about to lose [npc2.her] anal virginity.",
										" [npc2.Name] [npc2.sobVerb] in distress at the thought of losing [npc2.her] anal virginity to [npc.namePos] [npc.tentacle+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Stop! I've never done anal before!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I've never done anal before!)]",
										" [npc2.Name] [npc2.sobVerb] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my anal virginity!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.assCloaca] away from [npc.namePos] [npc.tentacle].",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(struggle)] against [npc.name].",
										" [npc2.Name] [npc2.sobVerb] in distress as [npc2.she] [npc2.verb(try)] to pull away from [npc.namePos] [npc.tentacle]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.assCloaca] away from [npc.namePos] [npc.tentacle(true)],"
												+ " [npc2.speech(No! Don't! Please, get away from me!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",
										" [npc2.Name] [npc2.sobVerb] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
							}
						}
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tease tentacle (anal)";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc2.tentacleTip] of [npc2.namePos] [npc2.tentacle(true)] over your [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], before guiding it up to [npc.her] [npc.assCloaca+]."
									+ " Slowly pushing the [npc2.tentacleTip+] up and down over [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",

							"With a soft [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] and [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " before starting to gently slide the [npc2.tentacleTip] up and down over [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle+(true)], [npc.name] gently [npc.verb(slide)] the [npc2.tentacleTip+] over [npc.her] [npc.asshole+],"
									+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], before guiding it up to [npc.her] [npc.assCloaca+]."
									+ " Eagerly pushing the [npc2.tentacleTip+] up and down over [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] and [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " before starting to eagerly slide the [npc2.tentacleTip] up and down over [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle+(true)], [npc.name] eagerly [npc.verb(slide)] the [npc2.tentacleTip+] over [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], before yanking it up to [npc.her] [npc.assCloaca+]."
									+ " Roughly forcing the [npc2.tentacleTip+] up and down over [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] and yank it up to [npc.her] [npc.assCloaca+],"
									+ " before starting to roughly [npc.verb(force)] the [npc2.tentacleTip] up and down over [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle+(true)], [npc.name] roughly [npc.verb(grind)] the [npc2.tentacleTip+] over [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], before guiding it up to [npc.her] [npc.assCloaca+]."
									+ " Pushing the [npc2.tentacleTip+] up and down over [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",

							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] and [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " before starting to slide the [npc2.tentacleTip] up and down over [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(slide)] the [npc2.tentacleTip+] over [npc.her] [npc.asshole+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] gently rubbing [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently sliding [npc2.her] [npc2.tentacle(true)] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] gently sliding [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], before forcefully grinding [npc2.her] [npc2.tentacle(true)] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] being stimulated, and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] enthusiastically rubbing [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly sliding [npc2.her] [npc2.tentacle(true)] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] eagerly sliding [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] rubbing [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before sliding [npc2.her] [npc2.tentacle(true)] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] sliding [npc2.her] [npc2.her] [npc2.tentacle(true)] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.tentacle+(true)] away from [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before frantically trying to pull [npc2.her] [npc2.tentacle+(true)] away from [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)] [npc.name] to let go of [npc2.her] [npc2.tentacle(true)]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.tentacle+(true)] into [npc2.namePos] [npc2.asshole+] and start tentacle-pegging [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.tentacle+(true)] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] between [npc2.namePos] ass cheeks,"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tentacle+(true)] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] between [npc2.namePos] ass cheeks, "
									+ "and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards, forcing [npc.her] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] between [npc2.namePos] ass cheeks,"
									+ " and with a forceful thrust, [npc.she] roughly [npc.verb(slam)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.tentacle+(true)] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] between [npc2.namePos] ass cheeks,"
									+ " and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.tentacle+(true)] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.tentacleTip+] of [npc.her] [npc.tentacle(true)] between [npc2.namePos] ass cheeks,"
									+ " and with a little thrust, [npc.she] [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as the [npc.tentacle+(true)] enters [npc2.herHim],"
										+ " before gently bucking [npc2.her] [npc2.hips] back in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] back,"
										+ " sinking [npc.namePos] [npc.tentacle+(true)] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.tentacle+(true)] enters [npc2.herHim],"
										+ " before violently thrusting [npc2.her] [npc2.hips] back in order to force it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips] back,"
										+ " roughly forcing [npc.name] to sink [npc.her] [npc.tentacle+(true)] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.tentacle+(true)] enters [npc2.herHim],"
										+ " before eagerly bucking [npc2.her] [npc2.hips] back in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] back,"
										+ " desperately helping to sink [npc.namePos] [npc.tentacle+(true)] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.tentacle+(true)] enters [npc2.herHim],"
										+ " before bucking [npc2.her] [npc2.hips] back in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] back,"
										+ " helping to sink [npc.namePos] [npc.tentacle+(true)] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as the [npc.tentacle+(true)] enters [npc2.herHim],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to pull out.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
										+ " tears running down [npc2.her] [npc2.face] as [npc.namePos] unwelcome [npc.tentacle(true)] pushes deep into [npc2.her] [npc2.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.namePos] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly thrusting [npc2.her] [npc2.hips] back, [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on tentacle-pegging [npc2.herHim].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] back,"
									+ " eagerly begging for [npc.name] to continue tentacle-pegging [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.tentacle(true)],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, thrusting [npc2.her] [npc2.hips] back, [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on tentacle-pegging [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips+] back,"
									+ " begging for [npc.name] to continue tentacle-pegging [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue tentacle-pegging [npc2.her] [npc2.asshole+].",
	
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, slowly bucking [npc2.her] [npc2.hips] back, [npc2.she] [npc2.verb(implore)] [npc.name] to carry on tentacle-pegging [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] back,"
									+ " begging for [npc.name] to continue tentacle-pegging [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(demand)] that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",
	
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly slamming [npc2.her] [npc2.hips] back, [npc2.she] [npc2.verb(order)] [npc.name] to carry on tentacle-pegging [npc2.herHim].",
	
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips+] back,"
									+ " ordering [npc.name] to continue tentacle-pegging [npc2.herHim] as [npc2.her] movements force [npc.her] [npc.tentacle+(true)] deep into [npc2.her] [npc2.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TENTACLE_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.tentacle(true)] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] sliding it in and out, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(tentacle-peg)] [npc2.name].",

					"Slowly pushing [npc.her] [npc.tentacle+(true)] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] softly [npc2.verb(thrust)] it in and out, letting out a little [npc.moan] as [npc.she] gently [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Sliding [npc.her] [npc.tentacle+(true)] into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tentacle+(true)] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically pumping it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(tentacle-peg)] [npc2.name].",

					"Enthusiastically pushing [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] frantically [npc2.verb(start)] thrusting it in and out, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.tentacle+(true)] in and out of [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] roughly pumping it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] brutally [npc.verb(tentacle-peg)] [npc2.name].",

					"Violently thrusting [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] roughly thrusting it in and out, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Forcefully driving [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to roughly slam it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] violently [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] pumping it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(tentacle-peg)] [npc2.name].",

					"Pushing [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] thrusting it in and out, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly pump your [npc.tentacle+(true)] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically pumping it in and out, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(tentacle-peg)] [npc2.name].",

					"Enthusiastically pushing [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] frantically [npc2.verb(start)] thrusting it in and out, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Thrusting [npc.her] [npc.tentacle+(true)] deep into [npc2.namePos] [npc2.asshole+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump it in and out, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tentacle(true)] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] out of [npc2.namePos] [npc2.assCloaca],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a gentle hold of it, before softly forcing it back into [npc2.her] [npc2.asshole+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
									+ " before gently grinding [npc2.her] [npc2.assCloaca] back and forcing it inside [npc2.her] [npc2.asshole+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] out of [npc2.namePos] [npc2.assCloaca],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a rough hold of it, before aggressively forcing it back into [npc2.her] [npc2.asshole+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
									+ " before roughly slamming [npc2.her] [npc2.assCloaca] back and forcing it inside [npc2.her] [npc2.asshole+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.tentacle+(true)]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] out of [npc2.namePos] [npc2.assCloaca],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a firm hold of it, before eagerly forcing it back into [npc2.her] [npc2.asshole+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
									+ " before eagerly grinding [npc2.her] [npc2.assCloaca] back and forcing it inside [npc2.her] [npc2.asshole+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.tentacle(true)] away from [npc2.namePos] [npc2.assCloaca+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.tentacle+(true)]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop tentacle-pegging";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tentacle+(true)] out of [npc2.namePos] [npc2.asshole+] and stop tentacle-pegging [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.tentacle+(true)] out of [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.tentacleTip+] up and down over [npc2.her] [npc2.asshole+] one last time before pulling back.",

							"Thrusting deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.tentacle+(true)] back out of [npc2.her] [npc2.asshole+], putting an end to the rough tentacle-pegging."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tentacle(true)] out of [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(rub)] the [npc.tentacleTip] up and down over [npc2.her] [npc2.asshole+] one last time before pulling back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.tentacle+(true)] back out of [npc2.her] [npc2.asshole+], putting an end to the tentacle-pegging."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole],"
										+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(carry)] on weakly struggling against [npc.herHim].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.asshole+] away from [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+(true)] out of [npc2.her] [npc2.asshole+], eager for more of [npc.her] attention.",
	
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_TENTACLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Get tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc2.namePos] [npc2.tentacle+(true)] into your [npc.asshole+] and get tentacle-pegged.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] slowly [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] back and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
									+ " before slowly pushing [npc.her] [npc.hips] back and letting out a soft [npc.moan] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] eagerly [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " letting out [npc.a_moan+] before desperately bucking [npc.her] [npc.hips] back and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
									+ " before eagerly thrusting [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] roughly yank it up to [npc.her] [npc.assCloaca+],"
									+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] back and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
									+ " before eagerly slamming [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(guide)] it up to [npc.her] [npc.assCloaca+],"
									+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] back and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",

							"Grabbing [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
									+ " before pushing [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.tentacle+(true)]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " gently pushing [npc2.her] [npc2.tentacle(true)] forwards as [npc2.she] [npc2.verb(start)] to tentacle-fuck [npc.namePos] [npc.asshole+].",
	
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] tentacle-pegging [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " eagerly pushing [npc2.her] [npc2.tentacle(true)] forwards as [npc2.she] [npc2.verb(start)] enthusiastically tentacle-fucking [npc.namePos] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] energetically tentacle-pegging [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " and, seeking to remind [npc.name] who's in charge, [npc2.she] roughly slams [npc2.her] [npc2.tentacle(true)] forwards and [npc2.verb(start)] to ruthlessly tentacle-fuck [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.tentacle(true)] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly tentacle-fucking [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], pushing [npc2.her] [npc2.tentacle(true)] forwards as [npc2.she] [npc2.verb(start)] tentacle-fucking [npc.namePos] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] forwards, sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] tentacle-pegging [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.tentacle+(true)] free from [npc.her] [npc.asshole+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)] as deep as possible into [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.tentacle(true)] away from [npc.namePos] [npc.assCloaca],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
		
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",
		
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.tentacle(true)] away from [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] gently [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.tentacle+(true)] as deep as possible into [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] deep into [npc.namePos] [npc.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TENTACLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck your [npc.asshole+] on [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.tentacle+(true)] ever deeper into [npc.her] [npc.asshole+].",

					"Slowly thrusting [npc.her] [npc.hips] back,"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.asshole+] on [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] thrusting [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.tentacle+(true)] ever deeper into [npc.her] [npc.asshole+].",

					"Energetically thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [npc.asshole+] on [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] slamming [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.tentacle+(true)] ever deeper into [npc.her] [npc.asshole+].",

					"Forcefully thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements roughly force [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [npc.asshole+] on [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.tentacle+(true)] ever deeper into [npc.her] [npc.asshole+].",

					"Thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.asshole+] on [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+].",

					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] thrusting [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.tentacle+(true)] ever deeper into [npc.her] [npc.asshole+].",

					"Energetically thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.tentacle+(true)] deep into [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] slowly sliding in and out of [npc.her] [npc.asshole+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] gently sliding deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] frantically pumping in and out of [npc.her] [npc.asshole+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] greedily thrusting deep into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+].",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.assCloaca] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] roughly slamming in and out of [npc.her] [npc.asshole+].",

							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] violently thrusting deep into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Stop tentacle-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.tentacle(true)] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.name] to stop fucking [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.name] to stop fucking [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.tentacle(true)] out of [npc.her] [npc.asshole+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from fucking [npc.her] [npc.asshole+].",
	
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.namePos] [npc.asshole+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anal control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled asshole down around [npc2.namePos] [npc2.tentacle(true)].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] [npc.asshole] down around [npc2.namePos] [npc2.tentacle+(true)].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] [npc.asshole]."
							+ " Gripping and squeezing them down around [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim] to let out an involuntary cry of pleasure."
						:""),

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans] falling into a steady rhythm as [npc.she] [npc.verb(concentrate)]"
							+ " on squeezing the extra muscles within [npc.her] [npc.asshole+] down around [npc2.namePos] [npc2.tentacle+(true)].",

					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] [npc.asshole],"
							+ " gripping them down and massaging [npc2.namePos] [npc2.tentacle+(true)] as [npc.she] [npc.verb(squeal)] in pleasure.");
		}
	};
}
