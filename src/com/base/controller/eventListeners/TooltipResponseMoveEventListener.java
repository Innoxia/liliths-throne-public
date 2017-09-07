package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.base.controller.MainController;
import com.base.main.Main;

/**
 * @since 0.1.61
 * @version 0.1.69
 * @author Innoxia
 */
public class TooltipResponseMoveEventListener implements EventListener {

	@Override
	public void handleEvent(Event event) {
		double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
		if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
			xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;

		Main.mainController.getTooltip().setAnchorX(xPosition);
		Main.mainController.getTooltip().setAnchorY(Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
				- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY()));
	}
}