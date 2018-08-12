package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.7
 * @version 0.2.9
 * @author Innoxia
 */
public class SALilayaSpecials {
	
	// Demand pull out
	public static final SexAction PARTNER_DEMAND_PULL_OUT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
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
			return (Main.game.getPlayer().getArousal() >= ArousalLevel.THREE_HEATED.getMinimumValue()
					|| Sex.getActivePartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue())
					&& !SexFlags.partnerRequestedPullOut
					&& !Main.game.getLilaya().isVisiblyPregnant()
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you,"
					+" [npc.speech(Just remember to pull out! I'm <b>not</b> getting pregnant!)]";
				
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedPullOut = true;
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					return "[npc.Name] lets out a soft [npc.moan] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_ROUGH:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_EAGER:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_RESISTING:
					return "[npc.Name] lets out [npc.a_moan+] as [npc.she] desperately tries to pull away from you before you orgasm.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
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
			return Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.game.getLilaya().isVisiblyPregnant()
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
					+ "[npc.speech(Pull out! I don't want to get pregnant!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedPullOut = true;
		}
	};
	
	// Furious stop sex
	public static final SexAction PARTNER_FURIOUS_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
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
			return Sex.getActivePartner().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)
					&& !Main.game.getLilaya().isVisiblyPregnant()
					&& !Sex.getCharacterPerformingAction().isPlayer();
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
