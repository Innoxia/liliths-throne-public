package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public class PlayerTongueAnus {
	
	public static final SexAction PLAYER_ANILINGUS_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Start Anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Press your [pc.lips+] up against [npc.name]'s [npc.asshole+] and start performing anilingus on [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing your [pc.lips+] against [npc.name]'s [npc.ass+], you plant a soft kiss on one of [npc.her] cheeks, before gently sliding your [pc.tongue+] out to give [npc.her] [npc.asshole+] a hot, wet lick.",
							"Planting a series of soft kisses on [npc.name]'s [npc.ass+], you then move down and slide out your [pc.tongue+], before giving [npc.her] [npc.asshole+] a long, wet lick."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing your [pc.lips+] against [npc.name]'s [npc.ass+], you plant a passionate kiss on one of [npc.her] cheeks,"
									+ " before desperately sliding your [pc.tongue+] out to give [npc.her] [npc.asshole+] a hot, wet lick.",
							"Planting a series of passionate kisses on [npc.name]'s [npc.ass+], you then move down and slide out your [pc.tongue+], before greedily giving [npc.her] [npc.asshole+] a long, wet lick."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding your [pc.lips+] against [npc.name]'s [npc.ass+], you plant a forceful kiss on one of [npc.her] cheeks,"
									+ " before greedily sliding your [pc.tongue+] out to give [npc.her] [npc.asshole+] a hot, wet lick.",
							"Planting a series of forceful kisses on [npc.name]'s [npc.ass+], you then move down and slide out your [pc.tongue+], before giving [npc.her] [npc.asshole+] a rough, wet lick."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing your [pc.lips+] against [npc.name]'s [npc.ass+], you plant a passionate kiss on one of [npc.her] cheeks,"
									+ " before desperately sliding your [pc.tongue+] out to give [npc.her] [npc.asshole+] a hot, wet lick.",
							"Planting a series of passionate kisses on [npc.name]'s [npc.ass+], you then move down and slide out your [pc.tongue+], before greedily giving [npc.her] [npc.asshole+] a long, wet lick."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing your [pc.lips+] against [npc.name]'s [npc.ass+], you plant a kiss on one of [npc.her] cheeks, before sliding your [pc.tongue+] out to give [npc.her] [npc.asshole+] a hot, wet lick.",
							"Planting a series of kisses on [npc.name]'s [npc.ass+], you then move down and slide out your [pc.tongue+], before giving [npc.her] [npc.asshole+] a long, wet lick."));
					break;
				default:
					break;
			}

			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, gently bucking [npc.her] [npc.ass] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Gently bucking [npc.her] [npc.ass] out against your [pc.face] in response to your oral attention, [npc.she] [npc.moansVerb] out loud, begging for you to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.ass] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Desperately bucking [npc.her] [npc.ass] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, roughly thrusting [npc.her] [npc.ass] out against your [pc.face] as [npc.she] orders you to keep going.",
							" Roughly thrusting [npc.her] [npc.ass] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, commanding you to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.ass] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Desperately bucking [npc.her] [npc.ass] out against your [pc.face] in an eager response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, bucking [npc.her] [npc.ass] out against your [pc.face] as [npc.she] begs for you to keep going.",
							" Bucking [npc.her] [npc.ass] out against your [pc.face] in response to your oral attention, [npc.she] [npc.moansVerb+] out loud, begging for you to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] frantically tries to wriggle away from your unwanted oral attention, [npc.sobbing] and squirming as [npc.she] begs for you to leave [npc.herHim] alone.",
							" [npc.A_sob+] bursts out from [npc.her] mouth, and, struggling against you, [npc.she] begs for you to take your [pc.tongue] away from [npc.her] [npc.asshole]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_GENTLE,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you plant a series of delicate kisses on [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly begs for you to keep on going.",
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you start to gently kiss and lick [npc.her] [npc.ass+], drawing out [npc.a_moan+] from between [npc.her] [npc.lips+].",
							"Drawing your [pc.lips+] away from [npc.name]'s [npc.asshole+], you move over to start gently kissing and nuzzling against [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you plant a series of delicate kisses on [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] tries to pull away from your unwanted oral attention.",
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you start to gently kiss and lick [npc.her] [npc.ass+], drawing out [npc.a_sob+] as [npc.she] tries to pull away from your unwanted oral attention.",
							"Drawing your [pc.lips+] away from [npc.name]'s [npc.asshole+], you move over to start gently kissing and nuzzling against [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] tries to pull away from you."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you plant a series of delicate kisses on [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_moan] as [npc.she] begs for you to keep on going.",
							"Sliding your [pc.tongue+] out from [npc.name]'s [npc.asshole+], you start to gently kiss and lick [npc.her] [npc.ass+], drawing out [npc.a_moan] from between [npc.her] [npc.lips+].",
							"Drawing your [pc.lips+] away from [npc.name]'s [npc.asshole+], you move over to start gently kissing and nuzzling against [npc.her] [npc.ass+],"
									+ " causing [npc.herHim] to let out [npc.a_moan]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_NORMAL,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [pc.tongue] into [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] desperately pushes [npc.her] [npc.ass] out against your [pc.face].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you eagerly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+], which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+],"
									+ " holding [npc.herHim] still as [npc.she] struggles against you, [npc.sobbing] and squirming as [npc.she] pleads for you to stop.",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] desperately tries to pull away from your [pc.face].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you eagerly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " ignoring the [npc.sobs] and struggles coming from your unwilling partner as you hold [npc.herHim] still."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] pushes [npc.her] [npc.ass] out against your [pc.face].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you eagerly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+], which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.DOM_ROUGH,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s [npc.ass+], you roughly drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Roughly grinding your [pc.face] into [npc.name]'s [npc.ass+], you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] desperately pushes [npc.her] [npc.ass] out against your [pc.face].",
							"Aggressively pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you roughly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s [npc.ass+], you roughly drive your [pc.tongue] deep into [npc.her] [npc.asshole+],"
									+ " holding [npc.herHim] still as [npc.she] struggles against you, [npc.sobbing] and squirming as [npc.she] pleads for you to stop.",
							"Roughly grinding your [pc.face] into [npc.name]'s [npc.ass+], you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_sob+] as [npc.she] desperately tries to pull away from your [pc.face].",
							"Aggressively pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you roughly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " ignoring the [npc.sobs] and struggles coming from your unwilling partner as you hold [npc.herHim] still."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding your [pc.face] into [npc.name]'s [npc.ass+], you roughly drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Roughly grinding your [pc.face] into [npc.name]'s [npc.ass+], you aggressively drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] pushes [npc.her] [npc.ass] out against your [pc.face].",
							"Aggressively pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you roughly spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_ANILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_RESISTING,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.tongue] out of [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s [npc.ass+], but [npc.she] continues gently pressing [npc.her] [npc.asshole+] against your [pc.lips+], holding you in place as [npc.she] forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] quickly forces your [pc.face] right back into [npc.her] [npc.ass+], gently grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.asshole+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] presses [npc.herself] against you."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s [npc.ass+], but [npc.she] roughly slams [npc.her] [npc.asshole+] against your [pc.lips+], holding you in place as [npc.she] violently forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] violently forces your [pc.face] right back into [npc.her] [npc.ass+], roughly grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.asshole+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] roughly grinds [npc.herself] against you."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You try to pull your [pc.face] away from [npc.name]'s [npc.ass+], but [npc.she] continues eagerly thrusting [npc.her] [npc.asshole+] against your [pc.lips+], holding you in place as [npc.she] forces [npc.herself] on you.",
							"With [pc.a_sob+], you try to pull away from [npc.name], but [npc.she] quickly forces your [pc.face] right back into [npc.her] [npc.ass+], eagerly grinding [npc.herself] against you as [npc.she] ignores your struggles.",
							"You feel tears welling up in your [pc.eyes], and with [pc.a_sob+], you try to pull your mouth away from [npc.name]'s [npc.asshole+],"
									+ " but [npc.she] quickly shifts position, ignoring your protests as [npc.she] eagerly grinds [npc.herself] against you."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_NORMAL,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [pc.tongue] into [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] gently pushes [npc.her] [npc.ass] back against your [pc.lips+].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you spear your [pc.tongue+] deep into [npc.her] [npc.asshole+], which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.ass] back against your [pc.lips+].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you spear your [pc.tongue+] deep into [npc.her] [npc.asshole+], which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Pressing your [pc.face] into [npc.name]'s [npc.ass+], you drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly thrusts [npc.her] [npc.ass] back against your [pc.lips+].",
							"Pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you spear your [pc.tongue+] deep into [npc.her] [npc.asshole+], which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER,
			SexPace.SUB_EAGER,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [pc.tongue] into [npc.name]'s [npc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of soft [npc.moans] from between [npc.her] [npc.lips+].",
							"Desperately pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out a soft [npc.moan] as [npc.she] gently pushes [npc.her] [npc.ass] back against your [pc.lips+].",
							"Enthusiastically pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you greedily spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Desperately pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] roughly grinds [npc.her] [npc.ass] back against your [pc.lips+].",
							"Enthusiastically pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you greedily spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing your [pc.face] into [npc.name]'s [npc.ass+], you greedily drive your [pc.tongue] deep into [npc.her] [npc.asshole+], drawing a series of [npc.moans+] from between [npc.her] [npc.lips+].",
							"Desperately pressing your [pc.face] into [npc.name]'s [npc.ass+], you eagerly drive your [pc.tongue] as deep as possible into [npc.her] [npc.asshole+],"
									+ " causing [npc.herHim] to let out [npc.a_moan+] as [npc.she] eagerly thrusts [npc.her] [npc.ass] back against your [pc.lips+].",
							"Enthusiastically pressing your [pc.lips+] up against [npc.name]'s [npc.ass+], you greedily spear your [pc.tongue+] deep into [npc.her] [npc.asshole+],"
									+ " which draws out a series of [npc.moans+] from between [npc.her] [npc.lips+]."));
					break;
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.PITCHER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.tongue] out of [npc.name]'s [npc.asshole+] and stop eating [npc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, you pull your [pc.face] away from [npc.name]'s [npc.asshole+].",
							"Giving [npc.name]'s [npc.asshole+] a final, rough kiss, you pull your [pc.face] away from [npc.her] [npc.ass+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last, wet lick, you pull your [pc.face] away from [npc.name]'s [npc.asshole+].",
							"Giving [npc.name]'s [npc.asshole+] a final, wet kiss, you pull your [pc.face] away from [npc.her] [npc.ass+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] continues to struggle against you, [npc.sobbing] and squirming in discomfort as [npc.she] realises that you aren't completely finished with [npc.herHim] just yet.",
							" Realising that you haven't completely finished with [npc.herHim] just yet, [npc.she] continues struggling and [npc.sobbing],"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you stop pleasuring [npc.her] [npc.asshole], betraying [npc.her] desire for more of your attention.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+] as you stop pleasuring [npc.her] [npc.asshole+], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_ANILINGUS_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER) {
		
		@Override
		public String getActionTitle() {
			return "Get licked";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to start licking your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pressing [npc.her] [npc.ass+] against your [pc.face], [npc.name] lets out a soft [npc.moan] as [npc.she] starts slowly grinding [npc.her] [npc.asshole+] down on your [pc.lips+].",
							"Repositioning [npc.her] [npc.hips+] so that your [pc.face] is forced into [npc.her] [npc.ass+],"
									+ " [npc.name] lets out a soft [npc.moan] as [npc.she] starts gently pressing [npc.her] [npc.asshole+] down against your [pc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.ass+] against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically grinding [npc.her] [npc.asshole+] down on your [pc.lips+].",
							"Repositioning [npc.her] [npc.hips+] so that your [pc.face] is forced into [npc.her] [npc.ass+],"
									+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pressing [npc.her] [npc.asshole+] down against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.ass+] against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts forcefully grinding [npc.her] [npc.asshole+] down on your [pc.lips+].",
							"Repositioning [npc.her] [npc.hips+] so that your [pc.face] is forced into [npc.her] [npc.ass+],"
									+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly grinding [npc.her] [npc.asshole+] down against your [pc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.ass+] against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically grinding [npc.her] [npc.asshole+] down on your [pc.lips+].",
							"Repositioning [npc.her] [npc.hips+] so that your [pc.face] is forced into [npc.her] [npc.ass+],"
									+ " [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pressing [npc.her] [npc.asshole+] down against your [pc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.ass+] against your [pc.face], [npc.name] lets out [npc.a_moan+] as [npc.she] starts grinding [npc.her] [npc.asshole+] down on your [pc.lips+].",
							"Repositioning [npc.her] [npc.hips+] so that your [pc.face] is forced into [npc.her] [npc.ass+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts pressing [npc.her] [npc.asshole+] down against your [pc.lips+]."));
					break;
				default:
					break;
			}

			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slowly slide your [pc.tongue+] out as you press your [pc.face] into [npc.her] [npc.ass], letting out a muffled [pc.moan] as you start gently licking and kissing [npc.her] [npc.asshole+].",
							" Gently sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] [npc.asshole], you let out a muffled [pc.moan] as you start planting a series of tender kisses on [npc.her] [npc.ass+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You greedily slide your [pc.tongue+] out as you press your [pc.face] into [npc.her] [npc.ass], letting out a muffled [pc.moan] as you start happily licking and kissing [npc.her] [npc.asshole+].",
							" Greedily sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] [npc.asshole], you let out a muffled [pc.moan] as you start planting a series of passionate kisses on [npc.her] [npc.ass+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You roughly thrust your [pc.tongue+] out as you press your [pc.face] into [npc.her] [npc.ass], letting out a muffled [pc.moan] as you start greedily licking and kissing [npc.her] [npc.asshole+].",
							" Greedily thrusting your [pc.tongue] out to deliver a rough, wet lick to [npc.her] [npc.asshole], you let out a muffled [pc.moan] as you start planting a series of forceful kisses on [npc.her] [npc.ass+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You greedily slide your [pc.tongue+] out as you press your [pc.face] into [npc.her] [npc.ass], letting out a muffled [pc.moan] as you start happily licking and kissing [npc.her] [npc.asshole+].",
							" Greedily sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] [npc.asshole], you let out a muffled [pc.moan] as you start planting a series of passionate kisses on [npc.her] [npc.ass+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You slide your [pc.tongue+] out as you press your [pc.face] into [npc.her] [npc.ass], letting out a muffled [pc.moan] as you start licking and kissing [npc.her] [npc.asshole+].",
							" Sliding your [pc.tongue] out to deliver a long, slow lick to [npc.her] [npc.asshole], you let out a muffled [pc.moan] as you start planting a series of kisses on [npc.her] [npc.ass+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against [npc.herHim], you let out [pc.a_sob+] as your [pc.face] is forced ever deeper into [npc.her] [npc.asshole+].",
							" [pc.Sobbing] and struggling against [npc.herHim], your protests seem to be in vain as your [pc.face] is forced ever deeper into [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently enjoy [pc.name]'s [pc.tongue+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.ass] into your [pc.face], letting out a soft [npc.moan] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.ass+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With a soft [npc.moan], [npc.name] gently grinds [npc.her] [npc.ass] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
									+ " greedily thrusting into [npc.her] [npc.asshole+] as [npc.she] continues pressing back against your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.ass] into your [pc.face], letting out a soft [npc.moan] as you desperately try to struggle and pull away from [npc.her] [npc.asshole+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.ass+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you desperately try to pull away from [npc.her] [npc.asshole+].",
							"With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.ass] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
									+ " letting out a muffled [pc.sob] as [npc.she] continues pressing [npc.her] [npc.asshole+] against your [pc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently bucks [npc.her] [npc.ass] into your [pc.face], letting out a soft [npc.moan] as you thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out a soft [npc.moan], [npc.name] gently presses [npc.her] [npc.ass+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With a soft [npc.moan], [npc.name] gently thrusts [npc.her] [npc.ass] into your [pc.face], and you dart your [pc.tongue] out in response,"
									+ " thrusting into [npc.her] [npc.asshole+] as [npc.she] continues pressing back against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.tongue+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.ass+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.ass] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
									+ " greedily thrusting into [npc.her] [npc.asshole+] as [npc.she] continues desperately pressing back against your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you desperately try to struggle and pull away from [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.asshole+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you frantically try to pull away from [npc.her] groin.",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.ass] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
									+ " letting out a muffled [pc.sob] as [npc.she] continues desperately pressing [npc.her] [npc.asshole+] against your [pc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] desperately presses [npc.her] [npc.ass+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_moan+], [npc.name] enthusiastically thrusts [npc.her] [npc.ass] into your [pc.face], and you dart your [pc.tongue] out in response,"
									+ " thrusting into [npc.her] [npc.asshole+] as [npc.she] continues desperately pressing back against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Rough anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [npc.asshole+] down against [pc.name]'s [pc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly grinds [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you greedily thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.ass+] against your [pc.lips+], and, all too eager to please [npc.herHim], you greedily dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.ass] into your [pc.face], and you eagerly dart your [pc.tongue] out in response,"
									+ " greedily thrusting into [npc.her] [npc.asshole+] as [npc.she] continues roughly grinding against your [pc.lips+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly grinds [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you desperately try to struggle and pull away from [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.asshole+] against your [pc.lips+], and, [pc.sobbing] and struggling in distress, you frantically try to pull away from [npc.her] [npc.ass+].",
							"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.ass] into your [pc.face], and you scrunch your [pc.eyes] closed in response,"
									+ " letting out a muffled [pc.sob] as [npc.she] continues roughly grinding [npc.her] [npc.asshole+] against your [pc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly grinds [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] violently grinds [npc.her] [npc.asshole+] against your [pc.lips+], and, eager to please [npc.herHim], you dart your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.ass] into your [pc.face], and you dart your [pc.tongue] out in response,"
									+ " thrusting into [npc.her] [npc.asshole+] as [npc.she] continues roughly grinding against your [pc.lips+]."));
					break;
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Enjoy anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Enjoy [pc.name]'s [pc.tongue+] in your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.ass+] against your [pc.lips+], and you gently slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] bucks [npc.her] [npc.ass] into your [pc.face], and you slowly slide your [pc.tongue] out in response,"
									+ " gently thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you roughly thrust your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.asshole+] against your [pc.lips+], and you roughly slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] bucks [npc.her] [npc.ass] into your [pc.face], and you greedily slide your [pc.tongue] out in response,"
									+ " roughly thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you eagerly slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] presses [npc.her] [npc.asshole+] against your [pc.lips+], and you greedily slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] bucks [npc.her] [npc.ass] into your [pc.face], and you eagerly slide your [pc.tongue] out in response,"
									+ " greedily thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eager anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Grind your [npc.asshole+] down against [pc.name]'s [pc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.ass+] against your [pc.lips+], and you gently slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] frantically bucks [npc.her] [npc.ass] into your [pc.face], and you slowly slide your [pc.tongue] out in response,"
									+ " gently thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you roughly thrust your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.asshole+] against your [pc.lips+], and you roughly slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] frantically bucks [npc.her] [npc.ass] into your [pc.face], and you greedily slide your [pc.tongue] out in response,"
									+ " roughly thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] frantically bucks [npc.her] [npc.ass] into your [pc.face], letting out [npc.a_moan+] as you eagerly slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly grinds [npc.her] [npc.asshole+] against your [pc.lips+], and you greedily slide your [pc.tongue+] deep into [npc.her] [npc.asshole+] in response.",
							"With [npc.a_moan+], [npc.name] frantically bucks [npc.her] [npc.ass] into your [pc.face], and you eagerly slide your [pc.tongue] out in response,"
									+ " greedily thrusting into [npc.her] [npc.asshole+] as [npc.she] continues grinding against your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_RECEIVING_ANILINGUS_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [pc.name]'s [pc.tongue+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.ass] away from your [pc.face], letting out [npc.a_sob+] as you gently slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a soft kiss on [npc.her] [npc.ass], before gently sliding your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue gently thrusting your [pc.tongue+] into [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.hips] away from your [pc.face], letting out [npc.a_sob+] as you roughly thrust your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a wet kiss on [npc.her] [npc.ass+], before roughly thrusting your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue roughly thrusting your [pc.tongue+] into [npc.her] [npc.asshole+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately tries to pull [npc.her] [npc.hips] away from your [pc.face], letting out [npc.a_sob+] as you greedily slide your [pc.tongue] deep into [npc.her] [npc.asshole+].",
							"Letting out [npc.a_sob+], [npc.name] desperately tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+]."
									+ " Ignoring [npc.her] protests, you hold [npc.herHim] in place as you plant a passionate kiss on [npc.her] [npc.ass+], before greedily sliding your [pc.tongue+] deep into [npc.her] [npc.asshole+].",
							"With [npc.a_sob+], [npc.name] frantically tries to pull [npc.her] [npc.asshole+] away from your [pc.lips+], but you hold [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as you continue eagerly thrusting your [pc.tongue+] into [npc.her] [npc.asshole+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.ANUS,
			SexParticipantType.CATCHER) {

		@Override
		public String getActionTitle() {
			return "Stop anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.tongue+] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your head away from [npc.her] [npc.ass+], [npc.name] orders you to stop pleasuring [npc.her] [npc.asshole+].",
							"Roughly grinding [npc.her] [npc.asshole+] into your [pc.face] one last time, [npc.name] suddenly pulls [npc.her] [npc.ass] away, before ordering you to stop."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling your head away from [npc.her] [npc.asshole+], [npc.name] tells you to stop pleasuring [npc.her] [npc.asshole+].",
							"Pressing [npc.her] [npc.asshole+] into your [pc.face] one last time, [npc.name] suddenly pulls [npc.her] [npc.ass] away, before telling you to stop."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down your face, you let out [pc.a_sob+] as you realise that [npc.she] isn't finished with you just yet.",
							" You let out [pc.a_sob+] as you continue struggling against [npc.herHim], begging for [npc.herHim] to let you go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], betraying your desire to give [npc.her] [npc.asshole+] more of your oral attention.",
							" Still hungry for more, you let out [pc.a_moan+] as [npc.she] puts an end to your oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
