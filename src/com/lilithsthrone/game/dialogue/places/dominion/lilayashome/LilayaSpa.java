package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class LilayaSpa {
	
	public static final String SPA_CONSTRUCTTION_TIMER_ID = "spa_construction_timer";
	
	private static Cell cellInstallation = null;
	
    private static List<GameCharacter> slavesWashing;
    private static List<GameCharacter> slavesSex;
	private static List<GameCharacter> bathingStripped;
    private static List<GameCharacter> slavesForMassage;

	private static GameCharacter drinksCharacter = null;
	private static GameCharacter massageSlave = null;
	private static GameCharacter guest = null;
	private static boolean playerStripped = false;
	
	private static boolean massageSlaveSex = false;
	
    private static List<AbstractItemType> getDrinks() {
    	return Util.newArrayListOfValues(
    			ItemType.getItemTypeFromId("innoxia_race_human_vanilla_water"),
    			ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_juice_box"),
    			ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush"),
    			ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider"),
    			ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy"),
    			ItemType.getItemTypeFromId("innoxia_race_fox_vulpines_vineyard"),
    			ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum"),
    			ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey"));
    }
    
    public static void setCellInstallation(Cell cellInstallation) {
    	LilayaSpa.cellInstallation = cellInstallation;
    }
    
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> charactersPresent = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		charactersPresent.removeIf(character -> !character.isSlave());
		return charactersPresent;
	}
	
	public static void initGuestAtSpa(GameCharacter guest) {
		LilayaSpa.guest = guest;
		drinksCharacter = null;
	}
	
	public static boolean isGuestAbleToEquipSwimwear(GameCharacter guest) {
		if(guest.isFeminine()) {
			AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
			AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_chest_micro_bikini
			AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_groin_micro_bikini
			
			return guest.isAbleToEquip(swimsuit, true, guest) || (guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest));
			
		} else {
			AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
			return guest.isAbleToEquip(swimShorts, true, guest);
		}
	}
	
	private static boolean isGuestPresent() {
		return guest!=null;
	}
	
	// Reception slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> receptionGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] professionally curtsies as [npc.she] sees you enter the reception area, before asking,"
						+ "#ELSE"
							+ "[npc.Name] professionally bows as [npc.she] sees you enter the reception area, before asking,"
						+ "#ENDIF",
						"Flashing you a professional smile, [npc.name] asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] leans forwards over the counter and puts on [npc.her] most seductive tone of voice as [npc.she] asks,"
						+ "#ELSE"
							+ "[npc.Name] gazes into your [pc.eyes] and puts on [npc.her] most seductive tone of voice as [npc.she] asks,"
						+ "#ENDIF",
						"Flashing you a seductive smile, [npc.name] asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] bites [npc.her] lip and lets out an inappropriate, slutty moan as [npc.she] asks,"
						+ "#ELSE"
							+ "[npc.Name] lustfully grins at you as you enter, before bluntly asking,"
						+ "#ENDIF",
						"Flashing you a lewd smile, [npc.name] asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] curtsies as [npc.she] sees you enter the reception area, before asking,"
						+ "#ELSE"
							+ "[npc.Name] smiles as [npc.she] sees you enter the reception area, before asking,"
						+ "#ENDIF",
						"Flashing you a quick smile, [npc.name] asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] lets out a happy little cry as [npc.she] sees you enter the reception area, before eagerly asking,"
						+ "#ELSE"
							+ "[npc.Name] happily smiles as [npc.she] sees you enter the reception area, before eagerly asking,"
						+ "#ENDIF",
						"Flashing you a loving smile, [npc.name] eagerly asks,")));

	private static Map<SlavePermissionSetting, List<String>> receptionGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] professionally curtsies as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ELSE"
							+ "[npc.Name] professionally bows as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ENDIF",
						"Flashing you a professional smile, [npc.name] obediently waits to see if [npc.she]'ll be able to help you with anything.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] leans forwards over the counter and flashes you [npc.her] most seductive smile as [npc.she] waits to see if you'll ask anything of [npc.herHim]."
						+ "#ELSE"
							+ "[npc.Name] gazes into your [pc.eyes] and flashes you [npc.her] most seductive smile as [npc.she] waits to see if you'll ask anything of [npc.herHim]."
						+ "#ENDIF",
						"Flashing you a seductive smile, [npc.name] waits to see if [npc.she]'ll be able to help you with anything.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] bites [npc.her] lip and lets out an inappropriate, slutty moan as [npc.she] waits to see if you'll ask anything of [npc.herHim]."
						+ "#ELSE"
							+ "[npc.Name] lustfully grins at you as you enter, before waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ENDIF",
						"Flashing you a lustful grin, [npc.name] waits to see if [npc.she]'ll be able to help you with anything.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] curtsies as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ELSE"
							+ "[npc.Name] smiles as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ENDIF",
						"Flashing you a quick smile, [npc.name] waits to see if [npc.she]'ll be able to help you with anything.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] lets out a happy little cry as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ELSE"
							+ "[npc.Name] happily smiles as [npc.she] sees you enter the reception area, before dutifully waiting to see if you'll ask anything of [npc.herHim]."
						+ "#ENDIF",
						"Flashing you a loving smile, [npc.name] happily waits to see if [npc.she]'ll be able to help you with anything.")));

	private static Map<SlavePermissionSetting, List<String>> receptionSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"How may I help you, [pc.name]?",
						"Is there anything I may assist you with, [pc.name]?",
						"Would you care for a shower before entering the spa, [pc.name]?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"Would you care for some <i>personal</i> assistance?",
						"Is there <i>anything</i> I can help you with, [pc.name]?",
						"How may I <i>assist</i> you today, [pc.name]?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "How about you bend me over this desk and fuck me senseless?"
						+ "#ELSE"
							+ "You're looking for a good fuck, right?"
						+ "#ENDIF",
						"Come on and fuck me already!",
						"Let's fuck, [pc.name]!")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Can I help you with anything, [pc.name]?",
						"What can I do for you, [pc.name]?",
						"Is there anything I can help you with, [pc.name]?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"Hi, [pc.name], can I do anything to help you?! I'm so happy to see you again!",
						"What can I do for you, [pc.name]?!",
						"Hi, [pc.name], what can I do for you today?!")));
	
	private static List<String> receptionRudeGreetings = Util.newArrayListOfValues(
			"Glaring angrily at you, [npc.name] disobediently scowls, ",
			"With [npc.her] [npc.eyes+] full of resentment, [npc.name] glares at you and snaps,",
			"Clearly not at all happy with being forced to work in the spa, [npc.name] angrily growls,");

	private static List<String> receptionRudeGreetingsMute = Util.newArrayListOfValues(
			"Glaring angrily at you, [npc.name] lets out a disobedient scowl, making it quite clear that [npc.sheHasFull] no interest in helping you with anything...",
			"With [npc.her] [npc.eyes+] full of resentment, [npc.name] glares at you and lets out a disobedient growl.",
			"Clearly not at all happy with being forced to work in the spa, [npc.name] angrily growls at you, before crossing [npc.her] [npc.arms] and refusing to offer you any assistance.");
	
	private static List<String> receptionRudeSpeech = Util.newArrayListOfValues(
			"What the fuck do you want now, <i>[pc.name]</i>?",
			"Just hurry the fuck up and get out of here.",
			"How about you turn around and fuck off?");
	
	
	// Shower slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> showerGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Lathering soap over [npc.her] [npc.hands], [npc.name] sets about washing your back, saying,",
						"Acting in a professional manner, [npc.name] sets about rubbing soap over your body, saying,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] leans in and presses [npc.her] [npc.breasts+] against your back, teasing,"
						+ "#ELSE"
							+ "[npc.Name] leans in and presses [npc.herself] against your back, teasing,"
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "Seductively teasing [npc.her] soapy [npc.fingers] over your body, [npc.name] teases,"
						+ "#ELSE"
							+ "Seductively running [npc.her] soapy [npc.hands] over your body, [npc.name] teases,"
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "Shamelessly rubbing [npc.her] soapy [npc.breasts] over your back, [npc.name] crudely asks,"
						+ "#ELSE"
							+ "Shamelessly pressing [npc.her] soapy body against your back, [npc.name] crudely asks,"
						+ "#ENDIF",
						"Giving your body a good grope as [npc.she] rubs [npc.her] soapy [npc.hands] over your [pc.skin], [npc.name] pointedly asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Lathering soap over [npc.her] [npc.hands], [npc.name] sets about washing your back, saying,",
						"Setting about [npc.her] job with all due diligence, [npc.name] rubs soap over your body, saying,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] lovingly runs [npc.her] soapy hands over your body, before leaning in against you and sighing,"
						+ "#ELSE"
							+ "[npc.Name] lovingly runs [npc.her] soapy hands over your body, before leaning in against you and saying,"
						+ "#ENDIF",
						"Happily humming to [npc.herself] as [npc.she] rubs soap over your body, [npc.name] says,")));

	private static Map<SlavePermissionSetting, List<String>> showerGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Lathering soap over [npc.her] [npc.hands], [npc.name] sets about washing your back in a professional manner.",
						"Acting in a professional manner, [npc.name] sets about rubbing soap over your body and helping to clean yourself.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] leans in and seductively presses [npc.her] [npc.breasts+] against your back as [npc.she] helps to clean you."
						+ "#ELSE"
							+ "[npc.Name] leans in and seductively presses [npc.herself] against your back as [npc.she] helps to clean you."
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "Seductively teasing [npc.her] soapy [npc.fingers] over your body, [npc.name] smirks to [npc.herself] as [npc.she] helps to clean you."
						+ "#ELSE"
							+ "Seductively running [npc.her] soapy [npc.hands] over your body, [npc.name] smirks to [npc.herself] as [npc.she] helps to clean you."
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "Shamelessly rubbing [npc.her] soapy [npc.breasts] over your back, [npc.name] reaches down to give your groin a good grope and lets out a hungry [npc.moan]."
						+ "#ELSE"
							+ "Shamelessly pressing [npc.her] soapy body against your back, [npc.name] reaches down to give your groin a good grope and lets out a hungry [npc.moan]."
						+ "#ENDIF",
						"Giving your body a good grope as [npc.she] rubs [npc.her] soapy [npc.hands] over your [pc.skin], [npc.name] lets out a hungry [npc.moan] as [npc.she] helps to clean you.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Lathering soap over [npc.her] [npc.hands], [npc.name] dutifully sets about washing your back.",
						"Setting about [npc.her] job with all due diligence, [npc.name] rubs soap over your body as [npc.she] helps to clean you.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] lovingly runs [npc.her] soapy hands over your body, before leaning in against you and letting out a happy sigh."
						+ "#ELSE"
							+ "[npc.Name] lovingly runs [npc.her] soapy hands over your body, before leaning in against you and letting out a happy sigh."
						+ "#ENDIF",
						"After happily rubbing soap all over your body, [npc.name] leans in against you and lets out a contented sigh.")));

	private static Map<SlavePermissionSetting, List<String>> showerSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"I'll have you nice and clean in no time, [pc.name].",
						"This will only take a moment, [pc.name].")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"You know, there's more we can do in here than just getting clean...",
						"Perhaps we could spend a little more time in here together...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "Come on, [pc.name], push me against the wall and give me a good fucking!"
						+ "#ELSE"
							+ "Come on, [pc.name], let's fuck!"
						+ "#ENDIF",
						"Come on and fuck me already!")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"You'll be sparkling clean after this, [pc.name].",
						"I'll have you nice and clean in just a moment, [pc.name].")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"I hope this is to your liking, [pc.name]...",
						"Is this how you like it, [pc.name]?")));
	
	private static List<String> showerRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name] angrily scowls as [npc.she] reluctantly helps you to clean yourself,",
			"Glaring angrily at you as [npc.she] half-heartedly helps to wash your body, [npc.name] growls,",
			"Resenting the fact that [npc.sheHasFull] to help you clean yourself, [npc.name] angrily sneers,");

	private static List<String> showerRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name] angrily scowls as [npc.she] reluctantly helps you to clean yourself...",
			"Glaring angrily at you the entire time, [npc.name] half-heartedly helps to wash your body...",
			"Resenting the fact that [npc.sheHasFull] to help you clean yourself, [npc.name] angrily glares at you...");
	
	private static List<String> showerRudeSpeech = Util.newArrayListOfValues(
			"Just fucking hurry up so I can get out of here...",
			"I hate this so much...",
			"Why the fuck do I have to do this?");
	
	
	// Bathing slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> bathingGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Sinking down into the pool's warm water, [npc.name] sighs,",
						"Letting out a deep sigh as [npc.she] enters the pool, [npc.name] remarks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] bites [npc.her] [npc.lip] and flashes you a seductive look as [npc.she] teases,"
						+ "#ELSE"
							+ "[npc.Name] flashes you a seductive smirk and teases,"
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "Seductively teasing [npc.her] wet [npc.fingers] over [npc.her] body, [npc.name] teases,"
						+ "#ELSE"
							+ "Seductively running [npc.her] wet [npc.hands] over [npc.her] body, [npc.name] teases,"
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"Splashing through the pool's water to rub up against you, [npc.name] crudely asks,",
						"Sliding up next to you, [npc.name] gives your body a good grope as [npc.she] pointedly asks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Sinking down into the pool's warm water, [npc.name] sighs,",
						"Letting out a deep sigh as [npc.she] enters the pool, [npc.name] remarks,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name] blushes a little as [npc.she] sinks down into the warm water and sighs,",
						"Happily humming to [npc.herself] as [npc.she] slides down into the warm water, [npc.name] sighs,")));

	private static Map<SlavePermissionSetting, List<String>> bathingGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Sinking down into the pool's warm water, [npc.name] lets out a quiet sigh and simply enjoys this opportunity to relax.",
						"Letting out a barely-audible sigh as [npc.she] enters the pool, [npc.name] closes [npc.her] [npc.eyes] and relaxes in the warm water.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name] bites [npc.her] [npc.lip] and flashes you a seductive look as [npc.she] moves up close to where you're sitting."
						+ "#ELSE"
							+ "[npc.Name] flashes you a seductive smirk as [npc.she] moves up close to where you're sitting."
						+ "#ENDIF",
						"[npc.Name] makes a show of seductively running [npc.her] wet [npc.hands] over [npc.her] body, before suggestively winking at you.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"Splashing through the pool's water to rub up against you, [npc.name] crudely motions for you to fuck [npc.herHim]...",
						"Sliding up next to you, [npc.name] gives your body a good grope as [npc.she] lets out a hungry whine.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
							"Sinking down into the pool's warm water, [npc.name] lets out a quiet sigh and simply enjoys this opportunity to relax.",
							"Letting out a barely-audible sigh as [npc.she] enters the pool, [npc.name] closes [npc.her] [npc.eyes] and relaxes in the warm water.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name] blushes a little as [npc.she] sinks down into the warm water and smiles at you.",
						"[npc.Name] happily hums to [npc.herself] as [npc.she] slides down into the warm water and flashes you a loving smile.")));

	private static Map<SlavePermissionSetting, List<String>> bathingSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Please let me know if you require anything of me, [pc.name]...",
						"This is most pleasing...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"You know, we could be doing things other than just relaxing right now...",
						"Perhaps we could find a way to make this more exciting?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "Come on, [pc.name], give me a good fucking already!"
						+ "#ELSE"
							+ "Come on, [pc.name], let's just fuck already!"
						+ "#ENDIF",
						"Hey, how about we fuck in the pool? Sounds good, right?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"This feels so good...",
						"Thanks for letting me join you in here, [pc.name]...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"This feels great... Thank you so much for letting me be with you, [pc.name]...",
						"Thank you for sharing this with me, [pc.name]...")));
	
	private static List<String> bathingRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name] angrily scowls as [npc.she] reluctantly joins you in the pool,",
			"Glaring angrily at you as [npc.she] splashes down into the pool's warm water, [npc.name] scowls,",
			"Resenting the fact that [npc.sheHasFull] to join you in the pool, [npc.name] angrily sneers,");

	private static List<String> bathingRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name] angrily scowls as [npc.she] reluctantly joins you in the pool...",
			"Glaring angrily at you, [npc.name] reluctantly splashes down into the pool's warm water...",
			"Resenting the fact that [npc.sheHasFull] to join you in the pool, [npc.name] angrily glares at you...");
	
	private static List<String> bathingRudeSpeech = Util.newArrayListOfValues(
			"The spa's nice, but it's much better when you're not here...",
			"Hurry up and leave so that I can enjoy this by myself again...",
			"Just hurry up and get out of here...");
	
	
	private static String getReceptionSlavesDescription(List<GameCharacter> slavesReception) {
		if(slavesReception.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesReception.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesReception) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("Having been instructed to work as a receptionist at your private spa, your slave, "+Util.stringsToStringList(names, false)+", is standing behind the counter.");
			} else {
				sb.append("Having been instructed to work as receptionists at your private spa, your slaves, "+Util.stringsToStringList(names, false)+", are standing behind the counter.");
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesReception.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(receptionGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(receptionGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(receptionSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesReception);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(receptionRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(receptionRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(receptionRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "Having been instructed to assist you with cleaning yourself, your slave, "+Util.stringsToStringList(names, false)+", [npc.steps] into the shower and prepares to start washing you..."));
			} else {
				sb.append(UtilText.parse(slavesWashing, "Having been instructed to assist you with cleaning yourself, your slaves, "+Util.stringsToStringList(names, false)+", [npc.step] into the shower and prepare to start washing you..."));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(showerGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(showerGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(showerSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(showerRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(showerRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(showerRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}

	private static String getSpaSlavesDescription(List<GameCharacter> slavesPresent) {
		if(slavesPresent.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		boolean soloSlave = slavesPresent.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesPresent) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("[style.boldMinorGood(Slaves Present)]<b>:</b>"
						+ "<br/>");
				sb.append(UtilText.parse(slavesPresent, "Sitting on one of the loungers positioned beside the pools, your slave, "+Util.stringsToStringList(names, false)+", is waiting for you to give [npc.her] an order."));
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p>"
							+ "<p>[style.boldAqua(Bathing)]<b>:</b>"
							+ "<br/>As you have instructed "+Util.stringsToStringList(names, false)+" to join you whenever you bathe in the spa, you can be sure that [npc.she]'ll accompany you if you were to slip into one of the pools."));
				}
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p>"
							+ "<p>[style.boldTan(Massage)]<b>:</b>"
							+ "<br/>"+Util.stringsToStringList(names, false)+" [npc.has] been given some training in how to perform a massage,"
									+ " so if you wanted [npc.herHim] to give you one, all you'd need to do is lie down on one of the loungers and call [npc.herHim] over."));
				}
				
			} else {
				sb.append("[style.boldMinorGood(Slaves Present)]<b>:</b>"
						+ "<br/>");
				sb.append(UtilText.parse(slavesPresent, "Sitting on the loungers positioned beside the pools, your slaves, "+Util.stringsToStringList(names, false)+", are waiting for you to give them an order."));
				List<String> bathingNames = new ArrayList<>();
				GameCharacter bathingSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
						bathingSlave = npc;
						bathingNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!bathingNames.isEmpty()) {
					sb.append("</p>"
							+ "<p>[style.boldAqua(Bathing)]<b>:</b>"
							+ "<br/>");
					if(bathingNames.size()==1) {
						sb.append(UtilText.parse(bathingSlave,
								"As you have instructed [npc.herHim] to join you whenever you bathe in the spa, you can be sure that "+Util.stringsToStringList(bathingNames, false)+" will accompany you if you were to slip into one of the pools."));
					} else {
						sb.append("As you have instructed them to join you whenever you bathe in the spa, you can be sure that "+Util.stringsToStringList(bathingNames, false)+" will accompany you if you were to slip into one of the pools.");
					}
				}
				List<String> massageNames = new ArrayList<>();
				GameCharacter massageSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
						massageNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!massageNames.isEmpty()) {
					sb.append("</p>"
							+ "<p>[style.boldTan(Massage)]<b>:</b>"
							+ "<br/>");
					if(massageNames.size()==1) {
						sb.append(UtilText.parse(massageSlave,
								Util.stringsToStringList(bathingNames, false)+" [npc.has] been given some training in how to perform a massage,"
										+ " so if you wanted [npc.herHim] to give you one, all you'd need to do is lie down on one of the loungers and call [npc.herHim] over."));
					} else {
						sb.append(Util.stringsToStringList(bathingNames, false)+" have been given some training in how to perform a massage,"
										+ " so if you wanted one of these slaves to give you one, all you'd need to do is lie down on one of the loungers and call one of them over.");
					}
				}
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static String getBathingSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "Having been instructed to join you whenever you bathe, your slave, "+Util.stringsToStringList(names, false)+", [npc.steps] into the pool and sinks down into the warm water beside you..."));
			} else {
				sb.append(UtilText.parse(slavesWashing, "Having been instructed to join you whenever you bathe, your slaves, "+Util.stringsToStringList(names, false)+", [npc.step] into the pool and sink down into the warm water beside you..."));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(bathingGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(bathingGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(bathingSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(bathingRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(bathingRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(bathingRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode SPA_INSTALLATION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Commit", "Commit to extending this room and turning it into a private spa.", SPA_INSTALLATION_COMMIT) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation;
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX(), cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Change mind", "Tell Lilaya that you've changed your mind...", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA.getInstallCost());
						cellInstallation.addPlaceUpgrade(PlaceUpgrade.LILAYA_EMPTY_ROOM);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_INSTALLATION_COMMIT = new DialogueNode("Spa", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Follow in Lilaya's footsteps and head on out into the corridor...", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT_LEAVE"));
						Main.game.getTextStartStringBuilder().append(PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false).getContent());
						if(Main.game.getActiveWorld().getWorldType()==WorldType.LILAYAS_HOUSE_GROUND_FLOOR) { // To cover for if the player is upgrading via Office's occupancy ledger
							Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_INSTALLATION_END = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_CONSTRUCTION = new DialogueNode("Building site", "", false) {
		@Override
		public void applyPreParsingEffects() {
			// This is a backup check to finish construction if somehow the building site has not been converted to the spa:
			if(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID) > 7) {
				LilayaHomeGeneric.dailyUpdate();
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
			UtilText.addSpecialParsingString(Util.intToString(daysLeft)+" "+(daysLeft==1?"day":"days"), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CONSTRUCTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION = new DialogueNode("Spa", "", false) {
		@Override
		public void applyPreParsingEffects() {
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
				UtilText.addSpecialParsingString(Util.intToString(daysLeft)+" "+(daysLeft==1?"day":"days"), true);
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CONSTRUCTION");
				
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION", getSlaves()));
				sb.append(getReceptionSlavesDescription(getSlaves()));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_END"));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
				return sb.toString();
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<GameCharacter> slavesAssignedToRoom = getSlaves();
			
			if(index==0) {
				return null;
				
			} else if(index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Manage room", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if(index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Manage people", "You need a slaver license or permission from Lilaya to house your friends or dolls in order to access this menu!",  null);
				}
				
			} else if(index==3) {
				if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)!=null) {
					return new Response("Shower",
							"Use one of the showers to quickly clean yourself before entering the spa proper."
								+ "<br/>[style.italicsGood(Cleans <b>a maximum of "+Units.fluid(500)+"</b> of fluids from all orifices.)]"
								+ "<br/>[style.italicsGood(This will clean <b>only</b> your currently equipped clothing.)]",
							SPA_RECEPTION_SHOWER){
						@Override
						public void effects() {
							slavesWashing = getSlaves().stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA_RECEPTIONIST, SlaveJobSetting.SPA_SHOWERING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, true, null, 240+30);
							}

							Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>You leave your clothes in the changing room so that they can be cleaned while you wash yourself...</i></p>");
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
						}
					};
				}
				return null;
			}
			//TODO Inviting slaves, occupants, Arthur, Lilaya (who is always accompanied by Rose)
			// Leads into them taking showers and into pools
			//TODO make spa core scene travel disabled
			// If get Lilaya drunk, Rose makes her perform cunnilingus (Rose sits on edge of pool while Lilaya is in it)
				// Rose asks if you'd like to join her bitch
				// Leads into Rose fucking you and Lilaya with strapon
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = (NPC) slavesAssignedToRoom.get(index-indexPresentStart);
				return LilayaHomeGeneric.interactWithNPC(character);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION_SHOWER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER", getSlaves()));
			sb.append(getShowerSlavesDescription(slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER_END", getSlaves()));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("Finish", "Finish having a shower and head back out into the changing room.", AFTER_SHOWER_FINISHED);
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("Sex", "You do not have any slaves assigned to work in the spa's reception with the washing permission, so there's nobody for you to have sex with...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size(); i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Sex",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Have dominant sex with [npc.name] in the shower.")
							:"Have dominant sex with your slaves in the shower.",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_DOM", slavesWashing)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
					}
				};
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("Submissive sex", "You do not have any slaves assigned to work in the spa's reception with the washing permission, so there's nobody for you to have submissive sex with...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Submissive sex",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "As [npc.name] isn't attracted to you, [npc.she] is unwilling to be the dominant partner in sex...")
								:UtilText.parse(slavesWashing, "As [npc2.name] isn't attracted to you, [npc2.she] is unwilling to be the dominant partner in sex..."),
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				return new ResponseSex("Submissive sex",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Let [npc.name] dominantly fuck you in the shower.")
										:"Let your slaves dominantly fuck you in the shower.",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_SUB", attractedSlaves)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("Finished", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "Having had [npc.her] fun, [npc.name] reminds you that you have other things you need to be getting on with...");
			} else {
				return "Having had their fun, your slaves remind you that you have other things you need to be getting on with...";
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_SEX", sexSlaves));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_SHOWER_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_FINISHED", slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_CORE = new DialogueNode("", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return isGuestPresent();
		}
		@Override
		public void applyPreParsingEffects() {
			try {
				Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).get(0).getInventory().cleanAllClothing(true);
			} catch(Exception ex) {
				System.err.println("Clothes cleaning in SPA_CORE failed!");
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(String.valueOf(getSlaves().isEmpty()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE"));
			
			sb.append(getSpaSlavesDescription(getSlaves()));
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA_BAR).isEmpty()) {
				return null;
			}
			if(index==0) {
				return "Pools";
			} else if(index==1) {
				return "Bar";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuestPresent() && index==0) {
				return new Response("Leave",
						UtilText.parse(guest, "Tell [npc.name] that you've got to leave now and accompany [npc.herHim] out of the spa."),
						SPA_GUEST_END);
			}
			
			if(responseTab==1) {
				if(index==0) {
					return null;
				}
				GameCharacter target = 
						drinksCharacter==null || (!getSlaves().contains(drinksCharacter) && !isGuestPresent())
							?Main.game.getPlayer()
							:drinksCharacter;
							
				if(index==11) {
					return new ResponseEffectsOnly(
							UtilText.parse(target, "Target: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Cycle the targeted character to receive a drink.") {
						@Override
						public void effects() {
							List<GameCharacter> characters = Util.newArrayListOfValues(Main.game.getPlayer());
							if(isGuestPresent()) {
								characters.add(guest);
							} else {
								characters.addAll(getSlaves());
							}
							for(int i=0; i<characters.size();i++) {
								if(characters.get(i).equals(target)) {
									if(i==characters.size()-1) {
										drinksCharacter = characters.get(0);
									} else {
										drinksCharacter = characters.get(i+1);
										break;
									}
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				if(index-1<getDrinks().size()) {
					AbstractItem drink = Main.game.getItemGen().generateItem(getDrinks().get(index-1));
					return new Response(drink.getName(false, false),
							target.isPlayer()
								?"Have a glass of "+drink.getName(false, false)+" from the bar."
								:UtilText.parse(target, "Give [npc.name] a glass of "+drink.getName(false, false)+" from the bar."),
							SPA_CORE_BAR_DRINK) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(drink.getName(false, false), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BAR_DRINK", target));
							if(target instanceof Arthur || target instanceof Rose) { // Arthur and Rose do not get drunk (Lilaya does...)
								if(!drink.getItemTags().contains(ItemTag.ALCOHOLIC)) {
									Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
								}
								
							} else {
								Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
							}
						}
					};
				}
				
				return null;
			}
			
			if(isGuestPresent()) {
				if(index==1) {
					return new Response("Use pool",
							UtilText.parse(guest, "Slip into one of the pools with [npc.name] and take some time to relax...")
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]",
							SPA_GUEST_CORE_BATHING){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(guest.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
				}
				if(index==2) {
					return new Response("Give massage",
							UtilText.parse(guest, "Get [npc.name] to lie down on one of the loungers and give [npc.herHim] a massage."),
							SPA_GUEST_CORE_MASSAGE_GIVE);
				}
				if(index==3) {
					return new Response("Receive massage",
							UtilText.parse(guest,
									"Lie down on one of the loungers and have [npc.name] give you a massage."),
							SPA_GUEST_CORE_MASSAGE_RECEIVE);
				}
				
			} else {
				List<GameCharacter> slaves = new ArrayList<>(getSlaves());
				if(index==1) {
					return new Response("Use pool",
							"Slip into one of the pools and take some time to relax..."
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(This <b>does</b> clean companions.)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==2) {
					return new Response("Use pool (strip)",
							"Strip naked, then slip into one of the pools and take some time to relax..."
								+ "<br/>[style.italicsExcellent(This will clean <b>all</b> fluids out of all your orifices.)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(This <b>does</b> clean companions.)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							bathingStripped.add(Main.game.getPlayer());
							Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==3) {
					if(!slaves.stream().anyMatch(npc -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE))) {
						return new Response("Massage",
								"There are no available slaves to give you a massage!"
										+ "<br/><i>You need to assign slaves to work in the spa and give them the '"+SlaveJobSetting.SPA_MASSAGE.getName()+"' permission.</i>",
								null);
					}
					return new Response("Massage",
							"Have one of your slaves give you a massage.<br/>[style.italics(Proceed to the slave selection screen, where you can choose which slave you want to massage you.)]",
							SPA_MASSAGE_SELECTION);
					
				} else if(index==4) {
//					return new Response("Relax", "Lie down on one of the loungers and just relax for a while...", null); //TODO Slave use player
					return null;
					
				} else if(index==5) { //TODO All slimes turn the pool into slime and massage you. Requires unique sex scene with special actions as they fuck you from everywhere at once
//					if(!slaves.stream().anyMatch(npc -> npc.getRace()==Race.SLIME && npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING))) {
//						return new Response("Slime soak", "TODO", null);
//					}
//					return new Response("Slime soak", "TODO", null);
					return null;
				}
				int indexPresentStart = 6;
				if(index>0 && index-indexPresentStart<getSlaves().size()) {
					NPC character = (NPC) getSlaves().get(index-indexPresentStart);
					return LilayaHomeGeneric.interactWithNPC(character);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_CORE_BAR_DRINK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Decide what to do next...", SPA_CORE);
			}
			return null;
		}
	};

	public static final DialogueNode SPA_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene, and populate slaves for sex:
			slavesSex = new ArrayList<>();
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
				if(slave.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_STRIP_TO_BATHE)) {
					bathingStripped.add(slave);
					slave.unequipAllClothingIntoHoldingInventory(slave, false, false);
				}
				if(slavesSex.size()<3) {
					slavesSex.add(slave);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(bathingStripped.contains(Main.game.getPlayer())), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING"));
			sb.append(getBathingSlavesDescription(slavesWashing));
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_END", slavesWashing));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathingSlots = new SexSlot[] {
					SexSlotSitting.SITTING_IN_LAP,
					SexSlotSitting.SITTING_TWO,
					SexSlotSitting.SITTING_THREE};
			
		    List<GameCharacter> slavesSexNoNulls = new ArrayList<>(slavesSex);
		    slavesSexNoNulls.removeIf(s->s==null);
			
			if(index==1) {
				return new Response("Finish", "Finish with your bathing session and get out of the pool.", BATHING_FINISHED);
				
			} else if(index==2) {
				if(slavesSex.isEmpty()) {
					return new Response("Sex", "You do not have any slaves assigned to work in the spa with the bathing permission, so there's nobody for you to have sex with...", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("Sex", "You need to select at least one slave to have sex with...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesSexNoNulls.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(slavesSexNoNulls.get(i), bathingSlots[i]);
				}
//				UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
				List<GameCharacter> notAttractedList = new ArrayList<>();
				List<String> notAttractedNamesList = new ArrayList<>();
				
				for(GameCharacter slaveTarget : slavesSexNoNulls) {
					if(!slaveTarget.isAttractedTo(Main.game.getPlayer())) {
						notAttractedList.add(slaveTarget);
						notAttractedNamesList.add(UtilText.parse(slaveTarget, "[npc.name]"));
					}
				}
				
				if(!Main.game.isNonConEnabled() && !notAttractedList.isEmpty()) {
					return new Response("Sex",
							notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "[npc.Name] is not attracted to you, and so [npc.she] isn't willing to let you have sex with [npc.herHim]...")
								:Util.stringsToStringList(notAttractedNamesList, true)+" are not attracted to you, and so they aren't willing to let you have sex with them...",
							null);
				}
				return new ResponseSex(slavesSexNoNulls.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()))?"Rape":"Sex",
						(slavesSexNoNulls.size()==1
							?UtilText.parse(slavesSexNoNulls, "Have dominant sex with [npc.name].")
							:(slavesSexNoNulls.size()==2
								?UtilText.parse(slavesSexNoNulls, "Have dominant sex with [npc.name] while [npc2.name] sits beside you.")
								:UtilText.parse(slavesSexNoNulls, "Have dominant sex with [npc.name] while [npc2.name] and [npc3.name] sit beside you.")))
						+(notAttractedList.isEmpty()
							?""
							:(notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "<br/>[style.italicsBad([npc.Name] is not attracted to you, and so would consider this to be rape...)]")
								:"<br/>[style.italicsBad("+Util.stringsToStringList(notAttractedNamesList, true)+" are not attracted to you, and so would consider this to be rape...)]")),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								slaveSlots) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_DOM", slavesSexNoNulls));
					}
				};
				
			} else if(index==3) {
				if(slavesSex.isEmpty()) {
					return new Response("Submissive sex", "You do not have any slaves assigned to work in the spa with the bathing permission, so there's nobody for you to have submissive sex with...", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("Submissive sex", "You need to select at least one slave to have sex with...", null);
				}
				
				if(!slavesSexNoNulls.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Submissive sex",
							slavesSexNoNulls.size()==1
								?UtilText.parse(slavesSexNoNulls, "As [npc.name] isn't attracted to you, [npc.she] is unwilling to be the dominant partner in sex...")
								:"As your slaves aren't attracted to you, they are unwilling to be the dominant partners in sex...",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesSexNoNulls.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(attractedSlaves.get(i), bathingSlots[i]);
				}
				return new ResponseSex("Submissive sex",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Let [npc.name] dominantly fuck you in the pool.")
										:"Let your slaves dominantly fuck you in the pool.",
						true, true,
						new SMBath(SexPosition.SITTING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_SUB", attractedSlaves));
					}
				};
			}
			if(slavesSex.size()>0) {
				if(index==11) {
					GameCharacter target = slavesSex.get(0);
					return new ResponseEffectsOnly(
							target==null
								?"Target: [style.boldDisabled(None)]"
								:UtilText.parse(target, "Target: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Cycle the primary targeted character to have sex with.") {
						@Override
						public void effects() {
							setNextSexTarget(0);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==12 && slavesSex.size()>1) {
					GameCharacter target = slavesSex.get(1);
					return new ResponseEffectsOnly(
							target==null
								?"Target: [style.boldDisabled(None)]"
								:UtilText.parse(target, "Target: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Cycle the secondary targeted character to have sex with.") {
						@Override
						public void effects() {
							setNextSexTarget(1);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==13 && slavesSex.size()>2) {
					GameCharacter target = slavesSex.get(2);
					return new ResponseEffectsOnly(
							target==null
								?"Target: [style.boldDisabled(None)]"
								:UtilText.parse(target, "Target: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Cycle the tertiary targeted character to have sex with.") {
						@Override
						public void effects() {
							setNextSexTarget(2);
							Main.game.updateResponses();
						}
					};
				}
			}
			return null;
		}
	};
	
	private static void setNextSexTarget(int index) {
		GameCharacter current = slavesSex.get(index);
		if(slavesSex.get(index)==null) {
			slavesSex.remove(index);
			slavesSex.add(index, slavesWashing.get(0));
		} else {
			for(int i=0; i<slavesWashing.size();i++) {
				if(slavesWashing.get(i).equals(current)) {
					slavesSex.remove(index);
					if(i==slavesWashing.size()-1) {
						slavesSex.add(index, null);
					} else {
						slavesSex.add(index, slavesWashing.get(i+1));
					}
					break;
				}
			}
		}
		
		Set<GameCharacter> excludedCharacters = new HashSet<>();
		GameCharacter newTarget = slavesSex.get(index);
		if(newTarget!=null) {
			excludedCharacters.add(newTarget);
		}
		
		for(int i=0; i<slavesSex.size(); i++) {
			if(i!=index) {
				if(excludedCharacters.contains(slavesSex.get(i))) {
					slavesSex.remove(i);
					slavesSex.add(i, current);
				}
			}
		}
	}

	public static final DialogueNode BATHING_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			for(GameCharacter character : bathingStripped) {
//				character.equipAllClothingFromHoldingInventory();
//			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "Having had [npc.her] fun, [npc.name] reminds you that you have other things you need to be getting on with...");
			} else {
				return "Having had their fun, your slaves remind you that you have other things you need to be getting on with...";
			}
		}
		@Override
		public String getContent() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_AFTER_SEX", sexSlaves);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE_BATHING.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE_BATHING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BATHING_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter character : bathingStripped) {
				character.equipAllClothingFromHoldingInventory();
			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_FINISHED", slavesWashing));
			sb.append(SPA_CORE.getContent());
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_MASSAGE_SELECTION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			slavesForMassage = new ArrayList<>(getSlaves());
			slavesForMassage.removeIf(s -> !s.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE));
		}
		@Override
		public String getContent() {
			List<String> names = new ArrayList<>();
			for(GameCharacter npc : slavesForMassage) {
				names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
			}
			
			UtilText.addSpecialParsingString(String.valueOf(slavesForMassage.size()), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION", slavesForMassage);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide against having a massage after all...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION_BACK"));
					}
				};
			}
			if(index-1<slavesForMassage.size()) {
				GameCharacter slave = slavesForMassage.get(index-1);
				return new Response(UtilText.parse(slave, "[npc.Name]"),
						UtilText.parse(slave,
								"Lie down on one of the loungers and have [npc.name] give you a massage."
								+ (slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())
									?(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)
										?"<br/>[style.italicsSex(As [npc.name] has permission to use you for sex, and as [npc.she] is currently pent up, [npc.she] is certain to start fucking you during the massage!)]"
										:"<br/>[style.italicsSex(As [npc.name] has permission to use you for sex, [npc.she] might start fucking you during the massage!)]")
									:"")),
						SPA_MASSAGE) {
					@Override
					public Colour getHighlightColour() {
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							return PresetColour.GENERIC_SEX;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						massageSlave = slave;
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							massageSlaveSex = slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE) || Math.random()<0.5f;
						} else {
							massageSlaveSex = false;
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(massageSlaveSex) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX", massageSlave);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE", massageSlave);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(massageSlaveSex) {
				if(index==1) {
					return new ResponseSex(
							"Fucked",
							UtilText.parse(massageSlave, "[npc.Name] has you pinned down on the lounger, ready to start fucking you..."),
							true,
							false,
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FUCKED", massageSlave));	
				}
				
			} else {
				if(index==1) {
					return new Response("Continue", "Now that you've had a nice relaxing massage, you wonder what to do next...", SPA_CORE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FINISHED", massageSlave));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isNonConEnabled() && !massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex", UtilText.parse(massageSlave, "[npc.Name] is not attracted to you, and so [npc.she] isn't willing to let you have sex with [npc.herHim]..."), null);
					}
					return new ResponseSex(
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?"Rape"
								:"Sex",
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?UtilText.parse(massageSlave, "Push [npc.name] down onto the lounger and rape [npc.herHim].")
								:UtilText.parse(massageSlave, "Push [npc.name] down onto the lounger and have dominant sex with [npc.herHim]."),
							true,
							false,
							new SexManagerDefault(massageSlave.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), massageSlave.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(massageSlave, massageSlave.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_DOM", massageSlave));
					
				} else if(index==3) {
					if(!massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex", UtilText.parse(massageSlave, "As [npc.name] is not attracted to you, [npc.she] is not willing to take the dominant role in having sex with you..."), null);
					}
					return new ResponseSex(
							"Submissive sex",
							UtilText.parse(massageSlave, "Tell [npc.name] to fuck you on the lounger..."),
							true,
							false,
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_SUB", massageSlave));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MASSAGE_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(massageSlave, "Having had [npc.her] fun, [npc.name] reminds you that you have other things you need to be getting on with...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX", massageSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had an unexpectedly fun massage, you wonder what to do next...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX_FINISHED", massageSlave));
					}
				};
			}
			return null;
		}
	};
	
	
	// Guest content:
	
	public static final DialogueNode SPA_GUEST_INVITE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_INVITE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("To the spa", UtilText.parse(guest, "Accompany [npc.name] to the spa."), SPA_GUEST_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_SPA);
						guest.setLocation(Main.game.getPlayer());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			if(guest.isFeminine()) {
				AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
				AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing microBikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				AbstractClothing microBikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				
				if(index==1) {
					if(guest.isAbleToEquip(swimsuit, true, guest)) {
						return new Response("Swimsuit", UtilText.parse(guest, "Tell [npc.name] to wear a modest swimsuit."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimsuit, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIMSUIT", guest));
							}
						};
					} else {
						return new Response("Swimsuit", UtilText.parse(guest, "[npc.Name] cannot wear a swimsuit, as some of [npc.her] sealed clothing is blocking [npc.herHim] from doing so..."), null);
					}
				}
				if(index==2) {
					if(guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest)) {
						return new Response("Bikni", UtilText.parse(guest, "Tell [npc.name] to wear a bikini."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(bikiniTop, true, guest);
								guest.equipClothingFromNowhere(bikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_BIKINI", guest));
							}
						};
					} else {
						return new Response("Bikni", UtilText.parse(guest, "[npc.Name] cannot wear a bikini, as some of [npc.her] sealed clothing is blocking [npc.herHim] from doing so..."), null);
					}
				}
				if(index==3) {
					if(guest.isAbleToEquip(microBikiniTop, true, guest) && guest.isAbleToEquip(microBikiniBottom, true, guest)) {
						return new Response(
								guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.colourBad(Micro bikini)]"
									:"Micro bikini",
								UtilText.parse(guest, "Tell [npc.name] to wear a highly revealing micro bikini."
										+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
											?"[style.italicsBad(As [npc.name] has a negative desire towards the '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' fetish, [npc.she] will not like this!)]"
											:"")),
								SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(microBikiniTop, true, guest);
								guest.equipClothingFromNowhere(microBikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_MICRO_BIKINI", guest));
								if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
									Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
								}
							}
						};
					} else {
						return new Response("Micro bikini", UtilText.parse(guest, "[npc.Name] cannot wear a micro bikini, as some of [npc.her] sealed clothing is blocking [npc.herHim] from doing so..."), null);
					}
				}
				if(index==4) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(Naked)]"
								:"Naked",
							UtilText.parse(guest, "Tell [npc.name] to strip out of all of [npc.her] clothes and head into the spa naked."
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(As [npc.name] has a negative desire towards the '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' fetish, [npc.she] will not like this!)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
				
			} else {
				AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
				if(index==1) {
					if(guest.isAbleToEquip(swimShorts, true, guest)) {
						return new Response("Swimming shorts", UtilText.parse(guest, "Tell [npc.name] to wear swimming shorts."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimShorts, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIM_SHORTS", guest));
							}
						};
					} else {
						return new Response("Swimming shorts", UtilText.parse(guest, "[npc.Name] cannot wear swimming shorts, as some of [npc.her] sealed clothing is blocking [npc.herHim] from doing so..."), null);
					}
				}
				if(index==2) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(Naked)]"
								:"Naked",
							UtilText.parse(guest, "Tell [npc.name] to strip out of all of [npc.her] clothes and head into the spa naked."
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(As [npc.name] has a negative desire towards the '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' fetish, [npc.she] will not like this!)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Don't strip", "Decide against going naked and continue on into the spa as you are.", SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = false;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_CONTINUE", guest));
					}
				};
			}
			if(index==2) {
				return new Response("Go naked",
						"Strip out of all of your clothes and head into the spa naked.",
						SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = true;
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_NAKED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Pools", UtilText.parse(guest, "Lead [npc.name] through into the pools."), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL_POOLS", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE", guest);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finish", "Finish with your bathing session and get out of the pool.", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Sex",
							UtilText.parse(guest, "[npc.Name] is not attracted to you, and so [npc.she] isn't willing to let you have sex with [npc.herHim]..."),
							null);
				}
				return new ResponseSex("Sex",
						UtilText.parse(guest, "Have dominant sex with [npc.name]."),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SEX_START", guest));
				
			} else if(index==3) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Submissive sex",
							UtilText.parse(guest, "[npc.Name] is not attracted to you, and so [npc.she] isn't willing to let you have sex with [npc.herHim]..."),
							null);
				}
				return new ResponseSex("Submissive sex",
						UtilText.parse(guest, "Let [npc.name] take charge and fuck you right here in the pool."),
						true, true,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SUBMISSIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_BATHING_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "Having had [npc.her] fun, [npc.name] asks if you'd like to do something else...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Finish with your bathing session and get out of the pool.", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX_END", guest));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_GIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(guest, "Now that you've given [npc.name] a nice relaxing massage, you wonder what to do next..."), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response(UtilText.parse(guest, "Fuck [npc.herHim]"),
							UtilText.parse(guest, "As [npc.name] is not attracted to you, [npc.she] is not willing to have sex with you..."),
							null);
				}
				return new ResponseSex(
						UtilText.parse(guest, "Fuck [npc.herHim]"),
						UtilText.parse(guest, "Give [npc.name] what it is that [npc.she] so clearly wants from you..."),
						true,
						false,
						new SexManagerDefault(guest.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), guest.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(guest, guest.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "massage table";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_RECEIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had a nice relaxing massage, you wonder what to do next...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_END", massageSlave));
					}
				};
				
			}
			if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Fucked",
							UtilText.parse(guest, "As [npc.name] is not attracted to you, [npc.she] is not willing to have sex with you..."),
							null);
				}
				return new ResponseSex(
						"Fucked",
						UtilText.parse(guest, "Tell [npc.name] that [npc.she] can fuck you..."),
						true,
						false,
						new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(guest, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "massage table";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "Having had [npc.her] fun, [npc.name] asks if you'd like to do something else...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had an unexpectedly fun massage, you wonder what to do next...", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX_FINISHED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.equipAllClothingFromHoldingInventory();
			guest.returnToHome();
			if(playerStripped) {
				Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			}
			Main.game.getPlayer().setNearestLocation(PlaceType.LILAYA_HOME_CORRIDOR);

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_END", guest));
			
			LilayaSpa.guest = null;
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Now that you've had an unexpectedly fun massage, you wonder what to do next...", PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false));
			}
			return null;
		}
	};
	
	
	
	
	//TODO
	// Expansion:
	

	public static final DialogueNode SPA_SAUNA_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Commit", "Commit to extending the spa and adding a sauna extension.", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Change mind", "Tell Lilaya that you've changed your mind...", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_SAUNA.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_SAUNA_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_SAUNA = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sauna", "Spend some time in the sauna.", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	

	public static final DialogueNode SPA_POOL_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Commit", "Commit to extending the spa and adding a sauna extension.", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Change mind", "Tell Lilaya that you've changed your mind...", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_POOL.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_POOL_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_POOL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Swim", "Go for a swim.", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	
}
