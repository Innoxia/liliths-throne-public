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
 * @version 0.3.3.10
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Innoxia";
	public static final String GAME_NAME = "Lilith's Throne";
	public static final String VERSION_NUMBER = "0.3.3.10";
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
			+ "I'm really sorry about the length of time between this release and the last one."
			+ " I had some issues over the month of July, and was not able to work on Lilith's Throne enough to get a release out."
			+ " I know I should have communicated this a lot sooner to you, but my feelings of guilt at not having anything new to give to you made me reluctant to post anything."
			+ " I'll try not to let this happen in the future."
		+ "</p>"
			
		+ "<p>"
			+ "Anyway, regarding this update, I got some bugs fixed, some Father's day content added (rather late, due to the issue of not being here throughout July), balance changes applied, and some otehr minor things."
			+ " I've also got the full support for multiple characters interacting with one targeted character's sex area finished, although I've only added actions & descriptions for this related to up to three cahracters performing a blowjob on one target so far."
			+ " I'm going to revamp the way positioning actions work in sex for the next release, so it will be a lot easier to get these multiple-character situations started in future (I think it's impossible to see this multiple-character blowjob outside of the one sex scene in the new Father's day content so far)."
		+ "</p>"
			
		+ "<p>"
			+ "As usual, there might (will) be bugs in this version."
			+ " I'll do my very best to get it all polished for the full release, in which there will be the aforementioned sex positioning improvements, along with some other stuff."
			+ " I won't give a precise date for this release, but I am aiming to have it within a week or so of this preview release."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you for your support and understanding over the past month, and I'll work extra hard to make up for the time I missed!"
		+ "</p>"
			
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or offering financial backing!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.3.10</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added some Father's day content, which triggers when entering Lilaya's mansion during daylight hours between the 14th and 21st of June (in-game time). You also need to have progressed the main quest past the point of meeting Lyssieth, and not have a negative desire towards the incest fetish.</ul>"
			+"<ul>Added 'offhand strike' as a combat move, which deals 100% of your offhand weapon's damage to the target.</ul>"
			+"<ul>Added a toggle in the content options to disable the 'Enchantment capacity' mechanic. (The game won't ever be balanced around this option being turned off, so you will be able to get very OP with it off.)</ul>"
			+"<ul>Clothing can now support multiple slots into which it can be fit. The 'rental_mommy.xml' file is no longer used as the example for modders, as I've made a 'template' folder as a tutorial instead, which also explains how to define these multiple slot options.</ul>"
			+"<ul>Added a small Father's day encounter where someone gives you Vixen's Virility pills (occurs during the third week of June).</ul>"

			+"<li>Artwork:</li>"
			+"<ul>Added image variations of ChattyNeko's Lilaya artwork of her with a shirt on.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Fixed bug where the back button on the selfie page wasn't working. (Fix by Stadler)</ul>"
			+"<ul>Added: Toeless stockings. (Variation of the 'striped toeless stockings' by Blooms)</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Hard hat. (Head slot, no femininity requirements, sold by Nyan.)</ul>"

			+"<li>Balance:</li>"
			+"<ul>Altered energy and aura calculations, to give more weight to level and physique/arcane.</ul>"
			+"<ul>Reset the player's base attributes, as an extra 10 physique was incorrectly applied to the player upon starting a new game. (This shouldn't affect anything else, as permanent attributes are only obtained through the debug menu at the moment.)</ul>"
			+"<ul>It is now free to remove positive enchantments from clothing/weapons/tattoos.</ul>"
			+"<ul>Positive corruption, fertility, and virility attribute enchantments no longer have an enchantment capacity cost.</ul>"
			+"<ul>Allies no longer regain 100% of health and aura when ending combat.</ul>"
			+"<ul>Elementals now have 100 spell efficiency from their core 'elemental' perk.</ul>"
			+"<ul>Balanced natural physical resistance on all clothing.</ul>"
			+"<ul>Did another balance pass on all resistance values from perks and status effects.</ul>"

			+"<li>Sex:</li>"
			+"<ul>Added support for multiple characters participating in the same ongoing penetrative action. Currently implemented for blowjobs, so up to three characters can be performing a blowjob on one person at once. Added description variations for all blowjob actions related to this.</ul>"
			+"<ul>When in sex, if a character's mouth is blocked, all of their speech is now replaced with muffled noises.</ul>"
			+"<ul>Converted the 'sitting' sex position into the new format, so that it correctly supports taur bodies (who cannot sit on a chair).</ul>"
			+"<ul>Added an 'Assist blowjob' sex action, where a third party can push the head of the person giving a blowjob down onto the cock of the person receiving it.</ul>"
			+"<ul>Amber now 'assists' you if you choose to thank Zaranix by giving him a blowjob. Zaranix now also orgasms twice before ending that sex scene, and several other minor issues have been fixed in it (such as being being able to stop the blowjob).</ul>"
			+"<ul>Characters moving to perform cunnilingus or anilingus on taurs will now correctly move behind their partner, insstead of beneath them.</ul>"
			+"<ul>Added a 'receive oral' position variation during sitting sex for taurs.</ul>"
			+"<ul>Added 'between legs' position slot during sitting sex for non-taurs.</ul>"
			+"<ul>Altered requirements of the 'Request rough sex' action, so that only NPCs with the 'masochist' fetish will use it. (This also fixes Nyan asking for rough sex.)</ul>"
			+"<ul>'Dirty talk', 'Submissive talk', 'Rough talk', 'Request rough sex', and the 'Request/Offer X' actions now all have variations for if the person's mouth is blocked.</ul>"
			+"<ul>Kate's sex scenes now correctly start with you standing between her legs (or over the top of her, if you're a taur), not sitting in her lap.</ul>"
			+"<ul>Nyan's sex scene now takes into account if you have a taur body or not.</ul>"
			+"<ul>NPCs who dislike the masochist fetish will now ask for their dominant partner to be gentle with them in sex.</ul>"
			+"<ul>'Submissive talk' and 'Rough talk' actions are now only available to characters who have the submissive or dominant/sadist fetishes, respectively. These actions are now also available for you to use, limited by the same criteria. The 'convincing requests' perk affects these actions, overriding the target's preference for accepting/denying the related pace.</ul>"

			+"<li>Other:</li>"
			+"<ul>Renamed 'energy' attribute to 'health'.</ul>"
			+"<ul>Renamed 'resistance' attributes to 'shielding'.</ul>"
			+"<ul>'Barren' and 'Firing blanks' perks now give -200 to fertility and virility, respectively.</ul>"
			+"<ul>Improved debug menu's item viewer.</ul>"
			+"<ul>Renamed 'enchantment stability' to 'enchantment capacity' to make it clearer that it's a limiting attribute.</ul>"
			+"<ul>Removed majority of attribute boosts from elementals' subspecies status effect, as their stats are derived from perks instead.</ul>"
			+"<ul>The action name for 'Strike' and 'Offhand-strike' combat moves now display the descriptor for your equipped weapon.</ul>"
			+"<ul>The 'Savage attack' special attack is now available to anyone who has a wolf-morph's face and arms.</ul>"
			+"<ul>Changed Vicky's shop icon on the map.</ul>"
			+"<ul>Youko can no longer alter the number of tails they have via TF potions.</ul>"
			+"<ul>Potions made from the Fox-morph racial ingredient (Chicken Pot Pie) can no longer grant Youko tails. (Both of these changes are in preparation for the lore-friendly Youko TF.)</ul>"
			+"<ul>Defined tail ribbon as being able to be alternatively equipped into the hair slot. Defined hair scrunchie as being able to be alternatively equipped into the wrist or tail slot.</ul>"
			+"<ul>Added tooltips to combat's shielding icons.</ul>"
			+"<ul>Turning slimes into angelic slimes, and then reverting them to flesh, will now revert those body parts to human, in the same way non-demonic slimes lose their demon parts when turning back to flesh.</ul>"
			+"<ul>Moved all hand clothing out into xml files in the res folder.</ul>"
			+"<ul>All non-slave slimes and demons will now transform back into their preferred gender form when you are no longer in the same tile as them.</ul>"
			+"<ul>Added occupation 'OCCUPATION_occupation_enum' and clothing type 'CT_clothing_id' parser tags. Also added a way to define Strings for use in the parser in the code, by means of the new 'SpecialParsingString' methods in UtilText.</ul>"
			+"<ul>Arthur's home in Demon Home is not revealed until you've gained the quest to find him. Similarly, Zaranix's house has been moved to its own tile in Demon Home, and is not revealed until you get the associated quest.</ul>"
			+"<ul>The 'too masculine/feminine' clothing status effect now inflicts -15 lust damage instead of -5 arcane.</ul>"
			+"<ul>Added centaurs, unitaurs, pegataurs, and alitaurs to the list of races you can find when searching for a partner in 'The Watering Hole'.</ul>"
			+"<ul>Added text variations for the footsie and seating sex actions in 'The Watering Hole' for if either you or your partner are not bipedal, and fixed the toilet stall sex not supporting taur positions.</ul>"
			+"<ul>Added support for if you have a taur body when having sex with Kalahari and interacting with Kruger.</ul>"
			+"<ul>Added more information to the character information tooltip (when hovering over the race symbol next to their name).</ul>"
			+"<ul>Added minor variations for Vanessa's sex scenes for if you have a non-bipedal lower body.</ul>"
			+"<ul>Moved all of Kate's dialogue out into an external .txt file in the res folder.</ul>"
			+"<ul>Added ability to switch in to/out of the 'sitting' sex position. As it's part of the to-be-fully-updated new sex positioning methods, the follow-on options are currently a little limited.</ul>"
			+"<ul>Added 'freckled (face)' covering for skin, to apply a freckled pattern only to the character's face.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed major bug in how weapon damage was being calculated, which was causing damage to rise multiplicatively with each attribute that was affecting it (making damage numbers far too high).</ul>"
			+"<ul>Fixed several parsing errors.</ul>"
			+"<ul>Fixed the purchase of the quadruple slave room upgrade not working correctly, and made it available as an upgrade from double slave rooms.</ul>"
			+"<ul>Fixed giving yourself perk points in the debug menu not working.</ul>"
			+"<ul>Summoning elementals out of combat now correctly drains your aura.</ul>"
			+"<ul>Fixed fire elemental not being able to be summoned outside of combat (through the spells menu), and fixed it so that it uses energy to cast if you are too low on mana.</ul>"
			+"<ul>Fixed elementals not having their core two perks unlocked.</ul>"
			+"<ul>Fixed clothing resistance values being incorrectly displayed in a couple of places.</ul>"
			+"<ul>Removed 'Untouchable' perk from player (obtained via bug in previous version) and replaced it with your correct occupational trait.</ul>"
			+"<ul>Fixed 'Angel's Purity' having a value of 0.</ul>"
			+"<ul>Fixed not being able to dye weapons/clothing that was on the floor.</ul>"
			+"<ul>Fixed description of pumping cum into a slave's womb in the milking room saying it was due to 'having unprotected sex'.</ul>"
			+"<ul>The 'creampie' orgasm action has been removed for if you're fucking someone with a strapon.</ul>"
			+"<ul>Allowing NPCs to manage your clothing in sex should now work correctly.</ul>"
			+"<ul>Fixed baneful fissure's status effect being applied to allies of the caster.</ul>"
			+"<ul>Attempting (and failing or succeeding) to escape from combat will now correctly reverse selected moves of you and your allies (thereby refunding spent aura/energy).</ul>"
			+"<ul>Fixed bug where combat moves would stay queued up when attempting to escape.</ul>"
			+"<ul>Fixed bug where NPCs trying to cast fire spells would softlock combat.</ul>"
			+"<ul>Fixed 'Ass-to-mouth' and 'Pussy-to-mouth' orgasm actions being available during spitroasting.</ul>"
			+"<ul>Fixed issue with clothing tooltips breaking when they had too many extra effects.</ul>"
			+"<ul>Fixed men's leather jacket not concealing torso clothing.</ul>"
			+"<ul>Removed reference to '+0' in racial status effects' core attribute modifiers.</ul>"
			+"<ul>Fixed incorrect reference to Kate having black hair in her intro scenes.</ul>"
			+"<ul>Fixed issue with not being able to rename Youko slaves.</ul>"
			+"<ul>Fixed issue with altering a Youko's tail count not increasing their stats.</ul>"
			+"<ul>Clothing management during sex will now correctly use the rough description variant if the managing character is in the rough pace.</ul>"
			+"<ul>Importing a character when starting a new game, or when importing one as a slave, will now correctly clear their sex and combat history.</ul>"
			+"<ul>Fixed incorrect description of clothing going into NPCs' inventory when you took it for yourself.</ul>"
			+"<ul>Fixed bug where new player characters would start with an extra 10 physique.</ul>"
			+"<ul>Fixed incorrect attribute modifiers for racial essences.</ul>"
			+"<ul>Fixed inspection of slaves describing them stripping, even if they were already naked.</ul>"
			+"<ul>Masturbation and breast groping actions are no longer available if hands are not able to be accessed.</ul>"
			+"<ul>Fixed issue with demonic slaves transforming back into their preferred gender forms.</ul>"
			+"<ul>Defeated NPCs will no longer have multiple copies of their related lore book in their inventory.</ul>"
			+"<ul>Slaves who are allowed to masturbate now wait 6 hours after gaining the 'Pent-up' status effect before masturbating, during which time they are available to use you for sex (if they can reach you).</ul>"
			+"<ul>Sex with slaves now correctly sets your companions and other slaves present in the room to be dominant & submissive spectators, respectively.</ul>"
			+"<ul>Fixed background errors being thrown upon starting a new game related to the Rental Mommy not being spawned in.</ul>"
			+"<ul>Fixed some inconsistent dialogue in Lilaya's laboratory sex scene dialogue, and added correct variations for the chair sex for if you're a taur.</ul>"
			+"<ul>Fixed dildos not being displayed correctly in the character information tooltip (when hovering over the race symbol next to their name).</ul>"
			+"<ul>Fixed Lilaya's and Meraxis's nipples, anus, vagina, and penis colours not being updated to their correct demonic colours when being transformed (or have already been transformed) into a demon.</ul>"
			+"<ul>Fixed fingering and other appendage-area actions missing from the standing sex position.</ul>"
			+"<ul>Fixed orgasm cum targets missing from some sex positions (making it so cumming on the floor was)</ul>"
			+"<ul>Fixed another issue in sex where if a character was receiving a handjob, they would be unable to start receiving anal or vaginal sex.</ul>"
			+"<ul>Noxinia can now have items equipped onto her, allowing you to enslave her again.</ul>"
			+"<ul>Fixed issue where threesome sex in slave/occupant rooms would not work when even if you had a companion with you.</ul>"
			+"<ul>Fixed NPCs not being able to use the 'Choke' & 'Slap face' sex actions. </ul>"
			+"<ul>Fixed issue with the 'leader' of the submissive or dominant sex partners not being at the top of the list.</ul>"
			+"<ul>Fixed bug where if you went to give Nyan a gift, and had no gift available, the 'Gift' action was still put on cooldown.</ul>"
			+"<ul>Both Pix's and your character's bodies now actually get cleaned when taking a shower after her workout.</ul>"
			+"<ul>Fixed incorrect description being parsed in the loss of your pure virginity scene.</ul>"
			+"<ul>Fixed bug where sex actions already ongoing when sex starts might not be targeting the correct person.</ul>"
			+"<ul>The character 'selfie' view now describes horn length, and also had a related horn parsing bug fixed.</ul>"
			+"<ul>Fixed the 'grab horns', 'twintail pull', and 'ear pull' sex actions sometimes not being available when they should have been.</ul>"
			+"<ul>When NPCs use a sex action that ends sex, it now correctly stops other characters from performing more actions afterwards.</ul>"
			+"<ul>Fixed some categories of clothing being absent from the debug item view menus.</ul>"
			+"<ul>Fixed issue in the sex scene with Ralph's 'big discount', as well as in the Breeding stall scene, where vaginally-equipped clothing might get permanently deleted.</ul>"
		+ "</list>"
	;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

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
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 18));
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
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 19));
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
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("awrfyu_", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 11, 0));
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
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13));
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
			Alert a = new Alert(AlertType.ERROR,
					"Unable to find the 'data' folder. Saving and error logging is disabled."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
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
			Alert a = new Alert(AlertType.WARNING,
					"Could not find the 'res' folder. This WILL cause errors and present sections of missing text."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
					ButtonType.YES, ButtonType.NO);
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
			Main.getProperties().lastQuickSaveName = "QuickSave_"+Main.game.getPlayer().getName(false);
			saveGame("QuickSave_"+Main.game.getPlayer().getName(false), true);
		}
	}

	public static void quickLoadGame() {
		String name = "QuickSave_"+Main.game.getPlayer().getName(false);
		
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
			properties.name = game.getPlayer().getName(false);
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.arcaneEssences = game.getPlayer().getEssenceCount(TFEssence.ARCANE);
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
