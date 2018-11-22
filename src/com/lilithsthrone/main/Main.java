package com.lilithsthrone.main;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.FileUtils;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.12";
	public static final String VERSION_DESCRIPTION = "Alpha";
	
	/**
	 * To turn it on, just add -Ddebug=true to java's VM options. (You should be able to do this in Eclipse through Run::Run Configurations...::Arguments tab::VM Arguments).
	 * Help page: https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Flaunchers%2Farguments.htm
	 *  Or, from the command line java -Ddebug=true -jar LilithsThrone.jar
	 */
	public final static boolean DEBUG = Boolean.valueOf(System.getProperty("debug", "false"));

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");
	
	private static Properties properties;
	
	public static String patchNotes =
		
		"<p>"
			+ "Hello again!"
		+ "</p>"
			
		// If you're building this from github, please be aware that there will be a more detailed post on my blog soon
		+ "<p>"
			+ "There were a lot more bugs than I expected in 0.2.11.9, so all of my time was spent fixing those."
			+ " I haven't got the citadel content added yet, but it will definitely be done for the next version!"
		+ "</p>"
			
		+ "<p>"
			+ "I also decided to call this version '0.2.12' instead of '0.3', as I feel like it would be best to reserve that version number for the one that actually contains the new fields area..."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.12</h6>"
			+"<li>Artwork:</li>"
			+"<ul>Added FriendlyAlienFriend's Nyan artwork.</ul>"
			+"<ul>Added Jam's Arthur artwork.</ul>"

			+"<li>Contributors:</li>"
				+"<ul>Fixed a lot of typos, both in the code, as well as in-game dialogue. (#964 by Pimgd)</ul>"
		
			+"<li>Items:</li>"
			+"<ul>Improved pencil skirt icon.</ul>"
			+"<ul>Added 'feminine short sleeved shirt' as a replacement for Lilaya's unisex shirt. (Sold by Nyan.)</ul>"

			+"<li>Other:</li>"
			+"<ul><b>Reverted</b> currency display to the single 'flames' value. (It was a little too messy in many screens with the gold/silver/copper.)</ul>"
			+"<ul>Newly summoned elementals now share many appearance values with their summoner, and additionally spawn with wings large enough for them to fly.</ul>"
			+"<ul>Added 'pull down' displacements for skater, slip, and plunge dresses, where you shrug off the shoulder straps and pull the top down to reveal the chest area.</ul>"
			+"<ul>Cargo trousers can now be equipped/removed over shoes.</ul>"
			+"<ul>Slightly adjusted Lilaya's appearance, increasing her 'appears as' age to 32, her wing size from tiny to average, reduced her muscle from 'muscular' to 'lightly muscled', and fixed her legs to be human instead of demonic.</ul>"
			+"<ul>Removed Lilaya's headband (as I thought it would be uncomfortable for her to wear with her horns), gave her a watch, and she now wears an unbuttoned lab coat while in her lab.</ul>"
			+"<ul>Made minor changes to the prologue to reflect Lilaya's changes.</ul>"
			+"<ul>Increased maximum width of the UI's centre pane from 1000px to 1200px.</ul>"
			+"<ul>Increased starting money from 500 to 5000 flames.</ul>"
			+"<ul>Added metallic colours to all makeup. Disabled glowing makeup in character creation.</ul>"
			+"<ul>Added indication in transform menu that vagina cannot be transformed while pregnant.</ul>"
			+"<ul>Removed the 25 energy cost of milking yourself in a milking room.</ul>"
			+"<ul>Added options to pump fluids in slave's milking room into your vagina & anus. This can be used for self-insemination. There might be bugs if you remove your slave before self-impregnating with their cum.</ul>"
			+"<ul>Footjobs can now be given/received even if the feet are covered. Foot names are replaced with the name of the clothing touching the target's penis.</ul>"

			+"<li>Bugs:</li>"
			+"<ul><b>Fixed</b> stored fluids in slave milking rooms not saving details about whose milk/cum/girlcum it was. <b>All saved fluids in slave milking rooms will be deleted when you load into this version</b> as a result of this fix, so please sell/drink all stored fluids before loading into this version.</ul>"
			+"<ul>Fixed issue where you couldn't stop self actions if you were a submissive partner in some sex scenes.</ul>"
			+"<ul>Fixed issue where after defeating an imp fortress's guards, you'd be unable to re-enter the fortress's 'entrance' tile.</ul>"
			+"<ul>Fixed background error being thrown when slaves were having sex with one another while you were in an unrelated sex scene.</ul>"
			+"<ul>Fixed clothing not being replaced after sex, but also added an option to turn on/off this behaviour in the content settings. (Clothing replacement is on by default.)</ul>"
			+"<ul>Fixed issue in one of the fortress sex scenes where the imps were being treated as both participants and spectators.</ul>"
			+"<ul>Fyrsia's groin is now correctly exposed at the start of the post-'brawler' scene's sex.</ul>"
			+"<ul>Fixed money formatting to correctly display negative values.</ul>"
			+"<ul>Fixed elemental perk tree's 'chill' perk applying +5/+5 ice damage/resistance instead of the intended +1/+1.</ul>"
			+"<ul>Fixed issue with Jhortrax's post-sex 'Collapse' button sometimes not responding.</ul>"
			+"<ul>Fixed issue where character imports would break if they were exported while in possession of a modded weapon that is no longer in your mods folder.</ul>"
			+"<ul>Fixed issue with inventory management where sometimes clicking on clothing in the bottom-right of the game would cause major bugs. (Most noticeable in slavery management.) </ul>"
			+"<ul>Fixed parser errors in several of the 'generic NPC' dialogues.</ul>"
			+"<ul>Fixed major bug where attacking sadists and masochists who had full lust would cause the game to crash.</ul>"
			+"<ul>Fixed a bug where some special tease attacks would not work.</ul>"
			+"<ul>Fixed major issue where maximum energy and aura never reflected increases & decreases from enchanted clothing being worn or potions being consumed. Also changed energy/aura gained from physique/arcane and levels to apply to the 'bonus' value.</ul>"
			+"<ul>Fixed a cause of the issue where combat would keep repeating after beating Hyorlyss.</ul>"
			+"<ul>Fixed major issues with all imp fortress parsing if you cleared them with an elemental as your main companion.</ul>"
			+"<ul>Fixed a large amount of parsing errors in imp fortresses, mostly in the 'scare off' scenes after clearing the fortress through non-combat means.</ul>"
			+"<ul>Fixed more parser errors in imp fortresses, which were related to pacifying the guards, then clearing the fortress.</ul>"
			+"<ul>Imp fortress guards no longer respawn if the leader is already defeated.</ul>"
			+"<ul>Fixed issue where imp gate and boss guards would not spawn in fortresses until you'd entered and left once already. (If you didn't do the enter/leave, it was causing numerous parsing and background logic errors.)</ul>"
			+"<ul>Fixed some typos and grammatical errors.</ul>"
			+"<ul>Fixed errors occurring if you didn't have a companion with you when you used the non-combat bypass options in Fyrsia's and Hyorlyss's fortresses.</ul>"
			+"<ul>Elementals loaded in from versions prior to 0.2.11.9 will no longer have a non-elemental job perk. (Which was causing their physique to be 0.)</ul>"
			+"<ul>Fixed combat loss sex scenes with Hyorlyss not working.</ul>"
			+"<ul>Fixed issue with Hyorlyss's dominant sex scenes not starting if she couldn't get access to her groin.</ul>"
			+"<ul>Jinxed clothing equips after imp fortress combat-defeat sex has been moved to be applied when you click to 'Continue', so that the jinxed effect isn't prematurely displayed.</ul>"
			+"<ul>Lilaya will now correctly return to wearing her usual lab clothes after her geisha scenes.</ul>"
			+"<ul>Fixed some issues with status effects while in the character creation.</ul>"
			+"<ul>Fixed clothing mods that use the old equip/unequip description format having their equip/unequip text defaulting to the generic ones.</ul>"
			+"<ul>Fixed Jhortrax only having 10 arcane. (He now has 35.)</ul>"
			+"<ul>Fixed issue with the 'arcane cloud' spell not applying damage to targets who have the 'Desperate for sex' debuff.</ul>"
			+"<ul>Fixed lust damage from masochist and sadist fetishes not taking into account lust resistance.</ul>"
			+"<ul>Fixed 'seductive look' not being available for dominant partners in doggy style sex.</ul>"
			+"<ul>Fixed being able to use the 'Spread legs' action in missionary when targeting someone other than the character between your legs.</ul>"
			+"<ul>Fixed incorrect display of slave's daily earnings when assigned to be milked.</ul>"
			+"<ul>Fixed shop overflow dropping on the floor, which was caused by too many modded weapons/clothing being used. (Available items are randomly chosen to be stocked, so if you don't see some of your mods, try again the next day.)</ul>"
			+"<ul>Fixed incorrect condom equip descriptions in sex.</ul>"
			+"<ul>'Biojuice Canister' and 'Bubblegum Lollipop' items are now correctly marked as being transformative, meaning that they cannot be used on non-slave unique NPCs.</ul>"
			+"<ul>Fixed quest clothing having enchantment postfixes automatically appended to the name.</ul>"
			+"<ul>Fixed issue with quest clothing and weapons not being placed into quest inventory if regular inventory was full.</ul>"
			+"<ul>Fixed another issue with masochist/sadist fetishes causing freezes in combat.</ul>"
			+"<ul>Fixed issue with lust damage information not being appended correctly in combat.</ul>"
			+"<ul>Fixed debug menu's transform options for legs/face/arms being limited.</ul>"
			+"<ul>Fixed parsing error when using the 'True Nympho' option in Hyorlyss's fortress.</ul>"
		+ "</list>"
	;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

			+ "<p>This game is a <b>fictional</b> text-based erotic RPG. All content contained within this game forms part of a fictional universe that is not related to real-life places, people or events.<br/><br/>"

			+ " All of the characters that appear in this story are fictional entities who have given their consent to appear and act in this story."
			+ " If you find yourself concerned for the characters in the story then please be reassured that they are all consenting adults who are speaking lines from a script."
			+ " None of the characters portrayed within this game were or are being harmed in any way during the construction or execution of this game."
			+ " Every character in the game is at least 18 years of age (or is magically the legal age needed to appear in erotic literature in whatever country you are playing this).<br/><br/>"

			+ "By agreeing to this disclaimer and playing this game you agree to be exposed to graphic sexual and adult content that is presented as part of the game's fictional universe."
			+ " Such content consists of, but is not limited to; graphic depictions of sex, inter-species sex (with fantasy creatures), sexual transformation,"
			+ " rape fantasy/implied lack of consent, mild physical violence, sexual violence, and drug use.<br/>"
			+ "Extreme fetish content such as gore/extreme violence, scat, and under/questionable age, is <i>not</i> a part of this game.<br/><br/>"

			+ "By agreeing to this disclaimer and playing this game you also agree that you are <b>at least 18 years of age</b>,"
			+ " or at least the legal age for you to purchase and view pornographic material in your country if that age is over 18.<br/><br/>"

			+ "As a final note, the creators of this game wish to stress that the content presented within is entirely fictional and does not reflect any of their personal views or opinions."
			+ " This game has been made in the spirit of creating a piece of artistic interactive literature, and it is imperative that you maintain a clear distinction between reality and the fictional events depicted in this game.</p>";
	
	public static List<CreditsSlot> credits = new ArrayList<>();

	// World generation:
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Anonymous", "", 99, 99, 99, 99));

		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alvinsimon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 12));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Anonymous_Platypus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 2, 11));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 5));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Tieria", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 12));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Captain_Sigmus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 9, 5));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Darthsawyer", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 8, 1));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("suka", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Aceofspades", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Assiyalos", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kaerea", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Tappi", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Karlimero", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 4));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Littlemankitten", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Mega", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Seo Leifthrasir", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 8));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 6, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Sir beans", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 12, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("The Brocenary", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TKaempfer", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 8));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("SolarEidolon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 9));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Weegschaal", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Drahin", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Wolfrave", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 9));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;
		
		Main.primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if(t) {
					TooltipUpdateThread.cancelThreads = true;
				}
			}
		});

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION+(DEBUG?" (Debug Mode)":""));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.hasValue(PropertyValue.lightTheme)) {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
		}

		mainController = loader.getController();
		Main.primaryStage.setScene(mainScene);
		Main.primaryStage.show();
		Main.game = new Game();
		Main.sexEngine = new Sex();
		
		loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().hasValue(PropertyValue.lightTheme))
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
	
	protected static void CheckForDataDirectory() {
		File dir = new File("data/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.ERROR, "Unable to find the 'data' folder. Saving and error logging is disabled.\nMake sure that you've extracted the game from the zip file, and that the file has write permissions.\nContinue?",
					ButtonType.YES, ButtonType.NO);
			a.showAndWait().ifPresent(response -> {
			     if (response == ButtonType.NO) {
			         System.exit(1);
			     }
			 });
		}
	}
	
	protected static void CheckForResFolder() {
		File dir = new File("res/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.WARNING, "Could not find the 'res' folder. This might cause errors and present sections of missing text.\nContinue?", ButtonType.YES, ButtonType.NO);
			a.showAndWait().ifPresent(response -> {
				if(response == ButtonType.NO)
				{
					System.exit(1);
				}
			});
		}
	}

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
		// Open error log
		if(!DEBUG) {
			try {
				@SuppressWarnings("resource")
				PrintStream stream = new PrintStream("data/error.log");
				System.setErr(stream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
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
						if (Main.getProperties().hasValue(PropertyValue.lightTheme))
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

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
			int maxlength = (v1.length > v2.length) ? v1.length : v2.length;
			for (int i = 0; i < maxlength; i++) {
				int v1i;
				int v2i;
				
				if(v1[1].charAt(0)=='1') { // Versions prior to 0.2.x used an old system of the format: 0.1.10.1 being a lower version than 0.1.9.1:
					v1i = (i < v1.length) ? Integer.valueOf((v1[i]+"00").substring(0, 3)) : 0;
					v2i = (i < v2.length) ? Integer.valueOf((v2[i]+"00").substring(0, 3)) : 0;
					
				} else { // Versions of 0.2.x and higher use a new system of the format: 0.2.10.1 being a higher version than 0.2.9.1:
					v1i = (i < v1.length) ? Integer.valueOf(v1[i]) : 0;
					v2i = (i < v2.length) ? Integer.valueOf(v2[i]) : 0;
				}
				
				if (v1i < v2i) {
					return true;
				} else if (v1i > v2i) {
					return false;
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
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType()!=DialogueNodeType.NORMAL) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Can only quicksave in a normal scene!");
			
		} else if (!Main.game.isStarted() || !Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogueNoEncounter())) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Cannot save in this scene!");
			
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
		return Main.game.isStarted() && Main.game.getSavedDialogueNode() == Main.game.getDefaultDialogueNoEncounter();
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
			properties.race = game.getPlayer().getSubspecies().getName(game.getPlayer());
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
		if (FileUtils.deleteFile("data/saves/"+name+".xml")) {
			game.setContent(new Response("", "", game.getCurrentDialogueNode()));
		}
	}
	
	public static void deleteExportedCharacter(String name) {
		if (FileUtils.deleteFile("data/characters/"+name+".xml") && FileUtils.deleteDirectory("data/characters/"+name)) {
			game.setContent(new Response("", "", game.getCurrentDialogueNode()));
		}
	}
	
	public static List<File> getSavedGames() {
		return FileUtils.listFiles("data/saves", "*.xml");
	}
	
	public static List<File> getCharactersForImport() {
		return FileUtils.listFiles("data/characters", "*.xml");
	}
	
	public static void importCharacter(File xml, String folder) {
		if (xml != null) {
			try {
				Main.game.setPlayer(CharacterUtils.startLoadingCharacterFromXML());
				FileUtils.copyDirectory("data/characters/" + folder,
                        "res/images/characters/" + game.getPlayer().getArtworkFolderName());
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(xml, Main.game.getPlayer(),
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY));
				
				Main.game.getPlayer().getSlavesOwned().clear();
				Main.game.getPlayer().endPregnancy(false);
				
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
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
