package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * Utility class to store data loaded from external files.
 */
public class ExternalEncounterData {
	private String name;
	private String triggerConditional;
	private boolean opportunistic;
	private String dialogueId;
	
	public ExternalEncounterData(String name, String triggerConditional, boolean opportunistic, String dialogueId) {
		this.name = name;
		this.triggerConditional = triggerConditional;
		this.opportunistic = opportunistic;
		this.dialogueId = dialogueId;
	}
	
	public float getTriggerChance() {
		try {
			return Float.valueOf(UtilText.parse(this.getTriggerConditional()).trim());
		} catch(Exception ex) {
			System.err.println("Error in AbstractEncounter's ExternalEncounterData: getTriggerChance() for '"+getName()+"' failed to parse!");
			ex.printStackTrace();
			return 0f;
		}
	}
	
	public String getName() {
		return name;
	}
	public String getTriggerConditional() {
		return triggerConditional;
	}
	public boolean isOpportunistic() {
		return opportunistic;
	}
	public String getDialogueId() {
		return dialogueId;
	}
}