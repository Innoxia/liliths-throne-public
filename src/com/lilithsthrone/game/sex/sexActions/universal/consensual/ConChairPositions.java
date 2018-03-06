package com.lilithsthrone.game.sex.sexActions.universal.consensual;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
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
public class ConChairPositions {
	
	public static final SexAction PLAYER_SWITCH_CHAIR_POSITION_TO_TOP = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {

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
			return UtilText.parse(Sex.getActivePartner(),
					"Switch positions with [npc.name], so that [npc.she]'s the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getActivePartner(),
					"You reach up and grab [npc.name]'s [npc.hipSize] hips, and with a determined push, you cause [npc.herHim] to take a step back."
					+ " Still holding [npc.her] hips, you stand up, moving [npc.herHim] around before pushing [npc.herHim] down into the space that you just vacated."
					+ " [npc.She] smiles up at you as you step forwards between [npc.her] legs,"
					+ " [npc.speech(You want a go on the top, huh?)]");
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Main.game.getPlayer())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_BOTTOM))));
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP))));
			}
		}
	};
	
	public static final SexAction PLAYER_SWITCH_CHAIR_POSITION_TO_BOTTOM = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {

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
			return UtilText.parse(Sex.getActivePartner(),
					"Switch positions with [npc.name], so that you're the one sitting down.");
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getActivePartner(),
					"You reach down and grab [npc.name]'s [npc.hipSize] hips, and with a determined pull, you cause [npc.herHim] to stand up."
					+ " Still holding [npc.her] hips, you move [npc.herHim] to one side, sitting down in the space that [npc.she] just vacated before pulling [npc.herHim] forwards into your crotch."
					+ " Looking down at you, [npc.she] smiles,"
					+ " [npc.speech(You want a go on the bottom, huh?)]");
		}

		@Override
		public void applyEffects() {
			if(Sex.isDom(Main.game.getPlayer())) {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM)),
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_TOP))));
			} else {
				Sex.setSexManager(new SMChair(
						Util.newHashMapOfValues(new Value<>(Sex.getActivePartner(), SexPositionSlot.CHAIR_TOP)),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM))));
			}
		}
	};
}
