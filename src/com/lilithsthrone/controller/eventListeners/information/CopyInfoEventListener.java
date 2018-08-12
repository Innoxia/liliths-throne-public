package com.lilithsthrone.controller.eventListeners.information;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69.9
 * @version 0.1.69.9
 * @author Innoxia
 */
public class CopyInfoEventListener implements EventListener {

	@Override
	public void handleEvent(Event event) {
		Main.mainController.setTooltipSize(360, 170);
		
		Main.mainController.setTooltipContent(
				"<div class='subTitle'>"
				+(Main.game.getCurrentDialogueNode().getLabel() == "" || Main.game.getCurrentDialogueNode().getLabel() == null ? "-" : Main.game.getCurrentDialogueNode().getLabel())
				+ "</div>"
				+ "<div class='description'>"
				+ "Click to copy the currently displayed dialogue to your clipboard.<br/><br/>"
				+ "This scene was written by <b style='color:"
				+ Main.game.getCurrentDialogueNode().getAuthorColour().toWebHexString()+";'>"
				+ Main.game.getCurrentDialogueNode().getAuthor()
				+ "</b></div>");
		
		TooltipUpdateThread.updateToolTip(-1,-1);
	}
}