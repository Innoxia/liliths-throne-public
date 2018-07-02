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
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class PartnerPenisUrethraVagina {
	
	public static final SexAction PARTNER_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] and [npc2.verb(start)] fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing [npc.her] [npc.hips] forwards,"
									+ " sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a slow, steady pressure, [npc.she] gently  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] forwards,"
									+ " forcing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a forceful [npc2.verb(thrust)], [npc.she] roughly slams [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards,"
									+ " greedily sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a determined [npc2.verb(thrust)], [npc.she] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] outer labia, [npc.name] [npc.verb(let)] out [npc.a_moan+] before bucking [npc.her] [npc.hips] forwards, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+].",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] between [npc2.namePos] [npc2.legs+], and with a [npc2.verb(thrust)] of [npc.her] [npc.hips], [npc.she]  [npc.verb(sink)] [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], violently thrusting [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.hips] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] enters [npc2.herHim], and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to pull out as [npc2.name] weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.namePos] unwanted penetration, tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] into [npc2.herHim], softly pressing [npc.her] groin against [npc2.namePos]s with every [npc2.verb(thrust)] as [npc.she] slowly fucks [npc2.herHim].",

					"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] softly [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out a little [npc.moan] as [npc.she] gently fucks [npc2.herHim].",

					"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently buck [npc.her] [npc.hips], breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] enthusiastically fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] greedily [npc2.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] fucking [npc2.herHim].",

					"Enthusiastically thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips],"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] eagerly fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] violently pumping [npc.her] [npc.hips], grinding into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] brutally fucks [npc2.herHim].",

					"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] slamming [npc.her] [npc.hips] into [npc2.herHim], letting out [npc.a_moan+] as [npc.she] roughly fucks [npc2.herHim].",

					"Ruthlessly thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] roughly fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] enthusiastically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, eagerly thrusting [npc2.namePos] [npc2.hips] into [npc.herHim], [npc2.name] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name],"
									+ " eagerly begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.name] weakly [npc2.verb(try)] to push [npc.name] away, tears streaming down [npc2.namePos] [npc2.face] as [npc2.name] [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.Sobbing] in distress, and with tears running down [npc2.namePos] [npc2.face], [npc2.name] weakly struggle against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, thrusting [npc2.namePos] [npc2.hips] into [npc.name], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to carry on fucking [npc2.name] like this.",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.namePos] [npc2.hips+] against [npc.name], begging for [npc.herHim] to continue fucking [npc2.name] as [npc2.namePos] movements [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] fucks [npc2.herHim].",

					"Pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] fucking [npc2.herHim].",

					"thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Desperately sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips] into [npc2.herHim], slamming into [npc2.namePos] groin with every [npc2.verb(thrust)] as [npc.she] enthusiastically fucks [npc2.herHim].",

					"Eagerly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] against [npc2.herHim], letting out [npc.a_moan+] as [npc.she] [npc.verb(continue)] desperately fucking [npc2.herHim].",

					"Eagerly thrusting [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] frantically bucking [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc.she] fucks [npc2.herHim]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] slowly buck [npc2.namePos] [npc2.hips] in response, letting out a soft [npc2.moan] as [npc2.name] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.herHim].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] slowly [npc2.verb(grind)] [npc2.herself] against [npc.name], softly [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] roughly demand that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, roughly thrusting [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grind)] [npc2.herself] against [npc.name], ordering [npc.herHim] to continue as [npc2.namePos] aggressive movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] buck [npc2.namePos] [npc2.hips] in response, letting out [npc2.a_moan+] as [npc2.name] [npc2.verb(start)] imploring [npc.name] to continue fucking [npc2.herHim].",

							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], and, pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(beg)] for [npc.herHim] to continue fucking [npc2.herHim].",

							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.herself] against [npc.name], [npc2.moaning] for [npc.herHim] to continue as [npc2.namePos] movements cause [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist sex";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					" Desperately trying, and failing, to pull [npc.her] [npc.cock] free from [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] out [npc.a_sob+], pushing against [npc2.name] as [npc.she] weakly [npc2.verb(beg)]s to be released.",

					" [npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, pleading for [npc2.name] to take [npc2.namePos] [npc2.vaginaUrethra+] off [npc.her] [npc.cock].",

					" [npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.herHim], pleading for [npc2.name] to let go of [npc.her] [npc.cock]."));
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.name], letting out a soft [npc2.moan] as [npc2.name] [npc.verb(continue)] gently fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] gently [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, slowly grinding [npc2.herself] against [npc.herHim] and softly [npc2.moaning] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] roughly slam [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] violently fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] forcefully [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to roughly fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, roughly grinding [npc2.herself] against [npc.herHim] and [npc2.moaning+] out loud as [npc2.name] violently [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Ignoring [npc.her] protests, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.hips] out against [npc.name], letting out [npc2.a_moan+] as [npc2.name] [npc.verb(continue)] happily fucking [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and, totally ignoring [npc.namePos] protests,"
									+ " [npc2.Name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] out against [npc.her] groin, before continuing to energetically fuck [npc2.herself] on [npc.her] [npc.cock+].",

							" [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.namePos] protests, eagerly grinding [npc2.herself] against [npc.herHim] and [npc2.moaning+] out loud as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop fucking [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+],"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between [npc2.namePos] folds one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep inside of [npc2.name] one last time, [npc.name] then yanks [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.vaginaUrethra+], putting an end to the rough fucking."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down between [npc2.namePos] folds one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep inside of [npc2.name] one last time, [npc.name] then slides [npc.her] [npc.cock+] back out of [npc2.namePos] [npc2.vaginaUrethra+], putting an end to [npc2.namePos] fucking."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.namePos] [npc2.vaginaUrethra], and [npc2.name] [npc.verb(continue)] crying and protesting as [npc2.name] [npc.verb(continue)] to weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] withdraws from [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.vaginaUrethra+], eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only [npc2.verb(start)] fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// [npc2.name] can't penetrate if you're already fucking [npc2.namePos] partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)));
			} else {
				return false; //(Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this))) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.URETHRA_VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy Urethra Penetrated";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to sink [npc.her] [npc.cock+] into [npc2.namePos] pussy's [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] over [npc2.namePos] outer labia,"
									+ " letting out a little [npc2.moan] before gently bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.vaginaUrethra+].",

							"Lining [npc2.namePos] [npc2.vaginaUrethra+] up to [npc.namePos] [npc.cock+], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out a soft [npc2.moan] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
									+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.vaginaUrethra+].",

							"Lining [npc2.namePos] [npc2.vaginaUrethra+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
									+ " letting out [npc2.a_moan+] before roughly slamming [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.vaginaUrethra+].",

							"Lining [npc2.namePos] [npc2.vaginaUrethra+] up to [npc.namePos] [npc.cock+], [npc2.name] violently slam [npc2.namePos] [npc2.hips] into [npc.her] groin, letting out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(start)] fucking [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
									+ " letting out [npc2.a_moan+] before desperately bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.vaginaUrethra+].",

							"Lining [npc2.namePos] [npc2.vaginaUrethra+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] guide [npc.her] [npc.cock+] up to [npc2.namePos] outer labia,"
									+ " letting out [npc2.a_moan+] before bucking [npc2.namePos] [npc2.hips] forwards and forcing [npc.herHim] to penetrate [npc2.namePos] [npc2.vaginaUrethra+].",

							"Lining [npc2.namePos] [npc2.vaginaUrethra+] up to [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] forwards, letting out [npc2.a_moan+] as [npc2.name] penetrate [npc2.herself] on [npc.her] [npc.cock+]."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] enters [npc2.herHim], gently bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] [npc2.vaginaUrethra+].",

							" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], and, seeking to remind [npc2.name] who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] into [npc2.namePos] groin, seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly fucking [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(start)] energetically fucking [npc2.herHim]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] enters [npc2.herHim], bucking [npc.her] [npc.hips] into [npc2.namePos] groin as [npc.she] [npc.verb(start)] fucking [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc.a_moan+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.namePos] groin, sinking [npc.her] [npc.cock+] into [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(start)] fucking [npc2.herHim]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] inside of [npc2.herHim], and, struggling against [npc2.herHim], [npc.she] desperately tries to pull [npc.her] [npc.cock+] free from [npc2.namePos] [npc2.vaginaUrethra+].",

							" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Gentle urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Gently pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

					"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

					"Slowly thrusting [npc2.namePos] [npc2.hips] against [npc.name], a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].");
			
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Rough urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.herself] on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Violently slamming [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

					"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] against [npc.name], forcing [npc.her] [npc.cock+] ever deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

					"Roughly thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].");
			
		}

	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.vaginaUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

					"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

					"thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_RIDING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Eager urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly buck [npc2.namePos] hips against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] into [npc2.namePos] [npc2.vaginaUrethra].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.hips] out against [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+].",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips], forcing [npc.namePos] [npc.cock+] ever deeper into [npc2.namePos] [npc2.vaginaUrethra+].",

					"Enthusiastically thrusting [npc2.namePos] [npc2.hips] against [npc.name], [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist urethral fuck";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.vaginaUrethra+] away from [npc.namePos] [npc.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(continue)] gently fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] slowly sliding in and out of [npc2.namePos] [npc2.vaginaUrethra+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding in and out of [npc2.namePos] [npc2.vaginaUrethra+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.herHim].",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.hips] back from [npc.namePos] unwanted penetration,"
									+ " struggling in desperation as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming in and out of [npc2.namePos] [npc2.vaginaUrethra+].",

							"Trying desperately to pull [npc2.namePos] [npc2.hips] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming deep into [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
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
			return "Stop pussy urethral";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out of [npc2.namePos] pussy's [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+], [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop fucking [npc2.herHim].",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that [npc2.name] haven't finished with [npc.herHim] just yet.",

							" With [npc.a_sob+], [npc.name] [npc.verb(continue)] to protest and struggle against [npc2.name] as [npc2.name] hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc2.name] stop [npc.herHim] from fucking [npc2.namePos] [npc2.vaginaUrethra+].",

							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] [npc2.vaginaUrethra+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
