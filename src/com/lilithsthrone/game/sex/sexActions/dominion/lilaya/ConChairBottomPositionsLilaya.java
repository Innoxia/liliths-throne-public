package com.lilithsthrone.game.sex.sexActions.dominion.lilaya;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.dominion.lilaya.SMChairTopLilaya;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.7
 * @version 0.1.7
 * @author Innoxia
 */
public class ConChairBottomPositionsLilaya {
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
			Sex.setSexManager(new SMChairTopLilaya());
		}
	};
}
