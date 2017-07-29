package com.base.controller.eventListeners.information;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.controller.TooltipUpdateThread;
import com.base.main.Main;
import com.base.utils.Colour;

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
				+ "Click to copy the currently displayed dialogue to your clipboard.</br></br>"
				+ "This scene was written by <b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>"
				+ Main.game.getCurrentDialogueNode().getAuthor()
				+ "</b></div>");
		
		(new Thread(new TooltipUpdateThread())).start();
	}
}