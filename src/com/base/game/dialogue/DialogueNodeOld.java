package com.base.game.dialogue;

import java.io.Serializable;

import com.base.game.dialogue.responses.Response;

/**
 * @since 0.1.0
 * @version 0.1.78
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

	public boolean isNoTextForContinuesDialogue() {
		return false;
	}

	public abstract Response getResponse(int index);

	public boolean isTravelDisabled() {
		return travelDisabled;
	}

	public boolean isOptionsDisabled() {
		return false;
	}

	public boolean isInventoryDisabled() {
		return isTravelDisabled();
	}

	public MapDisplay getMapDisplay() {
		return MapDisplay.NORMAL;
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
}
