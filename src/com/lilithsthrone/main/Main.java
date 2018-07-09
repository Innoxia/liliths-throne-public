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
 * @version 0.2.8
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.2.8.2",
			VERSION_DESCRIPTION = "Alpha";
	
	private final static boolean DEBUG = false;

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
		
		"<p>"
			+ "Hello again!"
		+ "</p>"
			
		+ "<p>"
			+ "For this hotfix, I've sorted out all of the major sex scene bugs that you've reported, as well as writing in all of the nightclub's descriptions."
			+ " I plan on adding a few more things to the club (including gloryholes and a way to let your partner take cahrge and lead you around), which will hopefully make it into the next version."
		+ "</p>"
			
		+ "<p>"
			+ "For the next couple of versions, I'm going to focus on catching up on all of the Patreon poll winners, as well as fixing bugs and implementing as many smaller features as I can. ^^"
		+ "</p>"
			
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.7.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Finished work on sex action code to fully support NPC-on-NPC actions.</ul>"
			+"<ul>Added support for clit-fucking (when a clit is large enough to act as a pseudo-cock) and foot-related sex actions. (The actions will be added very soon!)</ul>"
			+"<ul>Added support for tentacles. (The actions will be added when tentacle TFs are added, which will be a little later on.)</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Added modifiers and girth for clitoris, with associated TFs.</ul>"
			+"<ul>Added oral position for chair sex.</ul>"
			+"<ul>Changed Broodmother and Seeder fetishes to be perks instead.</ul>"
			+"<ul>Added 'Face Sitting' as a generic position in sex scenes.</ul>"
			+"<ul>Added hoofed legs as a TF option for demons, with a 25% chance for demon NPCs to spawn in with hoofs.</ul>"
			+"<ul>Added 'zebra' as an equine subspecies, with a 'zebra tail' type.</ul>"
			+"<ul>Converted Kate's and Lilaya's very old chair sex scene format. (I will add the old chair-specific descriptions to sex actions as I convert them to NPC-on-NPC format.)</ul>"
			+"<ul>Added milk and cum regeneration enchantments for clothing.</ul>"
			+"<ul>Added foot fetishes. (Only Amber's scenes have foot-related actions at the moment, but I will be adding foot actions very soon!)</ul>"

			+"<li>Contributions:</li>"
			+"<ul>Cleaned EnchantingUtils getCost method. (Pimgd)</ul>"
			+"<ul>Added penis-related fetishes. (Rfpnj)</ul>"

			+"<li>Norin's Clothing Contribution:</li>"
			+"<ul>Added: Anal beads (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Clover clamps (nipple slot, BDSM set, sold by Finch).</ul>"
			+"<ul>Added: Realistic dildo (vagina slot, sold by Finch).</ul>"
			+"<ul>Added: Bat-wing barbells (nipple-piercing slot, sold by Kate).</ul>"
			+"<ul>Added: Heart barbells (nipple-piercing slot, sold by Kate).</ul>"
			+"<ul>Added: 'Caution-When-Wet' piercing (navel slot, sold by Kate).</ul>"
			+"<ul>Added: Tail ribbon (tail slot, sold by Nyan).</ul>"

			+"<li>Clothing:</li>"
			+"<ul>Added: Butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Jewelled butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Jewelled-heart butt plug (anus slot, sold by Finch).</ul>"
			+"<ul>Added: Tail butt plug (anal slot, sold by Finch).</ul>"
			+"<ul>Added: Insertable dildo (vagina slot, sold by Finch).</ul>"

			+"<li>Sex AI:</li>"
			+"<ul>Fixed issue where NPCs would sometimes perform actions that they didn't like.</ul>"
			+"<ul>Fixed NPCs never starting oral, even if they wanted to, if they still had their oral virginity.</ul>"
			+"<ul>NPCs will now prefer to penetrate using their penis rather than their tail.</ul>"
			+"<ul>NPCs should now be preferring to penetrate their partner before themselves.</ul>"

			+"<li>Other:</li>"
			+"<ul>Updated credits page.</ul>"
			+"<ul>Having a sheathed penis will now conceal your cock bulge from gender detection up to 16 inches. Non-sheathed penis bulge is visible at 8 inches.</ul>"
			+"<ul>Added freckles as a pattern for demon skin.</ul>"
			+"<ul>Added description of wing colour to selfie.</ul>"
			+"<ul>The chemise no longer blocks groin areas.</ul>"
			+"<ul>Companions and companions' elementals no longer have their level affected by difficulty settings. (Making the higher difficulty levels harder.)</ul>"
			+"<ul>You can now see what your partner in sex is wanting (under the 'Desires' status effect description).</ul>"
			+"<ul>Removed tooltips from enchanting 'Limit' and 'Potency' buttons. (They were just repeating the button text and getting in the way.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed issue with map rendering incorrectly when zoomed out.</ul>"
			+"<ul>Fixed game sometimes incorrectly scrolling back up to the top of dialogue.</ul>"
			+"<ul>Fixed 'Stroke cock' actions not showing up in sex scenes.</ul>"
			+"<ul>Fixed some incorrect sex action availability.</ul>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>NPCs will no longer spawn wearing leg clothing with dresses.</ul>"
			+"<ul>AI in sex will now correctly use actions based on their preferences (this bug was affecting cultists not respecting your 'Offer pussy' or 'Offer ass' choice).</ul>"
			+"<ul>Fixed issue where slimes were always spawning in with human-shaped bodies. (They can now spawn as slimes of any morph type.)</ul>"
			+"<ul>Fixed clothing pattern colours not being saved/loaded.</ul>"
			+"<ul>Fixed Lilaya asking you to pull out so as to not get pregnant when she was already pregnant.</ul>"
			+"<ul>Fixed limb description in selfie not taking into account body shape.</ul>"
			+"<ul>Fixed issue with covering patterns not working (which was causing the only patterns for hair to be plain or ombre).</ul>"
			+"<ul>Cum expulsion enchantment is now correctly free when you have the Water school passive.</ul>"
			+"<ul>Fixed incorrect penile virginity loss text.</ul>"
			+"<ul>Fixed formatting bug where periods would sometimes incorrectly carry over to the next line.</ul>"
			+"<ul>Fixed some instances of 'Submit' not having a corruption bypass.</ul>"
			+"<ul>Fixed another issue with companions' elementals not being dismissed properly.</ul>"
			+"<ul>Fixed issue of starting a new game as an exported slave identifying you as owning yourself.</ul>"
			+"<ul>Fixed deny orgasm action not working correctly, and added the denial fetishes as associated fetishes.</ul>"
			+"<ul>Fixed Brax always resisting in submissive sex.</ul>"
			+"<ul>Fixed offspring referring to you as their mother when you first greet them, even if you were the father.</ul>"
			+"<ul>Fixed NPCs reacting as though they knew you had a penis even if you were showing no bulge.</ul>"
			+"<ul>Fixed special attack descriptions returning incorrect target text.</ul>"
			+"<ul>Improved modded clothing load fail error messages.</ul>"
			+"<ul>Fixed nipple and urethra penetration actions not working.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.7.6</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finch now has a chance to stock (already-identified) enchanted items, and Roxy stocks 10 unidentified pieces of clothing. You will need to wait until they re-stock (at midnight) to see their new items for sale.</ul>"
			+"<ul>Added penis and foot fetishes to clothing and mystery kink enchantments.</ul>"
			+"<ul>Added 'appears as age' stat to characters, which is displayed in their description, along with age selection in character creation. (If you want to edit the age of your already-existing character, first save the game in this new version, then open the save file and search for/edit the 'yearOfBirth' field. The game converts any ages lower than 18 to 18.)</ul>"
			+"<ul>Added content option to disable ages being displayed.</ul>"
			+"<ul>Added tattoo selection to character creation.</ul>"
			+"<ul>Increased impregnation chance (formula's /4 modifier was reduced to /3).</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed typos in some sex positions. (Nnxx)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Moved some modded clothing/tattoo files over into new 'clothing' and 'tattoos' directories, to help distinguish between default LT files and mods that you've downloaded.</ul>"
			+"<ul>Slime subspecies (such as slime-cat-morphs, slime-horse-morphs, etc.) now respect your furry settings of the derived race. i.e. If you have horse-morphs disabled, no slime-horse-morphs will spawn.</ul>"
			+"<ul>Added description of foot structure to leg types.</ul>"
			
			+"<li>Sex AI:</li>"
			+"<ul>Fixed NPCs choosing 'Doggystyle (oral)' position when they wanted to have penetrative sex.</ul>"
			+"<ul>Fixed some sex actions not having correctly-associated fetishes, which was causing the AI to use actions they disliked/hated.</ul>"
			+"<ul>Fixed issue where NPCs would continuously start penetrating then stop over and over.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed parsing error when viewing NPC with demon wings.</ul>"
			+"<ul>Fixed bug where ellipsis wouldn't display correctly.</ul>"
			+"<ul>Fixed NullPointerException issue which was sometimes occurring in sex scenes.</ul>"
			+"<ul>Fixed Lilaya and Kate constantly switching positions in sex.</ul>"
			+"<ul>Fixed no actions being available in the chair sex scenes.</ul>"
			+"<ul>Fixed panty masturbation scene not working.</ul>"
			+"<ul>Fixed incorrect arousal gains in masturbation. (They were far too small before.)</ul>"
			+"<ul>Fixed error in conditional parser that was causing some sections of text to sometimes not be rendered.</ul>"
			+"<ul>Fixed another error in conditional parsing that was causing some dialogue to return 'Error in conditional parsing!'.</ul>"
			+"<ul>Fixed pregnancy roulette being broken for 'On back' positions, and for playing as one of the breeders not working.</ul>"
			+"<ul>Fixed bug in sex where your partner would use the 'Stop position switch' action every turn.</ul>"
			+"<ul>Fixed characters without a penis spawning in with the cock stud fetish.</ul>"
			+"<ul>Added paizuri to sixty-nine position.</ul>"
			+"<ul>Fixed some sex actions being incorrectly blocked (such as masturbating while giving someone a blowjob).</ul>"
			+"<ul>Made 'groin + mouth' actions blocked while there is any ongoing groin + area action (to stop impossible actions like receiving cunnilingus while performing paizuri in non-69 positions).</ul>"
			+"<ul>Fixed foot and penis fetish teases working effectively against the wrong fetish type.</ul>"
			+"<ul>Slave-on-slave sex events should now fire if the slaves are idle (in the same room, or with 'house freedom' permission set). Slaves will not initiate penetrative sex if their vagina is virginal.</ul>"
			+"<ul>Fixed cum regeneration being labelled as 'milk regeneration'.</ul>"
			+"<ul>For cowgirl: Added anal actions, fixed paizuri being available for the wrong person, and removed performing thigh sex on the one lying down.</ul>"
			+"<ul>Removed giving blowjob from doggy-style oral position.</ul>"
			+"<ul>Fixed slaves being able to be sent to Kate even if you hadn't encountered her.</ul>"
			+"<ul>Fixed bug where most characters were spawning with their penile virginity. (It's now a 15% chance for them to spawn as penile virgins.)</ul>"
			+"<ul>Fixed clothing type encyclopedia unlock events using custom names.</ul>"
			+"<ul>Naming enchanted tattoos now works correctly.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.7.8</h6>"
			+"<li>Contributors:</li>"
			+"<ul>Added: Fox-morphs (with Fennec, Youko, and Fennec-Youko subspecies), along with related essence, book, and TF food and drink. (Tukaima)</ul>"
			+"<ul>Added: Lynx, leopard, snow leopard, lion, tiger, cheetah, and caracal subspecies for cat-morphs. (Irbynx)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Eye transformations now affect iris and pupil shape.</ul>"
			+"<ul>The 'Deny' action in sex is now always available (so long as you're the dom or have equal control as the sub).</ul>"
			+"<ul>Event log is now sorted with the most recent events at the top of the list.</ul>"
			+"<ul>Converted kissing actions to NPC-on-NPC format.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed Ralph not starting anal when you asked for big discount.</ul>"
			+"<ul>Fixed not being able to swap or change positions with Lilaya & Kate in their sex scenes.</ul>"
			+"<ul>Fixed slaves in the stocks and milking stalls using their hands, and added anal options for the 'standing behind' position.</ul>"
			+"<ul>Typo and parsing fixes.</ul>"
			+"<ul>Fixed 'Deny' sex action not working.</ul>"
			+"<ul>Fixed tattoos without any writing loading in as having blank 'normal grey text'.</ul>"
			+"<ul>Fixed the 'Age' content toggle not working.</ul>"
			+"<ul>Fixed blowjob not being available sometimes in sixty-nine position.</ul>"
			+"<ul>Fixed buttplugs not actually blocking anus.</ul>"
			+"<ul>Fixed NPCs choosing oral sex actions even if they hated or disliked it.</ul>"
			+"<ul>Fixed Lilaya and Kate sometimes continuously switching positions in their sex scenes.</ul>"
			+"<ul>Fixed tattoos sometimes being applied with the last letter in their writing being cut off.</ul>"
			+"<ul>You can now end sex when starting submissive sex with a companion.</ul>"
			+"<ul>Fixed case where dominant NPC would refuse to take any action in sex.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.8</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Added 'The Watering Hole' nightclub to Nightlife. The descriptions are all placeholders at the moment, but I will get them written in fully for a hotfix.</ul>"
			
			+"<li>Sex action conversion:</li>"
			+"<ul>All anal fingering actions.</ul>"
			+"<ul>All nipple and breast actions. Improved some nipple fingering action descriptions and added actions for reacting to being nipple fingered.</ul>"
			+"<ul>All vaginal fingering actions. Improved structure of clit-related action.</ul>"
			+"<ul>All cock/ball stroking actions. Added 'handjob' as an ongoing sex action, with all associated reactions.</ul>"
			+"<ul>All anilingus actions. Fixed some incorrect descriptions in anilingus actions.</ul>"
			+"<ul>All cunnilingus actions. Separated clit and 'genderless mound' actions from vaginal actions.</ul>"
			+"<ul>All breast kissing and breastfeeding actions.</ul>"
			+"<ul>All tail-anal actions. Fixed several (long-standing) errors in these actions, such as incorrect references to vaginas and some actions being incorrectly blocked for subs. Expanded tail-pegging reaction descriptions.</ul>"
			+"<ul>All tail-vaginal actions. Improved and expanded several old descriptions.</ul>"
			+"<ul>All vaginal sex actions.</ul>"
			+"<ul>All anal sex actions.</ul>"
			+"<ul>All blowjob sex actions. Fixed several old errors in descriptions, and changed the 'deep throat' actions to be independent of pace.</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Fixed issues with stocks sex not working. (CognitiveMist)</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Improved initial penetration descriptions.</ul>"
			+"<ul>Added 'rose gold' and 'steel' colours to fur and scale dye colours.</ul>"
			+"<ul>Added indication in skin/fur/hair dying menus for metallic colours.</ul>"
			+"<ul>Added metallic colours to feather dyes.</ul>"
			+"<ul>You can now resist after choosing to submit upon combat victories.</ul>"
			+"<ul>Changed big cat icons.</ul>"
			+"<ul>Changed Wolf Whiskey's description.</ul>"
			+"<ul>Added harpy subspecies 'bald-eagle'. (Dark brown feathers on body, with white head feathers.)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed cause of bug where walking into a milking room shows the correct management UI, but the PC stays out of the room on the map.</ul>"
			+"<ul>Typo/parsing fixes.</ul>"
			+"<ul>Fixed dirty talk referring to tits when the character was flat-chested.</ul>"
			+"<ul>Fixed Amber having the wrong foot fetish.</ul>"
			+"<ul>Fixed bug in sex where some sex actions were not being added (mainly affected kissing actions).</ul>"
			+"<ul>Fixed being able to equip tail/horn/wing clothing if you didn't have a tail/horns/wings.</ul>"
			+"<ul>Fixed penetration description errors.</ul>"
			+"<ul>Fixed metal buttplugs to actually use metal colours.</ul>"
			+"<ul>Added checks for enchantment loading to handle cases of old saves having clothing enchanted with the old broodmother/seeder fetishes.</ul>"
			+"<ul>Strapless dildo now correctly requires the wearer to have a vagina.</ul>"
			+"<ul>Clothing that plugs vagina/anus/nipples now correctly blocks those orifices from being used.</ul>"
			+"<ul>Fixed sex stats being inverted (e.g. performing vaginal sex was counting as receiving vaginal sex in stats).</ul>"
			+"<ul>Fixed some fox-morph descriptions referencing wolf parts.</ul>"
			+"<ul>Fixed facesitting positioning action being available even while facesitting is ongoing.</ul>"
			+"<ul>Fixed cultists being able to spawn in disliking/hating using their cock.</ul>"
			+"<ul>Fixed position changing not working in stocks scene.</ul>"
			+"<ul>Fixed NPCs locked in stocks (and milking stalls) being able to perform actions with their hands.</ul>"
			+"<ul>Fixed stocks (and milking stalls) not having anilingus options.</ul>"
			+"<ul>Fixed being able to perform actions in the cultist's 'sealed' sex scene.</ul>"
			+"<ul>Fixed both cat-morph hair types having the same TF name.</ul>"
			+"<ul>Fixed kissing actions not being available if your partner was the one to initiate kissing.</ul>"
			+"<ul>Fixed breast groping action not applying milking effects to the correct character.</ul>"
			+"<ul>Characters no longer need breasts in order to be milked (they just need to be producing milk).</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.8.1</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Finished all descriptions in the new club area, and improved several actions.</ul>"
			+"<ul>Added more options to the club, allowing you to save characters you meet in order to see them there again.</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Increased maximum age of randomly spawned NPCs from 26 to 38.</ul>"
			+"<ul>NPCs will no longer insist on stopping kissing before penetrating in sex.</ul>"
			+"<ul>Improved elixir and potion descriptions, and increased base prices from 1000 to 1500 and 500 to 750, respectively.</ul>"
			+"<ul>Foxes now spawn in with red cocks.</ul>"
			+"<ul>Added all feather colours to angel wings. (You can still only get angel parts through the debug menu. Type 'buggy' during gameplay to bring it up.)</ul>"
			+"<ul>Added self-legs and self-groin as orgasm targets.</ul>"
			+"<ul>Added harpies, alligator-morphs, and rat-morphs to the subspecies found in the nightclub, and removed Youko. (Youko are special subspecies, and will be found only in special areas in the Fields.)</ul>"
			+"<ul>Removed 'freckled' pattern from tongue.</ul>"
			+"<ul>Fixed NPCs spawning in hating some fetishes which are related to fetishes they already have.</ul>"
			+"<ul>Slightly improved self-taking virginity text.</ul>"
			+"<ul>Demonic hoofs now prevent you from wearing footwear.</ul>"
			+"<ul>Biojuice canister's corruption increase is now permanent.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed sexual area exposed descriptions not being shown at start of sex.</ul>"
			+"<ul>Fixed handjob orgasms causing sex scenes to break.</ul>"
			+"<ul>Fixed hooded cloak's 'Pull Up' displacement not revealing lower body clothing slots.</ul>"
			+"<ul>Fixed Kalahari generating a new outfit after sex.</ul>"
			+"<ul>Fixed closing time text not displaying correctly in the club.</ul>"
			+"<ul>Fixed incorrect descriptions for buying your partner a drink in the club.</ul>"
			+"<ul>Fixed several cases of 'command_unknown' being displayed in (and out of) sex.</ul>"
			+"<ul>Fixed tooltip descriptions being very broken in sex.</ul>"
			+"<ul>Fixed some incorrect labels on actions in sex (such as penis+vagina being labelled as 'Tail-fucked', and cunnilingus being labelled as 'anilingus').</ul>"
			+"<ul>Fixed clitoris girth and TF modifiers sometimes not working.</ul>"
			+"<ul>Fixed clitoris and vagina modifiers not being saved/loaded correctly.</ul>"
			+"<ul>Fixed issues with chair sex scene positioning.</ul>"
			+"<ul>Fixed broken formatting of ongoing and initial penetration descriptions.</ul>"
			+"<ul>Fixed broken descriptions of areas being exposed during sex.</ul>"
			+"<ul>Minor typo and parsing fixes.</ul>"
			+"<ul>Fixed 'no_clothing_covering_nipples' errors in breast groping action.</ul>"
			+"<ul>Fixed game referring to your breasts in orgasm scene, even if you didn't have breasts.</ul>"
			+"<ul>Fixed 'Beg to stop' action returning incorrect dialogue.</ul>"
			+"<ul>Converted positioning, orgasming, miscellaneous, and dirty talk actions to new format, so parsing bugs in those actions should have been fixed.</ul>"
			+"<ul>Fixed 'self' sex actions not having masturbation fetish associated with them.</ul>"
			+"<ul>Improved clothing concealment method to fix issues such as the one where pulling up someone's skirt wouldn't reveal their swimsuit (even though it should then be visible), if the swimsuit's main slot (chest) was concealed. </ul>"
			+"<ul>(Hopefully) fixed the issue where Nyan, Finch, and the Winter Reindeer wouldn't restock their clothing properly.</ul>"
			+"<ul>Prevented use of tails in breeding stalls to stop the target from blocking their vagina with their tail.</ul>"
			+"<ul>Slaves in the holding cells at slaver alley will no longer be able to attack you in Lilaya's house.</ul>"
			+"<ul>Fixed combat win/loss stats not saving & loading correctly.</ul>"
		+ "</list>"

		+ "<br/>"

		+ "<list>"
			+ "<h6>v0.2.8.2</h6>"
			+"<li>Other:</li>"
			+"<ul>Added ongoing descriptions for handjobs.</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul><b>Fixed:</b> A couple of causes of the game soft-locking upon orgasm.</ul>"
			+"<ul>Fixed 'Resist fingering' being available while fingering your partner's anus.</ul>"
			+"<ul>Fixed tooltips not working correctly in the final action of sex.</ul>"
			+"<ul>Fixed a cause of a lot of parsing errors in the nightclub.</ul>"
			+"<ul>Fixed incorrect ongoing kissing descriptions.</ul>"
			+"<ul>Fixed issue where you'd be unable to move after telling Kruger that you're leaving.</ul>"
			+"<ul>Typo fixes.</ul>"
			+"<ul>Fixed incorrect initial penis penetration parsing.</ul>"
			+"<ul>Fixed incorrect descriptions of having your parts revealed in sex. (Things like 'You lets out a moan as...', instead of 'Lilaya lets out a moan as...')</ul>"
			+"<ul>Fixed incorrect descriptions of targeted areas in orgasm tooltips.</ul>"
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
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 2));
		
		credits.add(new CreditsSlot("Daniel D Magnan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Neximus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Suvarestin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Garkylal", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Apthydragon", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Mylerra", "", 0, 0, 2, 0));
		
		credits.add(new CreditsSlot("Blackheart", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Steph", "", 0, 0, 1, 0));

		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 1, 0));
		
		
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
				
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), 1, null, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

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
