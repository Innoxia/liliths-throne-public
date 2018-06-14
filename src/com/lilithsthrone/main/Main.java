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

	public static final String VERSION_NUMBER = "0.2.7",
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
			+ "For this version, I've got a lot of bugs and github pull requests sorted out, as well as adding in some other minor things here and there."
			+ " I've also done a refactor of the game's sex action code, which will now enable me to write in the NPC-on-NPC actions."
			+ " There are a lot of sex actions in the game's code that need to be updated to this new format, so while I'm working on updating those over the next few versions, I'll also add in checks for the things I mentioned in 0.2.7's goals"
			+ " (such as size difference, orifice/penetration modifiers, horns, wings, etc.)."
		+ "</p>"
			
		+ "<p>"
			+ "The next version (0.2.7.5 preview) should be out on Wednesday, June 20th, with the full, public release of 0.2.8 being out the following Wednesday, 27th."
			+ " It will be focusing on adding Nightlife content, along with updating the sex actions."
		+ "</p>"
			
		+ "<p>"
			+ "(The Imp fortresses and imp gang attacks in Submission haven't been forgotten, but I wanted to have some large gangbang scenes with the imps, which requires the NPC-on-NPC actions to be finished first. ^^)"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.6.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finished pregnancy roulette dialogue, and added a way to have sex with Epona.</ul>"
			+"<ul>Slightly changed pregnancy roulette mechanics. Instead of being fixed, the order in which the breeders use the volunteer is random. Correctly guessing who will get you pregnant as the volunteer now pays out 50,000 flames.</ul>"
			+"<ul>Slightly improved impregnation calculations. Now, instead of impregnation only being calculated once on the moment of orgasm, the game calculates impregnation every hour while a vagina has cum in it.</ul>"
			+"<ul>Impregnation chance has been halved (to try and balance the fact that impregnation is checked every hour now).</ul>"
			+"<ul>The 'Psychoactive Trip' and 'Addictions' status effects will now refresh every hour while psychoactive or addictive fluid is in one of your orifices.</ul>"
			+"<ul>It is now possible to impregnate/be impregnated even if virility or fertility are less than 0, provided the other partner's opposite attribute is high enough. (Having the 'firing blanks' or 'barren' parks overrides this behaviour, and removes the chance of pregnancy if virility or fertility are less than 0, respectively.)</ul>"
			+"<ul>Added Jam's artwork of Candi.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed a NullPointerException when entering the gambling den in new game. (Pimvgd)</ul>"
			+"<ul>Fixed addiction timer displays showing things like '21:60' instead of '22:00'. (Pimvgd)</ul>"
			+"<ul>Punctuation Fixes for Slime Queen Quest. (WoefulWombat)</ul>"
			+"<ul>Fixed missing explore button for the 'canal end' tiles. (Pimvgd)</ul>"
			+"<ul>Deduplicated colourReplacement in AbstractItemType. (Pimvgd)</ul>"
			+"<ul>Added set bonuses for sun and snowflake jewellery. (rfpnj)</ul>"
			+"<ul>Fixed several issues with incorrect cum, girlcum, and milk types being returned. (Pimvgd)</ul>"
			+"<ul>Added some parser commands that will simplify creating dialogue, by handling second & third person pronouns and verbs in single parser commands. (TadUnlikely)</ul>"
			+"<ul>Improved handling of key codes by converting a long switch statement to a map. (pinnae)</ul>"
			+"<ul>Added a spiked collar clothing item using the modding system. (Irbynx)</ul>"
			+"<ul>Removed glow buttons for skin colours in character creation. (Master of Puppets)</ul>"
			+"<ul>Improved Library button placement. (rfpnj)</ul>"

			+"<li>Other:</li>"
			+"<ul><b>Added:</b> ability to recolour clothing patterns.</ul>"
			+"<ul>Improved right UI panel's 'characters present' section. Hovering over the names/icons in that section now shows appropriate tooltips.</ul>"
			+"<ul>Changed boxers and crotchless briefs to be pulled down instead of shifted aside.</ul>"
			+"<ul>Added tongue length to clothing TF options. Added puffy, ribbing, muscled, and tentacled modifiers to clothing's anus, nipple, face, penis, and vagina TFs.</ul>"
			+"<ul>Slightly improved snowflake nose stud icon.</ul>"
			+"<ul>Synchronised slow clothing TF effects, so that they all fire on the hour. (I did this to fix the issue of having your game interrupted every time you move if you had a lot of TF clothing effects all firing at different times.)</ul>"
			+"<ul>Added 'periwinkle' as a clothing and body part colour.</ul>"
			+"<ul>Using the save/load menu to load enchantments onto clothing now identifies already-enchanted clothing as being suitable (so long as the enchantment is known), while prioritising non-enchanted clothing.</ul>"
			+"<ul>Slightly modified clothing's cow pattern.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed male kimono exposing anus when untied.</ul>"
			+"<ul>Starting a new game with an imported character that was exported with companions or a summoned elemental should now work correctly.</ul>"
			+"<ul>Fixed Lilaya's artwork not being able to be cycled through.</ul>"
			+"<ul>Fixed issue with fetish desires resetting when saving/loading while wearing clothing that grants a fetish.</ul>"
			+"<ul>Fixed bug where offspring of special subspecies wouldn't be of that subspecies. (i.e. Dobermann offspring would be regular dog-morphs.)</ul>"
			+"<ul>You can now set your base fetish desire to any value, but if you have the fetish, it's still always counted as 'love'. The pink outline on fetish desires correctly reflects this change.</ul>"
			+"<ul>Fixed not being able to select items from the right-hand UI element 'Items Present' while the inventory was already open.</ul>"
			+"<ul>Fixed character creation's virginity losses incorrectly referencing 'girlfriend', when it should have been 'boyfriend'.</ul>"
			+"<ul>Deleting saved enchantments now correctly removes them from the list.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed some inventory stacking errors related to clothing names and patterns.</ul>"
			+"<ul>Fixed issue where if an NPC had a piece of patterned clothing, all their clothing would have that pattern name.</ul>"
			+"<ul>Fixed weapons' primary colour not being able to be dyed.</ul>"
			+"<ul>Fixed weapon colours not persisting between saves.</ul>"
			+"<ul>Fixed incorrect names being returned when getting an NPC to use an item.</ul>"
			+"<ul>Fixed bug where wing slots were unable to get tattooed.</ul>"
			+"<ul>Fixed NPCs in the pregnancy roulette game putting their tails into your vagina... (And then afterwards claiming 'There's no way you won't get pregnant from this!')</ul>"
			+"<ul>Fixed game not keeping count of the times the player has had dominant or submissive sex.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.7</h6>"
			+"<li>Engine:</li>"
			+"<ul>Improved scripting support for parsing conditional statements.</ul>"
			+"<ul>Added support for multiple penetrations in a single orifice.</ul>"
			+"<ul>Added support for penetration + penetration or orifice + orifice sex actions. (It was limited to penetration + orifice before.)</ul>"
			+"<ul>Added support for NPC-on-NPC sex actions. (I have a thousand actions to convert to this new format, which will be done over the course of the next few versions.)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added cum storage/regeneration values (the same mechanics as milk). Added an 'expulsion' value to testicles (with associated TF modifier for potions), which determines how much of your stored cum is expelled upon orgasm. (This new mechanic can be toggled off in the content settings.)</ul>"
			+"<ul>Not producing any cum upon orgasm (or by having a cum expulsion value of 0) will now apply the frustrated effect.</ul>"
			
			+"<li>Artwork:</li>"
			+"<ul>Added ChattyNeko's Kate drawing.</ul>"
			+"<ul>Added ChattyNeko's Lumi drawing.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed self breast milking always emptying breasts. (Pimvgd)</ul>"
			+"<ul>Fixed bug in FluidStored constructor that was causing issues with addictive fluids. (Pimvgd)</ul>"
			+"<ul>Fixed incorrect descriptions in mouth orifice status tooltip. (Pimvgd)</ul>"
			+"<ul>Reduced UtilText.parse parsing time by two-thirds for large inputs. (Pimvgd)</ul>"
			+"<ul>Fixed major issue with parsing. (Pimvgd)</ul>"
			+"<ul>Optimised loading. As an example, this cut down the loading time of a large save file by 60%. (Pimvgd)</ul>"
			+"<ul>Fixed bug in FluidRegeneration value. (Pimvgd)</ul>"
			+"<ul>Added cum regeneration mechanics toggle. (Pimvgd)</ul>"
			+"<ul>Cleaned BodyCoveringType via BodyCoveringTemplateFactory. (Pimvgd)</ul>"
			+"<ul>Made more parsing performance improvements. (Pimvgd)</ul>"
			+"<ul>Added 'hair type' to the slime TF menu. (HarelMym)</ul>"
			+"<ul>Fixed ToolTipThread to not create a new thread with every display. (AlacoGit)</ul>"
			+"<ul>Punctuation Fixes for the Gambling Den and fixed a parsing error. (WoefulWombat)</ul>"
			+"<ul>Rearranged inventory slot layout (edited a little bit by Innoxia). (rfpnj)</ul>"
			+"<ul>Added generic orgasm denial actions. (Master of Puppets)</ul>"
			
			+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Butler set. (With boosted butler set effects if you chose the 'bulter' job at the start of the game.)</ul>"
			+"<ul>Added: Butler's Jacket. (Over-torso slot, masculine, butler set.)</ul>"
			+"<ul>Added: Butler's Waistcoat. (Under-torso slot, masculine, butler set.)</ul>"
			+"<ul>Added: Butler's Trousers. (Leg slot, masculine, butler set.)</ul>"
			+"<ul>Added: Butler's Gloves. (Hand slot, masculine, butler set.)</ul>"
			+"<ul>Added: Butler's Shoes. (Feet slot, masculine, butler set.)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Slightly improved map rendering when zoomed out.</ul>"
			+"<ul>Allowed side panels to expand their width when the game window is enlarged.</ul>"
			+"<ul>Added 'current litter size', and 'cum in pussy/ass' taken & received counters for tattoos.</ul>"
			+"<ul>Slightly improved clothing mod documentation (in rental_mommy.svg), and added functionality to use preset lists for concealed slots.</ul>"
			+"<ul>Improved the NPC stats panel (the information beneath their appearance description).</ul>"
			+"<ul>Improved the contacts page in your phone.</ul>"
			+"<ul>Fixed legs and arms always being described as 'slender'. The description is now drawn from your BodyShape (which is based on muscle and body size).</ul>"
			+"<ul>Cum and other fluids are now absorbed into the body through orifices, so even if it's plugged, the amount of fluid inside someone's orifice will gradually diminish over time.</ul>"
			+"<ul>The mother in pregnancy roulette now corectly takes a vixen's virility just before the game. (Epona gets you to take one too.)</ul>"
			+"<ul>You can now collect income/pay debt from slavery in the room management screen.</ul>"
			+"<ul>You can now upgrade a single slave room to a double without having to move the occupant and remove all upgrades first.</ul>"
			+"<ul>Sexual orientation -50% lust damage now affects both damage dealt and received.</ul>"
			+"<ul>Added two content options, 'Anal', and 'Futanari Testicles'. Both are enabled by default. Disabling anal will make all non-unique NPCs spawn in hating anal, while disabling futanari testicles will make all futas spawn in with internal balls.</ul>"
			+"<ul>Added sclera recolouring to the slime TF menu.</ul>"
			+"<ul>Added metallic colours for hair and fur dye.</ul>"
			+"<ul>Improved the spell UI to give an indication as to which spells are 'forbidden'.</ul>"
			+"<ul>Using a pregnancy tester now correctly reveals the father in the pregnancy stats page.</ul>"
			+"<ul>Removed negative physique effects from having full breasts.</ul>"
			+"<ul>Slightly increased size of inventory tooltips.</ul>"
			+"<ul>Added penis colour for slimes.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo and parsing fixes, including instances of your name being incorrectly parsed as 'you'.</ul>"
			+"<ul>Fixed some inconsistencies in Epona's pregnancy roulette scenes.</ul>"
			+"<ul>Fixed bug where enchanting an item of clothing would remove its pattern.</ul>"
			+"<ul>Fixed clothing with negative effects not having a rarity of 'jinxed'.</ul>"
			+"<ul>Fixed npc dildo reveal returning incorrect description.</ul>"
			+"<ul>Dildos no longer generate precum.</ul>"
			+"<ul>Fixed issue where clothing could accidentally get deleted when starting sex scenes with Zaranix and in Gambling Den's pregnancy roulette.</ul>"
			+"<ul>Fixed breeders in pregnancy roulette sometimes starting the game under the effects of promiscuity pills.</ul>"
			+"<ul>Fixed issue where Epona would seemingly forget who you are.</ul>"
			+"<ul>Fixed bug with the 'risk of pregnancy' status effect not re-applying instantly if you weren't pregnant but still had cum in your vagina.</ul>"
			+"<ul>Time now passes while in the Gambling Den (1 minute per tile).</ul>"
			+"<ul>Fixed bug where the map in the library would sometimes not open.</ul>"
			+"<ul>Fixed issue where NPCs would sometimes spawn wearing a dress + skirt.</ul>"
			+"<ul>Fixed issue with modded clothing being tagged to be found in alleyways not actually being found in alleyways.</ul>"
			+"<ul>Fixed issue with modded clothing marked for sale by Kate and Nyan not actually appearing in their inventories.</ul>"
			+"<ul>Fixed slime eye recolouring applying to your entire body.</ul>"
			+"<ul>Being bred in the pregnancy roulette will no longer consume a pregnancy tester from your inventory.</ul>"
			+"<ul>Fixed Zaranix regaining his penile virginity after sex.</ul>"
			+"<ul>Demon alleyway attackers will no longer have their virginities reset after sex.</ul>"
			+"<ul>Fixed bug where slimes would always spawn in with a load of jewellery in their inventory.</ul>"
			+"<ul>Fixed issue where Nyan's clothing might sometimes not load correctly.</ul>"
			+"<ul>Fixed drinking milk giving the 'Cummy Meal' status effect.</ul>"
			+"<ul>Fixed slimes drinking milk causing impregnation check to trigger.</ul>"
			+"<ul>Fixed bug where even if your cum production was 0, you'd still sometimes dirty your underwear when orgasming.</ul>"
			+"<ul>Fixed minor typo in inventory's 'Take (All)' tooltip.</ul>"
			+"<ul>Fixed bug where the repeat action tab in sex could be used to perform actions that weren't meant to be available in the current position.</ul>"
			+"<ul>Makeup, body hair, and pupil colour should now all save correctly.</ul>"
			+"<ul>Fixed Ralph's discount never being higher than 33%, even if the game was saying it was higher.</ul>"
			+"<ul>Sending a slave to Kate is now disabled if you've never actually met Kate.</ul>"
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
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer(), CharacterImportSetting.NO_PREGNANCY, CharacterImportSetting.NO_COMPANIONS, CharacterImportSetting.NO_ELEMENTAL));
				
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
