package com.base.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.LubricationType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerPenisMouth {
	/*
	 * Player - Cock slap
	 * Partner - Suck balls, lick head, herm fun
	 * 
	 * Player start/stop
	 * Receiving blowjob:
	 * 		Dom gentle, normal, rough
	 * 		Sub normal, eager, resist
	 * 
	 * Partner start/stop
	 * Partner giving blowjob:
	 * 		Dom gentle, normal, rough
	 * 		Sub normal, eager, resist
	 */
	
	public static SexAction PARTNER_COCK_SLAP = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Cock slap";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock] out of [pc.name]'s mouth and slap [pc.her] face with it.";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) {
				
				return UtilText.returnStringAtRandom(
						"[npc.Name] suddenly pulls back, sliding [npc.her] [npc.cock+] out of your mouth."
							+ " As you look up at [npc.herHim], [npc.she] slaps [npc.her] hard shaft against your cheek, splattering "
								+(Sex.getWetOrificeTypes().get(OrificeType.MOUTH_PLAYER).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")+"saliva across your face, before thrusting [npc.her] [npc.cock+] back down your throat.",
								
						"Stepping back, [npc.name] slides [npc.her] [npc.cock+] free from your mouth, and then proceeds to slap the saliva-coated head against your cheeks."
								+ " As you open your mouth to gasp in shock, [npc.she] uses the opportunity to guide [npc.her] the [npc.cockHead] of [npc.her] [npc.cock] back past your lips, before pushing it deep down your throat.",
						
						"[npc.Name] suddenly steps back and allows [npc.her] [npc.cock+] to slide out from your mouth."
							+ " Looking up at [npc.herHim], you're taken by surprise as [npc.she] slaps [npc.her] hard shaft against your face,"
							+ " leaving a streak of "+(Sex.getWetOrificeTypes().get(OrificeType.MOUTH_PLAYER).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
							+"saliva drooling down your cheek, before forcing [npc.her] [npc.cock+] back down your throat.",
								
						"Quickly pulling [npc.her] [npc.cock+] out from your mouth, [npc.name] holds the base in one hand while holding your head still with the other."
							+ " As you glance up to see what [npc.she]'s planning next, you're met with a wet slap as [npc.she] swings [npc.her] slimy [npc.cock] against your cheek."
							+ " Opening your mouth in shock, [npc.she] uses the opportunity to thrust [npc.her] [npc.cock+] back down your throat.");
				
			} else {
			
				return UtilText.returnStringAtRandom(
						"Pulling [npc.her] [npc.hips] back, [npc.name] slides [npc.her] [npc.cock+] out of your mouth."
							+ " Before you can react, [npc.she] suddenly slaps [npc.her] hard shaft against your cheek, splattering saliva "
							+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PARTNER).contains(LubricationType.PARTNER_PRECUM)?"and precum ":"")+"across your [pc.face], before thrusting [npc.her] [npc.cock+] back down your throat.",
								
						"Pulling back, [npc.name] slides [npc.her] [npc.cock+] free from your mouth, and with [npc.a_moan+], [npc.she] proceeds to slap the saliva-coated [npc.cockHead] against your [pc.face].",
						
						"[npc.Name] slides [npc.her] [npc.cock+] out from your mouth, and, grinning to [npc.herself], [npc.she] then slaps [npc.her] hard shaft against your [pc.face],"
							+ " leaving a streak of "+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PARTNER).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
							+"saliva drooling down your cheek, before forcing [npc.her] [npc.cock] back down your throat.",
								
						"Quickly pulling [npc.her] [npc.hips+] back, [npc.name] draws [npc.her] [npc.cock+] out from your mouth before starting to slap its slimy length against your cheeks.");
			
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PARTNER_FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Balls focus";
		}

		@Override
		public String getActionDescription() {
			return "Force [pc.name] to pay some attention to your [npc.balls+]";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of your mouth, before shuffling about until [npc.her] [npc.balls+] are gently pressing against your [pc.lips+].",
							"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are gently pressing against your [pc.lips+].",
							"[npc.Name] slides [npc.her] [npc.cock+] out of your mouth, before repositioning [npc.herself] so that your [pc.lips+] are gently pressed against [npc.her] [npc.balls+].",
							"Quickly sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] until [npc.she]'s gently pressing [npc.her] [npc.balls+] against your [pc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of your mouth, before shuffling about until [npc.her] [npc.balls+] are pressing against your [pc.lips+].",
							"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against your [pc.lips+].",
							"[npc.Name] slides [npc.her] [npc.cock+] out of your mouth, before repositioning [npc.herself] so that your [pc.lips+] are pressed against [npc.her] [npc.balls+].",
							"Quickly sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of your mouth, before shuffling about until [npc.her] [npc.balls+] are roughly grinding against your [pc.lips+].",
							"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are roughly grinding against your [pc.lips+].",
							"[npc.Name] slides [npc.her] [npc.cock+] out of your mouth, before repositioning [npc.herself] so that your [pc.lips+] are roughly grinding against [npc.her] [npc.balls+].",
							"Quickly sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] until [npc.she]'s roughly forcing [npc.her] [npc.balls+] against your [pc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of your mouth, before shuffling about until [npc.her] [npc.balls+] are pressing down against your [pc.lips+].",
							"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against your [pc.lips+].",
							"[npc.Name] slides [npc.her] [npc.cock+] out of your mouth, before repositioning [npc.herself] so that your [pc.lips+] are pressed against [npc.her] [npc.balls+].",
							"Quickly sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against your [pc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing [npc.her] [npc.hips] back, [npc.name] allows [npc.her] [npc.cock+] to slide out of your mouth, before shuffling about until [npc.her] [npc.balls+] are pressing down against your [pc.lips+].",
							"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] so that [npc.her] [npc.balls+] are pressing against your [pc.lips+].",
							"[npc.Name] slides [npc.her] [npc.cock+] out of your mouth, before repositioning [npc.herself] so that your [pc.lips+] are pressed against [npc.her] [npc.balls+].",
							"Quickly sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] repositions [npc.herself] until [npc.she]'s forcing [npc.her] [npc.balls+] against your [pc.lips+]."));
					break;
				default:
					break;
			
			}
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Slowly sliding your [pc.tongue+] out, you start to gently lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" You gently start to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting your [pc.tongue+] out, you greedily start to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" You eagerly start to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Sliding your [pc.tongue+] out, you start to roughly lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" You roughly start kissing and licking [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting your [pc.tongue+] out, you greedily start to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" You eagerly start to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Darting your [pc.tongue+] out, you start to lick and kiss [npc.her] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" You start to kiss and lick [npc.her] [npc.balls+], eliciting [npc.a_moan+] from their horny owner."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You desperately try to pull away, letting out [pc.a_sob+] as [npc.her] [npc.balls+] continue grinding against your [pc.lips+].",
							" You let out a muffled [pc.sob], trying desperately to pull away from [npc.her] [npc.balls+] as you struggle against [npc.herHim]."));
					break;
				default:
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_SUCK_BALLS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick balls";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss [npc.name]'s [npc.balls] for a while.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.name]'s [npc.cock+] slip completely out of your mouth, you move your head down and start to gently lick and suck on [npc.her] [npc.balls+].",
							"You let [npc.name]'s [npc.cock+] slide out of your mouth, before moving your [pc.lips+] down to start gently licking and kissing [npc.her] [npc.balls+].",
							"Sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down to start gently kissing and licking [npc.her] [npc.balls+].",
							"First sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down, before starting to gently kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.name]'s [npc.cock+] slip completely out of your mouth, you move your head down and start to eagerly lick and suck on [npc.her] [npc.balls+].",
							"You let [npc.name]'s [npc.cock+] slide out of your mouth, before moving your [pc.lips+] down to start desperately licking and kissing [npc.her] [npc.balls+].",
							"Sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down to start eagerly kissing and licking [npc.her] [npc.balls+].",
							"First sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down, before starting to eagerly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.name]'s [npc.cock+] slip completely out of your mouth, you move your head down and start to roughly lick and suck on [npc.her] [npc.balls+].",
							"You let [npc.name]'s [npc.cock+] slide out of your mouth, before moving your [pc.lips+] down to start roughly licking and kissing [npc.her] [npc.balls+].",
							"Sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down to start roughly kissing and licking [npc.her] [npc.balls+].",
							"First sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down, before starting to roughly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.name]'s [npc.cock+] slip completely out of your mouth, you move your head down and start to eagerly lick and suck on [npc.her] [npc.balls+].",
							"You let [npc.name]'s [npc.cock+] slide out of your mouth, before moving your [pc.lips+] down to start desperately licking and kissing [npc.her] [npc.balls+].",
							"Sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down to start eagerly kissing and licking [npc.her] [npc.balls+].",
							"First sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down, before starting to eagerly kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting [npc.name]'s [npc.cock+] slip completely out of your mouth, you move your head down and start to lick and suck on [npc.her] [npc.balls+].",
							"You let [npc.name]'s [npc.cock+] slide out of your mouth, before moving your [pc.lips+] down to start licking and kissing [npc.her] [npc.balls+].",
							"Sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down to start kissing and licking [npc.her] [npc.balls+].",
							"First sliding [npc.name]'s [npc.cock+] out from your mouth, you move your head down, before starting to kiss and nuzzle into [npc.her] [npc.balls+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_LICK_HEAD = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick head";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss the [npc.cockHead+] of [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String barbedSpecial = "", flaredSpecial = "";
			
			if(Sex.getPartner().isPenisBarbedShaft()) {
				barbedSpecial = "You slide your head back, letting out a muffled [pc.moan] as you feel the barbs lining [npc.name]'s [npc.cock] rake their way up your throat."
									+ " Leaving just the [npc.cockHead+] pushed past your [pc.lips+], you then start to passionately kiss and suck the tip of [npc.her] [npc.cock+].";
			}
			if(Sex.getPartner().isPenisFlaredHead()) {
				flaredSpecial = "Sliding your head back, you allow [npc.name]'s [npc.cock+] to slip almost completely out of your mouth,"
									+ " leaving just the wide, flared head pushed past your [pc.lips+], which you start to passionately kiss and lick.";
			}
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
							barbedSpecial, flaredSpecial,
							
						"With [pc.a_moan+], you pull your head back, wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock+], before starting to lick and kiss it.",
					
						"You pull your head back, letting out [pc.a_moan+] as you start concentrating on sucking and kissing the [npc.cockHead+] of [npc.name]'s [npc.cock+].",
	
						"Pulling your head back a little, you let most of [npc.name]'s [npc.cock+] slide out of your mouth, and,"
							+ " focusing on using your [pc.tongue+], you start licking and kissing the [npc.cockHead+] that's left poking past your [pc.lips+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_HERM_FUN = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING && Sex.getPartner().hasVagina() && Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.isPartnerFreeVagina();
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getPartner().isFeminine()) {
				return "Futa fun";
			} else {
				return "Herm fun";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pleasure both [npc.name]'s [npc.cock+] and [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give the [npc.cockHead] of [npc.her] [npc.cock+] a gentle suck, before pulling back and starting to slowly lick and kiss [npc.her] [npc.pussy+].",
							"Deciding to give [npc.name]'s [npc.pussy] some attention, you deliver a long, slow lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and gently sliding your [pc.tongue+] into [npc.her] [npc.pussy+].",
							"Delivering a gentle kiss to the [npc.cockHead] of [npc.name]'s [npc.cock+], you move down, bringing your [pc.lips+] to [npc.her] [npc.pussy+] before slowly pushing your [pc.tongue+] between [npc.her] folds."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss [npc.her] [npc.pussy+].",
							"Deciding to give [npc.name]'s [npc.pussy] some attention, you deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and eagerly darting your [pc.tongue+] into [npc.her] [npc.pussy+].",
							"Delivering a wet kiss to the [npc.cockHead] of [npc.name]'s [npc.cock+], you move down, bringing your [pc.lips+] to [npc.her] [npc.pussy+] before eagerly darting your [pc.tongue+] between [npc.her] folds."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to roughly lick and kiss [npc.her] [npc.pussy+].",
							"Deciding to give [npc.name]'s [npc.pussy] some attention, you deliver a long, roughly lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and dominantly thrusting your [pc.tongue+] into [npc.her] [npc.pussy+].",
							"Delivering a rough kiss to the [npc.cockHead] of [npc.name]'s [npc.cock+], you move down, bringing your [pc.lips+] to [npc.her] [npc.pussy+] before dominantly thrusting your [pc.tongue+] between [npc.her] folds."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss [npc.her] [npc.pussy+].",
							"Deciding to give [npc.name]'s [npc.pussy] some attention, you deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and eagerly darting your [pc.tongue+] into [npc.her] [npc.pussy+].",
							"Delivering a wet kiss to the [npc.cockHead] of [npc.name]'s [npc.cock+], you move down, bringing your [pc.lips+] to [npc.her] [npc.pussy+] before eagerly darting your [pc.tongue+] between [npc.her] folds."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give the [npc.cockHead] of [npc.her] [npc.cock+] a wet suck, before pulling back and starting to lick and kiss [npc.her] [npc.pussy+].",
							"Deciding to give [npc.name]'s [npc.pussy] some attention, you deliver a long, wet lick up the length of [npc.her] [npc.cock+],"
									+ " before pressing forwards and darting your [pc.tongue+] into [npc.her] [npc.pussy+].",
							"Delivering a wet kiss to the [npc.cockHead] of [npc.name]'s [npc.cock+], you move down, bringing your [pc.lips+] to [npc.her] [npc.pussy+] before darting your [pc.tongue+] between [npc.her] folds."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan], gently pushing [npc.her] [npc.pussy+] against your [pc.lips+] before you decide to shift your focus back to [npc.her] [npc.cock+] once again.",
							" Gently thrusting [npc.her] [npc.pussy+] into your [pc.face], [npc.she] lets out a soft [npc.moan], before you decide to move back to focusing on [npc.her] [npc.cock+].",
							" Softly [npc.moaning], [npc.she] gently bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back to [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly pushing [npc.her] [npc.pussy+] against your [pc.lips+] before you decide to shift your focus back to [npc.her] [npc.cock+] once again.",
							" Eagerly thrusting [npc.her] [npc.pussy+] into your [pc.face], [npc.she] lets out [npc.a_moan+], before you decide to move back to focusing on [npc.her] [npc.cock+].",
							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back to [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+] before you decide to shift your focus back to [npc.her] [npc.cock+] once again.",
							" Roughly grinding [npc.her] [npc.pussy+] into your [pc.face], [npc.she] lets out [npc.a_moan+], before you decide to move back to focusing on [npc.her] [npc.cock+].",
							" [npc.Moaning+], [npc.she] roughly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly pushing [npc.her] [npc.pussy+] against your [pc.lips+] before you decide to shift your focus back to [npc.her] [npc.cock+] once again.",
							" Eagerly thrusting [npc.her] [npc.pussy+] into your [pc.face], [npc.she] lets out [npc.a_moan+], before you decide to move back to focusing on [npc.her] [npc.cock+].",
							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], pushing [npc.her] [npc.pussy+] against your [pc.lips+] before you decide to shift your focus back to [npc.her] [npc.cock+] once again.",
							" Thrusting [npc.her] [npc.pussy+] into your [pc.face], [npc.she] lets out [npc.a_moan+], before you decide to move back to focusing on [npc.her] [npc.cock+].",
							" [npc.Moaning+], [npc.she] bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back to [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Sobbing] and squirming in discomfort, [npc.she] desperately tries to pull away from you, begging to be left alone as you shift your attention back down to [npc.her] [npc.cock+] once again.",
							" With [npc.a_sob+], [npc.she] tries to push you away, squirming in discomfort as you move back to focusing your attention on [npc.her] [npc.cock+].",
							" With tears streaming down [npc.her] [npc.face], [npc.she] lets out [npc.a_sob+] as you move back to focusing on [npc.her] [npc.cock+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Get blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] into [pc.name]'s mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab your head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+],"
										+ " before gently pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into your mouth.",
								"Reaching down to take hold of your head, [npc.name] pushes the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+],"
										+ " before slowly bucking [npc.her] [npc.hips] into your [pc.face] and gently sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab your head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+],"
										+ " before eagerly pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into your mouth.",
								"Reaching down to take hold of your head, [npc.name] pushes the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+],"
										+ " before eagerly bucking [npc.her] [npc.hips] into your [pc.face] and sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab your head, [npc.name] lines the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] into your mouth.",
								"Reaching down to take hold of your head, [npc.name] pushes the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] into your [pc.face] and forcing [npc.her] [npc.cock+] into your mouth."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly lowers [npc.her] [npc.cock+] down to your mouth,"
										+ " before gently pushing the [npc.cockHead+] past your [pc.lips+] and collapsing down onto your [pc.face+].",
								"Gently lowering [npc.her] [npc.cock+] down to your mouth, [npc.name] slowly allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past your [pc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly drops [npc.her] [npc.cock+] down to your mouth,"
										+ " before eagerly pushing the [npc.cockHead+] past your [pc.lips+] and collapsing down onto your [pc.face+].",
								"Quickly dropping [npc.her] [npc.cock+] down to your mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly forces the [npc.cockHead+] past your [pc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly grinds [npc.her] [npc.cock+] down against your mouth,"
										+ " before forcefully pushing the [npc.cockHead+] past your [pc.lips+] and collapsing down onto your [pc.face+].",
								"Roughly grinding [npc.her] [npc.cock+] down against your mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past your [pc.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+], [npc.name] gently pushes [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into your mouth.",
								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+], [npc.name] slowly bucks [npc.her] [npc.hips] into your [pc.face], gently sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+], [npc.name] eagerly pushes [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into your mouth.",
								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+], [npc.name] eagerly bucks [npc.her] [npc.hips] into your [pc.face], sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+], [npc.name] roughly slams [npc.her] [npc.hips] forwards and forces [npc.her] [npc.cock+] into your mouth.",
								"Grinding the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+], [npc.name] roughly slams [npc.her] [npc.hips] into your [pc.face], forcing [npc.her] [npc.cock+] into your mouth."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+], [npc.name] eagerly pushes [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into your mouth.",
								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+], [npc.name] eagerly bucks [npc.her] [npc.hips] into your [pc.face], sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.lips+], [npc.name] pushes [npc.her] [npc.hips] forwards and slides [npc.her] [npc.cock+] into your mouth.",
								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against your [npc.lips+], [npc.name] bucks [npc.her] [npc.hips] into your [pc.face], sliding [npc.her] [npc.cock+] into your mouth."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a muffled [pc.moan], slowly sliding your head forwards as you start gently sucking [npc.her] [npc.cock+].",
							" With a soft, and very muffled, [pc.moan], you start gently sliding your head forwards, wrapping your [pc.lips+] around [npc.her] [npc.cock+] as you start giving [npc.herHim] a blowjob."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly sliding your head forwards as you start happily sucking [npc.her] [npc.cock+].",
							" With an eager, and very muffled, [pc.moan], you start happily sliding your head forwards, wrapping your [pc.lips+] around [npc.her] [npc.cock+] as you start giving [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], roughly slamming your head forwards as you start forcing [npc.her] [npc.cock+] deep down your throat.",
							" With an eager, and very muffled, [pc.moan], you forcefully push your head forwards, wrapping your [pc.lips+] around [npc.her] [npc.cock+] as you start giving [npc.herHim] a rough blowjob."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly sliding your head forwards as you start happily sucking [npc.her] [npc.cock+].",
							" With an eager, and very muffled, [pc.moan], you start happily sliding your head forwards, wrapping your [pc.lips+] around [npc.her] [npc.cock+] as you start giving [npc.herHim] a blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], sliding your head forwards as you start sucking [npc.her] [npc.cock+].",
							" With a muffled, [pc.moan], you start sliding your head forwards, wrapping your [pc.lips+] around [npc.her] [npc.cock+] as you start giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a muffled [pc.sob], gargling and choking on [npc.her] [npc.cock+] as you frantically try to pull your head away from [npc.her] groin.",
							" With a muffled [pc.sob], you frantically try to pull away from [npc.her] [npc.cock+], gargling and choking as you squirm and struggle against [npc.herHim]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Hold still and let [pc.name] suck your [npc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by eagerly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] slowly pushes [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as [npc.she] feels you respond by eagerly wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Gently bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out a soft [npc.moan] as [npc.she] feels you respond by happily sliding your head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.sob] as you respond by desperately trying to pull your head away from [npc.her] groin.",
							"[npc.Name] slowly pushes [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as [npc.she] feels you respond by frantically trying to pull your head away from [npc.her] [npc.cock+].",
							"Gently bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out a soft [npc.moan] as [npc.she] feels you respond by desperately trying to pull away from [npc.her] [npc.cock+],"
									+ " letting out a muffled [pc.sob] as tears well up in your [pc.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently slides [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] slowly pushes [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as [npc.she] feels you respond by wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Gently bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out a soft [npc.moan] as [npc.she] feels you respond by sliding your head up and down,"
									+ " obediently continuing to give [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Continue blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [pc.name]'s face to encourage [pc.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by eagerly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by eagerly wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Frantically bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by happily sliding your head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.sob] as you respond by desperately trying to pull your head away from [npc.her] groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by frantically trying to pull your head away from [npc.her] [npc.cock+].",
							"Frantically bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by desperately trying to pull away from [npc.her] [npc.cock+],"
									+ " letting out a muffled [pc.sob] as tears well up in your [pc.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Frantically bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by sliding your head up and down,"
									+ " obediently continuing to give [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] down [pc.name]'s throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Sex.getPartner().isPenisKnotted()) {
					knottedSpecial = "With a sudden, violent thrust forwards, [npc.name] buries [npc.her] [npc.cock+] deep down your throat."
									+ " Holding your head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking your [pc.face],"
									+ " and you feel tears streaming from your [pc.eyes] as [npc.she] slams [npc.her] knot repeatedly against your [pc.lips+].";
				}
				if(Sex.getPartner().isPenisBarbedShaft()) {
					barbedSpecial = "With a sudden, violent thrust forwards, [npc.name] buries [npc.her] [npc.cock+] deep down your throat."
									+ " Holding your head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking your [pc.face],"
									+ " and you feel tears streaming from your [pc.eyes] as you feel the barbs lining [npc.her] shaft repeatedly raking up the sides of your throat.";
				}
				if(Sex.getPartner().isPenisFlaredHead()) {
					flaredSpecial = "With a sudden, violent thrust forwards, [npc.name] buries [npc.her] [npc.cock+] deep down your throat."
									+ " Holding your head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking your [pc.face],"
									+ " and you feel tears streaming from your [pc.eyes] as [npc.her] wide, flared head forces its way up and down your throat.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"[npc.Name] grabs the sides of your head, and before you know what's happening, [npc.she]'s roughly slamming [npc.her] [npc.cock+] in and out of your facial fuck-hole.",
						
								"Letting out [npc.a_moan+], [npc.name] slams [npc.her] [npc.cock+] all the way down your throat."
									+ " As you blink back tears, [npc.she] starts rapidly bucking [npc.her] [npc.hips] back and forth, holding your head in place with both hands as [npc.she] ruthlessly fucks your [pc.face].",
								
								"Grabbing the sides of your head, [npc.name] roughly pulls you into [npc.her] groin, sinking [npc.her] [npc.cock+] deep down your throat before starting to ruthlessly fuck your [pc.face].",
										
								"With a forceful thrust, [npc.name] hilts [npc.her] [npc.cock+] deep down your throat."
									+ " As a slimy stream of saliva "+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PARTNER).contains(LubricationType.PARTNER_PRECUM)?"and precum ":"")
									+"drools from the corners of your mouth, [npc.she] bucks back, letting you gasp for air for a brief moment before starting to aggressively fuck your [pc.face]."));
			
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Sex.getPartner().isPenisKnotted()) {
					knottedSpecial = "Spreading [npc.her] knees out on either side of your head, [npc.name] violently thrusts downwards, burying [npc.her] [npc.cock+] deep down your throat."
										+ " Grinding [npc.her] fat knot against your [pc.lips+] for moment, [npc.she] then proceeds to start roughly fucking your face,"
										+ " and you find yourself [pc.moaning] in desperation as you struggle to breathe,"
										+ " your pitiful cries being accompanied by the wet slapping sounds that [npc.her] fat knot makes at it repeatedly slams against your [pc.lips].";
				}
				if(Sex.getPartner().isPenisBarbedShaft()) {
					barbedSpecial = "Spreading [npc.her] knees out on either side of your head, [npc.name] violently thrusts downwards, burying [npc.her] [npc.cock+] deep down your throat."
										+ " Grinding the base against your [pc.lips+] for moment, [npc.she] then proceeds to start roughly fucking your face,"
										+ " and you find yourself [pc.moaning] in desperation as you struggle to breathe,"
										+ " squirming about beneath [npc.herHim] as you feel your throat being stretched out by the wide, flared head of [npc.her] [npc.cock+].";
				}
				if(Sex.getPartner().isPenisFlaredHead()) {
					flaredSpecial = "Spreading [npc.her] knees out on either side of your head, [npc.name] violently thrusts downwards, burying [npc.her] [npc.cock+] deep down your throat."
										+ " Grinding the base against your [pc.lips+] for moment, [npc.she] then proceeds to start roughly fucking your face,"
										+ " and you find yourself [pc.moaning] in desperation as you struggle to breathe,"
										+ " squirming about beneath [npc.herHim] as you feel your throat being raked by the series of barbs that line the sides of [npc.her] [npc.cock+].";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"[npc.Name] spreads [npc.her] knees out on either side of your head, and before you realise what's about to happen,"
										+ " [npc.she] roughly starts slamming [npc.her] [npc.cock+] in and out of your mouth, letting out a series of [npc.moans+] as [npc.she] ruthlessly fucks your face.",
								
								"Letting out [npc.a_moan+], [npc.name] slams [npc.her] [npc.cock+] all the way down your throat."
									+ " As you blink back tears, [npc.she] starts rapidly thrusting [npc.her] [npc.hips] up and down, letting out more [npc.moans+] as [npc.she] ruthlessly fucks your face.",
								
								"Dropping down onto your face, [npc.name] roughly slams [npc.her] [npc.cock+] deep down your throat,"
										+ " letting out [npc.a_moan+] before starting to violently thrust [npc.her] [npc.hips] up and down as [npc.she] ruthlessly fucks your face.",
										
								"With a forceful thrust, [npc.name] hilts [npc.her] [npc.cock+] deep down your throat."
									+ " As a slimy stream of "+(Sex.getWetOrificeTypes().get(OrificeType.MOUTH_PLAYER).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
									+"saliva drools from the corners of your mouth, [npc.she] lifts [npc.herself] up, letting you gasp for air for a brief moment before sinking down once more and starting to aggressively fuck your face."));
				
			} else {
			
				switch(Sex.getSexPacePlayer()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by obediently relaxing your throat,"
										+ " allowing [npc.herHim] to brutally fuck your [pc.face].",
								"[npc.Name] roughly slams [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by obediently relaxing your throat,"
										+ " signalling your willingness for [npc.herHim] to continue brutally fucking your [pc.face].",
								"Violently slamming [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by happily relaxing your throat,"
										+ " all too eager for [npc.her] [npc.cock+] to brutally fuck your [pc.face]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.sob] as you respond by frantically trying to pull away,"
										+ " tears streaming from your [pc.eyes] as [npc.she] brutally fucks your [pc.face].",
								"[npc.Name] roughly slams [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by frantically trying to pull away from [npc.her] groin,"
										+ " your muffled [pc.sobs] only serving to encourage [npc.herHim] as [npc.she] brutally fucks your [pc.face].",
								"Violently slamming [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by frantically trying to pull away from [npc.herHim],"
										+ " [pc.sobbing] and squirming in distress as [npc.she] brutally fucks your [pc.face]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly slams [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by obediently holding still,"
										+ " allowing [npc.herHim] to brutally fuck your [pc.face].",
								"[npc.Name] roughly slams [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by obediently staying in position,"
										+ " allowing [npc.herHim] to continue brutally fucking your [pc.face].",
								"Violently slamming [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by holding your head still,"
										+ " happy for [npc.her] [npc.cock+] to brutally fuck your [pc.face]."));
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] out of [pc.name]'s mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from your face, but [npc.her] efforts prove to be in vain as you gently, but firmly, holds [npc.herHim] in place,"
								+ " slowly sliding your [pc.lips+] up and down the length of [npc.her] [npc.cock+] as you continue giving [npc.herHim] an unwanted blowjob.",
						"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of your mouth."
								+ " Your grip proves to be too strong, and you gently, but firmly, continue to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against you.",
						"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of your mouth, but your grip is too strong for [npc.herHim]"
								+ ", and [npc.her] struggles prove to be in vain as you continue giving [npc.herHim] a slow, gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from your face, but [npc.her] efforts prove to be in vain as you roughly hold [npc.herHim] in place,"
									+ " growling in a threatening manner as you forcefully slide your [pc.lips+] up and down the length of [npc.her] [npc.cock+].",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of your mouth."
									+ " Your grip proves to be far too strong, and you let out a threatening growl as you continue to suck [npc.her] [npc.cock+].",
							"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of your mouth, but your grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as you continue giving [npc.herHim] a rough, forceful blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] hips away from your face, but [npc.her] efforts prove to be in vain as you firmly hold [npc.herHim] in place,"
									+ " eagerly sliding your [pc.lips+] up and down the length of [npc.her] [npc.cock+] as you continue giving [npc.herHim] an unwanted blowjob.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] [npc.cock+] out of your mouth."
									+ " Your grip proves to be too strong, and you eagerly continue to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against you.",
							"[npc.Sobbing+], [npc.name] tries to pull [npc.her] [npc.cock+] out of your mouth, but your grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as you continue giving [npc.herHim] an eager blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Continue blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [pc.name]'s face to encourage [pc.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by slowly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by gently wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by slowly sliding your head up and down,"
									+ " continuing to give [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] thrusts [npc.her] [npc.cock+] past your [pc.lips+],"
									+ " and you find yourself letting out a threatening growl as you remind [npc.herHim] who's in charge, before roughly bobbing your head up and down into your groin.",
							"[npc.Name] pushes [npc.her] [npc.hips] into your [pc.face], and, wanting to remind [npc.herHim] who's in charge, you let out a threatening growl before roughly clamping your [pc.lips+] around [npc.her] [npc.cock+].",
							"As [npc.name] bucks [npc.her] [npc.hips+] into your [pc.face], you let out a threatening growl to remind [npc.herHim] who's in charge,"
									+ " before sliding your head up and down, continuing to give [npc.herHim] a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by eagerly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by eagerly wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by happily sliding your head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			null,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.hips] into [pc.name]'s face, forcing your [npc.cock+] down [pc.her] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by slowly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by gently wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Frantically bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by slowly sliding your head up and down,"
									+ " continuing to give [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+],"
									+ " and you find yourself letting out a threatening growl as you remind [npc.herHim] who's in charge, before roughly bobbing your head up and down into your groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], and, wanting to remind [npc.herHim] who's in charge,"
									+ " you let out a threatening growl before roughly clamping your [pc.lips+] around [npc.her] [npc.cock+].",
							"As [npc.name] frantically bucks [npc.her] [npc.hips+] into your [pc.face], you let out a threatening growl to remind [npc.herHim] who's in charge,"
									+ " before sliding your head up and down, continuing to give [npc.herHim] a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily thrusts [npc.her] [npc.cock+] past your [pc.lips+], and you find yourself letting out a muffled [pc.moan] as you respond by eagerly bobbing your head up and down into [npc.her] groin.",
							"[npc.Name] desperately pushes [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as [npc.she] feels you respond by eagerly wrapping your [pc.lips+] around [npc.her] [npc.cock+].",
							"Frantically bucking [npc.her] [npc.hips+] into your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] feels you respond by happily sliding your head up and down,"
									+ " all too eager to continue giving [npc.herHim] a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_BLOWJOB_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [pc.name]'s mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] allows [npc.her] [npc.cock+] to slide up and out of your mouth,"
							+ " and you look up to see a slimy strand of saliva linking your lips to the [npc.cockHead+] of [npc.her] [npc.cock] for a brief moment, before breaking to fall down over your face.");
				
			} else {
				switch(Sex.getSexPacePartner()) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] down your throat one last time, [npc.name] then pulls [npc.her] [npc.hips] back, grinning as you splutter and gasp for air.",
								"Slamming [npc.her] [npc.hips] into your [pc.face], [npc.name] forces [npc.her] [npc.cock+] deep down your throat, before pulling completely back and out of your mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.cock+] out of your mouth, [npc.name] lets out [npc.a_moan+] as [npc.she] puts an end to the blowjob.",
								"With [npc.a_moan+], [npc.name] pulls [npc.her] [npc.hips] back, sliding [npc.her] [npc.cock+] fully out of your mouth."));
						break;
				}
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+], struggling against [npc.herHim] as you plead for [npc.herHim] to let you go.",
							" You feel tears streaming down your cheeks as you try to struggle out of [npc.her] grip, letting out [pc.a_sob+] before begging for [npc.herHim] to let you go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], revealing your desire to continue sucking [npc.her] [npc.cock+].",
							" You [pc.moanVerb] as [npc.she] withdraws from your mouth, betraying your lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static SexAction PLAYER_BLOWJOB_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || Sex.isPlayerDom(); // Player can only start in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.name]'s [npc.cock+] into your mouth and start sucking [npc.herHim] off.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you slowly lower your head between [npc.her] [npc.legs],"
										+ " passionately kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before taking [npc.herHim] into your mouth.",
								"Gently lowering your head down between [npc.name]'s [npc.legs], you deliver a long, slow lick up the length of [npc.her] [npc.cock+], before taking the [npc.cockHead+] into your mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you eagerly drop your head between [npc.her] [npc.legs],"
										+ " passionately kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before greedily taking [npc.herHim] into your mouth.",
								"Eagerly dropping your head down between [npc.name]'s [npc.legs], you deliver a long, wet lick up the length of [npc.her] [npc.cock+], before greedily taking the [npc.cockHead+] into your mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you quickly drop your head between [npc.her] [npc.legs],"
										+ " roughly kissing the [npc.cockHead+] of [npc.her] [npc.cock+] before forcefully taking [npc.herHim] into your mouth.",
								"Dropping your head down between [npc.name]'s [npc.legs], you deliver a rough, wet lick up the length of [npc.her] [npc.cock+], before forcefully taking the [npc.cockHead+] into your mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing your [pc.lips+] up to the [npc.cockHead+] of [npc.name]'s [npc.cock], you slowly take [npc.herHim] into your mouth, letting out a muffled [pc.moan] as you start giving [npc.herHim] a blowjob.",
								"Wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock], you let out a muffled [pc.moan] as you start giving [npc.herHim] a gentle blowjob."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing your [pc.lips+] up to the [npc.cockHead+] of [npc.name]'s [npc.cock], you eagerly take [npc.herHim] into your mouth, letting out a muffled [pc.moan] as you happily start giving [npc.herHim] a blowjob.",
								"Wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock], you let out a muffled [pc.moan] as you eagerly start giving [npc.herHim] a gentle blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing your [pc.lips+] up to the [npc.cockHead+] of [npc.name]'s [npc.cock], you forcefully take [npc.herHim] into your mouth, letting out a muffled [pc.moan] as you start giving [npc.herHim] a rough blowjob.",
								"Forcefully wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock], you let out a muffled [pc.moan] as you start giving [npc.herHim] a rough blowjob."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing your [pc.lips+] up to the [npc.cockHead+] of [npc.name]'s [npc.cock], you eagerly take [npc.herHim] into your mouth, letting out a muffled [pc.moan] as you happily start giving [npc.herHim] a blowjob.",
								"Wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock], you let out a muffled [pc.moan] as you eagerly start giving [npc.herHim] a gentle blowjob."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing your [pc.lips+] up to the [npc.cockHead+] of [npc.name]'s [npc.cock], you take [npc.herHim] into your mouth, letting out a muffled [pc.moan] as you start giving [npc.herHim] a blowjob.",
								"Wrapping your [pc.lips+] around the [npc.cockHead+] of [npc.name]'s [npc.cock], you let out a muffled [pc.moan] as you eagerly start giving [npc.herHim] a blowjob."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] gently bucks [npc.her] [npc.hips] into your [pc.face], softly [npc.moaning] as you suck [npc.her] [npc.cock+].",
							" With a gentle buck of [npc.her] [npc.hips], [npc.she] lets out a soft [npc.moan] as [npc.she] enjoys the feeling of your [pc.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] greedily bucks [npc.her] [npc.hips] into your [pc.face], [npc.moaning+] as you suck [npc.her] [npc.cock+].",
							" With an energetic buck of [npc.her] [npc.hips], [npc.she] lets out [npc.a_moan+] as [npc.she] enjoys the feeling of your [pc.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] roughly slams [npc.her] [npc.hips] into your [pc.face], [npc.moaning+] as you suck [npc.her] [npc.cock+].",
							" With an rough thrust of [npc.her] [npc.hips], [npc.she] lets out [npc.a_moan+] as [npc.she] enjoys the feeling of your [pc.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] greedily bucks [npc.her] [npc.hips] into your [pc.face], [npc.moaning+] as you suck [npc.her] [npc.cock+].",
							" With an energetic buck of [npc.her] [npc.hips], [npc.she] lets out [npc.a_moan+] as [npc.she] enjoys the feeling of your [pc.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] bucks [npc.her] [npc.hips] into your [pc.face], [npc.moaning+] as you suck [npc.her] [npc.cock+].",
							" Bucking [npc.her] [npc.hips] into your [pc.face], [npc.she] lets out [npc.a_moan+] as [npc.she] enjoys the feeling of your [pc.lips+] sliding up and down the length of [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+], trying, and failing, to pull [npc.her] [npc.cock] out of your mouth as [npc.she] struggles against your unwanted oral attention.",
							" With tears welling up in [npc.her] [npc.eyes], [npc.name] struggles against your unwanted oral attention, [npc.sobbing] and squirming as [npc.she] begs for you to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_DOMINANT));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Gently suck [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] as [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face].",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you slowly bob your head up and down, letting out a muffled [pc.moan] as [npc.she] eagerly thrusts [npc.her] [npc.hips] into your [pc.face] in response.",
							"Slowly bobbing your head up and down, you gently suck [npc.name]'s [npc.cock+], causing [npc.herHim] to frantically buck [npc.her] [npc.hips] into your [pc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobsVerb] in distress.",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you slowly bob your head up and down,"
									+ " holding [npc.herHim] in place as [npc.she] desperately [npc.sobsVerb] and struggles against your unwanted oral attention.",
							"Slowly bobbing your head up and down, you gently suck [npc.name]'s [npc.cock+], holding [npc.herHim] in place as [npc.she] [npc.sobs] and begs for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] and drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you slowly bob your head up and down, drawing [npc.a_moan+] from [npc.her] mouth.",
							"Slowly bobbing your head up and down, you gently suck [npc.name]'s [npc.cock+], causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eager cock sucking";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] as [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face].",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you rapidly bob your head up and down, letting out a muffled [pc.moan] as [npc.she] eagerly thrusts [npc.her] [npc.hips] into your [pc.face] in response.",
							"Rapidly bobbing your head up and down, you eagerly suck [npc.name]'s [npc.cock+], causing [npc.herHim] to frantically buck [npc.her] [npc.hips] into your [pc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobsVerb] in distress.",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you rapidly bob your head up and down,"
									+ " holding [npc.herHim] in place as [npc.she] desperately [npc.sobsVerb] and struggles against your unwanted oral attention.",
							"Rapidly bobbing your head up and down, you eagerly suck [npc.name]'s [npc.cock+], holding [npc.herHim] in place as [npc.she] [npc.sobs] and begs for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily push your head forwards, wrapping your [pc.lips+] around [npc.name]'s [npc.cock+] and drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you rapidly bob your head up and down, drawing [npc.a_moan+] from [npc.her] mouth.",
							"Rapidly bobbing your head up and down, you eagerly suck [npc.name]'s [npc.cock+], causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
					
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.name]'s [npc.cock+] as deep down your throat as you can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam your head forwards, taking [npc.name]'s [npc.cock+] deep down your throat as [npc.she] desperately bucks [npc.her] [npc.hips] into your [pc.face].",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+],"
									+ " you slam your head forwards and deep throat [npc.herHim], letting out a muffled [pc.moan] as [npc.she] eagerly grinds [npc.her] [npc.hips] into your [pc.face].",
							"Roughly slamming your head forwards, you take [npc.name]'s [npc.cock+] deep down your throat, causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly bucks [npc.her] [npc.hips] forwards in response."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam your head forwards, taking [npc.name]'s [npc.cock+] deep down your throat and preventing [npc.herHim] from pulling away as [npc.she] struggles and [npc.sobs] in distress.",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+],"
									+ " you slam your head forwards and deep throat [npc.herHim], letting out a muffled [pc.moan] [npc.she] desperately [npc.sobsVerb] and struggles against your unwanted oral attention.",
							"Roughly slamming your head forwards, you take [npc.name]'s [npc.cock+] deep down your throat, holding [npc.herHim] in place as [npc.she] [npc.sobsVerb] and begs for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly slam your head forwards, taking [npc.name]'s [npc.cock+] deep down your throat and drawing [npc.a_moan+] from [npc.her] mouth.",
							"Wrapping your [pc.lips+] around [npc.name]'s [npc.cock+], you slam your head forwards and deep throat [npc.herHim], drawing [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Roughly slamming your head forwards, you take [npc.name]'s [npc.cock+] deep down your throat, causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
			}
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.SUB_RESISTING,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull [npc.name]'s [npc.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You let out a muffled [pc.sob], squirming and struggling in distress as [npc.name] holds you in place, slowly sliding [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
						"With [pc.a_sob+], you try to push [npc.name] away, your protestations coming out in gargled bursts as [npc.she] continues gently pushing [npc.her] [npc.cock+] down your throat.",
						"You feel tears running down your cheeks as you weakly try to push [npc.name] away, [pc.sobbing] in distress as [npc.she] continues gently sliding [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out a muffled [pc.sob], squirming and struggling in distress as [npc.name] forcefully holds you in place, roughly pumping [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_sob+], you try to push [npc.name] away, your protestations coming out in gargled bursts as [npc.she] continues roughly slamming [npc.her] [npc.cock+] down your throat.",
							"You feel tears running down your cheeks as you weakly try to push [npc.name] away, [pc.sobbing] in distress as [npc.she] continues roughly thrusting [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out a muffled [pc.sob], squirming and struggling in distress as [npc.name] holds you in place, eagerly sliding [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_sob+], you try to push [npc.name] away, your protestations coming out in gargled bursts as [npc.she] continues eagerly pushing [npc.her] [npc.cock+] down your throat.",
							"You feel tears running down your cheeks as you weakly try to push [npc.name] away, [pc.sobbing] in distress as [npc.she] continues eagerly sliding [npc.her] [npc.cock+] in and out of your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static SexAction PLAYER_GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Suck cock";
		}

		@Override
		public String getActionDescription() {
			return "Continue sucking [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You let out [pc.a_moan+], obediently holding still as [npc.name] slowly slides [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
						"With [pc.a_moan+], you happily continue giving [npc.name] a blowjob, obediently holding still as [npc.she] continues gently pushing [npc.her] [npc.cock+] down your throat.",
						"You let out a muffled [pc.moan] as you obediently hold still, allowing [npc.name] to continue gently sliding [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.a_moan+], obediently holding still as [npc.name] roughly pumps [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_moan+], you happily continue giving [npc.name] a blowjob, obediently holding still as [npc.she] continues roughly slamming [npc.her] [npc.cock+] down your throat.",
							"You let out a muffled [pc.moan] as you obediently hold still, allowing [npc.name] to continue roughly thrusting [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.a_moan+], obediently holding still as [npc.name] eagerly slides [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_moan+], you happily continue giving [npc.name] a blowjob, obediently holding still as [npc.she] continues eagerly thrusting [npc.her] [npc.cock+] down your throat.",
							"You let out a muffled [pc.moan] as you obediently hold still, allowing [npc.name] to continue eagerly pumping [npc.her] [npc.cock+] in and out of your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc.name]'s [npc.cock+] as deep down your throat as you can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You let out [pc.a_moan+], eagerly bobbing your head up and down as [npc.name] slowly slides [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
						"With [pc.a_moan+], you enthusiastically bob your head up and down, loving every moment of this as [npc.name] continues gently pushing [npc.her] [npc.cock+] down your throat.",
						"You let out a muffled [pc.moan] as you energetically bob your head up and down, helping [npc.name] to continue gently sliding [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.a_moan+], eagerly bobbing your head up and down as [npc.name] roughly pumps [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_moan+], you enthusiastically bob your head up and down, loving every moment of this as [npc.name] continues roughly slamming [npc.her] [npc.cock+] down your throat.",
							"You let out a muffled [pc.moan] as you energetically bob your head up and down, helping [npc.name] to continue roughly thrusting [npc.her] [npc.cock+] in and out of your mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You let out [pc.a_moan+], energetically bobbing your head up and down as [npc.name] eagerly slides [npc.her] [npc.cock+] back and forth past your [pc.lips+].",
							"With [pc.a_moan+], you enthusiastically bob your head up and down, loving every moment of this as [npc.name] continues eagerly thrusting [npc.her] [npc.cock+] down your throat.",
							"You let out a muffled [pc.moan] as you energetically bob your head up and down, helping [npc.name] to continue eagerly pumping [npc.her] [npc.cock+] in and out of your mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_BLOWJOB_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || Sex.isPlayerDom(); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.name]'s [npc.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc.name]'s [npc.cock+] down your throat one last time, you then pull your head back, putting a quick end to [npc.her] blowjob.",
							"Slamming your [pc.face] into [npc.name]'s groin, you force [npc.her] [npc.cock+] deep down your throat, before pulling completely back and letting [npc.herHim] slip out of your mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.cock+] out of your mouth, you let out [pc.a_moan+] as you put an end to the blowjob.",
							"With [pc.a_moan+], you pull your head back, sliding [npc.name]'s [npc.cock+] fully out of your mouth."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+], struggling against you as [npc.she] pleads for you to let [npc.herHim] go.",
							" Tears stream down [npc.her] cheeks as [npc.she] tries to struggle out of your grip, letting out [npc.a_sob+] before begging for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], revealing [npc.her] desire for you to continue sucking [npc.her] [npc.cock+].",
							" [npc.She] [npc.moansVerb] as you withdraws from [npc.her] groin, betraying [npc.her] desire to feel your [pc.lips+] wrapped around [npc.her] [npc.cock+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
