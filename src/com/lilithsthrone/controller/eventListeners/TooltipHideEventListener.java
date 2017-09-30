package com.lilithsthrone.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.main.Main;

/**
 * Hides the tooltip.
 * 
 * @since 0.1.0
 * @version 0.1.3
 * @author Innoxia
 */
public class TooltipHideEventListener implements EventListener {
	@Override
	public void handleEvent(Event event) {
		TooltipUpdateThread.cancelThreads = true;
		Main.mainController.getTooltip().hide();
	}
}
