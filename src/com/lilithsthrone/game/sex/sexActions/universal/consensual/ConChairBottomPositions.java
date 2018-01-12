package com.lilithsthrone.game.sex.sexActions.universal.consensual;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.consensual.SMChair;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.69.9
 * @version 0.1.97
 * @author Innoxia
 */
public class ConChairBottomPositions {
	
	public static final SexAction PLAYER_SWITCH_CHAIR_POSITIONS = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyNonSelfPenetrationHappening();
		}
		
		@Override
		public String getActionTitle() {
			return "Switch positions";
		}

		@Override
		public String getActionDescription() {
			return UtilText.genderParsing(Sex.getActivePartner(),
					"Switch positions with "+Sex.getActivePartner().getName("the")+", so that <she>'s the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getActivePartner(),
					"You reach up and grab "+Sex.getActivePartner().getName("the")+"'s "+Sex.getActivePartner().getHipSize().getDescriptor()+" hips, and with a determined push, you cause <herPro> to take a step back."
					+ " Still holding <her> hips, you stand up, moving <herPro> around before pushing <herPro> down into the space that you just vacated."
					+ " <She> smiles up at you as you step forwards between <her> legs, "
					+ UtilText.parseSpeech("You want a go on the top, huh?", Sex.getActivePartner()));
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMChair(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP)),
					Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_BOTTOM))));
		}
	};
}
