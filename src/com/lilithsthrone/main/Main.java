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
 * @version 0.3.5.5
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
	public static final String VERSION_NUMBER = "0.3.5.9";
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
			+ "I was a little busier over the Christmas period than I expected, so I didn't really get much time in which I could sit down and get on with the writing for Axel's quest."
			+ " I did manage to grab time here and there in which to fix bugs, however, and then over this weekend I've got as much writing done as I could, but there is still a lot left to add..."
		+ "</p>"
			
		+ "<p>"
			+ "So, with that in mind, please be aware that there is still a lot of placeholder dialogue in this version of Axel's quest."
			+ " [style.italicsBad(I would advise only playing through Axel's quest if you want a sneak peek of the sort of content involved!)]"
			+ " A more polished version (v0.3.6) will be released as soon as possible!"
		+ "</p>"
			
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.5.9</h6>"
			+"<li>Contributions:</li>"
			+"<ul>Fixed NullPointerException bug when visiting Lyssieth. (by Stadler)</ul>"
			+"<ul>Fixed several bugs and typos. (PR#1250 by LewdAlt)</ul>"
			+"<ul>Added finger to crotch-nipple interactions. (PR#1243 by LewdAlt)</ul>"
			+"<ul>Fixed issue with sex in an apartment being public when the occupant has just moved in. (PR#1251 by AceXP)</ul>"
			
			+"<li>Artwork:</li>"
			+"<ul>Added artwork of Meraxis, drawn by FriendlyAlienFriend. There are 6 images of her as a half-demon, and 6 variations for when she's a full demon. These include pregnant and non-pregnant variations.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Filled in some of the Axel's quest placeholders, although there are still a lot of placeholders left (will all be filled in for the next update).</ul>"
			+"<ul>Added 'slovenly' speech modifier, which converts all speech into a representation of speaking with poor pronunciation. There is a 25% chance for muggers and alleyway prostitutes to have this modifier.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Moved demonstone melee weapons out into external res folder.</ul>"
			+"<ul>Added support for weapon mods to have spells based on their damage type, and also a variable to set whether spells should regenerate when the weapon's damage type is changed.</ul>"
			+"<ul>Added support for weapons to use unarmed damage calculations. Demonstones and knuckle dusters now use unarmed damage calculations instead of those for melee weapon.</ul>"
			+"<ul>Slightly improved the 'arm wraps' icon.</ul>"
			+"<ul>Moved all 'head' slot clothing items out into external res folder. Slightly tweaked some base values of these items, and the 'circlet' is now unisex. Added dye options to the 'antler headband'.</ul>"
			+"<ul>Added 'paw-print' and 'horse-shoe' tattoos.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added 'pale pink' as a skin colour, and changed default rat tail skin colour from light pink to pale pink.</ul>"
			+"<ul>Random attackers in Submission's tunnels now have a 5% chance to be taurs.</ul>"
			+"<ul>Arctic-fox-morphs now have white hair and pale skin by default.</ul>"
			+"<ul>NPCs who are able to self-transform back into their preferred gender identity (after you've transformed them into something else), will now only alter their femininity, breast size, and genitals. They will no longer completely reset all aspects of their bodies.</ul>"
			+"<ul>The 'Slap ass' sex action can now be used on characters bent over a desk/table by the character standing behind.</ul>"
			+"<ul>Scarlett (and Helena) now close their slave shop at 22:00 and open it again at 06:00.</ul>"
			+"<ul>If you equipped jinxed clothing which also has the 'servitude' enchantment (preventing you from unjinxing clothing), you can now visit Lilaya to have your clothing unjinxed.</ul>"
			+"<ul>Added 'arctic-youko-morph' subspecies detection.</ul>"
			+"<ul>Added a small warning about there being a potential fight in the tile outside Brax's office.</ul>"
			+"<ul>Improved naturally-spawning wolf-morph fur colours to be closer to realistic values.</ul>"
			+"<ul>NPCs now require both oral fetishes to start sucking their own tails.</ul>"
			+"<ul>Slightly improved 'Object of desire' perk icon, and changed 'Convincing requests' perk icon.</ul>"
			+"<ul>Added 'orgasmic level drain' perk, which allows you (and NPCs) to drain levels from orgasming partners in sex. (NPCs will not randomly spawn with this perk, and you can disable unique NPC usage of this perk in the content settings.)</ul>"
			+"<ul>Amber now drains your levels in sex while you're wearing her collar (but only if the 'Level drain' setting is enabled).</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>Fixed issues with fetish tease attack descriptions not making much sense when enemies were using them.</ul>"
			+"<ul>Using the fast travel actions in Lilaya's house now correctly sets the response tab to 'actions' upon arrival.</ul>"
			+"<ul>Fixed missing dialogue when playing without a companion and offering your body to a random attacker in Dominion's alleyways.</ul>"
			+"<ul>Fixed issue where random demonic attackers could spawn as taurs, even if you had taurs turned off.</ul>"
			+"<ul>Fixed bug where performing any action in your room would cause the 'Bathroom' tab to disappear.</ul>"
			+"<ul>Fixed bug where you could rape non-slave companions via interacting with a slave in that slave's room.</ul>"
			+"<ul>Lyssieth now correctly reverts to her human form after having sex with her in her lilin form.</ul>"
			+"<ul>Fixed issue where demons encountered in Dominion's 'dark alleyway' tiles would disappear after sex, and would also be unable to be talked to.</ul>"
			+"<ul>Fixed feral half-demon taur genitals always being referred to as being that of a 'demonic-horse', and not the subspecies that was appropriate.</ul>"
			+"<ul>Fixed speech actions which had 'blocked mouth' alternative text being unavailable when the performing character's mouth was blocked in sex.</ul>"
			+"<ul>Unique NPCs can no longer be fed transformative or fetish-altering consumables during post-combat-victory sex scenes.</ul>"
			+"<ul>Fixed issue where requesting to perform oral on a taur would sometimes result in them responding with a blank action.</ul>"
			+"<ul>Fixed Helena being duplicated if loading in from an older version.</ul>"
			+"<ul>Fixed Silence's high affection towards Shadow incorrectly being set as being towards herself.</ul>"
			+"<ul>Arcane feathers now correctly grant spells when equipped, based on their damage type (like they used to a version or two ago).</ul>"
			+"<ul>Fixed Maximillian's surname to properly reflect his demonic heritage.</ul>"
			+"<ul>Fixed bug where selection of randomly spawned weapons in Dominion's alleyways was very limited.</ul>"
			+"<ul>Shadow now spawns wearing both arm wraps and a stomach sarachi (so that her crotch boobs (if enabled) are not exposed). </ul>"
			+"<ul>Fixed 'addiction satisfied' and' psychoactive effect applied' descriptions being displayed every turn in sex when you were eating someone out who had those appropriate modifiers on their girlcum (although the effects are still applied).</ul>"
			+"<ul>Fixed bug where using the 'Quick sex' action would not count participants having orgasmed, causing post-sex scenes to show the 'not satisfied' variant (if one existed).</ul>"
			+"<ul>Fixed related bug to the one above where dominant partners would not use the correct number of sex actions corresponding to their desired orgasms when using the 'Quick sex' action.</ul>"
			+"<ul>Fixed several issues with sex actions being incorrectly available when in missionary sex with a taur.</ul>"
			+"<ul>Fixed the debug menu's 'Centaur' action sometimes spawning a human.</ul>"
			+"<ul>Fixed bug where characters could have their birthday set to February 29th in a non-leap year.</ul>"
			+"<ul>Fixed cause of an issue where the game would sometimes randomly freeze for seemingly no reason. (This was being caused by pregnant NPCs with sealed clothing giving birth in the background update loop.)</ul>"
			+"<ul>Fixed bug where passing into a new year would cause the game to think it was always night time.</ul>"
			+"<ul>Fixed Murk's penis shrinking transformation incorrectly causing your penis to grow.</ul>"
			+"<ul>Characters can now take less than 1 lust damage from the self-inflicting lust damage effect of masochist and sadist fetishes.</ul>"
			+"<ul>Sex count tracking is now only incremented for non-spectators (so watching two people have sex no longer counts as you having had sex as well).</ul>"
			+"<ul>Fixed 'quick sex' not counting creampies correctly, causing tattoo counters to not increment.</ul>"
			+"<ul>Fixed bug where dying/reforging equipped unique weapons would duplicate the weapon into your inventory.</ul>"
			+"<ul>Friendly occupants who have a job are now correctly displayed as generating 100 flames per day (to cover their room's upkeep cost).</ul>"
			+"<ul>Fixed an issue in clothing displacement method which could sometimes cause bugs.</ul>"
		+ "</list>"
			
		+ "<br/>"
			
		+ "<list>"
			+ "<h6>v0.3.5.8</h6>"
			+"<li>Axel's quest:</li>"
			+"<ul>Tidied up fetish gain text in captive TF routes.</ul>"
			+"<ul>Fixed issues with NPC targeting in the post-combat-loss stocks sex scene.</ul>"
			+"<ul>Fixed numerous bugs and issues with dialogue flow in the Murk captive loss route.</ul>"
			+"<ul>Added and altered some scenes in Murk's captive loss route.</ul>"
			+"<ul>Locked several of the transformation administered by Murk behind the new 'gape' content toggle.</ul>"
			+"<ul>Murk now wields a metal bat.</ul>"
			+"<ul>If involuntary NTR is off, you now get the option to have your companion escape at the end of combat losses.</ul>"
			+"<ul>You can now save the game when Murk's captive (at night, when you can move around).</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added: 'Wooden bat' and 'Metal bat' melee weapons. One-handed, sold by Vicky. Muggers can sometimes be found using them.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>Orifice elasticity is now factored into the calculation of whether a penetration is too big to fit comfortably into an orifice. The stretch calculation also now more accurately takes into consideration the penetrating object's size.</ul>"
			+"<ul>At lower elasticity values, orifices now stretch out slower and stretch out to sizes closer to the penetrating object's size (due to the change above).</ul>"
			+"<ul>Penis girth is now factored in when calculating if a penis is too large for an orifice.</ul>"
			+"<ul>NPCs will no longer start sucking their own tail during sex unless they have an oral fetish.</ul>"
			+"<ul>Crotch-nipples now have stretch effects when being penetrated in sex like all other orifices.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>The 'locked in bondage' clothing set is now activated when wearing at least 3 set items instead of at least 4.</ul>"
			+"<ul>Zebra, leopard, and lion morphs now have 5 Fire shielding. Snow leopard morphs now have 5 Cold shielding.</ul>"
			+"<ul>Added 'arctic-fox' subspecies, which is detected by being a fox-morph with white fox-fur.</ul>"
			+"<ul>Added 'dark blue' and 'blue' to eye colours. The old value of 'blue' eyes is now classified as 'light blue'.</ul>"
			+"<ul>Slightly reduced save file size.</ul>"
			+"<ul>Swapped 'thin' and 'slender' penis girth decriptors (so 'thin' is now the thinnest).</ul>"
			+"<ul>Character view's capacity descriptors for anus and vagina are now coloured according to their capacity.</ul>"
			+"<ul>Added inverted nipples as a new nipple shape. There's a 2.5% chance for characters with breasts to spawn in with inverted nipples. Added a transformation for inverted nipple shape.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing fixes.</ul>"
			+"<ul>Fixes issue with 'Anal control' action being unlocked by having an internally-muscled vagina, instead of internally-muscled anus.</ul>"
			+"<ul>Fixed some (harmless) background errors being thrown when half-demon characters were generated.</ul>"
		+ "</list>"
		
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.5.7</h6>"
			+"<li>Contributions:</li>"
			+"<ul>Fixed some stores in the Shopping Arcade causing a softlock. (PR#1227 by triples941)</ul>"
			+"<ul>Fixed several issues with random attacker dialogue not displaying correctly. (PR#1231 by triples941)</ul>"
			+"<ul>Made some slight adjustments to Maven configuration. (PR#1235 by Zsar)</ul>"
			+"<ul>Fixed issue with NPC sex positioning sometimes causing a game-breaking bug in sex. (by Rist)</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added a new quest for Axel. (There is a lot of placeholder dialogue for now, and is still very rough around the edges. It will be improved for the next update!)</ul>"
			+"<ul>Added a 'Quick Sex' action to sex, under the 'Misc. Actions' tab. Selecting this will immediately end sex, but apply all effects as though (some basic) sex had taken place. A short description of the sex actions performed will be displayed.</ul>"
			+"<ul>Added a 'Gape content' preference, which by default is turned on (keeping the game's behaviour as it always has been). When turned off, 'gaping' descriptions of orifices are replaced with less extreme descriptions (such as just 'loose'), and any special gape-related content will be hidden.</ul>"
			
			+"<li>Artwork:</li>"
			+"<ul>Added: 16 images for Lyssieth drawn by FriendlyAlienFriend. These include variations for human/lilin forms, pregnancy, and futa.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Demons no longer spawn with tentacles in their anus and vagina. (I'll add those for different demon varieties in the jungle and some other places later on.)</ul>"
			+"<ul>Improved generic sex effects (such as when slaves are having sex when you are not present) to take into account girlcum and milk being drunk, condoms being used, orifices being stretched, and body parts being dirtied by cum.</ul>"
			+"<ul>Moved dice poker and some more of Slaver Alley's dialogue text into external .txt files.</ul>"
			+"<ul>Added 'Bounty Hunter Lodge' location in Slaver Alley.</ul>"
			+"<ul>Renamed 'Alexa' to 'Helena'. (For three reasons: I felt there were a disproportionate amount of 'A'-named unique NPCs; I wanted her name to be similar to a beautiful character from mythology; I didn't want her to share a name with a popular intrusive electronic device.)</ul>"
			+"<ul>NPCs will now prefer to move out of the 'standing' position if they want to have penetrative sex.</ul>"
			+"<ul>Added day incrementing to debug menu's 'Misc.' actions.</ul>"
			+"<ul>In combat, NPCs should now only cast status-effect-application spells on suitable targets (i.e. they should no longer continuously use spells such as 'poison vapours' on a target which already has has associated status effect).</ul>"
			+"<ul>In combat, NPCs will now use a wider range of moves available to them, and will be a lot more willing to cast defensive spells on themselves or their allies.</ul>"
			+"<ul>You can now transform vaginas of pregnant characters (but cannot remove them). The unborn offspring are unaffected by this.</ul>"
			+"<ul>Added 'Mute' personality trait, which at the moment is unavailable to the player, and has a 0.01% of being given to a random NPC.</ul>"
			+"<ul>Added options to have repeat sex with Lyssieth in her lilin form after you've been transformed into a demon.</ul>"
			+"<ul>Improved Lyssieth's sex preferences when having repeat sex with her in her office.</ul>"
			
			+"<li>Items:</li>"
			+"<ul>Added: 'Pistol crossbow' (common, one-handed, ranged, sold by Vicky).</ul>"
			+"<ul>Updated: Chaos feather (rare) to 'rough arcane feather', and Chaos feather (Epic) to 'arcane feather'. Updated icons for each, and increased their value.</ul>"
			+"<ul>Added: 'Pristine arcane feather' (legendary, one-handed, ranged, sold by Vicky).</ul>"
			+"<ul>Added support for tertiary colouring of weapons.</ul>"
			+"<ul>Added a 'self-transformation inhibition' enchantment, which can be added to clothing and tattoos by selecting the primary modifier 'Special effects' and then the secondary modifier 'Self-Transformation inhibition'. While worn/tattooed onto someone, this enchantment prevents demons and slimes from accessing the self-transformation menu.</ul>"
			+"<ul>Moved slave collar into res folder, improved icon and description, and added more recolouring options. Default enchantments for slave collar are now -15 arcane, -15 lust resist, sealing, enslavement, and self-transformation inhibition.</ul>"
			+"<ul>Added 'removal' as a secondary modifier for breast transformation potions, which completely flattens breasts when used.</ul>"
			+"<ul>Moved all leg clothing into external res folder, and slightly improved several leg clothing icons (skirt, miniskirt, pleated microskirt, belted microskirt, bike shorts, hotpants, tight jeans, jeans).</ul>"
			+"<ul>Added a requirements element to tattoo xml files, so you can limit tattoo availability based on the target who is receiving it. (See the 'heart womb' tattoo xml file for an example.)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed background error sometimes being thrown when generating half-demons, which was blocking randomly-spawned characters from being half-demons.</ul>"
			+"<ul>Quite a few typo, parsing, and formatting fixes.</ul>"
			+"<ul>Fixed some background errors when in non-companion dialogue for the random harpy attackers, Submission tunnel attackers, and Bat cavern attackers.</ul>"
			+"<ul>Fixed incorrect descriptions when an enemy forces a transformation potion onto your companion (after being defeated in combat).</ul>"
			+"<ul>Asymmetrical skirt now correctly blocks the anus slot when not pulled up.</ul>"
			+"<ul>NPCs spawning with randomly generated weapons will now have enough essences to use their weapons (if any of them have an essence cost).</ul>"
			+"<ul>Fixed incorrect public sex descriptions from being displayed while you were watching sex in public places.</ul>"
			+"<ul>Fixed 'Offer body' dialogue not working in random attacker scenes when you didn't have a companion with you.</ul>"
			+"<ul>Fixed bug where bat cavern random bat-morph attackers would not initialise correctly (and therefore no bat-morph enemies would spawn when you were travelling through or exploring the tunnels of the bat caverns).</ul>"
			+"<ul>Fixed issue where randomly spawned clients in Angel's Kiss might sometimes not be cleared up correctly.</ul>"
			+"<ul>Fixed formatting bug where expanding a character's 'Stats' information box would conceal the 'Perks' information box beneath it.</ul>"
			+"<ul>Fixed some cases where menu navigation could get slightly broken when sending companions home or dismissing elementals.</ul>"
			+"<ul>Fixed issue where characters would not be recognised as half-siblings if one or more of the three relevant parents were removed from the game.</ul>"
			+"<ul>Corrected Slime Queen's job perk icon.</ul>"
			+"<ul>Companion's elementals should no longer do increased damage if the difficulty setting is meant to be boosting enemy damage.</ul>"
			+"<ul>Fixed application of lisp and stutter in speech causing style tags to break.</ul>"
			+"<ul>Fixed player's gender identity not being correctly set (this shouldn't affect anything, and if it's used in future content, I'll provide a way to define it).</ul>"
			+"<ul>Breast feeding and other oral+nipple actions are no loner blocked when nipple penetration content is turned off.</ul>"
			+"<ul>Fixed issue where the UI could sometimes freeze on the 1st of every month.</ul>"
			+"<ul>Fixed bug where half-demon offspring would not initialise correctly and cause game-breaking freezes. This was being encountered at the moment of impregnation if generated offspring were half-demons.</ul>"
			+"<ul>Fixed mouth orifice always being referred to as 'a mouth'.</ul>"
			+"<ul>Fixed issues with Roxy sometimes being happy to switch to anilingus or other sex types.</ul>"
			+"<ul>Random NPCs will no longer spawn wearing Enforcer's tactical combat boots.</ul>"
			+"<ul>'Impregnations' tattoo counter should now function correctly.</ul>"
			+"<ul>Helena will no longer return to her nest after taking over Scarlet's shop in slaver alley.</ul>"
			+"<ul>Fixed bug where clothing unequipped as a result of a body part transformation (such as unequipping a tail ribbon when losing your tail) would not revert attribute modifications from that clothing item.</ul>"
			+"<ul>Clothing which requires a penis to be equipped (condoms & chastity cages) can no longer be equipped onto strapons or dildos. (This change was made due to an underlying conflict in the code, but I will try to refactor it at some point to re-enable this behaviour.)</ul>"
			+"<ul>Meraxis (The Dark Siren) will now correctly use all of the spells at her disposal.</ul>"
			+"<ul>Fixed minor issue where NPCs' home locations were not cleared correctly upon being removed from the game.</ul>"
			+"<ul>Fixed issue with NPCs' combat move selection not working correctly.</ul>"
			+"<ul>Fixed some incorrect skin colours on Axel.</ul>"
			+"<ul>Fixed 'Offer body' in the post-loss dice poker scene being available even if the gambler is not attracted to you.</ul>"
		+ "</list>"
			
		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.5.4</h6>"
			+"<li>Engine:</li>"
			+"<ul>Improved handling of turn updates, which has reduced the amount of lag encountered each turn, especially when the time would pass into a new hour.</ul>"
			+"<ul>Improved speed of body generation for randomly-generated NPCs.</ul>"
			+"<ul>Moved more text content into external txt files.</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Day/night time is now based on real-world sunrise/sunset times for the coordinates 51.4934 N, 0.0000 E, so days are shorter in winter, and longer in summer.</ul>"
			+"<ul>Added a minor darkening effect to the map when in civil & nautical twilight, and a major darkening effect when in astronomical twilight or night time.</ul>"
			+"<ul>Added sunrise/sunset info to the clock tooltip.</ul>"
			+"<ul>Sleeping in your room or a friend's apartment is now to the next sunrise or sunset, instead of to 07:00 or 21:00.</ul>"
			+"<ul>All shops in the Shopping Arcade are now only open between 06:00 and 22:00.</ul>"
			+"<ul>Candi's desk is only open between 09:00 and 17:00.</ul>"
			+"<ul>Zaranix's home is locked up between 22:00 and 06:00.</ul>"
			+"<ul>The harpy matriarchs go to sleep between 22:00 and 06:00.</ul>"
			+"<ul>Lilaya and Rose go to bed at 22:00 and return to the lab at 06:00. The 'lab assistant' and 'test subject' slave jobs are no longer available during these times.</ul>"
			+"<ul>Breastfeeding/Nipple-kissing is now an ongoing action, with the target's milk (if they are lactating) being drunk every turn. Also improved descriptions of these actions to factor in nipple-shape.</ul>"
			+"<ul>You can now take a bath in your room's ensuite, which cleans your companions and applies a regenerative-boosting status effect. (Descriptions are still a 'TODO')</ul>"
			+"<ul>Roxy now smokes and has this reflected in her dialogue. She also no longer wears lipstick (which I thought might be weird on a greater rat-morph face).</ul>"
			+"<ul>Added some unique sex actions in Roxy's facesitting scene: her smoking; her teasing you; a customer briefly interrupting her. Her sex scene now also correctly starts with you eating her out, and her dress pulled up.</ul>"

			+"<li>Items:</li>"
			+"<ul>Added cigarettes as individual items and as packs of 20. Smoking cigarettes provides a status effect which applies +10 aura and -5 health.</ul>"
			+"<ul>Added  'rat skull' tattoo.</ul>"
			+"<ul>Slightly improved the short sleeve shirt icon and added dye colours for the buttons & label.</ul>"
			+"<ul>Added 'Kerambit' weapon (one handed, sold by Vicky).</ul>"
			+"<ul>Added 'Gemstone ring' (finger slot, sold by Nyan).</ul>"
			+"<ul>Added 'Demonstone necklace' (neck slot, sold by Nyan).</ul>"
			+"<ul>Moved internal finger and neck clothing into the res folder.</ul>"
			+"<ul>Added secondary colours to heart and ankh necklaces, and you can now equip these necklaces (as well as sun and snowflake necklaces) into the wrist slot.</ul>"
			+"<ul>Improved bell collar and added secondary and tertiary dye colours for metal parts.</ul>"

			+"<li>Other:</li>"
			+"<ul>You can now equip unique clothing & weapons onto NPCs. If these NPCs are removed from the game at any point, the unique clothing/weapons which they have equipped are returned to your inventory.</ul>"
			+"<ul>Reduced the arcane lightning globe's base damage from 25 to 15, and reduced its value from 500,000 to 250,000.</ul>"
			+"<ul>If you have an alligator-morph vagina, you now get the egg-laying birthing variation.</ul>"
			+"<ul>Changed the 'rose' gift item into an item of clothing, which can now be worn in the 'hair' slot. Any roses already in your game will be automatically converted into their clothing counterpart when you load into this version.</ul>"
			+"<ul>Added 'pitch black' for eye colours.</ul>"
			+"<ul>Added 'bun' and 'chignon' hair styles, and reduced the chance of NPCs spawning with 'unusual' hair styles (from 20% to 10%).</ul>"
			+"<ul>Added a debug consumable item which turns the drinker into a youko, and adds a tail to drinkers who are already a youko.</ul>"
			+"<ul>Added 'footjobs' to the sex stats screen (only displayed if you have foot content turned on). Anal, nipple-penetration, and urethral sex stats are now hidden if you have the associated content disabled.</ul>"
			+"<ul>Added personality setting tab to debug menu.</ul>"
			+"<ul>Clothing & weapons enchanted to remove corruption now cost enchantment stability, equal to the value of corruption removed.</ul>"
			+"<ul>NPCs who have a positive desire towards the 'cum stud' or 'impregnation' fetishes, and are either selfish or in the rough pace, will now refuse to wear condoms in sex. </ul>"
			+"<ul>Added a 'fast travel' tab which is available everywhere within the shopping arcade.</ul>"
			+"<ul>You can now suggest a job (or change of job) to any of the friendly occupants either living with you or in their own apartment.</ul>"
			+"<ul>Added an indication that the main quest currently ends at the 'Finding the youko' quest in the quest log.</ul>"
			+"<ul>Added more slave greeting and sleeping dialogue variations for when they are assigned to your room. Also added slave-related quick/thorough wash descriptions.</ul>"
			+"<ul>Added office tile to gambling den map.</ul>"
			+"<ul>Reduced 'vaccuum' spell's 'secondary voids' effects from -20 to -5 energy shielding, and -25 to -15 critical damage.</ul>"

			+"<li>Forced-transformation content:</li>"
			+"<ul>NPCs now have a gender, subspecies, and race-stage preference, instead of a fully pre-generated body preference. This should help to cut down on save file size.</ul>"
			+"<ul>NPCs will no longer try to transform you if you are a demon, nor try to transform you into a demon or imp (although they will still force non-racial transformations onto you). Youko tail addition/removal transformations have also been disabled.</ul>"
			+"<ul>Fixed issues with forced TF potions not applying genital changes correctly, and the NPC getting stuck on trying to change your genitals even when you had the genitals they wanted.</ul>"
			+"<ul>Fixed issue with NPCs trying to transform away any strap-on that you were wearing.</ul>"
			+"<ul>Improved NPCs transformation selection for the potions they force on you.</ul>"
			+"<ul>Added more dialogue for each part that an NPC transforms.</ul>"
			+"<ul>Forced transformation content availability is no longer linked to the non-con content toggle.</ul>"
			+"<ul>NPCs who want to transform you into a slime will now be able to do so without getting stuck.</ul>"
			+"<ul>Demons in the dark alleyway tiles have had forced transformation behaviour added to them. (They usually end up preferring demons, however, in which case they will only attempt to transform your gender.)</ul>"
			+"<ul>Added penis girth, ball size, body size, muscle, vagina capacity, ass capacity, and ass wetness to forced transformation variations.</ul>"
			+"<ul>Increased non-racial TF amounts in a single forced-TF (such as ass size or height being increased/reduced by larger amounts).</ul>"
			+"<ul>NPCs will now transform both you and your companion.</ul>"
			+"<ul>NPCs will now apply fetish changes as well as body transformations in the same scene.</ul>"
			+"<ul>NPCs will no longer TF if mouth blocked.</ul>"

			+"<li>Random encounters:</li>"
			+"<ul>Changed and expanded dialogue for post-defeat transformations and sex for: Dominion alleyway; Harpy nest; Submission tunnels; Demon alleyway; Cavern attacker/slime/bat.</ul>"
			+"<ul>Added quick TF options (in the combat's post-victory actions) to all these scenes.</ul>"
			+"<ul>NPCs will now choose whether to have sex with you, your companion, or both of you after defeating you.</ul>"
			+"<ul>NPCs who dislike raping will no longer rape.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>Fixed issue with the Office's 'Occupancy ledger' sometimes causing a background error to be thrown, which was sometimes breaking the game.</ul>"
			+"<ul>Fixed similar issue where accessing inventories through the slave list would sometimes result in not being able to close the inventory, leading to numerous background errors being thrown.</ul>"
			+"<ul>Fixed another issue with the 'Occupancy Ledger', where inspecting a friendly occupant would cause the game to throw several background errors.</ul>"
			+"<ul>Fixed bug where slaves assigned to sex-related jobs would have their virginity loss descriptions reset.</ul>"
			+"<ul>Jules no longer wears socks on his hoofs.</ul>"
			+"<ul>Fixed bug where previously-creampied orifices would not be recognised as being lubricated by cum at the start of sex.</ul>"
			+"<ul>Fixed bug where room upgrades were not affecting obedience & affection gains for slaves working there.</ul>"
			+"<ul>Any weapons that are unequipped during sex are now correctly re-equipped when sex ends.</ul>"
			+"<ul>Fixed bug where backing out of the inventory screen during sex would sometimes return you to the scene you were in before sex, while not correctly ending sex, causing the game to break.</ul>"
			+"<ul>Fixed yet more bugs in stuttering characters' speech effects.</ul>"
			+"<ul>Fixed minor issue where checking imp gang inventories defaulted the response tab to the empty 'selected item' one, rather than the 'overview' one.</ul>"
			+"<ul>Fixed bug where breast/nipple interactions would be disabled when receiving a blowjob in the sixty-nine position.</ul>"
			+"<ul>Fixed bug where clothing tooltips would display the rarity of unidentified items, letting you know if they were jinxed or not without first identifying them.</ul>"
			+"<ul>Fixed 'Mystery Kink' not being recognised as a fetish-giving item, and 'Glowing Mushrooms' not being recognised as transformative, which was allowing some unique NPCs to consume them during sex.</ul>"
			+"<ul>Fixed issue where offspring could sometimes spawn with 'heterochromatic' eyes that both had the same colour.</ul>"
			+"<ul>Fixed bug causing incorrect descriptions when removing horns.</ul>"
			+"<ul>Fixed incorrect action labels in debug's race reset screen.</ul>"
			+"<ul>Fixed bug where buying items from Vicky would clear the entire stack of that item from her inventory the next time you opened her trade menu.</ul>"
			+"<ul>Fixed Takahashi not having 3 youko tails as intended.</ul>"
			+"<ul>Fixed Fury offspring (half-demon harpies) spawning in Dominion (they now only spawn in the harpy nests). Also fixed offspring of non-Submission half-demon subspecies spawning in Submission's tunnels.</ul>"
			+"<ul>Fixed equipped piercings displaying a 'No tattoo' tooltip when hovering over them in tattoo view mode.</ul>"
			+"<ul>Fixed tertiary dye colours not displaying for several items of clothing.</ul>"
			+"<ul>If, in a previous version, Brax has somehow bugged out after completing the side quest to obtain him, he will correctly have his ownership transferred to you when you load into this version.</ul>"
			+"<ul>Fixed item and clothing encyclopedia entries not being added if you used an item from an NPC's inventory, or equipped clothing onto an NPC.</ul>"
			+"<ul>Fixed incorrectly describing Kate's breasts as E-cup.</ul>"
			+"<ul>Fixed footjob climax tattoo counter not working.</ul>"
			+"<ul>Fixed bug where footjob sex stats were not being saved/loaded correctly.</ul>"
			+"<ul>Fixed sex bug where characters couldn't start oral actions while they were receiving a footjob.</ul>"
			+"<ul>Fixed bug where performing/receiving foot-related actions would result in a huge lust drain per turn if neither participant had a positive desire towards foot fetishes. This will not affect NPCs' decision to use foot actions (which they prefer to avoid unless they have an associated fetish).</ul>"
			+"<ul>Fixed bug where the 'Localised Storm' upgrade for the Arcane Cloud spell would affect enemies of the target, instead of all their allies.</ul>"
			+"<ul>Fixed bug where clothing was able to be equipped in sex into slots which were not supposed to be available.</ul>"
			+"<ul>You can no longer equip clothing during sex into inventory slots that are involved in an ongoing action.</ul>"
			+"<ul>Fixed Rose's handholding sex scene missing the mouth-finger interactions which were present in older versions.</ul>"
			+"<ul>Fixed 'Suck clit' sex action having arousal increment values reversed for the performer/target.</ul>"
			+"<ul>Fixed enslavement collars not spawning with the enslavement enchantment.</ul>"
			+"<ul>Fixed dates before 0CE being displayed as a negative value, and are now correctly displayed as 'xxxx BCE'.</ul>"
			+"<ul>Fixed bug in the flow of dialogue when talking to Candi for the first time about Brax.</ul>"
			+"<ul>Fixed clothing enchantments which reduce corruption being regarded as 'bad' enchantments.</ul>"
			+"<ul>Fixed broken condom equip behaviour for cultists, dark alleyway demon attackers, Kate, and Ralph.</ul>"
			+"<ul>Fixed bug where slaves who were given the permission to use you would only do so when they were supposed to be at work, instead of when they had free time (set to 'idle').</ul>"
			+"<ul>Actions using a parnter's clit are no longer displayed as greyed-out actions, until you know that the partner has a vagina.</ul>"
			+"<ul>Fixed another cause of the bug where slaves jumping you when sleeping would not work correctly.</ul>"
			+"<ul>Fixed some issues with the lactation fetish special tease attack, and made it available to characters who have crotch boobs which are lactating.</ul>"
			+"<ul>When starting sex, the active response tab should no longer start on one that has no actions available in it.</ul>"
			+"<ul>Fixed some incorrect descriptions of the 'choke' action in multiple-character sex scenes.</ul>"
			+"<ul>Fixed bug where character home icons would not be shown on any tiles that bordered the map's lower or left edges.</ul>"
			+"<ul>Fixed some references to Jhortrax fathering imps instead of half-demons.</ul>"
			+"<ul>Girlcum fluid modifiers are now described in a character's appearance page.</ul>"
			+"<ul>Characters performing oral on a vagina will now correctly ingest girlcum (once the vagina is lubricated by girlcum).</ul>"
			+"<ul>Fixed bug where incorrect dialogue would be displayed when slaves jumped you for sex after sleeping.</ul>"
			+"<ul>Fixed some issues with half-demon subspecies detection, which should have resolved the unusual self-transformation behaviour encountered after turning yourself into a half-demon through the debug menu.</ul>"
			+"<ul>Fixed alligator hair-scales and slime hair being called by the wrong name.</ul>"
			+"<ul>Fixed branching quests not correctly saving/loading the quest route you selected (was just affecting the slime queen quest, but wasn't having any impact on gameplay).</ul>"
			+"<ul>Blaze and Crystal (the Slime Queen's guards) are now correctly identified in the code as being siblings (but only if you have incest content turned on).</ul>"
			+"<ul>Fixed bug where post-sex dialogue after beating and then submitting to the imps in the Dark Siren's fortress would be the 'defeated' variant instead of the correct 'victory' variant.</ul>"
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
		credits.add(new CreditsSlot("GentleTark", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("QW", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Master Isami", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
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
			
		} else if (!Main.game.isStarted() || !Main.game.getCurrentDialogueNode().equals(Main.game.getDefaultDialogue(false))) {
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
		return Main.game.isStarted() && Main.game.getSavedDialogueNode() == Main.game.getDefaultDialogue(false);
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
