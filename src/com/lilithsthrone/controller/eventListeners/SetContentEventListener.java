package com.lilithsthrone.controller.eventListeners;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.main.Main;

/**
 * Sets the MainController's content.
 * 
 * @since 0.1.0
 * @version 0.1.7
 * @author Innoxia
 */
public class SetContentEventListener implements EventListener {
	private int index;
	private boolean nextPage = false, previousPage = false;

	@Override
	public void handleEvent(Event event) {
		if (nextPage) {
			if (Main.game.isHasNextResponsePage()) {
				Main.game.setResponsePage(Main.game.getResponsePage() + 1);
				Main.game.updateResponses();
//				Main.game.setResponses(Main.game.getCurrentDialogueNode());
			}
		} else if (previousPage) {
			if (Main.game.getResponsePage() != 0) {
				Main.game.setResponsePage(Main.game.getResponsePage() - 1);
				Main.game.updateResponses();
//				Main.game.setResponses(Main.game.getCurrentDialogueNode());
			}
		} else {
			if (Main.game.getResponsePage() == 0)
				Main.game.setContent(Main.game.getResponsePage() * MainController.RESPONSE_COUNT + index);
			else {
				if (index != 0)
					Main.game.setContent(Main.game.getResponsePage() * MainController.RESPONSE_COUNT + index - 1);
				else
					Main.game.setContent(Main.game.getResponsePage() * MainController.RESPONSE_COUNT + (MainController.RESPONSE_COUNT-1));
			}

		}
	}

	public SetContentEventListener setIndex(int index) {
		this.index = index;

		nextPage = false;
		previousPage = false;
		return this;
	}

	public SetContentEventListener nextPage() {
		nextPage = true;
		previousPage = false;

		return this;
	}

	public SetContentEventListener previousPage() {
		nextPage = false;
		previousPage = true;

		return this;
	}
}