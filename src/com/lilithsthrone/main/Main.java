package com.lilithsthrone.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DebugDialogue;
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
 * @version 0.2.0
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.0.5",
			VERSION_DESCRIPTION = "Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone! Sorry that this release was a little late... :("
		+ "</p>"
			
		+ "<p>"
			+ "I've managed to get a lot of work done on adding Submission, Imps, and slimes, but I underestimated how long it would take to get them all working fully..."
			+ " As a result, I haven't managed to get the lactation content finished just yet, but it will be added for the full version next week!"
		+ "</p>"

		+"<p>"
			+ "<b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Please be aware that this is a preview build, and most of the content is only half-done!</b>"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.0.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Please note that a lot of this is half-finished in this version!</b></ul>"
			+"<ul><b>Improved:</b> Job selection in character creator, and added an associated perk for each job.</ul>"
			+"<ul><b>Added:</b> *Basic* map of Submission. This will be expanded upon and improved throughout version 0.2. Most of the descriptions are still TODO!</ul>"
			+"<ul><b>Added:</b> Imp race.</ul>"
			+"<ul><b>Added:</b> Slime race. (5% chance of finding slime TF item in Submission's tunnels.)</ul>"
			+"<ul>Demons should now correctly produce imp offspring, not more demons.</ul>"
			+"<ul>All characters now have a 'resting lust' value, and over time will gain/lose lust to realign with this value. (Resting lust = Corruption/2)</ul>"
			+"<ul>Increased amount of experience gained after beating an NPC in combat from 2*level to 10*level.</ul>"
			+"<ul>Alligator-morphs are now only found in Submission.</ul>"

			+"<li>Combat:</li>"
			+"<ul>When a character is suffering from the 'Desperate for sex' status effect, incoming lust damage is now converted to 2*Energy damage and 1*Aura Damage. (So a seduction attack that would deal 15 lust damage now deals 30 energy damage and 15 aura damage.)</ul>"

			+"<li>Sex:</li>"
			+"<ul><b>Improved:</b> NPCs should no longer insist on fingering you through the entirety of sex scenes, and should now prioritise penis/tail actions once they get past 25 arousal. They will still finger you during the 'foreplay' (under 25 arousal) section of sex.</ul>"
			+"<ul>Partners should no longer re-penetrate the player on the same turn that you stop them from penetrating you.</ul>"
			+"<ul>Halved the arousal reduction from the generic 'resist' action in sex.</ul>"
			+"<ul>Slaves should no longer be able to end sex in the side-by-side scene.</ul>"
			+"<ul>Obedient slaves and submissive NPCs will now always obey your requests to pull out or creampie you.</ul>"
			+"<ul>Added 'No request' reaction to partner orgasming.</ul>"

			+"<li>Other:</li>"
			+"<ul>Slightly improved encyclopedia's race page to show subspecies.</ul>"
			+"<ul>Boosted lab assistant slave job income from 10 to 100 per hour.</ul>"
			+"<ul>Ordered colours in dye menu.</ul>"
			+"<ul>Fixed character having 40 arcane in the intro.</ul>"
			+"<ul>NPCs now tell you what they're trying to turn you into when they force TF potions on you.</ul>"
			+"<ul>Improved multiple-tab switching behaviour, so now you won't end up on a blank page of actions.</ul>"
			+"<ul>Resting and washing no longer reset lust to 0.</ul>"
			+"<ul>Balanced room upgrade costs.</ul>"
			+"<ul>Added 'girth' as a minor transformation to penis. (It is just for descriptions at the moment - I will most likely include a mechanical impact later on.)</ul>"
			+"<ul>Offspring should now only spawn in suitable locations (i.e. imps/alligator-morphs only spawn in Submission).</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Added throat capacity, elasticity, plasticity, and wetness to available enchantments. (CondingAnon)</ul>"
			+"<ul>Added a significant amount of extra colour availability to clothing & skin. (Master of Puppets)</ul>"
			+"<ul>Fixed bug where clothing dyed in secondary/tertiary colours wouldn't be loaded correctly. (Master of Puppets)</ul>"
			+"<ul>Fixed bug where after reaching the maximum limit of a clothing TF enchantment, it would revert back to the minimum value. (riwigica & Master of Puppets)</ul>"
			+"<ul>Fixed import compatibility issue for potions. (Master of Puppets)</ul>"
			+"<ul>Fixed import error caused by null values in PlayerCharacter's booksRead Set. (Master of Puppets)</ul>"
			+"<ul>Applied encoding compatibility for special characters. (CognitiveMist)</ul>"
			+"<ul>Fixed 'Blog' and 'Github' buttons in the options menu causing the game to hang on Linux systems. (picobyte)</ul>"
			+"<ul>Fixed the 'Witch's Toy' sex scene (after losing to the Cultist) not working. (Master of Puppets)</ul>"
			+"<ul>Typo fixes. (Woeful Wombat)</ul>"
			+"<ul>Added glossy, matte, metallic, and sparkly modifiers to makeup. (Master of Puppets)</ul>"
			+"<ul>Fixed dirty clothing icon not showing for clothing in inventory. (picobyte)</ul>"
			+"<ul>Fixed prologue NPC human status effect showing incorrect text. (Master of Puppets)</ul>"
			+"<ul>Added more cum target availability in several sex positions. (Master of Puppets)</ul>"

			+"<li>Bugs:</li>"
			+"<ul><b>Fixed:</b> Huge bug where sex actions belonging to your partner would be available for you to use.</ul>"
			+"<ul>Fixed cause of bug where you wouldn't be able to select clothing.</ul>"
			+"<ul>Fixed NPCs using special attacks when they weren't supposed to be available.</ul>"
			+"<ul>Unzipped 'frontal-zip dress' should now correctly provide access to your groin.</ul>"
			+"<ul>Fixed lack of rounding in some tooltips.</ul>"
			+"<ul>Tooltips should now appear for the sex actions that run over into a second page.</ul>"
			+"<ul>Fixed duplicated sex action appearing in slot 1 on second page of sex actions.</ul>"
			+"<ul>Fixed detection of subspecies not always being applied correctly (the game should now correctly detect whe you TF into a Dobermann-morph).</ul>"
			+"<ul>Fixed dog-morph status effect never showing up as a Dobermann-morph.</ul>"
			+"<ul>Stroke pussy and stroke cock now have the same associated corruption (vanilla).</ul>"
			+"<ul>Fixed but where resetting perk points would retain stat increases.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Vaginal tease is now only available when your targeted opponent has a vagina.</ul>"
			+"<ul>Fixed penis descriptors being applied globally.</ul>"
			+"<ul>Fixed 'Seductive look' in doggy style being available for all targeted partners.</ul>"
			+"<ul>Fixed some bugs in Karl & Wolfgang's sex scene.</ul>"
			+"<ul>Fixed NPC ordering in sex scenes not matching intended order.</ul>"
			+"<ul>Fixed thighs not being detected as a plural.</ul>"
			+"<ul>Fixed NPC's alcohol intake message.</ul>"
			+"<ul>Fixed incorrect initial penetration descriptions.</ul>"
			+"<ul>Fixed NPCs spawning in with incorrect stats for their race.</ul>"
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
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		credits.add(new CreditsSlot("Anonymous", "", 0, 6, 111+61, 37+18));
		
		
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 3));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 3));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 3, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 2, 0));
		
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 2));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 7, 0));
		
		
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
		
		// Open error log
		try {
			System.setErr(new PrintStream("error.log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	// Yes, this is probably a stupid way to do it... x_x
	public static boolean isVersionOlderThan(String versionToCheck, String versionToCheckAgainst) {
		String[] v1 = versionToCheck.split("\\.");
		String[] v2 = versionToCheckAgainst.split("\\.");
		
		try {
			int v1i1 = Integer.valueOf((v1[0]+"00").substring(0, 3));
			int v1i2 = Integer.valueOf((v1[1]+"00").substring(0, 3));
			int v1i3 = 0;
			int v1i4 = 0;
			if(v1.length>2) {
				v1i3 = Integer.valueOf((v1[2]+"00").substring(0, 3));
			}
			if(v1.length>3) {
				v1i4 = Integer.valueOf((v1[3]+"00").substring(0, 3));
			}
			int v2i1 = Integer.valueOf((v2[0]+"00").substring(0, 3));
			int v2i2 = Integer.valueOf((v2[1]+"00").substring(0, 3));
			int v2i3 = 0;
			int v2i4 = 0;
			if(v2.length>2) {
				v2i3 = Integer.valueOf((v2[2]+"00").substring(0, 3));
			}
			if(v2.length>3) {
				v2i4 = Integer.valueOf((v2[3]+"00").substring(0, 3));
			}
			
			if(v1i1 < v2i1) {
				return true;
			} else if(v1i1 == v2i1) {
				if(v1i2 < v2i2) {
					return true;
				} else if(v1i2 == v2i2) {
					if(v1i3 < v2i3) {
						return true;
					} else if(v1i3 == v2i3) {
						if(v1i4 < v2i4) {
							return true;
						}
					}
				}
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

	public static boolean isSaveGameAvailable() {
		return Main.game.isStarted() && Main.game.getSavedDialogueNode() == DebugDialogue.getDefaultDialogueNoEncounter();
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
		
		Game.exportGame(name, allowOverwrite);

		try {
			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininityValue()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName();
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.arcaneEssences = game.getPlayer().getEssenceCount(TFEssence.ARCANE);
			properties.race = game.getPlayer().getSubspecies().getName();
			properties.quest = game.getPlayer().getQuest(QuestLine.MAIN).getName();

			properties.savePropertiesAsXML();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isLoadGameAvailable(String name) {
		File file = new File("data/saves/"+name+".xml");

		return file.exists();
	}
	
	public static void loadGame(String name) {
		if (isLoadGameAvailable(name)) {
			Game.importGame(name);
		}
	}
	
	public static void deleteGame(String name) {
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
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
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
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer(), CharacterImportSetting.NO_PREGNANCY));
				
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
