package com.lilithsthrone.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.5",
			VERSION_DESCRIPTION = "Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello once again!"
		+ "</p>"
			
		+ "<p>"
			+ "For this version, I've made some improvements to slavery (mostly milking room enhancements), added a way to save and load enchantments (build upon a PR from Master of Puppets), and added a small amount of in-game Submission content."
			+ " I've got a lot more Submission content done behind-the-scenes (mainly a large side quest involving slimes and the slime TF item), but I didn't manage to get it finished for this release."
		+ "</p>"
			
		+ "<p>"
			+ "I'm going to continue writing Submission content over the next five days, and should have it ready for 0.2.5.5. Then I'll move on to Nightlife content, so it will all be ready and in the game for 0.2.6."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.4.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Optimised save files. In the ones I tested, file size was reduced by over 50%, and load times saw a similar decrease.</ul>"
			+"<ul>Added support for masturbation sex scenes.</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Added: Panty masturbation scene in Lilaya's room, with a couple of follow-on sex scenes.</ul>"

			+"<li>Balancing:</li>"
			+"<ul>You now need at least three spells in a spell school to unlock the related special ability.</ul>"
			+"<ul>Spell books are now consumed on use, and added to Lilaya's library. (This was to prevent buying a spell book, learning the spell, and then selling it, making it significantly cheaper than intended to buy & learn all spells.)</ul>"
			+"<ul>Made default bottled arcane essence slightly cheaper than the racially enchanted ones.</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Strap-on, and support in the engine for correctly treating a strap-on as an artificial penis during sex. (BDSM set, no femininity requirements, penis slot.)</ul>"
			+"<ul>Added: Toeless striped stockings. (Feminine, calves slot.)</ul>"
			+"<ul>Added: Tube top. (Feminine, chest slot.)</ul>"
			+"<ul>Added more tags for clothing, including ones for plug support. Added plug modifier to nipple tape crosses (prevents cum from leaking out).</ul>"
			+"<ul>Added fertility and virility to available clothing attribute enchantments.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Fixed typos in air elemental upgrades. (rfpnj)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added '+10 spell points' to debug menu.</ul>"
			+"<ul>Improved some of the demon/slime transformation menu options. Added options for internal testicles, horn size, tongue length, wing size, and horn colour.</ul>"
			+"<ul>Added more options for horn pattern and colour.</ul>"
			+"<ul>Improved item, weapon, and clothing pages in the encyclopedia, and added preview images for items.</ul>"
			+"<ul>You can now re-read race books, but you only receive the damage/resistance boost the first time. This should now allow new characters to gain these bonuses, even if races have been unlocked by a different playthrough.</ul>"
			+"<ul>The 'vacuum' spell can no longer strip a person's piercings.</ul>"
			+"<ul>NPCs will only re-dress themselves once a day.</ul>"
			+"<ul>Vicky will now buy spell books and scrolls.</ul>"
			+"<ul>Tidied up Lilaya's lab dialogue flow a little.</ul>"
			+"<ul>Added a chance to overhear Rose saying some things to Lilaya when you enter the lab.</ul>"
			+"<ul>Added indication in slavery overview for approximate hourly income of milking jobs.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed annoying bug where when inspecting NPCs, it would show a different, incorrect NPC's name/inventory on the right of the screen. (This should definitely be completely fixed this time. x_x)</ul>"
			+"<ul>Fixed Arcane elemental spell cost being extremely low.</ul>"
			+"<ul>Fixed dresses and kimonos that were parts of sets still blocking the leg slot.</ul>"
			+"<ul>Fixed the broken encyclopedia item view.</ul>"
			+"<ul>Throat wetness should now correctly save/load.</ul>"
			+"<ul>Typos, parsing, and incorrect clothing displacement fixes.</ul>"
			+"<ul>Fixed slime sclera and pupil recolouring not working.</ul>"
			+"<ul>Resetting spell schools now refunds the correct amount of upgrade points.</ul>"
			+"<ul>Fixed arcane storm timer (from Arcane spell school's unique ability) not working correctly.</ul>"
			+"<ul>Fixed Water spell school's unique ability (liquid TFs are free) not working correctly.</ul>"
			+"<ul>Being defeated in combat while your allies are still fighting will now correctly only give you the option to watch.</ul>"
			+"<ul>Fixed bug where there would sometimes be gaps in actions during sex.</ul>"
			+"<ul>Fixed some incorrect skin colouring on Rose.</ul>"
			+"<ul>Improved NPC removal method, which should help to reduce save file size and save/load times. This will be further improved for 0.2.5.</ul>"
			+"<ul>(Hopefully) fixed a cause of the bug where the game would stop being able to save.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.4.5</h6>"
			+ "<li>Gameplay:</li>"
			+ "<ul>Filled in more of the placeholder text in Submission.</ul>"
			+ "<ul>Added more content for Submission Enforcer Post, including a new Enforcer NPC, 'Claire'. When entering a Submission Enforcer Post for the first time, Claire's dialogue will trigger. (There will be a quest related to this added for the next release.)</ul>"
			
			+ "<li>Slavery:</li>"
			+ "<ul>'Dairy Cow' job now correctly supports 8 slaves per milking room.</ul>"
			+ "<ul>Made some minor improvements to slavery UI.</ul>"
			+ "<ul>Milking room now milks a slave of their milk, cum, and girlcum at the same time.</ul>"
			+ "<ul>Added options to enable/disable milk, cum, and girlcum milking.</ul>"
			+ "<ul>Moved pregnancy job settings (Promiscuity pills, no pills, or Vixen's Virility pills) from the 'Stocks' and 'Prostitute' jobs, and moved them into a standard permission.</ul>"
			+ "<ul>Added options in milking room to set automatic selling, as well as options to drink or sell any stored fluids.</ul>"
			+ "<ul>Balanced fluid values.</ul>"
			+ "<ul>Added three new upgrades to the milking room.</ul>"
			+ "<ul>Added self-milking options to milking rooms.</ul>"
			
			+ "<li>Enchanting:</li>"
			+ "<ul>You can now set a custom name for any potions or clothing that you create.</ul>"
			+ "<ul><b>Added:</b> Saving and loading of enchantment effects. (Built upon framework created by Master of Puppets.)</ul>"
			
			+ "<li>Contributors:</li>"
			+ "<ul>Added testing for Nnxx's character 'Lumi' in the debug menu. Once she's finished, she will be added a random encounter in Dominion's alleys, but for now, she's only accessible through the debug menu.</ul>"
			+ "<ul>Fixed typos in Telepathic Communication description. (Mach565)</ul>"
			+ "<ul>Companion's damage scaling (on higher difficulty settings) is now treated the same as the player's. (Darkon47)</ul>"
			+ "<ul>Added rings for masculine characters in character creation. (rfpnj)</ul>"
			+ "<ul>Huge amount of punctuation fixes. (WoefulWombat)</ul>"
			+ "<ul>Even more punctuation fixes. (WoefulWombat)</ul>"
			+ "<ul>Added framework for saving and loading enchantment effects. (Master of Puppets)</ul>"
			+ "<ul>Fixed bug with intoxication percentages not displaying correctly. (Pimvgd)</ul>"
			+ "<ul>Fixed prologue typo. (Mach565)</ul>"
			+ "<ul>Fixed cause of strange characters being displayed in some svgs. (Pimvgd)</ul>"
			+ "<ul>Fixed bug where impregnation chance was using the wrong partner's fertility stat. (Itpatch)</ul>"
			
			+ "<li>Other:</li>"
			+ "<ul>Ankle spreader bar now blocks escape in combat (if you are unable to fly).</ul>"
			+ "<ul>Wrist restraints now block flight from arm-wings (such as harpy or bat wings).</ul>"
			+ "<ul>NPCs now gain or lose affection towards you after sex, depending on if they were in the resisting pace or not.</ul>"
			+ "<ul>Slimes can no longer fly.</ul>"
			
			+ "<li>Bugs:</li>"
			+ "<ul>Fixed bug where NPCs would have a random colour for their lips/nipples/anus. This resets NPC colours for those parts only if they're not your slave, so your slave's colours won't be reset by this.</ul>"
			+ "<ul>Typo and parsing fixes.</ul>"
			+ "<ul>Fixed issue with parsing some characters in text fields.</ul>"
			+ "<ul>Fixed spell page showing special ability was unlocked, even when it wasn't.</ul>"
			+ "<ul>Fixed bug where some icons would show random letters on some systems.</ul>"
			+ "<ul>Fixed (yet another) cause of slavery stats getting stuck on the right-hand section of the screen.</ul>"
			+ "<ul>Fixed broken colours in rainbow set status effect icon.</ul>"
			+ "<ul>Fixed bug where fetish screen would sometimes become unresponsive.</ul>"
			+ "<ul>NPCs should no longer spawn in with fetish or transforming fetishes if you have them set to 0% in your content options.</ul>"
			+ "<ul>Fixed milking room upgrade being blocked when you had companions in your party.</ul>"
			+ "<ul>Fixed slave multiple-partner sex breaking if you had companions in your party.</ul>"
			+ "<ul>Fixed bug where slaves could start spitroast even if they weren't attracted to you.</ul>"
			+ "<ul>Crotchless chaps no longer conceal the anus slot.</ul>"
			+ "<ul>Fixed yet more bugs related to slavery targets being broken between scenes.</ul>"
			+ "<ul>Fixed 'Dairy Cow' slave job having some settings from prostitute.</ul>"
			+ "<ul>Fixed bug where fluids would sometimes load in their modifiers twice (potentially causing issues with removal of fluid modifiers).</ul>"
			+ "<ul>Fixed slimes being able to TF penis type to 'artificial'.</ul>"
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

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Anonymous", "", 0, 6, 111+61, 37+18));
		
		
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 5));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 5));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 5, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 4, 0));
		

		credits.add(new CreditsSlot("Desgax", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 3));
		
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 2, 0));

		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 1, 0));
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 7));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 7));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 8, 1));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 7));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 6, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 3));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 8));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 4));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 9, 0));
		
		
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

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
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
		try {
			@SuppressWarnings("resource")
			PrintStream stream = new PrintStream("data/error.log");
			System.setErr(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

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
				int v1i = (i < v1.length) ? Integer.valueOf((v1[i]+"00").substring(0, 3)) : 0;
				int v2i = (i < v2.length) ? Integer.valueOf((v2[i]+"00").substring(0, 3)) : 0;
			
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
			
		} else if (Main.game.getCurrentDialogueNode().getDialgoueNodeType()!=DialogueNodeType.NORMAL) {
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
