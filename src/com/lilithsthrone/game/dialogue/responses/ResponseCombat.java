package com.lilithsthrone.game.dialogue.responses;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;

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
