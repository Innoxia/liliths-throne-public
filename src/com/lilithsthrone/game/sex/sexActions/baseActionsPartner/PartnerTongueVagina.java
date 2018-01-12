package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerTongueVagina {
	
	public static final SexAction PLAYER_FORCE_CLIT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Clit attention";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start sucking on your clit.";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Pushing your [pc.pussy+] down against [npc.name]'s [npc.lips], you shuffle back on your knees until your [pc.clit+] is pressing down against [npc.her] [npc.tongue]."
										+ " Refusing to submit, [npc.she] starts [npc.sobbing] and struggling against you, although [npc.her] muffled protestations only serve to turn you on even more.",
								"Dropping down a little, you purposefully grind your [pc.clit] down against [npc.name]'s [npc.tongue], causing [npc.herHim] to let out a muffled [npc.sob] as [npc.she] struggles against you.",
								"Shuffling back a little on your knees, you reposition yourself so that you're grinding your [pc.clit+] down against [npc.name]'s unwilling [npc.lips+],"
										+ " ignoring [npc.her] muffled [npc.sobs] and protests.");
						
					default:
						return UtilText.returnStringAtRandom(
								"Pushing your [pc.pussy+] down against [npc.name]'s [npc.lips], you shuffle back on your knees until your [pc.clit+] is pressing down against [npc.her] [npc.tongue]."
										+ " Quickly realising what you want, [npc.she] starts sucking and kissing your [pc.clit], causing you to let out [pc.a_moan+].",
								"Dropping down a little, you purposefully grind your [pc.clit] down against [npc.name]'s [npc.tongue]."
										+ " Much to your delight, [npc.she] quickly starts sucking and kissing your sensitive nub, causing you to let out a desperate, shuddering moan.",
								"You turn your head back and tell [npc.name] to start giving your clit some attention, and [npc.she] obediently starts sucking and licking your little button, causing you to squeal and moan in pleasure.",
								"Shuffling back a little on your knees, you push your [pc.pussy+] down against [npc.name]'s [npc.lips], repositioning yourself so that [npc.she]'s focusing on kissing and licking your sensitive little clit.");
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Shifting your [pc.hips], you reposition [npc.name]'s mouth over your [pc.clit+]."
										+ " Refusing to submit, [npc.she] starts [npc.sobbing] and struggling against you, although [npc.her] muffled protestations only serve to turn you on even more.",
								"With a quick shift of your [pc.hips], you bump your [pc.clit] down against [npc.name]'s [npc.tongue], causing [npc.herHim] to let out a muffled [npc.sob] as [npc.she] struggles against you.",
								"Pushing your [pc.pussy] down against [npc.name]'s mouth, you reposition yourself so that you're grinding your [pc.clit+] down against [npc.her] unwilling [npc.lips+],"
										+ " ignoring [npc.her] muffled [npc.sobs] and protests.");
					default:
						return UtilText.returnStringAtRandom(
								"Shifting your [pc.hips], you reposition [npc.name]'s mouth over your [pc.clit+]."
										+ " Quickly realising what you want, [npc.she] starts sucking and kissing your [pc.clit], causing you to let out [pc.a_moan+].",
								"With a quick shift of your [pc.hips], you bump your [pc.clit] down against [npc.name]'s [npc.tongue]."
										+ " Much to your delight, [npc.she] quickly starts sucking and kissing your sensitive nub, causing you to let out a desperate, shuddering moan.",
								"You tell [npc.name] to start giving your clit some attention, and [npc.she] obediently starts sucking and licking your little button, causing you to squeal and moan in pleasure.",
								"Pushing your [pc.pussy] down on [npc.name]'s [npc.tongue], you reposition yourself so that [npc.she]'s focusing on kissing and licking your sensitive little clit.");
				}
			}
		}
		
	};
	
	public static final SexAction PARTNER_SUCK_CLIT = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Suck [pc.name]'s [pc.clit+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding [npc.her] [npc.tongue] over your [pc.pussy+], [npc.name] presses [npc.her] [npc.lips+] against your [pc.clit+], before starting to gently suck and kiss it.",
							"With a long, slow lick, [npc.name] runs [npc.her] [npc.tongue] up and over your [pc.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] gently starts kissing and sucking on your [pc.clit+].",
							"Gently kissing and licking your [pc.pussy+], [npc.name] slowly makes [npc.her] way to your [pc.clit+],"
									+ " and with a series of soft licks from [npc.her] [npc.tongue+], [npc.she] starts focusing on pleasuring your [pc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over your [pc.pussy+], [npc.name] presses [npc.her] [npc.lips+] against your [pc.clit+], before starting to greedily suck and kiss it.",
							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over your [pc.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly starts kissing and sucking on your [pc.clit+].",
							"Eagerly kissing and licking your [pc.pussy+], [npc.name] makes [npc.her] way to your [pc.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] starts focusing on pleasuring your [pc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly dragging [npc.her] [npc.tongue+] over your [pc.pussy+], [npc.name] forcefully presses [npc.her] [npc.lips+] against your [pc.clit+], before starting to dominantly suck and kiss it.",
							"With a rough, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over your [pc.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] starts forcefully kissing and sucking on your [pc.clit+].",
							"Roughly kissing and licking your [pc.pussy+], [npc.name] makes [npc.her] way to your [pc.clit+],"
									+ " and with a series of greedy, wet licks from [npc.her] [npc.tongue+], [npc.she] starts focusing on pleasuring your [pc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over your [pc.pussy+], [npc.name] presses [npc.her] [npc.lips+] against your [pc.clit+], before starting to greedily suck and kiss it.",
							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over your [pc.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly starts kissing and sucking on your [pc.clit+].",
							"Eagerly kissing and licking your [pc.pussy+], [npc.name] makes [npc.her] way to your [pc.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] starts focusing on pleasuring your [pc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue] over your [pc.pussy+], [npc.name] presses [npc.her] [npc.lips+] against your [pc.clit+], before starting to suck and kiss it.",
							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over your [pc.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] starts kissing and sucking on your [pc.clit+].",
							"Kissing and licking your [pc.pussy+], [npc.name] makes [npc.her] way to your [pc.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] starts focusing on pleasuring your [pc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan], and, gently bucking your [pc.hips] into [npc.her] [npc.face], you cry out for [npc.herHim] to continue.",
							" A shuddering [pc.moan] drifts out from between your [pc.lips+], and, gently pressing your [pc.pussy] against [npc.her] [npc.face], you beg for [npc.herHim] not to stop.",
							" Gently bucking your [pc.hips] out against [npc.her] [npc.face], you let out a soft [pc.moan] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, eagerly bucking your [pc.hips] into [npc.her] [npc.face], you cry out for [npc.herHim] to continue.",
							" A shuddering [pc.moan] bursts out from between your [pc.lips+], and, eagerly pressing your [pc.pussy] against [npc.her] [npc.face], you beg for [npc.herHim] not to stop.",
							" Eagerly bucking your [pc.hips] out against [npc.her] [npc.face], you let out [pc.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, roughly grinding your [pc.hips] into [npc.her] [npc.face], you order [npc.herHim] to continue.",
							" A shuddering [pc.moan] bursts out from between your [pc.lips+], and, roughly grinding your [pc.pussy] against [npc.her] [npc.face], you command [npc.herHim] not to stop.",
							" Roughly grinding your [pc.hips] out against [npc.her] [npc.face], you let out [pc.a_moan+] before ordering [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, eagerly bucking your [pc.hips] into [npc.her] [npc.face], you cry out for [npc.herHim] to continue.",
							" A shuddering [pc.moan] bursts out from between your [pc.lips+], and, eagerly pressing your [pc.pussy] against [npc.her] [npc.face], you beg for [npc.herHim] not to stop.",
							" Eagerly bucking your [pc.hips] out against [npc.her] [npc.face], you let out [pc.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, bucking your [pc.hips] into [npc.her] [npc.face], you cry out for [npc.herHim] to continue.",
							" A shuddering [pc.moan] bursts out from between your [pc.lips+], and, pressing your [pc.pussy] against [npc.her] [npc.face], you beg for [npc.herHim] not to stop.",
							" Bucking your [pc.hips] out against [npc.her] [npc.face], you let out [pc.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You desperately try to pull your [pc.pussy] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as you beg for [npc.herHim] to leave you alone.",
							" With tears streaming down your [pc.face], you struggle against [npc.herHim], [pc.sobbing] out loud as you try to pull your [pc.pussy] away from [npc.her] unwelcome [npc.tongue].",
							" [pc.Sobbing] out loud, and with tears in your [pc.eyes], you beg for [npc.herHim] to leave you alone,"
									+ " frantically trying to pull your [pc.hips] back each time you feel [npc.her] [npc.tongue+] running over your [pc.clit+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_HERM_FUN = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getActivePartner())!=SexPace.SUB_RESISTING && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.isPlayerFreePenis();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives your [pc.pussy+] a gentle kiss, before pulling back and starting to slowly suck and kiss the [pc.cockHead] of your [pc.cock+].",
							"Deciding to give your [pc.cock] some attention, [npc.name] delivers a long, slow lick up the length of your [pc.pussy+],"
									+ " before pulling back and starting to kiss and suck the [pc.cockHead] of your [pc.cock+].",
							"Delivering a gentle kiss to your [pc.pussy+], [npc.name] pulls back, bringing [npc.her] [npc.lips+] up to the [pc.cockHead] of your [pc.cock+] before taking you into [npc.her] mouth."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives your [pc.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [pc.cockHead] of your [pc.cock+].",
							"Deciding to give your [pc.cock] some attention, [npc.name] delivers a long, wet lick up the length of your [pc.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [pc.cockHead] of your [pc.cock+].",
							"Delivering a wet kiss to your [pc.pussy+], [npc.name] pulls back, bringing [npc.her] [npc.lips+] up to the [pc.cockHead] of your [pc.cock+] before eagerly taking you into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives your [pc.pussy+] a rough kiss, before pulling back and starting to dominantly suck and kiss the [pc.cockHead] of your [pc.cock+].",
							"Deciding to give your [pc.cock] some attention, [npc.name] delivers a long, rough lick up the length of your [pc.pussy+],"
									+ " before pulling back and starting to dominantly kiss and suck the [pc.cockHead] of your [pc.cock+].",
							"Delivering a rough kiss to your [pc.pussy+], [npc.name] pulls back, bringing [npc.her] [npc.lips+] up to the [pc.cockHead] of your [pc.cock+] before dominantly taking you into [npc.her] mouth."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives your [pc.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [pc.cockHead] of your [pc.cock+].",
							"Deciding to give your [pc.cock] some attention, [npc.name] delivers a long, wet lick up the length of your [pc.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [pc.cockHead] of your [pc.cock+].",
							"Delivering a wet kiss to your [pc.pussy+], [npc.name] pulls back, bringing [npc.her] [npc.lips+] up to the [pc.cockHead] of your [pc.cock+] before eagerly taking you into [npc.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore your other sexual organ, [npc.name] gives your [pc.pussy+] a kiss, before pulling back and starting to suck and kiss the [pc.cockHead] of your [pc.cock+].",
							"Deciding to give your [pc.cock] some attention, [npc.name] delivers a long lick up the length of your [pc.pussy+],"
									+ " before pulling back and starting to kiss and suck the [pc.cockHead] of your [pc.cock+].",
							"Delivering a kiss to your [pc.pussy+], [npc.name] pulls back, bringing [npc.her] [npc.lips+] up to the [pc.cockHead] of your [pc.cock+] before taking you into [npc.her] mouth."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan], gently pushing your [pc.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] focus back to your [pc.pussy+] once again.",
							" Gently thrusting your [pc.cock] into [npc.her] mouth, you let out a soft [pc.moan], before [npc.she] decides to move back down to focusing on your [pc.pussy+].",
							" Softly [pc.moaning], you gently buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly pushing your [pc.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] focus back to your [pc.pussy+] once again.",
							" Eagerly thrusting your [pc.cock] into [npc.her] mouth, you let out [pc.a_moan+], before [npc.she] decides to move back down to focusing on your [pc.pussy+].",
							" [pc.Moaning+], you eagerly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], roughly slamming your [pc.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] focus back to your [pc.pussy+] once again.",
							" Roughly slamming your [pc.cock] into [npc.her] mouth, you let out [pc.a_moan+], before [npc.she] decides to move back down to focusing on your [pc.pussy+].",
							" [pc.Moaning+], you roughly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to your [pc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly pushing your [pc.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] focus back to your [pc.pussy+] once again.",
							" Eagerly thrusting your [pc.cock] into [npc.her] mouth, you let out [pc.a_moan+], before [npc.she] decides to move back down to focusing on your [pc.pussy+].",
							" [pc.Moaning+], you eagerly buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], pushing your [pc.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] focus back to your [pc.pussy+] once again.",
							" Thrusting your [pc.cock] into [npc.her] mouth, you let out [pc.a_moan+], before [npc.she] decides to move back down to focusing on your [pc.pussy+].",
							" [pc.Moaning+], you buck your [pc.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.Sobbing] and squirming in discomfort, you desperately try to pull away from [npc.herHim], begging to be left alone as [npc.she] shifts [npc.her] attention back down to your [pc.pussy+] once again.",
							" With [pc.a_sob+], you try to push [npc.herHim] away, squirming in discomfort as [npc.she] moves back to focusing [npc.her] attention on your [pc.pussy+].",
							" With tears streaming down your [pc.face], you let out [pc.a_sob+] as [npc.she] moves back down to focusing on your [pc.pussy+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			return "Eat [pc.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into [pc.name]'s [pc.pussy+] and start eating [pc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] slowly lowers [npc.her] head between your [pc.legs],"
										+ " passionately kissing your outer folds before [npc.her] [npc.tongue+] suddenly pushes deep into your [pc.pussy+].",
								"Gently lowering [npc.her] head down between your [pc.legs], [npc.name] delivers a long, slow lick to your outer labia, before pushing [npc.her] [npc.tongue+] deep into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] eagerly drops [npc.her] head between your [pc.legs],"
										+ " greedily kissing your outer folds before [npc.her] [npc.tongue+] suddenly thrusts deep into your [pc.pussy+].",
								"Eagerly dropping [npc.her] head down between your [pc.legs], [npc.name] delivers a long, wet lick to your outer labia, before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You feel [npc.name]'s hot breath on your groin as [npc.she] quickly drops [npc.her] head between your [pc.legs],"
										+ " roughly kissing and licking your outer folds before [npc.her] [npc.tongue+] suddenly forces its way deep into your [pc.pussy+].",
								"Dropping [npc.her] head down between your [pc.legs], [npc.name] delivers a rough, wet lick to your outer labia, before forcefully thrusting [npc.her] [npc.tongue+] deep into your [pc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against your groin, [npc.name] plants a soft kiss on your outer folds, before slowly, but firmly, sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
								"Planting a series of soft kisses on your outer labia, [npc.name] gives your outer folds a long, wet lick, before gently pushing [npc.her] [npc.tongue+] into your [pc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against your groin, [npc.name] plants a passionate kiss on your outer folds, before desperately sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
								"Planting a series of passionate kisses on your outer labia, [npc.name] gives your outer folds a hungry lick, before greedily pushing [npc.her] [npc.tongue+] into your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding [npc.her] [npc.lips+] against your groin, [npc.name] plants a forceful kiss on your outer folds, before greedily sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
								"Planting a series of forceful kisses on your outer labia, [npc.name] gives your outer folds a rough lick, before greedily pushing [npc.her] [npc.tongue+] into your [pc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against your groin, [npc.name] plants a passionate kiss on your outer folds, before desperately sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
								"Planting a series of passionate kisses on your outer labia, [npc.name] gives your outer folds a hungry lick, before greedily pushing [npc.her] [npc.tongue+] into your [pc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against your groin, [npc.name] plants a kiss on your outer folds, before sliding [npc.her] [npc.tongue+] into your [pc.pussy+].",
								"Planting a series of kisses on your outer labia, [npc.name] gives your outer folds a wet lick, before pushing [npc.her] [npc.tongue+] into your [pc.pussy+]."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, gently bucking your [pc.hips] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Gently bucking your [pc.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Desperately bucking your [pc.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, roughly thrusting your [pc.hips] out against [npc.her] [npc.face] as you order [npc.herHim] to keep going.",
							" Roughly thrusting your [pc.hips] out against [npc.her] [npc.face] in an eager response to [npc.her] oral attention, you [pc.moan] out loud, commanding [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.hips] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Desperately bucking your [pc.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, bucking your [pc.hips] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Bucking your [pc.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You frantically try to wriggle away from [npc.her] unwanted oral attention, [pc.sobbing] and squirming as you beg for [npc.herHim] to leave your pussy alone.",
							" [pc.A_sob+] bursts out from your mouth, and, struggling against [npc.herHim], you beg for [npc.herHim] to take [npc.her] [npc.tongue] away from your pussy."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] plants a series of delicate kisses on your soft outer folds,"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck your [pc.hips] against [npc.her] [npc.face].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] starts to gently kiss and lick your soft labia, drawing out [pc.a_moan+] from between your [pc.lips+].",
							"Pressing [npc.her] [npc.lips+] against your [pc.pussy+], [npc.name] starts gently kissing and nuzzling against your outer folds,"
									+ " breathing in your [pc.scent] as you [pc.moan+] and buck your [pc.hips] into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] plants a series of delicate kisses on your soft outer folds,"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.hips] away from [npc.her] [npc.face].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] starts to gently kiss and lick your soft labia, causing you to let out [pc.a_sob+] as you try to pull away from [npc.her] unwanted oral attention.",
							"Pressing [npc.her] [npc.lips+] against your [pc.pussy+], [npc.name] starts gently kissing and nuzzling against your outer folds,"
									+ " breathing in your [pc.scent] as you [pc.sob] and try to pull your [pc.hips] away from [npc.her] [npc.face]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] plants a series of delicate kisses on your soft outer folds,"
									+ " causing you to let out [pc.a_moan+] as you buck your [pc.hips] against [npc.her] [npc.face].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.pussy+], [npc.name] starts to gently kiss and lick your soft labia, drawing out [pc.a_moan+] from between your [pc.lips+].",
							"Pressing [npc.her] [npc.lips+] against your [pc.pussy+], [npc.name] starts gently kissing and nuzzling against your outer folds,"
									+ " breathing in your [pc.scent] as you [pc.moan+] and buck your [pc.hips] into [npc.her] [npc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eat out";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " eagerly eating you out as [npc.she] draws a series of [pc.moans+] from between your [pc.lips+].",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you desperately thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] eagerly starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] pleasures your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue+] deep into your [pc.pussy+],"
									+ " eagerly eating you out as you struggle against [npc.herHim], [pc.sobbing] and squirming as you plead for [npc.herHim] to stop.",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_sob+] as you desperately try to pull away from [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] eagerly starts eating you out, ignoring your [pc.sobs+] and struggles as [npc.she] pleasures your [pc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " eagerly eating you out as [npc.she] draws a series of [pc.moans+] from between your [pc.lips+].",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] eagerly starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] pleasures your [pc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your groin, [npc.name] roughly drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " greedily eating you out as [npc.she] draws a series of [pc.moans+] from between your [pc.lips+].",
							"Roughly grinding [npc.her] [npc.lips+] into your groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you desperately thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] roughly starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] uses your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your groin, [npc.name] roughly drives [npc.her] [npc.tongue+] deep into your [pc.pussy+],"
									+ " greedily eating you out as you struggle against [npc.herHim], [pc.sobbing] and squirming as you plead for [npc.herHim] to stop.",
							"Roughly grinding [npc.her] [npc.lips+] into your groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_sob+] as you desperately try to pull away from [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] roughly starts eating you out, ignoring your [pc.sobs+] and struggles as [npc.she] uses your [pc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your groin, [npc.name] roughly drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " greedily eating you out as [npc.she] draws a series of [pc.moans+] from between your [pc.lips+].",
							"Roughly grinding [npc.her] [npc.lips+] into your groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue], [npc.name] roughly starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] uses your [pc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tongue] out of [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your groin, but you continue gently thrusting your [pc.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you quickly force [npc.her] [npc.face] right back into your groin, gently grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.pussy+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you press your folds against [npc.her] [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your groin, but you roughly slam your [pc.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you violently force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you violently force [npc.her] [npc.face] right back into your groin, roughly grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.pussy+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you roughly grind your folds against [npc.her] [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your groin, but you continue eagerly thrusting your [pc.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you quickly force [npc.her] [npc.face] right back into your groin, eagerly grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.pussy+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you eagerly press your folds against [npc.her] [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Continue cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " drawing a series of soft [pc.moans] from between your [pc.lips+] as [npc.she] eats you out.",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out a soft [pc.moan] as you gently push your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eating you out, drawing a series of soft [pc.moans] from between your [pc.lips+] as [npc.she] pleasures your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] eats you out.",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you roughly thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] pleasures your [pc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your groin, [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
									+ " drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] eats you out.",
							"Pressing [npc.her] [npc.lips+] into your groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly thrust your [pc.hips] out against [npc.her] [npc.face].",
							"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] pleasures your [pc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			null,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly eat [pc.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into [pc.name]'s [pc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionNew.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
			
				return UtilText.returnStringAtRandom(
						"You suddenly feel [npc.name]'s [npc.hands] reach around and take hold of your [pc.ass], and before you can react, [npc.she] pulls you forwards and buries [npc.her] [npc.tongue+] deep in your [pc.pussy+].",
						"Driving [npc.her] head between your [pc.legs], you let out [pc.moan+] as you feel [npc.name]'s [npc.tongue] spearing deep into your [pc.pussy+].",
						"Grinding [npc.her] [npc.face] between your legs, [npc.name] starts greedily thrusting [npc.her] [npc.tongue] deep into your [pc.pussy+], causing you to uncontrollably [pc.moan] and writhe in pleasure.",
						"Reaching around to grab your [pc.ass+], [npc.name] pulls you forwards, grinding [npc.her] [npc.face] into your crotch as [npc.she] greedily eats you out.");
				
			} else {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
										+ " drawing a series of soft [pc.moans] from between your [pc.lips+] as [npc.she] eats you out.",
								"Desperately pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
										+ " causing you to let out a soft [pc.moan] as you gently push your [pc.hips] out against [npc.her] [npc.face].",
								"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eagerly eating you out, drawing a series of soft [pc.moans] from between your [pc.lips+] as [npc.she] greedily pleasures your [pc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
										+ " drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] eats you out.",
								"Desperately pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
										+ " causing you to let out [pc.a_moan+] as you roughly thrust your [pc.hips] out against [npc.her] [npc.face].",
								"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eagerly eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] greedily pleasures your [pc.pussy+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into your groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.pussy+],"
										+ " drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] eats you out.",
								"Desperately pressing [npc.her] [npc.lips+] into your groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.pussy+],"
										+ " causing you to let out [pc.a_moan+] as you eagerly thrust your [pc.hips] out against [npc.her] [npc.face].",
								"With a deep thrust of [npc.her] [npc.tongue+], [npc.name] starts eagerly eating you out, drawing a series of [pc.moans+] from between your [pc.lips+] as [npc.she] greedily pleasures your [pc.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl(); // Partner can only stop if they're in charge.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of [pc.name]'s [pc.pussy+] and stop eating [pc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last rough lick, [npc.name] pulls [npc.her] [npc.face] away from your [pc.pussy+].",
						"Giving your [pc.pussy+] a final, rough kiss, [npc.name] pulls [npc.her] [npc.face] away from your groin."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last lick, [npc.name] pulls [npc.her] [npc.face] away from your [pc.pussy+].",
						"Giving your [pc.pussy+] a final, wet kiss, [npc.name] pulls [npc.her] [npc.face] away from your groin."));
				break;
		}
		
		switch(Sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You continue to struggle against [npc.herHim], [pc.sobbing] and squirming in discomfort as you realise that [npc.she] isn't completely finished with you just yet.",
						" Realising that [npc.she] hasn't completely finished with you just yet, you continue struggling and [pc.sobbing],"
								+ " tears streaming down your [pc.face] as you plead for [npc.herHim] to let you go."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" You let out [pc.a_moan+] as [npc.she] stops pleasuring your [pc.pussy], betraying your desire for more of [npc.her] attention.",
						" [pc.A_moan+] drifts out from between your [pc.lips+] as [npc.she] stops pleasuring your [pc.pussy+], betraying your desire for more of [npc.her] attention."));
				break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_CUNNILINGUS_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only start fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get licked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start licking your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionNew.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, you gently take hold of [npc.name]'s head, and with a soft [pc.moan], you pull [npc.her] [npc.face] into your groin, before starting to slowly grind your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Pulling [npc.name]'s head forwards, you slowly force [npc.her] [npc.face] between your [pc.legs],"
										+ " and with a soft [pc.moan], you start gently pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, you take hold of [npc.name]'s head, and with [pc.a_moan+], you eagerly pull [npc.her] [npc.face] into your groin,"
										+ " before starting to frantically grind your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Pulling [npc.name]'s head forwards, you quickly force [npc.her] [npc.face] between your [pc.legs],"
										+ " and with [pc.a_moan+], you start eagerly pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, you take hold of [npc.name]'s head, and with [pc.a_moan+], you roughly slam [npc.her] [npc.face] into your groin,"
										+ " before starting to forcefully grind your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Yanking [npc.name]'s head forwards, you roughly force [npc.her] [npc.face] between your [pc.legs],"
										+ " and with [pc.a_moan+], you start roughly grinding your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you slowly lower your [pc.pussy+] down to [npc.name]'s mouth, before letting your [pc.legs] slide out from under you and collapsing down onto [npc.her] [npc.face+].",
								"Gently lowering your groin down to [npc.name]'s mouth, you slowly allow your [pc.legs] to give out from under you"
										+ " as you press your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you quickly drop your [pc.pussy+] down to [npc.name]'s mouth, before letting your [pc.legs] slide out from under you and collapsing down onto [npc.her] [npc.face+].",
								"Quickly dropping your groin down to [npc.name]'s mouth, you slowly allow your [pc.legs] to give out from under you"
										+ " as you eagerly press your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, you roughly slam your [pc.pussy+] down over [npc.name]'s mouth, letting your [pc.legs] slide out from under you as you grind down onto [npc.her] [npc.face+].",
								"Slamming your groin down over [npc.name]'s mouth, you allow your [pc.legs] to give out from under you"
										+ " as you roughly grind your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing your crotch down against [npc.name]'s [npc.face], you let out a soft [pc.moan] as you start slowly grinding your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Repositioning your groin so that [npc.name]'s [npc.face] is forced between your [pc.legs],"
										+ " you let out a soft [pc.moan] as you start gently pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing your crotch down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start frantically grinding your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Repositioning your groin so that [npc.name]'s [npc.face] is forced between your [pc.legs],"
										+ " you let out [pc.a_moan+] as you start eagerly pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming your crotch down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start forcefully grinding your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Repositioning your groin so that [npc.name]'s [npc.face] is forced between your [pc.legs],"
										+ " you let out [pc.a_moan+] as you start roughly grinding your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing your crotch down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start frantically grinding your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Repositioning your groin so that [npc.name]'s [npc.face] is forced between your [pc.legs],"
										+ " you let out [pc.a_moan+] as you start eagerly pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing your crotch down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start grinding your [pc.pussy+] down on [npc.her] [npc.lips+].",
								"Repositioning your groin so that [npc.name]'s [npc.face] is forced between your [pc.legs],"
										+ " you let out [pc.a_moan+] as you start pressing your [pc.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in your [pc.scent], [npc.she] slowly slides [npc.her] [npc.tongue+] between your folds, letting out a muffled [npc.moan] as [npc.she] starts gently licking and kissing your [pc.pussy+].",
							" Gently sliding [npc.her] [npc.tongue] out to deliver a long, slow lick to your soft folds,"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of tender kisses on your [pc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in your [pc.scent], [npc.she] greedily slides [npc.her] [npc.tongue+] between your folds, letting out a muffled [npc.moan] as [npc.she] starts happily licking and kissing your [pc.pussy+].",
							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your soft folds,"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of passionate kisses on your [pc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in your [pc.scent], [npc.she] roughly thrusts [npc.her] [npc.tongue+] between your folds, letting out a muffled [npc.moan] as [npc.she] starts greedily licking and kissing your [pc.pussy+].",
							" Greedily thrusting [npc.her] [npc.tongue] out to deliver a rough, wet lick to your soft folds,"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts forcefully eating you out."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in your [pc.scent], [npc.she] greedily slides [npc.her] [npc.tongue+] between your folds, letting out a muffled [npc.moan] as [npc.she] starts happily licking and kissing your [pc.pussy+].",
							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your soft folds,"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of passionate kisses on your [pc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in your [pc.scent], [npc.she] slides [npc.her] [npc.tongue+] between your folds, letting out a muffled [npc.moan] as [npc.she] starts licking and kissing your [pc.pussy+].",
							" Sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your soft folds, [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of kisses on your [pc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against you, [npc.she] lets out [npc.a_sob+] as [npc.she] breathes in your [pc.scent], but [npc.her] protests prove to be in vain as you force [npc.her] [npc.face] ever deeper into your [pc.pussy+].",
							" [npc.Sobbing] and struggling against you, [npc.her] protests prove to be in vain as you force [npc.her] [npc.face] ever deeper into your [pc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_GENTLE,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [npc.name]'s [npc.tongue+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
							"Letting out a soft [pc.moan], you gently press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
							"With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " greedily eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] desperately tries to struggle and pull away from your [pc.pussy+].",
							"Letting out a soft [pc.moan], you gently press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from your groin.",
							"With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.hips] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
							"Letting out a soft [pc.moan], you gently press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
							"With a soft [pc.moan], you gently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc.name]'s [npc.tongue+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you enthusiastically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " greedily eating you out as you continue desperately pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] desperately tries to struggle and pull away from your [pc.pussy+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from your groin.",
							"With [pc.a_moan+], you enthusiastically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as you continue eagerly pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you enthusiastically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " eating you out as you continue desperately pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.DOM_ROUGH,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [pc.pussy+] down against [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionNew.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.name]'s head, you forcefully pull [npc.herHim] into your crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
								"Stepping forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
								"Grabbing the back of [npc.name]'s head, you step forwards, roughly pressing your [pc.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] eagerly darts [npc.her] [npc.tongue] out in response, greedily eating you out as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"With a quick step forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, eager to please you, [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your hungry [pc.pussy]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.name]'s head, you forcefully pull [npc.herHim] into your crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] desperately tries to struggle and pull away from your [pc.pussy+].",
								"Stepping forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from your groin.",
								"Grabbing the back of [npc.name]'s head, you step forwards, roughly pressing your [pc.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] scrunches [npc.her] [npc.eyes] closed in response, letting out a muffled [npc.sob] as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"With a quick step forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, letting out [npc.a_sob+], [npc.she] struggles and tries to pull away from your [pc.pussy]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.name]'s head, you forcefully pull [npc.herHim] into your crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
								"Stepping forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
								"Grabbing the back of [npc.name]'s head, you step forwards, roughly pressing your [pc.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] darts [npc.her] [npc.tongue] out in response, obediently eating you out as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"With a quick step forwards, you grind your [pc.pussy+] down against [npc.name]'s mouth, and, eager to please you, [npc.she] thrusts [npc.her] [npc.tongue+] deep into your hungry [pc.pussy]."));
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow your knees to slide out from under you, and as you collapse down onto [npc.name]'s [npc.face], you start roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"Collapsing down onto [npc.name]'s [npc.face], you grind your [pc.pussy+] against [npc.her] [npc.lips+], letting out [pc.a_moan+] as [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
								"With [pc.a_moan+], you allow your knees to give way, slamming your [pc.pussy+]"
									+" down onto [npc.name]'s [npc.face] before starting to roughly grind up and down, forcing [npc.her] [npc.tongue+] deep into your folds as you squeal and moan in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow your knees to slide out from under you, and as you collapse down onto [npc.name]'s [npc.face], you start roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"Collapsing down onto [npc.name]'s [npc.face], you grind your [pc.pussy+] against [npc.her] [npc.lips+], letting out [pc.a_moan+] as [npc.she] desperately tries to wriggle out from under you.",
								"With [pc.a_moan+], you allow your knees to give way, slamming your [pc.pussy+]"
									+" down onto [npc.name]'s [npc.face] before starting to roughly grind up and down, totally ignoring [npc.her] [npc.sobs+] as [npc.she] struggles against you."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow your knees to slide out from under you, and as you collapse down onto [npc.name]'s [npc.face], you start roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+].",
								"Collapsing down onto [npc.name]'s [npc.face], you grind your [pc.pussy+] against [npc.her] [npc.lips+], letting out [pc.a_moan+] as [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
								"With [pc.a_moan+], you allow your knees to give way, slamming your [pc.pussy+]"
									+" down onto [npc.name]'s [npc.face] before starting to roughly grind up and down, forcing [npc.her] [npc.tongue+] deep into your folds as you squeal and moan in pleasure."));
						break;
				}
					
			} else {
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly grind your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
								"Letting out [pc.a_moan+], you roughly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
								"With [pc.a_moan+], you violently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
										+ " greedily eating you out as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly grind your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] desperately tries to struggle and pull away from your [pc.pussy+].",
								"Letting out [pc.a_moan+], you roughly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from your groin.",
								"With [pc.a_moan+], you violently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
										+ " letting out a muffled [npc.sob] as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly grind your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.pussy+].",
								"Letting out [pc.a_moan+], you roughly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
								"With [pc.a_moan+], you violently thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
										+ " eating you out as you continue roughly grinding your [pc.pussy+] against [npc.her] [npc.lips+]."));
						break;
				}
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_RESISTING,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [pc.pussy+] away from [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.hips] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a soft kiss on your outer labia, before gently sliding [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_sob+], you frantically try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+], but [npc.she] holds you in place,"
									+ " ignoring your protests as [npc.she] continues gently eating you out."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.hips] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] roughly slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a wet kiss on your outer labia, before roughly thrusting [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_sob+], you frantically try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+], but [npc.she] firmly holds you in place,"
									+ " ignoring your protests as [npc.she] continues roughly eating you out."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.hips] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] greedily slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a passionate kiss on your outer labia, before greedily sliding [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_sob+], you frantically try to pull your [pc.pussy+] away from [npc.name]'s [npc.lips+], but [npc.she] holds you in place,"
									+ " ignoring your protests as [npc.she] continues eagerly eating you out."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_NORMAL,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [npc.name]'s [npc.tongue+] in your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, after planting a soft kiss on your outer labia, [npc.she] gently slides [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " gently eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, after planting a wet kiss on your outer labia, [npc.she] roughly thrusts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you press your [pc.pussy+] against [npc.name]'s [npc.lips+], and, after planting a passionate kiss on your outer labia,"
									+ " [npc.she] hungrily slides [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " desperately eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [pc.pussy+] down against [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you eagerly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and,"
									+ " after planting a soft kiss on your outer labia, [npc.she] gently slides [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you frantically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " gently eating you out as you continue eagerly pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you eagerly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and,"
									+ " after planting a wet kiss on your outer labia, [npc.she] roughly thrusts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you frantically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly eating you out as you continue eagerly pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.hips] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into your [pc.pussy+].",
							"Letting out [pc.a_moan+], you eagerly grind your [pc.pussy+] against [npc.name]'s [npc.lips+], and, after planting a passionate kiss on your outer labia,"
									+ " [npc.she] hungrily slides [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you frantically thrust your [pc.hips] into [npc.name]'s [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " desperately eating you out as you continue pressing your [pc.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Main.game.getPlayer()); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.tongue+] out of your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				UtilText.nodeContentSB.append("Using your knees to lift yourself up, you allow [npc.name]'s [npc.tongue] to slip out of your [pc.pussy+],"
						+ " and you feel a thin strand of saliva linking your lips for a brief moment, before breaking to fall down over [npc.her] [npc.face].");
				
			} else {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly yanking [npc.her] head away from your [pc.pussy+], you order [npc.name] to stop eating you out.",
								"Roughly grinding your [pc.pussy+] into [npc.name]'s [npc.face] one last time, you then pull your [pc.hips] away, before ordering [npc.herHim] to stop eating you out."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pulling [npc.her] head away from your [pc.pussy+], you tell [npc.name] to stop eating you out.",
								"Pressing your [pc.pussy+] into [npc.name]'s [npc.face] one last time, you then pull your [pc.hips] away, before telling [npc.herHim] to stop eating you out."));
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down [npc.her] [npc.face], [npc.she] lets out [npc.a_sob+] as [npc.she] realises that you aren't finished with [npc.herHim] just yet.",
							" [npc.She] lets out [npc.a_sob+] as [npc.she] continues struggling against you, begging for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], betraying [npc.her] desire to give your [pc.pussy+] more of [npc.her] oral attention.",
							" Still hungry for more, [npc.she] lets out [npc.a_moan+] as you put an end to [npc.her] oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
