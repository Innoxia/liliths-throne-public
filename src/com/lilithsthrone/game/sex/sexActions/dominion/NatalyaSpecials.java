package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class NatalyaSpecials {

	public static final SexAction PARTNER_DEMAND_FACIAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDemandedFacial)
					&& Main.game.getNpc(Natalya.class).getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		@Override
		public String getActionTitle() {
			return "Demand facial";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getDescription() {
			return "Natalya's breathing starts to get faster and heavier, and as her [natalya.cockGirth], bestial cock starts wildly throbbing in your [pc.hands], she desperately paws on the ground with one of her front hoofs and calls out,"
					+ " [natalya.speechNoEffects(Slave! ~Ooh!~ I am getting close! ~Aah!~ As your reward, you are to... ~Ooh!~ ...you are to take my load all over your face! ~Mmm!~)]";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDemandedFacial, true);
		}
	};
	
	public static final SexAction PLAYER_PREPARE_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Prepare ([style.colourMinorGood(facial)])";
		}
		@Override
		public String getActionDescription() {
			return "[style.colourMinorGood(Do as Natalya commands)] and position your [pc.face] in front of her horse-like cock, thereby preparing to receive a facial from her.";
		}
		@Override
		public String getDescription() {
			return "Letting out a lewd moan, Natalya cries out, [natalya.speechNoEffects(Slave! I am about... ~Ooh!~ ...about to cum! Do as I command and take my load on your face!)]"
					+ "<br/><br/>"
					+ "With the [natalya.race]'s [natalya.cockGirth] cock wildly throbbing in your [pc.hands], you do as you're told and shuffle around in front of it."
					+ " Continuing to rub your Mistress's musky precum up and down the length of her hot shaft, you point the flared head directly at your face and prepare to receive your reward...";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, true);
		}
		@Override
		public int getActionRenderingPriority() {
			return 1;
		}
	};
	
	public static final SexAction PLAYER_PREPARE_NO_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Prepare ([style.colourMinorBad(floor)])";
		}
		@Override
		public String getActionDescription() {
			return "[style.colourMinorBad(Refuse to do as Natalya commands)], and instead of receiving a facial from her, aim her cock away from you, causing her to cum all over the floor.";
		}
		@Override
		public String getDescription() {
			return "Letting out a lewd moan, Natalya cries out, [natalya.speechNoEffects(Slave! I am about... ~Ooh!~ ...about to cum! Do as I command and take my load on your face!)]"
					+ "<br/><br/>"
					+ "With the [natalya.race]'s [natalya.cockGirth] cock wildly throbbing in your [pc.hands], you decide that you'd rather not do as you're told and instead shuffle around to the side of it."
					+ " Continuing to rub your Mistress's musky precum up and down the length of her hot shaft, you point the flared head away from you and prepare for it to unload all over the floor...";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, false);
		}
	};
}
