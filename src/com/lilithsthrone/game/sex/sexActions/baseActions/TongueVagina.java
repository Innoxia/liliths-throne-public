package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueVagina {
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).isFeminine()) {
				return "Futa fun";
			} else {
				return "Herm fun";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pleasure both [npc2.namePos] [npc2.cock+] and [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
					&& Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] a gentle kiss,"
									+ " before pulling back and starting to slowly suck and kiss the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Delivering a gentle kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back,"
									+ " bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] a wet kiss,"
									+ " before pulling back and starting to eagerly suck and kiss the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Delivering a wet kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back,"
									+ " bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.her] [npc2.cock+] before eagerly taking [npc2.herHim] into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] a rough kiss,"
									+ " before pulling back and starting to dominantly suck and kiss the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] [npc.verb(deliver)] a long, rough lick up the length of [npc2.her] [npc2.pussy+],"
									+ " before pulling back and starting to dominantly kiss and suck the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Delivering a rough kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back,"
									+ " bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.her] [npc2.cock+] before dominantly taking [npc2.herHim] into [npc.her] mouth."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] a wet kiss,"
									+ " before pulling back and starting to eagerly suck and kiss the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Delivering a wet kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back,"
									+ " bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.her] [npc2.cock+] before eagerly taking [npc2.herHim] into [npc.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] a kiss,"
									+ " before pulling back and starting to suck and kiss the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] [npc.verb(deliver)] a long lick up the length of [npc2.her] [npc2.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc2.cockHead] of [npc2.her] [npc2.cock+].",

							"Delivering a kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back,"
									+ " bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan],"
									+ " gently pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth before [npc.she] decides to shift [npc.her] focus back to [npc2.her] [npc2.pussy+] once again.",

							" Gently thrusting [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan],"
									+ " before [npc.she] decides to move back down to focusing on [npc2.her] [npc2.pussy+].",

							" Softly [npc2.moaning], [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " eagerly pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth before [npc.she] decides to shift [npc.her] focus back to [npc2.her] [npc2.pussy+] once again.",

							" Eagerly thrusting [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " before [npc.she] decides to move back down to focusing on [npc2.her] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " roughly slamming [npc2.her] [npc2.cock] into [npc.namePos] mouth before [npc.she] decides to shift [npc.her] focus back to [npc2.her] [npc2.pussy+] once again.",

							" Roughly slamming [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " before [npc.she] decides to move back down to focusing on [npc2.her] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] roughly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " eagerly pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth before [npc.she] decides to shift [npc.her] focus back to [npc2.her] [npc2.pussy+] once again.",

							" Eagerly thrusting [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " before [npc.she] decides to move back down to focusing on [npc2.her] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " pushing [npc2.her] [npc2.cock] into [npc.namePos] mouth before [npc.she] decides to shift [npc.her] focus back to [npc2.her] [npc2.pussy+] once again.",

							" Thrusting [npc2.her] [npc2.cock] into [npc.namePos] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.she] decides to move back down to focusing on [npc2.her] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Sobbing] and squirming in discomfort, [npc2.name] desperately [npc2.verb(try)] to pull away from [npc.name],"
									+ " begging to be left alone as [npc.she] [npc.verb(shift)] [npc.her] attention back down to [npc2.her] [npc2.pussy+] once again.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to push [npc.name] away, squirming in discomfort as [npc.she] [npc.verb(move)] back to focusing [npc.her] attention on [npc2.her] [npc2.pussy+].",

							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(move)] back down to focusing on [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into [npc2.namePos] [npc2.pussy+] and start performing cunnilingus.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] a series soft kisses on [npc2.her] [npc2.labia+],"
									+ " before slowly, but firmly, sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Planting a series of soft kisses on [npc2.namePos] [npc2.labia+], [npc.name] gives [npc2.her] [npc2.pussy+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.labia+],"
									+ " before desperately sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.labia+], [npc.name] gives [npc2.her] [npc2.pussy+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] a series of forceful kisses on [npc2.her] [npc2.labia+],"
									+ " before greedily sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Planting a series of forceful kisses on [npc2.namePos] [npc2.labia+], [npc.name] gives [npc2.her] [npc2.pussy+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] a series of passionate kisses on [npc2.her] [npc2.labia+],"
									+ " before desperately sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.labia+], [npc.name] gives [npc2.her] [npc2.pussy+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] a series of kisses on [npc2.her] [npc2.labia+], before sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Planting a series of kisses on [npc2.namePos] [npc2.labia+], [npc.name] gives [npc2.her] [npc2.pussy+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Gently bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(order)] [npc.herHim] to keep going.",

							" Roughly thrusting [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] in an eager response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, commanding [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moanVerb] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] frantically [npc2.verb(try)] to wriggle away from [npc.namePos] unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",

							" [npc2.A_sob+] bursts out from [npc2.namePos] mouth, and, struggling against [npc.name], [npc2.she] [npc2.verb(beg)] for [npc.herHim] to take [npc.her] [npc.tongue] away from [npc2.her] pussy."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+].",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, eagerly bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.pussy].",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.labia+] down against [npc.namePos] [npc.face],"
								+ " before eagerly begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.pussy+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Failing to recoil [npc2.her] [npc2.hips] away from [npc.namePos] unwelcome [npc.tongue],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.she] [npc.verb(continue)] to lick and kiss [npc2.her] [npc2.pussy+].",
	
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.tongue],"
								+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to orally service [npc2.her] [npc2.pussy+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
								+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+].",
	
						" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, gently bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.pussy].",
	
						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.labia+] down against [npc.namePos] [npc.face],"
								+ " before eagerly begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.pussy+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.hips] back in response,"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+].",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, roughly slamming [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name] to continue servicing [npc2.her] [npc2.pussy].",
	
						" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.labia+] down against [npc.namePos] [npc.face],"
								+ " before aggressively commanding [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.pussy+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] back in response,"
								+ " letting out [npc2.a_moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+].",
	
						" [npc2.A_moan] drifts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue servicing [npc2.her] [npc2.pussy].",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.labia+] down against [npc.namePos] [npc.face],"
								+ " before begging [npc.herHim] to drive [npc.her] [npc.tongue+] as deep as possible into [npc2.her] [npc2.pussy+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.labia+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] [npc2.labia+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.pussy+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc2.namePos] [npc2.labia+],"
							+ " before leaning forwards and gently thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.labia+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.labia+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.pussy+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.labia+],"
							+ " before leaning forwards and enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.labia+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to roughly kiss and lick [npc2.namePos] [npc2.labia+],"
							+ " before pressing forwards and violently thrusting [npc.her] [npc.tongue] into [npc2.her] [npc2.pussy+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] forcefully kissing and licking [npc2.namePos] [npc2.labia+],"
							+ " before leaning forwards and roughly thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tongue] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc.verb(continue)] gently pressing [npc2.her] [npc2.pussy+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.pussy+],"
									+ " gently grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.pussy+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] violently [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.pussy+],"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc.verb(continue)] eagerly thrusting [npc2.her] [npc2.pussy+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.her] [npc2.pussy+],"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",

							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.labia+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to kiss and lick [npc2.namePos] [npc2.labia+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.pussy+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] kissing and nuzzling against [npc2.namePos] [npc2.labia+],"
							+ " before leaning forwards and thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.labia+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.her] [npc2.labia+] and [npc.verb(let)] out a muffled [npc.moan].",

					"Withdrawing [npc.her] [npc.tongue+] from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc2.namePos] [npc2.labia+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc2.her] [npc2.pussy+] once more.",

					"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc2.namePos] [npc2.labia+],"
							+ " before leaning forwards and enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of [npc2.namePos] [npc2.pussy+] and stop performing cunnilingus.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.pussy+].",

						"Giving [npc2.namePos] [npc2.labia+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.pussy+]."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.pussy+].",

						"Giving [npc2.namePos] [npc2.labia+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.pussy+]."));
				break;
		}
		
		switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc.verb(continue)] to struggle against [npc.name], [npc2.sobbing] and squirming in discomfort as [npc2.she] realise that [npc.name] isn't completely finished with [npc2.herHim] just yet.",

						" Realising that [npc.she] hasn't completely finished with [npc2.herHim] just yet, [npc2.name] [npc.verb(continue)] struggling and [npc2.sobbing],"
								+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] takes [npc.her] [npc.tongue+] away from [npc2.her] [npc2.pussy+].",
						
						" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(stop)] pleasuring [npc2.her] [npc2.pussy+], betraying [npc2.her] desire for more of [npc.namePos] attention."));
				break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start licking [npc.namePos] [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pressing [npc.her] [npc.labia+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] slowly grinding [npc.her] [npc.pussy+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.labia+],"
									+ " [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently pressing [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.labia+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.pussy+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.labia+],"
									+ " [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.labia+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] forcefully grinding [npc.her] [npc.pussy+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.labia+],"
									+ " [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.labia+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically grinding [npc.her] [npc.pussy+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.labia+],"
									+ " [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly pressing [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.labia+] down against [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] grinding [npc.her] [npc.pussy+] down on [npc2.her] [npc2.lips+].",

							"Shifting [npc.her] [npc.hips] so that [npc2.namePos] [npc2.face] is forced into [npc.her] [npc.labia+],"
									+ " [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] pressing [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly slides [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.pussy+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] gently licking and kissing [npc.her] [npc.labia+].",

							" Gently sliding [npc2.her] [npc2.tongue] out to deliver a long, slow lick to the [npc.labia+] pressing down against [npc2.her] [npc2.lips+],"
									+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of tender kisses on [npc.namePos] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily slides [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.pussy+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.labia+].",

							" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.labia+] pressing down against [npc2.her] [npc2.lips+],"
									+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.pussy+],"
									+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] roughly licking and kissing [npc.her] [npc.labia+].",

							" Greedily thrusting [npc2.her] [npc2.tongue] out to deliver a rough, wet lick to the [npc.labia+] pressing down against [npc2.her] [npc2.lips+],"
									+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of forceful kisses on [npc.namePos] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily slides [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.pussy+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] happily licking and kissing [npc.her] [npc.labia+].",

							" Greedily sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.labia+] pressing down against [npc2.her] [npc2.lips+],"
									+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of passionate kisses on [npc.namePos] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slides [npc2.her] [npc2.tongue+] into [npc.namePos] [npc.pussy+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] licking and kissing [npc.her] [npc.labia+].",

							" Sliding [npc2.her] [npc2.tongue] out to deliver a long, wet lick to the [npc.labia+] pressing down against [npc2.her] [npc2.lips+],"
									+ " [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.she] [npc2.verb(start)] planting a series of kisses on [npc.namePos] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling in desperation, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc.her] [npc.pussy+] down against [npc2.her] [npc2.lips+].",

							" [npc2.Sobbing] and struggling in distress, [npc2.namePos] protests prove to be in vain as [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] down over [npc2.her] [npc2.face]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+],"
								+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+].",
	
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
								+ " and, eagerly pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+].",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia],"
								+ " before eagerly driving [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.pussy+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Failing to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.labia],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.she] [npc.verb(continue)] to force [npc.her] [npc.labia+] down against [npc2.her] [npc2.face+].",
	
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.labia],"
								+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to press [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+],"
								+ " letting out a soft, muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+].",
	
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, gently pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+].",
	
						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia],"
								+ " before gently sliding [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.pussy+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+],"
								+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+].",
	
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, roughly grinding [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+].",
	
						" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia],"
								+ " before aggressively thrusting [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.pussy+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+],"
								+ " letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+].",
	
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth,"
								+ " and, pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.tongue+] deep into [npc.namePos] [npc.pussy+].",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.labia],"
								+ " before driving [npc2.her] [npc2.tongue+] as deep as possible into [npc.her] [npc.pussy+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently press your [npc.labia+] down over [npc2.namePos] [npc2.lips] in order to drive [npc2.her] [npc2.tongue+] into your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pressing [npc.her] [npc.labia+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] firmly plants [npc.her] [npc.pussy+] down over [npc2.her] [npc2.lips+].",

					"With a soft [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face+], before gently grinding [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+].",

					"Letting out a soft [npc.moan], [npc.name] gently [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly press your [npc.labia+] down over [npc2.namePos] face in order to drive [npc2.her] [npc2.tongue+] into your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.labia+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly plants [npc.her] [npc.pussy+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly grind your [npc.pussy+] down against [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly pressing [npc.her] [npc.labia+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] violently [npc.verb(slam)] [npc.her] [npc.pussy+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face+], before forcefully grinding [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist receiving cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.pussy+] away from [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.labia] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.namePos] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.pussy+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.pussy+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] plants a soft kiss on [npc.her] [npc.labia+],"
									+ " before gently sliding [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.pussy+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.labia+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] gently thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.labia] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.namePos] roughly [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.pussy+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.pussy+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] plants a wet kiss on [npc.her] [npc.labia+],"
									+ " before roughly thrusting [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.pussy+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.labia+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] firmly holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] roughly thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.labia] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.namePos] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue] deep into [npc.her] [npc.pussy+].",

							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.pussy+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] plants a passionate kiss on [npc.her] [npc.labia+],"
									+ " before greedily sliding [npc2.her] [npc2.tongue+] deep into [npc.her] [npc.pussy+].",

							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.labia+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] eagerly thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Press your [npc.pussy+] down against [npc2.namePos] [npc2.face+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pressing [npc.her] [npc.labia+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly plants [npc.her] [npc.pussy+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face+], before grinding [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly grind your [npc.pussy+] down against [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pressing [npc.her] [npc.labia+] down over [npc2.namePos] [npc2.face+],"
							+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] firmly plants [npc.her] [npc.pussy+] down over [npc2.her] [npc2.lips+].",

					"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face+], before greedily grinding [npc.her] [npc.pussy+] against [npc2.her] [npc2.lips+].",

					"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.pussy+] down against [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop receiving cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.tongue+] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc2.namePos] head away from [npc.her] [npc.pussy+], [npc.name] [npc.verb(order)] [npc2.herHim] to stop performing cunnilingus on [npc.herHim].",

							"Roughly grinding [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.hips] away, putting an end to the ongoing cunnilingus."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc2.namePos] head away from [npc.her] [npc.pussy+], [npc.name] [npc.verb(tell)] [npc2.herHim] to stop performing cunnilingus on [npc.herHim].",

							"Pressing [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(pull)] [npc.her] [npc.hips] away, putting an end to the ongoing cunnilingus."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] realises that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",

							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(continue)] struggling against [npc.name], begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.namePos] [npc.pussy+] more of [npc2.her] oral attention.",

							" Still hungry for more, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(move)] away from [npc2.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
