package com.lilithsthrone.main;

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

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.LilayasHome;

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
 * @version 0.1.86.1
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	public static String author = "Innoxia";

	public static final String VERSION_NUMBER = "0.1.86.1",
			VERSION_DESCRIPTION = "Early Alpha";

	public static final Image WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

	private static Properties properties;
	
	public static String patchNotes =
			
//		"<h1 style='text-align:center;'>Version " + Main.VERSION_NUMBER + "</h1>"
//		+ "<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Moderately Buggy Preview!</b></h6>"
//		"<h6 style='text-align:center;'><b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha!</b></h6>"
		
		"<p><b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Important information:</b> <i>If you don't see a mini-map in the bottom-left corner of the screen after starting the game, please update your java!</i></p>"
		
		+ "<p>"
			+ "Hello again everyone! ^^"
		+ "</p>"
		
		+ "<p><b>Hotfix version!</b> Scroll to the very bottom to see the hotfix patch notes. :3</p>"
			
		+ "<p>"
			+ "So, finally, the incest content is done. The reason that it took so much time is that it's the first content I've written that's tried to take into account multiple different background factors (job, affection, and personality)."
			+ " After quite a bit of trial-and-error (and two complete rewrites), I think it's in a decent enough state now."
			+ " I will definitely be expanding upon it and improving it in the future, but I really need to move on and get work done on slavery, sex content, and new transformation content."
		+ "</p>"
		
		+ "<p>"
			+ "For the next version, I'll be focusing on those three things, alongside adding in more main story content. ^^"
		+ "</p>"
			
		+ "<p>"
			+ "I hope you enjoy this version, and there will be lots more in the next! :3"
		+ "</p>"
		
		+ "<p>"
			+ "If you wanted to ask me any specific questions about the game, you can either find me on my blog, or on the Lilith's Throne Discord. You can find a link to the discord on my blog. ^^"
		+ "</p>"
			
		+ "</br>"

		+ "<list>"
		
		+ "<h6>Preview (v0.1.85.5)</h6>"
		
		+"<li>Gameplay:</li>"
		+"<ul><b>Added to and improved:</b> Character creation. (I still have a little more to add to this.)</ul>"
		+"<ul><b>Changed:</b> You now start the game with whatever clothes you picked in the character creation process. (Rip hoodie and yoga pants. ;_;)</ul>"
		+"<ul><b>Added:</b> (A very basic) genetics system to offspring. Offspring's features will now be inherited and influenced by their parents. (I will improve this at some stage in the future, but it should be ok as a placeholder.)</ul>"
		+"<ul><b>Added:</b> Four personality types. These will be used to influence the random NPCs' dialogue, obedience & affection gains/losses, and other minor things.</ul>"
		+"<ul>Added breast shape, along with breast shape enchantment elements. (Round, pointy, side-set, wide, and narrow.)</ul>"
		+"<ul>Added labia size.</ul>"
		+"<ul>Increased storage capacity for each tile's ground inventory from 24 to 48.</ul>"
			
		+"<li>UI:</li>"
		+"<ul>Updated the content preferences screen.</ul>"
		+"<ul>Updated the light theme to work with the new UI elements. (It could still do with a lot of improvements though.)</ul>"
			
		+"<li>Clothing:</li>"
		+"<ul>(I needed to add some more clothing for the character creation.)</ul>"
		+"<ul>Added: Stiletto heels (feminine, foot slot).</ul>"
		+"<ul>Added: Trainer socks (unisex, sock slot).</ul>"
		+"<ul>Added: Low-top skater shoes (unisex, foot slot).</ul>"
		+"<ul>Added: Tie (unisex, neck slot).</ul>"
		+"<ul>Added: Suit jacket (masculine, over-torso slot).</ul>"
			
		+"<li>Other:</li>"
		+"<ul><b>Improved:</b> Gender detection and naming to take into account whether the person has breasts or not.</ul>"
		+"<ul>Assumed gender from appearance now takes into account whether your balls are internal, whether your penis is sheathed, and whether you have a cloaca. (A bulge won't be visible for internal balls or cloacas, and will only be visible for very large sheathed penises.)</ul>"
		+"<ul>Added 'Use all' to inventory options.</ul>"
		+"<ul>Moved tape crosses from chest to nipple slot.</ul>"
		+"<ul>Added more pattern availability to nail polish and lipstick.</ul>"
		+"<ul>Improved parser capitalisation detection for body part colours.</ul>"
		+"<ul>Inventory is now ordered by slot type.</ul>"
		+"<ul>Nyan now stocks the cattle set's items.</ul>"
		+"<ul>Enabled 'Take all' in inventory for taking all of an NPC's items at once.</ul>"
		+"<ul>Changed bovine penis default modifier from flared to tapered. (Thanks Rfpnj!)</ul>"
		+"<ul>NPCs will now only spawn with non-con, incest, or transformation fetishes if you have the associated option turned on in the content preferences.</ul>"
		+"<ul>NPCs can now span with up to five fetishes.</ul>"
		+"<ul>Increased chance (to 50%) for your offspring to have the incest fetish.</ul>"
		+"<ul>NPCs that you enslave will start off loathing you and will be very disobedient.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>The icon for watches now have their hands pointing to the correct in-game time again.</ul>"
		+"<ul>Typo and formatting fixes.</ul>"
		+"<ul>Fixed crotchless thong being called 'crotchless thongs'.</ul>"
		+"<ul>Negative values for fertility or virility now correctly prevent pregnancy.</ul>"
		+"<ul>Clothing unequipping in sex is now correctly blocked by other clothing. (e.g. can't unequip your partner's panties if they're wearing yoga pants.)</ul>"
		+"<ul>Changing the capacity of an orifice (through drinking potions) now sets yours stretched capacity to the new value.</ul>"
		+"<ul>Fixed bug where Nyan would sometimes get the clothing she sold to you back in her inventory.</ul>"
		+"<ul>Unzipping trousers and shorts no longer grants access to vagina. (You can still pull them down to do this.)</ul>"
		+"<ul>Scarlett should no longer regenerate her clothing after sex.</ul>"
		+"<ul>Fixed description in combat's dual attack always saying that you hit, even if you missed.</ul>"
		+"<ul>Fixed Pix's post-sex scene leading to the arcade's entrance dialogue, instead of the gym's exterior dialogue.</ul>"
		+"<ul>You should no longer be assumed to be a hermaphrodite if only your penis is visible.</ul>"
		+"<ul>Possibly fixed a cause of the Properties file being reset now and again.</ul>"
		+"<ul>Fixed ability to see NPC's vagina and penis in the tooltip when hovering over their level when you did not know what their genitals looked like.</ul>"
		+"<ul>Fixed tooltip for NPC's equipped clothing not displaying the correct information.</ul>"
		+"<ul>Fixed broken description in selfie for 'almost unnoticeable' breast sizes.</ul>"
		+"<ul>Pubic, facial, and body hair for NPCs should now be displayed if you have those options turned on.</ul>"
		+"<ul>Fixed weapon tooltips displaying incorrect damage.</ul>"
		+"<ul>Fixed bugs which would allow you to use dye-brushes in an incorrect manner, thereby wasting them.</ul>"
		+"<ul>Fixed bug related to Alexa's unsold slaves not being cleared from the game's memory.</ul>"
		+"<ul><b>Fixed:</b> You no longer lose your vagina when importing a character that's either pregnant, or has the pregnancy risk effect.</ul>"
		+"<ul>Importing a character now correctly sets your virginity loss description.</ul>"
		+"<ul>Equipping a slave collar on an NPC from their inventory should now correctly trigger enslavement.</ul>"
		+ "</list>"
		
		+ "</br>"
		
		+ "<list>"

		+ "<h6>Full release (v0.1.86)</h6>"
		+"<li>Engine:</li>"
		+"<ul>Slightly reworked the detection system for consensual sex detection, in order to better support different sex scenes. (Some actions might be available when they shouldn't; I'll get this fixed for the next version.)</ul>"
			
		+"<li>Gameplay:</li>"
		+"<ul><b>Added:</b> Incest dialogue. Your offspring's reactions and dialogue will alter based on your affection with them, as well as their personality and history (at the moment, the available histories are just 'mugger' and 'prostitute').</ul>"
		+"<ul>Finished the basics of the character creator. (There's still more to add!)</ul>"
		+"<ul>Added a couple more room upgrades for slaves, as well as some placeholder actions for conversation with them.</ul>"
			
		+"<li>AI:</li>"
		+"<ul>NPCs will now only use anal actions if they have an anal fetish, or if there is no vagina available.</ul>"
		+"<ul>NPCs will no longer suck their own fingers.</ul>"
			
		+"<li>Other:</li>"
		+"<ul><b>Reworked:</b> Condoms are now a 'clothing' item, and can be equipped into the penis slot just like any other clothing."
		+"</br>Just as they worked before, once the wearer orgasms, a 'filled condom' item is added to their inventory. (Jinxed condoms are affected just like regular ones.)</ul>"
		+"<ul>Unequipping all during sex will no longer remove piercings.</ul>"
		+"<ul>Added the ability for NPCs to use the 'self-doggy' position (where they're the dom, but are the ones on all fours).</ul>"
		+"<ul>Characters with a furry rating of 'Minor' or 'Partial' will no longer spawn with multiple nipples.</ul>"
		+"<ul>Added 'perky' to breast shapes.</ul>"
		+"<ul>Added a couple more options for gender encounter rate settings.</ul>"
		+"<ul>NPCs will now spawn wearing makeup.</ul>"
		+"<ul>Tweaked hair style spawn probabilities.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>Fixed Scarlett slave interactions sometimes being broken.</ul>"
		+"<ul>Fixed default gender spawn preferences only being males and traps.</ul>"
		+"<ul>Setting all gender preferences to 0 now defaults to a 50/50 male/female chance, instead of making no encounters happen.</ul>"
		+"<ul>Fixed bug where you'd start with two demonstones.</ul>"
		+"<ul>Fixed masculine characters being assumed to be a cuntboy instead of a male.</ul>"
		+"<ul>Fixed bug where loading a previously saved game might sometimes cut off sections of dialogue.</ul>"
		+"<ul>Fixed NPCs dropping items in random tiles.</ul>"
		+"<ul>Fixed bug where Vicky's inventory (and possibly Ralph's, under certain circumstances) would sometimes break.</ul>"
		+"<ul>'Buy (5)' and 'Buy (All)' options in inventory dialogue should now correctly be greyed-out if you can't afford the cost.</ul>"
		+"<ul>Typo fixes.</ul>"
		+"<ul>Fixed bug where hotkey input was being registered when typing into a text field.</ul>"
		+"<ul>Fixed exposed & exhibitionist tooltips not returning the correct descriptions for NPCs.</ul>"
		+"<ul>Rough tail-pegging/tail fucking now have the correct related fetishes.</ul>"
		+"<ul>Fixed combat damage calculations not taking into account damage range.</ul>"
		+"<ul>Skin colourings for lips, anus, vagina, penis, and nipples will now be correctly updated to the skin colour of the rest of your body in the character creator.</ul>"
		+"<ul>Random name/surname buttons in the character creator will no loner reset the other field when pressed.</ul>"
		+"<ul>Fixed the availability of clothing's unequipping/drop actions in sex.</ul>"
		+"<ul>Fixed cocks being described as sheathed during sex.</ul>"
		+"<ul>Fixed NPCs not raping you if they had the non-con fetish. (Oops)</ul>"
		+"<ul>Fixed lips always being described as full.</ul>"
		+"<ul>Cow-morph consumables can now be used on alleyway attackers and harpies in the nests.</ul>"
		+ "</list>"
		
		

		+ "</br>"
		
		+ "<list>"

		+ "<h6>Hotfix (v0.1.86.1)</h6>"
		+"<li>Other:</li>"
		+"<ul>Added: Ability to remove jinxes from any NPC's clothing.</ul>"
		+"<ul>Added: Breeder collar (feminine, neck slot). Can be bought from Nyan's 'Specials' menu.</ul>"
		+"<ul>Removing a jinx from clothing no longer removes its negative attributes.</ul>"
		+"<ul>Enabled futanari as a minimal gender encounter by default.</ul>"
		+"<ul>Improved the gender preferences screen to show how each gender is defined.</ul>"
			
		+"<li>Bugs:</li>"
		+"<ul>Fixed the strange behaviour in sex scenes, where dom/sub would sometimes switch, as well as sex ending when either one of you came.</ul>"
		+"<ul>Fixed game freeze/crash when trying to remove your offspring.</ul>"
		+"<ul>Fixed character import bug (where characters from pre-0.1.86 were not being imported correctly).</ul>"
		+"<ul>Character import now correctly loads in your surname.</ul>"
		+"<ul>Fixed no_clothing_covering_nipples error that was appearing in some sex actions.</ul>"
		+"<ul>Fixed nipples being described as 'dry'.</ul>"
		+"<ul>Typo fixes.</ul>"
		+"<ul>Changed flat-chested 'breasts' parser description to output 'pecs' instead of 'chest'. (Hopefully this hasn't broken descriptions in places. x_x)</ul>"
		+"<ul>Condoms will no longer magically re-equip themselves after sex.</ul>"
		+"<ul>Fixed formatting in all stats pages.</ul>"
		+"<ul>Succubi in the Dark Alleyway tiles will no longer disappear as soon as you move away.</ul>"
		+"<ul>Feeding your prostitute daughter a Vixen's Virility pill will now correctly override her promiscuity pill's effects.</ul>"
		+"<ul>Fixed 'Paizuri into mouth' action being available even if your mouth was blocked.</ul>"
		+"<ul>Fixed Nyan stocking condoms, and added them to Ralph's inventory again.</ul>"
		+"<ul>Fixed an instance of an NPC's orgasm not describing them wearing a condom.</ul>"
		+"<ul>Piercings should now correctly appear in the character creation.</ul>"
		+"<ul>Fixed virginity loss description being printed twice.</ul>"
		+"<ul>Fixed an import bug marking cum as milk.</ul>"
		+"<ul>Fixed a couple of inconsistencies in the prologue dialogue.</ul>"
		+"<ul>Fixed genders with breast requirements (such as busty-boys or flat-chested traps) not spawning.</ul>"
		+"<ul>Fixed random encounters of certain genders when they were supposed to be turned off.</ul>"
		+"<ul>Prostitute offspring will no longer rsist after you've paid to have sex with them.</ul>"
		+"<ul>Fixed all starting characters having 5 corruption (it's going to be based on your sexual experience until I add in the final parts of the character creation process).</ul>"
		+"<ul>Fixed slave room description not taking into account bed upgrades.</ul>"
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
	private static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		credits.add(new CreditsSlot("Anonymous", "", 0, 0, 5, 2));
		// A. L2 | C.C. R2 | G. R2 | JD 1R
		
		
		credits.add(new CreditsSlot("A", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Adhana Konker", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Lexi <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("48days", "", 0, 0, 2, 1));
		credits.add(new CreditsSlot("A", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Spaghetti Code", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Argmoe", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Arkhan", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("B H", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("B", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Blacktouch", "", 0, 0, 2, 1));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("B", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Burt", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Calrak", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("CelestialNightmare", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Sxythe", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Lexi the slut", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Vmpireassassin (Chloe)", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Cursed Rena", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("Yllarius", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("John Scarlet", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Elmsdor", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Gr33n B3ans", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Farseeker", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Fiona", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Niki Parks", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 1));
		credits.add(new CreditsSlot("G", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("J B", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("suka", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Enigamatic Yoshi", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Kestrel", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Kernog", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Knight-Lord Xander", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("L", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Pallid", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Mr L", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("KingofKings", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Nightmare", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("AlphaOneBravo", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Max Nobody", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("N", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("N L", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Niko", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Rohsie", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("P H", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Pierre Mura", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Pokys", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Rakesh", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Master's dumb bitch", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("R", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Saladine", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 2));
		credits.add(new CreditsSlot("Shas'O Dal'yth Kauyon Kais Taku", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Crow Invictus", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Sorter", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Spookermen", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("S D", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 0, 0));
		credits.add(new CreditsSlot("Swift Shot", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Tanner Daves", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Terrance", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Jordan Aitken", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Torinir", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Isidoros", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Vaelin", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("V", "", 0, 1, 2, 0));
		credits.add(new CreditsSlot("V", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("William Brown", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Wolfregis", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Nelson Adams", "", 0, 0, 3, 0));
		
		credits.add(new CreditsSlot("A", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("A t A", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Avery", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("B G", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Blackcanine", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Crimson", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alatar", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("pupslut felix", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("G", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("G", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("H", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("J M", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Krejil", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("J D", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kiroberos", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("L", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("L", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("M M", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Muhaku", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Kvernik", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Party Commissar", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Sheltem", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("T M", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("T", "", 0, 1, 1, 0));
		credits.add(new CreditsSlot("T. Garou", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("TreenVall", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("V", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Whatever", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("W J O", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Z", "", 0, 0, 1, 0));
		
		
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
		
		try {
			Main.game = new Game();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

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
				Main.game.setPlayer(new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_ENTRANCE_HALL));

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
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Game loadedGame = (Game) ois.readObject();
				ois.close();
				fin.close();
				Main.game = loadedGame;
				Main.game.reloadContent();
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
				Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, false), true, Main.game.getPlayer());
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
