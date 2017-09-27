package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.79
 * @version 0.1.8
 * @author Innoxia
 */
public class PartnerSelfNoPen {
	
	public static final SexAction PARTNER_STROKE_VAGINA = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.VAGINA_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Touch self";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from touching yourself.";
		}

		@Override
		public String getDescription() {
			if(Sex.getPartner().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] legs, [npc.name] teases [npc.her] fingers over the entrance to [npc.her] [npc.pussy+], letting out [npc.a_moan+] as [npc.she] stimulates [npc.her] outer folds.",
						"[npc.Name] probes [npc.her] fingers down between [npc.her] legs, moaning softly as [npc.she] teases [npc.her] fingers over the entrance to [npc.her] inviting [npc.pussy].",
						"Sliding [npc.her] fingertips over [npc.her] neglected [npc.pussy+], [npc.name] lets out a moan as [npc.she] stimulates [npc.her] outer labia.",
						"[npc.Name] eagerly slides [npc.her] fingers over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] gently presses down on [npc.her] outer labia.");

			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] runs [npc.her] hand down over [npc.her] groin, pressing [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" down hard against [npc.her] needy [npc.pussy] as [npc.she] lets out a little whimper.",
						"[npc.Name] pushes [npc.her] fingers down between [npc.her] legs, [npc.moaning+] as [npc.she] rubs [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" against [npc.her] pussy lips.",
						"[npc.Name] slides [npc.her] fingertips over [npc.her] "+Sex.getPartner().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+", letting out [npc.a_moan+] as [npc.she] presses down and tries to stimulate [npc.her] [npc.pussy+] through [npc.her] clothing.",
						"Pushing down between [npc.her] legs with the palm of [npc.her] hand, [npc.name] squeezes [npc.her] thighs together as [npc.she] presses [npc.her] "
								+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName() +" tightly against [npc.her] neglected [npc.pussy].");
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	
	
	public static final SexAction PARTNER_STROKE_PENIS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.URETHRA_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Masturbate";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from stroking your [npc.cock].";
		}

		@Override
		public String getDescription() {
			if(Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] legs, [npc.name] takes hold of [npc.her] [npc.cock+], stroking up and down its length as [npc.she] lets out a low groan.",
						"[npc.Name] teases [npc.her] fingers over the [npc.cockHead+] of [npc.her] [npc.cock+], [npc.groaning+] as [npc.she] rubs [npc.her] thumb over the top.",
						"Taking hold of [npc.her] [npc.cock+] in one hand, [npc.name] eagerly starts jerking off.",
						"Wrapping [npc.her] fingers around [npc.her] [npc.cock+], [npc.name] lets out a series of [npc.groans+] as [npc.she] starts masturbating.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] runs [npc.her] [npc.hand] over [npc.her] groin, pressing [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+" down against [npc.her] [npc.cock+] as [npc.she] lets out a little [npc.groan].",
						"[npc.Name] slides [npc.her] fingers down between [npc.her] [npc.legs], letting out a little [npc.groan] as [npc.she] presses [npc.her] "
						 +Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+" down against [npc.her] [npc.cock+].",
						"[npc.Name] slides [npc.her] fingertips over [npc.her] "+Sex.getPartner().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+", before pressing down and trying to stimulate [npc.her] [npc.cock+] through [npc.her] clothing.",
						"Pushing down between [npc.her] [npc.legs] with the palm of [npc.her] [npc.hand], [npc.name] rubs [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
								+" tightly against [npc.her] concealed [npc.cock+].");
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
	
	
	public static final SexAction PARTNER_STROKE_MOUND = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPartner().hasPenis()
					&& !Sex.getPartner().hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke mound";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from touching your genderless mound.";
		}

		@Override
		public String getDescription() {
			if(Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] [npc.legs], [npc.name] runs [npc.her] fingertips over [npc.her] doll-like mound, [npc.moaning+] as [npc.she] teases the sensitive area.",
						"[npc.Name] teases [npc.her] fingers over the sensitive doll-like mound between [npc.her] legs, [npc.moaning+] as [npc.she] stimulates [npc.herself].",
						"With probing fingers, [npc.name] reaches down and starts to pinch and rub at [npc.her] delicate genderless crotch.",
						"Despite lacking genitalia, [npc.name]'s crotch remains a highly sensitive erogenous zone, and [npc.she] eagerly starts rubbing and pressing down on it with greedy fingers.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] runs [npc.her] [npc.hand] over [npc.her] groin, pressing [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" down against [npc.her] doll-like mound as [npc.she] lets out a little sigh.",
						"[npc.Name] pushes [npc.her] fingers down between [npc.her] legs, [npc.moaning+] as [npc.she] rubs [npc.her] "+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" against [npc.her] genderless crotch.",
						"[npc.Name] slides [npc.her] fingertips over [npc.her] "+Sex.getPartner().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", before pressing down and trying to stimulate [npc.her] doll-like mound through [npc.her] clothing.",
						"Pushing down between [npc.her] [npc.legs] with the palm of [npc.her] [npc.hand], [npc.name] squeezes [npc.her] thighs together as [npc.she] presses [npc.her] "
								+Sex.getPartner().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" tightly down against [npc.her] genderless mound.");
				
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_MASTURBATION));
		}
	};
	
}
