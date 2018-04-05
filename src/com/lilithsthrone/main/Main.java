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
 * @version 0.2.2
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.2.1",
			VERSION_DESCRIPTION = "Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone!"
		+ "</p>"
			
		+ "<p>"
			+ "As I said in the most recent blog & Patreon posts, I've had some personal issues going on since last Friday, and while I'd hoped that they'd all be sorted out by Tuesday,"
				+ " it wasn't until Thursday morning that I was able to really get started on this week's progress."
			+ " As a result, I didn't get as many of this week's goals done as I'd liked..."
		+ "</p>"
			
		+ "<p>"
			+ "That being said, there is some new content to be seen in the brothel 'Angel's Kiss', as well as the addition of rat, bat, and rabbit morphs."
			+ " I know rabbit-morphs weren't in my goals, but I really wanted to get them added in time for Easter. ^^"
		+ "</p>"
		
		+ "<p>"
			+ "I will continue to work over this weekend to get urethral penetration actions added into the game, as well as finishing Submission's core content and making Zaranix & his maids repeatable encounters."
			+ " I expect to push another release with all that in on Monday evening."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.1.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added fixed maps for Dominion, Submission, and Harpy Nests. (I will most likely tweak and improve these over the next few versions. I will also add random maps in some form or another a little later on.)</ul>"
			+"<ul>Added Canal tiles to Dominion. (Function similar to back alleys, but random attackers are more likely to be slimes and alligator morphs rather than normal alleyway races.)</ul>"
			+"<ul>Added three Parks, a central plaza, and boulevard tiles to Dominion.</ul>"
			+"<ul>Added basic descriptions for all new Dominion areas, and for about half of Submission. (Will be finished for the full release.)</ul>"

			+"<li>Other:</li>"
			+"<ul><b>Changed:</b> Ass and Breasts are now covered in the same skin/fur type as that which covers the character's torso.</ul>"
			+"<ul><b>Removed:</b> Auto-milking chance from full breasts, and replaced it with a minor debuff. (The unavoidable loss in player agency was not in keeping with the rest of the game.)</ul>"
			+"<ul>Changed bandana displacement to be pulls down, not pulls up.</ul>"
			+"<ul>You can now see your opponent's fetishes in combat. (I'll tidy this up soon.)</ul>"
			+"<ul>Added content option for feminine characters to have facial hair (disabled by default).</ul>"
			+"<ul>Slave rooms no longer give +0.2 affection per hour.</ul>"
			+"<ul>Changed alligator-morph's 'scales in place of hair' to coarse hair. (All naturally-spawning alligators now spawn in with no hair.)</ul>"
			+"<ul>Increased chance of NPCs to spawn in with addictive or psychoactive cum/girlcum from 0.5% to 2%.</ul>"
			+"<ul>Added penis girth to body changing options.</ul>"
			+"<ul>Random NPCs with the lactation fetish will now spawn in producing milk.</ul>"
			+"<ul>Added normal human skin colours as colour options for demons.</ul>"
			+"<ul>Updated Scarlett's appearance and clothing, and gave her three fetishes. (These changes won't be applied if she is already your slave, as I didn't want to interfere with any transformations/clothing/fetishes you've given to her.)</ul>"
			+"<ul>Halved all milk regeneration values (even the minimum one was refilling to maximum after just 1 day).</ul>"
			+"<ul>Added training and hyper sizes to breast cup size detection.</ul>"
			+"<ul>Adjusted prostitute prices.</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Sleeveless turtleneck (feminine, torso slot).</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Cleaned various issues. (Pimvgd)</ul>"
			+"<ul><b>Forced Fetishes and Forced TF Gender Tendency Settings.</b> This implements several new settings and related features for forced transformations and fetishes. You can see the full changes here: https://github.com/Innoxia/liliths-throne-public/pull/113 (FFongWong)</ul>"
			+"<ul>Add coverage to clothing for slots with no sex actions. This adds slots such as BACK, STOMACH, and HAIR to clothing concealment, which should fix the bug where you cum on a character's back or stomach and it dirties the body through the clothes. (Master of Puppets)</ul>"
			+"<ul>Add cum targets for characters cumming on themselves. (Master of Puppets)</ul>"
			+"<ul>Add error logging to 'data/error.log' instead of STDERR. (Master of Puppets)</ul>"
			+"<ul>Add check for new potion enchantments. (Master of Puppets)</ul>"
			+"<ul>Fixed spell cost reduction giving negative costs. (Master of Puppets)</ul>"
			+"<ul>Remove translation of INTELLIGENCE to MAJOR_ARCANE for old character imports - this was giving all imported characters extra arcane ability, which the majority of them shouldn't have. (Master of Puppets)</ul>"
			+"<ul>Body hair/fur/feathers now uses the patterns for hair rather than skin. (Master of Puppets)</ul>"
			+"<ul>Increased Flame reward from Angry Harpy quest from 1500 to 5000 flames. (rfpnj)</ul>"
			+"<ul>Changed what triggers the 'Frustrated' status effect from 'did not cum' to 'ended sex just before orgasming'. (Master of Puppets)</ul>"
			+"<ul>Removed requirement for characters to have breasts in order to pinch nipples. (Master of Puppets)</ul>"
			+"<ul>Added several new hair styles. (WoefulWombat)</ul>"
			+"<ul>Fixed speech parsing not using correct femininity colour. (Master of Puppets)</ul>"
			+"<ul>Fixed Maid's Stockings secondary colour not working. (Master of Puppets)</ul>"
			+"<ul>Fixed loading the game advancing time. (Master of Puppets)</ul>"
			+"<ul>Fixed 'squirter' property of vaginas not being saved/loaded. (Master of Puppets)</ul>"
			+"<ul>Fixed bug where after having started a game and then selecting new game, you would be unable to set your new character's femininity and other attributes. (Master of Puppets)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Slavery log events now properly save and load.</ul>"
			+"<ul>Fixed bimbo talk sometimes generating incorrect sentence endings. </ul>"
			+"<ul>Fixed being able to cast spells even when out of aura.</ul>"
			+"<ul>Fixed bug in enchanting screen, where adding a new enchantment that matched an existing enchantment (i.e. Adding 5 seduction damage when an existing +5 seduction damage already existed), showed that the new one required an arcane cost to remove.</ul>"
			+"<ul>Fixed enchanting screen bug, where clicking on the left clothing icon would cause a soft-lock.</ul>"
			+"<ul>Fixed addictions not saving/loading properly.</ul>"
			+"<ul>Triple obedience gains from the 'In Control' trait (the one gained from having the Teacher background) will now correctly be applied to all obedience gains.</ul>"
			+"<ul>NPCs will no longer be enslaved if the enslavement clothing can't be equipped onto them.</ul>"
			+"<ul>Saving game will no longer progress time.</ul>"
			+"<ul>You will now take energy & aura damage from tease attacks when at 100 lust (just like enemy demons do).</ul>"
			+"<ul>Fetishes gained from clothing will now correctly apply attribute bonuses, special attacks, and derived fetishes.</ul>"
			+"<ul>Fixed intro sex displaying that you lost your virginity 'in the jungle'.</ul>"
			+"<ul>Fixed cause of imps spawning in at incorrect heights, and of slimes birthing humans.</ul>"
			+"<ul>Added tooltips for milking actions.</ul>"
			+"<ul>Fixed active traits with buffs (such as +25 aura) remaining in effect even if you unequipped them.</ul>"
			+"<ul>Fixed bug where increasing height would say its increased even past the maximum limit.</ul>"
			+"<ul>Fixed gifting chocolate to Nyan boosting affection by 50 (it now correctly boosts by 5).</ul>"
			+"<ul>Fixed 'locked in stocks' applying vaginal sex events to slaves with no vagina.</ul>"
			+"<ul>Slaves will no longer have amorous encounters with relations if they don't have the associated fetish.</ul>"
			+"<ul>Fixed cum stud tease parsing the wrong names.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.2</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Added:</b> Rat-morphs. Found in Submission and in Dominion's canal tiles.</ul>"
			+"<ul><b>Added:</b> Bat-morphs. Found in Submission's bat caverns.</ul>"
			+"<ul><b>Added:</b> Rabbit-morphs, with 'lop-rabbit' subspecies (they have floppy ears). Found rarely in Dominion's alleyways.</ul>"
			+"<ul>Added full map display to phone's default menu.</ul>"
			+"<ul>Added map to Lilaya's library.</ul>"
			+"<ul>Added urethra orifice to vagina.</ul>"
			+"<ul>Added all necessary support for penetrating urethras in the engine. (I will write in the associated sex actions and push another update within a couple of days.)</ul>"
			+"<ul>Added content toggles for lactation and urethral content.</ul>"
			
			+"<li>Angel's Kiss:</li>"
			+"<ul>Removed Angel's Kiss from Slaver Alley, and added it to a new location 'Red light district' in Dominion.</ul>"
			+"<ul>Added NPCs for Angel's Kiss: Angel, Bunny, and Loppy.</ul>"
			+"<ul>Added ability to buy a prostitution license from Angel, which allows you to send slaves to work as prostitutes in Angel's Kiss, ask alleyway prostitutes to move to Angel's Kiss, and whore yourself out for money.</ul>"
			
			+"<li>Clothing:</li>"
			+"<ul>Added: Collar bowtie (neck slot).</ul>"
			+"<ul>Added: Suit cuffs (wrist slot).</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added slime birthing scene variation.</ul>"
			+"<ul>Made Pix hate receiving anal sex, dislike being pregnant, and like receiving vaginal sex.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed addiction time bug (it was always saying that NPCs weren't suffering from withdrawal, even if they were). (AlacoGit)</ul>"
			+"<ul>Added fast travel for Dream Lover. (Delvigore)</ul>"
			+"<ul>Nerfed experience and essence gains from combat, (experience from 10 * enemyLevel to 2 * enemyLevel, and essence gain roughly halved). (Rfpnj)</ul>"
			+"<ul>Typo fixes. (Master of Puppets)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed major bugs in availability of actions in multiple-partner sex.</ul>"
			+"<ul>Fixed bug in multiple-partner sex where impregnation chances would fire for every participant, not just the one who came inside.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed incorrect tongue and penis head descriptors.</ul>"
			+"<ul>Fixed bug where map wouldn't load correctly when importing a pre-0.2.1.5 save.</ul>"
			+"<ul>Fixed strange sex behaviour of demon attackers (the ones in dark alleyways tiles).</ul>"
			+"<ul>Slaves with the non-con fetish now correctly gain affection from non-con sex.</ul>"
			+"<ul>Fixed Submisison's slime spawn rate being far too high.</ul>"
			+"<ul>Fixed newly enslaved NPCs re-equipped new clothing as they get transferred to Slavery Administration.</ul>"
			+"<ul>Fixed bug in combat where if you were defeated/victorious/escaped/stunned, the spells and special attacks tabs wouldn't change to reflect this.</ul>"
			+"<ul>Fixed minor display bugs in slave management dialogue.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.2.1</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Ralph now stocks bat, rat, and rabbit consumables.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Added 'Swap Position' action to sex, so you can now access every possible position by swapping with your partner. (Available if you're the dom, or if you have equal control in the sex scene. Disabled in some unique sex scenes.)</ul>"
			+"<ul>Added 'penis in vaginal urethra' penetration actions for both player and partner.</ul>"
			+"<ul>Added 'penis in penis urethra' penetration actions for both player and partner.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Changed the perks 'barren' and 'firing blanks' to traits (can now be equipped/unequipped will), and changed the trait 'arcane weaver' to a perk.</ul>"
			+"<ul>Improved the inventory screen to use pages. (Using a scrollbar wasn't viable, as JavaFX struggles to render so many SVGs at once.)</ul>"
			+"<ul>Increased inventory slot limit from 32 to 120.</ul>"
			+"<ul>Reduced angel and demon base slave values.</ul>"
			+"<ul>Swapped Nyan's talk & trade tabs to make it (slightly) faster to shop there.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed some bugs in Bunny's and Loppy's dialogue options.</ul>"
			+"<ul>Clothing now correctly conceals piercing slots.</ul>"
			+"<ul>Fixed slave prostitute job's obedience, affection, and income being far too high.</ul>"
			+"<ul>Fixed rabbit-morph tails being tagged as prehensile and suitable for penetration.</ul>"
			+"<ul>Fixed bugs with rat and bat morph race detection.</ul>"
			+"<ul>Fixed bat penis being detected as squirrel penis.</ul>"
			+"<ul>Loppy and Bunny now correctly return to their rooms after the threesome.</ul>"
			+"<ul>Fixed some strange spell descriptions.</ul>"
			+"<ul>Fixed duplicate spell icons appearing in your status effects bar during combat.</ul>"
			+"<ul>Fixed rabbit morph item descriptions.</ul>"
			+"<ul>Fixed import errors that were causing the map to not display.</ul>"
			+"<ul>You can no longer enchant clothing with an enslavement modifier before obtaining a slaver license.</ul>"
			+"<ul>Fixed inconsistent location changes related to Zaranix's quest.</ul>"
			+"<ul>Giving birth as a slime no longer stretches your vagina.</ul>"
			+"<ul>Debug mode now correctly reveals your phone's map.</ul>"
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
