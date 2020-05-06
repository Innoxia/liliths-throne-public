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

/**
 * @since 0.1.0
 * @version 0.3.7.5
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sex;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Innoxia";
	public static final String GAME_NAME = "Lilith's Throne";
	public static final String VERSION_NUMBER = "0.3.7.5";
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
			+ "I've got a few bugs and other minor things sorted out for this preview release, as well as getting the framework for Natalya's content added."
			+ " I ended up once again adding more than I planned for this character, so all of the dialogue for her mini quest is placeholders at the moment."
			+ " With this in mind, I advise only playing it if you want to see the sort of content it involves, and waiting to do a proper playthrough of it until the next release, when all of the dialogue will be filled in."
		+ "</p>"
			
		+ "<p>"
			+ "I expect the next update t be the full release of v0.3.8, which should be out around Wednesday, 13th May."
			+ " I had originally planned for it to be out on Friday, but I really need some extra days in which to get things finished off."
		+ "</p>"
		
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "<br/>"

		+ "<list>"
		+ "<h6>v0.3.7.5</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Refactored and cleaned up code handling forced transformations. This fixed a few issues with forced transformations, such as your leg configuration never being transformed into that of a centaur. (PR#1255 by Stadler76)</ul>"
			+"<ul>Fixed bug where the phone menu wouldn't work in the SHopping Arcade. (PR#1328 by zR1OQicz)</ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Moved all eye clothing and final hard-coded weapons into external res folders.</ul>"
			+"<ul>Outfit files no longer require clothing conditionals to be numbered, and can be called anything, so long as they start with 'clothingConditional' or 'cond'. (e.g. 'clothingConditionalMelee' or 'condDress' are now both valid conditional tags.)</ul>"
			+"<ul>You can now define multiple weapon types to be drawn from at random in one weapon element in outfit files (see mugger files for examples).</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added framework of Natalya's content. It's all still placeholder dialogue at the moment, so only play through if you want to see the sort of content it has. The dialogue will be completely filled in for v0.3.8.</ul>"
			+"<ul>Random storm attackers will no longer force transformation potions on you if you lose to them. (As they are non-persistent, it didn't make much sense to have them apply partial transformations to you when they'd never get the chance to complete them.)</ul>"
			+"<ul>You can no longer pay off demon attackers in dark alleyway tiles (as they are only interested in sex, not money).</ul>"
			+"<ul>Characters can now have a 'heavy layer' of lipstick applied, which causes lipstick marks to be left on kissed body parts during sex. There is a content option to disable this feature in the 'sex' category.</ul>"
			
			+"<li>Balance:</li>"
			+"<ul>Dominion alleyway muggers will now only spawn with physical damage weapons, and will no longer spawn with pistol crossbows.</ul>"
			+"<ul>Submission tunnel attackers can now spawn with kerambits, opaque demonstones, and rough arcane feathers.</ul>"
			+"<ul>All imps in Submission now wear rags and carry weapons, not just the ones in the imp fortress tunnels.</ul>"
			+"<ul>Dominion dark alleyway demons now have their own outfit files and spawn with weapons.</ul>"
			+"<ul>Increased natural physical shielding of all non-Enforcer clothing items.</ul>"
			+"<ul>Buffed damage of most weapons (swords in particular have had their damage increased a lot). Increased value of most weapons.</ul>"
			+"<ul>Submission enemies now demand 500 flames instead of 250.</ul>"
			+"<ul>The +15 physical damage on the 'Daisho' set bonus has been replaced by +15 melee weapon damage.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added clothing: 'Filly choker' (neck slot, feminine). Can only be obtained during Natalya's content (or spawned from the debug menu).</ul>"
			+"<ul>Added item: 'Arcane makeup set'. Can be bought from Ralph or Kate, and when used, opens the makeup application screen.</ul>"
			+"<ul>Renamed old Enforcer's shirt, shorts, and skirt to 'fancy-dress' items, to make it clearer that they aren't official Enforcer uniform items.</ul>"
			+"<ul>Added a sheathed icon for the Zweihander.</ul>"
			+"<ul>Added recolouring options for the shortbow.</ul>"
			+"<ul>'Amber's bitch collar' now spawns with the servitude enchantment (so you can't self-transform or unjinx it while worn).</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Reduced reindeer and rabbit tail lengths from 15% and 10% of body height, respectively, to 5% and 7.5%.</ul>"
			+"<ul>Added an option under the 'Bodies' tab in the content settings to set how much smaller (or bigger) trap penises should be than normal.</ul>"
			+"<ul>The debug menu now enables you to change the number of your tails if you are a youko (which is also only possible via the debug menu at the moment).</ul>"
			+"<ul>Improved clothing/weapon/item tooltips in debug menu's item view screens.</ul>"
			+"<ul>Offspring encountered will now spawn with correct clothing for their background/race/location.</ul>"
			+"<ul>Increased number of essences NPCs will spawn with when they spawn with a weapon which requires essences to fire.</ul>"
			+"<ul>Added a 'Wiki' button to the main menu, which, like the github and blog buttons, opens a page in your default web-browser to display the game's wiki.</ul>"
			+"<ul>Spell efficiency is now listed under combat stats instead of miscellaneous stats in the phone's stats menu.</ul>"
			+"<ul>Improved demon alleyway encounter dialogue.</ul>"
			+"<ul>Added 'penile deflowerments' as a tattoo counter.</ul>"
			+"<ul>Added crit requirements to spell tooltips in the phone menu.</ul>"
			+"<ul>Characters who have their cum or girlcum milked in a milking room are now considered to have orgasmed, and so have their 'Frustrated' or 'Pent-up' status effects removed.</ul>"
			+"<ul>Half-demons with no human body parts are now counted as having a race-stage of 'greater' instead of 'lesser'.</ul>"
			+"<ul>Made some very minor improvements and fixes to Helena's date content.</ul>"
			+"<ul>Added generic 'leathery' wings as a counterpart to generic 'feathered' wings.</ul>"
			+"<ul>Demons now have a 20% chance to spawn with 4 testicles instead of 2, instead of 100%.</ul>"
			+"<ul>Slightly adjusted clothing slot positions in the UI (by swapping mouth-neck slots and moving wrists-hands-stomach slots around).</ul>"
			+"<ul>Increased Ralph's penis size from 20cm to 30cm.</ul>"
			+"<ul>Added 'makeup' screen to the debug menu's self-transformation menu.</ul>"
			+"<ul>The primary colour requirement for being classed as a Dobermann-morph is now either 'black' or 'pitch black', instead of just 'black'.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing and typo fixes.</ul>"
			+"<ul>Fixed some incorrect paragraph formatting in some transformation descriptions.</ul>"
			+"<ul>Fixed bug where imps and demons would sometimes say they were trying to forcibly transform you into a demon, even though that's not possible.</ul>"
			+"<ul>Fixed bug where imp gangs in Submission would try to force-feed you transformation potions even if all the effects did nothing.</ul>"
			+"<ul>Fixed bug where masturbate action would always be greyed-out.</ul>"
			+"<ul>Fixed bug where a saved game would fail to load if it was saved in a state where an item of clean, sealed clothing was about to be dirtied on the next turn. (This was most apparent in the autosave immediately after losing to any of the imp fortress bosses.)</ul>"
			+"<ul>NPCs will no longer refer to fetish endowment potions by whatever name you've given it (as they logically wouldn't know what you've called the potion).</ul>"
			+"<ul>Fixed missing descriptions in quick sex for fingering someone's ass.</ul>"
			+"<ul>Fixed bug where 'Orgasmic Level Drain' was not working for the player character.</ul>"
			+"<ul>Fixed issue where NPCs would sometimes reset their combat moves to the basic starter moves without being told to do so.</ul>"
			+"<ul>Fixed bug in slave management where navigating from any of a slave's management screens to a sex scene would cause a background error to be thrown.</ul>"
			+"<ul>Fixed bug where slaves created using Helena's custom slave designer would not be able to have their age changed.</ul>"
			+"<ul>Fixed bug where NPC birthdays would end up being incorrect after saving/loading a few times. As part of fixing this bug, all NPC birthdays have been reset to what should be accurate values.</ul>"
			+"<ul>Fixed bug where newly-spawned NPCs would be a lot older than they should have been.</ul>"
			+"<ul>Fixed bug where setting forced transformations to 0% would still result in being forcibly transformed if you had forced fetishes turned on as well.</ul>"
			+"<ul>Fixed bug where if you tried to send a companion home while you had an elemental summoned you'd get stuck in the phone menu.</ul>"
			+"<ul>Fixed tails being treated as plural in parsing, even if the character with the tail only had one.</ul>"
			+"<ul>Fixed bug where if you failed to be chosen by Scarlett after acting as her servant (in Helena's nest after completing Helena's romance quest), you were placed in Scarlett's room and got stuck in Helena's apartment.</ul>"
			+"<ul>Fixed missing descriptions for receiving a blowjob from Helena in her store's back room.</ul>"
			+"<ul>Fixed rare bug during Lyssieth's demon transformation sex scene where the game could freeze when she orgasms. (Was being triggered by having her orgasm while fucking her and giving her a handjob at the same time.)</ul>"
			+"<ul>Characters no longer lose their penile virginity if they penetrate someone using a dildo in quick sex.</ul>"
			+"<ul>Being transformed into a demon now sets your horn length to 10cm, instead of leaving it as 0cm.</ul>"
			+"<ul>Fixed bug where actions outside of sex which had perk, fetish, race, or femininity requirements would not be available to choose if you had the 'Sex action bypass' content option disabled.</ul>"
			+"<ul>Fixed bug where speech would not parse correctly in tooltip descriptions.</ul>"
			+"<ul>Fixed bug where outfits files marked as being 'feminine' would sometimes be applied to masculine characters.</ul>"
			+"<ul>Fixed bug where randomly-generated NPCs would sometimes have a date of conception many years before their birthday.</ul>"
			+"<ul>Fixed issue where NPC forced-TF potion generation would be based on a slightly different body preference each time you met them.</ul>"
			+"<ul>Choosing 'Unequip all' during character creation now correctly puts the clothing into storage, not your inventory.</ul>"
			+"<ul>Fixed bug where you could clone earrings and other piercing clothing during character creation.</ul>"
			+"<ul>Fixed bug where horn type wasn't being factored in when calculating a character's race (so characters with just a unicorn horn, for example were not being identified as being a unicorn).</ul>"
			+"<ul>Fixed bug where if non-con content was turned off, you couldn't manually milk your slaves who were being milked in a milking room.</ul>"
			+"<ul>Taking a shower in Helena's room after sex will no longer clean all clothes in your inventory.</ul>"
			+"<ul>Kate now correctly sells items marked as being 'SOLD_BY_KATE'.</ul>"
		+"</list>"
			
			+ "<br/>"

		+ "<list>"
		+ "<h6>v0.3.7.3</h6>"
			+"<li>Engine:</li>"
			+"<ul>Converted WorldType enum to classes.</ul>"
			+"<ul>Moved all Harpy Nests dialogue out into external .txt file.</ul>"
			+"<ul>Naming artwork files with 'penis' or 'vagina' in them will now remove those images from in-game display if that character does have a penis or vagina, respectively. (e.g. An artwork image named 'npc_penis' would only be displayed in-game if the character has a penis.)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added the variation to Scarlett's content in Helena's nest for after you've completed Helena's romance quest.</ul>"
			+"<ul>Filled in Scarlett's missing sex dialogue for if you were fucked by her vaginally or orally up in Helena's nest (prior to completing Helena's romance quest).</ul>"
			+"<ul>You can no longer encounter NPCs in alleyways/canals/harpy nests during an arcane storm, as they all go into hiding until it's passed.</ul>"
			+"<ul>You can now find items, clothing, and weapons in random encounters on Dominion canal tiles just like you can in alleyway tiles.</ul>"
			+"<ul>Exploring tiles (alleyways, canals, Submission tunnels, Harpy Nest walkways, etc.) now takes 30 minutes instead of 0 minutes.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Characters now use simpler descriptions of orifice capacities when talking. (e.g. Instead of saying 'somewhat tight, wet pussy', an NPC will now say 'tight, wet pussy'.)</ul>"
			+"<ul>NPCs are now only added to your contacts list once you discover their name (so entering Slaver Alley no longer adds 'Enforcer' to your contacts list until you talk to him and discover his name).</ul>"
			+"<ul>Reduced chance of random characters being virgins from 10% to 5%.</ul>"
			+"<ul>Maximum 'resting lust' is now capped at 80, so highly corrupt NPCs will no longer get stuck at 100 lust during an arcane storm, which was allowing you to instantly beat them over and over again.</ul>"
			+"<ul>Fast-travel via flying is now restricted in most indoor areas.</ul>"
			+"<ul>NPCs present in canal tiles now have the same 'Character lurking in this area' overview text as alleyway tiles.</ul>"
			+"<ul>Made some slight improvements to dialogue in Harpy Nests.</ul>"
			+"<ul>Added reference to horse and centaur-pulled carts to Dominion's boulevard descriptions.</ul>"
			+"<ul>Scarlett's naked in-game images drawn by Jam will now correctly display the penis/vagina variations based on whether Scarlett has a penis or vagina.</ul>"
			+"<ul>Added action in debug menu (in 'stats' tab) to give your companions 500xp.</ul>"
			+"<ul>If Scarlett likes you, she will no longer allow random harpies to impregnate her (when she has sex with other harpies after gaining a vagina).</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo, parsing, and grammatical fixes.</ul>"
			+"<ul>Fixed parsing bug in imp tunnels.</ul>"
			+"<ul>Fixed some parsing bugs in Helena's new content.</ul>"
			+"<ul>Reset Loppy's age to 22, as it was accidentally set to a far higher value in a previous version.</ul>"
			+"<ul>Fixed issue with NPCs' birth dates not matching up with the lore of all characters aging by 18 years after birth.</ul>"
			+"<ul>Fixed issue where obedience description could reveal the gender of concealed characters.</ul>"
			+"<ul>Fixed bug where knowing what an NPCs' ass looks like would also reveal their anus to you, even if it was still concealed by clothing.</ul>"
			+"<ul>Fixed incorrect naming of the elixirs which spawn in imps' inventories in Submission's tunnels.</ul>"
			+"<ul>The 'too feminine'/'too masculine' clothing status effects now display correct tooltip descriptions when applied to NPCs.</ul>"
			+"<ul>Fixed bug where the status effect 'Broken Virgin' (lost virginity, no hymen) would be active at the same time as the 'Pure 'Virgin' (lost virginity, regrown hymen) status effect.</ul>"
			+"<ul>The 'Sex action bypass' content setting should now work correctly. (i.e. When this setting is turned off, sex actions which would result in your character gaining corruption are disabled.)</ul>"
			+"<ul>The 'Calming suggestion' and 'Lustful suggestion' sex actions are now correctly greyed-out when not available.</ul>"
			+"<ul>Fixed bug where Helena would be described as not being in her nest even if she was actually there.</ul>"
			+"<ul>Fixed throat plasticity self-transformation buttons not working.</ul>"
			+"<ul>Fixed bug where the 'safe alleyways' description was being shown for all alleyway tiles, not just the one to the direct north of Slaver Alley.</ul>"
			+"<ul>Fixed bug where prostitutes were 4 times more likely than other characters to spawn with the 'pure virgin' fetish, instead of the other way around. </ul>"
			+"<ul>Fixed issue where clicking on a slav's name/race icon to view them while in their slave management screen would cause a background error to be thrown when exiting the character view screen. This was resulting in game-freezes or displaying previous encounter dialogue.</ul>"
			+"<ul>Fixed some incorrect sex action availabilities in the face-to-wall position when there is a sze-difference between the two participants.</ul>"
			+"<ul>Fixed bug where giving a gift to Helena would prevent you from being able to give her another gift the next time you took her out on a date.</ul>"
			+"<ul>Fixed rare issue in Helena's 'romantic' sex scene where you'd be forced to repeat her oral scene after completing it once.</ul>"
			+"<ul>Fixed tooltip description of your arcane attribute describing a new player's starting value of 20 as being weaker than it was when you first entered this world.</ul>"
			+"<ul>Fixed bug where viewing an NPC's weapon would not work and throw a background error if your inventory was full.</ul>"
			+"<ul>Fixed bug where NPCs who failed to get pregnant would have their name stuck in the pregnancy stats screen as being 'Ready for birthing'.</ul>"
			+"<ul>Fixed bug where imp offspring could spawn in Dominion's alleyway tiles.</ul>"
			+"<ul>Fixed issue where dirty talk during nipple kissing would make reference to the character's nipples being penetrated, even if they weren't fuckable.</ul>"
			+"<ul>Fixed some issues with the flow and restoration of dialogue in Nyan's and Helena's gift-giving scenes.</ul>"
			+"<ul>The TF potion which you can get Scarlett to drink on her lunch break now increases her breast size by +1/+2 cup-size, instead of to C/D-cups (as she may have already had her cup size increase via pregnancy).</ul>"
			+"<ul>Scarlett will no longer remove clothing or start other sex actions in her fingering/handjob scene in the cafe during her lunch break, or during her 'sleepover' sex scene near the end of Helena's romance quest (to stop her from performing unintended actions).</ul>"
			+"<ul>Fixed issue with dialogue flow in Candi's post-sex scene.</ul>"
			+"<ul>Fixed bug in Helena's romance quest where (if you'd previously set her free) Scarlett would stay in Helena's nest after you've told her that Helena wants her.</ul>"
			+"<ul>Fixed Scarlett's perk map not being able to be reset while she was your slave.</ul>"
			+"<ul>Fixed related issue where unique NPCs would always have their perk maps reset to their starting values upon every game load, even if they had been legitimately modified.</ul>"
			+"<ul>Fixed bug where changing a character's image could cause you to get stuck in their character view screen.</ul>"
			+"<ul>Fixed bug where characters could summon a fire elemental using health even if they didn't have that ability unlocked (by knowing at least 3 fire spells).</ul>"
			+"<ul>Added error handling for an instance of incorrectly configured clothing mods causing background errors to be thrown.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.7.2</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added Helena's date content. (Unlocked after completing her romance quest, and requires you to visit her in her store between 17:00-21:00 on a Friday.)</ul>"
	
			+"<li>Other:</li>"
			+"<ul>The 'Unequip all' action in the inventory overview no longer unequips weapons.</ul>"
			+"<ul>Added 'offspring fathered' as a tattoo counter type.</ul>"
			+"<ul>Increased the installation cost of room service from 100 to 500 flames.</ul>"
			+"<ul>Added 'Dog Bowls' as an affection-negative, obedience-positive room upgrade counterpart to the affection-positive, obedience-negative 'Room Service'.</ul>"
			+"<ul>The 'XML test' in the debug's parser menu now outputs a warning at the top of the parsed dialogues to let you know if any tags are being repeated.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>Orifices with the 'internally-muscled' modifier are now immune to the negative 'too loose' effect when being penetrated, no matter how slender the insertion nor how loose its capacity might be.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixed some grammatical errors in displacing/replacing clothing descriptions.</ul>"
			+"<ul>Fixed bug where sealed clothing which was unsealed and removed during sex would be duplicated in the wearer's inventory when sex ended.</ul>"
			+"<ul>Unjinxing clothing during sex will no longer return an incorrect description or just 'null'.</ul>"
			+"<ul>Fixed fluffy tails not being described as being fluffy in character view screen.</ul>"
			+"<ul>Fixed bug where your clothing unequipped by NPCs during sex would always cause an instance of that clothing to be removed from your inventory. (e.g. If an NPC unequipped your white bra, and you had a second white bra in your inventory, the bra in your inventory was being deleted after sex.)</ul>"
			+"<ul>Fixed bug where clothing with a secondary or tertiary colour of 'black' would have that colour reset to the first available colour (usually white) every time your game was loaded.</ul>"
			+"<ul>Fixed issue with player pregnancy not being able to be resolved while being Vengar's captive.</ul>"
			+"<ul>Fixed incorrect tooltip description for 'Quick shower' in your room.</ul>"
		+"</list>"

		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.7.1</h6>"
			+"<li>Other:</li>"
			+"<ul>Added support for throat capacity, elasticity, plasticity, and wetness transformations from clothing. Also added depth transformations from clothing for all orifices.</ul>"
			+"<ul>The chance for a client to just grope your prostitute slaves has been reduced from 20% to 5%.</ul>"
			+"<ul>Added 'offspring birthed' and 'oral deflowerments' as tattoo counter types.</ul>"
			+"<ul>Tattoos can now be enchanted with 'major' attributes, such as physique, arcane, health, etc.</ul>"
			+"<ul>Slime and imp spawns in Submission's tunnels are no longer affected by user preferences (as they need to spawn for story/quest reasons).</ul>"
			+"<ul>The feathered wing transformation, which was previously exclusive to horse-morphs (to make pegasi), are now available for all non-demonic and non-angelic races. (i.e. Any normal race's TF food item can now be enchanted to give feathered back wings.)</ul>"
			+"<ul>Arousal gains from masochist/sadist special arousal effects are now limited to 10 per attack instead of 25.</ul>"
			+"<ul>Added minor description of centaur-pulled carts being present throughout Dominion.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>Added: Double cunnilingus support (i.e. two characters performing cunnilingus on one character at the same time).</ul>"
			+"<ul>Fixed issue where characters in the 'kneeling oral' slots in the 'sitting' position could have sex/tribbing with one another.</ul>"
			+"<ul>Characters sitting down in the 'sitting' position can now interact with characters sitting next to them.</ul>"
			+"<ul>Random NPCs whose name you do not know (and therefore they do not know your name) will no longer call out your name during sex.</ul>"
			+"<ul>In quick sex which involves multiple orgasms, characters will now regenerate 5 minutes' worth of cum regeneration between orgasms.</ul>"
			+"<ul>Added stomach bulge text for too large penetrations inside of size queens and masochists.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixed issue with imp and half-demon offspring not being encountered in Submission's tunnels.</ul>"
			+"<ul>Fixed minor bug with incorrect colour parsing in offspring birthed list.</ul>"
			+"<ul>Fixed bug where all femininity changes would be described as being masculine, even if they made the target more feminine.</ul>"
			+"<ul>Fixed incorrect opening times being displayed in Enforcer HQ dialogue.</ul>"
			+"<ul>Fixed Helena's description referring to her as 'Alexa'.</ul>"
			+"<ul>Fixed bug where penis reveal dialogue was parsing incorrectly.</ul>"
			+"<ul>Fixed personality trait removals from the hypno watch not being described correctly.</ul>"
			+"<ul>Tattoo counters for sub/dom sex should now correctly increment for slaves who have sex while working as a prostitute or set as public use in the stocks.</ul>"
			+"<ul>Fixed issue where sex experience buttons in character creation wouldn't work.</ul>"
			+"<ul>Fixed some minor issues with 'dirty talk' parsing during sex.</ul>"
			+"<ul>Characters who are visibly pregnant will no longer be described as having their stomach bulge out when their vagina or ass is penetrated too deeply.</ul>"
			+"<ul>Fixed bug where job recommendations you've made to friendly occupants would be reset upon loading your game.</ul>"
			+"<ul>Fixed clothing pattern dye colours not displaying in the dye menu.</ul>"
			+"<ul>Fixed bug where opening/closing the main menu screen while managing a room or people in Lilaya's house would return you to a previous, unrelated scene.</ul>"
			+"<ul>NPCs in sex will no longer keep on offering you pills after you've refused them the first time.</ul>"
			+"<ul>Fixed bug where all management options would be disabled in the scene after having sex with a slave.</ul>"
			+"<ul>Fixed bug in combat where enemies hit by sadists would have their arousal increased, instead of the sadistic attacker.</ul>"
			+"<ul>Basic earrings are now considered to be unisex again.</ul>"
		+"</list>"
			
		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.7</h6>"
			+"<li>Engine:</li>"
			+"<ul>Converted Colour Enum into a 'PresetColour' class, which contains static Colour classes.</ul>"
			+"<ul>Added parsing commands for toy inserted into character's vagina.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added first section of Helena's romance quest.</ul>"
			+"<ul>The alleyway tile immediately to the north of Slaver Alley is now a 'safe' alleyway tile. Any NPC which was present in this tile has been moved to one of two new back alley tiles to the south-west of Dominion.</ul>"
			+"<ul>You can now enter the cafes in Slaver Alley, and the descriptions of the inaccessible slaver stores have been slightly altered.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added: 'Velvet choker' (androgynous, neck slot, sold by Nyan).</ul>"
			+"<ul>Added: 'Dangle chain earrings' (feminine, ear piercing slot, sold by Kate).</ul>"
			+"<ul>Added: 'Plunge-neck clubbing dress' (feminine, torso slot, sold by Nyan).</ul>"
			+"<ul>Added: 'Strapless bra' (feminine, chest slot, sold by Nyan).</ul>"
			+"<ul>Added: 'Diamond necklace' (feminine, neck slot, sold by Nyan).</ul>"
			+"<ul>Added item tag 'CHOKER_SNAP', which causes the item worn to snap when the wearer's throat bulges too much during sex. (Applied to the new 'velvet choker' item.)</ul>"
			+"<ul>'Angel's Tears' now reduces the drinker's lust to their resting lust level.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>The capacity of the 'throat' orifice now behaves in the same manner as other orifices, and will stretch/recover based on its plasticity/elasticity. (The references to 'blowjob ability' in character's description screens will be restored later on once I add effects for fetish experience.)</ul>"
			+"<ul>Characters can now receive paizuri from the character they are sitting on while in the 'cowgirl' position (provided that they aren't riding that person's cock).</ul>"
			+"<ul>Characters performing oral in missionary position (with their face between their partner's legs) can now force their partner to cum inside them.</ul>"
			+"<ul>NPCs who are fucking someone (using their penis) will no longer start fingering their own anus/vagina.</ul>"
			+"<ul>'Orgasm cum lock' actions are no longer available if the sex manager is setting a special pullout condition for the other NPC. (This will only affect a few special sex scenes.)</ul>"
			+"<ul>The character lying underneath in the 69 position can now perform anilingus on their partner who's on top.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added 'grey-green' as a natural iris colour.</ul>"
			+"<ul>Randomly-spawned fox-morphs should now correctly have orange-white, tan-white, grey-white, or black fur.</ul>"
			+"<ul>Added penis diameter to characters' body overview tooltip.</ul>"
			+"<ul>The 'dirty clothing' and 'dirty body' status effects now give +5 corruption each, instead of -2 arcane.</ul>"
			+"<ul>Horse-morphs with anthro horse-morph faces now spawn in with 'natural' hair styles (representing a mane) if they are not feminine. If feminine, they have a 50% chance of having a mane.</ul>"
			+"<ul>Randomly-selected hair styles are now additionally filtered by femininity. (This does not affect anything other than random NPC spawns.)</ul>"
			+"<ul>Debug menu's '+50 essences' action is now '+1000 essences'.</ul>"
			+"<ul>Slightly improved formatting of furry preferences screen.</ul>"
			+"<ul>Reduced values of racial food items.</ul>"
			+"<ul>Added button to randomise name in slave renaming management screen.</ul>"
			+"<ul>Added throat capacity, depth, elasticity, and plasticity to self-transformation menu.</ul>"
			+"<ul>Improved the UI for changing covering colours, as well as for the other options in Kate's shop.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixed incorrectly displaying 'dense crowds' as being present on Dominion's boulevard tiles during an arcane storm.</ul>"
			+"<ul>Fixed incorrect name formatting for clothing which had a colour pattern.</ul>"
			+"<ul>Performing/receiving paizuri will no longer output an error message to the error.log file.</ul>"
			+"<ul>Fixed UI bug where the inventory overview panel's bottom border would grow when clicking on clothing during combat or sex.</ul>"
			+"<ul>The ordering of the event log is no longer reversed when loading a game.</ul>"
			+"<ul>Losing to Sean in his challenge now correctly moves you over to the public stocks tile.</ul>"
			+"<ul>Fixed bug where knotting descriptions would sometimes incorrectly describe a character's tail as being the penetration type that had a knot instead of their penis.</ul>"
			+"<ul>Fixed bug where you couldn't offer your ass to Scarlett when she was attracted to you, but you could if she wasn't attracted to you. Also fixed related bug where 'Offer ass' would be displayed in every response slot when it was available.</ul>"
			+"<ul>Scarlett's sex scene in her nest is no longer treated as being 'public sex', and she will now correctly want to continue fucking your ass once started.</ul>"
			+"<ul>Characters in unique sex scenes will no longer move into positions in which they can't satisfy their sex desires.</ul>"
			+"<ul>Fixed minor bug where setting penis/vagina sex experience in character creation, then going back to remove your vagina/penis wouldn't reset the underlying sex experience.</ul>"
			+"<ul>Fixed bug where some coloured words would not be capitalised as intended.</ul>"
		+"</list>"
	;
	
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
	
	public static boolean isQuickSaveAvailable() {
		return Main.game.isStarted()
				&& !Main.game.isInCombat()
				&& !Main.game.isInSex()
				&& Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL
				&& Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogue(false));
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
			
		} else if (!Main.game.isStarted() || !Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogue(false))) {
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
			
		} else if (!Main.game.isStarted() || !Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogue(false))) {
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
		return Main.game.isStarted() && Main.game.getSavedDialogueNode() == Main.game.getDefaultDialogue(false);
	}
	
	public static void saveGame(String name, boolean allowOverwrite) {
		if (name.length()==0) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name too short!");
			return;
		}
		if (name.length() > 32) {
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
				Main.game.setPlayer(CharacterUtils.startLoadingCharacterFromXML());
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer(),
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
