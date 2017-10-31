package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerTongueVagina {
	
	public static final SexAction PARTNER_FORCE_CLIT = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Clit attention";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to start sucking on your clit.";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				switch(Sex.getSexPacePlayer()) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Pushing [npc.her] [npc.pussy+] down against your [pc.lips], [npc.name] shuffles back on [npc.her] knees until [npc.her] [npc.clit+] is pressing down against your [pc.tongue]."
										+ " Refusing to submit, you start [pc.sobbing] and struggling against [npc.herHim], although your muffled protestations only seem to be turning [npc.herHim] on even more.",
								"Dropping down a little, [npc.name] purposefully grinds [npc.her] [npc.clit+] against your [pc.tongue], causing you to let out a muffled [pc.sob] as you struggle against [npc.herHim].",
								"Shuffling back a little on [npc.her] knees, [npc.name] repositions [npc.herself] so that [npc.she]'s grinding [npc.her] [npc.clit+] down against your unwilling [pc.lips+],"
										+ " ignoring your muffled [pc.sobs] and protestations.");
					default:
						return UtilText.returnStringAtRandom(
								"Pushing [npc.her] [npc.pussy+] down against your [pc.lips], [npc.name] shuffles back on [npc.her] knees until [npc.her] [npc.clit+] is pressing down against your [pc.tongue]."
										+ " Quickly realising what [npc.she] wants, you start sucking and kissing it, eliciting a series of desperate moans from your horny partner's lips.",
								"Dropping down a little, [npc.name] purposefully grinds [npc.her] [npc.clit+] against your [pc.tongue]."
										+ " Much to [npc.her] delight, you quickly start sucking and kissing [npc.her] sensitive nub, causing [npc.herHim] to let out a desperate, shuddering moan.",
								"[npc.Name] turns [npc.her] head back and tells you to start giving [npc.her] clit some attention, and as you obediently start sucking and licking [npc.her] [npc.clit+],"
										+ " [npc.she] lets out a series of deep, shuddering [npc.moans].",
								"Shuffling back a little on [npc.her] knees, [npc.name] pushes [npc.her] [npc.pussy+] down against your lips, repositioning [npc.herself] until you're focusing on kissing and licking [npc.her] [npc.clit+].");
				}
				
			} else {
			
				switch(Sex.getSexPacePlayer()) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Shifting [npc.her] [pc.hips], [npc.name] repositions your mouth over [npc.her] [npc.clit+]."
										+ " Refusing to submit, you start [pc.sobbing] and struggling against [npc.herHim], although your muffled protestations only seem to be turning [npc.herHim] on even more.",
								"With a quick shift of [npc.her] [npc.hips], [npc.name] bumps [npc.her] [npc.clit] down against your [pc.tongue], causing you to let out a muffled [pc.sob] as you struggle against [npc.herHim].",
								"Pushing [npc.her] [npc.pussy] down against your mouth, [npc.name] repositions [npc.herself] so that [npc.she]'s grinding [npc.her] [npc.clit+] down against your unwilling [pc.lips+],"
										+ " ignoring your muffled [pc.sobs] and protests.");
					default:
						return UtilText.returnStringAtRandom(
								"Shifting [npc.her] [pc.hips], [npc.name] repositions your mouth over [npc.her] [npc.clit+]."
										+ " Quickly realising what [npc.she] wants, you start sucking and kissing [npc.her] [npc.clit], causing [npc.herHim] to let out a shuddering squeal of pleasure.",
								"With a quick shift of [npc.her] [npc.hips], [npc.name] bumps [npc.her] [npc.clit] down against your [pc.tongue]."
										+ " Much to [npc.her] delight, you quickly start sucking and kissing [npc.her] sensitive nub, causing [npc.herHim] to let out a desperate, shuddering moan.",
								"[npc.Name] tells you to start giving [npc.her] clit some attention, and you obediently start sucking and licking [npc.her] little button, causing [npc.herHim] to squeal and moan in pleasure.",
								"Pushing [npc.her] [npc.pussy] down on your [pc.tongue], [npc.name] repositions [npc.herself] so that you're focusing on kissing and licking [npc.her] sensitive little clit.");
				}
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_SUCK_CLIT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Suck [npc.name]'s [npc.clit+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding your [pc.tongue] over [npc.name]'s [npc.pussy+], you press your [pc.lips+] against [npc.her] [npc.clit+], before starting to gently suck and kiss it.",
							"With a long, slow lick, you run your [pc.tongue] up and over [npc.name]'s [npc.clit+], pressing your [pc.lips+] against it as you gently start kissing and sucking on [npc.her] [npc.clit+].",
							"Gently kissing and licking [npc.name]'s [npc.pussy+], you slowly make your way to [npc.her] [npc.clit+],"
									+ " and with a series of soft licks from your [pc.tongue+], you start focusing on pleasuring [npc.her] [npc.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding your [pc.tongue] over [npc.name]'s [npc.pussy+], you press your [pc.lips+] against [npc.her] [npc.clit+], before starting to greedily suck and kiss it.",
							"With a long, wet lick, you run your [pc.tongue] up and over [npc.name]'s [npc.clit+], pressing your [pc.lips+] against it as you eagerly start kissing and sucking on [npc.her] [npc.clit+].",
							"Eagerly kissing and licking [npc.name]'s [npc.pussy+], you make your way to [npc.her] [npc.clit+],"
									+ " and with a series of greedy licks from your [pc.tongue+], you start focusing on pleasuring [npc.her] [npc.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly dragging your [pc.tongue+] over [npc.name]'s [npc.pussy+], you forcefully press your [pc.lips+] against [npc.her] [npc.clit+], before starting to dominantly suck and kiss it.",
							"With a long, rough lick, you run your [pc.tongue+] up and over [npc.name]'s [npc.clit+], pressing your [pc.lips+] against it as you start forcefully kissing and sucking on [npc.her] [npc.clit+].",
							"Roughly kissing and licking [npc.name]'s [npc.pussy+], you make your way to [npc.her] [npc.clit+],"
									+ " and with a series of greedy licks from your [pc.tongue+], you start focusing on pleasuring [npc.her] [npc.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding your [pc.tongue] over [npc.name]'s [npc.pussy+], you press your [pc.lips+] against [npc.her] [npc.clit+], before starting to greedily suck and kiss it.",
							"With a long, wet lick, you run your [pc.tongue] up and over [npc.name]'s [npc.clit+], pressing your [pc.lips+] against it as you eagerly start kissing and sucking on [npc.her] [npc.clit+].",
							"Eagerly kissing and licking [npc.name]'s [npc.pussy+], you make your way to [npc.her] [npc.clit+],"
									+ " and with a series of greedy licks from your [pc.tongue+], you start focusing on pleasuring [npc.her] [npc.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue] over [npc.name]'s [npc.pussy+], you press your [pc.lips+] against [npc.her] [npc.clit+], before starting to suck and kiss it.",
							"With a long, wet lick, you run your [pc.tongue] up and over [npc.name]'s [npc.clit+], pressing your [pc.lips+] against it as you start kissing and sucking on [npc.her] [npc.clit+].",
							"Kissing and licking [npc.name]'s [npc.pussy+], you make your way to [npc.her] [npc.clit+], and with a series of licks from your [pc.tongue+], you start focusing on pleasuring [npc.her] [npc.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan], and, gently bucking [npc.her] [npc.hips] into your [pc.face], [npc.she] cries out for you to continue.",
							" A shuddering [npc.moan] drifts out from between [npc.her] [npc.lips+], and, gently pressing [npc.her] [npc.pussy] against your [pc.face], [npc.she] begs for you not to stop.",
							" Gently bucking [npc.her] [npc.hips] out against your [pc.face], [npc.she] lets out a soft [npc.moan] before pleading for you to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, eagerly bucking [npc.her] [npc.hips] into your [pc.face], [npc.she] cries out for you to continue.",
							" A shuddering [npc.moan] bursts out from between [npc.her] [npc.lips+], and, eagerly pressing [npc.her] [npc.pussy] against your [pc.face], [npc.she] begs for you not to stop.",
							" Eagerly bucking [npc.her] [npc.hips] out against your [pc.face], [npc.she] lets out [npc.a_moan+] before pleading for you to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, roughly grinding [npc.her] [npc.hips] into your [pc.face], [npc.she] orders you to continue.",
							" A shuddering [npc.moan] bursts out from between [npc.her] [npc.lips+], and, roughly grinding [npc.her] [npc.pussy] against your [pc.face], [npc.she] commands you not to stop.",
							" Roughly grinding [npc.her] [npc.hips] out against your [pc.face], [npc.she] lets out [npc.a_moan+] before ordering you to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, eagerly bucking [npc.her] [npc.hips] into your [pc.face], [npc.she] cries out for you to continue.",
							" A shuddering [npc.moan] bursts out from between [npc.her] [npc.lips+], and, eagerly pressing [npc.her] [npc.pussy] against your [pc.face], [npc.she] begs for you not to stop.",
							" Eagerly bucking [npc.her] [npc.hips] out against your [pc.face], [npc.she] lets out [npc.a_moan+] before pleading for you to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], and, bucking [npc.her] [npc.hips] into your [pc.face], [npc.she] cries out for you to continue.",
							" A shuddering [npc.moan] bursts out from between [npc.her] [npc.lips+], and, pressing [npc.her] [npc.pussy] against your [pc.face], [npc.she] begs for you not to stop.",
							" Bucking [npc.her] [npc.hips] out against your [pc.face], [npc.she] lets out [npc.a_moan+] before pleading for you to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] desperately tries to pull [npc.her] [npc.pussy] away from your [pc.face], letting out [npc.a_sob+] as [npc.she] begs for you to leave [npc.herHim] alone.",
							" With tears streaming down [npc.her] [npc.face], [npc.she] struggles against you, [npc.sobbing] out loud as [npc.she] tries to pull [npc.her] [npc.pussy] away from your unwelcome [pc.tongue].",
							" [npc.Sobbing] out loud, and with tears in [npc.her] [npc.eyes], [npc.she] begs for you to leave [npc.herHim] alone,"
									+ " frantically trying to pull [npc.her] [npc.hips] back each time your [pc.tongue+] runs over [npc.her] [npc.clit+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_HERM_FUN = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {
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
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING && Sex.getPartner().hasPenis() && Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS) && Sex.isPartnerFreePenis();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give [npc.her] [npc.pussy+] a gentle kiss, before pulling back and starting to slowly suck and kiss the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Deciding to give [npc.her] [npc.cock] some attention, you deliver a long, slow lick up the length of [npc.her] [npc.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Delivering a gentle kiss to [npc.name]'s [npc.pussy+], you pull back, bringing your [pc.lips+] up to the [npc.cockHead] of [npc.her] [npc.cock+] before taking [npc.herHim] into your mouth."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give [npc.her] [npc.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Deciding to give [npc.her] [npc.cock] some attention, you deliver a long, wet lick up the length of [npc.her] [npc.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Delivering a wet kiss to [npc.name]'s [npc.pussy+], you pull back, bringing your [pc.lips+] up to the [npc.cockHead] of [npc.her] [npc.cock+] before eagerly taking [npc.herHim] into your mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give [npc.her] [npc.pussy+] a rough kiss, before pulling back and starting to dominantly suck and kiss the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Deciding to give [npc.her] [npc.cock] some attention, you deliver a long, rough lick up the length of [npc.her] [npc.pussy+],"
									+ " before pulling back and starting to dominantly kiss and suck the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Delivering a rough kiss to [npc.name]'s [npc.pussy+], you pull back, bringing your [pc.lips+] up to the [npc.cockHead] of [npc.her] [npc.cock+] before dominantly taking [npc.herHim] into your mouth."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give [npc.her] [npc.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Deciding to give [npc.her] [npc.cock] some attention, you deliver a long, wet lick up the length of [npc.her] [npc.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Delivering a wet kiss to [npc.name]'s [npc.pussy+], you pull back, bringing your [pc.lips+] up to the [npc.cockHead] of [npc.her] [npc.cock+] before eagerly taking [npc.herHim] into your mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc.name]'s other sexual organ, you give [npc.her] [npc.pussy+] a kiss, before pulling back and starting to suck and kiss the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Deciding to give [npc.her] [npc.cock] some attention, you deliver a long lick up the length of [npc.her] [npc.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc.cockHead] of [npc.her] [npc.cock+].",
							"Delivering a kiss to [npc.name]'s [npc.pussy+], you pull back, bringing your [pc.lips+] up to the [npc.cockHead] of [npc.her] [npc.cock+] before taking [npc.herHim] into your mouth."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan], gently pushing [npc.her] [npc.cock] into your mouth before you decide to shift your focus back to [npc.her] [npc.pussy+] once again.",
							" Gently thrusting [npc.her] [npc.cock] into your mouth, [npc.she] lets out a soft [npc.moan], before you decide to move back down to focusing on [npc.her] [npc.pussy+].",
							" Softly [npc.moaning], [npc.she] gently bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back down to [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly thrusting [npc.her] [npc.cock] into your mouth before you decide to shift your focus back to [npc.her] [npc.pussy+] once again.",
							" Eagerly thrusting [npc.her] [npc.cock] into your mouth, [npc.she] lets out [npc.a_moan+], before you decide to move back down to focusing on [npc.her] [npc.pussy+].",
							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back down to [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], roughly slamming [npc.her] [npc.cock] into your mouth before you decide to shift your focus back to [npc.her] [npc.pussy+] once again.",
							" Roughly slamming [npc.her] [npc.cock] into your mouth, [npc.she] lets out [npc.a_moan+], before you decide to move back down to focusing on [npc.her] [npc.pussy+].",
							" [npc.Moaning+], [npc.she] roughly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back down to [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], eagerly thrusting [npc.her] [npc.cock] into your mouth before you decide to shift your focus back to [npc.her] [npc.pussy+] once again.",
							" Eagerly thrusting [npc.her] [npc.cock] into your mouth, [npc.she] lets out [npc.a_moan+], before you decide to move back down to focusing on [npc.her] [npc.pussy+].",
							" [npc.Moaning+], [npc.she] eagerly bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back down to [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+], thrusting [npc.her] [npc.cock] into your mouth before you decide to shift your focus back to [npc.her] [npc.pussy+] once again.",
							" Thrusting [npc.her] [npc.cock] into your mouth, [npc.she] lets out [npc.a_moan+], before you decide to move back down to focusing on [npc.her] [npc.pussy+].",
							" [npc.Moaning+], [npc.she] bucks [npc.her] [npc.hips] into your [pc.face] for a few moments, before you decide to shift your oral attention back down to [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Sobbing] and squirming in discomfort, [npc.she] desperately tries to pull away from you, begging to be left alone as you shift your attention back down to [npc.her] [npc.pussy+] once again.",
							" With [npc.a_sob+], [npc.she] tries to push you away, squirming in discomfort as you move back to focusing your attention on [npc.her] [npc.pussy+].",
							" With tears streaming down [npc.her] [npc.face], [npc.she] lets out [npc.a_sob+] as you move back down to focusing on [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Eat [npc.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [pc.tongue] into [npc.name]'s [npc.pussy+] and start eating [npc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you slowly lower your head between [npc.her] [npc.legs],"
										+ " passionately kissing [npc.her] outer folds before pushing your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
								"Gently lowering your head down between [npc.name]'s [npc.legs], you deliver a long, slow lick to [npc.her] outer labia, before pushing your [pc.tongue+] deep into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you eagerly drop your head between [npc.her] [npc.legs],"
										+ " greedily kissing [npc.her] outer folds before thrusting your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
								"Eagerly dropping your head down between [npc.name]'s [npc.legs], you deliver a long, wet lick to [npc.her] outer labia, before greedily thrusting your [pc.tongue+] deep into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Your hot breath falls down onto [npc.name]'s groin as you quickly drop your head between [npc.her] [npc.legs],"
										+ " roughly kissing and licking [npc.her] outer folds before forcing your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
								"Dropping your head down between [npc.name]'s [npc.legs], you deliver a rough, wet lick to [npc.her] outer labia, before forcefully thrusting your [pc.tongue+] deep into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
				
			} else {
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing your [pc.lips+] against [npc.name]'s groin, you plant a soft kiss on [npc.her] outer folds, before slowly, but firmly, sliding your [pc.tongue+] into [npc.her] [npc.pussy+].",
								"Planting a series of soft kisses on [npc.name]'s outer labia, you give [npc.her] outer folds a long, wet lick, before gently pushing your [pc.tongue+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing your [pc.lips+] against [npc.name]'s groin, you plant a passionate kiss on [npc.her] outer folds, before desperately sliding your [pc.tongue+] into [npc.her] [npc.pussy+].",
								"Planting a series of passionate kisses on [npc.name]'s outer labia, you give [npc.her] outer folds a hungry lick, before greedily pushing your [pc.tongue+] into [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding your [pc.lips+] against [npc.name]'s groin, you plant a forceful kiss on [npc.her] outer folds, before greedily forcing your [pc.tongue+] into [npc.her] [npc.pussy+].",
								"Planting a series of forceful kisses on [npc.name]'s outer labia, you give [npc.her] outer folds a rough lick, before greedily pushing your [pc.tongue+] into [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing your [pc.lips+] against [npc.name]'s groin, you plant a passionate kiss on [npc.her] outer folds, before desperately sliding your [pc.tongue+] into [npc.her] [npc.pussy+].",
								"Planting a series of passionate kisses on [npc.name]'s outer labia, you give [npc.her] outer folds a hungry lick, before greedily pushing your [pc.tongue+] into [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing your [pc.lips+] against [npc.name]'s groin, you plant a kiss on [npc.her] outer folds, before sliding your [pc.tongue+] into [npc.her] [npc.pussy+].",
								"Planting a series of kisses on [npc.name]'s outer labia, you give [npc.her] outer folds a wet lick, before pushing your [pc.tongue+] into [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
			}

			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, gently bucking [npc.her] [npc.hips] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Gently bucking [npc.her] [npc.hips] out against your [pc.face] in response to your oral attention, [npc.she] [npc.moansVerb] out loud, begging for you to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Desperately bucking [npc.her] [npc.hips] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, roughly thrusting [npc.her] [npc.hips] out against your [pc.face] as [npc.she] orders you to keep going.",
							" Roughly thrusting [npc.her] [npc.hips] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, commanding you to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Desperately bucking [npc.her] [npc.hips] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, bucking [npc.her] [npc.hips] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Bucking [npc.her] [npc.hips] out against your [pc.face] in response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] frantically tries to wriggle away from your unwanted oral attention, [npc.sobbing] and squirming as [npc.she] begs for you to leave [npc.her] pussy alone.",
							" [npc.A_sob+] bursts out from [npc.her] mouth, and, struggling against you, [npc.she] begs for you to take your [pc.tongue] away from [npc.her] pussy."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_GENTLE,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you plant a series of delicate kisses on [npc.her] soft outer folds,"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly bucks [npc.her] [npc.hips] against your [pc.face].",
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you start to gently kiss and lick [npc.her] soft labia, drawing out [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.lips+] against [npc.name]'s [npc.pussy+], you start gently kissing and nuzzling against [npc.her] outer folds,"
									+ " breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] and bucks [npc.her] [npc.hips] into your [pc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you plant a series of delicate kisses on [npc.her] soft outer folds,"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] tries to pull [npc.her] [npc.hips] away from your [pc.face].",
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you start to gently kiss and lick [npc.her] soft labia, drawing out [npc.a_sob+] as [npc.she] tries to pull away from your unwanted oral attention.",
							"Pressing your [pc.lips+] against [npc.name]'s [npc.pussy+], you start gently kissing and nuzzling against [npc.her] outer folds,"
									+ " breathing in [npc.her] [npc.scent] as [npc.she] [npc.sobsVerb+] and tries to pull [npc.her] [npc.hips] away from your [pc.face]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you plant a series of delicate kisses on [npc.her] soft outer folds,"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] bucks [npc.her] [npc.hips] against your [pc.face].",
							"Drawing your [pc.tongue+] out from [npc.name]'s [npc.pussy+], you start to gently kiss and lick [npc.her] soft labia, drawing out [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.lips+] against [npc.name]'s [npc.pussy+], you start gently kissing and nuzzling against [npc.her] outer folds,"
									+ " breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb] and bucks [npc.her] [npc.hips] into your [pc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_NORMAL,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eat out";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [pc.tongue] into [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " eagerly eating [npc.herHim] out as you draw a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] desperately grinds [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you eagerly start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you pleasure [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " eagerly eating [npc.herHim] out as [npc.she] struggles against you, [npc.sobbing] and squirming as [npc.she] pleads for you to stop.",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] desperately tries to pull away from your [pc.face].",
							"With a deep thrust of your [pc.tongue], you eagerly start eating [npc.name] out, ignoring the [npc.sobs] and struggles from your unwilling partner as you pleasure [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " eagerly eating [npc.herHim] out as you draw a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] grinds [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you eagerly start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you pleasure [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.DOM_ROUGH,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Rough cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s groin, you roughly drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " greedily eating [npc.herHim] out as you draw a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Roughly grinding your [pc.lips+] into [npc.name]'s groin, you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] desperately bucks [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you roughly start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you use [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s groin, you roughly drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " greedily eating [npc.herHim] out as [npc.she] struggles against you, [npc.sobbing] and squirming as [npc.she] pleads for you to stop.",
							"Roughly grinding your [pc.lips+] into [npc.name]'s groin, you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] desperately tries to pull away from your [pc.face].",
							"With a deep thrust of your [pc.tongue], you roughly start eating [npc.name] out, ignoring the [npc.sobs] and struggles from your unwilling partner as you use [npc.her] [npc.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s groin, you roughly drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
									+ " greedily eating [npc.herHim] out as you draw a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Roughly grinding your [pc.lips+] into [npc.name]'s groin, you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] bucks [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you roughly start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you use [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_RESISTING,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.tongue] out of [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s groin, but [npc.she] continues gently thrusting [npc.her] [npc.pussy+] against your [pc.lips+], holding you in place as [npc.she] forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] quickly forces your [pc.face] right back into [npc.her] groin, gently grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.pussy+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] presses [npc.herself] against you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s groin, but [npc.she] roughly slams [npc.her] [npc.pussy+] against your [pc.lips+], holding you in place as [npc.she] violently forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] violently forces your [pc.face] right back into [npc.her] groin, roughly grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.pussy+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] roughly grinds [npc.herself] against you."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s groin, but [npc.she] continues eagerly thrusting [npc.her] [npc.pussy+] against your [pc.lips+], holding you in place as [npc.she] forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] quickly forces your [pc.face] right back into [npc.her] groin, eagerly grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.pussy+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] eagerly grinds [npc.herself] against you."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_NORMAL,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Continue cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [pc.tongue] into [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you drive your [pc.tongue] deep into [npc.her] [npc.pussy+], drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] gently pushes [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you start eating [npc.name] out, drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+] as you pleasure [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you drive your [pc.tongue] deep into [npc.her] [npc.pussy+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you pleasure [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s groin, you drive your [pc.tongue] deep into [npc.her] [npc.pussy+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
							"Pressing your [pc.lips+] into [npc.name]'s groin, you drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly thrusts [npc.her] [npc.hips] out against your [pc.face].",
							"With a deep thrust of your [pc.tongue], you start eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you pleasure [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			SexPace.SUB_EAGER,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly eat [npc.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [pc.tongue] into [npc.name]'s [npc.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				
				return UtilText.returnStringAtRandom(
						"Lifting your [pc.hands] up, you reach around and take hold of [npc.name]'s [npc.ass], and before [npc.she] can react, you pull [npc.herHim] forwards and bury your [pc.tongue+] deep in [npc.her] [npc.pussy+].",
						"Driving your head between [npc.name]'s [npc.legs], you cause [npc.herHim] to let out [npc.moan+] as you spear your [pc.tongue] deep into [npc.her] [npc.pussy+].",
						"Grinding your [pc.face] between [npc.name]'s [npc.legs], you start greedily thrusting your [pc.tongue] deep into [npc.her] [npc.pussy+], causing [npc.herHim] to uncontrollably [npc.moan] and writhe in pleasure.",
						"Reaching around to grab [npc.name]'s [npc.ass+], you pull [npc.herHim] forwards, grinding your [pc.face] into [npc.her] crotch as you greedily eat [npc.herHim] out.");
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
										+ " drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
								"Desperately pressing your [pc.lips+] into [npc.name]'s groin, you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
										+ " causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] gently pushes [npc.her] [npc.hips] out against your [pc.face].",
								"With a deep thrust of your [pc.tongue], you start eagerly eating [npc.name] out, drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+] as you greedily pleasure [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
										+ " drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
								"Desperately pressing your [pc.lips+] into [npc.name]'s groin, you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
										+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.hips] out against your [pc.face].",
								"With a deep thrust of your [pc.tongue], you start eagerly eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you greedily pleasure [npc.her] [npc.pussy+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing your [pc.face] into [npc.name]'s groin, you greedily drive your [pc.tongue] deep into [npc.her] [npc.pussy+],"
										+ " drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you eat [npc.herHim] out.",
								"Desperately pressing your [pc.lips+] into [npc.name]'s groin, you greedily drive your [pc.tongue] as deep as possible into [npc.her] [npc.pussy+],"
										+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly thrusts [npc.her] [npc.hips] out against your [pc.face].",
								"With a deep thrust of your [pc.tongue], you start eagerly eating [npc.name] out, drawing a series of [npc.moans+] from between [npc.her] [npc.lips+] as you greedily pleasure [npc.her] [npc.pussy+]."));
						break;
				}
			
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static final SexAction PLAYER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() ||Sex.isConsensual(); // Player can only stop if they're in charge.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.tongue] out of [npc.name]'s [npc.pussy+] and stop eating [npc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, you pull your [pc.face] away from [npc.name]'s [npc.pussy+].",
							"Giving [npc.name]'s [npc.pussy+] a final, rough kiss, you pull your [pc.face] away from [npc.her] groin."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last, wet lick, you pull your [pc.face] away from [npc.name]'s [npc.pussy+].",
							"Giving [npc.name]'s [npc.pussy+] a final, wet kiss, you pull your [pc.face] away from [npc.her] groin."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] continues to struggle against you, [npc.sobbing] and squirming in discomfort as [npc.she] realises that you aren't completely finished with [npc.herHim] just yet.",
							" Realising that you haven't completely finished with [npc.herHim] just yet, [npc.she] continues struggling and [npc.sobbing],"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you stop pleasuring [npc.her] [npc.pussy], betraying [npc.her] desire for more of your attention.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+] as you stop pleasuring [npc.her] [npc.pussy+], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_CUNNILINGUS_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only start cunnilingus in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get licked";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to start licking your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc.name] gently takes hold of your head, and with a soft [npc.moan], [npc.she] pulls your [pc.face] into [npc.her] groin,"
										+ " before starting to slowly grind [npc.her] [npc.pussy+] down against your [pc.lips+].",
								"Pulling your head forwards, [npc.name] slowly forces your [pc.face] between [npc.her] [npc.legs],"
										+ " and with a soft [npc.moan], [npc.she] starts gently pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc.name] takes hold of your head, and with [npc.a_moan+], [npc.she] eagerly pulls your [pc.face] into [npc.her] groin,"
										+ " before starting to frantically grind [npc.her] [npc.pussy+] down against your [pc.lips+].",
								"Pulling your head forwards, [npc.name] quickly forces your [pc.face] between [npc.her] [npc.legs],"
										+ " and with [npc.a_moan+], [npc.she] starts eagerly pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc.name] takes hold of your head, and with [npc.a_moan+], [npc.she] roughly slams your [pc.face] into [npc.her] groin,"
										+ " before starting to forcefully grind [npc.her] [npc.pussy+] down against your [pc.lips+].",
								"Yanking your head forwards, [npc.name] roughly forces your [pc.face] between [npc.her] [npc.legs],"
										+ " and with [npc.a_moan+], [npc.she] starts roughly grinding [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly lowers [npc.her] [npc.pussy+] down to your mouth, before letting [npc.her] [npc.legs] slide out from under [npc.herHim] and collapsing down onto your [pc.face+].",
								"Gently lowering [npc.her] groin down to your mouth, [npc.name] slowly allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] presses [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly drops [npc.her] [npc.pussy+] down to your mouth, before letting [npc.her] [npc.legs] slide out from under [npc.herHim] and collapsing down onto your [pc.face+].",
								"Quickly dropping [npc.her] groin down to your mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly presses [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly slams [npc.her] [npc.pussy+] down over your mouth, letting [npc.her] [npc.legs] slide out from under [npc.herHim] as [npc.she] grinds down onto your [pc.face+].",
								"Slamming [npc.her] groin down over your mouth, [npc.name] allows [npc.her] [npc.legs] to give out from under [npc.herHim] as [npc.she]"
										+ " roughly grinds [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] crotch down against your [pc.face], [npc.name] lets out a soft [npc.moan] as [npc.she] starts slowly grinding [npc.her] [npc.pussy+] down on your [pc.lips+].",
								"Repositioning [npc.her] groin so that your [pc.face] is forced between [npc.her] [npc.legs],"
										+ " [npc.name] lets out a soft [npc.moan] as [npc.she] starts gently pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] crotch down against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically grinding [npc.her] [npc.pussy+] down on your [pc.lips+].",
								"Repositioning [npc.her] groin so that your [pc.face] is forced between [npc.her] [npc.legs],"
										+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] crotch down against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts forcefully grinding [npc.her] [npc.pussy+] down on your [pc.lips+].",
								"Repositioning [npc.her] groin so that your [pc.face] is forced between [npc.her] [npc.legs],"
										+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly grinding [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] crotch down against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically grinding [npc.her] [npc.pussy+] down on your [pc.lips+].",
								"Repositioning [npc.her] groin so that your [pc.face] is forced between [npc.her] [npc.legs],"
										+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] crotch down against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts grinding [npc.her] [npc.pussy+] down on your [pc.lips+].",
								"Repositioning [npc.her] groin so that your [pc.face] is forced between [npc.her] [npc.legs],"
										+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts pressing [npc.her] [npc.pussy+] down against your [pc.lips+]."));
						break;
					default:
						break;
				}
			
			}

			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc.her] [npc.scent], you slowly slide your [pc.tongue+] between [npc.her] folds, letting out a muffled [pc.moan] as you start gently licking and kissing [npc.her] [npc.pussy+].",
							" Gently sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] soft folds, you let out a muffled [pc.moan] as you start planting a series of tender kisses on [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc.her] [npc.scent], you greedily slide your [pc.tongue+] between [npc.her] folds, letting out a muffled [pc.moan] as you start happily licking and kissing [npc.her] [npc.pussy+].",
							" Greedily sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] soft folds, you let out a muffled [pc.moan] as you start planting a series of passionate kisses on [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc.her] [npc.scent], you roughly thrust your [pc.tongue+] between [npc.her] folds, letting out a muffled [pc.moan] as you start greedily licking and kissing [npc.her] [npc.pussy+].",
							" Greedily thrusting your [pc.tongue] out to deliver a rough, wet lick to [npc.her] soft folds, you let out a muffled [pc.moan] as you start forcefully eating [npc.herHim] out."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc.her] [npc.scent], you greedily slide your [pc.tongue+] between [npc.her] folds, letting out a muffled [pc.moan] as you start happily licking and kissing [npc.her] [npc.pussy+].",
							" Greedily sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] soft folds, you let out a muffled [pc.moan] as you start planting a series of passionate kisses on [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc.her] [npc.scent], you slide your [pc.tongue+] between [npc.her] folds, letting out a muffled [pc.moan] as you start licking and kissing [npc.her] [npc.pussy+].",
							" Sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] soft folds, you let out a muffled [pc.moan] as you start planting a series of kisses on [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against [npc.herHim], you let out [pc.a_sob+] as you breathe in [npc.her] [npc.scent], your protests seemingly in vain as your [pc.face] is forced ever deeper into [npc.her] [npc.pussy+].",
							" [pc.Sobbing] and struggling against [npc.herHim], your protests seem to be in vain as your [pc.face] is forced ever deeper into [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [pc.name]'s [pc.tongue+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.pussy+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] folds.",
							"With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
									+ " greedily eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as you desperately try to struggle and pull away from [npc.her] [npc.pussy+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.pussy+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you desperately try to pull away from [npc.her] groin.",
							"With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
									+ " letting out a muffled [pc.sob] as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.hips] into your [pc.face], letting out a soft [npc.moan] as you thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.pussy+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] folds.",
							"With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.hips] into your [pc.face], and you dart your [pc.tongue] out in response,"
									+ " eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.tongue+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.pussy+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.hips] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
									+ " greedily eating [npc.herHim] out as [npc.she] continues desperately pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you desperately try to struggle and pull away from [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.pussy+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you frantically try to pull away from [npc.her] groin.",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.hips] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
									+ " letting out a muffled [pc.sob] as [npc.she] continues eagerly pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.pussy+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.hips] into your [pc.face], and you dart your [pc.tongue] out in response,"
									+ " eating [npc.herHim] out as [npc.she] continues desperately pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [npc.pussy+] down against [pc.name]'s [pc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				
				switch(Sex.getSexPacePartner()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of your head, [npc.name] forcefully pulls you into [npc.her] crotch,"
										+ " roughly grinding down against your [pc.face] as you greedily thrust your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
								"Stepping forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] folds.",
								"Grabbing the back of your head, [npc.name] steps forwards, roughly pressing [npc.her] [npc.pussy+] down against your [pc.face]."
										+ " You eagerly dart your [pc.tongue] out in response, greedily eating [npc.herHim] out as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"With a quick step forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, eager to please [npc.herHim], you greedily thrust your [pc.tongue+] deep into [npc.her] hungry [npc.pussy]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of your head, [npc.name] forcefully pulls you into [npc.her] crotch,"
										+ " roughly grinding down against your [pc.face] as you desperately try to struggle and pull away from [npc.her] [npc.pussy+].",
								"Stepping forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, [pc.sobbing] and struggling in distress, you frantically try to pull away from [npc.her] groin.",
								"Grabbing the back of your head, [npc.name] steps forwards, roughly pressing [npc.her] [npc.pussy+] down against your [pc.face]."
										+ " You scrunch your [pc.eyes] closed in response, letting out a muffled [pc.sob] as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"With a quick step forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, letting out [pc.a_sob+], you struggle and try to pull away from [npc.her] [npc.pussy]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of your head, [npc.name] forcefully pulls you into [npc.her] crotch,"
										+ " roughly grinding down against your [pc.face] as you thrust your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
								"Stepping forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] folds.",
								"Grabbing the back of your head, [npc.name] steps forwards, roughly pressing [npc.her] [npc.pussy+] down against your [pc.face]."
										+ " You dart your [pc.tongue] out in response, obediently eating [npc.herHim] out as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"With a quick step forwards, [npc.name] grinds [npc.her] [npc.pussy+] down against your mouth, and, eager to please [npc.herHim], you thrust your [pc.tongue+] deep into [npc.her] hungry [npc.pussy]."));
						break;
				}
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				
				switch(Sex.getSexPacePlayer()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]'s knees suddenly slide out from under [npc.herHim], and as [npc.she] collapses down onto your face, [npc.she] starts roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"Collapsing down onto your face, [npc.name] grinds [npc.her] [npc.pussy+] against your lips, letting out [npc.a_moan+] as you greedily dart your [pc.tongue+] deep into [npc.her] folds.",
								"With [npc.a_moan+], [npc.name] allows [npc.her] knees to give way, slamming [npc.her] [npc.pussy+]"
									+" down onto your face before starting to roughly grind up and down, forcing your tongue deep into [npc.her] folds as [npc.she] squeals and moans in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]'s knees suddenly slide out from under [npc.herHim], and as [npc.she] collapses down onto your face, [npc.she] starts roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"Collapsing down onto your face, [npc.name] grinds [npc.her] [npc.pussy+] against your lips, letting out [npc.a_moan+] as you desperately try to wriggle out from under [npc.herHim].",
								"With [npc.a_moan+], [npc.name] allows [npc.her] knees to give way, slamming [npc.her] [npc.pussy+]"
									+" down onto your face before starting to roughly grind up and down, totally ignoring your [pc.sobs+] as you struggle against [npc.herHim]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]'s knees suddenly slide out from under [npc.herHim], and as [npc.she] collapses down onto your face, [npc.she] starts roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+].",
								"Collapsing down onto your face, [npc.name] grinds [npc.her] [npc.pussy+] against your lips, letting out [npc.a_moan+] as you dart your [pc.tongue+] deep into [npc.her] folds.",
								"With [npc.a_moan+], [npc.name] allows [npc.her] knees to give way, slamming [npc.her] [npc.pussy+]"
									+" down onto your face before starting to roughly grind up and down, forcing your tongue deep into [npc.her] folds as [npc.she] squeals and moans in pleasure."));
						break;
				}
					
			} else {
			
				switch(Sex.getSexPacePlayer()) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly grinds [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
								"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.pussy+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] folds.",
								"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.hips] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
										+ " greedily eating [npc.herHim] out as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly grinds [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you desperately try to struggle and pull away from [npc.her] [npc.pussy+].",
								"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.pussy+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you frantically try to pull away from [npc.her] groin.",
								"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.hips] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
										+ " letting out a muffled [pc.sob] as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly grinds [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
								"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.pussy+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] folds.",
								"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.hips] into your [pc.face], and you dart your [pc.tongue] out in response,"
										+ " eating [npc.herHim] out as [npc.she] continues roughly grinding [npc.her] [npc.pussy+] against your [pc.lips+]."));
						break;
				}
			
			}
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.tongue+] in your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.pussy+] against your [pc.lips+], and, after planting a soft kiss on [npc.her] outer labia, you gently slide your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.face], and you slowly slide your [pc.tongue] out in response,"
									+ " gently eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you roughly thrust your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.pussy+] against your [pc.lips+], and, after planting a wet kiss on [npc.her] outer labia, you roughly thrust your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.face], and you greedily slide your [pc.tongue] out in response,"
									+ " roughly eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you eagerly slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.pussy+] against your [pc.lips+], and, after planting a passionate kiss on [npc.her] outer labia,"
									+ " you hungrily slide your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] into your [pc.face], and you eagerly slide your [pc.tongue] out in response,"
									+ " desperately eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [npc.pussy+] down against [pc.name]'s [pc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.pussy+] against your [pc.lips+], and,"
									+ " after planting a soft kiss on [npc.her] outer labia, you gently slide your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] frantically thrusts [npc.her] [npc.hips] into your [pc.face], and you slowly slide your [pc.tongue] out in response,"
									+ " gently eating [npc.herHim] out as [npc.she] continues eagerly pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you roughly thrust your [pc.tongue+] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.pussy+] against your [pc.lips+], and,"
									+ " after planting a wet kiss on [npc.her] outer labia, you roughly thrust your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] frantically thrusts [npc.her] [npc.hips] into your [pc.face], and you greedily slide your [pc.tongue] out in response,"
									+ " roughly eating [npc.herHim] out as [npc.she] continues eagerly pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.hips] into your [pc.face], letting out [npc.a_moan+] as you eagerly slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.pussy+] against your [pc.lips+], and, after planting a passionate kiss on [npc.her] outer labia,"
									+ " you hungrily slide your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_moan+], [npc.name] frantically thrusts [npc.her] [npc.hips] into your [pc.face], and you eagerly slide your [pc.tongue] out in response,"
									+ " desperately eating [npc.herHim] out as [npc.she] continues pressing [npc.her] [npc.pussy+] against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist giving cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.pussy+] away from [pc.name]'s [pc.tongue+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.hips] away from your [pc.face], letting out [npc.a_sob+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a soft kiss on [npc.her] outer labia, before gently sliding your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue gently eating [npc.herHim] out."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.hips] away from your [pc.face], letting out [npc.a_sob+] as you roughly thrust your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a wet kiss on [npc.her] outer labia, before roughly thrusting your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue roughly eating [npc.herHim] out."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.hips] away from your [pc.face], letting out [npc.a_sob+] as you greedily slide your [pc.tongue] deep into [npc.her] [npc.pussy+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a passionate kiss on [npc.her] outer labia, before greedily sliding your [pc.tongue+] deep into [npc.her] folds.",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.pussy+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue eagerly eating [npc.herHim] out."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.tongue+] out of your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] allows your [pc.tongue] to slip out of [npc.her] [npc.pussy+],"
						+ " and you look up to see a thin strand of saliva linking your lips for a brief moment, before breaking to fall down over your [pc.face].");
				
			} else {
				switch(Sex.getSexPacePartner()) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly yanking your head away from [npc.her] [npc.pussy+], [npc.name] orders you to stop eating [npc.herHim] out.",
								"Roughly grinding [npc.her] [npc.pussy+] into your [pc.face] one last time, [npc.name] suddenly pulls [npc.her] [npc.hips] away, before ordering you to stop eating [npc.herHim] out."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pulling your head away from [npc.her] [npc.pussy+], [npc.name] tells you to stop eating [npc.herHim] out.",
								"Pressing [npc.her] [npc.pussy+] into your [pc.face] one last time, [npc.name] suddenly pulls [npc.her] [npc.hips] away, before telling you to stop eating [npc.herHim] out."));
						break;
				}
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down your face, you let out [pc.a_sob+] as you realise that [npc.she] isn't finished with you just yet.",
							" You let out [pc.a_sob+] as you continue struggling against [npc.herHim], begging for [npc.herHim] to let you go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], betraying your desire to give [npc.her] [npc.pussy+] more of your oral attention.",
							" Still hungry for more, you let out [pc.a_moan+] as [npc.she] puts an end to your oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
