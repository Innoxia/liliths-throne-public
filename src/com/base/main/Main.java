package com.base.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.base.controller.MainController;
import com.base.game.Game;
import com.base.game.Properties;
import com.base.game.character.CharacterUtils;
import com.base.game.character.NameTriplet;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.story.CharacterCreation;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.utils.CreditsSlot;
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
 * @version 0.1.84
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.84.1",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/base/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha release!</b></h6>"
		
		+ "<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
		+ "<p>"
			+ "Hello again everyone! ^^"
		+ "</p>"
			
		+ "<p>"
			+ "I didn't manage to achieve all of my goals this week, and I feel particularly guilty about not getting the basic slavery mechanics into the game."
			+ " I also forgot to make a mid-week progress report, so I'm really sorry about that as well."
			+ " To try and make it up to you, I'll work on getting paizuri (breast fucking) added to the game over the weekend, and I'll (hopefully) release a hotfix with that in it by Monday."
		+ "</p>"
			
		+ "<p>"
			+ "Anyway, in this version, I've managed to add forced transformations, a better UI for Kate's shop (which took far longer than I anticipated,"
			+ " and still has a couple of minor formating bugs), and did some behind-the-scenes work on improving the engine."
			+ " I'm planning on focusing solely on slavery content and bug fixing next week, so there will (finally) be slavery added to the game in the next version!"
		+ "</p>"
			
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"

		+ "</br>"

		+ "<list>"
		
		+ "<h6>Hotfix (v0.1.84.1)</h6>"
		
		+"<li>Gameplay:</li>"
		+"<ul>Slightly tweaked gender preferences for NPC's forced TFs.</ul>"
		+"<ul>You can now use elixirs and potions on your offspring in consensual sex, as well as if you're the dom in non-consensual.</ul>"
		+"<ul>NPCs applying forced transformations will now shrink your cock and reduce your vagina's capacity down to minimum values before removing them.</ul>"
		
		+"<li>Sex:</li>"
		+"<ul>Added: Paizuri and Naizuri. All paizuri actions have three variations; one for breasts larger than B-cups, one for completely flat chests, and another for the other sizes in-between.</ul>"
		+"<ul>Added Paizuri to 69, kneeling, and cow-girl positions.</ul>"
		+"<ul>Added nipple-fucking to cow-girl position.</ul>"
		+"<ul>Very minor change to AI in sex to make them uninterested in getting access to your nipples if your groin is already exposed. (I'm going to massively improve the AI within the next couple of versions.)</ul>"
		
		+"<li>Other:</li>"
		+"<ul>You can now dye Enforcer's clothing to any colour.</ul>"
		+"<ul>Split Nyan's 'Female lingerie' up into 'Female underwear' and 'Female lingerie' to prevent item overflow. (You'll be able to buy enchanted clothing from her again now. :3)</ul>"
		+"<ul>Added squirrel-morph (and cow-morph) potions and essences to Vicky's shop.</ul>"
		+"<ul>Tweaked NPC's forced TF preferences to have a chance to be a related race (i.e. cat morphs may prefer to TF you into a harpy, or dog/wolf-morphs may TF you into a cat-morph).</ul>"
		+"<ul>Added fur highlighting options.</ul>"
		+"<ul>Added afro hair style.</ul>"
		
		+"<li>Bugs:</li>"
		+"<ul>Fixed game freeze once NPC finished transforming you into their preferred body type.</ul>"
		+"<ul>Fixed major bug where items and weapons would sometimes not stack in inventory.</ul>"
		+"<ul>Fixed items showing up in sex as disabled, even if they were available to be used.</ul>"
		+"<ul>Fixed weapons not having any spells.</ul>"
		+"<ul>Fixed bug where the main quest couldn't be progressed if you started an imported character.</ul>"
		+"<ul>Minor typo fixes.</ul>"
		+"<ul>Fixed parsing bugs (which was causing the parser's 'Commands' action to be unresponsive.)</ul>"
		+"<ul>Vicky will now buy Bottled Squirrel-Morph Essence.</ul>"
		+"<ul>Horn transformations now apply the correct horn type, based on whether the target to be transformed is masculine or feminine.</ul>"
			
		+"<li>Contributors:</li>"
			
		+"<li>Pimgd:</li>"
		+"<ul>Disallowed quick manage drop if cell is full, thereby preventing item loss.</ul>"
		
		+"<li>Rfpnj:</li>"
		+"<ul>Cow-morph:</ul>"
		+"<ul>Added all cow-morph transformations and body parts.</ul>"
		+"<ul>Added 'Milking Cows' (lore book).</ul>"
		+"<ul>Added bottled cow-morph essence.</ul>"
		+"<ul>Added Bubble Milk. (Icon design help from Blue999.)</ul>"
		+"<ul>Added Bubble Cream.</ul>"
		+"<ul>Added Ear tag (no femininity requirements, ear piercing slot).</ul>"
		+"<ul>Added Cowbell collar (no femininity requirements, neck slot).</ul>"
		+"<ul>Added bovine nose ring (no femininity requirements, nose piercing slot).</ul>"
		
		+"<li>Blue999:</li>"
		+"<ul>Added: Milk Maid's headdress (Feminine, head slot, Milk Maid set).</ul>"

		+ "</list>"
		
		+ "</br>"
		
		+ "<list>"
		
		+ "<h6>Patch Notes - <b>Version 0.1.83.5</b></h6>"
		
		+"<li>Gameplay:</li>"
		+"<ul>Reverted penis/vagina loss when shrinking past minimum size/capacity respectively. You now remove a penis or vagina by adding the 'remove' effect to a potion.</ul>"
		+"<ul>Ralph now stocks squirrel-morph consumables.</ul>"
			
		+"<li>Sex:</li>"
		+"<ul>Converted the following actions into the new cowgirl position for both sub and dom positions: Starting kiss, penetrating anus, penetrating vagina.</ul>"
			
		+"<li>Clothing:</li>"
		+"<ul><b>Temporarily added:</b> Special clothing to Nyan's store, allowing you to buy the rainbow, Maid, and Milk Maid clothing. (I'm going to change this in the future!)</ul>"
		+"<ul>Added: 'Milk Maid' Set, requires Milk Maid's Dress and Kerchief to be worn. (I'll change the set's effects to increase lactation when I convert the attributes over to the new system.)</ul>"
		+"<ul>Added: Milk Maid's Dress (Feminine, Torso slot). (Created by Blue999)</ul>"
		+"<ul>Added: Kerchief (Unisex, head slot).</ul>"
		+"<ul>Added: Nursing bra (Feminine, Chest slot). (Created by Blue999)</ul>"
		+"<ul>Added: Crotchless thong (Feminine, groin slot).</ul>"
		+"<ul>Added: Keyhole sweater (Feminine, torso slot).</ul>"
		+"<ul>Added: Jeans (Unisex, leg slot).</ul>"
			
		+"<li>Other:</li>"
		+"<ul>Testicle-related actions are now correctly disabled if they are internal.</ul>"
		+"<ul>Added correct descriptions for multiple tails in the character view screen.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>Fixed some typos.</ul>"
		+"<ul>Fixed some incorrect gender pronouns.</ul>"
		+"<ul>Fixed some minor UI issues in Kate's shop.</ul>"
		
		+ "</list>"
		
		+ "<list>"

		+ "<h6>Patch Notes - <b>Version " + Main.VERSION_NUMBER + "</b></h6>"
		
		+"<li>Engine:</li>"
		+"<ul>Moved ClothingTypes, ItemTypes, and WeaponTypes from Enum values over to static classes.</ul>"
		+"<ul>Added support for different genital arrangements (normal, cloaca, etc.).</ul>"
			
		+"<li>Gameplay:</li>"
		+"<ul><b>Changed:</b> Encyclopedia entries for clothing, items, weapons, and races are now stored in the properties file, so discoveries are carried over between characters.</ul>"
		+"<ul><b>Added:</b> Forced transformations by random NPCs after combat loss. (You'll need to turn this on in the content preferences first.) (Also, it's only working for the alleyway attackers and harpies in the Nests at the moment.)</ul>"
		+"<ul>Transformation fetish now increases the amount of forced transformations applied after combat defeat from 1 to 3.</ul>"
		+"<ul>Finished Alexa/Scarlett's quest content. (More Alexa content related to her shop will be added soon.)</ul>"
		+"<ul>Reduced the size of Lilaya's home to make navigation more manageable. (I'll add in the larger version later on as an upgrade to the house.)</ul>"
		+"<ul>Made the nursing bra have random enchantments.</ul>"
		+"<ul>Added the hair styles: mohawk, sidecut.</ul>"
		+"<ul>Added colours for pupils and irises: aqua, grey, lilac, purple, violet, crimson, golden.</ul>"
		+"<ul>Added character-specific quicksaves. (Quicksave will now save as 'QuickSave_X', where X is your character's name.)</ul>"
			
		+"<li>Sex:</li>"
		+"<ul>Finished converting old cowgirl position descriptions into the new one.</ul>"
		+"<ul>Converted Brax's cowgirl scene into the new format. (The facesitting descriptions are absent, but I'll add those back in for a proper facesitting position.)</ul>"
			
		+"<li>UI:</li>"
		+"<ul><b>Finished:</b> Remade all the options in Kate's shop to use proper buttons. (I'll use this system for the character creation menu as well.)</ul>"
		+"<ul>Fixed the annoying way the screen would jump back to the top every time you clicked a button (e.g. in preferences menu).</ul>"
		+"<ul>Moved content preferences into its own option in the main menu to make it more obvious.</ul>"
		
		+"<li>Bugs:</li>"
		+"<ul>Fixed some typos.</ul>"
		+"<ul>Fixed some incorrect descriptions in the selfie view.</ul>"
		+"<ul>Several other small bug fixes that I forgot to keep track of...</ul>"
			
		+"<li>Contributors:</li>"
		
		+"<li>Contributed by Pimgd:</li>"
		+"<li>Inventory-related bugs fixed:</li>"
			+"<ul>Take all from floor doesn't handle stackables well if your inventory is full.</ul>"
			+"<ul>Take all weapon doesn't work if inventory is full.</ul>"
			+"<ul>Jinxed items don't stack when unjinxed.</ul>"
			+"<ul>You can't store stackables if the area is full.</ul>"
			+"<ul>Unidentified items will show their state in the buyback menu.</ul>"
			+"<ul>Jinxed items can't be selected if you're carrying a dye brush and just identified them.</ul>"
			+"<ul>Unidentified items will sell for their real value and not the fixed value you get for an unidentified item.</ul>"
			+"<ul>That includes the 'vendor will buy for x' text (not overlay) and the button.</ul>"
			+"<ul>'These items will disappear when you leave the area' when trading on a tile with dropped stuff.</ul>"
			+"<ul>Can't buy/buyback a stackable with full inventory? Check item/weapon/clothing!</ul>"
			+"<ul>Can't unequip weapons/clothing even although they would stack if you have a full inventory.</ul>"
			+"<ul>Unequipping clothing (weapons too?) causing your inventory to be full claims it gets dropped when it doesn't.</ul>"
			+"<ul>Equipping clothing (weapons too?) when you're already wearing something with a full inventory will drop that already worn item to the ground, even if it was stackable in your inventory.</ul>"
			+"<ul>Displaced clothing is dropped even if it could be stacked with a full inventory.</ul>"
			+"<ul>Equipping an item may reset the string builder halfway through causing some warnings not to display.</ul>"
			+"<ul>Sell All has improper rounding as it rounds after all items instead of each item individually (same goes for buy all).</ul>"
			+"<ul>Getting transformed with full inventory will drop your clothing even although it fits in inventory as stackable.</ul>"
			+"<ul>Improvement: Deduplicate printing items now that weapon/clothing/item is similar.</ul>"
			+"<ul>The overlay display 'Vendor wants x' in the buyback shows the wrong price.</ul>"

		+"<li>Body-related fixes:</li>"
			+"<ul>Fixed lip size increment from rolling over causing a constant resize.</ul>"
			+"<ul>Fixed descriptions that go beyond size limits for TongueLength.</ul>"
			+"<ul>Cleaned code for antenna, arms, ass/assSize/hipSize, breasts, eyes, face, hair, horn, mouth, nipples, anus, penis, orifices, tail, testicles, tongue, and vagina.</ul>"
			+"<ul>Fixed nose piercing returning descriptions of lip being pierced.</ul>"
			+"<ul>Fixed a lot of identity mistakes between pc and npc.</ul>"
			+"<ul>Improved nipple and nippleShape changes to properly account of multiple changes form one transformative item.</ul>"
			+"<ul>Tidied up a lot of other formatting.</ul>"

		+"<li>Other fixes:</li>"
			+"<ul>Simplified a lot of if statements.</ul>"
			+"<ul>Removed a lot of empty statements (i.e. removing a ton of unnecessary semi-colons).</ul>"
			+"<ul>Removed a lot of unnecessary casts.</ul>"
			+"<ul>Made all public static SexActions final.</ul>"
			+"<ul>Reordered modifiers to comply with java language specification.</ul>"
		
		+"<li>Contributed by Rfpnj:</li>"
			+"<ul>Expanded and reorganised the Library in Lilaya's house.</ul>"
			+"<ul>Added green skin for demons.</ul>"
		
		+"<li>Contributed by CognitiveMist:</li>"
			+"<ul>Did a huge amount of spelling corrections.</ul>"
			+"<ul>Code cleanup, involving:</ul>"
			+"<ul>List sorting uses comparator class</ul>"
			+"<ul>Use addAll instead of for loops</ul>"
			+"<ul>String comparison don't use ==</ul>"
			+"<ul>Javadoc stuff</ul>"
			+"<ul>Spelling</ul>"
			+"<ul>Change some variables to local, that were only used in one spot</ul>"
			+"<ul>Eliminated some dead/constant logic</ul>"
			+"<ul>Any other cleanup that I could find</ul>"
			+"<ul>Util methods cleanup:</ul>"
			+"<ul>Removed Util.getFileExtention, replaced by a FilenameFilter</ul>"
			+"<ul>Fleshed out / fixed up some Javadocs</ul>"
			+"<ul>Did some witchcraftery on the stringList functions :)</ul>"
		
		+"<li>Contributed by Blue999:</li>"
			+"<ul>Added: Lacy panties (feminine, groin slot).</ul>"
			+"<ul>Added: Lacy plunge bra (feminine, chest slot).</ul>"
		+"</list>"
		;
	
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

		credits.add(new CreditsSlot("Anonymous", "", 0, 0, 2, 2));
		// A. L2 | C.C. R1 | G. R1
		
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Pierrre Mura", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("N.", "", 0, 0, 0, 2)); // TODO
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Rohise", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("S.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Tanner Daves", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("V.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("R.S.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("C.T.", "", 0, 0, 0, 1)); // TODO
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 1)); // TODO
		
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("P.H.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("L.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S.D", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("J.B.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("B.", "", 0, 0, 2, 0)); // TODO
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("V.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("b00marrows ", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("V.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("G.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("L.B.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("D.D.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("K.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("R.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("A.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("N.L.", "", 0, 0, 1, 0)); // TODO
		credits.add(new CreditsSlot("Burt", "", 0, 0, 1, 0));
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		Main.primaryStage.setTitle("Lilith's Throne " + VERSION_NUMBER + " " + VERSION_DESCRIPTION);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.lightTheme) {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
		}

		mainController = loader.getController();

		Main.primaryStage.setScene(mainScene);
		
		Main.primaryStage.show();
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		loader = new FXMLLoader(getClass().getResource("/com/base/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

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
		} catch (ClassNotFoundException | IOException e1) {
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
						pane = loader.load();
						Main.mainController = loader.getController();

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
			saveGame("QuickSave_"+Main.game.getPlayer().getName(), true);
		}
	}

	public static void quickLoadGame() {
		loadGame("QuickSave_"+Main.game.getPlayer().getName());
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
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/saves");
		dir.mkdir();
		
		boolean overwrite = false;
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".lts"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
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
		
		File file = new File("data/saves/"+name+".lts");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
//			long timeStart = System.nanoTime();
//			System.out.println(timeStart);
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			  oos.writeObject(Main.game);
			  oos.close();
			}
			
//			System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);

			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininity()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName();
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.race = game.getPlayer().getRace().getName();
			properties.quest = game.getPlayer().getQuest(QuestLine.MAIN).getName();

			properties.savePropertiesAsXML();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			if(overwrite) {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Save game overwritten!");
			} else {
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), Colour.GENERIC_GOOD, "Game saved!");
			}
		} else if(name.equals("QuickSave_"+Main.game.getPlayer().getName())){
			Main.game.flashMessage(Colour.GENERIC_GOOD, "Quick saved!");
		}
	}

	public static void loadGame(String name) {
		
		File file = new File("data/saves/"+name+".lts");
		
		if (file.exists()) {
			try {
				Main.game = new Game();
				Game loadedGame = null;
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
					loadedGame = (Game) ois.readObject();
				}
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
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".lts"));
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
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(CharacterUtils.startLoadingCharacterFromXML());
				Main.game.setPlayer(CharacterUtils.loadCharacterFromXML(file, Main.game.getPlayer()));
				
				Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
				Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_HOODIE, false), true, Main.game.getPlayer());
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
