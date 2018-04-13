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
import com.lilithsthrone.game.PropertyValue;
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
 * @version 0.2.3
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.2.5",
			VERSION_DESCRIPTION = "Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "I've managed to get a lot of bugs and other issues sorted out for this preview, as well as adding in an optional artwork viewer on unique characters' information pages."
			+ " I've got a lot of writing for Amber's and Zaranix's repeatable content done in draft format, so I'll get that added to the game for the next full release, along with Submission content (of which I've also got some drafts done). ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.2.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added raven-harpy and border-collie subspecies (for Scarlett and Pix, respectively).</ul>"
			+"<ul>Essence gains from combat and sex are now doubled during arcane storms.</ul>"

			+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Corset dress (feminine, torso slot).</ul>"
			+"<ul><b>Added:</b> Suspender belt  (feminine, hips slot).</ul>"
			+"<ul><b>Added:</b> Stockings (feminine, calves slot).</ul>"
			+"<ul><b>Added:</b> Sport shorts (unisex, leg slot).</ul>"
			+"<ul><b>Added:</b> Sweatband (unisex, head slot).</ul>"
			+"<ul><b>Added:</b> Wristbands (unisex, wrist slot).</ul>"

			+"<li>Slavery:</li>"
			+"<ul>Moved 'Slavery Overview' from an option in every room to just being available from your room, and made the options available in rooms specific to that room.</ul>"
			+"<ul>Tidied up the appearance and buttons for slavery and room management.</ul>"
			+"<ul>Added some dialogue variation for if you've set your slave to be crawling.</ul>"
			+"<ul>Added Milking Room upgrade, along with 'Milking Cow'/'Milking Bull' job for slaves. (Still needs finishing off.)</ul>"

			+"<li>Other:</li>"
			+"<ul><b>Added:</b> Optional in-game artwork viewer (enabled by default, can be disabled in the content options screen). Major NPCs which have had artwork drawn of them will now have that artwork displayed in their character screen. You can also add in custom images (info is in the res/images/characters/modding.txt folder).</ul>"
			+"<ul>Added: Demon tail TF variation. (Hair-tipped instead of spaded.)</ul>"
			+"<ul>Slightly improved Nyan's clothes.</ul>"
			+"<ul>You can now export characters from your phone's contacts screen (using the same export button in the top-right).</ul>"
			+"<ul>Updated Vicky's outfit.</ul>"
			+"<ul>Phone's map now flashes to show which tile you're currently on.</ul>"
			+"<ul>Phone's map now scales better for smaller maps.</ul>"
			+"<ul>Books no longer give +0.5 arcane, and instead, give +5 damage and +5 resistance vs the book's associated race.</ul>"
			+"<ul>Unequipping or displacing an NPC's clothing while outside of sex will now display the same reveal text for ass/breasts/vagina/penis as when in sex.</ul>"
			+"<ul>Slaves with the 'cum addict' fetish now gain affection towards you instead of losing it when being tasked to work as a prostitute.</ul>"
			+"<ul>Added a 'Take' action for when you're interacting with an NPC's equipped clothing, allowing you to unequip their clothing and add it to your inventory in one click.</ul>"
			+"<ul>You can now change body hair colours at Kate's shop and through the demon transformation screen.</ul>"
			+"<ul>Slimes no longer need to have their body parts pierced in order to equip piercings (you still need to grow a vagina or penis to equip those piercings, however).</ul>"
			+"<ul>NPCs with the Submissive fetish will now prefer to stick with the gentle pace while being the dom in sex (unless they are a switch or sadist, in which case they'll use normal/rough paces as normal).</ul>"
			+"<ul>Added white slime colour.</ul>"
			+"<ul>Added a lot more colours for feathers.</ul>"
			+"<ul>NPCs who hate sadism will now always be gentle while domming, and NPCs that dislike sadism will prefer the normal pace.</ul>"
			+"<ul>Added option to be the dom in prostitute's 'Sell body' action.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Fixed cause of bad displacement imports leading to a crash after sex. (Master of Puppets)</ul>"
			+"<ul>Adjusted the display of subspecies lists in the Encyclopedia. (tukaima)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed bug where NPCs could still perform urethral penetration actions, even if it was turned off. (So sorry about this!)</ul>"
			+"<ul>Fixed all urethras being penetrable, even if capacity was at 0.</ul>"
			+"<ul>Fixed page automatically scrolling down when opening character view screen.</ul>"
			+"<ul>Fixed minor bug in harpy ear descriptions in character view screen.</ul>"
			+"<ul>Fixed final gift-related bugs in Nyan's tabs.</ul>"
			+"<ul>Dog and wolf-morphs should now correctly have scarlet-coloured penises by default.</ul>"
			+"<ul>Fixed fetish screen's desire buttons being incorrectly aligned.</ul>"
			+"<ul>Fixed 'Arcane Weaver' perk not apply its essence reduction effect.</ul>"
			+"<ul>Fixed NPCs spawning with the pussy slut fetish, even if they didn't have a vagina. Also, NPCs should no longer spawn in with fetishes if you have their related content settings turned off.</ul>"
			+"<ul>Typo, formatting, and parsing fixes.</ul>"
			+"<ul>Fixed a lot of incorrect clothing equip/unequip/displace descriptions.</ul>"
			+"<ul>You can no longer equip penis and vagina piercings, even if you had no penis or vagina.</ul>"
			+"<ul>Drinking a biojuice canister while already being a slime will no longer display the slime transformation description.</ul>"
			+"<ul>Imp offsprings no longer have their height determined by genetics (which was resulting in very tall imps).</ul>"
			+"<ul>Fixed Loppy's post-sex scene returning Bunny's dialogue.</ul>"
			+"<ul>Swapping positions in sex now correctly resets all ongoing sex actions.</ul>"
			+"<ul>Fixed bug where mutually exclusive slave job settings would not be set correctly when you loaded a game.</ul>"
			+"<ul>Slavery income should now correctly be saved/loaded.</ul>"
			+"<ul>Lilaya has been told to stop replacing the spare pair of goggles that are found in her lab.</ul>"
			+"<ul>Slave obedience gains from the teacher perk 'In control' now correctly only affects positive obedience increments.</ul>"
			+"<ul>Fixed bug where blocked slot icons would show incorrect race icon.</ul>"
			+"<ul>Fixed bug where you could have sex with prostitutes in Angel's Kiss while they were entertaining a client.</ul>"
			+"<ul>Fixed bug where first floor of Angel's Kiss would end up getting flooded with random characters.</ul>"
			+"<ul>Fixed Lexi's creampie status effect showing 0ml.</ul>"
			+"<ul>Fixed major bug where the slavery upkeep calculation was not working.</ul>"
			+"<ul>Fixed bug where NPCs in Dominion's canal tiles would disappear.</ul>"
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
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 4));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 4));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 4, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 3, 0));
		
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 2));

		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 3, 0));

		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 4, 0));

		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 2, 0));

		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 1));

		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 1, 0));
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 6, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 2));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 7));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 6, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 3));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 8, 0));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;

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

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
		// Open error log
//		try {
//			@SuppressWarnings("resource")
//			PrintStream stream = new PrintStream("data/error.log");
//			System.setErr(stream);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
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
			
		} else if (!Main.game.isStarted() || !Main.game.getCurrentDialogueNode().equals(DebugDialogue.getDefaultDialogueNoEncounter())) {
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
