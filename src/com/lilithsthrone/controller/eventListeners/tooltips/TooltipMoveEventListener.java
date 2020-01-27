package com.lilithsthrone.controller.eventListeners.tooltips;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public class TooltipMoveEventListener implements EventListener {
	@Override
	public void handleEvent(Event event) {
//		boolean tooWide = false;
		double xPosition = ((MouseEvent) event).getScreenX() + 16;
		
		int tooltipWidth = (int) (Main.mainController.getTooltip().getWidth() + 8);
		
		if (xPosition + tooltipWidth +16 > Main.primaryStage.getX() + Main.primaryStage.getWidth()) {
			xPosition = ((MouseEvent) event).getScreenX() - tooltipWidth - 16;
//			tooWide = true;
		}
		
		Main.mainController.getTooltip().setAnchorX(xPosition);
		
		
		double yPosition = ((MouseEvent) event).getScreenY() + 16;
//		if(tooWide) {
//			if (yPosition + Main.mainController.getTooltip().getHeight() +16 > Main.primaryStage.getY() + Main.primaryStage.getHeight()) {
//				yPosition = ((MouseEvent) event).getScreenY() - Main.mainController.getTooltip().getHeight() - 16;
//			}
//		} else {
//			System.out.println(((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight());
			
			if(((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight() + 24 > 0) {
				yPosition -= (((((MouseEvent) event).getScreenY() - Main.primaryStage.getY()) + Main.mainController.getTooltip().getHeight()) - Main.primaryStage.getHeight() + 24);
			}
//		}
		
		Main.mainController.getTooltip().setAnchorY(yPosition);
		
	}
}