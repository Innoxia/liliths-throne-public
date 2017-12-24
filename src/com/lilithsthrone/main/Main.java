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
 * @version 0.1.96.1
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.96.1",
			VERSION_DESCRIPTION = "Early Alpha";

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
			+ "(This is the 0.1.96.1 hotfix version, which addresses some bugs and adds in a male variant of the kimono. The extra patch notes are at the bottom of this page. :3)"
		+ "</p>"	
		
		+ "<p>"
			+ "First of all, I wish you all a very Merry Christmas, and a Happy New year! :3"
		+ "</p>"
			
		+ "<p>"
			+ "I didn't quite get as much done this version as I'd hoped (the new orgasm work was pushed back due to the Christmas content taking a while), but hopefully you'll have fun with the content that I did manage to include."
			+ " Some of it may be a little rough around the edges, so if I find any major bugs in this version, I'll make a hotfix as soon as I can."
		+ "</p>"
			
		+ "<p>"
			+ "<b>To those of you who have reported bugs:</b> I am working on getting them all fixed, so if I've replied to your bug report saying 'I'll get it fixed asap!', then please know that I really am! ^^"
		+ "</p>"
		
		+ "<p>"
			+ "I will be taking next week off to have a break over the holiday; I'll back to working on LT on January 2nd! :3"
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon! :3"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.96</h6>"
			+"<ul><b>There were several bug reports (mainly the game's options locking up) that I was unable to reproduce. If you're having issues like that, please try exporting, then importing your game.</b></ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Enabled support for clothing to have secondary and tertiary colours. Certain clothing (such as the new kimono) can have different parts dyed in different colours, allowing you to customise your clothing even more.</ul>"
				
			+"<li>Gameplay:</li>"
			+"<ul>Added weather type 'snow', which only occurs during winter (December, January, & February).</ul>"
			+"<ul>Added reindeer-morph encounters in alleyways during winter (they only start appearing after it's snowed for the first time).</ul>"
			+"<ul><b>Added:</b> Reindeer-morph encounters in the streets of Dominion. Four reindeer-morphs will spawn in Dominion when it first snows. (They are only present during the winter months.)</ul>"
			+"<ul>Added 'December' entry to your room's calendar.</ul>"
			+"<ul>Added new way to initiate a sex scene with Lilaya (go to her lab while having a present in your inventory; you need to perform the related action 3 times).</ul>"
				
			+"<li>Sex:</li>"
			+"<ul>Slightly reworked the way orgasms are handled in the game. Now, instead of combining both orgasms into one action if you're orgasming at the same time (which was essentially just concatenating both orgasm descriptions and presenting it as one action), your orgasm is resolved first, then you choose a reaction for your partner's orgasm. (I wanted to give you a little more control over how orgasms are played out.)</ul>"
			+"<ul>Partners should no longer be able to perform a penetrative action in the same turn that you stop penetration. (This is to stop the situation where you'd stop an ongoing sex type, then the NPC would immediately start it again.)</ul>"
	
			+"<li>Contributors:</li>"
			+"<ul>Minor fix to Gator's Gumbo usage text. (by Rfpnj)</ul>"
			+"<ul><b>Added reindeer morphs & associated consumable items (Sugar Cookie & Egg Nog).</b> (by Rfpnj)</ul>"
	
			+"<li>Clothing & Items:</li>"
			+"<ul>Added: Mince pie (non-racial consumable item).</ul>"
			+"<ul>Added: Antler headband (neutral femininity, head slot).</ul>"
			+"<ul>Added: Snowflake earrings (neutral femininity, ear piercing slot).</ul>"
			+"<ul>Added: Snowflake nose stud (neutral femininity, nose piercing slot).</ul>"
			+"<ul>Added: Snowflake necklace (neutral femininity, neck slot).</ul>"
			+"<ul>Added: Festive sweater (neutral femininity, torso-over slot).</ul>"
			+"<ul>Added: Jolnir's hat (neutral femininity, head slot, Jolnir set).</ul>"
			+"<ul>Added: Jolnir's dress (feminine, torso slot, Jolnir set).</ul>"
			+"<ul>Added: Jolnir's coat (neutral femininity, torso slot, Jolnir set).</ul>"
			+"<ul>Added: Jolnir's boots (masculine, foot slot, Jolnir set).</ul>"
			+"<ul>Added: Jolnir's heeled boots (feminine, foot slot, Jolnir set).</ul>"
			+"<ul>Added: Kimono (feminine, torso slot, Geisha set).</ul>"
			+"<ul>Added: Kanzashi (feminine, hair slot, Geisha set).</ul>"
			+"<ul>Added: Geta (feminine, foot slot, Geisha set).</ul>"
			+"<ul>Added: Dress coat (feminine, torso-over slot).</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Nyan's hair and fur colours are now black.</ul>"
			+"<ul>Reduced maximum prostitute spawn chance from 50% to 30%.</ul>"
			+"<ul>Added a 20% chance for NPCs to have unusual hair colours.</ul>"
			+"<ul>Changed hair length calculation to make alligator-morphs naturally bald, but only if their face type is that of an alligator-morph's (i.e. partial/lesser alligator-morphs will have human hair lengths).</ul>"
			+"<ul>NPCs will no longer generate new clothes while you're still interacting with them.</ul>"
			+"<ul>Added in (basic, to be expanded upon later) self-virginity-loss variants.</ul>"
			+"<ul>Made all self-actions in sex have the masturbation fetish associated with them.</ul>"
			+"<ul>Added amber & grey colours for demon skin.</ul>"
			+"<ul>Added cock length to selfie/character viewer screens.</ul>"
			+"<ul>Updated all weather status effect icons.</ul>"
			+"<ul>Added horn length enchantment option when making potions.</ul>"
			+"<ul>Added horse-morph and reindeer-morph names.</ul>"
			+"<ul>Added 'Morph' difficulty level (enemies scale level with you, but do normal damage).</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>Fixed another cause of game imports not working.</ul>"
			+"<ul>Fixed leg skin/fur colour not being displayed properly in selfie view.</ul>"
			+"<ul>Fixed bug where NPCs trying to pull down the Milk Maid's dress would cause a game crash (by fixing bug where the top couldn't be pulled down).</ul>"
			+"<ul>Gator's Gumbo TF menu now shows correct number modifiers for TF descriptions in enchantment menu.</ul>"
			+"<ul>Alligator-morph items can now be fed to NPCs like other consumables.</ul>"
			+"<ul>Fixed form input fields not displaying apostrophes and quotation marks correctly.</ul>"
			+"<ul>Minor typo fixes.</ul>"
			+"<ul>Changing your body (using the demonic transformation menu) during sex will now correctly update sex actions accordingly.</ul>"
			+"<ul>Fixed cause of UI bugs where the bottom response bar would not be anchored to the bottom of the screen.</ul>"
			+"<ul>Fixed some incorrect stats for the 'alligator-morph' status effect.</ul>"
			+"<ul>Fixed more causes of crashes in game importing.</ul>"
			+"<ul>Fixed male prostitute names.</ul>"
			+"<ul>Time will now pass in the Enforcer HQ area.</ul>"
			+"<ul>Fixed bug where highlighted hair was being treated as a natural colour.</ul>"
			+"<ul>Fixed rarity of alligator-morph's bottled essence.</ul>"
			+"<ul>NPC's body preferences (what they want to TF you into after beating you) should now be correctly loaded on game import.</ul>"
			+"<ul>Fixed forced TF bug (where all NPCs would keep giving you human penis or vagina parts even if they wanted to turn you into something else).</ul>"
			+"<ul>Slaves with the transformation fetish which are assigned as a test subject, but without feminine or masculine transformation specified, now correctly receive a gain in affection when Lilaya performs tests on them.</ul>"
			+"<ul>Having furry encounters enabled while having 'forced TF racial limits' set to 'Human Only' should no longer have the problem of the characters not giving you any genitals when you are transformed.</ul>"
			+"<ul>Prostitutes should no longer be able to spawn in as virgins.</ul>"
		+ "</list>"

		
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.96.1 (Hotfix)</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Minor typo fixes. (by Rfnpj)</ul>"
			+"<ul>Fixed instances of wings being described as allowing flight when they shouldn't. (by tukaima)</ul>"
			+"<ul>Fixed integer to string parsing error, as well as correcting the 'times fought' value on NPC-occupied city street squares. (by Lucasvdlaan)</ul>"
				
			+"<li>Clothing:</li>"
			+"<ul>Added: Men's kimono (masculine, torso slot, Ronin set).</ul>"
			+"<ul>Added: Haori (masculine, over-torso slot, Ronin set).</ul>"
			+"<ul>Added: Men's geta (masculine, foot slot, Ronin set).</ul>"
				
			+"<li>Other:</li>"
			+"<ul>Added option to have submissive sex with geisha Lilaya.</ul>"
			+"<ul>Reverted prostitutes not being able to be pure virgins (3% chance of pure virgins to be prostitutes).</ul>"
			+"<ul>You can now give Yuletide Gifts to slaves and offspring.</ul>"
			+"<ul>Added options to change the current month in the debug menu.</ul>"
				
			+"<li>Bugs:</li>"
			+"<ul>Yuletide Gifts, mince pies, egg nog, and sugar cookies will no longer spawn in alleyways.</ul>"
			+"<ul>Fixed antler headband being able to be dyed multiple colours.</ul>"
			+"<ul>You can no longer use NPCs' items during sex.</ul>"
			+"<ul>Fixed the cause of several strange issues where thigh sex would be treated as a self action.</ul>"
			+"<ul>Fixed some slave room values displaying '/day' instead of '/hour'.</ul>"
			+"<ul>Fixed rounding errors in the slave management screen.</ul>"
			+"<ul>Ending Lilaya's geisha sex scene now correctly moves you back to your room.</ul>"
			+"<ul>Moved Lilaya's and Kate's 'Grow cock' action to all sex scenes (available if your partner is a demon).</ul>"
			+"<ul>Chemise should no longer be able to be pulled up without first removing/displacing appropriate clothing.</ul>"
			+"<ul>Fixed bug where both NPCs and the player would be able to lose penile virginity multiple times.</ul>"
			+"<ul>Fixed bug where changing body-part colours in Kate's shop would charge you double (most cases were of being charged 400 instead of 200).</ul>"
			+"<ul>Fixed bug in Kate's shop where recolouring body hair wouldn't work.</ul>"
			+"<ul>Fixed major display bugs in the light theme (although the colours are still not great...).</ul>"
			+"<ul>Fixed some bugs in character creation's sex experience options (you now correctly start having lost penile and anal virginities if you select the appropriate choices).</ul>"
			+"<ul>Scarlett should now be able to be fed items during sex (some of the text might not be correct, but the effects should work).</ul>"
			+"<ul>Fixed major (and quite specific) bug which was causing both trader and player inventory to become unresponsive.</ul>"
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

		credits.add(new CreditsSlot("Anonymous", "", 0, 6, 115, 38));
		

		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 1));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 1));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 1, 0));
		
		
		
		
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
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 3));
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
