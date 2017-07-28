package com.base.game.dialogue.responses;

/**
 * @since 0.1.69
 * @version 0.1.69
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
