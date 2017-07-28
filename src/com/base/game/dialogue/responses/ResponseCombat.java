package com.base.game.dialogue.responses;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.combat.Combat;
import com.base.game.dialogue.DialogueNodeOld;

/**
 * @since 0.1.69
 * @version 0.1.82
 * @author Innoxia
 */
public class ResponseCombat extends Response {

	private NPC opponent;
	
	public ResponseCombat(String title, String tooltipText, DialogueNodeOld nextDialogue, NPC opponent) {
		super(title, tooltipText, nextDialogue);
		this.opponent = opponent;
	}
	
	public ResponseCombat(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue,
			NPC opponent,
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired) {
		super(title, tooltipText, nextDialogue,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired,
				null, null, null);
		this.opponent = opponent;
	}

	@Override
	public boolean isCombatHighlight() {
		return true;
	}

	public DialogueNodeOld initCombat() {
		Combat.COMBAT.initialiseCombat(opponent, 0);
		return Combat.COMBAT.startCombat();
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}
