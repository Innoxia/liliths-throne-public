package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.0
 * @author Innoxia
 */
public class PlayerSelfNoPen {
	
	public static final SexAction PLAYER_STROKE_VAGINA_SUB = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.HANDS);
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke pussy (self)";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from touching yourself.";
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between your [pc.legs+], you tease your fingers over the entrance to your [pc.pussy+], letting out a deep sigh as you stimulate your outer folds.",
						"You probe your fingers down between your [pc.legs+], moaning softly as you tease your fingers over the entrance to your inviting [pc.pussy].",
						"Sliding your fingertips over your neglected [pc.pussy], you let out [pc.a_moan+] as you start stimulating your outer labia.",
						"You eagerly slide your fingers over your needy [pc.pussy], moaning and sighing as you gently press down on your outer labia.");

			} else {
				return UtilText.returnStringAtRandom(
						"You run your hand over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" down hard against your needy [pc.pussy] as you let out a little whimper.",
						"You push your fingers down between your [pc.legs+], moaning and sighing as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" rubbing against your pussy lips.",
						"You slide your fingertips over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()+", before pressing down and trying to stimulate your [pc.pussy+] through your clothing.",
						"Pushing down between your legs with the palm of your [pc.hand], you squeeze your thighs together as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" being pressed tightly against your neglected [pc.pussy].");
			}
		}
		
		@Override
		public void applyEffects() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.FINGER, Main.game.getPlayer(), SexAreaOrifice.VAGINA);
			}
		}
		
	};
	
	
	public static final SexAction PLAYER_STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.HANDS);
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke cock (self)";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from stroking your [pc.cock].";
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between your [pc.legs+], you take hold of your [pc.cock+], stroking up and down its length as you let out [pc.a_moan+].",
						"You tease your [pc.fingers] over the [pc.cockHead+] of your [pc.cock+], groaning and sighing as you start jerking off.",
						"Taking hold of your [pc.cock+] in one hand, you let out [pc.a_moan+] as you start jerking off.",
						"Wrapping your [pc.fingers] around your [pc.cock+], you let out a series of [pc.groans+] as you start masturbating.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"You run your [pc.hand] over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+" down against your [pc.cock+] as you let out a little [pc.groan].",
						"You slide your fingers down between your [pc.legs+], letting out a little [pc.groan] as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
								+" rubbing down against your [pc.cock+].",
						"You slide your fingertips over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+", before pressing down and trying to stimulate your [pc.cock+] through your clothing.",
						"Pushing down between your legs with the palm of your hand, you rub up and down as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
								+" being pressed tightly against your concealed [pc.cock].");
			}
		}
		
		@Override
		public void applyEffects() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				Sex.transferLubrication(Main.game.getPlayer(), SexAreaPenetration.FINGER, Main.game.getPlayer(), SexAreaPenetration.PENIS);
			}
		}
		
	};
	
	
	
	public static final SexAction PLAYER_STROKE_MOUND = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasPenis()
					&& !Main.game.getPlayer().hasVagina()
					 && Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.HANDS);
		}
		
		@Override
		public String getActionTitle() {
			return "Stroke mound (self)";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from touching your genderless mound.";
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Reaching down between your legs, you run your fingertips over your doll-like mound, moaning and sighing as you tease the sensitive area.",
						"You tease your fingers over the sensitive doll-like mound between your legs, sighing and whining as you stimulate yourself.",
						"With probing fingers, you reach down and start to pinch and rub at your surprisingly delicate genderless crotch.",
						"Despite lacking genitalia, your crotch remains a highly sensitive erogenous zone, and you eagerly start rubbing and pressing down on it with greedy fingers.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"You run your hand over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" down against your doll-like mound as you let out a little sigh.",
						"You push your fingers down between your legs, moaning and sighing as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" rubbing against your genderless crotch.",
						"You slide your fingertips over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", before pressing down and trying to stimulate your doll-like mound through your clothing.",
						"Pushing down between your legs with the palm of your hand, you squeeze your thighs together as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" being pressed tightly against your genderless mound.");
			}
			
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
}
