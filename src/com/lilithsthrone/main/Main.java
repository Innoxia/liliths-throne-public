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
 * @version 0.2.7
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.7.5",
			VERSION_DESCRIPTION = "Alpha";
	
	private final static boolean DEBUG = true;

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello again! :3"
		+ "</p>"
			
		+ "<p>"
			+ "Here's my current progress towards 0.2.8!"
			+ " I've managed to get all of the sex code rework completely finished (there was a considerable amount that still needed to be changed, which I only realised on Friday),"
			+ " as well as getting some bugs and minor things added."
		+ "</p>"
			
		+ "<p>"
			+ "Now that all of the code work is finished, I'll move on to writing the NPC-on-NPC sex actions and adding Nightlife content for the full release of 0.2.8 next Wednesday! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.7.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Finished work on sex action code to fully support NPC-on-NPC actions.</ul>"
			+"<ul>Added support for clit-fucking (when a clit is large enough to act as a pseudo-cock) and foot-related sex actions. (The actions will be added very soon!)</ul>"
			+"<ul>Added support for tentacles. (The actions will be added when tentacle TFs are added, which will be a little later on.)</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Added modifiers and girth for clitoris, with associated TFs.</ul>"
			+"<ul>Added oral position for chair sex.</ul>"
			+"<ul>Changed Broodmother and Seeder fetishes to be perks instead.</ul>"
			+"<ul>Added 'Face Sitting' as a generic position in sex scenes.</ul>"
			+"<ul>Added hoofed legs as a TF option for demons, with a 25% chance for demon NPCs to spawn in with hoofs.</ul>"
			+"<ul>Added 'zebra' as an equine subspecies, with a 'zebra tail' type.</ul>"
			+"<ul>Converted Kate's and Lilaya's very old chair sex scene format. (I will add the old chair-specific descriptions to sex actions as I convert them to NPC-on-NPC format.)</ul>"
			+"<ul>Added milk and cum regeneration enchantments for clothing.</ul>"
			+"<ul>Added foot fetishes. (Only Amber's scenes have foot-related actions at the moment, but I will be adding foot actions very soon!)</ul>"

			+"<li>Contributions:</li>"
			+"<ul>Cleaned EnchantingUtils getCost method. (Pimgd)</ul>"
			+"<ul>Added penis-related fetishes. (Rfpnj)</ul>"

			+"<li>Norin's Clothing Contribution:</li>"
			+"<ul>Added: Anal beads (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Clover clamps (nipple slot, BDSM set, sold by Finch).</ul>"
			+"<ul>Added: Realistic dildo (vagina slot, sold by Finch).</ul>"
			+"<ul>Added: Bat-wing barbells (nipple-piercing slot, sold by Kate).</ul>"
			+"<ul>Added: Heart barbells (nipple-piercing slot, sold by Kate).</ul>"
			+"<ul>Added: 'Caution-When-Wet' piercing (navel slot, sold by Kate).</ul>"
			+"<ul>Added: Tail ribbon (tail slot, sold by Nyan).</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Jewelled butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Jewelled-heart butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Tail butt plug (anal slot, sold by Finch).</ul>"
			+"<ul>Added: Insertable dildo (vagina slot, sold by Finch).</ul>"

			+"<li>Sex AI:</li>"
			+"<ul>Fixed issue where NPCs would sometimes perform actions that they didn't like.</ul>"
			+"<ul>Fixed NPCs never starting oral, even if they wanted to, if they still had their oral virginity.</ul>"
			+"<ul>NPCs will now prefer to penetrate using their penis rather than their tail.</ul>"
			+"<ul>NPCs should now be preferring to penetrate their partner before themselves.</ul>"

			+"<li>Other:</li>"
			+"<ul>Updated credits page.</ul>"
			+"<ul>Having a sheathed penis will now conceal your cock bulge from gender detection up to 16 inches. Non-sheathed penis bulge is visible at 8 inches.</ul>"
			+"<ul>Added freckles as a pattern for demon skin.</ul>"
			+"<ul>Added description of wing colour to selfie.</ul>"
			+"<ul>The chemise no longer blocks groin areas.</ul>"
			+"<ul>Companions and companions' elementals no longer have their level affected by difficulty settings. (Making the higher difficulty levels harder.)</ul>"
			+"<ul>You can now see what your partner in sex is wanting (under the 'Desires' status effect description).</ul>"
			+"<ul>Removed tooltips from enchanting 'Limit' and 'Potency' buttons. (They were just repeating the button text and getting in the way.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed issue with map rendering incorrectly when zoomed out.</ul>"
			+"<ul>Fixed game sometimes incorrectly scrolling back up to the top of dialogue.</ul>"
			+"<ul>Fixed 'Stroke cock' actions not showing up in sex scenes.</ul>"
			+"<ul>Fixed some incorrect sex action availability.</ul>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>NPCs will no longer spawn wearing leg clothing with dresses.</ul>"
			+"<ul>AI in sex will now correctly use actions based on their preferences (this bug was affecting cultists not respecting your 'Offer pussy' or 'Offer ass' choice).</ul>"
			+"<ul>Fixed issue where slimes were always spawning in with human-shaped bodies. (They can now spawn as slimes of any morph type.)</ul>"
			+"<ul>Fixed clothing pattern colours not being saved/loaded.</ul>"
			+"<ul>Fixed Lilaya asking you to pull out so as to not get pregnant when she was already pregnant.</ul>"
			+"<ul>Fixed limb description in selfie not taking into account body shape.</ul>"
			+"<ul>Fixed issue with covering patterns not working (which was causing the only patterns for hair to be plain or ombre).</ul>"
			+"<ul>Cum expulsion enchantment is now correctly free when you have the Water school passive.</ul>"
			+"<ul>Fixed incorrect penile virginity loss text.</ul>"
			+"<ul>Fixed formatting bug where periods would sometimes incorrectly carry over to the next line.</ul>"
			+"<ul>Fixed some instances of 'Submit' not having a corruption bypass.</ul>"
			+"<ul>Fixed another issue with companions' elementals not being dismissed properly.</ul>"
			+"<ul>Fixed issue of starting a new game as an exported slave identifying you as owning yourself.</ul>"
			+"<ul>Fixed deny orgasm action not working correctly, and added the denial fetishes as associated fetishes.</ul>"
			+"<ul>Fixed Brax always resisting in submissive sex.</ul>"
			+"<ul>Fixed offspring referring to you as their mother when you first greet them, even if you were the father.</ul>"
			+"<ul>Fixed NPCs reacting as though they knew you had a penis even if you were showing no bulge.</ul>"
			+"<ul>Fixed special attack descriptions returning incorrect target text.</ul>"
			+"<ul>Improved modded clothing load fail error messages.</ul>"
			+"<ul>Fixed nipple and urethra penetration actions not working.</ul>"
		+ "</list>"
		;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

			+ "<p>This game is a <b>fictional</b> text-based erotic RPG." + " All content contained within this game forms part of a fictional universe that is not related to real-life places, people or events.<br/><br/>"

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
		
		
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 7));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 7));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 7, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 2, 2));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));

		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 2));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 5));
		
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 4, 0));

		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 3, 0));

		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Drahin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 2, 0));
		
		

		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 1, 0));
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 9));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 2));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 9, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 9));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 8, 3));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 9));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 1));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 5));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 10));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 9, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 7));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 6));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 11, 0));
		
		
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
