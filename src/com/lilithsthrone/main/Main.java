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
 * @version 0.1.97
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.97.5",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Buggy Preview!</b></h6>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b></h6>"
		
		"<p>"
			+ "Hello everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "I'm very close to finishing the new sex & orgasm content now - just another few days and it will be all done! I'll then move on to combat & Nyan work. :3"
		+ "</p>"
		
		+ "<p>"
			+ "Thank you all for playing Lilith's Throne! And a very big thank you to all the people supporting me on Patreon!"
			+ " If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
			+ "<h6>v0.1.97.5</h6>"
			+"<li>Engine:</li>"
			+"<ul>Finished converting the save system to .xml. All saves now use the new .xml system, so you won't need to export/import between versions anymore.</ul>"
			+"<ul>Optimised NPC load method, so loading a game should now be faster.</ul>"
			+"<ul>Added support for conditional parsing commands to the parsing engine. I'll add documentation for this soon.</ul>"
			+"<ul>Finished refactor of all sex engine code to properly support multiple-partner sex scenes.</ul>"

			+"<li>UI:</li>"
			+"<ul>Updated Save/Load UI, and changed Export/Import to exported character management screen.</ul>"
			+"<ul>Updated inventory slot positions. (This should now be the final layout.)</ul>"

			+"<li>Sex:</li>"
			+"<ul>NPCs should no longer switch positions just before they orgasm.</ul>"
			+"<ul>NPCs will no longer instantly re-penetrate themselves on the same turn that you forbid or stop all self-penetrative actions.</ul>"
			+"<ul>Reworked all back-end code for generic orgasms, ready to write in more detailed content for the full release.</ul>"
			+"<ul>Added 'cum on area' orgasm options, to allow you to cum on a specific area of your partner's body. (Not completely finished yet.)</ul>"

			+"<li>Other:</li>"
			+"<ul>Demons now only have 1 pair of breasts by default. (I originally intended this to be the case, but I ended up giving them 3 a long time ago to test multiple-breast rows, and forgot to change it back.)</ul>"
			+"<ul>Improved corruption descriptions to reflect the fact that corruption is a measure of perversion.</ul>"
			+"<ul>Offspring are now permanently removed from the encounterable NPC list when you tell them to leave. (I'll add a way to manually find them again in the future.)</ul>"
			+"<ul>Penis, anus, nipple, and vagina sex actions (such as 'Finger him/her' and 'Stroke his/her cock') are now completely hidden until you discover if the NPC has the correct genitals/nipples/anus to perform those actions on.</ul>"
			+"<ul>Removed old lore reference to the arcane making everyone bi-sexual (this was the case before I added sexual orientations to the game).</ul>"
			+"<ul>Moved pregnancy lore information out of the phone's 'Pregnancy stats' page and into a book in Lilaya's library.</ul>"
			+"<ul>Forced TFs will now increase your cum production, where appropriate.</ul>"
			+"<ul>Gynephilic NPCs will now prefer to forcibly TF you into a female, unless they have the 'pregnancy' or 'broodmother' fetishes, in which case they will prefer to TF you into a futanari, shemale, or trap.</ul>"
			+"<ul>Added a masochistic/cum addict variation of the 'dirty clothes' status effect.</ul>"
			+"<ul>Added a 'dirty body' status effect (with matching masochistic/cum addict variation), which is applied if any of your body parts are covered in cum. (Can be removed by washing in your room.)</ul>"

			+"<li>Contributors:</li>"
			+"<ul>Added Linux font compatibility (added Carlito as an alternative font for Calibri). (by PyrophoricPlumage)</ul>"
			+"<ul>Fixed incorrect colour reference. (by CognitiveMist)</ul>"
			+"<ul>Numerous performance improvements. (by CognitiveMist)</ul>"
			+"<ul>Addressed some control flow & code style items. (by CognitiveMist)</ul>"
			+"<ul>Fixed striped leggings, panties, and bra using incorrect colour variables. (by Tukaima)</ul>"

			+"<li>Bugs:</li>"
			+"<ul>NPC orgasms should now function correctly (that is, they should now cum inside when they're supposed to).</ul>"
			+"<ul>Typo fixes (some minor ones in tooltips, changing 'reindeer morph' to be hyphenated like other races, and several other small fixes).</ul>"
			+"<ul>Imported slaves at the auction block no longer spawn in as being pregnant.</ul>"
			+"<ul>Fixed bugs related to Finch's ownership of slaves. (He should now correctly lose ownership of slaves you purchase from the auction block.)</ul>"
			+"<ul>Fixed incorrect penis reveal dialogue (where the NPC would compare your penis size to their own, non-existent penis).</ul>"
			+"<ul>Fixed enchanting bug where crafting a potion to change a certain body type (primarily horns) would then lock out transformation options for other racial potions.</ul>"
			+"<ul>Fixed hypno-watch effect description returning 'command_unknown'.</ul>"
			+"<ul>Slightly changed piercing slot descriptions to reflect whether or not your character knows what the NPC's genitals/nipples look like.</ul>"
			+"<ul>Nyan's inventory is now correctly saved/loaded.</ul>"
			+"<ul>Multiple partner sex scenes should no loner suddenly lose one of the NPC partners.</ul>"
			+"<ul>Fixed major game-freezing bug where if you were on the receiving end of penetrative non-consensual sex, and your partner reached orgasm but you did not, you would get stuck on the 'receive creampie' and 'request pullout' options.</ul>"
			+"<ul>Fixed duplicate actions showing up in Kate's and Lilaya's sex scenes.</ul>"
			+"<ul>When starting from a character import, the flag that sets your having met Brax is now correctly reset.</ul>"
			+"<ul>Fixed minor bug in gender appearance method (masculine characters with no genitals are no assumed to be male if their groin is concealed).</ul>"
			+"<ul>Added correct description variants for NPCs when consuming the harpy matriarchs' special items.</ul>"
			+"<ul>Fixed bug where you could perform anilingus and cunnilingus at the same time. (Also fixed a related bug where multiple penis-related sex actions could be performed.)</ul>"
			+"<ul>Fixed four and five rows of breasts always being described as three.</ul>"
			+"<ul>Fixed issue where in some screens/dialogue/tooltips, incorrect NPC names or stats would be displayed.</ul>"
			+"<ul>Can no longer pull up T-shirt from under concealing/blocking clothing.</ul>"
			+"<ul>NPCs will no longer be stuck at having a fraction of their health, willpower, and stamina.</ul>"
			+"<ul>Vicky's post-sex scene will now correctly no longer reference Arthur's package after you've completed that section of the side-quest.</ul>"
			+"<ul>Importing a game that was exported while the difficulty was set to anything above 'Human' will no loner lock all NPC's levels to being double that of yours.</ul>"
			+"<ul>Slightly improved genetics/inheritance method, so that offspring's ass & hip sizes (and probably a few other things) will now more closely resemble that of their parents.</ul>"
			+"<ul>Fixed bugged arousal gain values in sex.</ul>"
			+"<ul>Winter event Reindeer-morphs will no longer spawn in as human (if your furry settings are set to 'Human', then they'll spawn in as minor Reindeer-morphs).</ul>"
			+"<ul>Reindeer overseers and cultist chapels should now both be able to be accessed if they are on the same tile.</ul>"
			+"<ul>'Spread pussy' action will now transfer lubrication between fingers & vagina.</ul>"
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
		

		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Dan", "", 0, 1, 0, 2));
		credits.add(new CreditsSlot("Ash", "", 0, 1, 0, 2));
		
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Casper &quot;Cdaser&quot; D.", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Cryostorm", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Brandon Stach", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 2, 0));

		credits.add(new CreditsSlot("Zakarin", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 2, 0));

		credits.add(new CreditsSlot("Endness", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Aleskah", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("The Void Prince", "", 0, 0, 1, 0));
		
		
		
		
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 4));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 4, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Baz GoldenClaw", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 4));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Destont", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Grakcnar", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Hedgehog", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Chris Turpin", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Vaddex", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Nick LaBlue", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Saladine the Legendary", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 5));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Sig", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Silentark", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Tanall", "", 0, 1, 3, 0));
		credits.add(new CreditsSlot("Tanner D.", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Antriad", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 5, 1));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 6, 0));
		
		
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
