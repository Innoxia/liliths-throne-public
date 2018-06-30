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
public class PartnerTongueBreasts {
	
	public static final SexAction PARTNER_KISS_BREAST = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Kiss breasts";
		}

		@Override
		public String getActionDescription() {
			return "Plant a series of kisses on [pc.name]'s exposed [pc.breasts].";
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
							"[npc.Name] slowly leans in to your chest, pressing [npc.her] [npc.lips+] against your [pc.breastSkin+] before starting to plant a series of gentle kisses on your [pc.breasts+].",
							"Gently pressing [npc.her] [npc.lips+] against your [pc.breastSkin], [npc.name] starts delivering a series of loving kisses to your exposed [pc.breasts+].",
							"[npc.Name] starts gently kissing your exposed [pc.breasts], breathing in your [pc.scent] as [npc.she] presses [npc.her] [npc.lips+] against your [pc.skin+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly leans in to your chest, greedily pressing [npc.her] [npc.lips+] against your [pc.breastSkin+] before starting to plant a series of passionate kisses on your [pc.breasts+].",
							"Greedily pressing [npc.her] [npc.lips+] against your [pc.breastSkin], [npc.name] starts delivering a series of enthusiastic kisses to your exposed [pc.breasts+].",
							"[npc.Name] starts passionately kissing your exposed [pc.breasts], breathing in your [pc.scent] as [npc.she] desperately presses [npc.her] [npc.lips+] against your [pc.skin+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily leans in to your chest, roughly pressing [npc.her] [npc.lips+] against your [pc.breastSkin+] before starting to plant a series of forceful kisses on your [pc.breasts+].",
							"Greedily pressing [npc.her] [npc.lips+] against your [pc.breastSkin], [npc.name] starts delivering a series of rough kisses to your exposed [pc.breasts+].",
							"[npc.Name] starts roughly kissing your exposed [pc.breasts], breathing in your [pc.scent] as [npc.she] forcefully presses [npc.her] [npc.lips+] against your [pc.skin+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly leans in to your chest, greedily pressing [npc.her] [npc.lips+] against your [pc.breastSkin+] before starting to plant a series of passionate kisses on your [pc.breasts+].",
							"Greedily pressing [npc.her] [npc.lips+] against your [pc.breastSkin], [npc.name] starts delivering a series of enthusiastic kisses to your exposed [pc.breasts+].",
							"[npc.Name] starts passionately kissing your exposed [pc.breasts], breathing in your [pc.scent] as [npc.she] desperately presses [npc.her] [npc.lips+] against your [pc.skin+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] leans in to your chest, pressing [npc.her] [npc.lips+] against your [pc.breastSkin+] before starting to plant a series of kisses on your [pc.breasts+].",
							"Pressing [npc.her] [npc.lips+] against your [pc.breastSkin], [npc.name] starts delivering a series of kisses to your exposed [pc.breasts+].",
							"[npc.Name] starts kissing your exposed [pc.breasts], breathing in your [pc.scent] as [npc.she] presses [npc.her] [npc.lips+] against your [pc.skin+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] in response, and gently pull [npc.her] [npc.face] into your chest as you cry out for [npc.herHim] to continue.",
							" A gentle [pc.moan] drifts out from between your [pc.lips] as you push out your chest in response.",
							" Gently pushing your chest out into [npc.her] [npc.face], you let out a soft [pc.moan] as you plead for [npc.herHim] to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, and eagerly pull [npc.her] [npc.face] into your chest as you cry out for [npc.herHim] to continue.",
							" [npc.A_moan+] drifts out from between your [pc.lips] as you enthusiastically push out your chest in response.",
							" Eagerly pushing your chest out into [npc.her] [npc.face], you let out [pc.a_moan+] as you plead for [npc.herHim] to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, and roughly yank [npc.her] [npc.face] into your chest as you order [npc.herHim] to continue.",
							" [npc.A_moan+] drifts out from between your [pc.lips] as you roughly push out your chest in response.",
							" Forcefully pushing your chest out into [npc.her] [npc.face], you let out [pc.a_moan+] as you order [npc.herHim] to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, and eagerly pull [npc.her] [npc.face] into your chest as you cry out for [npc.herHim] to continue.",
							" [npc.A_moan+] drifts out from between your [pc.lips] as you enthusiastically push out your chest in response.",
							" Eagerly pushing your chest out into [npc.her] [npc.face], you let out [pc.a_moan+] as you plead for [npc.herHim] to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] in response, and pull [npc.her] [npc.face] into your chest as you cry out for [npc.herHim] to continue.",
							" [npc.A_moan+] drifts out from between your [pc.lips] as you push out your chest in response.",
							" Pushing your chest out into [npc.her] [npc.face], you let out [pc.a_moan+] as you plead for [npc.herHim] to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You desperately try to pull your [pc.breasts] away from [npc.her] [npc.face], letting out [pc.a_sob+] as you beg for [npc.herHim] to leave you alone.",
							" With tears streaming down your [pc.face], you struggle against [npc.herHim], [pc.sobbing] out loud as you try to pull your [pc.breasts] away from [npc.her] unwelcome [npc.lips].",
							" [pc.Sobbing] out loud, and with tears in your [pc.eyes], you beg for [npc.herHim] to leave you alone as you frantically try to pull your [pc.breasts] away."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
