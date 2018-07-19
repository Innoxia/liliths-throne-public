package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.2.9
 * @author Innoxia
 */
public class ResponseSex extends Response {
	
	private boolean consensual;
	private boolean subHasEqualControl;
	private SexManagerInterface sexManager;
	private List<GameCharacter> spectators;
	private DialogueNodeOld postSexDialogue;
	private String sexStartDescription;

	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean canResist,
			SexManagerInterface sexManager,
			List<GameCharacter> spectators,
			DialogueNodeOld postSexDialogue) {
		this(title, tooltipText, consensual, canResist, sexManager, spectators, postSexDialogue, "");
	}

	public ResponseSex(String title,
			String tooltipText,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> spectators,
			DialogueNodeOld postSexDialogue, String sexStartDescription) {
		this(title, tooltipText,
				null, null, null, null,
				null, null, consensual,
				subHasEqualControl, sexManager, spectators, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title,
			String tooltipText,
			List<Fetish> fetishesForUnlock,
			List<Fetish> fetishesBlocking,
			CorruptionLevel corruptionBypass,
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			boolean consensual,
			boolean subHasEqualControl,
			SexManagerInterface sexManager,
			List<GameCharacter> spectators,
			DialogueNodeOld postSexDialogue, String sexStartDescription) {
		super(title, tooltipText, null,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired);
		
		this.consensual = consensual;
		this.subHasEqualControl = subHasEqualControl;
		
		this.sexManager = sexManager;
		
		if(spectators==null) {
			this.spectators = new ArrayList<>();
		} else {
			this.spectators = spectators;
		}
		
		this.postSexDialogue = postSexDialogue;
		this.sexStartDescription = sexStartDescription;
	}
	
	@Override
	public boolean isSexHighlight() {
		return true;
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
	
	public DialogueNodeOld initSex() {
		return Main.sexEngine.initialiseSex(consensual, subHasEqualControl, sexManager, spectators, postSexDialogue, sexStartDescription);
	}
	
	
	
}
