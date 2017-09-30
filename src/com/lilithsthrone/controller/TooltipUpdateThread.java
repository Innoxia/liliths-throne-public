package com.lilithsthrone.controller;

import com.lilithsthrone.main.Main;

import javafx.application.Platform;

/**
 * @since 0.1.3
 * @version 0.1.7
 * @author Innoxia
 */
public class TooltipUpdateThread extends Thread {

	public static boolean cancelThreads = false;

	public double x, y;
	
	public TooltipUpdateThread() {
		this(-1, -1);
	}
	
	public TooltipUpdateThread(double xPosition, double yPosition) {
		x = xPosition;
		y = yPosition;
	}

	public void run() {
		cancelThreads = false;

		try {
			sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!cancelThreads) {
					Main.mainController.getTooltip().show(Main.primaryStage);
					
					if(x!=-1){
						Main.mainController.getTooltip().setAnchorY(y);	
					}
				}
			}
		});

	}
}
