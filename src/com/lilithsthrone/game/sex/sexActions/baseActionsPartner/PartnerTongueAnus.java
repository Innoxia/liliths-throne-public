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
			return "Slide [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.asshole+] and [npc2.verb(start)] eating [npc2.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] plants a series soft kisses on [npc2.namePos] cheeks, before slowly, but firmly, sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.asshole+].",

							"Planting a series of soft kisses on [npc2.namePos] [npc2.ass+], [npc.name] gives [npc2.namePos] [npc2.asshole+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] plants a series of passionate kisses on [npc2.namePos] cheeks, before desperately sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.asshole+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.ass+], [npc.name] gives [npc2.namePos] [npc2.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] plants a series of forceful kisses on [npc2.namePos] cheeks, before greedily sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.asshole+].",

							"Planting a series of forceful kisses on [npc2.namePos] [npc2.ass+], [npc.name] gives [npc2.namePos] [npc2.asshole+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] plants a series of passionate kisses on [npc2.namePos] cheeks, before desperately sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.asshole+].",

							"Planting a series of passionate kisses on [npc2.namePos] [npc2.ass+], [npc.name] gives [npc2.namePos] [npc2.asshole+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] plants a series of kisses on [npc2.namePos] cheeks, before sliding [npc.her] [npc.tongue+] into [npc2.namePos] [npc2.asshole+].",

							"Planting a series of kisses on [npc2.namePos] [npc2.ass+], [npc.name] gives [npc2.namePos] [npc2.asshole+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Gently bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, roughly thrusting [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] as [npc2.name] order [npc.herHim] to keep going.",

							" Roughly thrusting [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] in an eager response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, commanding [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Desperately bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to keep going.",

							" Bucking [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face] in response to [npc.her] oral attention, [npc2.name] [npc2.moan] out loud, begging for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] frantically [npc2.verb(try)] to wriggle away from [npc.her] unwanted oral attention, [npc2.sobbing] and squirming as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.name] alone.",

							" [npc2.A_sob+] bursts out from [npc2.namePos] mouth, and, struggling against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to take [npc.her] [npc.tongue] away from [npc2.namePos] asshole."));
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
			return "Gentle anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently lick [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] plants a series of delicate kisses on [npc2.namePos] [npc2.ass+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck back against [npc.her] [npc.face].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] [npc2.ass+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] [npc2.ass+], causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] plants a series of delicate kisses on [npc2.namePos] [npc2.ass+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] [npc2.ass+], causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull away from [npc.her] unwanted oral attention.",

							"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] [npc2.ass+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] plants a series of delicate kisses on [npc2.namePos] [npc2.ass+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] buck back against [npc.her] [npc.face].",

							"Sliding [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc2.namePos] [npc2.ass+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"Drawing [npc.her] [npc.tongue+] out from [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] gently kissing and nuzzling against [npc2.namePos] [npc2.ass+], causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
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
			return "[npc.verb(continue)] thrusting [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck back against [npc.her] [npc.face].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull away from [npc.her] unwanted oral attention.",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] buck back against [npc.her] [npc.face].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
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
			return "Rough anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Roughly [npc2.verb(thrust)] [npc2.namePos] tongue into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck back against [npc.her] [npc.face].",

							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim].",

							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull away from [npc.her] unwanted oral attention.",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and"
									+ " causing [npc2.name] to let out [npc2.a_sob+] as [npc2.name] [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.herHim]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Grinding [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] roughly drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] buck back against [npc.her] [npc.face].",

							"Roughly grinding [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass+], [npc.name] aggressively drives [npc.her] [npc.tongue+] as deep as possible into [npc2.namePos] [npc2.asshole+], drawing out [npc2.a_moan+] from between [npc2.namePos] [npc2.lips+].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and causing [npc2.name] to [npc2.moan+] and buck back into [npc.her] [npc.face]."));
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
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.tongue] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] [npc2.ass+], but [npc2.name] [npc.verb(continue)] gently pressing [npc2.namePos] [npc2.asshole+] back against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] [npc2.ass+], gently grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.ass+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] press [npc2.namePos] [npc2.asshole+] against [npc.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] [npc2.ass+], but [npc2.name] roughly slam [npc2.namePos] [npc2.asshole+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] [npc2.ass+], roughly grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.ass+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] against [npc.her] [npc2.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] tries to pull [npc.her] [npc.face] away from [npc2.namePos] [npc2.ass+], but [npc2.name] [npc.verb(continue)] eagerly thrusting [npc2.namePos] [npc2.asshole+] against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.name] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",

							"With [npc.a_sob+], [npc.name] tries to pull away from [npc2.herHim], but [npc2.name] quickly [npc2.verb(force)] [npc.her] [npc.face] right back into [npc2.namePos] [npc2.ass+], eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.name] ignore [npc.her] struggles.",

							"Tears [npc2.verb(start)] to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] tries to pull [npc.her] mouth away from [npc2.namePos] [npc2.ass+],"
									+ " but [npc2.name] quickly shift position, ignoring [npc.her] protests as [npc2.name] eagerly press [npc2.namePos] [npc2.asshole+] against [npc.her] [npc2.lips+]."));
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
			return "[npc.verb(continue)] anilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] thrusting [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] gently buck back against [npc.her] [npc.face].",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(grind)] back against [npc.her] [npc.face].",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly slam [npc2.namePos] [npc2.ass] back against [npc.her] [npc.face].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck back against [npc.her] [npc.face].",

							"Pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly press [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face].",

							"[npc.Name] buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+]."));
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
			return "Eagerly [npc2.verb(thrust)] [npc.namePos] [npc.tongue] into [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] gently buck back against [npc.her] [npc.face].",

							"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face].",

							"[npc.Name] eagerly buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of soft [npc2.moans] from between [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(grind)] back against [npc.her] [npc.face].",

							"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] roughly slam [npc2.namePos] [npc2.ass] back against [npc.her] [npc.face].",

							"[npc.Name] eagerly buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.her] [npc.face] into [npc2.namePos] [npc2.ass+], [npc.name] greedily drives [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly buck back against [npc.her] [npc.face].",

							"Desperately pressing [npc.her] [npc.lips+] into [npc2.namePos] [npc2.ass+], [npc.name] eagerly drives [npc.her] [npc.tongue] as deep as possible into [npc2.namePos] [npc2.asshole+],"
									+ " causing [npc2.name] to let out [npc2.a_moan+] as [npc2.name] eagerly press [npc2.namePos] [npc2.ass] out against [npc.her] [npc.face].",

							"[npc.Name] eagerly buries [npc.her] [npc.face] in [npc.namePos] [npc.ass+], before greedily thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+] and drawing a series of [npc2.moans+] from between [npc2.namePos] [npc2.lips+]."));
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
			return "Pull [npc.namePos] [npc.tongue] out of [npc2.namePos] [npc2.asshole+] and stop eating [npc2.herHim] out.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.asshole+].",

						"Giving [npc2.namePos] [npc2.asshole+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.ass+]."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.asshole+].",

						"Giving [npc2.namePos] [npc2.asshole+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.ass+]."));
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
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] stops pleasuring [npc2.namePos] [npc2.asshole], betraying [npc2.namePos] desire for more of [npc.her] attention.",

						" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.she] stops pleasuring [npc2.namePos] [npc2.asshole+], betraying [npc2.namePos] desire for more of [npc.her] attention."));
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
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Receive anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start licking [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pressing [npc2.namePos] [npc2.ass+] down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] slowly grinding [npc2.namePos] [npc2.asshole+] down on [npc.her] [npc.lips+].",

							"Repositioning [npc2.namePos] [npc2.hips] so that [npc.namePos] [npc.face] is forced into [npc2.namePos] [npc2.ass+],"
									+ " [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently pressing [npc2.namePos] [npc2.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc2.namePos] [npc2.ass+] down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] frantically grinding [npc2.namePos] [npc2.asshole+] down on [npc.her] [npc.lips+].",

							"Repositioning [npc2.namePos] [npc2.hips] so that [npc.namePos] [npc.face] is forced into [npc2.namePos] [npc2.ass+],"
									+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] eagerly pressing [npc2.namePos] [npc2.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc2.namePos] [npc2.ass+] down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] forcefully grinding [npc2.namePos] [npc2.asshole+] down on [npc.her] [npc.lips+].",

							"Repositioning [npc2.namePos] [npc2.hips] so that [npc.namePos] [npc.face] is forced into [npc2.namePos] [npc2.ass+],"
									+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] roughly grinding [npc2.namePos] [npc2.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pressing [npc2.namePos] [npc2.ass+] down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] frantically grinding [npc2.namePos] [npc2.asshole+] down on [npc.her] [npc.lips+].",

							"Repositioning [npc2.namePos] [npc2.hips] so that [npc.namePos] [npc.face] is forced into [npc2.namePos] [npc2.ass+],"
									+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] eagerly pressing [npc2.namePos] [npc2.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.namePos] [npc2.ass+] down against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] grinding [npc2.namePos] [npc2.asshole+] down on [npc.her] [npc.lips+].",

							"Repositioning [npc2.namePos] [npc2.hips] so that [npc.namePos] [npc.face] is forced into [npc2.namePos] [npc2.ass+],"
									+ " [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] pressing [npc2.namePos] [npc2.asshole+] down against [npc.her] [npc.lips+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] slowly slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] gently licking and kissing [npc2.namePos] [npc2.asshole+].",

							" Gently sliding [npc.her] [npc.tongue] out to deliver a long, slow lick to [npc2.namePos] [npc2.ass+],"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of tender kisses on [npc2.namePos] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] happily licking and kissing [npc2.namePos] [npc2.asshole+].",

							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] [npc2.ass+],"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of passionate kisses on [npc2.namePos] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] roughly licking and kissing [npc2.namePos] [npc2.asshole+].",

							" Greedily thrusting [npc.her] [npc.tongue] out to deliver a rough, wet lick to [npc2.namePos] [npc2.ass+],"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of forceful kisses on [npc2.namePos] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] greedily slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] happily licking and kissing [npc2.namePos] [npc2.asshole+].",

							" Greedily sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] [npc2.ass+],"
									+ " [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of passionate kisses on [npc2.namePos] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] slides [npc.her] [npc.tongue+] out, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] licking and kissing [npc2.namePos] [npc2.asshole+].",

							" Sliding [npc.her] [npc.tongue] out to deliver a long, wet lick to [npc2.namePos] [npc2.ass+], [npc.she] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] planting a series of kisses on [npc2.namePos] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Struggling against [npc2.herHim], [npc.she] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.face] ever deeper into [npc2.namePos] [npc2.asshole+].",

							" [npc.Sobbing] and struggling against [npc2.herHim], [npc.her] protests prove to be in vain as [npc2.name] [npc2.verb(force)] [npc.her] [npc.face] ever deeper into [npc2.namePos] [npc2.asshole+]."));
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
			return "Gentle anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Gently [npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.ass+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With a soft [npc2.moan], [npc2.name] gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.asshole+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from [npc2.herHim].",

							"With a soft [npc2.moan], [npc2.name] gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] pressing [npc2.namePos] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out a soft [npc2.moan] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out a soft [npc2.moan], [npc2.name] gently press [npc2.namePos] [npc2.ass+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With a soft [npc2.moan], [npc2.name] gently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
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
			return "[npc2.verb(enjoy)] anilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] enthusiastically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] desperately tries to pull away from [npc2.namePos] [npc2.ass+].",

							"With [npc2.a_moan+], [npc2.name] enthusiastically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] eagerly pressing [npc2.namePos] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] desperately press [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] enthusiastically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
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
			return "Rough anilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] down against [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] greedily [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, all too eager to please [npc2.herHim], [npc.she] greedily darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] violently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] eagerly darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] desperately tries to struggle and [npc2.verb(pull)] away from [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, [npc.sobbing] and struggling in distress, [npc.she] frantically tries to pull away from [npc2.namePos] [npc2.ass+].",

							"With [npc2.a_moan+], [npc2.name] violently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] scrunches [npc.her] [npc.eyes] closed in response,"
									+ " letting out a muffled [npc.sob] as [npc2.name] [npc.verb(continue)] roughly grinding [npc2.namePos] [npc2.asshole+] against [npc.her] [npc.lips+]."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You roughly [npc2.verb(grind)] [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_moan+], [npc2.name] roughly [npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] against [npc.namePos] [npc.lips+], and, eager to please [npc2.herHim], [npc.she] darts [npc.her] [npc.tongue+] deep into [npc2.namePos] folds.",

							"With [npc2.a_moan+], [npc2.name] violently buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] darts [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan]."));
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
			return "Resist anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.asshole+] away from [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.asshole+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a soft kiss on [npc2.namePos] [npc2.ass+], before gently sliding [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass+] away from [npc.namePos] [npc.lips+], but [npc.she] holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] gently thrusting [npc.her] [npc.tongue] into [npc2.namePos] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] roughly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.asshole+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a wet kiss on [npc2.namePos] [npc2.ass+], before roughly thrusting [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass+] away from [npc.namePos] [npc.lips+], but [npc.she] firmly holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] roughly thrusting [npc.her] [npc.tongue] into [npc2.namePos] [npc2.asshole+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.namePos] [npc.face], letting out [npc2.a_sob+] as [npc.she] greedily slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"Letting out [npc2.a_sob+], [npc2.name] desperately [npc2.verb(try)] to pull [npc2.namePos] [npc2.asshole+] away from [npc.namePos] [npc.lips+]."
									+ " Ignoring [npc2.namePos] protests, [npc.she] holds [npc2.name] in place as [npc.she] plants a passionate kiss on [npc2.namePos] [npc2.ass+], before greedily sliding [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_sob+], [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass+] away from [npc.namePos] [npc.lips+], but [npc.she] holds [npc2.name] in place,"
									+ " ignoring [npc2.namePos] protests as [npc.she] [npc.verb(continue)] eagerly thrusting [npc.her] [npc.tongue] into [npc2.namePos] [npc2.asshole+]."));
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
			return "[npc2.verb(enjoy)] anilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(enjoy)] [npc.namePos] [npc.tongue+] in [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You press [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You press [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You press [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
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
			return "Eager anilingus";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(grind)] [npc2.namePos] [npc2.asshole+] down against [npc.namePos] [npc.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] gently slides [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] slowly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] roughly [npc2.verb(thrust)]s [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] greedily slides [npc.her] [npc.tongue] out in response,"
									+ " roughly thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], letting out [npc2.a_moan+] as [npc.she] eagerly slides [npc.her] [npc.tongue] deep into [npc2.namePos] [npc2.asshole+].",

							"You eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.ass+] back against [npc.namePos] [npc.lips+], letting out [npc2.a_moan+] as [npc.she] enthusiastically slides [npc.her] [npc.tongue+] deep into [npc2.namePos] [npc2.asshole+].",

							"With [npc2.a_moan+], [npc2.name] frantically buck [npc2.namePos] [npc2.ass] into [npc.namePos] [npc.face], and [npc.she] eagerly slides [npc.her] [npc.tongue] out in response,"
									+ " thrusting deep into [npc2.namePos] [npc2.asshole+] and letting out a muffled [npc.moan] of [npc.her] own."));
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
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop anilingus";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.tongue+] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] head away from [npc2.namePos] [npc2.asshole+], [npc2.name] order [npc.name] to stop giving [npc2.namePos] [npc2.ass] [npc.her] oral attention.",

							"Roughly grinding [npc2.namePos] [npc2.asshole+] into [npc.namePos] [npc.face] one last time, [npc2.name] then [npc2.verb(pull)] [npc2.namePos] [npc2.ass] away, before ordering [npc.herHim] to stop."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc.her] head away from [npc2.namePos] [npc2.asshole+], [npc2.name] tell [npc.name] to stop giving [npc2.namePos] [npc2.ass] [npc.her] oral attention.",

							"Pressing [npc2.namePos] [npc2.asshole+] into [npc.namePos] [npc.face] one last time, [npc2.name] then [npc2.verb(pull)] [npc2.namePos] [npc2.ass] away, before telling [npc.herHim] to stop."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears streaming down [npc.her] [npc.face], [npc.she] [npc.verb(let)] out [npc.a_sob+] as [npc.she] realises that [npc2.name] aren't finished with [npc.herHim] just yet.",

							" [npc.She] [npc.verb(let)] out [npc.a_sob+] as [npc.she] [npc.verb(continue)] struggling against [npc2.herHim], begging for [npc2.name] to let [npc.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], betraying [npc.her] desire to give [npc2.namePos] [npc2.asshole+] more of [npc.her] oral attention.",

							" Still hungry for more, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] put an end to [npc.her] oral fun."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
