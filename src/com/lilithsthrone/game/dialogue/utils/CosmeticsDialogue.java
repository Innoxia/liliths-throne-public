package com.lilithsthrone.game.dialogue.utils;

import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Holds generic dialogue nodes associated with application of cosmetics, for use in external dialogue files.
 * This is effectively just a generic copy of SuccubisSecrets.java
 * 
 * @since 0.4.2.5
 * @version 0.4.2.5
 * @author Innoxia
 */
public class CosmeticsDialogue {

	public static InventorySlot invSlotTattooToRemove = null;
	
	private static NPC beautician;
	private static DialogueNode returnToNode;
	
	public static void initDialogue(NPC beautician, GameCharacter target, DialogueNode returnToNode) {
		CosmeticsDialogue.beautician = beautician;
		CosmeticsDialogue.returnToNode = returnToNode;
		BodyChanging.setTarget(target);
	}
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("Trade", UtilText.parse(beautician, "Ask [npc.name] what cosmetics or jewellery [npc.she] has for sale."), beautician);
			
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_COSMETICS) {
				return new Response("Makeup", "You are already looking at what cosmetics are available...", null);
				
			} else if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				return new Response("Makeup", UtilText.parse(beautician, "As your body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", [npc.name] is unable to apply any makeup!"), null);
				
			} else {
				return new Response("Makeup",
						UtilText.parse(beautician, "[npc.Name] can apply many different styles and colours of lipstick, nail polish, and other forms of makeup."),
						BEAUTICIAN_COSMETICS);
			}
			
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_HAIR) {
				return new Response("Hair", "You are already looking at what hair styles are available...", null);
				
			} else {
				return new Response("Hair",
					UtilText.parse(beautician, "[npc.Name] is able to dye, style, cut, and even lengthen your [pc.hair]."),
					BEAUTICIAN_HAIR);
			}

		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_PIERCINGS) {
				return new Response("Piercings", "You are already looking at what piercings are available...", null);
				
			} else {
				return new Response("Piercings",
						UtilText.parse(beautician, "[npc.Name] is able to pierce many different parts of your body."),
						BEAUTICIAN_PIERCINGS);
			}

		}  else if (index == 5) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_EYES) {
				return new Response("Eyes", "You are already looking at what changes to your eyes are available...", null);
				
			} else {
				return new Response("Eyes",
						UtilText.parse(beautician, "[npc.Name] is able to recolour your eyes, although it's quite demanding on [npc.her] aura, and is therefore very expensive."),
						BEAUTICIAN_EYES);
			}

		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_SKIN_COLOUR) {
				return new Response("Coverings", "You are already looking at what changes to your coverings are available...", null);
				
			} else {
				return new Response("Coverings",
						UtilText.parse(beautician, "[npc.Name] is able to recolour all types of body coverings, such as skin, fur, and feathers. This is quite demanding on [npc.her] aura, and is therefore very expensive."),
						BEAUTICIAN_SKIN_COLOUR){
					@Override
					public void effects() {
						SuccubisSecrets.initCoveringsMap(Main.game.getPlayer());
					}
				};
			}
			
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_OTHER) {
				return new Response("Miscellaneous", "You are already looking at what miscellaneous services are available...", null);
				
			} else {
				return new Response("Miscellaneous", UtilText.parse(beautician, "[npc.Name] offers other miscellaneous services, such as anal bleaching."), BEAUTICIAN_OTHER);
			}
			
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_TATTOOS) {
				return new Response("Tattoos", "You are already looking at what tattoos are available...", null);
				
			} else {
				return new Response("Tattoos",
						UtilText.parse(beautician, "[npc.Name] is able to both apply and remove tattoos. She's even able to apply arcane-enchanted tattoos, but they look to be very expensive..."),
						BEAUTICIAN_TATTOOS);
			}

		} else if (index == 0) {
			return new Response("Back", "You've had enough of changing your appearance for now.", returnToNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(0);
				}
			};
			
		} else {
			return null;
		}
	}
	
	private static String getMoneyRemainingString() {
		return "<h6 style='text-align:center;'>"
					+ "You currently have [style.moneyFormat([pc.money], span)]"
				+ "</h6>";
	}

	public static final DialogueNode BEAUTICIAN_START = new DialogueNode("Cosmetics", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	public static final DialogueNode BEAUTICIAN_COSMETICS = new DialogueNode("Cosmetics", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_HAIR = new DialogueNode("Hair", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles you're able to have. The longer the hair, the more styles are available.")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by your hair length.")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getHairType().getRace(), Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
						"[pc.Hair] Colour", "All hair recolourings are permanent, so if you want to change your colour again at a later time, you'll have to visit Kate again.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_SKIN_COLOUR = new DialogueNode("Coverings", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = Main.game.getPlayer();
				
				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
						true,
						true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_EYES = new DialogueNode("Eyes", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeType().getRace(), Main.game.getPlayer().getEyeCovering(),
							"Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"Sclerae", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_PIERCINGS = new DialogueNode("Piercings", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPiercings(false));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_OTHER = new DialogueNode("Miscellaneous", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivAnalBleaching()
					
					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "Facial hair", "The body hair found on your face." 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Feminine characters cannot grow facial hair."))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "Pubic hair", "The body hair found in the genital area; located on and around your sex organs and crotch.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "Underarm hair", "The body hair found in your armpits.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "Ass hair", "The body hair found around your asshole.")
							:"")
					);
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "Body hair", "Your body hair.", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS = new DialogueNode("Tattoos", "-", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoos());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("Confirmations: ",
						"Toggle tattoo removal confirmations."
							+ " When turned on, it will take two clicks to remove tattoos."
							+ " When turned off, it will only take one click.",
							BEAUTICIAN_TATTOOS) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS_ADD = new DialogueNode("Tattoos", "-", true) {
		@Override
		public String getLabel() {
			return "Tattoos: "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName());
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoosAdd());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You don't have enough money to get a tattoo!", null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You need to select a tattoo type, add some writing, or add a counter in order to make a tattoo!", null);
					
				} else {
					return new Response("Apply ("+UtilText.formatAsMoney(value, "span")+")", UtilText.parse(beautician, "Tell [npc.name] that you'd like [npc.herHim] to give you this tattoo."), BEAUTICIAN_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==0) {
				return new Response("Back", "Decide not to get this tattoo and return to the main selection screen.", BEAUTICIAN_TATTOOS);
			}
			
			return null;
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
}
