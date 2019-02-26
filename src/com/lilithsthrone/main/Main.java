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
 * @version 0.3
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sexEngine;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.3.0.8";
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
			+ "<i>0.3.0.7:</i> This was meant to be a small hotfix, released earlier this week, but I got a little carried away,"
				+ " and ended up making improvements to the sex AI, adding an outfit system (after encountering muggers with ridiculous clothing several times in a row), as well as doing a lot of other minor fixes and improvements."
		+ "</p>"
				
		+ "<p>"
			+ "<i>0.3.0.7:</i> There were a few major issues in v0.3.0.6 which I wanted to fix, namely NPC pregnancies never finishing, identifying clothing using essences breaking the inventory menu, and the overview tab in the inventory not working."
		+ "</p>"
			
		+ "<p>"
			+ "The full, public release of v0.3.1 will be out roughly in next week's Friday-Sunday time period."
		+ "</p>"
			
		+ "<br/>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.0.5</h6>"
			+"<li>Gameplay:</li>"
			+"<ul><b>Reset:</b> Player body to human if you are a demon when loading into this version. I did this so you'd have the chance to see the new demon TF scenes.</ul>"
			+"<ul>Expanded the demon TF sex scene to add options at each stage of orgasm, with different body modification outcomes depending on what you do.</ul>"
			+"<ul>Added in all placeholder dialogue for Lyssieth and Meraxis.</ul>"
			+"<ul>Lilaya now gets very angry if you get her pregnant, and locks you out of her lab until she's given birth. You can give Rose 'Mother's milk' in her talking scenes (outside her room) in order to speed up Lilaya's pregnancy.</ul>"
			+"<ul>Lilaya now has reactions for if you're wearing a condom and for if your condom breaks while fucking her vagina.</ul>"
			+"<ul>Added demon transformation reaction scenes from Lilaya, and added follow-up scenes involving more transformations and sex.</ul>"
			+"<ul>Added teleportation action between Lyssieth's office and Lilaya's laboratory.</ul>"
			+"<ul>Added taur-compatible sex scenes to almost all of the 'generic' NPCs (such as the ones you meet in Dominion's alleyways). There are only three taur sex positions at the moment (initial standing, oral, and doggy-style/mounting positions). More will be added later on.</ul>"

			+"<li>Body transformations:</li>"
			+"<ul>Added full support for udders/crotch-boobs. This was primarily meant for only taur bodies, but there is an option under 'Options -> Furry Preferences' to have greater anthro-morphs spawning in with them as well.</ul>"
			+"<ul>Crotch-boobs/udders have separate transformations to your normal breasts. Transformation options have been added to add racial TF food item enchanting, as well as to the self-TF menu that demons and slimes have access to.</ul>"
			+"<ul>Added crotch-boob/udder milking support in the milking rooms. (The moo-milker doesn't work on udders yet.)</ul>"
			+"<ul>Added sex actions related to crotch-boobs/udders: Groping, kissing, breastfeeding, paizuri/naizuri, and nipple penetration.</ul>"
			+"<ul>Added 'cloaca' transformation option to racial potion crafting (set 'core' as primary modifier, and 'internal' as secondary, and then add/remove cloaca by using potency), as well as to the self-transformation menu (under 'core').</ul>"
			+"<ul>Updated sex actions to take into account cloaca - if someone does have one, then anal actions are not available from accessing their ass, but instead accessing their crotch. (i.e. Back-to-wall will unlock anal options if the target has a cloaca.)</ul>"
			+"<ul>Added option in 'Content Options' menu to disable cloacas on bipedal races. (Harpies and alligator-morphs spawn in with cloacas by default.)</ul>"
			+"<ul>Added engine support for the non-bipedal lower bodies: 'taur', 'winged-taur', 'tail' (mermaids), 'long tail' (lamia), 'arachnid' (arachnae), and 'cephalopod' (octopus-morphs).</ul>"
			+"<ul>Added transformation enchanting to food for leg configurations. (Only 'bipedal' and 'taur' races are in the game at the moment, but the other lower body types will be added later on.)</ul>"
			+"<ul>Added 'pegasus-morph', 'unicorn-morph', 'alicorn-morph', 'centaur', 'pegataur', 'unitaur', and 'alitaur' subspecies. Other unique taur subspecies will be added later on, but for now only their names & status effect icons change to show that they are 'taur'.</ul>"
			+"<ul>Added 'demonic horse' tail transformation.</ul>"
			+"<ul>Added 'pegataur' wing transformation for horse-morphs.</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Fixed incorrect dialogue been shown in the female imp fortress when scaring the leader off after sex. (Nnxx)</ul>"
			+"<ul>Expanded and improved detection of character relationships. (PR#929 by orvail)</ul>"
			+"<ul>Added a few more utility options to the debug menu. (PR#1007 by Nnxx)</ul>"
			+"<ul>Added a lot more names and name variety. (PR#989 by Nnxx)</ul>"
			+"<ul>Fixed anus/nipple areas always being revealed in body-part overview tooltip. (PR#1024 by 0d721077)</ul>"
			+"<ul>Fixed perk point bug, which was causing the issues where perk points could sometimes be negative, and sometimes be massively over-inflated. Perk points will be reset to their correct value when you load into this version. (PR#998 by BDPMP)</ul>"
			+"<ul>Added: 'Raincoat' (Over-torso slot, sold by Nyan, androgynous.) (Created by Bloom and wasp609.)</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: 'Anal-only pastie' (Vagina slot, sold by Finch, androgynous.)</ul>"
			+"<ul>Added: 'Ornate chastity cage' (Penis slot, sold by Finch, androgynous.)</ul>"
			+"<ul>Slightly improved chastity cage icon and description.</ul>"
			+"<ul>Added: 'Dragon-branded condom' and 'Stallion-branded condom' (Penis slot, sold by Ralph and Roxy, androgynous). Condoms can no longer be enchanted (when you load into this version, condom enchantments will be reset), and instead have a maximum cum limit before they break.</ul>"
			+"<ul>Condoms can no longer be enchanted. Instead, they can be sabotaged (so that they always break at moment of orgasm) and repaired (costs one essence to revert sabotage).</ul>"
			+"<ul>Altered belt icon and text.</ul>"
			+"<ul>Added: 'leather wrist bracelets' (Wrist slot, sold by Finch, androgynous. They're just a minor variation of the usual wrist restraints.)</ul>"
			+"<ul>Clothing slots are now 'restricted' instead of being completely blocked by certain racial parts (such as by legs with hoofed feet or harpy wings). Clothing now has tags that allow clothing to be equipped into these 'restricted' slots (this is shown in the clothing's tooltip if available).</ul>"
			+"<ul>Added more clothing tooltip information, to let you know about clothing's extra effects (such as allowing sex-equip, being discarded when unequipped, and requiring penis/vagina/fuckable nipples). Also added this info to the clothing inspect dialogue.</ul>"
			+"<ul>Improved fishnet images to make them a little clearer.</ul>"
			+"<ul>Added 'TRANSPARENT' item tag for clothing, to mark that it leaves all parts fully visible. This has been applied to fishnets, chastity cages, condoms, and anal plugs.</ul>"
			+"<ul>Added secondary and tertiary dye colours to stiletto heels.</ul>"
			+"<ul>Added: 'Taur-trousers' (Leg slot, sold by Nyan, androgynous, can only be worn by characters with a taur lower body.)</ul>"
			+"<ul>Added: 'Taur-skirt' (Leg slot, sold by Nyan, feminine, can only be worn by characters with a taur lower body.)</ul>"

			+"<li>Other:</li>"
			+"<ul>Improved background code for determining who is allowed to do what during sex.</ul>"
			+"<ul>Added support in sex engine for any number of actions to be executed upon sex initialisation.</ul>"
			+"<ul>Slightly altered the 'cum stud' fetish so that it's now mainly about the love of filling orifices with cum, so that NPCs who like/love this fetish will now prioritise creampies/cumming inside their partners.</ul>"
			+"<ul>Slightly altered Lyssieth's fetishes and desires.</ul>"
			+"<ul>Improved positioning action code, and made available to NPCs the 'facesitting' positions in generic sex.</ul>"
			+"<ul><b>Added:</b> 'Mating press' position for use in generic sex scenes.</ul>"
			+"<ul>Characters should now always refer to you (and others) by your nickname/pet name when talking.</ul>"
			+"<ul><b>Added:</b> 'Force creampie' (requires advantageous body position, such as being on top in 69 position), 'Leg-lock' (required unhindered legs), 'Hug-lock' (requires at least two free, unhindered arms), 'Wing-lock' (requires wings large enough to fly), and 'Tail-lock' (required at least one free prehensile tail) as orgasm preparation actions in relevant positions, allowing both you and NPCs to force partners to cum inside.</ul>"
			+"<ul>Tidied up generic orgasm code.</ul>"
			+"<ul>Added 'prevents erection' tag for chastity cages, but currently this only affects descriptors during sex. (I'll add more to it at some point.)</ul>"
			+"<ul>Characters who dislike the 'pregnancy' fetish, or who dislike the 'cum addict' fetish, will now ask for you to pull out just before you orgasm.</ul>"
			+"<ul>Slightly improved the 'sex noise' insertions in sentences.</ul>"
			+"<ul>Added number of times NPCs would like to orgasm to the 'Orgasms' sex status effect tooltip.</ul>"
			+"<ul>Average-size wings are no longer considered large enough to sustain flight on bipeds. Bipeds need large or huge wings in order to fly, while taurs need hugs wings. Arm-wings do not unlock flight for taurs.</ul>"
			+"<ul>Slightly improved aspects of Lilaya's scenes in her lab, and changed some of Rose's dialogue when you talk to her outside her room.</ul>"
			+"<ul>Improved Lilaya's reaction to being fed vixen's virility in sex.</ul>"
			+"<ul>Removed Lilaya's reaction implying sex would no longer be available during Lyssieth's long dialogue.</ul>"
			+"<ul>Added 'Innoxia's Gift' as a debug-only item. It now transforms non-demons into half-demons, and half-demons into full demons. (Type 'buggy' at any point to bring the debug menu up.)</ul>"
			+"<ul>Adjusted some of the more ridiculously-sized penises to slightly more believable, although still extremely large, values. (Such as Amber going from 18 inches to 10.)</ul>"
			+"<ul>Slightly tidied up and compacted the Furry Preferences screen.</ul>"
			+"<ul>Set multi-breast values for animal-morphs to more closely resemble their real-life thorax-counterparts. (Remember that you can turn this off in the 'Options -> Furry Settings' menu.)</ul>"
			+"<ul>Slightly altered some average breast sizes (rabbit-morph from E to C, bat-moprh from C to B).</ul>"
			+"<ul>Improved lactation transformation menu options.</ul>"
			+"<ul>Changed access requirement for Rose's hand holding scene. You now need to talk to her in the order 3, 1, 4, 2 in order for the hand-holding scene action to appear.</ul>"
			+"<ul>Humans and harpies can now spawn as slaves in the stocks in Slaver Alley.</ul>"
			+"<ul>Slightly reduced save file size.</ul>"
			+"<ul>Added 'Cowgirl (on back)' positioning action in sex.</ul>"
			+"<ul>Added foot structure transformation options to the 'racial food' items' enchantment menu. Select 'legs' as the primary enchantment, and then, depending on the item, you'll be able to select one or more from the three foot structure types as the secondary modifier. </ul>"
			+"<ul>Compacted formatting of additional information during sex. (Meaning things which were coloured, such as lubrications and penetrations.)</ul>"
			+"<ul>Youko tails are now treated as being prehensile.</ul>"
			+"<ul>Updated the exposes status effects, changing the icon, description, and effects. Added an alternative (neutral) effect for if the exposed cahracter's lower body is not bipedal.</ul>"
			+"<ul>Added 'horns per row' self-transform option to the demon & slime self-transformation menus.</ul>"
			+"<ul>Increased base value of demon and half-demon slaves to 120,000 and 50,000, respectively.</ul>"
			+"<ul>Clothing transformation status effect updates now tell you which item of your clothing has caused the transformation.</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed cause of a major bug where both unique and generic NPCs were sometimes not being loaded in correctly. (Reported instances of this were affecting Scarlett and Brax, but could have affected any character.) Unique characters are added back into your save when you load into this version.</ul>"
			+"<ul>Quite a few typo and parsing fixes, including quite a few grammatical fixes in sex actions.</ul>"
			+"<ul>Fixed issue where futanari testicles would be described as being external, even if futanari external testicles were turned off in content options.</ul>"
			+"<ul>Fixed bug where Lyssieth would always have a minimum-size penis.</ul>"
			+"<ul>Fixed post-sex affection decreases not taking into account if you liked the associated fetish or not. (It was only checking if you had the fetish, not if you liked it.)</ul>"
			+"<ul>Fixed issue with several incorrect 'initial penetration' descriptions during sex.</ul>"
			+"<ul>Fixed issue with sex sometimes starting with the dialogue already scrolled down a fair way.</ul>"
			+"<ul>Fixed being able to self-transform during initial demon transformation scene, which was messing things up.</ul>"
			+"<ul>Fixed 'limit--' and 'limit++' buttons in enchantment screen sometimes not working.</ul>"
			+"<ul>Fixed grammatically incorrect descriptions of your virginity losses when they were taken by yourself. </ul>"
			+"<ul>Fixed issue where error log would sometimes be spammed with incorrect warnings about clothing blocking other clothing's removal.</ul>"
			+"<ul>Intercrural sex is no longer blocked by socks or tights.</ul>"
			+"<ul>Fixed bug where partner would not be able to choose to cum on the floor in positions where they should have been able to.</ul>"
			+"<ul>Fixed 'keep fucking' action (preparation for partner orgasming while fucking you with a dildo) incorrectly having the cum fetishes associated with it.</ul>"
			+"<ul>Cum, milk, and girlcum will no longer be randomly given the descriptor 'human' in scenes.</ul>"
			+"<ul>Fixed issue where NPCs in sex would sometimes not get a chance to take an 'orgasm preparation' action before you orgasmed.</ul>"
			+"<ul>Clothing that you put on during sex is no longer removed and thrown to the floor at the end of sex.</ul>"
			+"<ul>Fixed bug where sometimes the sequence of orgasm actions would leave Lilaya with no opportunity to react to being creampied.</ul>"
			+"<ul>Fixed Lilaya and Meraxis not being recognised as your half-sisters, nor Lyssieth as your mother, after demon TF.</ul>"
			+"<ul>Ralph will now correctly stock modded clothing with the tag 'SOLD_BY_RALPH'.</ul>"
			+"<ul>Clothing rarity defined as non-common will now accurately display that rarity. (Mostly affects modded clothing.)</ul>"
			+"<ul>Fixed bug where demon offspring were not being assigned the correct subspecies. (Which was causing Lyssieth to give birth to elder lilin.)</ul>"
			+"<ul>Fixed bug where NPCs would sometimes react to your impending orgasm as though it was a different participant that was about to orgasm.</ul>"
			+"<ul>Fixed issue with incorrect recognition of potential fathers of ongoing pregnancies. This mostly affected dialogue where a character would (incorrectly) react to you getting them pregnant.</ul>"
			+"<ul>Fixed post-defeat siren being reset and moved back to her fortress after 2 weeks like her subordinates. (She will be returned to her office upon game loading.)</ul>"
			+"<ul>Fixed bug where footjob actions would be describing the wrong person's foot.</ul>"
			+"<ul>Foot structure is now correctly saved & loaded.</ul>"
			+"<ul>Fixed issue where spreader bar would be concealed by trousers, rendering both trousers and spreader bar impossible to remove.</ul>"
			+"<ul>Fixed pregnancy stats page's 'Resolved Pregnancy' entries having the genders of your offspring swapped.</ul>"
			+"<ul>Fixed parsing error when using a used condom on someone.</ul>"
			+"<ul>Tongues being lubricated by saliva will no longer be displayed as lubrication information during sex.</ul>"
			+"<ul>If one of your slaves ambushes you in the streets of DOminion, it should now be correctly treated as public sex.</ul>"
			+"<ul>Fixed issue where you could offer body to muggers, then immediately end sex in the resulting sex scene.</ul>"
			+"<ul>Fixed 'submit' in combat going back to the combat dialogue for one turn before defeat.</ul>"
			+"<ul>Fixed some odd lust tooltip descriptions (such as saying you aren't interested at all in having sex when at 100 lust).</ul>"
			+"<ul>The sadist fetish now correctly applies lust damage to the attacker, not the target.</ul>"
		+ "</list>"
			

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.0.5</h6>"
			+ "<li>Gameplay:</li>"
			+ "<ul>You can now spend 3 arcane essences to identify clothing in your possession.</ul>"
			+ "<ul>The cost for having a vendor identify your clothing has increased from 10 to 400.</ul>"
			
			+ "<li>Contributors:</li>"
			+ "<ul>Added clothing: 'Bunny Headband' (head slot, androgynous, sold by Nyan). (created by dsg)</ul>"
			+ "<ul>Fixed bug where adding fetishes/changing desires from phone fetish menu would not work. (PR#1034 by BDPMP)</ul>"
			+ "<ul>Fixed bug in the main quest, where purchasing Scarlett from Alexa would not work. (PR#1038 by BDPMP)</ul>"
			+ "<ul>Fixed a Lilaya Condom Breaking reaction then/else error. (PR#1039 by EPSIL0N)</ul>"
			+ "<ul>Correction from previous patch notes: GynoidDoesGames was the author of PR#989 (the increase in name variety).</ul>"
			
			+ "<li>Clothing:</li>"
			+ "<ul><b>Added:</b> Outfit generation framework, which enables the game to apply a preset list of clothing rules to characters of various sorts. At the moment, it's only applied to muggers, but I will add all other NPCs to it soon. The outfit generation is defined in xml files, so you can make mods for outfits. The 'mods/innoxia/outfits/casualDress/dress_toys.xml' file is annotated to explain how it works.</ul>"
			+ "<ul>Changed & added colours, altered icons, and moved out to res/clothing/innoxia the follow clothing items: 'suit jacket', 'anklet', 'shin guards', 'elbow-length gloves'.</ul>"
			+ "<ul>Added metallic effect to metal colours in clothing & weapon dye screens.</ul>"
			+ "<ul>'Heart barbells' are now considered feminine.</ul>"
			
			+ "<li>Other:</li>"
			+ "<ul>Moved multi-breast and udder settings from furry preferences to content options. Both of these options now disable/enable all relevant descriptions & interactions if you set them to off/on, instead of preventing/allowing NPCs to spawn with them.</ul>"
			+ "<ul>The game now starts at 20:34, so that by the time you get to your room (after 21:00), you can sleep until morning in one button press (instead of sleeping until 21:00 and then again until morning). Time now also passes in the character creation scenes.</ul>"
			+ "<ul>Improved formatting of the post-sex descriptions for orifices being stretched.</ul>"
			+ "<ul>Changed smallest time-unit that the game was tracking from minutes to seconds. Travel through some tiles now takes less than a minute (such as moving in Lilaya's home now taking 10 seconds per tile, and sex actions taking 20 seconds instead of 60).</ul>"
			+ "<ul>After waiting in the queue or giving Jules a blowjob at the nightclub, you will now be moved one tile north, into the club itself, instead of remaining on the entrance tile. </ul>"
			+ "<ul>Made the calendar and time button-areas larger (above the minimap), and added more information to the time tooltip (to include seconds).</ul>"
			+ "<ul>Creampies no longer drain during sex scenes.</ul>"
			+ "<ul>Reduced Brax's Physique, increased his corruption, stopped him from using 'Savage attack' in combat, and fixed issue where he would have just a single chaos feather as a weapon.</ul>"
			+ "<ul>Gave Alexa 15 base arcane, as well as the Slam spell, to explain how she's able to use arcane force in her punishment scenes.</ul>"
			+ "<ul>Improved 'recovering orifice' status effect description and icon.</ul>"
			+ "<ul>Improved status effect 'time remaining' tooltip display.</ul>"
			+ "<ul>Glory hole participants are now almost completely hidden from view - the only information available about them is their genitals and status effects.</ul>"
			+ "<ul>Increased 'Swamp Water' intoxication effect from +50% to +75% intoxication.</ul>"
			+ "<ul>The stretched orifices status effect is now shown in sex, so you can easily see how much everything is stretched by via the tooltip.</ul>"
			+ "<ul>Added auburn as a possible colour for fur and feathers.</ul>"
			+ "<ul>Added slime body hair as a new covering, so it can now have a separate colour to the rest of the body's slime.</ul>"
			+ "<ul>Added a short alternate ending to Lilaya's sex scenes for if you didn't get her to orgasm.</ul>"
			
			+ "<li>Sex (bugs and improvements):</li>"
			+ "<ul>Added handling of unusual scenario (which I couldn't replicate) where an NPC would have no available actions from which to select during sex.</ul>"
			+ "<ul>Fixed issue with NPCs performing redundant displacements (such as pulling down trousers, then unzipping them).</ul>"
			+ "<ul>Fixed issue where NPCs would continuously switch position in sex. They can still switch position if they have 'full' control (usually when they are the dom or if it's fully consensual sex), but will now only do it once in foreplay and once in main sex.</ul>"
			+ "<ul>Improved NPC preference generation methods, so NPCs' sex action preferences should now be a little more reasonable. (i.e. They will still take their fetishes into account, but are now more likely to want to perform actual penetration in sex.)</ul>"
			+ "<ul>Fixed issue with the 'request/offer oral' actions, where any form of tongue action was not being registered as being a viable oral option for the NPC to perform.</ul>"
			+ "<ul>You now earn essences for the first five orgasms each character has in sex, at a rate of 2/2/2/1/1 (4/4/4/2/2 with the Nymphomaniac trait). 0 essences are earned if the character has the 'recovering aura' effect. Updated orgasm tooltip to show this.</ul>"
			+ "<ul>Added 'cowgirl (bottom)' positioning action.</ul>"
			+ "<ul>Added more indication that positioning actions are requests when you are not the dom or a full-control sub.</ul>"
			+ "<ul>NPCs who do not dislike the 'cum stud' fetish now prefer to cum on body parts where available, instead of the floor.</ul>"
			+ "<ul>NPCs will now refuse your requests (offering/requesting body parts) if they are being rough, you are the sub, and you don't have full control in the scene.</ul>"
			+ "<ul>Improved the sex AI to reduce the instances of NPCs doing the annoying 'start/stop' action cycle. (After doing this, I saw no more instances of this annoying cycling of actions, but I can't be sure that it's been fixed entirely.)</ul>"
			+ "<ul>Fixed NPCs not using positioning actions in taur sex scenes.</ul>"
			+ "<ul>NPCs now take into account more than one fetish at a time when deciding upon what actions they want to perform, so now, for example, if they have oral+anal fetishes, anilingus is far more likely to be chosen than it was in previous versions.</ul>"
			+ "<ul>Added 'none' option to self-transform menu for crotch-boobs, and slightly improved the formatting of the breasts & crotch-boobs windows.</ul>"
			+ "<ul>NPCs' ability to perform actions based on their fetishes/desires has been improved.</ul>"
			
			+ "<li>Bugs:</li>"
			+ "<ul>Fixed major bug where walking in Dominion's boulevard tiles during an arcane storm would cause a game-breaking background bug. (Related to the tile's population not being generated correctly.)</ul>"
			+ "<ul>Fixed post-sex oral experience gain description for NPCs referencing your ability to perform oral, not theirs.</ul>"
			+ "<ul>Vaginal urethra and crotch nipples are now tracked correctly in the 'recovering orifices' status effect, and thus they now actually return to their natural capacity over time.</ul>"
			+ "<ul>Fixed background issue where orifices' post-stretched recover rate was being based on their elasticity, not plasticity.</ul>"
			+ "<ul>Fixed issue where taur land speed movement reduction (-50% time) wasn't being applied.</ul>"
			+ "<ul>Fixed decimal formatting in creampie status effects.</ul>"
			+ "<ul>Typo & parsing fixes.</ul>"
			+ "<ul>Fixed post-sex scene in the nightclub's toilets appending dialogue as though you were back out in the streets of Dominion.</ul>"
			+ "<ul>Fixed background error related to NPCs not having a body preference set when loading into this version from an old save.</ul>"
			+ "<ul>Randomly generated capacity descriptors (tight, loose, etc.) are now correctly generated based on the orifice's current capacity, taking into account any stretching.</ul>"
			+ "<ul>Intercrural is no longer available in the mating press position.</ul>"
			+ "<ul>Body parts' appearances affected by psychedelic fluids no longer always say that they are virgin.</ul>"
			+ "<ul>Glory hole users will no longer spawn in as taurs (as it would be quite difficult using the stalls, let alone the holes themselves, as a taur).</ul>"
			+ "<ul>Fixed glory hole user's full inventory being visible when they switched to fucking you.</ul>"
			+ "<ul>Fixed the 'too loose' sex status effect persisting after the penetration was removed.</ul>"
			+ "<ul>Fixed parsing errors in the taur positioning action tooltips, as well as some incorrect descriptions related to the starting scene text.</ul>"
			+ "<ul>Fixed issue with alitaur, unitaur, alicorn-morph, and unicorn-morph not being detected correctly (they were always detected as a pegataur or pegasus-morph).</ul>"
			+ "<ul>Fixed issues with some dialogues returning incorrect text in sex and transformations.</ul>"
			+ "<ul>Fixed udders being described as 'a pair'. Also fixed transformation text related to this issue.</ul>"
			+ "<ul>Fixed crotch-boob transformation screen from generating random names (crotch-boobs, crotch-tits, crotch-breasts, etc.) each time you selected an option, which was causing the screen's elements to resize.</ul>"
			+ "<ul>Fixed broken horn transformation text.</ul>"
			+ "<ul>Fixed issue where all races, including demons and humans, were spawning with crotch-boobs. Now only furry races will spawn with them. All crotch boobs on bipeds will be reset to default values when you load into this version.</ul>"
			+ "<ul>Races that do not normally have crotch boobs are no longer described as not having any in the appearance screen (as that is to be assumed).</ul>"
			+ "<ul>Fixed incorrect crotch nipple descriptors being generated (that were being based on breasts, not crotch-boobs).</ul>"
			+ "<ul>Fixed cases where crotch-boobs were being incorrectly treated as though they had penetrable nipples, both in descriptions and sex actions.</ul>"
			+ "<ul>Stopped excessive lube information (including slime parts and urethra info) from being displayed at the start of sex.</ul>"
			+ "<ul>Removed duplicate 'deepthroat' orgasm action from the kneeling oral position.</ul>"
			+ "<ul>Fixed post-sex scene in Lilaya's geisha scene returning an error.</ul>"
			+ "<ul>Fixed the 'Limit--' button in enchantment screen not working correctly.</ul>"
			+ "<ul>Wolfgang and Karl now correctly drop dog-related items after beating them, instead of wolf-related ones.</ul>"
			+ "<ul>Fixed issue where tigers could spawn as 'black with black stripes'. They now have a small chance to have their primary colour as auburn, amber, or tan instead of orange.</ul>"
			+ "<ul>Human parts are no longer ever described as being animal-like (for if you TF parts on a taur body to human).</ul>"
			+ "<ul>Lilaya and Meraxis now gain a significant amount of affection towards Lyssieth as part of their demon transformation. Lilaya now also gains a lot of affection towards you for agreeing to help her. This is all applied retroactively to your save when you load into this version. </ul>"
			+ "<ul>Fixed the body hair (beard, underarm, pubic, ass) options in the self-transformation menu showing a cost, even though it's free to change.</ul>"
			+ "<ul>Fixed pubic hair options in the self-transformation menu being locked out by having 'ass hair' disabled, instead of 'pubic hair'.</ul>"
			+ "<ul>Fixed debug menu's self-transform menu not having eye colours as an option.</ul>"
			+ "<ul>Fixed penetration/orifice type status effects (the 'ass status', 'vagina status', etc. ones) being applied to characters not in sex, which was sometimes causing a background error to be thrown.</ul>"
			+ "<ul>Fixed several issues with Lilaya's pregnancy-related dialogue after she's become a full demon.</ul>"
			+ "<ul>Blocked Lilaya from switching her position in her 'testing' chair sex scene, as she was being a little too aggressive with it.</ul>"
			+ "<ul>Fixed partner's lust dropping to 0 during the last turn of sex. (It is now correctly set to 0 after sex, so long as they orgasmed at least once.)</ul>"
			+ "<ul>Fixed the 'Arcane Pregnancy Tester' item not removing you as a potential father from the pregnancy stats page (so long as you were not the father). This was most notable after losing the pregnancy roulette game, where in the pregnancy stats screen, it would still show you as a potential father.</ul>"
			+ "<ul>Fixed bug where Roxy would not stock condoms.</ul>"
			+ "<ul>Fixed regular chastity cage being called 'ornate chastity cage'.</ul>"
			+ "<ul>Fixed lilin offspring not being generated with the correct surname.</ul>"
			+ "<ul>Fixed issue where dying clothing would sometimes not work and throw a background error.</ul>"
			+ "<ul>Fixed potential cause of an issue where slime colourings would sometimes reset.</ul>"
			+ "<ul>Added handling of game-breaking error that could sometimes occur when displacing clothing that was accidentally configured to block one anotehr's displacements.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.3.0.7</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>There is now a small chance for unidentified clothing to have a stronger sealing enchantment. (Chances for unidentified clothing are now: 50% it's a good item, 30% it's bad with a 5 essence removal cost, 12% bad with 25 cost, 6% bad with 100 cost, and 2% bad with 500 cost.)</ul>"
			+"<ul>When crotch-boobs are set to 'off' in the content settings, you will no longer see the crotch boob button in the self-TF menu, nor 'crotch-boob' or 'crotch-boob milk' TF modifiers in the enchantment menu when enchanting a food item.</ul>"
			+"<ul>Added 'remove' secondary modifier to crotch-boob transformation potions.</ul>"

			+"<li>Other:</li>"
			+"<ul>Standardised the positioning of the 'stretched from' and 'stretched to' values in the 'recovering orifices' status effect tooltip. (They were being reversed in sex, which was a little confusing.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed issue with the game's turn taking longer than usual when passing over midnight.</ul>"
			+"<ul>Fixed stretched orifices continuously expanding over time, instead of contracting.</ul>"
			+"<ul>Fixed the overview tab in the inventory not working.</ul>"
			+"<ul>Fixed issue where NPCs would never give birth.</ul>"
			+"<ul>Fixed the identify clothing action breaking inventory management.</ul>"
			+"<ul>Fixed incorrect fluid regeneration rate values (for milk and cum) being shown in your stats menu.</ul>"
			+"<ul>Fluids should now regenerate during sex (probably by a very small amount, considering it's only 10 seconds per sex action, unless you have boosted your regen).</ul>"
			+"<ul>Corrected some instances of 'kitsune' to 'youko', and changed the plural form from 'youkos' to 'youko'.</ul>"
			+"<ul>Fixed instances of youko's names not being displayed correctly (as they use their surname as a first name).</ul>"
			+"<ul>Fixed issue where random NPCs were spawning with jewellery for body slots they don't have pierced in their inventory.</ul>"
			+"<ul>You can now set your base desire for a fetish to 'love' even while you have that fetish.</ul>"
			+"<ul>Restored the 'black-with-black-stripes' colour for tiger-morphs (as a rare variation).</ul>"
			+"<ul>Fixed incorrect breast & crotch-boob type transformation descriptions.</ul>"
			+"<ul>Fixed being able to change a biped's crotch-boob type when you had that setting turned off.</ul>"
			+"<ul>Fixed bug where you sometimes couldn't teleport back to Lilaya's lab when she was pregnant as a full demon.</ul>"
			+"<ul>Fixed the 'Step outside' button not working when transforming Meraxis into a full demon.</ul>"
			+"<ul>Fixed NPCs using non-penis-related 'giving anal' options, even if they did not like the 'giving anal' fetish. (This was most noticeably resulting in NPCs suddenly fingering or tail-fucking your ass...)</ul>"
			+"<ul>Fixed Rose's sex preferences bugging out in her dominant scene (where she punished you in Lilaya's room).</ul>"
			+"<ul>Fixed issue where interacting with a friend in their apartment during an arcane storm would start spawning random NPCs on the tile, which you'd then start interacting with.</ul>"
			+"<ul>Lilaya should no longer lock out all of her options when starting a game with an imported demonic character. (Her dialogue will not react to you being a demon until after meeting Lyssieth.)</ul>"
			+"<ul>Fixed a potential cause of clothing not being stocked in some shops. (Although I think this will still keep on happening - this bug is very hard to track down.)</ul>"
		+ "</list>"
	;
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

			+ "<p>This game is a <b>fictional</b> text-based erotic RPG. All content contained within this game forms part of a fictional universe that is not related to real-life places, people or events.<br/><br/>"

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

		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Akira", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alvinsimon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("dragonouv2019", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Aklev", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 15));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Anonymous_Platypus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 4, 12));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Jack Cloudie", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Deimios", "", 0, 0, 3, 7));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Mhaak", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("FidelPinochetov", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Jason Paterson", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Tieria", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 15));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("BlueWolf", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Captain_Sigmus", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Atroykus", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 16));
		credits.add(new CreditsSlot("CancerMage", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 9, 5));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Cynical-Cy", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Saladofstones", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 13));
		credits.add(new CreditsSlot("Hikaru Lightbringer", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Griff", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Darthsawyer", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("DeadEyesSee", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 8, 4));
		credits.add(new CreditsSlot("CruellerTwo24 ", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Desgax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Endless", "", 0, 0, 6, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Erin Kyan", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Avandemine", "", 0, 0, 1, 9));
		credits.add(new CreditsSlot("F. Rowan", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Goldmember", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Aceofspades", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Assiyalos", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Helyriel", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Evit", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("no1skill", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("suka", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Kaerea", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Kaleb the Wise", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Karlimero", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Tappi", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 11, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("BlueVulcan", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Lee Thompson", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Leob", "", 0, 0, 10, 4));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Littlemankitten", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kitsune Lyn", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Manwho", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Beldamon", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Mega", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Mora", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Kobu", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Neon Swaglord Chen", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Nnxx", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("NorwegianMonster", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Seo Leifthrasir", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 16));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 11));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("PoyntFury", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("awrfyu_", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 9, 0));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Reila Oda", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Roarik", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Dark_Lord", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 16));
		credits.add(new CreditsSlot("Roger Reyne", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Schande", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Blue Kobold", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 16));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Sir beans", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Spectacular", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Starchiller", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 15, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("The Brocenary", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TKaempfer", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Tom Clancy's Pro Skater", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 16));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 11));
		credits.add(new CreditsSlot("Jess", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("SolarEidolon", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 12));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Venomy", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Weegschaal", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Will Landrum", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Marys", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Wolfrave", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Yuki_Sukafu", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Zaya", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Zero_One", "", 0, 0, 2, 0));
		
		
		
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

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION+(DEBUG?" (Debug Mode)":""));

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
			int maxlength = (v1.length > v2.length) ? v1.length : v2.length;
			for (int i = 0; i < maxlength; i++) {
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
		if (!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' _]*")) {
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
			properties.race = game.getPlayer().getSubspecies().getName(game.getPlayer());
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
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer(),
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY));
				
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
