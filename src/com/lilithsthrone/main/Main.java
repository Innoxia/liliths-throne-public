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

	public static final String VERSION_NUMBER = "0.2.11";
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
			+ "First of all, I'm so sorry for the length of time that I was absent! Everything's back to normal now, so after this release, further releases will be back on track for once every 7-10 days."
		+ "</p>"
			
		+ "<p>"
			+ "The major addition in this version is the ability for NPCs to use large group sex positions."
			+ " This is only implemented for the imps wandering through the tunnels in Submission at the moment, but I will make use of this for lots more encounters in the future!"
		+ "</p>"
			
		+ "<p>"
			+ "Getting all the variations for this group sex implemented in the code was a little time consuming, and I also had to spend a lot of time on writing in companion variations for the imp encounters."
			+ " As a result, the imp fortresses aren't quite ready yet, but I'll be working on them over the next few days, and will push the framework to the dev github once it's done."
			+ " After that, I'll write in all the content, and get Lyssieth's quest content added in time for 0.2.11.5! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.11</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added all dialogue for imp encounters in submission, including variations for if you have a companion with you. Imps now TF your companion alongside you if you lose or submit.</ul>"
			+"<ul>Added doggy-style and missionary group sex support, and made these positions available for imp gangs found in the tunnels, both for loss and victory scenes, with and without a companion. Includes a couple of new size-difference positions each position.</ul>"
			+"<ul>Persistent NPCs that are in an imp tunnel will no longer be encountered while the associated fortress is active.</ul>"

			+"<li>Items:</li>"
			+"<ul>Added weapon 'metal pipe'. (Found in Submission's tunnels, and carried by some imp gang members.)</ul>"
			+"<ul>Added weapon 'crude shield'. (Carried by some imp gang members.)</ul>"
			+"<ul>Added weapon 'Imp arcanist's staff'. (Carried by imp witch/wizard.)</ul>"
			+"<ul>Added clothing 'Foot wraps'. (Foot slot, only spawns on imp gang members.)</ul>"
			+"<ul>Added clothing 'Loin cloth'. (Leg slot, only spawns on masculine imp gang members.)</ul>"
			+"<ul>Added clothing 'Ragged chest wrap'. (Chest slot, only spawns on feminine imp gang members.)</ul>"
			+"<ul>Added clothing 'Ragged skirt'. (Leg slot, only spawns on feminine imp gang members.)</ul>"
			+"<ul>Added clothing 'Imp arcanist's hat'. (Head slot, only spawns on imp witch/wizard.)</ul>"

			+"<li>Other:</li>"
			+"<ul><b>Moved:</b> Ongoing sex descriptions from the main body of text in sex, to an extra description in the body-part status effect tooltips. (As I felt that the generic ongoing descriptions were flooding the scene descriptions, especially with 3+ sex participants.)</ul>"
			+"<ul>Added 'Spread legs' action in missionary (when on back).</ul>"
			+"<ul>Added receiving penetration actions in missionary, when acting as the one kneeling between legs.</ul>"
			+"<ul>When starting a new game as an imported character, your money will no longer reset to 500.</ul>"
			+"<ul>Added footjob availability when performing doggy-style oral.</ul>"
			+"<ul>Improved virility tease dialogue.</ul>"
			+"<ul>Added a button to reset age preferences to their default values.</ul>"
			+"<ul>Putting on a condom will now clear lubrications on the target's penis.</ul>"
			+"<ul>You can now have sex with your companion in Dominion's alleys and Submission's tunnels even if there are attackers in that tile.</ul>"
			+"<ul>Talking to an NPC after beating them in combat will now set their affection towards you to +10, instead of incrementing it by +10. (So you no longer need to beat them numerous times if their affection towards you was low.)</ul>"
			+"<ul>Added threesome and 'offer companion' actions to friendly NPC encounters. (When you re-encounter generic NPCs after having talked to them.)</ul>"
			+"<ul>NPCs will no longer spawn in disliking or hating the fetishes related to using their penis or vagina.</ul>"
			+"<ul>Reduced imp's penis size, and randomised their heights (within the 'tiny' range). Also increased imps' wing size, so that they're all able to fly.</ul>"
			+"<ul>Actions taken by NPCs during sex are now labelled, to help with quick identification of what's going on in large sex scenes.</ul>"
			+"<ul>Improved sex AI's method for determining what actions to take in sex, so that they now prefer to choose actions related to penis or vagina use.</ul>"
			+"<ul>Adjusted imp and alpha-imp starting attributes, so they no longer spawn in with the same stats as demons. (They are now a lot weaker, as they were in older versions.)</ul>"
			+"<ul>Slightly reduced NPC's chance of using actions that correlate to their current preference to 66%. (Their preference being the pink text at the bottom of the 'Desires' tooltip.)</ul>"
			+"<ul>Increased chance of NPCs targeting opposite partners in sex (i.e. doms targeting subs and vice versa), from a weighting of 2 to 5.</ul>"
			+"<ul>Tidied up formatting of multiple items gained after combat.</ul>"
			+"<ul>Added some more information to Claire's initial greeting dialogue, regarding the imp tunnels.</ul>"
			+"<ul>Doubled the 'Demon's Dagger' base damage from 6 to 12.</ul>"
			+"<ul>You can now click on sex status effects to select the character that's interacting with the associated area. (i.e. Clicking on your 'Pussy status' while someone is fingering you will now select that person.)</ul>"
			+"<ul>Did some more work to improve the AI's targeting in sex, and to reduce the occurrences of repeatedly starting & stopping actions.</ul>"
			+"<ul>NPCs will now try to expose genitals in foreplay, with a lesser priority than exposing breasts and mouth.</ul>"
			+"<ul>Penis size change potions now transform in increments of 1/5/10 instead of 1/5/15.</ul>"
			+"<ul>You can now start submissive penetrations in sex scenes in which you are the submissive, restricted partner. NPCs can still not do this. (i.e. You can get your partners to penetrate you in scenes where you couldn't before.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed issue with orgasm cum targets being illogical in multiple-partner sex scenes.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed doggy-style group sex action 'Slap ass' being able to be used on incorrect targets.</ul>"
			+"<ul>Fixed incorrect virginity loss descriptions from Rose taking your virginity in Lilaya's room.</ul>"
			+"<ul>Fixed incorrect parsing in large cum quantity creampies.</ul>"
			+"<ul>Fixed new game not starting if all age preferences were turned off in the content settings.</ul>"
			+"<ul>Fixed age not appearing correctly in character creation screen.</ul>"
			+"<ul>Fixed error in 'Spread ass' and 'Spread pussy' actions.</ul>"
			+"<ul>Fixed NPCs generating more and more accessories and piercings each time you visited them.</ul>"
			+"<ul>Fixed incorrect reaction to being impregnated by alleyway attackers.</ul>"
			+"<ul>Illogical actions in missionary position should now correctly be disabled.</ul>"
			+"<ul>Exported player characters will no longer be imported three years older than they should be.</ul>"
			+"<ul>Corrected incorrect nightclub dialogue, where refusing to follow your partner home returned toilet sex refusal text.</ul>"
			+"<ul>Fixed subspecies' status effect's attribute modifiers not being applied.</ul>"
			+"<ul>Fixed demon alleyway attacker's dialogue being a little mixed up.</ul>"
			+"<ul>Fixed some tooltip errors in inventory screen.</ul>"
			+"<ul>Fixed parsing error in handjob resistance description.</ul>"
			+"<ul>Enslaving your offspring should now work correctly.</ul>"
			+"<ul>NPCs will no longer be able to move into guest rooms whose occupant is currently acting as your companion.</ul>"
			+"<ul>Fixed bugs related to summoning elemental. You should now be able to summon your elemental without issue.</ul>"
			+"<ul>Fixed condom drink parsing sometimes incorrectly referring to an incorrect character, and fixed broken inventory tooltip when condom is equipped.</ul>"
			+"<ul>Friendly NPCs that you invite home will no longer get a job as a mugger or prostitute.</ul>"
			+"<ul>Fixed issues with illogical sex actions being available during penetrative sex in the missionary position.</ul>"
			+"<ul>Fixed characters in sex reacting to area reveals of characters that are watching.</ul>"
			+"<ul>Fixed formatting in hips TF description.</ul>"
			+"<ul>Fixed some incorrect vagina reveal dialogue.</ul>"
			+"<ul>Fixed incorrect special attack targeting descriptions.</ul>"
			+"<ul>Fixed the spell 'Arcane Arousal' not applying energy and aura damage once the target is at maximum lust.</ul>"
			+"<ul>Fixed incorrect 'Protective gusts' status effect description.</ul>"
			+"<ul>Fixed instances where NPCs orgasming in sex would present you with no actions to choose from, thereby soft-locking the game.</ul>"
			+"<ul>Fixed incorrect character references in the orgasm preparation action descriptions.</ul>"
			+"<ul>Fixed incorrect parsing of NPC's status at the end of sex.</ul>"
			+"<ul>Fixed a lot of 'Resist' actions not displaying any description.</ul>"
			+"<ul>Fixed instances where a resisting NPC would use inappropriate actions, such as starting a penetration, or getting their breasts groped.</ul>"
			+"<ul>Fixed NPCs with the non-con fetish not being able to take any action in consensual sex scenes.</ul>"
			+"<ul>Fixed issue where an NPC's orgasm might be skipped at the end of sex, if another NPC wanted to end sex and took a turn before them.</ul>"
			+"<ul>Fixed several parsing errors in sex actions.</ul>"
			+"<ul>When sex ends, the dialogue should no longer repeat ending statuses. (Things like essences gained, orifices stretched, and affection changes.)</ul>"
			+"<ul>Fixed oddities with the colouring of the first penis or vagina your character grows.</ul>"
			+"<ul>Fixed incorrect references during some orgasm reaction actions.</ul>"
			+"<ul>Fixed incorrect essence gain text at the end of multi-partner sex.</ul>"
			+"<ul>Fixed incorrect parsing in the 'Spread ass' and 'Spread pussy' actions.</ul>"
			+"<ul>Fixed imps and alpha-imps sometimes incorrectly spawning in with their body size randomised to make them demons/alpha-imps/imps.</ul>"
			+"<ul>Fixed issues with new imp fortress leaders spawning each time you loaded the game, which was slowly bloating save files.</ul>"
			+"<ul>Fixed issue with some npcs not dressing themselves properly.</ul>"

			+"<li>Additions after push to dev branch:</li>"
			+"<ul>Tested the new group sex positions, and fixed quite a few bugs related to that.</ul>"
			+"<ul>Fixed some bugs in the sex AI, and improved it for large group scenes. (This will still require some work.)</ul>"
			+"<ul>Fixed several conflicting methods in the group sex missionary position that were causing actions to be incorrectly disabled.</ul>"
			+"<ul>Remade imp fortress internal maps, and added gate guards and boss guards.</ul>"
			+"<ul>Added all imp fortress dialogue framework, with support for peaceful resolutions in the non-demon fortress. (All have placeholder dialogue for now.)</ul>"
			+"<ul>Fixed issue where you would end up fighting your own companion in the imp encounters.</ul>"
			+"<ul>Fixed your companion joining in with the sex after offering just yourself to imps in order to avoid a fight.</ul>"
			+"<ul>Added group oral sex position support.</ul>"
			+"<ul>In sex, NPCs are now far less likely to target another NPC of same sub/dom type if that target isn't attracted to the targeter.</ul>"
			+"<ul>Fixed issues where NPCs would not stop foreplay actions in order to perform real penetrations. (Such as stopping fingering in order to have penetrative sex.)</ul>"
			+"<ul>Fixed cum counts not being saved/loaded properly, which was causing tattoo counters to not keep a proper track of cum received.</ul>"
			+"<ul>Fixed issue with NPCs not calling you by the correct pet name.</ul>"
			+"<ul>Enslaving characters that are in your party will now work correctly. (I think this was the offspring enslavement bug? Everything else was working fine on my end.)</ul>"
			+"<ul>Typo and parsing fixes.</ul>"
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
