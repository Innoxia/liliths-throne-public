package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public class PlayerTongueBreasts {
	
	public static final SexAction PLAYER_KISS_BREAST = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.BREAST_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Kiss breasts";
		}

		@Override
		public String getActionDescription() {
			return "Plant a series of kisses on [npc.name]'s exposed [npc.breasts].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Main.game.getPlayer())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You slowly lean in to [npc.name]'s chest, pressing your [pc.lips+] against [npc.her] [npc.breastSkin+] before starting to plant a series of gentle kisses on [npc.her] [npc.breasts+].",
							"Gently pressing your [pc.lips+] against [npc.name]'s [npc.breastSkin], you start delivering a series of loving kisses to [npc.her] exposed [npc.breasts+].",
							"You start gently kissing [npc.name]'s exposed [npc.breasts], breathing in [npc.her] [npc.scent] as you press your [pc.lips+] against [npc.her] [npc.skin+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly lean in to [npc.name]'s chest, greedily pressing your [pc.lips+] against [npc.her] [npc.breastSkin+] before starting to plant a series of passionate kisses on [npc.her] [npc.breasts+].",
							"Greedily pressing your [pc.lips+] against [npc.name]'s [npc.breastSkin], you start delivering a series of enthusiastic kisses to [npc.her] exposed [npc.breasts+].",
							"You start passionately kissing [npc.name]'s exposed [npc.breasts], breathing in [npc.her] [npc.scent] as you desperately press your [pc.lips+] against [npc.her] [npc.skin+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You greedily lean in to [npc.name]'s chest, roughly pressing your [pc.lips+] against [npc.her] [npc.breastSkin+] before starting to plant a series of forceful kisses on [npc.her] [npc.breasts+].",
							"Greedily pressing your [pc.lips+] against [npc.name]'s [npc.breastSkin], you start delivering a series of rough kisses to [npc.her] exposed [npc.breasts+].",
							"You start roughly kissing [npc.name]'s exposed [npc.breasts], breathing in [npc.her] [npc.scent] as you forcefully press your [pc.lips+] against [npc.her] [npc.skin+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You eagerly lean in to [npc.name]'s chest, greedily pressing your [pc.lips+] against [npc.her] [npc.breastSkin+] before starting to plant a series of passionate kisses on [npc.her] [npc.breasts+].",
							"Greedily pressing your [pc.lips+] against [npc.name]'s [npc.breastSkin], you start delivering a series of enthusiastic kisses to [npc.her] exposed [npc.breasts+].",
							"You start passionately kissing [npc.name]'s exposed [npc.breasts], breathing in [npc.her] [npc.scent] as you desperately press your [pc.lips+] against [npc.her] [npc.skin+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You lean in to [npc.name]'s chest, pressing your [pc.lips+] against [npc.her] [npc.breastSkin+] before starting to plant a series of kisses on [npc.her] [npc.breasts+].",
							"Pressing your [pc.lips+] against [npc.name]'s [npc.breastSkin], you start delivering a series of kisses to [npc.her] exposed [npc.breasts+].",
							"You start kissing [npc.name]'s exposed [npc.breasts], breathing in [npc.her] [npc.scent] as you press your [pc.lips+] against [npc.her] [npc.skin+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] in response, and gently pulls your [pc.face] into [npc.her] chest as [npc.she] cries out for you to continue.",
							" A gentle [npc.moan] drifts out from between [npc.her] [npc.lips] as [npc.she] pushes out [npc.her] chest in response.",
							" Gently pushing [npc.her] chest out into your [pc.face], [npc.she] lets out a soft [npc.moan] as [npc.she] pleads for you to continue."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, and eagerly pulls your [pc.face] into [npc.her] chest as [npc.she] cries out for you to continue.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips] as [npc.she] enthusiastically pushes out [npc.her] chest in response.",
							" Eagerly pushing [npc.her] chest out into your [pc.face], [npc.she] lets out [npc.a_moan+] as [npc.she] pleads for you to continue."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, and roughly yanks your [pc.face] into [npc.her] chest as [npc.she] orders you to continue.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips] as [npc.she] roughly pushes out [npc.her] chest in response.",
							" Forcefully pushing [npc.her] chest out into your [pc.face], [npc.she] lets out [npc.a_moan+] as [npc.she] orders you to continue."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] in response, and eagerly pulls your [pc.face] into [npc.her] chest as [npc.she] cries out for you to continue.",
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips] as [npc.she] enthusiastically pushes out [npc.her] chest in response.",
							" Eagerly pushing [npc.her] chest out into your [pc.face], [npc.she] lets out [npc.a_moan+] as [npc.she] pleads for you to continue."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan] in response, and pulls your [pc.face] into [npc.her] chest as [npc.she] cries out for you to continue.",
							" [npc.A_moan] drifts out from between [npc.her] [npc.lips] as [npc.she] pushes out [npc.her] chest in response.",
							" Pushing [npc.her] chest out into your [pc.face], [npc.she] lets out [npc.a_moan] as [npc.she] pleads for you to continue."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] desperately tries to pull [npc.her] [npc.breasts] away from your [pc.face], letting out [npc.a_sob+] as [npc.she] begs for you to leave [npc.herHim] alone.",
							" With tears streaming down [npc.her] [npc.face], [npc.she] struggles against you, [npc.sobbing] out loud as [npc.she] tries to pull [npc.her] [npc.breasts] away from your unwelcome [pc.lips].",
							" [npc.Sobbing] out loud, and with tears in [npc.her] [npc.eyes], [npc.she] begs for you to leave [npc.herHim] alone as [npc.she] frantically tries to pull [npc.her] [npc.breasts] away."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
