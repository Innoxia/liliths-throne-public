package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
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
 * TODO
 * 
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class ToyVagina {
	
	public static AbstractClothing getToyInVagina(GameCharacter character) {
		return character.getClothingInSlot(InventorySlot.VAGINA);
	}
	
	public static boolean isToyInVagina(GameCharacter character) {
		return getToyInVagina(character)!=null;
	}
	
//	private static boolean isToyVibrating(GameCharacter character) {
//		if(!isToyInVagina(character)) {
//			return false;
//		}
//		return getToyInVagina(character).isVibrator();
//	}
	
	public static final SexAction TEASE_TOY_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isToyInVagina(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Toy-tease (pussy)";
		}
		@Override
		public String getActionDescription() {
			return "Tease [npc2.name] by sliding the [npc.cockHead] of your [npc.cock] over [npc2.her] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(start)] slowly teasing the [npc.cockTip+] up and down between [npc2.her] [npc2.labia+], ready to penetrate [npc2.herHim] at any moment.",
							"With a soft [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to gently slide the [npc.cockTip] up and down between [npc2.her] [npc2.labia+].",
							"Gently sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.cockTip+] up and down between [npc2.her] [npc2.labia+], ready to start fucking [npc2.herHim] at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " before starting to roughly [npc2.verb(grind)] the [npc.cockTip] up and down between [npc2.her] [npc2.labia+].",
							"Roughly grinding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(start)] sliding the [npc.cockTip+] up and down between [npc2.her] [npc2.labia+], ready to penetrate [npc2.herHim] at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to slide the [npc.cockTip] up and down between [npc2.her] [npc2.labia+].",
							"Sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockTip+] up and down between [npc2.her] [npc2.labia+], ready to penetrate [npc2.herHim] at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.pussy+], before starting to eagerly slide the [npc.cockTip] up and down between [npc2.her] [npc2.labia+].",
							"Eagerly sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.pussy+] being stimulated,"
										+ " and [npc2.she] gently [npc2.verb(push)] [npc2.her] [npc2.pussy+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently pushing [npc2.her] [npc2.pussy+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.pussy+], and gently [npc2.verb(push)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.pussy+] being stimulated,"
										+ " and [npc2.she] eagerly [npc2.verb(push)] [npc2.her] [npc2.labia+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly thrusting [npc2.her] [npc2.pussy+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.pussy+], and desperately [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.pussy+] being stimulated,"
										+ " and [npc2.she] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.labia+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before roughly thrusting [npc2.her] [npc2.pussy+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.pussy+], and violently [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.pussy+] being stimulated,"
										+ " and [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.labia+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before thrusting [npc2.her] [npc2.pussy+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.pussy+], and [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of being about to lose [npc2.her] virginity.",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(realise)] that [npc2.she] might be about to lose [npc2.her] virginity.",
										" [npc2.Name] [npc2.sobVerb] in distress at the thought of losing [npc2.her] virginity to [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Don't! Please! I-I'm a virgin! You can't do this!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I'm still a virgin!)]",
										" [npc2.Name] [npc2.sobsVerb] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my virginity!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(struggle)] against [npc.name].",
										" [npc2.Name] [npc2.sobVerb] in distress as [npc2.she] [npc2.verb(try)] to pull away from [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.cock],"
												+ " [npc2.speech(No! Don't! Please, get away from me!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",
										" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
							}
						}
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FORCE_TOY_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Cock tease";
		}

		@Override
		public String getActionDescription() {
			return "Tease [npc2.name] by sliding the [npc2.cockTip] of [npc2.her] [npc2.cock] over your [npc.pussy+].";
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
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.pussy+]."
									+ " Slowly pushing the [npc2.cockTip+] up and down between [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
							"With a soft [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.pussy+],"
									+ " before starting to gently slide the [npc2.cockTip] up and down between [npc.her] [npc.labia+].",
							"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] gently [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.pussy+],"
									+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before yanking it up to [npc.her] [npc.pussy+]."
									+ " Roughly forcing the [npc2.cockTip+] up and down between [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and yank it up to [npc.her] [npc.pussy+],"
									+ " before starting to roughly [npc.verb(force)] the [npc2.cockTip] up and down between [npc.her] [npc.labia+].",
							"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(grind)] the [npc2.cockTip+] over [npc.her] [npc.pussy+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.pussy+]."
									+ " Pushing the [npc2.cockTip+] up and down between [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.pussy+],"
									+ " before starting to slide the [npc2.cockTip] up and down between [npc.her] [npc.labia+].",
							"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.pussy+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.pussy+]."
									+ " Eagerly pushing the [npc2.cockTip+] up and down between [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
							"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.pussy+],"
									+ " before starting to eagerly slide the [npc2.cockTip] up and down between [npc.her] [npc.labia+].",
							"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.pussy+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] gently rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] gently sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], before forcefully grinding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] enthusiastically rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] eagerly sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] away from [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before frantically trying to pull [npc2.her] [npc2.cock+] away from [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)] [npc.name] to let go of [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Pussy control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled pussy down around [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] [npc.pussy] down around [npc2.namePos] [npc2.cock+].",
					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] [npc.pussy]."
							+ " Gripping and squeezing them down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim] to let out an involuntary cry of pleasure."
						:""),
					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans] falling into a steady rhythm as [npc.she] [npc.verb(concentrate)]"
							+ " on squeezing the extra muscles within [npc.her] [npc.pussy+] down around [npc2.namePos] [npc2.cock+].",
					"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] [npc.pussy],"
							+ " gripping them down and massaging [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(squeal)] in pleasure.");
		}
	};
	
	public static final SexAction TOY_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [npc2.namePos] [npc2.pussy+] and start fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] it deep into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+], "
									+ "and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards, forcing [npc.her] [npc.cock+] deep into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a forceful thrust, [npc.she] roughly [npc.verb(slam)] it deep into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.labia+],"
									+ " and with a little thrust, [npc.she] [npc.verb(sink)] it deep into [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as the [npc.cock+] enters [npc2.herHim],"
										+ " before gently thrusting out [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.pussy+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips],"
										+ " sinking [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before violently thrusting out [npc2.her] [npc2.hips] in order to force it even deeper into [npc2.her] [npc2.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips],"
										+ " roughly forcing [npc.name] to sink [npc.her] [npc.cock+] even deeper into [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before eagerly bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips],"
										+ " desperately helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips],"
										+ " helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as the [npc.cock+] enters [npc2.herHim],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to pull out.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
										+ " tears running down [npc2.her] [npc2.face] as [npc.namePos] unwelcome [npc.cock] pushes deep into [npc2.her] [npc2.pussy+]."));
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
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] [npc2.pussy+].",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] [npc2.pussy+].",
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
									+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] [npc2.pussy+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips+] out,"
									+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.pussy+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.her] [npc2.pussy+].",
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, slowly bucking [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)] [npc.name] to carry on fucking [npc2.herHim].",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] back,"
									+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.pussy+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(demand)] that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly slamming [npc2.her] [npc2.hips] into [npc.her] groin, [npc2.she] [npc2.verb(order)] [npc.name] to carry on fucking [npc2.herHim].",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips+] out into [npc.namePos] groin,"
									+ " ordering [npc.herHim] to continue fucking [npc2.herHim] as [npc2.her] movements force [npc.her] [npc.cock+] deep into [npc2.her] [npc2.pussy+]."));
				default:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out,"
									+ " eagerly begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.pussy+]."));
			}
		}
		return "";
	}
	
	public static final SexAction TOY_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle fucking";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.cock] in and out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.name].",
					"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] softly [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.herHim].",
					"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
					"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",
					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough fucking";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] brutally [npc.verb(fuck)] [npc2.name].",
					"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(fuck)] [npc2.herHim].",
					"Forcefully driving [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] violently [npc.verb(fuck)] [npc2.herHim]."));
		
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
					"Pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.herHim].",
					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager fucking";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly pump your [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
					"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",
					"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist fucking";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] down and [npc2.verb(take)] a gentle hold of it, before softly forcing it back into [npc2.her] [npc2.pussy+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before gently forcing it back inside [npc2.her] [npc2.pussy+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc2.her] [npc2.pussy+] down onto [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a rough hold of it, before aggressively forcing it back into [npc2.her] [npc2.pussy+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before roughly forcing it back inside [npc2.her] [npc2.pussy+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc2.her] [npc2.pussy+] down onto [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy],"
									+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a firm hold of it, before eagerly forcing it back into [npc2.her] [npc2.pussy+].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before eagerly forcing it back inside [npc2.her] [npc2.pussy+].",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.pussy+] down onto [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop fucking";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] [npc2.pussy+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockTip+] up and down over [npc2.her] [npc2.labia+] one last time before pulling back.",
							"Thrusting deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.pussy+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] the [npc.cockTip] up and down over [npc2.her] [npc2.labia+] one last time before pulling back.",
							"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.pussy+], putting an end to the fucking."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.pussy],"
										+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(carry)] on weakly struggling against [npc.herHim].",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.pussy+] away from [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.her] [npc2.pussy+], eager for more of [npc.her] attention.",
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction USING_TOY_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Get fucked";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc2.namePos] [npc2.cock+] into your [npc.pussy+] and get fucked.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] slowly [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
										+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.pussy+],"
										+ " before slowly pushing [npc.her] [npc.hips] back and letting out a soft [npc.moan] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] roughly yank it up to [npc.her] [npc.labia+],"
										+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.pussy+],"
										+ " before eagerly slamming [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.pussy+],"
										+ " before pushing [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] eagerly [npc.verb(guide)] it up to [npc.her] [npc.labia+],"
										+ " letting out [npc.a_moan+] before desperately bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.pussy+],"
										+ " before eagerly thrusting [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slowly [npc.verb(move)] [npc.her] [npc.hips] until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.labia+],"
										+ " and then with a little [npc.moan], [npc.she] gently [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Pushing [npc.her] [npc.pussy+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up between [npc.her] [npc.labia+],"
										+ " before slowly stepping back and letting out a soft [npc.moan] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] forcefully [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.labia+],"
										+ " and then with [npc.a_moan+], [npc.she] violently [npc.verb(slam)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Roughly pushing [npc.her] [npc.pussy+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up between [npc.her] [npc.labia+],"
										+ " before dominantly stepping back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.labia+],"
										+ " and then with [npc.a_moan+], [npc.she] [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Pushing [npc.her] [npc.pussy+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up between [npc.her] [npc.labia+],"
										+ " before stepping back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.labia+],"
										+ " and then with [npc.a_moan+], [npc.she] greedily [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.pussy+].",
								"Desperately pushing [npc.her] [npc.pussy+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up between [npc.her] [npc.labia+],"
										+ " before eagerly stepping back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " gently pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] to fuck [npc.namePos] [npc.pussy+].",
	
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.pussy+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " eagerly pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.namePos] [npc.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.pussy+] as [npc2.she] [npc2.verb(start)] energetically fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " and, seeking to remind [npc.name] who's in charge, [npc2.she] roughly slams [npc2.her] [npc2.cock] forwards and [npc2.verb(start)] to ruthlessly fuck [npc.her] [npc.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly fucking [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] fucking [npc.namePos] [npc.pussy+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards, sinking it deep into [npc.namePos] [npc.pussy+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] free from [npc.her] [npc.pussy+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep into [npc.her] [npc.pussy+]."));
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
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.pussy],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.pussy+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] gently [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" A gentle [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.pussy+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+]."));
				default:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.pussy+]."));
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TOY_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently fucked";
		}

		@Override
		public String getActionDescription() {
			return "Gently have your [npc.pussy+] fucked by [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a gentle thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Slowly gyrating [npc.her] [npc.hips], a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.pussy+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly fucked";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [npc.pussy+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a violent slam of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Forcefully gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements roughly force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TOY_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Fucked";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [npc.pussy+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With a quick buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly fucked";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.pussy+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+].",
					"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.pussy+].",
					"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist being fucked";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.pussy+] away from [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.pussy+].",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] slowly sliding in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] gently sliding deep into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.pussy+].",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] violently thrusting deep into [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.pussy+].",
							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.pussy] away from [npc2.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] frantically pumping in and out of [npc.her] [npc.pussy+].",
							"Trying desperately to pull [npc.her] [npc.hips] away,"
									+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] greedily thrusting deep into [npc.her] [npc.pussy+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Stop being fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.cock] out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.name] to stop fucking [npc.herHim].",
							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out of [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock] out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.name] to stop fucking [npc.herHim].",
							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out of [npc.her] [npc.pussy+]."));
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
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from fucking [npc.her] [npc.pussy+].",
								" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.namePos] [npc.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
