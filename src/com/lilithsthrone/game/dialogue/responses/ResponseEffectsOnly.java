package com.lilithsthrone.game.dialogue.responses;

/**
 * A Response class that does not progress to a new DialogueNode.<br/><br/>
 * 
 * As a result of there being no defined DialogueNode, no time will be passed unless you override this Response's getSecondsPassed() method.
 * 
 * @since 0.1.69
 * @version 0.3.1
 * @author Innoxia
 */
public class ResponseEffectsOnly extends Response {

	public ResponseEffectsOnly(String title, String tooltipText) {
		super(title, tooltipText, null);
	}
	
	@Override
	public boolean disabledOnNullDialogue(){
		return false;
	}
}
