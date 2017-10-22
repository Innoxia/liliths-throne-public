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
 * @version 0.1.87
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.87",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Moderately Buggy Preview!</b></h6>"
//		"<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha!</b></h6>"
		
		+"<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
		+ "<p>"
			+ "Hello again everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "This version is a little smaller than I'd have liked, due to some personal issues that I had to deal with over this past week."
			+ " I delayed the release by a day to try and get as much done as possible, but the amount of time I lost due to the aforementioned personal matters is probably still quite apparent..."
		+ "</p>"
			
		+ "<p>"
			+ "Anyway, enough with the gloomy talk! I did manage to get the core mechanics for slavery finished this week, although <b>some of the events, permissions, and interactions haven't been written in yet</b>."
			+ " Honestly, all of the hard work has been completed now on slavery, and adding in content will be easy from now on (which is my goal for all aspects of the game before I reach v0.2.0)."
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon! :3"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
		
			+ "<h6>Preview (v0.1.86.5)</h6>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Consolidated all essences into arcane essences. There were numerous problems with the old system of a unique essence for each race (unnecessary micro-management, far too many races planned, wasted resources), so I decided to remove them.</ul>"
			+"<ul>Repurposed all 'bottled essence' item effects into damage & resistance multipliers based on the essence's race.</ul>"
			+"<ul>Changed jinxed clothing removal mechanics. You now need to spend arcane essences to remove a jinx, and no longer need to worry about fiddling about with weapons.</ul>"
			+"<ul>Consolidated Lilaya's 'discovering essences' and 'jinxed' quests into one quest.</ul>"
				
			+"<li>Forced TF mechanics:</li>"
			+"<ul>Added forced TF preference (in the furry preferences screen).</ul>"
			+"<ul>Split transformation fetish into giving and receiving versions.</ul>"
			+"<ul>NPCs will now only try to TF you if they have the 'giving TF' fetish.</ul>"
			+"<ul>Removed the forced TF option in the content preferences, and instead you now have the option in each of the forced TF scenes to avoid the TF. (I might rework this a little more for the full release.)"
			+" I decided to change it to this method so that players are always able to see unique options (such as Brax's and the harpy matriarchs' TFs), instead of missing out on unique scenes entirely."
			+" <b>You can still avoid all forced TF content, don't worry if that's not your thing!</b> ^^</ul>"
			+"<ul>Changed Brax's TF scene to take into account TF preferences.</ul>"
			+"<ul>Changed all three of the harpy's scenes to take into account TF preferences.</ul>"
			+"<ul>Actions related to being forcefully transformed or transforming others now have associated fetish requirements and corruption bypasses.</ul>"
			+"<ul>NPCs will now apply all of their genital preferences to one potion.</ul>"
				
			+"<li>Settings & UI:</li>"
			+"<ul>Improved furry preferences screen and slightly altered what each of the settings mean. (Please make sure that everything is still suitable for you after starting up your game!)</ul>"
			+"<ul>Improved the phone's stats screens UI a little.</ul>"
			+"<ul>Added plasticity stats to the body stats screen, along with a description of how capacity, elasticity, and plasticity work.</ul>"
			+"<ul>Improved slavery manager UI.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added a very minor variation ('Furries?! Yes!') to the prologue, as an opposite reaction to 'Furries?! No!'.</ul>"
			+"<ul>Added descriptions for all vagina modifiers to selfie.</ul>"
			+"<ul>Changed Ralph to a greater horse-morph (instead of lesser), and gave him new clothes and the 'receiving oral' fetish.</ul>"
			+"<ul>Removed most of the restrictions on choosing your name.</ul>"
			+"<ul>You no longer start the game with any essences. (This is so that the essence discovery quest makes sense. I plan on letting you choose some starting fetishes in the character creation, which is what the 5 starting essences were intended for.)</ul>"
			+"<ul>Added ear colour to selfie.</ul>"
			+"<ul>Added ability to change surname at City Hall.</ul>"
			+"<ul>Increased limit of pairs of multi-breasts from 3 to 5.</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>Fixed bug where you couldn't properly remove a jinx from an NPC's clothing. (This is what led to me reworking the entire jinx removal system, as I realised just how cumbersome it was...)</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed bug in inventory where you'd be able to use inappropriate items on an NPC through the 'Use all' action.</ul>"
			+"<ul>Fixed bug where condoms wouldn't be removed upon NPC's orgasm.</ul>"
			+"<ul>Fixed UI bug where NPC's slots were shown as blocked based on your blocked slots.</ul>"
			+"<ul>Fixed 'storm generated' NPCs not being removed from place tiles.</ul>"
			+"<ul>Fixed body/pubic/facial hair potions not working correctly.</ul>"
			+"<ul>Imported characters with hooves/talons can now get past the clothing selection screen (you don't need to wear shoes to pass). (I don't know how you managed to get into the clothing selection with an imported character... ^^)</ul>"
			+"<ul>Fixed the description for Diana's perfume showing Lexi instead of Diana.</ul>"
			+"<ul>Fixed puffy vagina description not correctly referring to labia.</ul>"
			+"<ul>Fixed vagina modifiers not being exported/imported correctly.</ul>"
			+"<ul>Fixed minor inconsistency in the follow up exam with Lilaya, which was referencing your clothes being destroyed.</ul>"
			+"<ul>The 'Savage Attack' special ability for wolf-morphs now correctly requires both wolf-morph arms and face to be available.</ul>"
			+"<ul>You can now sell weapons and clothing to NPCs (such as Vicky) even if their inventory is full.</ul>"
			+"<ul>Fixed dirty talk sometimes incorrectly referring to fingers.</ul>"
			+"<ul>Fixed impenetrable nipples being described as 'extremely tight'.</ul>"
			+"<ul>Fixed reading library books showing that you'd gained intelligence twice.</ul>"
			+"<ul>Added NPC-specific descriptions to item effects that were lacking them.</ul>"
			+"<ul>Non-capitalised names will no longer automatically append 'the' in front of them if it's the player's name, or if the NPC's name is known.</ul>"
			+"<ul>Clothing can now be dyed if it's on the ground/being stored in an area.</ul>"
			+"<ul>Removing an NPC with the 'Risk of pregnancy' status effect now correctly removes the from your pregnancy stats page.</ul>"
			+"<ul>Fixed feminine offspring always having flat chests.</ul>"
			+"<ul>Fixed labia size always being described as 'tiny'.</ul>"
			+"<ul>Fixed bug where you could press 'overwrite' in the main menu (before starting a game), which would then corrupt that save file. (Sorry to those affected by this...)</ul>"
				
			+"<li>Save compatibility:</li>"
			+"<ul>Slightly improved efficiency of XML exports.</ul>"
			+"<ul>Added money and essence saving.</ul>"
			+"<ul>Added equipped weapon saving.</ul>"
			+"<ul>Added equipped clothing saving.</ul>"
			+"<ul>Added items, clothing, and weapons in inventory saving.</ul>"
			+"<ul>Added owned slaves to exported character. (May be a little buggy... I didn't have much time left in which to test this... ;_;)</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
		
			+ "<h6>v0.1.87</h6>"
					
			+"<li>Slavery:</li>"
			+"<ul><b>Finished:</b> All basic slavery mechanics. I'll expand the slavery content over the course of the game's development to add in more jobs/permissions/events, but all of the core slavery mechanics that I had planned are now in the engine.</ul>"
			+"<ul><b>Added:</b> Slavery event loop. The game calculates a slave's income, affection & obedience changes, location (based on job), and all other slavery-related information every in-game hour.</ul>"
			+"<ul><b>Added:</b> Slavery events. The game now has a chance to generate an event for each of your slaves every hour. Events take into account what other NPCs the slave has access to, as well as what permissions & job settings are enabled.</ul>"
			+"<ul><b>Added:</b> Jobs. You can assign your slaves to perform different jobs (9 at the moment), some of which have special setting that influence random events that occur while they're working.</ul>"
			+"<ul><b>Added:</b> Permissions. You can now assign permissions to your slaves, which influence random events that occur while they're working/idle.</ul>"
			+"<ul>Improved slave management UI.</ul>"
			+"<ul>Removed caps for room upgrades' obedience and affection changes.</ul>"
			
			+"<li>UI:</li>"
			+"<ul>Slightly improved layout at higher resolutions.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Buffed rainbow gloves and stockings.</ul>"
			+"<ul>Added safety goggles spawning in Lilaya's lab. (These have been in the game files for some time, but I forgot to add them in...)</ul>"
			+"<ul>Refined some of the named NPC's body types.</ul>"
			+"<ul>Added option to set the amount of NPCs spawning with the 'transformer' fetish.</ul>"
			+"<ul>'Forced TF racial limits' in the furry setting should now be working. (I didn't have time to test, so it may be a little buggy. ;_;)</ul>"
			+"<ul>NPCs will now try to forcibly transform you even if they aren't already attracted to you.</ul>"
			+"<ul>Candi and Brandi now wear stiletto heels.</ul>"
			+"<ul>Lactating nipples should now start sex pre-lubricated with milk.</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>Fixed a cause of a UI display bug in the furry preferences screen.</ul>"
			+"<ul>Fixed slave collar not sealing/jinxing itself onto slaves when equipped.</ul>"
			+"<ul>Fixed the 'Rest until Morning' action in your room sometimes causing you to sleep until the next day (for a 24 hr+ rest).</ul>"
			+"<ul>Fixed skewed prices of essence vials.</ul>"
			+"<ul>Fixed several of the racial lore books being unable to be sold.</ul>"
			+"<ul>Fixed imported slaves obtaining randomly assigned fetishes.</ul>"
			+"<ul>Imported slaves are now added to your contacts list.</ul>"
			+"<ul>Fixed bug where items in your wardrobe during character creation could be found on the map after game start.</ul>"
			+"<ul>Imported characters should no longer start with most of their combat stats drained.</ul>"
			+"<ul>Fixed bug where removing tongue modifiers using potions would add them instead.</ul>"
			+"<ul>Fixed bug where imported characters were starting with incorrect orifice modifiers.</ul>"
			+"<ul>Minor breast-size detection fix.</ul>"
			+"<ul>Lilaya's skin colour should now correctly be the same as your starting skin colour.</ul>"
			+"<ul>Inconsistent dialogue fixes in the prologue.</ul>"
			+"<ul>Fixed 'Spit' and 'Swallow' options always appearing after a fight (even when the NPC didn't have the 'giving TF' fetish).</ul>"
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

		credits.add(new CreditsSlot("Anonymous", "", 0, 1, 8, 2));
		// A. L2 | C.C. E2 | G. E2 | JD E1 | MM E1 | V. R1 E2
		
		
		credits.add(new CreditsSlot("A", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 1));
		credits.add(new CreditsSlot("A", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("B", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 1));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("B", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("G", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("J B", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Enigamatic Yoshi", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("L", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("N", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("N L", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("P H", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("R", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("S D", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 0, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Tanner Daves", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("V", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 3, 0));
		
		credits.add(new CreditsSlot("A", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("A t A", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("Baz Goldenclaw", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("H", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J D", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("L", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("T", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("V", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("W J O", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Z", "", 0, 0, 1, 0));
		
		
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
			try {
				Main.game = new Game();
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Game loadedGame = (Game) ois.readObject();
				ois.close();
				fin.close();
				Main.game = loadedGame;
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
