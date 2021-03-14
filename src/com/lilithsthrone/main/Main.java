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
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.colours.PresetColour;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * @since 0.1.0
 * @version 0.3.20
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sex;
	public static Combat combat;

	public static TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Innoxia";
	public static final String GAME_NAME = "Lilith's Throne";
	public static final String VERSION_NUMBER = "0.3.20";
	public static final String VERSION_DESCRIPTION = "Alpha";
	
	/**
	 * To turn it on, just add -Ddebug=true to java's VM options. (You should be able to do this in Eclipse through Run::Run Configurations...::Arguments tab::VM Arguments).
	 * Help page: https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Flaunchers%2Farguments.htm
	 *  Or, from the command line java -Ddebug=true -jar LilithsThrone.jar
	 */
	public final static boolean DEBUG = Boolean.valueOf(System.getProperty("debug", "false"));

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");
	
	private static Properties properties;
	
	public static String patchNotes;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

			+ "<p>This game is a <b>fictional</b> text-based erotic RPG. All content contained within this game forms part of a fictional universe that is not related to real-life places, people or events.<br/><br/>"

			+ " All of the characters that appear in this story are fictional entities who have given their consent to appear and act in this story."
			+ " If you find yourself concerned for the characters in the story then please be reassured that they are all consenting adults who are speaking lines from a script."
			+ " None of the characters portrayed within this game were or are being harmed in any way during the construction or execution of this game."
			+ " Every character in the game is at least 18 years of age (or is magically the legal age needed to appear in erotic literature in whatever country you are playing this)."
			+  " No character in this game is blood-related to any other; once again, they are simply speaking lines from a script.<br/><br/>"

			+ "By agreeing to this disclaimer and playing this game you agree to be exposed to graphic sexual and adult content that is presented as part of the game's fictional universe."
			+ " Such content consists of, but is not limited to; graphic depictions of sex, inter-species sex (with fantasy creatures), sexual transformation,"
			+ " rape fantasy/implied lack of consent, mild physical violence, sexual violence, and drug use.<br/>"
			+ "Extreme fetish content such as gore/extreme violence, scat, and under/questionable age, is <i>not</i> a part of this game.<br/><br/>"

			+ "By agreeing to this disclaimer and playing this game you also agree that you are <b>at least 18 years of age</b>,"
			+ " or at least the legal age for you to purchase and view pornographic material in your country if that age is over 18.<br/><br/>"

			+ "As a final note, the creators of this game wish to stress that the content presented within is entirely fictional and does not reflect any of their personal views or opinions."
			+ " This game has been made in the spirit of creating a piece of artistic interactive literature, and it is imperative that you maintain a clear distinction between reality and the fictional events depicted in this game.</p>";
	
	public static List<CreditsSlot> credits = new ArrayList<>();

	static {
		StringBuilder patchNotesSB = new StringBuilder();

		patchNotesSB.append(
				"<p>"
					+ "Hello again,"
				+ "</p>"
		
				+ "<p>"
					+ "I'm sorry that this isn't version 0.4. I'll get that out to you as another public release as soon as I possibly can."
				+ "</p>"
				
				+ "<br/>"
					
				+ "<p>"
					+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
					+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
				+ "</p>"
		
				+ "<br/>");

		patchNotesSB.append(
				"<list>"
					+ "<h6>v0.3.20</h6>"
						+"<li>Contributions:</li>"
						+"<ul>Fixed issue where some subspecies would spawn in canal tiles, even when their spawn rate is supposed to be zero. (by AceXP)</ul>"
						+"<ul>Fixed bug where Natalya would be a regular succubus in new games, and not a demonic centaur - the fix will apply when loading into this version for if she's affected by this in your game. (by AceXP)</ul>"
						+"<ul>Prevented Enforcers from inadvertently spawning as slimes. (by AceXP)</ul>"
						+"<ul>Updated dragon lore entries. (by DSG)</ul>"
						+"<ul>Fixed parsing error in spinneret webbing special attack. (by Stadler76)</ul>"
	
						+"<li>C4M's clothing contributions:</li>"
						+"<ul>(Note that these can only be spawned via the debug menu for the time being.)</ul>"
						+"<ul>Added 'Trickster's Mask' (eyes slot, unisex).</ul>"
						+"<ul>Added 'Trickster's Cloak' (over-torso & neck slots, unisex).</ul>"
						+"<ul>Added 'Trickster's Tunic' (torso slot, feminine).</ul>"
						+"<ul>Added 'Muramasa' weapon (melee, lust damage, one-handed).</ul>"
						+"<ul>Added 'trickster' clothing set bonus for the above items.</ul>"
	
						+"<li>TonyJC (creator) & NeverLucky (editor) clothing contributions:</li>"
						+"<ul>Added 'cropped tank-top' (feminine, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'cropped sweater' (feminine, over-torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'cropped T-shirt' (androgynous, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'daisy dukes' (androgynous, leg slot, sold by Nyan).</ul>"
						+"<ul>Added 'denim overalls' (androgynous, leg slot, sold by Nyan).</ul>"
						+"<ul>Added 'one-strap crop top' (feminine, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'shoulderless sweater' (feminine, over-torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'sleeveless shirt' (androgynous, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'spaghetti crop tank-top' (feminine, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'tank-top' (androgynous, torso slot, sold by Nyan).</ul>"
						+"<ul>Added 'tied-up croptop' (feminine, torso slot, sold by Nyan).</ul>"
	
						+"<li>Engine/Modding:</li>"
						+"<ul>Added 'spreadsHerLegs' parsing command for handling the possibility of characters with no legs when describing 'spreads her legs'.</ul>"
						+"<ul>Added ability to set 'additionalFootStructure' elements in modded 'leg' xml files, so you can set which foot structure is used by default for that leg type based on different leg configurations.</ul>"
						+"<ul>Added 'TAIL_ATTACK' BodyPartTag for setting whether a tail is suitable for being used for the 'tail swipe' attack.</ul>"
						+"<ul>Added 'HARPY_NESTS' as a world region, which prevents all Dominion races from showing up in the 'characters present' tooltip in the Harpy Nests.</ul>"
						+"<ul>Defining a horn length of '0' in RacialBody.xml files will now force that femininity to spawn with no horns (updated hyena's RacialBody.xml file with comments on how this works).</ul>"
						+"<ul>Added an optional 'nameTransformation' field to coveringType files so that you can set a name for the covering to be used in transformation menus (see 'race/innoxia/sheep/coveringTypes/wool_face.xml' for an example).</ul>"
	
						+"<li>Races:</li>"
						+"<ul>Added orange skin colour.</ul>"
						+"<ul>Changed covering type of harpy legs from being feathers to bird skin.</ul>"
						+"<ul>Added 'swan-harpy' as a subspecies of harpy, requiring white feathers and either grey or ebony leg skin to be identified as one. Helena is now a swan-harpy.</ul>"
						+"<ul>Added 'sheep-morph' race.</ul>"
						+"<ul>Added 'Ewe's Brew' (dandelion tea) consumable drink for the sheep-morph's attribute item.</ul>"
						+"<ul>Added 'Wooly Goodness' (candy floss) consumable food for the sheep-morph transformative item.</ul>"
						+"<ul>Added 'goat-morph' race.</ul>"
						+"<ul>Added 'Pan's Flute' (champagne) consumable drink for the goat-morph's attribute item.</ul>"
						+"<ul>Added 'Billy's Best' (goat cheese) consumable food for the goat-morph transformative item.</ul>"
						+"<ul>Added 'pig-morph' race.</ul>"
						+"<ul>Added 'Porcine Pop' (cola) consumable drink for the pig-morph's attribute item.</ul>"
						+"<ul>Added 'Oinkers Fries' (curly fries) consumable food for the pig-morph transformative item.</ul>"
	
						+"<li>Other:</li>"
						+"<ul>You can now additionally apply the vibration enchantment to clothing which fits into the nipple, chest, penis, vagina, nipple-piercing, vagina-piercing, or cock-piercing slots.</ul>"
						+"<ul>Added a few more items of clothing to the choices in character creation.</ul>"
						+"<ul>You now require a bipedal leg configuration to be transformed into a demon.</ul>"
						+"<ul>Added ability to set spider eyes as heterochromatic.</ul>"
						+"<ul>Updated Angel's clothing.</ul>"
						+"<ul>NPCs with a cloaca will no longer spawn with an equipped buttplug or chastity cage.</ul>"
						+"<ul>Greater spider-morphs now have a 50% chance to spawn with 6 arms.</ul>"
						+"<ul>Prevented rabbit and harpy tails from ever being suitable for penetration.</ul>"
						+"<ul>The 'tail swipe' attack is now only available to alligators, sharks, and dragons who have a tail length greater than 1 metre.</ul>"
						+"<ul>Safety goggles no longer spawn on the floor in Lilaya's lab (although they won't be removed if they're already spawned in your game). Instead, you can ask her for a spare lab coat (which gives you safety goggles as well).</ul>"
						+"<ul>Cow-eyes now correctly have horizontal pupils by default.</ul>"
						+"<ul>Made the 'furry hair' content option disabled by default (this won't affect your current settings).</ul>"
						+"<ul>Wolf-morphs and border-collie-morphs now have +25% sheep-morph damage as part of their subspecies status effect.</ul>"
						+"<ul>Added body size increase as an effect of the brown rat's burger.</ul>"
	
						+"<li>Bugs:</li>"
						+"<ul>Parsing fixes.</ul>"
						+"<ul>Fixed issue in sex scenes where background errors would be thrown and potentially softlock the game when an anal fetishist orgasmed while receiving anal and having an empty vagina.</ul>"
						+"<ul>Fixed issue where Helena's old slaves could get stuck on her shop tile and never despawn.</ul>"
						+"<ul>Fixed bug where trying to open your inventory during character creation would throw background errors.</ul>"
						+"<ul>Fixed issue where Dominion muggers were not using outfit files and so were spawning with completely random clothing.</ul>"
						+"<ul>Added catch to handle error if actions for partners in sex were not being generated properly.</ul>"
						+"<ul>Removed all references to 'legs' for characters who don't have legs (i.e. if they have a snake's tail instead of legs). If you find any references to your 'legs' while playing as a lamia or other similar race, please let me know.</ul>"
						+"<ul>Fixed references to your character laying multiple eggs in Lilaya's birthing scenes if you only had one egg to lay.</ul>"
						+"<ul>Fixed issue with arachnid leg configurations describing their feet as plantigrade.</ul>"
						+"<ul>Characters being bred in the pregnancy roulette game can no longer stroke their own cock.</ul>"
						+"<ul>Fixed issue with the 'unlikely whammer' burger spawning when silly mode was turned off.</ul>"
						+"<ul>Fixed incorrect tooltip for the friendly occupant petting action.</ul>"
						+"<ul>Fixed issue where the Enforcer bowler hat commissioner badge was using the same icon as the commissioner oak leaves.</ul>"
						+"<ul>Fixed issue with taur sapwn rate not working as intended.</ul>"
						+"<ul>Fixed bug where all taurs would spawn with horns, even if their race shouldn't have horns by default.</ul>"
						+"<ul>The 'cocoon'/'constrict'/'tentacle restraint' sex actions now correctly become greyed-out when unavailable.</ul>"
						+"<ul>Spinnerets are no longer incorrectly blocked by clothing which conceals a character's ass.</ul>"
						+"<ul>Fixed issue where some items of clothing would spawn with incorrect colours (such as the feminine and masculine watches).</ul>"
						+"<ul>Fixed bug where spider-morphs were sometimes spawning as tarantula-morphs.</ul>"
						+"<ul>Fixed bug where Rose would have her home tile set to Lilaya's lab instead of her room.</ul>"
						+"<ul>Fixed Youko race status effect displaying '0 corruption'.</ul>"
						+"<ul>Fixed bug where you could encounter half-demons of non-Dominion races on canal tiles.</ul>"
						+"<ul>Fixed bug where unarmed damage attribute was being applied as a damage modifier three times, making unarmed attacks far more powerful than intended.</ul>"
						+"<ul>Fixed issue in combat where if your AP fell to 0, you could never win or lose combat due to being permanently stunned.</ul>"
						+"<ul>Fixed some issues with importing characters which were exported in earlier versions.</ul>"
				+"</list>");
		
		patchNotesSB.append("<br/>");
		
		patchNotesSB.append(
				"<list>"
					+ "<h6>v0.3.19</h6>"
						+"<li>Contributions:</li>"
						+"<ul>Fixed issue where spider-morph book's front cover icon wasn't visible. (by NeverLucky)</ul>"
						+"<ul>Added furred spider tail as a TF option for spider TF potions. (PR#1510 by Stadler76)</ul>"
						
						+"<li>Engine/Modding:</li>"
						+"<ul>Added parser hook for BodyCoveringCategory using the prefix 'BODY_COVERING_CATEGORY_'.</ul>"
						+"<ul>Added BodyPartTag 'TAIL_TAPERING_BULBOUS' for defining the shape of spider abdomen-like tails.</ul>"
						
						+"<li>Other:</li>"
						+"<ul>Added 'hug' and 'pettings' actions to interactions with characters staying in the mansion as your guest.</ul>"
						+"<ul>Changed name of spider TF item from 'Chocolate Coated Cocoa Beans' to 'Chocolate Coated Coffee Beans'.</ul>"
						+"<ul>Added ability to regrow or remove hymen via the self-TF menu.</ul>"
						+"<ul>Added dirty talk variations for tribbing.</ul>"
						
						+"<li>Bugs:</li>"
						+"<ul>Typos, grammar, and parsing fixes.</ul>"
						+"<ul>Fixed bug where trying to set a slave free would throw background errors and cause buttons to become unresponsive.</ul>"
						+"<ul>Fixed bug where Helena would sometimes end up trying to sell more slaves than she actually owned.</ul>"
						+"<ul>Fixed related issue where Helena's slaves would not be correctly initialised if you were standing on her shop tile at the moment new slaves were generated.</ul>"
						+"<ul>The 'Dispel elemental' action now works correctly for companions.</ul>"
						+"<ul>Fixed bug where you'd sometimes lose a proportion of your health and aura when loading a saved game.</ul>"
						+"<ul>Fixed bug with the new 'Aristocrat' background, where your corruption would double every time you loaded your game. Also fixed related issue where the derived resistances from aristocrat's corruption would be incorrectly saved and loaded.</ul>"
						+"<ul>Fixed rare cases where nested else/if statements would parse incorrectly.</ul>"
						+"<ul>Fixed issue with some non-bat tails being referred to as 'bat' tails.</ul>"
						+"<ul>When freeing Scarlett (and therefore triggering her body to be reset to her harpy form), she now correctly returns to having her default fetishes, and also no longer regains her anal virginity if she'd lost it while being your slave.</ul>"
						+"<ul>Fixed issue where spider-morphs with an arachnid leg configuration wouldn't have a spinneret.</ul>"
						+"<ul>Spider-morphs will no longer spawn with crotch-boobs by default.</ul>"
						+"<ul>Fixed bug where a spinneret's orifice covering wouldn't be coloured according to the character's skin colour.</ul>"
						+"<ul>Fixed error log entries related to item spawns being generated when spawning a spider-morph attacker.</ul>"
						+"<ul>Fixed the save/load menu having very dark row background colours when in light mode, which was making it impossible to read saved game names.</ul>"
						+"<ul>Fixed issue where all clothing stickers would be unlocked by turning debug mode on, instead of the actual sticker unlocks mode.</ul>"
				+"</list>");
		
		patchNotesSB.append("<br/>");
		
		patchNotesSB.append(
				"<list>"
					+ "<h6>v0.3.18</h6>"
						+"<li>Charisma's spider-morph contribution:</li>"
						+"<ul>Added spider-morph race.</ul>"
						+"<ul>Added spider-morph and tarantula-morph subspecies.</ul>"
						+"<ul>Added 'Jet Black Coffee' and 'Chocolate Coated Cocoa Beans' for the spider-related attribute and TF items.</ul>"
						
						+"<li>DSG's race's updates (PR#1500):</li>"
						+"<ul>Non-mythical race bonuses were adjusted so that the total more closely matches the magnitude of the game's other non-mythical races.</ul>"
						+"<ul>Total of race bonus values for mythical races (dragons, gryphons) were aligned to the same range as demons and alicorns (demons/dragons at 180, alicorns at 165, gryphons at 150).</ul>"
						+"<ul>Fixed a tertiary color issue with dragon overlays, tweaked shark finned ear description.</ul>"
						
						+"<li>Contributors:</li>"
						+"<ul>Added option for the bandana to be worn in the neck slot. (by DSG)</ul>"
						+"<ul>Fixed huge issue with performance, making turn times roughly 30-50% faster. (by AceXP)</ul>"
						+"<ul>Fixed incorrect inventory contents of attackers spawned via the debug menu. (by AceXP)</ul>"
						+"<ul>Fixed issue with clit size not being able to be changed in character creation. (by AceXP)</ul>"
						+"<ul>Fixed issue with modified fluid colours being saved. (by AceXP)</ul>"
						+"<ul>Fixed issue with Gryphons (and other low spawn rate subspecies) not spawning as Enforcers. (by Stadler76)</ul>"
						+"<ul>Fixed issue where 'Warning: getClosestStringMatch()' entries would be shown when there was just a difference in capitalisation. (by AceXP)</ul>"
						+"<ul>Slimes and bats in the bat caverns will now correctly spawn with weapons. (by AceXP)</ul>"
						+"<ul>Added expanded hyena-morph lore in the 'Laughing Hyenas' lore book. (by Manwhore)</ul>"
						+"<ul>Added unique icon for border-collie morphs and gave bald-eagle harpy icon a coloured beak. (by NeverLucky)</ul>"
						+"<ul>In subspecies XMLs, if secondaryColour or tertiaryColour elements are empty they now default to colour. (PR#1503 by Stadler76)</ul>"
						+"<ul>Added several new methods in Body.java and BodyPartTypeInterface.java and used these methods to tidy up xml race files. (PR#1498 by Stadler76)</ul>"
						+"<ul>Fixed issue where becoming a demon didn't work for several LegConfigurations since the setLegsToDemon() method for those LegConfigurations wasn't implemented. (PR#1496 by Stadler76)</ul>"
						+"<ul>Fixed the body tooltip information for antenna to reflect changes to the number of antenna per row. (PR#1494 by debouchere)</ul>"
						+"<ul>Fixed arms appearance for feral harpies and bats mentioning that they had fingers. (PR#1456 by Stadler76)</ul>"
						
						+"<li>Engine/Modding:</li>"
						+"<ul>Added 'NEGATIVE_ONE_LEGENDARY', 'NEGATIVE_TWO_MYTHICAL', and 'NEGATIVE_THREE_ZERO' as new SubspeciesSpawnRarity values (for subspecies which should be extremely rare).</ul>"
						+"<ul>Added support for use of spaced 'ELSE IF' in conditional statements (although you should probably still continue to use 'ELSEIF' for style conformity).</ul>"
						+"<ul>Added 'Sticker unlocks' button to the debug menu, which bypasses all clothing sticker requirements when turned on (for ease of testing clothing stickers).</ul>"
						+"<ul>Added functionality for characters to gain category-locked perk points.</ul>"
						+"<ul>Added 'TAIL_NEVER_SUTABLE_FOR_PENETRATION' BodyPartTag so that you can prevent prehensile tails from ever being used in sex.</ul>"
						+"<ul>Added BodyPartTags 'FACE_VENOMOUS_TEETH' and 'FACE_VENOMOUS_TEETH_LUST', which causes the 'BITE' special attack to apply 'poisoned' or 'lust-poisoned' status effects to the target.</ul>"
						
						+"<li>Gameplay:</li>"
						+"<ul>Added Nyan content up to the point where you can repeatedly go on weekend dates with her (and her mother).</ul>"
						+"<ul>Added 'aristocrat' as a background choice during character creation.</ul>"
						+"<ul>Reworked Pix's gym so that exercising no longer drains health, and instead applies a 'post-workout fatigue' status effect. Exercising in the gym now also grants progress towards unlocking physical perk points.</ul>"
						+"<ul>You can now free your slaves by purchasing a Freedom Certification from Finch and then choosing the 'Set Free' action in your slave's management dialogue. If your slave likes you and you have a free guest room, you can have your slave become a guest after freeing them.</ul>"
						+"<ul>You can now import characters as guests in the same way that you can import them as slaves. To do so, go to the waiting room in City Hall and use the 'Lodgers' action. Importing guests requires you to have a free guest room in Lilaya's mansion for them to move into.</ul>"
						+"<ul>Snake-morphs now have venomous fangs, applying 'poisoned' to enemies which they bite in combat.</ul>"
						
						+"<li>Body:</li>"
						+"<ul>The property of a vagina to birth live young or lay eggs can now be modified via the self-TF menu and/or by crafting TF potions.</ul>"
						+"<ul>The orifice plasticity value of 'accommodating' now results in the orifice returning to 98% of its original tightness after being stretched, instead of 100%. (So the bottom 4 plasticity values now return to 100%, while the top 4 progressively return to lesser percentages.)</ul>"
						+"<ul>Alligator tails are no longer marked as 'prehensile', and panther tails are marked as 'prehensile' (so that they can be used in sex if you have furry tail penetration turned on).</ul>"
						+"<ul>Tails which are marked as suitable for penetration, but which are not prehensile, can now be used in sex so long as they are greater than 50% of the owner's height.</ul>"
						
						+"<li>Sex:</li>"
						+"<ul>Characters in a 'performing oral' or 'missionary' slot in the 'lying down' sex position can now interact with the characters lying to either side of their target.</ul>"
						+"<ul>Characters who are reverse face-sitting can now interact with characters who are scissoring the person whose face they are on.</ul>"
						+"<ul>When using quick-sex, characters will no longer lose their vaginal virginity while tribbing with a clit that's not large enough to act as a pseudo penis.</ul>"
						+"<ul>Characters with a positive desire towards the self-denial fetish no longer lose affection if they end sex without any orgasms, so long as they have been denied at least once (ending sex with no orgasms or denials still incurs an affection hit).</ul>"
						
						+"<li>Other:</li>"
						+"<ul>Added indication of the covering's race to covering recolouring menus.</ul>"
						+"<ul>Brax now starts with 150 essences, so he can use his pistol a lot more.</ul>"
						+"<ul>Updated the minimap's night-time gradient effects.</ul>"
						+"<ul>When searching for a partner in the club, subspecies are now ordered by race. You can also now select the race stage of your partner.</ul>"
						+"<ul>The elemental dispel button is now displayed in both the interaction and management tabs.</ul>"
						+"<ul>Changed maximum affection name from 'worships' to 'adores'.</ul>"
						+"<ul>The drunk speech modifier now only takes affect starting at the 'drunk' level, instead of 'tipsy'.</ul>"
						+"<ul>Body hair on furry morphs is now simply referred to as 'fur' instead of 'fur-like hair'.</ul>"
						+"<ul>When choosing your name in character creation, and changing it at city hall, you can now set your masculine/andgrogynous/feminine name triplet, which causes your first name to change based on your femininity.</ul>"
						+"<ul>Lyssieth now gains a lot of affection towards you after transforming you into a demon (will be retroactively applied if you are already a demon when loading into this version).</ul>"
						+"<ul>Added 'orgasm prevention' enchantment for clothing that's either marked as a sex toy or which fits into the groin slot. While under this effect, characters can never gain more than 95 arousal, effectively preventing them from ever orgasming.</ul>"
						+"<ul>Updated the icon for the 'desperate for sex' status effect.</ul>"
						+"<ul>When purchasing or otherwise gaining ownership of a slave, you now gain keys to all of their equipped clothing which provides a key (such as their slave collar).</ul>"
						+"<ul>If anal or lactation content is disabled, the enchantment menu will no longer show you secondary modifiers related to the anal orifice or lactation values.</ul>"
						+"<ul>Kate will now let you use pregnancy testers on her during sex.</ul>"
						+"<ul>The 'American tourist' background now grants doubled effects from eating burgers.</ul>"
						
						+"<li>Bugs:</li>"
						+"<ul>Parsing and description fixes.</ul>"
						+"<ul>Fixed issue where you could encounter egg-incubated offspring before they'd been laid and hatched.</ul>"
						+"<ul>To fix an issue with dragons spawning in unintended places. Only subspecies which naturally spawn in Dominion or Submission will now be used as custom-order slaves from Helena and as generic sex partners (which are used in glory holes and a few other places).</ul>"
						+"<ul>Fixed issue with breast stats not showing in the phone's stats menu if your cup size was less than AA-cups.</ul>"
						+"<ul>Kate's covering recolouring options now correctly include lips, tongue, nipples, and anus. Also fixed incorrect covering options for when sending a slave to Kate.</ul>"
						+"<ul>Custom-ordered slaves from Helena now have correct wing options available to them, and the colour of nipples/anus no longer resets when changing their human skin colouring.</ul>"
						+"<ul>Fixed bug where horn transformations were missing from demon self-TF menu options.</ul>"
						+"<ul>Fixed issue with the self-TF menu not working for recolouring slime fluids.</ul>"
						+"<ul>Brax will now correctly have his weapons equipped when first encountering him in a new game.</ul>"
						+"<ul>Fixed issue with Enforcer pepperball pistol's and rifle's 'Seven Rounds Rapid' and 'Mag Dump' moves throwing a background error.</ul>"
						+"<ul>Fixed issue with NPCs sometimes having their home location set to seemingly-random tiles. This was mainly affecting Enforcer patrols (who had their home set to Candi's desk) and Wes and Elle (who had their home set to the HQ's waiting area).</ul>"
						+"<ul>Fixed bug where error log could get spammed with null pointer exception warnings at the start of sex.</ul>"
						+"<ul>Fixed issue where you needed both the 'Minx' and 'Object of desire' perks in order to seduce Wolfgang/Karl, instead of just one of the two.</ul>"
						+"<ul>Fixed issue with feral demonic parts being referred to as 'feral Demonic-human'.</ul>"
						+"<ul>Pregnant characters will no longer have their stomaches bulged from large penetrations.</ul>"
						+"<ul>Fixed issue where human NPCs were spawning at a far higher frequency than what you set their spawn preference to be.</ul>"
						+"<ul>Characters who are hidden during a sex scene (such as when you encounter and watch two of your slaves having sex) can no longer have their levels drained upon orgasm by any of the participants.</ul>"
						+"<ul>Made fennec fox icon slightly smaller so that it fits in the icon background a little better.</ul>"
						+"<ul>Fixed some issues with incorrect slime subspecies identification.</ul>"
						+"<ul>Bunny and Loppy no longer act as though they didn't previously know what the other's genitals looked like the first time they're revealed in sex.</ul>"
						+"<ul>Fixed incorrect descriptions for the chastity status effects (and increased lust resistance drain from -25 to -50 for the final stage).</ul>"
						+"<ul>Fixed bug where very old save games would break when trying to load & save them in a newer version.</ul>"
						+"<ul>Fixed bug where clothing would sometimes spawn with its sticker prefix applied twice and its color prefix missing.</ul>"
						+"<ul>Fixed issue where changing Kay's makeup would change the player's makeup instead.</ul>"
						+"<ul>Fixed issue where covering recolouring buttons would stop working after inspecting a character and then returning to a covering recolouring screen (such as when using the arcane makeup kit).</ul>"
						+"<ul>Weapon descriptions now correctly list their special tags when you click on them in the inventory.</ul>"
						+"<ul>Fixed some missing dialogue issues in the scenes with Wolfgang and Karl in 'The Rusty Collar'.</ul>"
						+"<ul>Unemployed guests can no longer gain the 'stable mistress' job.</ul>"
						+"<ul>Fixed bug where elementals would not have any of their status effects updated nor regenerate health/aura over time.</ul>"
						+"<ul>Fixed issue with both lamia and melusine being tagged as the main race of snake-morphs, instead of just lamia.</ul>"
				+"</list>");
		
		patchNotesSB.append("<br/>");
		
		patchNotesSB.append("<list>"
			+ "<h6>v0.3.15</h6>"
			+"<li>NoStepOnSnek's races:</li>"
			+"<ul>Added snake race, with subspecies of 'snake-morph' (bipedal snakes), 'lamia' (long-tailed snakes), and 'melusine' (long-tailed snakes with wings).<ul>"
			+"<ul>Added octopus race, with subspecies of 'octopus-morph' (cephalopod body).</ul>"
			+"<ul>Added capybara race, with subspecies of capybara-morph (bipedal or quadrupedal body).</ul>"
			+"<ul>Added 6 food/drink items: Boiled Eggs/Snake Oil for snakes, Shrimp Cocktail/Ink Vodka for octopuses, Chocolate Brownie/Chamomile Tea for capybaras.</ul>"
			+"<ul>Added special attack 'Ink Cloud', unlocked by having an octopus face.</ul>"
			
			+"<li>Contributions:</li>"
			+"<ul>Added support for multiple vagina or penis types per race in enchanted TF potion effects. (PR#1465 by Stadler76)</ul>"
			+"<ul>Fixed issue where the combat moves of the player's elemental were reset. (by AceXP)</ul>"
			+"<ul>Fixed softlock at the end of combat when fighting imps in Submission. (by AceXP)</ul>"
			+"<ul>Improved performance by caching subspecies variables and only doing a recalculation when needed. (PR#1471 by AceXP)</ul>"
			+"<ul>Fixed typo in 'Ear pull' blowjob action. (PR#1472 by darkofoc)</ul>"
			+"<ul>Fixed issue where the appearance for feral legs was hardcoded and didn't take various leg configurations into account. (PR#1467 by Stadler76)</ul>"
			+"<ul>Tidied up a lot of race code, and fixed issue where changing a character's tongueType (as part of changing face type) wouldn't resets its modifiers. Also added 'WING_SIZE_' as a parser prefix hook for WingSize enum values. (PR#1480 by Stadler76)</ul>"
			+"<ul>Updated the tentacle-constrict icon. (by DSG)</ul>"
			+"<ul>Made the tooltip fade-in animation toggleable via the existing 'Fade-In' game option. This fixes the bug where tooltips were appearing blank when using some versions of java to run the game. (PR#1449 by CognitiveMist)</ul>"
			+"<ul>Improved damage calculations for special attacks. Added ability to set 'damageVariance' in modded combat moves. Added CombatMoveType POWER for use when defining special attacks instead of having to use ATTACK or SPELL. (PR#1473 by Stadler76)</ul>"
			+"<ul>Fixed references to the player using their wings in several scenes when the player was in fact able to fly without their wings. (PR#1483 by Stadler76)</ul>"
			+"<ul>Performance improvement and fixed issue with loading of book entries for subspecies without a bookEntries.xml file. (PR#1484 by AceXP)</ul>"
			+"<ul>Fixed an oversight for mer-tailed and serpent-tailed non-ferals where the full leg description, complete with references to feet, was being incorrectly shown. (PR#1487 by Stadler76)</ul>"
			+"<ul>Fixed graphical issues which arose when selecting very dark dye colors on items due to some stroke colors being set to CMYK black (#231f20) instead of RGB black (#000000). (PR#1488 by DSG)</ul>"
			+"<ul>Fixed parsing errors, typos and minor issues in Nyan's updated quest content. (PR#1491 by AceXP)</ul>"
			+"<ul>Added new icons for eagle-harpies and fennec-foxes. (by NeverLucky)</ul>"
			+"<ul>Fixed bug where upon summoning your elemental for the first time, background errors would be thrown. (by AceXP)</ul>"
			+"<ul>Made artwork file extension checking case insensitive. (PR#1448 by CognitiveMist)</ul>"
			+"<ul>Improved performance and memory usage by implementing use of a single instance of the TransformerFactory, DocumentBuilderFactory and DocumentBuilder. (PR#1493 by AceXP)</ul>"
			+"<ul>Fixed issue with wing types not being available in the enchantment menu, and also maximum penetration girth not being able to be selected. (by Stadler76)</ul>"
			
			+"<li>DSG's Enforcer Item updates (PR#1490):</li>"
			+"<ul>Updated Enforcer item files: deleted outdated comments; fixed typos; updated weapon effects to be applied via effects element.</ul>"
			+"<ul>Enforcer weapon effects are now applied on critical hits instead of by random chance.</ul>"
			+"<ul>Pepperball rifle and pistol now have new special attacks of 'Mag Dump' and 'Seven Rounds Rapid', respectively.</ul>"
			+"<ul>The Liquid Stun Gun's silly-mode effects have been moved into a new silly-mode variant of the weapon, which can be spawned via the debug menu's cheat spawn menu.</ul>"
			+"<ul>Added a test Enforcer beret with no sticker requirements to the debug menu's cheat spawn menu.</ul>"
			+"<ul>Updated Enforcer beret with some description text that was lost during sticker work.</ul>"
			
			+"<li>DSG's race fixes:</li>"
			+"<ul>Race version 1.3:</ul>"
			+"<ul>Removed face requirements from wyvern, drake, coatl, and ryu subspecies weighting for improved support for lesser morphs.</ul>"
			+"<ul>Removed attribute scaling from the Breath Weapon special attack as it was already having damage boosted in damage calcuation, creating a 'double-dip'. Base damage is now fixed at 75.</ul>"
			+"<ul>Removed ear requirement from coatls.</ul>"
			+"<ul>Removed face TF from wyverns and drakes.</ul>"
			+"<ul>Changed coatl tongue requirement to only require the BIFURCATED modifier.</ul>"
			+"<ul>Improved flightlessness detection in drakes and ryus.</ul>"
			+"<ul>Fixed incorrect year and formatting in the book entry for dragons.</ul>"
			+"<ul>Fixed typo in bear vagina descriptions.</ul>"
			+"<ul>Fixed gryphons spawning in with generic feathered wings instead of the correct wing type.</ul>"
			+"<ul>Race version 1.31:</ul>"
			+"<ul>Fixed three typoes in the dragon vagina TF description.</ul>"
			+"<ul>Race version 1.32:</ul>"
			+"<ul>Fixed similar typoes as 1.31 in the vagina TF descriptions for bears, ferrets, gryphons, otters, raccoons, sharks.</ul>"
			+"<ul>Race version 1.33:</ul>"
			+"<ul>Fixed formatting issue in the otter beverage effects description.</ul>"
			+"<ul>Changed Breath Weapon calculation, base damage will now scale with spell damage bonuses. Starting damage is now 20. Having either a dragon torso or dragon face will increase starting damage to 50. Having both will increase starting damage to 75.</ul>"
			+"<ul>Fixed feral coatls with a leg configuration of TAIL_LONG not being recongized as a coatl.</ul>"
			+"<ul>Race version 1.34:</ul>"
			+"<ul>Fixed dragon racialBody not having any tails listed</ul>"
			+"<ul>Fixed gryphon racialBody not having tail feathers listed</ul>"
			+"<ul>Added the 'feathered' tail type to dragons</ul>"
			+"<ul>Added a missing ARM_WINGS_LEATHERY tag to dragon arm-wings</ul>"
			
			+"<li>Engine/Modding:</li>"
			+"<ul>Added parser prefix hooks for muscle, body size, and body shape enums (using 'MUSCLE_', 'BODY_SIZE_', and 'BODY_SHAPE', respectively).</ul>"
			+"<ul>Added parser prefix hook for the DayPeriod enum (using 'DAY_PERIOD_').</ul>"
			+"<ul>Eye type xml files now support the 'tags' element.</ul>"
			+"<ul>Added argument to foot/feet parsing commands to ignore clothing when parsed during sex.</ul>"
			+"<ul>Added 'CHASTITY' item tag, which makes clothing with this tag apply the new chastity effect to characters who equip it.</ul>"
			+"<ul>Added an 'applicationLength' element to status effect mods. This enables you to define how long a custom status effect will be applied for if its application conditions are found to be true. If this 'applicationLength' is missing, the currently-used default value of -1 is used, so you don't need to refactor any of your status effect mod files to account for this change.</ul>"
			+"<ul>Added RESTING_LUST as an attribute, so any modded items or effects can now influence this.</ul>"
			+"<ul>Changed method signature for Sex methods: 'setTimesCummedInside()' and 'incrementTimesCummedInside()'.</ul>"
			+"<ul>Added 'newSexType(SexParticipantType, SexAreaInterface, SexAreaInterface)' method to the sex class (at the very bottom of the class file) so that there's a way to generate SexType objects from the parser (via the 'sex' object).</ul>"
			+"<ul>You can now define 'placeLocations' in subspecies xml files to allow for subspecies to spawn in specific place types. (See 'res/mods/innoxia/race/hyena/spotted.xml' for an annotated example.)</ul>"
			+"<ul>You can now actually leave 'regionLocations' and 'worldLocations' elements in subspecies xml files blank without throwing errors, as the comments said you could.</ul>"
			+"<ul>Added ability to define body hair availability to the bodyPart xml files: anus, arm, face, penis, and vagina.</ul>"
			+"<ul>Added ability to define subspecies names based on their leg configuration. Refactoring old subspecies xml files is not needed. If you would like to make use of this functionality, see the 'res/mods/innoxia/race/hyena/spotted.xml' file.</ul>"
			+"<ul>Also added ability to define subspecies silly-mode names. See the same file as above.</ul>"
			+"<ul>Added 'HAIR_NATURAL_MANE' as a body part tag for defining HairTypes as being naturally styled into a mane.</ul>"
			+"<ul>Added 'slotClothing(slot, coloured)' parsing command for parsing the name of a character's clothing.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Reworked Nyan's romance quest, which includes a new warehouse map and a new character. Nyan's post-quest dating content is half-finished, but will be completed for the next release.</ul>"
			+"<ul>Added 'cougar-morph' as a subspecies of cat-morph. Requirements to be identified as a cougar are: non-patterned tan fur, muscles of at least 'muscular', and body size of at least 'average'.</ul>"
			+"<ul>All chastity devices (cages and belt) now apply a new chastity status effect when equipped, which increases in intensity the longer the character continues wearing it.</ul>"
			+"<ul>Added internal map to Slaver Alley's bounty hunter lodge, and renamed it to 'The Rusty Collar' tavern.</ul>"
			+"<ul>Added public toilets to the Shopping Arcade, which you can use to wash yourself and/or use a gloryhole.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added: Women's trousers (feminine, leg slot, sold by Nyan).</ul>"
			+"<ul>Added: Gemstone necklace (androgynous, neck/wrist slots, sold by Nyan).</ul>"
			+"<ul>Added: Women's winter coat (feminine, over-torso slot, sold by Nyan).</ul>"
			+"<ul>Bandada can now be worn in the head and hair slots, and supports pattern recolouring.</ul>"
			+"<ul>Long-sleeved shirt is now unisex, and the colours of its buttons and tag can be changed.</ul>"
			+"<ul>Added more colouring options to men's and women's watches.</ul>"
			+"<ul>All glasses can now be displaced by being pulled up (onto forehead).</ul>"
			+"<ul>Made the three brown clothing colours less saturated, so they look less orange and more brown than before. Also slightly desaturated the tan colour.</ul>"
			+"<ul>Added 'dark desaturated brown' and 'desaturated tan' as new clothing colours.</ul>"
			+"<ul>In clothing/weapon dye screens, standard colours for the selected item now have a slightly lighter border, and the tooltip for each colour reveals whether it's a standard colour or not.</ul>"
			+"<ul>The 'Demonstone necklace' item is now sold by Vicky, not Nyan.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Added 'Stroke cock' as an ongoing self-action, which you can start/stop like other ongoing actions.</ul>"
			+"<ul>Added 'foot worship' actions (performing oral on a partner's feet). Available in the slots: Performing oral (in positions Standing/Behind desk/All fours/Lying down), Reverse cowgirl (for non-taurs), Missionary (for non-taurs), Scissoring.</ul>"
			+"<ul>Weighting of NPCs' choice to use foot actions is no longer partially based on the player character's fetishes, and is now only affected by the foot action content setting and whether the NPC at least likes the associated foot fetish.</ul>"
			+"<ul>Added more information to NPCs' 'Desires' status effect tooltip when in sex, so that you can now see NPCs' foreplay and main sex preferences.</ul>"
			+"<ul>Added 'Humping' sex slots to the 'Over desk' sex position.</ul>"
			+"<ul>Characters can now use their serpent-tail lower body to perform all of the usual tail sex actions. Serpent-tail girth is based on hip size, while length starts at 500% of height.</ul>"
			+"<ul>Stomach bulge descriptions are now appended when the penetration length inserted into a character's vagina or anus is greater than 12.5% of their height, instead of just when penetration lengths were detected as being uncomfortable.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>The five main horn types (curled, spiral, curved, swept-back, straight) are no longer limited to demons, and instead are generically available for all races who lack special horn types in the same way that feathered/leathery wings are.</ul>"
			+"<ul>Hyena, panther, and demon eyes now have night vision.</ul>"
			+"<ul>Rabbits, rats, and squirrels now correctly have plantigrade feet by default instead of digitgrade.</ul>"
			+"<ul>Removed the 8-hour loiter action and added a 24-hour action.</ul>"
			+"<ul>Resting lust increase from being vulnerable to an ongoing arcane storm has been reduced from 75 to 50.</ul>"
			+"<ul>Hyena-morphs now correctly spawn with vertical pupils instead of round, and panther-morphs spawn with round pupils instead of vertical.</ul>"
			+"<ul>You can now get your milk, cum, and girlcum recoloured by Kate at Succubi's Secrets, and you will no longer see covering recolouring options for parts you do not have (e.g. Horn covering colours will no be displayed if you have no horns).</ul>"
			+"<ul>NPC surnames are now shown in their name tooltip.</ul>"
			+"<ul>Updated home icons for Arthur, Zaranix, Desryth, and Helena.</ul>"
			+"<ul>Slightly adjusted penetration girth brackets.</ul>"
			+"<ul>Added indication of a serpent-tail's length in characters' body tooltip, and changed torso's 'Height' indication to 'Length' if the character is feral and their size is measured head-to-tail instead of how high they are.</ul>"
			+"<ul>Serpent-tailed lower bodies can now have their length transformed (either from the self-TF menu or by enchanting food items which have the 'serpent tail' leg configuration transformation available to them, such as snake eggs).</ul>"
			+"<ul>By default, only body parts belonging to mammal or bird races now support having pubic, facial, ass, and underarm hair.</ul>"
			+"<ul>Feral snake subspecies now have more accurate body-to-tail length ratios, and by default are now closer to 2m in total length as a result.</ul>"
			+"<ul>Added tentacle attributes to your phone's 'Body stats' screen and removed breast stats for if you're a feral which has no breasts.</ul>"
			+"<ul>Added subspecies' status effect attributes to their encyclopedia pages.</ul>"
			+"<ul>Nyan's Clothing Emporium is now open from 09:00-20:00 instead of 06:00-22:00.</ul>"
			+"<ul>Traders will no longer always sell/buy unidentified clothing for 50 flames, and instead will offer a buy/sell price that's based on the item's unenchanted value. Also halved items' value reduction from having a 'jinxed' rarity.</ul>"
			+"<ul>Added debug option to spawn an attacker of a defined race when on either a Submission tunnel tile or a Dominion alleyway or canal tile.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Numerous parsing fixes.</ul>"
			+"<ul>Fixed issue where characters could have their location set to coordinates outside of the map's edges.</ul>"
			+"<ul>Fixed incorrect parsing in numerous footjob actions.</ul>"
			+"<ul>Fixed rare issue where characters would have unintended sex preferences after changing position.</ul>"
			+"<ul>The 'Frustrated' status effect is now correctly removed when the character under its effect orgasms.</ul>"
			+"<ul>Fixed issue with Kruger and Kalahari not spawning correctly as lion-morphs (and retroactively reset their bodies to their correct forms when you load into this version).</ul>"
			+"<ul>Fixed bug where numerous miscellaneous sex actions (including most of the sadistic actions and the 'pull hair/ears'-type actions) would never be available to use.</ul>"
			+"<ul>The 'transformationName' element in vagina body part xml definitions now works correctly.</ul>"
			+"<ul>Fixed bug where Helena would repeat conversation topics before you'd seen all of them.</ul>"
			+"<ul>Fixed issue with several incorrectly-gendered cock names (e.g. A male fox's cock being called a 'vixen-cock').</ul>"
			+"<ul>Fixed very rare bug where the game could randomly freeze for a turn when removing certain items of clothing.</ul>"
			+"<ul>Fixed issue where 'Present pussy' positioning action was available in gloryhole scenes even if you didn't have a vagina.</ul>"
			+"<ul>The size of the preview image when dying clothing and weapons now correctly fits into the tooltip's width.</ul>"
			+"<ul>Fixed incorrect colouring of some status effect icons (such as the icons for the smoking effects).</ul>"
			+"<ul>Fixed minor bug where ordering of attributes in modded race tooltips would be random.</ul>"
			+"<ul>Fixed issue where tentacle count was not correctly linked to leg count.</ul>"
			+"<ul>Fixed incorrect descriptions of height and serpent-tail length for feral characters.</ul>"
			+"<ul>Fixed issue where transforming into a feral wouldn't apply the correct leg configuration changes, which was leaving characters with tails where they should have had them removed.</ul>"
			+"<ul>Fixed bug where feral attributes of modded races were not being loaded correctly.</ul>"
			+"<ul>Fixed minor issue with feral Coatl not being described as serpents in some places.</ul>"
			+"<ul>Fixed issue with unintended subspecies spawning in the stocks at slaver alley (such as dragons).</ul>"
			+"<ul>Changing pupil and sclera colour in Succubi's Secrets should now work correctly.</ul>"
			+"<ul>Fixed issue where hair type was not being checked as being suitable for pulling or not in hair-pulling sex actions.</ul>"
			+"<ul>Fixed issue where non-harpy offspring could be encountered in the harpy nests.</ul>"
			+"<ul>Tail length percentages in the self-TF menu should now always correctly increment by 5%, instead of sometimes jumping by 6%.</ul>"
			+"<ul>Fixed issue where rat-penises were no longer spawning in with pale pink skin by default.</ul>"
			+"<ul>Fixed issue with feral parts on a half-demon (such as on taurs) always being referred so as being of a 'demonic-horse', instead of the half-demon's correct subspecies.</ul>"
			+"<ul>Fixed bug where name of nipples was being shown instead of name of crotch nipples, and added more randomly-generated descriptors to the list which nipples use.</ul>"
		+"</list>");
		
		patchNotes = patchNotesSB.toString();
	}
	
	// World generation:
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Anonymous", "", 99, 99, 99, 99));
		
		
		credits.add(new CreditsSlot("Kyle S P", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Paradoxiso", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("luka_fateburn", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("CinnamonSuccubus", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Luka_Fateburn", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Phlarx", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Moro", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Neo", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Abaddon_TMZ", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("ForgottenOne", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Velvet", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("GentleTark", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("QW", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Master Isami", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Valeiya", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Bubbleeey", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("RatKing", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("H3adShotB33otch", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("BerzerkerSteel", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Dave Ziegler", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Kaas", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Dark Miros", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("DethEagle666", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Mystic Exarch", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Lucifer", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("A(woo)CE", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("BL4Z3ST0RM", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("~Chai~", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Scarecrowlust", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Doomtrack", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Ookurikara", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Inferniken", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Ace Morris", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alvinsimon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("dragonouv2019", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Aklev", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("AndroidSam", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 17));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Anonymous_Platypus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 4, 12));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 7));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Mhaak", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Tieria", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 17));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("BlueWolf", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Atroykus", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 18, Subspecies.DEMON));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Captain_Sigmus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 9, 5));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 15));
		credits.add(new CreditsSlot("Hikaru Lightbringer", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Griff", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("DarKaz", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Darthsawyer", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 8, 6));
		credits.add(new CreditsSlot("CruellerTwo24 ", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Nordo", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Di", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("DLI", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("suka", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 6, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 11));
		credits.add(new CreditsSlot("Evit", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("William E", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Grave Indignation", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("GravyRainbow", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Aceofspades", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Assiyalos", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("JaminGold", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Jason Paterson", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("no1skill", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Joeybear", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Joshua Walter", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Justicia Anthony", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kaerea", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Karlimero", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Tappi", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 12, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("BlueVulcan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Krozoz", "", 0, 0, 2, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Krulin", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kyralon", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 4));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Littlemankitten", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("LadyofFoxes", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 19, Subspecies.DEMON));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Manwhore", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Beldamon", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Mega", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Mia Montane", "", 0, 0, 0, 3, Subspecies.DEMON));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Natemare", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Neon Swaglord Chen", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Nexusman", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("SisterFister420", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Seo Leifthrasir", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 13));
		credits.add(new CreditsSlot("Patrik Gr&#246;nlund", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Totes Amazeballs", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("PoyntFury", "", 0, 0, 1, 4, Subspecies.DEMON));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 19, Subspecies.DEMON));
		credits.add(new CreditsSlot("awrfyu_", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 11, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Raruke", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Arpie", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13, Subspecies.DEMON));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Scith", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 18));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Shilou", "", 0, 0, 0, 1, Subspecies.DEMON));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 8, 2));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Sir beans", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Spencer", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 17, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("The Brocenary", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TKaempfer", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Tom Clancy's Pro Skater", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 13));
		credits.add(new CreditsSlot("Jess", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("SolarEidolon", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 14));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Weegschaal", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Will Landrum", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Marys", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Wolfrave", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Yuki_Sukafu", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 14, Subspecies.DEMON));
		credits.add(new CreditsSlot("Zaya", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Zero_One", "", 0, 0, 4, 0));
		
		
		
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

		Main.primaryStage.setTitle(GAME_NAME+" " + VERSION_NUMBER + " " + VERSION_DESCRIPTION+(DEBUG?" (Debug Mode)":""));

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
		Main.sex = new Sex();
		Main.combat = new Combat();
		
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
			
			Alert a = new Alert(AlertType.ERROR,
					"Unable to find the 'data' folder ("+dir.getAbsolutePath()+"). Saving and error logging is disabled."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'data' folder ("+dir.getAbsolutePath()+").");
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
			Alert a = new Alert(AlertType.WARNING,
					"Could not find the 'res' folder ("+dir.getAbsolutePath()+"). This WILL cause errors and present sections of missing text."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'res' folder ("+dir.getAbsolutePath()+").");
			a.showAndWait().ifPresent(response -> {
				if(response == ButtonType.NO) {
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

	public static DocumentBuilder getDocBuilder() {
		if (docBuilder == null) {
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return docBuilder;
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
			System.out.println("Printing to error.log");
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
				
				OptionsDialogue.startingNewGame = false;
				//Main.mainController.processNewDialogue();
			}
		});
		new Thread(gen).start();
	}
	
	public static boolean isVersionOlderThan(String versionToCheck, String versionToCheckAgainst) {
		String[] v1 = versionToCheck.split("\\.");
		String[] v2 = versionToCheckAgainst.split("\\.");
		
		try {
			int maxLength = (v1.length > v2.length) ? v1.length : v2.length;
			for (int i = 0; i < maxLength; i++) {
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
	
	public static boolean isQuickSaveAvailable() {
		return Main.game.isStarted()
				&& !Main.game.isInCombat()
				&& !Main.game.isInSex()
				&& Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL
				&& Main.game.isInNeutralDialogue();
	}
	
	public static String getQuickSaveUnavailabilityDescription() {
		if (!Main.game.isInNewWorld()) {
			return "You cannot save the game during the character creation process or prologue!";
			
		} else if (Main.game.isInCombat()) {
			return "You cannot save the game while while in combat!";
			
		} else if (Main.game.isInSex()) {
			return "You cannot save the game while in a sex scene!";
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType()!=DialogueNodeType.NORMAL) {
			return "You cannot save the game unless you are in a neutral scene!";
			
		} else if (!Main.game.isStarted() || !Main.game.isInNeutralDialogue()) {
			return "You cannot save the game unless you are in a neutral scene!";
		}
		
		return "";
	}
	
	public static String getQuickSaveName() {
		if(!Main.game.isStarted()) {
			return "QuickSave_intro";
		}
		return "QuickSave_"+Main.game.getPlayer().getName(false);
	}
	
	public static void quickSaveGame() {
		if (Main.game.isInCombat()) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot quicksave while in combat!");
			
		} else if (Main.game.isInSex()) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot quicksave while in sex!");
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType()!=DialogueNodeType.NORMAL) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Can only quicksave in a normal scene!");
			
		} else if (!Main.game.isStarted() || !Main.game.isInNeutralDialogue()) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot save in this scene!");
			
		} else {
			Main.getProperties().lastQuickSaveName = getQuickSaveName();
			saveGame(getQuickSaveName(), true);
		}
	}

	public static void quickLoadGame() {
		loadGame(getQuickSaveName());
	}

	public static boolean isSaveGameAvailable() {
		return Main.game.isStarted()
				&& ((!Main.game.getSavedDialogueNode().isTravelDisabled() && MapTravelType.WALK_SAFE.isAvailable(Main.game.getPlayerCell(), Main.game.getPlayer()))
						|| Main.game.getSavedDialogueNode().equals(Main.game.getDefaultDialogue(false)));
	}
	
	public static void saveGame(String name, boolean allowOverwrite) {
		if (name.length()==0) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name too short!");
			return;
		}
		if (name.length() > 64) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name too long!");
			return;
		}
		if (name.contains("\"")) {//!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' _]*")) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Incompatible characters!");
			return;
		}
		
		Game.exportGame(name, allowOverwrite);

		try {
			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininityValue()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName(false);
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.arcaneEssences = game.getPlayer().getEssenceCount();
			if (game.getPlayer().isFeminine()) {
				properties.race = game.getPlayer().getSubspecies().getSingularFemaleName(game.getPlayer());
			} else {
				properties.race = game.getPlayer().getSubspecies().getSingularMaleName(game.getPlayer());
			}
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
		MainController.updateUIButtons();
	}

	public static void loadGame(File f) {
		Game.importGame(f);
		MainController.updateUIButtons();
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
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
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
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
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
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
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
				Main.game.setPlayer(Main.game.getCharacterUtils().startLoadingCharacterFromXML());
				Main.game.setPlayer(Main.game.getCharacterUtils().loadCharacterFromXML(file, Main.game.getPlayer(),
						CharacterImportSetting.NEW_GAME_IMPORT,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED));
				
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
