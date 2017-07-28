package com.base.controller.eventListeners.buttons;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.main.Main;

/**
 * @since 0.1.69.9
 * @version 0.1.69.9
 * @author Innoxia
 */
public class ButtonJournalEventListener implements EventListener {

	@Override
	public void handleEvent(Event event) {
		Main.mainController.openPhone();
	}
}