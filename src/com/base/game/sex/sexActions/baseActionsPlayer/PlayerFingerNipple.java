package com.base.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia
 */
public class PlayerFingerNipple {
	
	public static SexAction PLAYER_FEEL_BREASTS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Grope breasts";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and start groping [npc.name]'s [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasBreasts() && Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out a soft [pc.moan] as you start fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " softly pressing [npc.her] [npc.lowClothing(nipples)] down against [npc.her] [npc.nipples+] in the process.",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to gently press your [pc.hands+]"
									+ " into the fabric of [npc.her] [npc.topClothing(nipples)], before starting to softly grope and squeeze [npc.her] chest.",
								"Teasing your [pc.fingers] over [npc.name]'s [npc.topClothing(nipples)], you start to gently fondle and grope [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start eagerly groping and squeezing [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " pressing [npc.her] [npc.lowClothing(nipples)] down against [npc.her] [npc.nipples+] in the process.",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to eagerly press your [pc.hands+]"
									+ " into the fabric of [npc.her] [npc.topClothing(nipples)], before starting to grope and squeeze [npc.her] chest.",
								"Teasing your [pc.fingers] over [npc.name]'s [npc.topClothing(nipples)], you start to eagerly grope and squeeze [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start roughly groping and squeezing [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " forcefully grinding [npc.her] [npc.lowClothing(nipples)] down against [npc.her] [npc.nipples+] in the process.",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to roughly grind your [pc.hands+]"
									+ " into the fabric of [npc.her] [npc.topClothing(nipples)], before starting to greedily grope and squeeze [npc.her] chest.",
								"Sinking your [pc.fingers] into [npc.name]'s [npc.topClothing(nipples)], you start to roughly grope and squeeze [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start eagerly groping and squeezing [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " pressing [npc.her] [npc.lowClothing(nipples)] down against [npc.her] [npc.nipples+] in the process.",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to eagerly press your [pc.hands+]"
									+ " into the fabric of [npc.her] [npc.topClothing(nipples)], before starting to grope and squeeze [npc.her] chest.",
								"Teasing your [pc.fingers] over [npc.name]'s [npc.topClothing(nipples)], you start to eagerly grope and squeeze [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start groping and squeezing [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " pressing [npc.her] [npc.lowClothing(nipples)] down against [npc.her] [npc.nipples+] in the process.",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to press your [pc.hands+]"
									+ " into the fabric of [npc.her] [npc.topClothing(nipples)], before starting to grope and squeeze [npc.her] chest.",
								"Teasing your [pc.fingers] over [npc.name]'s [npc.topClothing(nipples)], you start to grope and squeeze [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a soft [npc.moan] at your touch, before gently encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With a soft [npc.moan], [npc.she] slowly pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd little noises.",
								" Softly [npc.moaning] at your touch, [npc.name] gently encourages you to carry on playing with [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before roughly growling out; commanding you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, and in a firm tone, [npc.she] orders you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] demands that you carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before eagerly encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] eagerly pushes [npc.her] chest out, enthusiastically imploring you to continue as [npc.she] carries on making extremely lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] eagerly encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] tries to pull back, [npc.sobbing] and struggling against your touch as [npc.she] tries to knock your [pc.hands] away from [npc.her] [npc.breasts+].",
								" With [npc.a_sob+], [npc.she] starts writhing around in discomfort, pleading for you to leave [npc.her] alone as you continue playing with [npc.her] [npc.breasts+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+] at your touch, and as you carry on playing with [npc.her] [npc.breasts+], [npc.she] continues to struggle against your touch."));
						break;
					default:
						break;
				}
				
				switch (Main.game.getPlayer().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out into [npc.her] [npc.lowClothing(nipples)] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out into [npc.her] [npc.lowClothing(nipples)] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out into [npc.her] [npc.lowClothing(nipples)] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out into [npc.her] [npc.lowClothing(nipples)], causing [npc.herHim] to let out [npc.a_moan+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts drooling out in a little stream into [npc.her] [npc.lowClothing(nipples)] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a constant stream, quickly soaking [npc.her] [npc.lowClothing(nipples)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a heavy flow, quickly soaking [npc.her] [npc.lowClothing(nipples)].");
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
								"Reaching up to [npc.name]'s chest, you let out a soft [pc.moan] as you start gently fondling and groping [npc.her] [npc.breastRows] [npc.breasts+].",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to gently start groping and squeezing at [npc.her] exposed chest.",
								"Teasing your [pc.fingers] over [npc.name]'s exposed chest, you start to gently fondle and grope [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start eagerly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+].",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to eagerly start groping and squeezing at [npc.her] exposed chest.",
								"Teasing your [pc.fingers] over [npc.name]'s exposed chest, you start to eagerly fondle and grope [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start roughly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+].",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to start roughly groping and squeezing at [npc.her] exposed chest.",
								"Sinking your [pc.fingers] into [npc.name]'s exposed chest, you start to roughly fondle and grope [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start eagerly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+].",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to eagerly start groping and squeezing at [npc.her] exposed chest.",
								"Teasing your [pc.fingers] over [npc.name]'s exposed chest, you start to eagerly fondle and grope [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.name]'s chest, you let out [pc.a_moan+] as you start fondling and groping [npc.her] [npc.breastRows] [npc.breasts+].",
								"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and you reach up to start groping and squeezing at [npc.her] exposed chest.",
								"Teasing your [pc.fingers] over [npc.name]'s chest, you start to eagerly fondle and grope [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out a soft [npc.moan] at your touch, before gently encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With a soft [npc.moan], [npc.she] slowly pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd little noises.",
								" Softly [npc.moaning] at your touch, [npc.name] gently encourages you to carry on playing with [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before roughly growling out; commanding you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, and in a firm tone, [npc.she] orders you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] demands that you carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before eagerly encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] eagerly pushes [npc.her] chest out, enthusiastically imploring you to continue as [npc.she] carries on making extremely lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] eagerly encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.breasts+] your full attention.",
								" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
								" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.She] tries to pull back, [npc.sobbing] and struggling against your touch as [npc.she] tries to knock your [pc.hands] away from [npc.her] [npc.breasts+].",
								" With [npc.a_sob+], [npc.she] starts writhing around in discomfort, pleading for you to leave [npc.her] alone as you continue playing with [npc.her] [npc.breasts+].",
								" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+] at your touch, and as you carry on playing with [npc.her] [npc.breasts+], [npc.she] continues to struggle against your touch."));
						break;
					default:
						break;
				}
			
				switch (Sex.getPartner().getBreastLactation()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out over your [pc.fingers] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out over your [pc.fingers] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out over your [pc.fingers] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out over your [pc.fingers], and [npc.she] lets out a deep moan as it starts running down over [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts drooling out in a little stream over your [pc.fingers] as you squeeze down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a constant stream, quickly soaking [npc.her] breasts and dripping down onto the floor beneath [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out in a heavy flow, quickly soaking [npc.her] breasts and dripping down to form a large pool on the floor beneath [npc.herHim].");
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
	
	public static SexAction PLAYER_PINCH_NIPPLES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and start pinching [npc.name]'s [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasBreasts() && Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc.name]'s [npc.breasts+], you let out a soft [pc.moan] as you start to gently pinch and rub at [npc.her] [npc.nipples+].",
							"[npc.Name]'s [npc.breasts+], fully on display, prove to be too tempting a target for you to ignore, and with a soft little [pc.moan], you start gently tugging and pinching [npc.her] [npc.nipples+].",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breastRows] [npc.breasts+], you start to gently tug and pinch at [npc.her] [npc.nipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc.name]'s [npc.breasts+], you let out [pc.a_moan+] as you start to eagerly pinch and rub at [npc.her] [npc.nipples+].",
							"[npc.Name]'s [npc.breasts+], fully on display, prove to be too tempting a target for you to ignore, and with [pc.a_moan+], you start eagerly tugging and pinching [npc.her] [npc.nipples+].",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breastRows] [npc.breasts+], you start eagerly tugging and pinching at [npc.her] [npc.nipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc.name]'s [npc.breasts+], you let out [pc.a_moan+] as you start roughly groping [npc.her] chest, before moving up to forcefully pinch and squeeze [npc.her] [npc.nipples+].",
							"[npc.Name]'s [npc.breasts+], fully on display, prove to be too tempting a target for you to ignore, and with [pc.a_moan+], you start roughly pinching and squeezing [npc.her] [npc.nipples+].",
							"Sinking your [pc.fingers] into [npc.name]'s [npc.breastRows] [npc.breasts+], you let out [pc.a_moan+] before starting to roughly pinch and squeeze [npc.her] [npc.nipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc.name]'s [npc.breasts+], you let out [pc.a_moan+] as you start to eagerly pinch and rub at [npc.her] [npc.nipples+].",
							"[npc.Name]'s [npc.breasts+], fully on display, prove to be too tempting a target for you to ignore, and with [pc.a_moan+], you start eagerly tugging and pinching [npc.her] [npc.nipples+].",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breastRows] [npc.breasts+], you start eagerly tugging and pinching at [npc.her] [npc.nipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Reaching up to [npc.name]'s [npc.breasts+], you let out [pc.a_moan+] as you start to tug and squeeze [npc.her] [npc.nipples+].",
							"[npc.Name]'s [npc.breasts+], fully on display, prove to be too tempting a target for you to ignore, and with [pc.a_moan+], you start tugging and squeezing [npc.her] [npc.nipples+].",
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breastRows] [npc.breasts+], you start tugging and squeezing [npc.her] [npc.nipples+]."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] at your touch, before gently encouraging you to continue giving [npc.her] [npc.nipples+] your full attention.",
							" With a soft [npc.moan], [npc.she] slowly pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd little noises.",
							" Softly [npc.moaning] at your touch, [npc.name] gently encourages you to carry on stimulating [npc.her] [npc.nipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.nipples+] your full attention.",
							" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
							" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.nipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] at your touch, before roughly growling out; commanding you to continue giving [npc.her] [npc.nipples+] your full attention.",
							" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, and in a firm tone, [npc.she] orders you to continue as [npc.she] carries on making lewd noises.",
							" Letting out [npc.a_moan+] at your touch, [npc.name] demands that you carry on stimulating [npc.her] [npc.nipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] at your touch, before eagerly encouraging you to continue giving [npc.her] [npc.nipples+] your full attention.",
							" With [npc.a_moan+], [npc.she] eagerly pushes [npc.her] chest out, enthusiastically imploring you to continue as [npc.she] carries on making extremely lewd noises.",
							" Letting out [npc.a_moan+] at your touch, [npc.name] eagerly encourages you to carry on stimulating [npc.her] [npc.nipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] at your touch, before encouraging you to continue giving [npc.her] [npc.nipples+] your full attention.",
							" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to continue as [npc.she] carries on making lewd noises.",
							" Letting out [npc.a_moan+] at your touch, [npc.name] encourages you to carry on stimulating [npc.her] [npc.nipples+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] tries to pull back, [npc.sobbing] and struggling against your touch as [npc.she] tries to knock your [pc.fingers] away from [npc.her] [npc.nipples+].",
							" With [npc.a_sob+], [npc.she] starts writhing around in discomfort, pleading for you to leave [npc.her] alone as you continue stimulating [npc.her] [npc.nipples+].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+] at your touch, and as you carry on stimulating [npc.her] [npc.nipples+], [npc.she] continues to struggle against your touch."));
					break;
				default:
					break;
			}
			
			switch (Sex.getPartner().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" As you start pinching [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" As you start pinching [npc.her] [npc.nipples], a small squirt of [pc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" As you start pinching [npc.her] [npc.nipples], a trickle of [npc.milk] runs down over [npc.her] [npc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out over your [pc.fingers] as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts drooling out over your [pc.fingers] as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out over your [pc.fingers] as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts pouring out over your [pc.fingers] as you greedily milk [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_NIPPLE_FINGERING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Start nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [pc.fingers] into one of [npc.name]'s [npc.nipples] and start fingering [npc.her] breasts.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breasts+], you circle around one of [npc.her] [npc.nipples+], before slowly pushing your digits into the inviting orifice.",
							"You press your [pc.fingers] against one of [npc.name]'s [npc.nipples+], and with a slow, steady pressure, you gently sink your digits into the flesh of [npc.her] breast."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breasts+], you circle around one of [npc.her] [npc.nipples+], before eagerly pushing your digits into the inviting orifice.",
							"You press your [pc.fingers] against one of [npc.name]'s [npc.nipples+], and with a steady pressure, you greedily sink your digits into the flesh of [npc.her] breast."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Groping and squeezing [npc.name]'s [npc.breasts+], you start to circle your [pc.fingers] around one of [npc.her] [npc.nipples+], before roughly forcing your digits into the inviting orifice.",
							"Greedily pressing your [pc.fingers] against one of [npc.name]'s [npc.nipples+], you let out a little growl as you roughly sink your digits into the flesh of [npc.her] breast."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breasts+], you eagerly circle around one of [npc.her] [npc.nipples+], before desperately pushing your digits into the inviting orifice.",
							"You press your [pc.fingers] against one of [npc.name]'s [npc.nipples+], and with a steady pressure, you eagerly sink your digits into the flesh of [npc.her] breast."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing your [pc.fingers] over [npc.name]'s [npc.breasts+], you circle around one of [npc.her] [npc.nipples+], before eagerly pushing your digits into the inviting orifice.",
							"You press your [pc.fingers] against one of [npc.name]'s [npc.nipples+], and with a steady pressure, you greedily sink your digits into the flesh of [npc.her] breast."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as you start fingering [npc.her] [npc.breasts], gently pushing [npc.her] chest out to help you sink your [pc.fingers] even deeper into [npc.her] [npc.nipple+].",
							" With a soft [npc.moan], [npc.she] slowly pushes [npc.her] chest out, imploring you to sink your [pc.fingers] even deeper into [npc.her] breast."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start fingering [npc.her] [npc.breasts], eagerly pushing [npc.her] chest out to help you sink your [pc.fingers] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] eagerly pushes [npc.her] chest out, imploring you to sink your [pc.fingers] even deeper into [npc.her] breast."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start fingering [npc.her] [npc.breasts], violently thrusting [npc.her] chest out against you as [npc.she] commands you to sink your [pc.fingers]"
									+ " even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] responds by violently thrusting [npc.her] chest out, commanding you to sink your [pc.fingers] even deeper into [npc.her] breast."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start fingering [npc.her] [npc.breasts], eagerly pushing [npc.her] chest out to help you sink your [pc.fingers] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] eagerly pushes [npc.her] chest out, imploring you to sink your [pc.fingers] even deeper into [npc.her] breast."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start fingering [npc.her] [npc.breasts], pushing [npc.her] chest out to help you sink your [pc.fingers] even deeper into [npc.her] [npc.nipple+].",
							" With [npc.a_moan+], [npc.she] pushes [npc.her] chest out, imploring you to sink your [pc.fingers] even deeper into [npc.her] breast."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] tries to pull back, [npc.sobbing] and writhing in discomfort as you start to pump your [pc.fingers] in and out of [npc.her] [npc.nipple+].",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away from the unwanted penetration, protesting and struggling against your touch as you pump your [pc.fingers] in and out of [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sinking your [pc.fingers+] deep into [npc.name]'s [npc.nipple+], you slowly start sliding in and out of [npc.her] [npc.breast].",
					"You gently lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you gently pump your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
					"Gently pressing yourself in against [npc.name], you let out [pc.a_moan+] as you softly pump your [pc.fingers+] in and out of [npc.her] [npc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips], and [npc.she] starts eagerly thrusting [npc.her] chest out against your touch as [npc.she] begs you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] pushes out [npc.her] chest,"
									+ " eagerly imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] chest away from your touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to push you away from [npc.herHim].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips], before [npc.she] starts weakly trying to push you away, squirming and protesting as you continue to gently finger [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil [npc.her] chest away from your touch, struggling against you as your [pc.fingers] continue gently sliding deep into [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out [npc.a_moan] as [npc.she] implores you to continue fingering [npc.her] [npc.breasts].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips], and [npc.she] pushes [npc.her] chest out against your touch, imploring you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning+], [npc.she] pushes out [npc.her] chest, imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
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
					"Sinking your [pc.fingers+] deep into [npc.name]'s [npc.nipple+], you start sliding in and out, eagerly fingering [npc.her] [npc.breast] as you press yourself up against [npc.herHim].",
					"You lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you start eagerly pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
					"Pressing yourself in against [npc.herHim], you let out [pc.a_moan+] as you start eagerly pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips], and [npc.she] starts eagerly thrusting [npc.her] chest out against your touch as [npc.she] begs you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] pushes out [npc.her] chest,"
									+ " eagerly imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] chest away from your touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to push you away from [npc.herHim].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips], before [npc.she] starts weakly trying to push you away, squirming and protesting as you continue to finger [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil [npc.her] chest away from your touch, struggling against you as your [pc.fingers] continue sliding deep into [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out [npc.a_moan] as [npc.she] implores you to continue fingering [npc.her] [npc.breasts].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips], and [npc.she] pushes [npc.her] chest out against your touch, imploring you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning+], [npc.she] pushes out [npc.her] chest, imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Greedily plunging your [pc.fingers+] deep into [npc.name]'s [npc.nipple+], you start roughly slamming in and out, rapidly fingering [npc.her] [npc.breast] as you grind yourself up against [npc.herHim].",
					"You grind yourself against [npc.name], inhaling [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you start roughly slamming your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
					"Grinding yourself up against [npc.name], you let out [pc.a_moan+] as you start roughly slamming your [pc.fingers+] in and out of [npc.her] [npc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips], and [npc.she] starts eagerly thrusting [npc.her] chest out against your touch as [npc.she] begs you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] pushes out [npc.her] chest,"
									+ " eagerly imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc.her] chest away from your dominant touch, [npc.she] lets out [npc.a_sob+] as [npc.she] weakly tries to push you away from [npc.herHim].",
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips], before [npc.she] starts weakly trying to push you away, squirming and protesting as you continue to roughly finger [npc.her] [npc.nipple+].",
							" [npc.Sobbing] in distress, [npc.she] tries, in vain, to recoil [npc.her] chest away from your dominant touch,"
									+ " struggling against you as your [pc.fingers] continue relentlessly thrusting deep into [npc.her] [npc.nipple+]"));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out [npc.a_moan] as [npc.she] implores you to continue fingering [npc.her] [npc.breasts].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips], and [npc.she] pushes [npc.her] chest out against your touch, imploring you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning+], [npc.she] pushes out [npc.her] chest, imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static SexAction PLAYER_NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
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
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.fingers+] deep into [npc.name]'s [npc.nipple+], you start sliding in and out, fingering [npc.her] [npc.breast] as you press yourself up against [npc.herHim].",
					"You lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you start pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
					"Pressing yourself in against [npc.herHim], you let out [pc.a_moan+] as you start pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips], and [npc.she] starts gently pushing [npc.her] chest out against your touch as [npc.she] begs you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] gently pushes out [npc.her] chest,"
									+ " eagerly imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts commanding you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips],"
									+ " and [npc.she] starts roughly thrusting [npc.her] chest out against your touch as [npc.she] orders you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] thrusts out [npc.her] chest,"
									+ " commanding you to continue fingering [npc.her] [npc.breasts] as [npc.her] sudden movement causes you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out [npc.a_moan] as [npc.she] implores you to continue fingering [npc.her] [npc.breasts].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips], and [npc.she] pushes [npc.her] chest out against your touch, imploring you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning+], [npc.she] pushes out [npc.her] chest, imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.name]'s nipple.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Sinking your [pc.fingers+] deep into [npc.name]'s [npc.nipple+], you start sliding in and out, eagerly fingering [npc.her] [npc.breast] as you press yourself up against [npc.herHim].",
					"You lean in against [npc.name], breathing in [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" as you start eagerly pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
					"Pressing yourself in against [npc.herHim], you let out [pc.a_moan+] as you start eagerly pumping your [pc.fingers+] in and out of [npc.her] [npc.nipple+]."));
			
			switch(Sex.getSexPacePartner()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts enthusiastically imploring you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips], and [npc.she] starts gently pushing [npc.her] chest out against your touch as [npc.she] begs you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] gently pushes out [npc.her] chest,"
									+ " eagerly imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out a delighted [npc.moan] as [npc.she] starts commanding you to continue fingering [npc.her] [npc.breasts].",
							" A delighted [npc.moan] bursts out from between [npc.her] [npc.lips],"
									+ " and [npc.she] starts roughly thrusting [npc.her] chest out against your touch as [npc.she] orders you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning] in delight, [npc.she] thrusts out [npc.her] chest,"
									+ " commanding you to continue fingering [npc.her] [npc.breasts] as [npc.her] sudden movement causes you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] pushes [npc.her] chest out in response, letting out [npc.a_moan] as [npc.she] implores you to continue fingering [npc.her] [npc.breasts].",
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips], and [npc.she] pushes [npc.her] chest out against your touch, imploring you to continue fingering [npc.her] [npc.nipples+].",
							" [npc.Moaning+], [npc.she] pushes out [npc.her] chest, imploring you to continue fingering [npc.her] [npc.breasts] as [npc.her] movements cause you to sink your [pc.fingers] deep into [npc.her] [npc.nipple+]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.fingers] out of [npc.name]'s nipple and stop fingering [npc.her] breast.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.fingers] out of [npc.name]'s nipple, you give [npc.her] [npc.breast] one last rough squeeze as you stop fingering [npc.her] chest.",
							"Taking in one last breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you yank your [pc.fingers] out of [npc.her] nipple."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.name]'s nipple, you give [npc.her] [npc.breast] one last squeeze as you stop fingering [npc.her] chest.",
							"Taking in one last breath of [npc.name]'s "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+", you slide your [pc.fingers] out of [npc.her] nipple."));
					break;
			}
			
			switch(Sex.getSexPacePartner()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] continues to struggle against you.",
							" With [npc.a_sob+], [npc.name] continues to protest and struggle about in discomfort as you hold [npc.herHim] in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you stop stimulating [npc.her] [npc.breasts+].",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire for more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner reactions:
	
	public static SexAction PARTNER_NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Resist [pc.name]'s fingering.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPlayerDom();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"With tears in [npc.her] [npc.eyes], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to push your [pc.fingers] out of [npc.her] [npc.nipple].",
					"[npc.Name] lets out [npc.a_sob+], frantically trying to pull [npc.her] chest away from your unwanted touch as [npc.she] struggles against you.",
					"Trying desperately to pull [npc.her] [npc.breasts+] away from your greedy [pc.fingers], [npc.name] [npc.sobs] in distress as [npc.she] struggles against you."));
			
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soothing [pc.moan], ignoring [npc.her] weak protests as you continue gently sliding your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
							" With a soft [pc.moan], you gently slide your [pc.fingers+] even deeper into [npc.her] [npc.nipple+], drawing yet another [npc.sob] from between [npc.her] [npc.lips] as you ignore [npc.her] protestations.",
							" Pressing yourself against [npc.herHim], and totally ignoring [npc.her] weak protests, you gently slide your [pc.fingers+] deep into [npc.her] [npc.nipple], causing [npc.her] to let out yet another [npc.sob+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out an angry growl, ignoring [npc.her] weak protests as you seek to punish [npc.her] reluctance by continuing to ruthlessly slam your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
							" With a furious growl, you roughly thrust your [pc.fingers+] even deeper into [npc.her] [npc.nipple+],"
									+ " drawing yet another [npc.sob+] from between [npc.her] [npc.lips] as you ruthlessly finger-fuck [npc.her] [npc.breasts+].",
							" Grinding yourself against [npc.herHim], and totally ignoring [npc.her] weak protests,"
									+ " you roughly slam your [pc.fingers+] deep into [npc.her] [npc.nipple], grinning as you draw yet another [npc.sob+] out from between [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan], ignoring [npc.her] weak protests as you continue sliding your [pc.fingers+] in and out of [npc.her] [npc.nipple+].",
							" With [pc.a_moan], you slide your [pc.fingers+] even deeper into [npc.her] [npc.nipple+], drawing yet another [npc.sob] from between [npc.her] [npc.lips] as you ignore [npc.her] protestations.",
							" Pressing yourself against [npc.herHim], and totally ignoring [npc.her] weak protests, you slide your [pc.fingers+] deep into [npc.her] [npc.nipple], causing [npc.her] to let out yet another [npc.sob+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_SADIST));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_MASOCHIST));
		}
	};
	
	public static SexAction PARTNER_NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isConsensualSex() || !Sex.isPlayerDom(); // Partner can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.fingers] out of your nipple and stop fingering your breast.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPacePartner()) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.fingers] out of [npc.her] nipple, [npc.name] growls at you as [npc.she] commands you to stop fingering [npc.her] chest.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" before [npc.she] yanks your [pc.fingers] out of [npc.her] nipple."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.fingers] out of [npc.her] nipple, [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop fingering [npc.her] chest.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] "+(Sex.getPartner().isFeminine()?"feminine scent":"masculine musk")+" before [npc.she] slides your [pc.fingers] out of [npc.her] nipple."));
					break;
			}
			
			switch(Sex.getSexPacePlayer()) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
							" With [pc.a_sob+], you continue to protest and struggle in discomfort as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as you stop stimulating [npc.her] [npc.breasts+].",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to give [npc.her] [npc.breasts+] more of your attention."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
