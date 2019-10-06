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
 * @version 0.3.4.5
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
	public static final String VERSION_NUMBER = "0.3.5.1";
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
			+ "As you already most likely know, there were some annoying bugs (and a couple of major ones) in v0.3.5, so I've made this hotfix to get those addressed."
			+ " I am aware of a couple of other minor issues that are not fixed in this version, but I'll get them sorted out for the next release."
			+ " There is also the major issue of the forced-transformation content being a little buggy and underwhelming, so just to let you know, I'm planning on doing a major overhaul of that content soon (either in v0.3.5.5 or v0.3.6)."
		+ "</p>"
		
		+ "<p>"
			+ "For the next release (v0.3.5.5), I'm still aiming to get the same few things done that I previously had planned."
			+ " Firstly, I'm going to add a (very basic) day/night cycle system, along with some (very basic) routines for unique NPCs."
			+ " This will just be things like Lilaya & Rose going to their rooms at night, some shops only being open during the day, a higher chance of alleyway attacks at night, etc."
		+ "</p>"
			
		+ "<p>"
			+ "Along with that addition, I'll also be adding in Axel's quest, which will see you (finally) being able to interact with the 'Rat Warrens' tile in Submission."
			+ " As usual, I'll also be fixing bugs and making other improvements at the same time."
		+ "</p>"
			
		+ "<p>"
			+ "After that, I'll be working on the full public release of v0.3.6, which will (also finally) be focused on getting all the last parts of Dominion and Submission content finished off, so that I can start adding content for the Fields."
		+ "</p>"
			
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.5.1</h6>"
			+"<li>Contributions:</li>"
			+"<ul>Fixed bug where the 'Enforcer's tactical pants' didn't correctly conceal the sock slot. (by DSG)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Improved icon for the combat move, 'tease'.</ul>"
			+"<ul>Horns categorised as being 'small' can now be used as handles in sex (so only 'tiny' horns, of <=5cm in length, can't).</ul>"
			+"<ul>Random NPCs (such as those in alleyways) now have their inventories cleared whenever they generate a new outfit, and they gain half of the value of whatever was cleared.</ul>"
			+"<ul>You can now take/give money from/to other characters, as well as dropping it on the floor (or storing it in safe areas).</ul>"
			+"<ul>Improved outfits of slime attackers in the Bat Caverns.</ul>"
			+"<ul>Improved flow of dialogue menus when interacting with slaves and occupants, and moved renaming options from the header of all slave management dialogues into its own 'Set names' action.</ul>"
			+"<ul>Added ability to manage your companions' spells and spell upgrades via a new action in their 'Management' tab.</ul>"
			+"<ul>NPCs will now only request gentle sex if their partner is being rough with them. Also renamed 'Request gentle sex' action to 'Object to rough'.</ul>"
			+"<ul>Added 'Drop/Store all' action to the inventory menu.</ul>"
			+"<ul>Vicky now stocks considerably more bottled essences.</ul>"
			+"<ul>Added crotch-nipple penetration to sex stats, and slightly improved sex stats UI.</ul>"
			+"<ul>Added content settings for udder size and lactation increases from pregnancy, as well as udder size preferences.</ul>"
			+"<ul>In clothing enchanting, the 'sealing' and 'enslavement' enchantments are now secondary modifiers under a new 'special effects' primary modifier.</ul>"
			+"<ul>The new 'bedroom' slave job now has the same -1.0 fatigue modifier as 'idle'.</ul>"
			+"<ul>The old versions of the Enforcer uniform are now sold by Nyan.</ul>"
			+"<ul>Added hair style selection to debug self-transform's 'head' category.</ul>"
			+"<ul>Removed enchantment limits from Lyssieth's signet ring, the Slime Queen's Tiara, and the breeder collar.</ul>"
			+"<ul>Set the Slime Queen's Tiara and the Arcane Lightning Globe to have a rarity of 'unique' instead of 'legendary', and reduced the value of the Lightning Globe.</ul>"
			+"<ul>Added 'pastel rainbow' as a covering colour.</ul>"
			+"<ul>Auto-selling fluids that are milked from your slaves is now set via slave-specific job settings, instead of via a milking room setting.</ul>"
			+"<ul>You are now stripped naked when being put into the stocks (after losing in the Enforcer warehouse).</ul>"
			+"<ul>The 'Equip all' action will no longer equip jinxed items of clothing (provided that they have been identified as such). 'Unequp all' now also unequips weapons.</ul>"
			+"<ul>Added body hair and penis girth transformation effects to clothing enchantment options.</ul>"
			+"<ul>Halved chance for random characters to be prude or innocent.</ul>"
			+"<ul>The bimbo fetish now switches between 'bimbo' and 'bro', depending on if the owner of the fetish is feminine or masculine. This affects the descriptions/icon and slightly modifies the speech-changing aspect.</ul>"
			+"<ul>Slightly changed personality trait descriptions for 'lewd', 'prude', and 'innocent', to make them indicative of how a character acts, not what a character is willing to do during sex (which is what the corruption stat represents).</ul>"
			+"<ul>Added indication in map tile tooltips to let you know when if you can or can't teleport into/out of that tile.</ul>"
			+"<ul>Added spawn frequency settings for each subspecies in the 'furry preferences' options screen, and added bald-eagle-harpies and raven-harpies to this.</ul>"
			+"<ul>Added arousal bar to spectators' UI during sex.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>Health and aura percentages are now correctly maintained when losing status effects that negatively impacted maximum health or mana (such as the effects when going over maximum enchantment capacity).</ul>"
			+"<ul>Fixed issue with inconsistent 'Enchant' action visibility in the inventory dialogue.</ul>"
			+"<ul>Filled in missing job perk tooltip text for several NPCs, set Elizabeth's job to 'Lyssieth's guard' instead of 'Mugger', and filled in the missing job-related status effect for Epona.</ul>"
			+"<ul>Fixed issue with characters in the pregnancy roulette game sometimes choosing to get penetrated instead of penetrating you.</ul>"
			+"<ul>Characters being bred in pregnancy roulette can no longer use their tails to perform a tail-lock (as they are noted in the opening descriptions as being tied down).</ul>"
			+"<ul>Fixed incorrect border colouring of perks in elemental perk tree display.</ul>"
			+"<ul>You can now correctly order elementals to grow a cock during sex.</ul>"
			+"<ul>Fixed bug in sex where a character could be performing both mouth and a tongue ongoing actions simultaneously (such as a blowjob and cunnilingus).</ul>"
			+"<ul>Fixed issue with an impossible sex position, where characters could start receiving paizuri while receiving cunnilingus from the same person.</ul>"
			+"<ul>Fixed some issues with incorrect characters being targeted for sex in occupant and slave interaction actions.</ul>"
			+"<ul>Demon attackers in dark alleyway tiles now have more sensible (slutty) outfits equipped.</ul>"
			+"<ul>Fixed bug where clothing that's enchanted and then disenchanted would not stack with seemingly identical clothing that had not undergone the same process.</ul>"
			+"<ul>Fixed issue where there was a 99% chance for randomly-generated NPCs to be virgins. The chance has been adjusted to 10%.</ul>"
			+"<ul>Fixed several instances of Amber's and Zaranix's dialogue leading to you being returned to Arthur's Apartment tile, instead of to Zaranix's Home tile.</ul>"
			+"<ul>Fixed bug where half-demon fox-morphs could self-transform to give themselves a youko tail.</ul>"
			+"<ul>Fixed bug where older characters imported into this version would not have their personalities converted into the new trait system.</ul>"
			+"<ul>Fixed issue with stuttering speech sometimes causing all manner of parsing errors.</ul>"
			+"<ul>Meraxis now correctly has the 'arcane researcher' job perk, instead of 'mugger'.</ul>"
			+"<ul>Fixed incorrect penis colour being described when a character first grows a penis.</ul>"
			+"<ul>Fixed 'Fuck ass' action being available when transforming Lilaya/Meraxis into demons when you had the anal content setting disabled.</ul>"
			+"<ul>Fixed the 'Well Rested' status effect not being properly applied when resting in your room. The 'Well Rested' status effect is now also applied to all of your companions and bedroom slaves whenever you sleep in there.</ul>"
			+"<ul>The 'Soothing Waters' spell now correctly restores health.</ul>"
			+"<ul>Fixed issue with missing dialogue in the nightclub, and also an issue with partners being incorrectly identified as the submissive/dominant partner.</ul>"
			+"<ul>Jules no longer feels the need to take off his shirt when receiving a blowjob from you in exchange for skipping the line.</ul>"
			+"<ul>Multiple dildo types can no loner be simultaneously equipped (such as a strap-on and strapless dildo both equipped at once).</ul>"
			+"<ul>Fixed bug where the third tail transformation type when enchanting potions would dispplay the name for the second tail type instead. (So instead of 'tufted feline tail', it was incorrectly stating that it would give you a 'short feline tail'.)</ul>"
			+"<ul>Fixed combat behaviour (to their correct attack preferences) for: Meraxis (spells & main attacks); Fyrsia (main attacks); Hyorlyss (seduction); Jhortrax (main attacks).</ul>"
			+"<ul>Fixed issue where taur offspring were being affected by the taur furry preferences, instead of having their upper body's furryness based on their mother/father.</ul>"
			+"<ul>Fixed bug where post-sex dialogue (titled 'Step back') with companions would sometimes freeze or have content not displayed correctly.</ul>"
			+"<ul>You can now correctly interact with slaves working in the office.</ul>"
			+"<ul>Fixed bug in tooltip formatting where clothing which is able to be worn in multiple slots had the enchantment cost cut off the bottom.</ul>"
			+"<ul>The ass/anus of glory hole partners is no longer revealed to you.</ul>"
			+"<ul>Having sex in the seating area of the nightclub is now correctly limited to just the sitting down position.</ul>"
			+"<ul>Dominant partners in the nightclub will no longer drink until they collapse at the bar.</ul>"
			+"<ul>Fixed bug where Vicky would keep on replenishing essences and scrolls every time you opened her trading dialogue.</ul>"
			+"<ul>Vicky now stocks bottled essences from the very start of the game, instead of only starting to stock them after you'd started the essence quest.</ul>"
			+"<ul>The arresting guard in the enforcer warehouse now correctly moves to the entrance with you.</ul>"
			+"<ul>Fixed bug where characters could spawn in wearing two items of clothing that were blocking each other, making their removal impossible.</ul>"
			+"<ul>Crotch nipple penetration is now handled correctly and removes virginity when penetrated during sex.</ul>"
			+"<ul>Fixed the 'Perform oral' action while sitting not working correctly if it was an interaction with a taur.</ul>"
			+"<ul>Fixed daily obedience and affection gains for slaves not being displayed correctly.</ul>"
			+"<ul>Fixed issue where bimbo, muffled, or sex-modified speech could sometimes cause an error in parsing.</ul>"
			+"<ul>Fixed bug where slimes you forcibly transform into a different femininity would somehow self-transform themselves back into humans.</ul>"
			+"<ul>Fixed issue where slime offspring would be born as humans, which was caused by another bug related to offspring's gender identity being incorrectly set.</ul>"
			+"<ul>Added variations to Lilaya's pull out requests for if her speech is muffled.</ul>"
			+"<ul>Fixed bug where unemployed occupants living with you would go out to work between 9 and 5 as though they had a job.</ul>"
			+"<ul>Removing enchantment effects from a weapon or clothing now correctly removes the one that you selected, instead of the highest instance of the selected effect in the list.</ul>"
			+"<ul>Fixed issue where slaves assigned to the 'bedroom' job would not leave your room to go to their other jobs or back to their room while you were present.</ul>"
			+"<ul>Fixed issue with slave management list always returning to the tile's default dialogue instead of back to the list once you'd finished with the selected slave.</ul>"
			+"<ul>Pitch black as a secondary covering colour now has the correct shade of black on its icon.</ul>"
			+"<ul>Claire will now correctly end sex in the SWORD warehouse once you and her are satisfied, even if you restrict her control.</ul>"
			+"<ul>Fixed the Claire's sex scene in the SWORD warehouse triggering the action where an enforcer interrupts you, which was intended just for her sex scenes back in Submission.</ul>"
			+"<ul>Fixed being able to keep on having sex with Claire in the SWORD warehouse after the first time.</ul>"
			+"<ul>When unequipping weapons, any spells granted by those weapons are now correctly removed from equipped combat moves (so long as the character doesn't have them from another source).</ul>"
			+"<ul>Fixed post-offspring fight/sex dialogue acting as though you fought them in their apartment, even if you were fighting in the alleyway.</ul>"
			+"<ul>Fixed broken post-sex dialogue when having a threesome with a companion and a slave.</ul>"
			+"<ul>Fixed bug where attempting to view a companion who wasn't in the same tile as you (such as when visiting Ms. Cunningham) would cause an error to be thrown.</ul>"
			+"<ul>Fixed illogical speech from your+Lyssieth's offspring, where they'd act surprised at being related to an elder lilin.</ul>"
			+"<ul>Renamed covering colour 'dark purple' to 'violet', and renamed the old 'violet' to 'salmon-pink'.</ul>"
			+"<ul>Fixed issue where asses were being described as 'hairy' based on pubic hair, not ass hair.</ul>"
			+"<ul>Fixed bug in auto-selling of slave's milked fluids, where generated values for milk/girlcum/cum would be added more than once, resulting in an income that was far too high.</ul>"
			+"<ul>Fixed bug where characters had a chance to gain personality traits (such as lisp) every time the game was loaded. All non-unique characters have had their personality traits reset in this version to rectify the issue of everyone having so many traits due to this bug.</ul>"
			+"<ul>The 'arcane vampyrism' perk/trait now correctly needs to be equipped in order for its special effects to work in combat.</ul>"
			+"<ul>Fixed clothing 'Displacement type' information (which is displayed when clicking on an equipped item of clothing during sex) always showing 'unequip' as being available, even when it wasn't.</ul>"
			+"<ul>Fixed talons being allowed to be transformed into plantigrade or unguligrade foot structures.</ul>"
			+"<ul>Fixed cloak of flames's upgrades not applying the +1 unarmed damage per caster level.</ul>"
			+"<ul>Fixed the 'Grope breasts (self)' action being duplicated, and NPCs not being able to use this action.</ul>"
			+"<ul>Fixed speech in combat not parsing correctly.</ul>"
			+"<ul>Fixed the teleport spell's second upgrade, 'Mass Teleportation', causing the teleport status effect to be applied to your enemies instead of allies.</ul>"
			+"<ul>Fixed bug where opening the Slavery Management screens from any of the slave shops in Slaver Alley could sometimes cause the game to freeze.</ul>"
			+"<ul>Brax will no longer return to the Enforcer HQ after gaining him as a slave.</ul>"
			+"<ul>Friendly occupant slimes and demons, as well as elementals, will no longer automatically transform back into their original gender after yuo've transformed them into something else.</ul>"
			+"<ul>Fixed bug where, in the hourly slavery updates, slaves would sometimes not have left their job until after another slave had tried to move into their job-related locations, causing the job location to be left empty.</ul>"
			+"<ul>Fixed bug where upgrading rooms via the Occupancy Ledger would throw an error and stop working past the first upgrade.</ul>"
			+"<ul>When in an arcane storm, characters who are vulnerable to the storm will no longer have their lust lowered to 75 (although it will still be raised to 75 if lower).</ul>"
			+"<ul>Fixed potions which drain attributes having a default descriptor of 'boosted'.</ul>"
			+"<ul>Fixed bug where characters could masturbate when locked in stocks.</ul>"
			+"<ul>Fixed bug where message flashes would sometimes not work (such as the messages which display when quicksaving).</ul>"
			+"<ul>Rose now only ends her handholding sex scene once she's had enough orgasms to be satisfied.</ul>"
			+"<ul>Fixed some issues with the dialogue related to slaves using you while sleeping in your bedroom.</ul>"
			+"<ul>NPC spectators can now orgasm during sex.</ul>"
			+"<ul>Submissive gloryhole NPCs will no longer get stuck trying to remove their own clothing.</ul>"
			+"<ul>Fixed incorrect description of Fire spell school's passive buff.</ul>"
			+"<ul>Clicking on 'derived fetish' icons in the fetish screen no longer spends 5 essences to achieve nothing.</ul>"
			+"<ul>Fixed issue where Lilaya would sometimes demand a pullout during sex even if you weren't penetrating her vagina.</ul>"
			+"<ul>Fixed penis head descriptors not working correctly.</ul>"
			+"<ul>Fixed issue where slaves having sex with each other (or while doing their job) would only have sexual fluids splattered on them if they were allowed to be impregnated.</ul>"
			+"<ul>Fixed bug where successfully tricking Brax would have the final dialogue repeat the previous section of text, rahter than the correct 'success' variation.</ul>"
			+"<ul>Fixed some incorrect titles of orgasm actions where the character was cumming on their own body parts.</ul>"
			+"<ul>NPCs will no longer ask for their partners to pullout during sex if they also have the action to request creampie available to them (so they won't keep on switching between the two with every orgasm).</ul>"
			+"<ul>Fixed issue where the world map marker would not update correctly when fast-travelling.</ul>"
		+ "</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added a teleportation-related quest to Claire's content, which is available to start at any time via talking to her about teleportation.</ul>"
			+"<ul>Added a repeatable sex scene with Claire that can be accessed after completing her teleportation quest.</ul>"
			+"<ul>Overhauled the old, rather vague, personality system into one based on well-defined personality traits (which will make it a lot easier for me to factor them into dialogue variations in the future).</ul>"
			+"<ul>Old personalities are converted into the new system upon loading into this version, but if you want to save-edit it to something more suitable for your character, then you can add the traits in <trait> tags within a cahracter's <personality> tag. Values can be found here: https://github.com/Innoxia/liliths-throne-public/blob/dev/src/com/lilithsthrone/game/character/persona/PersonalityTrait.java</ul>"

			+"<li>DSG's Enforcer Clothing & Weapon Contributions:</li>"
			+"<ul>Added a huge amount of Enforcer-related clothing (48 unique items of clothing), created by 'DSG'.</ul>"
			+"<ul>Added 9 new Enforcer-related weapons (2 melee and 7 ranged), also all created by 'DSG'.</ul>"
			+"<ul>The full list of new clothing and weapons is at the end of these patch notes.</ul>"

			+"<li>Slavery:</li>"
			+"<ul>Added 'office' room upgrade, along with 'office worker' jobs for slaves, and moved the 'Slave List' action from your room into the office (and renamed it to 'Occupancy ledger').</ul>"
			+"<ul>The occupancy ledger now only tracks the last 7 days (to avoid save file bloat), and no longer gets spammed full of 'placeholder event' entries.</ul>"
			+"<ul>Milking events have been consolidated into one event per hour. (Previously saved milking event entries will only display milk collected, but events generated from this version onwards will correctly display milk, crotch-milk, girlcum, and cum.)</ul>"
			+"<ul>Changed job assignment mechanics to support a different (or the same, if you want) job being assigned in each of the 24 hour time slots.</ul>"
			+"<ul>Slightly improved the slave job assignment UI as part of this change.</ul>"
			+"<ul>Tidied up the slave permissions and job settings menus to reduce the amount of scrolling needed to access all permissions.</ul>"
			+"<ul>Exercise and diet permissions now have a targeted size that you want your slave to be at, instead of perpetually increasing/decreasing their muscle and body size each day until switched back to the 'average' setting.</ul>"
			+"<ul>Added 'fatigue' value to jobs, and changed the 'overworked' status effect into three effects of increasing severity, which are gained at 1->9, 10->19, and 20+ daily fatigue.</ul>"
			+"<ul>Added a 'behaviour' permissions category, allowing you to set how slaves should act towards you.</ul>"
			+"<ul>Added 'bedroom' job for slaves, allowing you to assign them to wait on you and sleep with you in your bedroom.</ul>"

			+"<li>Other:</li>"
			+"<ul>Slightly improved the description of your room and added a couple of room upgrades for it.</ul>"
			+"<ul>Characters with the denial fetish now lose affection if you end sex with them without giving them or denying them at least one orgasm.</ul>"
			+"<ul>Increased potion effect corruption gain from drinking Lilith's Gift (+1 corruption) and Impish Brew (+5 corruption) to +25 and +50 corruption, respectively.</ul>"
			+"<ul>Put all fast travel actions inside of Lilaya's Home into a separate tab, which can now be accessed anywhere inside her house.</ul>"
			+"<ul>Unified icon colour of all the game's laboratory tiles (Lilaya's, Zaranix's, and the one in the Imp Citadel) to lime green.</ul>"
			+"<ul>NPCs now prefer to remove clothing in chest slots during sex (i.e. bras), rather than just displacing them.</ul>"
			+"<ul>Gave Brax the background perk 'dirty minded' to give him a level of corruption to suit his disposition.</ul>"
			+"<ul>Quest progress updates are now added to the event log.</ul>"
			+"<ul>Added red border around equipped weapon icons which do not have enough essences to use, added a wanring in the tooltip that this is the case, and added an indication of how many essences a character has in their attribute information tooltip (when hovering over their name in the left or right panel).</ul>"
			+"<ul>Added unisex/feminine/masculine indication in clothing tooltips, and melee/ranged indication in weapon tooltips.</ul>"
			+"<ul>Maps not yet visited/discovered are no longer displayed as a '???' action in the map screen.</ul>"
			+"<ul>Updated Brax's, Claire's, and Candi's Enforcer uniforms to use the new Enforcer clothing from DSG. When buying Brax/Bree, they will now have their uniform in their inventory. If Brax/Bree is already your slave, they will have their new uniform in their inventory when loading into this version.</ul>"
			+"<ul>Tidied up Brax's dialogue, changed reference of his title of 'Chief of Dominion Operations' to just being an 'Inspector', and moved all his dialogue into external res files.</ul>"
			+"<ul>Improved 'heels' icon, and made some very minor changes to colouration of some other footwear.</ul>"
			+"<ul>The old Enforcer clothing is now part of a 'slutty Enforcer' set, and provides lust damage bonuses.</ul>"
			+"<ul>Added teleportation restrictions to certain areas and tiles (which are ignored if you are using debug mode).</ul>"
			+"<ul>Inventory weapon descriptions now display the weapon's essence cost to fire, as well as whether it's melee/ranged and one/two handed.</ul>"
			+"<ul>Expanded Enforcer Headquarters map to include some cells for Claire's (and future) content. (You only see it as an alternative defeat scene if non-con is off.)</ul>"
			+"<ul>Improved flow of dialogue when interacting with Claire.</ul>"
			+"<ul>NPCs are now only half as likely to use tease attacks against enemies they are not attracted to.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Parsing/grammar/typo fixes.</ul>"
			+"<ul>Fixed Dominion street tiles which have a special location on them taking 5 minutes to traverse instead of the usual 2.</ul>"
			+"<ul>Fixed the 'Grope breasts (self)' sex action not being correctly treated as a self-performing action.</ul>"
			+"<ul>Non-sex actions which have requirements (such as several of the actions when interacting with Brax) should now correctly be disabled if you don't meet the requirements.</ul>"
			+"<ul>Fixed issue where the game could softlock when one of your slaves ambushed you to have sex with you.</ul>"
			+"<ul>Fixed a cause of soft-locking in sex scenes (was definitely affecting Rose's sex scene, and probably other ones too in some rare cases).</ul>"
			+"<ul>Brax's perks are no longer automatically assigned when you reset his perk tree.</ul>"
			+"<ul>Fixed incorrect Brax's description incorrectly stating that he'd been turned into a bimbo wolf-girl if you hadn't feminised or bimboified him.</ul>"
			+"<ul>Fixed several issues with the availability requirements and damage calculations for the 'fertility', 'virility', 'dominant' and 'submissive' fetish-unlocked special tease attacks.</ul>"
			+"<ul>The 'Object of desire' perk now correctly applies the '+1 orgasms before satisfied' effect to half-demons, demons, and imps.</ul>"
			+"<ul>Amber no longer ends sex after orgasming once, and will now keep on going until she's fully satisfied.</ul>"
			+"<ul>Characters in glory hole sex scenes will no longer pointlessly displace/remove their clothing.</ul>"
			+"<ul>Post-sex affection changes now correctly display why NPCs are losing affection towards other sex participants.</ul>"
			+"<ul>Fixed bug where randomly generated NPCs could sometimes have clothing equipped into mutually exclusive slots (such as overbust corset and bras being equipped together), which would then result in the items of clothing blocking the removal of one another.</ul>"
			+"<ul>Starting a game with an imported character no longer resets their modified age appearance.</ul>"
			+"<ul>Fixed numerous background errors being thrown when starting a game with an imported character, and fixed issue where imported characters wouldn't have their position set to be in the museum.</ul>"
			+"<ul>Starting a game with an imported character now correctly resets tracking of worlds visited, friendly occupants, quests, and characters encountered.</ul>"
			+"<ul>Fixed bug where scenes with more then four participants would only have positioning menu slots available as though there were only two participants.</ul>"
			+"<ul>Fixed issue where error log would get notifications of two pieces of clothing 'blocking one another's removal', even if they weren't at all.</ul>"
			+"<ul>Fixed bug where attempting to equip clothing that conflicted with slots already used by other currently-equipped clothing would cause numerous errors and a softlock.</ul>"
			+"<ul>Fixed issue with unnecessary whitespace characters being present in outfit xml files.</ul>"
			+"<ul>Fixed issue where clothing with a mix of good and bad enchantments would always be considered 'bad', even if the overall net result of the enchantments was positive.</ul>"
			+"<ul>Fixed half-demon youko icons not being displayed correctly.</ul>"
			+"<ul>Fixed bug where the 'characters present' button would not work.</ul>"
			+"<ul>Fixed issue where using fast travel would not increment time until after you'd already arrived, resulting in your destination sometimes not correctly displaying the correct dialogue.</ul>"
			+"<ul>Fixed move cooldown tooltip descriptions always displaying information based on your stats and equipment, rather than the actual character who has the cooldown applied to them.</ul>"
			+"<ul>Fixed bug where you could remove a milking room while slaves were being milked in there.</ul>"
			+"<ul>Fixed a related bug where slaves would continue treating a room as a milking room even after you'd removed the milking room upgrade (so tehy'd keep travelling back to the removed milking room's location in order to get milked).</ul>"
			+"<ul>Slaves will no longer generate job events, have their affection/obedience affected by their job, nor earn income if they are not able to reach their job destination (typically caused by removal of their job's room).</ul>"
			+"<ul>Fixed issue that was causing NPCs to sometimes pull out when orgasming even if you were using a method of forcing creampie (such as leg-locking).</ul>"
			+"<ul>Fixed bug where swapping sex actions (such as going directly from getting fingered to being penetrated) sometimes wouldn't work.</ul>"
			+"<ul>Fixed issue where a fully upgraded 'ice shard' spell would not apply the critical hit stun effect when used more than once in a single turn.</ul>"
			+"<ul>Fixed clothing names having the incorrect enchantment postfix appended to them, and fixed negative enchantments on clothing not correctly identifying them as 'bad' items of clothing.</ul>"
			+"<ul>Getting a character to grow a cock during sex (or growing one yourself) via the 'Grow cock' action now correctly recalculates all NPCs' sex preferences (so they might decide to use the new cock available to them).</ul>"
			+"<ul>Fixed issue where buying/selling elixirs would turn it into a blank elixir.</ul>"
			+"<ul>Fixed issue where removing a dildo which was involved in an ongoing penetration would cause an error to be thrown and the sex scene to then display odd behaviour.</ul>"
			+"<ul>Fixed bug where you were able to remove Rose's clothing in the post-panty masturbation sex scene with her.</ul>"
			+"<ul>Fixed issue with randomly generated characters pawning in with same-coloured heterocromatic eyes.</ul>"
			+"<ul>Arcane vampyrism's effects now work correctly.</ul>"
			+"<ul>Fixed self-TF menu's crotch boob lactation regeneration setting incorrectly affecting the character's main breasts lactation regeneration.</ul>"
			+"<ul>Fixed unidentified clothing's 'Unknown' effects header being coloured corresponding to the rarity of the clothing (which was making it possible to tell if clothing was jinxed or not without identifying it).</ul>"
			+"<ul>Fixed issue in combat where moves which applied status effects would keep on adding to that status effect's duration, instead of refreshing it to the standard applied duration.</ul>"
			+"<ul>Fixed Jolnir's coat not concealing torso slots, and the antler headband having an incorrect description.</ul>"
			+"<ul>Weapons which are part of a set now count towards gaining the set bonus if they are all equipped in jut main or just offhand weapon slots (only applicable to characters with multiple arms).</ul>"
			+"<ul>Fixed bug where any character other than the player having the 'Aura Burn' status effect in combat would cause combat to bug out and softlock.</ul>"
			+"<ul>Altered the way the game handles kissing, so that when kissing another character in sex, the ongoing status effect (showing tongue in mouth) and all related arousal effects are applied to both of you.</ul>"
			+"<ul>Items which cannot be sold (i.e. unique quest items) will now correctly have their icon greyed-out in your inventory when interacting with a shopkeeper.</ul>"
			+"<ul>Fixed ongoing sex action tooltips not taking into account whether the action was being self-performed.</ul>"
			+"<ul>Fixed all combat moves not triggering special health damage effects (from sadist or masochist fetishes).</ul>"
			+"<ul>Fixed debug menu's 'All clothing' action (in the 'Item view' tab) not working.</ul>"
			+"<ul>Fixed inverted sex action bypass content setting (i.e. it was allowing sex action bypass when turned off, and disallowing it when turned on).</ul>"
			+"<ul>Fixed bug where Brax sometimes wouldn't be moved to Candi's desk after completing his part of the main quest.</ul>"
			+"<ul>Fixed bugs in Candi's dialogue, where the game would always display the initial Brax reaction scene, and also where Candi would always react to her pregnancy as though it was the first time you seeing it. Also slightly improved flow of her dialogue.</ul>"
			+"<ul>Brax now correctly loses affection towards you after completing his section of the main quest.</ul>"
			+"<ul>Fixed bug in pathing where it was possible to walk over impassable tiles.</ul>"
			+"<ul>Spell books no longer incorrectly state that they give +10 elemental damage.</ul>"
			+"<ul>Fixed bug where you sometimes had no orgasm actions available.</ul>"
			+"<ul>Fixed incorrect subspecies being present in the Submission Enforcer posts.</ul>"
			+"<ul>Fixed Submission's imp attackers spawning with no essences with with to fire their bows.</ul>"
			+"<ul>Fixed issue with Meraxis and Lilaya having incorrect orifice and lip colours after becoming full demons.</ul>"
			+"<ul>Using 'all-out strike' is no longer possible when you only have two arms and are using a two-handed weapon.</ul>"
			+"<ul>Fixed some very minor appearance issues with Lyssieth.</ul>"
			+"<ul>Fixed bug where slaves would only be milked once each time you rested, instead of once per hour for the amount of hours spent resting.</ul>"
			+"<ul>The 'Ass-to-mouth' and 'Pussy-to-mouth' orgasm actions are not correctly marked as being sadistic.</ul>"
			+"<ul>Fixed bug with the race books in the library not showing their contents correctly.</ul>"

			+"<li>DSG's Enforcer Clothing Contributions:</li>"
			+"<ul>The full list of DSG's Enforcer clothing (categorised by inventory slot) is as follows:</ul>"
			+"<li>Head slot:</li>"
			+"<ul>Enforcer's bowler hat (Feminine).</ul>"
			+"<ul>Enforcer's cap (Masculine).</ul>"
			+"<ul>Enforcer's riot helmet (Unisex).</ul>"
			+"<ul>Enforcer's beret (Unisex).</ul>"
			+"<ul>ORICL beret (Unisex).</ul>"
			+"<ul>SWORD beret (Unisex).</ul>"
			+"<ul>Enforcer's combat helmet (Unisex).</ul>"
			+"<li>Eyes slot:</li>"
			+"<ul>Ballistic glasses (Unisex).</ul>"
			+"<ul>Enforcer's ballistic goggles (Unisex).</ul>"
			+"<ul>Enforcer's night vision goggles (Unisex).</ul>"
			+"<li>Mouth slot:</li>"
			+"<ul>Enforcer's gas mask (Unisex).</ul>"
			+"<li>Neck slot:</li>"
			+"<ul>Enforcer's riot neck protector (Unisex).</ul>"
			+"<li>Over-torso slot:</li>"
			+"<ul>Enforcer's stabproof vest (Unisex).</ul>"
			+"<ul>Enforcer's riot armour (Unisex).</ul>"
			+"<ul>Enforcer's Chief Superintendent's coat (Unisex).</ul>"
			+"<ul>Enforcer's Superintendent's coat (Unisex).</ul>"
			+"<ul>Enforcer's Inspector's coat (Unisex).</ul>"
			+"<ul>Enforcer's Constable's coat (Unisex).</ul>"
			+"<ul>Enforcer's greatcoat (Unisex).</ul>"
			+"<ul>Commando sweater (Unisex).</ul>"
			+"<ul>Enforcer's commando sweater (Unisex).</ul>"
			+"<ul>V-necked commando sweater (Unisex).</ul>"
			+"<ul>Heavy Enforcer's plate carrier (Unisex).</ul>"
			+"<ul>Enforcer's plate carrier (Unisex).</ul>"
			+"<ul>Brax's Enforcer Inspector coat (Unisex).</ul>"
			+"<ul>Enforcer's Constable's coat (Unisex).</ul>"
			+"<ul>Candi's Enforcer Constable coat (Unisex).</ul>"
			+"<ul>Claire's Enforcer Sergeant coat (Unisex).</ul>"
			+"<ul>Claire's stabproof vest (Unisex).</ul>"
			+"<li>Torso slot:</li>"
			+"<ul>Enforcer's long-sleeved blouse (Feminine).</ul>"
			+"<ul>Enforcer's short-sleeved blouse (Feminine).</ul>"
			+"<ul>Enforcer's long-sleeved shirt (Masculine).</ul>"
			+"<ul>Enforcer's short-sleeved shirt (Masculine).</ul>"
			+"<ul>Combat shirt (Unisex).</ul>"
			+"<ul>Enforcer's combat shirt (Unisex).</ul>"
			+"<li>Wrists slot:</li>"
			+"<ul>Enforcer's riot arm guards (Unisex).</ul>"
			+"<ul>Enforcer's tactical elbow pads (Unisex).</ul>"
			+"<li>Hips slot:</li>"
			+"<ul>Enforcer's utility belt (Unisex).</ul>"
			+"<ul>Enforcer's riot belt (Unisex).</ul>"
			+"<ul>Enforcer's heavy battle belt (Unisex).</ul>"
			+"<ul>Enforcer's battle belt (Unisex).</ul>"
			+"<ul>Enforcer's dress belt (Unisex).</ul>"
			+"<ul>Enforcer's stable belt (Unisex).</ul>"
			+"<li>Legs slot:</li>"
			+"<ul>Enforcer's tactical trousers (Unisex).</ul>"
			+"<ul>Enforcer's uniform skirt (Feminine).</ul>"
			+"<ul>Enforcer's slacks (Unisex).</ul>"
			+"<ul>Enforcer's dress slacks (Unisex).</ul>"
			+"<li>Feet slot:</li>"
			+"<ul>Enforcer's jackboots (Unisex).</ul>"
			+"<ul>Enforcer's pumps (Feminine).</ul>"
			+"<ul>Tactical combat boots (Unisex).</ul>"
			+"<li>Ankles slot:</li>"
			+"<ul>Enforcer's riot shin guards (Unisex).</ul>"
			+"<ul>Enforcer's tactical kneepads (Unisex).</ul>"

			+"<li>DSG's Enforcer Weapon Contributions:</li>"
			+"<ul>The full list of DSG's Enforcer weapons (categorised by melee/ranged) are as follows:</ul>"
			+"<li>Melee slot:</li>"
			+"<ul>Enforcer's baton.</ul>"
			+"<ul>Enforcer's riot shield.</ul>"
			+"<li>Ranged slot:</li>"
			+"<ul>Cryoprism gun.</ul>"
			+"<ul>LSG-9000 stun gun.</ul>"
			+"<ul>Pepper grenade launcher.</ul>"
			+"<ul>Pepperball pistol.</ul>"
			+"<ul>Pepperball rifle.</ul>"
			+"<ul>Arcane taser.</ul>"
			+"<ul>Revolving tranquiliser rifle.</ul>"
		+ "</list>"

		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.4.6</h6>"
			+"<li>Other:</li>"
			+"<ul>Slightly tweaked sex action preference weighting, so that an action which has both a 'liked' and 'disliked' fetish associated with it will now have a total slightly negative weighting, so it won't be chosen.</ul>"
			+"<ul>Slightly changed the underwear worn by Alexandria (the prologue character in the museum).</ul>"
			+"<ul>The 'sadistic' sex actions ('degrading talk', 'choke', 'slap face', and 'spit in face') now apply a significant hit to the targeted character's lust if that targeted character doesn't have a positive desire for the 'masochist' fetish.</ul>"
			+"<ul>Provided you have enough control in the sex scene, you can now start ongoing actions with a partner's sex area even if they are using that sex area. (i.e. If your partner is fingering themselves, you can now start cunnilingus on them, which will automatically get them to stop fingering themselves.)</ul>"
			+"<ul>After selecting an orgasming action in sex, the game now restores your targeted character to the person you were targeting before orgasm, and puts you back into the tab you were on before (instead of putting you back into the default 'Misc. actions' tab after every orgasm).</ul>"
			+"<ul>Reformatted the stats screen to make it more readable.</ul>"
			+"<ul>Added 'Grope breasts (self)' sex action, allowing characters to grope their own breasts during sex.</ul>"
			+"<ul>Removed the stopwatch icon overlay on combat move cooldown status effects, to make it clearer which effects are on cooldown.</ul>"
			+"<ul>Added indication to clothing TF tooltips to make it clearer if the TF is applied to breasts or crotch boobs.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes, including fixes to incorrect characters being referenced in all multiple-ongoing sex actions (such as vaginal/anal double-penetration).</ul>"
			+"<ul>Fixed clover clamps and realistic dildo being able to be equipped onto/into a character's non-existent vagina.</ul>"
			+"<ul>Fixed issue where sex would softlock when a character was orgasming in the 'dominant standing' position.</ul>"
			+"<ul>Characters facing a wall can no longer grope the breasts of the person standing behind them.</ul>"
			+"<ul>Fixed issues with buying items, weapons, and clothing from vendors, where instead of the purchased item being placed into your inventory, it was being put into the buyback menu.</ul>"
			+"<ul>Fixed bug where trying to equip a condom while in Ralph's trade dialogue would cause an error to be thrown.</ul>"
			+"<ul>Fixed Lilaya's panties masturbation scene missing its unique panty-related actions.</ul>"
			+"<ul>Fixed Nyan's images displaying her 'gift' ones (which were intended for a later scene, and have been moved into a new 'NyanSpecials' folder).</ul>"
			+"<ul>You are no longer affected by the restricted areas you've set for any of your slaves in the public stocks. (So while members of the public will be unable to use their restricted areas, you can always do whatever you like with them.)</ul>"
			+"<ul>Fixed issue where exporting characters would set their exported birth year as 3 years later than it actually is.</ul>"
			+"<ul>Fixed issue where NPCs would generate new special perks every time you loaded a game.</ul>"
			+"<ul>Fixed issue where resetting perk trees would remove the 'core' perks at the top of each column, making it impossible to select any new perks.</ul>"
			+"<ul>Fixed some issues which would rarely cause NPCs to use incorrect actions when in group sex.</ul>"
			+"<ul>Fixed start double penetration actions being displayed when targeting the character who's already fucking you.</ul>"
			+"<ul>Fixed several issues with item, clothing, and weapon usage in masturbation scenes.</ul>"
			+"<ul>Fixed weapons in inventory not being displayed as disabled during sex.</ul>"
			+"<ul>Fixed issue with NPCs getting stuck trying to remove clothing in sex. (An example of this was NPCs not being able to figure out how to remove a corset dress while wearing a women's leather jacket.)</ul>"
			+"<ul>Fixed bug where removing a character (via the 'Kick out' action for friendly occupants, for example) who was your companion would block you from adding any new companions to your party.</ul>"
			+"<ul>Fixed characters face-down over a desk being able to grope the breasts of the person behind them.</ul>"
			+"<ul>Fixed issues with Brax's sex scenes sometimes using incorrect penetration descriptions.</ul>"
			+"<ul>Fixed your name being displayed as 'You' in your character UI box during sex.</ul>"
			+"<ul>Fixed sex with slaves/companions sometimes being erroneously described as 'rape' in the action title.</ul>"
			+"<ul>Fixed issues with companion sex always acting as though they were your slave.</ul>"
			+"<ul>Fixed issue where ongoing double penetration could sometimes result in the receiving orifice being described as continuously stretching, even if it was already stretched out to the maximum size.</ul>"
			+"<ul>Fixed the rough and normal variations of clothing displacements being switched, so the game would display non-rough displacement descriptions when you were in the rough pace and vice-versa.</ul>"
			+"<ul>Characters who have a negative desire towards the pregnancy fetish will no longer force others to creampie them.</ul>"
			+"<ul>Fixed elemental spawning during combat causing combat to break.</ul>"
			+"<ul>Fixed default reindeer penises being flared instead of tapered.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.4.5</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Fixed bug in lying down position where when using reverse face-sitting, the sex scene would sometimes break. (PR#1200 by Sphilia)</ul>"
			+"<ul>Fixed bug where quadruple slave room upgrades would instead be showing upgrades for the double slave room. (PR#1196 by EPSIL0N)</ul>"
			+"<ul>Added support for clover clamps and realistic dildo to be equipped in sex, and to not conceal the vagina. Also added support for slaves to gain experience from working at their job. (PR#1201 by Norin)</ul>"
			+"<ul>Fixed issues with slave/occupant selection when choosing who to have sex with in rooms in Lilaya's house. (PR#1205 by EPSIL0N)</ul>"

			+"<li>Items:</li>"
			+"<ul>Renamed 'knightly sword' to 'arming sword', and added a sheathed icon for when it's not equipped.</ul>"

			+"<li>Combat:</li>"
			+"<ul>All characters involved in combat now perform their moves simultaneously (so even if you defeat an enemy, they still get to perform their selected moves). If combat results every participant being defeated, it counts as your victory.</ul>"
			+"<ul>Instead of having only 8 combat moves being available at once, you can now select 8 'core' combat moves (which can be selected/deselected just like before), while moves which are not designated as 'core' are still available during combat, but suffer a +1 AP cost, and also have a +1 turn cooldown.</ul>"
			+"<ul>Added 'commands' tab in combat, which contains actions that allow you to give your companions attack-type orders. (The possible commands are: balanced, attack, defend, seduction, spells, and support.)</ul>"
			+"<ul>Fixed numerous issues with item usage during combat. Using an item now costs 1 AP, and items can no longer be used while defeated or stunned.</ul>"
			+"<ul>Removed ability to manipulate clothing and weapons in combat, as it was causing several issues with the underlying mechanics. (I'll try to enable this again at some point when I have time to add proper support and actions for it all.)</ul>"
			+"<ul>Added ability to equip a weapon in each one of your hands, based on how many arm rows you have, up to a total of 6 weapons at once. (The inventory UI changes layout to show a character's extra weapon slots only if they have more than one arm row.)</ul>"
			+"<ul>Renamed the combat move 'twin strike' to 'all-out strike'.</ul>"
			+"<ul>'Strike', 'Offhand strike', and 'All-out strike' now have their AP cost equal to how many arm rows you have, with a maximum AP of 3.</ul>"
			+"<ul>'All-out strike' no longer has any damage reductions on the weapons involved, but has a cooldown of two turns (including the turn in which it's used).</ul>"
			+"<ul>'Arcane strike' no longer uses your primary weapon to calculate damage, and instead deals lust damage to your target equal to 10% of the aura you recover. Aura recovery is calculated by: 2*level</ul>"
			+"<ul>NPCs will no longer use the 'bite' special attack if their mouth is blocked, nor any special tease attacks if they are completely ineffective (either due to missing aprts or the enemy not liking the associated fetish).</ul>"
			+"<ul>Buffed fetish tease attack critical damage, making it a lot more effective than regular critical tease attacks. (The non-crit damage is still slightly weaker than regular tease attacks.)</ul>"

			+"<li>Other:</li>"
			+"<ul>Added 'German-shepherd-morphs' as a subspecies of dog-morph. A character needs upright dog ears, as well as fluffy black fur with tan markings to be identified as a German-shepherd-morph.</ul>"
			+"<ul>Characters no longer respond to spectating characters' encouragements to pullout or creampie, and you, the player, also no longer responds to these encouragements from anybody (as the game doesn't know if you're going to pull out or not...).</ul>"
			+"<ul>Added kissing actions to cowgirl position, and breasts-to-mouth interactions for missionary.</ul>"
			+"<ul>The heavy muffling in sex (where all words are replaced with '~Mrph~' noises) is now only caused if the character is wearing clothing which impedes speech, or if the character is performing oral on a penis, tail, or tentacle. (So kissing no longer causes speech to be totally muffled.)</ul>"
			+"<ul>Dirty talk and other similar speech-type sex actions are now available even if the character is performing oral, so long as it's not on a penis, tail, or tentacle.</ul>"
			+"<ul>Added a 'face between legs' slot to the lying down position, so characters can now perform oral on characters who are lying down.</ul>"
			+"<ul>Imps now have a significant lust damage debuff (to represent both their uncouth mannerisms and their reputation as being on the very bottom rung of Dominion society).</ul>"
			+"<ul>Obedient slaves now have appropriate descriptions when you give them an item to consume/use.</ul>"
			+"<ul>Increased the chance of finding 'Glowing Mushrooms' when exploring the 'Bioluminescent Cavern' tiles.</ul>"
			+"<ul>Added icons to responses in sex to indicate if they're going to swap ongoing actions or have you join in as an additional ongoing character.</ul>"
			+"<ul>Slightly improved ordering of sex actions, based on their associated pace.</ul>"
			+"<ul>Improved buyback menu to correctly handle stacks of sold items/clothing/weapons.</ul>"
			+"<ul>Slightly improved flow of dialogue in Candi's scenes.</ul>"
			+"<ul>Added in variations of orgasm requests and denials for characters who have muffled speech.</ul>"
			+"<ul>Converted breeding stall position into new positioning format.</ul>"
			+"<ul>Increased value of slaves who still have their virginity.</ul>"
			+"<ul>Slightly rebalanced NPCs' sex action preference weighting, so it's now a little more likely that NPCs will prefer to use non-vaginal or anal actions in sex (although those two remain weighted heavier than other actions).</ul>"
			+"<ul>Pregnancy status effects now apply a small reduction in maximum health.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed several parsing errors and typos.</ul>"
			+"<ul>Fixed formatting and description errors in ongoing double-penetration action text (for both vaginal and anal).</ul>"
			+"<ul>Fixed bug where the 'Characters Present' screen would sometimes throw an error if you had no companion in your party.</ul>"
			+"<ul>Fixed issue with the 'Dominate' action in the Father's day content's threesome scene not working, and a related issue with the reverse cowgirl position causing sex to sometimes break.</ul>"
			+"<ul>The 'Encourage Pullout' action is no longer available if the orgasming character is currently unable to pull out. (i.e. If they're leg-locked, tail-locked, etc.)</ul>"
			+"<ul>Fixed combat bug where the offhand attack was calculating damage based on the weapon you had in your main hand.</ul>"
			+"<ul>Fixed issues with Brax being your slave, where his/her clothing would re-equip itself after sex, and several descriptions related to him/her acting as though he was still at the Enforcer Headquarters. Also fixed issues with his/her sex paces and perk tree not working correctly.</ul>"
			+"<ul>Fixed bug in sex where a character could end up using their tongue and mouth on different areas at the same time (such as performing cunnilingus while also sucking on a tail).</ul>"
			+"<ul>Fixed positioning bug where a character could access a slot that would normally be banned once they'd moved out of their currently-assigned slot.</ul>"
			+"<ul>Fixed some issues with the positioning menu which were resulting in broken parsing and sometimes causing the game to freeze.</ul>"
			+"<ul>Fixed bug where entering the positioning menu would sometimes put characters in slots that shouldn't have been available to them.</ul>"
			+"<ul>Fixed bug where post-combat sex actions were sometimes disabled, even if the dialogue indicated that the NPC wanted to have sex with you.</ul>"
			+"<ul>Rough sex action that target yourself (such as rough self-fingering) now correctly have the masochist fetish associated with them.</ul>"
			+"<ul>NPCs will no longer use self-targeting sex actions which take their own virginity (they will still use actions that take their virginity if they are interacting with another partner).</ul>"
			+"<ul>Rose should no longer self-penetrate with her tail in her sex scene.</ul>"
			+"<ul>Leg transformations into taurs (via TF potions or the self-TF demon/slime menu) will no longer change the attributes of lower body parts (such as penis size, vagina capacity, etc.). It *will* still change all lower body parts into the corresponding race of the leg type, however.</ul>"
			+"<ul>Lyssieth's speech can no longer be affected by any drunk status effect she might have obtained while in her vision scene.</ul>"
			+"<ul>Dominion attackers which spawn during a storm on non-alleyway tiles will no longer have the 'talk' action available after defeating them, which was causing them to get permanently stuck as non-interactive NPCs on that tile. NPCs stuck in this manner will be automatically cleaned up when loading into this version.</ul>"
			+"<ul>Fixed Ms. Cunningham's sex preferences when in the scene in which she's in the 'over-desk' position.</ul>"
			+"<ul>Enchantment capacity cost is no longer displayed for unidentified clothing.</ul>"
			+"<ul>Fixed error with dates displaying 11th, 12th, and 13th as 11st, 12nd, and 13rd.</ul>"
			+"<ul>Fixed telepathic seduction always being described as affecting you, even if it was an NPC targeting another NPC.</ul>"
			+"<ul>Repositioning when in a 'stocks' or 'milking stall' sex position no longer moves the characters locked into the stocks into a spectator slot.</ul>"
			+"<ul>Fixed bug where equipped/unequipped weapon icons would sometimes have gradients incorrectly coloured.</ul>"
			+"<ul>Fixed issue with the demon dagger's icon. </ul>"
			+"<ul>Fixed bug in combat where NPCs would have their cooldowns reduced by 2 turns each turn instead of by 1.</ul>"
			+"<ul>Jhortrax and Fyrsia (the imp fortress leaders) now correctly prefer to use melee attacks rather than trying to seduce you in combat.</ul>"
			+"<ul>Fixed issue in sex where two characters couldn't be simultaneously giving one another blowjobs (only noticeable in the 69 position).</ul>"
			+"<ul>Fixed issues with incorrectly configured sex area interactions when in the sixty nine position.</ul>"
			+"<ul>Fixed some old position-related actions no longer being available for use (such as slap ass & position changing actions in stocks scenes, and some other minor ones in others).</ul>"
			+"<ul>Restored some old position-related orgasm climax descriptions which were accidentally removed as part of the sex positioning refactor work.</ul>"
			+"<ul>Spells which critically hit now correctly have the duration of any applied status effects doubled. Status effect duration is also now correctly doubled if the caster has the 'Arcane Composition' perk (from the musician background).</ul>"
			+"<ul>Fixed fire spells performing critical hits when they were the only spell selected, instead of the intended condition of the caster being under 25% health.</ul>"
			+"<ul>Fixed numerous damage calculation errors in all special attacks derived from anthropomorphic body parts.</ul>"
			+"<ul>Fixed a few parsing and description errors related to the combat move display screen.</ul>"
			+"<ul>Fixed incoming tease damage not being correctly converted to health/mana damage when under the effect of the 'desperate for sex' status effect.</ul>"
			+"<ul>Applied temporary fix to status effect tooltip descriptions not wrapping around onto the next line (which was causing them to trail off outside of the tooltip). This might cause other slight formatting issues with status effect tooltips until I can get the time to come back and fix it properly.</ul>"
			+"<ul>Fixed 'futanari testicles' content option incorrectly being applied to just feminine characters instead of just characters with a vagina.</ul>"
			+"<ul>Characters who dislike the pregnancy fetish will no longer force their partners to cum inside their vagina.</ul>"
			+"<ul>The offspring map can now be used on tiles which already have one character on them.</ul>"
			+"<ul>If multiple characters are on the same tile (such as a random alleyway attacker and one of your offspring spawned via the offspring map), encounters on that tile will now be randomly chosen from all characters present, instead of being the same character every time.</ul>"
			+"<ul>Fixed issue where randomly generated hostile characters (such as those on alleyway tiles, submission's tunnels, harpy nests, etc.) who were spawned in an older version would not have their location correctly set as their home location.</ul>"
			+"<ul>Fixed bug where some items were displaying incorrect equip/displace/unequip text. (This was also causing the effects of some modded clothing to not work.)</ul>"
			+"<ul>Spectators in sex can no longer manage the clothing of the actual participants.</ul>"
			+"<ul>Fixed incorrect descriptions in taur transformation text, as well as in some orgasm scenes.</ul>"
			+"<ul>Fixed issue in several sex positions (mainly in the 'sitting' position) where only self-interacting sex actions would be available.</ul>"
			+"<ul>Fixed deflowering fetish experience not incrementing from taking others' virginities unless you had the fetish.</ul>"
			+"<ul>Fixed performing handjobs not being available while performing intercrural on the same person.</ul>"
			+"<ul>Fixed bug where characters were spawning in with just 5 aura, even if their maximum aura was far higher.</ul>"
			+"<ul>Fixed bug where all half-demons would always be female, no matter what your preferences were set to.</ul>"
			+"<ul>Fixed corruptive sex actions not being added to the repeat actions tab.</ul>"
			+"<ul>Brax's feminisation/bimboification transformations no longer give him/her hair (as she has a wolf's furred head instead).</ul>"
			+"<ul>Fixed characters in the 'face-to-wall' position being able to grope the breasts of the person behind them.</ul>"
			+"<ul>Fixed incorrect actions being available in the 'standing' position when in the 'standing behind' slot.</ul>"
			+"<ul>Resetting age or gender preferences to default now correctly saves the restored default preferences in your properties.xml file.</ul>"
			+"<ul>Fixed bug in sex where starting a finger-related action would stop all other ongoing finger-related actions. (i.e. Starting to give someone a handjob would make you stop fingering them, even if you had a hand free.)</ul>"
			+"<ul>Selling items no longer puts those items into the shopkeeper's inventory, and only puts them into the buyback menu.</ul>"
			+"<ul>Fixed the buyback menu's broken UI, and fixed the 'buy (5)' and 'buy (all)' actions to work properly.</ul>"
			+"<ul>Fixed numerous actions not being available in the 'all fours', 'milking stall', and 'stocks' positions.</ul>"
			+"<ul>Fixed the 'Important status effects' screen popping up in mid-dialogue scenes (it now displays when you get to a scene in which travel is no longer disabled).</ul>"
			+"<ul>Fixed being able to switch ongoing actions with Lyssieth in the Lilaya/Meraxis transformation scene, which would cause them to not be transformed, breaking the scene.</ul>"
			+"<ul>Fixed issue where asking someone (who is listening to your requests) to pullout would cause them to always choose to cum on the floor, instead of sometimes choosing to cum on a body part.</ul>"
			+"<ul>Fixed NPCs not prioritising character they are penetrating with their penis when choosing an orgasm action.</ul>"
			+"<ul>Fixed incorrect orgasm descriptions in the standing position.</ul>"
			+"<ul>Characters will no longer end sex while there is another character who's about to orgasm.</ul>"
			+"<ul>Lilaya and Meraxis no longer lose affection towards you or Lyssieth if their transformation sex scenes end without orgasming enough times to be satisfied.</ul>"
			+"<ul>You are no longer blocked from masturbating if you have a summoned elemental in your party.</ul>"
			+"<ul>Fixed raven harpies not being recognised if the harpy had 'pitch black' feathers instead of just 'black'.</ul>"
			+"<ul>Fixed issue with the character creation age selection acting as though you were three years older than you actually set yourself to be.</ul>"
			+"<ul>'Pussy-to-mouth' and 'Ass-to-mouth' orgasm actions are now correctly only available in the 'all fours' position, and NPCs who are listening to your requests to pull out will no longer use these actions.</ul>"
			+"<ul>Ongoing sex actions that you've already started no longer have their associated 'start' action show up as a greyed-out action in the action list.</ul>"
			+"<ul>Fixed bug where characters in the breeding stall could finger themselves.</ul>"
			+"<ul>You can now request position changes in Amber's sex scenes again.</ul>"
			+"<ul>Fixed numerous descriptions when interacting with Amber that didn't take into account whether you had a taur lower body or not.</ul>"
			+"<ul>Fixed cultists not using correct sex actions, and their post-sealed-sex scene displaying the incorrect scene description.</ul>"
			+"<ul>Fixed bug where partners in sex would almost never want to reveal your breasts.</ul>"
			+"<ul>Fixed issue with conditional parsing statements inside of speech breaking if the speech was affected by a lisp or other speech-altering method.</ul>"
			+"<ul>Bat tails now correctly require the 'furry tail penetration' setting to be on in order for characters to use them for penetration during sex.</ul>"
			+"<ul>Fixed offspring's initial pregnancy reaction dialogue sometimes not working.</ul>"
			+"<ul>Fixed issue where Lilaya would react as if you got her pregnant, even if it was Lyssieth who got her pregnant.</ul>"
			+"<ul>Noxinia's sex scenes are no longer treated as public sex.</ul>"
			+"<ul>Fixed issue with sex AI where they would only want to perform paizuri if their partner's nipples were able to be penetrated.</ul>"
			+"<ul>Fixed clothing with 4 available equip slots blocking out the 'pay to identify' action when interacting with shopkeepers.</ul>"
			+"<ul>Fixed bug in sex where NPCs would use opposite actions to the ones they wanted. (i.e. A character whose preference was to receive a blowjob would be equally as happy to start performing a blowjob, even if their fetish desires should normally have prohibited it.)</ul>"
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
		credits.add(new CreditsSlot("Phlarx", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Moro", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Neo", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Abaddon_TMZ", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("ForgottenOne", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Velvet", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
		
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
		if (name.contains("\"")) {//!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' _]*")) {
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

	public static void loadGame(File f) {
		Game.importGame(f);
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
