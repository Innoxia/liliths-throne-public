package com.base.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.base.controller.MainController;
import com.base.game.Game;
import com.base.game.Properties;
import com.base.game.character.CharacterUtils;
import com.base.game.character.NameTriplet;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.story.CharacterCreationDialogue;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.utils.CreditsSlot;
import com.base.utils.Util;
import com.base.world.Generation;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene, menuScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public final static String VERSION_NUMBER = "0.1.83P",
			VERSION_DESCRIPTION = "Preview"; //TODO Early Alpha

	public final static Image WINDOW_IMAGE = new Image("/com/base/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>This is a preview release, and contains half-finished content!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha release!</b></h6>"
		
		+ "<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
		+ "<p>"
		+ "This is just a preview release, so <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>only play it if you're prepared to encounter some serious bugs and half-written content!</b>"
		+ " The full, polished version of this release will be out on Friday!"
		+ "</p>"
		
		
		+ "<p>"
			+ "Hello again! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "I ended up completely rewriting Alexa's content a couple of times this week, and I'm still not 100% happy with it..."
			+ " Despite that, I'm going to finish it off as it is over the weekend, so I can move on to all the other targets that I set myself for this version next week."
		+ "</p>"
			
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
		
		+ "<list>"
		+ "<h6>Patch Notes - <b>Version " + Main.VERSION_NUMBER + "</b></h6>"
		
		+"<li>Gameplay:</li>"
			+"<ul><b>Added:</b> (Most of) Alexa & Scarlett main quest content.</ul>"
			+"<ul><b>Added:</b> Nikki, the manager of Slaver Alley's new 'Slave Administration' building. (Sells BDSM gear and slave collars once you get a slaver license.)</ul>"
			+"<ul>Slightly changed Slaver Alley's map.</ul>"
			+"<ul>Added 'Barren' perk.</ul>"
			+"<ul>Locked Brax's forced transformation behind the 'Transformation' fetish.</ul>"
		
		+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Bandana (no femininity requirements, mouth slot).</ul>"
			+"<ul><b>Added:</b> Tiara (feminine, head slot).</ul>"
			+"<ul><b>Added:</b> Arm wraps (no femininity requirements, hand slot).</ul>"
			+"<ul><b>Added:</b> Cowboy hat (no femininity requirements, head slot).</ul>"
			+"<ul><b>Added:</b> Mega Milk T-shirt (no femininity requirements, torso slot). Has a 1% chance to spawn in the 'Found clothing' event in alleyway tiles.</ul>"
			+"<ul><b>Added:</b> 'Virgin-killer' sweater (feminine, torso slot).</ul>"
			+"<ul><b>Added:</b> Ball gag (no femininity requirements, mouth slot, BDSM set).</ul>"
			+"<ul><b>Added:</b> Ring gag (no femininity requirements, mouth slot, BDSM set).</ul>"
			+"<ul><b>Added:</b> Tagged Choker (no femininity requirements, neck slot, BDSM set).</ul>"
			+"<ul><b>Added:</b> Wrist restraints (no femininity requirements, wrist slot, BDSM set).</ul>"
			+"<ul><b>Added:</b> Spreader bar (no femininity requirements, ankle slot, BDSM set).</ul>"
			+"<ul><b>Added:</b> Chastity belt (no femininity requirements, groin slot, BDSM set).</ul>"
			+"<ul>Added: 'Grey' to available clothing colours.</ul>"
			+"<ul>Improved: Circlet's icon.</ul>"
			+"<ul>Changed: Basic earrings and ringed barbell no longer have a femininity requirement.</ul>"
		
		+"<li>Other:</li>"
			+"<ul><b>Changed:</b> You can now only find bottled essences after starting the 'Enchantments & Essences' quest.</ul>"
			+"<ul>Split up and changed the debug menu's money/essence gain option from +100 flames/+1 essence to +1000 flames/+10 essences.</ul>"
			+"<ul>Reduced the arousal gain penalty while resisting from -75% to -50%.</ul>"
			+"<ul>Added a 0.5% chance to find an Eggplant in the alleyways. (Why did I do this?)</ul>"
		
		+"<li>AI:</li>"
			+"<ul>NPCs with the pure virgin or lusty maiden status effects should no longer use sex actions that will cause them to lose their virginity.</ul>"
			
		+"<li>Bugs:</li>"
			+"<ul>Fixed major bug where you could get stuck in your phone's menu if you opened the 'characters present' window during combat.</ul>"
			+"<ul>Fixed some typos.</ul>"
			+"<ul>Fixed the cause of the occasional game freeze when pressing 'Take all'. (Thanks sinstrudel.)</ul>"
			+"<ul>Fixed 'broken maiden' status effect being applied even if you didn't have the 'lusty maiden' fetish.</ul>"
			+"<ul>Cum addicts will no longer ask you to pull out when they're giving you a blowjob.</ul>"
			+"<ul>Removed old reference to non-con in options menu.</ul>"
			+"<ul>Dominant doggy-style orgasm should now correctly apply pregnancy chances.</ul>"
			+"<ul>Sex stats should now correctly track how many loads of cum you've given in each sex type.</ul>"
			+"<ul>If you have maximum attribute values (100 in strength, intelligence, and fitness), your phone will no longer highlight if you have attribute points to spend.</ul>"
			+"<ul>Fixed some incorrect detection of body part values.</ul>"
			+"<ul>Fixed bug where Kate's into scene would repeat every time you entered her store.</ul>"
			+"<ul>Fixed duplicate virginity loss descriptions in selfie.</ul>"
			+"<ul>Fixed bug where the Jinxed, Enchantments, and Pregnancy quests would sometimes not trigger correctly.</ul>"
			+"<ul>Fixed some bugs related to incorrect fur descriptions in TFs. (Thanks Rfpnj.)</ul>"
			+"<ul>Fixed bug in sex that would cause you to be unable to choose any action other than 'Use item'. (Thanks strudel.)</ul>"
			+"<ul>Fixed bug where fetishes wouldn't reset correctly sometimes.</ul>"
			
		+ "</list>";
	
	public static String disclaimer = "<h1 style='text-align:center;'>DISCLAIMER</h1>"
			+"<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

			+ "<p>This game is a <b>fictional</b> text-based erotic RPG." + " All content contained within this game forms part of a fictional universe that is not related to real-life places, people or events.</br></br>"

			+ " All of the characters that appear in this story are fictional entities who have given their consent to appear and act in this story."
			+ " If you find yourself concerned for the characters in the story then please be reassured that they are all consenting adults who are speaking lines from a script."
			+ " None of the characters portrayed within this game were or are being harmed in any way during the construction or execution of this game."
			+ " Every character in the game is at least 18 years of age (or is magically the legal age needed to appear in erotic literature in whatever country you are playing this).</br></br>"

			+ "By agreeing to this disclaimer and playing this game you agree to be exposed to graphic sexual and adult content that is presented as part of the game's fictional universe."
			+ " Such content consists of, but is not limited to; graphic depictions of sex, inter-species sex (with fantasy creatures), sexual transformation,"
			+ " rape fantasy/implied lack of consent, mild physical violence, sexual violence, and drug use.</br>"
			+ "Extreme fetish content such as gore/extreme violence, scat, and under/questionable age, is <i>not</i> a part of this game.</br></br>"

			+ "By agreeing to this disclaimer and playing this game you also agree that you are <b>at least 18 years of age</b>,"
			+ " or at least the legal age for you to purchase and view pornographic material in your country if that age is over 18.</br></br>"

			+ "As a final note, the creators of this game wish to stress that the content presented within is entirely fictional and does not reflect any of their personal views or opinions."
			+ " This game has been made in the spirit of creating a piece of artistic interactive literature, and it is imperative that you maintain a clear distinction between reality and the fictional events depicted in this game.</p>";
	
	public static List<CreditsSlot> credits = new ArrayList<>();

	// World generation:
	private static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("A.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Pierrre Mura", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("N.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Rohise", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 1));
		
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("P. H.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("48days", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("L.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S. D", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("T.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("J. B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("W. B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		
		
		Collections.sort(credits, new Comparator<CreditsSlot>() {
			public int compare(CreditsSlot left, CreditsSlot right) {
		    	return left.getName().toLowerCase().compareTo(right.getName().toLowerCase());
			}
		});
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));

		Pane pane = (Pane) loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
		}

		mainController = loader.<MainController> getController();

		Main.primaryStage.setScene(mainScene);
		
		Main.primaryStage.show();
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = (Pane) loader.load();
				Main.mainController = loader.<MainController> getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().lightTheme)
					Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
				else
					Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
			}

			Main.primaryStage.setScene(Main.mainScene);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		
	}

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
		// Load properties:
		if (new File("data/properties.xml").exists()) {
			try {
				properties = new Properties();
				properties.loadPropertiesFromXML();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			properties = new Properties();
			properties.savePropertiesAsXML();
		}
		
		launch(args);
	}
	
	/**
	 * Starts a completely new game. Runs a new World Generation.
	 */
	public static void startNewGame(DialogueNodeOld startingDialogueNode) {
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		Main.game.setPlayer(new PlayerCharacter("Player", "", 1, Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.CITY, Dominion.CITY_AUNTS_HOME));

		// Generate world:
		if (!(gen == null))
			if (gen.isRunning()) {
				gen.cancel();
			}

		gen = new Generation(WorldType.DOMINION);

		gen.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
				Pane pane;
				try {
					if (Main.mainScene == null) {
						pane = (Pane) loader.load();
						Main.mainController = loader.<MainController> getController();

						Main.mainScene = new Scene(pane);
						if (Main.getProperties().lightTheme)
							Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_ENTRANCE_HALL));

				Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_ENTRANCE_HALL, false);
				Main.game.initNewGame(startingDialogueNode);
				
				Main.game.endTurn(0);
				//Main.mainController.processNewDialogue();
			}
		});
		new Thread(gen).start();
	}
	
	public static int getFontSize() {
		return properties.fontSize;
	}

	public static void setFontSize(int size) {
		properties.fontSize = size;
		properties.savePropertiesAsXML();
	}

	public static void quickSaveGame() {
		if (Main.game.isInCombat()) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Cannot quicksave while in combat!");
			
		} else if (Main.game.isInSex()) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Cannot quicksave while in sex!");
			
		} else if (Main.game.getCurrentDialogueNode().getMapDisplay()!=MapDisplay.NORMAL) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Can only quicksave in a normal scene!");
			
		} else {
			saveGame("QuickSave", true);
		}
	}

	public static void quickLoadGame() {
		loadGame("QuickSave");
	}

	public static void saveGame(String name, boolean allowOverwrite) {
		if (name.length()==0) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Name too short!");
			return;
		}
		if (name.length() > 32) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Name too long!");
			return;
		}
		if (!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' '_]*")) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Incompatible characters!");
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/saves");
		dir.mkdir();
		
		boolean overwrite = false;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("lts")) {
						if (child.getName().equals(name+".lts")){
							if(!allowOverwrite) {
								Main.game.flashMessage(Colour.GENERIC_BAD, "Name already exists!");
								return;
							} else {
								overwrite = true;
							}
						}
					}
				}
			}
		}
		
		File file = new File("data/saves/"+name+".lts");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (file != null) {
			try {
				   
			    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			      oos.writeObject(Main.game);
			      oos.close();
			    }

				properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
				properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininity()).getColour().toWebHexString();
				properties.name = game.getPlayer().getName();
				properties.level = game.getPlayer().getLevel();
				properties.money = game.getPlayer().getMoney();
				properties.race = game.getPlayer().getRace().getName();
				properties.quest = game.getPlayer().getMainQuest().getName();

				properties.savePropertiesAsXML();
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			if(overwrite) {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Save game overwritten!");
			} else {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game saved!");
			}
		} else if(name.equals("QuickSave")){
			Main.game.flashMessage(Colour.GENERIC_GOOD, "Quick saved!");
		}
	}

	public static void loadGame(String name) {
		File file = new File("data/saves/"+name+".lts");
		
		if (file.exists()) {
			try {
				Main.game = new Game();
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Game loadedGame = (Game) ois.readObject();
				ois.close();
				fin.close();
				Main.game = loadedGame;
				Main.mainController.renderMap();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game loaded!");
				if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS) {
					Main.mainController.openOptions();
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(Colour.GENERIC_BAD, "File not found...");
		}
	}
	
	public static void deleteGame(String name) {
		File file = new File("data/saves/"+name+".lts");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(Colour.GENERIC_BAD, "File not found...");
		}
	}
	
	public static List<File> getSavedGames() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("lts"))
						filesList.add(child);
				}
			}
		}
		
//		Collections.sort(filesList, (a, b) -> b.getName().compareTo(a.getName()));
		Collections.sort(filesList, (e1, e2)->{return e1.lastModified()>e2.lastModified()?-1:(e1.lastModified()==e2.lastModified()?0:1);});
		
		return filesList;
	}
	
	public static List<File> getCharactersForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("xml"))
						filesList.add(child);
				}
			}
		}
		
		Collections.sort(filesList, (a, b) -> b.lastModified() > a.lastModified() ? 1 : -1);
		
		return filesList;
	}
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file));
				
				Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
				Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_HOODIE, false), true, Main.game.getPlayer());
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.game.getPlayer().setNewWeaponDiscovered(false);
				Main.game.getPlayer().setNewClothingDiscovered(false);
				Main.game.getPlayer().setNewItemDiscovered(false);
				Main.game.getPlayer().getItemsDiscovered().clear();
				Main.game.getPlayer().calculateStatusEffects(0);

				Main.game.initNewGame(CharacterCreationDialogue.START_GAME_WITH_IMPORT);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void saveProperties() {
		properties.savePropertiesAsXML();
	}
}
