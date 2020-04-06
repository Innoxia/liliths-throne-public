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
 * @version 0.3.6.9
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
	public static final String VERSION_NUMBER = "0.3.7";
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
			+ "Hello once again!"
		+ "</p>"
		
		+ "<p>"
			+ "Sorry about the unfinished state of this release. I'll try to get a more polished version out as soon as I can!"
		+ "</p>"
			
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne, and a very big thank you to all of you who support development by reporting bugs, making PRs, or backing me on SubscribeStar!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

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
			
		+"<br/>"
		
		+ "<list>"
		+ "<h6>v0.3.6.9</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added sleeveless Enforcer over-torso clothing variations (intended for Enforcers who cannot wear regular jackets, due to having wing-arms). This includes 5 'waistcoat' and 2 'sweater vest' variations. (by DSG)</ul>"
			+"<ul>Added 'Short-sleeved combat shirt' and 'Enforcer's short-sleeved combat shirt' as short-sleeved variations for the two combat shirts. (by DSG)</ul>"
			+"<ul>Typo fix. (PR#1290 by TadUnlikely)</ul>"
			+"<ul>Fixed bug where you couldn't orgasm when having sex with Amber when not wearing any neck clothing. (PR#1298 by triples941)</ul>"
	
			+"<li>Engine/Code:</li>"
			+"<ul>Converted the following Enums into classes: AntennaType, AnusType, EyeType, TongueType, MouthType, FaceType, and TailType.</ul>"
			+"<ul>Body class now additionally saves the version it was created in.</ul>"
			+"<ul>Reformatted all of the Orifice classes.</ul>"
			+"<ul>Moved all piercing clothing items into external res files.</ul>"
	
			+"<li>Gameplay:</li>"
			+"<ul>Added 'Argus's DIY Depot' as a new location in Dominion (several tiles south of Slaver Alley, next to the canal). (I have plans for this location to be involved in property improvement content later on.)</ul>"
			+"<ul>Added 'Warehouse District' as a new location in Dominion (several tiles north of Slaver Alley, next to the canal). (I also have plans for this area later on.)</ul>"
			+"<ul>Added a repeat sex scene with Scarlett in Helena's nest (for if she's present up there after choosing to free her).</ul>"
			+"<ul>Vaginal virginity can no longer ever be regained. Removing/regrowing a vagina now only restores your hymen, not the concept of virginity itself. Regrowing a hymen in this way (and also losing your hymen via using sex toys) applies modifiers to the 'pure virgin' status effect.</ul>"
			+"<ul>Edited and updated all of Helena's and Scarlett's dialogue.</ul>"
			+"<ul>Updated some of Helena's and Scarlett's stats, fetishes, and clothing.</ul>"
	
			+"<li>Penetrative size-difference:</li>"
			+"<ul>Fixed bug where diameters of tails and penises could be less than or equal to zero.</ul>"
			+"<ul>Tails' diameter now tapers off from the base, depending on the tail type's tapering factor. Demonic tails have an exponential taper, alligator tails have linear tapering, and most furry tails have no tapering. You can see the diameter tapering in the body stats menu.</ul>"
			+"<ul>The tapered diameter of tails is now factored into how much an orifice stretches (so it only stretches up to the diameter of the length of maximum tail insertion).</ul>"
			+"<ul>The 'size queen' fetish now correctly applies positive arousal gains from 'stretching' and 'uncomfortably deep' penetrations in sex.</ul>"
			+"<ul>Added indication in subspecies status effect tooltip for if your body material is allowing extra long penetrations (only slimes and elementals have this).</ul>"
			+"<ul>Slimes and elementals now consider penises to be 'far too short' as if their orifices were a normal depth (to prevent them from considering pretty much every partner to be too small).</ul>"
			+"<ul>Ongoing penetration tooltips now display how much of a penetration is inserted into the orifice, as well as its maximum length, and also the comfortable/uncomfortable depth of the orifice.</ul>"
			+"<ul>Fixed issue with submissive partners in sex 'uncomfortably' penetrating a dominant partner who didn't want to be fully penetrated.</ul>"
			+"<ul>Dominant characters (including the player) will now choose to get 'uncomfortably' penetrated if they have the size queen or masochist fetish.</ul>"
			+"<ul>'Uncomfortable' penetrative length descriptions are now always described from the dominant's point of view (showing that they are the ones deciding whether to get 'uncomfortably' penetrated or not).</ul>"
			+"<ul>Improved initial penetrative length descriptions, and added a small ongoing description for if an ongoing sex action is uncomfortable (similar to the ongoing ones describing whether an orifice is stretching or too loose).</ul>"
			+"<ul>Arousal modifiers from being uncomfortably penetrated have been altered from +4/-6 to +2.5/-10. (The positive value is for masochists and size queens. The negative value is for everyone else.)</ul>"
			+"<ul>Made some general improvements to the formatting and content of penetrative length descriptions.</ul>"
			+"<ul>Increased base vagina depth from 8% of character's height to 10%.</ul>"
			+"<ul>NPCs will no longer uncomfortably penetrate themselves if they are in the rough pace (unless they are also masochists or size queens).</ul>"
			+"<ul><b>Depth is now a modifiable stat</b> like elasticity and plasticity. Values go from 0 to 7, and apply a percentage modifier to the depth of the orifice. The average value of 2 represents 100% of the base depth, while the minimum of 0 applies a 50% modifier, and the maximum of 7 applies a 400% modifier.</ul>"
			+"<ul>The 'Extra deep' orifice modifier has been removed, as it was made redundant from the newly-added depth stat. When loading into this version, any character with 'extra deep' orifice modifiers will instead be given +2 to their depth value.</ul>"
			+"<ul>Instead of having hidden modifiers in the code, taurs now simply spawn with +2 depth to their base orifice depth values. Slimes and other non-flesh characters (such as elementals) have their orifice depths locked to 7. Their 'too short' sex modifier threshold acts as though their orifice is of an average depth (so while they have very deep orifices, they don't require gigantic penises to be satisfied).</ul>"
			+"<ul>Updated body stats page and body description with new depth values.</ul>"
			+"<ul>Resisting characters will no longer act as through they are eagerly taking big penetrations.</ul>"
			+"<ul>Penetrations are now considered to be 'too short' if they are 33% or less the length of the orifice's depth (instead of 50%).</ul>"
	
			+"<li>Items:</li>"
			+"<ul>Added: 'Horseshoe necklace' (neck/wrist slot, sold by Nyan).</ul>"
			+"<ul>Added: 'Thick-rimmed glasses' (eyes slot, sold by Nyan).</ul>"
			+"<ul>Added: 'Ball stud earrings' (ear piercing slot, sold by Kate).</ul>"
			+"<ul>Added: 'Ribbed dildo' (anus/vagina/mouth slot, sold by Finch).</ul>"
			+"<ul>Added: 'Rejuvenation potion' (common item, sold by Ralph). Consuming it instantly recovers all of your stretched orifices to their natural capacity, as well as filling your cum & milk up to their maximum storage values.</ul>"
			+"<ul>Renamed 'slut pill' to 'sterility pill'.</ul>"
			+"<ul>The clothing items 'v-necked commando sweater', 'combat shirt', and 'commando sweater' are no longer counted as being part of the Enforcer set. Also, their rarities are now all set to 'Uncommon'.</ul>"
			+"<ul>Added 'bronze' as a metallic colour for clothing & weapon dying.</ul>"
			+"<ul>Increased value of the three crystal weapons.</ul>"
			+"<ul>Slightly updated the available shades of red for clothing/weapon dying. Also added 'royal purple'.</ul>"
			+"<ul>Slightly improved necklace and  'Mystery kink' icons.</ul>"
			+"<ul>The debug-only item 'Innoxia's Gift' now works on the player character.</ul>"
			+"<ul>Slightly improved earring, cattle tag, cattle nose ring, gemstone barbell, and ringed barbell icons.</ul>"
			+"<ul>Increased value of all earrings.</ul>"
			+"<ul>All of the 'sun' and 'snowflake' jewellery is now sold by Kate, not Nyan.</ul>"
			+"<ul>Tongue barbell is now called 'basic barbell' and can now also be equipped into the ear piercing slot (representing an 'industrial' piercing).</ul>"
			+"<ul>Nipple barbells are now called 'basic barbells' and can now also be equipped into the ear piercing slot (representing 'industrial' piercings).</ul>"
			+"<ul>Gemstone and ringed barbells can now be fit into either the navel or vaginal piercing slots.</ul>"
	
			+"<li>Sex toys:</li>"
			+"<ul>Clothing which is able to be inserted into orifices no longer uses the old 'DILDO' tags to define their length. Instead, clothing xml files now have a couple of extra sections which are used to define length, girth, and modifiers of penetrative sex toys.</ul>"
			+"<ul>For an example of how this works (if you are wanting to make a modded sex toy item), see either the 'mods/innoxia/item/clothing/template/socks.xml' or the 'res/clothing/innoxia/buttPLugs/butt_plug.xml' file.</ul>"
			+"<ul>Sex toys now stretch the orifice they are inserted into, and if penetration length content is turned on, toys which are too long cause discomfort to the wearer.</ul>"
			+"<ul>Sex toys inserted into the vagina now tear any hymen which is present.</ul>"
			+"<ul>Non-slave NPCs will automatically remove clothing which is uncomfortable for them to be wearing.</ul>"
	
			+"<li>Sex:</li>"
			+"<ul>Fixed issue where random attackers would consider you to be an equal partner in sex where you 'offer body', meaning that they would never end sex if you didn't orgasm at least once.</ul>"
			+"<ul>Fixed issue with character targeting during creampie action sometimes not working correctly.</ul>"
			+"<ul>Fixed bug where a character with a large amount of lust would sometimes have it lowered at the start of sex.</ul>"
			+"<ul>Ongoing penetration tooltips in sex now display the capacity of the orifice, as well as the diameter of the penetration type being inserted (i.e. penis or tail diameter).</ul>"
			+"<ul>The 'Knotting' orgasm action is now a separate action from 'Creampie', so you can choose whether or not to insert your knot when orgasming.</ul>"
			+"<ul>Using the 'Knotting' orgasm action now effectively doubles the diameter of the penis in that one turn in which the action is taking place (so the knot can stretch out the orifice).</ul>"
			+"<ul>If 'Penetrative size-difference' is turned on, then knotting cannot be performed if the penis is too long to fit inside the orifice. Also, if long enough, the 'Knotting' action will push the penis into the 'uncomfortable' depth for that one turn.</ul>"
			+"<ul>Improved NPC's penis reveal reactions. They will now comment on the size of a penis as it relates to the depth of their orifices, instead of just by the penis length. (So imps will be more impressed by a 'large' cock than a centaur.)</ul>"
			+"<ul>Fixed issue where seemingly-happy submissive NPCs could enter resisting pace after orgasming, even if they had very high lust before the moment of orgasm. Now a character's lust will drop to a minimum of 15 upon orgasm.</ul>"
			+"<ul>Updated the 'glory hole' sex position: taurs can no longer put their cocks through the glory hole; the character servicing the holes is now considered to be the submissive; improved starting descriptions if taurs are present; split the 'getting fucked' slot into anal/vaginal fucked slots (to give the sub control over which hole they want penetrated).</ul>"
			+"<ul>Added paizuri as a possible action for the sub in glory hole sex.</ul>"
			+"<ul>A character's asshole is now correctly revealed if they are presenting a cloaca to a glory hole.</ul>"
			+"<ul>Fixed bug where the 'fondle balls' action would never be available.</ul>"
			+"<ul>Added some minor variations to the penis masturbation sex action descriptions for if the cock is precumming.</ul>"
	
			+"<li>Other:</li>"
			+"<ul>Increased default length of most non-human tongues.</ul>"
			+"<ul>Added Encyclopedia reset button to debug menu (in 'stats' tab). Also moved 'Unlock Encyclopedia' button to the 'Stats' tab.</ul>"
			+"<ul>Added 'wide', 'flat', and 'strong' as available tongue modifiers. These new modifiers will be added to applicable characters (including the player) when loading into this version (such as 'flat' and 'wide' being added to characters who have dog-morph tongues).</ul>"
			+"<ul>The 'interior' colour of nipples which cannot be penetrated is no longer displayed when referring to nipple colour.</ul>"
			+"<ul>Split up the 'Content Options' screen into categories, to help with readability of options.</ul>"
			+"<ul>Offspring race generation no longer factors in your 'furry stage' content preference (as it was messing with the generation and producing results that made no sense). Human + anthro offspring now have a chance to 'degrade' the furry race stage of the offspring (e.g. A greater dog-moprh + human has a chance to birth 'lesser' or 'partial' dog-morphs).</ul>"
			+"<ul>Added a small colour icon next to each body element in the character view tooltip.</ul>"
			+"<ul>Urethral enchantment modifiers are no longer displayed in the enchantement options if you have urethral content turned off.</ul>"
			+"<ul>Added more orifice parsing commands (depth, capacity, elasticity, plasticity) to orifices which lacked them.</ul>"
			+"<ul>Added clitoris transformations and orifice depths to the self-TF menu. Also slightly improved formatting.</ul>"
			+"<ul>Slave permission settings are no longer saved unless the character is actually a slave, which cuts down on save file size.</ul>"
			+"<ul>Made it slightly more likely for randomly-generated fox-morphs and unicorns to have an arcane background.</ul>"
			+"<ul>Added more metallic colours to fur/hair/feathers coverings, added 'light green' as a demon/unnatural skin colour, added 'pale lilac' and 'indigo' as covering colours. Slime, horn, antler, and skin coverings now get all the same dye options. Fixed 'lilac' not actually being a lilac colour.</ul>"
			+"<ul>Items in the debug spawn menus are now sorted by rarity.</ul>"
			+"<ul>Added 'wash' action to the public toilets area in 'The Watering Hole'.</ul>"
			+"<ul>Added a day of the week indication beneath the current date display.</ul>"
			+"<ul>Reduced corruption gain from harpy subspecies status effect, as well as from the harpy flock member background perks.</ul>"
			+"<ul>Defined names for demonic centaurs as 'demotaur', with 'incutaur/succutaur' being masculine/feminine versions.</ul>"
			+"<ul>'Facial hair' and 'Pubic hair' are now on by default (only takes effect when resetting proprty values or starting a game when there is no properties.xml file present).</ul>"
			+"<ul>'Virginity' fetish has been renamed to 'Vaginal virginity' fetish, to better reflect the fact that it is solely fetishising the character's vaginal virginity.</ul>"
			+"<ul>Urethral virginities are no longer restored upon removing/regrowing a character's penis or vagina.</ul>"
			+"<ul>Added 'demonic-horse' as an alternate leg type for demons. It is similar to the 'demonic-hoofed' leg type, except that they are covered in horse-like hair. Demonic taurs now have this leg type by default.</ul>"
			+"<ul>Demonic horse tails are now separately coloured from the character's hair by using the 'demon horse hair' colour.</ul>"
			+"<ul>Added weighting to covering pattern spawns (so there will be more consistently-coloured fur for horses, foxes, and a few other races), and also removed 'blonde', 'ginger', and 'jet black' from natural fur colours.</ul>"
	
			+"<li>Bugs:</li>"
			+"<ul>Parsing, formatting, and typo fixes.</ul>"
			+"<ul>Fixed the 'Throat control' sex action not working.</ul>"
			+"<ul>Fixed issue with crowds still being shown and described as being present on Dominion street tiles during an arcane storm.</ul>"
			+"<ul>Fixed bug where trying to import a character created before v0.3.6.4 would throw an error and not work.</ul>"
			+"<ul>Added catch for a potential error thrown by weapons being imported from old saves or character exports.</ul>"
			+"<ul>Fixed bug where non-elemental spell books were erroneously describing one of the effects as being the unlocking of the associated elemental encyclopedia entry.</ul>"
			+"<ul>Updated 'Double Size Bed' and 'Small Steel Bed' room upgrade descriptions to bring them more in line with the room's main description.</ul>"
			+"<ul>Fixed issue with three horns per row being described as a 'pair' instead of a 'trio'.</ul>"
			+"<ul>Fixed bug where a slime's crotch nipples may sometimes not have been the correct colour.</ul>"
			+"<ul>Fixed bug where items in an NPCs inventory would be greyed out even if you were able to interact with them.</ul>"
			+"<ul>Disabling lactation and/or incest now correctly hides the related fetishes from your phone's fetish menu screen, as well as from fetish potion enchanting.</ul>"
			+"<ul>Fixed some minor parsing issues with NPC dialogue during sex.</ul>"
			+"<ul>Fixed issue with a couple of 'resist' sex actions giving you corruption.</ul>"
			+"<ul>Fixed bug where entering the last stage of pregnancy would reference your 'crotch boobs' even if you didn't have any.</ul>"
			+"<ul>Fixed instances of descriptions of nipple sucking making mention of 'eating out' pussy-nipples, even if they were not able to be penetrated.</ul>"
			+"<ul>Fixed offspring of a human + anthro sometimes being a full human (which was contradicting the lore).</ul>"
			+"<ul>Fixed (harmless) background errors being thrown when there was an ongoing penetration with a non-internal orifice (such as breasts or thighs).</ul>"
			+"<ul>NPCs will no longer react as though they are seeing your exposed sexual areas for the first time if your areas were already exposed before starting sex.</ul>"
			+"<ul>Fixed some bugs with the gloryhole scenes accidentally revealing your partners' bodies.</ul>"
			+"<ul>The brief body overview tooltip now displays known sexual areas for characters whose race is otherwise concealed (such as the characters in the gloryholes).</ul>"
			+"<ul>Fixed bug where using the 'Quick sex' action would add entries for sex stat tracking three times instead of just once.</ul>"
			+"<ul>Fixed another bug when using 'Quick sex', where orgasming characters wouldn't have their cum storage depleted from their orgasms.</ul>"
			+"<ul>Fixed some formatting issues with player's anal and vaginal virginity loss scenes.</ul>"
			+"<ul>Removed metallic silver as a natural fur colour.</ul>"
			+"<ul>Fixed bug where using the milking machines in the milking room would not correctly drain your cum/milk.</ul>"
			+"<ul>Fixed bug where reindeer overseers would not have their inventory correctly updated each day.</ul>"
			+"<ul>Dialogue in Scarlett's shop now correctly passes time.</ul>"
			+"<ul>Selling a slave now automatically returns all of their unique clothing/weapons/items to you.</ul>"
			+"<ul>Fixed some issues with the flow of dialogue between slave management screens.</ul>"
			+"<ul>You can no longer see the date in the time tooltip before having looked at the calendar in your room.</ul>"
			+"<ul>Fixed tattoo tooltip's information being cut off at the bottom.</ul>"
			+"<ul>Fixed bug where characters could spawn in with covering patterns of the same colour. (e.g. 'Brown, brown-striped fur' or 'Blue-and-blue heterochromatic irises'.)</ul>"
			+"<ul>Fixed issue where orgasming in sex would apply any special orgasm effects multiple times in the background (potentially causing issues for Lyssieth's and Lilaya's sex scenes).</ul>"
		+"</list>"
		
		+"<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.6.8</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added FaceTypeTag enum for improved handling of face features. The 'Bite' special attack is now available to any face type which has a muzzle, fangs, beak, or shark teeth. (PR#1286 by Stadler)</ul>"
			+"<ul>Added graceful error handling for if an invalid ItemTag is detected when loading Clothing/Weapons from xml files. (PR#1278 by Stadler)</ul>"
			+"<ul>Fixed bug: Restocking of items sold by Vicky (Arcane Arts) produces wrong prices after buying all items of a single type. (PR#1276)</ul>"
			+"<ul>Fixed the issue where the NPC in the prologue knows about the player's penis when this shouldn't be the case. (PR#1274 by AxeXP)</ul>"
			+"<ul>Improved interaction between wing-arms (as seen on harpies and bat-morphs) and clothing. (PR#1249 by Stadler)</ul>"
			+"<ul>Fixed the phone menu sex stats display for blowjobs performed and received being the wrong way around. (PR#1270 by AceXP)</ul>"
			+"<ul>Fixed bug where CoverableArea.MOUTH could never be discovered, blocking knowledge of NPCs' oral skills. (PR#1260 by AceXP)</ul>"
			+"<ul>Altered race tooltip to better reflect knowledge from 'observant', and improved some of the related text in the full appearance description page. (PR#1236 by NoStepOnSneks)</ul>"
			+"<ul>Fixed bug where you could become an angel by self-transforming into the appearance of one while being a slime, and then solidifying into flesh via a TF potion. (PR#1292 by NoStepOnSneks)</ul>"
			
			+"<li>Engine/Code:</li>"
			+"<ul>Tidied up the default enslavement dialogue node and moved related text out into external txt files (into the txt/characters and txt/characters/offspring files).</ul>"
			+"<ul>Moved fluid modifier ingestion effects out of GameCharacter's ingestFluid method and into the corresponding FluidModifier Enum values.</ul>"
			+"<ul>Added methods in Game class for detection of metric sizes, weights, and fluids, so that these values can easily be found out when using the parsing engine.</ul>"
			+"<ul>Added hooks to item, weapon, and clothing types in the parser, under ITEM_id, WEAPON_id, and CLOTHING_id, where 'id' is the id of the item you want to reference.</ul>"
			+"<ul>Added functionality for NPCs to use items during sex.</ul>"
			
			+"<li>Gameplay:</li>"
			+"<ul>Added 'construction worker' as a background that can be chosen during character creation. It offers some physical attribute buffs, as well as halving all room upgrade costs.</ul>"
			+"<ul>Slightly altered friendly occupant job search mechanics. You can now prevent friendly occupants who don't have their own apartment from getting a job, and suggest to them that they quit their current job if they've already found one.</ul>"
			+"<ul>'Orgasmic level drain' is now an equippable trait.</ul>"
			+"<ul>The 'Hypno watch' which you get from Arthur can now be enchanted to add or remove lisp, stutter, and slovenely speech. (Only works on random NPCs and yourself.)</ul>"
			
			+"<li>Penetrative size-difference:</li>"
			+"<ul>Changed orifice capacity (and therefore related stretch mechanics) to be based on penetrative objects' diameter, not length.</ul>"
			+"<ul>Added calculation for penis diameter, based on a character's penis length, girth, and modifiers (flared gives +5% diameter, tapered gives -5%).</ul>"
			+"<ul>Added calculation for tail length, based on a character's height and tail type.</ul>"
			+"<ul>Added calculation for tail diameter, based on a character's tail length and girth.</ul>"
			+"<ul>Tails now stretch orifices in the same way that penises do.</ul>"
			+"<ul>All of the following changes can be toggled off via the content option 'Penetration Limitations' (which is on by default):</ul>"
			+"<ul>Internal orifices now have depth values, which are based on the character's height, if they are a taur, and if they have a non-flesh body material. Depth values be seen in the character description page (and in your phone menu's 'body stats' page).</ul>"
			+"<ul>Depth values have 'comfortable' and 'uncomfortable' components. Taking an insertion past the 'comfortable' limit will cause severe arousal losses per turn for any non-masochistic character being penetrated.</ul>"
			+"<ul>Characters in the 'rough' pace in sex will penetrate past the 'comfortable' depth limit, but all other paces will stop before passing into the 'uncomfortable' depth.</ul>"
			+"<ul>Added a 'size-queen' fetish, which makes all depth values for that character's orifices considered to be 'comfortable'.</ul>"
			+"<ul>Added 'extra-deep' orifice TF modifier, which doubles the depth of the orifice. This can be applied via the self-TF menu or by crafting TF potions.</ul>"
			
			+"<li>Sex:</li>"
			+"<ul>NPCs will now use pills during sex, depending on their fetishes, ongoing actions, mouth access, and whether or not they actually have any pills in their inventory.</ul>"
			+"<ul>When performing oral on any orifice, the performing character will now swallow fluids previously deposited in that orifice, at a rate of 25ml per turn.</ul>"
			+"<ul>NPCs who have the 'orgasmic level drain' perk will no longer choose to drain their slaves or companions.</ul>"
			+"<ul>Elementals are now immune to the 'orgasmic level drain' effect.</ul>"
			+"<ul>Added intercrural action availability to 'cowgirl' and 'reverse cowgirl' positions.</ul>"
			+"<ul>Added 'throat control' sex action for if a character is performing a blowjob and has the 'internal muscles' orifice modifier for their throat.</ul>"
			+"<ul>Characters now get +0.5 arousal/turn from an orifice they're penetrating being very tight, instead of -1/turn.</ul>"
			+"<ul>Masochists now gain arousal per turn, instead of losing it, while their orifices are being stretched or penetrated too deeply.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Added quicksave and quickload buttons in the bottom-right of the game window, which grey-out when unavailable.</ul>"
			+"<ul>Humans are no longer listed in the 'Races Present' tooltip for Enforcers present in tiles.</ul>"
			+"<ul>In the inventory screen, added a colour indication to the 'Equip' action whenever enslavement-enchanted clothing to be equipped on an NPC will either succeed or fail to enslave them.</ul>"
			+"<ul>You can no longer enslave NPCs before you have obtained a slaver license.</ul>"
			+"<ul>Slightly tidied up the action titles for using & equipping items/clothing/weapons on NPCs in the inventory dialogue.</ul>"
			+"<ul>Added fluid modifiers to the self-transformation menu for milk, crotch-milk, girlcum, and cum.</ul>"
			+"<ul>Doubled the amount of essences which Sean has (to make sure that he doesn't run out of them during a fight).</ul>"
			+"<ul>The 'scratch' and 'squirrel scratch' special attacks now have a 1-turn cooldown.</ul>"
			+"<ul>You can no longer force the harpy matriarchs to drink fetish-altering potions (as that has the potential to alter their personalities in such a way so as to make future interactions with them completely nonsensical).</ul>"
			+"<ul>Renamed 'Vixen's Virility' to 'Breeder pill', and 'Promiscuity pill' to 'Slut pill'. Improved effects tooltip for both of these items.</ul>"
			+"<ul>Random NPCs who have a positive or negative desire towards either the pregnancy or impregnation fetish will now spawn in with slut pills or breeder pill in their inventory.</ul>"
			+"<ul>Randomly-generated non-slave NPCs will now refresh their inventories every day.</ul>"
			+"<ul>'Slut pills' are now far more expensive to buy from shopkeepers than 'breeder pills', due to a tax on their sale.</ul>"
			+"<ul>Added an action in the debug menu to unlock all encyclopedia entries (under the 'Misc.' tab).</ul>"
			+"<ul>Added two toggles in content options for allowing/disallowing furry or scaly characters to spawn in with hair. By default, furry hair is on, but scaly hair is off.</ul>"
			+"<ul>Alligator 'hair' has been changed from 'head-scales' to 'hair', due to their default state of lacking hair now being correctly handled.</ul>"
			+"<ul>NPCs who you invite to come and live with you now have their occupation reset to 'unemployed' when they move in, instead of remaining as a 'mugger' or 'prostitute' until finding a new job. (This is also retroactively applied to friendly occupants with no job when loading in.)</ul>"
			+"<ul>Added some missing colours to slimes (including metallic colours as unnatural slime dye choices).</ul>"
			+"<ul>Added basic tooltips to the buttons in the bottom-left of the game window (to make it a little more obvious that there are hotkey shortcuts for them).</ul>"
			+"<ul>Improved formatting of the prices overlaid on item icons when trading in shops.</ul>"
			+"<ul>The 'milk maid' clothing set bonus now has a far more powerful alternative version for if your background occupation is that of a maid. (In the same way that the basic 'maid' set bonus is buffed.)</ul>"
			+"<ul>Slightly improved formatting of body description screen.</ul>"
			+"<ul>Removed '+10 aura' effect from the crossdresser fetish.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Parsing and typo fixes.</ul>"
			+"<ul>Fixed issue of the debug menu's self-TF function treating you as though you're a slime even if you weren't one.</ul>"
			+"<ul>Fixed bug where a significant amount of unique NPCs would have their core corruption stat reset every time you loaded the game. (This appeared to be most noticeable with Brax.)</ul>"
			+"<ul>Fixed issue with defining itemTags in clothing. If an itemTags element without a defined slot was present in the xml file, then itemTags with defined slots would accidentally override its tags (instead of being added on top of what was meant to be slot-independent tags).</ul>"
			+"<ul>Defeating the rats after attempting to beat Murk up now correctly sets that area of the Rat Warrens as being cleared, which resolves the issue of Murk being incorrectly treated as still being present in the Milking Room.</ul>"
			+"<ul>Fixed issue where enslaving the first of the defeated gang members in the post-combat scenes in the Rat Warrens would immediately return you to the tile's default dialogue, while leaving the other 5 rats on the tile.</ul>"
			+"<ul>Fixed bug where there would be no dialogue displayed in the left and right corridors of the Rat Warrens after clearing them out.</ul>"
			+"<ul>Fixed bug where you could choose the 'Leave' action in the Rat Warrens' Milking Room even after clearing that area of rats, which would cause Murk to reappear on the Milking Storage tile.</ul>"
			+"<ul>You can now enslave the imps found in gangs in the tunnels in Submission.</ul>"
			+"<ul>Fixed issue with slovenly speech not parsing correctly in some instances.</ul>"
			+"<ul>Fixed issue with feathers and scales being incorrectly referred to in their singular form in standard parsing.</ul>"
			+"<ul>Companions are no longer able to give birth while in your party.</ul>"
			+"<ul>The occupancy ledger now keeps track of when a slave has given birth.</ul>"
			+"<ul>Fixed issue where upon loading a saved game, all crotch-milk modifiers, including flavour, would be reset to the same modifiers of the character's main breast milk.</ul>"
			+"<ul>After defeating them and driving them off, the demon leaders in the three Submission Fortresses will now correctly re-equip their clothing in time for your next encounter with them.</ul>"
			+"<ul>Fixed Rose having the 'prude' personality trait. She now has the 'cowardly' and 'selfish' traits instead.</ul>"
			+"<ul>Fixed a reference to the world map being 150 square kilometers, instead of correctly being described as 150km*150km.</ul>"
			+"<ul>Fixed bug where Cultists would swallow promiscuity pills which you offered them, even though the text implied that they knocked it out of your hand.</ul>"
			+"<ul>Fixed minor parsing issue with item tooltips having a greater height than was needed to fit the displayed information.</ul>"
			+"<ul>Fixed bug with light theme tooltips not displaying correctly, and also fixed some other minor bugs related to light theme's formatting and appearance.</ul>"
			+"<ul>Fixed minor issue with random German-shepherd-morph NPCs not having correct determiners appended in front of their name (such as 'a' or 'the').</ul>"
			+"<ul>Fixed issue where demons in dark alleyway tiles would incorrectly reference pregnancies as having imps as the resulting offspring.</ul>"
			+"<ul>The 'Double creampie' action now correctly applies penetrative effects to the second character (i.e. taking their virginity, starting to stretch them them out, etc.).</ul>"
			+"<ul>Fixed issues with the availability of the 'slap ass' sex action.</ul>"
			+"<ul>Bike shorts no longer block intercrural sex actions.</ul>"
			+"<ul>Fixed bug where if you had the 'Servant of fire' spell upgrade (or any of the other elemental school equivalents), your elemental would refuse to have dominant sex with you, instead of refusing to have submissive sex.</ul>"
			+"<ul>Fixed issue with slimes' subspecies detection not being based on the slime colour, but the character's underlying (hidden) subspecies colour. (e.g. A blue harpy-slime was being incorrectly identified as a phoenix-harpy-slime if the characters' 'feather colour' was glowing red, even though these feathers cannot be seen.)</ul>"
			+"<ul>Menu buttons in the bottom-left of the game window now correctly refresh when loading a game.</ul>"
			+"<ul>Fixed bug where the hypno watch could disappear from your inventory upon loading your game.</ul>"
			+"<ul>Fixed bug where random characters who used you in the stocks would not disappear after the scene had ended.</ul>"
			+"<ul>Fixed issue with incorrect Enforcer races being displayed as being present in Submission's safe path tiles.</ul>"
			+"<ul>Fixed potential issue with Brax not being found at the Enforcer HQ's reception desk after completing his part of the main quest.</ul>"
			+"<ul>Fixed issue with the debug menu's 'race resets' not working correctly for 'half-demon' and 'demon' options. Resetting your race to something other than a demon will now also correctly remove your underlying demon identification.</ul>"
			+"<ul>Fixed characters being able to use 'muscle control' sex actions while in the 'resisting' pace.</ul>"
			+"<ul>Urethra and nipple internal orifice modifiers (ribbed, muscled, tentacled), are now only applied if the orifice is able to be penetrated.</ul>"
			+"<ul>Fixed incorrect name parsing for all orifice modifier addition/removal descriptions, and incorrect references to a character's breasts when modifying crotch-boobs.</ul>"
			+"<ul>If you have the related content options turned off, then anal and foot fetishes will no longer show up in your fetish menu, nor in the fetish enchantment options.</ul>"
			+"<ul>Fixed several instances where ingesting fluids could throw a background error and potentially cause the game to become unresponsive.</ul>"
		+"</list>"

		+ "<br/>"
		
		+ "<list>"
			+ "<h6>v0.3.6.4</h6>"
			+"<li>Engine/Code:</li>"
			+"<ul>Fixed issue with incorrect line endings at the top of most files.</ul>"
			+"<ul>Moved remaining Slaver Alley dialogue into the external .txt file.</ul>"
			+"<ul>Tidied up virginity loss code.</ul>"
			+"<ul>The 'companion' parser target has been shortened to 'com', and should now work correctly in conditional statements.</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Added content for the 'Complain' action in Slaver Alley's public stocks tile, which includes a new unique Enforcer NPC. (There are some additional dialogue variations for if Brax is in your party.)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>The 'Tail swipe' combat move is now available to anyone who has a prehensile tail which has a girth of at least 'thick'.</ul>"
			+"<ul>Alligator-morph tails are now considered to be suitable for penetrative actions.</ul>"
			+"<ul>Added ability to have your companion join in on sex with slaves locked up in the stocks in Slaver Alley.</ul>"
			+"<ul>Added 'sandy' as a covering colour.</ul>"
			+"<ul>Slightly improved slovenly speech parsing.</ul>"
			+"<ul>Gave Helena a new surname and some clothes to wear.</ul>"
			+"<ul>Level drain option now defaults to 'off' in sex scenes which include your companion, slaves, or friendly occupants.</ul>"
			+"<ul>Scarlett and Brax can now have their levels drained via the use of the 'Orgasmic level drain' once they've been enslaved.</ul>"
			+"<ul>Added kissing interaction availability to 'mating press' + 'lying down' positions, and anilingus availability to 'face between legs' + 'lying down' positions.</ul>"
			+"<ul>Added 'phoenix-harpy' subspecies, which is just a harpy with glowing red, orange, or yellow feathers. They can only be found naturally spawning in the Harpy Nests as an extremely rare chance.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Numerous parsing fixes (most of them in the new Rat Warrens dialogue).</ul>"
			+"<ul>Feminising Murk will now correctly give her a vagina. (Fix is also retroactively applied if you've already feminised him.)</ul>"
			+"<ul>Fixed tail girth description in selfie/character viewer not updating to describe the character's actual tail girth.</ul>"
			+"<ul>Disabled unique position switching actions in stocks sex when there were more than two participants, as it was causing some issues. (You can still change position as the dom using the more in-depth position selection action.)</ul>"
			+"<ul>Quick sex now correctly takes into account each participant's banned areas. (i.e. In slaver alley stocks sex, the slave's orifices which are banned will be excluded from quick sex.)</ul>"
			+"<ul>Fixed more issues of quick sex ignoring the availability of sex areas.</ul>"
			+"<ul>Quick sex will no longer apply level drain to targets who should be immune (i.e. unique characters).</ul>"
			+"<ul>Characters with the deflowering fetish now correctly gain experience from taking virginities in quick sex.</ul>"
			+"<ul>Removed line in alleyway demon encounter which suggested that the demon grows a cock when you offer yourself to her.</ul>"
			+"<ul>Fixed bug where companions would go to work while being held as a captive (bug was present in the Rat Warrens loss scenes).</ul>"
			+"<ul>Parsing and dialogue flow fixes in scene where you get sent to the cells after losing in the Enforcer Warehouse.</ul>"
			+"<ul>Fixed bug in the post-loss Enforcer Warehouse cells scene, where you'd remain in the Enforcer HQ's cells after you were supposed to have been escorted outside by Claire.</ul>"
			+"<ul>Fixed inactive 'Continue' button being shown after Claire has warned you about the SWORD Enforcers in the Warehouse.</ul>"
			+"<ul>Your clothing now correctly gets unequipped when locked into the stocks (in the Enforcer Warehouse loss scene).</ul>"
			+"<ul>Fixed post-sex stretching information referring to every stretched orifice as the character's 'asshole', instead of the correct orifice.</ul>"
			+"<ul>Fixed issue with Helena returning to her nest when she should have been in Scarlett's Shop in Slaver Alley.</ul>"
			+"<ul>Fixed some minor issues with dialogue flow when talking to Candi and when getting the Lipsticks from Ralph (as part of the 'Buying Brax' quest).</ul>"
			+"<ul>Fixed awkward phrasing of the exhibitionist fetish's short description.</ul>"
			+"<ul>Quick sex should now display correct behaviour for if a sex scene is meant to end in a creampie or not.</ul>"
			+"<ul>Fixed issue with quick sex where it would sometimes throw a background error and not work.</ul>"
			+"<ul>Fixed bug in quick sex where characters with impregnation fetish would sometimes pull out of a partner willing to be impregnated.</ul>"
			+"<ul>Fixed 'ready for birthing' status effect having a nonsensical description for taur characters.</ul>"
			+"<ul>Fixed bug where you could request Claire's help in dealing with Vengar even after you'd completed his quest.</ul>"
			+"<ul>Fixed incorrect parsing in combat when sadistic characters took lust damage from dealing damage to their target.</ul>"
			+"<ul>Fixed bug where the breeder collar would spawn with a duplicate sealing enchantment.</ul>"
			+"<ul>Fixed bug where Murk's dialogue in the Rat Warrens would still be working even after he'd meant to have been enslaved.</ul>"
			+"<ul>Fixed Bree gaining a random colour for her vagina when undergoing her initial feminisation from Brax.</ul>"
			+"<ul>Fixed issue where opening the positioning menu on the 'sitting' position would place both doms and subs into sitting slots.</ul>"
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
