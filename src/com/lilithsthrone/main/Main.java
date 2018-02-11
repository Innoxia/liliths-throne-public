package com.lilithsthrone.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
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
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @since 0.1.0
 * @version 0.1.98
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.99",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone!"
		+ "</p>"
			
		+ "<p>"
			+ "I ran into some major unexpected problems (involving the UI and code refactoring) when reworking the stats and perk systems this week (as a precursor to the combat rework), so I lost a lot of time getting all of that sorted."
			+ " As a result, Nyan's content and the fluid mechanics are not done yet, and the combat rework is only half-way done."
			+ " I really am very sorry that I didn't manage to get all of that done on time. :("
		+ "</p>"
			
		+ "<p>"
			+ "I will get all of that finished off for 0.2.0, which will be the last update in which I'm working mainly on engine-side stuff."
			+ " Once this combat and stats work is finished, all of the core engine mechanics will be finished, and I'll move on to mainly writing content."
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.98.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Updated to Java 9.0.4. If you're using the .jar version of the game, and are encountering UI bugs, please update your Java to 9.0.4 (.exe versions are unaffected, as they are packaged with 9.0.4).</ul>"
			+"<ul>Save files now only keep track of the last 50 events in the event log. (As only the last 50 are ever displayed within the game, it was pointless to be saving more than this.)</ul>"

			+"<li>Gameplay:</li>"
			+"<ul>Expanded fetish mechanics to tie-in with the lust mechanic in sex. You can now select your 'desire' for each fetish, which affects your lust gains in sex. (Lust affects NPC pace, and when in consensual sex, dominant NPCs stop sex if their lust gets to 0, and submissive NPCs stop it when they drop below 20.)</ul>"
			+"<ul>All NPCs now have their own desires. You can see these by hovering over their 'desires' status effect. (I chose to display these in a easy manner, rather than having you find them out 1-by-1, as I thought 'desire discovery' mechanics would get very tedious.)</ul>"
			+"<ul>Added voyeurist, vaginal, pussy slut, leg lover, and strutter fetishes.</ul>"
			+"<ul>Added new fetishes and desire increases/decreases to the 'mystery kink' item's enchantments. Also organised the fetish order in that menu.</ul>"
			+"<ul>You now earn experience for each fetish-related action both in and out of sex. Higher fetish levels increase arousal gains from those actions in sex, and for the next version I'll make them level the fetish up to provide larger bonuses.</ul>"
			+"<ul>Added the start of stamina drains in sex (although I haven't added exhaustion for when the character reaches 0 yet).</ul>"
			+"<ul>Added the start of alcoholic effects (the rest & psychoactive effects will be finished for the full version).</ul>"

			+"<li>Other:</li>"
			+"<ul>Improved the save/load icons. (I'm sorry they were so poorly thought-out before!)</ul>"
			+"<ul>Added more antler and horn colours.</ul>"
			+"<ul>If you are the dom in a sex scene in which your partner does not have equal control (i.e. a non-con scene), then your partner will not be allowed to remove your clothing by default. (You can still permit them to.)</ul>"
			+"<ul>Taking a shower now cleans 500ml of cum from all orifices, except your mouth (representing cum in stomach), which isn't affected.</ul>"
			+"<ul>Sightly improved formatting of all tooltips.</ul>"
			+"<ul>Added associated fetishes to orgasms.</ul>"
			+"<ul>Added save/load entries to event log.</ul>"
			+"<ul>Added correct fetishes to Lilaya & Rose (I still need to go through all unique NPCs and give them proper likes/dislikes).</ul>"
			+"<ul>Ralph now stocks 25 dye brushes instead of 5. (Wait for his shop to refresh at midnight for him to start stocking 25.)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>Fixed ongoing penetration status effect's incorrect tooltip descriptions.</ul>"
			+"<ul>Typos and parsing fixes (including fixing knotting descriptions).</ul>"
			+"<ul>Fixed incorrect naming for breast shape and horn TF potions.</ul>"
			+"<ul>Loading a game no longer overwrites the activity log.</ul>"
			+"<ul>Your discoveries of NPC genitals are now correctly saved/loaded.</ul>"
			+"<ul>Cumming inside a partner multiple times should no longer result in multiple pregnancy possibilities in the pregnancy stats screen.</ul>"
			+"<ul>The description of losing your penile virginity should now always be appended correctly in sex.</ul>"
			+"<ul>Body parts being exposed in sex should now have the correct descriptions appended.</ul>"
			+"<ul>Fixed bug where the action 'Pull out (chest)' sometimes wouldn't work.</ul>"
			+"<ul>Fixed grey horns and antlers text colour being red.</ul>"
			+"<ul>Fixed Kate's intro referencing three pairs of breasts (she now just has a single pair of breasts).</ul>"
			+"<ul>Fixed clothing remaining dirty after taking a shower.</ul>"
			+"<ul>Fixed some sex actions randomly not being available.</ul>"
			+"<ul>Fixed bug where 'stop X' actions would show up in sex actions for when your partner was using themselves. (e.g. 'Stop fingering' would show up when your partner was fingering themselves.)</ul>"
			+"<ul>Fixed bug where the status message informing you that your clothing was getting dirty would lock up sex scenes.</ul>"
			+"<ul>Fixed penile virginity being reset on every game load (old characters will still incorrectly be penile virgins, but after losing it their non-virgin state will be saved).</ul>"
			+"<ul>NPCs will no longer listen to your pullout/creampie requests if they're the dom in non-con sex.</ul>"
		+ "</list>"

		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.99</h6>"
			+"<li>Gameplay:</li>"
			+"<ul>Increased level cap to 50.</ul>"
			+"<ul>Changed core stats from Strength, Intelligence, Fitness, Corruption + Health, Willpower, Stamina to Strength, Arcane, Lust, Corruption + Energy, Aura. (Removed fitness and stamina stats.) This is still a work-in-progress.</ul>"
			+"<ul>Reworked all attribute-related status effects. (The bonuses you get from raising strength/arcane/corruption.)</ul>"
			+"<ul>Removed level-up points (the points that you'd spend on strength, intelligence, and fitness).</ul>"
			+"<ul><b>Updated:</b> Perk tree mechanics. (I didn't intend for this to be part of the combat update just yet, but it turned out to be necessary in order for other parts of the game to continue to function properly.)</ul>"
			
			+"<li>Sex AI:</li>"
			+"<ul>NPCs that either love or like the Submissive or Dominant fetishes will now start sex in Eager or Rough paces.</ul>"
			+"<ul>NPCs will no longer perform actions that they dislike or hate.</ul>"
			+"<ul>Dominant NPCs will no longer end sex due to having 0 lust. Only submissive NPCs who have equal control (such as in consensual scenes, like Lilaya's), will end sex when they fall into the resisting pace.</ul>"
			+"<ul>NPCs should no longer refrain from performing penetration actions (this was a bug where all NPC preferences were accidentally being removed, leaving them only wanting to perform self-actions).</ul>"
			+"<ul>Fixed a cause of incorrect fetish associations for sex actions (such as your partner being affected by your masturbation actions as though they were the one masturbating), which should help the AI's decision-making process a little.</ul>"
			+"<ul>NPC's anal action preference is now treated the same as all the others. (The fetish desire system has replaced the old method I was using to weigh NPC action preferences.)</ul>"
			+"<ul>NPCs have been told to reign in their obsession with self-actions.</ul>"
			
			+"<li>Other:</li>"
			+"<ul><b>Removed:</b> All references to slimes in the code (as they will be implemented a little differently to how I originally planned). If you've become a slime through the debug menu, you should switch back before loading your character into this version.</ul>"
			+"<ul>Tweaked base height and breast sizes for several races.</ul>"
			+"<ul>Halved the lust drop from performing hated sex actions from -50 to -25.</ul>"
			+"<ul>Succubus attackers and Cultists now have fetishes & desires generated like other NPCs (although they will never dislike or hate performing non-con).</ul>"
			+"<ul>You are now always able to remove NPCs' clothing during sex (although they will still default to not being able to remove yours if they are the sub in non-equal-control sex).</ul>"
			
			+"<li>Contributors:</li>"
			+"<ul>Use correct character for special penetration descriptions. (MissyDirection)</ul>"
			+"<ul>Allow player to stop getting tail-pegged during chair sex. (MissyDirection)</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed a cause of some UI lag (caused by some poor flexbox performance).</ul>"
			+"<ul>Fixed Vicky's sex scene causing a game-breaking bug to occur.</ul>"
			+"<ul>Minor fix to Brax's TF description.</ul>"
			+"<ul>Minor formatting fix to event log to prevent horizontal overflow.</ul>"
			+"<ul>Several typo fixes.</ul>"
			+"<ul>Fixed displacement text for an NPC's nursing bra being pulled down.</ul>"
			+"<ul>Fixed bug with t-shirt displacement causing a major crash to occur.</ul>"
			+"<ul>'Cummy meal' status effect no longer continuously makes your mouth dirty.</ul>"
			+"<ul>Fixed resisting NPCs casually commenting on your cock size.</ul>"
			+"<ul>Fixed 'Anal tease' sex action applying incorrect lubrication transfers.</ul>"
			+"<ul>NPCs who hate/dislike non-con should now never use you after combat.</ul>"
			+"<ul>Fixed incorrect inflation descriptions.</ul>"
			+"<ul>You can no longer set your skin to be glowing in the character creation.</ul>"
			+"<ul>Fixed incorrect virginity loss descriptions for succubus attacker.</ul>"
			+"<ul>Fixed incorrect breast-related dirty talk.</ul>"
			+"<ul>Fixed self-penetrative dirty talk referencing your partner.</ul>"
			+"<ul>You can no longer help yourself to Ralph's condoms in his sex scene.</ul>"
			+"<ul>Extra piercings will no longer spawn in the character creation's inventory when repeatedly going to check clothing and back.</ul>"
			+"<ul>Fixed potions displaying '+X Attribute' twice when drunk.</ul>"
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

		credits.add(new CreditsSlot("Anonymous", "", 0, 6, 111+61, 37+20));
		
		
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 3));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 3));
		
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 3, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 2, 0));
		
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 5));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 2));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 7, 0));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
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
				if (Main.getProperties().lightTheme)
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

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
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
						if (Main.getProperties().lightTheme)
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.EMPTY, PlaceType.GENERIC_MUSEUM));

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
			if(Integer.valueOf(v1[0]) < Integer.valueOf(v2[0])) {
				return true;
			}
			
			if(Integer.valueOf((v1[1].length()==1?v1[1]+"0":v1[1])) < Integer.valueOf((v2[1].length()==1?v2[1]+"0":v2[1]))) {
				return true;
			}
			
			if(Integer.valueOf((v1[2].length()==1?v1[2]+"0":v1[2])) < Integer.valueOf((v2[2].length()==1?v2[2]+"0":v2[2]))) {
				return true;
			}
			
			if(v1.length<4) {
				if(v2.length<4) {
					return false;
				} else {
					return true;
				}
			}
			if(v2.length<4) {
				return false;
			}
			
			if(Integer.valueOf((v1[3].length()==1?v1[3]+"0":v1[3])) < Integer.valueOf((v2[3].length()==1?v2[3]+"0":v2[3]))) {
				return true;
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
			
		} else if (Main.game.getCurrentDialogueNode().getMapDisplay()!=MapDisplay.NORMAL) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Can only quicksave in a normal scene!");
			
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
		return Main.game.isStarted() && Main.game.getSavedDialogueNode() == DebugDialogue.getDefaultDialogueNoEncounter();
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
			properties.race = game.getPlayer().getRace().getName();
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
				Main.getProperties().setNewWeaponDiscovered(false);
				Main.getProperties().setNewClothingDiscovered(false);
				Main.getProperties().setNewItemDiscovered(false);
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
