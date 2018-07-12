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
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class PartnerPenisThighs {
	
	public static final SexAction PARTNER_THIGH_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// NPC can't penetrate if PC is already fucking them, due to physical limitations.
			return Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc.namePos] [npc.cock+] between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hips+], [npc.name] slowly moves [npc.her] grip down to gently [npc.verb(press)] [npc2.namePos] legs together, before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs and thrusting forwards between them.",

							"Slowly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] gently teases the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs, before letting out [npc.a_moan+] and thrusting forwards between them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hips+], [npc.name] eagerly moves [npc.her] grip down to [npc.verb(press)] [npc2.namePos] legs together,"
									+ " before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs and desperately thrusting forwards between them.",

							"Eagerly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs, before letting out [npc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly taking hold of [npc2.namePos] [npc2.hips+], [npc.name] quickly moves [npc.her] grip down to force [npc2.namePos] legs together,"
									+ " before dominantly teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs and violently thrusting forwards between them.",

							"Forcefully pushing [npc2.namePos] [npc2.legs+] together, [npc.name] roughly teases the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs, before letting out [npc.a_moan+] and slamming forwards between them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hips+], [npc.name] eagerly moves [npc.her] grip down to [npc.verb(press)] [npc2.namePos] legs together,"
									+ " before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs and desperately thrusting forwards between them.",

							"Eagerly pushing [npc2.namePos] [npc2.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs, before letting out [npc.a_moan+] and greedily thrusting forwards between them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] [npc2.hips+], [npc.name] moves [npc.her] grip down to [npc.verb(press)] [npc2.namePos] legs together, before teasing the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs and thrusting forwards between them.",

							"Pushing [npc2.namePos] [npc2.legs+] together, [npc.name] teases the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] thighs, before letting out [npc.a_moan+] and thrusting forwards between them."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, gently bucking [npc2.namePos] [npc2.hips] against [npc.name] as [npc2.name] squeeze [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.hips] against [npc.herHim], squeezing [npc2.namePos] thighs down as [npc2.name] [npc2.verb(help)] to stimulate [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.hips] against [npc.name] as [npc2.name] enthusiastically squeeze [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] [npc2.hips] against [npc.herHim], eagerly squeezing [npc2.namePos] thighs down as [npc2.name] [npc2.verb(help)] to stimulate [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, violently bucking [npc2.namePos] [npc2.hips] against [npc.name] as [npc2.name] roughly squeeze [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.hips] against [npc.herHim], roughly squeezing [npc2.namePos] thighs down as [npc2.name] forcefully stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, eagerly bucking [npc2.namePos] [npc2.hips] against [npc.name] as [npc2.name] enthusiastically squeeze [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] rapidly bucking [npc2.namePos] [npc2.hips] against [npc.herHim], eagerly squeezing [npc2.namePos] thighs down as [npc2.name] [npc2.verb(help)] to stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, bucking [npc2.namePos] [npc2.hips] against [npc.name] as [npc2.name] squeeze [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.herHim], squeezing [npc2.namePos] thighs down as [npc2.name] [npc2.verb(help)] to stimulate [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.name] to stop as [npc2.name] weakly struggles against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] [npc.cock+], tears running down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.name] alone."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs, [npc.name] then [npc.verb(start)] bucking [npc.her] [npc.hips] forwards, softly bumping [npc.her] groin against [npc2.name] with every [npc2.verb(thrust)].",

					"Slowly pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out a little [npc.moan] as [npc.she] fucks [npc2.namePos] thighs.",

					"Sliding [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(let)] out a little [npc.moan] before starting to gently buck [npc.her] [npc.hips] against [npc2.herHim], breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly fucks [npc2.namePos] thighs."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] face as [npc2.name] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.name] alone.",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] face as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop.",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] cheeks, [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs, [npc.name] then [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against [npc2.name] with every [npc2.verb(thrust)].",

					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Eagerly thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] against [npc2.herHim],"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] greedily fuck [npc2.namePos] thighs."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] face as [npc2.name] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.name] alone.",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] face as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop.",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] cheeks, [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] thighs, [npc.name] then [npc.verb(start)] violently pumping [npc.her] [npc.hips] forwards, brutally grinding [npc.her] groin against [npc2.name] with every [npc2.verb(thrust)].",

					"Violently thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] brutally fucks [npc2.namePos] thighs.",

					"Ruthlessly thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently slamming [npc.her] [npc.hips] against [npc2.herHim],"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] roughly fucks [npc2.namePos] thighs."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.herHim], letting out [npc2.a_moan+] as [npc2.name] enthusiastically grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.herHim],"
									+ " eagerly begging for [npc.name] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] around [npc.her] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.her] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] face as [npc2.name] weakly [npc2.verb(beg)] for [npc.name] to leave [npc2.name] alone.",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] face as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop.",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] cheeks, [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] grip [npc2.namePos] thighs down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] against [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.namePos] thighs.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.namePos] thighs as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs, [npc.name] then [npc.verb(start)] bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against [npc2.name] with every [npc2.verb(thrust)].",

					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] fucks [npc2.namePos] thighs.",

					"thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips] against [npc2.herHim], breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.namePos] thighs."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(implore)] [npc.herHim] to continue.",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue.",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(order)] [npc.name] to continue.",

							" [npc2.Name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.name] aggressively squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(implore)] [npc.name] to continue.",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(beg)] for [npc.name] to continue.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs, [npc.name] then [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] forwards, slamming [npc.her] groin against [npc2.name] with every [npc2.verb(thrust)].",

					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Eagerly thrusting [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] against [npc2.herHim], breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.namePos] thighs."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out a soft [npc2.moan] as [npc2.name] gently [npc2.verb(implore)] [npc.herHim] to continue.",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue.",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(order)] [npc.name] to continue.",

							" [npc2.Name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.name] aggressively squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(implore)] [npc.name] to continue.",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.her] groin, [npc2.name] [npc2.verb(beg)] for [npc.name] to continue.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.her] [npc.cock] away from [npc.her] thighs.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately trying, and failing, to pull [npc.her] [npc.cock] out from between [npc2.namePos] thighs, [npc.name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc2.verb(beg)] to be released.",

					"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, pleading for [npc2.name] to leave [npc.herHim] alone.",

					"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.herHim], pleading for [npc2.name] to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.herHim], letting out a soft [npc2.moan] as [npc2.name] gently squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, before squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, slowly grinding [npc2.herself] against [npc.herHim] and softly [npc2.moaning] as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] roughly slam [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] violently squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, before continuing to roughly squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests,"
									+ " roughly grinding [npc2.herself] against [npc.name] and [npc2.moaning+] out loud as [npc2.name] violently squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, before continuing to energetically squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, eagerly grinding [npc2.herself] against [npc.herHim] and [npc2.moaning+] out loud as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_THIGH_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out from between [npc2.namePos] legs and stop fucking [npc2.her] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out from between [npc2.namePos] legs, [npc.name] dominantly slides [npc.her] [npc.cockHead] up and down over [npc2.namePos] thighs one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep between [npc2.namePos] [npc2.legs+] one last time, [npc.name] then yanks [npc.her] [npc.cock] back out from between [npc2.namePos] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out from between [npc2.namePos] legs, [npc.name] slides [npc.her] [npc.cockHead] up and down over [npc2.namePos] thighs one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep between [npc2.namePos] [npc2.legs+] one last time, [npc.name] then slides [npc.her] [npc.cock] back out from between [npc2.namePos] thighs."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.sobVerb+] as [npc.name] [npc.verb(pull)] back, and find [npc2.herself] unable to stop crying as [npc2.name] [npc.verb(continue)] to weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.herHim], tears streaming down [npc2.namePos] [npc2.face] as [npc.name] stops using [npc2.namePos] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out from between [npc2.namePos] thighs.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.namePos] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_THIGH_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS)) {
				return (Sex.isSubHasEqualControl() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)));
			} else {
				return false;
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Receive thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
									+ " letting out a little [npc2.moan] before gently bucking [npc2.namePos] [npc2.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between [npc2.namePos] thighs.",

							"Lining [npc.namePos] [npc.cock+] up between [npc2.namePos] [npc2.legs], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.herHim],"
									+ " softly [npc2.moaning] as [npc2.name] gently [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly slide [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
									+ " letting out [npc2.a_moan+] before suddenly bucking [npc2.namePos] [npc2.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between [npc2.namePos] thighs.",

							"Lining [npc.namePos] [npc.cock+] up between [npc2.namePos] [npc2.legs], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.herHim],"
									+ " [npc2.moaning+] as [npc2.name] greedily [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc2.herself] against [npc.name], [npc2.name] roughly position [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
									+ " letting out [npc2.a_moan+] before violently slamming [npc2.namePos] [npc2.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between [npc2.namePos] thighs.",

							"Lining [npc.namePos] [npc.cock+] up between [npc2.namePos] [npc2.legs], [npc2.name] violently slam [npc2.namePos] [npc2.hips] against [npc.herHim],"
									+ " [npc2.moaning+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] thighs."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly slide [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
									+ " letting out [npc2.a_moan+] before suddenly bucking [npc2.namePos] [npc2.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between [npc2.namePos] thighs.",

							"Lining [npc.namePos] [npc.cock+] up between [npc2.namePos] [npc2.legs], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.herHim],"
									+ " [npc2.moaning+] as [npc2.name] greedily [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] thighs."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slide [npc.her] [npc.cock+] between [npc2.namePos] [npc2.legs+],"
									+ " letting out [npc2.a_moan+] before suddenly bucking [npc2.namePos] [npc2.hips] against [npc.herHim] and forcing [npc.her] [npc.cock+] between [npc2.namePos] thighs.",

							"Lining [npc.namePos] [npc.cock+] up between [npc2.namePos] [npc2.legs], [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] against [npc.herHim],"
									+ " [npc2.moaning+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] thighs."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a soft [npc.moan] in response, gently bucking [npc.her] [npc.hips] into [npc2.name] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] thighs.",

							" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs as [npc.she] [npc.verb(start)] gently fucking them."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] into [npc2.name] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] thighs.",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs as [npc.she] [npc.verb(start)] energetically fucking them."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, and, seeking to remind [npc2.name] who's in charge, [npc.she] roughly slams [npc.her] [npc.hips] into [npc2.name] as [npc.she] [npc.verb(start)] to ruthlessly fuck [npc2.namePos] thighs.",

							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs as [npc.she] [npc.verb(start)] ruthlessly fucking them."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, eagerly bucking [npc.her] [npc.hips] into [npc2.name] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] thighs.",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs as [npc.she] [npc.verb(start)] energetically fucking them."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] in response, bucking [npc.her] [npc.hips] into [npc2.name] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] thighs.",

							" With [npc.a_moan+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] between [npc2.namePos] thighs as [npc.she] [npc.verb(start)] fucking them."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] between [npc2.namePos] thighs, and, struggling against [npc2.name] in vain, [npc.she] desperately [npc2.verb(beg)] [npc2.name] to stop.",

							" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep between [npc2.namePos] thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Gentle thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Gently [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {
		
			return UtilText.returnStringAtRandom(
					"Gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

					"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.name], squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Slowly thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.name] softly [npc2.moanVerb] as [npc2.name] squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] greedily squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.name], eagerly squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.name] [npc2.moanVerb+] as [npc2.name] greedily squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Rough thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Roughly [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Violently slamming [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

					"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.name], roughly squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Roughly thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.name] [npc2.moanVerb+] as [npc2.name] forcefully squeezes [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].");
			
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Normal thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] between [npc2.namePos] [npc2.thighs].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep between [npc2.namePos] thighs.",

					"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] thighs.",

					"thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.name] [npc2.moanVerb+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] thighs.");
			
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Eager thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] between [npc2.namePos] [npc2.thighs].";
		}

		@Override
		public String getDescription() {

			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] greedily squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] against [npc.name], eagerly squeezing [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+] as [npc.she] fucks [npc2.namePos] thighs.",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.name] [npc2.moanVerb+] as [npc2.name] greedily squeeze [npc2.namePos] [npc2.legs+] down around [npc.her] [npc.cock+].");
					
		}
	};
	
	public static final SexAction PLAYER_THIGH_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
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
			return "Resist thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] thighs away from [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], before [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+], weakly trying to pull [npc.namePos] [npc.cock] out from between [npc2.namePos] [npc2.legs] as [npc.she] [npc.verb(continue)] gently fucking [npc2.namePos] thighs.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] sliding in and out between [npc2.namePos] thighs.",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sobVerb] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding in and out between [npc2.namePos] thighs."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], before [npc2.name] suddenly [npc.verb(let)] out [npc2.a_sob+], weakly trying to pull [npc.namePos] [npc.cock] out from between [npc2.namePos] [npc2.legs] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.namePos] thighs.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out between [npc2.namePos] thighs.",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sobVerb] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out between [npc2.namePos] thighs."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], before [npc2.name] suddenly [npc.verb(let)] out [npc2.a_sob+], weakly trying to pull [npc.namePos] [npc.cock] out from between [npc2.namePos] [npc2.legs] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.namePos] thighs.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] away from [npc.name],"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out between [npc2.namePos] thighs.",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sobVerb] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out between [npc2.namePos] thighs."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_THIGH_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isSubHasEqualControl() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Stop thigh sex";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out from between [npc2.namePos] thighs.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.namePos] [npc.cock] out from between [npc2.namePos] [npc2.legs], [npc2.name] growl at [npc.name] as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.namePos] thighs.",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before [npc2.name] suddenly yank [npc.her] [npc.cock] out from between [npc2.namePos] thighs."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.cock] out from between [npc2.namePos] [npc2.legs], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.namePos] thighs.",

							"You lean into [npc.name], causing [npc.herHim] to inhale [npc2.namePos] [npc2.scent] before [npc2.name] slide [npc.her] [npc.cock] out from between [npc2.namePos] thighs."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that [npc2.name] haven't finished with [npc.herHim] yet.",

							" With [npc.a_sob+], [npc.name] [npc.verb(continue)] to protest and struggle against [npc2.name] as [npc2.name] hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] thighs.",

							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] thighs."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
