package com.base.game.dialogue.responses;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.managers.SexManagerInterface;

/**
 * @since 0.1.69
 * @version 0.1.78
 * @author Innoxia
 */
public class ResponseSex extends Response {
	
	private NPC partner;
	private SexManagerInterface sexManager;
	private DialogueNodeOld postSexDialogue;
	private String sexStartDescription;
	protected SexPace sexPacePlayer, sexPacePartner;

	public ResponseSex(String title, String tooltipText, DialogueNodeOld nextDialogue,
			NPC partner, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue) {
		this(title, tooltipText, nextDialogue,
				 partner, sexManager, postSexDialogue, "");
	}
	
	public ResponseSex(String title, String tooltipText, DialogueNodeOld nextDialogue,
			NPC partner, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		this(title, tooltipText, nextDialogue,
				null, null, null,
				null, null, null,
				 partner, sexManager, postSexDialogue, sexStartDescription);
	}
	
	public ResponseSex(String title, String tooltipText, DialogueNodeOld nextDialogue,
			List<Fetish> fetishesForUnlock, List<Fetish> fetishesBlocking, CorruptionLevel corruptionBypass,
			List<Perk> perksRequired, Femininity femininityRequired, Race raceRequired,
			NPC partner, SexManagerInterface sexManager, DialogueNodeOld postSexDialogue, String sexStartDescription
			) {
		super(title, tooltipText, nextDialogue,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired);
		
		this.partner=partner;
		this.sexManager=sexManager;
		this.postSexDialogue=postSexDialogue;
		this.sexStartDescription=sexStartDescription;
	}
	
	@Override
	public boolean isSexHighlight() {
		return true;
	}
	
	public DialogueNodeOld initSex() {
		return Sex.SEX.initialiseSex(partner, sexManager, postSexDialogue, sexStartDescription, sexPacePlayer, sexPacePartner);
	}
	

	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
	
}
