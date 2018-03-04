package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.rendering.RenderingEngine;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class GiftDialogue {
	
	private static GameCharacter receiver;
	private static DialogueNodeOld dialogueToReturnTo;
	private static DialogueNodeOld dialogueToProceedTo;
	
	public static DialogueNodeOld getGiftDialogue(GameCharacter receiver, DialogueNodeOld dialogueToReturnTo, DialogueNodeOld dialogueToProceedTo) {
		GiftDialogue.receiver = receiver;
		GiftDialogue.dialogueToReturnTo = dialogueToReturnTo;
		GiftDialogue.dialogueToProceedTo = dialogueToProceedTo;
		
		return GIFT_DIALOGUE;
	}
	
	public static final DialogueNodeOld GIFT_DIALOGUE = new DialogueNodeOld("Choose Gift", "-", true) {
		private static final long serialVersionUID = 1L;

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
				return new Response("Back", "Return to the previous screen.", dialogueToReturnTo);
				
			} else {
				return null;
			}
		}
	};

	public static GameCharacter getReceiver() {
		return receiver;
	}

	public static DialogueNodeOld getDialogueToProceedTo() {
		return dialogueToProceedTo;
	}
}
