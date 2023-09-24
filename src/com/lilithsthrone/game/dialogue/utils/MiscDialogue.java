package com.lilithsthrone.game.dialogue.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.62
 * @version 0.3.7.7
 * @author Innoxia
 */
public class MiscDialogue {
	
	public static final DialogueNode STATUS_EFFECTS = new DialogueNode("Important status effect updates", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			for(Entry<Long, Map<AbstractStatusEffect, String>> entry : Main.game.getPlayer().getStatusEffectDescriptions().entrySet()){
				if(!entry.getValue().isEmpty()) {
					sb.append("<div class='container-full-width'>");
						sb.append("<h6 style='text-align:center; margin:16px auto 0 auto; padding:0;'>"+Units.dateTime(Main.game.getStartingDate().plusSeconds(entry.getKey()))+":</h6>");
						for(Entry<AbstractStatusEffect, String> innerEntry : entry.getValue().entrySet()) {
							sb.append("<hr/>");
							sb.append("<h6 style='text-align:center; margin:0; padding:0;'>");
								sb.append(Util.capitaliseSentence(innerEntry.getKey()==null?"Miscellaneous Effects":innerEntry.getKey().getName(Main.game.getPlayer())));
							sb.append("</h6>");
							sb.append("<p style='margin-top:0;'>");
								sb.append(UtilText.parse(Main.game.getPlayer(), innerEntry.getValue()));
							sb.append("</p>");
						}
					sb.append("</div>");
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Continue", "Carry on with whatever you were doing."){
					@Override
					public void effects() {
						Main.game.getPlayer().getStatusEffectDescriptions().clear();
						Main.game.restoreSavedContent(false);
					}
				};
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.STATUS_EFFECT_MESSAGE;
		}
	};

	private static boolean withHairStyle;
	private static String makeupOpeningDescription;

	public static DialogueNode getMakeupDialogueForEqualityCheck() {
		return BODY_CHANGING_MAKEUP;
	}
	
	public static DialogueNode getMakeupDialogue(boolean withHairStyle, String makeupOpeningDescription) {
		MiscDialogue.withHairStyle = withHairStyle;
		MiscDialogue.makeupOpeningDescription = makeupOpeningDescription;
		return BODY_CHANGING_MAKEUP;
	}
	
	private static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Makeup", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ makeupOpeningDescription
					+ "</div>"
					
					+ (withHairStyle
						?CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						:""));
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
				sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ UtilText.parse(BodyChanging.getTarget(),
								"<i>As [npc.namePos] body is made of "+BodyChanging.getTarget().getBodyMaterial().getName()+", [npc.sheIsFull] [style.colourBad(unable to wear any makeup)]!</i>")
					+ "</div>");
				
			} else {
				sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true));
			}
			
			return sb.toString();
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
	

	public static DialogueNode getDollCustomisationDialogue() {
		return BODY_CHANGING_DOLL_CUSTOMISATION;
	}
	
	private static final DialogueNode BODY_CHANGING_DOLL_CUSTOMISATION = new DialogueNode("D.E.C.K.", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"You plug the D.E.C.K.'s cable into port on the rear of your neck and prepare to customise yourself..."
								:"You plug the D.E.C.K.'s cable into the port on the rear of [npc.namePos] neck and prepare to customise [npc.herHim]...")
					+ "</div>");
			
			return sb.toString();
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
