package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class ClitMouth {
	
	public static final SexAction FORCE_SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Clit attention";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to start sucking on your clit.";
		}

		@Override
		public String getDescription() {
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to gently grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Gently pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");

				case SUB_EAGER:
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to eagerly grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] greedily [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Eagerly pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to roughly grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(grind)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Aggressively pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] roughly grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself] in order to grind [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+].",
							
							"With a quick shift of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.tongue+].",
							
							"Pressing [npc.her] [npc.pussy+] down against [npc2.namePos] mouth,"
									+ " [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] grinding [npc.her] [npc.clit+] down against [npc2.namePos] [npc2.lips+].");
					
				default:
					break;
			}
			
			return "";
		}
		
	};
	
	public static final SexAction SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
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

							"With a long, slow lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] gently [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Gently kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] slowly [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of soft licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly dragging [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.pussy+],"
									+ " [npc.name] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to dominantly suck and kiss it.",

							"With a rough, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] forcefully kissing and sucking on [npc2.her] [npc2.clit+].",

							"Roughly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of greedy, wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to greedily suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] eagerly [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Eagerly kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.tongue] over [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.clit+], before starting to suck and kiss it.",

							"With a long, wet lick, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] up and over [npc2.namePos] [npc2.clit+],"
									+ " pressing [npc.her] [npc.lips+] against it as [npc.she] [npc.verb(start)] kissing and sucking on [npc2.her] [npc2.clit+].",

							"Kissing and licking [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] way to [npc2.her] [npc2.clit+],"
									+ " and with a series of wet licks from [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] focusing on pleasuring [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], and, gently bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gently pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Gently bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, roughly grinding [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly grinding [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc.verb(command)] [npc.herHim] not to stop.",

							" Roughly grinding [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before ordering [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, eagerly bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Eagerly bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",

							" A shuddering [npc2.moan] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, pressing [npc2.her] [npc2.pussy] against [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] not to stop.",

							" Bucking [npc2.her] [npc2.hips] out against [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] before pleading for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.namePos] [npc.face],"
									+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",

							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(struggle)] against [npc.name],"
									+ " [npc2.sobbing] out loud as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.pussy] away from [npc.her] unwelcome [npc.tongue].",

							" [npc2.Sobbing] out loud, and with tears in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beg)] for [npc.name] to leave [npc2.herHim] alone,"
									+ " frantically trying to pull [npc2.her] [npc2.hips] back each time [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tongue+] running over [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
