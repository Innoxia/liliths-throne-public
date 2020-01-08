package com.lilithsthrone.controller.eventListeners.buttons;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69.9
 * @version 0.1.69.9
 * @author Innoxia
 */
public class ButtonCopyDialogueEventListener implements EventListener {

	@Override
	public void handleEvent(Event event) {
		StringSelection selection = new StringSelection(Main.game.getContentForClipboard());
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	}
}