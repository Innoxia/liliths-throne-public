package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public class PartnerTongueAnus {

	public static final SexAction PARTNER_ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Start anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into [pc.name]'s [pc.asshole+] and start eating [pc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] plants a series soft kisses on your cheeks, before slowly, but firmly, sliding [npc.her] [npc.tongue+] into your [pc.asshole+].",
							"Planting a series of soft kisses on your [pc.ass+], [npc.name] gives your [pc.asshole+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] plants a series of passionate kisses on your cheeks, before desperately sliding [npc.her] [npc.tongue+] into your [pc.asshole+].",
							"Planting a series of passionate kisses on your [pc.ass+], [npc.name] gives your [pc.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] plants a series of forceful kisses on your cheeks, before greedily sliding [npc.her] [npc.tongue+] into your [pc.asshole+].",
							"Planting a series of forceful kisses on your [pc.ass+], [npc.name] gives your [pc.asshole+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] plants a series of passionate kisses on your cheeks, before desperately sliding [npc.her] [npc.tongue+] into your [pc.asshole+].",
							"Planting a series of passionate kisses on your [pc.ass+], [npc.name] gives your [pc.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] plants a series of kisses on your cheeks, before sliding [npc.her] [npc.tongue+] into your [pc.asshole+].",
							"Planting a series of kisses on your [pc.ass+], [npc.name] gives your [pc.asshole+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, gently bucking your [pc.ass] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Gently bucking your [pc.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.ass] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Desperately bucking your [pc.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, roughly thrusting your [pc.ass] out against [npc.her] [npc.face] as you order [npc.herHim] to keep going.",
							" Roughly thrusting your [pc.ass] out against [npc.her] [npc.face] in an eager response to [npc.her] oral attention, you [pc.moan] out loud, commanding [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, eagerly bucking your [pc.ass] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Desperately bucking your [pc.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, bucking your [pc.ass] out against [npc.her] [npc.face] as you beg for [npc.herHim] to keep going.",
							" Bucking your [pc.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, you [pc.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You frantically try to wriggle away from [npc.her] unwanted oral attention, [pc.sobbing] and squirming as you beg for [npc.herHim] to leave you alone.",
							" [pc.A_sob+] bursts out from your mouth, and, struggling against [npc.herHim], you beg for [npc.herHim] to take [npc.her] [npc.tongue] away from your asshole."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

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
			return "Gently lick [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] plants a series of delicate kisses on your [pc.ass+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck back against [npc.her] [npc.face].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts to gently kiss and lick your [pc.ass+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"Drawing [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts gently kissing and nuzzling against your [pc.ass+], causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] plants a series of delicate kisses on your [pc.ass+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts to gently kiss and lick your [pc.ass+], causing you to let out [pc.a_sob+] as you try to pull away from [npc.her] unwanted oral attention.",
							"Drawing [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts gently kissing and nuzzling against your [pc.ass+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] plants a series of delicate kisses on your [pc.ass+],"
									+ " causing you to let out [pc.a_moan+] as you buck back against [npc.her] [npc.face].",
							"Sliding [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts to gently kiss and lick your [pc.ass+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"Drawing [npc.her] [npc.tongue+] out from your [pc.asshole+], [npc.name] starts gently kissing and nuzzling against your [pc.ass+], causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

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
			return "Continue thrusting your [npc.tongue] into [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck back against [npc.her] [npc.face].",
							"Pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim].",
							"Pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull away from [npc.her] unwanted oral attention.",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you buck back against [npc.her] [npc.face].",
							"Pressing [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

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
			return "Roughly thrust your tongue into [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your [pc.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck back against [npc.her] [npc.face].",
							"Roughly grinding [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your [pc.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim].",
							"Roughly grinding [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_sob+] as you try to pull away from [npc.her] unwanted oral attention.",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and"
									+ " causing you to let out [pc.a_sob+] as you try to pull your [pc.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into your [pc.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you buck back against [npc.her] [npc.face].",
							"Roughly grinding [npc.her] [npc.lips+] against your [pc.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into your [pc.asshole+], drawing out [pc.a_moan+] from between your [pc.lips+].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and causing you to [pc.moan+] and buck back into [npc.her] [npc.face]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.tongue] out of [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your [pc.ass+], but you continue gently pressing your [pc.asshole+] back against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you quickly force [npc.her] [npc.face] right back into your [pc.ass+], gently grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.ass+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you press your [pc.asshole+] against [npc.her] [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your [pc.ass+], but you roughly slam your [pc.asshole+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you violently force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you violently force [npc.her] [npc.face] right back into your [pc.ass+], roughly grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.ass+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you roughly grind your [pc.asshole+] against [npc.her] [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from your [pc.ass+], but you continue eagerly thrusting your [pc.asshole+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as you force yourself on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] tries to pull away from you, but you quickly force [npc.her] [npc.face] right back into your [pc.ass+], eagerly grinding yourself against [npc.herHim] as you ignore [npc.her] struggles.",
							"Tears start to well up in [npc.name]'s [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from your [pc.ass+],"
									+ " but you quickly shift position, ignoring [npc.her] protests as you eagerly press your [pc.asshole+] against [npc.her] [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Continue anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you gently buck back against [npc.her] [npc.face].",
							"Pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out a soft [pc.moan] as you gently push your [pc.ass] out against [npc.her] [npc.face].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of soft [pc.moans] from between your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you roughly grind back against [npc.her] [npc.face].",
							"Pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you roughly slam your [pc.ass] back against [npc.her] [npc.face].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of [pc.moans+] from between your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck back against [npc.her] [npc.face].",
							"Pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly press your [pc.ass] out against [npc.her] [npc.face].",
							"[npc.Name] buries [npc.her] [npc.face] in your [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of [pc.moans+] from between your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

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
			return "Eagerly thrust your [npc.tongue] into [pc.name]'s [pc.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you gently buck back against [npc.her] [npc.face].",
							"Desperately pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out a soft [pc.moan] as you gently push your [pc.ass] out against [npc.her] [npc.face].",
							"[npc.Name] eagerly buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of soft [pc.moans] from between your [pc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you roughly grind back against [npc.her] [npc.face].",
							"Desperately pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you roughly slam your [pc.ass] back against [npc.her] [npc.face].",
							"[npc.Name] eagerly buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of [pc.moans+] from between your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into your [pc.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly buck back against [npc.her] [npc.face].",
							"Desperately pressing [npc.her] [npc.lips+] into your [pc.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into your [pc.asshole+],"
									+ " causing you to let out [pc.a_moan+] as you eagerly press your [pc.ass] out against [npc.her] [npc.face].",
							"[npc.Name] eagerly buries [npc.her] [npc.face] in your [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+] and drawing a series of [pc.moans+] from between your [pc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of [pc.name]'s [pc.asshole+] and stop eating [pc.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last rough lick, [npc.name] pulls [npc.her] [npc.face] away from your [pc.asshole+].",
						"Giving your [pc.asshole+] a final, rough kiss, [npc.name] pulls [npc.her] [npc.face] away from your [pc.ass+]."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last lick, [npc.name] pulls [npc.her] [npc.face] away from your [pc.asshole+].",
						"Giving your [pc.asshole+] a final, wet kiss, [npc.name] pulls [npc.her] [npc.face] away from your [pc.ass+]."));
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
						" You let out [pc.a_moan+] as [npc.she] stops pleasuring your [pc.asshole], betraying your desire for more of [npc.her] attention.",
						" [pc.A_moan+] drifts out from between your [pc.lips+] as [npc.she] stops pleasuring your [pc.asshole+], betraying your desire for more of [npc.her] attention."));
				break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start licking your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pressing your [pc.ass+] down against [npc.name]'s [npc.face], you let out a soft [pc.moan] as you start slowly grinding your [pc.asshole+] down on [npc.her] [npc.lips+].",
							"Repositioning your [pc.hips] so that [npc.name]'s [npc.face] is forced into your [pc.ass+],"
									+ " you let out a soft [pc.moan] as you start gently pressing your [pc.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing your [pc.ass+] down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start frantically grinding your [pc.asshole+] down on [npc.her] [npc.lips+].",
							"Repositioning your [pc.hips] so that [npc.name]'s [npc.face] is forced into your [pc.ass+],"
									+ " you let out [pc.a_moan+] as you start eagerly pressing your [pc.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming your [pc.ass+] down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start forcefully grinding your [pc.asshole+] down on [npc.her] [npc.lips+].",
							"Repositioning your [pc.hips] so that [npc.name]'s [npc.face] is forced into your [pc.ass+],"
									+ " you let out [pc.a_moan+] as you start roughly grinding your [pc.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing your [pc.ass+] down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start frantically grinding your [pc.asshole+] down on [npc.her] [npc.lips+].",
							"Repositioning your [pc.hips] so that [npc.name]'s [npc.face] is forced into your [pc.ass+],"
									+ " you let out [pc.a_moan+] as you start eagerly pressing your [pc.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing your [pc.ass+] down against [npc.name]'s [npc.face], you let out [pc.a_moan+] as you start grinding your [pc.asshole+] down on [npc.her] [npc.lips+].",
							"Repositioning your [pc.hips] so that [npc.name]'s [npc.face] is forced into your [pc.ass+],"
									+ " you let out [pc.a_moan+] as you start pressing your [pc.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] slowly slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] starts gently licking and kissing your [pc.asshole+].",
							" Gently sliding [npc.her] [npc.tongue] out to deliver a long, slow lick to your [pc.ass+],"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of tender kisses on your [pc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] starts happily licking and kissing your [pc.asshole+].",
							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your [pc.ass+],"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of passionate kisses on your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] starts roughly licking and kissing your [pc.asshole+].",
							" Greedily thrusting [npc.her] [npc.tongue] out to deliver a rough, wet lick to your [pc.ass+],"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of forceful kisses on your [pc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] starts happily licking and kissing your [pc.asshole+].",
							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your [pc.ass+],"
									+ " [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of passionate kisses on your [pc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] starts licking and kissing your [pc.asshole+].",
							" Sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to your [pc.ass+], [npc.she] lets out a muffled [npc.moan] as [npc.she] starts planting a series of kisses on your [pc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against you, [npc.she] lets out [npc.a_sob+] as you force [npc.her] [npc.face] ever deeper into your [pc.asshole+].",
							" [npc.Sobbing] and struggling against you, [npc.her] protests prove to be in vain as you force [npc.her] [npc.face] ever deeper into your [pc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Gently enjoy [npc.name]'s [npc.tongue+] in your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.ass] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out a soft [pc.moan], you gently press your [pc.ass+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With a soft [pc.moan], you gently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.ass] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] desperately tries to struggle and pull away from your [pc.asshole+].",
							"Letting out a soft [pc.moan], you gently press your [pc.asshole+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from you.",
							"With a soft [pc.moan], you gently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as you continue pressing your [pc.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck your [pc.ass] into [npc.name]'s [npc.face], letting out a soft [pc.moan] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out a soft [pc.moan], you gently press your [pc.ass+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With a soft [pc.moan], you gently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Enjoy [npc.name]'s [npc.tongue+] in your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.asshole+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you enthusiastically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] desperately tries to struggle and pull away from your [pc.asshole+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.asshole+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from your [pc.ass+].",
							"With [pc.a_moan+], you enthusiastically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as you continue eagerly pressing your [pc.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out [pc.a_moan+], you desperately press your [pc.asshole+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you enthusiastically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Grind your [pc.asshole+] down against [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly grind your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] greedily thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out [pc.a_moan+], you roughly grind your [pc.asshole+] against [npc.name]'s [npc.lips+], and, all too eager to please you, [npc.she] greedily darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you violently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly grind your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] desperately tries to struggle and pull away from your [pc.asshole+].",
							"Letting out [pc.a_moan+], you roughly grind your [pc.asshole+] against [npc.name]'s [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from your [pc.ass+].",
							"With [pc.a_moan+], you violently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as you continue roughly grinding your [pc.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly grind your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"Letting out [pc.a_moan+], you roughly grind your [pc.asshole+] against [npc.name]'s [npc.lips+], and, eager to please you, [npc.she] darts [npc.her] [npc.tongue+] deep into your folds.",
							"With [pc.a_moan+], you violently buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan]."));
					break;
			}
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

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
			return "Try and pull your [pc.asshole+] away from [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.ass] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.asshole+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a soft kiss on your [pc.ass+], before gently sliding [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_sob+], you frantically try to pull your [pc.ass+] away from [npc.name]'s [npc.lips+], but [npc.she] holds you in place,"
									+ " ignoring your protests as [npc.she] continues gently thrusting [npc.her] [npc.tongue] into your [pc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.ass] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] roughly slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.asshole+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a wet kiss on your [pc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_sob+], you frantically try to pull your [pc.ass+] away from [npc.name]'s [npc.lips+], but [npc.she] firmly holds you in place,"
									+ " ignoring your protests as [npc.she] continues roughly thrusting [npc.her] [npc.tongue] into your [pc.asshole+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately try to pull your [pc.ass] away from [npc.name]'s [npc.face], letting out [pc.a_sob+] as [npc.she] greedily slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"Letting out [pc.a_sob+], you desperately try to pull your [pc.asshole+] away from [npc.name]'s [npc.lips+]."
									+ " Ignoring your protests, [npc.she] holds you in place as [npc.she] plants a passionate kiss on your [pc.ass+], before greedily sliding [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_sob+], you frantically try to pull your [pc.ass+] away from [npc.name]'s [npc.lips+], but [npc.she] holds you in place,"
									+ " ignoring your protests as [npc.she] continues eagerly thrusting [npc.her] [npc.tongue] into your [pc.asshole+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Enjoy [npc.name]'s [npc.tongue+] in your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You press your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You press your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You press your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_RECEIVING_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Grind your [pc.asshole+] down against [npc.name]'s [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You eagerly grind your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you frantically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You eagerly grind your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] roughly thrusts [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you frantically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck your [pc.ass] into [npc.name]'s [npc.face], letting out [pc.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into your [pc.asshole+].",
							"You eagerly grind your [pc.ass+] back against [npc.name]'s [npc.lips+], letting out [pc.a_moan+] as [npc.she] enthusiastically slides [npc.her] [npc.tongue+] deep into your [pc.asshole+].",
							"With [pc.a_moan+], you frantically buck your [pc.ass] into [npc.name]'s [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into your [pc.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
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
			return "Get [npc.name] to pull [npc.her] [npc.tongue+] out of your [pc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] head away from your [pc.asshole+], you order [npc.name] to stop giving your [pc.ass] [npc.her] oral attention.",
							"Roughly grinding your [pc.asshole+] into [npc.name]'s [npc.face] one last time, you then pull your [pc.ass] away, before ordering [npc.herHim] to stop."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc.her] head away from your [pc.asshole+], you tell [npc.name] to stop giving your [pc.ass] [npc.her] oral attention.",
							"Pressing your [pc.asshole+] into [npc.name]'s [npc.face] one last time, you then pull your [pc.ass] away, before telling [npc.herHim] to stop."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down [npc.her] [npc.face], [npc.she] lets out [npc.a_sob+] as [npc.she] realises that you aren't finished with [npc.herHim] just yet.",
							" [npc.She] lets out [npc.a_sob+] as [npc.she] continues struggling against you, begging for you to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], betraying [npc.her] desire to give your [pc.asshole+] more of [npc.her] oral attention.",
							" Still hungry for more, [npc.she] lets out [npc.a_moan+] as you put an end to [npc.her] oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
