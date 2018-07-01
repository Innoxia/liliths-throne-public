package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerTongueVagina {
	
	public static final SexAction PLAYER_FORCE_CLIT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Clit attention";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start sucking on [npc2.namePos] clit.";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Pushing [npc2.namePos] [npc2.pussy+] down against [npc.namePos] [npc.lips], [npc2.name] shuffle back on [npc2.namePos] knees until [npc2.namePos] [npc2.clit+] is pressing down against [npc.her] [npc.tongue]."
										+ " Refusing to submit, [npc.she] [npc.verb(start)] [npc.sobbing] and struggling against [npc2.herHim], although [npc.her] muffled protestations only serve to turn [npc2.name] on even more.",

								"Dropping down a little, [npc2.name] purposefully [npc2.verb(grind)] [npc2.namePos] [npc2.clit] down against [npc.namePos] [npc.tongue], causing [npc.herHim] to let out a muffled [npc.sob] as [npc.she] struggles against [npc2.herHim].",

								"Shuffling back a little on [npc2.namePos] knees, [npc2.name] reposition [npc2.herself] so that you're grinding [npc2.namePos] [npc2.clit+] down against [npc.namePos] unwilling [npc.lips+],"
										+ " ignoring [npc.her] muffled [npc.sobs] and protests.");
						
					default:
						return UtilText.returnStringAtRandom(
								"Pushing [npc2.namePos] [npc2.pussy+] down against [npc.namePos] [npc.lips], [npc2.name] shuffle back on [npc2.namePos] knees until [npc2.namePos] [npc2.clit+] is pressing down against [npc.her] [npc.tongue]."
										+ " Quickly realising what [npc2.name] want, [npc.she] [npc.verb(start)] sucking and kissing [npc2.namePos] [npc2.clit], causing [npc2.name] to let out [npc2.a_moan+].",

								"Dropping down a little, [npc2.name] purposefully [npc2.verb(grind)] [npc2.namePos] [npc2.clit] down against [npc.namePos] [npc.tongue]."
										+ " Much to [npc2.namePos] delight, [npc.she] quickly [npc.verb(start)] sucking and kissing [npc2.namePos] sensitive nub, causing [npc2.name] to let out a desperate, shuddering moan.",

								"You turn [npc2.namePos] head back and tell [npc.name] to start giving [npc2.namePos] clit some attention, and [npc.she] obediently [npc.verb(start)] sucking and licking [npc2.namePos] little button, causing [npc2.name] to squeal and moan in pleasure.",

								"Shuffling back a little on [npc2.namePos] knees, [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] [npc.lips], repositioning [npc2.herself] so that [npc.she]'s focusing on kissing and licking [npc2.namePos] sensitive little clit.");
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Shifting [npc2.namePos] [npc2.hips], [npc2.name] reposition [npc.namePos] mouth over [npc2.namePos] [npc2.clit+]."
										+ " Refusing to submit, [npc.she] [npc.verb(start)] [npc.sobbing] and struggling against [npc2.herHim], although [npc.her] muffled protestations only serve to turn [npc2.name] on even more.",

								"With a quick shift of [npc2.namePos] [npc2.hips], [npc2.name] bump [npc2.namePos] [npc2.clit] down against [npc.namePos] [npc.tongue], causing [npc.herHim] to let out a muffled [npc.sob] as [npc.she] struggles against [npc2.herHim].",

								"Pushing [npc2.namePos] [npc2.pussy] down against [npc.namePos] mouth, [npc2.name] reposition [npc2.herself] so that you're grinding [npc2.namePos] [npc2.clit+] down against [npc.her] unwilling [npc.lips+],"
										+ " ignoring [npc.her] muffled [npc.sobs] and protests.");
					default:
						return UtilText.returnStringAtRandom(
								"Shifting [npc2.namePos] [npc2.hips], [npc2.name] reposition [npc.namePos] mouth over [npc2.namePos] [npc2.clit+]."
										+ " Quickly realising what [npc2.name] want, [npc.she] [npc.verb(start)] sucking and kissing [npc2.namePos] [npc2.clit], causing [npc2.name] to let out [npc2.a_moan+].",

								"With a quick shift of [npc2.namePos] [npc2.hips], [npc2.name] bump [npc2.namePos] [npc2.clit] down against [npc.namePos] [npc.tongue]."
										+ " Much to [npc2.namePos] delight, [npc.she] quickly [npc.verb(start)] sucking and kissing [npc2.namePos] sensitive nub, causing [npc2.name] to let out a desperate, shuddering moan.",

								"You tell [npc.name] to start giving [npc2.namePos] clit some attention, and [npc.she] obediently [npc.verb(start)] sucking and licking [npc2.namePos] little button, causing [npc2.name] to squeal and moan in pleasure.",

								"Pushing [npc2.namePos] [npc2.pussy] down on [npc.namePos] [npc.tongue], [npc2.name] reposition [npc2.herself] so that [npc.she]'s focusing on kissing and licking [npc2.namePos] sensitive little clit.");
				}
			}
		}
		
	};
	
	public static final SexAction PARTNER_SUCK_CLIT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Suck clit";
		}

		@Override
		public String getActionDescription() {
			return "Suck [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to gently suck and kiss it.",

							"With a long, slow lick, [npc.name] runs [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] gently [npc.verb(start)] kissing and sucking on [npc2.namePos] [npc2.clit+].",

							"Gently kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] slowly makes [npc.her] way to [npc2.namePos] [npc2.clit+],"
									+ " and with a series of soft licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.namePos] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.namePos] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] makes [npc.her] way to [npc2.namePos] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.namePos] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly dragging [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.pussy+], [npc.name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to dominantly suck and kiss it.",

							"With a rough, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] forcefully kissing and sucking on [npc2.namePos] [npc2.clit+].",

							"Roughly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] makes [npc.her] way to [npc2.namePos] [npc2.clit+],"
									+ " and with a series of greedy, wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.namePos] [npc2.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.namePos] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] makes [npc.her] way to [npc2.namePos] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.namePos] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to suck and kiss it.",

							"With a long, wet lick, [npc.name] runs [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+], pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] kissing and sucking on [npc2.namePos] [npc2.clit+].",

							"Kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] makes [npc.her] way to [npc2.namePos] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.namePos] [npc2.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], and, gently bucking [npc2.namePos] [npc2.hips] into [npc.her] [npc.face], [npc2.name] cry out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pressing [npc2.namePos] [npc2.pussy] against [npc.her] [npc.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Gently bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] [npc.face], [npc2.name] cry out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly pressing [npc2.namePos] [npc2.pussy] against [npc.her] [npc.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, roughly grinding [npc2.namePos] [npc2.hips] into [npc.her] [npc.face], [npc2.name] order [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+], and, roughly grinding [npc2.namePos] [npc2.pussy] against [npc.her] [npc.face], [npc2.name] [npc.verb(command)] [npc.herHim] not to stop.",

							" Roughly grinding [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before ordering [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] [npc.face], [npc2.name] cry out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly pressing [npc2.namePos] [npc2.pussy] against [npc.her] [npc.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, bucking [npc2.namePos] [npc2.hips] into [npc.her] [npc.face], [npc2.name] cry out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+], and, pressing [npc2.namePos] [npc2.pussy] against [npc.her] [npc.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.name] alone.",

							" With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] struggle against [npc.herHim], [npc2.sobbing] out loud as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy] away from [npc.her] unwelcome [npc.tongue].",

							" [npc2.Sobbing] out loud, and with tears in [npc2.namePos] [npc2.eyes], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.name] alone,"
									+ " frantically trying to pull [npc2.namePos] [npc2.hips] back each time [npc2.name] [npc2.verb(feel)] [npc.her] [npc.tongue+] running over [npc2.namePos] [npc2.clit+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING && Sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
					&& Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] gives [npc2.namePos] [npc2.pussy+] a gentle kiss, before pulling back and starting to slowly suck and kiss the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] delivers a long, slow lick up the length of [npc2.namePos] [npc2.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Delivering a gentle kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back, bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] before taking [npc2.name] into [npc.her] mouth."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] gives [npc2.namePos] [npc2.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] delivers a long, wet lick up the length of [npc2.namePos] [npc2.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Delivering a wet kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back, bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] before eagerly taking [npc2.name] into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] gives [npc2.namePos] [npc2.pussy+] a rough kiss, before pulling back and starting to dominantly suck and kiss the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] delivers a long, rough lick up the length of [npc2.namePos] [npc2.pussy+],"
									+ " before pulling back and starting to dominantly kiss and suck the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Delivering a rough kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back, bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] before dominantly taking [npc2.name] into [npc.her] mouth."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] gives [npc2.namePos] [npc2.pussy+] a wet kiss, before pulling back and starting to eagerly suck and kiss the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] delivers a long, wet lick up the length of [npc2.namePos] [npc2.pussy+],"
									+ " before pulling back and starting to eagerly kiss and suck the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Delivering a wet kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back, bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] before eagerly taking [npc2.name] into [npc.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] gives [npc2.namePos] [npc2.pussy+] a kiss, before pulling back and starting to suck and kiss the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Deciding to give [npc2.namePos] [npc2.cock] some attention, [npc.name] delivers a long lick up the length of [npc2.namePos] [npc2.pussy+],"
									+ " before pulling back and starting to kiss and suck the [npc2.cockHead] of [npc2.namePos] [npc2.cock+].",

							"Delivering a kiss to [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] back, bringing [npc.her] [npc.lips+] up to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] before taking [npc2.name] into [npc.her] mouth."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], gently pushing [npc2.namePos] [npc2.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] [npc.verb(focus)] back to [npc2.namePos] [npc2.pussy+] once again.",

							" Gently thrusting [npc2.namePos] [npc2.cock] into [npc.her] mouth, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan], before [npc.she] decides to move back down to focusing on [npc2.namePos] [npc2.pussy+].",

							" Softly [npc2.moaning], [npc2.name] gently buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly pushing [npc2.namePos] [npc2.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] [npc.verb(focus)] back to [npc2.namePos] [npc2.pussy+] once again.",

							" Eagerly thrusting [npc2.namePos] [npc2.cock] into [npc.her] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.she] decides to move back down to focusing on [npc2.namePos] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] eagerly buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] [npc2.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] [npc.verb(focus)] back to [npc2.namePos] [npc2.pussy+] once again.",

							" Roughly slamming [npc2.namePos] [npc2.cock] into [npc.her] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.she] decides to move back down to focusing on [npc2.namePos] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] roughly buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly pushing [npc2.namePos] [npc2.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] [npc.verb(focus)] back to [npc2.namePos] [npc2.pussy+] once again.",

							" Eagerly thrusting [npc2.namePos] [npc2.cock] into [npc.her] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.she] decides to move back down to focusing on [npc2.namePos] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] eagerly buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], pushing [npc2.namePos] [npc2.cock] into [npc.her] mouth before [npc.she] decides to shift [npc.her] [npc.verb(focus)] back to [npc2.namePos] [npc2.pussy+] once again.",

							" thrusting [npc2.namePos] [npc2.cock] into [npc.her] mouth, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.she] decides to move back down to focusing on [npc2.namePos] [npc2.pussy+].",

							" [npc2.Moaning+], [npc2.name] buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face] for a few moments, before [npc.she] decides to shift [npc.her] oral attention back down to [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Sobbing] and squirming in discomfort, [npc2.name] desperately [npc2.verb(try)] to pull away from [npc.herHim], begging to be left alone as [npc.she] shifts [npc.her] attention back down to [npc2.namePos] [npc2.pussy+] once again.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to push [npc.herHim] away, squirming in discomfort as [npc.she] moves back to focusing [npc.her] attention on [npc2.namePos] [npc2.pussy+].",

							" With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] moves back down to focusing on [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Eat [npc2.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.pussy+] and [npc2.verb(start)] eating [npc2.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You [npc2.verb(feel)] [npc.namePos] hot breath on [npc2.namePos] groin as [npc.she] slowly lowers [npc.her] head between [npc2.namePos] [npc2.legs],"
										+ " passionately kissing [npc2.namePos] outer folds before [npc.her] [npc.tongue+] suddenly [npc.verb(push)] deep into [npc2.namePos] [npc2.pussy+].",

								"Gently lowering [npc.her] head down between [npc2.namePos] [npc2.legs], [npc.name] delivers a long, slow lick to [npc2.namePos] outer labia, before pushing [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You [npc2.verb(feel)] [npc.namePos] hot breath on [npc2.namePos] groin as [npc.she] eagerly drops [npc.her] head between [npc2.namePos] [npc2.legs],"
										+ " greedily kissing [npc2.namePos] outer folds before [npc.her] [npc.tongue+] suddenly [npc2.verb(thrust)]s deep into [npc2.namePos] [npc2.pussy+].",

								"Eagerly dropping [npc.her] head down between [npc2.namePos] [npc2.legs], [npc.name] delivers a long, wet lick to [npc2.namePos] outer labia, before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You [npc2.verb(feel)] [npc.namePos] hot breath on [npc2.namePos] groin as [npc.she] quickly drops [npc.her] head between [npc2.namePos] [npc2.legs],"
										+ " roughly kissing and licking [npc2.namePos] outer folds before [npc.her] [npc.tongue+] suddenly forces its way deep into [npc2.namePos] [npc2.pussy+].",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs], [npc.name] delivers a rough, wet lick to [npc2.namePos] outer labia, before forcefully thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] groin, [npc.name] plants a soft kiss on [npc2.namePos] outer folds, before slowly, but firmly, sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+].",

								"Planting a series of soft kisses on [npc2.namePos] outer labia, [npc.name] gives [npc2.namePos] outer folds a long, wet lick, before gently pushing [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] groin, [npc.name] plants a passionate kiss on [npc2.namePos] outer folds, before desperately sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+].",

								"Planting a series of passionate kisses on [npc2.namePos] outer labia, [npc.name] gives [npc2.namePos] outer folds a hungry lick, before greedily pushing [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] groin, [npc.name] plants a forceful kiss on [npc2.namePos] outer folds, before greedily sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+].",

								"Planting a series of forceful kisses on [npc2.namePos] outer labia, [npc.name] gives [npc2.namePos] outer folds a rough lick, before greedily pushing [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] groin, [npc.name] plants a passionate kiss on [npc2.namePos] outer folds, before desperately sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+].",

								"Planting a series of passionate kisses on [npc2.namePos] outer labia, [npc.name] gives [npc2.namePos] outer folds a hungry lick, before greedily pushing [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] groin, [npc.name] plants a kiss on [npc2.namePos] outer folds, before sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+].",

								"Planting a series of kisses on [npc2.namePos] outer labia, [npc.name] gives [npc2.namePos] outer folds a wet lick, before pushing [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.pussy+]."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Gently bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] as [npc2.name] order [npc.herHim] to keep going.",

							" Roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] in an eager response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, commanding [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Bucking [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] frantically [npc2.verb(try)] to wriggle away from [npc.her] unwanted oral attention, [npc2.sobbing] and squirming as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.namePos] pussy alone.",

							" [npc2.A_sob+] bursts out from [npc2.namePos] mouth, and, struggling against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to take [npc.her] [npc.tongue] away from [npc2.namePos] pussy."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] plants a series of delicate kisses on [npc2.namePos] soft outer folds,"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck [npc2.namePos] [npc2.hips] against [npc.her] [npc.face].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] soft labia, drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] outer folds,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moan+] and buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] plants a series of delicate kisses on [npc2.namePos] soft outer folds,"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.her] [npc.face].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] soft labia, causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull away from [npc.her] unwanted oral attention.",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] outer folds,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.sob] and [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.her] [npc.face]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] plants a series of delicate kisses on [npc2.namePos] soft outer folds,"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] buck [npc2.namePos] [npc2.hips] against [npc.her] [npc.face].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] soft labia, drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] outer folds,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moan+] and buck [npc2.namePos] [npc2.hips] into [npc.her] [npc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "Eat out";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] thrusting [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " eagerly eating [npc2.name] out as [npc.she] [npc2.verb(draw)] a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+].",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] desperately [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] eagerly [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+],"
									+ " eagerly eating [npc2.name] out as [npc2.name] struggle against [npc.herHim], [npc2.sobbing] and squirming as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop.",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] desperately [npc2.verb(try)] to pull away from [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] eagerly [npc.verb(start)] eating [npc2.name] out, ignoring [npc2.namePos] [npc2.sobs+] and struggles as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " eagerly eating [npc2.name] out as [npc.she] [npc2.verb(draw)] a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+].",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] eagerly [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "Rough cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly [npc2.verb(thrust)] [npc2.namePos] tongue into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] roughly drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " greedily eating [npc2.name] out as [npc.she] [npc2.verb(draw)] a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+].",

							"Roughly grinding [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] desperately [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] roughly [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] uses [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] roughly drives [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+],"
									+ " greedily eating [npc2.name] out as [npc2.name] struggle against [npc.herHim], [npc2.sobbing] and squirming as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop.",

							"Roughly grinding [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] desperately [npc2.verb(try)] to pull away from [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] roughly [npc.verb(start)] eating [npc2.name] out, ignoring [npc2.namePos] [npc2.sobs+] and struggles as [npc.she] uses [npc2.namePos] [npc2.pussy+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] roughly drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " greedily eating [npc2.name] out as [npc.she] [npc2.verb(draw)] a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+].",

							"Roughly grinding [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue], [npc.name] roughly [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] uses [npc2.namePos] [npc2.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.tongue] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] groin, but [npc2.name] [npc.verb(continue)] gently thrusting [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] groin, gently grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] press [npc2.namePos] folds against [npc.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] groin, but [npc2.name] roughly slam [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] groin, roughly grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] folds against [npc.her] [npc2.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] groin, but [npc2.name] [npc.verb(continue)] eagerly thrusting [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] groin, eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.pussy+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] eagerly press [npc2.namePos] folds against [npc.her] [npc2.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "[npc.verb(continue)] cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] thrusting [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eating [npc2.name] out, drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+] as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
									+ " drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

							"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] pleasures [npc2.namePos] [npc2.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
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
			return "Eagerly eat [npc2.herHim] out";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly [npc2.verb(thrust)] [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
			
				return UtilText.returnStringAtRandom(
						"You suddenly [npc2.verb(feel)] [npc.namePos] [npc.hands] reach around and take hold of [npc2.namePos] [npc2.ass], and before [npc2.name] can react, [npc.she] [npc2.verb(pull)]s [npc2.name] forwards and buries [npc.her] [npc.tongue+] deep in [npc2.namePos] [npc2.pussy+].",

						"Driving [npc.her] head between [npc2.namePos] [npc2.legs], [npc2.name] [npc2.verb(let)] out [npc2.moan+] as [npc2.name] [npc2.verb(feel)] [npc.namePos] [npc.tongue] spearing deep into [npc2.namePos] [npc2.pussy+].",

						"Grinding [npc.her] [npc.face] between [npc2.namePos] legs, [npc.name] [npc.verb(start)] greedily thrusting [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+], causing [npc2.name] to uncontrollably [npc2.moan] and writhe in pleasure.",

						"Reaching around to grab [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(pull)] [npc2.name] forwards, grinding [npc.her] [npc.face] into [npc2.namePos] crotch as [npc.she] greedily eats [npc2.name] out.");
				
			} else {
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
										+ " drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

								"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
										+ " causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

								"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eagerly eating [npc2.name] out, drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+] as [npc.she] greedily pleasures [npc2.namePos] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
										+ " drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

								"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
										+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

								"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eagerly eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] greedily pleasures [npc2.namePos] [npc2.pussy+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+],"
										+ " drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] eats [npc2.name] out.",

								"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] groin, [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.pussy+],"
										+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] [npc.face].",

								"With a deep [npc2.verb(thrust)] of [npc.her] [npc.tongue+], [npc.name] [npc.verb(start)] eagerly eating [npc2.name] out, drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+] as [npc.she] greedily pleasures [npc2.namePos] [npc2.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) || Sex.isSubHasEqualControl(); // Partner can only stop if they're in charge.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.tongue] out of [npc2.namePos] [npc2.pussy+] and stop eating [npc2.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.pussy+].",

						"Giving [npc2.namePos] [npc2.pussy+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] groin."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.pussy+].",

						"Giving [npc2.namePos] [npc2.pussy+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] groin."));
				break;
		}
		
		switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc.verb(continue)] to struggle against [npc.herHim], [npc2.sobbing] and squirming in discomfort as [npc2.name] realise that [npc.she] isn't completely finished with [npc2.name] just yet.",

						" Realising that [npc.she] hasn't completely finished with [npc2.name] just yet, [npc2.name] [npc.verb(continue)] struggling and [npc2.sobbing],"
								+ " tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to let [npc2.name] go."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] stops pleasuring [npc2.namePos] [npc2.pussy], betraying [npc2.namePos] desire for more of [npc.her] attention.",

						" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.she] stops pleasuring [npc2.namePos] [npc2.pussy+], betraying [npc2.namePos] desire for more of [npc.her] attention."));
				break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Player can only [npc2.verb(start)] fingering in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Get licked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start licking [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc2.name] gently take hold of [npc.namePos] head, and with a soft [npc2.moan], [npc2.name] [npc2.verb(pull)] [npc.her] [npc.face] into [npc2.namePos] groin, before starting to slowly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Pulling [npc.namePos] head forwards, [npc2.name] slowly [npc2.verb(force)] [npc.her] [npc.face] between [npc2.namePos] [npc2.legs],"
										+ " and with a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc2.name] take hold of [npc.namePos] head, and with [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(pull)] [npc.her] [npc.face] into [npc2.namePos] groin,"
										+ " before starting to frantically [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Pulling [npc.namePos] head forwards, [npc2.name] quickly [npc2.verb(force)] [npc.her] [npc.face] between [npc2.namePos] [npc2.legs],"
										+ " and with [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down, [npc2.name] take hold of [npc.namePos] head, and with [npc2.a_moan+], [npc2.name] roughly slam [npc.her] [npc.face] into [npc2.namePos] groin,"
										+ " before starting to forcefully [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Yanking [npc.namePos] head forwards, [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.face] between [npc2.namePos] [npc2.legs],"
										+ " and with [npc2.a_moan+], [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc2.name] slowly lower [npc2.namePos] [npc2.pussy+] down to [npc.namePos] mouth, before letting [npc2.namePos] [npc2.legs] slide out from under [npc2.name] and collapsing down onto [npc.her] [npc.face+].",

								"Gently lowering [npc2.namePos] groin down to [npc.namePos] mouth, [npc2.name] slowly allow [npc2.namePos] [npc2.legs] to give out from under you"
										+ " as [npc2.name] press [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc2.name] quickly drop [npc2.namePos] [npc2.pussy+] down to [npc.namePos] mouth, before letting [npc2.namePos] [npc2.legs] slide out from under [npc2.name] and collapsing down onto [npc.her] [npc.face+].",

								"Quickly dropping [npc2.namePos] groin down to [npc.namePos] mouth, [npc2.name] slowly allow [npc2.namePos] [npc2.legs] to give out from under you"
										+ " as [npc2.name] eagerly press [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc2.name] roughly slam [npc2.namePos] [npc2.pussy+] down over [npc.namePos] mouth, letting [npc2.namePos] [npc2.legs] slide out from under [npc2.name] as [npc2.name] [npc2.verb(grind)] down onto [npc.her] [npc.face+].",

								"Slamming [npc2.namePos] groin down over [npc.namePos] mouth, [npc2.name] allow [npc2.namePos] [npc2.legs] to give out from under you"
										+ " as [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc2.namePos] crotch down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] slowly grinding [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Repositioning [npc2.namePos] groin so that [npc.namePos] [npc.face] is forced between [npc2.namePos] [npc2.legs],"
										+ " [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc2.namePos] crotch down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] frantically grinding [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Repositioning [npc2.namePos] groin so that [npc.namePos] [npc.face] is forced between [npc2.namePos] [npc2.legs],"
										+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] eagerly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc2.namePos] crotch down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] forcefully grinding [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Repositioning [npc2.namePos] groin so that [npc.namePos] [npc.face] is forced between [npc2.namePos] [npc2.legs],"
										+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc2.namePos] crotch down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] frantically grinding [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Repositioning [npc2.namePos] groin so that [npc.namePos] [npc.face] is forced between [npc2.namePos] [npc2.legs],"
										+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] eagerly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc2.namePos] crotch down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] grinding [npc2.namePos] [npc2.pussy+] down on [npc.her] [npc.lips+].",

								"Repositioning [npc2.namePos] groin so that [npc.namePos] [npc.face] is forced between [npc2.namePos] [npc2.legs],"
										+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.lips+]."));
						break;
					default:
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc2.namePos] [npc2.scent], [npc.she] slowly slides [npc.her] [npc.tongue+] between [npc2.namePos] folds, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] gently licking and kissing [npc2.namePos] [npc2.pussy+].",

							" Gently sliding [npc.her] [npc.tongue] out to deliver a long, slow lick to [npc2.namePos] soft folds,"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of tender kisses on [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc2.namePos] [npc2.scent], [npc.she] greedily slides [npc.her] [npc.tongue+] between [npc2.namePos] folds, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] happily licking and kissing [npc2.namePos] [npc2.pussy+].",

							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] soft folds,"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of passionate kisses on [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc2.namePos] [npc2.scent], [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue+] between [npc2.namePos] folds, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] greedily licking and kissing [npc2.namePos] [npc2.pussy+].",

							" Greedily thrusting [npc.her] [npc.tongue] out to deliver a rough, wet lick to [npc2.namePos] soft folds,"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] forcefully eating [npc2.name] out."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc2.namePos] [npc2.scent], [npc.she] greedily slides [npc.her] [npc.tongue+] between [npc2.namePos] folds, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] happily licking and kissing [npc2.namePos] [npc2.pussy+].",

							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] soft folds,"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of passionate kisses on [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Breathing in [npc2.namePos] [npc2.scent], [npc.she] slides [npc.her] [npc.tongue+] between [npc2.namePos] folds, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] licking and kissing [npc2.namePos] [npc2.pussy+].",

							" Sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] soft folds, [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of kisses on [npc2.namePos] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against [npc2.herHim], [npc.she] [npc.verb(let)] out [npc.a_sob+] as [npc.she] breathes in [npc2.namePos] [npc2.scent], but [npc.her] protests prove to be in vain as [npc2.name] [npc2.verb(force)] [npc.her] [npc.face] ever deeper into [npc2.namePos] [npc2.pussy+].",

							" [npc.Sobbing] and struggling against [npc2.herHim], [npc.her] protests prove to be in vain as [npc2.name] [npc2.verb(force)] [npc.her] [npc.face] ever deeper into [npc2.namePos] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Gentle cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently [npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " greedily eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.pussy+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from [npc2.namePos] groin.",

							"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "[npc2.verb(enjoy)] cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] enthusiastically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " greedily eating [npc2.name] out as [npc2.name] [npc.verb(continue)] desperately pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from [npc2.namePos] groin.",

							"With [npc2.a_moan+], [npc2.name] enthusiastically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] eagerly pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] enthusiastically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " eating [npc2.name] out as [npc2.name] [npc.verb(continue)] desperately pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.namePos] head, [npc2.name] forcefully [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

								"Stepping forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"Grabbing the back of [npc.namePos] head, [npc2.name] step forwards, roughly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] eagerly darts [npc.her] [npc.tongue] out in response, greedily eating [npc2.name] out as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"With a quick step forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, eager to please [npc2.herHim], [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] hungry [npc2.pussy]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.namePos] head, [npc2.name] forcefully [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.pussy+].",

								"Stepping forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from [npc2.namePos] groin.",

								"Grabbing the back of [npc.namePos] head, [npc2.name] step forwards, roughly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] scrunches [npc.her] [npc.eyes] closed in response, letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"With a quick step forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, letting out [npc.a_sob+], [npc.she] struggles and tries to pull away from [npc2.namePos] [npc2.pussy]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing the back of [npc.namePos] head, [npc2.name] forcefully [npc2.verb(pull)] [npc.herHim] into [npc2.namePos] crotch,"
										+ " roughly grinding down against [npc.her] [npc.face] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

								"Stepping forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"Grabbing the back of [npc.namePos] head, [npc2.name] step forwards, roughly pressing [npc2.namePos] [npc2.pussy+] down against [npc.her] [npc.face]."
										+ " [npc.She] darts [npc.her] [npc.tongue] out in response, obediently eating [npc2.name] out as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"With a quick step forwards, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] mouth, and, eager to please [npc2.herHim], [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] hungry [npc2.pussy]."));
						break;
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow [npc2.namePos] knees to slide out from under [npc2.herHim], and as [npc2.name] collapse down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"Collapsing down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"With [npc2.a_moan+], [npc2.name] allow [npc2.namePos] knees to give way, slamming [npc2.namePos] [npc2.pussy+]"
									+" down onto [npc.namePos] [npc.face] before starting to roughly [npc2.verb(grind)] up and down, forcing [npc.her] [npc.tongue+] deep into [npc2.namePos] folds as [npc2.name] squeal and moan in pleasure."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow [npc2.namePos] knees to slide out from under [npc2.herHim], and as [npc2.name] collapse down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"Collapsing down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] desperately tries to wriggle out from under [npc2.herHim].",

								"With [npc2.a_moan+], [npc2.name] allow [npc2.namePos] knees to give way, slamming [npc2.namePos] [npc2.pussy+]"
									+" down onto [npc.namePos] [npc.face] before starting to roughly [npc2.verb(grind)] up and down, totally ignoring [npc.her] [npc.sobs+] as [npc.she] struggles against [npc2.herHim]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You allow [npc2.namePos] knees to slide out from under [npc2.herHim], and as [npc2.name] collapse down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+].",

								"Collapsing down onto [npc.namePos] [npc.face], [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"With [npc2.a_moan+], [npc2.name] allow [npc2.namePos] knees to give way, slamming [npc2.namePos] [npc2.pussy+]"
									+" down onto [npc.namePos] [npc.face] before starting to roughly [npc2.verb(grind)] up and down, forcing [npc.her] [npc.tongue+] deep into [npc2.namePos] folds as [npc2.name] squeal and moan in pleasure."));
						break;
				}
					
			} else {
			
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

								"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"With [npc2.a_moan+], [npc2.name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
										+ " greedily eating [npc2.name] out as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.pussy+].",

								"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from [npc2.namePos] groin.",

								"With [npc2.a_moan+], [npc2.name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
										+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
						break;
					default: // SUB_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.pussy+].",

								"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

								"With [npc2.a_moan+], [npc2.name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
										+ " eating [npc2.name] out as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
						break;
				}
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Resist cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a soft kiss on [npc2.namePos] outer labia, before gently sliding [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+], but [npc.she] holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] gently eating [npc2.name] out."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] roughly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a wet kiss on [npc2.namePos] outer labia, before roughly thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+], but [npc.she] firmly holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] roughly eating [npc2.name] out."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] greedily slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a passionate kiss on [npc2.namePos] outer labia, before greedily sliding [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.pussy+] away from [npc.namePos] [npc.lips+], but [npc.she] holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] eagerly eating [npc2.name] out."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "[npc2.verb(enjoy)] cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, after planting a soft kiss on [npc2.namePos] outer labia, [npc.she] gently slides [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " gently eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, after planting a wet kiss on [npc2.namePos] outer labia, [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] press [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, after planting a passionate kiss on [npc2.namePos] outer labia,"
									+ " [npc.she] hungrily slides [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " desperately eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Pussy grind";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] down against [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and,"
									+ " after planting a soft kiss on [npc2.namePos] outer labia, [npc.she] gently slides [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] frantically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " gently eating [npc2.name] out as [npc2.name] [npc.verb(continue)] eagerly pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and,"
									+ " after planting a wet kiss on [npc2.namePos] outer labia, [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] frantically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly eating [npc2.name] out as [npc2.name] [npc.verb(continue)] eagerly pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.pussy+].",

							"Letting out [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.pussy+] against [npc.namePos] [npc.lips+], and, after planting a passionate kiss on [npc2.namePos] outer labia,"
									+ " [npc.she] hungrily slides [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] frantically [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " desperately eating [npc2.name] out as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.pussy+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
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
			return "Stop cunnilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.tongue+] out of [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexPositionSlot.SIXTY_NINE_TOP) {
				UtilText.nodeContentSB.append("Using [npc2.namePos] knees to lift [npc2.herself] up, [npc2.name] allow [npc.namePos] [npc.tongue] to slip out of [npc2.namePos] [npc2.pussy+],"
						+ " and [npc2.name] [npc2.verb(feel)] a thin strand of saliva linking [npc2.namePos] lips for a brief moment, before breaking to fall down over [npc.her] [npc.face].");
				
			} else {
				switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly yanking [npc.her] head away from [npc2.namePos] [npc2.pussy+], [npc2.name] order [npc.name] to stop eating [npc2.name] out.",

								"Roughly grinding [npc2.namePos] [npc2.pussy+] into [npc.namePos] [npc.face] one last time, [npc2.name] then [npc2.verb(pull)] [npc2.namePos] [npc2.hips] away, before ordering [npc.herHim] to stop eating [npc2.name] out."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pulling [npc.her] head away from [npc2.namePos] [npc2.pussy+], [npc2.name] tell [npc.name] to stop eating [npc2.name] out.",

								"Pressing [npc2.namePos] [npc2.pussy+] into [npc.namePos] [npc.face] one last time, [npc2.name] then [npc2.verb(pull)] [npc2.namePos] [npc2.hips] away, before telling [npc.herHim] to stop eating [npc2.name] out."));
						break;
				}
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down [npc.her] [npc.face], [npc.she] [npc.verb(let)] out [npc.a_sob+] as [npc.she] realises that [npc2.name] aren't finished with [npc.herHim] just yet.",

							" [npc.She] [npc.verb(let)] out [npc.a_sob+] as [npc.she] [npc.verb(continue)] struggling against [npc2.herHim], begging for [npc2.name] to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], betraying [npc.her] desire to give [npc2.namePos] [npc2.pussy+] more of [npc.her] oral attention.",

							" Still hungry for more, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] put an end to [npc.her] oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	
	
	
	
	
	// Mound:
	
	
	
	
	
	

	public static final SexAction PARTNER_MOUND_SNOG = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Snog mound";
		}

		@Override
		public String getActionDescription() {
			return "Roughly snog [npc2.namePos] genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).hasVagina() && !Sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] roughly grinds [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound, forcefully snogging and licking at [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

						"You [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(feel)] [npc.name] drop [npc.her] head between [npc2.namePos] [npc2.legs] before starting to roughly snog [npc2.namePos] doll-like mound.",

						"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] roughly grinds [npc.her] mouth against [npc2.namePos] genderless crotch, snogging and licking [npc2.namePos] sensitive mound as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

						"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] grinding [npc.her] [npc.lips+] against [npc2.namePos] genderless mound, roughly snogging and licking [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].");
		}
	};
	
	public static final SexAction PARTNER_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately kiss and lick [npc2.namePos] genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).hasVagina() && !Sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound, passionately snogging and licking at [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

					"You [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(feel)] [npc.name] drop [npc.her] head between [npc2.namePos] [npc2.legs] before starting to passionately snog [npc2.namePos] doll-like mound.",

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] passionately [npc.verb(press)] [npc.her] mouth against [npc2.namePos] genderless crotch, snogging and licking [npc2.namePos] sensitive mound as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] pressing [npc.her] [npc.lips+] against [npc2.namePos] genderless mound, eagerly snogging and licking [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].");
		}
	};
	
	public static final SexAction PARTNER_GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc2.namePos] genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).hasVagina() && !Sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] gently [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound, planting delicate kisses on [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

					"You [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(feel)] [npc.name] drop [npc.her] head between [npc2.namePos] [npc2.legs] before starting to gently kiss and lick [npc2.namePos] doll-like mound.",

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] [npc.verb(press)] [npc.her] mouth against [npc2.namePos] genderless crotch, planting a series of gentle kisses on [npc2.namePos] sensitive mound as [npc2.name] [npc2.moan+] beneath [npc.herHim].",

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] genderless mound, gently kissing and licking [npc2.namePos] sensitive crotch as [npc2.name] [npc2.moan+] beneath [npc.herHim].");
		}
	};
	
	public static final SexAction PARTNER_MOUND_SNOG_SUB_EAGER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Snog mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately snog [npc2.namePos] genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).hasVagina() && !Sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] up against [npc2.namePos] doll-like mound, passionately snogging and licking at [npc2.namePos] sensitive crotch as [npc2.name] squeal and moan above [npc.herHim].",

						"You [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(feel)] [npc.name] [npc.verb(start)] passionately snogging [npc2.namePos] doll-like mound.",

						"Pushing [npc.her] head up, [npc.name] eagerly [npc.verb(press)] [npc.her] mouth against [npc2.namePos] genderless crotch, snogging and licking [npc2.namePos] sensitive mound as [npc2.name] squeal and cry out in pleasure.",

						"Reaching around and grabbing [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(pull)] [npc2.name] down onto [npc.her] [npc.face+],"
								+ " grinding [npc.her] [npc.lips] against [npc2.namePos] genderless mound as [npc.she] enthusiastically snogs and licks at [npc2.namePos] sensitive crotch.");
		}
	};
	
	public static final SexAction PARTNER_MOUND_KISSING_SUB_NORMAL = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}
	
		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc.namePos] doll-like crotch.";
		}
	
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).hasVagina() && !Sex.getCharacterTargetedForSexAction(this).hasPenis();
		}
	
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] plants a series of delicate kisses on [npc2.namePos] doll-like mound, causing [npc2.name] to let out [npc2.a_moan+] and [npc2.verb(push)] [npc2.namePos] [npc2.hips] down against [npc.her] [npc.face+].",

						"You [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(feel)] [npc.name] gently kiss and lick [npc2.namePos] doll-like crotch.",

						"With delicate care, [npc.name] plants a series of gentle kisses on [npc2.namePos] genderless mound, causing [npc2.name] to let out [npc2.a_moan+].",

						"[npc.Name] gently kisses and licks at [npc2.namePos] genderless crotch, causing [npc2.name] to let out [npc2.a_moan+].");
		}
	};
}
