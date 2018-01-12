package com.lilithsthrone.game.sex.sexActions.dominion.lilaya;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.lilaya.SMChairLilaya;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.7
 * @version 0.1.97
 * @author Innoxia
 */
public class ConChairTopPositionsLilaya {
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
					"Switch positions with "+Sex.getActivePartner().getName("the")+", so that you're the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.genderParsing(Sex.getActivePartner(),
					"You reach down and grab "+Sex.getActivePartner().getName("the")+"'s "+Sex.getActivePartner().getHipSize().getDescriptor()+" hips, and with a determined pull, you cause <herPro> to stand up."
					+ " Still holding <her> hips, you move <herPro> to one side, sitting down in the space that <she> just vacated before pulling <herPro> forwards into your crotch."
					+ " Looking down at you, <she> smiles, "
					+ UtilText.parseSpeech("You want a go on the bottom, huh?", Sex.getActivePartner()));
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMChairLilaya(
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM_LILAYA)),
					Util.newHashMapOfValues(new Value<>(Main.game.getLilaya(), SexPositionSlot.CHAIR_TOP_LILAYA))));
		}
	};
}
