package com.base.game.sex.sexActions.dominion.lilaya;

import com.base.game.character.attributes.ArousalLevel;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.StatusEffect;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;

/**
 * @since 0.1.7
 * @version 0.1.78
 * @author Innoxia
 */
public class SALilayaSpecials {
	
	// Demand pull out
	public static SexAction PARTNER_DEMAND_PULL_OUT = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getPlayer().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					|| Sex.getPartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue())
					&& !SexFlags.partnerRequestedPullOut
					&& !Main.game.getLilaya().isVisiblyPregnant();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
					+"[npc.speech(Just remember to pull out! I'm <b>not</b> getting pregnant!)]";
				
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedPullOut = true;
		}
	};
	
	
	// Furious stop sex
	public static SexAction PARTNER_FURIOUS_STOP_SEX = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& !Main.game.getLilaya().isVisiblyPregnant();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.Name] feels your [pc.cum] dripping out of her [lilaya.pussy+], and with an angry cry, she violently shoves you away from her, "
					+ "[npc.speechNoEffects(What the fuck?! I told you to pull out!)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
