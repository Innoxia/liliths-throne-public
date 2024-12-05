package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.3.1
 * @author Innoxia
 */
public class TongueBreasts {
	
	public static final SexAction KISS_BREAST = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				return "Kiss breasts";
			} else {
				return "Kiss chest";
			}
		}

		@Override
		public String getActionDescription() {
			return "Plant a series of kisses on [npc2.namePos] exposed [npc2.breasts].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] slowly [npc.verb(lean)] in to [npc2.namePos] chest,"
									+ " pressing [npc.her] [npc.lips+] against [npc2.her] [npc2.breastSkin+] before starting to plant a series of gentle kisses on [npc2.her] [npc2.breasts+].",

							"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] chest,"
									+ " [npc.name] [npc.verb(start)] delivering a series of loving kisses to [npc2.her] exposed [npc2.breasts+].",

							"[npc.Name] [npc.verb(start)] gently kissing [npc2.namePos] exposed [npc2.breasts],"
									+ " breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.skin+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(lean)] in to [npc2.namePos] chest,"
									+ " greedily pressing [npc.her] [npc.lips+] against [npc2.her] [npc2.breastSkin+] before starting to plant a series of passionate kisses on [npc2.her] [npc2.breasts+].",

							"Greedily pressing [npc.her] [npc.lips+] against [npc2.namePos] chest,"
									+ " [npc.name] [npc.verb(start)] delivering a series of enthusiastic kisses to [npc2.her] exposed [npc2.breasts+].",

							"[npc.Name] [npc.verb(start)] passionately kissing [npc2.namePos] exposed [npc2.breasts],"
									+ " breathing in [npc2.her] [npc2.scent+] as [npc.she] desperately [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.skin+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] greedily [npc.verb(lean)] in to [npc2.namePos] chest,"
									+ " roughly pressing [npc.her] [npc.lips+] against [npc2.her] [npc2.breastSkin+] before starting to plant a series of forceful kisses on [npc2.her] [npc2.breasts+].",

							"Greedily pressing [npc.her] [npc.lips+] against [npc2.namePos] chest,"
									+ " [npc.name] [npc.verb(start)] delivering a series of rough kisses to [npc2.her] exposed [npc2.breasts+].",

							"[npc.Name] [npc.verb(start)] roughly kissing [npc2.namePos] exposed [npc2.breasts],"
									+ " breathing in [npc2.her] [npc2.scent+] as [npc.she] forcefully [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.skin+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(lean)] in to [npc2.namePos] chest,"
									+ " greedily pressing [npc.her] [npc.lips+] against [npc2.her] [npc2.breastSkin+] before starting to plant a series of passionate kisses on [npc2.her] [npc2.breasts+].",

							"Greedily pressing [npc.her] [npc.lips+] against [npc2.namePos] chest,"
									+ " [npc.name] [npc.verb(start)] delivering a series of enthusiastic kisses to [npc2.her] exposed [npc2.breasts+].",

							"[npc.Name] [npc.verb(start)] passionately kissing [npc2.namePos] exposed [npc2.breasts],"
									+ " breathing in [npc2.her] [npc2.scent+] as [npc.she] desperately [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.skin+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)] in to [npc2.namePos] chest,"
									+ " pressing [npc.her] [npc.lips+] against [npc2.her] [npc2.breastSkin+] before starting to plant a series of kisses on [npc2.her] [npc2.breasts+].",

							"Pressing [npc.her] [npc.lips+] against [npc2.namePos] chest,"
									+ " [npc.name] [npc.verb(start)] delivering a series of kisses to [npc2.her] exposed [npc2.breasts+].",

							"[npc.Name] [npc.verb(start)] kissing [npc2.namePos] exposed [npc2.breasts],"
									+ " breathing in [npc2.her] [npc2.scent+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] [npc2.skin+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response, and gently [npc2.verb(pull)] [npc.namePos] [npc.face] into [npc2.her] chest as [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",
	
								" A gentle [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips] as [npc2.she] [npc2.verb(push)] out [npc2.her] chest in response.",
	
								" Gently pushing [npc2.her] chest out into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, and eagerly [npc2.verb(pull)] [npc.namePos] [npc.face] into [npc2.her] chest as [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",
	
								" [npc.A_moan+] drifts out from between [npc2.namePos] [npc2.lips] as [npc2.she] enthusiastically [npc2.verb(push)] out [npc2.her] chest in response.",
	
								" Eagerly pushing [npc2.her] chest out into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, and roughly [npc2.verb(yank)] [npc.namePos] [npc.face] into [npc2.her] chest as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue.",
	
								" [npc.A_moan+] drifts out from between [npc2.namePos] [npc2.lips] as [npc2.she] roughly [npc2.verb(push)] out [npc2.her] chest in response.",
	
								" Forcefully pushing [npc2.her] chest out into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, and eagerly [npc2.verb(pull)] [npc.namePos] [npc.face] into [npc2.her] chest as [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",
	
								" [npc.A_moan+] drifts out from between [npc2.namePos] [npc2.lips] as [npc2.she] enthusiastically [npc2.verb(push)] out [npc2.her] chest in response.",
	
								" Eagerly pushing [npc2.her] chest out into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, and [npc2.verb(pull)] [npc.namePos] [npc.face] into [npc2.her] chest as [npc2.she] [npc2.verb(cry)] out for [npc.herHim] to continue.",
	
								" [npc.A_moan+] drifts out from between [npc2.namePos] [npc2.lips] as [npc2.she] [npc2.verb(push)] out [npc2.her] chest in response.",
	
								" Pushing [npc2.her] chest out into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to continue."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.breasts] away from [npc.namePos] [npc.face],"
										+ " letting out [npc2.a_sob+] as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to leave [npc2.herHim] alone.",
	
								" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(struggle)] against [npc.name],"
										+ " [npc2.sobbing] out loud as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.breasts] away from [npc.her] unwelcome [npc.lips].",
	
								" [npc2.Sobbing] out loud, and with tears in [npc2.namePos] [npc2.eyes],"
										+ " [npc2.name] [npc2.verb(beg)] for [npc.name] to leave [npc2.herHim] alone as [npc2.she] frantically [npc2.verb(try)] to pull [npc2.her] [npc2.breasts] away."));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
