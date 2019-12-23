package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum CaptiveInteractionType {
	
	TEASE {
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return true;
		}
	},
	
	SEX {
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return true;
		}
	},
	
	SEX_THREESOME {
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasVagina() || Main.game.isAnalContentEnabled() || target.hasPenis();
		}
	},
	
	MILKING {
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.isPlayer()) {
				return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStarted);
			}
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStartedCompanion);
		}
	},
	
	PUNISHMENT {
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive();
		}
	}
	;
	
	private CaptiveInteractionType() {
	}
	
	public abstract boolean isConditionsMet(GameCharacter target);
	
	public static CaptiveInteractionType getRandomType(GameCharacter target) {
		List<CaptiveInteractionType> types = new ArrayList<>(Arrays.asList(CaptiveInteractionType.values()));
		types.removeIf(cit -> !cit.isConditionsMet(target));
		return Util.randomItemFrom(types);
	}
}
