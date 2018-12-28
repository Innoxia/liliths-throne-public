package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class GiftDialogue {
	
	private static GameCharacter receiver;
	private static DialogueNode dialogueToReturnTo;
	private static int returnDialogueTab;
	private static DialogueNode dialogueToProceedTo;
	private static int proceedDialogueTab;
	
	/**
	 * @param receiver The NPC to receive the gift.
	 * @param dialogueToReturnTo The DialogueNode that should be returned if the player backs out of the gift menu.
	 * @param returnDialogueTab The tab which should be selected when returning to dialogueToReturnTo.
	 * @param dialogueToProceedTo The DialogueNode that should be returned if the player gives a gift to the receiver.
	 * @param proceedDialogueTab The tab which should be selected when proceeding to dialogueToProceedTo.
	 * @return
	 */
	public static DialogueNode getGiftDialogue(GameCharacter receiver, DialogueNode dialogueToReturnTo, int returnDialogueTab, DialogueNode dialogueToProceedTo, int proceedDialogueTab) {
		GiftDialogue.receiver = receiver;
		GiftDialogue.dialogueToReturnTo = dialogueToReturnTo;
		GiftDialogue.returnDialogueTab = returnDialogueTab;
		GiftDialogue.dialogueToProceedTo = dialogueToProceedTo;
		GiftDialogue.proceedDialogueTab = proceedDialogueTab;
		
		return GIFT_DIALOGUE;
	}
	
	public static final DialogueNode GIFT_DIALOGUE = new DialogueNode("Choose Gift", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parse(receiver,
					"<p>"
						+ "The following items are suitable to be given as a gift to [npc.name]. Select the one that you'd like to give to [npc.herHim]."
					+ "</p>")
					+RenderingEngine.ENGINE.getGiftDiv(receiver);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueToReturnTo) {
					@Override
					public void effects() {
						Main.game.setResponseTab(returnDialogueTab);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static GameCharacter getReceiver() {
		return receiver;
	}

	public static DialogueNode getDialogueToProceedTo() {
		return dialogueToProceedTo;
	}

	public static int getProceedDialogueTab() {
		return proceedDialogueTab;
	}
}
