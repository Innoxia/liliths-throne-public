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

/**
 * @since 0.1.0
 * @version 0.3.9.3
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sex;
	public static Combat combat;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Innoxia";
	public static final String GAME_NAME = "Lilith's Throne";
	public static final String VERSION_NUMBER = "0.3.9.3";
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
			+ "This is a hotfix for v0.3.9, in which there were a couple of major bugs and a whole list of minor ones."
			+ " With this version, the game should be in a stable state once again, with the character overview, perk trees, and slave jobs all working correctly."
		+ "</p>"
			
		+ "<p>"
			+ "I've also re-enabled the ability to start Axel's quest (the dreaded Rat Warrens quest), as most of the content for it has now been finished."
			+ " There are still some placeholders in it (including all of the post-quest Vengar and Murk content in the Gambling Den), and I haven't had time to thoroughly test it, but it *should* be entirely functional."
			+ " I will get it 100% finished and polished for the next version, so if you'd like to wait until it's completely finished before trying it out, then you'll only have to wait for the next update."
		+ "</p>"
			
		+ "<p>"
			+ "As part of the Rat Warrens content, I've included the game's first 'bad end'."
			+ " I'm only planning on adding these into the game where I think that it would make no narrative sense for your character to get out of their situation."
		+ "</p>"
		
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.9.1</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Fixed issue where slime covering colour would be forgotten and randomised every time you loaded the game. (PR#1313 by AceXP)</ul>"
			+"<ul>Fixed parsing issues in potion application description. (PR#1343 by Eliria)</ul>"
			+"<ul>Added more colours to the test t-shirt item and added new ColourListPreset for testing purposes. (PR#1376 by DSG)</ul>"
			+"<ul>Fixed minor parsing issues and typos in dialogues. (PR#1375 by AceXP)</ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Removed TFEssence Enum (so accessing essence-modifying methods is now possible via use of the parser).</ul>"
			+"<ul>Added ways to define 'on hit' and 'on critical hit' effects to weapons in their xml files, as well as a way to define extra effects to be displayed in their tooltip. You can see an example of how to use this in 'res/weapons/innoxia/dagger/dagger.xml'</ul>"
			+"<ul>Moved all reindeer overseer dialogue out into a new external xml file.</ul>"
			+"<ul>Refactored Race Enum into an abstract class.</ul>"
			
			+"<li>Rat Warrens:</li>"
			+"<ul>Mostly completed Rat Warrens quest. There are just a few placeholders here and there and I haven't yet done thorough testing of it. Also, the post-quest Murk & Vengar content (in the Gambling Den) is still placeholders.</ul>"
			+"<ul>Expanded Murk's captivity content and made it a little more linear. Also added a bad end state at the end of it.</ul>"
			+"<ul>Losing to Vengar now leads into the same Murk content as losing to one of the generic gang fights.</ul>"
			+"<ul>Resolving Axel's quest by cooperating with Vengar now leads into the end result as fighting him.</ul>"
			+"<ul>Companions now always escape after losing to any fight which leads into Murk or Vengar captivity.</ul>"
			+"<ul>Reduced size of hostile rat gangs from 6 to 4.</ul>"
			+"<ul>Fixed issue where if non-consent content was disabled, the Rat Warrens would enter into a bugged state if you lost combat and were thrown out.</ul>"
			+"<ul>Slightly altered and improved descriptions of Murk and his milking storage and milking rooms.</ul>"
			+"<ul>Murk now only rents his milkers out between 14:00 and 22:00.</ul>"
			+"<ul>Slightly altered Murk's and his milkers' bodies & fetishes.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Characters who have a negative desire towards the impregnation fetish now have a low priority to creampie the vagina of characters who are not visibly pregnant.</ul>"
			+"<ul>Fixed issue where NPCs were not using items & condoms properly.</ul>"
			+"<ul>NPCs with the 'Pure virgin' fetish will no longer beg to have their virginity taken during sex.</ul>"
			+"<ul>NPCs will no longer get stuck trying to repeatedly remove sealed bras.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added a silly-mode 'BONK!' baseball bat, sold by Vicky (she'll only be selling it if silly mode is on when she restocks at midnight each day).</ul>"
			+"<ul>Increased default girth for the 'short' cat-morph tail from 'narrow' to 'average'.</ul>"
			+"<ul>Slightly improved the reindeer overseer dialogue, and fixed the issue where you could have sex with them even if they weren't attracted to you.</ul>"
			+"<ul>Slightly improved the 'bro' speech modifier.</ul>"
			+"<ul>Increased cum storage from 65 to 150 and 250 for Vicky and Ralph, respectively.</ul>"
			+"<ul>Imps and alpha-imps in Submission's tunnels are now far more likely to have the 'slovenly' personality modifier.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Numerous parsing and typo fixes.</ul>"
			+"<ul>Fixed major bug where accessing the slave job assignment menu would cause an error reading: 'Error: getContent() method is throwing an exception'</ul>"
			+"<ul>Fixed major bug where selfie and perk screens would not open if you had learned an elemental spell but had not yet summoned your elemental.</ul>"
			+"<ul>Vaginal urethra depth no longer resets to the default value every time you import import a character or load a saved game.</ul>"
			+"<ul>NPCs who have a negative desire towards others' cocks or pussies no longer positively react to another characters' cock/pussy being revealed. </ul>"
			+"<ul>Added the 'all fours' slot to the positioning menu in the 'stocks'/'milking stall' sex position.</ul>"
			+"<ul>Fixed issue where newly-spawned muggers/prostitutes in Dominion could throw background errors and not have the correct subspecies assigned to them.</ul>"
			+"<ul>Fixed bug where you could access the 'sex' and 'manage' actions of a friendly occupant in their apartment even when they weren't actually present in the tile.</ul>"
			+"<ul>Fixed several instances where after exiting a friendly occupant's apartment, their stats & inventory would continue to be displayed in the right-hand rendering column, which would throw background errors when trying to interact with it.</ul>"
			+"<ul>Fixed incorrect bread roll description.</ul>"
			+"<ul>Fixed issue where having more than one tail would cause all body parts to be parsed as though they were plural.</ul>"
			+"<ul>Foot-to-groin sex actions have been restored to the 'reverse face-sitting' position.</ul>"
			+"<ul>Fixed incorrect overview description of the milking stall sex position for if someone was in the 'all fours' slot.</ul>"
			+"<ul>Fixed issues with random NPCs somehow knowing your name during sex when performing forced-creampie actions and when giving you pills.</ul>"
			+"<ul>Desryth (the Father's Day incubus) is now correctly tagged as being added to your contacts screen after encountering him.</ul>"
			+"<ul>Fetish modification effects from the item 'Mystery Kink' will no longer include anal, lactation, incest, or foot fetishes if you have the associated content setting turned off.</ul>"
			+"<ul>Fixed (more) issues with the 'bedroom' slave job, where they would wake you up when they hadn't even arrived in your bedroom yet, and have wakeup-sex with you even after having left.</ul>"
			+"<ul>Fixed 'Elemental damage' perk referring specifically to arcane elementals instead of elementals in general.</ul>"
			+"<ul>Fixed bug where the room list in the 'office' room upgrade's Occupancy ledger would not work.</ul>"
			+"<ul>Fixed bug where slaves could stack up in your bedroom at the time when they were meant to arrive/leave.</ul>"
			+"<ul>Reset the bodies of all the unique slime characters in the Slime Queen's tower to their default starting values (as they were being randomised due to the bug fixed by AceXP).</ul>"
			+"<ul>Fixed issue with some vagina types having incorrect names.</ul>"
			+"<ul>Removed elementals as an option from the debug menu's 'race resets', as it was causing numerous issues having the player transformed into an elemental.</ul>"
			+"<ul>Fixed issue where the initial dialogue scene with your elemental would sometimes display a lot of conditional parsing errors.</ul>"
			+"<ul>Fixed issue where companions would be unable to use fire spells out of combat by sacrificing health.</ul>"
			+"<ul>Fixed game-breaking issues when getting your companion to summon an elemental.</ul>"
			+"<ul>Fixed issue where exploring in Dominion's alleyways would sometimes pass time but not display any encounter.</ul>"
			+"<ul>Fixed a couple of instances where Dominion storm attackers would not be removed from street tiles after interacting with them. (Any NPCs stuck on tiles in this manner will be cleaned up whne loading into this version.)</ul>"
			+"<ul>Half-demon dog, wolf, and fox morphs should now correctly spawn with scarlet penises.</ul>"
			+"<ul>Fixed issue where Loppy was spawning with her penile virginity.</ul>"
			+"<ul>Fixed bug where having sex with more tahn 4 slaves in your bathroom's shower or bath would throw background errors and thus not work.</ul>"
			+"<ul>Fixed bug where all of your clothing would be duplicated into your inventory after stripping to use the spa and then having sex with your salves in the pool.</ul>"
			+"<ul>Fixed bug where, after sex, unique NPCs would drop clothing that had become dirty during sex on the floor.</ul>"
			+"<ul>Offspring resulting from an imp mother or father will now correctly be imps.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.9</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added five new fluid flavourings: coffee; tea; cinnamon; lemon; maple. (by 'Charisma is my Dump Stat')</ul>"
			+"<ul>Added five new fluid flavourings: grape; melon; coconut; blueberry; orange. (by 'DSG')</ul>"
			+"<ul>Improved the icon for 'lacy panties'. (by DSG)</ul>"
			+"<ul>Fixed some typos in the taser attack description. (by DSG)</ul>"
	
			+"<li>Engine:</li>"
			+"<ul>Added parser hook ('itemGen') for access to ItemGeneration class, enabling clothing, weapons, and items to be generated as effects via the parser.</ul>"
			+"<ul>Converted PlaceUgrades from enum to abstract classes.</ul>"
	
			+"<li>Gameplay:</li>"
			+"<ul>Added 'spa' as a new room upgrade in Lilaya's mansion.</ul>"
	
			+"<li>Elementals:</li>"
			+"<ul>Elementals are no longer summoned as companions. Instead, when summoned, they have their name and status effects appended to the bottom of their sumoner's character info.</ul>"
			+"<ul>Elementals now have an active and passive form. They assume their passive form when out of combat/sex.</ul>"
			+"<ul>You can initiate dialogue with your elemental via an action in your phone screen, or by clicking on their name at the bottom of their sumoner's character info.</ul>"
			+"<ul>Fully added several interaction options with your elemental in their interaction screen.</ul>"
	
			+"<li>Balance:</li>"
			+"<ul>The 'martial artist' perk now doubles base unarmed damage instead of tripling it. It additionally grants +25% critical power.</ul>"
			+"<ul>'Potion effects' are now limited to a maximum value of 25 per attribute. The 'Chef' background doubles this limit to 50.</ul>"
	
			+"<li>Items:</li>"
			+"<ul>Added 'Breast pumps', 'Cock pump', and 'Pussy pump' clothing items. These are not available to buy anywhere, and are instead automatically equipped onto slaves who are being milked.</ul>"
			+"<ul>Changed essence icons to be automatically generated from subspecies data.</ul>"
			+"<ul>'Angels Tears' have been replaced by 'Bread Roll' as the human transformative food item.</ul>"
			+"<ul>'Angels Tears' is now a non-racial consumable which lowers lust when drunk.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>Characters can now grope the breasts of someone locked in the milking stocks while fucking them from behind.</ul>"
			+"<ul>All characters now produce girlcum as an ingestable fluid when orgasming, but characters who are squirters produce 5 times as much.</ul>"
			+"<ul>When having sex with milker slaves in a milking room, for as long as you leave the milking pumps attached to the milker's breasts/cock/pussy, their produced fluids during sex will be added to the room's storage.</ul>"
			+"<ul>Added an 'attach pumps' sex action in milking stall sex for attaching all possible pumps to a milker.</ul>"
			+"<ul>Added 'humping' sex slot for the stocks and milking stall sex positions.</ul>"
			+"<ul>Improved orifice stretching effects when using the 'quick sex' action.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Slaves will now only be milked of their milk, cum, and girlcum if they are able to automatically equip their milking pumps. This requires access to nipple, penis, and vagina inventory slots (and the stomach slot for udders, if applicable).</ul>"
			+"<ul>Added a setting for the 'milking' slave job, which is off by default, to allow hymen tearing (which can be caused by the dildo-like 'pussy pump').</ul>"
			+"<ul>Slightly increased the impact that girth values have on a penetrative body part's diameter. (Values were increased from +/-15%, +/-30%, +/-60% to +/-20%, +/-40%, +/-80%).</ul>"
			+"<ul>Slightly adjusted values for orifice capacity categories to make the 'gaping' values more in line with the others.</ul>"
			+"<ul>Added maximum health and maximum aura to the attribute overview tooltip (when hovering over a character's name).</ul>"
			+"<ul>Added the option to refuse to act as a test subject in Arthur's hypno-watch side-quest.</ul>"
			+"<ul>Added action to hug Lilaya in her lab.</ul>"
			+"<ul>Removed Lilaya's garden icons and made tiles green instead. Changed icon for Candi's reception desk.</ul>"
			+"<ul>Increased hourly fatigue for the slave job 'lab assistant' from 1 to 1.5.</ul>"
			+"<ul>You now gain a beneficial 'recently showered' status effect whenever taking a shower, which is a slightly weaker version of teh 'recently bathed' status effect.</ul>"
			+"<ul>Changed the 'milking' slave job permissions from 'forbid X-fluid' to 'Collect X-fluid'. Permissions hould have been converted automatically to this new format, but it may be worth checking your milk slaves' job permissions to be sure.</ul>"
			+"<ul>Snow-leopard-morphs now spawn with 'very-fluffy' tails instead of 'narrow'.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixed a high femininity sometimes being described as 'very masculine'.</ul>"
			+"<ul>Fixed issue where slaves were producing a lot less girlcum per hour while being milked than intended.</ul>"
			+"<ul>Fixed issue with unarmed damage tooltip overflowing out of the tooltip box.</ul>"
			+"<ul>Fixed incorrect formatting of percentage attribute values in clothing, weapon, tattoo, and item tooltips, as well as in the enchantment screen.</ul>"
			+"<ul>Fixed bug where penises and vaginas were always appending their race name in front of their randomly-parsed names (e.g. 'cow-pussy') instead of it only being added 25% of the time as intended.</ul>"
			+"<ul>When giving Jules a blowjob to gain entry to the night club, any clothing which is covering your mouth is now correctly displaced at the start of the sex scene.</ul>"
			+"<ul>Fixed some incorrect US-English conversions (when using the silly-mode 'American tourist' background).</ul>"
			+"<ul>Fixed issue where health/aura would sometimes incorrectly drop when a status effect expired.</ul>"
			+"<ul>Fixed issue where you could discard clothing in sex scenes which shouldn't have allowed it (such as when locked into stocks).</ul>"
			+"<ul>Fixed issue with player thought parsing not working correctly.</ul>"
			+"<ul>Fixed incorrect value being displayed as the maximum comfortable penetration diameter of orifices in the selfie screen.</ul>"
			+"<ul>Fixed selfie describing your age as starting as 18, even though the 'starting at 18' mechanic doesn't apply to the player.</ul>"
			+"<ul>Fixed issue where equip text that included conditional statements was being parsed during game initialisation or loading, which was throwing background errors.</ul>"
			+"<ul>Fixed issue where characters could still get pregnant/impregnate others even if they had the 'barren' or 'firing blanks' perks, or the 'menopause' status effect.</ul>"
			+"<ul>Fixed issue where half-demon centaurs would spawn in without having any demonic parts, which was causing several issues stemming from their resulting incorrect race recognition.</ul>"
			+"<ul>Fixed issue with slimes and elementals being describes as being 'covered in' slime, stone, flames, etc. instead of being 'made out of' the material.</ul>"
			+"<ul>Fixed issue where you could only save your save when your location's default dialogue was being displayed. (You can now save whenever your character is free to move.)</ul>"
			+"<ul>Fixed bug where the spawn rate of taurs in Submission was being influenced by the half-demon spawn rate setting instead of the taur spawn rate setting.</ul>"
			+"<ul>The 'furry preference' for tauric subspecies is no longer overridden by your global taur furriness settings.</ul>"
			+"<ul>Elementals now spawn with no speech-modifying personality traits.</ul>"
			+"<ul>Fixed issue with unique NPC slaves removing sex toys after they've been equipped during sex.</ul>"
			+"<ul>NPCs now correctly replace their dirtied clothing after sex instead of leaving it lying on the floor.</ul>"
		+"</list>"

		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.8.9</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Updated the lacy plunge bra icon. (by DSG)</ul>"
			+"<ul>Fixed typos in plate carrier, heavy plate carrier, and combat helmet descriptions. (by DSG)</ul>"
			
			+"<li>Engine/Modding:</li>"
			+"<ul>Slightly decreased size of save files.</ul>"
			+"<ul>Added accessor for RacialBody in parsing, using the 'RACIAL_BODY_' prefix. Also added 'getRacialBody()' method to Race class for access to RacialBodies.</ul>"
			+"<ul>Added 'appendToTextStartStringBuilder' and 'appendToTextEndStringBuilder' methods to correctly parse text to the game's 'start' and 'end' StringBuilders (for use in mods that apply effects).</ul>"
			+"<ul>You can now define 'criticalHitText' values in weapon mods, which allow you to define hit descriptions which are only shown when critically hitting. (For an example, see 'res/weapons/innoxia/dagger/dagger.xml'.)</ul>"
			+"<ul>Added accessor for AbstractFluidTypes, using the 'FLUID_TYPE_' prefix. Added a version of the GameCharacter.ingestFluid() method which takes an AbstractFluidType instead of a FluidInterface (so it can be called via parsing). </ul>"
			+"<ul>Added a version of GameCharacter.calculateGenericSexEffects() which can be called from the parser. Added accessors for SexParticipantType ('SEX_PT_') and GenericSexFlag ('SEX_FLAG_') for use in this method.</ul>"
			+"<ul>The 'SPECIAL_PARSE' command should now work correctly as logic in conditional statements (it gets parsed before the conditional is checked).</ul>"
			+"<ul>Converted last of the body type enums to abstract classes and added accessors for use in the parser for all of them.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added options to have sex with slaves who help you to wash when taking a shower or bath in your room. The Slime Queen's bath sex scene and Pix's shower sex scene also now use the ongoing-cleaning and water lubrication mechanics of having sex in a bath/shower.</ul>"
			+"<ul>Axel's quest (involving the Rat Warrens) is under construction again, and as such, I've disabled the ability to start his quest in this version. If you've already started it, then please wait until the next release before continuing with it!</ul>"
			
			+"<li>Balance:</li>"
			+"<ul>Characters are now only vulnerable to arcane storms and combat loss at maximum lust while they have the 'arcane impotence' status effect. (i.e. Only if their arcane stat is less than 10.)</ul>"
			+"<ul>The chance for an Enforcer encounter to trigger in alleyway tiles has been increased from 2% to 2.5%.</ul>"
			+"<ul>The 5 alleyway tiles closest to Dominion's Enforcer HQ now have a significantly higher chance of generating Enforcer encounters than normal alleyway tiles (15% instead of 2.5%), while also having a lesser chance of generating a generic attacker encounter (10% instead of 15%).</ul>"
			+"<ul>Increased maximum shielding values from 100 to 500, and made it so that a value of 500 in any shielding category grants complete immunity from the related damage type. (Only used for fire and ice elementals being immune to fire/ice damage.)</ul>"
			+"<ul>Removed the diminishing returns factor from all damage attributes and made it clear that these are referring to percentage increases wherever they're displayed (in tooltips and stats screen).</ul>"
			+"<ul>The 'spell efficiency' attribute is now capped at 80 instead of 100, and all damage attributes have a lower cap of -80 instead of -100.</ul>"
			+"<ul>Removed the 'cum quantity pregnancy modifier' from the game. (Now that cum quantity has a more direct impact of higher volumes staying in a character's orifices for longer, this modifier was no longer needed.)</ul>"
			+"<ul>Slightly altered the pregnancy calculation to be: 10% + (Cum-character's Virility% / 2) + (Womb-character's Fertility% / 2)</ul>"
			+"<ul>Randomly-spawned NPCs will no longer have the 'martial artist' trait.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Changed the 'pentagram piercing' rarity to 'common'.</ul>"
			+"<ul>Improved description of 'Angel's Nectar' to correctly state that it removes intoxication and psychoactive effects. You can now also enchant it to create a potion that regenerates or removes hymens.</ul>"
			+"<ul>There is no longer an arcane stat requirement for NPCs to absorb bottled essences.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Added some flavour text for characters with the 'pure virgin' fetish (and who are either still vaginal virgins or have a regrown hymen) performing or receiving the 'pussy tease' sex action (with both penis or tail).</ul>"
			+"<ul>Added some variation in the tail/penis-vagina penetration sex action descriptions for when the character being penetrated is still a virgin.</ul>"
			+"<ul>'Stop ongoing' actions are now coloured differently than the 'start ongoing' actions.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>You can now recolour human skin to any colour, just like with other covering types. (Humans will still only ever spawn with natural colours.)</ul>"
			+"<ul>NPCs' 'home' icons on the minimap are now semi-transparent to make it clearer that they aren't actually there.</ul>"
			+"<ul>Improved 'barren' and 'firing blanks' tooltip descriptions to make it clearer how those perks work.</ul>"
			+"<ul>NPCs who are not your slave, elemental, or one of your friendly occupants will now automatically spend their perk points when they level up (via draining your levels or gaining experience from beating you in combat).</ul>"
			+"<ul>When losing a level (from orgasmic level drain), characters now additionally lose a random perk if their perk points are reduced to below 0 in the process.</ul>"
			+"<ul>The clothing & weapons sold by Finch, Nyan, Kate, and Vicky are now generated in stacks of 2-6 instead of just 1.</ul>"
			+"<ul>Added some pet names for use in Amber's scene where she walks you and you run into her friend & their pet.</ul>"
			+"<ul>Reduced the default capacity for several races' (reindeer, squirrel, rat, rabbit, and bat) vaginas, as they were still using values from before the penetration girth/depth rework.</ul>"
			+"<ul>Enforcers who choose to give you a handjob after the strip search will now only finger your asshole at the same time if they have a positive desire towards the anal-giving fetish.</ul>"
			+"<ul>Sex scenes no longer start with a spam of 'X area was already lubricated by Y' messages.</ul>"
			+"<ul>Added more staircases to Lilaya's house.</ul>"
			+"<ul>The 'begin slave interaction' actions in Lilaya's house are now coloured according to the slave's femininity.</ul>"
			+"<ul>Added setting to enable bad ends in gameplay content option (on by default). (There are no bad ends in the game just yet, but I may start adding some alongside new content in the future.)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixed a minor issue where Finch, Kate, and Nyan would sometimes not generate the correct number/type of enchanted clothing for sale.</ul>"
			+"<ul>Fixed issue where having the 'opportunistic attackers' content setting turned on would cause some Encounters to never trigger.</ul>"
			+"<ul>Fixed bug where two human half-demons would create half-demon offspring instead of imps.</ul>"
			+"<ul>Fixed issue where demons/lilin who had transformed to look like a different race would create offspring of a subspecies based on their appearance, instead of their true demonic race.</ul>"
			+"<ul>Fixed bug where if you had the American tourist background perk, the conversion of colour to color was followed by a random pair of parentheses.</ul>"
			+"<ul>(Finally) Fixed issue where Scarlett would sometimes not appear in Helena's shop after sending her there during Helena's romance quest.</ul>"
			+"<ul>Fixed related issue where Scarlett's location was not being set properly during the stage of the main quest where you need to buy her from Helena.</ul>"
			+"<ul>Characters who have lost their vaginal virginity and then regown their hymen now correctly have their new hymen torn in situations where they should be losing their virginity.</ul>"
			+"<ul>Fixed bug where the 'Defend yourself' action in the Enforcer encounter would be on every button instead of just one.</ul>"
			+"<ul>NPCs who spawn with enchanted clothing/weapons that pushes them over their enchantment stability limit will now automatically take perks to give themselves more enchantment stability.</ul>"
			+"<ul>Fixed issue where ingesting addictive fluids during sex would not display a message about the ingesting cahracter's addiction being satisfied.</ul>"
			+"<ul>Fixed issue with the name of fluids not being parsed correctly in the tooltip for the addiction status effect.</ul>"
			+"<ul>Formatting of attribute numbers in status effect tooltips no longer always has a trailing '.0'.</ul>"
			+"<ul>Fixed issue with the flow of dialogue in Amber's 'Insult her' scene.</ul>"
			+"<ul>Vargrs, hellhounds, and demonic-fox-morphs now correctly spawn in with red canine-like cocks.</ul>"
			+"<ul>Fixed bug where your character's health would sometimes keep on dropping during character creation.</ul>"
			+"<ul>Fixed issue where Claire would react as though you hadn't used the resonance stone in the Rat Warrens quest even when you had.</ul>"
			+"<ul>Fixed a parsing error with the description of crotch nipples recovering from being stretched after sex.</ul>"
			+"<ul>Fixed the asterisk denoting whether hair is long enough to be pulled in sex not being displayed in the character creation's hair length choice box.</ul>"
			+"<ul>Fixed issue with half-demons who have a unique name not gaining any name variation when they were taurs.</ul>"
			+"<ul>Fixed bug where offspring would react as though you're enslaving them with a collar even if you used a different item of clothing.</ul>"
			+"<ul>Tauric Enforcers will no longer spawn wearing slacks (which should have been impossible to fit onto their tauric bodies).</ul>"
			+"<ul>Fixed issue where the description of slaves initiating sex with you after sleeping in your room would be missing unless you went to sleep with your slaves already having the 'pent up' status effect.</ul>"
			+"<ul>Fixed issue where not all of the intended enslavement effects were applied when getting Enforcers to enslave a criminal for you. (i.e. They now correctly have their true name revealed and their starting obedience is set to -100.)</ul>"
			+"<ul>Removing and regrowing a character's vagina now correctly regrows their hymen.</ul>"
			+"<ul>The Slime Queen's bath and Pix's shower scene now correctly clean your body & clothes.</ul>"
			+"<ul>Fixed issue with incrementing health and mana not working when called from within status effects.</ul>"
		+"</list>"

		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.8.6</h6>"
			+"<li>DSG Enforcer Clothing update:</li>"
			+"<ul>Modernized the replacement/displacement text blocks of all items to the newer, more compact style</ul>"
			+"<ul>General improvements to item descriptions and replacement/displacement text (including fixing US/UK spelling discrepancies and some typos which were at least two years old x_x)</ul>"
			+"<ul>Buffed the default poison resistance of the Enforcer's gas mask to 15</ul>"
			+"<ul>Added five new set bonuses (clothing not listed have been stripped of their set bonuses):</ul>"
			+"<li>Dutiful Enforcer</li>"
			+"<ul>Grants +1 lust resistance</ul>"
			+"<ul>One from each list below is required unless locked out</ul>"
			+"<ul>Over-torso: All varieties of the Enforcer's coat/waistcoat, including unique variants, Enforcer's commando sweater/sweater vest</ul>"
			+"<ul>Leg: Enforcer dress slacks</ul>"
			+"<li>Vigilant Enforcer</li>"
			+"<ul>One from each list below is required unless locked out</ul>"
			+"<ul>Over-torso: Enforcer's stabproof vest</ul>"
			+"<ul>Hips: Enforcer's utility belt</ul>"
			+"<li>Methodical Enforcer</li>"
			+"<ul>Grants +5 lust resistance, +3 energy shielding, +5% critical damage</ul>"
			+"<ul>One from each list below is required unless locked out</ul>"
			+"<ul>Over-torso: Enforcer's plate carrier</ul>"
			+"<ul>Hips: Enforcer's battle belt</ul>"
			+"<li>Courageous Enforcer</li>"
			+"<ul>Grants +7 lust resistance, +5 energy shielding, +5% critical damage</ul>"
			+"<ul>One from each list below is required unless locked out</ul>"
			+"<ul>Over-torso: Enforcer's heavy plate carrier</ul>"
			+"<ul>Hips: Enforcer's heavy battle belt</ul>"
			+"<li>Steadfast Enforcer</li>"
			+"<ul>Grants +5 lust resistance, +5 fire resistance, +3 energy shielding</ul>"
			+"<ul>One from each list below is required unless locked out</ul>"
			+"<ul>Over-torso: Enforcer's riot armour</ul>"
			+"<ul>Hips: Enforcer's riot belt</ul>"
			+"<ul>Wrist: Enforcer's riot arm guards</ul>"
			+"<ul>Ankle: Enforcer's riot shinguards</ul>"
			+"<ul>Head: Enforcer's riot helmet</ul>"
			+"<li>Other</li>"
			+"<ul>Added handcuffs (wrist slot).</ul>"
			+"<ul>Added unique Enforcer coat variants for Wesley and Aellasys. (Characters will be added soon.)</ul>"
			+"<ul>Fixed minor issue with outline colouration in the Enforcer's utility belt icon.</ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Slightly improved game's performance (by updating character status effect checks and removing some background parsing).</ul>"
			+"<ul>Reduced size of save files by about 800kB.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul><b>Companion content is now locked behind a toggle in gameplay options, and is disabled by default.</b> No further support for companions will be added going forwards, as accounting for companion variations, both in the writing and dialogue framework, was taking a huge amount of time and slowing down the pace of the game's development too much.</ul>"
			+"<ul>Added full content for the Enforcer encounter, which can now trigger in both Dominion's canal and alleyway tiles. Increased the chance of this encounter triggering from 1% to 2%.</ul>"
			
			+"<li>Balance:</li>"
			+"<ul>Martial artist perk now triples base unarmed damage.</ul>"
			+"<ul>Base unarmed damage is now calculated as 20% of a cahracter's physique, and is no longer capped at 8.</ul>"
			+"<ul>Characters can now only perform sex actions of one corruption level higher than their current level without gaining corruption. (e.g. A 'pure' character can only perform 'pure' or 'vanilla' actions without gaining corruption.)</ul>"
			+"<ul>The boundaries for the six levels of physique/arcane/corruption perks has been standardised to 0-10/10-30/30-50/50-70/70-90/90-100.</ul>"
			+"<ul>Slimes no longer have -100 unarmed damage as their subspecies modifier, and instead have their base unarmed damage reduced by 75%.</ul>"
			+"<ul>'Unarmed' weapons (such as the knuckle dusters) now include the equipping character's base unarmed damage in their damage calculation.</ul>"
			+"<ul>Characters now gain unique effects from non-flesh body materials, instead of the effects being part of the subspecies status effect. (Only affects slimes and elementals at the moment.)</ul>"
			
			+"<li>Elementals:</li>"
			+"<ul>Elementals no longer have two perks giving them their core stats, and instead have an 'Elemental' trait which does this.</ul>"
			+"<ul>Elementals' attributes are now determined by their body material, and are applied via their racial status effect.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>When a character wearing a condom cums, and the condom doesn't break, they now pull out of whatever orifice they were fucking (so that they can remove the condom).</ul>"
			+"<ul>NPCs will no longer stop all ongoing 'foreplay' sex actions before starting their preferred 'main sex' penetrative action (if the action is available to them).</ul>"
			+"<ul>Disabling anal content now only disables actions involving a character's anus, not their ass. (So you can now perform/receive hotdogging even if anal is off.)</ul>"
			+"<ul>NPCs will now only suck their own tails during sex if they have both the performing and receiving oral fetishes.</ul>"
			+"<ul>Characters who have the pure virgin fetish and a virgin vagina (or a regrown hymen) will now have a very strong aversion to performing sex acts that involve having their vagina penetrated. (This also affects sex type generation during quick sex.)</ul>"
			
			+"<li>Slavery:</li>"
			+"<ul>Slaves will no longer have sex with other slaves who aren't attracted to them, unless they are willing to rape that other slave. (Willingness to rape requires non-con content to be on and for the slave to have a non-negative fetish desire towards raping others.)</ul>"
			+"<ul>Slaves now have a chance (25%) to bond with one another for each hour that they spend together (either in the same room or at the same job). The chance to bond is reduced if the slaves are shy and increased if they are confident or related to one another.</ul>"
			+"<ul>Bonding events can either result in a positive or negative change to the slaves' affection towards one another. Being kind or related to one another increases the chance of a positive event, while being selfish decreases it.</ul>"
			+"<ul>Slaves will now only masturbate after 12 hours of having the 'Pent-up' status effect, instead of 6, so (if their setting enable them to) they will be more likely to have sex with other slaves (or ambush the player for sex).</ul>"
			+"<ul>Improved information shown in slave job tooltips.</ul>"
			+"<ul>'Daily fatigue' has been renamed to 'daily stamina'. All slaves have 24 stamina, and instead of slave jobs increasing fatigue, they now drain stamina. The 'idle' and 'bedroom' jobs now have no effect on a slave's daily stamina.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>The icon representing items in a tile is now shown in the main map screen.</ul>"
			+"<ul>Rainbow colours are now parsed correctly.</ul>"
			+"<ul>Buffed the 'tourist' perk by making all enemies suffer -1AP. (Silly mode occupation.)</ul>"
			+"<ul>Changed Dominion's street and boulevard 'Characters Present' to have less Enforcers, as well as adding 'centaur carts' as being present in boulevard tiles.</ul>"
			+"<ul>The girth of rat tails now tapers off instead of being the same value all the way to the tip.</ul>"
			+"<ul>Half-demon rabbit-morphs are now called 'jackalopes'.</ul>"
			+"<ul>Reduced the fine which Dominion alleyway prostitutes need to pay off from 20,000 to 10,000 flames.</ul>"
			+"<ul>After completing Helena's romance quest, Scarlett will now slowing regain affection towards Helena until she is neutral towards her.</ul>"
			+"<ul>Moved the 'copy dialogue' and 'export character' buttons from the top-left and top-right of the dialogue title bar to the very bottom-right part of the game's UI.</ul>"
			+"<ul>Improved formatting of offspring map dialogue.</ul>"
			+"<ul>The 'Inno-chan's gift' debug item now works on the player character.</ul>"
			+"<ul>Increased save file name length limit from 32 characters to 64.</ul>"
			+"<ul>Updated the icons for physique/arcane/corruption attributes.</ul>"
			+"<ul>Added unique colours for association with unarmed, melee, and ranged damage.</ul>"
			+"<ul>Added a tooltip to show you what your unarmed damage is when you hover over an empty weapon slot in your inventory.</ul>"
			+"<ul>Added 'tapered tail' as a demonic tail type.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Numerous parsing, formatting, and typo fixes.</ul>"
			+"<ul>Fixed issue where characters with a lisp wouldn't have their dialogue parsed correctly if there was an ELSE conditional within it.</ul>"
			+"<ul>Fixed issue with VAR parsing in xml files sometimes throwing background errors.</ul>"
			+"<ul>Items of 'Unique' rarity will no longer automatically be deleted from tiles.</ul>"
			+"<ul>Teleporting or using any other means of fast travel will no longer cause any dropped items in your current tile to avoid being automatically deleted.</ul>"
			+"<ul>Loitering for 8 hours now correctly passes 8 hours instead of 4.</ul>"
			+"<ul>Fixed issue where entering and then exiting the enchantment screen while trading with someone would allow you to fully manage the trader's inventory.</ul>"
			+"<ul>Fixed bug where your clothing would sometimes be duplicated after the Enforcer encounter's strip-search sex scene.</ul>"
			+"<ul>Fixed issue where all status effects that had a 'time remaining' value assigned to them (most noticeably the 'pregnancy' effects) would be deleted upon saving & then loading your game.</ul>"
			+"<ul>Fixed issue where externally-defined status effects would not be applied to your character after loading a saved game until you took an action.</ul>"
			+"<ul>Fixed issue where the 'prevent creampie' flag was not working when calculating generic sex results. (This was the cause of the bug where Scarlett was letting random harpies cum inside of her even when she liked you.)</ul>"
			+"<ul>The response buttons are now correctly refreshed to what they should be displaying after using any of the loiter actions.</ul>"
			+"<ul>Fixed issue where enslaving a storm attacker on a street tile which also had a friendly occupant living in it would result in the post-enslavement dialogue breaking.</ul>"
			+"<ul>Resting until sunrise/sunset now rests you to 5 minutes past the exact time of sunrise/sunset, which should fix the issue of shortening/lengthening day times causing the rest button to sometimes rest you for 24 hours + 1 minute.</ul>"
			+"<ul>Fixed bug where the 'important status effect' screen would keep spamming 'cleaned vagina' messages if you had both pussy and vaginal-urethra creampies and had a dildo inserted into your vagina.</ul>"
			+"<ul>Fixed incorrect tooltip description for the sex positioning action to move beneath a taur in order to perform oral on them.</ul>"
			+"<ul>Fixed issue where not all characters would not react to a character's sexual areas being revealed in sex.</ul>"
			+"<ul>Fixed issue where characters who cuoldn't see each other were reacting to each other's sex areas being revealed (such as the two people you service in the nightclub's gloryhole scene).</ul>"
			+"<ul>Fixed issue where the availability of the 'incest tease' and 'vaginal tease' was incorrectly based on the performer requiring a vagina.</ul>"
			+"<ul>Any Constables which were spawned and incorrectly stayed in the tile after the encounter had ended will be automatically cleaned up when loading into this version.</ul>"
			+"<ul>Fixed bug where viewing your offspring's character screen or using the offspring map would sometimes throw a background error and cause the game to lock up.</ul>"
			+"<ul>Fixed bug where you were able to order a custom slave from Helena even if you couldn't afford it.</ul>"
			+"<ul>Fixed bug where custom slaves ordered from Helena were ten times more expensive than they should have been.</ul>"
			+"<ul>Saved clothing elements which have been corrupted and saved as being equipped into an impossible slot should now have their loading gracefully cancelled by the game.</ul>"
			+"<ul>Modded clothing will no longer spawn on randomly-generated NPCs with colours that are not defined as being available for them.</ul>"
			+"<ul>Fixed issue with a singular unicorn being described as 'An unicorn'.</ul>"
			+"<ul>Fixed bug where if a character was already addicted to a certain fluid type, they would never recognise any other character as having also given them their addiction, even if that other character has addictive fluids.</ul>"
			+"<ul>After combat, the active response tab no longer incorrectly remains on whatever tab you were on during combat, and instead resets to the first one.</ul>"
			+"<ul>If you somehow gain access to Brax's office after completing his part of the main quest, you (and Brax) will no longer get stuck in the tile.</ul>"
			+"<ul>Fixed bug where after asking Ralph for a big discount, he would start fucking you without displacing your clothing to gain access to your pussy/ass.</ul>"
			+"<ul>Finch now correctly stocks weapons and items which are tagged as being sold by him. (So he should now correctly stock the 'riding crop' weapon.)</ul>"
			+"<ul>Fixed bug where after performing a double anal creampie, the orgasming character would end by penetrating the second target's vagina (even if they didn't have one...) instead of their asshole.</ul>"
			+"<ul>Fixed bug where the 'Forced TF Racial Limits' option would be in every content option category, instead of just in 'Gameplay'.</ul>"
			+"<ul>Scarlett now correctly stops calling you by the name you told her to call you by when releasing her from being your slave.</ul>"
			+"<ul>Fixed weapon description always displaying its stats as though the player had it equipped. (So when a weapon is equipped by an NPC, it now correctly shows the damage as though it's using their stats.)</ul>"
			+"<ul>Fixed issue where Lyssieth was being detected as a human instead of an elder lilin.</ul>"
			+"<ul>Fixed bug where if you were a demonic slime, your self-transformation choices would be limited to just human or demon.</ul>"
			+"<ul>Fixed related bug where you wouldn't get a slime's bonuses if you were also a demon.</ul>"
			+"<ul>Fixed bug where self-transformation horn options were being displayed in the 'Hair' category.</ul>"
			+"<ul>Shopkeepers will no longer generate randomly-enchanted items which should have defined pre-set enchantment (such as clothing/weapons which form part of a set).</ul>"
			+"<ul>Enforcers now only equip condoms if they have the associated fetishes for that behaviour. (i.e. They either dislike cumming or you have a vagina, you aren't visibly pregnant, and they have a non-positive desire towards the impregnation fetish.)</ul>"
		+"</list>"

		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.8.5</h6>"
			+"<li>Artwork:</li>"
			+"<ul>Added artwork of Rose, by UltraBondageFairy.</ul>"
	
			+"<li>DSG clothing contributions:</li>"
			+"<li>DSG's Enforcer items update:</li>"
			+"<ul>Added pattern support to the following items: Short-sleeved combat shirt; Enforcer's short-sleeved combat shirt.</ul>"
			+"<ul>Restored pattern support to the following items: Combat shirt; Enforcer's combat shirt; Enforcer's combat helmet; Enforcer's plate carrier; Enforcer's heavy plate carrier; Enforcer's tactical trousers.</ul>"
			+"<ul>Added a new fixed color pattern: 'urban splinter camo'. This new pattern is set as the default at 100% chance for the following items: Enforcer's combat shirt; Enforcer's short-sleeved combat shirt; Enforcer's tactical trousers.</ul>"
			+"<ul>Changed rarity and set bonus of the 'civilian' version of the short-sleeved combat shirt to be the same as the long-sleeved.</ul>"
			+"<ul>Changed cryoprism artwork to have less HK G-11 in it</ul>"
			+"<ul>Added three color support to the following weapons; Cryoprism gun; Liquid stun gun; Pepper grenade launcher; Pepperball rifle; Revolving tranquilizer rifle.</ul>"
			+"<ul>Added AoE support to the following weapons:</ul>"
			+"<ul>Liquid stun gun (4 additional targets, 85/85/10/10% chance, 50/50/20/20% damage)</ul>"
			+"<ul>Pepper grenade launcher (8 additional targets, 100% chance all around, 90/90/70/70/40/40/10/10% damage)</ul>"
			+"<ul>Pepperball rifle (2 additional targets, 25/25% chance, 20/20% damage)</ul>"
			+"<ul>Updated equiptext of the pepper grenade launcher.</ul>"
			+"<ul>Updated description of the pepperball rifle to not overflow the tooltip.</ul>"
			+"<ul>Updated the hittext of the revolving tranquilizer rifle to be more lore friendly.</ul>"
			+"<li>DSG's second Enforcer items update:</li>"
			+"<ul>Changed the name of the LSG-9000 Stun Gun to Arcane Stun Gun in normal mode and LSG-9000 Coom Gun in silly mode.</ul>"
			+"<ul>Changed the hit/missText of the Enforcer's Baton and Arcane Taser to be more consistent with the other weapons.</ul>"
			+"<ul>Fixed several typos and incorrect parser tags in the hit/missText of the Riot Shield, Enforcer's Baton, and the Arcane Liquid Stun Gun.</ul>"
			+"<ul>Fixed several formatting issues introduced by IF/ELSE statements.</ul>"
			+"<ul>The following weapons now apply various status effects to enemies:</ul>"
			+"<ul>The Enforcer's Baton has a 33% chance to apply Dazed for 5 turns.</ul>"
			+"<ul>The Riot Shield has a 50% chance to apply Dazed for 3 turns.</ul>"
			+"<ul>The Arcane Taser applies Lustful Distraction for 3 turns.</ul>"
			+"<ul>The Arcane Liquid Stun Gun applies Lustful Distraction for 5 turns.</ul>"
			+"<ul>The Revolving Tranquilizer Rifle applies Arcane Duality (Negative) for 5 turns.</ul>"
			+"<ul>The Cyroprism Gun applies Freezing Fog for 3 turns and Frozen for 1 turn if the enemy already has Freezing Fog.</ul>"
			+"<li>DSG's third Enforcer items update:</li>"
			+"<ul>Buffed the following items' default fire/poison/freeze resistance:</ul>"
			+"<ul>The Enforcer's Stabproof Vest and Claire's unique variant: 5/3/3</ul>"
			+"<ul>The Enforcer's Riot Helmet: 3/2/2</ul>"
			+"<ul>The Enforcer's Riot Armor: 10/6/6</ul>"
			+"<ul>The Enforcer's Combat Shirt and Enforcer's Short-Sleeved Combat Shirt: 1/1/1</ul>"
			+"<ul>The Enforcer's Tactical Trousers: 1/1/1 </ul>"
			+"<ul>The Enforcer's Combat Helmet: 5/3/3</ul>"
			+"<ul>The Enforcer's Plate Carrier: 15/10/10</ul>"
			+"<ul>The Enforcer's Heavy Place Carrier: 20/12/12</ul>"
			+"<li>DSG's Kitty Lingerie:</li>"
			+"<ul>Added 'kitty lingerie' clothing, which includes: bra, panties, stockings, headband, and gloves. All sold by Nyan. (All created by DSG)</ul>"
	
			+"<li>Contributors:</li>"
			+"<ul>Added 'Slavery Administration' shirt, which is worn by Finch. (by Rfpnj)</ul>"
			+"<ul>Fixed bug where empty 'defaultPatterns' elements in clothing xml files would throw unhelpful errors. (by CognitiveMist)</ul>"
	
			+"<li>Engine:</li>"
			+"<ul>Converted FluidType, HairType, NippleType, WingType enums into abstract classes.</ul>"
			+"<ul>Changed parsing command of 'character.bodypart(boolean)' to have the boolean argument force the singular form, instead of forcing an automatic adjustment. (In other words, all body part commands now use the correct default plurality of the chararacter's part. e.g. 'horn' on a character with two horns would return 'horns'.)</ul>"
			+"<ul>Added modding support for adding clothing/weapon sets (look in 'res/mods/innoxia/setBonuses/template.xml' and 'res/setBonuses/innoxia/enforcer.xml' for examples).</ul>"
			+"<ul>Added modding support for adding status effects (look in 'res/mods/innoxia/statusEffects/set_template.xml' and 'res/statusEffects/innoxia/set_kitty.xml' for examples).</ul>"
	
			+"<li>Gameplay:</li>"
			+"<ul>Added framework for Enforcer Dominion alleyway encounter (1% chance of it triggering). Most of the dialogue is still placeholder, but it should be functionally complete.</ul>"
			+"<ul>In the 'locked in stocks' scene in Slaver Alley (from Sean's content), the number of strangers using you or your partner is now randomised each time (to be either one or two). You can now also choose to stay in the stocks for another round at the end.</ul>"
			+"<ul>Characters no longer gain +1 corruption from discovering negatively-enchanted clothing (by equipping clothing which has an unknown enchantment).</ul>"
			+"<ul>After becoming a demon, if you fully self-transform all of your body parts into human parts, then you are treated by the game as being a human. (You retain your demonic status effect and ability to transform back into a demon at any time.)</ul>"
	
			+"<li>Items:</li>"
			+"<ul>Added 'feminine apron'. (Over-torso slot, feminine, sold by Nyan).</ul>"
			+"<ul>You can now fit wrist restraints onto arm-wings.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>The 'Irresistible Appeals' perk now makes it so that all of your requests are granted during standard sex scenes, and you are also able to access the positioning menu in standard sex scenes where you otherwise wouldn't be allowed to.</ul>"
			+"<ul>The 'Cock tease', 'Cock tease (anal)', and 'Hotdogging tease' actions (performed by the character who is taking hold of their partner's cock) are now correctly disabled if that character can't reach their partner's cock. (Such as in the pregnancy roulette scenes.)</ul>"
			+"<ul>When using the 'Quick Sex' action, and a character's condom breaks, that character now correctly cums inside whatever orifice they were penetrating.</ul>"
			+"<ul>More than only 20 seconds now passes when using the 'Quick sex' action. (5 minutes pass per character orgasm.)</ul>"
			+"<ul>'Quick sex' now has a chance to use crotch-boobs/nipples as one of the actions performed.</ul>"
			+"<ul>Non-taurs in the 'humping' slot in the 'all fours' sex position can now perform finger-groin interactions.</ul>"
			+"<ul>Added 'Keep going' action to Claire's risky sex scene (so you can ignore her request to stay quiet when an Enforcer enters the store room).</ul>"
			+"<ul>NPCs who either have a negative fetish desire towards the 'cum stud' fetish, or who have a non-positive desire towards impregnation and who are having sex with someone with a vagina, will now self-equip condoms from out of their inventory.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Removed instances of 'jinxes/jinxed' in the game, and replaced them with 'seals/sealed'.</ul>"
			+"<ul>The 'jinxed clothing' status effect has been renamed to 'sealed clothing', and now applies -5 maximum aura instead of +10 corruption.</ul>"
			+"<ul>You now have five choices as to how long you wish to loiter in an area for: 15 minutes, 1 hour, 4 hours, 8 hours, or 12 hours.</ul>"
			+"<ul>Defeating the groups of six rats in the Rat Warrens now proceeds into post-combat victory scenes in which two of them have escaped (as having 6 rats during sex and management was causing numerous issues).</ul>"
			+"<ul>Lilaya now has a small reaction when you get her to drink fertility-altering potions during sex.</ul>"
			+"<ul>If Scarlett has a vagina and ends up having sex up in Helena's nest, she will no longer allow other harpies to get her pregnant if she likes you.</ul>"
			+"<ul>Next to a character's age in their description page, a small reminder has been added that everyone in the world of LT starts out as being 18 from the date of their birth.</ul>"
			+"<ul>Added 'wide', 'flat', and 'strong' tongue modifiers to the transformative options when crafting potions and clothing.</ul>"
			+"<ul>You can no longer equip clothing during sex which would ordinarily enslave the person it is equipped onto.</ul>"
			+"<ul>Improved flow of dialogue after Kalahari's sex scene.</ul>"
			+"<ul>When you've unlocked the special ability of the school of fire, casting spells at a cost of health now uses up whatever aura you have left before calculating health cost. (e.g. If a spell costs 100 aura, and you have 80 aura left, the 80 aura will be spent first, then health cost will be 25% of 20 aura, so 5 health.)</ul>"
			+"<ul>If she's not attracted to you, Scarlett will no longer want to receive oral from you in Helena's shop, nor spend her lunch break with you.</ul>"
			+"<ul>Transformation description of fluid regeneration changes now tells you exactly how much fluid is being regenerated per day.</ul>"
			+"<ul>Moved 'Forced TF Racial Limits' option out of the Furry Preferences menu and into the gameplay content settings menu (so that it's beside the other forced TF settings).</ul>"
			+"<ul>Added finer control in the 'Furry preferences' menu for how often you want humans, taurs, and half-demons to spawn.</ul>"
			+"<ul>Changed the way the tauric upper-body furriness setting (found in the 'Furry preferences' menu) works. <b>Your tauric upper-body furry preference will have been reset when you load into this version.</b></ul>"
			+"<ul>Added a new background occupation for silly mode.</ul>"
			+"<ul>Dog, wolf, and fox vaginas now have the 'puffy' orifice modifier by default.</ul>"
			+"<ul>Half-demons and imps now have access to the 'Grow cock' sex action.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Multiple parsing and typo fixes.</ul>"
			+"<ul>Added a check to remove old slaves which weren't being cleared from the shops in Slaver Alley.</ul>"
			+"<ul>Fixed parsing error in taur skirt's removal text.</ul>"
			+"<ul>All unique demon characters now have 2 testicles instead of having a random chance to spawn with 4.</ul>"
			+"<ul>Fixed issue with non-unique NPCs continuously replacing their clothing every hour.</ul>"
			+"<ul>Kate's sex scenes now correctly start with her clothing being displaced to expose her vagina.</ul>"
			+"<ul>Fixed bug where NPCs with non-plantigrade feet would spawn wearing no legwear or underwear.</ul>"
			+"<ul>Fixed bug where the knotting orgasm action would sometimes throw an error in group sex scenes.</ul>"
			+"<ul>Fixed issue where orgasm descriptions wouldn't describe all of the clothing & areas which were cummed on.</ul>"
			+"<ul>Clothing with an enchantment that gives an increase to corruption is no longer given the naming postfix of 'of purity'.</ul>"
			+"<ul>Fixed issue where enchanting items while in slavery or occupant management would return you to an inventory screen in which you were no longer interacting with the NPC.</ul>"
			+"<ul>Fixed bug where selecting the 'Leave' action in a slave's management screen would leave their inventory being rendered in the right UI panel.</ul>"
			+"<ul>Fixed issue where accessing the cosmetics screen via the slave management list would result in the slave's inventory continuing to be rendered in the right UI panel even after exiting that menu.</ul>"
			+"<ul>Fixed issue with the 'loiter' action not updating the responses in the tile which you loiter in.</ul>"
			+"<ul>Fixed issue with given/received creampie stats being reversed (so 'creampies given' was being counted as 'creampies taken' and vice versa).</ul>"
			+"<ul>If you have age content turned off, the age of a character is no longer displayed in their character description page.</ul>"
			+"<ul>Fixed issue where some NPCs (most notably the custom slave you can order from Helena) would spawn without any base perks, making it impossible to ever assign any perks to them. (This has been retroactively fixed for any perkless NPCs in your save.)</ul>"
			+"<ul>Fixed cause of several bugs when having sex with more than 4 characters in either the 'submissives' or 'dominants' category.</ul>"
			+"<ul>Fixed some issues with Lilaya not reacting correctly when you came inside of her, and sometimes not asking you to pull out.</ul>"
			+"<ul>When choosing to be 'Male Bred' in the pregnancy roulette, feminine harpies no longer have a chance to spawn.</ul>"
			+"<ul>You can no longer get your partner to use items or equip clothing (such as condoms) in the pregnancy roulette sex scenes.</ul>"
			+"<ul>Fixed issue where you could use items during Pix's shower sex scene and during Rose's hand-holding scene.</ul>"
			+"<ul>Clothing and item icons are now correctly greyed-out in your inventory when they are not available for use during sex.</ul>"
			+"<ul>Fixed issue where exported NPCs were failing to load correctly when selected to be your imported playable character at the start of a new game.</ul>"
			+"<ul>Fixed issue with a character's singular horn in the 'Grab horns' sex action being described as though there were two of them.</ul>"
			+"<ul>Fixed bug where dying all clothing in a stack (which was on the floor) would clone the original stack of clothing.</ul>"
			+"<ul>Fixed issue in Combat's getAllies method, which was returning a list that incorrectly included the character whose allies were to be found.</ul>"
			+"<ul>Fixed Soothing Water's 'Bouncing Orbs' upgrade not working correctly.</ul>"
			+"<ul>Fixed availability of several sex actions when a taur is receiving oral from a character who is down on all fours.</ul>"
			+"<ul>Fixed bug where characters couldn't start the ongoing sex action where they had their partner start to kiss/suckle their crotch-nipples.</ul>"
			+"<ul>Fixed bug where characters with a knotted penis would cause the game to freeze if they orgasmed while not having their penis interact with anything.</ul>"
			+"<ul>Transformations which make it impossible to wear certain items of equipped, sealed clothing now correctly remove them (breaking the seal in the process).</ul>"
			+"<ul>Fixed issue in enchanting screen, where adding corruption modifying enchantments would not display the correct projected capacity cost.</ul>"
			+"<ul>Fixed issue with the descriptions of Meraxis's demon lieutenants having their names incorrectly parsed.</ul>"
			+"<ul>Imps in Submission's fortresses no longer spawn with completely random outfits.</ul>"
			+"<ul>Fixed issue with Lilaya being older than intended when starting a new game.</ul>"
			+"<ul>The error log should no longer produce a spam of 'Failed to load character present' warnings when starting a new game via using an imported character.</ul>"
			+"<ul>Fixed bug where importing a previously-exported player character as a slave would increase their age by 18 years.</ul>"
			+"<ul>Fixed issue where having two items of identical clothing equipped in two slots (such as the ribbon being equipped to hair & tail) would throw background errors when you tried to unequip one of them.</ul>"
			+"<ul>Fixed bug where the descriptions generated from clothing transformations would sometimes be filled with parser errors.</ul>"
			+"<ul>Fixed issue with mute characters sometimes being able to speak during some sex actions.</ul>"
			+"<ul>When using the 'Quick sex' action, the 'Orgasmic level drain' perk now works even if you are a submissive partner.</ul>"
			+"<ul>Fixed bug in pregnancy stats screen where pregnancy possibilities could be displayed as 'Certainty' even if they were not a certainty.</ul>"
			+"<ul>Cultists now correctly pull up their dress as the scene describes when you return to their chapel.</ul>"
			+"<ul>Fixed tongue modifier addition/removal descriptions not being parsed correctly (if at all).</ul>"
			+"<ul>Casting spells out of combat now correctly costs mana (or health for fire spells when fire school ability is unlocked).</ul>"
			+"<ul>Fixed bug where forgetting centaurs in Dominion Express would sometimes delete the wrong centaur (which could cause Thunder to be deleted). If Thunder has been deleted in your save, they are respawned when loading into this version.</ul>"
			+"<ul>Fixed bug where saved centaurs in Dominion Express would not correctly return to the stables during the night.</ul>"
			+"<ul>Fixed bug where Natalya would act as though you'd upgraded your collar from bronze to silver the first time you visit her office after completing your training.</ul>"
			+"<ul>Fixed issue where sleeping in the stables of Dominion Express after having sex with a centaur would advance time to 00:06, instead of to 06:00.</ul>"
			+"<ul>Fixed dialogue for talking to harpies up in the Harpy Nests throwing 'dialogue not found' errors.</ul>"
			+"<ul>NPCs no longer have a chance to desire nipple-penetration actions if you have nipple-penetration content turned off (which was also affecting generated descriptions in the 'Quick sex' action).</ul>"
			+"<ul>Slaves will no longer have a sex event generated for them if you are in their tile (which should fix the issue of slaves somehow having sex with other slaves while you're having sex with them).</ul>"
			+"<ul>Fixed incorrect description when using the 'calming suggestion' sex action on a submissive partner.</ul>"
			+"<ul>Fixed bug in quick sex description for paizuri actions, where the target's suitability for performing paizuri was checked, instead of the performer.</ul>"
			+"<ul>Fixed issue with the special 'arcane allergy' perk throwing background errors when it was attempted to be added to a randomly-generated NPC.</ul>"
			+"<ul>Fixed issue with attributes sometimes being inadvertently permanently changed (which was due to status effects' attribute modifiers changing while you were under the effect of them).</ul>"
			+"<ul>Fixed broken formatting of all tooltips which displayed a small picture next to the effects list (the spacing around the icon was far too big).</ul>"
			+"<ul>Using quick sex during breeding roulette (as the breeder) now correctly results in penis-vagina actions being performed.</ul>"
			+"<ul>Fixed issue where self-transforming some of your demonic body parts into human would give you the half-demon status effect attribute modifiers, instead of retaining the demon status effect.</ul>"
			+"<ul>Fixed issue with demonic centaurs, pegataurs, unitaurs, and alitaurs not being recognised as half-demons.</ul>"
			+"<ul>Fixed bug where half-demons could be transformed into full demons by feeding them racial transformative potions (which would incorrectly be converted to demon transformations).</ul>"
			+"<ul>Fixed incorrect availability of hair/horn/ear pull in doggy-style sex.</ul>"
			+"<ul>Fixed issue with parser breaking when using the VAR/ENDVAR functionality.</ul>"
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
		credits.add(new CreditsSlot("RatKing", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("H3adShotB33otch", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("BerzerkerSteel", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Dave Ziegler", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Kaas", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Dark Miros", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("DethEagle666", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Mystic Exarch", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Lucifer", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
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
				System.err.println("Game Version: "+VERSION_NUMBER);
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
