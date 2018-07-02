package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

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
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.84
 * @author Innoxia
 */
public class PartnerPenisMouth {
	/*
	 * Player - Cock slap
	 * Partner - Suck balls, lick head, herm fun
	 * 
	 * Player [npc2.verb(start)]/stop
	 * Receiving blowjob:
	 * 		Dom gentle, normal, rough
	 * 		Sub normal, eager, resist
	 * 
	 * Partner [npc2.verb(start)]/stop
	 * Partner giving blowjob:
	 * 		Dom gentle, normal, rough
	 * 		Sub normal, eager, resist
	 */
	
	public static final SexAction PARTNER_COCK_SLAP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Cock slap";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock] out of [npc2.namePos] mouth and slap [npc2.her] face with it.";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				return UtilText.returnStringAtRandom(
						"[npc.Name] suddenly [npc2.verb(pull)]s back, sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
							+ " As [npc2.name] look up at [npc.herHim], [npc.she] slaps [npc.her] hard shaft against [npc2.namePos] cheek, splattering "
								+(Sex.getWetAreas(Sex.getCharacterTargetedForSexAction(this)).get(SexAreaOrifice.MOUTH).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
								+"saliva across [npc2.namePos] face, before thrusting [npc.her] [npc.cock+] back down [npc2.namePos] throat.",

								
						"Stepping back, [npc.name] slides [npc.her] [npc.cock+] free from [npc2.namePos] mouth, and then proceeds to slap the saliva-coated head against [npc2.namePos] cheeks."
								+ " As [npc2.name] open [npc2.namePos] mouth to gasp in shock, [npc.she] uses the opportunity to guide [npc.her] the [npc.cockHead] of [npc.her] [npc.cock] back past [npc2.namePos] lips, before pushing it deep down [npc2.namePos] throat.",

						
						"[npc.Name] suddenly steps back and allows [npc.her] [npc.cock+] to slide out from [npc2.namePos] mouth."
							+ " Looking up at [npc.herHim], you're taken by surprise as [npc.she] slaps [npc.her] hard shaft against [npc2.namePos] face,"
							+ " leaving a streak of "+(Sex.getWetAreas(Sex.getCharacterTargetedForSexAction(this)).get(SexAreaOrifice.MOUTH).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
							+"saliva drooling down [npc2.namePos] cheek, before forcing [npc.her] [npc.cock+] back down [npc2.namePos] throat.",

								
						"Quickly pulling [npc.her] [npc.cock+] out from [npc2.namePos] mouth, [npc.name] holds the base in one hand while holding [npc2.namePos] head still with the other."
							+ " As [npc2.name] glance up to see what [npc.she]'s planning next, you're met with a wet slap as [npc.she] swings [npc.her] slimy [npc.cock] against [npc2.namePos] cheek."
							+ " Opening [npc2.namePos] mouth in shock, [npc.she] uses the opportunity to thrust [npc.her] [npc.cock+] back down [npc2.namePos] throat.");
				
			} else {
			
				return UtilText.returnStringAtRandom(
						"Pulling [npc.her] [npc.hips] back, [npc.name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
							+ " Before [npc2.name] can react, [npc.she] suddenly slaps [npc.her] hard shaft against [npc2.namePos] cheek, splattering saliva "
							+(Sex.getWetAreas(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).contains(LubricationType.PARTNER_PRECUM)?"and precum ":"")+"across [npc2.namePos] [npc2.face], before thrusting [npc.her] [npc.cock+] back down [npc2.namePos] throat.",

								
						"Pulling back, [npc.name] slides [npc.her] [npc.cock+] free from [npc2.namePos] mouth, and with [npc.a_moan+], [npc.she] proceeds to slap the saliva-coated [npc.cockHead] against [npc2.namePos] [npc2.face].",

						
						"[npc.Name] slides [npc.her] [npc.cock+] out from [npc2.namePos] mouth, and, grinning to [npc.herself], [npc.she] then slaps [npc.her] hard shaft against [npc2.namePos] [npc2.face],"
							+ " leaving a streak of "+(Sex.getWetAreas(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
							+"saliva drooling down [npc2.namePos] cheek, before forcing [npc.her] [npc.cock] back down [npc2.namePos] throat.",

								
						"Quickly pulling [npc.her] [npc.hips+] back, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] out from [npc2.namePos] mouth before starting to slap its slimy length against [npc2.namePos] cheeks.");
			
			}
		}
		
	};
	
	public static final SexAction PARTNER_FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isInternalTesticles() && Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Balls focus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(force)] [npc2.name] to pay some attention to [npc.namePos] [npc.balls+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth, before shuffling about until [npc.her] [npc.balls+] are gently pressing against [npc2.namePos] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are gently pressing against [npc2.namePos] [npc2.lips+].",

							"[npc.Name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.namePos] [npc2.lips+] are gently pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] until [npc.she]'s gently pressing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth, before shuffling about until [npc.her] [npc.balls+] are pressing against [npc2.namePos] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.namePos] [npc2.lips+].",

							"[npc.Name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.namePos] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth, before shuffling about until [npc.her] [npc.balls+] are roughly grinding against [npc2.namePos] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are roughly grinding against [npc2.namePos] [npc2.lips+].",

							"[npc.Name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.namePos] [npc2.lips+] are roughly grinding against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] until [npc.she]'s roughly forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth, before shuffling about until [npc.her] [npc.balls+] are pressing down against [npc2.namePos] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.namePos] [npc2.lips+].",

							"[npc.Name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.namePos] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth, before shuffling about until [npc.her] [npc.balls+] are pressing down against [npc2.namePos] [npc2.lips+].",

							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.namePos] [npc2.lips+].",

							"[npc.Name] slides [npc.her] [npc.cock+] out of [npc2.namePos] mouth, before repositioning [npc.herself] so that [npc2.namePos] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",

							"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."));
					break;
				default:
					break;
			
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Slowly sliding [npc2.namePos] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to gently lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] gently [npc2.verb(start)] to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc2.namePos] [npc2.tongue+] out, [npc2.name] greedily [npc2.verb(start)] to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] eagerly [npc2.verb(start)] to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Sliding [npc2.namePos] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to roughly lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] roughly [npc2.verb(start)] kissing and licking [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc2.namePos] [npc2.tongue+] out, [npc2.name] greedily [npc2.verb(start)] to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] eagerly [npc2.verb(start)] to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Darting [npc2.namePos] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",

							" [npc2.Name] [npc2.verb(start)] to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull away, letting out [npc2.a_sob+] as [npc.her] [npc.balls+] [npc.verb(continue)] grinding against [npc2.namePos] [npc2.lips+].",

							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], trying desperately to pull away from [npc.her] [npc.balls+] as [npc2.name] struggle against [npc.herHim]."));
					break;
				default:
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_SUCK_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isInternalTesticles() && Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick balls";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss [npc.namePos] [npc.balls] for a while.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.namePos] [npc.cock+] slip completely out of [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down and [npc2.verb(start)] to gently lick and suck on [npc.her] [npc.balls+].",

							"You [npc2.verb(let)] [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, before moving [npc2.namePos] [npc2.lips+] down to start gently licking and kissing [npc.her] [npc.balls+].",

							"Sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down to start gently kissing and licking [npc.her] [npc.balls+].",

							"First sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down, before starting to gently kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.namePos] [npc.cock+] slip completely out of [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down and [npc2.verb(start)] to eagerly lick and suck on [npc.her] [npc.balls+].",

							"You [npc2.verb(let)] [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, before moving [npc2.namePos] [npc2.lips+] down to start desperately licking and kissing [npc.her] [npc.balls+].",

							"Sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down to start eagerly kissing and licking [npc.her] [npc.balls+].",

							"First sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down, before starting to eagerly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.namePos] [npc.cock+] slip completely out of [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down and [npc2.verb(start)] to roughly lick and suck on [npc.her] [npc.balls+].",

							"You [npc2.verb(let)] [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, before moving [npc2.namePos] [npc2.lips+] down to start roughly licking and kissing [npc.her] [npc.balls+].",

							"Sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down to start roughly kissing and licking [npc.her] [npc.balls+].",

							"First sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down, before starting to roughly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.namePos] [npc.cock+] slip completely out of [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down and [npc2.verb(start)] to eagerly lick and suck on [npc.her] [npc.balls+].",

							"You [npc2.verb(let)] [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, before moving [npc2.namePos] [npc2.lips+] down to start desperately licking and kissing [npc.her] [npc.balls+].",

							"Sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down to start eagerly kissing and licking [npc.her] [npc.balls+].",

							"First sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down, before starting to eagerly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.namePos] [npc.cock+] slip completely out of [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down and [npc2.verb(start)] to lick and suck on [npc.her] [npc.balls+].",

							"You [npc2.verb(let)] [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, before moving [npc2.namePos] [npc2.lips+] down to start licking and kissing [npc.her] [npc.balls+].",

							"Sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down to start kissing and licking [npc.her] [npc.balls+].",

							"First sliding [npc.namePos] [npc.cock+] out from [npc2.namePos] mouth, [npc2.name] move [npc2.namePos] head down, before starting to kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_LICK_HEAD = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick head";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss the [npc.cockHead+] of [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String barbedSpecial = "", flaredSpecial = "";
			
			if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
				barbedSpecial = "You slide [npc2.namePos] head back, letting out a muffled [npc2.moan] as [npc2.name] [npc2.verb(feel)] the barbs lining [npc.namePos] [npc.cock] rake their way up [npc2.namePos] throat."
									+ " Leaving just the [npc.cockHead+] pushed past [npc2.namePos] [npc2.lips+], [npc2.name] then [npc2.verb(start)] to passionately kiss and suck the tip of [npc.her] [npc.cock+].";
			}
			if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
				flaredSpecial = "Sliding [npc2.namePos] head back, [npc2.name] allow [npc.namePos] [npc.cock+] to slip almost completely out of [npc2.namePos] mouth,"
									+ " leaving just the wide, flared head pushed past [npc2.namePos] [npc2.lips+], which [npc2.name] [npc2.verb(start)] to passionately kiss and lick.";
			}
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
							barbedSpecial, flaredSpecial,
							
						"With [npc2.a_moan+], [npc2.name] [npc2.verb(pull)] [npc2.namePos] head back, wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock+], before starting to lick and kiss it.",

					
						"You [npc2.verb(pull)] [npc2.namePos] head back, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] concentrating on sucking and kissing the [npc.cockHead+] of [npc.namePos] [npc.cock+].",

	
						"Pulling [npc2.namePos] head back a little, [npc2.name] [npc2.verb(let)] most of [npc.namePos] [npc.cock+] slide out of [npc2.namePos] mouth, and,"
							+ " focusing on using [npc2.namePos] [npc2.tongue+], [npc2.name] [npc2.verb(start)] licking and kissing the [npc.cockHead+] that's left poking past [npc2.namePos] [npc2.lips+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING && Sex.getCharacterPerformingAction().hasVagina() && Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)
					&& Sex.isOrificeFree(Sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isFeminine()) {
				return "Futa fun";
			} else {
				return "Herm fun";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pleasure both [npc.namePos] [npc.cock+] and [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.namePos] other sexual organ, [npc2.name] give the [npc.cockHead] of [npc.her] [npc.cock+] a gentle suck, before pulling back and starting to slowly lick and kiss [npc.her] [npc.pussy+].",

							"Deciding to give [npc.namePos] [npc.pussy] some attention, [npc2.name] deliver a long, slow lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and gently sliding [npc2.namePos] [npc2.tongue+] into [npc.her] [npc.pussy+].",

							"Delivering a gentle kiss to the [npc.cockHead] of [npc.namePos] [npc.cock+], [npc2.name] move down, bringing [npc2.namePos] [npc2.lips+] to [npc.her] [npc.pussy+] before slowly pushing [npc2.namePos] [npc2.tongue+] between [npc.her] folds."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.namePos] other sexual organ, [npc2.name] give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss [npc.her] [npc.pussy+].",

							"Deciding to give [npc.namePos] [npc.pussy] some attention, [npc2.name] deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and eagerly darting [npc2.namePos] [npc2.tongue+] into [npc.her] [npc.pussy+].",

							"Delivering a wet kiss to the [npc.cockHead] of [npc.namePos] [npc.cock+], [npc2.name] move down, bringing [npc2.namePos] [npc2.lips+] to [npc.her] [npc.pussy+] before eagerly darting [npc2.namePos] [npc2.tongue+] between [npc.her] folds."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.namePos] other sexual organ, [npc2.name] give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to roughly lick and kiss [npc.her] [npc.pussy+].",

							"Deciding to give [npc.namePos] [npc.pussy] some attention, [npc2.name] deliver a long, roughly lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and dominantly thrusting [npc2.namePos] [npc2.tongue+] into [npc.her] [npc.pussy+].",

							"Delivering a rough kiss to the [npc.cockHead] of [npc.namePos] [npc.cock+], [npc2.name] move down, bringing [npc2.namePos] [npc2.lips+] to [npc.her] [npc.pussy+] before dominantly thrusting [npc2.namePos] [npc2.tongue+] between [npc.her] folds."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.namePos] other sexual organ, [npc2.name] give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss [npc.her] [npc.pussy+].",

							"Deciding to give [npc.namePos] [npc.pussy] some attention, [npc2.name] deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and eagerly darting [npc2.namePos] [npc2.tongue+] into [npc.her] [npc.pussy+].",

							"Delivering a wet kiss to the [npc.cockHead] of [npc.namePos] [npc.cock+], [npc2.name] move down, bringing [npc2.namePos] [npc2.lips+] to [npc.her] [npc.pussy+] before eagerly darting [npc2.namePos] [npc2.tongue+] between [npc.her] folds."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.namePos] other sexual organ, [npc2.name] give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to lick and kiss [npc.her] [npc.pussy+].",

							"Deciding to give [npc.namePos] [npc.pussy] some attention, [npc2.name] deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and darting [npc2.namePos] [npc2.tongue+] into [npc.her] [npc.pussy+].",

							"Delivering a wet kiss to the [npc.cockHead] of [npc.namePos] [npc.cock+], [npc2.name] move down, bringing [npc2.namePos] [npc2.lips+] to [npc.her] [npc.pussy+] before darting [npc2.namePos] [npc2.tongue+] between [npc.her] folds."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out a soft [npc.moan], gently pushing [npc.her] [npc.pussy+] against [npc2.namePos] [npc2.lips+] before [npc2.name] decide to shift [npc.namePos] [npc.verb(focus)] back to [npc.her] [npc.cock+] once again.",

							" Gently thrusting [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out a soft [npc.moan], before [npc2.name] decide to move back to focusing on [npc.her] [npc.cock+].",

							" Softly [npc.moaning], [npc.she] gently bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] for a few moments, before [npc2.name] decide to shift [npc2.namePos] oral attention back to [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_moan+], eagerly pushing [npc.her] [npc.pussy+] against [npc2.namePos] [npc2.lips+] before [npc2.name] decide to shift [npc.namePos] [npc.verb(focus)] back to [npc.her] [npc.cock+] once again.",

							" Eagerly thrusting [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out [npc.a_moan+], before [npc2.name] decide to move back to focusing on [npc.her] [npc.cock+].",

							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] for a few moments, before [npc2.name] decide to shift [npc2.namePos] oral attention back to [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_moan+], roughly grinding [npc.her] [npc.pussy+] against [npc2.namePos] [npc2.lips+] before [npc2.name] decide to shift [npc.namePos] [npc.verb(focus)] back to [npc.her] [npc.cock+] once again.",

							" Roughly grinding [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out [npc.a_moan+], before [npc2.name] decide to move back to focusing on [npc.her] [npc.cock+].",

							" [npc.Moaning+], [npc.she] roughly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] for a few moments, before [npc2.name] decide to shift [npc2.namePos] oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_moan+], eagerly pushing [npc.her] [npc.pussy+] against [npc2.namePos] [npc2.lips+] before [npc2.name] decide to shift [npc.namePos] [npc.verb(focus)] back to [npc.her] [npc.cock+] once again.",

							" Eagerly thrusting [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out [npc.a_moan+], before [npc2.name] decide to move back to focusing on [npc.her] [npc.cock+].",

							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] for a few moments, before [npc2.name] decide to shift [npc2.namePos] oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_moan+], pushing [npc.her] [npc.pussy+] against [npc2.namePos] [npc2.lips+] before [npc2.name] decide to shift [npc.namePos] [npc.verb(focus)] back to [npc.her] [npc.cock+] once again.",

							" thrusting [npc.her] [npc.pussy+] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out [npc.a_moan+], before [npc2.name] decide to move back to focusing on [npc.her] [npc.cock+].",

							" [npc.Moaning+], [npc.she] bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] for a few moments, before [npc2.name] decide to shift [npc2.namePos] oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Sobbing] and squirming in discomfort, [npc.she] desperately tries to pull away from [npc2.herHim], begging to be left alone as [npc2.name] shift [npc2.namePos] attention back down to [npc.her] [npc.cock+] once again.",

							" With [npc.a_sob+], [npc.she] tries to push [npc2.name] away, squirming in discomfort as [npc2.name] move back to focusing [npc2.namePos] attention on [npc.her] [npc.cock+].",

							" With tears streaming down [npc.her] [npc.face], [npc.she] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] move back to focusing on [npc.her] [npc.cock+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Get blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc.namePos] [npc.cock+] into [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " before gently pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before slowly bucking [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] and gently sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " before eagerly pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before eagerly bucking [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] and sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] and forcing [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly lowers [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before gently pushing the [npc.cockHead+] past [npc2.namePos] [npc2.lips+] and collapsing down onto [npc2.namePos] [npc2.face+].",

								"Gently lowering [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] slowly allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.namePos] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly drops [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before eagerly pushing the [npc.cockHead+] past [npc2.namePos] [npc2.lips+] and collapsing down onto [npc2.namePos] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly forces the [npc.cockHead+] past [npc2.namePos] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly grinds [npc.her] [npc.cock+] down against [npc2.namePos] mouth,"
										+ " before forcefully pushing the [npc.cockHead+] past [npc2.namePos] [npc2.lips+] and collapsing down onto [npc2.namePos] [npc2.face+].",

								"Roughly grinding [npc.her] [npc.cock+] down against [npc2.namePos] mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.namePos] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+], [npc.name] gently [npc.verb(push)] [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+], [npc.name] slowly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], gently sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+], [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+], [npc.name] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+], [npc.name] roughly slams [npc.her] [npc.hips] forwards and forces [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Grinding the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], forcing [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+], [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+], [npc.name] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+], [npc.name] bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.namePos] mouth."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan], slowly sliding [npc2.namePos] head forwards as [npc2.name] [npc2.verb(start)] gently sucking [npc.her] [npc.cock+].",

							" With a soft, and very muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] gently sliding [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.namePos] head forwards as [npc2.name] [npc2.verb(start)] happily sucking [npc.her] [npc.cock+].",

							" With an eager, and very muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] head forwards as [npc2.name] [npc2.verb(start)] forcing [npc.her] [npc.cock+] deep down [npc2.namePos] throat.",

							" With an eager, and very muffled, [npc2.moan], [npc2.name] forcefully [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a rough blowjob."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.namePos] head forwards as [npc2.name] [npc2.verb(start)] happily sucking [npc.her] [npc.cock+].",

							" With an eager, and very muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], sliding [npc2.namePos] head forwards as [npc2.name] [npc2.verb(start)] sucking [npc.her] [npc.cock+].",

							" With a muffled, [npc2.moan], [npc2.name] [npc2.verb(start)] sliding [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], gargling and choking on [npc.her] [npc.cock+] as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] head away from [npc.her] groin.",

							" With a muffled [npc2.sob], [npc2.name] frantically [npc2.verb(try)] to pull away from [npc.her] [npc.cock+], gargling and choking as [npc2.name] squirm and struggle against [npc.herHim]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Hold still and [npc2.verb(let)] [npc2.name] suck [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by eagerly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by eagerly wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by happily sliding [npc2.namePos] head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.sob] as [npc2.name] respond by desperately trying to pull [npc2.namePos] head away from [npc.her] groin.",

							"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by frantically trying to pull [npc2.namePos] head away from [npc.her] [npc.cock+].",

							"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by desperately trying to pull away from [npc.her] [npc.cock+],"
									+ " letting out a muffled [npc2.sob] as tears well up in [npc2.namePos] [npc2.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] feels [npc2.name] respond by sliding [npc2.namePos] head up and down,"
									+ " obediently continuing to give [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc.verb(continue)] blowjob";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] [npc.namePos] [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving [npc2.name] a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by eagerly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by eagerly wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Frantically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by happily sliding [npc2.namePos] head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.sob] as [npc2.name] respond by desperately trying to pull [npc2.namePos] head away from [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by frantically trying to pull [npc2.namePos] head away from [npc.her] [npc.cock+].",

							"Frantically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by desperately trying to pull away from [npc.her] [npc.cock+],"
									+ " letting out a muffled [npc2.sob] as tears well up in [npc2.namePos] [npc2.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Frantically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by sliding [npc2.namePos] head up and down,"
									+ " obediently continuing to give [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly [npc2.verb(thrust)] [npc.namePos] [npc.cock+] down [npc2.namePos] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					knottedSpecial = "With a sudden, violent [npc2.verb(thrust)] forwards, [npc.name] buries [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " Holding [npc2.namePos] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.namePos] [npc2.face],"
									+ " and [npc2.name] [npc2.verb(feel)] tears streaming from [npc2.namePos] [npc2.eyes] as [npc.she] slams [npc.her] knot repeatedly against [npc2.namePos] [npc2.lips+].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
					barbedSpecial = "With a sudden, violent [npc2.verb(thrust)] forwards, [npc.name] buries [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " Holding [npc2.namePos] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.namePos] [npc2.face],"
									+ " and [npc2.name] [npc2.verb(feel)] tears streaming from [npc2.namePos] [npc2.eyes] as [npc2.name] [npc2.verb(feel)] the barbs lining [npc.her] shaft repeatedly raking up the sides of [npc2.namePos] throat.";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					flaredSpecial = "With a sudden, violent [npc2.verb(thrust)] forwards, [npc.name] buries [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " Holding [npc2.namePos] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.namePos] [npc2.face],"
									+ " and [npc2.name] [npc2.verb(feel)] tears streaming from [npc2.namePos] [npc2.eyes] as [npc.her] wide, flared head forces its way up and down [npc2.namePos] throat.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"[npc.Name] grabs the sides of [npc2.namePos] head, and before [npc2.name] know what's happening, [npc.she]'s roughly slamming [npc.her] [npc.cock+] in and out of [npc2.namePos] facial fuck-hole.",

						
								"Letting out [npc.a_moan+], [npc.name] slams [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
									+ " As [npc2.name] blink back tears, [npc.she] [npc.verb(start)] rapidly bucking [npc.her] [npc.hips] back and forth, holding [npc2.namePos] head in place with both hands as [npc.she] ruthlessly fucks [npc2.namePos] [npc2.face].",

								
								"Grabbing the sides of [npc2.namePos] head, [npc.name] roughly [npc2.verb(pull)]s [npc2.name] into [npc.her] groin, sinking [npc.her] [npc.cock+] deep down [npc2.namePos] throat before starting to ruthlessly fuck [npc2.namePos] [npc2.face].",

										
								"With a forceful [npc2.verb(thrust)], [npc.name] hilts [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of saliva "+(Sex.getWetAreas(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).contains(LubricationType.PARTNER_PRECUM)?"and precum ":"")
									+"drools from the corners of [npc2.namePos] mouth, [npc.she] bucks back, letting [npc2.name] gasp for air for a brief moment before starting to aggressively fuck [npc2.namePos] [npc2.face]."));
			
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					knottedSpecial = "Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
										+ " Grinding [npc.her] fat knot against [npc2.namePos] [npc2.lips+] for moment, [npc.she] then proceeds to start roughly fucking [npc2.namePos] face,"
										+ " and [npc2.name] find [npc2.herself] [npc2.moaning] in desperation as [npc2.name] struggle to breathe,"
										+ " [npc2.namePos] pitiful cries being accompanied by the wet slapping sounds that [npc.her] fat knot makes at it repeatedly slams against [npc2.namePos] [npc2.lips].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.BARBED)) {
					barbedSpecial = "Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
										+ " Grinding the base against [npc2.namePos] [npc2.lips+] for moment, [npc.she] then proceeds to start roughly fucking [npc2.namePos] face,"
										+ " and [npc2.name] find [npc2.herself] [npc2.moaning] in desperation as [npc2.name] struggle to breathe,"
										+ " squirming about beneath [npc.herHim] as [npc2.name] [npc2.verb(feel)] [npc2.namePos] throat being stretched out by the wide, flared head of [npc.her] [npc.cock+].";
				}
				if(Sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.FLARED)) {
					flaredSpecial = "Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc2.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
										+ " Grinding the base against [npc2.namePos] [npc2.lips+] for moment, [npc.she] then proceeds to start roughly fucking [npc2.namePos] face,"
										+ " and [npc2.name] find [npc2.herself] [npc2.moaning] in desperation as [npc2.name] struggle to breathe,"
										+ " squirming about beneath [npc.herHim] as [npc2.name] [npc2.verb(feel)] [npc2.namePos] throat being raked by the series of barbs that line the sides of [npc.her] [npc.cock+].";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"[npc.Name] spreads [npc.her] knees out on either side of [npc2.namePos] head, and before [npc2.name] realise what's about to happen,"
										+ " [npc.she] roughly [npc.verb(start)] slamming [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks [npc2.namePos] face.",

								
								"Letting out [npc.a_moan+], [npc.name] slams [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
									+ " As [npc2.name] blink back tears, [npc.she] [npc.verb(start)] rapidly thrusting [npc.her] [npc.hips] up and down, letting out more [npc.moans+] as [npc.she] ruthlessly fucks [npc2.namePos] face.",

								
								"Dropping down onto [npc2.namePos] face, [npc.name] roughly slams [npc.her] [npc.cock+] deep down [npc2.namePos] throat,"
										+ " letting out [npc.a_moan+] before starting to violently [npc2.verb(thrust)] [npc.her] [npc.hips] up and down as [npc.she] ruthlessly fucks [npc2.namePos] face.",

										
								"With a forceful [npc2.verb(thrust)], [npc.name] hilts [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of "+(Sex.getWetAreas(Sex.getCharacterTargetedForSexAction(this)).get(SexAreaOrifice.MOUTH).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
									+"saliva drools from the corners of [npc2.namePos] mouth, [npc.she] lifts [npc.herself] up, letting [npc2.name] gasp for air for a brief moment before sinking down once more and starting to aggressively fuck [npc2.namePos] face."));
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by obediently relaxing [npc2.namePos] throat,"
										+ " allowing [npc.herHim] to brutally fuck [npc2.namePos] [npc2.face].",

								"[npc.Name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by obediently relaxing [npc2.namePos] throat,"
										+ " signalling [npc2.namePos] willingness for [npc.herHim] to continue brutally fucking [npc2.namePos] [npc2.face].",

								"Violently slamming [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by happily relaxing [npc2.namePos] throat,"
										+ " all too eager for [npc.her] [npc.cock+] to brutally fuck [npc2.namePos] [npc2.face]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.sob] as [npc2.name] respond by frantically trying to pull away,"
										+ " tears streaming from [npc2.namePos] [npc2.eyes] as [npc.she] brutally fucks [npc2.namePos] [npc2.face].",

								"[npc.Name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by frantically trying to pull away from [npc.her] groin,"
										+ " [npc2.namePos] muffled [npc2.sobs] only serving to encourage [npc.herHim] as [npc.she] brutally fucks [npc2.namePos] [npc2.face].",

								"Violently slamming [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by frantically trying to pull away from [npc.herHim],"
										+ " [npc2.sobbing] and squirming in distress as [npc.she] brutally fucks [npc2.namePos] [npc2.face]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by obediently holding still,"
										+ " allowing [npc.herHim] to brutally fuck [npc2.namePos] [npc2.face].",

								"[npc.Name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by obediently staying in position,"
										+ " allowing [npc.herHim] to continue brutally fucking [npc2.namePos] [npc2.face].",

								"Violently slamming [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by holding [npc2.namePos] head still,"
										+ " happy for [npc.her] [npc.cock+] to brutally fuck [npc2.namePos] [npc2.face]."));
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] out of [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.name] gently, but firmly, holds [npc.herHim] in place,"
								+ " slowly sliding [npc2.namePos] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.name] [npc.verb(continue)] giving [npc.herHim] an unwanted blowjob.",

						"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " [npc2.namePos] grip proves to be too strong, and [npc2.name] gently, but firmly, [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.namePos] grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] a slow, gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.name] roughly hold [npc.herHim] in place,"
									+ " growling in a threatening manner as [npc2.name] forcefully slide [npc2.namePos] [npc2.lips+] up and down the length of [npc.her] [npc.cock+].",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
									+ " [npc2.namePos] grip proves to be far too strong, and [npc2.name] [npc2.verb(let)] out a threatening growl as [npc2.name] [npc.verb(continue)] to suck [npc.her] [npc.cock+].",

							"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.namePos] grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] a rough, forceful blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.name] firmly hold [npc.herHim] in place,"
									+ " eagerly sliding [npc2.namePos] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.name] [npc.verb(continue)] giving [npc.herHim] an unwanted blowjob.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
									+ " [npc2.namePos] grip proves to be too strong, and [npc2.name] eagerly [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",

							"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.namePos] grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc.verb(continue)] giving [npc.herHim] an eager blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc.verb(continue)] blowjob";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] [npc.namePos] [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving [npc2.name] a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by slowly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by gently wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by slowly sliding [npc2.namePos] head up and down,"
									+ " continuing to give [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " and [npc2.name] find [npc2.herself] letting out a threatening growl as [npc2.name] remind [npc.herHim] who's in charge, before roughly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], and, wanting to remind [npc.herHim] who's in charge, [npc2.name] [npc2.verb(let)] out a threatening growl before roughly clamping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"As [npc.name] bucks [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(let)] out a threatening growl to remind [npc.herHim] who's in charge,"
									+ " before sliding [npc2.namePos] head up and down, continuing to give [npc.herHim] a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by eagerly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by eagerly wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by happily sliding [npc2.namePos] head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly [npc2.verb(thrust)] [npc.namePos] [npc.hips] into [npc2.namePos] face, forcing [npc.namePos] [npc.cock+] down [npc2.her] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by slowly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by gently wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Frantically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by slowly sliding [npc2.namePos] head up and down,"
									+ " continuing to give [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " and [npc2.name] find [npc2.herself] letting out a threatening growl as [npc2.name] remind [npc.herHim] who's in charge, before roughly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], and, wanting to remind [npc.herHim] who's in charge,"
									+ " [npc2.Name] [npc2.verb(let)] out a threatening growl before roughly clamping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"As [npc.name] frantically bucks [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(let)] out a threatening growl to remind [npc.herHim] who's in charge,"
									+ " before sliding [npc2.namePos] head up and down, continuing to give [npc.herHim] a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc2.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+], and [npc2.name] find [npc2.herself] letting out a muffled [npc2.moan] as [npc2.name] respond by eagerly bobbing [npc2.namePos] head up and down into [npc.her] groin.",

							"[npc.Name] desperately [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by eagerly wrapping [npc2.namePos] [npc2.lips+] around [npc.her] [npc.cock+].",

							"Frantically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] feels [npc2.name] respond by happily sliding [npc2.namePos] head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] allows [npc.her] [npc.cock+] to slide up and out of [npc2.namePos] mouth,"
							+ " and [npc2.name] look up to see a slimy strand of saliva linking [npc2.namePos] lips to the [npc.cockHead+] of [npc.her] [npc.cock] for a brief moment, before breaking to fall down over [npc2.namePos] face.");
				
			} else {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] down [npc2.namePos] throat one last time, [npc.name] then [npc2.verb(pull)]s [npc.her] [npc.hips] back, grinning as [npc2.name] splutter and gasp for air.",

								"Slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.name] forces [npc.her] [npc.cock+] deep down [npc2.namePos] throat, before pulling completely back and out of [npc2.namePos] mouth."));
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
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling against [npc.herHim] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to let [npc2.name] go.",

							" [npc2.Name] [npc2.verb(feel)] tears streaming down [npc2.namePos] cheeks as [npc2.name] [npc2.verb(try)] to struggle out of [npc.her] grip, letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.name] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.namePos] desire to continue sucking [npc.her] [npc.cock+].",

							" [npc2.Name] [npc2.moanVerb] as [npc.she] withdraws from [npc2.namePos] mouth, betraying [npc2.namePos] lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Player can only [npc2.verb(start)] in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.namePos] [npc.cock+] into [npc2.namePos] mouth and [npc2.verb(start)] sucking [npc.herHim] off.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos] hot breath falls down onto [npc.namePos] groin as [npc2.name] slowly lower [npc2.namePos] head between [npc.her] [npc.legs],"
										+ " passionately kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before taking [npc.herHim] into [npc2.namePos] mouth.",

								"Gently lowering [npc2.namePos] head down between [npc.namePos] [npc.legs], [npc2.name] deliver a long, slow lick up the length of [npc.her] [npc.cock+], before taking the [npc.cockHead+] into [npc2.namePos] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos] hot breath falls down onto [npc.namePos] groin as [npc2.name] eagerly drop [npc2.namePos] head between [npc.her] [npc.legs],"
										+ " passionately kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before greedily taking [npc.herHim] into [npc2.namePos] mouth.",

								"Eagerly dropping [npc2.namePos] head down between [npc.namePos] [npc.legs], [npc2.name] deliver a long, wet lick up the length of [npc.her] [npc.cock+], before greedily taking the [npc.cockHead+] into [npc2.namePos] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos] hot breath falls down onto [npc.namePos] groin as [npc2.name] quickly drop [npc2.namePos] head between [npc.her] [npc.legs],"
										+ " roughly kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before forcefully taking [npc.herHim] into [npc2.namePos] mouth.",

								"Dropping [npc2.namePos] head down between [npc.namePos] [npc.legs], [npc2.name] deliver a rough, wet lick up the length of [npc.her] [npc.cock+], before forcefully taking the [npc.cockHead+] into [npc2.namePos] mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc2.namePos] [npc2.lips+] up to the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] slowly take [npc.herHim] into [npc2.namePos] mouth, letting out a muffled [npc2.moan] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob.",

								"Wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a gentle blowjob."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc2.namePos] [npc2.lips+] up to the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] eagerly take [npc.herHim] into [npc2.namePos] mouth, letting out a muffled [npc2.moan] as [npc2.name] happily [npc2.verb(start)] giving [npc.herHim] a blowjob.",

								"Wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] eagerly [npc2.verb(start)] giving [npc.herHim] a gentle blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc2.namePos] [npc2.lips+] up to the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] forcefully take [npc.herHim] into [npc2.namePos] mouth, letting out a muffled [npc2.moan] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a rough blowjob.",

								"Forcefully wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a rough blowjob."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc2.namePos] [npc2.lips+] up to the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] eagerly take [npc.herHim] into [npc2.namePos] mouth, letting out a muffled [npc2.moan] as [npc2.name] happily [npc2.verb(start)] giving [npc.herHim] a blowjob.",

								"Wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] eagerly [npc2.verb(start)] giving [npc.herHim] a gentle blowjob."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc2.namePos] [npc2.lips+] up to the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] take [npc.herHim] into [npc2.namePos] mouth, letting out a muffled [npc2.moan] as [npc2.name] [npc2.verb(start)] giving [npc.herHim] a blowjob.",

								"Wrapping [npc2.namePos] [npc2.lips+] around the [npc.cockHead+] of [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] eagerly [npc2.verb(start)] giving [npc.herHim] a blowjob."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] gently bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], softly [npc.moaning] as [npc2.name] suck [npc.her] [npc.cock+].",

							" With a gentle buck of [npc.her] [npc.hips], [npc.she] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc2.verb(enjoy)]s the feeling of [npc2.namePos] [npc2.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] greedily bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.moaning+] as [npc2.name] suck [npc.her] [npc.cock+].",

							" With an energetic buck of [npc.her] [npc.hips], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc2.verb(enjoy)]s the feeling of [npc2.namePos] [npc2.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] roughly slams [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.moaning+] as [npc2.name] suck [npc.her] [npc.cock+].",

							" With an rough [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc2.verb(enjoy)]s the feeling of [npc2.namePos] [npc2.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] greedily bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.moaning+] as [npc2.name] suck [npc.her] [npc.cock+].",

							" With an energetic buck of [npc.her] [npc.hips], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc2.verb(enjoy)]s the feeling of [npc2.namePos] [npc2.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.moaning+] as [npc2.name] suck [npc.her] [npc.cock+].",

							" Bucking [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc2.verb(enjoy)]s the feeling of [npc2.namePos] [npc2.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_sob+], trying, and failing, to pull [npc.her] [npc.cock] out of [npc2.namePos] mouth as [npc.she] struggles against [npc2.namePos] unwanted oral attention.",

							" With tears welling up in [npc.her] [npc.eyes], [npc.name] struggles against [npc2.namePos] unwanted oral attention, [npc.sobbing] and squirming as [npc.she] [npc2.verb(beg)]s for [npc2.name] to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Gently suck [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc.she] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face].",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] slowly bob [npc2.namePos] head up and down, letting out a muffled [npc2.moan] as [npc.she] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] in response.",

							"Slowly bobbing [npc2.namePos] head up and down, [npc2.name] gently suck [npc.namePos] [npc.cock+], causing [npc.herHim] to frantically buck [npc.her] [npc.hips] into [npc2.namePos] [npc2.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobsVerb] in distress.",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] slowly bob [npc2.namePos] head up and down,"
									+ " holding [npc.herHim] in place as [npc.she] desperately [npc.sobsVerb] and struggles against [npc2.namePos] unwanted oral attention.",

							"Slowly bobbing [npc2.namePos] head up and down, [npc2.name] gently suck [npc.namePos] [npc.cock+], holding [npc.herHim] in place as [npc.she] [npc.sobs] and [npc2.verb(beg)]s for [npc2.name] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] and drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] slowly bob [npc2.namePos] head up and down, drawing [npc.a_moan+] from [npc.her] mouth.",

							"Slowly bobbing [npc2.namePos] head up and down, [npc2.name] gently suck [npc.namePos] [npc.cock+], causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Eager cock sucking";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc.she] eagerly bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face].",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly bob [npc2.namePos] head up and down, letting out a muffled [npc2.moan] as [npc.she] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face] in response.",

							"Rapidly bobbing [npc2.namePos] head up and down, [npc2.name] eagerly suck [npc.namePos] [npc.cock+], causing [npc.herHim] to frantically buck [npc.her] [npc.hips] into [npc2.namePos] [npc2.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobsVerb] in distress.",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly bob [npc2.namePos] head up and down,"
									+ " holding [npc.herHim] in place as [npc.she] desperately [npc.sobsVerb] and struggles against [npc2.namePos] unwanted oral attention.",

							"Rapidly bobbing [npc2.namePos] head up and down, [npc2.name] eagerly suck [npc.namePos] [npc.cock+], holding [npc.herHim] in place as [npc.she] [npc.sobs] and [npc2.verb(beg)]s for [npc2.name] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily [npc2.verb(push)] [npc2.namePos] head forwards, wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+] and drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] rapidly bob [npc2.namePos] head up and down, drawing [npc.a_moan+] from [npc.her] mouth.",

							"Rapidly bobbing [npc2.namePos] head up and down, [npc2.name] eagerly suck [npc.namePos] [npc.cock+], causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
					
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.namePos] [npc.cock+] as deep down [npc2.namePos] throat as [npc2.name] can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam [npc2.namePos] head forwards, taking [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat as [npc.she] desperately bucks [npc.her] [npc.hips] into [npc2.namePos] [npc2.face].",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+],"
									+ " [npc2.Name] slam [npc2.namePos] head forwards and deep throat [npc.herHim], letting out a muffled [npc2.moan] as [npc.she] eagerly grinds [npc.her] [npc.hips] into [npc2.namePos] [npc2.face].",

							"Roughly slamming [npc2.namePos] head forwards, [npc2.name] take [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly bucks [npc.her] [npc.hips] forwards in response."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam [npc2.namePos] head forwards, taking [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobs] in distress.",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+],"
									+ " [npc2.Name] slam [npc2.namePos] head forwards and deep throat [npc.herHim], letting out a muffled [npc2.moan] [npc.she] desperately [npc.sobsVerb] and struggles against [npc2.namePos] unwanted oral attention.",

							"Roughly slamming [npc2.namePos] head forwards, [npc2.name] take [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat, holding [npc.herHim] in place as [npc.she] [npc.sobsVerb] and [npc2.verb(beg)]s for [npc2.name] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam [npc2.namePos] head forwards, taking [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat and drawing [npc.a_moan+] from [npc.her] mouth.",

							"Wrapping [npc2.namePos] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.name] slam [npc2.namePos] head forwards and deep throat [npc.herHim], drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",

							"Roughly slamming [npc2.namePos] head forwards, [npc2.name] take [npc.namePos] [npc.cock+] deep down [npc2.namePos] throat, causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
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
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc.namePos] [npc.cock+] out of [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You [npc2.verb(let)] out a muffled [npc2.sob], squirming and struggling in distress as [npc.name] holds [npc2.name] in place, slowly sliding [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

						"With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to push [npc.name] away, [npc2.namePos] protestations coming out in gargled bursts as [npc.she] [npc.verb(continue)] gently pushing [npc.her] [npc.cock+] down [npc2.namePos] throat.",

						"You [npc2.verb(feel)] tears running down [npc2.namePos] cheeks as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, [npc2.sobbing] in distress as [npc.she] [npc.verb(continue)] gently sliding [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out a muffled [npc2.sob], squirming and struggling in distress as [npc.name] forcefully holds [npc2.name] in place, roughly pumping [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to push [npc.name] away, [npc2.namePos] protestations coming out in gargled bursts as [npc.she] [npc.verb(continue)] roughly slamming [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(feel)] tears running down [npc2.namePos] cheeks as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, [npc2.sobbing] in distress as [npc.she] [npc.verb(continue)] roughly thrusting [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out a muffled [npc2.sob], squirming and struggling in distress as [npc.name] holds [npc2.name] in place, eagerly sliding [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to push [npc.name] away, [npc2.namePos] protestations coming out in gargled bursts as [npc.she] [npc.verb(continue)] eagerly pushing [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(feel)] tears running down [npc2.namePos] cheeks as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, [npc2.sobbing] in distress as [npc.she] [npc.verb(continue)] eagerly sliding [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Suck cock";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] sucking [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You [npc2.verb(let)] out [npc2.a_moan+], obediently holding still as [npc.name] slowly slides [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

						"With [npc2.a_moan+], [npc2.name] happily [npc.verb(continue)] giving [npc.name] a blowjob, obediently holding still as [npc.she] [npc.verb(continue)] gently pushing [npc.her] [npc.cock+] down [npc2.namePos] throat.",

						"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] obediently hold still, allowing [npc.name] to continue gently sliding [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.a_moan+], obediently holding still as [npc.name] roughly pumps [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_moan+], [npc2.name] happily [npc.verb(continue)] giving [npc.name] a blowjob, obediently holding still as [npc.she] [npc.verb(continue)] roughly slamming [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] obediently hold still, allowing [npc.name] to continue roughly thrusting [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.a_moan+], obediently holding still as [npc.name] eagerly slides [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_moan+], [npc2.name] happily [npc.verb(continue)] giving [npc.name] a blowjob, obediently holding still as [npc.she] [npc.verb(continue)] eagerly thrusting [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] obediently hold still, allowing [npc.name] to continue eagerly pumping [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.namePos] [npc.cock+] as deep down [npc2.namePos] throat as [npc2.name] can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You [npc2.verb(let)] out [npc2.a_moan+], eagerly bobbing [npc2.namePos] head up and down as [npc.name] slowly slides [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

						"With [npc2.a_moan+], [npc2.name] enthusiastically bob [npc2.namePos] head up and down, loving every moment of this as [npc.name] [npc.verb(continue)] gently pushing [npc.her] [npc.cock+] down [npc2.namePos] throat.",

						"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] energetically bob [npc2.namePos] head up and down, helping [npc.name] to continue gently sliding [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.a_moan+], eagerly bobbing [npc2.namePos] head up and down as [npc.name] roughly pumps [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_moan+], [npc2.name] enthusiastically bob [npc2.namePos] head up and down, loving every moment of this as [npc.name] [npc.verb(continue)] roughly slamming [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] energetically bob [npc2.namePos] head up and down, helping [npc.name] to continue roughly thrusting [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(let)] out [npc2.a_moan+], energetically bobbing [npc2.namePos] head up and down as [npc.name] eagerly slides [npc.her] [npc.cock+] back and forth past [npc2.namePos] [npc2.lips+].",

							"With [npc2.a_moan+], [npc2.name] enthusiastically bob [npc2.namePos] head up and down, loving every moment of this as [npc.name] [npc.verb(continue)] eagerly thrusting [npc.her] [npc.cock+] down [npc2.namePos] throat.",

							"You [npc2.verb(let)] out a muffled [npc2.moan] as [npc2.name] energetically bob [npc2.namePos] head up and down, helping [npc.name] to continue eagerly pumping [npc.her] [npc.cock+] in and out of [npc2.namePos] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
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
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc.namePos] [npc.cock+] down [npc2.namePos] throat one last time, [npc2.name] then [npc2.verb(pull)] [npc2.namePos] head back, putting a quick end to [npc.her] blowjob.",

							"Slamming [npc2.namePos] [npc2.face] into [npc.namePos] groin, [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat, before pulling completely back and letting [npc.herHim] slip out of [npc2.namePos] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.cock+] out of [npc2.namePos] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] put an end to the blowjob.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(pull)] [npc2.namePos] head back, sliding [npc.namePos] [npc.cock+] fully out of [npc2.namePos] mouth."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_sob+], struggling against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let [npc.herHim] go.",

							" Tears stream down [npc.her] cheeks as [npc.she] tries to struggle out of [npc2.namePos] grip, letting out [npc.a_sob+] before begging for [npc2.name] to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] [npc.verb(let)] out [npc.a_moan+], revealing [npc.her] desire for [npc2.name] to continue sucking [npc.her] [npc.cock+].",

							" [npc.She] [npc.moansVerb] as [npc2.name] withdraws from [npc.her] groin, betraying [npc.her] desire to feel [npc2.namePos] [npc2.lips+] wrapped around [npc.her] [npc.cock+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
