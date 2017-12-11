package com.lilithsthrone.main;

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

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

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
 * @version 0.1.89.5
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.90.5",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone! :3"
		+ "</p>"
			
		+ "<p>"
			+ "I've got all the non-sex-related goals pretty much finished now, so next week I can fully concentrate on adding in all the extra sex stuff (like knotting and position-specific orgasms), as well as get multiple-partner sex implemented. ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Speaking of the next version, it will be 0.1.95, and will have the sole target of getting multiple-partner sex scenes set up in the engine. :3"
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon! :3"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.90.5 (Preview)</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finished Zaranix's house placeholders (the ones after fighting Zaranix). Imported games with a version prior to 0.1.95 should reset Zaranix's quest, allowing you to complete it without experiencing bugs (hopefully).</ul>"
			+"<ul>Added in slave's 'molest' action content.</ul>"
			+"<ul>Added wing size to possible transformations.</ul>"
			+"<ul>Added demonic transformation menu to your phone, as a post-combat victory action over demons, and to demon slaves. (The UI really needs a lot more polishing, but it should be fully functional.)</ul>"
			+"<ul>Added month selection to character creation process. (If you want to edit the date after starting the game, simply change the 'date' value in the save's .xml value. The in-game date is that value plus the amount of minutes shown in the 'minutesPassed' value.)</ul>"
			+"<ul>Added public use slaves in the stocks in Slaver Alley.</ul>"
			+"<ul>Added an incubus variation to the succubus alleyway attacker (also set spawn rates for succubi/incubi to be based on your gender preferences). Also improved variety of clothing worn by succubus/incubus attackers. (Some of the text hasn't been updated for the incubus just yet. It will be finished for the next release.)</ul>"
			+"<ul>Added 'public use' as a job for your slaves. When selected, your slaves will be sent to Slaver Alley's public stocks, where they will be used by randomly generated NPCs.</ul>"
			+"<ul>Added self-doggy oral positions.</ul>"
			+"<ul>Added difficulty setting in the options.</ul>"

			+"<li>Other:</li>"
			+"<ul>In-game start date is now set to the current date (instead of 1 week behind).</ul>"
			+"<ul>Added a new name generator for demons.</ul>"
			+"<ul>Slaves can now only clean their clothes/wash their bodies while not at work.</ul>"
				
			+"<li>Contributions:</li>"
			+"<ul>Multiple spelling corrections (submitted via github PR by shutizake).</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed an issue with older character exports not being imported correctly (related to an old personality value being changed in newer versions).</ul>"
			+"<ul>Fixed an issue with used condoms causing game imports to break.</ul>"
			+"<ul>Imported slaves can now be impregnated.</ul>"
			+"<ul>Fixed chemise and pantyhose blocking displacement of each other (which was causing a major crash during sex).</ul>"
			+"<ul>Minor grammatical and typo fixes.</ul>"
			+"<ul>Fixed bug where imported slaves would call you by their own name. (Will take effect for newly exported slaves from now on.)</ul>"
			+"<ul>Slaves should now go to, and return from, work on time. (They were an hour late for both.)</ul>"
			+"<ul>Fixed several causes of major errors when importing old games.</ul>"
			+"<ul>Fixed a few minor dialogue bugs in Zaranix's house.</ul>"
			+"<ul>Fixed major bug of Zaranix's house being repeatable once completed. (I will make it repeatable in the future, but for now it needs to be disabled to avoid major bugs & dialogue inconsistencies.)</ul>"
			+"<ul>Fixed succubi only ever using the 'back-to-wall' position.</ul>"
			+"<ul>Fixed bug where some slaves would have unchangeable names like 'horse-girl'.</ul>"
			+"<ul>Stopped used condoms from being able to be spawned in using the debug menu (as it was causing issues).</ul>"
			+"<ul>Versions prior to 0.1.95 will now have all NPC's hair lengths reset to default values (to address broken hair lengths in older versions).</ul>"
		+"</list>"
		;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

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
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		credits.add(new CreditsSlot("Anonymous", "", 0, 9, 124, 41));
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Enigamatic Yoshi", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 5, 0));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
		}

		mainController = loader.getController();
		Main.primaryStage.setScene(mainScene);
		Main.primaryStage.show();
		Main.game = new Game();
		
		loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().lightTheme)
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
				else
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
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
		
		Main.game = new Game();
		
		// Generate world:
		if (!(gen == null))
			if (gen.isRunning()) {
				gen.cancel();
			}

		gen = new Generation();

		gen.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
				Pane pane;
				try {
					if (Main.mainScene == null) {
						pane = loader.load();
						Main.mainController = loader.getController();

						Main.mainScene = new Scene(pane);
						if (Main.getProperties().lightTheme)
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

				Main.game.initNewGame(startingDialogueNode);
				
				Main.game.endTurn(0);
				//Main.mainController.processNewDialogue();
			}
		});
		new Thread(gen).start();
	}
	
	public static boolean isVersionOlderThan(String versionToCheck, String versionToCheckAgainst) {
		String[] v1 = versionToCheck.split("\\.");
		String[] v2 = versionToCheckAgainst.split("\\.");
		
		try {
			if(Integer.valueOf(v1[0]) < Integer.valueOf(v2[0])) {
				return true;
			}
			
			if(Integer.valueOf((v1[1].length()==1?v1[1]+"0":v1[1])) < Integer.valueOf((v2[1].length()==1?v2[1]+"0":v2[1]))) {
				return true;
			}
			
			if(Integer.valueOf((v1[2].length()==1?v1[2]+"0":v1[2])) < Integer.valueOf((v2[2].length()==1?v2[2]+"0":v2[2]))) {
				return true;
			}
			
			if(v1.length<4) {
				if(v2.length<4) {
					return false;
				} else {
					return true;
				}
			}
			if(v2.length<4) {
				return false;
			}
			
			if(Integer.valueOf((v1[3].length()==1?v1[3]+"0":v1[3])) < Integer.valueOf((v2[3].length()==1?v2[3]+"0":v2[3]))) {
				return true;
			}
		} catch(Exception ex) {
			return true;
		}
		
		return false;
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
			Main.getProperties().lastQuickSaveName = "QuickSave_"+Main.game.getPlayer().getName();
			saveGame("QuickSave_"+Main.game.getPlayer().getName(), true);
		}
	}

	public static void quickLoadGame() {
		String name = "QuickSave_"+Main.game.getPlayer().getName();
		
//		if(new File("data/saves/"+name+".lts").exists()) {
			loadGame(name);
//		} else {
//			loadGame(Main.getProperties().lastQuickSaveName);
//		}
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
			properties.arcaneEssences = game.getPlayer().getEssenceCount(TFEssence.ARCANE);
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
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				Main.game = (Game) ois.readObject();
				Main.game.reloadContent();
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
	
	public static void deleteExportedGame(String name) {
		File file = new File("data/saves/"+name+".xml");

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
	
	public static void deleteExportedCharacter(String name) {
		File file = new File("data/characters/"+name+".xml");

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
	
	public static List<File> getSlavesForImport() {
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
	
	public static List<File> getGamesForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
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
				
				Main.game.getPlayer().getSlavesOwned().clear();
				Main.game.getPlayer().endPregnancy(false);
				
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
