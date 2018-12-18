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
 * @version 0.2.12
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.12.96";
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
			+ "There were a considerable amount more bugs in v0.2.12.6 than I expected, and it took quite a while to get them all fixed."
			+ " As I then moved on to adding Lyssieth's content, I realised that I needed to add her succubus guard, Elizabeth, and in order to add her, I needed to get the elite guards' uniforms added."
			+ " After doing all <i>that</i>, I found myself out of time in which to get the content added."
		+ "</p>"
			
		+ "<p>"
			+ "As I really want v0.3 to signify that the new area is in, I've called this 0.2.12.9."
			+ " I'm going to lose Saturday due to staying up so late trying to get this version finished in time, but I'll spend Sunday and Monday getting Lyssieth's content added, and will release a hotfix/mini-update of v0.3 on Wednesday night. ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.12.96</h6>"
			+"<li>Bugs:</li>"
			+"<ul>Fixed issue where random NPCs were appearing in tiles.</ul>"
			+"<ul>Fixed your name being displayed as 'You' in your character panel on the left of the screen.</ul>"
			+ "<br/>"
			
			+ "<h6>v0.2.12.95</h6>"
			+"<li>Bugs:</li>"
			+"<ul>Fixed major bug where entering a lot of areas would freeze the game.</ul>"
			+"<ul>Exiting the citadel after beating the Siren now correctly displays the 'Enter' action.</ul>"
			+"<ul>Beating the Siren now correctly removes Takahashi from the citadel. (Also retroactively removes her if you've already beaten the Siren.)</ul>"
			+"<ul>Fixed desaturated youko tile icon still being coloured.</ul>"
			+"<ul>Fixed (for real this time) the bug where asking Elizabeth about her uniform would lock you into that tile.</ul>"
			+"<ul>The arcane revolver now correctly drains 1 essence when fired.</ul>"
			+"<ul>Fixed issue where Fyrsia would appear in the other imp fortresses, sometimes in the place of the correct leader. (She will move back to her own keep once you leave/re-enter any fortress.)</ul>"
			+"<ul>Fixed missing dialogue when entering Hyorlyss's pacified keep.</ul>"
			+"<ul>Enemies no longer only use defensive spells in combat.</ul>"
			+"<ul>Fixed numerous issues with clubbers in the Watering Hole - it should all work correctly again now.</ul>"
			+"<ul>Fixed age preferences not being saved and restored between games.</ul>"
			+"<ul>Fixed issue with changing Youko slaves' names.</ul>"
			+ "<ul>Fixed background errors being generated when NPCs were removed from the game.</ul>"
			+ "<br/>"
		
			+ "<h6>v0.2.12.9</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added NPC 'Elizabeth' - the guard at Lyssieth's palace. Also started to update her dialogue - this will be finished for an upcoming patch.</ul>"
			+"<ul>If your save is from a version prior to 0.2.12.6, the imp fortresses will all reset, as several things changed from then to now, so it all needs to be reset in order to work. The imp citadel should also reset if you cleared it using the quick 'Clear' button in versions prior to 0.2.12.6.</ul>"
	
			+"<li>Contributors:</li>"
			+"<ul>Fixed classpath issue causing game to not compile correctly. (#981 by Alaco)</ul>"
			+"<ul>Fixed Rental Mommy & Rental Daddy t-shirt errors, which was causing the rental mommy encounter to throw errors. (#984 by CognitiveMist)</ul>"
	
			+"<li>Clothing:</li>"
			+"<ul>Added colours 'midnight brown' and 'olive'.</ul>"
			+"<ul>Added 'Lyssieth's Guard' set, which contains:</ul>"
			+"<ul>Service dress tunic: Over-torso slot, feminine.</ul>"
			+"<ul>Service dress hat: Head slot, feminine.</ul>"
			+"<ul>Service dress skirt: Leg slot, feminine.</ul>"
			+"<ul>Service dress shoes: Foot slot, feminine.</ul>"
			+"<ul>Arcane revolver: Ranged, on-handed.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Humans, demons, cat, dog, wolf, horse, and cow-morphs now have a 1% chance to have heterochromatic eyes. (Before it was just humans, who had a chance of 50%.)</ul>"
			+"<ul>Added more detailed messages to any negative affection changes at the end of sex.</ul>"
			+"<ul>Added several new item tags, allowing clothing mods to define fuckable nipple requirement, arm/leg hinderance, speech muffling, discarding on unequip, and equip during sex.</ul>"
			+"<ul>Also added 'SEALS_AREA' variations for the 'PLUGS_AREA' cltohing tags. Sealing counts as sealing the orifice without being inserted into it.</ul>"
			+"<ul>The strap-on, anal beads, clover clamps, realistic dildo, butt plugs, insertable dildo, and strapless dildo can now all be equipped on non-unique NPCs during sex.</ul>"
			+"<ul>Wearing clothing with a 'PLUGS_VAGINA' tag is now detected and influences the wearer's orgasm descriptions.</ul>"
			+"<ul>In order to fix a bug, all characters now have surnames, with generic characters having randomly-generated ones. Surnames can be seen in the character view screen.</ul>"
			+"<ul>Offspring now inherit their mother's surname. If your surname is blank, your offspring will be given a random surnames. If you change your surname at the town hall, all of your offspring will have their surnames updated.</ul>"
			+"<ul>You can now change your slave's surnames in their inspection screen.</ul>"
			+"<ul>Improved map rendering performance.</ul>"
			+"<ul>Non-spectating participants in non-masturbation sex scenes now gain experience for the 'Pure virgin' fetish if they have a vagina, end the scene while being a virgin, and at least one of their partners orgasmed.</ul>"
			+"<ul>Added arcane essence cost of using/firing weapons to their tooltips (if non-zero).</ul>"
			+"<ul>Breast feeding part of the 'giving birth' scene is now changed to bottle-feeding if your nipples are inaccessible, your nipples are not normally-shaped, or you have lactation content turned off.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Fixed grammatical errors in several sex actions.</ul>"
			+"<ul>Fixed parsing issues in Submission imp attack dialogue.</ul>"
			+"<ul>Fixed parser errors in imp citadel content.</ul>"
			+"<ul>Fixed issue where NPCs would sometimes get stuck trying to remove clothing during sex.</ul>"
			+"<ul>Heterochromatic eyes will no longer be generated with the same colour. (Although you can still make them the same colour via Kate's shop or demon/slime transformations.)</ul>"
			+"<ul>Fixed formatting error in eye descriptions.</ul>"
			+"<ul>Fixed repetitive mouth-knotting orgasm description.</ul>"
			+"<ul>Fixed incorrect descriptions of tests being performed on slaves in Lilaya's lab.</ul>"
			+"<ul>Fixed error log being spammed by messages informing you that NPCs were returning null in some methods.</ul>"
			+"<ul>Fixed rarity of unidentified clothing being displayed in the dye preview window.</ul>"
			+"<ul>Friendly occupants will no longer get a job as 'an unemployed', and any occupants already affected by this in your save will start looking for a new job when you load in (unemployed occupants that have already moved out will get a new job).</ul>"
			+"<ul>Fixed damage tooltips showing different values before and after buying a weapon from a shop. (When weapon is not held by you, tooltip now displays both the holder's and your damage.)</ul>"
			+"<ul>Fixed issue where returning to a Hyorlyss's keep after gaining her key would throw a background error and softlock the game. </ul>"
			+"<ul>Fixed issue where imps could be transformed to be taller to the point where they were classified as demons, and vice-versa.</ul>"
			+"<ul>Added some error handling to catch the unusual case of an NPC's name being loaded in as an empty String.</ul>"
			+"<ul>Fixed issue with rarity detection in clothing sometimes throwing a background error.</ul>"
			+"<ul>Fixed issue with Jhortrax (the male imp fortress leader) not inserting dildo after combat loss sex scene. (Thanks to 'asymptote' for details on how to fix this.)</ul>"
			+"<ul>Fixed issue where trying to open the debug menu in the main menu would throw background errors and break teh game until restarted.</ul>"
			+"<ul>Fixed requirement of 'Tameshigiri' action in Jhortrax's imp fortress to take your weapon's modified damage into account.</ul>"
			+"<ul>Fixed issue where saves wouldn't load into 0.2.12.6 correctly due to Youko surnames being blank.</ul>"
			+"<ul>Fixed incorrect descriptions in alleyway succubus quick transform breast size tooltips.</ul>"
			+"<ul>Fixed issue with some clothing items (such as the croptop) not concealing slots correctly.</ul>"
			+"<ul>Fixed incorrect effects description of Biojuice Canister.</ul>"
			+"<ul>Fixed slime encyclopedia entry not working.</ul>"
			+"<ul>Fixed victory in Wolfgang & Karl's fight not dropping any items.</ul>"
			+"<ul>Fixed bug where spectators in sex were using incorrect actions (which were being drawn from the available actions of non-spectators).</ul>"
			+"<ul>Defeating the imps in the citadel now correctly sets the 'imps defeated' flag, resolving issues related to the youko still being in her laboratory, imps guards still being in the citadel, and the 'challenge' action remaining available.</ul>"
			+"<ul>Added missing dialogue after citadel's imp defeat sex scene.</ul>"
			+"<ul>Fixed a couple of issues with imp fortress's demon leaders not returning to their keeps.</ul>"
			+"<ul>Fixed game freezing if you lost to imp fortress gate guards, or had sex with the gate guards, and were then thrown out.</ul>"
			+"<ul>Fixed getting stuck when asking Lyssieth's gate guards about their uniforms.</ul>"
			+"<ul>Fixed issue with clubbers spawning in the night club having their home tile being set as the very bottom-left of Dominion's street tiles. (This will also retroactively fix all clubbers with this issue.)</ul>"
			+"<ul>Fixed item entries in the encyclopedia being lost between saves.</ul>"
			+"<ul>Self-impregnations now count offspring as just mothered, not both mothered and fathered, in the pregnancy stats screen.</ul>"
			+"<ul>Self-milking should now correctly take into account milking room upgrades. Also added stats into the milking room upgrades info to let you know exactly what the base maximum output per hour is.</ul>"
			+"<ul>Fixed issue where assigning slaves to be publicly used could sometimes result in an NPC being placed out-of-bounds of the map, which would cause your save file to not load correctly.</ul>"
			+"<ul>As a temporary fix to enslavement dialogue bugs, the imps inside fortresses & the citadel can no longer be enslaved. I will add support for enslaving them later, but for now, you can get just imp slaves from the tunnels.</ul>"
			+"<ul>Duplicate arcane keys and key-chain necklaces will be removed when you load in to 0.3. Lyssieth's ring will also be removed, if it should have been.</ul>"
			+"<ul>Fixed modded clothing using the new shortened equip descriptions format using the 'rough' variant in non-rough scenes, and vice-versa.</ul>"
			+"<ul>Fixed issue where your companion would be counted as a submissive, instead of dominant, spectator when choosing 'Solo sex' after beating imp groups.</ul>"
		
			+"<br/>"
			
			+ "<h6>v0.2.12.6</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added all dialogue for solo and companion scenes in the imp citadel.</ul>"
			+"<ul>Added post-defeat sex scene for losing Siren combat. (There was a more involved sequence of events planned for this, but I didn't have time to add it - it will be added at some point in the future.)</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Switched primary and secondary colours for tagged choker, and changed the icon's scribble to read 'Bound Bitch'.</ul>"
			+"<ul>Female horses and reindeer now have a base ass size of 'large', and female cows have 'huge'. (This is randomised when NPCs are generated, so some will still have larger or smaller asses.)</ul>"
			+"<ul>Increased base size of all race's female nipples, and additionally increased base size of female cow areolae.</ul>"
			+"<ul>Improved race menu in encyclopedia, so that subspecies of a race are displayed in a sub-menu.</ul>"
			+"<ul>Consolidated all elemental races into one 'elemental' race (keeping the elemental types as subspecies), and converted individual elemental damage/resistance to a universal 'elemental' one.</ul>"
			+"<ul>Moved witch set out into res folder, added tertiary colour to witch hat, and added 'wide-brimmed' hat variation.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Fixed BDSM clothing set icon to be black instead of white.</ul>"
			+"<ul>You can no longer escape from the boss fights in imp fortresses, nor the imp 'challenge' fight in the citadel.</ul>"
			+"<ul>Fixed cause of tattoo tooltips being cut off at the bottom. (The tooltip formatting system is still a little buggy - I'll remake it at some point.)</ul>"
			+"<ul>Fixed breast size in subspecies encyclopedia using the enum name for the description.</ul>"
			+"<ul>Added some error handling for if clothing throws errors related to patterns not being present.</ul>"
			+"<ul>Fixed issue with youko icons not having a background circle.</ul>"
			+"<ul>Fixed imp damage/resistance attributes not working.</ul>"
			+"<ul>Fixed imps sometimes giving birth to non-imps.</ul>"
			+"<ul>Your companion now gets the same attribute boost as you do after winning the imp fight when using 'challenge' in the citadel.</ul>"
			+"<ul>Fixed being able to start the imp challenge even after the siren had been defeated.</ul>"
			+"<ul>Fixed issue with the citadel's imp challenge freezing at the end of combat.</ul>"
			+"<ul>Your status panel is no longer minimised when acting as a spectator.</ul>"
			+"<ul>Fixed issues with some unique NPCs not putting their clothing back on correctly after sex.</ul>"
			+"<ul>Takahashi will now correctly deny orgasms during her oral scene.</ul>"
			+"<ul>Fixed issue where dropping equipped clothing during sex wouldn't work on the first button press.</ul>"
			+"<ul>The demon leaders should now correctly be in the siren's throne room when you get there.</ul>"
		
			+"<br/>"
			
			+ "<h6>v0.2.12.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added the majority of the framework for the citadel, which can be entered and tested. <b>Warning:</b> I'd advise saving your game before going through it, so you can reload and do the content once it's fully finished in 0.2.12.6.</ul>"
			+"<ul>Added a map of the museum in the prologue.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Simplified getting data from XML. Refactored clothing mod loading from file. Fixed mod loading issue, so that when XML file is found invalid, the loading of it is halted, error logged and program continues trying loading other mods. (#657 by BlazingMagpie)</ul>"
			+"<ul>Tidied up pregnancy calculation code. (#736 by Pimgd)</ul>"
			+"<ul>Added sexual orientation preferences. (#766 by GalacticOtter)</ul>"
			+"<ul>Added condom durability code. (#903 by picobyte)</ul>"
			+"<ul>Fixed bug where occupants who have moved out get duplicated descriptions on Harpy Nest tiles. (#934 by KittenBarrage)</ul>"
			+"<ul>Improved mod error descriptions, and fixed support for core attributes. (#969 by Pimgd, #945 by CognitiveMist)</ul>"
			+"<ul>Corrected misspelled instances of 'coreAtributes' in clothing xml files. (#967 by Pimgd)</ul>"
			+"<ul>Deduplicated code in the Pregnancy Stats screen, and added a percentage of offspring encountered readout at the top. (#917 by uglybeard)</ul>"
			+"<ul>Added font support for non-windows systems. (#919 by DJ4ddi)</ul>"
			+"<ul>Typo fixes in weapon files. (#965 by Pimgd)</ul>"
			+"<ul>Made improvements to NPC loading method, so games now load faster. (#949 by AlacoGit)</ul>"
			+"<ul>Made considerable optimizations to all svg images, which should speed up the game wherever lots of icons are rendered. (#940 by DJ4ddi)</ul>"

			+"<li>Other:</li>"
			+"<ul>For clothing: Added a 'midnight' colour variation for purple, blue, and red. Also added 'deep pink', 'hot pink' and 'mustard yellow'.</ul>"
			+"<ul>Made 'leather belt' unisex.</ul>"
			+"<ul>NPC affection displays in the character sheet screen now only shows affection towards their relations, unique NPCs, your slaves, and friendly occupants.</ul>"
			+"<ul>In the 'Characters present' box in the top-right, added an indication of background NPCs present in each tile.</ul>"
			+"<ul>The eggplant can now be enchanted.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed parsing error in Hyorlyss's fortress.</ul>"
			+"<ul>Fixed issue with button tooltips when managing NPCs clothing in the inventory screen.</ul>"
			+"<ul>Fixed issue where if you had the sadist fetish, dealing damage would softlock combat.</ul>"
			+"<ul>Fixed issue with 'kneeling oral' position having incorrect description at start of sex.</ul>"
			+"<ul>Fixed background errors when saving a game after removing modding clothing/weapons from your mod folders.</ul>"
			+"<ul>Fixed reindeer overseers' interaction menu being available on their 'home' tile, but not the tile they're actually in. (Fixed by moving their home tile to always be the tile they're in.)</ul>"
			+"<ul>Fixed katherine and Kelly getting stuck on the stairs in Zaranix's home. (They move around the area after you're cleared that part of the main quest.)</ul>"
			+"<ul>Fixed some minor inconsistent descriptions, typos, and parsing errors.</ul>"
			+"<ul>Fixed issue where spectators would join the sex scene when switching to the doggy style position.</ul>"
			+"<ul>Fixed issue where combat in the imp fortresses would never end if you were playing on higher difficulties. Also fixed bug where post-combat sex could sometimes result in a never-ending masturbation scene.</ul>"
			+"<ul>Fixed fluids not being described correctly when you gained an addiction for them.</ul>"
			+"<ul>Fixed incorrect equip/unequip descriptions for all of the Siren's clothing, distressed jeans, hakama, gothic boots, and striped gloves.</ul>"
			+"<ul>Added correct association with oral fetishes to the 'Thrust into mouth' and 'Take into mouth' actions during paizuri.</ul>"
			+"<ul>Fixed incorrect title for the stop paizuri action.</ul>"
			+"<ul>Fixed issue with oral actions not being available in face sitting position is the one doing the sitting was interacting with their partner's groin in any way.</ul>"
			+"<ul>Fixed arcane elemental being described as being bound to air.</ul>"
			+"<ul>Fixed 'initial penetration' text when starting a footjob not taking into account any shoes/socks being worn.</ul>"
			+"<ul>Fixed parser error in the prologue's sex scene.</ul>"
			+"<ul>You can no longer modify unique and non-slave NPCs' fetishes, just like you can't TF them. The hypno watch is considered a fetish-altering item for this.</ul>"
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
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, null, WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE));

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
