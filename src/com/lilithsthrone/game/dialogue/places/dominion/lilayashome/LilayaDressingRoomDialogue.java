package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.outfit.Outfit;
import com.lilithsthrone.game.inventory.outfit.OutfitSource;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.10.8
 * @version 0.4.10.9
 * @author Innoxia
 */
public class LilayaDressingRoomDialogue {
	
	public static String deleteConfirmationName = "";
	private static Map<String, Outfit> loadedOutfitsMap;
	private static Map<Outfit, Integer> loadedOutfitsAvailabilityFromTile;
	private static Outfit activeOutfit;
	private static String loadedFileName;
	private static boolean outfitFilesFullVisibility = false;
	private static boolean outfitObtainedViaPurchase = false;
	
	public static boolean newlyCreatedWeapon = false;

	private static String dollID;
	private static void initDressupDoll() {
		BasicDoll doll = new BasicDoll();
		try {
			dollID = Main.game.addNPC(doll, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doll.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.HUMAN, RaceStage.GREATER, true);
		doll.setBodyMaterial(BodyMaterial.SILICONE);
		doll.setTailType(TailType.DEMON_COMMON);
		doll.setWingType(WingType.DEMON_COMMON);
		doll.setHornType(HornType.STRAIGHT);
		doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		doll.setArmRows(3);
		
		doll.setPiercedEar(true);
		doll.setPiercedLip(true);
		doll.setPiercedNavel(true);
		doll.setPiercedNipples(true);
		doll.setPiercedNipplesCrotch(true);
		doll.setPiercedNose(true);
		doll.setPiercedPenis(true);
		doll.setPiercedTongue(true);
		doll.setPiercedVagina(true);
		
		doll.setName("Dress-up doll");
//		doll.setLocation(Main.game.getPlayer());
		Main.game.setActiveNPC(doll);
	}
	
	/**
	 * @return A GameCharacter which is used for utility methods, primarily for detecting whether equipped outfit clothing is incompatible with other clothing.
	 */
	public static GameCharacter getDoll() {
		if(dollID==null || !Main.game.isCharacterExisting(dollID)) {
			initDressupDoll();
		}
		try {
			return Main.game.getNPCById(dollID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final DialogueNode ROOM_DRESSING_ROOM = new DialogueNode("Dressing room", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(LilayaHomeGeneric.getBaseRoomDescription());
			
			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_DRESSING_ROOM.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_DRESSING_ROOM.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_DRESSING_ROOM.getRoomDescription(Main.game.getPlayerCell())
					+ "</p>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if(index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Manage room", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if(index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Manage people", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE)) {
					return new Response("Outfits",
							"Thanks to the power of Lyssieth's wardrobe, you can now create and edit outfits for both yourself and your slaves.",
							OUTFITS);
				} else {
					return new Response("Outfits",
							"You need to pay Lilaya to re-activate Lyssieth's wardrobe before you're able to use the outfit management feature of your dressing room...",
							null);
				}
				
			} else if(index==5) {
				boolean autoCleaning = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomAutoClean);
				return new Response("Auto-clean: "+(autoCleaning?"[style.colourGood(ON)]":"[style.colourBad(OFF)]"),
						"Toggle whether clothing stored in your dressing room will be automatically cleaned."
							+ "<br/><b>Note:</b> When checking this area for clothing to form completed outfits, dirty clothing [style.italicsBad(will not)] be accounted for."
							+ " So if you want to make use of the 'equip from floor' feature, then it's advised to leave this setting on.",
						ROOM_DRESSING_ROOM) {
					@Override
					public int getSecondsPassed() {
						return 0;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomAutoClean, !autoCleaning);
					}
				};
			}
			
			// Slaves cannot be assigned here, but commented out in case added later:
			
//			int indexPresentStart = 4;
//			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
//				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
//				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
//					return LilayaHomeGeneric.interactWithNPC(character);
//				} else {
//					return new Response(UtilText.parse(character, "[npc.Name]"), 
//							UtilText.parse(character, "Although this is [npc.namePos] room, [npc.sheIs] "
//									+(character.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)
//											?"relaxing in a slave lounge at the moment."
//											:"out at work at the moment.")), null);
//				}
//			}
			
			return null;
		}
	};
	

	public static void initOutfitsMenu() {
		if(Main.game.getCurrentDialogueNode()!=OUTFITS) {
			deleteConfirmationName = "";
		}
		loadedOutfitsMap = new TreeMap<>();
		
		for(File f : getSavedOutfits()) {
			try {
				String name = Util.getFileIdentifier(f);
				Outfit loadedOutfit = loadOutfit(name);
				if(loadedOutfit.getGameCreationID()==Main.game.getId() || outfitFilesFullVisibility) {
					loadedOutfitsMap.put(name, loadedOutfit);
				}
			} catch(Exception ex) {
			}
		}
		
		calculateOutfitAvailability();
	}
	
	public static final DialogueNode OUTFITS = new DialogueNode("Outfit Manager", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initOutfitsMenu();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
//			saveLoadSB.append(
//					"<p style='text-align:center;'>"
//						+ "<i>"
//							+ "From here you can create a new outfit or edit or delete an existing one."
//						+ "</i>"
//					+ "</p>");
					
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:70%; margin:0; text-align:center; background:transparent;'>"
							+ "Name & File"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "Available"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "Cost"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "Delete"
						+ "</div>"
					+ "</div>");

			if(loadedOutfitsMap.isEmpty()) {
				saveLoadSB.append(
						"<div class='container-full-width' style='padding:0; margin:0;'>"
								+ "<div class='container-full-width' style='width:calc(100% - 16px); text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
									+ "<i>You don't have any saved outfits yet!</i>"
								+ "</div>"
						+ "</div>");
				
			} else {
				int i=0;
				for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()){
					saveLoadSB.append(getOutfitListRow(entry.getKey(), entry.getValue(), i%2==0));
					i++;
				}
			}
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("New outfit", "Create a new outfit.", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						activeOutfit = new Outfit();
						loadedFileName = "";
					}
				};
				
			} else if(index==2) {
				return new Response("New (from equipped)",
						"Create a new outfit, starting with a template generated from the clothing and weapons which you currently have equipped."
							+ "<br/>[style.italicsMinorBad(Please note that any unique or legendary items you have equipped will be ignored, as outfits cannot replicate such powerful items.)]",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						activeOutfit = new Outfit();
						loadedFileName = "";
						
						for(InventorySlot slot : InventorySlot.allWeaponSlots) {
							AbstractWeapon weapon = Main.game.getPlayer().getWeaponInSlot(slot);
							if(weapon!=null && weapon.getRarity()!=Rarity.QUEST && weapon.getRarity()!=Rarity.LEGENDARY) {
								activeOutfit.addWeapon(slot, Main.game.getItemGen().generateWeapon(weapon));
							}
						}
						for(InventorySlot slot : InventorySlot.getClothingSlots()) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingInSlot(slot);
							if(clothing!=null && clothing.getRarity()!=Rarity.QUEST && clothing.getRarity()!=Rarity.LEGENDARY) {
								AbstractClothing clothingClone = Main.game.getItemGen().generateClothing(clothing);
								clothingClone.setDirty(null, false);
//								clothingClone.setUnlocked(true);
								activeOutfit.addClothing(slot, clothingClone);
							}
						}
					}
				};
				
				
			} else if(index == 11) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to delete a saved outfit."
							+ " When turned on, it will take two clicks to apply a deletion button press."
							+ " When turned off, it will only take one click.",
							OUTFITS) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if(index==12) {
				if(outfitFilesFullVisibility) {
					return new Response("Show: [style.colourExcellent(All)]",
							"You are currently viewing [style.colourExcellent(all)] outfits in your 'res/outfits' folder."
							+ " Outfits which were created in a different save file are marked with a '[style.boldExcellent(*)]'."
							+ "<br/><i>Activate this response to switch to 'local' mode, which will only show outfits that have been created within your current game.</i>",
							OUTFITS) {
						@Override
						public void effects() {
							outfitFilesFullVisibility = !outfitFilesFullVisibility;
						}
					};
					
				} else {
					return new Response("Show: [style.colourMinorGood(Local)]",
							"You are currently viewing only the outfits which have been created within your current game."
							+ "<br/><i>Activate this response to switch to 'all' mode, which will show every outfit in your 'res/outfits' folder.</i>",
							OUTFITS) {
						@Override
						public void effects() {
							outfitFilesFullVisibility = !outfitFilesFullVisibility;
						}
					};
				}
				
			} else if(index == 0) {
				return new Response("Back", "Exit the outfit management menu.", ROOM_DRESSING_ROOM);
			}
			return null;
		}
	};
	
	public static List<File> getSavedOutfits() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/outfits");
		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if(directoryListing!=null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getOutfitListRow(String baseName, Outfit loadedOutfit, boolean altColour) {
		String fileName = (baseName+".xml");
		
		int availabilityCount = getOutfitAvailabilityFromTile(loadedOutfit);
		int essenceCost = loadedOutfit.getEssenceCost();
		
		return "<div class='container-full-width"+(altColour?" light":"")+"' style='padding:0; margin:0 0 4px 0; position:relative;'>"
					
					+ "<div class='container-full-width"+(altColour?" light":"")+" hover-enabled' id='LOADED_OUTFIT_" + baseName + "' style='width:90%; margin:0;'>"
					
						+ "<div class='container-full-width' style='width:calc(10% - 8px); margin:0 0 0 8px; padding:0; background:transparent; position:relative; float:left;'>"
							+"<div class='inventoryImage' style='width:100%;'>"
								+ "<div class='inventoryImage-content'>"
									+ loadedOutfit.getIconSVG()
								+ "</div>"
								+ "<div class='overlay no-highlight' style='cursor:pointer;'></div>"
							+ "</div>"
						+ "</div>"
					
						+ "<div style='width:calc(67.7% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
							+ "<h6 style='margin:0; padding:2px;'>"
								+ (loadedOutfit.getGameCreationID()==Main.game.getId()?"":"[style.boldExcellent(*)]")
								+loadedOutfit.getName()
							+"</h6>"
							+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/outfits/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
						+"</div>"
						
						+ "<div style='width:calc(11.1% - 8px); padding:0; margin:0 0 0 8px; position:relative; text-align:center; float:left;'>"
							+ "<p style='margin:0; padding:2px;'>"+availabilityCount+"</p>"
						+"</div>"
						+ "<div style='width:calc(11.1% - 8px); padding:0; margin:0 0 0 8px; position:relative; text-align:center; float:left;'>"
							+ "<p style='margin:0; padding:2px;'>[style.moneyFormat("+loadedOutfit.getCost()+", span)]</p>"
							+ (essenceCost==0
								?UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)
								:UtilText.formatAsEssences(essenceCost, "b", false))
						+"</div>"
					+ "</div>"
					+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
						+ (fileName.equals(deleteConfirmationName)
							?"<div class='square-button saveIcon' style='width:75%; margin:12.5%;' id='DELETE_OUTFIT_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button saveIcon' style='width:75%; margin:12.5%;' id='DELETE_OUTFIT_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}

	public static void saveOutfit(String name, Outfit outfit, boolean allowOverwrite, DialogueNode dialogueNode) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/outfits");
		dir.mkdir();

		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if(directoryListing!=null) {
				for (File child : directoryListing) {
					if(child.getName().equals(name+".xml")){
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

			Element element = doc.createElement("exportedOutfit");
			doc.appendChild(element);
			
			outfit.saveAsXML(element, doc);
			
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
			
			String saveLocation = "data/outfits/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		if(dialogueNode!=null) {
			Main.game.setContent(new Response("", "", dialogueNode));
		}
		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Outfit saved!");
	}

	public static Outfit loadOutfit(String name) {
		if(isLoadOutfitAvailable(name)) {
			File file = new File("data/outfits/"+name+".xml");

			if(file.exists()) {
				try {
					Document doc = Main.getDocBuilder().parse(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					
					Element outerOutfitElement = (Element) doc.getElementsByTagName("exportedOutfit").item(0);
					Element outfitElement = (Element) outerOutfitElement.getElementsByTagName("outfit").item(0);
					
					loadedFileName = name;
					
					return Outfit.loadFromXML(outfitElement, doc);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadOutfitAvailable(String name) {
		File file = new File("data/outfits/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static String getOutfitSaveName() {
		return Main.checkFileName(activeOutfit.getName()).toLowerCase();
	}

	public static boolean isSaveOutfitAvailable(boolean allowOverwrite) {
		return isSaveOutfitAvailable(getOutfitSaveName(), allowOverwrite);
	}
	
	public static boolean isSaveOutfitAvailable(String saveName, boolean allowOverwrite) {
		if(saveName.isEmpty()) {
			return false;
		}

		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/outfits");
		dir.mkdir();

		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if(directoryListing!=null) {
				for (File child : directoryListing) {
					if(child.getName().equals(saveName+".xml")){
						if(!allowOverwrite) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static void deleteOutfit(String name) {
		File file = new File("data/outfits/"+name+".xml");

		if(file.exists()) {
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

	public static Outfit getActiveOutfit() {
		return activeOutfit;
	}
	
	public static void setActiveOutfit(Outfit outfit) {
		activeOutfit = outfit;
	}
	
	public static void setOutfitName(String name) {
//		if(isSaveOutfitAvailable(Main.checkFileName(name).toLowerCase(), true)) {
			activeOutfit.setName(name);
//		}
	}
	
	public static final DialogueNode OUTFIT_EDITOR = new DialogueNode("Outfit Editor", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getDoll().resetInventory(true);
			getDoll().loadOutfit(activeOutfit, OutfitSource.NOWHERE, OutfitSource.NOWHERE);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			sb.append("<div style='float:left; border-radius:5px; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; text-align:center; width:90%; margin:1% 5%; padding:8px;'>");
				sb.append("<i>");
					sb.append("Click on any slot to open the clothing/weapon selection screen.");
				sb.append("</i>");
			sb.append("</div>");
			
			sb.append(getInventoryEquippedPanel());
			
			sb.append("<div style='width:calc("+(100-inventoryUIWidth)+"% - 8px); padding:0; margin:0 0 0 8px; float:left;'>");
				sb.append("<h5 style='text-align:center;'>Outfit Details</h5>");
				
				// Name field:
				sb.append("<form style='text-align:center;float:left; width:80%; padding:0; margin:0 0 5% 5%;'>");
					sb.append("<input type='text' id='outfit_name' placeholder='Outfit name' value='"+activeOutfit.getName()+"' style='padding:0;margin:0;width:100%;'>");
				sb.append("</form>");
				sb.append("<div class='normal-button' id='apply_outfit_name' style='float:left; width:9.5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
					+ "&#10003;"
				+ "</div>");
				
				// Details:
				sb.append("<p style='margin-bottom:0; padding-bottom:0;'>");
					sb.append("Name:");
					sb.append("<br/><span style='font-size:1.1em;'>"+activeOutfit.getName()+"</span>");
					sb.append("<br/>");
					sb.append("<br/>");
					
					sb.append("Outfit cost:");
					sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsMoney(activeOutfit.getCost())+"</span>");
					int essenceCost = activeOutfit.getEssenceCost();
					if(essenceCost==0) {
						sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)+"</span>");
					} else {
						sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsEssences(essenceCost, "b", false)+"</span>");
					}
					sb.append("<br/>");
					sb.append("<br/>");
					
					sb.append("Outfits available from area:");
					sb.append("<br/><span style='font-size:1.1em;'>"+getOutfitAvailabilityFromTile(activeOutfit)+"</span>");
					sb.append("</p>");

			sb.append("</div>");

			
			sb.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			Outfit loadedOutfit = loadOutfit(loadedFileName);
			boolean outfitChanged = loadedFileName!=null
					&& !loadedFileName.isEmpty()
					&& activeOutfit!=null
					&& loadedOutfit!=null
					&& activeOutfit.hashCode()!=loadedOutfit.hashCode();
			
			if(index == 1) {
				if(isSaveOutfitAvailable(false)) {
					return new ResponseEffectsOnly("[style.colourMinorGood(Save)]", "Save this outfit as a new outfit file named '"+getOutfitSaveName()+"', which will allow you to wear it or assign it your slaves.") {
						@Override
						public void effects() {
							saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
							loadedFileName = getOutfitSaveName();
						}
					};
					
				} else if(isSaveOutfitAvailable(true)) {
					return new Response("Save", "A file with the name '"+getOutfitSaveName()+"' already exists, so you cannot save it as a new file...", null);
					
				} else {
					return new Response("Save", "You cannot save this outfit as it doesn't have a name!", null);
				}

			} else if(index==2) {
				if(loadedFileName==null || loadedFileName.isEmpty()) {
					return new Response("Overwrite", "This is a newly created outfit, so there's no file to overwrite...", null);
				}
				if(!outfitChanged) {
					return new Response("Overwrite", "You haven't modified your outfit, so there's no need to overwrite it at the moment...", null);
				}
				String fileNameForOverwrite = !isSaveOutfitAvailable(false)?getOutfitSaveName():loadedFileName;
				return new ResponseEffectsOnly("[style.colourMinorBad(Overwrite)]",
						"Replace your saved outfit which has the file name '"+fileNameForOverwrite+"' with this outfit."
							+ "<br/>[style.italicsBad(Your existing outfit with the name '"+fileNameForOverwrite+"' will be lost if you do this!)]"){
					@Override
					public void effects() {
						deleteOutfit(fileNameForOverwrite);
						saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
						loadedFileName = getOutfitSaveName();
					}
				};
				
//				if((loadedFileName!=null && !loadedFileName.isEmpty() && outfitChanged) || !isSaveOutfitAvailable(false)) {
//					String fileNameForOverwrite = !isSaveOutfitAvailable(false)?getOutfitSaveName():loadedFileName;
//					return new ResponseEffectsOnly("[style.colourMinorBad(Overwrite)]",
//							"Replace your saved outfit which has the file name '"+fileNameForOverwrite+"' with this outfit."
//								+ "<br/>[style.italicsBad(Your existing outfit with the name '"+fileNameForOverwrite+"' will be lost if you do this!)]"){
//						@Override
//						public void effects() {
//							deleteOutfit(fileNameForOverwrite);
//							saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
//							loadedFileName = getOutfitSaveName();
//						}
//					};
//					
//				} else if(loadedFileName!=null && !loadedFileName.isEmpty() && !outfitChanged) {
//					return new Response("Overwrite", "You haven't modified your outfit, so there's no need to overwrite it at the moment...", null);
//					
//				} else {
//					return new Response("Overwrite", "This is a newly created outfit, so there's no file to overwrite...", null);
//				}
				
			} else if(index == 4) {
				return new Response("Ignore all",
						"Set all currently empty slots to be 'ignored'."
							+ " This means that whenever this outfit is applied to a character, instead of removing whatever item the character currently has equipped in that slot, it will be ignored and left as-is.",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						for(InventorySlot slot : InventorySlot.values()) {
							if(activeOutfit.getClothing().get(slot)==null && activeOutfit.getWeapons().get(slot)==null) {
								activeOutfit.addIgnoredSlot(slot);
							}
						}
					}
				};
				
			} else if(index == 5) {
				return new Response("Clear ignores",
						"Set all currently ignored slots to be empty instead."
							+ " This means that whenever this outfit is applied to a character, all slots which are defined as empty will be cleared instead of being left as-is.",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						for(InventorySlot slot : InventorySlot.values()) {
							if(activeOutfit.getIgnoredSlots().contains(slot)) {
								if(slot.isWeapon()) {
									activeOutfit.addWeapon(slot, null);
								} else {
									activeOutfit.addClothing(slot, null);
								}
							}
						}
					}
				};
				
			} else if(index == 6) {
				if(Main.game.getPlayer().getMoney()<activeOutfit.getCost() || Main.game.getPlayer().getEssenceCount()<activeOutfit.getEssenceCost()) {
					return new Response("Buy ("+UtilText.formatAsMoneyUncoloured(activeOutfit.getCost(), "span")+")",
							"You can't afford to purchase this outfit."
							+ "<br/>"
								+(Main.game.getPlayer().getMoney()<activeOutfit.getCost()
									?"You [style.colourBad(don't have)] [style.moneyFormat("+activeOutfit.getCost()+", span)] (you only have [style.moneyFormat("+Main.game.getPlayer().getMoney()+", span)])."
									:"You [style.colourGood(have)] the required [style.moneyFormat("+activeOutfit.getCost()+", span)].")
							+ (activeOutfit.getEssenceCost()<=0
								?""
								:"<br/>"
									+(Main.game.getPlayer().getEssenceCount()<activeOutfit.getEssenceCost()
										?"You [style.colourBad(don't have)] [style.essenceFormat("+activeOutfit.getEssenceCost()+", span)] (you only have [style.essenceFormat("+Main.game.getPlayer().getEssenceCount()+", span)])."
										:"You [style.colourGood(have)] the required [style.essenceFormat("+activeOutfit.getEssenceCost()+", span)].")),
							null);
				} else {
					return new Response("Buy ("+UtilText.formatAsMoney(activeOutfit.getCost(), "span")+")",
							"Purchase this outfit, after which you can choose whether to equip it yourself or onto one of your slaves."
							+ "<br/>"
							+ "This will [style.colorBad(cost)] you [style.moneyFormat("+activeOutfit.getCost()+", span)]"
							+(activeOutfit.getEssenceCost()<=0
								?"."
								:" and [style.essenceFormat("+activeOutfit.getEssenceCost()+", span)].")
							+ "<br/>"
							+ "[style.colorGood(You have)] [style.moneyFormat("+Main.game.getPlayer().getMoney()+", span)]"
								+(activeOutfit.getEssenceCost()<=0
								?"."
								:" and [style.essenceFormat("+Main.game.getPlayer().getEssenceCount()+", span)]."),
							OUTFIT_PURCHASE) {
						@Override
						public void effects() {
							if(activeOutfit.getCost()==0) {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_NOTHING"));
							} else {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_PURCHASE"));
							}
							outfitObtainedViaPurchase = true;
							if(activeOutfit.getCost()>0) {
								Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(-activeOutfit.getCost()));
							}
							if(activeOutfit.getEssenceCost()>0) {
								Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementEssenceCount(-activeOutfit.getEssenceCost(), false));
							}
							
						}
					};
				}
				
			} else if(index == 7) {
				if(getOutfitAvailabilityFromTile(activeOutfit)<=0) {
					return new Response("Equip from area",
							"You don't have all of the necessary components for this outfit in this area, so if you want to equip this outfit then you'll have to purchase it first...",
							null);
				} else {
					return new Response("Equip from area",
							"As you have all of the necessary components for this outfit in this area, you can equip it onto yourself or onto one of your slaves without needing to purchase anything.",
							OUTFIT_PURCHASE) {
						@Override
						public void effects() {
							if(activeOutfit.getCost()==0) {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_NOTHING"));
							} else {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_OWNED"));
							}
							outfitObtainedViaPurchase = false;
						}
					};
				}
				
			} else if(index == 0) {
				boolean unsavedNewOutfit = isSaveOutfitAvailable(false) && activeOutfit.hashCode()!=new Outfit().hashCode();
				
				return new Response(
						outfitChanged || unsavedNewOutfit
							?"Cancel"
							:"Back",
						(outfitChanged || unsavedNewOutfit
							?"Cancel your changes and exit the outfit editor to return to the outfit selection screen."
								+(unsavedNewOutfit
										?"<br/>[style.italicsTerrible(As you haven't yet saved this outfit, all of the changes which you've made will be lost if you do this!)]"
										:"<br/>[style.italicsTerrible(The changes which you've made to this outfit will be lost if you do this!)]")
							:"Exit the outfit editor and return to the outfit selection screen."),
						OUTFITS) {
					@Override
					public Colour getHighlightColour() {
						if(outfitChanged || unsavedNewOutfit) {
							return PresetColour.GENERIC_TERRIBLE;
						}
						return super.getHighlightColour();
					}
				};
			}
			return null;
		}
	};
	
	private enum equipType {
		UNEQUIP("Unequipped", PresetColour.BASE_BLUE_LIGHT, true),
		EQUIP("Equipped", PresetColour.GENERIC_GOOD, false),
		FAILED("Failed to equip", PresetColour.GENERIC_BAD, true);
		private String name;
		private Colour colour;
		private boolean droppedOnFloor;
		private equipType(String name, Colour colour, boolean droppedOnFloor){
			this.name=name;
			this.colour=colour;
			this.droppedOnFloor = droppedOnFloor;
		}
	}
	private static String getOutfitEquipTextRow(equipType type, String name) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='margin:0; padding:0; width:20%; text-align:right; color:"+type.colour.toWebHexString()+"; float:left;'>");
			sb.append(type.name);
		sb.append("</div>");

		sb.append("<div style='margin:0; padding:0; width:60%; text-align:center; float:left;'>");
			sb.append(name);
		sb.append("</div>");
		
		sb.append("<div style='margin:0; padding:0; width:20%; text-align:left; float:left;'>");
			if(type.droppedOnFloor) {
				sb.append("([style.italics(Dropped on floor)])");
			}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String applyRowWrapper(String content, boolean alternateRow) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='background:"+RenderingEngine.getEntryBackgroundColour(alternateRow)+"; width:100%; margin:0;'>");
			sb.append(content);
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode OUTFIT_PURCHASE = new DialogueNode("Outfit Purchase", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "Decide against purchasing this outfit after all...", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						if(outfitObtainedViaPurchase) {
							Main.game.appendToTextEndStringBuilder("<p>");
								if(activeOutfit.getCost()>0) {
									Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(activeOutfit.getCost()));
								}
								if(activeOutfit.getEssenceCost()>0) {
									Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementEssenceCount(activeOutfit.getEssenceCost(), false));
								}
							Main.game.appendToTextEndStringBuilder("</p>");
						}
					}
				};
			}
			String helperText = "<br/>Any clothing or weapons unequipped when equipping this outfit will be placed in your Dressing Room.";
			if(index==1) {
				boolean sealedClothingEquipped = Main.game.getPlayer().isAnyEquippedClothingSealed();
				String additionalHelperText = "";//"<br/>[style.italicsMinorGood(Your slave currently has no sealed clothing equipped.)]";
				if(sealedClothingEquipped) {
					additionalHelperText = "<br/>[style.italicsMinorBad(*You currently have sealed clothing equipped, which might prevent you from equipping every item in this outfit.)]";
				}
				return new Response("You"+(sealedClothingEquipped?"[style.colourMinorBad(*)]":""),
						"Equip this outfit yourself."+helperText+additionalHelperText,
						OUTFIT_EDITOR) {
					@Override
					public void effects() {  //aaaaaaaaaaaaaaaaaaa kalm
						List<String> unequipsList = new ArrayList<>();
						List<String> equipsList = new ArrayList<>();
						List<String> failuresList = new ArrayList<>();

						Map<InventorySlot, AbstractWeapon> weaponsEquippedBeforeOutfitApplication = new HashMap<>();
						for(InventorySlot weaponSlot : InventorySlot.allWeaponSlots) {
							AbstractWeapon weapon = Main.game.getPlayer().getWeaponInSlot(weaponSlot);
							if(weapon!=null) {
								weaponsEquippedBeforeOutfitApplication.put(weaponSlot, weapon);
							}
						}
						
						Map<InventorySlot, AbstractClothing> clothingEquippedBeforeOutfitApplication = new HashMap<>();
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							clothingEquippedBeforeOutfitApplication.put(c.getSlotEquippedTo(), c);
						}
						
						Map<InventorySlot, AbstractCoreItem> failureToEquipMap = Main.game.getPlayer().loadOutfit(activeOutfit, OutfitSource.CELL, outfitObtainedViaPurchase?OutfitSource.NOWHERE:OutfitSource.CELL);
						
						Main.game.appendToTextEndStringBuilder("<h3 style='text-align:center; margin-bottom:0;'>You equip the '"+activeOutfit.getName()+"' outfit:</h3>");
						
						for(Entry<InventorySlot, AbstractWeapon> weapons : activeOutfit.getWeapons().entrySet()) {
							if(!failureToEquipMap.containsKey(weapons.getKey())) {
								equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
							} else {
								failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
							}
						}
						for(Entry<InventorySlot, AbstractClothing> clothing : activeOutfit.getClothing().entrySet()) {
							if(!failureToEquipMap.containsKey(clothing.getKey())) {
								equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
							} else {
								failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
							}
						}
						
						for(Entry<InventorySlot, AbstractWeapon> weaponPreviouslyEquipped : weaponsEquippedBeforeOutfitApplication.entrySet()) {
							if(Main.game.getPlayer().getWeaponInSlot(weaponPreviouslyEquipped.getKey())!=weaponPreviouslyEquipped.getValue()) {
								unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(weaponPreviouslyEquipped.getValue().getName(false, true))));
							}
						}
						for(Entry<InventorySlot, AbstractClothing> clothingPreviouslyEquipped : clothingEquippedBeforeOutfitApplication.entrySet()) {
							if(Main.game.getPlayer().getClothingInSlot(clothingPreviouslyEquipped.getKey())!=clothingPreviouslyEquipped.getValue()) {
								unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(clothingPreviouslyEquipped.getValue().getName(false, true))));
							}
						}
						
						Main.game.appendToTextEndStringBuilder("<div class='container-full-width'>");
							int i = 0;
							for(String entry : unequipsList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
							for(String entry : equipsList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
							for(String entry : failuresList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
						Main.game.appendToTextEndStringBuilder("</div>");
						
						if(outfitObtainedViaPurchase) {
							for(Entry<InventorySlot, AbstractCoreItem> entry: failureToEquipMap.entrySet()) {
								if(entry.getValue() instanceof AbstractClothing) {
									Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing)entry.getValue());
								} else if(entry.getValue() instanceof AbstractWeapon) {
									Main.game.getPlayerCell().getInventory().addWeapon((AbstractWeapon)entry.getValue());
								}
							}
						}
						calculateOutfitAvailability();
					}
				};
			}
			List<Response> slaveResponses = new ArrayList<>();
			for(GameCharacter slave : Main.game.getPlayer().getSlavesOwnedAsCharacters()) {
				SlaveJob currentJob = slave.getSlaveJob(Main.game.getHourOfDay());
				if(currentJob.getFlags().contains(SlaveJobFlag.SPECIAL_UNIFORM)) {
					String jobName = currentJob.getName(slave);
					slaveResponses.add(new Response(
							UtilText.parse(slave, "[npc.Name]"),
							UtilText.parse(slave, "Your [npc.raceFull(true)] slave, [npc.name], is currently working as "+UtilText.addDeterminer(jobName)+"."
									+ " Due to this job's special uniform requirements, [npc.sheIs] unable to equip outfits at the moment."),
							null));
					
				} else {
					boolean sealedClothingEquipped = slave.isAnyEquippedClothingSealed();
					String additionalHelperText = "";//"<br/>[style.italicsMinorGood(Your slave currently has no sealed clothing equipped.)]";
					if(sealedClothingEquipped) {
						additionalHelperText = "<br/>[style.italicsMinorBad(*[npc.Name] currently has sealed clothing equipped, which might prevent [npc.herHim] from equipping every item in this outfit.)]";
					}
					slaveResponses.add(new Response(
							UtilText.parse(slave, "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"+(sealedClothingEquipped?"[style.colourMinorBad(*)]":"")),
							UtilText.parse(slave, "Equip this outfit onto your [npc.raceFull(true)] slave, [npc.name]."+helperText+additionalHelperText),
							OUTFIT_EDITOR) {
						@Override
						public void effects() {
							List<String> unequipsList = new ArrayList<>();
							List<String> equipsList = new ArrayList<>();
							List<String> failuresList = new ArrayList<>();
							Map<InventorySlot, AbstractWeapon> weaponsEquippedBeforeOutfitApplication = new HashMap<>();
							for(InventorySlot weaponSlot : InventorySlot.allWeaponSlots) {
								AbstractWeapon weapon = slave.getWeaponInSlot(weaponSlot);
								if(weapon!=null) {
									weaponsEquippedBeforeOutfitApplication.put(weaponSlot, weapon);
								}
							}
							
							Map<InventorySlot, AbstractClothing> clothingEquippedBeforeOutfitApplication = new HashMap<>();
							for(AbstractClothing c : slave.getClothingCurrentlyEquipped()) {
								clothingEquippedBeforeOutfitApplication.put(c.getSlotEquippedTo(), c);
							}
							
							Cell c = slave.getCell();
							slave.setLocation(Main.game.getPlayer());
							Map<InventorySlot, AbstractCoreItem> failureToEquipMap = slave.loadOutfit(activeOutfit, OutfitSource.CELL, outfitObtainedViaPurchase?OutfitSource.NOWHERE:OutfitSource.CELL);
							slave.setLocation(c);
							
							Main.game.appendToTextEndStringBuilder(UtilText.parse(slave,
									"<h3 style='text-align:center; margin-bottom:0;'>Your slave, [npc.name], equips the '"+activeOutfit.getName()+"' outfit:</h3>"));
							
							for(Entry<InventorySlot, AbstractWeapon> weapons : activeOutfit.getWeapons().entrySet()) {
								if(!failureToEquipMap.containsKey(weapons.getKey())) {
									equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
								} else {
									failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
								}
							}
							for(Entry<InventorySlot, AbstractClothing> clothing : activeOutfit.getClothing().entrySet()) {
								if(!failureToEquipMap.containsKey(clothing.getKey())) {
									equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
								} else {
									failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
								}
							}
							
							for(Entry<InventorySlot, AbstractWeapon> weaponPreviouslyEquipped : weaponsEquippedBeforeOutfitApplication.entrySet()) {
								if(slave.getWeaponInSlot(weaponPreviouslyEquipped.getKey())!=weaponPreviouslyEquipped.getValue()) {
									unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(weaponPreviouslyEquipped.getValue().getName(false, true))));
								}
							}
							for(Entry<InventorySlot, AbstractClothing> clothingPreviouslyEquipped : clothingEquippedBeforeOutfitApplication.entrySet()) {
								if(slave.getClothingInSlot(clothingPreviouslyEquipped.getKey())!=clothingPreviouslyEquipped.getValue()) {
									unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(clothingPreviouslyEquipped.getValue().getName(false, true))));
								}
							}
							
							Main.game.appendToTextEndStringBuilder("<div class='container-full-width'>");
								int i = 0;
								for(String entry : unequipsList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
								for(String entry : equipsList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
								for(String entry : failuresList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
							Main.game.appendToTextEndStringBuilder("</div>");

							if(outfitObtainedViaPurchase) {
								for(Entry<InventorySlot, AbstractCoreItem> entry: failureToEquipMap.entrySet()) {
									if(entry.getValue() instanceof AbstractClothing) {
										Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing)entry.getValue());
									} else if(entry.getValue() instanceof AbstractWeapon) {
										Main.game.getPlayerCell().getInventory().addWeapon((AbstractWeapon)entry.getValue());
									}
								}
							}
							calculateOutfitAvailability();
						}
					});
				}
			}
			for(int i=0; i< slaveResponses.size(); i++) {
				if(index-2==i) {
					return slaveResponses.get(i);
				}
			}
			
			return null;
		}
	};
	
	private static float inventoryUIWidth = 60f;
	private static float mainClothingPanelWidth = 69f; // nice
	
	private static String getInventoryEquippedPanel() {
		StringBuilder sb = new StringBuilder();

		// For more than 1 arm row rendering:
		String weaponStyle = "width:31%; margin:1%;";
		String piercingStyle = "width:40%; margin:5% 5%;";
		
		float piercingTopOffset = 7.5f;
		
		sb.append("<div class='container-full-width' style='width:"+(inventoryUIWidth-5)+"%; padding:0; margin:0 0 0 5%; float:left;'>");
		
		// EQUIPPED:
		sb.append("<div class='container-full-width' style='width:"+mainClothingPanelWidth+"%; padding:0; margin:0;'>");
			for(InventorySlot invSlot : RenderingEngine.mainInventorySlots) {
				sb.append(getClothingSlotDiv(invSlot, activeOutfit.getClothing().get(invSlot), false));
			}
		sb.append("</div>");
		
		// Render weapons & piercings:
		sb.append("<div class='container-full-width' style='width:"+(100-mainClothingPanelWidth)+"%; padding:0; margin:0.5% 0 0 0;'>");
		
		AbstractWeapon mainWeapon1 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_1);
		AbstractWeapon mainWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_2);
		AbstractWeapon mainWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_3);
		
		AbstractWeapon offhandWeapon1 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_1);
		AbstractWeapon offhandWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_2);
		AbstractWeapon offhandWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_3);
		
		// Main weapon:
		if(mainWeapon1!=null) {
			sb.append(getWeaponDiv(mainWeapon1, InventorySlot.WEAPON_MAIN_1, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_1, weaponStyle));
		}
		
		// Weapon in second slot:
		if(mainWeapon2!=null) {
			sb.append(getWeaponDiv(mainWeapon2, InventorySlot.WEAPON_MAIN_2, weaponStyle));
			
		}  else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_2, weaponStyle));
		}

		// Weapon in third slot:
		if(mainWeapon3!=null) {
			sb.append(getWeaponDiv(mainWeapon3, InventorySlot.WEAPON_MAIN_3, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_3, weaponStyle));
		}
		
		// Offhand weapon:
		if(offhandWeapon1!=null) {
			sb.append(getWeaponDiv(offhandWeapon1, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
			
		} else if(mainWeapon1!=null && mainWeapon1.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
		}
		
		// Weapon in second slot:
		offhandWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_2);
		if(offhandWeapon2!=null) {
			sb.append(getWeaponDiv(offhandWeapon2, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
			
		} else if(mainWeapon2!=null && mainWeapon2.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
		}
		
		// Weapon in third slot:
		offhandWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_3);
		if(offhandWeapon3!=null) {
			sb.append(getWeaponDiv(offhandWeapon3, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
			
		} else if(mainWeapon3!=null && mainWeapon3.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
		}
		

		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:"+piercingTopOffset+"% 0 0 0;'>");
			//piercingSlots
			for (InventorySlot invSlot : RenderingEngine.piercingSlots) {
				AbstractClothing clothing = activeOutfit.getClothing().get(invSlot);
				
				if(clothing!=null) {
					// add to content:
					sb.append(
							"<div class='inventory-item-slot " + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed()
										? "style='"+piercingStyle+" border-width:2px; border-color:" + PresetColour.SEALED.toWebHexString() + "; border-style:solid;'"
										: "style='"+piercingStyle+"'")
								+ ">");
						sb.append("<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>");
						sb.append("<div class='item-price'>"
								+ UtilText.formatAsItemPrice(clothing.getValue())
							+ "</div>");
						sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
						sb.append(getItemDeleteButton(invSlot, 14));
					sb.append("</div>");
					
	
				} else {
					sb.append("<div class='inventory-item-slot' style='"+piercingStyle+"'>");

						if(activeOutfit.getIgnoredSlots().contains(invSlot)) {
							sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
						}
						sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
					sb.append("</div>");
				}
			}
		sb.append("</div>");
		
		sb.append("</div>");
		
		
		// Render final row of clothing:
		
		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
		
		for (InventorySlot invSlot : RenderingEngine.secondaryInventorySlots) {
			sb.append(getClothingSlotDiv(invSlot, activeOutfit.getClothing().get(invSlot), true));
		}
		
		// Filler for final icon:
//		equippedPanelSB.append("<div class='inventory-item-slot secondary "+getClassRarityIdentifier(Rarity.COMMON)+"'>");
//			equippedPanelSB.append("<div class='inventory-icon-content'>"
//										+SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchTattoo()
//									+"</div>"
//									+ "<div class='overlay' id='TATTOO_SWITCH_LEFT'></div>");
//		equippedPanelSB.append("</div>");

		sb.append("</div>");
		
		sb.append("</div>");
		
		return sb.toString();
	}

	/**
	 * @return true if this InventorySlot is blocked by other clothing in this outfit.
	 */
	public static boolean isSlotDisabled(InventorySlot invSlot) {
		String blockText = getSlotDisabledText(invSlot);
		return blockText!=null && !blockText.isEmpty();
	}
	
	public static String getSlotDisabledText(InventorySlot invSlot) {
		List<String> clothingBlockingThisSlot = new ArrayList<>();
		for(AbstractClothing c : getDoll().getClothingCurrentlyEquipped()) {
			if(c.getIncompatibleSlots(getDoll(), c.getSlotEquippedTo()).contains(invSlot)) {
				clothingBlockingThisSlot.add(c.getName());
			}
		}
		
//		BodyPartClothingBlock block = invSlot.getBodyPartClothingBlock(getDoll());
		
		if(!clothingBlockingThisSlot.isEmpty()) {
			return "This slot is currently <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>blocked</b> by "+ Util.stringsToStringList(clothingBlockingThisSlot, false) + ".";
		}
//		else if(block != null) {
//			return UtilText.parse(getDoll(), block.getDescription());
//		}
		
		return "";
	}
	
	private static String getClothingSlotDiv(InventorySlot invSlot, AbstractClothing clothing, boolean isSecondary) {
		StringBuilder sb = new StringBuilder();
		
		String className = "inventory-item-slot";
		String styleModifier= "width:23%; margin:1%; padding:0;";
		if(isSecondary) {
			className = "inventory-item-slot secondary";
			styleModifier = "width:"+((mainClothingPanelWidth/4)-1.5f)+"%; margin:0.75%; padding:0;";
		}

		if(clothing!=null) {
			int essenceCost = getClothingEssenceCost(clothing);
			sb.append("<div class='"+className+getClassRarityIdentifier(clothing.getRarity())+"'"
					+(clothing.isSealed()
						?" style='border-width:2px; border-color:"+PresetColour.SEALED.toWebHexString()+"; border-style:solid;"+styleModifier+"'"
						:" style='"+styleModifier+"'")
					+">");
				sb.append("<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>");

				if(essenceCost>0) {
					sb.append("<div class='item-price' style='bottom:14px;'>"
								+ (essenceCost==0
									?UtilText.formatAsEssencesUncoloured(getClothingEssenceCost(clothing), "b", false)
									:UtilText.formatAsEssences(getClothingEssenceCost(clothing), "b", false))
							+ "</div>");
				}
				
				sb.append("<div class='item-price'>"
						+ UtilText.formatAsItemPrice(clothing.getValue())
					+ "</div>");
				sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
				sb.append(getItemDeleteButton(invSlot));
			sb.append("</div>");
			
		} else {
			boolean disabled = isSlotDisabled(invSlot);
			sb.append("<div class='"+className+(disabled?" disabled":"")+"' style='"+styleModifier+"'>");
				if(activeOutfit.getIgnoredSlots().contains(invSlot) && !disabled) {
					sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
				}
				sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "' style='"+(disabled?"cursor:default;":"")+"'>" + "</div>");
			sb.append("</div>");
		}
		
		return sb.toString();
	}

	public static int getClothingEssenceCost(AbstractClothing clothing) {
		int essenceCost = 0;
		for(ItemEffect ie : clothing.getEffects()) {
			if(!getDefaultEffects(clothing).contains(ie)) {
				essenceCost += EnchantingUtils.getModifierEffectCost(true, clothing, ie);
			}
		}
		return essenceCost;
	}
	
	private static String getEmptyWeaponDiv(boolean disabled, InventorySlot slot, String weaponStyle) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='inventory-item-slot"+(disabled?" disabled":"")+"' "+(disabled?"id='outfit_select_slot_" + slot.toString() + "'":"")+" style='"+weaponStyle+"'>");
		
		if(!disabled) {
			if(activeOutfit.getIgnoredSlots().contains(slot)) {
				sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
			} else {
				sb.append("<div class='inventory-icon-content' style='width:75%; margin:12.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getFist()+"</div>");
			}
			sb.append("<div class='overlay' id='outfit_select_slot_" + slot.toString() + "'></div>");	
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	

	private static String getWeaponDiv(AbstractWeapon weapon, InventorySlot slot, String weaponStyle) {
		StringBuilder sb = new StringBuilder();
		String weaponCount = getThrownWeaponCountDiv(weapon.getWeaponType());
		int essenceCost = getWeaponEssenceCost(weapon);
		sb.append("<div class='inventory-item-slot" + getClassRarityIdentifier(weapon.getRarity()) + "' style='"+weaponStyle+"'>"
					+ "<div class='inventory-icon-content'>"+weapon.getSVGEquippedString(Main.game.getPlayer())+"</div>");
		if(essenceCost>0) {
			sb.append("<div class='item-price' style='bottom:14px;'>"
							+ (essenceCost==0
								?UtilText.formatAsEssencesUncoloured(getWeaponEssenceCost(weapon), "b", false)
								:UtilText.formatAsEssences(getWeaponEssenceCost(weapon), "b", false))
						+ "</div>");
		}
		sb.append("<div class='item-price'>"
						+ UtilText.formatAsItemPrice(weapon.getValue())
					+ "</div>"
					+ "<div class='overlay' id='outfit_select_slot_" + slot.toString() + "'>"+weaponCount+"</div>");
			sb.append(getItemDeleteButton(slot, 14));
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
	public static int getWeaponEssenceCost(AbstractWeapon weapon) {
		int essenceCost = 0;
		for(ItemEffect ie : weapon.getEffects()) {
			if(!getDefaultEffects(weapon).contains(ie)) {
				essenceCost += EnchantingUtils.getModifierEffectCost(true, weapon, ie);
			}
		}
		return essenceCost;
	}

	private static String getItemDeleteButton(InventorySlot slot) {
		return getItemDeleteButton(slot, 16);
	}
	
	private static String getItemDeleteButton(InventorySlot slot, int size) {
		StringBuilder sb = new StringBuilder();
		int sizeReduced = size/3;
		sb.append("<div class='normal-button' id='clear_slot_"+slot.toString()+"'"
						+ "style='position:absolute; right:-"+sizeReduced+"px; top:-"+sizeReduced+"px; text-align:center; font-size:"+size+"px; line-height:"+size+"px; width:"+(size+2)+"px;"
								+ "  padding:0; margin:0; color:"+PresetColour.GENERIC_BAD.toWebHexString()+"; border:1px solid "+PresetColour.BACKGROUND_DARK.toWebHexString()+";'>");
			sb.append("X");
//			sb.append(SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete());
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getThrownWeaponCountDiv(AbstractWeaponType weaponType) {
		int amount = 10;
		
		if(!weaponType.isOneShot()) {
			return "";
		}
		return "<div class='item-count' "+(amount==0?"style='opacity:0.5;'":"")+">+" + amount + "</div>";
	}

	private static String getClassRarityIdentifier(Rarity rarity) {
		return RenderingEngine.getClassRarityIdentifier(rarity);
	}
	
	// Item selection and modification:
	
	private static InventorySlot selectedSlot;
	private static AbstractWeapon weaponSelected;
	private static AbstractClothing clothingSelected;

	public static InventorySlot getSelectedSlot() {
		return selectedSlot;
	}
	public static void setSelectedSlot(InventorySlot selectedSlot) {
		LilayaDressingRoomDialogue.selectedSlot = selectedSlot;
	}
	
	public static boolean initItemFromSlot() {
		if(selectedSlot.isWeapon()) {
			AbstractWeapon weapon = activeOutfit.getWeapons().get(selectedSlot);
			if(weapon!=null) {
				weaponSelected = weapon;
				return true;
			}
			
		} else {
			AbstractClothing clothing = activeOutfit.getClothing().get(selectedSlot);
			if(clothing!=null) {
				clothingSelected = clothing;
				return true;
			}
		}
		return false;
	}
	
	public static AbstractWeapon getWeaponSelected() {
		return weaponSelected;
	}
	public static void setWeaponSelected(AbstractWeaponType weaponType) {
		LilayaDressingRoomDialogue.weaponSelected = Main.game.getItemGen().generateWeapon(weaponType);
	}

	public static AbstractClothing getClothingSelected() {
		return clothingSelected;
	}
	public static void setClothingSelected(AbstractClothingType clothingType) {
		LilayaDressingRoomDialogue.clothingSelected = Main.game.getItemGen().generateClothing(clothingType, false);
	}
	
	public static AbstractCoreItem getSelectedItem() {
		if(weaponSelected!=null) {
			return weaponSelected;
		}
		if(clothingSelected!=null) {
			return clothingSelected;
		}
		return null;
	}
	
	public static void clearSlot() {
		activeOutfit.clearSlot(selectedSlot);
	}

	public static void ignoreSlot() {
		activeOutfit.addIgnoredSlot(selectedSlot);
	}
	
	public static final DialogueNode OUTFIT_EDITOR_ITEM_CHOICE = new DialogueNode("Selection", "", true) {
		@Override
		public void applyPreParsingEffects() {
			weaponSelected = null;
			clothingSelected = null;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0 0 8px 0; text-align:center'>");
				sb.append("<h3 style='margin:0'>Selection [style.colourDisabled(-> Dye -> Enchantments)]</h3>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0 0 8px 0; text-align:center'>");
				sb.append("<p style='margin:0;'>");
					sb.append("<i>All "+(selectedSlot.isWeapon()?"weapons":"clothing items")+" which you've discovered are available to be selected. This takes into account your 'Shared Encyclopedia' content setting.</i>");
				sb.append("</p>");
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+Rarity.COMMON.getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
						+ "<div class='inventory-icon-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div>"
						+ "<div class='overlay' id='clear_slot' style='cursor:default;'></div>"
					+ "</div>");
				sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+Rarity.COMMON.getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
						+ "<div class='inventory-icon-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>"
						+ "<div class='overlay' id='ignore_slot' style='cursor:default;'></div>"
					+ "</div>");
			
				if(selectedSlot.isWeapon()) {
					for(AbstractWeaponType weaponType : PhoneDialogue.getWeaponsDiscoveredList()) {
						if(weaponType.getRarity()==Rarity.QUEST
								|| weaponType.getRarity()==Rarity.LEGENDARY
								|| weaponType.getItemTags().contains(ItemTag.REMOVE_FROM_DRESSING_ROOM_OUTFITS)) {
							continue;
						}
						boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType) || Main.game.isDebugMode();
						
						sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
								+ "<div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div>"
								+ (discovered
									?"<div class='item-price'>"
										+ UtilText.formatAsItemPrice(weaponType.getBaseValue())
									+ "</div>"
									:"")
								+ "<div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
							+ "</div>");
					}
					
				} else {
					for(AbstractClothingType clothingType : PhoneDialogue.getClothingDiscoveredList()) {
						if(clothingType.getRarity()==Rarity.QUEST
								|| clothingType.getRarity()==Rarity.LEGENDARY
								|| clothingType.getDefaultItemTags().contains(ItemTag.REMOVE_FROM_DRESSING_ROOM_OUTFITS)
								|| clothingType.getDefaultItemTags().contains(ItemTag.MILKING_EQUIPMENT)) {
							continue;
						}
						if(!clothingType.getEquipSlots().contains(selectedSlot)) {
							continue;
						}
						boolean discovered = Main.getProperties().isClothingDiscovered(clothingType) || Main.game.isDebugMode();
						
						// Warn player if this clothing is incompatible with any of the outfit's currently selected clothing
						List<AbstractClothing> incompatibleClothing = new ArrayList<>();
						for(InventorySlot slot : Main.game.getItemGen().generateClothing(clothingType, false).getIncompatibleSlots(getDoll(), selectedSlot)) {
							if(getDoll().getClothingInSlot(slot)!=null) {
								incompatibleClothing.add(getDoll().getClothingInSlot(slot));
							}
						}
						
						List<Colour> clothingColours = new ArrayList<>();
						for(ColourReplacement cr : clothingType.getColourReplacements()) {
							// Use a consistent seed so the colours are always the same:
							Util.random.setSeed(clothingType.getName().hashCode());
							Colour colour = cr.getRandomOfDefaultColours();
							clothingColours.add(colour);
						}
						
						sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>");
							sb.append("<div class='inventory-icon-content'>"+(discovered?clothingType.getSVGEquippedImage(Main.game.getPlayer(), selectedSlot, clothingColours, null, new ArrayList<>(), null):"")+"</div>");
								if(discovered) {
									if(!incompatibleClothing.isEmpty()) {
									sb.append("<div class='item-price' style='top:8px; font-size:1.4em;'>"
												+ "[style.boldBad(!)]"
											+ "</div>");
									}
									sb.append("<div class='item-price'>"
												+ UtilText.formatAsItemPrice(clothingType.getBaseValue())
											+ "</div>");
								}
							sb.append("<div class='overlay"+(discovered?"' id='"+clothingType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>");
						sb.append("</div>");
					}
				}

				Util.random.setSeed(System.nanoTime()); // Reset seed to be close to random
				
			sb.append("</div>");
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "Return to the outfit editor.", OUTFIT_EDITOR);
			}
			return null;
		}
	};

	public static final DialogueNode OUTFIT_EDITOR_ITEM_DYE = new DialogueNode("Dye", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<h3 style='text-align:center'>[style.colourDisabled(Selection ->)] Dye [style.colourDisabled(-> Enchantments)]</h3>");
			sb.append("</div>");

			if(clothingSelected!=null) {
				sb.append(getClothingDyeUI());
			} else {
				sb.append(getWeaponDyeUI());
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue to the enchantment screen.", OUTFIT_EDITOR_ITEM_ENCHANT) {
					@Override
					public void effects() {
						initEnchantDialogue();
					}
				};
				
			} else if(index == 0) {
				return new Response("Back",
						"Return to the item choice screen."
						+ "<br/>[style.italicsBad(You will lose all of your changes if you do this.)]",
						OUTFIT_EDITOR_ITEM_CHOICE);
			}
			return null;
		}
	};
	

	private static String getClothingDyeUI() {
		InventorySlot slotEquippedTo = getSelectedSlot();
		StringBuilder sb = new StringBuilder();
		
		sb.append(
//				"<div class='container-full-width'>"
//					+ "<div class='inventoryImage'>"
//						+ "<div class='inventoryImage-content'>"
//							+ clothingSelected.getSVGString()
//						+ "</div>"
//					+ "</div>"
//					+ "<h3 style='text-align:center;'><b>"+clothingSelected.getDisplayName(true)+"</b></h3>"
//					+ "<p>"
//						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new clothing will look, press the 'Dye' option at the bottom of the screen to apply your changes."
//					+ "</p>"
//				+ "</div>"
//					
//				+ "<br/>"
				
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothingSelected.getClothingType().getSVGImage(
									slotEquippedTo,
									clothingSelected.getColours(),
									clothingSelected.getPattern(),
									clothingSelected.getPatternColours(),
									clothingSelected.getStickers())
						+ "</div>"
					+ "</div>");
		
		sb.append("<h3 style='text-align:center;'><b>Dye & Preview</b></h3>");
		
		if(!clothingSelected.getClothingType().getStickers().isEmpty()) {
			StringBuilder stickerSB = new StringBuilder();
			boolean stickerFound = false;
			List<StickerCategory> orderedCategories = new ArrayList<>(clothingSelected.getClothingType().getStickers().keySet());
			Collections.sort(orderedCategories, (s1, s2)->s1.getPriority()-s2.getPriority());
			
			for(StickerCategory cat : orderedCategories) {
				stickerSB.append("<div class='container-quarter-width' style='width:calc(75% - 16px); margin:0 8px; padding:0;'>");
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding-top:6px; width:20%;'>");
						stickerSB.append(Util.capitaliseSentence(cat.getName())+":"); // Category name
					stickerSB.append("</div>");
					
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding:0; width:80%;'>");
						List<Sticker> orderedStickers = new ArrayList<>(clothingSelected.getClothingType().getStickers().get(cat));
						Collections.sort(orderedStickers, (s1, s2)->s1.getPriority()-s2.getPriority());
						for(Sticker sticker : orderedStickers) {
							String requirements = UtilText.parse(sticker.getUnavailabilityText()).trim();
							if(requirements.isEmpty() || sticker.isShowDisabledButton()) {
								boolean specialSticker = !sticker.getAvailabilityText().isEmpty() || !sticker.getTagsApplied().isEmpty() || !sticker.getTagsRemoved().isEmpty();
								stickerFound = true;
								String id = "ITEM_STICKER_"+cat.getId()+sticker.getId();
								if(!requirements.isEmpty()) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button disabled'>"
													+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
									
								} else if(clothingSelected.getStickers().get(cat.getId())==sticker.getId()) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button active'>"
													+ "<b style='color:" + sticker.getColourSelected().toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
								} else {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button'>"
													+ "<span style='color:"+sticker.getColourDisabled().toWebHexString()+";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</span>"
											+ "</div>");
								}
							}
						}
					stickerSB.append("</div>");
				stickerSB.append("</div>");
				
				if(stickerFound) {
					stickerFound = false;
					sb.append(stickerSB.toString());
					stickerSB = new StringBuilder();
				}
			}
		}
		
		List<Colour> clothingColours = clothingSelected.getColours();
		for(int i=0; i<clothingSelected.getClothingType().getColourReplacements().size(); i++) {
			ColourReplacement cr = clothingSelected.getClothingType().getColourReplacement(i);
			if(!cr.getAllColours().isEmpty()) {
				sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
							+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Colour"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(cannot be changed)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
//					if(!c.isDesaturated()) {
						sb.append("<div class='normal-button"+(clothingColours.size()>i && clothingColours.get(i)==c?" selected":"")+"' id='DYE_CLOTHING_"+i+"_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px; border-width:1px;"
												+(cr.getDefaultColours().contains(c)
													?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
													:"")
												+(clothingColours.size()>i && clothingColours.get(i)==c
													?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
													:"")+"'>"
										+ "<div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
//					}
				}
				sb.append("</div>");
			}
		}
		
		if(clothingSelected.getClothingType().isPatternAvailable()){
			sb.append(
					"<br/>"
					+ "<div class='container-full-width'>"
					+ "Pattern:<br/>");
	 
			for (Pattern pattern : Pattern.getAllPatterns()) {
				if (clothingSelected.getPattern().equals(pattern.getId())) {
					sb.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</b>"
							+ "</div>");
				} else {
					sb.append(
							"<div id='ITEM_PATTERN_"+pattern.getId()+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</span>"
							+ "</div>");
				}
			}
			sb.append("</div>");

			for(int i=0; i<clothingSelected.getClothingType().getPatternColourReplacements().size(); i++) {
				ColourReplacement cr = clothingSelected.getClothingType().getPatternColourReplacement(i);
				if(!cr.getAllColours().isEmpty() && Pattern.getPattern(clothingSelected.getPattern()).isRecolourAvailable(cr)) {
					sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
								+ "Pattern "+Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Colour:<br/>");
					
					for (Colour c : cr.getAllColours()) {
						sb.append("<div class='normal-button"+(clothingSelected.getPatternColours().size()>i && clothingSelected.getPatternColours().get(i)==c?" selected":"")+"' id='DYE_CLOTHING_PATTERN_"+i+"_"+c.getId()+"'"
										+ " style='width:auto; margin-right:4px;"+(clothingSelected.getPatternColours().size()>i && clothingSelected.getPatternColours().get(i)==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
							+ "</div>");
					}
					sb.append("</div>");
				}
			}
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getWeaponDyeUI() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(
//				"<div class='container-full-width'>"
//					+ "<div class='inventoryImage'>"
//						+ "<div class='inventoryImage-content'>"
//							+ weaponSelected.getSVGString()
//						+ "</div>"
//					+ "</div>"
//					+ "<h3 style='text-align:center;'><b>"+weaponSelected.getDisplayName(true)+"</b></h3>"
//					+ "<p>"
//						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new weapon will look, press the 'Dye' option at the bottom of the screen to apply your changes."
//					+ "</p>"
//				+ "</div>"
//				+ "<br/>"
				"<div class='container-full-width'>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(25% - 16px); float:right;'>"
						+ "<div class='inventoryImage' style='width:100%;'>"
							+ (weaponSelected.getWeaponType().isEquippedSVGImageDifferent()
								?"Unequipped"
								:"")
							+ "<div class='inventoryImage-content'>"
								+ weaponSelected.getSVGString()
							+ "</div>"
						+ "</div>"
						+(weaponSelected.getWeaponType().isEquippedSVGImageDifferent()
							?"<div class='inventoryImage' style='width:100%;'>"
								+ "Equipped"
									+ "<div class='inventoryImage-content'>"
										+ weaponSelected.getSVGEquippedString(Main.game.getPlayer())
									+ "</div>"
								+ "</div>"
							:"")
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>Dye & Preview</b></h3>");
		
		
		sb.append("<div class='container-quarter-width' style='text-align:center;width:calc(75% - 16px);'>"
				+ "<b>Damage type:</b><br/>");
		for(DamageType dt : weaponSelected.getWeaponType().getAvailableDamageTypes()) {
			sb.append("<div class='normal-button"+(weaponSelected.getDamageType()==dt?" selected":"")+"' id='DAMAGE_TYPE_"+dt.toString()+"'"
							+ "style='width:20%; margin:0 2.5%; color:"+(weaponSelected.getDamageType()==dt?dt.getColour().toWebHexString():dt.getColour().getShades(8)[0])+";'>"
						+ Util.capitaliseSentence(dt.getName())
					+ "</div>");
		}
		sb.append("</div>");

		boolean colourOptions = false;

		for(int i=0; i<weaponSelected.getWeaponType().getColourReplacements(false).size(); i++) {
			colourOptions = true;
			ColourReplacement cr = weaponSelected.getWeaponType().getColourReplacement(false, i);
			if(!cr.getAllColours().isEmpty()) {
				sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
						+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Colour"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(cannot be changed)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
					sb.append("<div class='normal-button"+(weaponSelected.getColours().size()>i && weaponSelected.getColours().get(i)==c?" selected":"")+"' id='DYE_WEAPON_"+i+"_"+c.getId()+"'"
										+ " style='width:auto; margin-right:4px; border-width:1px;"
											+(cr.getDefaultColours().contains(c)
												?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
												:"")
											+(weaponSelected.getColours().size()>i && weaponSelected.getColours().get(i)==c
												?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
												:"")
										+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				sb.append("</div>");
			}
		}
		
		if(!colourOptions) {
			sb.append("<div class='container-half-width' style='text-align:center;'>"
					+ "[style.colourDisabled(No dye options available...)]"
					+ "</div>");
		}

		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static void initEnchantDialogue() {
		if(newlyCreatedWeapon) {
			getSelectedItem().getEffects().clear();
			getSelectedItem().getEffects().addAll(getDefaultEffects(getSelectedItem()));
		}
		
		LilayaDressingRoomDialogue.effects.clear();
		LilayaDressingRoomDialogue.resetEnchantmentVariables();
		LilayaDressingRoomDialogue.initModifiers();
		LilayaDressingRoomDialogue.setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), effects));

//		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
//		if(defaultName) {
//			setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
//		} else {
//			if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
//				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
//				setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
//			}
//		}
	}
	
	public static final DialogueNode OUTFIT_EDITOR_ITEM_ENCHANT = new DialogueNode("Enchantments", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<h3 style='text-align:center'>[style.colourDisabled(Selection -> Dye ->)] Enchantments</h3>");
			sb.append("</div>");
			
			sb.append(getEnchantmentUI());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Confirm", "Use this item and return to the outfit editor.", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						if(clothingSelected!=null) {
							AbstractClothing craftedClothing = EnchantingUtils.craftClothing(clothingSelected, effects);
							activeOutfit.addClothing(selectedSlot, craftedClothing);

							// Remove incompatible clothing:
							for(InventorySlot slot : craftedClothing.getIncompatibleSlots(LilayaDressingRoomDialogue.getDoll(), LilayaDressingRoomDialogue.getSelectedSlot())) {
								activeOutfit.addClothing(slot, null);
							}
							
							clothingSelected = null;
						} else {
							AbstractWeapon craftedWeapon = EnchantingUtils.craftWeapon(weaponSelected, effects);
							activeOutfit.addWeapon(selectedSlot, craftedWeapon);
							weaponSelected = null;
						}
						
						calculateOutfitAvailability();
					}
				};
				
			//TODO add save/load with conditional based on if unlocked and slot compatible - index 2
				// (no save)
				
			} else if(index==4) {
				boolean defaultsMissing = false;
				for(ItemEffect ie : getDefaultEffects(getSelectedItem())) {
					if(!effects.contains(ie)) {
						defaultsMissing = true;
						break;
					}
				}
				String helperText = "<br/><i>Default enchantments have no essence cost when purchasing outfits.</i>";
				
				if(getDefaultEffects(getSelectedItem()).isEmpty()) {
					return new Response("Restore defaults", "This item has no default enchantments to restore."+helperText, null);
					
				} else if(!defaultsMissing) {
					return new Response("Restore defaults", "This item already has all of its default enchantments."+helperText, null);
					
				} else {
					return new ResponseEffectsOnly("Restore defaults", "Restore this item's default enchantments."+helperText) {
						@Override
						public void effects() {
							int i=0;
							for(ItemEffect ie : getDefaultEffects(getSelectedItem())) {
								effects.remove(ie);
								effects.add(i, ie);
								i++;
							}
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
				}
				
			} else if(index==5) {
				if(activeOutfit.getIconSlotPriority()!=selectedSlot) {
					return new ResponseEffectsOnly("Icon: [style.colourBad(OFF)]",
							(activeOutfit.getIconSlotPriority()==null
								?"This outfit is currently using the highest value item as its icon."
								:"This outfit is currently using the '<i>"+activeOutfit.getIconSlotPriority().getName()+"</i>' slot as its icon.")
							+"<br/>[style.italics(Activate to make this outfit use this slot (<i>"+selectedSlot.getName()+"</i>) for its default icon instead.)]") {
						@Override
						public void effects() {
							activeOutfit.setIconSlotPriority(selectedSlot);
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Icon: [style.colourGood(ON)]",
							"This outfit is currently using this slot (<i>"+selectedSlot.getName()+"</i>) for its default icon."
									+"<br/>[style.italics(Activate to make this outfit use the highest value item as its icon instead.)]") {
						@Override
						public void effects() {
							activeOutfit.setIconSlotPriority(null);
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
				}
				
			} else if(index == 0) {
				return new Response("Back", "Return to the item choice screen.", OUTFIT_EDITOR_ITEM_DYE);
			}
			return null;
		}
	};

	private static List<ItemEffect> effects = new ArrayList<>();
	private static TFModifier primaryMod = TFModifier.NONE;
	private static TFModifier secondaryMod = TFModifier.NONE;
	public static TFModifier previousPrimaryMod = TFModifier.NONE;
	public static TFModifier previousSecondaryMod = TFModifier.NONE;
	private static TFPotency potency = TFPotency.MINOR_BOOST;
	private static int limit = 0;
	private static String outputName = "";

	
	public static List<ItemEffect> getEffects() {
		return effects;
	}
	
	public static List<ItemEffect> getDefaultEffects(AbstractCoreItem item) {
		List<ItemEffect> defaultEffects = new ArrayList<>();
		
		if(item instanceof AbstractClothing) {
			AbstractClothing selectedClothing = (AbstractClothing)item;
			defaultEffects = new ArrayList<>(selectedClothing.getClothingType().getEffects());
			
		} else if(item instanceof AbstractWeapon) {
			AbstractWeapon selectedWeapon = (AbstractWeapon)item;
			AbstractWeapon defaultWeapon = Main.game.getItemGen().generateWeapon(selectedWeapon.getWeaponType(), selectedWeapon.getDamageType());
			defaultEffects = new ArrayList<>(defaultWeapon.getEffects());
		}
		
		return defaultEffects;
	}
	
	public static List<ItemEffect> getEffectsPlusDefaults() {
		List<ItemEffect> effectsPlusDefaults = new ArrayList<>(getDefaultEffects(getSelectedItem()));

		for(ItemEffect ie : effects) {
			if(!effectsPlusDefaults.contains(ie)) {
				effectsPlusDefaults.add(ie);
			}
		}
		
		return effectsPlusDefaults;
	}
	
	public static TFModifier getPrimaryMod() {
		return primaryMod;
	}

	public static void setPrimaryMod(TFModifier primaryMod) {
		LilayaDressingRoomDialogue.primaryMod = primaryMod;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFModifier getSecondaryMod() {
		return secondaryMod;
	}

	public static void setSecondaryMod(TFModifier secondaryMod) {
		LilayaDressingRoomDialogue.secondaryMod = secondaryMod;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFPotency getPotency() {
		return potency;
	}

	public static void setPotency(TFPotency potency) {
		LilayaDressingRoomDialogue.potency = potency;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static int getLimit() {
		return limit;
	}

	public static void setLimit(int limit) {
		LilayaDressingRoomDialogue.limit = limit;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static String getOutputName() {
		return outputName;
	}
	
	public static void setOutputName(String outputName) {
		// Handle parsing:
		outputName = outputName.replaceAll("\\[\\#(.*?)]", ""); // Remove game parsing
		LilayaDressingRoomDialogue.outputName = outputName;
	}
	
	public static void resetEnchantmentVariables() {
		LilayaDressingRoomDialogue.primaryMod = TFModifier.NONE;
		LilayaDressingRoomDialogue.secondaryMod = TFModifier.NONE;
		LilayaDressingRoomDialogue.potency = TFPotency.MINOR_BOOST;
		LilayaDressingRoomDialogue.limit = 0;
	}
	
	public static boolean addEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		
		boolean added = false;
		
		if(effects.size()<getSelectedItem().getEnchantmentLimit()) {
			added = getEffects().add(effect);
			
			if(added) {
				if(defaultName) {
					setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
				} else {
					if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
						setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
					}
				}
			}
		}
		
		return added;
	}
	
	public static boolean removeEffect(int index) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		getEffects().remove(index);

		if(defaultName) {
			setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
		} else {
			if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
				setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
			}
		}
		
		return true;
	}
	
	public static boolean removeEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		boolean removed = getEffects().remove(effect);
		
		if(removed) {
			if(defaultName) {
				setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
			} else {
				if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
					setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
				}
			}
		}
		
		return removed;
	}

	public static void initModifiers() {
		effects = new ArrayList<>(getSelectedItem().getEffects());
		
		if(!getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).contains(primaryMod)) {
			primaryMod = getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).get(0);
		}
		if(!getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).contains(secondaryMod)) {
			secondaryMod = getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).get(0);
		}
		if(!getSelectedItem().getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(potency)) {
			potency = TFPotency.MINOR_BOOST;
		}
		if(limit <= getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod)) {
			limit = getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod);
		}
	}
	
	private static String getEnchantmentUI() {
		StringBuilder inventorySB = new StringBuilder();
		
		ItemEffect effect = getCurrentEffect();
		
		int displaySlots = Math.max(32, 8*(int)Math.ceil(
				Math.max(getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).size(), getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).size())/8f));
		
		// Primary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem())) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
				+ "<h5 style='margin:0; padding:0;'>Primary Modifier</h5>"
				+ "</div>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(primaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0;background-color:"+primaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+primaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div></div>");
		
		inventorySB.append("</div>");
		
		
		// Secondary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod)) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(secondaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0; background-color:"+secondaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+secondaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
					+ "<h5 style='margin:0; padding:0;'>Secondary Modifier</h5>"
				+ "</div>"
				+ "</div>");
		
		inventorySB.append("</div>");

		
		// Potency:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
		for(TFPotency potency : TFPotency.values()) {
			inventorySB.append("<div class='normal-button"
									+(getSelectedItem().getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(potency)?"":" disabled")
									+(LilayaDressingRoomDialogue.potency==potency?" selected":"")+"'"
								+ " id='POTENCY_"+potency+"'"
								+ " style='"+(LilayaDressingRoomDialogue.potency==potency?"color:"+potency.getColour().toWebHexString()+";":"")+" margin:0 1%; width:14%;'>"+potency.getName()+"</div>");
		}
		
		inventorySB.append("</div>");

		// Limits:
		int ingredientLimit = getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod);
		if(ingredientLimit!=0) {
			inventorySB.append(
					"<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_MINIMUM' style='width:100%;'>Limit Min.</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE_LARGE' style='width:100%;'>Limit--</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE' style='width:100%;'>Limit-</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE' style='width:100%;'>Limit+</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE_LARGE' style='width:100%;'>Limit++</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_MAXIMUM' style='width:100%;'>Limit Max.</div>"
						+ "</div>"
					+ "</div>");
		}
		
		// Effect:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");

			inventorySB.append("<div class='container-half-width' style='width:28%; margin:0 1%;'>"
									+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Effect to be added:</b>"
								+ "</div>");
		
			inventorySB.append("<div class='container-half-width' style='width:48%; margin:0 1%;'>");
				if(effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					int i=0;
					for(String s : effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
						if(i>0) {
							inventorySB.append("<br/>");
						}
						inventorySB.append("<b>"+Util.capitaliseSentence(s)+"</b>");
						i++;
					}
				} else {
					inventorySB.append("<b>-</b>");
				}

				// Append enchantment capacity cost for weapons/clothing/tattoos
				if(Main.game.isEnchantmentCapacityEnabled()) {
					if((getSelectedItem() instanceof AbstractClothing)
							|| (getSelectedItem() instanceof AbstractWeapon)
							|| (getSelectedItem() instanceof Tattoo)) {
						
						
						if(effect.getItemEffectType()==ItemEffectType.CLOTHING
								|| effect.getItemEffectType()==ItemEffectType.WEAPON
								|| effect.getItemEffectType()==ItemEffectType.TATTOO) {
							if(effect.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE || effect.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								int cost = Math.max(0, effect.getPotency().getClothingBonusValue());
								if(effect.getSecondaryModifier()==TFModifier.FERTILITY
										|| effect.getSecondaryModifier()==TFModifier.VIRILITY) {
									cost = 0;
								} else if(effect.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(effect.getPotency().isNegative()) {
										cost = Math.abs(effect.getPotency().getClothingBonusValue());
									} else {
										cost = 0;
									}
								}
								inventorySB.append("<br/>"
										+ (cost>0
												?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+")]: "+UtilText.formatAsEnchantmentCapacity(cost, "b")
												:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+": [style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(cost, "b")+")]"));
							}
						}
					}
				}
			inventorySB.append("</div>");
			
			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%;'>");
				if(effects.size() >= getSelectedItem().getEnchantmentLimit()
						|| getSelectedItem().getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| getSelectedItem().getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()
						|| getEnchantmentEffectBlockedReason(effect)!=null) {
					inventorySB.append(
							"<div class='normal-button disabled' style='width:100%; margin:auto 0;'>"
							+ "<b>Add</b> | "
							+ (getSelectedItem() instanceof Tattoo
									?UtilText.formatAsMoneyUncoloured(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
									:UtilText.formatAsEssencesUncoloured(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect), "b", false))
							+ "<div class='overlay no-pointer' id='ENCHANT_ADD_BUTTON_DISABLED'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append(
							"<div class='normal-button' style='width:100%; margin:auto 0;'>"
							+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Add</b> | "
									+ (getSelectedItem() instanceof Tattoo
											?UtilText.formatAsMoney(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
											:UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect), "b", false))
							+ "<div class='overlay' id='ENCHANT_ADD_BUTTON'></div>"
							+ "</div>");
					
				}
			inventorySB.append("</div>");
		
		inventorySB.append("</div>");
		
		
		// Item crafting:
		// The costing UI differs here from the standard enchanting UI, as the costs for the enchantment need to all be shown at all times
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
				inventorySB.append("<b>Input</b>"
						+ "<div class='enchanting-ingredient' style='background-color:"+getSelectedItem().getRarity().getBackgroundColour().toWebHexString()+";'>"
						+ "<div class='enchanting-ingredient-content'>"+getSelectedItem().getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'  style='cursor:default;'></div>"
						+ "</div>");
			inventorySB.append("</div>");

			// Effects:
			inventorySB.append("<div class='container-half-width' style='width:58%; margin:0 1%;'>");

			//style='text-align:center;float:left; width:80%; padding:0; margin:0 0 5% 5%;'
				inventorySB.append("<form style='padding:0; margin:0 0 4px 0; float:left; width:90%; text-align:center;'>");
					inventorySB.append("<input type='text' id='output_name' value='" +UtilText.parseForHTMLDisplay(outputName)+"' style='padding:0;margin:0;width:100%;text-align:center;'>");
				inventorySB.append("</form>");
				inventorySB.append("<div class='normal-button' id='apply_enchanted_item_name' style='float:left; width:9.5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>");
					inventorySB.append("&#10003;");
				inventorySB.append("</div>");
				
				
				List<ItemEffect> defaultEffects = getDefaultEffects(getSelectedItem());
				int totalCost = 0;
				for(ItemEffect ie : effects) {
					if(!defaultEffects.contains(ie)) {
						totalCost += EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), ie);
					}
				}
				
				inventorySB.append("<b>Effects (</b>"
									+ (effects.size()>=getSelectedItem().getEnchantmentLimit()?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>":"<b>")+""
											+ effects.size()+"/"+getSelectedItem().getEnchantmentLimit()+"</b><b>)</b> | Total cost: "
												+ (getSelectedItem() instanceof Tattoo
														?UtilText.formatAsMoney(EnchantingUtils.getCost(getSelectedItem(), effects)*EnchantingUtils.FLAME_COST_MODIFER, "b")
														:UtilText.formatAsEssences(totalCost,  "b", false)
//															(getSelectedItem() instanceof AbstractClothing
//																?UtilText.formatAsEssences(EnchantingUtils.getCost(Main.game.getItemGen().generateClothing(((AbstractClothing)getSelectedItem()).getClothingType(), false), effects), "b", false)
//																:UtilText.formatAsEssences(EnchantingUtils.getCost(Main.game.getItemGen().generateWeapon(((AbstractWeapon)getSelectedItem()).getWeaponType()), effects), "b", false))
														// UtilText.formatAsEssences(EnchantingUtils.getCost(getSelectedItem(), effects), "b", false)
														)
								);
			
				if(effects.isEmpty()) {
					inventorySB.append("<br/><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No effects added</span>");
				} else {
					inventorySB.append("<br/><i>Default effects are marked with a [style.boldDisabled('D')], and are free.</i>");
					int capcityCost = 0;
					
					int it = 0;
					for(ItemEffect ie : effects) {
						boolean isDefaultEffect = defaultEffects.contains(ie);
						if(ie.getItemEffectType()==ItemEffectType.CLOTHING
								|| ie.getItemEffectType()==ItemEffectType.WEAPON
								|| ie.getItemEffectType()==ItemEffectType.TATTOO) {
							if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
									|| ie.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								if(ie.getSecondaryModifier()==TFModifier.FERTILITY
										|| ie.getSecondaryModifier()==TFModifier.VIRILITY) {
									capcityCost += 0;
								} else if(ie.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(ie.getPotency().isNegative()) {
										capcityCost += Math.abs(ie.getPotency().getClothingBonusValue());
									}
								} else {
									capcityCost += Math.max(0, ie.getPotency().getClothingBonusValue());
								}
							}
						}
						
						int i=0;
						for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							inventorySB.append(
									"<div class='container-full-width'"
											+ " style='background:"+RenderingEngine.getEntryBackgroundColour(it%2==0)+"; width:98%; margin:0 1%; padding:0;'>");

								inventorySB.append("<div style='width:calc(100% - 94px); line-height:22px; margin:0; padding:0; float:left;'>");
									if(isDefaultEffect) {
										inventorySB.append("<div style='position:absolute; left:0; margin-right:24px;'>"
													+ "<i>[style.boldDisabled(D)]</i>"
												+ "</div>");
									}
									inventorySB.append(Util.capitaliseSentence(s));
								inventorySB.append("</div>");
								if(i==0) {
									// Show cost for adding this effect:
									inventorySB.append("<div style='width:64px; line-height:22px; margin:0; padding:0 0 0 4px; float:left;'>");
										int essenceCost = EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), ie);
										if(isDefaultEffect) {
											if(effects.contains(ie)) {
												essenceCost = 0;
											} else {
												essenceCost = EnchantingUtils.getModifierEffectCost(false, getSelectedItem(), ie);
											}
										}
										if(essenceCost==0) {
											inventorySB.append(UtilText.formatAsEssencesUncoloured(essenceCost, "b", false));
										} else {
											inventorySB.append(UtilText.formatAsEssences(essenceCost, "b", false));
										}
									inventorySB.append("</div>");
									
//									if(getSelectedItem().getEffects().contains(ie)) {
										inventorySB.append(
												"<div class='normal-button' style='width:22px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0 0 0 4px; float:left; text-align:left;'>"
													+ "<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>X</b> "
													+ "<div class='overlay' id='DELETE_EFFECT_"+it+"'></div>"
												+ "</div>");
										
//									} else {
//										inventorySB.append(
//												"<div class='normal-button' id='DELETE_EFFECT_"+it+"' style='width:22px; height:22px; line-height:22px; font-size:16px; margin:0; padding:0; float:right; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"
//													+ "<b>X</b>"
//												+ "</div>");
//									}
								}
							inventorySB.append("</div>");
							i++;
						}
						it++;
					}
					
					if(Main.game.isEnchantmentCapacityEnabled()) {
						if((getSelectedItem() instanceof AbstractClothing)
								|| (getSelectedItem() instanceof AbstractWeapon)
								|| (getSelectedItem() instanceof Tattoo)) {
							inventorySB.append("<br/>"
									+ (capcityCost>0
											?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+")]: "+UtilText.formatAsEnchantmentCapacity(capcityCost, "b")
											:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+": [style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(capcityCost, "b")+")]"));
						}
					}
				}
			inventorySB.append("</div>");
			
			
			AbstractCoreItem preview = null;
			if(getSelectedItem() instanceof AbstractItem) {
				preview = EnchantingUtils.craftItem(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			} else if(getSelectedItem() instanceof AbstractClothing) {
				preview = EnchantingUtils.craftClothing(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			} else if(getSelectedItem() instanceof AbstractWeapon) {
				preview = EnchantingUtils.craftWeapon(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			}  else if(getSelectedItem() instanceof Tattoo) {
				preview = EnchantingUtils.craftTattoo(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());
			}
			
			if(preview!=null) {
				inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
					inventorySB.append("<b>Output</b>");
					inventorySB.append("<div class='enchanting-ingredient' style='background-color:"+preview.getRarity().getBackgroundColour().toWebHexString()+";'>");
						inventorySB.append("<div class='enchanting-ingredient-content'>"+preview.getSVGString()+"</div>");
						inventorySB.append("<div class='overlay' id='OUTPUT_ENCHANTING' style='cursor:default;'></div>");
					inventorySB.append("</div>");
				inventorySB.append("</div>");
			}
		inventorySB.append("</div>");
		inventorySB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		
		return inventorySB.toString();
	}

	public static ItemEffect getCurrentEffect() {
		return new ItemEffect(getSelectedItem().getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit);
	}
	
	public static String getEnchantmentEffectBlockedReason(ItemEffect effect) {
		if(getSelectedItem() instanceof AbstractClothing) {
			if(effect.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
				for(ItemEffect ie : effects) {
					if(ie.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
						return "Only one 'vibration' effect can be added to clothing!";
					}
				}
			}
		}
		return null;
	}
	
	
	// Utility methods for tile:
	
	public static void calculateOutfitAvailability() {
		loadedOutfitsAvailabilityFromTile = new HashMap<>();

		for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()) {
			loadedOutfitsAvailabilityFromTile.put(entry.getValue(), getOutfitAvailabilityCount(entry.getValue()));
		}
		
//		Map<Outfit, Map<AbstractWeapon, Integer>> outfitToWeaponToRequirementMap = new HashMap<>();
//		Map<Outfit, Map<AbstractClothing, Integer>> outfitToClothingToRequirementMap = new HashMap<>();
//
//		for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()) {
//			loadedOutfitsAvailabilityFromTile.put(entry.getValue(), 0);
//			outfitToWeaponToRequirementMap.put(entry.getValue(), new HashMap<>());
//			outfitToClothingToRequirementMap.put(entry.getValue(), new HashMap<>());
//			
//			for(Entry<InventorySlot, AbstractWeapon> e2 : entry.getValue().getWeapons().entrySet()) {
//				outfitToWeaponToRequirementMap.get(entry.getValue()).putIfAbsent(e2.getValue(), 0);
//				outfitToWeaponToRequirementMap.get(entry.getValue()).put(e2.getValue(), outfitToWeaponToRequirementMap.get(entry.getValue()).get(e2.getValue())+1);
//			}
//			
//			for(Entry<InventorySlot, AbstractClothing> e2 : entry.getValue().getClothing().entrySet()) {
//				outfitToClothingToRequirementMap.get(entry.getValue()).putIfAbsent(e2.getValue(), 0);
//				outfitToClothingToRequirementMap.get(entry.getValue()).put(e2.getValue(), outfitToClothingToRequirementMap.get(entry.getValue()).get(e2.getValue())+1);
//			}
//		}
//		
//
//		for(Outfit outfit : outfitToClothingToRequirementMap.keySet()) {
//			int minimumOutfitsCompleted = 9999;
//			for(Entry<AbstractWeapon, Integer> weaponRequirementEntry : outfitToWeaponToRequirementMap.get(outfit).entrySet()) {
//				int maximumOutfitsFromThisWeapon = 0;
//				for(Entry<AbstractWeapon, Integer> weaponPresentEntry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
//					if(weaponPresentEntry.getKey().equals(weaponRequirementEntry.getKey())) {
//						maximumOutfitsFromThisWeapon = weaponPresentEntry.getValue() / weaponRequirementEntry.getValue();
//					}
//				}
//				if(maximumOutfitsFromThisWeapon<minimumOutfitsCompleted) {
////					loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisWeapon);
//					minimumOutfitsCompleted = maximumOutfitsFromThisWeapon;
//				}
//				if(maximumOutfitsFromThisWeapon==0) {
//					break;
//				}
//			}
//			for(Entry<AbstractClothing, Integer> clothingRequirementEntry : outfitToClothingToRequirementMap.get(outfit).entrySet()) {
//				int maximumOutfitsFromThisClothing = 0;
//				for(Entry<AbstractClothing, Integer> clothingPresentEntry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
//					if(clothingPresentEntry.getKey().equals(clothingRequirementEntry.getKey())) {
//						maximumOutfitsFromThisClothing = clothingPresentEntry.getValue() / clothingRequirementEntry.getValue();
//					}
//				}
//				if(maximumOutfitsFromThisClothing<minimumOutfitsCompleted) {
////					loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisClothing);
//					minimumOutfitsCompleted = maximumOutfitsFromThisClothing;
//				}
//				if(maximumOutfitsFromThisClothing==0) {
//					break;
//				}
//			}
//			loadedOutfitsAvailabilityFromTile.put(outfit, minimumOutfitsCompleted);
//		}
		
//		for(Entry<Outfit, Integer> e : loadedOutfitsAvailabilityFromTile.entrySet()) {
//			System.out.println(e.getKey().getName()+": "+e.getValue());
//			System.out.println("###########");
//		}
	}
	

	private static int getOutfitAvailabilityCount(Outfit outfit) {
//		if(outfit.getWeapons().isEmpty() && outfit.getClothing().isEmpty()) {
//			
//		}
		
		Map<Outfit, Map<AbstractWeapon, Integer>> outfitToWeaponToRequirementMap = new HashMap<>();
		Map<Outfit, Map<AbstractClothing, Integer>> outfitToClothingToRequirementMap = new HashMap<>();
		
		outfitToWeaponToRequirementMap.put(outfit, new HashMap<>());
		outfitToClothingToRequirementMap.put(outfit, new HashMap<>());
		
		for(Entry<InventorySlot, AbstractWeapon> e2 : outfit.getWeapons().entrySet()) {
			outfitToWeaponToRequirementMap.get(outfit).putIfAbsent(e2.getValue(), 0);
			outfitToWeaponToRequirementMap.get(outfit).put(e2.getValue(), outfitToWeaponToRequirementMap.get(outfit).get(e2.getValue())+1);
		}
		
		for(Entry<InventorySlot, AbstractClothing> e2 : outfit.getClothing().entrySet()) {
			outfitToClothingToRequirementMap.get(outfit).putIfAbsent(e2.getValue(), 0);
			outfitToClothingToRequirementMap.get(outfit).put(e2.getValue(), outfitToClothingToRequirementMap.get(outfit).get(e2.getValue())+1);
		}
		
		int minimumOutfitsCompleted = 9999;
		for(Entry<AbstractWeapon, Integer> weaponRequirementEntry : outfitToWeaponToRequirementMap.get(outfit).entrySet()) {
			int maximumOutfitsFromThisWeapon = 0;
			for(Entry<AbstractWeapon, Integer> weaponPresentEntry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
				if(weaponPresentEntry.getKey().equals(weaponRequirementEntry.getKey())) {
					maximumOutfitsFromThisWeapon = weaponPresentEntry.getValue() / weaponRequirementEntry.getValue();
				}
			}
			if(maximumOutfitsFromThisWeapon<minimumOutfitsCompleted) {
//				loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisWeapon);
				minimumOutfitsCompleted = maximumOutfitsFromThisWeapon;
			}
			if(maximumOutfitsFromThisWeapon==0) {
				break;
			}
		}
		for(Entry<AbstractClothing, Integer> clothingRequirementEntry : outfitToClothingToRequirementMap.get(outfit).entrySet()) {
			int maximumOutfitsFromThisClothing = 0;
			for(Entry<AbstractClothing, Integer> clothingPresentEntry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
				if(clothingPresentEntry.getKey().equals(clothingRequirementEntry.getKey())) {
					maximumOutfitsFromThisClothing = clothingPresentEntry.getValue() / clothingRequirementEntry.getValue();
				}
			}
			if(maximumOutfitsFromThisClothing<minimumOutfitsCompleted) {
//				loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisClothing);
				minimumOutfitsCompleted = maximumOutfitsFromThisClothing;
			}
			if(maximumOutfitsFromThisClothing==0) {
				break;
			}
		}
		
		return minimumOutfitsCompleted;
	}
	
	/**
	 * @return Integer representing how many instances of the outfit can be constructed from items within the player's current tile.
	 */
	public static int getOutfitAvailabilityFromTile(Outfit outfit) {
		// If this outfit is not in the map, then calculate for just this outfit:
		if(!loadedOutfitsAvailabilityFromTile.containsKey(outfit)) {
			return getOutfitAvailabilityCount(outfit);
		}
		
		try {
			return loadedOutfitsAvailabilityFromTile.get(outfit);
		} catch(Exception ex) {
			System.out.println(outfit.getName());
			ex.printStackTrace();
		}
		return -1;
	}
	
	// Installation dialogue:
	
	public static final DialogueNode INSTALLATION = new DialogueNode("Dressing room", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "You've had this room converted into a dressing room, and now you're left wondering whether to have Lyssieth's wardrobe fixed...", ROOM_DRESSING_ROOM);
			}
			return null;
		}
	};

	public static final DialogueNode WARDROBE_ACTIVATION = new DialogueNode("Dressing room", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "WARDROBE_ACTIVATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Lyssieth's wardrobe is once again functional, allowing you to create outfits from nothing.", ROOM_DRESSING_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated, true);
					}
				};
			}
			return null;
		}
	};
	
}
