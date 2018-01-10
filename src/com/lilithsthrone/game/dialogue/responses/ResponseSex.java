package com.lilithsthrone.game.dialogue.responses;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69
 * @version 0.1.97
 * @author Innoxia
 */
public class ResponseSex extends Response {
	
	private boolean consensual;
	private boolean subHasEqualControl;
	private List<GameCharacter> submissives;
	private List<GameCharacter> dominants;
	private SexManagerInterface sexManager;
	private DialogueNodeOld postSexDialogue;
	private String sexStartDescription;

	public ResponseSex(String title, String tooltipText, boolean consensual,
			boolean canResist, List<GameCharacter> dominants, List<GameCharacter> submissives, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue) {
		this(title, tooltipText, consensual, canResist, dominants, submissives, sexManager, postSexDialogue, "");
	}
	
	public ResponseSex(String title, String tooltipText, boolean consensual,
			boolean subHasEqualControl, List<GameCharacter> dominants, List<GameCharacter> submissives, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		this(title, tooltipText,
				null, null, null, null,
				null, null, consensual,
				subHasEqualControl, dominants, submissives, sexManager, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title, String tooltipText, boolean consensual,
			boolean canResist, GameCharacter dominant, GameCharacter submissive, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue) {
		this(title, tooltipText, consensual, canResist, Util.newArrayListOfValues(new ListValue<>(dominant)), Util.newArrayListOfValues(new ListValue<>(submissive)), sexManager, postSexDialogue, "");
	}
	
	public ResponseSex(String title, String tooltipText, boolean consensual,
			boolean subHasEqualControl, GameCharacter dominant, GameCharacter submissive, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		this(title, tooltipText,
				null, null, null, null,
				null, null, consensual,
				subHasEqualControl,  Util.newArrayListOfValues(new ListValue<>(dominant)), Util.newArrayListOfValues(new ListValue<>(submissive)), sexManager, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title, String tooltipText, List<Fetish> fetishesForUnlock,
			List<Fetish> fetishesBlocking, CorruptionLevel corruptionBypass, List<Perk> perksRequired,
			Femininity femininityRequired, Race raceRequired, boolean consensual,
			boolean subHasEqualControl, GameCharacter dominant, GameCharacter submissive, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		this(title, tooltipText,
				null, null, null, null,
				null, null, consensual,
				subHasEqualControl,  Util.newArrayListOfValues(new ListValue<>(dominant)), Util.newArrayListOfValues(new ListValue<>(submissive)), sexManager, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title, String tooltipText,
			List<Fetish> fetishesForUnlock, List<Fetish> fetishesBlocking, CorruptionLevel corruptionBypass, List<Perk> perksRequired,
			Femininity femininityRequired, Race raceRequired, boolean consensual,
			boolean subHasEqualControl, List<GameCharacter> dominants, List<GameCharacter> submissives, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		super(title, tooltipText, null,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired);
		
		this.consensual = consensual;
		this.subHasEqualControl = subHasEqualControl;
		
		if(dominants==null) {
			this.dominants = new ArrayList<>();
		} else {
			this.dominants = dominants;
		}
		
		if(submissives==null) {
			this.submissives = new ArrayList<>();
		} else {
			this.submissives = submissives;
		}
		
		this.sexManager = sexManager;
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
		return Sex.SEX.initialiseSex(consensual, subHasEqualControl, dominants, submissives, sexManager, postSexDialogue, sexStartDescription);
	}
	
	public SexPace getStartingSexPaceModifier(GameCharacter character) {
		return null;
	}
	
}
