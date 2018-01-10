package com.lilithsthrone.game.sex.sexActions.dominion.brax;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.dominion.brax.SMBraxSubCowgirl;
import com.lilithsthrone.game.sex.managers.dominion.brax.SMBraxSubDoggy;
import com.lilithsthrone.game.sex.managers.dominion.brax.SMBraxSubStart;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.8
 * @version 0.1.8
 * @author Innoxia
 */
public class BraxPositioning {
	
	// Doggy:
	public static final SexAction PLAYER_FORCE_POSITION_DOGGY = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPositionType.DOGGY_PARTNER_ON_ALL_FOURS
					&& Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Doggy-style";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] down onto all fours and kneel behind [npc.herHim], ready to fuck [npc.herHim] in the doggy-style position.";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s shoulders, you push [npc.herHim] down on all fours."
					+ " Stepping around behind [npc.herHim], you drop down onto your knees, shuffling forwards to grind your crotch against [npc.her] [npc.ass+]."
					+ " Grabbing hold of [npc.her] [npc.hips+], you [pc.moan], "
					+ "[pc.speech(Be a good [npc.girl] and hold still while I fuck you like the bitch you are!)]";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBraxSubDoggy());

			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
			} else {
				return null;
			}
		}
	};
	
	// Cowgirl
	public static final SexAction PLAYER_FORCE_POSITION_COWGIRL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Cowgirl";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name] onto [npc.his] back and straddle [npc.his] stomach, getting ready to ride [npc.him], cowgirl-style.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPositionType.COWGIRL_PLAYER_TOP
					&& Sex.isDom(Main.game.getPlayer())
					&& Sex.getActivePartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return "Bending down, you take hold of [npc.name]'s shoulders, throwing [npc.him] a mischievous grin before pushing [npc.him] forcefully down onto [npc.his] back."
					+ " Before [npc.he] has a chance to sit back up, you jump down on his stomach, letting out a little laugh as you find yourself straddling [npc.name] in the cowgirl position.";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBraxSubCowgirl());

			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	// Return to oral:
	public static final SexAction PLAYER_FORCE_POSITION_KNEELING_ORAL = new SexAction(
			SexActionType.PLAYER_POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Kneeling";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to kneel before you again.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !SexFlags.positioningBlockedPlayer
					&& Sex.getPosition() != SexPositionType.KNEELING_PARTNER_PERFORMING_ORAL
					&& Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "Feeling like you want a change of pace, you lean down and take hold of [npc.name]'s shoulders, before pulling [npc.him] to [npc.his] knees as you stand back up.";
		}

		@Override
		public void applyEffects() {
			Sex.setSexManager(new SMBraxSubStart());

			SexFlags.positioningBlockedPartner = true;
			SexFlags.resetRequests();
		}
	};
}
