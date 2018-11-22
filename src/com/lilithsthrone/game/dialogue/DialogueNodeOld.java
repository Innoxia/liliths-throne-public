package com.lilithsthrone.game.dialogue;

import java.io.Serializable;

import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public abstract class DialogueNodeOld implements Serializable {

	protected static final long serialVersionUID = 1L;

	private String label, description;
	private boolean travelDisabled, continuesDialogue;

	public DialogueNodeOld(String label, String description, boolean travelDisabled) {
		this(label, description, travelDisabled, false);
	}

	public DialogueNodeOld(String label, String description, boolean travelDisabled, boolean continuesDialogue) {
		this.label = label;
		this.description = description;
		this.travelDisabled = travelDisabled;

		this.continuesDialogue = continuesDialogue;
	}
	
	public int getMinutesPassed(){
		return 0;
	}

	/**
	 * Content that is set above the main content. It is not affected by fade-in
	 * effects.
	 */
	public String getHeaderContent() {
		return null;
	}

	/**
	 * The main content for this DialogueNode. It is affected by fade-in
	 * effects.
	 */
	public abstract String getContent();

	@Override
	public String toString() {
		return label + ":" + description.substring(0, description.length() <= 20 ? description.length() : 20);
	}

	public String getLabel() {
		if(Main.game.isStarted() && (label==null || label.isEmpty())) {
			return Main.game.getPlayerCell().getPlace().getName();
		}
		return label;
	}

	public DialogueNodeOld setLabel(String label) {
		this.label = label;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public boolean isContinuesDialogue() {
		return continuesDialogue;
	}
	
	public boolean isDisplaysActionTitleOnContinuesDialogue() {
		return true;
	}

	public boolean isNoTextForContinuesDialogue() {
		return false;
	}
	
	/**
	 * Starts at 0.
	 */
	public String getResponseTabTitle(int index) {
		return null;
	}
	
	public abstract Response getResponse(int responseTab, int index);
	
	public boolean isMapDisabled() {
		return false;
	}
	
	public boolean isTravelDisabled() {
		return travelDisabled;
	}

	public boolean isOptionsDisabled() {
		return false;
	}

	public boolean isInventoryDisabled() {
		return isTravelDisabled();
	}

	public DialogueNodeType getDialogueNodeType() {
		return DialogueNodeType.NORMAL;
	}

	public boolean isRegenerationDisabled() {
		return false;
	}
	
	public String getAuthor(){
		return "Innoxia";
	}
	
	public boolean disableHeaderParsing() {
		return false;
	}
	
	public boolean reloadOnRestore() {
		return false;
	}
}
