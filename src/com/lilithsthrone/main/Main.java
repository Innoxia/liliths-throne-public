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
 * @version 0.2.6
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.6.1",
			VERSION_DESCRIPTION = "Alpha";
	
	private final static boolean DEBUG = false;

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello once again!"
		+ "</p>"
			
		+ "<p>"
			+ "Sorry once again for the delay last week! For this version, I've managed to get tattoos fully implemented, as well as adding in mechanics for the gambling Den's dice poker."
			+ " Both of these things took a little longer than I'd hoped (especially the tattoos), so the Gambling Den content is only half-finished."
			+ " I will be releasing another mini update (v0.2.6.1) in two days (on Saturday evening), which will have the Gambling Den's content completely finished."
		+ "</p>"
			
		+ "<p>"
			+ "So, to reiterate, there will be a mini-update, v0.2.6.1, out on the evening/night of <b>Saturday 2nd</b>."
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.5.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added new quest 'Slime Queen'. You should automatically pick this up from Claire when you enter Submission (she will repeat her initial dialogue if you've already seen it).</ul>"
			+"<ul>Added unique slime attack descriptions for Submission's tunnels, along with transformation behaviour of wanting to TF you into a slime.</ul>"
			+"<ul>(Not yet finished!) Added the map 'Slime Queen's tower' as part of the new quest, with 4 new NPCs in there.</ul>"

			+"<li>Balance:</li>"
			+"<ul>Changed slime's status effect from -100 physical damage to -100 unarmed damage.</ul>"

			+"<li>Artwork:</li>"
			+"<ul>Added Jam's artwork of Claire.</ul>"

			+"<li>Weapons:</li>"
			+"<ul>Added support for dying weapon colours.</ul>"
			+"<ul>Added: Zweihander (Main weapon slot).</ul>"
			+"<ul>Added: Knightly Sword (Main weapon slot).</ul>"
			+"<ul>Added: Buckler (Offhand weapon slot).</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added secondary colour to navel barbell (for the gem).</ul>"
			+"<ul>Added snowflake jewelry to Nyan's shop.</ul>"
			+"<ul>Added: Sun necklace (no femininity requirements, neck slot).</ul>"
			+"<ul>Added: Sun earrings (no femininity requirements, ear piercing slot).</ul>"
			+"<ul>Added: Sun nose stud (no femininity requirements, nose piercing slot).</ul>"

			+"<li>Other:</li>"
			+"<ul>The losing party's elementals are now dispelled at the end of combat.</ul>"
			+"<ul>Slightly changed biojuice canister icon, lowered its value, and removed it from random Submission drops.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed bug in clothing displacement detection method, which could sometimes cause the game to lock up when wearing sealed clothing.</ul>"
			+"<ul>Fixed chaos feathers only being available in the poison damage type.</ul>"
			+"<ul>Enchanting clothing will no longer reset item tags (which was causing the bug of nipple tape crosses losing their plugging effect).</ul>"
			+"<ul>Fixed obsolete reference to a dress in succubus attacker dialogue.</ul>"
			+"<ul>Fixed slime colours resetting every time the game was loaded.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.5.8</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finished Slime Queen's quest, adding the upper floor of the tower.</ul>"
			+"<ul>Added harpy offspring encounters in the Harpy Nests.</ul>"
			+"<ul>Temporarily re-enabled angel offspring in Dominion (for those of you who have set yourself to be angels through the debug menu).</ul>"
			+"<ul>Added item 'Glowing Mushrooms', found randomly in the bat Caverns as well as being dropped by slimes down there.</ul>"
			+"<ul>Filled in last placeholders in Bat Cavern descriptions.</ul>"
			+"<ul>Added unique encounter/attack descriptions for slimes and bats in the bat caverns.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added variation in the slime guards' dialogue for if you have the related content setting turned off.</ul>"
			+"<ul>Added ongoing penetration descriptions for hotdogging and paizuri, and added icon for hotdogging status effect.</ul>"
			+"<ul>Increased value of girlcum, and slightly decreased extra modifiers' impact on cum value.</ul>"
			+"<ul>Reduced biojuice canister value from 5000 to 2500.</ul>"
			+"<ul>You can now enchant biojuice canisters to make a potion that returns body material to normal.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed bug where when entering Submission, you would sometimes travel down to an incorrect tile location.</ul>"
			+"<ul>Fixed being forced to have sex with the slime guards after beating them in combat.</ul>"
			+"<ul>Fixed some typos and parsing errors in 0.2.5.5's content.</ul>"
			+"<ul>Removed biojuice canisters from bat cavern drops.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed Blaze and Crystal's earrings being unequipped when loading a save.</ul>"
			+"<ul>Fixed Blaze and Crystal's repeat encounter acting like they'd never seen you before.</ul>"
			+"<ul>Fixed bug where switching target in combat would not show the correct target's inventory.</ul>"
			+"<ul>Fixed human damage/resistance stats being shown twice in phone's stats menu.</ul>"
			+"<ul>Fixed NPCs spawning in with multiple books in their inventory.</ul>"
			+"<ul>Fixed bug where you'd gain knowledge of some characters' breasts/genitals on game load. (This was affecting Candi, Claire, and probably many others.)</ul>"
			+"<ul>Fixed jewellery not being unequipped when removing piercings.</ul>"
			+"<ul>Characters will no longer lose penile virginity from using strapons.</ul>"
			+"<ul>Penis transformations will no longer be applied to equipped strap-on.</ul>"
			+"<ul>Fixed rendering bugs in stats page.</ul>"
			+"<ul>Fixed bug where spreader bar being equipped under trousers would result in the spreader bar blocking trouser removal, and also being concealed, making both items of clothing impossible to remove.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.6</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added 'generic' encounter variations for bat and slime attackers in the Bat Caverns (for if you've transformed them).</ul>"
			+"<ul><b>Added:</b> Support for tattoos, with ability to have them enchanted just like clothing. Kate can now give/enchant/remove both your and your slaves' tattoos.</ul>"
			+"<ul>Added modding support for tattoos. (Works in the same way as clothing mods.)</ul>"
			+"<ul>Added scar support. (I'll add a way to add/remove scars for the next version.)</ul>"
			+"<ul><b>Added:</b> Basics of the gambling den (will be finished in 0.2.6.1). It has a small internal map, three unique NPCs, a dice poker game, a shop, and 'pregnancy roulette'.</ul>"
			
			+"<li>Clothing:</li>"
			+"<ul>Added secondary dye colour to aviators.</ul>"
			+"<ul>Added: Eye patch (no femininity requirements, eye slot).</ul>"
			+"<ul>Added: Jockstrap (masculine, groin slot).</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Changed Lumi's 'Take Advantage' scene to actually be a sex scene (only available if noncon is on). (Nnxx)</ul>"
			+"<ul>Fixed inserted bimbo/sex/muffled/drunk words breaking capitalisation. (pinnae)</ul>"
			+"<ul>Changed 'Romance' quests to 'Relationship' quests, and reassigned Nyan's side quest as a relationship quest. (rfpnj)</ul>"
			+"<ul>Improved action ordering and availability in sex's 'Repeat Actions' tab. (Master of Puppets)</ul>"
			+"<ul>Added some new skin colours. (rfpnj)</ul>"
			+"<ul>Fixed several issues with coding style. (Pimvgd)</ul>"
			+"<ul>Converted UtilText.parse from recursive to iterative. This increases performance by a small amount, and reduces memory usage by a large amount. (Pimvgd)</ul>"
			+"<ul>Deduplicated book itemtypes. (Pimvgd)</ul>"
			+"<ul>Punctuation fixes in Rental Mommy encounter. (WoefulWombat)</ul>"
			+"<ul>Punctuation fixes in panty masturbation scenes. (WoefulWombat)</ul>"
			+"<ul>Changed Fruit Bat Squash to restore aura, not energy, to fit in with the other arcane-related consumables. (rfpnj)</ul>"
			+"<ul>Fixed HornType.NONE showing up as an option for enchanting. Horn removal can be done by selecting the remove option. (Pimvgd)</ul>"
			+"<ul>Fixed 'No cock' and 'Vagina' actions in succubus encounter not doing anything. (Master of Puppets)</ul>"
			+"<ul>Fixed incorrect pronoun parsing in bat encounter. (Mach565)</ul>"
			+"<ul>Fixed incorrect Slime Queen quest completion descriptions. (rfpnj)</ul>"
			+"<ul>Fixed typo in Brax's scene. (Pimvgd)</ul>"
			+"<ul>Fixed inconsistent starting positions in Brax's sex scenes. (rfpnj)</ul>"
			+"<ul>Fixed FemininityRestriction not being set properly for clothing loaded as mod. (Pimvgd)</ul>"
			+"<ul>Added mod folder to gitignore. (Pimvgd)</ul>"
			+"<ul>Fixed instance of weapon comparator contract violation when checking weapon type. (Pimvgd)</ul>"
			+"<ul>Added a new colour preset, 'ALL_WITH_METALS'. (rfpnj)</ul>"
			+"<ul>Fixed several causes of slow load times, which, as an example, reduced a huge save file's load time from 40+ seconds down to 5. (Pimvgd)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added 'wing' slot as an inventory & tattoo slot.</ul>"
			+"<ul>Reorganised inventory slots in the UI, so that 'optional' ones are at the bottom (tail, wings, horns, penis, and vagina).</ul>"
			+"<ul>Elementals can no longer summon elementals.</ul>"
			+"<ul>Elementals are now unable to drink potions.</ul>"
			+"<ul>Companions' elementals now show up in the character panel.</ul>"
			+"<ul>Companion icons now show up on the map.</ul>"
			+"<ul>Added 'Dispell elemental' action in your companions' 'Manage' tab, so you can get them to remove their elementals after a fight.</ul>"
			+"<ul>Added a few tattoos to Kate.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed Claire repeating the first 'report back' dialogue after dealing with the slime queen.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed harpy offspring not spawning in after the nests have been pacified.</ul>"
			+"<ul>Added 'offer body' dialogue to slime and bat attacker scenes.</ul>"
			+"<ul>Helping the Slime Queen now correctly increases slime spawn rates in Submission.</ul>"
			+"<ul>Fixed Maximilian spawning in as a virgin.</ul>"
			+"<ul>Fixed Slime Queen regenerating her crown after she's given it to you.</ul>"
			+"<ul>Fixed slime transformation scenes saying 'Nothing happens...'.</ul>"
			+"<ul>Fixed Maximilian's post-sparring scenes.</ul>"
			+"<ul>Fixed bug where harpies, imps, and slimes would have their spawn rate set to 0% if you ever selected 'disable all' in the furry preferences screen.</ul>"
			+"<ul>Fixed horizontal scrollbar appearing when inspecting watches.</ul>"
			+"<ul>Fixed bug in combat where companions summoning elementals would throw an error.</ul>"
			+"<ul>Fixed bug where if a companion summoned an elemental, that elemental would block encounters.</ul>"
			+"<ul>Fixed clothing mod bug, where user-defined colours wouldn't work correctly.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.2.6.1</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finished all descriptions in Gambling Den.</ul>"
			+"<ul>Added slot machines to the Gambling Den.</ul>"
			+"<ul>Added Roxy's content.</ul>"
			+"<ul>Added item 'Arcane Pregnancy Tester', which reveals if a person is pregnant, along with some information about the litter. (Sold by Ralph and Roxy, and found in Dominion alleyways and Submission tunnels.)</ul>"
			+"<ul>Added mechanics for pregnancy roulette. It should all be fully functional, but the descriptions are still placeholders, as I ran out of time. I'll have them fully finished for the next release.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul><b>Added:</b> Clothing patterns. Several pieces of clothing now have support for the patterns; Camo, Cow-patterned, Tiger-striped (horizontal), Tiger-striped (vertical), Leopard-printed, and rainbow. (Irbynx)</ul>"
			+"<ul>Fixed typo in colours. (rfpnj)</ul>"
			+"<ul>Fixed orgasm descriptions mentioning player's name. (Pimvgd)</ul>"
			+"<ul>Fixed cause of saves in slaver alley stocks cell breaking. (Master of Puppets)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>AI will now always reroll the dice poker hand 'runt'.</ul>"
			+"<ul>Lumi now uses the resist pace in her sex scene.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed dice poker sex scene using the wrong NPC, or failing to start entirely.</ul>"
			+"<ul>Fixed feminine furry preference changes influencing masculine furry preferences.</ul>"
			+"<ul>Fixed Kate not charging you for enchanting tattoos.</ul>"
			+"<ul>Fixed bug where when giving slaves tattoos, the counter output example showed your stat rather than the slave's stat.</ul>"
			+"<ul>Fixed background error in some sex actions that would cause the action to appear not to have worked.</ul>"
			+"<ul>Fixed cause of Slime Queen's dialogue bugging out and not being able to be selected.</ul>"
			+"<ul>Fixed bug where NPCs would not choose any sex action if they disliked/hated noncon and it was a noncon scene.</ul>"
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
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 6));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 6));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 6, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));

		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 1));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 1));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 4));
		
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 3, 0));

		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 2, 0));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 2));

		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Drahin", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 1, 0));
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 8));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 1));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 8, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 8));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 8, 2));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 8));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 7, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 4));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 9));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 8, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 5));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 10, 0));
		
		
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
