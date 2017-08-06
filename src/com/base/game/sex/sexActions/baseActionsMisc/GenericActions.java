package com.base.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class GenericActions {
	
	public static SexAction PLAYER_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.NEGATIVE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexPace.SUB_RESISTING,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PLAYER) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned back against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed you back against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you back against the wall.");
				
			} else if(Sex.getPosition()==SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"You let out [pc.a_sob+] as you try to crawl away from [npc.name], but your efforts prove to be in vain as [npc.she] grabs your [pc.hips] and pulls your [pc.ass] back into [npc.her] groin.",
						"Trying to crawl away from [npc.name] on all fours, you let out [pc.a_sob+] as you feel [npc.herHim] grasp your [pc.hips], before pulling you back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately try to crawl away from [npc.name], [pc.sobbing] in distress as [npc.she] takes hold of your [pc.hips] and pulls you back into [npc.herHim].");
				
			} else if(Sex.getPosition()==SexPosition.FACING_WALL_PLAYER) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned up against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed up against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you up against the wall.");
				
			} else if(Sex.getPosition()==SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc.name]'s groin away from your [pc.face], but your efforts prove to be in vain as [npc.she] grabs hold of your head and pulls you back into [npc.her] crotch.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to pull your [pc.face] away from [npc.her] groin, but [npc.her] grasp on your head is too strong, and you're quickly forced back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pulls your [pc.face] back into [npc.her] groin.");
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PARTNER_TOP) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc.name] off of you as you desperately try to wriggle out from under [npc.herHim], but your efforts prove to be in vain as [npc.she] easily pins you to the floor.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out from under [npc.herHim], but [npc.she] presses [npc.her] body down onto yours, preventing you from getting away.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] uses [npc.her] body to pin you to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily continues restraining you.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as you realise that [npc.her] grip is too strong.");
			}
		}
	};
	
	public static SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Forbid partner self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& Sex.isPartnerAllowedToUseSelfActions();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER).isPlayer()) {
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop stimulating [npc.her] [npc.pussy+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("As you put an end to [npc.name]'s self-stimulation of [npc.her] [npc.asshole], [npc.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] pouts at you as you force [npc.herHim] to stop stimulating [npc.her] [npc.nipples+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop using [npc.her] mouth.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0)
				UtilText.nodeContentSB.append("</br></br>");
			UtilText.nodeContentSB.append("[pc.speech(I don't want to see you trying to get yourself off, understood?)] you growl at [npc.name].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will no longer use any self-penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(PenetrationType pt : PenetrationType.values()) {
				if(!pt.isPlayer()) {
					if(Sex.getOngoingPenetrationMap().containsKey(pt)) {
						for(OrificeType ot : OrificeType.values()) {
							if(!ot.isPlayer()) {
								Sex.removePenetration(pt, ot);
							}
						}
					}
				}
			}
			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.VAGINA_PARTNER);
//
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.ANUS_PARTNER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.NIPPLE_PARTNER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.MOUTH_PARTNER);
			
			Sex.setPartnerAllowedToUseSelfActions(false);
		}
	};
	
	public static SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Permit partner self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& !Sex.isPartnerAllowedToUseSelfActions();
		}

		@Override
		public String getDescription() {
			return "[pc.speech(I want to see you trying to get yourself off!)] you growl at [npc.name].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to use any self-penetrative actions.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerAllowedToUseSelfActions(true);
		}
	};
	
	public static SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Forbid clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from managing any of your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& (Sex.isPartnerCanRemovePlayersClothes());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(Don't you <i>dare</i> try and touch any of my clothes!)] you growl at [npc.name].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will not attempt to remove or displace any of your clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerCanRemovePlayersClothes(false);
		}
	};
	
	public static SexAction PLAYER_PERMIT_PARTNER_CLOTHING_REMOVAL = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Permit clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to take off and displace your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& (!Sex.isPartnerCanRemovePlayersClothes());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(How about you help me take off some of these clothes?)] you [pc.moan].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to manage your clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerCanRemovePlayersClothes(true);
		}
	};
	
	public static SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Forbid self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from managing any of [npc.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& (Sex.isPartnerCanRemoveOwnClothes());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(Don't you <i>dare</i> try and touch your clothes!)] you growl at [npc.name].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will not attempt to remove or displace any of [npc.her] clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerCanRemoveOwnClothes(false);
		}
	};
	
	public static SexAction PLAYER_PERMIT_PARTNER_CLOTHING_SELF_REMOVAL = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Permit self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to take off and displace [npc.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex())
					&& (!Sex.isPartnerCanRemoveOwnClothes());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(How about you start taking off some of your clothes?)] you [pc.moan].</br></br>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to manage [npc.her] clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerCanRemoveOwnClothes(true);
		}
	};
	
	
	public static SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stop partner";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPartnerSelfPenetrationHappening()
					&& (Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER).isPlayer()) {
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop stimulating [npc.her] [npc.pussy+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("As you put an end to [npc.name]'s self-stimulation of [npc.her] [npc.asshole], [npc.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] pouts at you as you force [npc.herHim] to stop stimulating [npc.her] [npc.nipples+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop using [npc.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(PenetrationType pt : PenetrationType.values()) {
				if(!pt.isPlayer()) {
					if(Sex.getOngoingPenetrationMap().containsKey(pt)) {
						for(OrificeType ot : OrificeType.values()) {
							if(!ot.isPlayer()) {
								Sex.removePenetration(pt, ot);
							}
						}
					}
				}
			}
			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.VAGINA_PARTNER);
//
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.ANUS_PARTNER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.NIPPLE_PARTNER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)!=null)
//				if(!Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER).isPlayer())
//					Sex.removePenetration(OrificeType.MOUTH_PARTNER);
		}
	};
	
	// Partner:
	
	public static SexAction PARTNER_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [pc.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned back against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed back against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] back against the wall.");
				
			} else if(Sex.getPosition()==SexPosition.DOGGY_PARTNER_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_sob+] as [npc.she] tries to crawl away from you, but [npc.her] efforts prove to be in vain as you grab [npc.her] [npc.hips] and pull [npc.her] [npc.ass] back into your groin.",
						"Trying to crawl away from you on all fours, [npc.name] lets out [npc.a_sob+] as you grasp [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately tries to crawl away from you, [npc.sobbing] in distress as you take hold of [npc.her] [npc.hips] and pull [npc.herHim] back into you.");
				
			} else if(Sex.getPosition()==SexPosition.FACING_WALL_PARTNER) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned up against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed up against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] up against the wall.");
				
			} else if(Sex.getPosition()==SexPosition.KNEELING_PARTNER_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push your groin away from [npc.her] [npc.face], but [npc.her] efforts prove to be in vain as you grab hold of [npc.her] head and pull [npc.herHim] back into your crotch.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to pull [npc.her] [npc.face] away from your groin,"
								+ " but your grasp on [npc.her] head is too strong, and you quickly force [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you pull [npc.her] [npc.face] back into your groin.");
				
			} else if(Sex.getPosition()==SexPosition.SIXTY_NINE_PLAYER_TOP) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push you off of [npc.herHim] as [npc.she] desperately tries to wriggle out from under you, but [npc.her] efforts prove to be in vain as you easily pin [npc.herHim] to the floor.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out from under you, but you press your body down onto [npc.hers], preventing [npc.herHim] from getting away.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you use your body to pin [npc.herHim] to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily continue restraining [npc.herHim].",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you easily hold [npc.herHim] in place.");
			}
		}
	};
	
	public static SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stop player";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerSelfPenetrationHappening()
					&& !Sex.isAnyNonSelfPenetrationHappening()
					&& (!Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER).isPlayer()) {
					UtilText.nodeContentSB.append("[npc.Name] lets out an angry growl as [npc.she] forces you to stop stimulating your [pc.pussy+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("As [npc.name] puts an end to your self-stimulation of your [pc.asshole], [npc.she] growls menacingly at you.");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER)!=null) {
				if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] frowns at you as [npc.she] forces you to stop stimulating your [pc.nipples+].");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)!=null) {
				if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER).isPlayer()) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("</br>");
					UtilText.nodeContentSB.append("[npc.Name] makes a disapproving clicking noise with [npc.her] [npc.tongue] as [npc.she] forces you to stop using your mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(PenetrationType pt : PenetrationType.values()) {
				if(pt.isPlayer()) {
					if(Sex.getOngoingPenetrationMap().containsKey(pt)) {
						for(OrificeType ot : OrificeType.values()) {
							if(ot.isPlayer()) {
								Sex.removePenetration(pt, ot);
							}
						}
					}
				}
			}
			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
//				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER).isPlayer())
//					Sex.removePenetration(OrificeType.VAGINA_PLAYER);
//
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
//				if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER).isPlayer())
//					Sex.removePenetration(OrificeType.ANUS_PLAYER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER)!=null)
//				if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER).isPlayer())
//					Sex.removePenetration(OrificeType.NIPPLE_PLAYER);
//			
//			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)!=null)
//				if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER).isPlayer())
//					Sex.removePenetration(OrificeType.MOUTH_PLAYER);
		}
	};
	
	public static SexAction PARTNER_BLOCKS_REQUESTS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Respond";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().isEmpty()
					&& Sex.getPartner().hasFetish(Fetish.FETISH_DOMINANT)
					&& !Sex.isPlayerDom();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.Name] frowns as you try to tell [npc.herHim] how to use you, and with a menacing tone in [npc.her] voice, [npc.she] growls at you, "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Don't try and tell me what to do!)]",
									"[npc.speech(I'll use whatever hole I feel like!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			SexFlags.requestsBlockedPlayer=true;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.PARTNER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(!Sex.getSexManager().isConsensualSex()) {
				return !Sex.isPlayerDom() && Sex.getNumberOfPartnerOrgasms()>=1;
			} else {
				return Sex.getNumberOfPartnerOrgasms()>=1 && Sex.getNumberOfPlayerOrgasms()>=1;
			}
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] disentangles [npc.herself] from your clutches, before stating that [npc.she]'s had enough for now.";
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stop sex";
		}

		@Override
		public String getActionDescription() {
			return "Stop having sex with [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPlayerAbleToStopSex();
		}

		@Override
		public String getDescription() {
			return "Deciding that you've had enough, you step back from [npc.name].";
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
