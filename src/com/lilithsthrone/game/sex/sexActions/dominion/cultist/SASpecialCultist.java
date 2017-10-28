package com.lilithsthrone.game.sex.sexActions.dominion.cultist;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.generic.Cultist;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.88
 * @version 0.1.88
 * @author Innoxia
 */
public class SASpecialCultist {

	public static final SexAction PARTNER_SEALED = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
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
			return Sex.isPlayerDom() && ((Cultist)Sex.getPartner()).isSealedSex();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] tries to make a move, but the Witch's Seal is too strong, and [npc.she] collapses back onto the altar, stunned.",
					"The purple glow of a pentagram materialises beneath [npc.name]'s body as [npc.she] tries to make a move; proof that the Witch's Seal is still keeping [npc.herHim] bound in place.",
					"[npc.Name] tries and sit up on the altar, but [npc.she]'s only able to squirm about a little under the immobilising effects of the Witch's Seal.",
					"The soft purple glow of the Witch's Seal can be seen all around [npc.name] as [npc.she] struggles to make a move.",
					"[npc.speech(~Mmm!~)] [npc.name] moans, struggling in vain against the Witch's Seal.",
					"[npc.speech(~Aah!~)] [npc.name] whimpers, squirming about on the altar as the With's Seal keeps her locked in place.");
		}
	};
	
	public static final SexAction PLAYER_SEALED = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "[style.boldArcane(Sealed!)]";
		}

		@Override
		public String getActionDescription() {
			return "The Witch's Seal that [npc.name] cast on you is preventing you from making a move!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isPlayerDom() && ((Cultist)Sex.getPartner()).isSealedSex();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You try to make a move, but the Witch's Seal is too strong, and you soon find yourself forced back onto the altar, stunned by the powerful spell.",
					"The purple glow of a pentagram materialises beneath your body as you try to make a move. An invisible arcane force quickly forces you back down onto the altar; proof that the Witch's Seal is still in effect.",
					"You try to sit up on the altar, but you end up only being able to squirm about a little under the immobilising effects of the Witch's Seal.",
					"The soft purple glow of the Witch's Seal can be seen all around your body as you struggle to make a move.",
					"[pc.speech(~Mmm!~)] you [pc.moan], struggling in vain against the Witch's Seal.",
					"[pc.speech(~Aah!~)] you whimper, squirming about on the altar as the With's Seal keeps you locked in place.");
		}
	};
}
