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
 * @version 0.2.10
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.10.2",
			VERSION_DESCRIPTION = "Alpha";
	
	private final static boolean DEBUG = false;

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
		
		"<p>"
			+ "Hello again!"
		+ "</p>"
			
		+ "<p>"
			+ "Here's 0.2.10! I've got all the non-slavery moving in content added for alleyway attackers, as well ad adding in all the dialogue that was placeholders for the dom clubber route in 0.2.9.5."
			+ " I've also added some more shops to Slaver Alley, as well adding a few other things here and there."
			+ " Alexa's content will have to be added in the next version, as I simply ran out of time in which to get it finished."
		+ "</p>"
		
		+ "<p>"
			+ "This version (0.2.10.1) is a hotfix, addressing some issues that cropped up in 0.2.10."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.9.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added footjob/hoofjob sex actions, with variations for single or double footjob (single variation is used when standing).</ul>"
			+"<ul>Added 'creampie/cum over' variations for hotdogging (cum over back and ass), paizuri/naizuri (cum over chest and face), thigh sex (cum over legs), handjobs (cum over hands), and footjobs (cum over feet).</ul>"
			+"<ul>Improved alleyway attacker dialogue and pregnancy reactions for when you have a companion. Also changed companion detection so that your elemental (if you have one) can take part in the sex scenes.</ul>"
			+"<ul>In progress: Added a submissive route to the nightclub, but the descriptions are still placeholders.</ul>"
			+"<ul>In progress: Added most of the framework for getting non-slave NPCs to move in with you. All the dialogue is placeholders for the moment, and it only works for Dominion alleyway NPCs.</ul>"

			+"<li>Other:</li>"
			+"<ul>Improved 'bald' description in selfie.</ul>"
			+"<ul>Changed hair TF category from 'lesser' to 'minor', so 'minor' furry NPCs will now spawn with race-specific hair.</ul>"
			+"<ul>Added detection for shoes blocking foot actions in sex. (I'll add shoe-related actions later on.)</ul>"
			+"<ul>Added 'blunt' TF modifier to enchanting penis and clit.</ul>"
			+"<ul>Harpy wings no longer block the finger slot.</ul>"
			+"<ul>NPCs can no longer gain affection towards you from sex, but can still lose it (if they are resisting at the end without the sub non con fetish, or if they didn't orgasm). This was changed due to the intention of affection being used as a content progression mechanic.</ul>"

			+"<li>Sex AI:</li>"
			+"<ul>NPCs no longer prefer pulling out in all sex scenes, and now instead have a 66% chance to want to creampie. (100% chance if they have an associated fetish.)</ul>"
			+"<ul>NPCs will no longer deny requests if they either like or love the request's associated fetish.</ul>"
			+"<ul>NPCs with the anal fetish will now prefer anal penetration instead of hotdogging.</ul>"
			+"<ul>Fixed issue where NPCs would be unwilling to initiate any penetrative anal actions. (Mainly an issue in glory hole scenes.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed NPCs wanting to use tail+breast actions, even though there are none currently in the game.</ul>"
			+"<ul>Fixed parsing error in Zaranix post-victory dialogue, and in a few of the harpy matriarch scenes.</ul>"
			+"<ul>Fixed Kruger, Vicky, and Cultists not progressing through positions in sex properly.</ul>"
			+"<ul>Fixed sex scenes marked as 'public' (such as Kruger's), repeating the end sex dialogue about essence gains.</ul>"
			+"<ul>Fixed 'Tease nipple' action being available when nipple penetration content was turned off.</ul>"
			+"<ul>Naked character portraits in tooltips are now correctly concealed if you've never had sex with that character before.</ul>"
			+"<ul>Fixed incorrect parsing of virginity loss descriptions.</ul>"
			+"<ul>Fixed 'Slap ass' action in stocks sex being available in incorrect positions, and returning errors when used.</ul>"
			+"<ul>Fixed incorrect breast grope tooltip description.</ul>"
			+"<ul>Numerous typo fixes and corrections to the grammar in sex actions.</ul>"
			+"<ul>Fixed angel faces having human skin, and angels having human tongues. (Angels aren't officially in-game yet, this is just through the debug menu.)</ul>"
			+"<ul>Amber now correctly prefers the doggy style position.</ul>"
			+"<ul>Slightly altered detection for cat-morph subspecies to allow for lesser/minor tigers, lions, and leopards (by detecting either a panther or human face).</ul>"
			+"<ul>Sex with your companion in the night club's seating area or toilets is no longer public, and sex in the VIP or seating area using the chair sex options.</ul>"
			+"<ul>Fixed incorrect action availability in milking stall sex scenes.</ul>"
			+"<ul>Fixed issue with parser targets not applying correctly to characters present.</ul>"
			+"<ul>Fixed 'Nympho Queen' option in the 'Angry Harpies' quest not being available even if requirements were met.</ul>"
			+"<ul>Fixed litters using a simple day counter for tracking time of conception and birth, which was leading to incorrect descriptions of conception and birth dates.</ul>"
			+"<ul>Fixed issue where upon starting a new game, some characters wouldn't display their artwork until after saving & loading.</ul>"
			+"<ul>Fixed issue with NPC pregnancy reactions not being saved/applied correctly. The change I made in the code means that some NPCs that have already reacted to being pregnant might react to their pregnancies once more.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.10</h6>"
			+"<li>Engine:</li>"
			+"<ul>Added support for the parsing engine to use the same scripting feature as it's conditional statements in regular parsing statements. (Basically, just put a '#' at the start of the parsing command, then use the same syntax as conditional IFs.)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added all descriptions for the club's 'sub' scenes (where an NPC is leading you around the club), with variations for nice/normal/sleazy partners.</ul>"
			+"<ul>Filled in all the placeholders for the alleyway attacker's non-violent scenes, with variations of the 'talk' action based on their affection towards you.</ul>"
			+"<ul>Added more talk options for non-slave occupants, and some further progression for them in the form of them getting a job (10% chance each day) and being able to move out into their own apartment (after getting a job).</ul>"
			+"<ul>Enabled 'Inspect' and 'Send to Kate' options for non-slave occupants.</ul>"
			+"<ul>You can now manage your slaves' and friendly occupants' perks. The action is alongside the other management ones (Inspect/Job/etc.).</ul>"
			+"<ul>Updated Slaver Alley's map to add some stores that you can buy slaves from, as well as some new descriptions for the other tiles.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Both sub and dom NPCs that are re-encountered in the club (through the 'contacts' action) now have their affection towards you reset to 5, so that they'll go through the full sequence of buying drinks/talking/flirting each time you encounter them.</ul>"
			+"<ul>Added ability for NPCs to remember your body areas, so they won't be surprised every time they see your penis/vagina, and improved reveal dialogue. (Their reactions will reset if you TF your genitalia/breasts/nipples.)</ul>"
			+"<ul>Repositioned talk/manage/sex tabs in slavery & occupant dialogue to be in the same as in the view character dialogue (so all three now have the format: talk/sex/manage).</ul>"
			+"<ul>Added day of the week + year to date tooltip (when hovering over the calendar icon above the minimap).</ul>"
			+"<ul>Clicking on your name while in combat will now set your target to yourself, instead of opening the perk screen.</ul>"
			+"<ul>Added version of Java used to compile LT at the top of the README.</ul>"
			+"<ul>Added footjob climaxes given/received to tattoo counter types.</ul>"
			+"<ul>Improved creampie/pull out requests to be available for paizuri and footjobs.</ul>"
			+"<ul>Added feet as an orgasm target.</ul>"
			+"<ul>Alexa now stocks 3 slaves instead of 5.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed typos and parsing errors.</ul>"
			+"<ul>Slaves will no longer go to their job while having sex with you.</ul>"
			+"<ul>Fixed alleyway attackers incorrectly saying you were the one to get them pregnant, even if it wasn't you.</ul>"
			+"<ul>Added catch for null characters being returned by some methods, which will have fixed some instances of the game becoming unresponsive.</ul>"
			+"<ul>Fixed clothing mods' 'imageEquippedName' not working correctly.</ul>"
			+"<ul>Fixed issue in group sex orgasm scenes where incorrect characters would be described as performing or being targeted by actions.</ul>"
			+"<ul>Fixed knotting orgasm scenes describing hands/breasts/thighs/ass getting locked to the knot.</ul>"
			+"<ul>Fixed squirrel face TF being labelled as simply 'furry' instead of 'squirrel'.</ul>"
			+"<ul>Fixed issue that was causing some newly spawned characters to have a conception date three years earlier than intended.</ul>"
			+"<ul>Fixed bug where accessing your perk screen while in slavery management could cause a background error to be thrown.</ul>"
			+"<ul>Fixed issue where sex scenes with you watching your companion + NPC could go on forever if your companion was resisting.</ul>"
			+"<ul>Cumming on feet/hands will now correctly dirty any clothing in the foot/hand slot.</ul>"
			+"<ul>Fixed autosave code so that the game now correctly autosaves when entering a new area, except if the entrance dialogue is unique. (e.g. The first entry to the Harpy Nests, where the Enforcer questions you, won't autosave, but subsequent visits will.)</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.10.1</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Enabled friendly occupants to be added as party members, both from their room, and new apartment (if you get them to move out).</ul>"
			+"<ul>All non-unique NPCs that have the 'transformation test subject' fetish will now accept all TF potions that you ask for them to drink.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed bug where having a hash character in the save file path would cause saves to not work. (HomeofTrees PR#884)</ul>"
			+"<ul>Added keybind presets, and the ability to save custom keybind presets. Added response cursor navigation keys to keybinds. (DJ4ddi PR#882)</ul>"
			+"<ul>Added: Arcane Bow. Sold by Vicky, and comes in Physical, Fire, Ice, and Poison types. (Rfpnj PR#669)</ul>"
			+"<ul>Added fox-morph book to Lilaya's Library. (Rfpnj PR#871)</ul>"
			+"<ul>Added a couple more entries to DisplacementType Enum: OPEN and UNBUCKLE. (Rfpnj PR#877)</ul>"
			+"<ul>Added support for silly mode (disabled by default), which for now just changes the race name 'wolf-morph' to 'awoo-morph'. (Nnxx PR#887)</ul>"
			+"<ul>Added: Leather belt, sold by Nyan. Masculine, hip slot. (kobolds PR#879)</ul>"
			
			+"<li>DJ4ddi's Artwork image improvements (PR#881):</li>"
			+"<ul>Improved image scaling (removes jagged edges from a lot of in-game images).</ul>"
			+"<ul>Naked player images are no longer hidden.</ul>"
			+"<ul>Image selection controls now work for the player.</ul>"
			+"<ul>Newly created NPCs (e.g. after starting a new game) now have their artwork loaded immediately.</ul>"
			+"<ul>Unrecognized file name prefixes are now loaded as clothed.</ul>"
			+"<ul>The tooltip image is now hidden if it is locked in the info screen.</ul>"
			+"<ul>JPG images actually work.</ul>"
			+"<ul><b>Added:</b> A small button with an encircled '+' icon is now placed next to the artist information button (or in the same spot, if there is no artwork). Clicking it opens a dialogue that allows selecting one or more image files which are copied into the character's folder and imported into the game. A log entry indicates success or failure of the import.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>You can now use non-transformative items on Elementals.</ul>"
			+"<ul>Standardised the decision making of all non-unique NPCs in regards to accepting an item from you, and improved some of the descriptions of them using items you give to them, based on their fetishes.</ul>"
			+"<ul>Slaves and friendly occupants are now always no longer affected by level scaling, instead of only being reset to their real level once added to your party.</ul>"
			+"<ul>Changed the 'Important status effects' interrupt screen's continue button placement from action 6 to action 0. (So it's easier to continue by just pressing space instead of 6.)</ul>"
			+"<ul>Added NPC icons to the phone's map, and information in the tooltip to show who is present in that tile. (With a greyed-out name if that tile is the character's home tile, but they are currently elsewhere.)</ul>"
			+"<ul>Masochistic slaves now gain affection from being spanked, and both masochistic and exhibitionist slaves gain affection from being inspected.</ul>"
			+"<ul>Improved virginity loss tracking code, and added virginity-taken counts to tattoo count types. Also improved the tattoo count UI a little. (Virginity counts will all start at 0 from this version, as previous versions didn't track who took a character's virginity.)</ul>"
			+"<ul>Added hair modification as a secondary element to penis and vagina transformation potions, which increases/decreases pubic hair. (Not displayed if pubic hair is turned off in content options.)</ul>"
			+"<ul>Added foot actions to face-sitting sex position.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed major issue of friendly occupants accidentally being deleted once they'd moved out to their new apartment.</ul>"
			+"<ul>Fixed 'Butler' job not being available at character creation.</ul>"
			+"<ul>Fixed issue where some sex scenes would not correctly re-equip all clothing after sex.</ul>"
			+"<ul>Added a new ClothingSlot 'ANUS', and applied it to all clothing and butt plugs, so they can't be inserted/removed through clothing anymore.</ul>"
			+"<ul>Fixed issue where the game would softlock when anyone orgasmed while performing paizuri or thigh sex.</ul>"
			+"<ul>You can no longer use the 'Talk' option after combat with arcane storm attackers.</ul>"
			+"<ul>Orgasming while hotdogging or performing paizuri or thigh-sex will now correctly cover your partner's ass/breasts+face/legs with cum.</ul>"
			+"<ul>Facials will now correctly cover the mouth, neck, and eye clothing slots in cum, or, if covered, the clothing in those slots.</ul>"
			+"<ul>Doggy style 'Cum over back' orgasm should now correctly cover the targets back & clothing in cum.</ul>"
			+"<ul>Androgynous characters now have access to glasses in character creation.</ul>"
			+"<ul>Dirty inventory slots will now correctly be saved/loaded.</ul>"
			+"<ul>Fixed UI bug in enchantment screen, where, depending on the order in which you removed enchantments, the remove button would stop showing the removal price.</ul>"
			+"<ul>Footjobs are no longer disabled in kneeling oral position when there's an ongoing blowjob.</ul>"
			+"<ul>Fixed some issues in body part descriptors that were throwing errors in sex.</ul>"
			+"<ul>Fixed conditional parsing errors in new alleyway attacker dialogue.</ul>"
			+"<ul>Fixed bug where in the nightclub, if you bought a drink that gets you drunk, your speech would be slurred before being described as drinking it.</ul>"
			+"<ul>Fixed issue where enslaving a friendly occupant would break a lot of menus and slavery management options.</ul>"
			+"<ul>Fixed issue where friendly occupants added to your house in v0.2.9.5 would not have their name known.</ul>"
			+"<ul>Fixed exploit where you could get any NPC to use any transformation item if you first placed it in their inventory.</ul>"
			+"<ul>Fixed some grammatical mistakes in sex actions.</ul>"
			+"<ul>Fixed 'Slap ass' action in 'stocks' and 'milking stall' sex scenes returning a huge amount of errors.</ul>"
			+"<ul>Added support for clothing/tattoo mods to use a correctly-spelled version of the 'coreAtributes' element.</ul>"
			+"<ul>Fixed alleyway attackers being referred to by their name before they've told you it.</ul>"
			+"<ul>Fixed issue where setting all or all-but-one gender preferences to off would cause background errors to be thrown, which most notably disabled the 'New Game' action.</ul>"
			+"<ul>You can no longer install guest rooms before finishing the quest 'Bed & Board'. (The one where you get permission from Lilaya to install guest rooms.)</ul>"
			+"<ul>Fixed nightclub dialogue where, as the sub, you refuse to have sex in the toilet.</ul>"
			+"<ul>Fixed incorrect foot descriptions being used in the request to cum on feet.</ul>"
			+"<ul>Fixed peaceful canal introduction dialogue not being found.</ul>"
			+"<ul>You can no longer send slaves which you do not own to Kate, or assign their perk points.</ul>"
			+"<ul>Fixed bug that could sometimes cause NPCs to use your ass after accepting your request to use theirs.</ul>"
			+"<ul>Fixed not being able to interact with your friend's apartment if they were out at work.</ul>"
			+"<ul>Fixed NPCs in group sex scenes always losing affection towards only you, instead of each of the people they're having sex with.</ul>"
			+"<ul>Wrapped some code in try-catch blocks to try and handle the issue where Nyan and Finch sometimes don't restock correctly.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed issue where you'd lose all knowledge of NPCs' areas upon loading your game.</ul>"
			+"<ul>Fixed alleyway attackers incorrectly saying that you were the one to get them pregnant.</ul>"
			+"<ul>Fixed NPCs referring to the wrong person while dirty talking in group sex scenes.</ul>"
			+"<ul>Fixed issue where cleaning clothes in your inventory (through your room's 'wash' action) would cause a background error, making the cleaned clothes impossible to select.</ul>"
			+"<ul>Loading a game will no longer trigger autosave once loading is complete.</ul>"
			+"<ul>Dominant/submissive clubbers can no longer spawn in hating or disliking dominant/submissive fetishes, respectively.</ul>"
			+"<ul>Fixed bug where friendly occupants could move into a guest room already occupied by someone else.</ul>"
			+"<ul>Fixed bug where if one of the friendly occupants had moved out to an apartment, the 'Manage People' action in rooms would no longer work.</ul>"
			+"<ul>Fixed being able to move friendly occupants back into your guest rooms from their apartments</ul>"
			+"<ul>Fixed penis/testicle transformations being applied to your dildo if you were wearing one.</ul>"
			+"<ul>Removing a strapon that is engaged in penetration during sex will now correctly stop that penetration.</ul>"
			+"<ul>NPCs arousal is no longer affected by self actions that you perform.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.10.2</h6>"
			+"<li>Bugs:</li>"
			+"<ul>Fixed errors in 'Seductive look' action.</ul>"
			+"<ul>Fixed issue where if you had a companion that was a slave that you imported through the Auction Block, the game would break.</ul>"
			+"<ul>Fixed not being able to penetrate partners vaginally when you're in the missionary (on your back) position.</ul>"
			+"<ul>Fixed tattoo defloration counters being reset each time you loaded a game.</ul>"
			+"<ul>Typo fixes.</ul>"
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

		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alvinsimon", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 10));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 8));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 3));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 10));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 8, 4));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 10));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 8));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 3, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Assiyalos", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("suka", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 8, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 2));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Littlemankitten", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 6));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 4, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 10));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Sir beans", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 8));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 7));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Weegschaal", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Drahin", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 7));
		
		
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
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

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
