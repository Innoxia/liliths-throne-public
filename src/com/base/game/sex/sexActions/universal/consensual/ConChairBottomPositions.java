package com.base.game.sex.sexActions.universal.consensual;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.Sex;
import com.base.game.sex.managers.universal.consensual.SMChairTop;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.69.9
 * @version 0.1.69.9
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
			return UtilText.genderParsing(Sex.getPartner(),
					"Switch positions with "+Sex.getPartner().getName("the")+", so that <she>'s the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getPartner(),
					"You reach up and grab "+Sex.getPartner().getName("the")+"'s "+Sex.getPartner().getHipSize().getDescriptor()+" hips, and with a determined push, you cause <herPro> to take a step back."
					+ " Still holding <her> hips, you stand up, moving <herPro> around before pushing <herPro> down into the space that you just vacated."
					+ " <She> smiles up at you as you step forwards between <her> legs, "
					+ UtilText.parseSpeech("You want a go on the top, huh?", Sex.getPartner()));
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMChairTop()); // TODO May need override in some sex scenes
		}
	};
}
