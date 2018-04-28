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
 * @version 0.2.4
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.4",
			VERSION_DESCRIPTION = "Alpha";

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
			+ "As you've probably seen, I've held off from marking this version as 0.2.4, as I've run out of time towards the end of getting all the new spell work done."
			+ " It took a huge amount of time to get all of the supporting code done for these new spells, and while I've been working on it non-stop since Saturday, I didn't have enough time to get the last spell school (Arcane) finished for this release."
		+ "</p>"
			
		+ "<p>"
			+ "I need another two days to get the spells completely finished, and to add the slavery improvements and the start of Nightlife, as well as more Submission content."
			+ " Once that's done, I'll call it 0.2.4 and release it, which should be on Friday night/Saturday morning."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.3.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Added:</b> Companion system. You can just take one slave around with you for now, and the interaction options are a little limited (post-combat sex options haven't been implemented yet), but this adds functionality for adding unique followers later on. (Contribution from Irbynx.)</ul>"
			+"<ul><b>Added:</b> Clothing mod support. (Look in the mod folder for details.)</ul>"
			+"<ul>Updated personality types from being a set one-in-four, to having weights on five personality traits (Adventurousness, Agreeableness, Conscientiousness, Extroversion, and Neuroticism).</ul>"
			+"<ul>Added personality selection to the new character screen. (If you want to set it on your current character, just save the game in this new version, then open the save file and search for 'personality' under your character's entry. You can edit the values there.)</ul>"
			+"<ul>Added map of the Bat Caverns, along with bat-morph and slime encounters.</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Updated all clothing to have correct concealments, and added appropriate tooltip information to let you know what clothing is responsible. (e.g. Panties being worn beneath a skirt will be hidden to you until you unequip or pull up the skirt.)</ul>"
			+"<ul><b>Added:</b> Enforcer's miniskirt (Feminine, Leg slot). Candi now wears this instead of shorts.</ul>"
			+"<ul>Updated Enforcer clothing to use a third colour for buttons, and the frontal-zip dress to use a customisable colour for the zip.</ul>"

			+"<li>Contributors:</li>"
			+"<ul><b>Added:</b> Groundwork for the new companion system. (Irbynx)</ul>"
			+"<ul>Fixed harpy nest entrance background colour. (Master of Puppets)</ul>"
			+"<ul>Typo fix. (Master of Puppets)</ul>"
			+"<ul>Fixed strange behaviour when having sex with NPCs in stocks, where if it was banned for them to receive a certain type of sex, you would be banned from performing related sex actions as well. (Master of Puppets)</ul>"
			+"<ul>Changed sex actions so the only ones that reduce arousal are now ones related to denial. Resisting pace/actions no longer cause arousal to drop, and instead, arousal increases are zero on non-erogenous zone actions, and low on others. Having the non-con fetish sets these arousal increases to high. (Master of Puppets)</ul>"
			+"<ul>Restricted use of 'calm down' action to only be available when you're the dom, or when you have the same amount of control as the dom. (Master of Puppets)</ul>"
			+"<ul>Added tooltips to the phone's map page. (Master of Puppets)</ul>"
			+"<ul>Changed the code so that the 'new addiction' dialog only shows if the ingesting character isn't already addicted to the fluid, and otherwise shows the 'satisfied craving' dialog if the ingesting character is addicted. (AlacoGit)</ul>"
			+"<ul>Tidied up the game's version checking method. (lehardo)</ul>"
			+"<ul>Fixed some instances of incorrect cum parsing. (ltpatch)</ul>"
			+"<ul>Fixed issue with harpies not respecting your gender preferences. (Master of Puppets)</ul>"
			+"<ul>Added folder checking on startup. (Bukkyo256)</ul>"
			+"<ul>Fixed Lilaya's Submissive Geisha scene immediately ending. (TadUnlikely)</ul>"
			+"<ul>Fixed Vixen's Virility returning incorrect descriptions. (ltpatch)</ul>"

			+"<li>Other:</li>"
			+"<ul>Added ChattyNeko's artwork for Nyan.</ul>"
			+"<ul>Moved the 'Explore' action from button 6 to 1. (I originally put it on 6 so that when you were spamming it to explore, you wouldn't accidentally skip the encounter dialogue. Now that 'Explore' always results in an encounter on the first press, there's no need for it to be so out of place.)</ul>"
			+"<ul>Added throat wetness, capacity, elasticity, and plasticity to the 'Body Stats' phone dialogue page, and added more throat descriptions to the selfie/character view page, as well as improving the coloured parts of all body description text.</ul>"
			+"<ul>Added formatting to essence counter, and made the formatting specific to locale. (So now it really should use a comma, period, or space depending on your computer's locale.)</ul>"
			+"<ul>Fixed gloves blocking you from wearing rings.</ul>"
			+"<ul>Non-slave NPCs which do not have the exhibitionist fetish should now properly replace their clothing after you leave them alone.</ul>"
			+"<ul>Removed hard-coded sealing effects from all BDSM set gear, and made them have the 'sealing' enchantment by default instead.</ul>"
			+"<ul>The 'cattle' clothing set is now sold by Finch, instead of Nyan.</ul>"
			+"<ul>Reorganised Nyan's trading menus.</ul>"
			+"<ul>Added support in the code for adding custom artwork for the PlayerCharacter (follow the instructions in 'res -> images -> characters -> modding.txt' to add artwork).</ul>"
			+"<ul>Changed Dobermann subspecies name from the clunky 'Dobermann-morph' to just 'Dobermann'.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed (that annoying) bug where the game's tooltips could get stuck on your screen when alt-tabbing/changing window focus.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed bug in vixen's virility pill effect description.</ul>"
			+"<ul>Fixed incorrect Katherine and Kelly descriptions.</ul>"
			+"<ul>Fixed Zaranix getting stuck in his lounge.</ul>"
			+"<ul>Fixed enchanting UI not showing correct cost reduction from 'Arcane Weaver' perk.</ul>"
			+"<ul>Fixed some bugs in the way clothing concealment was working.</ul>"
			+"<ul>Fixed several clothing displacements being erroneously available through other clothing.</ul>"
			+"<ul>Fixed bug where looking for trouble in the harpy nests would never return a harpy fight encounter.</ul>"
			+"<ul>(Hopefully) fixed that annoying bug where slave's stats would get stuck to the right of your screen.</ul>"
			+"<ul>Fixed bug where public sex would stop being counted as public as soon as you switched position.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.3.9</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Added:</b> Final version of the spell system, along with 16 new & reworked spells (Arcane school spells aren't finished yet).</ul>"
			+"<ul><b>Changed:</b> Spells are now learned through spell books, or by being taught to you by someone else. You gain spell upgrade points by reading appropriate scrolls, which can then be spent to upgrade your spells.</ul>"
			+"<ul><b>Added:</b> Support for elemental companions. (I still need to add in a lot of unique dialogue for them, including unique sex actions. I also need to finish off their body descriptions, as a lot of it is incorrect at the moment...)</ul>"
			+"<ul>Added dodge chance, miss chance, unarmed damage, melee weapon damage, and ranged weapon damage as attributes. Removed spell resistance (to fit in with resistances being focused on countering damage types, not attack types).</ul>"
			+"<ul>All damage and resistance attributes have had value ranges changed from '0 to 100' to '-100 to 100'.</ul>"
			
			+"<li>Artwork:</li>"
			+"<ul>Added Jam's artwork of Zaranix.</ul>"
			+"<ul>Added ChattyNeko's artwork of Scarlett.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Expanded BodyMaterial to add noun and adjective support for all types of body features. Also added relevant parser commands. (tukaima)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Lore books are no longer removed from your inventory after use.</ul>"
			+"<ul>In combat, if all enemies are stunned, escape chance is now 100%.</ul>"
			+"<ul>Added ability to self-target and target allies in combat. (Yes, you can punch yourself or your allies... Please don't do this...)</ul>"
			+"<ul>Rebalanced the passive buffs from high level arcane and physique status effects.</ul>"
			+"<ul>Condensed the character UI panels a little, by removing traits from the shown status effects, and only showing non-timed or combat-related SEs while in combat.</ul>"
			+"<ul>NPCs are now properly able to use spells in combat.</ul>"
			+"<ul>Made the 'Calm Down' sex action available in all scenes again.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed Candi wearing a normal, non-enforcer mini-skirt, and made her uniform pink.</ul>"
			+"<ul>Fixed personality attributes not being saved/loaded correctly.</ul>"
			+"<ul>Fixed bug where modded clothing icons wouldn't load on linux.</ul>"
			+"<ul>Critically casting beneficial spells will no longer apply arcane weakness.</ul>"
			+"<ul>Fixed bug where NPCs would continuously spawn clothing to cover themselves.</ul>"
			+"<ul>Fixed bug where NPC clothing displacements would not be saved.</ul>"
			+"<ul>Fixed demon TF menu options showing incorrect button labels.</ul>"
			+"<ul>Fixed being able to remove companion and manage their inventory when not in a neutral dialogue.</ul>"
			+"<ul>Fixed status effect rendering order.</ul>"
			+"<ul>Fixed bug where DoT effects in combat would tick twice in the turn that they were applied.</ul>"
			+"<ul>Fixed bug where Karl & Wolfgang's fight wouldn't remove your companions.</ul>"
			+"<ul>Fixed bug where Controlled Aggression perk would sometimes not work.</ul>"
			+"<ul>Fixed bugs where your companions would be marked as slaves for sale/use in slaver alley's stocks and auction block. Also fixed similar bugs in Angel's Kiss.</ul>"
			+"<ul>Fixed bug where you could transform Rose in her special scene.</ul>"
			+"<ul>Fixed minor UI bug where the save button would be under the load column.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.4</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Vicky now sells spell books and scrolls. This is temporary until the spell shop is added. (She deliberately doesn't sell spell books for Cleanse, Steal, Soothing Water, Teleport, and Lilith's Command, as these are all planned to be quest-related. If you want to try them out, use the debug menu.)</ul>"
			+"<ul>Finished adding effects for all 8 of the Arcane spells.</ul>"
			+"<ul>Random attackers now spawn with random items in their inventory.</ul>"
			+"<ul>Added variable point costs for spell upgrades.</ul>"
			+"<ul>Added mechanics for spell school unique abilities. (Dye without needing dye-brushes for Earth, fluid enchantments free for Water, Energy & aura regeneration doubled for Air, deal 2 extra fire damage in melee for Fire, know time until next storm for Arcane.)</ul>"
			+"<ul>Added support for clicking on the phone's map to teleport (if you have the 'Teleport' spell).</ul>"
			+"<ul>Added a little passage in the prologue where Lilaya gives you the spell book for 'Ice Shard'. (You will find this spell book in your room when you load into this version.)</ul>"
			+"<ul>Added in crystals and feathers granting you spells/attribute boosts again.</ul>"
			+"<ul>Added elemental encyclopedia lore entries (unlocked when reading the elemental spell book).</ul>"
			+"<ul>Removed Witch's Seal and Witch's Charm spell books. (These were accidentally added in 0.2.3.9.)</ul>"
	
			+"<li>Balancing:</li>"
			+"<ul>Buffed Witch's Seal to last for 3 turns, and increased costs of Witch's Charm and Witch's Seal to 40 and 80, respectively.</ul>"
			+"<ul>Random succubi attackers in the dark alley tiles now spawn knowing the spells 'Arcane Arousal' and 'Telepathic Communication'.</ul>"
			+"<ul>Increased spell book and spell scroll values.</ul>"
	
			+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Hoop earrings (feminine, ear-piercing slot). Candi and Brandi now wear hoop earrings.</ul>"
			+"<ul>Dresses no longer block the leg slot.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Added support for recolouring sclera (the white part of the eye).</ul>"
			+"<ul>Added lots of tongue colours and patterns.</ul>"
			+"<ul>Added 'Reset spells' to debug options.</ul>"
			+"<ul>Renamed 'Seduction' damage/resistance to 'Lust' damage/resistance.</ul>"
			+"<ul>Offensive spells can now only be cast on enemies.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Fixed inventory scaling bug that was present in Java 10 and more recent versions of Java 8.</ul>"
			+"<ul>Fixed potion name generation.</ul>"
			+"<ul>Fixed numerous minor bugs related to elementals (incorrect status effects, summoning costs, descriptions).</ul>"
			+"<ul>Fixed some items of clothing (such as the overbust corset) concealing their own inventory slot.</ul>"
			+"<ul>Fixed clients in Angel's Kiss not being attracted to you.</ul>"
			+"<ul>Fixed bug where safety goggles would get equipped onto you during the prologue.</ul>"
			+"<ul>Fixed bug where female characters could start the game having already lost their penile virginity.</ul>"
			+"<ul>Fixed humans being referred to such things as 'a masculine man male', or 'a feminine woman female'.</ul>"
			+"<ul>Fixed being able to upgrade spells even if upgrade points were 0.</ul>"
			+"<ul>Fixed incorrect NPC eye colour transformation descriptions.</ul>"
			+"<ul>Fixed elementals in your party being available at the stocks.</ul>"
			+"<ul>Fixed companions instantly regeneration 100% of energy and aura after fights.</ul>"
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

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Anonymous", "", 0, 6, 111+61, 37+18));
		
		
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 5));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 5));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 5, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 4, 0));
		

		credits.add(new CreditsSlot("Desgax", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 3));
		
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 2, 0));

		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 1, 0));
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 7));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 7));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 8, 1));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 7));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 6, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 3));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 8));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 4));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 9, 0));
		
		
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
