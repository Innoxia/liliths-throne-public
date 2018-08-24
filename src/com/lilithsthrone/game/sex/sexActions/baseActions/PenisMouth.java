package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class PenisMouth {
	
	public static final SexAction COCK_SLAP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Cock slap";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock] out of [npc2.namePos] mouth and slap [npc2.her] face with it.";
		}

		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(pull)] back, sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
							+ " As [npc2.name] look up at [npc.herHim], [npc.she] quickly [npc.verb(slap)] [npc.her] hard shaft against [npc2.namePos] cheek, splattering "
								+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"cummy ":"wet ")
								+"saliva across [npc2.her] face, before thrusting [npc.her] [npc.cock+] back down [npc2.her] throat.",

						"Stepping back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] free from [npc2.namePos] mouth, before proceeding to slap the saliva-coated [npc1.cockHead] against [npc2.her] cheeks."
								+ " As [npc2.name] [npc2.verb(open)] [npc2.namePos] mouth to gasp in shock, [npc.name] [npc2.verb(use)] the opportunity to guide the [npc.cockHead] of [npc.her] [npc.cock] back past [npc2.namePos] [npc2.lips+],"
								+ " before pushing it deep down [npc2.namePos] throat.",

						"[npc.Name] [npc.verb(step)] back and [npc.verb(allow)] [npc.her] [npc.cock+] to slide out from [npc2.namePos] mouth."
							+ " Looking up at [npc.herHim], [npc2.nameIs] taken by surprise as [npc.name] [npc.verb(slap)] [npc.her] hard shaft against [npc2.her] face,"
							+ " leaving a streak of "+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"cummy ":"wet ")
								+"saliva drooling down [npc2.her] cheek, before forcing [npc.her] [npc.cock+] back down [npc2.her] throat.",

						"Quickly pulling [npc.her] [npc.cock+] out from [npc2.namePos] mouth, [npc.name] [npc.verb(hold)] the base in one hand while holding [npc2.namePos] head still with the other."
							+ " As [npc2.name] [npc2.verb(look)] up to see what's happening, [npc2.sheIs] met with a wet slap as [npc.name] [npc.verb(swing)] [npc.her] slimy [npc.cock] against [npc2.her] cheek."
							+ " As [npc2.name] [npc2.verb(open)] [npc2.her] mouth in shock, [npc.name] [npc.verb(use)] the opportunity to thrust [npc.her] [npc.cock+] back down [npc2.her] throat.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"Pulling [npc.her] [npc.hips] back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
							+ " Before [npc2.name] can react, [npc.she] quickly [npc.verb(slap)] [npc.her] hard shaft against [npc2.her] cheek, splattering saliva "
							+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"and precum ":"")
							+"across [npc2.her] [npc2.face], before thrusting [npc.her] [npc.cock+] back down [npc2.her] throat.",

						"Pulling back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] free from [npc2.namePos] mouth,"
								+ " and with [npc.a_moan+], [npc.she] [npc.verb(proceed)] to slap the saliva-coated [npc.cockHead] against [npc2.her] [npc2.face].",

						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from [npc2.namePos] mouth, and, grinning to [npc.herself], [npc.she] then [npc.verb(slap)] [npc.her] hard shaft against [npc2.namePos] [npc2.face],"
							+ " leaving a streak of "+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"cummy ":"wet ")
							+"saliva drooling down [npc2.her] cheek, before forcing [npc.her] [npc.cock] back down [npc2.her] throat.",

						"Quickly pulling [npc.her] [npc.hips+] back, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] out from [npc2.namePos] mouth, before starting to slap [npc.her] slimy length against [npc2.namePos] cheeks.");
			}
		}
	};
	
	public static final SexAction FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isInternalTesticles()
					&& Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Balls focus";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to pay some attention to your [npc.balls+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth,"
									+ " before shuffling about until [npc.her] [npc.balls+] are gently pressing against [npc2.her] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are gently pressing against [npc2.her] [npc2.lips+].",

							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are gently pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] gently pressing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth,"
									+ " before shuffling about until [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",

							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc.her] [npc.hips] back, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth,"
									+ " before shuffling about until [npc.her] [npc.balls+] are roughly grinding against [npc2.her] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are roughly grinding against [npc2.her] [npc2.lips+].",

							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are roughly grinding against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] roughly forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth,"
									+ " before shuffling about until [npc.her] [npc.balls+] are pressing down against [npc2.her] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",

							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				default:
					break;
			
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Slowly sliding [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to gently lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] gently [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc2.her] [npc2.tongue+] out, [npc2.name] greedily [npc2.verb(start)] to lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] eagerly [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Sliding [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to roughly lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] roughly [npc2.verb(start)] kissing and licking [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Darting [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull away, letting out [npc2.a_sob+] as [npc.namePos] [npc.balls+] [npc.verb(continue)] grinding against [npc2.her] [npc2.lips+].",

							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], trying desperately to pull away from [npc.namePos] [npc.balls+] as [npc2.she] [npc2.verb(struggle)] against [npc.herHim]."));
					break;
				default:
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction SUCK_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick balls";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss [npc2.namePos] [npc2.balls] for a while.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc2.namePos] [npc2.cock+] slip completely out of [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to gently lick and suck on [npc2.her] [npc2.balls+].",

							"[npc.Name] [npc2.verb(let)] [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start gently licking and kissing [npc2.her] [npc2.balls+].",

							"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc2.her] head down to start gently kissing and licking [npc2.her] [npc2.balls+].",

							"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down, before starting to gently kiss and nuzzle into [npc2.her] [npc2.balls+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc2.namePos] [npc2.cock+] slip completely out of [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to eagerly lick and suck on [npc2.her] [npc2.balls+].",

							"[npc.Name] [npc2.verb(let)] [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start desperately licking and kissing [npc2.her] [npc2.balls+].",

							"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc2.her] head down to start eagerly kissing and licking [npc2.her] [npc2.balls+].",

							"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down, before starting to eagerly kiss and nuzzle into [npc2.her] [npc2.balls+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc2.namePos] [npc2.cock+] slip completely out of [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to roughly lick and suck on [npc2.her] [npc2.balls+].",

							"[npc.Name] [npc2.verb(let)] [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start roughly licking and kissing [npc2.her] [npc2.balls+].",

							"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc2.her] head down to start roughly kissing and licking [npc2.her] [npc2.balls+].",

							"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down, before starting to roughly kiss and nuzzle into [npc2.her] [npc2.balls+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc2.namePos] [npc2.cock+] slip completely out of [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to lick and suck on [npc2.her] [npc2.balls+].",

							"[npc.Name] [npc2.verb(let)] [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start licking and kissing [npc2.her] [npc2.balls+].",

							"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc2.her] head down to start kissing and licking [npc2.her] [npc2.balls+].",

							"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, [npc.name] [npc.verb(move)] [npc.her] head down, before starting to kiss and nuzzle into [npc2.her] [npc2.balls+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction LICK_HEAD = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick head";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();
			
			for(PenetrationModifier pm : Sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
				switch(pm) {
					case BARBED:
						descriptions.add("[npc.Name] [npc.verb(slide)] [npc.her] head back, letting out a muffled [npc.moan] as [npc.she] [npc.verb(feel)] the barbs lining [npc2.namePos] [npc2.cock] rake their way up [npc.her] throat."
								+ " Leaving just the [npc2.cockHead+] pushed past [npc.her] [npc.lips+], [npc.name] then [npc.verb(start)] to passionately kiss and suck the tip of [npc2.her] [npc2.cock+].");
						break;
					case BLUNT:
						break;
					case FLARED:
						descriptions.add("Sliding [npc.her] head back, [npc.name] [npc.verb(allow)] [npc2.namePos] [npc2.cock+] to slip almost completely out of [npc.her] mouth,"
								+ " leaving just the wide, flared head pushed past [npc.her] [npc.lips+], which [npc.she] then [npc.verb(start)] to passionately kiss and lick.");
						break;
					case KNOTTED:
						break;
					case PREHENSILE:
						break;
					case RIBBED:
						break;
					case SHEATHED:
						break;
					case TAPERED:
						break;
					case TENTACLED:
						break;
					case VEINY:
						break;
				}
			}
			
			descriptions.add("With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+], before starting to lick and kiss it.");
			
			descriptions.add("[npc.Name] [npc.verb(pull)] [npc.her] head back, letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] concentrating on sucking and kissing the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+].");
			
			descriptions.add("Pulling [npc.her] head back a little, [npc.name] [npc.verb(let)] most of [npc2.namePos] [npc2.cock+] slide out of [npc2.her] mouth, and,"
					+ " focusing on using [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] licking and kissing the [npc2.cockHead+] that's left poking past [npc.her] [npc.lips+].");
			
			return Util.randomItemFrom(descriptions);
		}
		
	};
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)
					&& Sex.isOrificeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
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
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a gentle suck,"
									+ " before pulling back and starting to slowly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and gently sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a gentle kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before slowly pushing [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to eagerly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and eagerly darting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a wet kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before eagerly darting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to roughly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, roughly lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and dominantly thrusting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a rough kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before dominantly thrusting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and darting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a wet kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before darting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan],"
									+ " gently pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] [npc2.verb(focus)] back to [npc2.her] [npc2.cock+] once again.",

							" Gently thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" Softly [npc2.moaning], [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " eagerly pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] [npc2.verb(focus)] back to [npc2.her] [npc2.cock+] once again.",

							" Eagerly thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " roughly grinding [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] [npc2.verb(focus)] back to [npc2.her] [npc2.cock+] once again.",

							" Roughly grinding [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] roughly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] [npc2.verb(focus)] back to [npc2.her] [npc2.cock+] once again.",

							" Thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Sobbing] and squirming in discomfort, [npc2.she] desperately tries to pull away from [npc.herHim],"
									+ " begging to be left alone as [npc.she] [npc.verb(shift)] [npc.her] attention back down to [npc2.her] [npc2.cock+] once again.",

							" With [npc2.a_sob+], [npc2.name] tries to push [npc.name] away, squirming in discomfort as [npc.she] [npc.verb(move)] back to focusing [npc.her] attention on [npc2.her] [npc2.cock+].",

							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(move)] back to focusing on [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] into [npc2.namePos] mouth and get [npc2.herHim] to give you a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before gently pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before slowly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and gently sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before eagerly pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before eagerly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] into [npc2.her] [npc2.face] and forcing [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly [npc.verb(lower)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before gently pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Gently lowering [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] slowly [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before eagerly pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.cock+] down against [npc2.namePos] mouth,"
										+ " before forcefully pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Roughly grinding [npc.her] [npc.cock+] down against [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] gently [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], gently sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly slams [npc.her] [npc.hips] forwards and [npc.verb(force)] [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Grinding the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], forcing [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan], slowly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] gently sucking [npc.namePos] [npc.cock+].",

							" With a soft, muffled [npc2.moan], [npc2.name] [npc2.verb(start)] gently sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] happily sucking [npc.namePos] [npc.cock+].",

							" With an eager, and very muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] head forwards as [npc2.she] [npc2.verb(start)] forcing [npc.namePos] [npc.cock+] deep down [npc2.her] throat.",

							" With an eager, and very muffled, [npc2.moan], [npc2.name] forcefully [npc2.verb(push)] [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a rough blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] sucking [npc.namePos] [npc.cock+].",

							" With a muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], gargling and choking on [npc.namePos] [npc.cock+] as [npc2.she] frantically [npc2.verb(try)] to pull [npc2.her] head away from [npc.her] groin.",

							" With a muffled [npc2.sob], [npc2.name] frantically [npc2.verb(try)] to pull away from [npc.namePos] [npc.cock+],"
									+ " gargling and choking as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(struggle)] against [npc.herHim]."));
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
						" [npc2.Name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] enthusiastically [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, eagerly wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] enthusiastically [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " eagerly licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull back from [npc.namePos] [npc.cock],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(make)] muffled noises of protest.",
	
						" A muffled [npc2.sob] escapes from [npc2.namePos] mouth as [npc2.she] weakly [npc2.verb(try)] to pull back,"
								+ " tears streaming down [npc2.her] [npc2.face] as [npc.namePos] [npc.cock+] continues sliding back and forth past [npc2.her] [npc2.lips+].",
	
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name] as [npc2.she] [npc2.verb(make)] muffled noises of protest."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] slowly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, gently wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] lovingly [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " gently licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] forcefully [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] roughly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, forcefully wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] roughly [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.cock+],"
								+ " forcefully licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
		}
		return "";
	}
	
	public static final SexAction BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Hold still and let [npc2.name] suck your [npc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] gently [npc.verb(slide)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out a soft [npc.moan] as [npc.she] steadily [npc.verb(pump)] [npc.her] [npc.hips] forwards into [npc2.her] [npc2.face].",
	
						"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] throat.",
	
						"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(continue)] receiving [npc.her] blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] forwards into [npc2.her] [npc2.face].",
	
						"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly fucks [npc2.her] throat.",
	
						"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] down [npc2.namePos] throat and give [npc2.herHim] a good face-fucking.";
		}

		@Override
		public String getDescription() {
			
			List<String> descriptions = new ArrayList<>();

			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_RECEIVING_ORAL
					|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.CHAIR_ORAL_SITTING) {
				
				for(PenetrationModifier pm : Sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as the barbs lining [npc.namePos] shaft repeatedly rake up the sides of [npc2.her] throat.");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] wide, flared head forces its way up and down [npc2.her] throat.");
							break;
						case KNOTTED:
							descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
												+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] [npc.verb(slam)] [npc.her] knot repeatedly against [npc2.her] [npc2.lips+].");
							break;
						case PREHENSILE:
							break;
						case RIBBED:
							break;
						case SHEATHED:
							break;
						case TAPERED:
							break;
						case TENTACLED:
							break;
						case VEINY:
							break;
					}
				}
				
				descriptions.add("[npc.Name] [npc.verb(grab)] the sides of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
									+ " [npc.nameIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");

				descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
									+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly bucking [npc.her] [npc.hips] back and forth,"
									+ " holding [npc2.namePos] head in place with both hands as [npc.she] ruthlessly fucks [npc2.her] [npc2.face].");

				descriptions.add("Grabbing the sides of [npc2.namePos] head, [npc.name] roughly [npc2.verb(pull)] [npc2.her] face into [npc.her] groin,"
									+ " sinking [npc.her] [npc.cock+] deep down [npc2.her] throat before starting to ruthlessly fuck [npc2.her] [npc2.face].");

				descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of saliva "+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"and precum ":"")
									+"drools from the corners of [npc2.her] mouth, [npc.name] bucks back, letting [npc2.name] gasp for air for a brief moment before starting to aggressively fuck [npc2.her] [npc2.face].");
				
				return Util.randomItemFrom(descriptions);
				
				
			} else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				for(PenetrationModifier pm : Sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being raked by the series of barbs that line the sides of [npc.her] [npc.cock+].");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being stretched out by the wide, flared head of [npc.her] [npc.cock+].");
							break;
						case KNOTTED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.lips+] being repeatedly battered by the fat knot at the base of [npc.her] [npc.cock+].");
							break;
						case PREHENSILE:
							break;
						case RIBBED:
							break;
						case SHEATHED:
							break;
						case TAPERED:
							break;
						case TENTACLED:
							break;
						case VEINY:
							break;
					}
				}
				
				descriptions.add("[npc.Name] spreads [npc.her] knees out on either side of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
									+ " [npc.nameIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");
				

				descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
									+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly slamming [npc.her] [npc.hips] up and down,"
									+ " letting out more [npc.moans+] as [npc.she] ruthlessly fucks [npc2.her] [npc2.face].");

				descriptions.add("Dropping down onto [npc2.namePos] face, [npc.name] roughly slams [npc.her] [npc.cock+] deep down [npc2.her] throat,"
									+ " letting out [npc.a_moan+] before starting to violently slam [npc.her] [npc.hips] up and down as [npc.she] ruthlessly fucks [npc2.her] face.");

				descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of "+(Sex.hasLubricationTypeFromAnyone(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"cummy ":"")
									+"saliva drools from the corners of [npc2.her] mouth, [npc.name] lifts [npc.herself] up,"
										+ " letting [npc2.name] gasp for air for a brief moment before sinking down once more and starting to aggressively fuck [npc2.her] face.");

				return Util.randomItemFrom(descriptions);
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly [npc.verb(slam)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] violently [npc.verb(thrust)] [npc.her] [npc.hips] forwards into [npc2.her] [npc2.face].",
		
							"[npc.Name] desperately [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] roughly fucks [npc2.her] throat.",
		
							"Aggressively bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.her] face."));

				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc2.namePos] [npc2.cock] out of your mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] gently, but firmly,"
								+ " holds [npc.herHim] in place, slowly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.she] [npc2.verb(continue)] giving [npc.herHim] an unwanted blowjob.",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be too strong, however, and [npc2.name] gently, but firmly, [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] a slow, gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] roughly hold [npc.herHim] in place,"
								+ " growling in a threatening manner as [npc2.she] forcefully [npc2.verb(slide)] [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+].",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be far too strong, however, and [npc2.name] [npc2.verb(let)] out a threatening growl as [npc2.name] [npc.verb(continue)] to suck [npc.her] [npc.cock+].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] a rough, forceful blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] firmly hold [npc.herHim] in place,"
								+ " eagerly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.she] [npc2.verb(continue)] giving [npc.herHim] an unwanted blowjob.",

						"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " [npc2.Her] grip proves to be too strong, however, and [npc2.she] eagerly [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] an eager blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] forwards into [npc2.her] [npc2.face].",
	
						"[npc.Name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] fucks [npc2.her] throat.",
	
						"Bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.hips] into [npc2.namePos] face, forcing your [npc.cock+] down [npc2.her] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
								+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] forwards into [npc2.her] [npc2.face].",
	
						"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly fucks [npc2.her] throat.",
	
						"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
								+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop receiving blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] mouth and stop receiving a blowjob form [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.SIXTY_NINE_TOP) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide up and out of [npc2.namePos] mouth."
							+ " A slimy strand of saliva links [npc2.namePos] [npc2.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock] for a brief moment, before breaking to fall down over [npc2.her] face.");
				
			} else {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] deep down [npc2.namePos] throat one last time, [npc.name] then [npc2.verb(pull)] [npc.her] [npc.hips] back,"
										+ " grinning as [npc2.name] [npc2.verb(splutter)] and [npc2.verb(gasp)] for air.",

								"Slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.name] forces [npc.her] [npc.cock+] deep down [npc2.her] throat, before pulling completely back and out of [npc2.her] mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] puts an end to the blowjob.",

								"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] back, sliding [npc.her] [npc.cock+] fully out of [npc2.namePos] mouth."));
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling in desperation as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go.",

							" [npc2.Name] [npc2.verb(feel)] tears streaming down [npc2.her] cheeks as [npc2.she] [npc2.verb(try)] to struggle out of [npc.namePo] grip,"
									+ " letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire to continue sucking [npc.namePos] [npc.cock+].",

							" [npc2.Name] [npc2.moanVerb] as [npc.name] withdraws from [npc2.her] mouth, betraying [npc2.her] desperate desire to continue sucking [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] into your mouth and start giving [npc2.herHim] a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.namePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] slowly [npc.verb(lower)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Gently lowering [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.cock+], before taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.namePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] eagerly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before greedily taking [npc2.herHim] into [npc.her] mouth.",

								"Eagerly dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+], before greedily taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.namePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] quickly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " roughly kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before forcefully taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a rough, wet lick up the length of [npc2.her] [npc2.cock+], before forcefully taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.namePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+], before taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] slowly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a gentle blowjob."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] eagerly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] happily [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] a gentle blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] forcefully [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a rough blowjob.",

								"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a rough blowjob."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] a blowjob."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], softly [npc2.moaning] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With a gentle buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With an energetic buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With an rough [npc.verb(thrust)] of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" Bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], trying, and failing, to pull [npc2.her] [npc2.cock] out of [npc.namePos] mouth as [npc2.she] [npc2.verb(struggle)] against the unwanted oral attention.",

							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(struggle)] against the unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] as deep as possible down your throat.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] [npc.verb(push)] [npc.her] head forwards,"
									+ " taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",

							"Slowly sliding [npc.her] head forwards, [npc.name] gently [npc.verb(part)] [npc.her] [npc.lips+], before sliding [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " greedily taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] eagerly [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] desperately [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",

							"Greedily sliding [npc.her] head forwards, [npc.name] readily [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] roughly [npc.verb(slam)] [npc.her] head forwards,"
									+ " forcing [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] roughly [npc.verb(force)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",

							"Aggressively pushing [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
									+ " taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",

							"With a muffled, [npc.moan+], [npc.name] [npc.verb(lean)] forwards,"
									+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",

							"Sliding [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
					break;
				default:
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep down [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] enthusiastically receives [npc2.her] blowjob.",
	
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep down [npc.namePos] throat.",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] throat."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.pussy],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] deep into [npc.her] throat.",
	
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] mouth."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
								+ " letting out a soft, muffled [npc2.moan] as [npc2.she] receives [npc2.her] blowjob.",
	
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",

						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] receives [npc2.her] blowjob.",
	
						" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",

						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] throat."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
								+ " letting out a muffled [npc2.moan] as [npc2.she] receives [npc2.her] blowjob.",
	
						" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",

						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat."));
				break;
		}
		return "";
	}
	
	public static final SexAction GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Gently suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a loving blowjob.",

					"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",

					"Slowly bobbing [npc.her] head up and down, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] gives [npc2.herHim] a blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic blowjob.",

					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",

					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] gives [npc2.herHim] an eager blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Roughly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Forcefully gripping [npc.her] [npc.lips+] down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] aggressively bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a rough blowjob.",

					"With a muffled, [npc.moan+], [npc.name] violently [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " roughly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",

					"Roughly bobbing [npc.her] head up and down, [npc.name] dominantly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] gives [npc2.herHim] a forceful blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist performing blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try and push [npc2.namePos] [npc2.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
									+ " slowly sliding [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",
	
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] gently pushing [npc2.her] [npc2.cock+] down [npc.her] throat.",
	
							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] gently sliding [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] forcefully holds [npc.herHim] in place,"
									+ " roughly pumping [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] roughly slamming [npc2.her] [npc2.cock+] down [npc.her] throat.",

							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] roughly thrusting [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
									+ " eagerly sliding [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",

							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
									+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] eagerly pushing [npc2.her] [npc2.cock+] down [npc.her] throat.",

							"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
									+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] eagerly sliding [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Continue sucking [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a blowjob.",

					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",

					"Rapidly bobbing [npc.her] head up and down, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] gives [npc2.herHim] a blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic blowjob.",

					"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
							+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",

					"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] gives [npc2.herHim] an eager blowjob."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] out of your mouth and stop giving [npc2.herHim] a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc2.namePos] [npc2.cock+] down [npc.her] throat one last time, [npc.name] then [npc.verb(pull)] [npc.her] head back, putting a quick end to [npc2.her] blowjob.",

							"Slamming [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat,"
									+ " before pulling completely back and letting [npc2.herHim] slip out of [npc.her] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock+] out of [npc.her] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(put)] an end to the blowjob.",

							"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, sliding [npc2.namePos] [npc2.cock+] fully out of [npc.her] mouth."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to let [npc2.herHim] go.",

							" Tears stream down [npc2.namePos] cheeks as [npc2.she] tries to struggle out of [npc.namePos] grip, letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire for [npc.name] to continue sucking [npc2.her] [npc2.cock+].",

							" [npc2.Name] [npc2.moansVerb] as [npc.name] withdraws from [npc2.her] groin, betraying [npc2.her] desire to feel [npc.her] [npc.lips+] wrapped around [npc2.her] [npc2.cock+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
