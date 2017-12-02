package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.84
 * @author Innoxia
 */
public class PlayerPenisMouth {
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
	
	public static final SexAction PLAYER_COCK_SLAP = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_ROUGH,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Cock slap";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock] out of [npc.name]'s mouth and slap [npc.her] face with it.";
		}

		@Override
		public String getDescription() {

			if(Sex.getPosition()==SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) {
				
				return UtilText.returnStringAtRandom(
						"Pulling your [pc.hips] back, you slide your [pc.cock+] out of [npc.name]'s mouth."
								+ " Before [npc.she] can react, you slap your hard shaft against [npc.her] cheek, splattering saliva "
								+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PLAYER).contains(LubricationType.PLAYER_PRECUM)?"and precum ":"")+"across [npc.her] face, before thrusting your [pc.cock+] back down [npc.her] throat.",
								
						"Stepping back, you slide your [pc.cock+] free from [npc.name]'s mouth, and then proceed to slap the saliva-coated [pc.cockHead] against [npc.her] [npc.face].",
						
						"You slide your [pc.cock+] out from [npc.name]'s mouth, and as [npc.she] looks up, you slap your hard shaft against [npc.her] [npc.face],"
							+ " leaving a streak of "+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PLAYER).contains(LubricationType.PLAYER_PRECUM)?"cummy ":"")
							+"saliva drooling down [npc.her] cheek, before forcing your length back down [npc.her] throat.",
								
						"Quickly pulling your [pc.cock+] out from [npc.name]'s mouth, you hold the base in one hand while holding [npc.her] head still with the other and start slapping your throbbing length against [npc.her] cheeks.",
							
						"Grabbing [npc.name]'s head, you pull back, drawing your [pc.cock+] out from [npc.her] mouth before starting to slap its slimy length against [npc.her] cheeks.");
				
			} else {
			
				return UtilText.returnStringAtRandom(
						"Pulling your [pc.hips] back, you slide your [pc.cock+] out of [npc.name]'s mouth."
							+ " Before [npc.she] can react, you slap your hard shaft against [npc.her] cheek, splattering saliva "
							+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PLAYER).contains(LubricationType.PLAYER_PRECUM)?"and precum ":"")+"across [npc.her] face, before thrusting your [pc.cock+] back down [npc.her] throat.",
								
						"Pulling back, you slide your [pc.cock+] free from [npc.name]'s mouth, and with [pc.a_moan+], you proceed to slap the saliva-coated [pc.cockHead] against [npc.her] [npc.face].",
						
						"You slide your [pc.cock+] out from [npc.name]'s mouth, and, grinning to yourself, you then slap your hard shaft against [npc.her] face,"
							+ " leaving a streak of "+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PLAYER).contains(LubricationType.PLAYER_PRECUM)?"cummy ":"")
							+"saliva drooling down [npc.her] cheek, before forcing your [pc.cock] back down [npc.her] throat.",
								
						"Quickly pulling your [pc.hips+] back, you draw your [pc.cock+] out from [npc.name]'s mouth before starting to slap its slimy length against [npc.her] cheeks.");
			
			}
		}
		
	};
	
	public static final SexAction PLAYER_FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().isInternalTesticles() && Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Balls focus";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to pay some attention to your [pc.balls+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing your [pc.hips] back, you allow your [pc.cock+] to slide out of [npc.name]'s mouth, before shuffling about until your [pc.balls+] are gently pressing against [npc.her] [npc.lips+].",
							"Sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself so that your [pc.balls+] are gently pressing against [npc.her] [npc.lips+].",
							"You slide your [pc.cock+] out of [npc.name]'s mouth, before repositioning yourself so that [npc.her] [npc.lips+] are gently pressed against your [pc.balls+].",
							"Quickly sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself until you're gently pressing your [pc.balls+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing your [pc.hips] back, you allow your [pc.cock+] to slide out of [npc.name]'s mouth, before shuffling about until your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"Sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself so that your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"You slide your [pc.cock+] out of [npc.name]'s mouth, before repositioning yourself so that [npc.her] [npc.lips+] are pressed against your [pc.balls+].",
							"Quickly sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself until you're forcing your [pc.balls+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling your [pc.hips] back, you allow your [pc.cock+] to slide out of [npc.name]'s mouth, before shuffling about until your [pc.balls+] are roughly grinding against [npc.her] [npc.lips+].",
							"Sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself so that your [pc.balls+] are roughly grinding against [npc.her] [npc.lips+].",
							"You slide your [pc.cock+] out of [npc.name]'s mouth, before repositioning yourself so that [npc.her] [npc.lips+] are roughly grinding against your [pc.balls+].",
							"Quickly sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself until you're roughly forcing your [pc.balls+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing your [pc.hips] back, you allow your [pc.cock+] to slide out of [npc.name]'s mouth, before shuffling about until your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"Sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself so that your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"You slide your [pc.cock+] out of [npc.name]'s mouth, before repositioning yourself so that [npc.her] [npc.lips+] are pressed against your [pc.balls+].",
							"Quickly sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself until you're forcing your [pc.balls+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Drawing your [pc.hips] back, you allow your [pc.cock+] to slide out of [npc.name]'s mouth, before shuffling about until your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"Sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself so that your [pc.balls+] are pressing against [npc.her] [npc.lips+].",
							"You slide your [pc.cock+] out of [npc.name]'s mouth, before repositioning yourself so that [npc.her] [npc.lips+] are pressed against your [pc.balls+].",
							"Quickly sliding your [pc.cock+] out of [npc.name]'s mouth, you reposition yourself until you're forcing your [pc.balls+] against [npc.her] [npc.lips+]."));
					break;
				default:
					break;
			
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Slowly sliding [npc.her] [npc.tongue+] out, [npc.she] starts to gently lick and kiss your [pc.balls+], causing [pc.a_moan+] to drift out from between your [pc.lips+].",
							" [npc.She] gently starts to kiss and lick your [pc.balls+], causing you to let out [pc.a_moan+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc.her] [npc.tongue+] out, [npc.she] greedily starts to lick and kiss your [pc.balls+], causing [pc.a_moan+] to drift out from between your [pc.lips+].",
							" [npc.She] eagerly starts to kiss and lick your [pc.balls+], causing you to let out [pc.a_moan+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Sliding [npc.her] [npc.tongue+] out, [npc.she] starts to roughly lick and kiss your [pc.balls+], causing [pc.a_moan+] to drift out from between your [pc.lips+].",
							" [npc.She] roughly starts to kiss and lick your [pc.balls+], causing you to let out [pc.a_moan+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc.her] [npc.tongue+] out, [npc.she] greedily starts to lick and kiss your [pc.balls+], causing [pc.a_moan+] to drift out from between your [pc.lips+].",
							" [npc.She] eagerly starts to kiss and lick your [pc.balls+], causing you to let out [pc.a_moan+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Darting [npc.her] [npc.tongue+] out, [npc.she] starts to lick and kiss your [pc.balls+], causing [pc.a_moan+] to drift out from between your [pc.lips+].",
							" [npc.She] starts to kiss and lick your [pc.balls+], causing you to let out [pc.a_moan+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] desperately tries to pull away, letting out [npc.a_sob+] as your [pc.balls+] continue grinding against [npc.her] [npc.lips+].",
							" [npc.She] lets out a muffled [npc.sob], trying desperately to pull away from your [pc.balls+] as [npc.she] struggles against you."));
					break;
				default:
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_SUCK_BALLS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().isInternalTesticles() && Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick balls";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss [pc.name]'s [pc.balls] for a while.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting your [pc.cock+] slip completely out of [npc.her] mouth, [npc.name] moves [npc.her] head down and starts to gently lick and suck on your [pc.balls+].",
							"[npc.Name] lets your [pc.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start gently licking and kissing your [pc.balls+].",
							"Sliding your [pc.cock+] from [npc.her] mouth, [npc.name] moves [npc.her] head down to start gently kissing and licking your [pc.balls+].",
							"First sliding your [pc.cock+] out from [npc.her] mouth, [npc.name] moves [npc.her] head down, before starting to gently kiss and nuzzle into your [pc.balls+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting your [pc.cock+] slip completely out of [npc.her] mouth, [npc.name] moves [npc.her] head down and starts to eagerly lick and suck on your [pc.balls+].",
							"[npc.Name] lets your [pc.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start desperately licking and kissing your [pc.balls+].",
							"Sliding your [pc.cock+] from [npc.her] mouth, [npc.name] moves [npc.her] head down to start eagerly kissing and licking your [pc.balls+].",
							"First sliding your [pc.cock+] out from [npc.her] mouth, [npc.name] moves [npc.her] head down, before starting to eagerly kiss and nuzzle into your [pc.balls+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting your [pc.cock+] slip completely out of [npc.her] mouth, [npc.name] moves [npc.her] head down and starts to roughly lick and suck on your [pc.balls+].",
							"[npc.Name] lets your [pc.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start roughly licking and kissing your [pc.balls+].",
							"Sliding your [pc.cock+] from [npc.her] mouth, [npc.name] moves [npc.her] head down to start roughly kissing and licking your [pc.balls+].",
							"First sliding your [pc.cock+] out from [npc.her] mouth, [npc.name] moves [npc.her] head down, before starting to roughly kiss and nuzzle into your [pc.balls+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting your [pc.cock+] slip completely out of [npc.her] mouth, [npc.name] moves [npc.her] head down and starts to eagerly lick and suck on your [pc.balls+].",
							"[npc.Name] lets your [pc.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start desperately licking and kissing your [pc.balls+].",
							"Sliding your [pc.cock+] from [npc.her] mouth, [npc.name] moves [npc.her] head down to start eagerly kissing and licking your [pc.balls+].",
							"First sliding your [pc.cock+] out from [npc.her] mouth, [npc.name] moves [npc.her] head down, before starting to eagerly kiss and nuzzle into your [pc.balls+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting your [pc.cock+] slip completely out of [npc.her] mouth, [npc.name] moves [npc.her] head down and starts to lick and suck on your [pc.balls+].",
							"[npc.Name] lets your [pc.cock+] slide out of [npc.her] mouth, before moving [npc.her] [npc.lips+] down to start licking and kissing your [pc.balls+].",
							"Sliding your [pc.cock+] from [npc.her] mouth, [npc.name] moves [npc.her] head down to start kissing and licking your [pc.balls+].",
							"First sliding your [pc.cock+] out from [npc.her] mouth, [npc.name] moves [npc.her] head down, before starting to kiss and nuzzle into your [pc.balls+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_LICK_HEAD = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick head";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss the [pc.cockHead+] of [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String barbedSpecial = "", flaredSpecial = "";
			
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
				barbedSpecial = "[npc.Name] slides [npc.her] head back, letting out a muffled [npc.moan] as the barbs lining your [pc.cock] rake their way up [npc.her] throat."
									+ " Leaving just the [pc.cockHead+] pushed past [npc.her] [npc.lips+], [npc.she] then starts to passionately kiss and suck the tip of your [pc.cock+].";
			}
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
				flaredSpecial="Sliding [npc.her] head back, [npc.name] allows your [pc.cock+] to slip almost completely out of [npc.her] mouth,"
									+ " leaving just the wide, flared head pushed past [npc.her] [npc.lips+], which [npc.she] starts to passionately kiss and lick.";
			}
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
							barbedSpecial, flaredSpecial,
							
						"With [npc.a_moan+], [npc.name] pulls [npc.her] head back, wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], before starting to lick and kiss it.",
					
						"[npc.Name] pulls [npc.her] head back, letting out [npc.a_moan+] as [npc.she] starts concentrating on sucking and kissing the [pc.cockHead+] of your [pc.cock].",
	
						"Pulling [npc.her] head back a little, [npc.name] lets most of your [pc.cock+] slide out of [npc.her] mouth, and,"
							+ " focusing on using [npc.her] [npc.tongue+], [npc.she] starts licking and kissing the [pc.cockHead+] that's left poking past [npc.her] [npc.lips+]."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_HERM_FUN = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA) && Sex.isPlayerFreeVagina();
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isFeminine()) {
				return "Futa fun";
			} else {
				return "Herm fun";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pleasure both [pc.name]'s [pc.cock+] and [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives the [pc.cockHead] of your [pc.cock+] a gentle suck, before pulling back and starting to slowly lick and kiss your [pc.pussy+].",
							"Deciding to give your [pc.pussy] some attention, [npc.name] delivers a long, slow lick up the length of your [pc.cock+],"
									+ " before pressing forwards and gently sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
							"Delivering a gentle kiss to the [pc.cockHead] of your [pc.cock+], [npc.name] moves down, bringing [npc.her] [npc.lips+] to your [pc.pussy+] before slowly pushing [npc.her] [npc.tongue+] between your folds."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives the [pc.cockHead] of your [pc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss your [pc.pussy+].",
							"Deciding to give your [pc.pussy] some attention, [npc.name] delivers a long, wet lick up the length of your [pc.cock+],"
									+ " before pressing forwards and eagerly darting [npc.her] [npc.tongue+] into your [pc.pussy+].",
							"Delivering a wet kiss to the [pc.cockHead] of your [pc.cock+], [npc.name] moves down, bringing [npc.her] [npc.lips+] to your [pc.pussy+] before eagerly darting [npc.her] [npc.tongue+] between your folds."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives the [pc.cockHead] of your [pc.cock+] a wet suck, before pulling back and starting to roughly lick and kiss your [pc.pussy+].",
							"Deciding to give your [pc.pussy] some attention, [npc.name] delivers a long, rough lick up the length of your [pc.cock+],"
									+ " before pressing forwards and dominantly thrusting [npc.her] [npc.tongue+] into your [pc.pussy+].",
							"Delivering a rough kiss to the [pc.cockHead] of your [pc.cock+], [npc.name] moves down, bringing [npc.her] [npc.lips+] to your [pc.pussy+] before dominantly thrusting [npc.her] [npc.tongue+] between your folds."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives the [pc.cockHead] of your [pc.cock+] a wet suck, before pulling back and starting to eagerly lick and kiss your [pc.pussy+].",
							"Deciding to give your [pc.pussy] some attention, [npc.name] delivers a long, wet lick up the length of your [pc.cock+],"
									+ " before pressing forwards and eagerly darting [npc.her] [npc.tongue+] into your [pc.pussy+].",
							"Delivering a wet kiss to the [pc.cockHead] of your [pc.cock+], [npc.name] moves down, bringing [npc.her] [npc.lips+] to your [pc.pussy+] before eagerly darting [npc.her] [npc.tongue+] between your folds."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives the [pc.cockHead] of your [pc.cock+] a wet suck, before pulling back and starting to lick and kiss your [pc.pussy+].",
							"Deciding to give your [pc.pussy] some attention, [npc.name] delivers a long, wet lick up the length of your [pc.cock+],"
									+ " before pressing forwards and darting [npc.her] [npc.tongue+] into your [pc.pussy+].",
							"Delivering a wet kiss to the [pc.cockHead] of your [pc.cock+], [npc.name] moves down, bringing [npc.her] [npc.lips+] to your [pc.pussy+] before darting [npc.her] [npc.tongue+] between your folds."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan], gently pushing your [pc.pussy+] against [npc.her] [npc.lips+] before [npc.she] decides to shift [npc.her] focus back to your [pc.cock+] once again.",
							" Gently thrusting your [pc.pussy+] into [npc.her] [npc.face], you let out a soft [pc.moan], before [npc.she] decides to move back to focusing on your [pc.cock+].",
							" Softly [pc.moaning], you gently buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back to your [pc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly pushing your [pc.pussy+] against [npc.her] [npc.lips+] before [npc.she] decides to shift [npc.her] focus back to your [pc.cock+] once again.",
							" Eagerly thrusting your [pc.pussy+] into [npc.her] [npc.face], you let out [pc.a_moan+], before [npc.she] decides to move back to focusing on your [pc.cock+].",
							" [pc.Moaning+], you eagerly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back to your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+] before [npc.she] decides to shift [npc.her] focus back to your [pc.cock+] once again.",
							" Roughly grinding your [pc.pussy+] into [npc.her] [npc.face], you let out [pc.a_moan+], before [npc.she] decides to move back to focusing on your [pc.cock+].",
							" [pc.Moaning+], you roughly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back to your [pc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly pushing your [pc.pussy+] against [npc.her] [npc.lips+] before [npc.she] decides to shift [npc.her] focus back to your [pc.cock+] once again.",
							" Eagerly thrusting your [pc.pussy+] into [npc.her] [npc.face], you let out [pc.a_moan+], before [npc.she] decides to move back to focusing on your [pc.cock+].",
							" [pc.Moaning+], you eagerly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back to your [pc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], pushing your [pc.pussy+] against [npc.her] [npc.lips+] before [npc.she] decides to shift [npc.her] focus back to your [pc.cock+] once again.",
							" Thrusting your [pc.pussy+] into [npc.her] [npc.face], you let out [pc.a_moan+], before [npc.she] decides to move back to focusing on your [pc.cock+].",
							" [pc.Moaning+], you buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back to your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.Sobbing] and squirming in discomfort, you desperately try to pull away from [npc.herHim], begging to be left alone as [npc.she] shifts [npc.her] attention back down to your [pc.cock+] once again.",
							" With [pc.a_sob+], you try to push [npc.herHim] away, squirming in discomfort as [npc.she] moves back to focusing [npc.her] attention on your [pc.cock+].",
							" With tears streaming down your [pc.face], you let out [pc.a_sob+] as [npc.she] moves back down to focusing on your [pc.cock+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_BLOWJOB_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Get blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [pc.cock+] into [npc.name]'s mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) {
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc.name]'s head, you line the [pc.cockHead+] of your [pc.cock] up to [npc.her] [npc.lips+],"
										+ " before gently pushing your [pc.hips] forwards and sliding your [pc.cock+] into [npc.her] mouth.",
								"Reaching down to hold [npc.name]'s head, you push the [pc.cockHead+] of your [pc.cock] against [npc.her] [npc.lips+],"
										+ " before slowly bucking your [pc.hips] into [npc.her] [npc.face] and gently sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc.name]'s head, you line the [pc.cockHead+] of your [pc.cock] up to [npc.her] [npc.lips+],"
										+ " before eagerly pushing your [pc.hips] forwards and sliding your [pc.cock+] into [npc.her] mouth.",
								"Reaching down to hold [npc.name]'s head, you push the [pc.cockHead+] of your [pc.cock] against [npc.her] [npc.lips+],"
										+ " before eagerly bucking your [pc.hips] into [npc.her] [npc.face] and sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc.name]'s head, you line the [pc.cockHead+] of your [pc.cock] up to [npc.her] [npc.lips+],"
										+ " before roughly slamming your [pc.hips] forwards and forcing your [pc.cock+] into [npc.her] mouth.",
								"Reaching down to hold [npc.name]'s head, you push the [pc.cockHead+] of your [pc.cock] against [npc.her] [npc.lips+],"
										+ " before roughly slamming your [pc.hips] into [npc.her] [npc.face] and forcing your [pc.cock+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you slowly lower your [pc.cock+] down to [npc.name]'s mouth,"
										+ " before gently pushing the [pc.cockHead+] past [npc.her] [npc.lips+] and collapsing down onto [npc.her] [npc.face+].",
								"Gently lowering your [pc.cock+] down to [npc.name]'s mouth, you slowly allow your [pc.legs] to give out from under you"
										+ " as you force the [pc.cockHead+] past [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you quickly drop your [pc.cock+] down to [npc.name]'s mouth,"
										+ " before eagerly pushing the [pc.cockHead+] past [npc.her] [npc.lips+] and collapsing down onto [npc.her] [npc.face+].",
								"Quickly dropping your [pc.cock+] down to [npc.name]'s mouth, you allow your [pc.legs] to give out from under you"
										+ " as you eagerly force the [pc.cockHead+] past [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you roughly grind your [pc.cock+] down against [npc.name]'s mouth,"
										+ " before forcefully pushing the [pc.cockHead+] past [npc.her] [npc.lips+] and collapsing down onto [npc.her] [npc.face+].",
								"Roughly grinding your [pc.cock+] down against [npc.name]'s mouth, you allow your [pc.legs] to give out from under you"
										+ " as you force the [pc.cockHead+] past [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.lips+], you gently push your [pc.hips] forwards and slide your [pc.cock+] into [npc.her] mouth.",
								"Pushing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.lips+], you slowly buck your [pc.hips] into [npc.her] [npc.face], gently sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.lips+], you eagerly push your [pc.hips] forwards and slide your [pc.cock+] into [npc.her] mouth.",
								"Pushing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.lips+], you eagerly buck your [pc.hips] into [npc.her] [npc.face], sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.lips+], you roughly slam your [pc.hips] forwards and force your [pc.cock+] into [npc.her] mouth.",
								"Grinding the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.lips+], you roughly slam your [pc.hips] into [npc.her] [npc.face], forcing your [pc.cock+] into [npc.her] mouth."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.lips+], you eagerly push your [pc.hips] forwards and slide your [pc.cock+] into [npc.her] mouth.",
								"Pushing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.lips+], you eagerly buck your [pc.hips] into [npc.her] [npc.face], sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [pc.cockHead+] of your [pc.cock] up to [npc.name]'s [npc.lips+], you push your [pc.hips] forwards and slide your [pc.cock+] into [npc.her] mouth.",
								"Pushing the [pc.cockHead+] of your [pc.cock] against [npc.name]'s [npc.lips+], you buck your [pc.hips] into [npc.her] [npc.face], sliding your [pc.cock+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			}
			
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a muffled [npc.moan], slowly sliding [npc.her] head forwards as [npc.she] starts gently sucking your [pc.cock+].",
							" With a soft, and very muffled, [npc.moan], [npc.she] starts gently sliding [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as [npc.she] starts giving you a blowjob."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly sliding [npc.her] head forwards as [npc.she] starts happily sucking your [pc.cock+].",
							" With an eager, and very muffled, [npc.moan], [npc.she] starts happily sliding [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as [npc.she] starts giving you a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], roughly slamming [npc.her] head forwards as [npc.she] forces your [pc.cock+] deep down [npc.her] throat.",
							" With an eager, and very muffled, [npc.moan], [npc.she] forcefully pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as [npc.she] starts giving you a rough blowjob."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly sliding [npc.her] head forwards as [npc.she] starts happily sucking your [pc.cock+].",
							" With an eager, and very muffled, [npc.moan], [npc.she] starts happily sliding [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as [npc.she] starts giving you a blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], sliding [npc.her] head forwards as [npc.she] starts sucking your [pc.cock+].",
							" With a muffled [npc.moan], [npc.she] starts sliding [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as [npc.she] starts giving you a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a muffled [npc.sob], gargling and choking on your [pc.cock+] as [npc.she] frantically tries to pull [npc.her] head away from your groin.",
							" With a muffled [npc.sob], [npc.she] frantically tries to pull away from your [pc.cock+], gargling and choking as [npc.she] squirms and struggles against you."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
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
			return "Hold still and let [npc.name] suck your [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sliding your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by eagerly bobbing [npc.her] head up and down into your groin.",
							"You slowly push your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as you feel [npc.herHim] respond by eagerly wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Gently bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out a soft [pc.moan] as [npc.she] responds by happily sliding [npc.her] head up and down, all too eager to continue giving you a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sliding your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.sob] as [npc.she] responds by desperately trying to pull [npc.her] head away from your groin.",
							"You slowly push your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as you feel [npc.herHim] respond by frantically trying to pull [npc.her] head away from your [pc.cock+].",
							"Gently bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out a soft [pc.moan] as [npc.she] responds by desperately trying to pull away from your [pc.cock+],"
									+ " letting out a muffled [npc.sob] as tears well up in [npc.her] [npc.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sliding your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by bobbing [npc.her] head up and down into your groin.",
							"You slowly push your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as you feel [npc.herHim] respond by wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Gently bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out a soft [pc.moan] as [npc.she] responds by sliding [npc.her] head up and down, obediently continuing to give you a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_NORMAL,
			null) {

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
			return "Push your [pc.cock+] into [npc.name]'s face to encourage [npc.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by eagerly bobbing [npc.her] head up and down into your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by eagerly wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by happily sliding [npc.her] head up and down, all too eager to continue giving you a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.sob] as [npc.she] responds by desperately trying to pull [npc.her] head away from your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by frantically trying to pull [npc.her] head away from your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by desperately trying to pull away from your [pc.cock+],"
									+ " letting out a muffled [npc.sob] as tears well up in [npc.her] [npc.eyes]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by bobbing [npc.her] head up and down into your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by sliding [npc.her] head up and down, obediently continuing to give you a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.DOM_ROUGH,
			null) {

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
			return "Roughly thrust your [pc.cock+] down [npc.name]'s throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(Sex.getPosition()==SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial = "With a sudden, violent thrust forwards, you bury your [pc.cock+] deep down [npc.name]'s throat."
									+ " Holding [npc.her] head in place with both [pc.hands], you then proceed to start roughly fucking [npc.her] [npc.face],"
									+ " and you see tears streaming from [npc.her] [npc.eyes] as you slam your knot repeatedly against [npc.her] [npc.lips+].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial = "With a sudden, violent thrust forwards, you bury your [pc.cock+] deep down [npc.name]'s throat."
									+ " Holding [npc.her] head in place with both [pc.hands], you then proceed to start roughly fucking [npc.her] [npc.face],"
									+ " and you see tears streaming from [npc.her] [npc.eyes] as you feel the barbs lining your shaft repeatedly raking up the sides of [npc.her] throat.";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial = "With a sudden, violent thrust forwards, you bury your [pc.cock+] deep down [npc.name]'s throat."
									+ " Holding [npc.her] head in place with both [pc.hands], you then proceed to start roughly fucking [npc.her] [npc.face],"
									+ " and you see tears streaming from [npc.her] [npc.eyes] as your wide, flared head forces its way up and down [npc.her] throat.";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"You grab the sides of [npc.name]'s head, and before [npc.she] knows what's happening, you're roughly slamming your [pc.cock+] in and out of [npc.her] facial fuck-hole.",
						
								"Letting out [pc.a_moan+], you slam your [pc.cock+] all the way down [npc.name]'s throat."
									+ " As [npc.she] blinks back tears, you start rapidly bucking your hips back and forth, holding [npc.her] head in place with both hands as you ruthlessly fuck [npc.her] [npc.face].",
								
								"Grabbing the sides of [npc.name]'s head, you roughly pull [npc.herHim] into your groin, sinking your [pc.cock+] deep down [npc.her] throat before starting to ruthlessly fuck [npc.her] [npc.face].",
										
								"With a forceful thrust, you hilt your [pc.cock+] deep down [npc.name]'s throat."
									+ " As a slimy stream of saliva "+(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PLAYER).contains(LubricationType.PLAYER_PRECUM)?"and precum ":"")
									+"drools from the corners of [npc.her] mouth, you buck back, letting [npc.herHim] gasp for air for a brief moment before starting to aggressively fuck [npc.her] [npc.face]."));
			
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				
				String knottedSpecial = "", barbedSpecial = "", flaredSpecial = "";
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					knottedSpecial = "Spreading your knees out on either side of [npc.name]'s head, you violently thrust downwards, burying your [pc.cock+] deep down [npc.her] throat."
										+ " Grinding your fat knot against [npc.her] [npc.lips+] for moment, you then proceed to start roughly fucking [npc.her] [npc.face+],"
										+ " grinning as you hear [npc.herHim] [npc.moaning] in desperation as [npc.she] struggles to breathe,"
										+ " [npc.her] pitiful cries being accompanied by the wet slapping sounds that your fat knot makes at it repeatedly slams against [npc.her] [npc.lips].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
					barbedSpecial = "Spreading your knees out on either side of [npc.name]'s head, you violently thrust downwards, burying your [pc.cock+] deep down [npc.her] throat."
										+ " Grinding the base against [npc.her] [npc.lips+] for moment, you then proceed to start roughly fucking [npc.her] [npc.face+],"
										+ " grinning as you hear [npc.herHim] [npc.moaning] in desperation as [npc.she] struggles to breathe,"
										+ " squirming about beneath you as you feel [npc.her] throat being stretched out by the wide, flared head of your [pc.cock+].";
				}
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					flaredSpecial = "Spreading your knees out on either side of [npc.name]'s head, you violently thrust downwards, burying your [pc.cock+] deep down [npc.her] throat."
										+ " Grinding the base against [npc.her] [npc.lips+] for moment, you then proceed to start roughly fucking [npc.her] [npc.face+],"
										+ " grinning as you hear [npc.herHim] [npc.moaning] in desperation as [npc.she] struggles to breathe,"
										+ " squirming about beneath you as you feel [npc.her] throat being raked by the series of barbs that line the sides of your [pc.cock+].";
				}
				
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								knottedSpecial, barbedSpecial, flaredSpecial,
								
								"You spread your knees out on either side of [npc.name]'s head, and before [npc.she] has time to realise what's about to happen,"
										+ " you roughly start slamming your [pc.cock+] in and out of [npc.her] mouth, letting out a series of [pc.moans+] as you ruthlessly fuck [npc.her] [npc.face+].",
								
								"Letting out [pc.a_moan+], you slam your [pc.cock+] all the way down [npc.name]'s throat."
									+ " As [npc.she] blinks back tears, you start rapidly thrusting your [pc.hips] up and down, letting out more [pc.moans+] as you ruthlessly fuck [npc.her] [npc.face+].",
								
								"Dropping down onto [npc.name]'s face, you roughly slam your [pc.cock+] deep down [npc.her] throat,"
										+ " letting out [pc.a_moan+] before starting to violently thrust your [pc.hips] up and down as you ruthlessly fuck [npc.her] [npc.face].",
										
								"With a forceful thrust, you hilt your [pc.cock+] deep down [npc.name]'s throat."
									+ " As a slimy stream of "+(Sex.getWetOrificeTypes().get(OrificeType.MOUTH_PLAYER).contains(LubricationType.PARTNER_PRECUM)?"cummy ":"")
									+"saliva drools from the corners of [npc.her] mouth, you lift yourself up,"
									+ " letting [npc.herHim] gasp for air for a brief moment before sinking down once more and starting to aggressively fuck [npc.her] [npc.face]."));
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by obediently relaxing [npc.her] throat,"
										+ " allowing you to brutally fuck [npc.her] [npc.face].",
								"You roughly slam your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by obediently relaxing [npc.her] throat,"
										+ " clearly eager for you to continue brutally fucking [npc.her] [npc.face].",
								"Violently slamming your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by happily relaxing [npc.her] throat,"
										+ " all too eager for your [pc.cock+] to brutally fuck [npc.her] [npc.face]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by frantically trying to pull away,"
										+ " tears streaming from [npc.her] [npc.eyes] as you brutally fuck [npc.her] [npc.face].",
								"You roughly slam your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by frantically trying to pull away from your groin,"
										+ " [npc.her] muffled [npc.sobs] only serving to encourage you as you brutally fuck [npc.her] [npc.face].",
								"Violently slamming your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by frantically trying to pull away from you,"
										+ " [npc.sobbing] and squirming in distress as you brutally fuck [npc.her] [npc.face]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by obediently holding still,"
										+ " allowing you to brutally fuck [npc.her] [npc.face].",
								"You roughly slam your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by obediently staying in position,"
										+ " allowing you to continue brutally fucking [npc.her] [npc.face].",
								"Violently slamming your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by holding [npc.her] head still,"
										+ " happy for your [pc.cock+] to brutally fuck [npc.her] [npc.face]."));
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
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
			return "Try to pull your [pc.cock] out of [npc.name]'s mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Letting out [pc.a_sob+], you frantically try to pull your hips away from [npc.name]'s face, but your efforts prove to be in vain as [npc.she] gently, but firmly, holds you in place,"
								+ " slowly sliding [npc.her] [npc.lips+] up and down the length of your [pc.cock+] as [npc.she] continues giving you an unwanted blowjob.",
						"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your [pc.cock+] out of [npc.name]'s mouth."
								+ " [npc.Her] grip proves to be too strong, and [npc.she] gently, but firmly, continues to suck your [pc.cock+] as you struggle against [npc.herHim].",
						"[pc.Sobbing+], you try to pull your [pc.cock+] out of [npc.name]'s mouth, but [npc.her] grip is too strong for you, and your struggles prove to be in vain as [npc.she] continues giving you a slow, gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [pc.a_sob+], you frantically try to pull your hips away from [npc.name]'s face, but your efforts prove to be in vain as [npc.she] roughly holds you in place,"
									+ " growling in a threatening manner as [npc.her] [npc.lips+] forcefully slide up and down the length of your [pc.cock+].",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your [pc.cock+] out of [npc.name]'s mouth."
									+ " [npc.Her] grip proves to be far too strong, and [npc.she] lets out a threatening growl as [npc.she] continues to suck your [pc.cock+].",
							"[pc.Sobbing+], you try to pull your [pc.cock+] out of [npc.name]'s mouth, but [npc.her] grip is too strong for you,"
									+ " and your struggles prove to be in vain as [npc.she] continues giving you a rough, forceful blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [pc.a_sob+], you frantically try to pull your hips away from [npc.name]'s face, but your efforts prove to be in vain as [npc.she] firmly holds you in place,"
									+ " eagerly sliding [npc.her] [npc.lips+] up and down the length of your [pc.cock+] as [npc.she] continues giving you an unwanted blowjob.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your [pc.cock+] out of [npc.name]'s mouth."
									+ " [npc.Her] grip proves to be too strong, and [npc.she] eagerly continues to suck your [pc.cock+] as you struggle against [npc.herHim].",
							"[pc.Sobbing+], you try to pull your [pc.cock+] out of [npc.name]'s mouth, but [npc.her] grip is too strong for you, and your struggles prove to be in vain as [npc.she] continues giving you an eager blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.SUB_NORMAL,
			null) {

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
			return "Push your [pc.cock+] into [npc.name]'s face to encourage [npc.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by slowly bobbing [npc.her] head up and down into your groin.",
							"Pushing your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by gently wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by slowly sliding [npc.her] head up and down, continuing to give you a gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a threatening growl as [npc.she] reminds you who's in charge, before roughly bobbing [npc.her] head up and down into your groin.",
							"As you push your [pc.hips] into [npc.name]'s [npc.face],"
									+ " [npc.she] growls out that [npc.she]'s the one in charge, before roughly clamping [npc.her] [npc.lips+] down around your [pc.cock+].",
							"Bucking your [pc.hips+] into [npc.name]'s [npc.face], [npc.she] lets out a threatening growl as [npc.she] reminds you who's in charge,"
									+ " before sliding [npc.her] head up and down, continuing to give you a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by eagerly bobbing [npc.her] head up and down into your groin.",
							"Pushing your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by eagerly wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by happily sliding [npc.her] head up and down, all too eager to continue giving you a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			SexPace.SUB_EAGER,
			null) {

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
			return "Eagerly thrust your [pc.hips] into [npc.name]'s face, forcing your [pc.cock+] down [npc.her] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by slowly bobbing [npc.her] head up and down into your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by gently wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by slowly sliding [npc.her] head up and down, continuing to give you a gentle blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a threatening growl as [npc.she] reminds you who's in charge, before roughly bobbing [npc.her] head up and down into your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face],"
									+ " letting out [pc.a_moan+] as [npc.she] growls out that [npc.she]'s the one in charge, before roughly clamping [npc.her] [npc.lips+] down around your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], [npc.she] lets out a threatening growl as [npc.she] reminds you who's in charge,"
									+ " before sliding [npc.her] head up and down, continuing to give you a rough blowjob."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily thrusting your [pc.cock+] past [npc.name]'s [npc.lips+], [npc.she] lets out a muffled [npc.moan] as [npc.she] responds by eagerly bobbing [npc.her] head up and down into your groin.",
							"You desperately push your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as you feel [npc.herHim] respond by eagerly wrapping [npc.her] [npc.lips+] around your [pc.cock+].",
							"Frantically bucking your [pc.hips+] into [npc.name]'s [npc.face], you let out [pc.a_moan+] as [npc.she] responds by happily sliding [npc.her] head up and down, all too eager to continue giving you a blowjob."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_BLOWJOB_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isPlayerDom(); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out of [npc.name]'s mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				UtilText.nodeContentSB.append("Using your knees to lift yourself up, you allow your [pc.cock+] to slide up and out of [npc.name]'s mouth,"
							+ " and you feel a slimy strand of saliva linking [npc.her] [npc.lips+] to the [pc.cockHead+] of your [pc.cock] for a brief moment, before breaking to fall down over [npc.her] [npc.face].");
				
			} else {
				switch(Sex.getSexPacePlayer()) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your [pc.cock+] down [npc.name]'s throat one last time, you then pull your [pc.hips] back, grinning as [npc.she] splutters and gasps for air.",
								"Slamming your [pc.hips] into [npc.name]'s [npc.face], you force your [pc.cock+] deep down [npc.her] throat, before pulling completely back and out of [npc.her] mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding your [pc.cock+] out of [npc.name]'s mouth, you let out [pc.a_moan+] as you put an end to the blowjob.",
								"With [pc.a_moan+], you pull your [pc.hips] back, sliding your [pc.cock+] fully out of [npc.name]'s mouth."));
						break;
				}
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+], struggling against you as [npc.she] pleads for you to let [npc.herHim] go.",
							" Tears stream down [npc.her] cheeks as [npc.she] tries to struggle out of your grip, letting out [npc.a_sob+] before begging for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], revealing [npc.her] desire to continue sucking your [pc.cock+].",
							" [npc.She] [npc.moansVerb] as you withdraw from [npc.her] mouth, betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_BLOWJOB_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only start in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [pc.name]'s [pc.cock+] into your mouth and start sucking [pc.herHim] off.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] slowly lowers [npc.her] head between your [pc.legs],"
										+ " passionately kissing the [pc.cockHead+] of your [pc.cock+] before taking you into [npc.her] mouth.",
								"Gently lowering [npc.her] head down between your [pc.legs], [npc.name] delivers a long, slow lick up the length of your [pc.cock+], before taking the [pc.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] eagerly drops [npc.her] head between your [pc.legs],"
										+ " greedily kissing the [pc.cockHead+] of your [pc.cock+] before greedily taking you into [npc.her] mouth.",
								"Eagerly dropping [npc.her] head down between your [pc.legs], [npc.name] delivers a long, wet lick up the length of your [pc.cock+], before greedily taking the [pc.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] quickly drops [npc.her] head between your [pc.legs],"
										+ " roughly kissing the [pc.cockHead+] of your [pc.cock+] before forcefully taking you into [npc.her] mouth.",
								"Dropping [npc.her] head down between your [pc.legs], [npc.name] delivers a rough, wet lick up the length of your [pc.cock+], before forcefully taking the [pc.cockHead+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [pc.cockHead+] of your [pc.cock], [npc.name] slowly takes you into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] starts giving you a blowjob.",
								"Wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], [npc.name] lets out a muffled [npc.moan] as [npc.she] starts giving you a gentle blowjob."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [pc.cockHead+] of your [pc.cock], [npc.name] eagerly takes you into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] happily starts giving you a blowjob.",
								"Wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], [npc.name] lets out a muffled [npc.moan] as [npc.she] eagerly starts giving you a blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [pc.cockHead+] of your [pc.cock], [npc.name] forcefully takes you into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] starts giving you a rough blowjob.",
								"Forcefully wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], [npc.name] lets out a muffled [npc.moan] as [npc.she] starts giving you a rough blowjob."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [pc.cockHead+] of your [pc.cock], [npc.name] eagerly takes you into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] happily starts giving you a blowjob.",
								"Wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], [npc.name] lets out a muffled [npc.moan] as [npc.she] eagerly starts giving you a blowjob."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [pc.cockHead+] of your [pc.cock], [npc.name] takes you into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] starts giving you a blowjob.",
								"Wrapping [npc.her] [npc.lips+] around the [pc.cockHead+] of your [pc.cock], [npc.name] lets out a muffled [npc.moan] as [npc.she] starts giving you a blowjob."));
						break;
					default:
						break;
				}
			}

			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You gently buck your [pc.hips] into [npc.her] [npc.face], softly [pc.moaning] as [npc.she] sucks your [pc.cock+].",
							" With a gentle buck of your [pc.hips], you let out a soft [pc.moan] as you enjoy the feeling of [npc.her] [npc.lips+] sliding up and down the length of your [pc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You greedily buck your [pc.hips] into [npc.her] [npc.face], [pc.moaning+] as [npc.she] sucks your [pc.cock+].",
							" With an energetic buck of your [pc.hips], you let out [pc.a_moan+] as you enjoy the feeling of [npc.her] [npc.lips+] sliding up and down the length of your [pc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You roughly slam your [pc.hips] into [npc.her] [npc.face], [pc.moaning+] as [npc.she] sucks your [pc.cock+].",
							" With a rough thrust of your [pc.hips], you let out [pc.a_moan+] as you enjoy the feeling of [npc.her] [npc.lips+] sliding up and down the length of your [pc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You greedily buck your [pc.hips] into [npc.her] [npc.face], [pc.moaning+] as [npc.she] sucks your [pc.cock+].",
							" With an energetic buck of your [pc.hips], you let out [pc.a_moan+] as you enjoy the feeling of [npc.her] [npc.lips+] sliding up and down the length of your [pc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You buck your [pc.hips] into [npc.her] [npc.face], [pc.moaning+] as [npc.she] sucks your [pc.cock+].",
							" With a buck of your [pc.hips], you let out [pc.a_moan+] as you enjoy the feeling of [npc.her] [npc.lips+] sliding up and down the length of your [pc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+], trying, and failing, to pull your [pc.cock] out of [npc.her] mouth as you struggle against [npc.her] unwanted oral attention.",
							" Feeling tears welling up in your [pc.eyes], you struggle against [npc.her] unwanted oral attention, [pc.sobbing] and squirming as you beg for [npc.herHim] to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
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
			return "Gently suck [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as you eagerly buck your [pc.hips] into [npc.her] [npc.face].",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slowly bobs [npc.her] head up and down, and you find yourself eagerly thrusting your [pc.hips] into [npc.her] [npc.face] in response.",
							"Slowly bobbing [npc.her] head up and down, [npc.name] gently sucks your [pc.cock+], causing you to frantically buck your [pc.hips] into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] and preventing you from pulling away as you struggle and [pc.sob] in distress.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slowly bobs [npc.her] head up and down, holding you in place as you desperately [pc.sob] and struggle against [npc.her] unwanted oral attention.",
							"Slowly bobbing [npc.her] head up and down, [npc.name] gently sucks your [pc.cock+], holding you in place as you [pc.sobVerb] and beg for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] and drawing [pc.a_moan+] from your mouth.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slowly bobs [npc.her] head up and down, and you find yourself letting out [pc.a_moan+] in response.",
							"Slowly bobbing [npc.her] head up and down, [npc.name] gently sucks your [pc.cock+], causing you to let out [pc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eager cock sucking";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] as you eagerly buck your [pc.hips] into [npc.her] [npc.face].",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] rapidly bobs [npc.her] head up and down, and you find yourself eagerly thrusting your [pc.hips] into [npc.her] [npc.face] in response.",
							"Rapidly bobbing [npc.her] head up and down, [npc.name] eagerly sucks your [pc.cock+], causing you to frantically buck your [pc.hips] into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] and preventing you from pulling away as you struggle and [pc.sob] in distress.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] rapidly bobs [npc.her] head up and down, holding you in place as you desperately [pc.sob] and struggle against [npc.her] unwanted oral attention.",
							"Rapidly bobbing [npc.her] head up and down, [npc.name] eagerly sucks your [pc.cock+], holding you in place as you [pc.sobVerb] and beg for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily pushes [npc.her] head forwards, wrapping [npc.her] [npc.lips+] around your [pc.cock+] and drawing [pc.a_moan+] from your mouth.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] rapidly bobs [npc.her] head up and down, and you find yourself letting out [pc.a_moan+] in response.",
							"Rapidly bobbing [npc.her] head up and down, [npc.name] eagerly sucks your [pc.cock+], causing you to let out [pc.a_moan+]."));
					break;
			}
					
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
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
			return "Take [pc.name]'s [pc.cock+] as deep down your throat as you can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs

			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] head forwards, taking your [pc.cock+] deep down your throat as you desperately buck your [pc.hips] into [npc.her] [npc.face].",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slams [npc.her] head forwards, and you find yourself eagerly grinding your [pc.hips] into [npc.her] [npc.face] as [npc.she] deep throats you.",
							"Roughly slamming [npc.her] head forwards, [npc.name] takes your [pc.cock+] deep down [npc.her] throat, causing you to let out [pc.a_moan+] as you eagerly buck your [pc.hips] forwards in response."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] head forwards, taking your [pc.cock+] deep down your throat and preventing you from pulling away as you struggle and [pc.sob] in distress.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slams [npc.her] head forwards,"
									+ " taking you deep down [npc.her] throat as you desperately [pc.sob] and struggle against [npc.her] unwanted oral attention.",
							"Roughly slamming [npc.her] head forwards, [npc.name] takes your [pc.cock+] deep down [npc.her] throat, holding you in place as you [pc.sobVerb] and beg for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] head forwards, taking your [pc.cock+] deep down your throat and drawing [pc.a_moan+] from your mouth.",
							"Wrapping [npc.her] [npc.lips+] around your [pc.cock+], [npc.name] slams [npc.her] head forwards, and you find yourself letting out [pc.a_moan+] as [npc.she] deep throats you.",
							"Roughly slamming [npc.her] head forwards, [npc.name] takes your [pc.cock+] deep down [npc.her] throat, causing you to let out [pc.a_moan+]."));
					break;
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
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
			return "Try and pull [pc.name]'s [pc.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] lets out a muffled [npc.sob], squirming and struggling in distress as you hold [npc.herHim] in place, slowly sliding your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
						"With [npc.a_sob+], [npc.name] tries to push you away, [npc.her] protestations coming out in gargled bursts as you continue gently pushing your [pc.cock+] down [npc.her] throat.",
						"Tears start running down [npc.name]'s cheeks as [npc.she] weakly tries to push you away, [npc.sobbing] in distress as you continue gently sliding your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out a muffled [npc.sob], squirming and struggling in distress as you forcefully hold [npc.herHim] in place, roughly pumping your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_sob+], [npc.name] tries to push you away, [npc.her] protestations coming out in gargled bursts as you continue roughly slamming your [pc.cock+] down [npc.her] throat.",
							"Tears start running down [npc.name]'s cheeks as [npc.she] weakly tries to push you away, [npc.sobbing] in distress as you continue roughly thrusting your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out a muffled [npc.sob], squirming and struggling in distress as you hold [npc.herHim] in place, eagerly sliding your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_sob+], [npc.name] tries to push you away, [npc.her] protestations coming out in gargled bursts as you continue eagerly pushing your [pc.cock+] down [npc.her] throat.",
							"Tears start running down [npc.name]'s cheeks as [npc.she] weakly tries to push you away, [npc.sobbing] in distress as you continue eagerly sliding your [pc.cock+] in and out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Suck cock";
		}

		@Override
		public String getActionDescription() {
			return "Continue sucking [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_moan+], obediently holding still as you slowly slide your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
						"With [npc.a_moan+], [npc.name] happily continues giving you a blowjob, obediently holding still as you continue gently pushing your [pc.cock+] down [npc.her] throat.",
						"[npc.Name] lets out a muffled [npc.moan] as [npc.she] obediently holds still, allowing you to continue gently sliding your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.a_moan+], obediently holding still as you roughly pump your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_moan+], [npc.name] happily continues giving you a blowjob, obediently holding still as you continue roughly slamming your [pc.cock+] down [npc.her] throat.",
							"[npc.Name] lets out a muffled [npc.moan] as [npc.she] obediently holds still, allowing you to continue roughly thrusting your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.a_moan+], obediently holding still as you eagerly slide your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_moan+], [npc.name] happily continues giving you a blowjob, obediently holding still as you continue eagerly thrusting your [pc.cock+] down [npc.her] throat.",
							"[npc.Name] lets out a muffled [npc.moan] as [npc.she] obediently holds still, allowing you to continue eagerly pumping your [pc.cock+] in and out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
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
			return "Take [pc.name]'s [pc.cock+] as deep down your throat as you can.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_moan+], eagerly bobbing [npc.her] head up and down as you slowly slide your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
						"With [npc.a_moan+], [npc.name] enthusiastically bobs [npc.her] head up and down, clearly loving every moment of this as you continue gently pushing your [pc.cock+] down [npc.her] throat.",
						"[npc.Name] lets out a muffled [npc.moan] as [npc.she] energetically bobs [npc.her] head up and down, helping you to gently slide your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.a_moan+], eagerly bobbing [npc.her] head up and down as you roughly thrust your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_moan+], [npc.name] enthusiastically bobs [npc.her] head up and down, clearly loving every moment of this as you continue roughly slamming your [pc.cock+] down [npc.her] throat.",
							"[npc.Name] lets out a muffled [npc.moan] as [npc.she] energetically bobs [npc.her] head up and down, helping you to roughly thrust your [pc.cock+] in and out of [npc.her] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] lets out [npc.a_moan+], eagerly bobbing [npc.her] head up and down as you greedily thrust your [pc.cock+] back and forth past [npc.her] [npc.lips+].",
							"With [npc.a_moan+], [npc.name] enthusiastically bobs [npc.her] head up and down, clearly loving every moment of this as you continue eagerly thrusting your [pc.cock+] down [npc.her] throat.",
							"[npc.Name] lets out a muffled [npc.moan] as [npc.she] energetically bobs [npc.her] head up and down, helping you to eagerly pump your [pc.cock+] in and out of [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_BLOWJOB_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.MOUTH_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull [pc.name]'s [pc.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing your [pc.cock+] down [npc.her] throat one last time, [npc.name] then pulls [npc.her] head back, putting a quick end to your blowjob.",
							"Slamming [npc.her] [npc.face] into your groin, [npc.name] forces your [pc.cock+] deep down [npc.her] throat, before pulling completely back and letting you slip out of [npc.her] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock+] out of [npc.her] mouth, [npc.name] lets out [npc.a_moan+] as [npc.she] puts an end to the blowjob.",
							"With [npc.a_moan+], [npc.name] pulls [npc.her] head back, sliding your [pc.cock+] fully out of [npc.her] mouth."));
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+], struggling against [npc.herHim] as you plead for [npc.herHim] to let you go.",
							" Tears stream down your cheeks as you try to struggle out of [npc.her] grip, letting out [pc.a_sob+] before begging for [npc.herHim] to let you go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], revealing your desire for [npc.herHim] continue sucking your [pc.cock+].",
							" You [pc.moanVerb] as [npc.she] withdraws from your groin, betraying your desire to feel [npc.her] [npc.lips+] wrapped around your [pc.cock+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
