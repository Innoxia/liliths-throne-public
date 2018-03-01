package com.lilithsthrone.main;

import java.io.File;
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
 * @version 0.2.0
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.0",
			VERSION_DESCRIPTION = "Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone! Finally, I managed to reach 0.2.0! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "There are still a couple more bugs and minor combat/sex improvements left for me to work on, but they are very small things, and won't hold me back from moving on to focusing on content from here on out."
			+ " Over the next few versions, I'll be adding Submission, a few new races, more content for the existing NPCs (looking at you Pix...), and a lot of other things! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all so, so much for all of your support and kind words through LT's 'Early Alpha' phase! Now that I've finished all the engine work, I'll now move on to 'Alpha'. :3"
		+ "</p>"
		
		+ "<p>"
			+ "There might be some bugs in this version, and if they prove to be large enough, I'll make a hotfix in time for Friday evening (GMT). :3"
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.99.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Added support to read .xml files for parsing game content.</ul>"
			+"<ul>Added 'subspecies' as a more refined way to track a character's race.</ul>"
			+"<ul>Added support for clothing to conceal slots (I'll fill in the data for all clothing items as soon as I can).</ul>"
			+"<ul>Reworked the way Quest data is handled in the engine (to allow branching quests).</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Maximum Energy and Aura now take level into account (+2 per level).</ul>"
			+"<ul>Added: Ashley's shop ('Dream Lover'). This is a gift shop, and the items bought from here can be used as a way to boost affection with NPCs (only Nyan for now). (This shop, and the character 'Ashley', were both written by Kumiko.)</ul>"
			+"<ul>Added: Gifts for Ashley to sell; Chocolates, Rose Bouquet, Rose Perfume, and Teddy Bear. Also moved feather duster from Vicky's shop to Ashley's.</ul>"
			+"<ul>Nyan no longer stocks enchanted or 'special' clothing by default (her shop's contents will refresh to the correct stock at midnight).</ul>"
			+"<ul>Nyan now has a quest 'Supplier Issues', through which you can unlock enchanted clothing (she now also stocks one +5/+5 rare piece of clothing in each category after completing this quest).</ul>"
			+"<ul>Nyan now has several romance actions. (Unlocked after completing 'Supplier Issues'.)</ul>"

			+"<li>Transformations/Body Parts:</li>"
			+"<ul>Improved race detection for the 'greater/lesser/minor/partial' system (it now uses weighting of all body parts).</ul>"
			+"<ul>Added 'dobermann-morph' as a dog-morph subspecies (for use in Nyan's quest).</ul>"
			+"<ul>The transformation to 'increase antenna/tail/horn count' will now grow a new pair of antennae/tail/pair of horns if you have none.</ul>"
			+"<ul>Added 'marked' to possible covering patterns (naturally occurs on dog, wolf, cat, horse, and cow morphs, as well as harpies).</ul>"
			+"<ul>Standardised the availability of covering patterns for all skin/fur/hair coverings.</ul>"
			+"<ul>Added 'pointed' dog-morph ear variation TF. (Standard dog-morph ears are now described as 'floppy'.)</ul>"
			+"<ul>Added 'stubby' dog-morph tail variation TF.</ul>"
			+"<ul>Dog-morph penis transformation now sets penis colour to red, and dog-morph penises will now spawn with red skin.</ul>"
			+"<ul>Penis transformation when growing a new penis now sets size to 1 inch (if the underlying size was 0).</ul>"
			+"<ul>Added 'modifier' for coverings (such as 'smooth' and 'fluffy' for cat fur), which can be changed in Kate's shop. (I needed this for the Dobermann addition.)</ul>"
			+"<ul>Added 'tan' as a covering colour for fur.</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Cloak (Gender neutral, over-torso slot).</ul>"
			+"<ul>Remade the 'ankle boots' and 'platform boots' icons.</ul>"
			+"<ul>Added: Chelsea boots (feminine, foot slot).</ul>"

			+"<li>Combat:</li>"
			+"<ul>Added support for multiple combatants, both as allies and enemies.</ul>"

			+"<li>Other:</li>"
			+"<ul>Improved price calculations for all clothing, weapons, and items. Also increased loot drop money, Pix's gym costs to match new amounts.</ul>"
			+"<ul>Removed 'pure' damage and resistance as attributes. (Pure damage will be a damage type for certain spells and attacks later on, but will function as expected - to bypass all resistances.)</ul>"
			+"<ul>Renamed 'strength' attribute to 'physique'.</ul>"
			+"<ul>Added 'Muscle Definition', 'Body Size', and 'Body Shape' to the 'Body Stats' page in your phone.</ul>"
			+"<ul>The 'Cardio' and 'Weights' options in Pix's Playground now reduce your Body Size and increase your Muscle, respectively, while Pix's special workout will apply both effects.</ul>"
			+"<ul>Added physical resist to 'Masochist' fetish. (Also tweaked Sadist and Sadomasochist fetish attribute buffs.)</ul>"
			+"<ul>Added money and essences back to the main UI.</ul>"
			+"<ul>Added unique icons for wolf-morph status effect and items.</ul>"
			+"<ul>Doubled the quantity of items that Ralph stocks.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed poor formatting for covering colours (such as 'brown, hair,').</ul>"
			+"<ul>Active traits should now be correctly restored when loading a game.</ul>"
			+"<ul>Fixed bug where tiles in the bottom-left of maps would be hidden on loading a game.</ul>"
			+"<ul>Fixed some instances of incorrect dirty talk lines.</ul>"
			+"<ul>Fixed issue where NPCs would generate a huge amount of fetish desires, as well as the bug where NPCs would re-generate desires on a game load. (I think - please let me know if this is still happening!)</ul>"
			+"<ul>Fixed UI bug where horizontal scroll bar would sometimes appear in the left panel.</ul>"
			+"<ul>Added ability to get anally penetrated while lying down in the missionary position.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.99.6</h6>"
			+"<li>Bugs:</li>"
			+"<ul>Fixed Nyan's 'Small Talk' action incrementing arousal by 100 instead of 1...</ul>"
			+"<ul>Fixed bug where character imports wouldn't work.</ul>"
			+"<ul>Fixed bug where the 'Face' modification screen in character creation would cause a major background bug to occur.</ul>"
			+"<ul>Fixed game-breaking bug where if you clicked on an item in the right-panel during the prologue, it would freeze the game.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.0</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Increased both slavery income and value to fit in with the recent economy balancing.</ul>"
			+"<ul>Fetish desires are now free to change.</ul>"
			+"<ul>All addictions now progress through withdrawal stages at a rate of one stage per day.</ul>"
			+"<ul>Addictions are no longer removed by simply waiting them out. You now need to drink 'Angel's Kiss' to remove addictions.</ul>"
			+"<ul>Satisfying an NPC's addiction while they're under the effects of withdrawal will make them gain affection towards you (and also obedience if they're a slave).</ul>"
			+"<ul>Added alcohol content to all alcoholic drink items.</ul>"
			+"<ul>Going over 50% intoxication level will apply 'drunk speech' to your sentences.</ul>"
			+"<ul>Psychoactive fluids now apply a status effect. While under this effect, you will randomly suffer from hallucinations (~0.75% chance of a hallucination triggering every minute).</ul>"
			+"<ul>While under the effect of psychoactive fluids, your view of people's bodies will be distorted.</ul>"
			+"<ul>You can perform hypnotic suggestions to NPCs under the influence of psychoactive fluids during sex. (NPCs will also do this to you under the right circumstances.)</ul>"
			+"<ul>Added small chance (0.5% each) for NPCs to spawn with addictive or psychoactive cum and girlcum.</ul>"
			+"<ul>Added 15% chance for NPCs with breasts to spawn in lactating. Added further chance (2.5% each) for the resulting milk to be addictive or psychoactive.</ul>"
			+"<ul>Added 'squirter' modifier to vagina, along with orgasm descriptions for it. You can add/remove the 'squirter' modifier through potions.</ul>"
			+"<ul>Finished Nyan's gift dialogues.</ul>"
			
			+"<li>Enchanting:</li>"
			+"<ul><b>Added:</b> Clothing enchanting.</ul>"
			+"<ul>Improved the enchantment UI.</ul>"
			
			+"<li>Combat & stats:</li>"
			+"<ul>NPCs now have 'resting' lust values based on their corruption. NPCs who are vulnerable to arcane storms have a high amount of lust when exposed to a storm.</ul>"
			+"<ul>Seduction attacks can no longer critically hit. Only the special seduction attacks can score criticals.</ul>"
			+"<ul>Seduction attacks no longer damage the target's aura.</ul>"
			+"<ul>A character will no longer lose combat if their aura reaches 0.</ul>"
			+"<ul>Characters with an arcane ability of less than 15 will lose combat when reaching 100 lust.</ul>"
			+"<ul>Characters with an arcane ability of greater than 15 will suffer from 'desperate for sex' status effect when reaching 100 lust, causing all incoming damage to ignore resistances.</ul>"
			+"<ul>Difference in levels no longer provides a flat +/-25% when levels are 3 or more apart. Instead, each level in difference awards a +/-2% damage modifier to the attacker.</ul>"
			+"<ul>Damage stats are now calculated with 0 as the base value, instead of 100 (to move them in-line with all the other attributes).</ul>"
			+"<ul>Masochist fetish damage reduction reduced from 40% to 25%.</ul>"
			
			+"<li>Clothing:</li>"
			+"<ul>Added more colour options for breeder collar, ballgag, ring gag, choker, restraints, spreader bar, chastity belt, chastity cage, maid dress/sleeves/heels/headpiece, enforcer shorts, livestock tags, cow-bell collar, milk maid headband, mega milk t-shirt, witch boots/hat/dress, and scientist coat/goggles.</ul>"
			+"<ul>Changed slave collar to simply be called 'metal collar', and added it to the bdsm set.</ul>"
			+"<ul>Improved clothing tooltips.</ul>"
			+"<ul>Breeder collar now enslaves the wearer, seals onto them, and applies a vaginal wetness TF by default.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added 'hazel' eye colour.</ul>"
			+"<ul>Added 'auburn' to hair colour, and renamed 'tan' hair colour to 'dirty blonde'.</ul>"
			+"<ul>Added 'ombre' style to all hair types.</ul>"
			+"<ul>Added 'freckled' style to all skin types.</ul>"
			+"<ul>Added counter to orgasm status effect icon.</ul>"
			+"<ul>Alleyway attackers now demand 250 flames instead of 25.</ul>"
			+"<ul>You can now load games while in sex or combat.</ul>"
			+"<ul>Adjusted Scarlett's price from 2000/1000 to 15000/10000.</ul>"
			+"<ul>Taking/dealing damage now increases masochist/sadist fetishes, respectively.</ul>"
			+"<ul>'Stroke cock' and 'Stroke pussy' now transfers lubrication between penis/vagina and fingers.</ul>"
			+"<ul>Angel's Nectar now cures alcoholic and psychoactive effects, in addition to removing addictions.</ul>"
			+"<ul>Removed fetish desire 'cumming' from the randomly generated dislike/hate pool.</ul>"
			+"<ul>Retconned lore about kissing slaves being taboo.</ul>"
			+"<ul>The more cum in an orifice, the faster it now leaks out. (Up to 1.75x the normal value.)</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Added Nyan pregnancy reaction. (by Duner)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed a bug where games saved after exploring the storage rooms in the Supplier Depot would not be able to be loaded.</ul>"
			+"<ul>Fixed font error in Ashley's height description.</ul>"
			+"<ul>Chelsea boots are now unisex.</ul>"
			+"<ul>Loading a game now correctly restores the number of horns characters have.</ul>"
			+"<ul>Minor typo fixes.</ul>"
			+"<ul>NPCs will now generate a new outfit for themselves if you beat them and steal their clothes (to leave them exposed) even if you don't sex with them.</ul>"
			+"<ul>Ashley's race should no longer be added to your encyclopedia (as it's meant to be hidden).</ul>"
			+"<ul>Fixed clothing with negative enchantments having a very low value (such as chastity cages and gags being sold for 1 flame).</ul>"
			+"<ul>Fixed bug where your offspring's race would be described as 'null'.</ul>"
			+"<ul>Fixed bug where sometimes your virginity would not be lost when penetrated.</ul>"
			+"<ul>Fixed broken throat-stretching descriptions.</ul>"
			+"<ul>Fixed minor text bug when using the hypno watch to set someone's orientation to androphilic.</ul>"
			+"<ul>Fixed duplicate associated fetishes showing up for sex action tooltips, and removed vaginal/seeder/impregnation fetishes where not appropriate, as well as fixing a bug where incorrect associated fetishes would be displayed.</ul>"
			+"<ul>Fixed some character import bugs.</ul>"
			+"<ul>Fixed bug where 'Tease her pussy' sex action wouldn't show any text.</ul>"
			+"<ul>Offspring dialogue flags (the values used to determine whether you've visited/attacked/apologised/etc.) should now be correctly saved.</ul>"
			+"<ul>Cumming down someone's throat will no longer dirty their mouth slot.</ul>"
			+"<ul>Fixed bug where special attacks would describe you attacking yourself.</ul>"
			+"<ul>Fixed 'Commands' button not working in the debugger's parser.</ul>"
			+"<ul>Fixed bug where sadist effects were being applied to the wrong person.</ul>"
			+"<ul>Temporarily fixed position-switching bug in multiple-partner sex by disabling position switching. (I'll fix this properly for the next version.)</ul>"
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
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 3));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 3));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 3, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 2, 0));
		
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 2));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 7, 0));
		
		
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
			if(Integer.valueOf(v1[0]) > Integer.valueOf(v2[0])) {
				return false;
			}
			
			if(Integer.valueOf((v1[1].length()==1?v1[1]+"0":v1[1])) > Integer.valueOf((v2[1].length()==1?v2[1]+"0":v2[1]))) {
				return false;
			}
			
			if(Integer.valueOf((v1[2].length()==1?v1[2]+"0":v1[2])) > Integer.valueOf((v2[2].length()==1?v2[2]+"0":v2[2]))) {
				return false;
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
