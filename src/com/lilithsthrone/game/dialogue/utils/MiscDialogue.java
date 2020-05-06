package com.lilithsthrone.game.dialogue.utils;

import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.62
 * @version 0.3.1
 * @author Innoxia
 */
public class MiscDialogue {
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	public static final DialogueNode STATUS_EFFECTS = new DialogueNode("Important status effect updates", "", true) {

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
						Main.game.restoreSavedContent(false);
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
	

	public static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Makeup", "", true) {
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
							?"You open the arcane makeup set and prepare to get started..."
							:"You open the arcane makeup set and prepare to get started on applying makeup to [npc.name]...")
					+ "</div>"
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Finished", "Return to your inventory screen.") {
					@Override
					public void effects() {
						if(BodyChanging.getTarget().isPlayer()) {
							Main.mainController.openInventory();
						} else {
							Main.mainController.openInventory((NPC) BodyChanging.getTarget(), InventoryInteraction.FULL_MANAGEMENT);
						}
					}
				};
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
