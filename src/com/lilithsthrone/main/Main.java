package com.lilithsthrone.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
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
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.sex.Sex;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.3";
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
			
		+ "<p>"
			+ "Once again I'm sorry for the delay on this; there was a significant amount of lore that I had to go over and check before adding Lyssieth's content, which I hadn't properly looked at since around v0.1."
			+ " That took quite a lot of time, as did actually writing the Lyssieth quest resolution dialogue (sorry that it's a big info dump - I already cut it back once, and struggled to edit it any smaller)."
			+ " I also had to make quite a few changes to the code in order to get the demon subspecies working, so, in all, there was far more work to do than I originally planned for."
		+ "</p>"
			
		+ "<p>"
			+ "While there is a world map in this (which you see after completing Lyssieth's section of the main quest), you cna't travel to the fields just yet, as I simply ran out of time in which to add content for it."
			+ " There are also some placeholders in Lyssieth's palace, and Lilaya's reactions to your demon transformation are missing."
			+ " All of that will make it into v0.3.1, along with bug fixes and other things."
		+ "</p>"
			
		+ "<p>"
			+ "I need to take a couple of days to be with family over Christmas, but I will be back and working on v0.3.1 as soon as I can! I hope you have a Merry Christmas, and a happy new year!"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Your character's demonic parts will be reset to human</b> when you load into this version. Demonic transformations are now only attainable through lore-correct methods (i.e. being corrupted into a demon by a lilin).</ul>"
			+"<ul>Removed item 'Innoxia's gift' and any crafted elixirs that were made using this item. (Again, to align demon transformations to the game's lore.)</ul>"
			+"<ul>If a character is not a demon, then demonic transformations applied while being a slime are reset to human upon returning to flesh. Non-demonic transformations are reset to demonic ones in a similar fashion if the character is a demon.</ul>"
			+"<ul>Demons and all subspecies are now correctly immune to all transformation potions.</ul>"
			+"<ul>Tidied up, expanded, and fixed bugs in Elizabeth's dialogue.</ul>"
			+"<ul>If you have completed the Siren's quest up to returning to Elizabeth in a previous version, your quest progress will be reverted one step (so you just need to return to her again), and you will be moved back out from the gates or the palace tile into one of the palace cavern tiles. (So that you can see the new handing-in quest dialogue, as well as to fix issues with being too far ahead.)</ul>"
			+"<ul>Added Lyssieth as an in-game NPC, along with a map of her palace.</ul>"
			+"<ul>Added final part of Dominion/Submission quest, where you meet Lyssieth.</ul>"
			+"<ul>If you have the masochist fetish, you now gain 1 essence every time you are critically hit in combat. If you have the sadist fetish, you now gain 1 essence every time you critically hit an opponent.</ul>"
			+"<ul>Changed the game's opening to explicitly state that the museum is in London.</ul>"
			+"<ul>Added 'maps' menu to phone, so you can view every area you've been to at any time. (You will need to re-visit areas to unlock them if you're carrying on from a previous save.)</ul>"
			+"<ul>Added a world map, which is unlocked after Lyssieth's content. Travel to other areas isn't in just yet, but it will be in the next version.</ul>"
			+"<ul>Added lore-friendly demon TF for the player, via Lyssieth content. Lilaya & Meraxis reactions, along with more demon TF content, will come in the next version as well.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Fixed incorrect labelling and descriptions in the demon alleyway attacker quick transformation scene. (#985 by ChillaChris)</ul>"
			+"<ul>Minor description fix. (#986 by ChillaChris)</ul>"
			+"<ul>Fixed modding clothing's 'equipped' image variant not working. (#988 by CognitiveMist)</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added a 'drab green' colour for clothing.</ul>"
			+"<ul>Added: 'plunge blouse' (Feminine, torso slot.)</ul>"
			+"<ul>Added: 'asymmetrical skirt' (Feminine, leg slot.)</ul>"
			+"<ul>Added: 'strappy stiletto sandals' (Feminine, foot slot.)</ul>"
			+"<ul>Added: 'lacy thong' (Feminine, groin slot.)</ul>"
			+"<ul>Added: 'feminine blazer' (Feminine, over-torso slot.)</ul>"
			+"<ul>Added: 'half-rim glasses' (Androgynous, eye slot.)</ul>"
			+"<ul>The butler outfit is now classed as being androgynous.</ul>"

			+"<li>Other:</li>"
			+"<ul>Expanded lore in the 'Lilith's Dynasty' and 'arcane arousal' books in the library, and organised library actions.</ul>"
			+"<ul>Tidied up Rose's dialogue about the world.</ul>"
			+"<ul>Updated demon lore in the encyclopedia, adn slightly expanded slime lore to make mention of their body's hydrophobic membrane.</ul>"
			+"<ul>Submissive partners in sex can no longer remove sex toys from their partner. (Dildos, and anything that plugs or seals orifices are considered sex toys.)</ul>"
			+"<ul>Added subspecies and proper detection for half-demons, lilin, and elder lilin.</ul>"
			+"<ul>Slightly improved background code for offspring and litters - I did test this a lot, but if you run into any issues with pregnancies, please let me know!</ul>"
			+"<ul>Changed Lilaya's post-sex dialogue to remove references to arcane-related lore that ended up not being used.</ul>"
			+"<ul>Added foot structure options to demon/slime transform menu, along with numerous other minor changes and improvements.</ul>"
			+"<ul>Added covering patterns 'marked', 'mottled', 'spotted', and 'striped' for penis.</ul>"
			+"<ul>Changed transformation references of 'breasts' and 'ass' to 'nipples' and 'anus', as that's all the transformation is affecting. (Breast and ass coverings are based on your torso's covering.)</ul>"
			+"<ul>Added some more descriptors for breasts and ass to draw from for when being described.</ul>"
			+"<ul>Tidied up code in a lot of places.</ul>"
			+"<ul>Changed the siren's name from Mhyralyss to Meraxis. (I was finding the spelling of 'Mhyralyss' to be a little too cumbersome.)</ul>"
			+"<ul>Dark alleyway demons now obey the same rules as all other NPCs when it comes to using items on them.</ul>"
			+"<ul>Subspecies lore books have had their icons changed.</ul>"
			+"<ul>Added half-demon lore book to Lilaya's library.</ul>"
			+"<ul>Lilaya now tells you to remember to pull out at the start of sex.</ul>"
			+"<ul>Changed 'Obeys lilin' effect at maximum corruption to 'Demonic mindset'. (A requirement to be turned into a demon.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed potential issue with radial gradients in images not being coloured correctly.</ul>"
			+"<ul>Fixed issue with null pointer exception being thrown in some sex scenes, which was causing sex to freeze.</ul>"
			+"<ul>Fixed issue with not being able to progress past one action with dominant partners in the Watering Hole (everything should be working correctly in there again now).</ul>"
			+"<ul>Fixed issue where equipping clothing during sex would sometimes result in an error being thrown when sex finished.</ul>"
			+"<ul>Fixed some days and months being displayed in the language of your computer's default language instead of English.</ul>"
			+"<ul>Fixed issue where all weapons would revert their primary colour to default when you enchanted them.</ul>"
			+"<ul>Fixed pregnancy probability descriptions during sex always acting as though the potential mother already had a chance of being pregnant.</ul>"
			+"<ul>Fixed incorrect name parsing during Siren's pre-fight scene.</ul>"
			+"<ul>Fixed issue where Imps would be incorrectly shown as being present in the very bottom-left tunnel tile of Submission.</ul>"
			+"<ul>Added error handling for some bugs in clothing management during sex.</ul>"
			+"<ul>Fixed elementals not getting perk points as their summoner level up.</ul>"
			+"<ul>Enchanting strength-related drinks will no longer duplicate physical damage & resistance as primary modifiers.</ul>"
			+"<ul>Fixed error log spamming 'Failed to load character' messages.</ul>"
			+"<ul>Fixed issue with NPCs in sex sometimes failing to remove clothing, which would throw an error and freeze the sex scene.</ul>"
			+"<ul>Fixed some clothing (such as the clover clamps) not registering that they were blocking parts correctly, resulting in some parsing errors in some sex actions.</ul>"
			+"<ul>Fixed some incorrect descriptions in sex scenes.</ul>"
			+"<ul>Fixed imps in the tunnels being treated as though their generic names ('fearless imp', 'nervous imp', etc.) were their real names.</ul>"
			+"<ul>Added error handling for when two pieces of clothing accidentally end up blocking one another.</ul>"
			+"<ul>Fixed incorrect parsing in imp fortresses, where imps would be described instead of the demon boss.</ul>"
			+"<ul>Fixed issue where a scene from Arthur's dialogue was missing (at the point where you agree to find Lyssieth).</ul>"
			+"<ul>Fixed issue where covering colour options would not show up in transform or Kate's menus.</ul>"
			+"<ul>Fixed siren's call being available as a spell book, and removed it from known spells, if you'd already learned it.</ul>"
			+"<ul>Fixed bug where setting all age categories to disabled would sometimes throw an error and break the game in strange ways.</ul>"
			+"<ul>Fixed Maximilian (the slime queen's royal guard) not being a demon slime.</ul>"
			+"<ul>Fixed Wolfgang and Karl having empty descriptions, and fixed their post-sex 'offer body' scene acting as though you fought them.</ul>"
			+"<ul>Performing a handjob climax on a slime no longer incorrectly describes cum shooting inside of them.</ul>"
			+"<ul>Fixed issue where all characters would sometimes gain health and mana when you loaded a game.</ul>"
			+"<ul>Fixed body covering colours not saving if set via the debug menu, causing them to be reset every time you loaded the game.</ul>"
			+"<ul>Fixed some subspecies advanced lore entries being hidden, even if they shared the same unlocked lore entry as their race's core subspecies.</ul>"
			+"<ul>You can no longer change surnames to be blank after character creation, as it was causing some bugs.</ul>"
			+"<ul>Fixed incorrect grammatical parsing in some sex actions.</ul>"
			+"<ul>Fixed issue where unique NPCs would accept fetish TF potions.</ul>"
			+"<ul>Fixed characters who like the pregnancy or cum addict fetishes asking their partners to pull out.</ul>"
			+"<ul>Fixed issue where when loading a game, all characters on your tile would have their concealed parts revealed.</ul>"
			+"<ul>Some fixes to sex action grammar.</ul>"
			+"<ul>Fixed the 'Foxy Fuck' potion (from the youko in the citadel) not transforming your ass into a fox's.</ul>"
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
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 13));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Anonymous_Platypus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 3, 11));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 6));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Tieria", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 13));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Captain_Sigmus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Atroykus", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 9, 5));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 11));
		credits.add(new CreditsSlot("Hikaru Lightbringer", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Darthsawyer", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 8, 2));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 6, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 7));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Aceofspades", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Assiyalos", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Evit", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kaerea", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Karlimero", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Tappi", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("BlueVulcan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 4));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Littlemankitten", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Mega", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Neon Swaglord Chen", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Seo Leifthrasir", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 9));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("PoyntFury", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 7, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 14));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Sir beans", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 13, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("The Brocenary", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TKaempfer", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Tom Clancy's Pro Skater", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 9));
		credits.add(new CreditsSlot("Jess", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("SolarEidolon", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 10));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Weegschaal", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Will Landrum", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Drahin", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Wolfrave", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 10));
		
		
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

		loadFonts();
		
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

	/**
	 * Attempts to load fallback fonts to make sure that they are available later. The size doesn't actually matter as
	 * the WebEngine will reload other sizes as required. The files referenced must persist until application shutdown.
	 *
	 * Do not call Font.getFamilies() prior to this as additional fonts must be loaded before the list is cached.
	 */
	protected void loadFonts() {
		// Load fallback for Calibri
		if (Font.loadFont(toUri("res/fonts/Carlito/Carlito-Regular.ttf"), 11) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Bold.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-BoldItalic.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Italic.ttf"), 11);
		} else {
			System.err.println("Carlito font could not be loaded.");
		}

		// Load fallback for Verdana
		if (Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans.ttf"), 12) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Bold.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-BoldOblique.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-ExtraLight.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Oblique.ttf"), 12);
		} else {
			System.err.println("DejaVu Sans font could not be loaded.");
		}
	}

	/**
	 * Creates a URI string with spaces. The given path can be absolute or relative to the current working directory.
	 * @param path The path to convert
	 * @return A string containing a URI
	 */
	public static String toUri(String path) {
		return Paths.get(path).toUri().toString().replaceAll("%20", " ");
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
				System.err.println("Version: "+VERSION_NUMBER);
				System.err.println("Java: "+System.getProperty("java.version"));
//				System.err.println("OS: "+System.getProperty("os.name"));
				
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
	public static void startNewGame(DialogueNode startingDialogueNode) {
		
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
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE));

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
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer(),
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
