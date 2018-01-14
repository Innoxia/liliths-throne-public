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
public class ConChairPositionsLilaya {
	public static final SexAction PLAYER_SWITCH_CHAIR_POSITION_TO_BOTTOM = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyNonSelfPenetrationHappening() && (Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_TOP || Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_TOP_LILAYA);
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
			if(Sex.isDom(Main.game.getPlayer())) {
				Sex.setSexManager(new SMChairLilaya(
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM_LILAYA)),
						Util.newHashMapOfValues(new Value<>(Main.game.getLilaya(), SexPositionSlot.CHAIR_TOP_LILAYA))));
			} else {
				Sex.setSexManager(new SMChairLilaya(
						Util.newHashMapOfValues(new Value<>(Main.game.getLilaya(), SexPositionSlot.CHAIR_TOP_LILAYA)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM_LILAYA))));
			}
		}
	};
	
	public static final SexAction PLAYER_SWITCH_CHAIR_POSITION_TO_TOP = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyNonSelfPenetrationHappening() && (Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_BOTTOM || Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_BOTTOM_LILAYA);
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
			if(Sex.isDom(Main.game.getPlayer())) {
				Sex.setSexManager(new SMChairLilaya(
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP_LILAYA)),
						Util.newHashMapOfValues(new Value<>(Main.game.getLilaya(), SexPositionSlot.CHAIR_BOTTOM_LILAYA))));
			} else {
				Sex.setSexManager(new SMChairLilaya(
						Util.newHashMapOfValues(new Value<>(Main.game.getLilaya(), SexPositionSlot.CHAIR_BOTTOM_LILAYA)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP_LILAYA))));
			}
		}
	};
}
