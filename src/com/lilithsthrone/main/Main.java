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
 * @version 0.1.89
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.89",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Moderately Buggy Preview!</b></h6>"
		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha!</b></h6>"
		
		+ "<p>"
			+ "Hello! :3"
		+ "</p>"
			
		+ "<p>"
			+ "As I said in my goals post, the main focus of this release was fixing bugs, adding content, and finally implementing save compatibility."
			+ " I managed to get a lot of bugs fixed, and got the first version of save compatibility working, but I ran out of time in which to fully add the main story & sex content I had planned. ;_;"
		+ "</p>"
		
		+ "<p>"
			+ "I am extremely conscious of of the fact that bugs & engine work have been the focus of the last couple of releases, but please remember that I'm still in the 'engine work' phase of the game's development,"
				+ " and I really do need to get these final tasks finished before I can really focus on adding content."
			+ " That being said, I promise that I won't touch any engine work until the promised main story, sex, and slavery content is finished."
		+ "</p>"
		
		+ "<p>"
			+ "After that, I just have multiple-partner sex scenes and the combat & stats rework to complete, and then all the major engine work will be finished."
			+ " I'm aiming to have reached version 0.2.0 by Christmas (the version numbers will leap a little), so that I can fully concentrate on writing content going into the new year."
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon! :3"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
		
			+ "<h6>v0.1.88.5 (scroll down for 0.1.89 notes)</h6>"
			
			+"<li>Engine:</li>"
			+"<ul><b>Added:</b> First iteration of save compatibility (some minor things aren't working yet, but I'll finish it off over the weekend). This took a loooong time to implement... Sorry that there isn't much else here... ;_;</ul>"
			+"<ul><b>Changed:</b> Importing your character no longer carries over your slaves (and doesn't carry over pregnancies either). This is due to inter-NPC relationships getting quite complicated, so, for example, there would be several serious issues with importing an enslaved Scarlett into a new game, and then meeting the new game's version of Scarlett.</ul>"
			+"<ul>Did a lot of general back-end fixes in numerous parts of the code.</ul>"
			+"<ul>Added support for multiple pages of responses (for use in the new Sex UI).</ul>"
				
			+"<li>UI:</li>"
			+"<ul>Further improved the Sex UI. Thank you for all of your feedback regarding this!</ul>"
			+"<ul>Added an 'Export/Import' tab in the main menu, through which you can use the new Game Export. ^^</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>Filled condoms should now correct import with all cum properties intact.</ul>"
			+"<ul>Penile virginity is now correctly lost upon performing a penetrative sex action.</ul>"
			+"<ul>Fixed broken lubrication messages during sex.</ul>"
			
		+"</list>"

		
		+ "</br>"

		+ "<list>"
		
			+ "<h6>v0.1.89</h6>"
		
			+"<li>Engine:</li>"
			+"<ul>Added more detail to the game's 'CoverableArea' system, to allow for clothing to block ass/anus and breasts/nipples separately.</ul>"
			+"<ul>Added in a lot of the background framework for Zaranix into the engine. I'll get this finished asap. ;_;</ul>"
			
			+"<li>Save game compatibility:</li>"
			+"<ul>Fixed several major bugs associated with loading in a game where you had filled condoms in your inventory.</ul>"
			+"<ul>Fixed major bug related to loading an exported game with pregnant NPCs in it.</ul>"
			+"<ul>Fixed pregnancy 'potential partners as father' not being imported correctly.</ul>"
			+"<ul>Fixed incorrect cell inventory size.</ul>"
			+"<ul>Added support for generating missing maps.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Submitting to an NPC after defeating them no longer locks out penetrative sex actions.</ul>"
			+"<ul>You can now access the inventory of defeated alleyway attackers, succubi, offspring, and harpies after sex.</ul>"
			+"<ul>Split the 'Non-con' fetish up into giving & receiving variants. NPCs with the receiving variant will prefer to resist during sex (if non-con is enabled).</ul>"
			+"<ul>Reduced the default amount of children birthed by each race, so most now give birth to one or two offspring, with the current maximum litter size possible being 16 (for harpies).</ul>"
				
			+"<li>Sex:</li>"
			+"<ul>Added a generic 'Stop penetrations' action to stop all ongoing penetrative actions at once.</ul>"
			+"<ul>Changed sex action's associated fetishes to make them more consistent.</ul>"
			+"<ul>NPCs will no longer try to get access to their penis/pussy if they don't have one.</ul>"
				
			+"<li>UI:</li>"
			+"<ul>Added hotkey support for response tab switching. By default, 'Previous tab' is 'ctrl+Q', while 'Next tab' is 'ctrl+E'. (Can be changed in the options menu.)</ul>"
			+"<ul>Added indication in enchanting menu of the exact value that an effect will apply. (e.g. Will now display cum increases in ml.)</ul>"
			+"<ul>Improved inventory UI to use tabs. (I still have some work left to do on this.)</ul>"
				
			+"<li>Other:</li>"
			+"<ul><b>Reverted:</b> NPCs will now beg for you to take their virginity if they're in the 'eager' pace, will inform you that they're a virgin in the 'normal' pace, and will beg for you not to take it in the 'resisting' pace.</ul>"
			+"<ul>Slightly changed the secondary modifiers for enchanting body parts. Now, instead of leaving the secondary essence blank to transform your body part, you select a 'Transformative' essence.</ul>"
			+"<ul>Improved horn transformations, and added several more colours to horns (available colours are now white, red, black, and grey).</ul>"
			+"<ul>Changed horn types from 'demon' and 'bovine' into some more specific types: curled, spiral, curved, swept-back, and straight.</ul>"
			+"<ul>Added horn length.</ul>"
			+"<ul>Reduced offspring spawn chance from 25% to 20%.</ul>"
			+"<ul>Added penile virginity loss descriptions during sex, as well as experience gain for the other partner if they have the 'Deflowering' fetish.</ul>"
			+"<ul>Added submissive sex option in slaves' rooms.</ul>"
			+"<ul>NPCs with 'Transformation test subject' fetish now gain affection from being assigned as Lilaya's test subject.</ul>"
			+"<ul>Penises will only get lubricated by precum/cum if they're not covered with a condom. (Although the urethra will still get lubricated regardless of condom status.)</ul>"
			+"<ul>The 'Repeat action' in combat will now only show actions that aren't already in the main combat menu (i.e. spells and special attacks).</ul>"
			+"<ul>The 'Calm down' sex action has had its arousal reduction strength doubled from -5 to -10.</ul>"
			+"<ul>Added 'pixie cut', 'bob cut', and 'slicked back' hair styles.</ul>"
			+"<ul>Changed Rose's hair style.</ul>"
			+"<ul>Added pure damage/resistance to the stats screen.</ul>"
			+"<ul>You can now use fetish TF potions on the harpy matriarchs.</ul>"
			+"<ul>Alleyway Succubi will now use their special tease attacks.</ul>"
			+"<ul>Partners in consensual sex will no longer end sex once they've orgasmed. They will continue until you've orgasmed as well.</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>In the crafting screen, you can now change the potency of milk, cum, and girlcum enchantments.</ul>"
			+"<ul>Fixed bug where offspring would spawn twice in the same tile.</ul>"
			+"<ul>Fixed bug where swapping positions during sex would incorrectly display ongoing penetration text.</ul>"
			+"<ul>Fixed duplicate virginity loss descriptions in selfie/character view.</ul>"
			+"<ul>Fixed incorrect virginity loss place descriptors.</ul>"
			+"<ul>Ongoing penetration description fixes.</ul>"
			+"<ul>Fixed UI scaling bug in Fetish screen.</ul>"
			+"<ul>Fixed more strange floating point numbers in slavery management screens, as well as the main stats screen. (i.e. Things like 2.3999999 should now display as 2.40.)</ul>"
			+"<ul>Fade-in text is now correctly off by default.</ul>"
			+"<ul>'Lubricate fingers' self-action now returns the correct text.</ul>"
			+"<ul>Masculine transformations (when letting Lilaya use someone as a test subject) will no longer grow breasts, and will now correctly shrink them.</ul>"
			+"<ul>Fixed issue with Vicky sometimes not buying essences, and with Ralph sometimes not buying condoms.</ul>"
			+"<ul>Fixed UI issue with pregnancy stats screen and slave inspection screen.</ul>"
			+"<ul>The stats tooltip that shows up when you mouse over your name now shows the correct value of your global resistance.</ul>"
			+"<ul>Breast kissing can no longer be performed if breasts are not exposed.</ul>"
			+"<ul>Fixed levelling up method to enable gaining multiple levels at once.</ul>"
			+"<ul>Improved arousal increases from ongoing status effects, and fixed incorrect arousal tooltip values.</ul>"
			+"<ul>Lilaya will now always ask you to pull out.</ul>"
			+"<ul>Fixed incorrect NPC reactions just before you reach your orgasm.</ul>"
			+"<ul>You should now be able to use the 'Pull out' orgasm if your partner asked for you to pull out.</ul>"
			+"<ul>Fixed bug in sex where moving into a position would cause your partner to continuously do nothing.</ul>"
			+"<ul>Fixed potentially game-breaking bug related to Brax's clothing being unequipped after defeating him.</ul>"
			+"<ul>Fixed storm immunity and items disappearing inconsistencies.</ul>"

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

		credits.add(new CreditsSlot("Anonymous", "", 0, 3, 68, 25));
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 2));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 2));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Enigamatic Yoshi", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 4, 0));
		
		
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 1, 0));
		
		 
		
		
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
