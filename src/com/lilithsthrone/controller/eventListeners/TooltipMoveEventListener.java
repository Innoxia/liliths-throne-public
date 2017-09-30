package com.lilithsthrone.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class TooltipMoveEventListener implements EventListener {
	@Override
	public void handleEvent(Event event) {
		boolean tooWide = false;
		double xPosition = ((MouseEvent) event).getScreenX() + 16;
		if (xPosition + 368 +16 > Main.primaryStage.getX() + Main.primaryStage.getWidth()) {
			xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 368 - 16;
			tooWide = true;
		}
		
		Main.mainController.getTooltip().setAnchorX(xPosition);
		
		
		double yPosition = ((MouseEvent) event).getScreenY() + 16;
		if(tooWide) {
			if (yPosition + Main.mainController.getTooltip().getHeight() +16 > Main.primaryStage.getY() + Main.primaryStage.getHeight())
				yPosition = ((MouseEvent) event).getScreenY() - Main.mainController.getTooltip().getHeight() - 16;
		} else {
//			System.out.println(((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight());
			
			if(((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight() +16 > 0)
				yPosition -= ((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight() +16;
		}
		
		Main.mainController.getTooltip().setAnchorY(yPosition);
	}
}