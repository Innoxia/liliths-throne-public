package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.*;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.settings.*;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.ArtistWebsite;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Units.UnitType;
import com.lilithsthrone.utils.Units.ValueType;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class OptionsDialogue {

	private static boolean confirmNewGame = false;
	
	public static final DialogueNode MENU = new DialogueNode("Menu", "Menu", true) {
		
		@Override
		public String getLabel() {
			return "";
		}
		
		@Override
		public String getContent(){
			return "<h1 class='special-text' style='font-size:48px; line-height:52px; text-align:center;'>"+Main.GAME_NAME+"</h1>"
					+ "<h5 class='special-text' style='text-align:center;'>Created by "+Main.AUTHOR+"</h5>"
					+ "<br/>"
					+ "<p>"
						+ "This game is a text-based erotic RPG, and contains a lot of graphic sexual content. You must agree to the game's disclaimer before playing this game!"
					+ "</p>"
					+"<p>"
						+ "You can visit my blog (https://lilithsthrone.blogspot.co.uk) to check on development progress (use the 'Blog' button below to open the blog in your default browser)."
					+ "</p>"
					+ "<p style='text-align:center'>"
						+ "<b>Please use either my blog or github to get the latest official version of Lilith's Throne!</b>"
					+ "</p>"
					+ getJavaVersionInformation()
					+ (Toolkit.getDefaultToolkit().getScreenSize().getHeight()<800
							?"<p style='text-align:center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"
								+ "If the game's resolution isn't fitting to your screen, press the keys: 'Windows' + 'Up Arrow' to maximise!"
							+ "</p>"
							:"")
					+ "<br/>"
					+ (Main.game.isStarted() || Main.getProperties().name.isEmpty()
							?""
							:"<h4 style='text-align:center;'>Last save:</h4>"
								+ "<h5 style='color:" + Main.getProperties().nameColour + ";text-align:center;'>" + Main.getProperties().name + "</h5>"
								+ "<p style='text-align:center;'><b>Level " + Main.getProperties().level + " " + Util.capitaliseSentence(Main.getProperties().race) + "</b></p>"
								+ "<p style='text-align:center;'>" + UtilText.formatAsMoney(Main.getProperties().money, "b") + "</p>"
								+ "<div style='text-align:center; display:block; margin:auto;'>" + UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false) + "</div>"
								+ "<p style='text-align:center;'>Quest: " + Util.capitaliseSentence(Main.getProperties().quest) + "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			 if (index == 1) {
				 if(confirmNewGame || !Main.game.isStarted()) {
					return new ResponseEffectsOnly(
							(!Main.game.isStarted()
									?"New Game"
									:"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Confirm</b>"),
							"Start a new game."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]</b>"
									:"")){
						@Override
						public void effects() {
							//Fixes a bug where inventory would stay on screen
							if (Main.game.isStarted()) {
								Main.game.setInCombat(false);
								Main.game.setInSex(false);
							}
							
							Main.mainController.setAttributePanelContent("");
							Main.mainController.setRightPanelContent("");
							Main.mainController.setButtonsContent("");
							Main.game.setRenderMap(false);
							Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
							confirmNewGame = false;
						}
					};
					
				 } else {
					 return new Response(
							 "New Game",
							 "Start a new game."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]"
									:""),
								MENU){
							@Override
							public void effects() {
								confirmNewGame = true;
							}
						};
				 }
				
			} else if (index == 2) {
				return new Response("Save/Load", "Open the save/load game window.", SAVE_LOAD){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 3) {
				return new Response("Export character", "Open the character export game window.", IMPORT_EXPORT){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 4) {
				return new Response("Disclaimer", "View the game's disclaimer.", DISCLAIMER){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
				
			} else if (index == 5) {
				return new ResponseEffectsOnly("Quit", "Quits your current game and closes the program.<br/><br/><b>Remember to save your game first!</b>"){
					@Override
					public void effects() {
						Main.primaryStage.close();
						confirmNewGame=false;
						
					}
				};
				
			} else if (index == 6) {
				return new Response("Options", "Open the options page.", OPTIONS){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};

			} else if (index == 7) {
				return new Response("Content Options", "Set your preferred content settings.", CONTENT_PREFERENCE){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};
			
			} else if (index == 8) {
				return new Response("Patch notes", "View the patch notes for this version.", PATCH_NOTES);
			
			} else if (index == 9) {
				return new Response("Credits", "View the game's credits screen.", CREDITS);
				
			} else if (index == 10) {
				return new ResponseEffectsOnly("Blog", "Opens the page:<br/><br/><i>https://lilithsthrone.blogspot.co.uk/</i><br/><br/><b>Externally in your default browser.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://lilithsthrone.blogspot.co.uk/");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 11) {
				return new ResponseEffectsOnly("Github", "Opens the page:<br/><br/><i>https://github.com/Innoxia/liliths-throne-public</i><br/><br/><b>Externally in your default browser.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://github.com/Innoxia/liliths-throne-public");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 0) {
				if(Main.game.isStarted()) {
					return new ResponseEffectsOnly("Resume", "Return to whatever you were doing before opening this menu."){
						@Override
						public void effects() {
							Main.mainController.openOptions();
							confirmNewGame=false;
							
						}
					};
					
				} else {
					if(Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
						return new ResponseEffectsOnly("Resume", "Continue playing from your last save."){
							@Override
							public void effects() {
								Main.loadGame(Main.getProperties().lastSaveLocation);
								confirmNewGame=false;
								
							}
						};
					} else if ( "".equals(Main.getProperties().lastSaveLocation) ) {
						return new Response("Resume", "There is no previously saved game to resume.", null);
					} else {
						return new Response("Resume", "Previously saved game (by the title '"+Main.getProperties().lastSaveLocation+"') not found in 'data/saves' folder.", null);
					}
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getJavaVersionInformation() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>"
					+ "Your java version: "+System.getProperty("java.version"));
//				+" | ");
		
//		String[] version = System.getProperty("java.version").split("\\.");
//		if(version[0]!=null) {
//			if(Integer.valueOf(version[0])<9) {
//				sb.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least 9.0.1 to work correctly!");
//			} else {
//				sb.append("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//			}
//		}
//		if(version.length>=2) {
//			if(Integer.valueOf(version[1])<8) {
//				sb.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//				
//			} else {
//				if(version.length==3){
//					String[] versionMinor = version[2].split("_");
//					if(versionMinor.length>=2)
//						if(Integer.valueOf(versionMinor[1])<131) {
//							sb.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//							
//						} else {
//							sb.append("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//						}
//				} else {
//					sb.append("This game needs at least v1.8.0_131 to work correctly!");
//				}
//			}
//		}
		
		sb.append("</p>");
		
		return sb.toString();
	}

	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	
	public static final DialogueNode SAVE_LOAD = new DialogueNode("Save game files", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p style='text-align:center;'>"
						+ "<b>Please Note:</b>"
					+ "</p>"
					+ "<p>"
						+ "1. Only standard characters (letters and numbers) will work for save file names.<br/>"
						+ "2. The 'AutoSave' file is automatically overwritten every time you move between maps.<br/>"
						+ "3. The 'QuickSave' file is automatically overwritten every time you quick save (binding is "+Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE).getFullName()+").<br/>"
						+ "4. You cannot save during scenes which restrict your movement, including combat and sex."
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "Time"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); text-align:center; background:transparent;'>"
							+ "Name"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "Save | Load | Delete"
						+ "</div>"
					+ "</div>");


			int i=0;
			
			if(Main.game.isStarted()) {
				saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
				i++;
			}
			
			Main.getSavedGames().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSavedGames()){
				saveLoadSB.append(getSaveLoadRow("<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to load, overwrite, or delete a saved game."
							+ " When turned on, it will take two clicks to apply any button press."
							+ " When turned off, it will only take one click.",
						SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
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
				return new Response("Back", "Back to the main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode IMPORT_EXPORT = new DialogueNode("Export character", "", true) {
	
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
	
			saveLoadSB.append("<p>"
						+ "Here you can export your current character, or delete any characters that you've exported in the past."
						+ " Any NPC can be exported in-game by viewing their information screen (either from the 'characters present' or your phone's 'contacts' screen), and then pressing the small 'export character' button in the top-right."
					+ "</p>"
					+ "<p>"
						+ "Exported characters can be used as a playable character when starting a new game (choose 'Start (Import)'), or as an importable slave at the Auction Block in Slaver Alley."
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-quarter-width' style='text-align:center;'>"
							+ "Time"
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(55% - 16px); text-align:center; background:transparent;'>"
							+ "Name"
						+ "</div>"
						+ "<div class='container-quarter-width' style='width:calc(20% - 16px); text-align:center; background:transparent;'>"
							+ "Functions"
						+ "</div>"
					+ "</div>");
			
			Main.getCharactersForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			int i = 0;
			for(File f : Main.getCharactersForImport()){
				saveLoadSB.append(OptionsDialogue.getImportRow("<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to load, overwrite, or delete a saved game."
							+ " When turned on, it will take two clicks to apply any button press."
							+ " When turned off, it will only take one click.",
							IMPORT_EXPORT) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};
	
			} else if (index == 2) {
				if(Main.game.isStarted()) {
					return new Response("Export character", "Exports your character file to the 'data/characters/' folder.", IMPORT_EXPORT){
						@Override
						public void effects() {
							CharacterUtils.saveCharacterAsXML(Main.game.getPlayer());
							Main.game.flashMessage(Colour.GENERIC_GOOD, "Character exported!");
						}
					};
				} else {
					return new Response("Export character", "You'll need to start a game first!", null);
				}
			
			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", OptionsDialogue.MENU);
	
			} else {
				return null;
			}
		}
	
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getSaveLoadRow(String date, String name, boolean altColour) {
		if(name!=null){
			String baseName = Util.getFileName(name);
			String identifierName = Util.getFileIdentifier(name);
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ date
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+ baseName
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
									?(name.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='overwrite_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='overwrite_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='overwrite_saved_" + identifierName + "_disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (name.equals(loadConfirmationName)
									?"<div class='square-button saveIcon' id='load_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
									:"<div class='square-button saveIcon' id='load_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
	
	
							+ (name.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='delete_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='delete_saved_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "-"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' value='New Save' style='padding:0;margin:0;width:100%;'></form>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
								?"<div class='square-button saveIcon' id='new_saved' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
								:"<div class='square-button saveIcon disabled' id='new_saved_disabled' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
						+ "</div>"
					+ "</div>";
				
		}
	}

	private static String getImportRow(String date, String name, boolean altColour) {
		String baseName = Util.getFileName(name);
		String identifierName = Util.getFileIdentifier(name);
		
		return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					+ "<div class='container-quarter-width' style='background:transparent;'>"
						+ date
					+ "</div>"
					+ "<div class='container-half-width' style='width: calc(55% - 16px); background:transparent;'>"
						+ baseName
					+ "</div>"
					+ "<div class='container-quarter-width' style='padding:auto 0; margin:auto 0; width:20%; text-align:center; background:transparent;'>"
					+ (name.equals(deleteConfirmationName)
							?"<div class='square-button big' id='delete_saved_character_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button big' id='delete_saved_character_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}
	
	
	public static final DialogueNode OPTIONS = new DialogueNode("Options", "Options", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
					+ "<b>Light/Dark theme:</b>"
					+ "<br/>This switches the main display between a light and dark theme. (Work in progress!)"
					+ "</p>"
					
					+"<p>"
					+ "<b>Font-size:</b><br/>"
					+ "This cycles the game's base font size. This currently only affects the size of the text in the main dialogue, but in the future I'll expand it to include every display element.<br/>"
					+ "Minimum font size is "+Game.FONT_SIZE_MINIMUM+". Default font size is "+Game.FONT_SIZE_NORMAL+". Maximum font size is "+Game.FONT_SIZE_HUGE+".<br/>"
					+ "Current font size: "+Main.getProperties().fontSize+"."
					+ "</p>"

					+"<p>"
					+ "<b>Fade-in:</b>"
					+ "<br/>This option is responsible for fading in the main part of the text each time a new scene is displayed."
					+ " Although it makes scene transitions a little prettier, it is off by default, as it can cause some annoying lag in inventory screens."
					+ "</p>"

					+"<p>"
					+ "<b>Difficulty (Currently set to "+Main.getProperties().difficultyLevel.getName()+"):</b>");
			
			for(DifficultyLevel dl : DifficultyLevel.values()) {
				UtilText.nodeContentSB.append("<br/>"+(
						Main.getProperties().difficultyLevel==dl
							?"<b style='color:"+dl.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(dl.getName())+"</b> "+dl.getDescription()
							:"<span style='color:"+dl.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(dl.getName())+"</span> [style.colourDisabled("+dl.getDescription()+")]")
						 );
			}

			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keybinds", "Open the keybinds page, where you can customise all the game's key bindings.", KEYBINDS);
				
			} else if (index == 2) {

				if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
					return new Response("Dark theme", "Switch the theme to the dark variant.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
						};
				} else {
					return new Response("Light theme (WIP)", "Switch the theme to the light variant.<br/><br/><b>This is still a work in progress...</b>.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
					};
				}

			} else if (index == 3) {
				return new Response("Font-size -",
						"Increase the size of the game's font. Default value is 18. Current value is "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize > Game.FONT_SIZE_MINIMUM) {
							Main.getProperties().fontSize--;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 4) {
				return new Response("Font-size +",
						"Increase the size of the game's font. Default value is 18. Current value is "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize < Game.FONT_SIZE_HUGE) {
							Main.getProperties().fontSize++;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 5) {
				return new Response("Fade-in: " + (Main.getProperties().hasValue(PropertyValue.fadeInText)
						? "[style.boldGood(ON)]"
						: "[style.boldBad(OFF)]"), "Toggle the fading in of the game's text. If turned on, it may cause some minor lag in inventory screens.", OPTIONS) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.fadeInText, !Main.getProperties().hasValue(PropertyValue.fadeInText));
						Main.saveProperties();
					}
				};
				
			} else if (index == 6) {
				return new Response("Gender pronouns", "Customise all gender pronouns and names.", OPTIONS_PRONOUNS);
				
			} else if (index == 7) {
				return new Response("Gender preferences", "Set your preferred gender encounter rates.", GENDER_PREFERENCE);

			} else if (index == 8) {
				return new Response("Orientation preferences", "Set your preferred sexual orientation encounter rates.", ORIENTATION_PREFERENCE);
			
			} else if (index == 9) {
				return new Response("Age preferences", "Set your preferred age encounter rates.", AGE_PREFERENCE);
				
			} else if (index == 10) {
				return new Response("Furry preferences", "Set your preferred transformation encounter rates.", FURRY_PREFERENCE);

			} else if (index == 11) {
				return new Response("Unit preferences", "Set your preferred measurement units.", UNIT_PREFERENCE);

			} else if (index == 12) {
				return new Response("Difficulty: "+Main.getProperties().difficultyLevel.getName(), "Cycle the game's difficulty.", OPTIONS){
					@Override
					public void effects() {
						switch(Main.getProperties().difficultyLevel) {
							case NORMAL:
								Main.getProperties().difficultyLevel = DifficultyLevel.LEVEL_SCALING;
								break;
							case LEVEL_SCALING:
								Main.getProperties().difficultyLevel = DifficultyLevel.HARD;
								break;
							case HARD:
								Main.getProperties().difficultyLevel = DifficultyLevel.NIGHTMARE;
								break;
							case NIGHTMARE:
								Main.getProperties().difficultyLevel = DifficultyLevel.HELL;
								break;
							case HELL:
								Main.getProperties().difficultyLevel = DifficultyLevel.NORMAL;
								break;
						}
						Main.saveProperties();
						
						for(NPC npc : Main.game.getAllNPCs()) {
							if(!Main.game.isInCombat() || !Combat.getAllCombatants(false).contains(npc)) {
								npc.setMana(npc.getAttributeValue(Attribute.MANA_MAXIMUM));
								npc.setHealth(npc.getAttributeValue(Attribute.HEALTH_MAXIMUM));
							}
						}
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode KEYBINDS = new DialogueNode("Options", "Options", true) {

		@Override
		public String getHeaderContent() {
			return "<p>"
					+ "<table align='center'>"
					+ "<tr><th>Action</th><th>Primary binding</th><th>Secondary binding</th></tr>"
					+ getKeybindTableRow(KeyboardAction.MENU)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.QUICKSAVE)
					+ getKeybindTableRow(KeyboardAction.QUICKLOAD)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MENU_SELECT)
					+ getKeybindTableRow(KeyboardAction.INVENTORY)
					+ getKeybindTableRow(KeyboardAction.JOURNAL)
					+ getKeybindTableRow(KeyboardAction.MAP)
					+ getKeybindTableRow(KeyboardAction.CHARACTERS)
					+ getKeybindTableRow(KeyboardAction.ZOOM)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_EAST)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_1)
					+ getKeybindTableRow(KeyboardAction.RESPOND_2)
					+ getKeybindTableRow(KeyboardAction.RESPOND_3)
					+ getKeybindTableRow(KeyboardAction.RESPOND_4)
					+ getKeybindTableRow(KeyboardAction.RESPOND_5)
					+ getKeybindTableRow(KeyboardAction.RESPOND_6)
					+ getKeybindTableRow(KeyboardAction.RESPOND_7)
					+ getKeybindTableRow(KeyboardAction.RESPOND_8)
					+ getKeybindTableRow(KeyboardAction.RESPOND_9)
					+ getKeybindTableRow(KeyboardAction.RESPOND_10)
					+ getKeybindTableRow(KeyboardAction.RESPOND_11)
					+ getKeybindTableRow(KeyboardAction.RESPOND_12)
					+ getKeybindTableRow(KeyboardAction.RESPOND_13)
					+ getKeybindTableRow(KeyboardAction.RESPOND_14)
					+ getKeybindTableRow(KeyboardAction.RESPOND_0)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_PAGE)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_PAGE)

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_TAB)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_TAB)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_EAST)
					+ "</table>"
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		ArrayList<Properties> presets;

		private void loadPresets() {
			presets = new ArrayList<>();

			// Load all text files in the folder as properties
			File presetFolder = new File("res/keybinds");
			if (presetFolder.exists() && presetFolder.isDirectory()) {
				for (File f : presetFolder.listFiles((dir, name) -> name.endsWith(".txt"))) {
					try (FileInputStream input = new FileInputStream(f)) {
						Properties preset = new Properties();
						preset.load(input);
						presets.add(preset);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				presetFolder.mkdirs();
			}
		}

		private void loadPreset(Properties preset) {
			// Clear existing mappings
			Main.getProperties().hotkeyMapPrimary.clear();
			Main.getProperties().hotkeyMapSecondary.clear();

			// Create a key mapping for every action contained in the given property
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (preset.containsKey(ka.name())) {
					String[] keys = preset.getProperty(ka.name()).split(" or ");
					Main.getProperties().hotkeyMapPrimary.put(ka, KeyCodeWithModifiers.fromString(keys[0]));
					if (keys.length == 2) Main.getProperties().hotkeyMapSecondary.put(ka, KeyCodeWithModifiers.fromString(keys[1]));
				}
			}
		}

		private void savePreset(int index) {
			// Create new properties containing current key mappings
			Properties preset = new Properties();
			preset.setProperty("NAME", "Custom " + index);
			preset.setProperty("DESCRIPTION", "Reapply your previously saved key bindings.");

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapPrimary.entrySet())
				if (e.getValue() != null)
					preset.setProperty(e.getKey().name(), e.getValue().toString());

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapSecondary.entrySet()) {
				if (e.getValue() != null) {
					// Write or append to existing entry
					String primary = preset.getProperty(e.getKey().name());
					primary = primary == null ? e.getValue().toString() : primary + " or " + e.getValue().toString();
					preset.setProperty(e.getKey().name(), primary);
				}
			}

			// Write properties to file
			try (FileOutputStream output = new FileOutputStream("res/keybinds/custom" + index + ".txt")) {
				preset.store(output, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			// Load the presets if uninitialized
			if (presets == null) loadPresets();

			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else if (index <= presets.size()) {
				Properties preset = presets.get(index - 1);
				return new Response(preset.getProperty("NAME", "Custom " + index), preset.getProperty("DESCRIPTION", "Reapply your previously saved key bindings."), KEYBINDS) {
					@Override
					public void effects() {
						loadPreset(preset);
						Main.saveProperties();
					}
				};
				
			} else if (index == 14) {
				return new Response("Save preset",
						"Store the current key bindings in a file. If you want to delete any saved presets, navigate to the 'res/keybinds' folder and delete the .txt files that you no longer want."
								+ " (They will stop showing up in this list after a game restart.)",
						KEYBINDS) {
					@Override
					public void effects() {
						savePreset(presets.size() - 2);
						loadPresets();
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getKeybindTableRow(KeyboardAction action) {
		return "<tr>"
				+ "<td>"
				+ action.getName()
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='primary_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapPrimary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? " empty" : "")
				+ "' id='primaryClear_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& !Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='secondary_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapSecondary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? " empty" : "")
				+ "' id='secondaryClear_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode OPTIONS_PRONOUNS = new DialogueNode("Options", "Options", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "<h5 style='text-align:center;'>Global gender names:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
							+ "<th>Body Parts</th>"
								+ "<th style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine</th>"
								+ "<th style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Androgynous</th>"
								+ "<th style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine</th>"
							+ "</tr>");
			
			for(GenderNames gn : GenderNames.values()) {
				sb.append(getGenderNameTableRow(gn));
			}
							
			sb.append("</table>"
					+ "</p>"
					
					+ "<p>"
						+ "<h5 style='text-align:center;'>Player-specific pronouns:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
								+ "<th>Pronoun</th>"
								+ "<th style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine</th>"
								+ "<th style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine</th>"
							+ "</tr>"
							+ getPronounTableRow(GenderPronoun.NOUN)
							+ getPronounTableRow(GenderPronoun.YOUNG_NOUN)
							+ getPronounTableRow(GenderPronoun.SECOND_PERSON)
							+ getPronounTableRow(GenderPronoun.THIRD_PERSON)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_BEFORE_NOUN)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_ALONE)
						+ "</table>"
					+ "</p>"
					+ "<h5 style='text-align:center;'><span style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Androgynous bodies</span> (option 3)</h5>"
					+ "<p>"
					+ "<b style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine:</b> Treated as <b style='color:"+Colour.FEMININE.toWebHexString()+";'>feminine</b>.<br/>"
					+ "<b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Clothing feminine:</b> Treated according to clothing femininity."
							+ " If clothing is neutral, treated as <b style='color:"+Colour.FEMININE.toWebHexString()+";'>feminine</b>.<br/>"
					+ "<b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Clothing masculine:</b> Treated according to clothing femininity."
							+ " If clothing is neutral, treated as <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>masculine</b>.<br/>"
					+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine:</b> Treated as <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>masculine</b>.<br/>"
					+ "</p>");
			
			return sb.toString();	
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Save", "Save all the pronouns that are currently displayed.") {
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameNeutral.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameFemale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + gn +"').value")).toLowerCase());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp +"').value")).toLowerCase());
							Main.getProperties().genderPronounMale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp +"').value")).toLowerCase());
						}
						Main.saveProperties();
						Main.game.flashMessage(Colour.GENERIC_GOOD, "Pronouns saved!");
					}
				};
				
			} else if (index == 2) {
				return new Response("Defaults", "Resets all pronouns to their default values.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, gn.getMasculine());
							Main.getProperties().genderNameNeutral.put(gn, gn.getNeutral());
							Main.getProperties().genderNameFemale.put(gn, gn.getFeminine());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, gp.getFeminine());
							Main.getProperties().genderPronounMale.put(gp, gp.getMasculine());
						}
						Main.saveProperties();
						
					}
				};
				
			} else if (index == 3) {
				return new Response("<span style='color:"+Main.getProperties().androgynousIdentification.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.getProperties().androgynousIdentification.getName())+"</span>",
						"Cycle the way the game treats androgynous bodies as described above.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						switch(Main.getProperties().androgynousIdentification){
							case FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_FEMININE;
								break;
							case CLOTHING_FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_MASCULINE;
								break;
							case CLOTHING_MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.MASCULINE;
								break;
							case MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.FEMININE;
								break;
							default:
								break;
						}
						
						Main.saveProperties();
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else {
				return null;
			}
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderNameTableRow(GenderNames name) {
		return "<tr>"
					+ "<td>"
						+ (name.isHasPenis()?"[style.colourGood(Penis)]":"[style.colourDisabled(Penis)]")
						+ " " + (name.isHasVagina()?"[style.colourGood(Vagina)]":"[style.colourDisabled(Vagina)]")
						+ " " + (name.isHasBreasts()?"[style.colourGood(Breasts)]":"[style.colourDisabled(Breasts)]")
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_MASCULINE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameMale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_ANDROGYNOUS_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameNeutral.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_FEMININE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameFemale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
				+ "</tr>";
	}
	
	private static String getPronounTableRow(GenderPronoun pronoun) {
		return "<tr>"
				+ "<td>"
					+ pronoun.getName()
				+ "</td>"
					+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='masculine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounMale.get(pronoun))+ "'>"
					+ "</form>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='feminine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounFemale.get(pronoun))+ "'></form>"
				+ "</td>"
				+ "</tr>";
	}
	
	
	public static final DialogueNode PATCH_NOTES = new DialogueNode("Patch Notes", "Patch notes", true) {
		
		@Override
		public String getContent(){
			return Main.patchNotes;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode DISCLAIMER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent(){
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	
	public static final DialogueNode GENDER_PREFERENCE = new DialogueNode("Gender preferences", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "These options will determine the gender encounter rates of random NPCs."
					+ " Some NPCs, such as random succubi attackers, have restrictions on their gender, but your preferences will be taken into account wherever possible.<br/>"
					+ "<b>A visual representation of the encounter chances can be seen in the bars at the bottom of each section.</b>"
					+ " (The different shades of each gender are solely for recognition in the bars, and don't mean anything other than that.)"
					+ "<br/>"
					+ "A character is considered to have breasts if they are at least an AA-cup."
					+ "</div>");
			
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else if (index == 1) {
				return new Response("Defaults", "Restore all gender preferences to their default values.", GENDER_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetGenderPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderPreferencesPanel(PronounType type) {
		int count = 0;
		Colour colour = Colour.MASCULINE;
		switch(type) {
			case FEMININE:
				colour = Colour.FEMININE;
				break;
			case MASCULINE:
				colour = Colour.MASCULINE;
				break;
			case NEUTRAL:
				colour = Colour.ANDROGYNOUS;
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		for(Gender g : Gender.values()) {
			if(g.getType()==type) {
				sb.append(
						"<div style='display:inline-block; margin:4px auto;width:100%;'>"
							+ "<div style='display:inline-block; margin:0 auto;'>"
								+ "<div style='width:140px; float:left;'><b style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"</b></div>");
				
				for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
					sb.append("<div id='"+preference+"_"+g+"' class='preference-button"+(Main.getProperties().genderPreferencesMap.get(g)==preference.getValue()?" selected":"")+"'>"+Util.capitaliseSentence(preference.getName())+"</div>");
				}
								
				sb.append("<p><br/>"
								+ "<span style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"s</span> have "
										+(g.getGenderName().isHasVagina()?"a [style.colourGood(vagina)]":"no [style.colourBad(vagina)]")+", "
										+(g.getGenderName().isHasPenis()?"a [style.colourGood(penis)]":"no [style.colourBad(penis)]")+", and "
										+ (g.getGenderName().isHasBreasts()?"[style.colourGood(breasts)]":"no [style.colourBad(breasts)]")+"."
								+ "</p>"
							+ "</div>"
						+ "</div>"
						+ "<hr/>");
				count++;
			}
		}
		
		sb.append(
				getGenderRepresentation()
				+"</div>");
		
		return sb.toString();
	}

	private static String getOrientationRepresentation() {
		float total=0;
		for(SexualOrientation o : SexualOrientation.values()) {
			total+=Main.getProperties().orientationPreferencesMap.get(o);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+Colour.ANDROGYNOUS.toWebHexString()+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			for(SexualOrientation o : SexualOrientation.values()) {
				sb.append("<div style='width:" + (Main.getProperties().orientationPreferencesMap.get(o)/total) * (100) + "%; height:12px; background:"
						+ o.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>");
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode ORIENTATION_PREFERENCE = new DialogueNode("Orientation preferences", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "These options will determine the sexual orientation encounter rates of random NPCs."
					+ " Note that the race and femininity of NPCs can have an influence on their orientation, and that some NPCs have pre-determined orientations, but your preferences will be taken into account wherever possible.</br>"
					+ "<b>A visual representation of the encounter chances can be seen in the bars at the bottom.</b>"
					+ " (The different shades of each orientation are solely for recognition in the bars, and don't mean anything other than that.)"
					+ "</div>"
		
					+ "<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.ANDROPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.AMBIPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.GYNEPHILIC));

			UtilText.nodeContentSB.append(getOrientationRepresentation() + "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getOrientationPreferencesPanel(SexualOrientation orient) {
		Colour colour = orient.getColour();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
					+ "<div style='width:140px; float:left;'><b style='color:"+colour.toWebHexString()+";'>" +Util.capitaliseSentence(orient.getName())+"</b></div>");
		
		for(SexualOrientationPreference preference : SexualOrientationPreference.values()) {
			sb.append("<div id='"+preference+"_"+orient+"' class='preference-button"+(Main.getProperties().orientationPreferencesMap.get(orient)==preference.getValue()?" selected":"")+"'>"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
		}
						
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}
	
	private static String getGenderRepresentation() {
		
		float total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+Colour.FEMININE.getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			int f=0, m=0, n=0;
			for(Gender g : Gender.values()) {
				switch(g.getType()) {
					case MASCULINE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+Colour.MASCULINE.getShades(8)[m] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						m++;
						break;
					case NEUTRAL:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+Colour.ANDROGYNOUS.getShades(8)[n] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						n++;
						break;
					case FEMININE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+Colour.FEMININE.getShades(8)[f] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						f++;
						break;
					default:
						break;
				}
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode AGE_PREFERENCE = new DialogueNode("Age preferences", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "These options will determine the age encounter rates of random NPCs, based on their femininity."
					+ " Some NPCs, such as demons and harpies, may appear to be younger than they actually are, but your preferences will be taken into account wherever possible.<br/>"
					+ "<b>A visual representation of the age chances can be seen in the bars at the bottom of each section.</b>"
					+ "</div>");
			
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else if (index == 1) {
				return new Response("Defaults", "Restore all age preferences to their default values.", AGE_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetAgePreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getAgePreferencesPanel(PronounType type) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		int i=AgeCategory.values().length-1;
		for(AgeCategory ageCat : AgeCategory.values()) {
			sb.append(
					"<div style='display:inline-block; margin:4px auto;width:100%;'>"
						+ "<div style='display:inline-block; margin:0 auto;'>"
							+ "<div style='width:140px; float:left;'><b style='color:"+type.getColour().getShades(AgeCategory.values().length)[i]+";'>" +Util.capitaliseSentence(ageCat.getName())+"</b></div>");
			
			for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
				sb.append(
						"<div id='"+type+"_"+preference+"_"+ageCat+"' class='preference-button"+(Main.getProperties().agePreferencesMap.get(type).get(ageCat)==preference.getValue()?" selected":"")+"'>"
								+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
							
			sb.append("</div>"
					+ "</div>"
					+ "<hr/>");
			i--;
		}
		
		sb.append(
				getAgeRepresentation(type)
				+"</div>");
		
		return sb.toString();
	}
	
	private static String getAgeRepresentation(PronounType type) {
		
		float total=0;
		for(AgeCategory ageCat : AgeCategory.values()) {
			total+=Main.getProperties().agePreferencesMap.get(type).get(ageCat);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+type.getColour().getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");

			int i=(AgeCategory.values().length*2)-1;
			for(AgeCategory ageCat : AgeCategory.values()) {
				if(Main.getProperties().agePreferencesMap.get(type).get(ageCat)>0) {
					sb.append("<div style='width:calc(" + (Main.getProperties().agePreferencesMap.get(type).get(ageCat)/total) * (100) + "% - 1px); height:12px;"
							+ " background:"+type.getColour().getShades(AgeCategory.values().length*2)[i] + "; float:left; border-radius: 2;'></div>");
					sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
				}
				i--;
				i--;
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode FURRY_PREFERENCE = new DialogueNode("Furry preferences", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
						+ "These options determine the amount of furry content that you'll encounter in the game."
						+ " The 'Human encounters' option determines what the chance is for random NPCs to be fully human."
						+ " <b>These options only affect random NPCs at the moment, but I'll do my best to add reduced-furry versions of each major NPC as well!</b>"
						
						+ "<br/><br/>[style.boldGood(Hover over the buttons to see what each option means!)]"
						
						+ "<br/><br/>Please note that mythological and demonic races, such as harpies and demons, are not affected by furry preferences."
					+ "</div>"
							
					+ "<span style='height:16px;width:800px;float:left;'></span>");
					
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					
						+"<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>"
							+ "<b style='color:"+Colour.RACE_HUMAN.toWebHexString()+"; float:left; width:100%; text-align:center;'>Human encounters</b>"
							+ "<div style='display:inline-block; padding-left:5%; width:100%;'>"
								+ "<div id='furry_preference_human_encounter_zero' class='square-button"+(Main.getProperties().humanEncountersLevel==0
									?" selected' style='border-color:"+Colour.RACE_HUMAN.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZero()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled()+"</div></div>")
							
								+ "<div id='furry_preference_human_encounter_one' class='square-button"+(Main.getProperties().humanEncountersLevel==1
									?" selected' style='border-color:"+Colour.RACE_HUMAN.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOne()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled()+"</div></div>")
								
								+ "<div id='furry_preference_human_encounter_two' class='square-button"+(Main.getProperties().humanEncountersLevel==2
									?" selected' style='border-color:"+Colour.RACE_HUMAN.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled()+"</div></div>")
								
								+ "<div id='furry_preference_human_encounter_three' class='square-button"+(Main.getProperties().humanEncountersLevel==3
									?" selected' style='border-color:"+Colour.RACE_HUMAN.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThree()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled()+"</div></div>")
								
								+ "<div id='furry_preference_human_encounter_four' class='square-button"+(Main.getProperties().humanEncountersLevel==4
									?" selected' style='border-color:"+Colour.RACE_HUMAN.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFour()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled()+"</div></div>")
							+"</div>"
						+ "</div>"
					
						+"<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>"
							+ "<b style='color:"+Colour.RACE_CENTAUR.toWebHexString()+"; float:left; width:100%; text-align:center;'>Taurs</b>"
							+ "<div style='display:inline-block; padding-left:5%; width:100%;'>"
								+ "<div id='taur_furry_preference_zero' class='square-button"+(Main.getProperties().taurFurryLevel==0
									?" selected' style='border-color:"+Colour.RACE_CENTAUR.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZero()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled()+"</div></div>")
							
								+ "<div id='taur_furry_preference_one' class='square-button"+(Main.getProperties().taurFurryLevel==1
									?" selected' style='border-color:"+Colour.RACE_CENTAUR.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOne()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled()+"</div></div>")
								
								+ "<div id='taur_furry_preference_two' class='square-button"+(Main.getProperties().taurFurryLevel==2
									?" selected' style='border-color:"+Colour.RACE_CENTAUR.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled()+"</div></div>")
								
								+ "<div id='taur_furry_preference_three' class='square-button"+(Main.getProperties().taurFurryLevel==3
									?" selected' style='border-color:"+Colour.RACE_CENTAUR.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThree()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled()+"</div></div>")
								
								+ "<div id='taur_furry_preference_four' class='square-button"+(Main.getProperties().taurFurryLevel==4
									?" selected' style='border-color:"+Colour.RACE_CENTAUR.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFour()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled()+"</div></div>")
							+"</div>"
						+ "</div>"
						
						+"<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+"; float:left; width:100%; text-align:center;'>Forced TF Racial Limits</b>"
							+ "<div style='display:inline-block; padding-left:5%; width:100%;'>"
								+ "<div id='forced_tf_limit_human' class='square-button"+(Main.getProperties().getForcedTFPreference()==FurryPreference.HUMAN
									?" selected' style='border-color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZero()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled()+"</div></div>")
							
								+ "<div id='forced_tf_limit_minimum' class='square-button"+(Main.getProperties().getForcedTFPreference()==FurryPreference.MINIMUM
									?" selected' style='border-color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOne()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled()+"</div></div>")
								
								+ "<div id='forced_tf_limit_reduced' class='square-button"+(Main.getProperties().getForcedTFPreference()==FurryPreference.REDUCED
									?" selected' style='border-color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled()+"</div></div>")
								
								+ "<div id='forced_tf_limit_normal' class='square-button"+(Main.getProperties().getForcedTFPreference()==FurryPreference.NORMAL
									?" selected' style='border-color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThree()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled()+"</div></div>")
								
								+ "<div id='forced_tf_limit_maximum' class='square-button"+(Main.getProperties().getForcedTFPreference()==FurryPreference.MAXIMUM
									?" selected' style='border-color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFour()+"</div></div>"
									:"'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled()+"</div></div>")
							+"</div>"
						+ "</div>"
							
					+ "</div>");
			
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
												+ "<div style='display:inline-block; margin:0 auto;'>"
													+"<div style='float:left; text-align:right; margin-right:16px;'>"
														+ "<b>Set all:</b>"
													+ "</div>"
													+ "<div id='furry_preference_human_all' class='preference-button'>"+FurryPreference.HUMAN.getName()+"</div>"
													+ "<div id='furry_preference_minimum_all' class='preference-button'>"+FurryPreference.MINIMUM.getName()+"</div>"
													+ "<div id='furry_preference_reduced_all' class='preference-button'>"+FurryPreference.REDUCED.getName()+"</div>"
													+ "<div id='furry_preference_normal_all' class='preference-button'>"+FurryPreference.NORMAL.getName()+"</div>"
													+ "<div id='furry_preference_maximum_all' class='preference-button'>"+FurryPreference.MAXIMUM.getName()+"</div>"
												+"</div>"
											+"</div>"
												
											+ "<div class='container-full-width' style='text-align: center;'>"
											+"<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(false)+";'>"
												+"<div class='container-full-width' style='text-align:center; width:calc(60% - 16px);background:transparent; margin:0 0 0 40%; padding:0;'>"
													+ "<b style='color:"+Colour.FEMININE.toWebHexString()+"; float:left; width:50%; text-align:center;'>Feminine:</b>"
													+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+"; float:left; width:50%; text-align:center;'>Masculine:</b>"
												+ "</div>"
											+ "</div>");

			int i=0;
			for(Subspecies subspecies : Subspecies.values()) {
				if(subspecies.isDisplayedInFurryPreferences()) {
					UtilText.nodeContentSB.append(getSubspeciesPreferencesPanel(subspecies, i%2==0));
					i++;
				}
			}
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getEntryBackgroundColour(boolean alternative) {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(alternative) {
				return "#d9d9d9";
			}
			return "#dddddd";
		} else {
			if(alternative) {
				return "#222222";
			}
			return "#1f1f1f";  
		}
	}
	
	private static String getSubspeciesPreferencesPanel(Subspecies s, boolean altColour) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(altColour)+";'>");
		
		sb.append("<div class='container-full-width' style='text-align:center; width:40%;background:transparent; margin:auto 0; padding:auto 0;'>"
					+"<b style='color:"+s.getColour(null).toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getName(null))+"</b>"
					+ "<div class='title-button no-select' id='SUBSPECIES_PREFERNCE_INFO_"+s+"' style='position:absolute; left:5%; right:auto; top:auto; bottom:auto;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
				+"</div>"
				+"<div class='container-full-width' style='text-align:center; width:60%;background:transparent; margin:0; padding:0;'>");
		
		for(FurryPreference preference : FurryPreference.values()) {
			sb.append("<div id='FEMININE_"+preference+"_"+s+"' class='square-button small"
						+(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(s)==preference
							?" selected' style='width:7%; border-color:"+Colour.FEMININE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
							:"' style='width:7%;'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
		}
		sb.append("<div style='width:10%; display:inline-block; position:relative; padding:0; margin:0;'>&nbsp;</div>");
		for(FurryPreference preference : FurryPreference.values()) {
			sb.append("<div id='MASCULINE_"+preference+"_"+s+"' class='square-button small"
						+(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(s)==preference
							?" selected' style='width:7%; border-color:"+Colour.MASCULINE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
							:"' style='width:7%;'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
		}
		
		sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}

	public static final DialogueNode UNIT_PREFERENCE = new DialogueNode("Unit preferences", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "These options determine the units used throughout the game."
							+ " Using auto detect will attempt to identify the correct settings for your system language."
							+ " <br/><b>Overriding any option disables auto detect.</b>"
							+ "</div>"

							+ "<span style='height:16px;width:800px;float:left;'></span>");

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"AUTO_LOCALE",
					Colour.BASE_BLUE_LIGHT,
					"Automatic",
					"When enabled, the system locale is used. Otherwise, the following options are applied.",
					Main.getProperties().hasValue(PropertyValue.autoLocale)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"METRIC_SIZES",
					Colour.BASE_BLUE_STEEL,
					"Metric sizes",
					"The game will use metres and centimetres instead of feet and inches.",
					Main.getProperties().hasValue(PropertyValue.metricSizes)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"METRIC_FLUIDS",
					Colour.BASE_BLUE_STEEL,
					"Metric fluids",
					"The game will use litres and millilitres instead of gallons and ounces.",
					Main.getProperties().hasValue(PropertyValue.metricFluids)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"METRIC_WEIGHTS",
					Colour.BASE_BLUE_STEEL,
					"Metric weights",
					"The game will use kilograms and grams instead of pounds and ounces.",
					Main.getProperties().hasValue(PropertyValue.metricWeights)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"TWENTYFOUR_HOUR_TIME",
					Colour.BASE_LILAC_LIGHT,
					"24 hour time",
					"The time will be displayed as 24 hours instead of AM/PM.",
					Main.getProperties().hasValue(PropertyValue.twentyFourHourTime)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"INTERNATIONAL_DATE",
					Colour.BASE_LILAC_LIGHT,
					"International date",
					"The abbreviated date will be displayed as day.month.year instead of month/day/year.",
					Main.getProperties().hasValue(PropertyValue.internationalDate)));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);

			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CONTENT_PREFERENCE = new DialogueNode("Content Options", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.GENERIC_GOOD, "Autosave Frequency", "Choose how often want the game to autosave when you transition from one map to another."));
			for(int i=2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='AUTOSAVE_FREQUENCY_"+i+"' class='normal-button"+(Main.getProperties().autoSaveFrequency==i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().autoSaveFrequency==i
							?"[style.boldGood("
							:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.autoSaveLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
								"ENCHANTMENT_LIMITS",
								Colour.GENERIC_ARCANE,
								"Enchantment Capacity",
								"Toggle the 'enchantment capacity' mechanic, which restricts how many enchanted items you can wear. This is on by default, and you will potentially break the balance of the game's combat by turning it off.",
								Main.getProperties().hasValue(PropertyValue.enchantmentLimits)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
								"ARTWORK",
								Colour.BASE_BLUE_LIGHT,
								"Artwork",
								"Enables artwork to be displayed in characters' information screens.",
								Main.getProperties().hasValue(PropertyValue.artwork)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("THUMBNAIL",
								Colour.BASE_BLUE_STEEL,
								"Thumbnails",
								"Enables tooltips containing thumbnail images of the character.",
								Main.getProperties().hasValue(PropertyValue.thumbnail)));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.BASE_AQUA, "Preferred Artist", "Which artist's work is used by default."));
			List<Artist> artists = new ArrayList<>(Artwork.allArtists);
			Collections.reverse(artists);// So that they're in alphabetical order
			for(Artist artist : artists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append(
							(Main.getProperties().preferredArtist.equals(artist.getFolderName())
									?"<div id='ARTIST_"+artist.getFolderName()+"' class='normal-button selected' style='width:75%; text-align:center; float:right;'>"
									+ "<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b>"
									+ "</div>"
									:"<div id='ARTIST_"+artist.getFolderName()+"' class='normal-button' style='width:75%; text-align:center; float:right;'>"
									+ "[style.boldDisabled("+artist.getName()+")]"
									+ "</div>"));
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"AUTO_SEX_CLOTHING_MANAGEMENT",
							Colour.BASE_BLUE_STEEL,
							"Post-sex clothing replacement",
							"Enables equipped clothing to be automatically pulled back into their pre-sex states after sex scenes.",
							Main.getProperties().hasValue(PropertyValue.autoSexClothingManagement)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"AGE",
							Colour.AGE_TWENTIES,
							"Age",
							"This enables descriptions of the age that characters appear to be.",
							Main.getProperties().hasValue(PropertyValue.ageContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"NON_CON",
							Colour.BASE_CRIMSON,
							"Non-consent",
							"This enables the 'resist' pace in sex scenes, which contains some more extreme non-consensual descriptions.",
							Main.getProperties().hasValue(PropertyValue.nonConContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"SADISTIC_SEX",
							Colour.BASE_RED,
							"Sadistic sex",
							"This unlocks 'sadistic' sex actions, such as choking, slapping, and spitting on partners in sex.",
							Main.getProperties().hasValue(PropertyValue.sadisticSexContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"SILLY",
							Colour.GENERIC_GOOD,
							"Silly mode",
							"This enables funny flavour text throughout the game.",
							Main.getProperties().hasValue(PropertyValue.sillyMode)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"OPPORTUNISTIC_ATTACKERS",
							Colour.BASE_CRIMSON,
							"Opportunistic attackers",
							"This makes random attacks more likely when you're high on lust, low on health, covered in fluids, exposed, or drunk.",
							Main.game.isOpportunisticAttackersEnabled()));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"BYPASS_SEX_ACTIONS",
							Colour.BASE_PINK,
							"Sex action bypass",
							"If disabled, action requirements during sex may no longer be bypassed. (i.e. All 'Corruptive' actions will be unavailable.)",
							Main.getProperties().hasValue(PropertyValue.bypassSexActions)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"VOLUNTARY_NTR",
							Colour.GENERIC_MINOR_BAD,
							"Voluntary NTR",
							"When enabled, you will get the option to offer certain enemies sex with your companions as a way to avoid combat.",
							Main.getProperties().hasValue(PropertyValue.voluntaryNTR)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"INVOLUNTARY_NTR",
							Colour.GENERIC_BAD,
							"Involuntary NTR",
							"When enabled, enemies might choose to only have sex with your companion after beating your party in combat."
									+ " When disabled, all post-combat-loss sex scenes will involve you.",
							Main.getProperties().hasValue(PropertyValue.involuntaryNTR)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"INCEST",
							Colour.BASE_ROSE,
							"Incest",
							"This will enable sexual actions with all of your blood-relatives.",
							Main.getProperties().hasValue(PropertyValue.incestContent)));
				
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"LACTATION",
							Colour.BASE_YELLOW_LIGHT,
							"Lactation",
							"This enables lactation content.",
							Main.getProperties().hasValue(PropertyValue.lactationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"CUM_REGENERATION",
							Colour.CUM,
							"Cum Regeneration",
							"This enables cum regeneration related content, such as decreasing quantity for multiple orgasms in one session and the full balls status effect."
							+ "<br>When disabled, balls will always be treated as full, but without any negative effects.",
							Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"URETHRAL",
							Colour.BASE_PINK_DEEP,
							"Urethral content",
							"This enables urethral transformations and penetrations.",
							Main.getProperties().hasValue(PropertyValue.urethralContent)));
				
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"NIPPLE_PEN",
							Colour.BASE_PINK_DEEP,
							"Nipple Penetrations",
							"This enables nipple-penetration transformations and sex actions.",
							Main.getProperties().hasValue(PropertyValue.nipplePenContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"ANAL",
							Colour.BASE_ORANGE,
							"Anal Content",
							"When disabled, removes all anal-related actions from being available during sex.",
							Main.getProperties().hasValue(PropertyValue.analContent)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"FOOT",
							Colour.BASE_TAN,
							"Foot Content",
							"When disabled, removes all foot-related actions from being available during sex.",
							Main.getProperties().hasValue(PropertyValue.footContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"FUTA_BALLS",
							Colour.BASE_PINK,
							"Futanari Testicles",
							"When enabled, futanari NPCs will be able to have external testicles. When disabled, they are locked to always being internal.",
							Main.getProperties().hasValue(PropertyValue.futanariTesticles)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"CLOACA",
							Colour.BASE_PINK_LIGHT,
							"Bipedal Cloacas",
							"When enabled, certain bipedal races (such as harpies and alligator-morphs) will have cloacas."
									+ " When disabled, all bipeds with cloacas will be treated as having a regular genitalia configuration."
									+ " Some special races, such as lamia, always have cloacas, and are not affected by this.",
							Main.getProperties().hasValue(PropertyValue.bipedalCloaca)));
				

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.NIPPLES, "Multi-breasts", "Choose how you want the game to display multi-breasts."));
			for(int i=2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='MULTI_BREAST_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().multiBreasts==i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
							+(Main.getProperties().multiBreasts==i
								?(i==0?"[style.boldBad(":"[style.boldGood(")
								:"[style.colourDisabled(")
							+com.lilithsthrone.game.Properties.multiBreastsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.NIPPLES_CROTCH, "Crotch-boobs & Udders", "Choose how you want the game to handle udders and crotch-boobs."));
			for(int i=2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='UDDER_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().udders==i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().udders==i
							?(i==0?"[style.boldBad(":"[style.boldGood(")
							:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.uddersLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"HAIR_FACIAL",
							Colour.BASE_LILAC_LIGHT,
							"Facial hair",
							"This enables facial hair descriptions and content.",
							Main.getProperties().hasValue(PropertyValue.facialHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"HAIR_PUBIC",
							Colour.BASE_LILAC,
							"Pubic hair",
							"This enables pubic hair descriptions and content.",
							Main.getProperties().hasValue(PropertyValue.pubicHairContent)));
				
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
						"HAIR_BODY",
						Colour.BASE_PURPLE,
						"Underarm hair",
						"This enables underarm hair descriptions and content.",
						Main.getProperties().hasValue(PropertyValue.bodyHairContent)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
						"HAIR_ASS",
						Colour.BASE_PURPLE_DARK,
						"Ass hair",
						"This enables ass hair descriptions and content.",
						Main.getProperties().hasValue(PropertyValue.assHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"FEMININE_BEARD",
							Colour.BASE_BLUE_STEEL,
							"Feminine Beards",
							"This enables feminine characters to grow beards.",
							Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)));
				
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"FORCED_TF",
							Colour.TRANSFORMATION_GENERIC,
							"Forced TF",
							"This sets the amount of NPCs spawning with the '"+Fetish.FETISH_TRANSFORMATION_GIVING.getName(null)+"' fetish, which causes them to forcibly transform you after beating you in combat.",
							Main.getProperties().forcedTFPercentage+"%",
							Main.getProperties().forcedTFPercentage,
							0,
							100));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"SPITTING_ENABLED",
					Colour.BASE_BLUE,
					"Rejecting TF potions",
					"Forced TF potions may be spat out if this is enabled.",
					!Main.game.isSpittingDisabled()));

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.BASE_GREEN, "Forced TF Gender Tendency", "This allows you to override NPC tastes when a forced transformation will alter your gender presentation."));
			UtilText.nodeContentSB.append((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.NEUTRAL
												?"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.NEUTRAL+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>"
													+ ForcedTFTendency.NEUTRAL.getName()
													+ "</div>"
												:"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.NEUTRAL+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedTFTendency.NEUTRAL.getName()+")]"
													+ "</div>")	
											+ (Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
												?"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.FEMININE.toWebHexString()+";'>"
													+ ForcedTFTendency.FEMININE.getName()
													+ "</div>"
												:"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedTFTendency.FEMININE.getName()+")]"
													+ "</div>")
											+(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY
												?"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE_HEAVY+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.FEMININE_PLUS.toWebHexString()+";'>"
													+ ForcedTFTendency.FEMININE_HEAVY.getName()
													+ "</div>"
												:"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE_HEAVY+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedTFTendency.FEMININE_HEAVY.getName()+")]"
													+ "</div>")
											+(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY
												?"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE_HEAVY+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.MASCULINE_PLUS.toWebHexString()+";'>"
													+ ForcedTFTendency.MASCULINE_HEAVY.getName()
													+ "</div>"
												:"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE_HEAVY+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedTFTendency.MASCULINE_HEAVY.getName()+")]"
													+ "</div>")
											+(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
												?"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.MASCULINE.toWebHexString()+";'>"
													+ ForcedTFTendency.MASCULINE.getName()
													+ "</div>"
												:"<div id='FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedTFTendency.MASCULINE.getName()+")]"
													+ "</div>"));
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"FORCED_FETISH",
							Colour.FETISH,
							"Forced Fetishes",
							"This sets the amount of NPCs spawning with the '"+Fetish.FETISH_KINK_GIVING.getName(null)+"' fetish, which causes them to try and forcibly give you fetishes after beating you in combat.",
							Main.getProperties().forcedFetishPercentage+"%",
							Main.getProperties().forcedFetishPercentage,
							0,
							100));
					

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(Colour.FETISH, "Forced Fetish Tendency",
					"This allows you to override NPC tastes and control the tendency for forced fetishes to be for topping or bottoming."));
			UtilText.nodeContentSB.append((Main.getProperties().getForcedFetishTendency()==ForcedFetishTendency.NEUTRAL
												?"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.NEUTRAL+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>"
													+ ForcedFetishTendency.NEUTRAL.getName()
													+ "</div>"
												:"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.NEUTRAL+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedFetishTendency.NEUTRAL.getName()+")]"
													+ "</div>")	
										+(Main.getProperties().getForcedFetishTendency()==ForcedFetishTendency.BOTTOM
												?"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>"
													+ ForcedFetishTendency.BOTTOM.getName()
													+ "</div>"
												:"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedFetishTendency.BOTTOM.getName()+")]"
													+ "</div>")
										+(Main.getProperties().getForcedFetishTendency()==ForcedFetishTendency.BOTTOM_HEAVY
												?"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM_HEAVY+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.BASE_PINK.toWebHexString()+";'>"
													+ ForcedFetishTendency.BOTTOM_HEAVY.getName()
													+ "</div>"
												:"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM_HEAVY+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedFetishTendency.BOTTOM_HEAVY.getName()+")]"
													+ "</div>")
										+(Main.getProperties().getForcedFetishTendency()==ForcedFetishTendency.TOP_HEAVY
												?"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP_HEAVY+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.BASE_PURPLE.toWebHexString()+";'>"
													+ ForcedFetishTendency.TOP_HEAVY.getName()
													+ "</div>"
												:"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP_HEAVY+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedFetishTendency.TOP_HEAVY.getName()+")]"
													+ "</div>")
										+(Main.getProperties().getForcedFetishTendency()==ForcedFetishTendency.TOP
												?"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+Colour.BASE_PURPLE_LIGHT.toWebHexString()+";'>"
													+ ForcedFetishTendency.TOP.getName()
													+ "</div>"
												:"<div id='FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
													+ "[style.colourDisabled("+ForcedFetishTendency.TOP.getName()+")]"
													+ "</div>"));
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
						"FURRY_TAIL_PENETRATION",
						Colour.BASE_MAGENTA,
						"Furry tail penetrations",
						"This enables furry tails to engage in penetrative actions in sex.",
						Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent)));
					
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
							"INFLATION_CONTENT",
							Colour.CUM,
							"Cum Inflation",
							"This enables cum inflation mechanics.",
							Main.getProperties().hasValue(PropertyValue.inflationContent)));
				

			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"PREGNANCY_BREAST_GROWTH",
							Colour.BASE_PINK,
							"Average Pregnancy Breast Growth",
							"Set the <b>average</b> cup size growth that characters' will gain from each pregnancy. Actual breast growth will be within "+Util.intToString(Main.getProperties().pregnancyBreastGrowthVariance)+" sizes of this value.",
							Main.getProperties().pregnancyBreastGrowth==0
								?"[style.boldDisabled(Disabled)]"
								:Main.getProperties().pregnancyBreastGrowth+" cup"+(Main.getProperties().pregnancyBreastGrowth!=1?"s":""),
							Main.getProperties().pregnancyBreastGrowth,
							0,
							10));
					
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"PREGNANCY_BREAST_GROWTH_LIMIT",
							Colour.BASE_PINK_LIGHT,
							"Pregnancy Breast Growth Limit",
							"Set the maximum limit of cup size that characters' breasts will grow to from pregnancies.",
							CupSize.getCupSizeFromInt(Main.getProperties().pregnancyBreastGrowthLimit).getCupSizeName()+"-cup",
							Main.getProperties().pregnancyBreastGrowthLimit,
							0,
							100));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"PREGNANCY_LACTATION",
							Colour.BASE_YELLOW,
							"Average Pregnancy Lactation",
							"Set the <b>average</b> increase in lactation that characters will gain as a result of each pregnancy. Actual lactation increase will be within "
									+Units.fluid(Main.getProperties().pregnancyLactationIncreaseVariance)+" of this value.",
							Main.getProperties().pregnancyLactationIncrease==0
								?"[style.boldDisabled(Disabled)]"
								:Units.fluid(Main.getProperties().pregnancyLactationIncrease),
							Main.getProperties().pregnancyLactationIncrease,
							0,
							1000));
					
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"PREGNANCY_LACTATION_LIMIT",
							Colour.BASE_YELLOW_LIGHT,
							"Pregnancy Lactation Limit",
							"Set the maximum limit of lactation that characters will gain from pregnancies.",
							Units.fluid(Main.getProperties().pregnancyLactationLimit, Units.ValueType.PRECISE, Units.UnitType.SHORT),
							Main.getProperties().pregnancyLactationLimit,
							0,
							Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));

			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"BREAST_SIZE_PREFERENCE",
							Colour.NIPPLES,
							"Cup Size Preference",
							"Affects randomly-generated NPCs' cup sizes (will not be reduced to below AA-cup).",
							(Main.getProperties().breastSizePreference>=0?"+":"") + Main.getProperties().breastSizePreference,
							Main.getProperties().breastSizePreference,
							-20,
							20));

			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
							"PENIS_SIZE_PREFERENCE",
							Colour.PENIS,
							"Penis Size Preference",
							"Affects randomly-generated NPCs' penis sizes (will not be reduced to below "+Units.size(8)+").",
							(Main.getProperties().penisSizePreference>=0?"+":"") + Units.size(Main.getProperties().penisSizePreference, ValueType.PRECISE, UnitType.SHORT),
							Main.getProperties().penisSizePreference,
							-20,
							20));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	

	/**
	 * TO be followed by: </div></div>
	 */
	private static String getCustomContentPreferenceDivStart(Colour colour, String title, String description) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		if(enabled) {
			contentSB.append(
					"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
							+ "[style.boldGood(ON)]"
						+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(OFF)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='"+id+"_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(ON)]"
					+ "</div>"
					+"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.boldBad(OFF)]"
					+ "</div>");
		}

		contentSB.append("</div>"
				+ "</div>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceVariableDiv(String id, Colour colour, String title, String description, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
				+ "</div>"
				+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
					+ "<b>"+valueDisplay+"</b>"
				+ "</div>"
				+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}
	
	
	public static final DialogueNode CREDITS = new DialogueNode("Credits", "", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Thank you for playing "+Main.GAME_NAME+", I hope you enjoy it just as much as I do making it!"
						+ " Thank you so much to all of you who offer financial support! Thanks to you, I'm able to spend more time working on "+Main.GAME_NAME+", and I promise that I'll make this game the very best that I can!"
					+ "</p>"
					+"<p style='text-align:center;'>"
						+ Main.GAME_NAME+" has been created by:<br/>"
						+ "<b style='color:#9b78fa;'>Innoxia</b>"
						+ "<br/><br/>"
						+ "Artists whose character art can be found in the game:<br/>");
			
			for(Artist artist : Artwork.allArtists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b><br/>");
				}
			}	

			UtilText.nodeContentSB.append("<br/>"
					+ "Contributors:</br>" // In alphabetical order:
					+ "<b style='color:#21bfc5;'>DJ Addi</b></br>"
					+ "<b style='color:#21bfc5;'>DSG</b></br>"
					+ "<b style='color:#21bfc5;'>Irbynx</b></br>"
					+ "<b style='color:#21bfc5;'>Nnxx</b></br>"
					+ "<b style='color:#21bfc5;'>Norin</b></br>"
					+ "<b style='color:#21bfc5;'>Phlarx</b></br>"
					+ "<b style='color:#21bfc5;'>Pimgd</b></br>"
					+ "<b style='color:#21bfc5;'>Rfpnj</b></br>"
					+ "<b style='color:#21bfc5;'>Tukaima</b></br>");
			
			UtilText.nodeContentSB.append("<br/>"
						+ "Special thanks to:<br/>"
						+ "<b>Sensei</b>,<br/>"
						+ "<b style='color:#fa0063;'>loveless</b>, <b style='color:#c790b2;'>Blue999</b>, and <b style='color:#ec9538;'>DesuDemona</b><br/>"
						+ "<b style='color:#21bec4;'>Github & wiki contributors</b><br/>"
						+ "<b style='color:#e06e5f;'>Everyone who's financially supported me</b>,<br/>"
						+ "<b>Bug reporters</b>,<br/>"
						+ "and<br/>"
						+ "<b>Everyone for playing Lilith's Throne!</b>"
					+ "</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>Legendary Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					if(cs.getName().equals("Anonymous")) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_UNCOMMON.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_RARE.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>?</b> ");
					} else {
						for(int i=0; i<cs.getUncommonCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getRareCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getEpicCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getLegendaryCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getLegendaryCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>&#9679</b> ");
						}
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Colour.RARITY_EPIC.toWebHexString()+";'>Epic Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					for(int i=0; i<cs.getUncommonCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getRareCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
					}
					for(int i=0; i<cs.getEpicCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Subspecies.DEMON.getColour(null).toWebHexString()+";'>Demonic backers</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()==0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			} else {
				int i=1;
				for(Artist artist : Artwork.allArtists) {
					for(ArtistWebsite website : artist.getWebsites()) {
						if(index==i) {
							return new ResponseEffectsOnly(website.getName(), "Opens the page:<br/><br/><i>"+website.getURL()+"</i><br/><br/><b>Externally in your default browser.</b>"){
								@Override
								public void effects() {
									Util.openLinkInDefaultBrowser(website.getURL());
								}
							};
						}
						i++;
					}
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
}
