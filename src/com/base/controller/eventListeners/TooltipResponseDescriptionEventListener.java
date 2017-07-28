package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.base.controller.MainController;
import com.base.controller.TooltipUpdateThread;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class TooltipResponseDescriptionEventListener implements EventListener {
	private int index;
	private boolean nextPage = false, previousPage = false;
	
	private static StringBuilder tooltipSB;
	static {
		tooltipSB = new StringBuilder();
	}

	@Override
	public void handleEvent(Event event) {

		Main.mainController.setTooltipContent("");

		if (nextPage) {
			if (Main.game.isHasNextResponsePage()) {

				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>Next Page</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				(new Thread(new TooltipUpdateThread(xPosition, yPosition))).start();
			}
		} else if (previousPage) {
			if (Main.game.getResponsePage() != 0) {
				
				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>Previous Page</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				(new Thread(new TooltipUpdateThread(xPosition, yPosition))).start();
			}
			
		} else {

			Response response = null;
			if(Main.game.getCurrentDialogueNode()!=null) {
				if (Main.game.getResponsePage() == 0) {
					response = Main.game.getCurrentDialogueNode().getResponse(index);
				} else {
					if (index != 0) {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponsePage() * MainController.RESPONSE_COUNT + index - 1);
					} else {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponsePage() * MainController.RESPONSE_COUNT + MainController.RESPONSE_COUNT-1);
					}
				}
			}
			
			if (response != null) {
				tooltipSB.setLength(0);
				
				int boxHeight = 130;
				
				if(!response.hasRequirements()) {
					
					if(response.isSexHighlight()) {
						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span></div>");
						boxHeight+=44;
					} else if(response.isCombatHighlight()) {
						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span></div>");
						boxHeight+=44;
					}
					
					tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
				
				} else {
					
					if(response.isAvailable()) {
						if(response.isSexHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span>)</div>");
						else if(response.isCombatHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span>)</div>");
						else
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span></div>");
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex Pace:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.isAbleToBypass()) {
						if(response.isSexHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span>)</div>");
						else if(response.isCombatHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span>)</div>");
						else
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span></div>");
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex Pace:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else {
						if(response.isSexHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span>)</div>");
						else if(response.isCombatHighlight())
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span>)</div>");
						else
							tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span></div>");
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex Pace:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>" + response.getTooltipText() + "</span></div>");
					}
					
					tooltipSB.append(
							"<div class='description' style='height:"+((response.lineHeight()+1)*18)+"; text-align:center;'>"
									+ "<b>Availability:</b>"
									+response.getTooltipBlockingList()+response.getTooltipRequiredList()
							+"</div>");
					
					tooltipSB.append(
							"<div class='description-small'>"
									+response.getTooltipCorruptionBypassText()
							+"</div>");
					boxHeight+=54;
					
					boxHeight+= 24 + ((response.lineHeight()+1)*18);
				}
				
				Main.mainController.setTooltipSize(360, boxHeight);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - boxHeight
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				(new Thread(new TooltipUpdateThread(xPosition, yPosition))).start();
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			}
			
		}
	}

	public TooltipResponseDescriptionEventListener setIndex(int index) {
		this.index = index;

		nextPage = false;
		previousPage = false;
		return this;
	}

	public TooltipResponseDescriptionEventListener nextPage() {
		nextPage = true;
		previousPage = false;

		return this;
	}

	public TooltipResponseDescriptionEventListener previousPage() {
		nextPage = false;
		previousPage = true;

		return this;
	}

}