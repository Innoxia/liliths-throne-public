package com.base.game.sex.sexActions.dominion.lilaya;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.Sex;
import com.base.game.sex.managers.dominion.lilaya.SMChairBottomLilaya;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.7
 * @version 0.1.7
 * @author Innoxia
 */
public class ConChairTopPositionsLilaya {
	public static SexAction PLAYER_SWITCH_CHAIR_POSITIONS = new SexAction(
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
					"Switch positions with "+Sex.getPartner().getName("the")+", so that you're the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getPartner(),
					"You reach down and grab "+Sex.getPartner().getName("the")+"'s "+Sex.getPartner().getHipSize().getDescriptor()+" hips, and with a determined pull, you cause <herPro> to stand up."
					+ " Still holding <her> hips, you move <herPro> to one side, sitting down in the space that <she> just vacated before pulling <herPro> forwards into your crotch."
					+ " Looking down at you, <she> smiles, "
					+ UtilText.parseSpeech("You want a go on the bottom, huh?", Sex.getPartner()));
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMChairBottomLilaya());
		}
	};
}
