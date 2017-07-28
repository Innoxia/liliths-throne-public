package com.base.game.dialogue.utils;

import java.util.Map.Entry;

import com.base.game.character.effects.StatusEffect;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.main.Main;
import com.base.utils.Util;

/**
 * @since 0.1.62
 * @version 0.1.69
 * @author Innoxia
 */
public class MiscellaneousDialogue {
	
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
		public Response getResponse(int index) {
			if (index == 1) {
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.STATUS_EFFECT_MESSAGE;
		}
	};
}
