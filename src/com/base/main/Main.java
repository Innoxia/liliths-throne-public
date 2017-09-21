package com.base.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.base.controller.MainController;
import com.base.game.Game;
import com.base.game.Properties;
import com.base.game.character.CharacterUtils;
import com.base.game.character.NameTriplet;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.story.CharacterCreation;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.utils.CreditsSlot;
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
 * @version 0.1.85
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.84.5",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/base/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>VERY BUGGY PREVIEW!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha release!</b></h6>"
		
		+ "<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
		+ "<p>"
			+ "Hello again everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "This version is just a preview of my current progress, and contains a lot of bugs and half-finished content!"
			+ " I'm really sorry that it's in such a rough state, but implementing the system to allow clothing and item management between the player and any NPC was an absolute nightmare to create, and took all of my time. ;_;"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "</br>"

		+ "<list>"
		
		+ "<h6>Preview (v0.1.84.5)</h6>"
		
		+"<li>Engine:</li>"
		+"<ul><b>Added:</b> Functionality to give/take clothing, items, and weapons to/from NPCs. This took ages. x_x</ul>"
		+"<ul><b>Added:</b> All of the inventory slots that I had planned, including orifice-related ones (to allow for insertion of toys into orifices).</ul>"
		+"<ul><b>Added:</b> An option (in the options menu) to disable the 'fade in' effect on the main text window (which removes the input lag if using the mouse to click the map for movement).</ul>"
		
		+"<li>Gameplay:</li>"
		+"<ul><b>Added:</b> Functionality to convert the rooms in Lilaya's house into slave's quarters, along with functionality to apply upgrades those rooms.</ul>"
		
		+"<li>UI:</li>"
		+"<ul><b>Remade & expanded:</b> Inventory screen.</ul>"
		+"<ul>Moved the map from the bottom-left up into the main section of dialogue.</ul>"
		+"<ul>	While playing, I felt as though my focus was mostly on the tiny section at the bottom-left of the screen, and I was having to move my eyes a lot between the map and the main content panel. This was an attempt to address this situation.</ul>"
		+"<ul>Added an event log in the bottom-left of the screen.</ul>"
		+"<ul>Moved all 'Remove character' actions to button 10.</ul>"
			
		+"<li>Other:</li>"
		+"<ul>Moved Rose's dialogue out of the generic rooms and into her room's tile.</ul>"
		+"<ul>Ralph no longer feels the need to wear socks over his hooves.</ul>"
		+"<ul>Added more values for Body Hair.</ul>"
		+"<ul>NPCs should no longer be taking their own virginites.</ul>"
		+"<ul><b>A few more minor things:</b> But I forgot to make note of them...</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>Fixed bug where the potency of Bubble Cream's enchantments couldn't be changed.</ul>"
		+"<ul>NPCs with cow-morph body-type preference will now correctly TF you into a cow-morph instead of a human.</ul>"
		+"<ul>Fixed some issues with Bubble Milk displaying a large letter A in its icon.</ul>"
		+"<ul>Fixed 'null' being returned in text when cow-leg transformation caused you to be unable to wear shoes.</ul>"
		+"<ul>Fixed some typos.</ul>"
		+"</list>"
		;
	
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

		credits.add(new CreditsSlot("Anonymous", "", 0, 0, 2, 2));
		// A. L2 | C.C. R1 | G. R1
		
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Pierrre Mura", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("N.", "", 0, 0, 0, 2)); // TODO
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Rohise", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("S.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Tanner Daves", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("V.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("R.S.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("C.T.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 1)); // TODO
		
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("P.H.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("L.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S.D", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("J.B.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("B.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("b00marrows ", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("V.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("G.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("R.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("A.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("N.L.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Burt", "", 0, 0, 1, 0));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
		}

		mainController = loader.getController();

		Main.primaryStage.setScene(mainScene);
		
		Main.primaryStage.show();
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

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
		} catch (ClassNotFoundException | IOException e1) {
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
						pane = loader.load();
						Main.mainController = loader.getController();

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
			saveGame("QuickSave_"+Main.game.getPlayer().getName(), true);
		}
	}

	public static void quickLoadGame() {
		loadGame("QuickSave_"+Main.game.getPlayer().getName());
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
		if (!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' _]*")) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Incompatible characters!");
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/saves");
		dir.mkdir();
		
		boolean overwrite = false;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".lts"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
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
		
		File file = new File("data/saves/"+name+".lts");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
//			long timeStart = System.nanoTime();
//			System.out.println(timeStart);
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			  oos.writeObject(Main.game);
			  oos.close();
			}
			
//			System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);

			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininityValue()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName();
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.race = game.getPlayer().getRace().getName();
			properties.quest = game.getPlayer().getQuest(QuestLine.MAIN).getName();

			properties.savePropertiesAsXML();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			if(overwrite) {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Save game overwritten!");
			} else {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game saved!");
			}
		} else if(name.equals("QuickSave_"+Main.game.getPlayer().getName())){
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
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".lts"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static List<File> getCharactersForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(CharacterUtils.startLoadingCharacterFromXML());
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer()));
				
				Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
				Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, false), true, Main.game.getPlayer());
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.getProperties().setNewWeaponDiscovered(false);
				Main.getProperties().setNewClothingDiscovered(false);
				Main.getProperties().setNewItemDiscovered(false);
				Main.game.getPlayer().calculateStatusEffects(0);

				Main.game.initNewGame(CharacterCreation.START_GAME_WITH_IMPORT);
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
