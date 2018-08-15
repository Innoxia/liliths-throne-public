package com.lilithsthrone.game.dialogue.utils;

import java.util.Map.Entry;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.62
 * @version 0.1.87
 * @author Innoxia
 */
public class MiscDialogue {
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	
	public static final DialogueNodeOld STATUS_EFFECTS = new DialogueNodeOld("Important status effect updates", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB.setLength(0);
			
			for(Entry<StatusEffect, String> e : Main.game.getPlayer().getStatusEffectDescriptions().entrySet()){
				descriptionSB.append("<p>"
										+"<h6 style='text-align:center;'>"+Util.capitaliseSentence(e.getKey().getName(Main.game.getPlayer()))+"</h6>"
										+ e.getValue()
									+"</p>");
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Continue", "Carry on with whatever you were doing."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent();
					}
				};
			} else {
				return null;
			}
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.STATUS_EFFECT_MESSAGE;
		}
	};
}
