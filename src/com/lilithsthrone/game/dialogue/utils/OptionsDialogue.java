package com.lilithsthrone.game.dialogue.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.KeyboardAction;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.gender.AndrogynousIdentification;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.86
 * @author Innoxia
 */
public class OptionsDialogue {

	private static boolean confirmNewGame = false;
	
	public static final DialogueNodeOld MENU = new DialogueNodeOld("Menu", "Menu", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "";
		}
		
		@Override
		public String getContent(){
			return "<h1 class='special-text' style='font-size:48px; line-height:52px; text-align:center;'>Lilith's Throne</h1>"
					+ "<h5 class='special-text' style='text-align:center;'>"
						+ "Created by Innoxia"
					+ "</h5>"
					+ "</br>"
					+ "<p>"
						+ "This game is a text-based erotic RPG, and contains a lot of graphic sexual content. You must agree to the game's disclaimer before playing this game!"
					+ "</p>"
					+"<p>"
						+ "You can visit my blog (https://lilithsthrone.blogspot.co.uk) to check on development progress (use the 'Blog' button below to open the blog in your default browser)."
					+ "</p>"
					+ getJavaVersionInformation()
					+ "</br>"
					+ (Main.game.isStarted() || Main.getProperties().name.isEmpty()
							?""
							:"<h4 style='text-align:center;'>Last save:</h4>"
								+ "<h5 style='color:" + Main.getProperties().nameColour + ";text-align:center;'>" + Main.getProperties().name + "</h5>"
								+ "<p style='text-align:center;'><b>Level " + Main.getProperties().level + " " + Util.capitaliseSentence(Main.getProperties().race) + "</b></p>"
								+ "<p style='text-align:center;'>" + UtilText.formatAsMoney(Main.getProperties().money, "span") + "</p>"
								+ "<p style='text-align:center;'>Quest: " + Util.capitaliseSentence(Main.getProperties().quest) + "</p>");
		}
		
		@Override
		public Response getResponse(int index) {
			
			 if (index == 1) {
				 if(confirmNewGame || !Main.game.isStarted()) {
					 
					return new ResponseEffectsOnly(
							(!Main.game.isStarted()?"New Game":"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Confirm</b>"), "Start a new game.</br></br><b>Remember to save your game first!</b>"){
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
					 return new Response("New Game", "Start a new game.</br></br><b>Remember to save your game first!</b>", MENU){
							@Override
							public void effects() {
								confirmNewGame = true;
							}
						};
				 }
				
			} else if (index == 2) {
				if(Main.game.isInCombat()) {
					return new Response("Save/Load", "You cannot save during combat! Sorry! I'm trying to fix this so that you'll be able to!", null);
					
				} else if(Main.game.isInSex()) {
					return new Response("Save/Load", "You cannot save during sex! Sorry! I'm trying to fix this so that you'll be able to!", null);
							
				} else {
					return new Response("Save/Load", "Open the save/load game window.", SAVE_LOAD){
						@Override
						public void effects() {
							loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
							confirmNewGame=false;
						}
					};
				}
				
			} else if (index == 3) {
				return new Response("Options", "Open the options page.", OPTIONS){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};

			} else if (index == 4) {
				return new Response("Content Options", "Set your preferred content settings.", CONTENT_PREFERENCE){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};
			
			} else if (index == 5) {
				return new ResponseEffectsOnly("Quit", "Quits your current game and closes the program.</br></br><b>Remember to save your game first!</b>"){
					@Override
					public void effects() {
						Main.primaryStage.close();
						confirmNewGame=false;
						
					}
				};
				
			} else if (index == 6) {
				return new Response("Disclaimer", "View the game's disclaimer.", DISCLAIMER){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
				
			} else if (index == 7) {
				return new Response("Credits", "View the game's credits screen.", CREDITS);
				
			} else if (index == 8) {
				return new ResponseEffectsOnly("Blog", "Opens the page:</br></br><i>https://lilithsthrone.blogspot.co.uk/</i></br></br><b>Externally in your default browser.</b>"){
					@Override
					public void effects() {
						try {
							Desktop.getDesktop().browse(new URI("https://lilithsthrone.blogspot.co.uk/"));
						} catch (IOException | URISyntaxException e) {
							e.printStackTrace();
						}	
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
					return new ResponseEffectsOnly("Resume", "Continue playing from your last save."){
						@Override
						public void effects() {
							Main.loadGame(Main.getProperties().lastSaveLocation);
							confirmNewGame=false;
							
						}
					};
				}
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	private static String getJavaVersionInformation() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>"
					+ "Your java version: "+System.getProperty("java.version")
				+" | ");
		
		String[] version = System.getProperty("java.version").split("\\.");
		if(version.length>=2) {
			if(Integer.valueOf(version[1])<8) {
				sb.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
				
			} else {
				if(version.length==3){
					String[] versionMinor = version[2].split("_");
					if(versionMinor.length>=2)
						if(Integer.valueOf(versionMinor[1])<131) {
							sb.append("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
							
						} else {
							sb.append("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
						}
				} else {
					sb.append("This game needs at least v1.8.0_131 to work correctly!");
				}
			}
		}
		sb.append("</p>");
		
		return sb.toString();
	}

	public static String loadConfirmationName = "", overwriteConfirmationName = "", deleteConfirmationName = "";
	public static final DialogueNodeOld SAVE_LOAD = new DialogueNodeOld("Save game files", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append("<p'>"
					+ "<b>Please Note:</b></br>"
					+ "1. Only standard characters (letters and numbers) will work for save file names.</br>"
					+ "2. The 'AutoSave' file is automatically overwritten every time you move between maps.</br>"
					+ "3. The 'QuickSave' file is automatically overwritten every time you quick save (default keybind is F5).</br>"
					+ "<b>You cannot save during combat or sex due to some bugs that I need to fix!</b>"
					+ "</p>"
					+ "<p>"
					+ "<table align='center'>"
					+ "<tr>"
					+ "<th></th>"
					+ "<th>Name</th>"
					+ "<th></th>"
					+ "<th></th>"
					+ "<th></th>"
					+ "</tr>");
			
			Main.getSavedGames().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSavedGames()){
				try {
					saveLoadSB.append(getSaveLoadRow("<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+getFileTime(f)+"</span>", f.getName()));
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}

			if(Main.game.isStarted())
				saveLoadSB.append(getSaveLoadRow(null, null));

			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");

			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Confirmations: ",
						"Toggle confirmations being shown when you click to load, overwrite, or delete a saved game."
							+ " When turned on, it will take two clicks to apply any button press."
							+ " When turned off, it will only take one click.",
						SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().overwriteWarning
								?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
								:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().overwriteWarning = !Main.getProperties().overwriteWarning;
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	private static String getFileTime(File file) throws IOException {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - hh:mm");
	    return dateFormat.format(file.lastModified());
	}
	
	private static String getSaveLoadRow(String date, String name) {
		if(name!=null){
			String baseName = name.substring(0, name.lastIndexOf('.'));
			return "<tr>"
					+ "<td>"
						+ date
					+ "</td>"
					+ "<td style='min-width:200px;'>"
						+ baseName
					+ "</td>"
					+ "<td>"
						+ (name.equals(loadConfirmationName)
								?"<div class='saveLoadButton' id='load_saved_" + baseName + "' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Confirm</div>"
								:"<div class='saveLoadButton' id='load_saved_" + baseName + "' style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Load</div>")
					+ "</td>"
					+ "<td>"
						+ (name.equals(overwriteConfirmationName)
							?"<div class='saveLoadButton' id='overwrite_saved_" + baseName + "' style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Confirm</div>"
							:"<div class='saveLoadButton' id='overwrite_saved_" + baseName + "' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Overwrite</div>")
					+ "</td>"
					+ "<td>"
						+ (name.equals(deleteConfirmationName)
							?"<div class='deleteSaveButton' id='delete_saved_" + baseName + "' style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Confirm</div>"
							:"<div class='deleteSaveButton' id='delete_saved_" + baseName + "' style='color:"+Colour.CLOTHING_WHITE.toWebHexString()+";'>Delete</div>")
					+ "</td>"
					+ "</tr>";
			
		} else {
			return "<tr>"
					+ "<td>"
					+ "-"
					+ "</td>"
					+ "<td style='min-width:200px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' value='"
						+ "New Save"
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td>"
						+ "<div class='saveLoadButton' id='new_saved' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Save</div>"
					+ "</td>"
					+ "<td>"
						+ "<div class='saveLoadButton disabled'>-</div>"
					+ "</td>"
					+ "<td>"
						+ "<div class='saveLoadButton disabled'>-</div>"
					+ "</td>"
					+ "</tr>";
			
		}
	}
	
	
	
	public static final DialogueNodeOld OPTIONS = new DialogueNodeOld("Options", "Options", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent(){
			return "<p>"
					+ "<b>Light/Dark theme:</b>"
					+ "</br>This switches the main display between a light and dark theme. (Work in progress!)"
					+ "</p>"
					
					+"<p>"
					+ "<b>Font-size:</b>"
					+ "</br>This cycles the game's base font size. This currently only affects the size of the text in the main dialogue, but in the future I'll expand it to include every display element."
					+ "</p>"

					+"<p>"
					+ "<b>Fade-in:</b>"
					+ "</br>This option is responsible for fading in the main part of the text each time a new scene is displayed. It is off by default, as having it on may cause some annoying lag in inventory screens."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Keybinds", "Open the keybinds page, where you can customise all the game's key bindings.", KEYBINDS);
				
			} else if (index == 2) {

				if (Main.getProperties().lightTheme) {
					return new Response("Dark theme", "Switch the theme to the dark variant.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
						};
				} else {
					return new Response("Light theme (WIP)", "Switch the theme to the light variant.</br></br><b>This is still a work in progress...</b>.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
					};
				}

			} else if (index == 3) {
				return new Response("Font-size: " + (Main.getProperties().fontSize == Game.FONT_SIZE_NORMAL ? "<b>Normal</b>" : (Main.getProperties().fontSize == Game.FONT_SIZE_LARGE ? "<b>Large</b>" : "<b>Huge</b>")),
						"Cycle the font size between:</br>" + (Main.getProperties().fontSize == Game.FONT_SIZE_NORMAL ? "<b>Normal</b>" : "Normal") + " | " + (Main.getProperties().fontSize == Game.FONT_SIZE_LARGE ? "<b>Large</b>" : "Large") + " | "
								+ (Main.getProperties().fontSize == Game.FONT_SIZE_HUGE ? "<b>Huge</b>" : "Huge") + "</br></br>" + "<b>This is still a work in progress - expect a better version soon!</b>",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize <= Game.FONT_SIZE_NORMAL)
							Main.getProperties().fontSize = Game.FONT_SIZE_LARGE;
						else if (Main.getProperties().fontSize < Game.FONT_SIZE_HUGE)
							Main.getProperties().fontSize = Game.FONT_SIZE_HUGE;
						else
							Main.getProperties().fontSize = Game.FONT_SIZE_NORMAL;

						Main.saveProperties();
						
					}
				};
			
			} else if (index == 4) {
				return new Response("Fade-in: "+(Main.getProperties().fadeInText?"[style.boldGood(ON)]":"[style.boldBad(OFF)]"), "Toggle the fading in of the game's text. If turned on, it may cause some minor lag in inventory screens.", OPTIONS){
					@Override
					public void effects() {
						Main.getProperties().fadeInText = !Main.getProperties().fadeInText;

						Main.saveProperties();
					}
				};
				
			} else if (index == 5) {
				return new Response("Gender pronouns", "Customise all gender pronouns and names.", OPTIONS_PRONOUNS);
				
			} else if (index == 6) {
				if(Main.game.isStarted()) {
					return new ResponseEffectsOnly("Export character", "Exports your character file to the 'data/characters/' folder."){
						@Override
						public void effects() {
							CharacterUtils.saveCharacterAsXML(Main.game.getPlayer());
							Main.game.flashMessage(Colour.GENERIC_GOOD, "Character exported!");
							
						}
					};
				} else {
					return new Response("Export character", "You'll need to start a game first!", null);
				}
			
			} else if (index == 7) {
				return new Response("Patch notes", "View the patch notes for this version.", PATCH_NOTES);
			
			} else if (index == 8) {
				return new Response("Gender preferences", "Set your preferred gender encounter rates.", GENDER_PREFERENCE);
			
			} else if (index == 9) {
				return new Response("Furry preferences", "Set your preferred transformation encounter rates.", FURRY_PREFERENCE);
			
			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	public static final DialogueNodeOld KEYBINDS = new DialogueNodeOld("Options", "Options", true) {
		private static final long serialVersionUID = 1L;

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
					+ getKeybindTableRow(KeyboardAction.CHARACTERS)
					+ getKeybindTableRow(KeyboardAction.ZOOM)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_EAST)
					+ getKeybindTableRow(KeyboardAction.MOVE_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_WEST)
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
					+ "</table>"
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Default keys", "Resets all keybinds to their default values.", KEYBINDS){
					@Override
					public void effects() {
						for (KeyboardAction ka : KeyboardAction.values()) {
							Main.getProperties().hotkeyMapPrimary.put(ka, ka.getPrimaryDefault());
							Main.getProperties().hotkeyMapSecondary.put(ka, ka.getSecondaryDefault());
						}
						Main.saveProperties();
						
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
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
	
	public static final DialogueNodeOld OPTIONS_PRONOUNS = new DialogueNodeOld("Options", "Options", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "<h5 style='text-align:center;'>Global gender names:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
							+ "<th>Body Parts</th>"
								+ "<th style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine</th>"
								+ "<th style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Androgynous</th>"
								+ "<th style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine</th>"
							+ "</tr>");
			
			for(GenderNames gn : GenderNames.values()) {
				UtilText.nodeContentSB.append(getGenderNameTableRow(gn));
			}
							
			UtilText.nodeContentSB.append("</table>"
					+ "</p>"
					
					+ "<p>"
						+ "<h5 style='text-align:center;'>Player-specific pronouns:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
								+ "<th style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine</th>"
								+ "<th>Pronoun</th><th style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine</th>"
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
					+ "<b style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine:</b> Treated as <b style='color:"+Colour.FEMININE.toWebHexString()+";'>feminine</b>.</br>"
					+ "<b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Clothing feminine:</b> Treated according to clothing femininity."
							+ " If clothing is neutral, treated as <b style='color:"+Colour.FEMININE.toWebHexString()+";'>feminine</b>.</br>"
					+ "<b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>Clothing masculine:</b> Treated according to clothing femininity."
							+ " If clothing is neutral, treated as <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>masculine</b>.</br>"
					+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine:</b> Treated as <b style='color:"+Colour.MASCULINE.toWebHexString()+";'>masculine</b>.</br>"
					+ "</p>");
							
			return UtilText.nodeContentSB.toString();	
		}
		
		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Save", "Save all the pronouns that are currently displayed."){
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
				
			}else {
				return null;
			}
		}
		
		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
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
						+ Main.getProperties().genderNameMale.get(name)
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_ANDROGYNOUS_" + name + "' value='"
						+ Main.getProperties().genderNameNeutral.get(name)
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_FEMININE_" + name + "' value='"
						+ Main.getProperties().genderNameFemale.get(name)
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
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='masculine_" + pronoun + "' value='"
					+ Main.getProperties().genderPronounMale.get(pronoun)
					+ "'>"
					+ "</form>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='feminine_" + pronoun + "' value='"
					+ Main.getProperties().genderPronounFemale.get(pronoun)
					+ "'>"
					+ "</form>"
				+ "</td>"
				+ "</tr>";
	}
	
	
	public static final DialogueNodeOld PATCH_NOTES = new DialogueNodeOld("Patch Notes", "Patch notes", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent(){
			return Main.patchNotes;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	public static final DialogueNodeOld DISCLAIMER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent(){
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	
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
				sb.append("<div style='width:" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "%; height:12px; background:");
				switch(g.getType()) {
					case MASCULINE:
						sb.append(Colour.MASCULINE.getShades(8)[m] + "; float:left; border-radius: 2;'></div>");
						m++;
						break;
					case NEUTRAL:
						sb.append(Colour.ANDROGYNOUS.getShades(8)[n] + "; float:left; border-radius: 2;'></div>");
						n++;
						break;
					case FEMININE:
						sb.append(Colour.FEMININE.getShades(8)[f] + "; float:left; border-radius: 2;'></div>");
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
	
	public static final DialogueNodeOld GENDER_PREFERENCE = new DialogueNodeOld("Gender preferences", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "These options will determine the gender encounter rates of random NPCs."
					+ " Some NPCs, such as random succubi attackers, have restrictions on their gender, but your preferences will be taken into account wherever possible.</br>"
					+ "<b>A visual representation of the encounter chances can be seen in the bars at the bottom of each section.</b>"
					+ " (The different shades of each gender are solely for recognition in the bars, and don't mean anything other than that.)"
					+ "</br>"
					+ "A character is considered to have breasts if they are at least an AA-cup."
					+ "</div>");
			
			UtilText.nodeContentSB.append(getGenerPreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getGenerPreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getGenerPreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			 if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	private static String getGenerPreferencesPanel(PronounType type) {
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
				
				for(GenderPreference preference : GenderPreference.values()) {
					sb.append("<div id='"+preference+"_"+g+"' class='preference-button"+(Main.getProperties().genderPreferencesMap.get(g)==preference.getValue()?" selected":"")+"'>"+Util.capitaliseSentence(preference.getName())+"</div>");
				}
								
				sb.append("<p></br>"
								+ "<span style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"s</span> have "
										+(g.getGenderName().isHasVagina()?"a [style.colourGood(vagina)]":"no [style.colourBad(vagina)]")+", "
										+(g.getGenderName().isHasPenis()?"a [style.colourGood(penis)]":"no [style.colourBad(penis)]")+", and "
										+ (g.getGenderName().isHasBreasts()?"[style.colourGood(breasts)]":"no [style.colourBad(breasts)]")+"."
								+ "</p>"
							+ "</div>"
						+ "</div>"
						+ "<hr></hr>");
				count++;
			}
		}
		
		sb.append(
				getGenderRepresentation()
				+"</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNodeOld FURRY_PREFERENCE = new DialogueNodeOld("Furry preferences", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
						+ "These options determine the amount of furry content that you'll encounter in the game."
						+ " The 'Human encounters' option determines what the chance is for random NPCs to be fully human."
						+ " <b>These options only affect random NPCs at the moment, but I'll do my best to add reduced-furry versions of each major NPC as well!</b>"
						
						+ "</br><b style='color:"+RaceStage.HUMAN.getColour().toWebHexString()+";'>Human:</b> "+FurryPreference.HUMAN.getDescription()
						+ "</br><b style='color:"+RaceStage.PARTIAL_FULL.getColour().toWebHexString()+";'>Minimum:</b> "+FurryPreference.MINIMUM.getDescription()
						+ "</br><b style='color:"+RaceStage.PARTIAL_FULL.getColour().toWebHexString()+";'>Reduced:</b> "+FurryPreference.REDUCED.getDescription()
						+ "</br><b style='color:"+RaceStage.LESSER.getColour().toWebHexString()+";'>Normal:</b> "+FurryPreference.NORMAL.getDescription()
						+ "</br><b style='color:"+RaceStage.GREATER.getColour().toWebHexString()+";'>Maximum:</b> "+FurryPreference.MAXIMUM.getDescription()
						
						+ "</br></br>Please note that mythological and demonic races, such as harpies and demons, are not affected by furry preferences."
					+ "</div>"
							
					+ "<span style='height:16px;width:800px;float:left;'></span>"
					
					+ "<div class='container-full-width' style='text-align: center;'>"
					+ "<div style='display:inline-block; margin:0 auto;'>");
			
			UtilText.nodeContentSB.append(
					"<div style='width:160px; float:left;'>"
						+ "<b>Human encounters:</b> "
					+ "</div>"
						+ "<div id='furry_preference_human_encounter_zero' class='preference-button"+(Main.getProperties().humanEncountersLevel==0?" selected":"")+"'>Off</div>"
						+ "<div id='furry_preference_human_encounter_one' class='preference-button"+(Main.getProperties().humanEncountersLevel==1?" selected":"")+"'>5%</div>"
						+ "<div id='furry_preference_human_encounter_two' class='preference-button"+(Main.getProperties().humanEncountersLevel==2?" selected":"")+"'>10%</div>"
						+ "<div id='furry_preference_human_encounter_three' class='preference-button"+(Main.getProperties().humanEncountersLevel==3?" selected":"")+"'>20%</div>"
						+ "<div id='furry_preference_human_encounter_four' class='preference-button"+(Main.getProperties().humanEncountersLevel==4?" selected":"")+"'>50%</div>"
					+ "</br></br>"
						
					+"<div style='width:160px; float:left;'>"
						+ "<b>Multi-breasts:</b> "
					+ "</div>"
						+ "<div id='furry_preference_multi_breast_zero' class='preference-button"+(Main.getProperties().multiBreasts==0?" selected":"")+"'>Off</div>"
						+ "<div id='furry_preference_multi_breast_one' class='preference-button"+(Main.getProperties().multiBreasts==1?" selected":"")+"'>Furry-only</div>"
						+ "<div id='furry_preference_multi_breast_two' class='preference-button"+(Main.getProperties().multiBreasts==2?" selected":"")+"'>On</div>"
					+ "</br></br>"
					
					+"<div style='width:160px; float:left;'>"
						+ "<b>Set all:</b> "
					+ "</div>"
					+ "<div id='furry_preference_female_human_all' class='preference-button'>Human</div>"
					+ "<div id='furry_preference_female_minimum_all' class='preference-button'>Minimum</div>"
					+ "<div id='furry_preference_female_reduced_all' class='preference-button'>Reduced</div>"
					+ "<div id='furry_preference_female_normal_all' class='preference-button'>Normal</div>"
					+ "<div id='furry_preference_female_maximum_all' class='preference-button'>Maximum</div>");
			
			for(Race r : Race.values()) {
				if(r.isAffectedByFurryPreference()) {
					UtilText.nodeContentSB.append(
							"</br></br><div style='width:100%; text-align:center;'>"
								+ "<b style='color:"+r.getColour().toWebHexString()+";'>" +Util.capitaliseSentence(r.getName())+"</b>"
							+ "</div></br>"
							+ "<div style='width:160px; float:left;'>"
								+ "<b style='color:"+Colour.FEMININE.toWebHexString()+";'>Feminine:</b> "
							+ "</div>"
							+ "<div id='furry_preference_female_human_"+r+"' class='preference-button"+(Main.getProperties().raceFemininePreferencesMap.get(r)==FurryPreference.HUMAN?" selected":"")+"'>Human</div>"
							+ "<div id='furry_preference_female_minimum_"+r+"' class='preference-button"+(Main.getProperties().raceFemininePreferencesMap.get(r)==FurryPreference.MINIMUM?" selected":"")+"'>Minimum</div>"
							+ "<div id='furry_preference_female_reduced_"+r+"' class='preference-button"+(Main.getProperties().raceFemininePreferencesMap.get(r)==FurryPreference.REDUCED?" selected":"")+"'>Reduced</div>"
							+ "<div id='furry_preference_female_normal_"+r+"' class='preference-button"+(Main.getProperties().raceFemininePreferencesMap.get(r)==FurryPreference.NORMAL?" selected":"")+"'>Normal</div>"
							+ "<div id='furry_preference_female_maximum_"+r+"' class='preference-button"+(Main.getProperties().raceFemininePreferencesMap.get(r)==FurryPreference.MAXIMUM?" selected":"")+"'>Maximum</div>"
							+ "</br></br>"
							+ "<div style='width:160px; float:left;'>"
								+ "<b style='color:"+Colour.MASCULINE.toWebHexString()+";'>Masculine:</b> "
							+ "</div>"
							+ "<div id='furry_preference_male_human_"+r+"' class='preference-button"+(Main.getProperties().raceMasculinePreferencesMap.get(r)==FurryPreference.HUMAN?" selected":"")+"'>Human</div>"
							+ "<div id='furry_preference_male_minimum_"+r+"' class='preference-button"+(Main.getProperties().raceMasculinePreferencesMap.get(r)==FurryPreference.MINIMUM?" selected":"")+"'>Minimum</div>"
							+ "<div id='furry_preference_male_reduced_"+r+"' class='preference-button"+(Main.getProperties().raceMasculinePreferencesMap.get(r)==FurryPreference.REDUCED?" selected":"")+"'>Reduced</div>"
							+ "<div id='furry_preference_male_normal_"+r+"' class='preference-button"+(Main.getProperties().raceMasculinePreferencesMap.get(r)==FurryPreference.NORMAL?" selected":"")+"'>Normal</div>"
							+ "<div id='furry_preference_male_maximum_"+r+"' class='preference-button"+(Main.getProperties().raceMasculinePreferencesMap.get(r)==FurryPreference.MAXIMUM?" selected":"")+"'>Maximum</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			 if (index == 0) {
				return new Response("Back", "Go back to the options menu.", OPTIONS);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	
	public static final DialogueNodeOld CONTENT_PREFERENCE = new DialogueNodeOld("Content Options", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='background:transparent; padding:0; margin-bottom:0; margin-top:0;'>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"NON_CON",
					Colour.BASE_CRIMSON,
					"Non-consent",
					"This enables the 'resist' pace in sex scenes, which contains some more extreme non-consensual descriptions.",
					Main.getProperties().nonConContent));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"INCEST",
					Colour.BASE_ROSE,
					"Incest",
					"This will enable sexual actions with all of your blood-relatives.",
					Main.getProperties().incestContent));

			UtilText.nodeContentSB.append("</div><div class='container-full-width' style='background:transparent; padding:0; margin-bottom:0; margin-top:0;'>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"FORCED_TF",
					Colour.BASE_GREEN_LIGHT,
					"Forced Transformations",
					"With this turned on, random NPCs will forcibly transform you if you lose combat against them.",
					Main.getProperties().forcedTransformationContent));

			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"HAIR_FACIAL",
					Colour.BASE_LILAC_LIGHT,
					"Facial hair",
					"This enables facial hair descriptions and content.",
					Main.getProperties().facialHairContent));

			UtilText.nodeContentSB.append("</div><div class='container-full-width' style='background:transparent; padding:0; margin-bottom:0; margin-top:0;'>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"HAIR_PUBIC",
					Colour.BASE_LILAC,
					"Pubic hair",
					"This enables pubic hair descriptions and content.",
					Main.getProperties().pubicHairContent));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"HAIR_BODY",
					Colour.BASE_PURPLE,
					"Extra body hair",
					"This enables body hair descriptions and content for armpits and assholes.",
					Main.getProperties().bodyHairContent));

			UtilText.nodeContentSB.append("</div><div class='container-full-width' style='background:transparent; padding:0; margin-bottom:0; margin-top:0;'>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv(
					"FURRY_TAIL_PENETRATION",
					Colour.BASE_MAGENTA,
					"Furry tail penetrations",
					"This enables furry tails to engage in penetrative actions in sex.",
					Main.getProperties().furryTailPenetrationContent));
			

			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Non-con", "Toggle non-consensual content on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().nonConContent = !Main.getProperties().nonConContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 2) {
				return new Response("Incest", "Toggle incest encounters on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().incestContent = !Main.getProperties().incestContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 3) {
				return new Response("Forced TF", "Toggle forced TF content on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().forcedTransformationContent = !Main.getProperties().forcedTransformationContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 4) {
				return new Response("Facial hair", "Toggle facial hair content on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().facialHairContent = !Main.getProperties().facialHairContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 5) {
				return new Response("Pubic hair", "Toggle pubic hair content on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().pubicHairContent = !Main.getProperties().pubicHairContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 6) {
				return new Response("Full body hair", "Toggle body hair content on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().bodyHairContent = !Main.getProperties().bodyHairContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 7) {
				return new Response("Furry tail pen.", "Toggle furry tail penetrations on or off.", CONTENT_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().furryTailPenetrationContent = !Main.getProperties().furryTailPenetrationContent;
						Main.saveProperties();
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
	
	private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center; color:"+colour.toWebHexString()+";'>"
							+ title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>");
		
		if(enabled) {
			contentSB.append(
					"<div id='"+id+"_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.GENERIC_BAD.getShades()[0]+";'>OFF</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(ON)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldBad(OFF)]"
					+ "</div>"
					+ "<div id='"+id+"_ON' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>ON</span>"
					+ "</div>");
		}

		contentSB.append(
				"</div>");
		
		return contentSB.toString();
	}
	
	
	
	
	public static final DialogueNodeOld CREDITS = new DialogueNodeOld("Credits", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Thank you for playing Lilith's Throne, I hope you enjoy it just as much as I do making it!"
						+ " Thank you so much to all of the supporters on Patreon! Thanks to you, I'm able to spend more time working on Lilith's Throne, and I promise that I'll make this game the very best that I can!"
					+ "</p>"
					+"<p style='text-align:center;'>"
						+ "Lilith's Throne has been created by:</br>"
						+ "<b style='color:#9b78fa;'>Innoxia</b>"
						+ "</br></br>"
						+ "Special thanks to:</br>"
						+ "<b>Sensei</b>,</br>"
						+ "<b style='color:#fa0063;'>loveless</b>, <b style='color:#c790b2;'>Blue999</b>, and <b style='color:#ec9538;'>DesuDemona</b></br>"
						+ "<b style='color:#e06e5f;'>Everyone who's supported me on Patreon</b>,</br>"
						+ "<b>Bug reporters, wikia contributors, content writers, and feedback providers</b>,</br>"
						+ "and</br>"
						+ "<b>Everyone for playing Lilith's Throne!</b>"
					+ "</p>"
					+ "</br>"
					+ "<h5 style='text-align:center; color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>Legendary Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()>0) {
					UtilText.nodeContentSB.append("</br>");
					for(int i=0; i<cs.getLegendaryCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_LEGENDARY.toWebHexString()+";'>&#9679</b> ");
					}
					for(int i=0; i<cs.getEpicCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
					}
					for(int i=0; i<cs.getRareCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
					}
					for(int i=0; i<cs.getUncommonCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
					}
					UtilText.nodeContentSB.append(cs.getName());
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "</br>"
					+ "<h5 style='text-align:center; color:"+Colour.RARITY_EPIC.toWebHexString()+";'>Epic Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()>0) {
					UtilText.nodeContentSB.append("</br>");
					for(int i=0; i<cs.getEpicCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
					}
					for(int i=0; i<cs.getRareCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
					}
					for(int i=0; i<cs.getUncommonCount(); i++) {
						UtilText.nodeContentSB.append("<b style='color:"+Colour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
					}
					UtilText.nodeContentSB.append(cs.getName());
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.OPTIONS;
		}
	};
}
