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

/**
 * @since 0.1.0
 * @version 0.3.10
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
	public static final String VERSION_NUMBER = "0.3.13";
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
			+ "<i>This hotfix of v0.3.13 addresses some bugs that were present in v0.3.12.</i>"
		+ "</p>"
		
		+ "<p>"
			+ "This is an update to the race code refactoring work done in v0.3.10."
			+ " It includes a few bug fixes and other miscellaneous changes, as well as a lot of code contributions from quite a few people (thank you!)."
		+ "</p>"
			
		+ "<p>"
			+ "It also includes 7 new races, made by DSG, and added via the framework that was put in place in v0.3.10."
			+ " Each race has a pair of consumable items, and all of them have a unique special combat move."
		+ "</p>"
			
		+ "<p>"
			+ "I'm sorry that the Foloi Fields content still isn't added, but with this necessary bug-fixing release out of the way, I can move on to finally start working on v0.4!"
			+ " I'll hopefully have that out in a couple of weeks from this release."
		+ "</p>"
		
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.13</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Dragon breath attack is now unlocked by the character's race being identified as 'dragon', not by having a dragon torso. (by DSG)</ul>"
			+"<ul>Fixed issue with the dragon subspecies 'Coatl' not being detected correctly. (by Stadler)</ul>"
			+"<ul>Fixed bug where feral characters would spawn with crotch-boobs, even if crotch-boob row count was defined as 0. (by Stadler)</ul>"
			
			+"<li>Engine/Modding:</li>"
			+"<ul>Added parser hooks to penetration, orifice, and tongue modifiers (using prefixes 'PENETRATION_MODIFIER_', 'ORIFICE_MODIFIER_', and 'TONGUE_MODIFIER_' respectively).</ul>"
			+"<ul>Added support in racialBody xml files for setting 'breastCrotchType' to NONE for races you want to never spawn with crotch-boobs.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Characters no longer have their cum & girlcum automatically gain the 'musky' modifier when changing into a non-bipedal leg configuration. (Centaurs still have this modifier by default.)</ul>"
			+"<ul>Sharks and all dragon subspecies will no longer spawn with crotch-boobs.</ul>"
			+"<ul>Removed restrictive minimum and maximum limitations for insect wing size.</ul>"
			+"<ul>Ralph no longer sells Impish Brew (you can still buy it from Roxy).</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Racial food items which have more than one torso type associated with them now have access to those torso types in the enchantment menu.</ul>"
			+"<ul>Fixed dragon's 'Breath weapon' special attack dealing 0 damage.</ul>"
			+"<ul>Fixed some minor issues with NPCs spawned as a dragon subspecies having the odd part assigned incorrectly.</ul>"
			+"<ul>Fixed bug where the penis slot would not accept any clothing if a character lacked a penis, causing strapons to be impossible to equip.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.12</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added variations of starting demonstone damage type based on your birth month, and correct some descriptions during the prologue related to this. (PR#1409 by Rfpnj)</ul>"
			+"<ul>Fixed bug where the 'Dye & reforge all (stack)' action would not consume reforge hammers. (by AceXP)</ul>"
			+"<ul>Fixed bug where 'Unequip all' would inadvertently unequip milking pumps, which would end up with them being added to your inventory. (by AceXP)</ul>"
			+"<ul>Editing your character's description in their save file will now display that description in the in-game selfie screen. (PR#1411 by aDrunkLittleDerp)</ul>"
			+"<ul>Fixed issue with incorrect anus description for elementals (with help from PR#1412 by AceXP).</ul>"
			+"<ul>Fixed parser command 'glow' to work without a defined colour, as well as for rainbow colours. (PR#1439 by CognitiveMist)</ul>"
			+"<ul>Typo fix in bread roll description. (PR#1414 by aDrunkLittleDerp)</ul>"
			+"<ul>Added the option for a slave to be mute to Helena's custom slave shop and removed tongue recolouring from the Hair options tab. (PR#1416 by AceXP)</ul>"
			+"<ul>Characters with the NON_CON_DOM fetish will now gain lust when having sex with someone that is resisting. (PR#1417 by AceXP)</ul>"
			+"<ul>Slaves in the stocks at Slaver Alley without a vagina will no longer have vaginal sex events generated for them. Also fixed these slaves' descriptions and the issue where they would always have all permissions granted. (PR#1426 by AceXP)</ul>"
			+"<ul>Added checks for fertility pills, including broodmother. Roxy now sells broodmother pills and random NPCs may sometimes be carrying them. Ralph and Lilaya now react to broodmother pills in the same way as fertility pills. Fixed incorrect colour descriptions of fertility pills in several places. (PR#1422 by AceXP)</ul>"
			+"<ul>Fixed issue where hair length, breast size, ass size, and hip size had no tolerance so that post-combat forced TFs alternated between too big and too small. (PR#1334 by Stadler76)</ul>"
			+"<ul>Fixed typo in phoenix-harpy description and tidied up parts of the code in UtilText.java. (PR#1336 by Stadler76)</ul>"
			+"<ul>Fixed some instances of incorrect unit parsing. (PR#1321 by NoStepOnSneks)</ul>"
			+"<ul>Fixed a bug where if a clothing item to be equipped had multiple occurrences, equipping this item would lead to bugs when the next occurrence of the item was to be equipped. (PR#1419 by Deboucher & AceXP)</ul>"
			+"<ul>Fixed the bug where clicking on an equipped item while enchanting during a trading session would give you full access to the inventory of the trader, allowing you to take all their equipped items and the items in their inventory. (PR#1420 by AxeXP)</ul>"
			+"<ul>Fixed issue with milking rooms not being able to support 8 slaves, maids gathering on the player's tile when loitering in the corridors of Lilaya's home, and the 'well rested' status effect no being applied correctly. (PR#1425 by AceXP)</ul>"
			+"<ul>Enchantment recipes can now be applied to weapons that already have enchantments. (PR#1423 by AceXP)</ul>"
			+"<ul>Updated github build tutorial. (PR#1323 by LjyiH)</ul>"
			+"<ul>Fixed missing verbs in several files. (PR#1291 by TadUnlikely)</ul>"
			+"<ul>Fixed incorrect dialogue scene titles when getting tattoos in Kate's shop, incorrect description of how much cum was expelled during orgasm when performing a double creampie orgasm action, and a reference to ass instead of breast in nipple-fingering actions. (by AceXP)</ul>"
			+"<ul>Fixed a bug where having an elemental in a feral form would cause the the left and right sidebar to go blank. (PR#1457 by AceXP)</ul>"
			+"<ul>Prevented pregnant slimes without a vagina from transforming back to flesh, as the birthing scenes only account for characters with a vagina. (PR#1459 by AceXP)</ul>"
			+"<ul>Fixed issue where Sean could not be challenged a second time if you backed out and changed your mind about fighting him. Also fixed some incorrect clothing descriptions and missing text. (PR#1458 by AceXP)</ul>"
			+"<ul>Added support for defining feral face description in face modding xml files. (PR#1452 by Stadler76)</ul>"
			+"<ul>Added 'Poncho' clothing item. (Unisex, over-torso slot, sold by Nyan.) (by NoStepOnSnek)</ul>"
	
			+"<li>DSG's race contributions:</li>"
			+"<ul>Added 7 new races: Sharks, Bears, Dragons, Ferrets, Gryphons, Otters, and Raccoons.</ul>"
			+"<ul>Added 1 subspecies for each of these new races, except for the dragon race, which has the subspecies: Coatl, Ryu, Dragon, Drake, and Wyvern.</ul>"
			+"<ul>Added 18 new consumable items, consisting of a drink (attribute) & food item (transformative) for each of the 7 new races, with dragons having an extra 2 variants of their drink item and 2 extra variants of their food item.</ul>"
			+"<ul>Added 12 new colours related to the new races added.</ul>"
			+"<ul>Added 7 new combat moves for the new races: 'Charging Bite' (sharks), 'Five Claw Strike' (bears), 'Breath Weapon' (dragons), 'War Dance' (ferrets), 'Hunting Call' (gryphons), 'Tail Slap' (otters), and 'Cornered Beast' (raccoons).</ul>"
	
			+"<li>Engine/Modding:</li>"
			+"<ul>Made some slight improvements to the documentation of the xml files related to race modding.</ul>"
			+"<ul>Added support for wing types to have a defined minimum and maximum size (see the 'res/mods/innoxia/race/hyena/unused_bodyParts/wing.xml' for the commented example).</ul>"
			+"<ul>Added comments for the imageName elements in 'res/mods/innoxia/items/items/race/hyena_bone_crunchers.xml'.</ul>"
			+"<ul>Choosing to not define any covering modifiers or patterns in coveringType mods is now correctly handled without throwing background errors.</ul>"
			+"<ul>Leaving the 'backgroundName' element empty or not including it in subspecies xml mod files is now correctly handled and uses the default race background (as was implied it should in the comments).</ul>"
			+"<ul>Added elements in the 'racialBody.xml' file for defining male and female hip sizes ('maleHipSize' and 'femaleHipSize').</ul>"
			+"<ul>Added parsing hook to BodyPartTag (using the prefix 'BODY_PART_TAG_').</ul>"
			+"<ul>Added ColourTags, which can be added to modded colours to have them be automatically added to the game's covering colour lists.</ul>"
			+"<ul>Added setCovering() to Body class to make the setting of coverings easier in race mods. (See 'applySubspeciesChanges' element in lion.xml for an example on this being used.)</ul>"
			+"<ul>You can now leave the half-demon name elements empty in subspecies xml files (in which case the game will automatically generate a generic half-demon name for your subspecies).</ul>"
			+"<ul>Added support for a 'displaySize' attribute to the 'iconName' element in subspecies xml files, allowing you to define the resizing percentage of your icon image.</ul>"
			+"<ul>Added 'coveringIconColour' element for modded colours, allowing you to define the colour to use a different colour for covering icons than its usual colour for text.</ul>"
	
			+"<li>Gameplay:</li>"
			+"<ul>Added a separate variant of the aquatic status effects for characters of an aquatic race who have the leg configuration 'TAIL'.</ul>"
			+"<ul>Added ability to recolour cum, girlcum, and milk in the self-TF menu.</ul>"
			+"<ul>Minimum size for a clitoris to be used as a psuedo-penis has been increased from 1cm to 5cm.</ul>"
			+"<ul>Split cat-morph race into cat-morph and panther-morph. Removed cat-morph's 'panther face' TF option. Tigers, lions, leopards, and snow leopards are now classed as panther-morphs.</ul>"
			+"<ul>If a character has the cat-morph's 'panther face', their cat-morph body parts will be converted into panther-morph parts when loading into this version.</ul>"
			+"<ul>Added 'Deep Roar' and 'Panther's Delight' consumable items for the new panther-morph race.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Improved sorting of subspecies in several menus.</ul>"
			+"<ul>Updated spawn locations of all subspecies.</ul>"
			+"<ul>Altered the names of the reindeer and centaur books.</ul>"
			+"<ul>Added some more global NPC transformation debug buttons (in Misc. category).</ul>"
			+"<ul>Added labourer outfits, so reindeer overseers will no longer spawn with completely random outfits.</ul>"
			+"<ul>Slightly adjusted the default colours of the leather belt.</ul>"
			+"<ul>Diamond necklaces will no longer spawn as a random item in Dominion's alleyways, canal tiles, or in the crates in the Supplier Depot.</ul>"
			+"<ul>Characters with the TAIL_LONG leg configuration now gain access to the 'tail swipe' combat move.</ul>"
	
			+"<li>Body:</li>"
			+"<ul>Added ROUGH as a covering modifier.</ul>"
			+"<ul>Added TAPERED as a tongue modifier.</ul>"
			+"<ul>Added REPTILIAN and AMPHIBIAN foot types.</ul>"
			+"<ul>Added BodyPartTags TORSO_DORSAL_FIN, TAIL_TYPE_SCALES, THERMAL_VISION, and ALLOWS_FLIGHT.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>NPCs now have the chance to decide to initiate penetration using a clitoris that's large enough to act as a pseudo-penis.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Fixed bug where characters of a modded race would show as 'human' after loading a game.</ul>"
			+"<ul>Fixed non-centred alignment and uneven circle dimensions of race & item backgrounds.</ul>"
			+"<ul>Encyclopedia information for modded races should now load and display correctly.</ul>"
			+"<ul>Fixed issue with the tile's default dialogue being shown alongside the intended dialogue when refusing a partner's offer of sex in the nightclub.</ul>"
			+"<ul>Fixed issue where declining sex in a nightclub partner's apartment would lead into the nightclub's toilets dialogue instead of Dominion boulevard dialogue.</ul>"
			+"<ul>The game no longer autosaves when following or taking a nightclub partner home, as loading the autosave which was generated at that point would result in that NPC being unintentionally deleted. Instead the autosave is generated after leaving the partner.</ul>"
			+"<ul>Fixed bug where nightclub partners would not be saved if you followed them home and then refused sex, despite the tooltip indicating otherwise.</ul>"
			+"<ul>Fixed huge amount of background errors being thrown whenever you changed any of your furry preferences (which was causing that preference screen to lag a lot).</ul>"
			+"<ul>Fixed issue with race books not showing up in the library.</ul>"
			+"<ul>Added handling for automatic birthing of nipple-incubation eggs when a character transforms into a feral form which lacks breasts.</ul>"
			+"<ul>Fixed issue where litters birthed by NPCs would always have the same birth date as their conception date.</ul>"
			+"<ul>Fixed issue where background errors would be thrown if a generic sex event generated a half-demon character.</ul>"
			+"<ul>Fixed issue where NPCs with eggs implanted in them would never lay the eggs if you saved & loaded the game while they were in the 'completed' stage of incubation.</ul>"
			+"<ul>Removed incorrect reference to going to consulting Lilaya when having eggs implanted inside of you, and replaced it with a hint on having to access your phone's menu in order to lay them.</ul>"
			+"<ul>Enchanting the item 'Biojuice Canister' now correctly gives you just the option to create a slime-reversion elixir, instead of the full racial options that other standard racial TF items have access to.</ul>"
			+"<ul>Fixed image position and alignment in combat move tooltips.</ul>"
			+"<ul>Fixed issue where milking pumps and spider webbing seals could spawn as generic clothing on randomly-generated NPCs.</ul>"
			+"<ul>Fixed spotted & striped hyenas not having correct plural names for their feral forms.</ul>"
			+"<ul>Fixed issue where TF potions with a TF type that's been removed from the game would throw background errors when trying to use it.</ul>"
			+"<ul>Fixed parsing errors when eating the hyena food item 'Bone Crunchers'.</ul>"
			+"<ul>Fixed reference to feral characters having feet when they had a tail lower body type.</ul>"
			+"<ul>Fixed a couple of issues that were causing modded status effects to not work.</ul>"
			+"<ul>Fixed issue where loading a previously saved game would sometimes show parsing errors on the initial dialogue page (for example, in the entrance tile to Slaver Alley).</ul>"
			+"<ul>Fixed spam of warnings about getFeralAttributes() returning null every time you loaded the game as a feral character.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.10</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Fixed issue where NPC speech would sometimes have errors in it. (PR#1415 by AceXP)</ul>"
			+"<ul>Added a unique icon for the 'dazed' combat status effect. (by DSG)</ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Added modding support for items to be defined as being able to be enchanted into new items, and support for a background to be added to item images, as well as size and rotation definitions. (See 'res/items/innoxia/race/horse_equine_cider.xml' for an example.)</ul>"
			+"<ul>Added support for nesting folders inside mod folders (so mod makers can more easily categorise mod files within their directories).</ul>"
			+"<ul>Improved performance of status effect conditional checks, which should have considerably improved the time it takes to end a turn in games with many saved NPCs.</ul>"
			+"<ul>Added 'tags' field to status effect modding, allowing (some) ItemTags and their effects to be applied to modded status effects. (See the 'res/mods/innoxia/statusEffects/set_template.xml' file for an example.)</ul>"
			+"<ul>Added parsing commands for clitoris diameter and circumference.</ul>"
			+"<ul>Improved handling of body covering types in the code.</ul>"
			+"<ul>Added full mechanics support for all other leg configuration types (serpent-tail, fish-tail, arachnid, cephalopod, avian). You can test these lower body types by using the debug TF menu to give yourself the demonic leg types which support this.</ul>"
			+"<ul>Added 'aquatic' race definition and land/water tile definitions, so that aquatic races have beneficial/detrimental status effects applied when they are in a water/land tile respectively. Tile land/water status and danger status is now displayed at the top-right of the UI.</ul>"
			+"<ul>Added full support for spinnerets, which will be granted by spider tails and spider 'arachnid' leg configurations. Spinnerets can be used as sexual orifices, and also grant several web-spinning sex actions, along with a special combat move.</ul>"
			+"<ul>Added full support for penises and clitorises to be ovipositors, by means of giving them a modifier (via TF potion or self-TF menu). Characters with an egg-laying vagina which has been impregnated can use an ovipositor penis/clit to lay the fertilised eggs in vaginas, anuses, nipples, and mouths.</ul>"
			+"<ul>Cephalopod lower bodies now support tentacles as penetrative objects (like tails) and grant a special combat move.</ul>"
			+"<ul>Added support for combat move modding. (See 'res/mods/innoxia/combatMove' and 'res/combatMove' for examples.)</ul>"
			+"<ul>Added support for fully-feral bodies. (There is currently no way to obtain a feral body, but the underlying code is ready for future content.)</ul>"
			+"<ul>Added full support for race modding. (See 'res/mods/innoxia/race' for an example.)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added 'darkness' mechanic. Darkness status effect gets applied when a character is located in a map tile which is not illuminated. This status effect can be negated by having animal morph eyes which grant night vision, bat ears (which grant echo location), a body made of fire, mastery of fire spells, or an equipped piece of clothing/weapon which counteracts darkness (night vision goggles or the flashlight).</ul>"
			+"<ul>Increased upper cap of base unarmed damage from 20 to 50.</ul>"
			+"<ul>Added 'hyena' race with 'spotted hyena' and 'striped hyena' as its two subspecies. Added related 'Bone Crushers' food item and 'Workout Power' drink item.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added weapon: 'Flashlight'. Sold by Ralph, illuminates dark areas when equipped.</ul>"
			+"<ul>Improved icons for all racial attribute drinks (such as Equine Cider, Canine Crush, Wolf Whiskey, etc.), and moved all of them out from the code into the external 'res/items/innoxia/race' folder.</ul>"
			+"<ul>Moved all racial food items out from the code into the external 'res/items/innoxia/race' folder.</ul>"
			+"<ul>The 'Angel's Tears' item has been considerably buffed and can no longer be naturally found in-game (it will be added back into the game later on).</ul>"
			+"<ul>Made 'tabi socks' unisex.</ul>"
			+"<ul>Most racial transformative food items now additionally restore 10% health when consumed.</ul>"
			
			+"<li>Body:</li>"
			+"<ul>Added a transformable value for tail length, expressed as a percentage of the tail owner's height (minimum value is 5%, maximum is 250%). It can be modified via the self-transformation menu (for demons or slimes), or via a new potion enchantment option.</ul>"
			+"<ul>If a tail's girth is the same as the default value for its type, then changing the tail type will reset the girth to the new tail's default girth. The same happens with tail length.</ul>"
			+"<ul>Alligator-morph body hair is now coarse hair (the same as their head hair). It is always absent on alligator-morphs by default if you have your 'Scaly Hair' option off.</ul>"
			+"<ul>Added more options to change the colour of your elemental's body coverings.</ul>"
			+"<ul>Fixed issue where face type would always use the same covering as torso type.</ul>"
			+"<ul>Increased maximum breast and crotch-boob row counts from 5 to 6.</ul>"
			+"<ul>Slightly adjusted clitoris size category values to make them mroe in-line with penis size values, and reduced maximum length from 125cm to 100cm (so that it's the same as penis length).</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Fixed several parsing errors and incorrect descriptions of several sex actions (mainly tail-asshole and tail-vagina actions).</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Ralph now sells modded weapons which have been given the tag 'SOLD_BY_RALPH'.</ul>"
			+"<ul>Slaves now enjoy the prostitute job if they have the 'nymphomanic' trait active, instead of if they have the cum addict fetish.</ul>"
			+"<ul>The cum-inflation status effects for a character's breasts are now triggered by either breasts or crotch-boobs being inflated by cum.</ul>"
			+"<ul>Horn adn wing types will no longer have random ordering in the self-TF menu's options.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed issue where using the 'Quit' button in the main menu would sometimes fail to close the game's background java process.</ul>"
			+"<ul>Fixed bug where summoning your elemental for the first time in a new game wouldn't work.</ul>"
			+"<ul>Taking a thorough shower in your room's bathroom now correctly applies the 'Recently showered' status effect.</ul>"
			+"<ul>Fixed bug in conditional parser where identical if statements that each return 'true' would return the last encountered block instead of the first.</ul>"
			+"<ul>Elementals now correctly return to their passive form after successfully escaping from combat.</ul>"
			+"<ul>Hair clothing is now correctly unequipped when hair length is set to 0.</ul>"
			+"<ul>In the 'Witch's Seal' sex scene with cultists, the character who is sealed can no longer use orgasm-locking actions (i.e. 'leg-lock', 'hug-lock', etc.).</ul>"
			+"<ul>Fixed issue with some NPCs spawning with metallic-coloured shirts.</ul>"
			+"<ul>Fixed issue where tail length decrease button would continue to work when greyed-out.</ul>"
			+"<ul>Orgasm actions which direct cum onto a partner's groin will now correctly dirty their vagina/penis inventory slots.</ul>"
			+"<ul>Fixed issue with 'hoof-kick' attack's critical hit logic not working correctly.</ul>"
			+"<ul>'Martial artist' perk now correctly increases base unarmed damage, instead of modifying the damage in the same manner as the 'unarmed damage' attribute's calculation.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.9.9</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Starting Claire's teleportation side-quest now requires you to have first completed the 'Slime Queen' side quest. (As I felt as though it was a little out of character for the Enforcers to let a stranger see the teleportation pads.)</ul>"
			
			+"<li>Items:</li>"
			+"<ul>(For Clothing Modding) If multiple stickers in one sticker category are set to be the default sticker, one of these default stickers is now chosen at random to be used for an item of clothing, instead of always the last defined default sticker.</ul>"
			+"<ul>Made the shoulder strap on the 'ragged chest wrap' a sticker option.</ul>"
			+"<ul>'Impish Brew' now grants +25 corruption to potion effects, instead of +50.</ul>"
			+"<ul>Added ability to enchant clothing with vagina and penis addition and removal effects.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>You can no longer 'Encourage creampie' or 'Encourage pullout' when spectating in a 'hidden' position (such as when you watch your slaves having sex in the alleyway or hallway encounters).</ul>"
			+"<ul>You can now use level drain on orgasming characters while in a spectator slot.</ul>"
			+"<ul>Added interactions in the 'Sitting' position between the 'Sitting in lap' slot and the 'Between legs' slot.</ul>"
			+"<ul>Added interactions in the 'Over Desk' position between the 'Lying back' slot and the 'Bent over' slot.</ul>"
			+"<ul>Added kissing and groping interactions between dominant and submissive standing characters in the 'Standing' sex position.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>The output of all parsing commands embedded within speech should now be correctly modified by any speech-altering effects (such as a slovenly character pronouncing 'her' as ''er').</ul>"
			+"<ul>Transformative potions which turn a character's lower body into a taur now correctly transform all of the body parts which are affected (all parts below the waist) into their default animal counterpart states.</ul>"
			+"<ul>Added transformation actions to alleyway prostitutes' post-combat victory scene.</ul>"
			+"<ul>Added pregnancy reactions to Murk's greeting dialogue while in Epona's tile.</ul>"
			+"<ul>Standardised sorting of all racial transformation options by their race name.</ul>"
			+"<ul>Half-demons no longer use the same outfit generation as dark alleyway demons, and dark alleyway demons now have a 5% chance to spawn with demon daggers instead of 50%.</ul>"
			+"<ul>The 'Enforcer HQ' tile is no longer considered to be dangerous during an arcane storm.</ul>"
			+"<ul>Increased the chance of the street encounter in which Wes's quest starts from 10% to 50%.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>Fixed issues with sex scenes at Epona's stall in the Gambling Den being treated as public sex.</ul>"
			+"<ul>Fixed bug where the Rat Warrens content would break if rat-morph Subspecies preferences were set to 'human'.</ul>"
			+"<ul>Fixed bug where accessing the positioning menu in the prologue sex scene would reset all of the previous sex dialogue.</ul>"
			+"<ul>Fixed issue with there being two 'miss' parsing commands.</ul>"
			+"<ul>New slaves will now correctly start with all default job settings and permissions selected.</ul>"
			+"<ul>Fixed harpy attacker NPC descriptions not changing when you become friends with them or enslave them.</ul>"
			+"<ul>Fixed issue with the 'slovenly' character speech modification sometimes breaking parsing commands.</ul>"
			+"<ul>Fixed Murk's and Lyssieth's special dirty talk text not being parsed as speech.</ul>"
			+"<ul>Choosing to dominantly fuck Murk in Epona's tile (after completing Axel's quest) should now start with both of you in the intended positions.</ul>"
			+"<ul>Fixed bug where slave interaction scenes in alleyways or Lilaya's corridor would sometimes throw background errors and not initialise correctly.</ul>"
			+"<ul>The Spa tile is now correctly immune to arcane storm effects.</ul>"
			+"<ul>Fixed issue with enslavement of friendly NPCs describing them as though they'd just been defeated in combat.</ul>"
			+"<ul>Exiting Lyssieth's palace now correctly places you in the cavern tile instead of the palace gate tile.</ul>"
			+"<ul>Fixed existing Patrol Enforcers in Dominion wearing 'contractor' stab-proof vests instead of Enforcer ones.</ul>"
			+"<ul>If foot, anal, lactation, or non-con settings are turned off, NPCs will no longer have the related fetishes or fetish desires.</ul>"
			+"<ul>If non-con is disabled, the related fetishes are now correctly hidden from the fetish list and fetish altering potion effects.</ul>"
			+"<ul>Fixed NPCs being described as being in the 'holding cell' in their contacts page tooltip.</ul>"
			+"<ul>Fixed issue where attributes derived from clothing could sometimes be duplicated or persist after the clothing's removal.</ul>"
			+"<ul>Fixed issue where equipping transformative clothing onto demons or half-demons would sometimes spam the event log with messages.</ul>"
			+"<ul>Dominant partners in the Watering Hole will no longer spawn with the 'submissive' fetish, and likewise Submissive partners will no longer spawn with the 'dominant' fetish.</ul>"
			+"<ul>Fixed background errors being thrown if you decided to spam-click the 'New Game' action.</ul>"
			+"<ul>Added a 'no preference' option to Scarlett's sex scene (where you act as her servant in Helena's nest), so that if you have no areas available for her to fuck, you no longer get stuck in that scene.</ul>"
			+"<ul>Added actions to exit Helena's apartment at the entrance tile (for if you somehow manage to get stuck in there).</ul>"
			+"<ul>Elementals now have the correct arcane storm status effect applied to them.</ul>"
			+"<ul>Fixed bug where you could swap position with Murk's milkers during sex with them.</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.9.8</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Fixed issue where changing content options at the start of new character creation would throw background errors, causing the UI to become unresponsive. (by AceXP)</ul>"
			+"<ul>Fixed issue where fluid addiction status effect tooltip descriptions would display 'demonic horse fluid' instead of 'demonic fluid'. (by AceXP)</ul>"
			+"<ul>Fixed bug where the defined horse-morph names in Name.java were never being used. (by Rydelfox)</ul>"
			+"<ul>Several parsing and typo fixes. (by AceXP)</ul>"
			+"<ul>Fixed issue where you would return to an incorrect dialogue scene when leaving inventory management in a friendly occupant's apartment. (PR#1394 by AceXP)</ul>"
			+"<ul>Typo fixes in the Rat Warrens. (Pr#1400 by aDrunkLittleDerp)</ul>"
			+"<ul>While carrying an arcane makeup set, characters will now reapply heavy lipstick if it was worn off during sex. (PR#1403 by CognitiveMist)</ul>"
			+"<ul>Fixed parser errors in vagina reveal descriptions. (PR#1404 by AceXP)</ul>"
			+"<ul>Enable loading of patterns from the res/mods folder. (PR#1405 by AceXP)</ul>"
			+"<ul>Fixed bug where five minutes passed, instead of twenty-five, when selling yourself as a submissive partner in Angel's Kiss. (PR#1406 by void-weaver)</ul>"
			+"<ul>Fixed bug where Sean's fight scene wouldn't initialise correctly. (PR#1407 by void-weaver)</ul>"
			+"<ul>Fixed bug where if you used an item from an NPC's inventory it would be described as though the NPC was using the item. (PR#1408)</ul>"
			
			+"<li>DSG's Enforcer Uniform Update:</li>"
			+"<ul>Added sticker system support, consolidated all extant variants of the Enforcer stabvest, coat, waistcoat, beret, peaked cap, and bowler hat into their respective items.</ul>"
			+"<ul>Added the following sticker assets that did not already exist in some form in the game: Combat Diver Badge, Commissioner Cap Badge, Commissioner/Deputy Commissioner Visor/Crown Oak Leaves, Commissioner Aiguillette, Elis Cap Badge, Thinis Cap Badge, Itza'aak Cap Badge, Lyonesse Cap Badge.</ul>"
			+"<ul>Detailed buttons added to the Enforcer coat and waistcoat.</ul>"
			+"<ul>Fixes and standardization of ribbon racks and name plates.</ul>"
			+"<ul>Hand optimization of almost all vectorized text.</ul>"
			+"<ul>Added the 'Contractor's' variant to the stab vest and plate carrier.</ul>"
			
			+"<li>DSG's Enforcer outfit update:</li>"
			+"<ul>Added sticker and pattern support.</ul>"
			+"<ul>Renamed conditionals to be more reader friendly.</ul>"
			+"<ul>Changed comments to be more clear.</ul>"
			+"<ul>Removed berets from the Patrol Service Uniform.</ul>"
			+"<ul>Possibly fixed bugs related to headgear spawning on Enforcers with the wrong colors and no headgear spawning on Enforcers entirely.</ul>"
			
			+"<li>Engine:</li>"
			+"<ul>Added ItemTags for defining items, clothing, and weapons as being restricted or illegal, causing them to be unable to be sold to merchants and confiscated by Enforcers.</ul>"
			+"<ul>Added mod support for defining clothing 'stickers', which apply cosmetic changes to clothing items. (See 'res/mods/innoxia/items/clothing/rentalMommy/rental_mommy.xml' for a fully documented example of how to define them.)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added two new Enforcer characters and a new quest involving them, all of which has been designed by DSG. The start of this quest will randomly trigger in Dominion's street tiles under the following conditions: no arcane storm; main quest is past Brax's section; over 5 days have passed since completing the 'Angry Harpies' quest; time is 17:00-21:00.</ul>"
			+"<ul>Enforcers in the 'alleyway Enforcer encounters' will now confiscate illegal items, and arrest you if they find that you're carrying highly illegal items.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added sticker support to the 'rental mommy' and 'rental daddy' T-shirts.</ul>"
			+"<ul>'Biojuice Canisters' and 'Glowing Mushrooms' are now tagged as restricted items.</ul>"
			+"<ul>'Demon's Dagger' (no longer sold by Vicky) and all of the Enforcer weapons are now tagged as either illegal (Enforcers will confiscate them) or highly illegal (Enforcers will arrest you).</ul>"
			+"<ul>All Enforcer clothing is now tagged as illegal (Enforcers will confiscate them).</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Slightly altered description of 'cynical' personality trait to differentiate it from 'selfish'.</ul>"
			+"<ul>Items and weapons will now correctly display ItemTag descriptions in their tooltips.</ul>"
			+"<ul>Roxy now buys weapons as well as items and clothing. Her buy modifier has been reduced from 0.4 to 0.3 (meaning she will now only give you 30% of an item's value).</ul>"
			+"<ul>Sean now correctly wears an Enforcer patrol uniform instead of a dress uniform.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Updated example links in xml modding files to point to the correct files.</ul>"
			+"<ul>Fixed bug where you could get stuck in Brax's office after resolving the part of the main quest which involves him.</ul>"
			+"<ul>Fixed issue with the cheat guns' 'mag dump' combat move being automatically removed after selecting it.</ul>"
			+"<ul>Fixed descriptions of putting kitty panties on/off being inverted.</ul>"
			+"<ul>Fixed bug where weapons could show incorrect image previews in the dye screen. (The 'Demon's Dagger' was suffering from this.)</ul>"
		+"</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.9.4</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added an encounter in Dominion's alleyways and Lilaya's home's corridors where you stumble upon two of your slaves having sex with one another. To trigger, you need your slaves to have the appropriate outside/house freedom and sex permissions, and the one initiating sex needs to have not had sex for at least 4 hours and to be attracted to the other. (by PoyntFury)</ul>"
			+"<ul>Improved String-matching utility methods and dynamically generated ColorListPreset classes. (PR#1368 by CognitiveMist)</ul>"
			+"<ul>Fixed bug where sometimes multiple-partner sex would break upon orgasm. (by CognitiveMist)</ul>"
			+"<ul>Added 'broodmother pill', which doubles offspring conceived during its effect. (Usage dialogue written by PoyntFury)</ul>"
			+"<ul>Fixed a bug where having multiple arm pairs allowed an all-out strike with a 2-handed weapons. (PR#1384 by AceXP)</ul>"
			+"<ul>Added average penis girth in the Encyclopedia information on races. (PR#1385 by AceXP)</ul>"
			+"<ul>Prevented any succubus/incubus alleyway attackers from spawning with the 'Prude' trait, as it doesn't fit them. (PR#1386 by AceXP)</ul>"
			+"<ul>Fixed issue where 'Arcane impotence' was being applied when arcane was lower than 15, instead of lower than 10 as it should have been. (PR#1388 by AceXP)</ul>"
			+"<ul>Standardized all encounters to override getDialogues() instead of accepting Util.newHashMapOfValues() as a parameter. (PR#1389 by DSG)</ul>"
			+"<ul>Added a new 'limited' option for the bypass sex action content setting, which allows you to perform sex actions of one corruption level higher than your current corruption. (PR#1391 by AceXP)</ul>"
			+"<ul>Added 'wet wipes' item, which cleans the target's dirty inventory slots when used. (by DSG)</ul>"
			+"<ul>Added 'peach' as a silly-mode item, sold by Ralph. (Adapted from PR#339 by Rfpnj)</ul>"
			+"<ul>Added variation of what your starter spell is based on your character's birth month (fireball, slam, ice shard, or poison cloud). (PR#966 by Rfpnj)</ul>"

			+"<li>Engine:</li>"
			+"<ul>Added modding support for items. You can find a fully-documented example in 'res/items/innoxia/pills/fertility.xml'. Item modding will be expanded at a later date to support enchantments, but for now, the modding framework should be enough for most uses.</ul>"
			+"<ul>Converted Combat from an Enum to a class, which can now be accessed via the parser using the command 'combat'.</ul>"

			+"<li>Rat Warrens:</li>"
			+"<ul>Fixed bug where Murk would always use the 'normal' dom pace instead of being rough.</ul>"
			+"<ul>Fixed several instances of Murk's sex scenes ending with an action named 'Milking room' with no tooltip description.</ul>"
			+"<ul>Fixed bug where Murk would fuck you without first taking out your pussy pump.</ul>"
			+"<ul>Fixed issue with missing dialogue node when having sex with Murk in the Gambling Den.</ul>"
			+"<ul>THe 'Free captives' action in the milking room is now correctly disabled once you've discovered that the milkers cannot be freed.</ul>"
			+"<ul>Filled in all placeholder dialogue for both Vengar's and Murk's post-quest-completion scenes in the Gambling Den.</ul>"
			+"<ul>Murk's name now remains as 'Murk' after feminising him in the post-quest gambling den content.</ul>"

			+"<li>Items:</li>"
			+"<ul>Reduced rarity of crafted 'Fetish Endowment' potions from legendary to epic.</ul>"

			+"<li>Sex:</li>"
			+"<ul>NPCs will no longer feel the urgent need to expose masculine characters' nipples during sex.</ul>"
			+"<ul>You can no longer deny your partner's orgasm in the 'glory hole' sex position.</ul>"
			+"<ul>Fixed bug where your sealed clothing wouldn't be displaced at the start of the Enforcer's 'strip search' sex scene.</ul>"
			+"<ul>Added positioning requests to the 'glory hole' sex position for when you're dominantly using it, so you can ask the person on the other side to push their pussy or ass up against the hole to be fucked.</ul>"
			+"<ul>Blocked 'Offer X' and 'Request X' actions in the 'glory hole' sex position (as they didn't really work due to the position's limitations).</ul>"
			+"<ul>NPCs will now only self-use an item type once in sex (so you no longer have to repeatedly use pills on an NPC who wants to use the opposite type of pill).</ul>"
			+"<ul>Added an 'Automatic stripping' content option (in the 'sex' property category), which makes it so that all characters start naked in each sex scene. This is disabled by default.</ul>"
			+"<ul>Sex scenes in which characters automatically start naked no longer remove their piercings.</ul>"
			+"<ul>Fixed issue with NPCs self-using breeder pills in illogical situations.</ul>"
			+"<ul>Lowered corruption requirements for most positioning actions.</ul>"

			+"<li>Slavery:</li>"
			+"<ul>Added 'Save Virginity' as a generic permission for slaves, which is enabled by default.</ul>"
			+"<ul>Slightly improved wording of slave sex interactions in the log.</ul>"
			+"<ul>Reduced the chance for slaves to have the 'bonding' event with one another.</ul>"
			+"<ul>Added filtering options for the 'Occupancy ledger' in the Office room. You can filter events by their type and by slaves involved.</ul>"

			+"<li>Other:</li>"
			+"<ul>Enforcer encounters now have a cooldown of four hours before they can be randomly encountered again.</ul>"
			+"<ul>Characters in the 'lying down' sex slot can now kiss/suckle a character's breasts if they are in the 'cowgirl' sex slot.</ul>"
			+"<ul>The 'American tourist' occupation now only parses the main dialogue screen when converting spelling to American English (as it was causing some lag when trying to parse everything).</ul>"
			+"<ul>Elementals are now always treated as having all their body parts pierced (due to their transformative abilities).</ul>"
			+"<ul>Split up Elemental dialogue into 'Interactions' and 'Management' tabs, and added a management option 'Self-clean' to set whether or not your elemental automatically cleans dirty fluids from their body/clothes (enabled by default).</ul>"
			+"<ul>Elementals now spawn at maximum affection towards their summoner (this will be retroactively applied to your Elemental when loading into this version).</ul>"
			+"<ul>NPCs who do not have the martial artist perk and who have a main weapon equipped and no offhand weapon equipped will now be far less likely to use the 'offhand strike' attack.</ul>"
			+"<ul>The SWORD Enforcers in the Enforcer Warehouse (in Claire's teleportation quest) now prefer to attack in combat rather than teasing.</ul>"
			+"<ul>If you have anal content disabled, the 'buttslut' fetish is no longer a requirement for unlocking the 'lusty maiden' fetish.</ul>"
			+"<ul>You can no longer encounter the same person in the nightclub more than once per night.</ul>"
			+"<ul>Moved 'Post-sex clothing replacement' property category from 'gameplay' to 'sex'.</ul>"
			+"<ul>You can no longer give Bunny or Loppy items during sex.</ul>"
			+"<ul>Added pregnancy reactions for if you manage to impregnate Bunny and Loppy, and slightly improved the flow of dialogue when entering their rooms.</ul>"
			+"<ul>Added two new upgrades to the 'Soothing Waters' spell, which cause it to clean the target's body & clothing and wash out fluids from their orifices.</ul>"
			+"<ul>Added 'Head pat' action to Nyan's romance actions.</ul>"
			+"<ul>Added 'naive' and 'cynical' personality traits. (They are like the other core traits in that they are mostly for roleplaying purposes and will have some limited dialogue variations throughout the game.)</ul>"
			+"<ul>Updated 'Enforcer HQ' map.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Numerous parsing and typo fixes.</ul>"
			+"<ul>Fixed issue with slaves not being correctly identified as being able to ambush you in Dominion even if you gave them the right permissions.</ul>"
			+"<ul>Fixed issue where slaves would never jump you for sex in alleyways that already had a persistent character present in them.</ul>"
			+"<ul>Slaves being held in Slavery Administration will no longer generate slave-interaction events with other slaves.</ul>"
			+"<ul>Silly-mode items are no longer tracked by the Encyclopedia (so that players doing normal playthroughs will not be confused as to why some items are unobtainable).</ul>"
			+"<ul>Fixed minor issue with the display of clothing and weapon Encyclopedia counts passing their totals.</ul>"
			+"<ul>Fixed issue where characters with an unknown race would have their body overview tooltip show that they had crotch-boobs, even if they didn't have any.</ul>"
			+"<ul>Fixed bug where characters' images wouldn't display in their character overview screen if you had the 'American tourist' occupation.</ul>"
			+"<ul>Fixed issue where you couldn't change your Elemental's surname.</ul>"
			+"<ul>Fixed bug where attempting to enslave gang members in the Rat Warrens would cause the game to break.</ul>"
			+"<ul>Fixed issue with pregnancy roulette where sex would end after the character being bred had orgasmed, even if you hadn't orgasmed yet.</ul>"
			+"<ul>You no longer lose affection with characters in the pregnancy roulette game when sex is automatically ended after orgasming.</ul>"
			+"<ul>Newly-created characters are no longer considered to have lost their penile virginity if you give them 'handjobs received' experience.</ul>"
			+"<ul>A character's oral virginity loss description will now correctly be displayed in their body overview screen.</ul>"
			+"<ul>Fixed issue with Natalya's clothing not being displaced when starting sex with her.</ul>"
			+"<ul>Fixed issue where sex scenes with pure virgins could sometimes cause the game to lock up and become unresponsive.</ul>"
			+"<ul>Fixed bug where you could talk to your elemental via the phone action during combat, sex, or other non-neutral scenes.</ul>"
			+"<ul>Fixed issue where Elementals could use arcane scrolls even though they could not use the upgrade points gained from them.</ul>"
			+"<ul>Fixed bug where when Lyssieth self-transformed into her human form, her earrings would be unequipped and placed in her inventory.</ul>"
			+"<ul>Fixed issue with background error being thrown when first entering the Enforcer Warehouse (in Claire's teleportation quest).</ul>"
			+"<ul>Fixed bug where looking at your item Encyclopedia while trading with an NPC would not show any tooltips and throw numerous background errors.</ul>"
			+"<ul>Removed ability to contact previous partners in the nightclub if they are no longer attracted to you (if, for example, you've changed your femininity), as it was causing issues with sex immediately ending.</ul>"
			+"<ul>Fixed issue where maximum limits of testicle size and penis girth were swapped with one another when enchanting clothing with secondary and tertiary size effects.</ul>"
			+"<ul>Clothing which should be discarded on unequip is no longer placed into the unequipping character's inventory as a result of having body parts transformed (such as unequipping a condom when a character's penis is removed).</ul>"
			+"<ul>Fixed issue where milking slaves would sometimes not unequip their milking pumps after finishing their work hours in the milking room.</ul>"
			+"<ul>Fixed issue where opening the 'characters present' screen while managing a slave would result in background errors being thrown and the UI becoming unresponsive.</ul>"
			+"<ul>Fixed bug where if you ran out of items while enchanting during trading, you'd be able to take all of the shopkeeper's items for free.</ul>"
			+"<ul>Fixed issue where enslaving your offspring without first beating them in combat would display the interaction interface after enslaving them.</ul>"
			+"<ul>Fixed issue where slaves generating background sex interaction events would throw background errors if you were in a sex scene at the time they were generated.</ul>"
			+"<ul>Fixed bug where exiting Dominion locations (such as the City Hall or Enforcer HQ) during an arcane storm would spawn persistent characters on your tile without offering you any interactions with them.</ul>"
			+"<ul>Upgrading a room into the spa from the Office's 'Occupancy ledger' will no longer place the 'under construction' tile next to the office instead of next to the spa tile.</ul>"
			+"<ul>Fixed bug where your elemental was being affected by the 'Blinded by Freedom' status effect (from the silly mode's 'American Tourist' background perk).</ul>"
			+"<ul>Fixed bug where the 'Localised Storm' upgrade for the 'Arcane Cloud' spell would not apply lust damage to the character affected by it.</ul>"
			+"<ul>Fixed strawberry and blueberry fluid flavour using plural descriptions where all other flavours were singular.</ul>"
			+"<ul>In her sex scene in Lilaya's room, Rose should no longer have her orifices penetrated during 'quick sex' action generation.</ul>"
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
		credits.add(new CreditsSlot("A(woo)CE", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("BL4Z3ST0RM", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("~Chai~", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
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
