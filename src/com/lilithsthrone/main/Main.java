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
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.LilayasHome;

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

	public static final String VERSION_NUMBER = "0.1.85",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Moderately Buggy Preview!</b></h6>"
//		"<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha!</b></h6>"
		
		"<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"

		+ "<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>The 'light theme' is broken in this version! I'll get it fixed for 0.1.86, sorry! ;_;</i></p>"
		
		+ "<p>"
			+ "Hello again everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "For this version I was mainly focused on slavery content, which led to several large problems that I hadn't anticipated."
			+ " First, I had to do a huge rework of the inventory system to enable PC-NPC inventory management."
			+ " As part of this, I also added new orifice slots, which then led to the second problem of the UI not being able to fit in the new slots."
		+ "</p>"
			
		+ "<p>"
			+ "I'm sure you've already noticed this change to the UI, and <b>please be aware that I'm still working on it!</b>"
			+ " The event log is in a very rough state, as is the entire right-hand panel."
			+ " It should be functional, but there are definitely a lot of improvements that I can make."
		+ "</p>"
		
		+ "<p>"
			+ "I didn't get enough time to write in new incest content, but, as I'm sure you'll all be pleased to hear, this next version is going to be completely focused on <b>adding in new content and fixing bugs!</b>"
			+ " I'm not going to do any engine work (except from getting some progress done on the save compatibility), so there should be lots of new and exciting things in for the next version (0.1.86)! :3"
		+ "</p>"
			
		+ "<p>"
			+ "Oh, also, The UI is still being worked on. I <i>should</i> have it done for Friday, but if not, then it will be finished in next week's update. :3"
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

		+ "</br>"

		+ "<list>"
		
		+ "<h6>Preview 'Hotfix' (v0.1.84.6)</h6>"
		
		+"<li>Engine:</li>"
		+"<ul>Changed the way NPCs are tracked in an effort to make them easier to save and access.</ul>"
			
		+"<li>Slavery:</li>"
		+"<ul><b>Added:</b> Room management, Slave management UI (access from Slavery Administration, or any of the rooms in Lilaya's House).</ul>"
		+"<ul><b>Added:</b> Basic slave buying/selling. Once you've completed the part of the main quest involving Alexa, her shop will restock five slaves every day.</ul>"
		+"<ul><b>Added:</b> A lot of slave-related engine work. x_x</ul>"
		+"<ul><b>Added:</b> Placeholder dialogue for slaves. (This is the same as the offspring placeholders for now, I'm going to massively expand all these options over the next few versions.)</ul>"
			
		+"<li>UI:</li>"
		+"<ul>Moved the map back down to the bottom-left (I'll find a place for the event log next week ^^).</ul>"
		+"<ul>Swapped the finger and hips slots in the inventory UI.</ul>"
		+"<ul>Added 'Displace all', 'Replace all', 'Unequip all', and 'Equip all' actions to the inventory menu, along with NPC-targeted versions for displace, replace, and unequip.</ul>"
		+"<ul>Temporarily disabled quick manage in the inventory until I can get it fixed. (Sorry!)</ul>"
			
		+"<li>Other:</li>"
		+"<ul>Added 'unzip' displacement type for trousers, jeans, and shorts.</ul>"
		+"<ul>Added pattern options (striping/spotting/mottling) for demon and human skin in Kate's shop.</ul>"
		+"<ul>Added a fast travel option from your room to Lilaya's Lab, and vice versa. Also added fast travel back to the entrance from each of the shops in the arcade.</ul>"
		+"<ul>Moved Cowbell collar, Ear tag, and Bovine nose ring into a new 'Cattle' set. (The status effect buffs are boring at the moment, but I'll make them far more exciting when I move lactation/cum production into enchantable stats.)</ul>"
		+"<ul>Added 'silver' to eye colours.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>You should now properly be able to access your parnter's clothing during sex.</ul>"
		+"<ul>Enslaving someone by forcing them to equip a slave collar now correctly marks them as your slave.</ul>"
		+"<ul>Blazers are now correctly unisex.</ul>"
		+"<ul>Fixed bug where clothing would be duplicated after sex scenes.</ul>"
		+"<ul>Fixed a lot of incorrect descriptions of clothing management during sex.</ul>"
		+"<ul>You can now access the inventory of the people who try to rob you, as well as those who try to rape you.</ul>"
		+"<ul>Fixed bug where dying equipped clothes would cause those clothes to be duplicated into the owner's inventory.</ul>"
		+"<ul>Descriptions for adding a penis modifier will no longer display if the target has no penis.</ul>"
		+"<ul>Fixed demonic nipples always being described as nipple-cunts (even if they were regular nipples).</ul>"
		+"<ul>Arcane storm notifications should now properly trigger when in a vulnerable area.</ul>"
		+"<ul>Fixed starting hairstyle being random (your hair will now always start with a 'natural' style in the character creation).</ul>"
		+"<ul>Fixed bug where enforcer shirt, maid's dress, and Mega Milk T-shirt would not be able to be unequipped if you wore something over the top (like a hoodie).</ul>"
		+"<ul>Possibly fixed the bug that was causing the properties file to get re-created on a new game start.</ul>"
		+"<ul>Fixed minor bug where the text on the screen would disappear when pressing enter in the City Hall's change name box.</ul>"
		+"<ul>Scarlett's slave collar should now correctly be sealed.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
		
		+ "<h6>Version 0.1.85</h6>"
		
		+"<li>Engine:</li>"
		+"<ul>Added support for personality types and backgrounds for NPCs. This is the precursor to writing in proper dialogue with random NPCs.</ul>"
		
		+"<li>UI:</li>"
		+"<ul><b>Large rework:</b> Due to the inventory rework (adding in orifice slots), I've had to try and rework the UI to properly display the new options, while trying to retain the functionality of the old system.</ul>"
		+"<ul>As part of this rework, I've added a third column to the UI, but I'm still not entirely happy about it, and will most likely continue to tweak it over the next couple of weeks...</ul>"
			
		+"<li>Other:</li>"
		+"<ul>Reduced minimum hair length requirements for the Sidecut and Afro hair styles.</ul>"
		+"<ul>NPCs' name is now revealed when you enslave them.</ul>"
		+"<ul>You can no longer sell slaves to Finch (this was unintended behaviour).</ul>"
		+"<ul>Minor improvements to slave management UI.</ul>"
		+"<ul>Slightly changed bovine horn descriptions.</ul>"
		+"<ul>Scarlett can now be impregnated.</ul>"
		+"<ul>Added the displacement type 'pull down' to the keyhole sweater, which will expose your breasts.</ul>"
		+"<ul>Exhibitionist males will no longer wear clothing that blocks their nipples.</ul>"
		+"<ul>Added correct fetish associations to the 'Rough ride' option in sex.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>Fixed some typos</ul>"
		+"<ul>You can now sell your items even if the vendor's inventory is full.</ul>"
		+"<ul>Finch should now properly restock his inventory each day.</ul>"
		+"<ul>Enforcer Shorts can now be unzipped.</ul>"
		+"<ul>Slave sex should no longer end with dialogue suggesting you got dominated.</ul>"
		+"<ul>Fixed bug where Ralph's sex scene wouldn't start (and a series of associated bugs that were causing it in the background).</ul>"
		+"<ul>Text boxes should now respond correctly to pressing the 'Enter' key. (Fixes issues related to not being able to rename slaves, as well as all the text on the screen disappearing in the save dialogue.)</ul>"
		+"<ul>Rough Paizuri is now correctly tied to Sadist fetish like other rough pace actions.</ul>"
		+"<ul>Slaves no longer regenerate clothing after sex.</ul>"
		+"<ul>You should now be able to have sex with all of your slaves.</ul>"
		+"<ul>Fixed bug where transforming a slave and then returning to the management screen wouldn't update their appearance.</ul>"
		+"<ul>Using the used condom on an NPC now returns a more accurate description.</ul>"
		+"<ul>Cow and squirrel-morph potions now have correct titles.</ul>"
		+ "</list>"
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
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

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
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_ENTRANCE_HALL));

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
