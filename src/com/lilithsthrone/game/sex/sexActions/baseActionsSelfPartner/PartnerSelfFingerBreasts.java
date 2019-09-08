package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
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
 * @since 0.3.4.5
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PartnerSelfFingerBreasts {
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grope breasts (self)";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze and grope your own [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterPerformingAction().hasBreasts()
					&& Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					 && Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			if(!Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " softly pressing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to gently press [npc.her] [npc.hands+]"
									+ " against [npc.her] [npc.topClothing(NIPPLES)], before starting to softly grope and squeeze [npc.her] chest.",
								"Teasing [npc.her] [npc.fingers] over [npc.her] [npc.topClothing(NIPPLES)],"
										+ " [npc.name] [npc.verb(start)] softly [npc.moaning] and sighing as [npc.she] gently [npc.verb(fondle)] and [npc.verb(grope)] [npc.her] own [npc.breastRows] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " forcefully grinding [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to forcefully grind [npc.her] [npc.hands+]"
									+ " against [npc.her] [npc.topClothing(NIPPLES)], before starting to roughly grope and squeeze [npc.her] chest.",
								"Running [npc.her] [npc.fingers] over [npc.her] [npc.topClothing(NIPPLES)],"
										+ " [npc.name] [npc.verb(start)] loudly panting and [npc.moaning] as [npc.she] roughly [npc.verb(fondle)] and [npc.verb(grope)] [npc.her] own [npc.breastRows] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " pressing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to press [npc.her] [npc.hands+]"
									+ " against [npc.her] [npc.topClothing(NIPPLES)], before starting to grope and squeeze [npc.her] chest.",
								"Running [npc.her] [npc.fingers] over [npc.her] [npc.topClothing(NIPPLES)],"
										+ " [npc.name] [npc.verb(start)] panting and [npc.moaning] as [npc.she] [npc.verb(fondle)] and [npc.verb(grope)] [npc.her] own [npc.breastRows] [npc.breasts+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " desperately pressing [npc.her] [npc.lowClothing(NIPPLES)] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc.her] [npc.topClothing(NIPPLES)], before starting to frantically grope and squeeze [npc.her] chest.",
								"Running [npc.her] [npc.fingers] over [npc.her] [npc.topClothing(NIPPLES)],"
										+ " [npc.name] [npc.verb(start)] desperately panting and [npc.moaning] as [npc.she] eagerly [npc.verb(fondle)] and [npc.verb(grope)] [npc.her] own [npc.breastRows] [npc.breasts+]."));
						break;
				}
				
				switch (Sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out into [npc.her] [npc.lowClothing(NIPPLES)] as [npc.she] [npc.verb(squeeze)] down on [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out into [npc.her] [npc.lowClothing(NIPPLES)] as [npc.she] [npc.verb(squeeze)] down on [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out into [npc.her] [npc.lowClothing(NIPPLES)] as [npc.she] [npc.verb(squeeze)] down on [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Almost immediately, [npc.her] [npc.milk] [npc.verb(start)] to flow out of [npc.her] [npc.nipples+] and into [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] quickly [npc.verb(start)] streaming out into [npc.her] [npc.lowClothing(NIPPLES)] as [npc.she] [npc.verb(squeeze)] down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] immediately [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] immediately [npc.verb(start)] pouring out in a heavy flow, quickly soaking [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] exposed chest, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " softly pressing [npc.her] [npc.fingers+] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to gently press [npc.her] [npc.hands+]"
									+ " against [npc.her] exposed chest, before starting to softly grope and squeeze [npc.her] [npc.breastRows] [npc.breasts].",
								"Teasing [npc.her] [npc.fingers] over [npc.her] exposed [npc.breastRows] [npc.breasts+],"
										+ " [npc.name] [npc.verb(start)] gently [npc.moaning] and sighing as [npc.she] [npc.verb(begin)] to gently fondle and grope them."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] exposed chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] roughly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " forcefully grinding [npc.her] [npc.fingers+] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to forcefully grind [npc.her] [npc.hands+]"
									+ " against [npc.her] exposed chest, before starting to roughly grope and squeeze [npc.her] [npc.breastRows] [npc.breasts].",
								"Running [npc.her] [npc.fingers] over [npc.her] exposed [npc.breastRows] [npc.breasts+],"
										+ " [npc.name] [npc.verb(start)] loudly panting and [npc.moaning] as [npc.she] [npc.verb(begin)] to roughly fondle and grope them."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] exposed chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " pressing [npc.her] [npc.fingers+] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to press [npc.her] [npc.hands+]"
									+ " against [npc.her] exposed chest, before starting to grope and squeeze [npc.her] [npc.breastRows] [npc.breasts].",
								"Running [npc.her] [npc.fingers] over [npc.her] exposed [npc.breastRows] [npc.breasts+],"
										+ " [npc.name] [npc.verb(start)] panting and [npc.moaning] as [npc.she] [npc.verb(begin)] to fondle and grope them."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching up to [npc.her] exposed chest, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] eagerly fondling and groping [npc.her] [npc.breastRows] [npc.breasts+],"
										+ " desperately pressing [npc.her] [npc.fingers+] down against [npc.her] [npc.nipples+] in the process.",
								"Turned on and desperate to have [npc.her] [npc.breasts+] toyed with, [npc.name] [npc.verb(reach)] up to eagerly press [npc.her] [npc.hands+]"
									+ " against [npc.her] exposed chest, before starting to frantically grope and squeeze [npc.her] [npc.breastRows] [npc.breasts].",
								"Running [npc.her] [npc.fingers] over [npc.her] exposed [npc.breastRows] [npc.breasts+],"
										+ " [npc.name] [npc.verb(start)] desperately panting and [npc.moaning] as [npc.she] [npc.verb(begin)] to eagerly fondle and grope them."));
						break;
				}
				
				switch (Sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks out onto [npc.her] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc.namePos] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks out onto [npc.her] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc.namePos] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out over [npc.her] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc.namePos] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Almost immediately, [npc.her] [npc.milk] starts to flow out over [npc.her] [npc.fingers],"
								+ " and [npc.she] [npc.moansVerb] as [npc.she] [npc.verb(feel)] it running down over [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] quickly [npc.verb(start)] drooling out in a little stream over [npc.her] [npc.fingers] as [npc.she] [npc.verb(squeeze)] down on [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] immediately [npc.verb(start)] pouring out in a constant stream, quickly soaking [npc.her] [npc.breasts+] and dripping down onto the floor beneath [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] [npc.verb(start)] pouring out in a heavy flow, quickly soaking [npc.her] [npc.breasts+] and dripping down to form a large pool on the floor beneath [npc.herHim].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Sex.getCharacterPerformingAction().getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
}
