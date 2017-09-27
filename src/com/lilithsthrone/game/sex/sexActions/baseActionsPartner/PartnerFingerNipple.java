package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PartnerFingerNipple {
	
	public static final SexAction PARTNER_FEEL_BREASTS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Feel breasts";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts() && Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out a soft [npc.moan] as [npc.she] starts fondling and groping your [pc.breastRows] [pc.breasts+],"
										+ " softly pressing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to gently press [npc.her] [npc.hands+]"
									+ " into the fabric of your [pc.topClothing(nipples)], before starting to softly grope and squeeze your chest.",
								"Teasing [npc.her] [npc.fingers] over your [pc.topClothing(nipples)], [npc.name] starts to gently fondle and grope your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly fondling and groping your [pc.breastRows] [pc.breasts+],"
										+ " pressing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to eagerly press [npc.her] [npc.hands+]"
									+ " into the fabric of your [pc.topClothing(nipples)], before starting to grope and squeeze your chest.",
								"Teasing [npc.her] [npc.fingers] over your [pc.topClothing(nipples)], [npc.name] starts to eagerly fondle and grope your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly fondling and groping your [pc.breastRows] [pc.breasts+],"
										+ " forcefully grinding your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to forcefully grind [npc.her] [npc.hands+]"
									+ " into the fabric of your [pc.topClothing(nipples)], before starting to roughly grope and squeeze your chest.",
								"Sinking [npc.her] [npc.fingers] into your [pc.topClothing(nipples)], [npc.name] starts to roughly fondle and grope your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly fondling and groping your [pc.breastRows] [pc.breasts+],"
										+ " pressing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to eagerly press [npc.her] [npc.hands+]"
									+ " into the fabric of your [pc.topClothing(nipples)], before starting to grope and squeeze your chest.",
								"Teasing [npc.her] [npc.fingers] over your [pc.topClothing(nipples)], [npc.name] starts to eagerly fondle and grope your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts fondling and groping your [pc.breastRows] [pc.breasts+],"
										+ " pressing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to press [npc.her] [npc.hands+]"
									+ " into the fabric of your [pc.topClothing(nipples)], before starting to grope and squeeze your chest.",
								"Teasing [npc.her] [npc.fingers] over your [pc.topClothing(nipples)], [npc.name] starts to fondle and grope your [pc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] at [npc.her] touch, before gently encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With a soft [pc.moan], you slowly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd little noises.",
								" Softly [pc.moaning] at [npc.her] touch, you gently encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before roughly growling out; commanding [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you push your chest out, and in a firm tone, you order [npc.herHim] to continue before carrying on making lewd noises.",
								" Letting out [pc.a_moan+] at [npc.her] touch, you demand that [npc.she] carries on playing with your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You instinctively try to pull back, [pc.sobbing] and struggling against [npc.her] touch as you try to knock [npc.name]'s [npc.fingers] away from your [pc.breasts+].",
								" With [pc.a_sob+], you start writhing around in discomfort, pleading for [npc.herHim] to leave you alone as [npc.she] continues playing with your [pc.breasts+].",
								" [pc.A_sob+] bursts out from between your [pc.lips+] at [npc.her] touch, and as [npc.she] carries on playing with your [pc.breasts+], you continue to struggle against [npc.herHim]."));
						break;
					default:
						break;
				}
				
				switch (Main.game.getPlayer().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out into your [pc.lowClothing(nipples)], and you moan as you feel it running down over your [pc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream, quickly soaking your [pc.lowClothing(nipples)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow, quickly soaking your [pc.lowClothing(nipples)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}else{
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out a soft [npc.moan] as [npc.she] starts gently fondling and groping your [pc.breastRows] [pc.breasts+].",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to gently start groping and squeezing at your exposed chest.",
								"Teasing [npc.her] [npc.fingers] over your exposed chest, [npc.name] starts to gently fondle and grope your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly fondling and groping your [pc.breastRows] [pc.breasts+].",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to eagerly start groping and squeezing at your exposed chest.",
								"Teasing [npc.her] [npc.fingers] over your exposed chest, [npc.name] starts to eagerly fondle and grope your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly fondling and groping your [pc.breastRows] [pc.breasts+].",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to roughly start groping and squeezing at your exposed chest.",
								"Sinking [npc.her] [npc.fingers] into your exposed chest, [npc.name] starts to roughly fondle and grope your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly fondling and groping your [pc.breastRows] [pc.breasts+].",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to eagerly start groping and squeezing at your exposed chest.",
								"Teasing [npc.her] [npc.fingers] over your exposed chest, [npc.name] starts to eagerly fondle and grope your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to your chest, [npc.name] lets out [npc.a_moan+] as [npc.she] starts fondling and groping your [pc.breastRows] [pc.breasts+].",
								"[npc.Name] finds [npc.herself] unable to resist the temptation of your [pc.breasts+], and [npc.she] reaches up to start groping and squeezing at your exposed chest.",
								"Teasing [npc.her] [npc.fingers] over your exposed chest, [npc.name] starts to fondle and grope your [pc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out a soft [pc.moan] at [npc.her] touch, before gently encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With a soft [pc.moan], you slowly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd little noises.",
								" Softly [pc.moaning] at [npc.her] touch, you gently encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before roughly growling out; commanding [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you push your chest out, and in a firm tone, you order [npc.herHim] to continue before carrying on making lewd noises.",
								" Letting out [pc.a_moan+] at [npc.her] touch, you demand that [npc.she] carries on playing with your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You let out [pc.a_moan+] at [npc.her] touch, before encouraging [npc.herHim] to continue giving your [pc.breasts+] [npc.her] full attention.",
								" With [pc.a_moan+], you push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
								" [pc.Moaning+] at [npc.her] touch, you encourage [npc.herHim] to carry on playing with your [pc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" You instinctively try to pull back, [pc.sobbing] and struggling against [npc.her] touch as you try to knock [npc.name]'s [npc.fingers] away from your [pc.breasts+].",
								" With [pc.a_sob+], you start writhing around in discomfort, pleading for [npc.herHim] to leave you alone as [npc.she] continues playing with your [pc.breasts+].",
								" [pc.A_sob+] bursts out from between your [pc.lips+] at [npc.her] touch, and as [npc.she] carries on playing with your [pc.breasts+], you continue to struggle against [npc.herHim]."));
						break;
					default:
						break;
				}
				
				switch (Main.game.getPlayer().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out onto [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out onto [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out over [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out over [npc.her] fingers, and you moan as you feel it running down over your [pc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream over [npc.her] fingers as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream, quickly soaking your breasts and dripping down onto the floor beneath you.");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow, quickly soaking your breasts and dripping down to form a large pool on the floor beneath you.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().getBreastLactation().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()){
				if(Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
					Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
				}
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_PINCH_NIPPLES = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts() && Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to your [pc.breasts+], [npc.name] lets out a soft [npc.moan] as [npc.she] starts to gently pinch and rub at your [pc.nipples+].",
							"Your [pc.breasts+], fully on display, prove to be too tempting a target for [npc.name] to ignore, and with a soft little [npc.moan], [npc.she] starts gently tugging and pinching your [pc.nipples+].",
							"Teasing [npc.her] [npc.fingers] over your [pc.breastRows] [pc.breasts+], [npc.name] starts to gently tug and pinch at your [pc.nipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to your [pc.breasts+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts to eagerly pinch and rub at your [pc.nipples+].",
							"Your [pc.breasts+], fully on display, prove to be too tempting a target for [npc.name] to ignore, and with [npc.a_moan+], [npc.she] starts eagerly tugging and pinching your [npc.nipples+].",
							"Teasing [npc.her] [npc.fingers] over your [pc.breastRows] [pc.breasts+], [npc.name] starts eagerly tugging and pinching at your [pc.nipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to your [pc.breasts+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly groping your chest, before moving up to forcefully pinch and squeeze your [pc.nipples+].",
							"Your [pc.breasts+], fully on display, prove to be too tempting a target for [npc.name] to ignore, and with [npc.a_moan+], [npc.she] starts roughly pinching and squeezing your [pc.nipples+].",
							"Sinking [npc.her] [npc.fingers] into your [pc.breastRows] [pc.breasts+], [npc.name] lets out [npc.a_moan+] before starting to roughly pinch and squeeze your [pc.nipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to your [pc.breasts+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts to eagerly pinch and rub at your [pc.nipples+].",
							"Your [pc.breasts+], fully on display, prove to be too tempting a target for [npc.name] to ignore, and with [npc.a_moan+], [npc.she] starts eagerly tugging and pinching your [npc.nipples+].",
							"Teasing [npc.her] [npc.fingers] over your [pc.breastRows] [pc.breasts+], [npc.name] starts eagerly tugging and pinching at your [pc.nipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to your [pc.breasts+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts to pinch and rub at your [pc.nipples+].",
							"Your [pc.breasts+], fully on display, prove to be too tempting a target for [npc.name] to ignore, and with [npc.a_moan+], [npc.she] starts tugging and pinching your [npc.nipples+].",
							"Teasing [npc.her] [npc.fingers] over your [pc.breastRows] [pc.breasts+], [npc.name] starts tugging and pinching at your [pc.nipples+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] at [npc.her] touch, before gently encouraging [npc.herHim] to continue giving your [pc.nipples+] [npc.her] full attention.",
							" With a soft [pc.moan], you slowly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd little noises.",
							" Softly [pc.moaning] at [npc.her] touch, you gently encourage [npc.herHim] to carry on stimulating your [pc.nipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.nipples+] [npc.her] full attention.",
							" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
							" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on stimulating your [pc.nipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] at [npc.her] touch, before roughly growling out; commanding [npc.herHim] to continue giving your [pc.nipples+] [npc.her] full attention.",
							" With [pc.a_moan+], you push your chest out, and in a firm tone, you order [npc.herHim] to continue before carrying on making lewd noises.",
							" Letting out [pc.a_moan+] at [npc.her] touch, you demand that [npc.she] carries on stimulating your [pc.nipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] at [npc.her] touch, before eagerly encouraging [npc.herHim] to continue giving your [pc.nipples+] [npc.her] full attention.",
							" With [pc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
							" [pc.Moaning+] at [npc.her] touch, you eagerly encourage [npc.herHim] to carry on stimulating your [pc.nipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] at [npc.her] touch, before encouraging [npc.herHim] to continue giving your [pc.nipples+] [npc.her] full attention.",
							" With [pc.a_moan+], you push your chest out, imploring [npc.herHim] to continue as you carry on making lewd noises.",
							" [pc.Moaning+] at [npc.her] touch, you encourage [npc.herHim] to carry on stimulating your [pc.nipples+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You instinctively try to pull back, [pc.sobbing] and struggling against [npc.her] touch as you try to knock [npc.name]'s [npc.fingers] away from your [pc.nipples+].",
							" With [pc.a_sob+], you start writhing around in discomfort, pleading for [npc.herHim] to leave you alone as [npc.she] continues stimulating your [pc.nipples+].",
							" [pc.A_sob+] bursts out from between your [pc.lips+] at [npc.her] touch, and as [npc.she] carries on stimulating your [pc.nipples+], you continue to struggle against [npc.herHim]."));
					break;
				default:
					break;
			}
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" As [npc.she] starts pinching your [pc.nipples], a small trickle of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" As [npc.she] starts pinching your [pc.nipples], a small squirt of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" As [npc.she] starts pinching your [pc.nipples], a trickle of [pc.milk] runs down over your [pc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	
	// Penetration:
	
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Start nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.fingers] into one of [pc.name]'s [pc.nipples] and start fingering [pc.her] breasts.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over your [pc.breasts+], you let out a gasp as [npc.name] circles around one of your [pc.nipples+], before slowly pushing [npc.her] digits into your inviting orifice.",
							"[npc.Name] presses [npc.her] [npc.fingers] against one of your [pc.nipples+], and with a slow, steady pressure, [npc.she] gently sinks [npc.her] digits into the flesh of your breast."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over your [pc.breasts+], you let out a gasp as [npc.name] circles around one of your [pc.nipples+], before eagerly pushing [npc.her] digits into your inviting orifice.",
							"[npc.Name] presses [npc.her] [npc.fingers] against one of your [pc.nipples+], and with a steady pressure, [npc.she] greedily sinks [npc.her] digits into the flesh of your breast."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Groping and squeezing your [pc.breasts+], [npc.name] starts to circle [npc.her] [npc.fingers] around one of your [pc.nipples+], before roughly forcing [npc.her] digits into your inviting orifice.",
							"Greedily pressing [npc.her] [npc.fingers] against one of your [pc.nipples+], [npc.name] lets out a little growl as [npc.she] roughly sinks [npc.her] digits into the flesh of your breast."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over your [pc.breasts+], you let out a gasp as [npc.name] eagerly circles around one of your [pc.nipples+], before desperately pushing [npc.her] digits into your inviting orifice.",
							"[npc.Name] presses [npc.her] [npc.fingers] against one of your [pc.nipples+], and with a steady pressure, [npc.she] eagerly sinks [npc.her] digits into the flesh of your breast."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing [npc.her] [npc.fingers] over your [pc.breasts+], you let out a gasp as [npc.name] circles around one of your [pc.nipples+], before eagerly pushing [npc.her] digits into your inviting orifice.",
							"[npc.Name] presses [npc.her] [npc.fingers] against one of your [pc.nipples+], and with a steady pressure, [npc.she] greedily sinks [npc.her] digits into the flesh of your breast."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan] as [npc.she] starts fingering your [pc.breasts], gently pushing your chest out as you help [npc.herHim] sink [npc.her] [npc.fingers] even deeper into your [pc.nipple+].",
							" With a soft [pc.moan], you slowly push your chest out, imploring [npc.herHim] to sink [npc.her] [npc.fingers+] even deeper into your breast."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts fingering your [pc.breast], eagerly pushing your chest out as you help [npc.herHim] sink [npc.her] [npc.fingers] even deeper into your [pc.nipple+].",
							" With [npc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your breast."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts fingering your [pc.breasts], violently thrusting your chest out against [npc.her] touch as you command [npc.herHim] to sink [npc.her] [npc.fingers]"
									+ " even deeper into your [pc.nipple+].",
							" With [pc.a_moan+], you respond by violently thrusting your chest out, commanding [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your breast."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts fingering your [pc.breasts], eagerly pushing your chest out as you help [npc.herHim] sink [npc.her] [npc.fingers] even deeper into your [pc.nipple+].",
							" With [npc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your breast."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] starts fingering your [pc.breast], eagerly pushing your chest out as you help [npc.herHim] sink [npc.her] [npc.fingers] even deeper into your [pc.nipple+].",
							" With [npc.a_moan+], you eagerly push your chest out, imploring [npc.herHim] to sink [npc.her] [npc.fingers] even deeper into your breast."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You try to pull back, [pc.sobbing] and writhing in discomfort as [npc.she] starts to pump [npc.her] [npc.fingers] in and out of your [pc.nipple+].",
							" With [pc.a_sob+], you try, in vain, to pull away from the unwanted penetration, protesting and struggling against [npc.her] touch as [npc.she] pumps [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [pc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking [npc.her] [npc.fingers+] deep into your [pc.nipple+], [npc.name] slowly starts to slide in and out of your [pc.breast].",
					"[npc.Name] gently leans in against you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as [npc.she] gently pumps [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
					"Gently pressing [npc.herself] in against you, [npc.name] lets out [npc.a_moan+] as [npc.she] softly pumps [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start eagerly thrusting your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you push out your chest,"
									+ " eagerly imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your chest away from [npc.her] touch, you let out [pc.a_sob+] as you weakly try to push [npc.herHim] away from you.",
							" [pc.A_sob+] bursts out from between your [pc.lips], before you start weakly trying to push [npc.herHim] away, squirming and protesting as [npc.she] continues to gently finger your [pc.nipple+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil your chest away from [npc.her] touch, struggling against [npc.herHim] as [npc.her] [npc.fingers] continue gently sliding deep into your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out [pc.a_moan] as you implore [npc.herHim] to continue fingering your [pc.breasts].",
							" [pc.A_moan+] bursts out from between your [pc.lips], and you push your chest out against [npc.her] touch as you implore [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning+], you push out your chest, imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Finger nipple";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [pc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into your [pc.nipple+], [npc.name] starts eagerly fingering your [pc.breasts], letting out [npc.a_moan+] as [npc.she] presses [npc.herself] up against you.",
					"[npc.Name] leans in against you, [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" overwhelming your senses as [npc.she] starts eagerly pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
					"Pressing [npc.herself] against you, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start eagerly thrusting your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you push out your chest,"
									+ " eagerly imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your chest away from [npc.her] unwanted touch, you let out [pc.a_sob+] as you weakly try to push [npc.herHim] away from you.",
							" [pc.A_sob+] bursts out from between your [pc.lips], before you start weakly trying to push [npc.herHim] away, squirming and protesting as [npc.she] continues greedily fingering your [pc.nipple+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil your chest away from [npc.her] touch, struggling against [npc.herHim] as [npc.her] greedy [npc.fingers] continue sliding deep into your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out [pc.a_moan] as you implore [npc.herHim] to continue fingering your [pc.breasts].",
							" [pc.A_moan+] bursts out from between your [pc.lips], and you push your chest out against [npc.her] touch as you implore [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning+], you push out your chest, imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [pc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily plunging [npc.her] [npc.fingers+] deep into your [pc.nipple+], [npc.name] starts roughly slamming [npc.her] digits in and out, rapidly fingering your [pc.breast] as [npc.she] grinds [npc.herself] up against you.",
					"[npc.Name] grinds [npc.herself] against you, forcing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as [npc.she] starts roughly slamming [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
					"Grinding [npc.herself] up against you, [npc.name] lets out [npc.a_moan+] as [npc.she] starts roughly slamming [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start eagerly thrusting your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you push out your chest,"
									+ " eagerly imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil your chest away from [npc.her] dominant touch, you let out [pc.a_sob+] as you weakly try to push [npc.herHim] away from you.",
							" [pc.A_sob+] bursts out from between your [pc.lips], before you start weakly trying to push [npc.herHim] away, squirming and protesting as [npc.she] continues ruthlessly fingering your [pc.nipple+].",
							" [pc.Sobbing] in distress, you try, in vain, to recoil your chest away from [npc.her] dominant touch, struggling against [npc.herHim] as [npc.her] [npc.fingers] continue slamming deep into your [pc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out [pc.a_moan] as you implore [npc.herHim] to continue fingering your [pc.breasts].",
							" [pc.A_moan+] bursts out from between your [pc.lips], and you push your chest out against [npc.her] touch as you implore [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning+], you push out your chest, imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers+] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Finger nipple";
		}

		@Override
		public String getActionDescription() {
			return "Continue fingering [npc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into your [pc.nipple+], [npc.name] starts sliding [npc.her] digits in and out, fingering your [pc.breast] as [npc.she] presses [npc.herself] up against you.",
					"[npc.Name] leans in against you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as [npc.she] starts pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
					"Pressing [npc.herself] against you, [npc.name] lets out [npc.a_moan+] as [npc.she] starts pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start gently pushing your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you gently push out your chest,"
									+ " imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start commanding [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips],"
									+ " and you start roughly thrusting your chest out against [npc.her] touch as you order [npc.name] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you thrust out your chest,"
									+ " commanding [npc.name] to continue fingering your [pc.breasts] as your sudden movement causes [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start pushing your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you push out your chest,"
									+ " eagerly imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [pc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking [npc.her] [npc.fingers+] deep into your [pc.nipple+], [npc.name] starts sliding [npc.her] digits in and out, eagerly fingering your [pc.breast] as [npc.she] presses [npc.herself] up against you.",
					"[npc.Name] leans in against you, causing you to breathe in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")
						+" as [npc.she] starts eagerly pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
					"Pressing [npc.herself] against you, [npc.name] lets out [npc.a_moan+] as [npc.she] starts eagerly pumping [npc.her] [npc.fingers+] in and out of your [pc.nipple+]."));
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start enthusiastically imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start gently pushing your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you gently push out your chest,"
									+ " imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start commanding [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips],"
									+ " and you start roughly thrusting your chest out against [npc.her] touch as you order [npc.name] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you thrust out your chest,"
									+ " commanding [npc.name] to continue fingering your [pc.breasts] as your sudden movement causes [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You push your chest out in response, letting out a delighted [pc.moan] as you start imploring [npc.herHim] to continue fingering your [pc.breasts].",
							" A delighted [pc.moan] bursts out from between your [pc.lips], and you start pushing your chest out against [npc.her] touch as you beg [npc.herHim] to continue fingering your [pc.nipples+].",
							" [pc.Moaning] in delight, you push out your chest,"
									+ " eagerly imploring [npc.herHim] to continue fingering your [pc.breasts] as your movements cause [npc.herHim] to sink [npc.her] [npc.fingers] deep into your [pc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.fingers] out of [pc.name]'s nipple and stop fingering [pc.her] breast.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.her] [npc.fingers] out of your [pc.nipple+], [npc.name] gives your [pc.breast] one last rough squeeze as [npc.she] stops fingering your chest.",
							"You sharply inhale, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as [npc.she] roughly yanks [npc.her] [npc.fingers] out of your [pc.nipple]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.fingers] out of your nipple, [npc.name] gives your [pc.breast] one last squeeze as [npc.she] stops fingering your chest.",
							"You sharply inhale, breathing in [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as [npc.she] suddenly slides [npc.her] [npc.fingers] out of your [pc.nipple]."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you continue to struggle against [npc.herHim].",
							" With [pc.a_sob+], you continue to protest and struggle in discomfort as [npc.she] holds you in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.name] stops stimulating your [pc.breasts+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire for more of [npc.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Force breast grope";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to start groping your [pc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts() && (Sex.isPlayerDom() || Sex.getSexManager().isConsensualSex());
		}

		@Override
		public String getDescription() {
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out a soft [pc.moan] as you press them into your [pc.breasts+],"
										+ " forcing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"You take hold of [npc.name]'s [npc.hands], guiding them up to gently press into your [pc.topClothing(nipples)], and holding them there for a moment as you encourage [npc.herHim] to grope and squeeze your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you gently guide [npc.her] [npc.fingers] up to your [pc.topClothing(nipples)], before softly pressing them into your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you eagerly press them into your [pc.breasts+],"
										+ " forcing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"You take hold of [npc.name]'s [npc.hands], eagerly guiding them up to press into your [pc.topClothing(nipples)], and holding them there for a moment as you encourage [npc.herHim] to grope and squeeze your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you eagerly guide [npc.her] [npc.fingers] up to your [pc.topClothing(nipples)], before enthusiastically pressing them into your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you yank them up to your chest, letting out [pc.a_moan+] as you roughly press them into your [pc.breasts+],"
										+ " forcing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"You take hold of [npc.name]'s [npc.hands], roughly yanking them up to press into your [pc.topClothing(nipples)], and holding them there for a moment as you order [npc.herHim] to grope and squeeze your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you violently pull them up to your chest, forcing [npc.her] [npc.fingers] to press into the [pc.topClothing(nipples)] that's covering your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you eagerly press them into your [pc.breasts+],"
										+ " forcing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"You take hold of [npc.name]'s [npc.hands], eagerly guiding them up to press into your [pc.topClothing(nipples)], and holding them there for a moment as you encourage [npc.herHim] to grope and squeeze your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you eagerly guide [npc.her] [npc.fingers] up to your [pc.topClothing(nipples)], before enthusiastically pressing them into your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you press them into your [pc.breasts+],"
										+ " forcing your [pc.lowClothing(nipples)] down against your [pc.nipples+] in the process.",
								"You take hold of [npc.name]'s [npc.hands], guiding them up to press into your [pc.topClothing(nipples)], and holding them there for a moment as you encourage [npc.herHim] to grope and squeeze your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you guide [npc.her] [npc.fingers] up to your [pc.topClothing(nipples)], before pressing them into your [pc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out a soft [npc.moan] in response to your eagerness, before gently pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With a soft [npc.moan], [npc.she] eagerly responds to your move by gently sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" Softly [npc.moaning], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] gently presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before eagerly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by enthusiastically sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] eagerly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, roughly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+] as [npc.she] growls out that [npc.she]'s still the one in charge.",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by roughly sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] roughly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before eagerly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by enthusiastically sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] eagerly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] responds to your move by sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] tries to pull back, [npc.sobbing] and struggling against your touch as you force [npc.her] [npc.hands] into your [pc.breasts+].",
								" With [npc.a_sob+], [npc.she] starts writhing around in discomfort, pleading for you to leave [npc.herHim] alone as you continue forcing [npc.her] [npc.hands] into your [pc.breasts+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], and as you carry on forcing [npc.her] [npc.hands] into your [pc.breasts+], [npc.she] continues to struggle against your touch."));
						break;
					default:
						break;
				}
				
				switch (Main.game.getPlayer().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out into your [pc.lowClothing(nipples)], and you moan as you feel it running down over your [pc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream into your [pc.lowClothing(nipples)] as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream, quickly soaking your [pc.lowClothing(nipples)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow, quickly soaking your [pc.lowClothing(nipples)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}else{
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out a soft [pc.moan] as you press them into your [pc.breasts+].",
								"You take hold of [npc.name]'s [npc.hands], guiding them up to gently press into the soft flesh of your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you gently guide [npc.her] [npc.fingers] up to softly press into your [pc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you eagerly press them into your [pc.breasts+].",
								"You take hold of [npc.name]'s [npc.hands], eagerly guiding them up to press into the soft flesh of your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you eagerly guide [npc.her] [npc.fingers] up to enthusiastically press into your [pc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you yank them up to your chest, letting out [pc.a_moan+] as you roughly press them into your [pc.breasts+].",
								"You take hold of [npc.name]'s [npc.hands], roughly yanking them up to press into the soft flesh of your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you violently pull them up to your chest, forcing [npc.her] [npc.fingers] to press into the soft flesh of your [pc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you eagerly press them into your [pc.breasts+].",
								"You take hold of [npc.name]'s [npc.hands], eagerly guiding them up to press into the soft flesh of your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you eagerly guide [npc.her] [npc.fingers] up to enthusiastically press them into your [pc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking [npc.name]'s [npc.hands] in yours, you guide them up to your chest, letting out [pc.a_moan+] as you press them into your [pc.breasts+].",
								"You take hold of [npc.name]'s [npc.hands], guiding them up to press into the soft flesh of your [pc.breasts].",
								"Taking hold of [npc.name]'s [npc.hands], you guide [npc.her] [npc.fingers] up to press them into your [pc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out a soft [npc.moan] in response to your eagerness, before gently pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With a soft [npc.moan], [npc.she] eagerly responds to your move by gently sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" Softly [npc.moaning], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] gently presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before eagerly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by enthusiastically sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] eagerly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, roughly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+] as [npc.she] growls out that [npc.she]'s still the one in charge.",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by roughly sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] roughly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before eagerly pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] eagerly responds to your move by enthusiastically sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] eagerly presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.Name] lets out [npc.a_moan+] in response to your eagerness, before pressing [npc.her] [npc.hands] into the flesh of your [pc.breasts+].",
								" With [npc.a_moan+], [npc.she] responds to your move by sinking [npc.her] [npc.fingers] into the soft flesh of your [pc.breasts].",
								" [npc.Moaning+], [npc.she] starts playing with your [pc.breasts+], drawing pleasurable [pc.moans] from between your [pc.lips] as [npc.she] presses [npc.her] [npc.fingers] into your [pc.breasts]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] tries to pull back, [npc.sobbing] and struggling against your touch as you force [npc.her] [npc.hands] into your [pc.breasts+].",
								" With [npc.a_sob+], [npc.she] starts writhing around in discomfort, pleading for you to leave [npc.herHim] alone as you continue forcing [npc.her] [npc.hands] into your [pc.breasts+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], and as you carry on forcing [npc.her] [npc.hands] into your [pc.breasts+], [npc.she] continues to struggle against your touch."));
						break;
					default:
						break;
				}
				
				switch (Main.game.getPlayer().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.milk] leaks out onto [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [pc.milk] leaks out onto [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [pc.milk] runs out over [npc.her] fingertips as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts to flow out over [npc.her] fingers, and you moan as you feel it running down over your [pc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts drooling out in a little stream over [npc.her] fingers as [npc.she] squeezes down on your [pc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a constant stream, quickly soaking your breasts and dripping down onto the floor beneath you.");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" Your [pc.milk] starts pouring out in a heavy flow, quickly soaking your breasts and dripping down to form a large pool on the floor beneath you.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES) && Main.game.getPlayer().getBreastLactation().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()){
				if(Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
					Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
				}
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static final SexAction PLAYER_NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Resist [npc.name]'s fingering.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"You feel tears welling up in your [pc.eyes] as you let out [pc.a_sob+], weakly trying to push [npc.name]'s [npc.fingers] out of your [pc.nipple] as you struggle against [npc.herHim].",
					"You let out [pc.a_sob+], frantically trying to pull your chest away from [npc.name]'s unwanted touch as you struggle against [npc.herHim].",
					"Trying desperately to pull your [pc.breasts+] away from [npc.name]'s greedy [npc.fingers], you [pc.sob] in distress as you struggle against [npc.herHim]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soothing [npc.moan], ignoring your weak protests as [npc.she] continues gently sliding [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
							" With a soft [npc.moan], [npc.she] gently slides [npc.her] [npc.fingers+] even deeper into your [pc.nipple+], drawing yet another [pc.sob] from between your [pc.lips+] as [npc.she] ignores your protestations.",
							" Pressing [npc.herself] against you, and totally ignoring your weak protests, [npc.she] gently slides [npc.her] [npc.fingers+] deep into your [pc.nipple], causing you to let out yet another [pc.sob+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out an angry growl, ignoring your weak protests as [npc.she] seeks to punish your reluctance by continuing to ruthlessly slam [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
							" With a furious growl, [npc.she] roughly thrusts [npc.her] [npc.fingers+] even deeper into your [pc.nipple+],"
									+ " drawing yet another [pc.sob+] from between your [pc.lips] as [npc.she] ruthlessly finger-fucks your [pc.breasts+].",
							" Grinding [npc.herself] against you, and totally ignoring your weak protests,"
									+ " [npc.she] roughly slams [npc.her] [npc.fingers+] deep into your [pc.nipple], grinning as [npc.she] draws yet another [pc.sob+] out from between your [pc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan], ignoring your weak protests as [npc.she] continues sliding [npc.her] [npc.fingers+] in and out of your [pc.nipple+].",
							" With [npc.a_moan], [npc.she] slides [npc.her] [npc.fingers+] even deeper into your [pc.nipple+], drawing yet another [pc.sob] from between your [pc.lips+] as [npc.she] ignores your protestations.",
							" Pressing [npc.herself] against you, and totally ignoring your weak protests, [npc.she] slides [npc.her] [npc.fingers+] deep into your [pc.nipple], causing you to let out yet another [pc.sob+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_SADIST));
		}
	};
	
	public static final SexAction PLAYER_NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || Sex.isPlayerDom(); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.fingers] out of your nipple and stop fingering your breast.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.her] [npc.fingers] out of your [pc.nipple], you growl at [npc.name] as you command [npc.herHim] to stop fingering your chest.",
							"Inhaling [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you yank [npc.her] [npc.fingers] out of your nipple, putting an end to [npc.her] fingering of your [pc.breasts]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.name]'s [npc.fingers] out of your nipple, you let out [pc.a_moan+] as you tell [npc.her] to stop fingering your chest.",
							"Inhaling [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you slide [npc.her] [npc.fingers] out of your nipple, putting an end to [npc.her] fingering of your [pc.breasts]."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that you aren't finished with [npc.herHim] yet.",
							" With [npc.a_sob+], [npc.she] continues to protest and struggle in discomfort as you hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as [npc.she] stops stimulating your [pc.breasts+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to give your [pc.breasts+] more of [npc.her] attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
