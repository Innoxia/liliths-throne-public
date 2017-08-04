package com.base.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.base.controller.MainController;
import com.base.game.Game;
import com.base.game.Properties;
import com.base.game.character.CharacterUtils;
import com.base.game.character.NameTriplet;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.story.CharacterCreationDialogue;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.utils.CreditsSlot;
import com.base.utils.Util;
import com.base.world.Generation;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

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
 * @version 0.1.82
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene, menuScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public final static String VERSION_NUMBER = "0.1.82",
			VERSION_DESCRIPTION = "Early Alpha"; //TODO Preview

	public final static Image WINDOW_IMAGE = new Image("/com/base/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>This is a preview release, and contains half-finished content!</b></h6>"
		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha release!</b></h6>"
		
		+ "<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
//		+ "<p>"
//		+ "This is just a preview release, so <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>only play it if you're prepared to encounter some serious bugs and half-written content!</b>"
//		+ " The full, polished version of this release will be out on Friday!"
//		+ "</p>"
		
		/*
		 * 
		I've got everything totally finished in the engine for romance and slavery now! Although I managed to achieve all of the sub-goals for this version, I didn't manage to get the main two done in time (Alexa content and save-game compatibility).
		I really want to move on to writing content, but bugs and other minor engine stuff keeps on cropping up.
		That being said, I think most things are now in a stable enough state, so I'll be able to spend the entire of next week solely focusing on writing Alexa, incest, and generic NPC content. (And work on the save-game compatibility.)
		I will have NPC slavery and romance done for next Friday!
		 */
		
		+ "<p>"
			+ "Hello once again everyone! ^^"
		+ "</p>"
		+ "<p>"
			+ "Yet again I find myself wishing that there was more time in the week. Although I managed to achieve all of the sub-goals for this version, I didn't manage to get the main two done in time (Alexa content and save-game compatibility)."
			+ " I really, <i>really</i> want to get stuck in with writing content, but there are so many bugs and other minor things to get sorted out first."
			+ " That being said, I think most things are now in a stable enough state, so I'll be able to spend the entire of next week solely focusing on writing Alexa, incest, and generic NPC romance content. (And work on the save-game compatibility.)"
		+ "</p>"
			
		+ "<p>"
			+ "<b>I'm aiming to have Alex, incest and generic NPC content done for next Friday!</b>"
		+ "</p>"
		
		+ "<p>"
			+ "A few notes on this version's content:</br>"
			+ "The character export/import is still really buggy. I'm so sorry that this has been an issue for so long, and I'm going to get this fixed for next Friday as my number 1 priority.</br>"
			+ "The incest content is still very very rough at the moment, but I've fully got the affection and slavery mechanics finished engine-side now, so it will all be written up for Friday!</br>"
			+ "I've managed to shrink my bug list by a tiny amount, but there are still quite a few left to fix!</br>"
		+ "</p>"
			
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
		
		+ "<list>"
		+ "<h6>Patch Notes - <b>Version " + Main.VERSION_NUMBER + "</b></h6>"
		
		+"<li>Engine:</li>"
			+"<ul>Finished implementing affection and obedience mechanics. (For romance and slavery content.)</ul>"
		
		+"<li>Gameplay:</li>"
			+"<ul><b>Changed:</b> Split up anal, oral, and breast fetishes into giving and receiving variants, with associated tease attacks.</ul>"
			+"<ul><b>Added:</b> Lusty maiden fetish. Derived fetish from having virginity, buttslut, oral performer, and breasts fetishes.</ul>"
			+"<ul><b>Added:</b> A lore book for each of the game's races, which each have a chance to be randomly dropped as loot after combat.</ul>"
			+"<ul>Added Lilaya's Library text, as well as entries for the new lore books (once they've been found).</ul>"
			+"<ul>Added sexual orientation for the player (gynephilic, androphilic, ambiphilic). This can be changed in the fetishes screen at any time. (I'm going to do some more work on this for the next version.)</ul>"
		
		+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Scarf (no femininity requirements, neck slot).</ul>"
			+"<ul><b>Added:</b> Cargo trousers (masculine, leg slot).</ul>"
		
		+"<li>Sex:</li>"
			+"<ul><b>Added:</b> Dominant kneeling position (for both player and NPCs).</ul>"
			+"<ul><b>Added:</b> Nipple penetration sex groups. (Player-Penis in Partner-Nipple, and Partner-Penis in Player-Nipple). Can be used in kneeling and 69 positions.</ul>"
			+"<ul>Slightly improved AI in sex so that they shouldn't keep stopping/starting penetrations.</ul>"
			+"<ul>Slightly improved NPC's sex position selection.</ul>"
			+"<ul>Added 'Request nipple-sex' as an action during sex.</ul>"
		
		+"<li>UI:</li>"
			+"<ul>Made perks slightly easier to distinguish between owned and unowned. (I'm going to do a big rework of this screen soon.)</ul>"
			+"<ul>Slightly improved the fetishes screen.</ul>"
			+"<ul>Added a 'Reset' action to the fetish screen, allowing you to reset your fetishes and receive a refund of half of their total arcane essence cost.</ul>"
			+"<ul>Added an 'average stats' panel to the race screen in your encyclopedia.</ul>"
			+"<ul>Added a credits screen, accessible from the main menu.</ul>"
			+"<ul>Expanded NPC character view UI to show affection and obedience.</ul>"
		
		+"<li>Other:</li>"
			+"<ul>Fetishes have been slightly reworked. Instead of the first fetish being free, you now start the game with 5 arcane essences. Fetishes that unlock content (Transformation and Non-con fetishes) are free.</ul>"
			+"<ul>Renamed 'City gym' to 'Pix's Playground'.</ul>"
			+"<ul>Improved dialogue flow in all of the shops in the Shopping Arcane. (They now all have an 'exterior' scene.)</ul>"
			+"<ul>Split encyclopedia entries for race into 'basic' and 'advanced' knowledge. You gain basic knowledge upon encountering a race, and advanced knowledge upon reading a book about that race.</ul>"
			+"<ul>Changed kissing dirty talk.</ul>"
			+"<ul>Exposed breasts status effect is now applied if your chest is exposed while having a feminine body, even if your breasts are flat.</ul>"
			+"<ul>Unique NPC's clothing is no longer able to be stolen.</ul>"
			+"<ul>Balls size now affects how strangers assume your gender. (If you have huge or larger balls, the bulge makes it obvious that you have a cock.)</ul>"
			+"<ul>If an NPC has the non-con fetish, they will no longer have reduced arousal gains when they resist in sex. (I'm treating the non-con fetish for NPCs as them loving it either way.)</ul>"
			+"<ul>Potion effects status effect can now be increased by drinking successive potions (+30 minutes for each effect), up to a maximum of 6 hours. (I'll rework this a little more for the next version.)</ul>"
		
		+"<li>Bugs:</li>"
			+"<ul>Fixed quite a few typos and parsing errors.</ul>"
			+"<ul>Fixed some incorrect penis descriptions in doggy-style orgasm scenes.</ul>"
			+"<ul>You now correctly start with 40 flames if you skip prologue.</ul>"
			+"<ul>Exhibitionists will now wear revealing clothing in their groin/chest slots.</ul>"
			+"<ul>Fixed actions being duplicated in Brax's dominant sex scene.</ul>"
			+"<ul>Fixed Arcane essence bottle icon.</ul>"
			+"<ul>Fixed incorrect descriptions in gentle self-fucking in the back-to-wall position.</ul>"
			+"<ul>Fixed incorrect options after defeating a harpy in combat.</ul>"
			+"<ul>Fixed some actions not being greyed-out when they're unavailable. (Such as the 'Stroke Pussy' action.)</ul>"
			+"<ul>Fixed bug that was causing your partner to struggle with removing underwear in sex.</ul>"
			+"<ul>Fixed bugs related to not knowing what an NPC's nipples look like.</ul>"
			+"<ul>You should now be able to permanently increase your oral sex skills (by sucking large cocks :3).</ul>"
			+"<ul>You should now start sex with your orifices already wet, where applicable (e.g. if you have a drooling vagina, you'll now be wet on the first turn of sex).</ul>"
			+"<ul>Succubi should now re-dress themselves after having their clothing stolen.</ul>"
			+"<ul>Fixed a lot of incorrect clothing removal descriptions.</ul>"
			+"<ul>Fixed bug where virginity loss by succubus tail would return a virginity loss by penis description.</ul>"
			+"<ul>Fixed NPC giving eager descriptions when genitalia was revealed in sex.</ul>"

		+ "<h6>Added in the Preview:</b></h6>"
		
		+"<li>Gameplay:</li>"
			+"<ul><b>Added:</b> 25% Chance for an attacker in dominion's alleyways to be one of your children. Requires having the incest fetish, as well as having had children (duh! ^^).</ul>"
			+"<ul><b>Added:</b> Framework for incest encounter affection progression mechanics. The current dialogue for incest encounters is heavily WIP, but will be a lot more polished for the full release next Friday!</ul>"
			+"<ul><b>Changed:</b> Children's race no longer depends on their mother's reproductive system. Instead, half of a mother's children will be of her race, the other half will be of the father's race.</ul>"
			+"<ul><b>Added:</b> Essence extraction ability in Lilaya's lab (unlocked after talking to her about essences). You can now extract your essences in the lab, then sell them to Vicky or Ralph!</ul>"
			+"<ul><b>Added:</b> Tail penetration action groups for vagina and anus. (PCTail-NPCvagina, PCTail-NPCAnus, NPCTail-PCvagina, NPCTail-PCAnus)</ul>"
			+"<ul><b>Added:</b> Non-consent fetish. I wanted to use the fetishes to gate content, rather than adding options for that in the main menu, so <b>non-consensual sex is now unlocked when you take the non-consent fetish</b>.</ul>"
			+"<ul><b>Removed:</b> Non-con option. The non-consent fetish is now the way to unlock non-consensual sex scenes!</ul>"
			+"<ul>Reworked some of Lilaya's lab content to make it a little smoother.</ul>"
			+"<ul>Added fingering and masturbation options to the 'standing' sex position.</ul>"
		
		+"<li>Items:</li>"
			+"<ul><b>Added:</b> Vanilla water (Human attribute consumable, similar to the other attribute consumables.)</ul>"
			+"<ul><b>Added:</b> Bottled essences. You can now buy bottled essences from Vicky.</ul>"
			+"<ul><b>Added:</b> Crotchless briefs. (Masculine, groin slot.)</ul>"
			+"<ul><b>Added:</b> Work boots. (Masculine, foot slot.)</ul>"
			
		+"<li>UI:</li>"
			+"<ul><b>Added:</b> A list of all your offspring to the pregnancy stats screen.</ul>"
			+"<ul><b>Expanded:</b> 'Characters present' and 'Phone contacts' screens to show NPC's status effects and a table of their stats.</ul>"
			
		+"<li>Other:</li>"
			+"<ul>Added a 'Buy all' action in the trader menu.</ul>"
			+"<ul>Added a 'Use all' action for items in your inventory.</ul>"
			+"<ul>Kate and Nyan will both now identify and purchase all clothing items (including jewellery).</ul>"
			+"<ul>Buffed the completed Enforcer set's status effect, as well as buffing the individual pieces of clothing.</ul>"
			+"<ul>Minor improvements to random harpy dialogue to take into account if she wants sex or not after combat.</ul>"
		
		+"<li>Bugs:</li>"
			+"<ul>Fixed several incorrect names showing in Lexi's and Diana's nests.</ul>"
			+"<ul>Fixed Lexi's post-fight-loss forced transformation (occurs if you have the TF fetish) turning you into a bimbo. It now correctly applies the 'Harpy Nymphomaniac' transformation.</ul>"
			+"<ul>Fixed incorrect sell amounts being displayed when you chose to 'Sell all'.</ul>"
			+"<ul>Minor typo fixes.</ul>"
			+"<ul>Fixed instances of cock tease over pussy/ass generating incorrect speech.</ul>"
			+"<ul>Fixed several major typos in partner's penis-vagina & penis-anus sex actions.</ul>"
			+"<ul>Fixed some bugs in succubus attacker's sex scene.</ul>"
			+"<ul>Fixed bug where random Harpies would not be able to be re-encountered after completing the 'Angry Harpies' quest.</ul>"
			+"<ul>Disabled quicksave while in phone menus, as it was causing a save game file corruption.</ul>"

		+ "<h6>Hotfix</h6>"
	
			+"<li>Clothing:</li>"
			+"<ul><b>Added:</b> Headband (No femininity requirements, head slot). Also added a 'headband with bow' variant (feminine, head slot).</ul>"
			+"<ul><b>Added:</b> Long-sleeved dress (feminine, torso slot).</ul>"
			+"<ul><b>Added:</b> Shin guards (No femininity requirements, ankle slot).</ul>"
			+"<ul><b>Added:</b> Stomach sarashi (No femininity requirements, stomach slot).</ul>"
			+"<ul><b>Added:</b> Chest sarashi (No femininity requirements, chest slot).</ul>"
			
			+"<li>Other:</li>"
			+"<ul>Removed harpy height limitation. (This was a left over from a very early version of the game.)</ul>"
			+"<ul>Slightly increased the arousal reduction on 'Calm down' action.</ul>"
			+"<ul>Humans now have a chance to drop Vanilla Water.</ul>"
			+"<ul>There is now a chance for NPCs to have three fetishes. (20% chance for three, 20% chance for two, 40% chance for one, 20% chance for none)</ul>"
			+"<ul>Added 'Permit/Forbid self clothing' actions in sex, to allow you to forbid your partner from managing any of their own clothes.</ul>"
			+"<ul>Vicky now stocks a lot more essences.</ul>"
			+"<ul>Rare clothing should now be spawning in correctly (10% chance for every random piece of clothing to have a rare enchantment).</ul>"
			
			+"<li>Bugs:</li>"
			+"<ul>Fixed bug where almost everyone would resist if you have the non-con fetish.</ul>"
			+"<ul>Minor typo fixes.</ul>"
			+"<ul>Fixed several old bugs from the sex system rework (could have been causing orifice stretching issues).</ul>"
			+"<ul>Fixed bug where fathered daughters wouldn't count in the pregnancy stats screen.</ul>"
			+"<ul>Fixed offspring description giving incorrect mother/father name.</ul>"
			+"<ul>Fixed Harpy Perfume tooltip incorrect effect description.</ul>"
			+"<ul>After meeting your offspring, their name should now correctly change from 'Unknown' in the pregnancy stats screen.</ul>"
			+"<ul>Added NPC variants for consuming Mother's Milk, Promiscuity Pills, and Vixen's Virility.</ul>"	
			
			+ "</list>";
	
	public static String disclaimer = "<h1 style='text-align:center;'>DISCLAIMER</h1>"
			+"<h6 style='text-align: center; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>You must read and agree to the following in order to play this game!</h6>"

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
	private static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("A.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Pierrre Mura", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("A. K.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("N.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("M. S.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Rohise", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 1));
		
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("P. H.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("48days", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("L.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S. D", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("T.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("J. B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("W. B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("F. F. 2. M.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("T. D. Y. K.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		
		
		Collections.sort(credits, new Comparator<CreditsSlot>() {
			public int compare(CreditsSlot left, CreditsSlot right) {
		    	return left.getName().toLowerCase().compareTo(right.getName().toLowerCase());
			}
		});
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));

		Pane pane = (Pane) loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
		}

		mainController = loader.<MainController> getController();

		Main.primaryStage.setScene(mainScene);
		
		Main.primaryStage.show();
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = (Pane) loader.load();
				Main.mainController = loader.<MainController> getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().lightTheme)
					Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
				else
					Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
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
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		Main.game.setPlayer(new PlayerCharacter("Player", "", 1, Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.CITY, Dominion.CITY_AUNTS_HOME));

		// Generate world:
		if (!(gen == null))
			if (gen.isRunning()) {
				gen.cancel();
			}

		gen = new Generation(WorldType.DOMINION);

		gen.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
				Pane pane;
				try {
					if (Main.mainScene == null) {
						pane = (Pane) loader.load();
						Main.mainController = loader.<MainController> getController();

						Main.mainScene = new Scene(pane);
						if (Main.getProperties().lightTheme)
							Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_ENTRANCE_HALL));

				Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_ENTRANCE_HALL, false);
				Main.game.initNewGame(startingDialogueNode);
				
				Main.game.endTurn(0);
				//Main.mainController.processNewDialogue();
			}
		});
		new Thread(gen).start();
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
			saveGame("QuickSave", true);
		}
	}

	public static void quickLoadGame() {
		loadGame("QuickSave");
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
		if (!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9' '_]*")) {
			Main.game.flashMessage(Colour.GENERIC_BAD, "Incompatible characters!");
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/saves");
		dir.mkdir();
		
		boolean overwrite = false;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("lts")) {
						if (child.getName().equals(name+".lts")){
							if(!allowOverwrite) {
								Main.game.flashMessage(Colour.GENERIC_BAD, "Name already exists!");
								return;
							} else {
								overwrite = true;
							}
						}
					}
				}
			}
		}
		
		File file = new File("data/saves/"+name+".lts");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (file != null) {
			try {
				   
			    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			      oos.writeObject(Main.game);
			      oos.close();
			    }

				properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
				properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininity()).getColour().toWebHexString();
				properties.name = game.getPlayer().getName();
				properties.level = game.getPlayer().getLevel();
				properties.money = game.getPlayer().getMoney();
				properties.race = game.getPlayer().getRace().getName();
				properties.quest = game.getPlayer().getMainQuest().getName();

				properties.savePropertiesAsXML();
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			if(overwrite) {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Save game overwritten!");
			} else {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game saved!");
			}
		} else if(name.equals("QuickSave")){
			Main.game.flashMessage(Colour.GENERIC_GOOD, "Quick saved!");
		}
	}

	public static void loadGame(String name) {
		File file = new File("data/saves/"+name+".lts");
		
		if (file.exists()) {
			try {
				Main.game = new Game();
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Game loadedGame = (Game) ois.readObject();
				ois.close();
				fin.close();
				Main.game = loadedGame;
				Main.mainController.renderMap();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game loaded!");
				if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS) {
					Main.mainController.openOptions();
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(Colour.GENERIC_BAD, "File not found...");
		}
	}
	
	public static void deleteGame(String name) {
		File file = new File("data/saves/"+name+".lts");

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
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("lts"))
						filesList.add(child);
				}
			}
		}
		
//		Collections.sort(filesList, (a, b) -> b.getName().compareTo(a.getName()));
		Collections.sort(filesList, (e1, e2)->{return e1.lastModified()>e2.lastModified()?-1:(e1.lastModified()==e2.lastModified()?0:1);});
		
		return filesList;
	}
	
	public static List<File> getCharactersForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (Util.getFileExtention(child.getName()).equals("xml"))
						filesList.add(child);
				}
			}
		}
		
		Collections.sort(filesList, (a, b) -> b.lastModified() > a.lastModified() ? 1 : -1);
		
		return filesList;
	}
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file));
				
				Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
				Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_HOODIE, false), true, Main.game.getPlayer());
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.game.getPlayer().setNewWeaponDiscovered(false);
				Main.game.getPlayer().setNewClothingDiscovered(false);
				Main.game.getPlayer().setNewItemDiscovered(false);
				Main.game.getPlayer().getItemsDiscovered().clear();
				Main.game.getPlayer().calculateStatusEffects(0);

				Main.game.initNewGame(CharacterCreationDialogue.START_GAME_WITH_IMPORT);
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
