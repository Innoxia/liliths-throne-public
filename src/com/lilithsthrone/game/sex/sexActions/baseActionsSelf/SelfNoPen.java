package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfNoPen {
	
	public static final SexAction STROKE_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
		}

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
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] legs, [npc.name] [npc.verb(tease)] [npc.her] fingers over the entrance to [npc.her] [npc.pussy+], letting out [npc.a_moan+] as [npc.she] [npc.verb(stimulate)] [npc.her] outer folds.",
						"[npc.Name] [npc.verb(probe)] [npc.her] fingers down between [npc.her] legs, [npc.moaning] softly as [npc.she] [npc.verb(tease)] [npc.her] fingers over the entrance to [npc.her] inviting [npc.pussy].",
						"Sliding [npc.her] fingertips over [npc.her] neglected [npc.pussy+], [npc.name] [npc.verb(let)] out a [npc.moan] as [npc.she] [npc.verb(stimulate)] [npc.her] outer labia.",
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] fingers over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] gently [npc.verb(press)] down on [npc.her] outer labia.");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] hand down over [npc.her] groin, pressing [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" down hard against [npc.her] needy [npc.pussy] as [npc.she] [npc.verb(let)] out a little whimper.",
						"[npc.Name] [npc.verb(push)] [npc.her] fingers down between [npc.her] legs, [npc.moaning+] as [npc.she] [npc.verb(rub)] [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" against [npc.her] pussy lips.",
						"[npc.Name] [npc.verb(slide)] [npc.her] fingertips over [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+", letting out [npc.a_moan+] as [npc.she] [npc.verb(press)] down and [npc.verb(try)] to stimulate [npc.her] [npc.pussy+] through [npc.her] clothing.",
						"Pushing down between [npc.her] legs with the palm of [npc.her] hand, [npc.name] [npc.verb(squeeze)] [npc.her] thighs together as [npc.she] [npc.verb(press)] [npc.her] "
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName() +" tightly against [npc.her] neglected [npc.pussy].");
			}
		}
		
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
			}
		}
		
	};
	
	public static final SexAction STROKE_MOUND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().hasPenis()
					&& !Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
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
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(run)] [npc.her] fingertips over [npc.her] doll-like mound, [npc.moaning+] as [npc.she] [npc.verb(tease)] the sensitive area.",
						"[npc.Name] [npc.verb(tease)] [npc.her] fingers over the sensitive doll-like mound between [npc.her] legs, [npc.moaning+] as [npc.she] [npc.verb(stimulate)] [npc.herself].",
						"With probing fingers, [npc.name] [npc.verb(reach)] down and [npc.verb(start)] to pinch and rub at [npc.her] delicate genderless crotch.",
						"Despite lacking genitalia, [npc.namePos] crotch remains a highly sensitive erogenous zone, and [npc.she] eagerly [npc.verb(start)] rubbing and pressing down on it with greedy fingers.");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] [npc.hand] over [npc.her] groin, pressing [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" down against [npc.her] doll-like mound as [npc.she] [npc.verb(let)] out a little sigh.",
						"[npc.Name] [npc.verb(push)] [npc.her] fingers down between [npc.her] legs, [npc.moaning+] as [npc.she] [npc.verb(rub)] [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" against [npc.her] genderless crotch.",
						"[npc.Name] [npc.verb(slide)] [npc.her] fingertips over [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", before pressing down and trying to stimulate [npc.her] doll-like mound through [npc.her] clothing.",
						"Pushing down between [npc.her] [npc.legs] with the palm of [npc.her] [npc.hand], [npc.name] [npc.verb(squeeze)] [npc.her] thighs together as [npc.she] [npc.verb(press)] [npc.her] "
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" tightly down against [npc.her] genderless mound.");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return null;
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			}
		}
	};
	
}
