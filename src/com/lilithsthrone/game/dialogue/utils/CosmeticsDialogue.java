package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.markings.Tattoo;
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
import com.lilithsthrone.rendering.SVGImages;
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
			
			} else if(index==2) {
				return new Response("Save/Load", "Save/Load tattoo presets.", TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						initTattooSaveLoadDialogue(BEAUTICIAN_TATTOOS_ADD);
					}
				};
			
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
	
	// Save/Load screen for tattoos:

	private static DialogueNode returnToNodeFromTattooSaveLoad;
	private static Map<String, Tattoo> loadedTattoosMap;
	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";

	public static void initTattooSaveLoadDialogue(DialogueNode returnToNodeFromTattooSaveLoad) {
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
		CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
		CosmeticsDialogue.returnToNodeFromTattooSaveLoad = returnToNodeFromTattooSaveLoad;
	}
	
	public static DialogueNode getReturnToNodeFromTattooSaveLoad() {
		return returnToNodeFromTattooSaveLoad;
	}

	public static void initSaveLoadMenu() {
		loadedTattoosMap = new TreeMap<>();
		
		for(File f : getSavedTattoos()) {
			try {
				String name = Util.getFileIdentifier(f);
				Tattoo loadedTattoo = loadTattoo(name);
				loadedTattoosMap.put(name, loadedTattoo);
			} catch(Exception ex) {
			}
		}
	}
	
	public static Map<String, Tattoo> getLoadedTattoosMap() {
		return loadedTattoosMap;
	}

	public static final DialogueNode TATTOO_SAVE_LOAD = new DialogueNode("Save tattoo files", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initSaveLoadMenu();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'>"
							+ "Only standard characters (letters and numbers) will work for save file names."
							+ "<br/>Hover over each tattoo's icon to see the full details of what will be saved/loaded."
							+(!Main.game.isInNewWorld()
								?"<br/>If a name is [style.colourBad(red)], then you cannot load that tattoo due to an incompatibility with your selected body area, or due to the tattoo having special effects which aren't available to you yet!"
								:"<br/>If a name is [style.colourBad(red)], then you cannot load that tattoo due to an incompatibility with your selected body area.")
					+ "</div>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>"
							+ "Name"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "Save | Load | Delete"
						+ "</div>"
					+ "</div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
			i++;
			
			for(Entry<String, Tattoo> entry : loadedTattoosMap.entrySet()){
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to load, overwrite, or delete a saved tattoo."
							+ " When turned on, it will take two clicks to apply any button press."
							+ " When turned off, it will only take one click.",
						TATTOO_SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Back to the tattoo menu.", returnToNodeFromTattooSaveLoad);
			}
			
			return null;
		}
	};
	
	public static List<File> getSavedTattoos() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/tattoos");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, Tattoo loadedTattoo, boolean altColour) {
		if(loadedTattoo!=null){
			String fileName = (baseName+".xml");
			
			boolean suitableSlot = loadedTattoo.getType().getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot);
			boolean specialEffectsLimitation = !Main.game.isInNewWorld()
					&& (loadedTattoo.getCounter()!=null || loadedTattoo.isGlowing() || (loadedTattoo.getWriting()!=null && loadedTattoo.getWriting().isGlow()));
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'>"
						
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
						
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ loadedTattoo.getSVGString()
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_TATTOO_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<h6 style='margin:0; padding:2px;'>"+(!suitableSlot || specialEffectsLimitation?"[style.boldBad("+loadedTattoo.getName()+")]":loadedTattoo.getName())+"</h6>"
								+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/tattoos/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
							+"</div>"
							
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted() && !Main.game.isInCombat() && !Main.game.isInSex()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (suitableSlot && !specialEffectsLimitation
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else { //TODO hmmmmmmm (also enchantment _CURRENT)
			String svgString = CharacterModificationUtils.tattoo.getSVGString();
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
					
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ svgString
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_TATTOO_CURRENT'></div>"//TODO
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
							+"</div>"
							
						+ "</div>"
					
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
						+ "</div>"
					+ "</div>";
				
		}
	}

	public static void saveTattoo(String name, boolean allowOverwrite, DialogueNode dialogueNode) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/tattoos");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name already exists!");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element tattooCoreElement = doc.createElement("tattooSave");
			
			doc.appendChild(tattooCoreElement);
			
			Element tattooElement = CharacterModificationUtils.tattoo.saveAsXML(tattooCoreElement, doc);
			
			// Do not save item effects:
			if(tattooElement.getElementsByTagName("effects").item(0)!=null) {
				tattooElement.removeChild(tattooElement.getElementsByTagName("effects").item(0));
			}
			
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/tattoos/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		if(dialogueNode!=null) {
			Main.game.setContent(new Response("", "", dialogueNode));
		}
		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Tattoo saved!");
	}

	public static Tattoo loadTattoo(String name) {
		if (isLoadTattooAvailable(name)) {
			File file = new File("data/tattoos/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = Main.getDocBuilder().parse(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					Element rootElement = (Element) doc.getElementsByTagName("tattooSave").item(0);
					
					return Tattoo.loadFromXML((Element) rootElement.getElementsByTagName("tattoo").item(0), doc);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadTattooAvailable(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteTattoo(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
		}
	}
	
}
