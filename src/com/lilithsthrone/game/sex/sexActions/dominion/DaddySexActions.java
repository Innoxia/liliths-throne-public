package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMDaddyDinnerOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DaddySexActions {

	public static final SexAction ORAL_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Waitress approaching";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.getCharacterPerformingAction() instanceof Daddy)
					&& Sex.getSexManager().isPartnerWantingToStopSex(Sex.getCharacterPerformingAction())
					&& (Sex.getSexManager() instanceof SMDaddyDinnerOral);
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			boolean lilayaPresent = Main.game.getCharactersPresent().contains(Main.game.getNpc(Lilaya.class));
			return "Now that [daddy.nameHasFull] been satisfied, [daddy.she] suddenly seems a lot more aware of [npc.her] surroundings, and, leaning down a little so as to speak to you"
					+ (lilayaPresent?" and Lilaya":"")
					+" under the table, [npc.she] hisses,"
					+ " [daddy.speechNoEffects(I think one of the waitresses knows what's going on! Sit back up!)]"
					+ "<br/><br/>"
					+ "Disappointed that [daddy.name] wants to end this so soon, "+(lilayaPresent?"the two of":"")+" you sulkily slide back up into your "+(lilayaPresent?"seats":"seat")+" from under the table,"
							+ " before making a little show of licking your lips and grinning mischievously at [daddy.herHim]."
					+ " Sure enough, just as you've finished making this slutty display, you see one of the waitresses walking over towards you...";
		}

		@Override
		public void applyEffects() {
			Sex.addRemoveEndSexAffection(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
