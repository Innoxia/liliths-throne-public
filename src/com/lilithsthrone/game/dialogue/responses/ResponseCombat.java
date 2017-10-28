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
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.69
 * @version 0.1.88
 * @author Innoxia
 */
public class ResponseCombat extends Response {

	private NPC opponent;
	
	private String playerStartingTitle;
	private String playerStartingDescription;
	private String opponentStartingTitle;
	private String opponentStartingDescription;
	
	public ResponseCombat(String title, String tooltipText, DialogueNodeOld nextDialogue, NPC opponent) {
		super(title, tooltipText, nextDialogue);
		this.opponent = opponent;
		
		playerStartingTitle = "Prepare";
		playerStartingDescription = "You prepare to make a move.";
		opponentStartingTitle = "Prepare";
		opponentStartingDescription = UtilText.parse(opponent, "[npc.Name] prepares to make a move.");
	}
	
	public ResponseCombat(String title, String tooltipText, DialogueNodeOld nextDialogue, NPC opponent,
			String playerStartingTitle,
			String playerStartingDescription,
			String opponentStartingTitle,
			String opponentStartingDescription) {
		super(title, tooltipText, nextDialogue);
		this.opponent = opponent;
		
		this.playerStartingTitle = playerStartingTitle;
		this.playerStartingDescription = playerStartingDescription;
		this.opponentStartingTitle = opponentStartingTitle;
		this.opponentStartingDescription = opponentStartingDescription;
	}
	
	public ResponseCombat(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue,
			NPC opponent,
			String playerStartingTitle,
			String playerStartingDescription,
			String opponentStartingTitle,
			String opponentStartingDescription,
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

		this.playerStartingTitle = playerStartingTitle;
		this.playerStartingDescription = playerStartingDescription;
		this.opponentStartingTitle = opponentStartingTitle;
		this.opponentStartingDescription = opponentStartingDescription;
	}

	@Override
	public boolean isCombatHighlight() {
		return true;
	}

	public DialogueNodeOld initCombat() {
		Combat.COMBAT.initialiseCombat(opponent, 0, playerStartingTitle, playerStartingDescription, opponentStartingTitle, opponentStartingDescription);
		return Combat.COMBAT.startCombat();
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}
