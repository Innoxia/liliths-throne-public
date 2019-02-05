package com.lilithsthrone.game.dialogue;

import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public abstract class DialogueNode {
	
	private String label;
	private String description;
	
	private boolean travelDisabled;
	private boolean continuesDialogue;

	public DialogueNode(String label, String description, boolean travelDisabled) {
		this(label, description, travelDisabled, false);
	}

	public DialogueNode(String label, String description, boolean travelDisabled, boolean continuesDialogue) {
		this.label = label;
		this.description = description;
		
		this.travelDisabled = travelDisabled;
		this.continuesDialogue = continuesDialogue;
	}
	
	/**
	 * @return The number of minutes that pass when entering into this dialogue node.
	 */
	public int getSecondsPassed() {
		return 0;
	}

	/**
	 * Content that is set above the main content. It is not affected by fade-in effects.<br/>
	 * <b>Append to Main.game.getTextStartStringBuilder() instead of using this!</b>
	 */
//	@Deprecated
	public String getHeaderContent() {
		return null;
	}

	/** The main content for this DialogueNode. If enabled in the options, it is affected by fade-in effects. */
	public abstract String getContent();

	@Override
	public String toString() {
		return label + ":" + description.substring(0, description.length() <= 20 ? description.length() : 20);
	}
	
	/**
	 * @return The label for the button proceeding to this dialogue node.<br/>
	 * <b>Note:</b> In almost all instances, this is overridden by the Response class's getTitle() method.
	 */
	public String getLabel() {
		if(Main.game.isStarted() && (label==null || label.isEmpty())) {
			return Main.game.getPlayerCell().getPlace().getName();
		}
		return label;
	}

	public DialogueNode setLabel(String label) {
		this.label = label;
		return this;
	}
	
	/**
	 * @return The description for the action proceeding to this dialogue node, to be displayed in the action tooltip.<br/>
	 * <b>Note:</b> In almost all instances, this is overridden by the Response class's getTooltipText() method.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return True if this dialogue node should append to the current dialogue, instead of clearing the screen and displaying it as a new scene.
	 */
	public boolean isContinuesDialogue() {
		return continuesDialogue;
	}
	
	/**
	 * @return True if this dialogue should display the actions title as part of a continuation of the scene, in this format:<br/><br/>
	 * <i>...sunt in culpa qui officia deserunt mollit anim id est laborum.<br/>
	 * <hr/>
	 * > Accept<br/>
	 * Lorem ipsum dolor sit amet, consectetur adipiscing elit...</i>
	 */
	public boolean isDisplaysActionTitleOnContinuesDialogue() {
		return true;
	}
	
	/**
	 * Index starts at 0.
	 * @return The title to be displayed on the response tab. Only indices that are defined as returning a title are displayed, so just return null as the fallback option.
	 */
	public String getResponseTabTitle(int index) {
		return null;
	}
	
	/**
	 * The responses that lead on from this dialogue node.
	 * @param responseTab The tab in which the response is to be found.
	 * @param index The index of the response.
	 * @return A response, if a response is returned at the specified responseTab & index, or, if not, then null.
	 */
	public abstract Response getResponse(int responseTab, int index);

	/**
	 * @return True if this dialogue node disables map movement.
	 */
	public boolean isTravelDisabled() {
		return travelDisabled;
	}

	/**
	 * @return True if this dialogue node disables inventory management. By default, this is disabled if {@code isTravelDisabled()} returns true.
	 */
	public boolean isInventoryDisabled() {
		return isTravelDisabled();
	}

	/**
	 * @return The type of dialogue that this is. Will almost always be {@code DialogueNodeType.NORMAL}.
	 */
	public DialogueNodeType getDialogueNodeType() {
		return DialogueNodeType.NORMAL;
	}

	/**
	 * @return True if health and aura regeneration is disabled in this dialogue node.
	 */
	public boolean isRegenerationDisabled() {
		return false;
	}
	
	/**
	 * @return The author of the scene.
	 */
	public String getAuthor(){
		return "Innoxia";
	}

	/**
	 * @return True if the header should not be run through the parsing engine.
	 */
	public boolean disableHeaderParsing() {
		return false;
	}

	/**
	 * @return True if this dialogue node should be re-run through the parser when it is restored (i.e. loaded from a saved dialogue node).
	 */
	public boolean reloadOnRestore() {
		return false;
	}
}
