package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.96
 * @author Innoxia
 */
public class GenericActions {
	
	public static final SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Grow cock";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to grow a demonic cock.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().getRace()==Race.DEMON
					&& !Sex.getActivePartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return "You decide that you'd like [npc.name] to use [npc.her] transformative powers to grow a nice thick demonic cock for you."
					+ " Grinning down at [npc.herHim], you tell [npc.herHim] as much, [pc.speech(Why don't you grow a nice big cock, so we can have even more fun!)]"
					+ "</br></br>"
					+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc.Name] lets out a little giggle, and as you look down at [npc.her] naked groin, you see a large bump start to form beneath [npc.her] [npc.skin]."
								+ " Before you have any time to change your mind, it quickly grows out into a fat demonic cock, and as you stare down at the little wriggling bumps that press out all along its shaft,"
								+ " a little spurt of precum shoots out onto your stomach."
						:"[npc.Name] lets out a little giggle, and as you look down at [npc.her] groin, you see a huge bulge quickly form beneath the fabric of [npc.her] "
								+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"."
								+ " Before you have any time to change your mind, [npc.name] lets out [npc.a_moan+], and you realise that [npc.she]'s now got a huge demonic cock hiding beneath [npc.her] clothing.");
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().setPenisType(PenisType.DEMON_COMMON);
			Sex.getActivePartner().setCumProduction(CumProduction.FIVE_HUGE.getMedianValue());
			Sex.getActivePartner().setTesticleSize(0);
			Sex.getActivePartner().setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue());
		}
	};
	
	public static final SexAction PLAYER_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.NEGATIVE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexPace.SUB_RESISTING,
			null) {
		
		@Override
		public ArousalIncrease getArousalGainPlayer() {
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.NEGATIVE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
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
			
			if(Sex.getPosition()==SexPositionNew.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned back against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed you back against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionNew.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"You let out [pc.a_sob+] as you try to crawl away from [npc.name], but your efforts prove to be in vain as [npc.she] grabs your [pc.hips] and pulls your [pc.ass] back into [npc.her] groin.",
						"Trying to crawl away from [npc.name] on all fours, you let out [pc.a_sob+] as you feel [npc.herHim] grasp your [pc.hips], before pulling you back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately try to crawl away from [npc.name], [pc.sobbing] in distress as [npc.she] takes hold of your [pc.hips] and pulls you back into [npc.herHim].");
				
			} else if(Sex.getPosition()==SexPositionNew.FACING_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned up against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed up against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionNew.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc.name]'s groin away from your [pc.face], but your efforts prove to be in vain as [npc.she] grabs hold of your head and pulls you back into [npc.her] crotch.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to pull your [pc.face] away from [npc.her] groin, but [npc.her] grasp on your head is too strong, and you're quickly forced back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pulls your [pc.face] back into [npc.her] groin.");
				
			} else if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
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
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_SUB));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM));
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stop penetrations";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyPenetrationHappening()
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			for(OrificeType ot : OrificeType.values()) {
				switch(ot) {
					case ANUS_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.asshole+].");
						}
						break;
					case ANUS_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.asshole+].");
						}
						break;
					case ASS_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you stop using [npc.her] [npc.ass+].");
						}
						break;
					case ASS_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.ass+].");
						}
						break;
					case BREAST_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you stop playing with [npc.her] [npc.breasts+].");
						}
						break;
					case BREAST_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.breasts+].");
						}
						break;
					case MOUTH_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] mouth.");
						}
						break;
					case MOUTH_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you pull out of your mouth.");
						}
						break;
					case NIPPLE_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.nipple+].");
						}
						break;
					case NIPPLE_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.nipple+].");
						}
						break;
					case URETHRA_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.urethra+].");
						}
						break;
					case URETHRA_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.urethra+].");
						}
						break;
					case VAGINA_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.pussy+].");
						}
						break;
					case VAGINA_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.pussy+].");
						}
						break;
					case THIGHS_PARTNER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[npc.Name] lets out [npc.a_moan+] as you pull out from between [npc.her] thighs.");
						}
						break;
					case THIGHS_PLAYER:
						if (Sex.getPenetrationTypeInOrifice(ot)!=null && Sex.getPenetrationTypeInOrifice(ot).isPlayer()) {
							UtilText.nodeContentSB.append("</br>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop playing with your thighs.");
						}
						break;
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(PenetrationType pt : PenetrationType.values()) {
				if(pt.isPlayer()) {
					if(Sex.getOngoingPenetrationMap().containsKey(pt)) {
						for(OrificeType ot : OrificeType.values()) {
							if(!ot.isPlayer()) {
								Sex.removePenetration(pt, ot);
							}
						}
					}
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Forbid self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
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
			
			Sex.setPartnerAllowedToUseSelfActions(false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Permit self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
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
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
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
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual())
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
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_REMOVAL = new SexAction(
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
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
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
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
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
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
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
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_SELF_REMOVAL = new SexAction(
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
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
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
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.PLAYER_SPECIAL,
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
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=null) {
				if(!Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER).isPlayer()) {
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [npc.moan] as you force [npc.herHim] to stop stimulating [npc.her] [npc.pussy+].");
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
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [npc.moan] as you force [npc.herHim] to stop using [npc.her] mouth.");
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
		}
	};
	
	// Partner:
	
	public static final SexAction PARTNER_RESIST = new SexAction(
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
			return Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
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
			
			if(Sex.getPosition()==SexPositionNew.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned back against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed back against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionNew.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_sob+] as [npc.she] tries to crawl away from you, but [npc.her] efforts prove to be in vain as you grab [npc.her] [npc.hips] and pull [npc.her] [npc.ass] back into your groin.",
						"Trying to crawl away from you on all fours, [npc.name] lets out [npc.a_sob+] as you grasp [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately tries to crawl away from you, [npc.sobbing] in distress as you take hold of [npc.her] [npc.hips] and pull [npc.herHim] back into you.");
				
			} else if(Sex.getPosition()==SexPositionNew.FACING_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned up against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed up against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionNew.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push your groin away from [npc.her] [npc.face], but [npc.her] efforts prove to be in vain as you grab hold of [npc.her] head and pull [npc.herHim] back into your crotch.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to pull [npc.her] [npc.face] away from your groin,"
								+ " but your grasp on [npc.her] head is too strong, and you quickly force [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you pull [npc.her] [npc.face] back into your groin.");
				
			} else if(Sex.getPosition()==SexPositionNew.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
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
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.PARTNER,
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
					&& (Sex.isDom(Main.game.getPlayer())?Sex.isSubHasEqualControl():true);
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
		}
	};
	
	public static final SexAction PARTNER_BLOCKS_REQUESTS = new SexAction(
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
					&& Sex.getActivePartner().getSexBehaviourDeniesRequests()
					&& (!Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl());
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
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			}
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
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
			if(!Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual()) {
				return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
				
			} else if(Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl()) {
				return false;
				
			} else {
				return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1 && Sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
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
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
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
