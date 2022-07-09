package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.submission.SMVengarDominantSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensDialogue {
	
	private static NPC gambler;
	private static final int PERSUASION_PAYMENT = 10_000;
	
	public static void init() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
		
		// Spawn guard rats if missing:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.M_P_MALE);
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "lieutenant", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GREEN_DRAB, PresetColour.CLOTHING_GUNMETAL)));
				rat.incrementEssenceCount(8, false);
				
				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "sidekick", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_KHAKI, PresetColour.CLOTHING_STEEL)));
				rat.incrementEssenceCount(3, false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Spawn humans:
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM).isEmpty()) {
			spawnMilkers();
		}
		
		// Spawn bar-tender:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				if(rat.hasVagina()) {
					rat.setVaginaSquirter(true);
				}
				if(!rat.hasPenis() && !rat.hasVagina()) {
					rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, true));
				}
				Main.game.addNPC(rat, false);
				rat.setLevel(4);
				rat.addFetish(Fetish.FETISH_ORAL_RECEIVING);
				if(rat.hasPenis()) {
					rat.addFetish(Fetish.FETISH_PENIS_GIVING);
				}
				if(rat.hasVagina()) {
					rat.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
				Main.game.getCharacterUtils().setGenericName(rat, rat.isFeminine()?"barwoman":"barman", null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
	}
	
	public static void applyCombatDefeatFlagsReset() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
	}
	
	public static String applyConflictQuestEnd() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		return Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END);
	}
	
	/**
	 * @return A list with the player's main companion in index 0, with gang members present being in slots after that.
	 */
	public static List<GameCharacter> getGuards(boolean includeCompanion) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof RatGangMember));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			guards.add(0, Main.game.getPlayer().getMainCompanion());
		}
		return guards;
	}
	
	public static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive && !Main.game.getPlayer().getCompanions().contains(milker)) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void banishGuards(boolean includeLeaderGuards) {
		for(GameCharacter guard : getGuards(false)) {
			if(includeLeaderGuards || guard.getLevel()<9) {
				Main.game.banishNPC((NPC) guard);
				
			} else {
				guard.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
			}
		}
	}
	
	public static void banishTwoGuards() {
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
	}
	
	public static void applyRatWarrensRaid() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);

		((Shadow)Main.game.getNpc(Shadow.class)).moveToBountyHunterLodge();
		((Silence)Main.game.getNpc(Silence.class)).moveToBountyHunterLodge();
		
		Main.game.getNpc(Shadow.class).removeItemByType(ItemType.RESONANCE_STONE);
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Vengar.class));
		Main.game.getNpc(Vengar.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Vengar.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Murk.class));
		Main.game.getNpc(Murk.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Murk.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);
		Main.game.getNpc(Murk.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);
		Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -100);
		Main.game.getNpc(Murk.class).setAffection(Main.game.getPlayer(), -100);
		
		Main.game.getPlayer().removeItemByType(ItemType.RESONANCE_STONE);

		List<NPC> ratGuards = new ArrayList<>(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE));
		ratGuards.addAll(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN));
		for(NPC ratGuard : ratGuards) {
			if(ratGuard instanceof RatGangMember && !Main.game.getPlayer().getCompanions().contains(ratGuard)) {
				Main.game.banishNPC(ratGuard);
			}
		}
	}
	
	public static void spawnMilkers() {
		try {
			String[] adjectives = new String[] {"brainwashed", "submissive", "obedient", "docile"};
			for(int i=0;i<4;i++) {
				NPC human = new RatWarrensCaptive(Gender.F_V_B_FEMALE);
				Main.game.addNPC(human, false);
				human.setGenericName(adjectives[i]+" milker");
				human.setAffection(Main.game.getNpc(Murk.class), 100);
				Main.game.getNpc(Murk.class).calculateGenericSexEffects(true, true, human, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.NO_DESCRIPTION_NEEDED);
				Main.game.getNpc(Murk.class).fillCumToMaxStorage();
				human.clearFluidsStored(SexAreaOrifice.VAGINA);
				human.calculateStatusEffects(1);
				AbstractItem milk = Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK);
				human.useItem(milk, human, false);
				if(human.isPregnant()) {
					if(Math.random()<0.75f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.5f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.25f) {
						human.useItem(milk, human, false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void banishMilkers() {
		for(GameCharacter milker : getMilkers()) {
			Main.game.banishNPC((NPC) milker);
		}
	}
	
	
	private static void spawnGuards(boolean withLeader, int totalRatsToSpawn) {
		try {
			List<String> adjectives = new ArrayList<>();
			if(withLeader) {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(Main.game.getPlayer(), true);
				rat.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
				rat.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
				rat.setGenericName(Util.randomItemFrom(Util.newArrayListOfValues("buff", "strong", "muscular", "muscly"))+" lieutenant");

				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, "sidekick", adjectives));
				
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, false);
				
			} else {
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, true);
			}
			for(int i=0;i<(withLeader?totalRatsToSpawn-2:totalRatsToSpawn);i++) {
				String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(i==1, i==2));
				Main.game.addNPC(rat, false);
				rat.setLevel(8-i);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static NPC generateGambler(Femininity femininity) {
		NPC gambler = new RatGangMember(Gender.getGenderFromUserPreferences(femininity));
		if(Math.random()<0.5f) { // 50% chance for them to be a cheater
			List<Dice> dice = new ArrayList<>();
			int weightedFace = Util.random.nextInt(6)+1;
			for(int i=0; i<5; i++) {
				dice.add(new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.getFaceFromInt(weightedFace), 5f))));
			}
			gambler.setDice(dice);
		}
		try {
			Main.game.addNPC(gambler, false);
			Main.game.getCharacterUtils().setGenericName(gambler, "gambler", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
		return gambler;
	}

	private static GameCharacter getGuardLeader() {
		if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Murk.class))) {
			return Main.game.getNpc(Murk.class);
		}
		return getGuards(false).get(0);
	}
	
	private static GameCharacter getMainCompanion() {
		if(Main.game.getPlayer().hasCompanions()) {
			return Main.game.getPlayer().getMainCompanion();
		}
		return null;
	}
	
	private static boolean isCompanionDialogue() {
		return getMainCompanion()!=null;
	}
	
	private static String applyCaptivity(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		Main.game.addSavedInventory(character);
		
		int essences = character.getEssenceCount();
		character.setInventory(new CharacterInventory(0));
		character.setEssenceCount(essences);

		Main.game.getPlayer().setCaptive(true);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		
		return "<p style='text-align:center;'>"+RatWarrensCaptiveDialogue.equipCollar(character, equipper, collarColour)+"</p>";
	}
	
	private static int getRumPrice() {
		return ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum").getValue(null)/2;
	}

	private static boolean isMouthAccess(GameCharacter target) {
		return target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	private static boolean isAssAccess(GameCharacter target) {
		return Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}

	private static boolean isVaginaAccess(GameCharacter target) {
		return target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	private static String getCooperationWarning() {
		return "<br/>[style.italicsSideQuest(You will be locked into the non-violent quest route if you select this option.)]";
	}
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY = new DialogueNode("Entrance", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_WHORE", getGuards(true));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INITIAL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) { // They want sex again:
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) { // They let you in again:
				if(index==1) {
					return new Response("Confidential", "Tell the rats that your business is private, and that Vengar will be angry if they delay your entry.", ENTRANCE_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT_ACCESSED", getGuards(true)));
						}
					};
				}
			}
			
			if(index==1) {
				return new Response("Axel", "Tell the gang members that you're here to discuss the matter of Axel's payments with Vengar.", ENTRANCE_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntry, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_AXEL", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new Response("Whore", "Tell the gang members that you're a whore who's been hired to pleasure Vengar.", RAT_WARREN_INITIAL_ENTRY_WHORE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntryWhore, true);
					}
				};
				
			} else if(index==3) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY_WHORE = new DialogueNode("Entrance", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse", "Tell the gang members that Vengar would be upset with them if he didn't get to be the first to fuck you.", ENTRANCE_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_NO_SEX", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"Sex (solo)"
							:"Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Tell the gang members that you'd be happy to show them what you're capable of, but that [npc.name] is going to save [npc.herself] for Vengar...")
							:"Tell the gang members that you'd be happy to show them what you're capable of...",
						Util.newArrayListOfValues(
								Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								getGuards(false),
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(
										getMainCompanion())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						AFTER_ENTRANCE_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_SOLO", getGuards(true)));
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Sex (both)"),
							UtilText.parse(getGuards(true), "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name] or [npc3.name], and you can't force [npc.herHim] into it..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Sex (both)"),
							UtilText.parse(getGuards(true), "Tell the gang members that both you and [npc.name] would be happy to show them what you're capable of."),
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companion),
									null,
									null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_BOTH", getGuards(true)));
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getGuards(true), "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and you can't force [npc2.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMainCompanion(), "Tell the gang members that you'd be happy to let [npc.name] show them what [npc.sheIs] capable of, but that you're going to save yourself for Vengar..."),
							Util.newArrayListOfValues(
									Fetish.FETISH_VOYEURIST),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_COMPANION", getGuards(true)));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_ENTRANCE_SEX = new DialogueNode("Finished", "The gang members have finished...", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(Main.sex.getSubmissiveSpectators().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_COMPANION_WATCHING", getGuards(true));
				} else if(Main.sex.getSubmissiveSpectators().contains(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_PLAYER_WATCHING", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_BOTH", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_SOLO", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_FIGHT = new DialogueNode("", "", true) {
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
				return new ResponseCombat("Fight",
						"Defend yourself against the rats!",
						(NPC)getGuardLeader(),
						getGuards(false),
						new HashMap<>());
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT) {
//				Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
//			}
//			banishTwoGuards();
		}
		
		@Override
		public String getContent() {
			if(getGuards(false).size()==1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE_REMAINING", getGuards(false));
				
			} else if(getGuards(false).isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ALL");
				
			} else if(getGuards(false).size()<4) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE", getGuards(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getGuards(false).isEmpty()) {
				if(index==0) {
					return "Interactions";
					
				} else if(index==1) {
					return "Inventories";
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("Continue", "As you've enslaved all of the gang members who dared to fight you, there's nothing left to do but continue on your way...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Scare off", "Tell the gang members to get out of here while they still can...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(false)));
							banishGuards(true);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo sex"
								:"Sex",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the gang members.")
								:"Have sex with the gang members.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX", getGuards(false)));
					
				} else if (index == 3) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo sex (Gentle)"
								:"Sex (Gentle)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the gang members.")
								:"Have sex with the gang members.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GENTLE", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
					
				} else if (index == 4) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo sex (Rough)"
								:"Sex (Rough)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the gang members.")
								:"Have sex with the gang members.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_ROUGH", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
					
				} else if (index == 5) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo submission"
								:"Submit",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you submit to the gang members, allowing them to have dominant sex with you.")
								:"Tell the gang members that they can have their way with you...",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null,
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null,
							true,
							false,
							getGuards(false),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							Util.newArrayListOfValues(getMainCompanion()),
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_SUBMIT", getGuards(false)));
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("Group sex",
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with one or more of the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Group sex"),
								UtilText.parse(companion, "Have dominant sex with the gang members, and get [npc.name] to join in with the fun."),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getGuards(false),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP", getGuards(false)));
					}
					
				} else if (index == 7 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("Group submission",
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with one or more of the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Group submission"),
								UtilText.parse(companion, "Get [npc.name] to join you in submitting to the gang members, allowing them to have dominant sex with the two of you."),
								true,
								false,
								getGuards(false),
								Main.game.getPlayer().getParty(),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP_SUBMIT", getGuards(false)));
					}
					
				} else if (index == 8 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with one or more of the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the gang members while you watch."),
								false,
								false,
								Util.newArrayListOfValues(getMainCompanion()),
								getGuards(false),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GIVE_TO_COMPANION", getGuards(false)));
					}
					
				} else if (index == 9 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedToGroup(getGuards(false)) && ((companion.isAbleToRefuseSexAsCompanion()) || !Main.game.isNonConEnabled())) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the gang members, and you can't force [npc.herHim] to do so..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "Hand [npc.name] over to the gang members, and watch as they have sex with [npc.herHim]."),
								true,
								false,
								getGuards(false),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_OFFER_COMPANION", getGuards(false))) {
							@Override
							public void effects() {
								if(!companion.isAttractedToGroup(getGuards(false)) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else if(responseTab == 1) {
				for(int i=1; i<=getGuards(false).size(); i++) {
					if(index==i) {
						NPC guard = (NPC) getGuards(false).get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(guard, "[npc.Name]"),
								UtilText.parse(guard, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(guard, InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY_AFTER_SEX = new DialogueNode("Step back", "Now that you've had your fun, you step back and wonder what to do with the gang members...", true) {

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_BOTH", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_SOLO", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_COMPANION", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARD_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("Continue", "As you've enslaved all of the gang members who dared to fight you, there's nothing left to do but continue on your way...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Scare off", "Scare the gang members off and continue on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishGuards(true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(true)));
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARD_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Thrown out", "The gang members unceremoniously throw you back out into Submission's tunnels.", PlaceType.SUBMISSION_RAT_WARREN.getDialogue(false)) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_THROWN_OUT", getGuards(true)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
							applyCombatDefeatFlagsReset();
							List<GameCharacter> guards = getGuards(false);
							for(GameCharacter npc : guards) {
								Main.game.banishNPC((NPC) npc);
							}
							exit();
						}
					};
				}
				
			} else {
				if(index == 1) {
					if(isCompanionDialogue()) {
						return new Response("Save [com.name]",
								"Use the last of your energy to hold off the rats long enough for [com.name] to escape."
										+ "<br/>[style.italicsMinorGood([com.Name] will safely return home.)]",
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_COMPANION_ESCAPE", getGuards(true)));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();
								
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("Dragged away",
								UtilText.parse(getGuards(false), "You are powerless to resist as the gang members drag you off deeper into their lair..."),
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	private static SexManagerInterface getStocksManager(List<GameCharacter> guards, SexPace pace) {
		return new SexManagerDefault(
				SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(
						new Value<>(guards.get(0), SexSlotMilkingStall.RECEIVING_ORAL),
						isCompanionDialogue()
							?new Value<>(guards.get(1), SexSlotMilkingStall.RECEIVING_ORAL_TWO)
							:null),
				Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL),
						isCompanionDialogue()
							?new Value<>(getMainCompanion(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO)
							:null)) {
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(guards.contains(character)) {
					if(character.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
						
					} else if(character.hasVagina()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter guard : guards) {
					map.put(guard, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA));
				}
				return map;
			}
			@Override
			public void assignNPCTarget(GameCharacter targeter) {
				if(isCompanionDialogue()) {
					if(targeter.equals(guards.get(0))) {
						Main.sex.setTargetedPartner(targeter, Main.game.getPlayer());
						
					} else if(isCompanionDialogue() && targeter.equals(guards.get(1))) {
						Main.sex.setTargetedPartner(targeter, getMainCompanion());
						
					} else {
						super.assignNPCTarget(targeter);
					}
					
				} else {
					super.assignNPCTarget(targeter);
				}
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer() || character.equals(getMainCompanion())) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				if(character.isPlayer() || character.equals(getMainCompanion())) {
					return false;
				}
				return super.isAbleToRemoveOthersClothing(character, clothing);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return !equippingCharacter.isPlayer() && !equippingCharacter.equals(getMainCompanion());
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return !character.isPlayer() && !character.equals(getMainCompanion());
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					return pace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS = new DialogueNode("", "", true) {
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
			if (index == 1) {
				return new Response("Stripped",
						UtilText.parse(getGuards(false), "Murk and [npc.name] set about stripping you naked..."),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("New home",
						UtilText.parse(getGuards(false), "Murk and [npc.name] lead you into the adjoining room to introduce you to your 'new home'..."),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END) {
					@Override
					public void effects() {
						List<GameCharacter> parsingCharacters = new ArrayList<>(getGuards(true));
						parsingCharacters.addAll(getMilkers());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END", parsingCharacters));
						
						List<GameCharacter> guards = getGuards(true);
						for(GameCharacter npc : guards) {
							npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						}
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	private static List<InitialSexActionInformation> getInitialCapturedStartSexActions(List<GameCharacter> guards) {
		List<InitialSexActionInformation> list = new ArrayList<>();
		
		if(guards.get(0).hasPenis()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
			
		} else if(guards.get(0).hasVagina()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
		}

		if(isCompanionDialogue()) {
			if(guards.get(1).hasPenis()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), PenisMouth.BLOWJOB_START, false, true));
				
			} else if(guards.get(1).hasVagina()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
			}
		}
		
		return list;
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> guards = getGuards(false);
			
			if (index == 1) {
				return new ResponseSex("Oral",
						UtilText.parse(getGuards(false), "Do what's expected of you and obediently perform oral on [npc.name]."),
						false,
						false,
						getStocksManager(guards, null),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Eager oral",
						UtilText.parse(getGuards(false), "Make a show of how willing you are to obediently perform oral on [npc.name]."),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_EAGER),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_EAGER", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Resist oral",
						UtilText.parse(getGuards(false), "Do your best to resist performing oral on [npc.name]."),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_RESISTING),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_RESIST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return UtilText.parse(getGuards(false).get(0), "[npc.Name] has finished having [npc.her] way with you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Murk", "Murk reappears to tell you what's in store for you next...", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0", getGuards(false)));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRANCE_NO_CONTENT = new DialogueNode("Entrance", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Entrance", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
					return new Response("Leave", "The rats have sealed the doors, preventing your escape! You'll have to confront Vengar in order to get out of here now...", null);
				}
				return new Response("Leave", "Tell the gang members that you're leaving, and head back out into Submission's tunnels.", SubmissionGenericPlaces.RAT_WARREN) {
					@Override
					public void effects() {
						exit();
					}
				};
				
			} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==6
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Twisting Passageway", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
				
			} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
			}
		}
		@Override
		public boolean isTravelDisabled() {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				return true;
			}
			return false;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else {
					if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_RIGHT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
				}
				
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_HOSTILE_WARNING", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR", getGuards(true)));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				if(index==1) {
					return new ResponseCombat("Fight",
							"The gang members aren't going to let you go without a fight, so you're forced to defend yourself!",
							(NPC)getGuardLeader(),
							getGuards(false),
							new HashMap<>());
				}
			}
			return null;
		}
	};

	public static final DialogueNode DORMITORY = new DialogueNode("Dormitory", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY", getGuards(true)));
			
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
					|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_OCCUPIED", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_CLEARED", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& ((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)))) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true, 4);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DICE_DEN = new DialogueNode("Dice Den", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_ENTRY", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensLootedDiceDen)) {
						return new Response("Loot bar", "You've already taken everything of value from behind the bar...", null);
						
					} else {
						return new Response("Loot bar", "You've already taken everything of value from behind the bar...", DICE_DEN_LOOT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensLootedDiceDen, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1500));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), 5, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), 2, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy"), 1, false, true));
							}
						};
					}
				}
				
			} else {
				DicePokerTable table = DicePokerTable.COPPER;
				int buyIn = table.getInitialBet()+table.getRaiseAmount();
				
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourMasculine(Rat-boy)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"Start playing dice poker with one of the rat-boys. The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.MASCULINE);
								gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("Rat-boy ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play with the rat-boy!",
								null);
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourFeminine(Rat-girl)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"Start playing dice poker with one of the rat-girls. The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.FEMININE);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("Rat-girl ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play with the rat-girl!",
								null);
					}
					
				} else if(index==3) {
					return new Response("Rules", UtilText.parse(getGuards(false).get(0), "Ask [npc.name] what the rules of dice poker are."), DICE_DEN_RULES);

				} else if(index==4) {
					int price = getRumPrice();
					if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("Rum ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't drink a glass of rum, as your mouth is blocked...", null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Rum ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford to buy a glass of rum from the rat behind the bar...", null);
					}
					return new Response("Rum ("+UtilText.formatAsMoney(price, "span")+")", "Buy a glass of rum from the rat behind the bar.", DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==5 && isCompanionDialogue()) {
					int price = getRumPrice();
					if(!getMainCompanion().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("Rum ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
								UtilText.parse(getMainCompanion(), "[npc.Name] can't drink a glass of rum, as [npc.her] mouth is blocked..."),
								null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Rum ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
								UtilText.parse(getMainCompanion(), "You can't afford to buy a glass of rum for [npc.name] from the rat behind the bar..."),
								null);
					}
					return new Response("Rum ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoney(price, "span")+")",
							UtilText.parse(getMainCompanion(), "Buy a glass of rum for [npc.name] from the rat behind the bar."),
							DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK_COMPANION", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getMainCompanion(), getMainCompanion())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(false, 1); // Set to 1 as the bar-tender makes the 2nd, and the entrance guards make 3rd and 4th
							for(GameCharacter rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
								rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DICE_DEN_LOOT = new DialogueNode("Dice Den", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_LOOT", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_RULES = new DialogueNode("Dice Den", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RULES", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Rules", UtilText.parse(getGuards(false).get(0), "You are already asking [npc.name] what the rules of dice poker are."), DICE_DEN_RULES);
			}
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_POST_GAMBLING = new DialogueNode("Dice Den", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.banishNPC(gambler);
			gambler = null;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_POST_GAMBLING", getGuards(true)));
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DICE_DEN_RUM = new DialogueNode("Dice Den", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC bartender = (NPC) getGuards(false).get(0);
			if(index==1) {
				return new Response("Refuse", UtilText.parse(bartender, "Refuse [npc.namePos] offer..."), DICE_DEN);
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Accept", UtilText.parse(bartender, "As you cannot access your mouth, you cannot perform oral on [npc.name] in order to get your flames back..."), null);
				}
				return new ResponseSex(
						"Accept",
						UtilText.parse(bartender, "Get paid "+getRumPrice()+" flames to perform oral on [npc.name]..."),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(!Main.sex.isDom(character)) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(bartender.hasPenis()) {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								} else {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								}
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(character.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}	
						},
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_DICE_DEN_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(bartender.hasPenis()) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
						}
					}
				};
				
			} else if (index==3 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Offer [com.name]", UtilText.parse(companion, bartender, "As [npc.name] cannot access [npc.her] mouth, [npc.she] cannot perform oral on [npc2.name] in order to get your flames back..."), null);
				}
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(companion, bartender, "You can tell that [npc.name] isn't at all interested in performing oral on [npc2.name], and you can't force [npc.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, bartender, "Get paid "+getRumPrice()+" flames to have [npc.name] perform oral on [npc2.name]..."),
									true,
									false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(companion, SexSlotStanding.PERFORMING_ORAL))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(!Main.sex.isDom(character)) {
												return SexControl.ONGOING_ONLY;
											}
											return super.getSexControl(character);
										}
										@Override
										public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
											return false;
										}
										@Override
										public boolean isPositionChangingAllowed(GameCharacter character) {
											return false;
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
										}
										@Override
										public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
											return false;
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											if(bartender.hasPenis()) {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											} else {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											}
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(!character.equals(companion)) {
												if(character.hasPenis()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
											return super.getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
											return new ArrayList<>();
										}	
									},
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_DICE_DEN_ORAL,
									UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL_COMPANION", getGuards(true))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(bartender.hasPenis()) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, PenisMouth.BLOWJOB_START, true, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
									}
								}
							};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DICE_DEN_ORAL = new DialogueNode("Finished", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getRumPrice()));
		}
		@Override
		public String getDescription() {
			return UtilText.parse((NPC) getGuards(false).get(0), "[npc.Name] has finished...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getSubmissiveParticipants(false).containsKey(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_STORAGE = new DialogueNode("Entrance", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM).isTravelledTo()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			}
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isCaptive()) {
				return 0; // So that the player can't advance days by repeatedly moving back and forth.
			}
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_QUEST_COMPLETE");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CLEARED", getGuards(true));
			}
			if(Main.game.getPlayer().isCaptive()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CAPTIVE", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(Main.game.getPlayer().isCaptive()) {
				if(index==1) {
					return new Response("Step back", "You can't get very far with the chain restricting your movements...", RatWarrensCaptiveDialogue.CAPTIVE_NIGHT) {
						@Override
						public int getSecondsPassed() {
							return 0; // So that the player can't advance days by repeatedly moving back and forth.
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM, false);
						}
					};
				}
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==1) {
					return new Response("Step back", "Do as Murk says and leave.", CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getHourOfDay()<14 || Main.game.getHourOfDay()>=22) {
						return new Response("Milkers ("+UtilText.formatAsMoneyUncoloured(500, "span")+")",
								"Murk only rents out his milkers between [style.time(14)] and [style.time(22)], so if you wanted to pay them a visit, you'll have to come back between those times.",
								null);
					}
					return new Response("Milkers ("+UtilText.formatAsMoney(500, "span")+")", "Pay Murk 500 flames to gain access to his 'milkers'.", MILKING_ROOM) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-500));
							Main.game.getNpc(Murk.class).incrementMoney(500);
							for(GameCharacter milker : getMilkers()) {
								((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
							}
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Challenge", "Tell Murk that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							spawnGuards(true, 4);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("Milking Room", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_QUEST_COMPLETE");
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM", getMilkers());
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CLEARED", getMilkers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(isCompanionDialogue()) {
				switch(index) {
					case 0:
						return "You";
					case 1:
						return UtilText.parse(getMainCompanion(), "[npc.Name]");
					case 2:
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
							return "Both";
						}
						break;
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			List<GameCharacter> milkers = getMilkers();
			if(responseTab==0) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								"Use "+milker.getName(true),
								UtilText.parse(milker, "Choose to have sex with [npc.name]..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								"Use "+milker.getName(true),
								UtilText.parse(milker, "Now that you've defeated Murk and the gang members in this area, there's nobody to stop you from having sex with [npc.name]..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==1) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker,
										"You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], and you can't force [npc.herHim] to do so..."),
								null);
					}
					
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "Tell [npc.name] to fuck [npc2.name] while you and Murk watch..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "Now that you've defeated Murk and the gang members in this area, there's nobody to stop you from ordering [npc.name] to have sex with [npc2.name] while you watch..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==2) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "Both ([npc.Name])"),
								UtilText.parse(getMainCompanion(), milker,
										"You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], and you can't force [npc.herHim] to do so..."),
								null);
					}
					
					return new ResponseSex(
							UtilText.parse(milker, "Both ([npc.Name])"),
							UtilText.parse(getMainCompanion(), milker, "Now that you've defeated Murk and the gang members in this area, there's nobody to stop you and [npc.name] from having sex with [npc2.name]..."),
							true,
							false,
							new SMMilkingStall(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL),
											new Value<>(getMainCompanion(), SexSlotMilkingStall.RECEIVING_ORAL)),
									Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
									if(character instanceof RatWarrensCaptive) {
										return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									} else {
										return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									}
								}
							},
							null,
							null,
							AFTER_MILKER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_BOTH_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						}
					};
				}
			}
			
			if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt)) {
						return new Response(
								"Free captives",
								"You've already tried to free the milkers, and discovered that they are entirely unwilling to escape with you...",
								null);
					}
					return new Response(
							"Free captives",
							"Now that you've defeated the gang members in this area, there's nobody to stop you from freeing the captive humans...",
							MILKING_ROOM_FREE_ATTEMPT) {
						@Override
						public void effects() {
							 Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt, true);
						}
					};
					
				} else {
					return new Response(
							"Milkers",
							"Ask Murk how he came to acquire these 'milkers'...",
							MILKING_ROOM_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersBackground, true);
						}
					};
				}
				
			} else if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("Fight Murk", "Tell Murk that you're here to fight him.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						spawnGuards(true, 4);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if(index==0 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("Leave", "Decide against having sex with any of the milkers and leave...<br/>[style.italicsBad(You will not get your money back!)]", MILKING_ROOM_BACKED_OUT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						for(GameCharacter milker : getMilkers()) {
							((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MILKING_ROOM_BACKED_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACK_OUT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_STORAGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_MILKER_SEX = new DialogueNode("Finished", "Step back and decide what to do now that you've had your fun...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
			Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			List<GameCharacter> characters = new ArrayList<>();
			if(isCompanionDialogue()) {
				characters.add(getMainCompanion());
			}
			characters.add(Main.sex.getSubmissiveParticipants(false).keySet().iterator().next());
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX", characters);
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CLEARED", characters);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==6 && !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Fight Murk",
							"Tell Murk that these girls are yours now, and that you're going to put him in his place.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]",
							ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(true, 4);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CHALLENGE", getGuards(true)));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
				return MILKING_STORAGE.getResponse(responseTab, index);
			} else {
				return MILKING_ROOM.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode MILKING_ROOM_BACKGROUND = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACKGROUND", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response(
						"Milkers",
						"You are already asking Murk about how he came to acquire these 'milkers'!",
						null);
			}
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_ROOM_FREE_ATTEMPT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_FREE_ATTEMPT", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL = new DialogueNode("Vengar's Hall", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Approach",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
							?"Approach Vengar and prepare to start fighting him."
							:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)
								?"Approach Vengar and start talking to him."
								:"Approach Vengar and introduce yourself to him."),
						VENGARS_HALL_APPROACH) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							if(!Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)) {
								if(isCompanionDialogue()) {
									if(!getMainCompanion().hasTraitActivated(Perk.OBSERVANT)) {
										Main.game.getTextStartStringBuilder().append(getMainCompanion().incrementHealth(-Main.game.getPlayer().getHealth()*0.75f));
									}
									
								} else {
									Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getHealth()/2));
								}
							}
						}
					}
				};
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==0) {
					return new Response("Leave", "Decide against approaching Vengar, and instead turn around and exit the hall.", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==2 && Main.game.getPlayer().hasItemType(ItemType.RESONANCE_STONE)) {
					return new Response("Resonance stone", "Use the resonance stone to signal the SWORD Enforcers to start their raid.", VENGARS_HALL_RESONANCE_STONE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensUsedResonanceStone, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			// Repeat encounters (after initial quest is resolved):
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==1) {
					return new Response("Bedroom",
							"Join Vengar in his private bed-chambers...",
							VENGARS_BEDROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;	
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
							}
						}
					};
					
				} else if(index==2 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)) {
					if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()
							&& Main.game.getPlayer().isFeminine()
							&& !Main.game.isVoluntaryNTREnabled()) {
						return new Response("Lexa", "Lexa is not attracted to you, so you'd be unable to have sex with her while visiting with Vengar...", null);
					}
					return new Response("Lexa", "Join Vengar in paying Lexa an unannounced visit...", LEXA_VISIT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
							}
						}
					};
					
				} else if(index==0) {
					return new Response("Leave", "Tell Vengar that you didn't need anything at the moment, but that you might be back some other time.", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==1) {
					// Fight
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response(
								"Prepare",
								"Prepare to defend yourself!",
								VENGARS_HALL_FIGHT_NO_CONTENT) {
							@Override
							public void effects() {
								boolean surprised = !isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT);
								boolean companionSurprised = isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) && !getMainCompanion().hasTraitActivated(Perk.OBSERVANT);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								if(surprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED", getGuards(true)));
								} else if(companionSurprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED_COMPANION", getGuards(true)));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT", getGuards(true)));
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
							@Override
							public boolean isCombatHighlight() {
								return true;
							}
						};
					}
					return new Response("Challenge", "Tell Vengar that you're here to put an end to him and his gang.", VENGARS_HALL_FIGHT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_CHALLENGE", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Persuade", "Vengar considers you to be an enemy, so he isn't going to listen to anything you have to say...", null);
					}
					return new Response("Persuade", "Try to persuade Vengar to stop extorting protection money from Axel...", VENGARS_HALL_APPROACH_PERSUADE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarPersuaded, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Threaten", "You don't have a chance to threaten Vengar, as you've got to deal with his bodyguards!", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
							|| Main.game.getPlayer().getRace()==Race.DEMON
							|| Main.game.getPlayer().getLevel()>=35) {
						return new Response("Threaten",
								"Threaten Vengar, which will cause him to directly fight you without relying on his bodyguards."
										+ "<br/>[style.italicsMinorGood(Unlocked "
											+(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
													?"due to acting like chuuni"
													:(Main.game.getPlayer().getRace()==Race.DEMON
														?"due to being a demon"
														:"due to being at least level 35"))
											+".)]",
								VENGARS_HALL_APPROACH_THREATEN) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarThreatened, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
						};
					}
					return new Response("Threaten",
							"You aren't able to threaten Vengar into stopping his extortion of Axel."
							+ "<br/>[style.italicsMinorBad(You need to be at least twice the level of Vengar, be a demon, or have the '"+Perk.CHUUNI.getName(Main.game.getPlayer())+"' perk active in order to successfully threaten him.)]",
							null);
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Seduce", "Vengar considers you to be an enemy, so he isn't going to pay attention to any attempts at seducing him...", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS)
							|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)
							|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=75) {
						return new Response("Seduce",
								"Seduce Vengar in an attempt to convince him to stop extorting protection money from Axel."
										+ "<br/>[style.italicsMinorGood(Unlocked from having the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' trait activated.)]"
										+getCooperationWarning(),
								VENGARS_HALL_APPROACH_SEDUCE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarSeduced, true);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
									character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
							}
						};
					}
					return new Response("Seduce",
							"You aren't skilled enough in the art of seduction to convince Vengar to stop extorting protection money from Axel in this manner."
									+ "<br/>[style.italicsMinorBad(Requires the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' trait to be active,"
											+ " at least 75 lust damage, or the '"+SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getName()+"' upgrade for the '"+Spell.TELEPATHIC_COMMUNICATION.getName()+"' spell.)]",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_FIGHT_NO_CONTENT = new DialogueNode("", "", true, true) {
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
				return new ResponseCombat("Fight",
						"Fight Vengar's rat-girl bodyguards.",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class), Main.game.getNpc(Silence.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Shadow.class), ""),
								new Value<>(Main.game.getNpc(Silence.class), "")));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<PERSUASION_PAYMENT) {
					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(PERSUASION_PAYMENT, "span")+")", "You do not have "+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+", so cannot do as Vengar asks...", null);
				}
				return new Response("Pay ("+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+")",
						"Agree to pay Vengar "+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+" to show your submission to him..."
						+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_PAY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PERSUASION_PAYMENT));
						Main.game.getNpc(Vengar.class).incrementMoney(PERSUASION_PAYMENT);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("Kneel",
						"Kneel down before Vengar to show your submission to him...",
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("Refuse", "Outright refuse to pay. Vengar is unlikely to take this well...", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_PAY", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Having already decided to take a non-violent approach, agreeing to get Axel to come and show his submission seems to be the only option left to you.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Submit",
						"Do as Vengar says and act as his foot rest."
								+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to humiliate yourself as Vengar orders. He is unlikely to take this well...", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT_START", getGuards(true)));
			
			if(isAssAccess(Main.game.getPlayer()) || isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(Main.game.isVoluntaryNTREnabled() && (isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion()))) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH", getGuards(true)));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_COMPANION_WATCHING", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_SOLO", getGuards(true)));
				}
				
			} else {
				if(isCompanionDialogue()) {
					if(isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion())) {
						if(Main.game.isVoluntaryNTREnabled()) {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_PLAYER_WATCHING", getGuards(true)));
						} else {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
						}
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isAssAccess(Main.game.getPlayer()) && !isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(!isAssAccess(getMainCompanion()) && !isVaginaAccess(getMainCompanion())) {
						if(index==1) {
							return new Response("Continue", "As Vengar is unable to fuck either of you, he's content to simply inform you what he wants you to do for him.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
								}
							};
						}
						return null;
						
					} else {
						if(!Main.game.isVoluntaryNTREnabled()) {
							if(index==1) {
								return new Response("Continue", "As Vengar is unable to fuck you, he's content to simply inform you what he wants you to do for him.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
							}
							return null;
							
						} else {
							if(index==1) {
								return new Response("Stand up",
										UtilText.parse(getMainCompanion(), "Refuse to allow Vengar to fuck [npc.name] in front of everyone, and tell him that you've already done as he asked."),
										VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSE_WATCH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
								
							} else if(index==2) {
								if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
									return new Response("Agree",
											UtilText.parse(getMainCompanion(), "You can tell that [npc.name] isn't at all interested in having sex with Vengar, and you can't force [npc.herHim] to do so..."),
											null);
								}
								return new ResponseSex(
										"Agree",
										UtilText.parse(getMainCompanion(), "Let Vengar fuck [npc.name] while you and everyone else in the hall watches."),
										true,
										false,
										new SMVengarDominantSex(
												SexPosition.ALL_FOURS,
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(
														new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
										Util.newArrayListOfValues(
												Main.game.getNpc(Shadow.class),
												Main.game.getNpc(Silence.class)),
										Util.newArrayListOfValues(
												Main.game.getPlayer()),
										VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
										UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										if(isVaginaAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
											
										} else if(isAssAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
										return super.getInitialSexActions();
									}
								};
							}
						}
						return null;
					}
					
				} else {
					if(index==1) {
						return new Response("Continue", "As Vengar is unable to fuck you, he's content to simply inform you what he wants you to do for him.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH"));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK"));
							}
						};
					}
					return null;
				}
			}
			
			if(index==1) {
				return new Response("Stand up",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Refuse to allow Vengar to fuck you or [npc.name] in front of everyone, and tell him that you've already done enough.")
							:"Refuse to allow Vengar to fuck you in front of everyone, and tell him that you've already done as he asked.",
						VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSED", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"Present yourself (solo)"
							:"Present yourself",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand back, before shuffling around and raising your ass towards Vengar, presenting yourself to be fucked by him in front of everyone in the hall.")
							:"Shuffle around and raise your ass towards Vengar, presenting yourself to be fucked by him in front of everyone in the hall.",
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								getMainCompanion()),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(Main.game.getPlayer())?"VENGARS_HALL_SUB_SEX_SOLO_START":"VENGARS_HALL_SUB_SEX_SOLO_START_ANAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(isVaginaAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Present yourselves"),
							UtilText.parse(getMainCompanion(), "You can tell that [npc.name] isn't at all interested in having sex with Vengar, and you can't force [npc.herHim] to do so..."),
							null);
					
				} else {
					return new Response(
							UtilText.parse(companion, "Present yourselves"),
							UtilText.parse(getMainCompanion(), "Tell [npc.name] to join you in shuffling around and raising your asses towards Vengar, presenting yourselves to be fucked by him in front of everyone in the hall."),
							VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedTo(Main.game.getNpc(Vengar.class)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with Vengar, and you can't force [npc.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMainCompanion(), "Tell Vengar that while you're not keen on the idea yourself, you'd be happy to let him fuck [npc.name] in front of everyone..."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
									Main.game.getPlayer()),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(isVaginaAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Fucked first",
						UtilText.parse(getMainCompanion(),
							"Push your [pc.hips] back against Vengar, signalling to him that you want him to fuck you first."
								+ (!isMouthAccess(Main.game.getPlayer())
									?" As you are unable to access your mouth, [npc.name] can't receive oral from you, and must simply watch and wait [npc.her] turn as Vengar fucks you..."	
									:(isVaginaAccess(getMainCompanion())
										?"You will be tasked with eating [npc.name] out while Vengar fucks you, so that [npc.sheIs] prepared for Vengar's cock once he's finished with you."
										:(isAssAccess(getMainCompanion())
											?"You will be tasked with performing anilingus on [npc.name] while Vengar fucks you, so that [npc.sheIs] prepared for Vengar's cock once he's finished with you."
											:" [npc.Name] will have to watch and wait [npc.her] turn as Vengar fucks you...")))),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
											?null
											:new Value<>(getMainCompanion(),
												isVaginaAccess(getMainCompanion())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_FIRST", getGuards(true))) {
					
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}

						if(isMouthAccess(Main.game.getPlayer())) {
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Fucked second",
						UtilText.parse(getMainCompanion(), 
							!isMouthAccess(getMainCompanion())
								?"Shuffle away from Vengar a little, signalling to him that you want him to fuck your companion first."
										+ " As [npc.name] is unable to access [npc.her] mouth, [npc.she] can't perform oral on you while Vengar fucks [npc.herHim], leaving you with nothing to do but watch and wait your turn..."
								:isVaginaAccess(Main.game.getPlayer())
									?"Get [npc.name] to start eating you out, signalling to Vengar that you want him to fuck your companion first."
									:(isAssAccess(getMainCompanion())
										?"Get [npc.name] to crawl around behind you and start performing anilingus on you, signalling to Vengar that you want him to fuck your companion first."
										:"Shuffle away from Vengar a little, signalling to him that you want him to fuck your companion first.")),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
											?null
											:new Value<>(Main.game.getPlayer(),
												isVaginaAccess(Main.game.getPlayer())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_FIRST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						
						if(isMouthAccess(getMainCompanion())) {
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE = new DialogueNode("Finished", "Vengar is satisfied, and brings an end to the sex...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_PLAYER_NEXT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_COMPANION_NEXT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> previousWetAreas = new HashMap<>(Main.sex.getAllWetAreas()); // Starting lube from saliva
			
			if(index==1) {
				if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
					return new ResponseSex("Fucked",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(Main.game.getPlayer())
									?"You lift your ass as you prepare to get fucked by Vengar."
									:isVaginaAccess(getMainCompanion())
										?"Start eating the cum out of [npc.namePos] pussy as you prepare to get fucked by Vengar next."
										:(isAssAccess(getMainCompanion())
												?"Start eating the cum out of [npc.namePos] ass as you get fucked by Vengar next."
												:"You lift your ass as you prepare to get fucked by Vengar.")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
												?null
												:new Value<>(getMainCompanion(),
													isVaginaAccess(getMainCompanion())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									return previousWetAreas;
								}
							},
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_SECOND_START", getGuards(true))) {
						
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}

							if(isMouthAccess(Main.game.getPlayer())) {
								if(isVaginaAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
					
				} else {
					return new ResponseSex(
							!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
								?UtilText.parse(getMainCompanion(), "[npc.NamePos] turn")
								:"Oral cleanup",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(getMainCompanion())
									?"Look on as Vengar fucks [npc.name] next..."
									:isVaginaAccess(Main.game.getPlayer())
										?"Get [npc.name] to start eating the cum out of your pussy as Vengar fucks [npc.herHim] next."
										:(isAssAccess(Main.game.getPlayer())
											?"Get [npc.name] to crawl around behind you and start eating the cum out of your ass as Vengar fucks [npc.herHim] next."
											:"Look on as Vengar fucks [npc.name] next...")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
												?null
												:new Value<>(Main.game.getPlayer(),
													isVaginaAccess(Main.game.getPlayer())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_SECOND_START", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							
							if(isMouthAccess(getMainCompanion())) {
								if(isVaginaAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX = new DialogueNode("Finished", "Vengar is satisfied, and brings an end to the sex...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stand up", "Stand up and await Vengar's next command...", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_FINISH", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_FINISH = new DialogueNode("", "", true, true) {
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
				return new Response("Agree", "Having already decided to take a non-violent approach, agreeing to get Axel to come and show his submission seems to be the only option left to you.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_CHUUNI", getGuards(true)));
				
			} else if(Main.game.getPlayer().getRace()==Race.DEMON) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_DEMON", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Defend yourself against Vengar!",
						Main.game.getNpc(Vengar.class));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.UNARMED_TRAINING)
						|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED)>=75
						|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					return new Response("Knock out",
							"You can take advantage of Vengar's haste to deliver a knock-out blow to him."
									+ "<br/>[style.italicsMinorGood("
									+(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)
										?"Unlocked from having the '"+SpellUpgrade.SLAM_3.getName()+"' upgrade to the '"+Spell.SLAM.getName()+"' spell."
										:(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.UNARMED_TRAINING)
											?"Unlocked from having the '"+Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())+"' trait."
											:"Unlocked from having over 75 unarmed damage."))
									+ ")]",
							VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT);
					
				} else {
					return new Response("Knock out",
							"You aren't able to take advantage of Vengar's haste..."
									+ "<br/>[style.italicsMinorBad(Requires the '"+Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())+"' trait,"
											+ " over 75 unarmed damage, or the '"+SpellUpgrade.SLAM_3.getName()+"' upgrade to the '"+Spell.SLAM.getName()+"' spell.)]",
							null);
				}
				
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SLAM", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Defend", "Defend yourself against the rat-girls!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Prepare", "Prepare yourself for whatever is coming!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID);
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any choice but to wait for the Frontline Patrol division to show up...", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "You don't have much choice but to do as Claire says and follow her out of the Rat Warrens...", SWORD_RAID_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SWORD_RAID_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
		}
		@Override
		public int getSecondsPassed() {
			return 6*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "SWORD_RAID_EXIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_TELEPATHY", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Just tease",
						"Tease Vengar by promising him sex at a later time, once all this business with Axel has been resolved.",
						VENGARS_HALL_APPROACH_SEDUCE_NO_SEX);
				
			} else if(index==2) {
				return new ResponseSex(
						"Submit",
						"Tell Vengar that he can do whatever he wants with you...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==3) {
				return new ResponseSex(
						"Dominate",
						"Tell Vengar that he's going to submit to you...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_NO_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Having already decided to take a non-violent approach, agreeing to get Axel to come and show his submission seems to be the only option left to you.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX = new DialogueNode("Finished", "Vengar is satisfied, and brings an end to the sex...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Having already decided to take a non-violent approach, agreeing to get Axel to come and show his submission seems to be the only option left to you.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_RESONANCE_STONE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_RESONANCE_STONE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Defend", "Defend yourself against the rat-girls!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
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
			return CORRIDOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SHADOW_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Spell.ELEMENTAL_AIR.applyEffect(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class), true, false);
			Main.game.getNpc(Silence.class).setHealthPercentage(1);
			Main.game.getNpc(Silence.class).setManaPercentage(1);
			Main.game.getNpc(Silence.class).addStatusEffect(StatusEffect.SPECIAL_SILENCE_TRANCE, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SHADOW_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Silence",
						"It looks like you'll have to deal with both Silence and her newly-summoned elemental!",
						Main.game.getNpc(Silence.class),
						Util.newArrayListOfValues(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class).getElemental()),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SILENCE_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setHealthPercentage(1);
			Main.game.getNpc(Shadow.class).setManaPercentage(1);
			Main.game.getNpc(Shadow.class).addStatusEffect(StatusEffect.SPECIAL_SHADOW_BESERK, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SILENCE_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Shadow",
						"It looks like you'll have to deal with an enraged Shadow!",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class)),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Vengar",
						"Now that you've dealt with his bodyguards, you're going to need to take on Vengar himself!",
						Main.game.getNpc(Vengar.class));
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Defend", "Defend yourself against the rat-girls!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS); //TODO test
				}
				
			} else {
				if(index==1) { //TODO
					if(isCompanionDialogue()) {
						return new Response("Save [com.name]",
								"Use the last of your energy to hold off the rats long enough for [com.name] to escape."
										+ "<br/>[style.italicsMinorGood([com.Name] will safely return home.)]",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE"));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();

								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("Dragged away",
								"You're powerless to resist as Vengar drags you off to another part of his lair...",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
				
				
				// Lead-in to Vengar sex slave content, which was set aside in v0.3.9 for later inclusion.
//				if(index==1) {
//					if(isCompanionDialogue()) {
//						return new Response(UtilText.parse(getMainCompanion(), "Save [npc.name]"),
//								UtilText.parse(getMainCompanion(), "Use the last of your energy to hold off the rats long enough for [npc.name] to escape."
//										+ "<br/>[style.italicsMinorGood(Unlocked by having the 'Involuntary NTR' content setting turned off."
//											+ " [npc.Name] will safely return home, but the rest of the loss route will proceed as normal.)]"),
//								VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE", getGuards(true)));
//								
//								GameCharacter companion = getMainCompanion();
//								Main.game.getPlayer().removeCompanion(companion);
//								companion.returnToHome();
//
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//						
//					} else {
//						return new Response("Stripped", "Shadow unceremoniously strips you in front of everyone in the hall...", VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//					}
//				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Stripped",
						"Murk sets about stripping you naked...",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("New home",
						"Murk and Vengar lead you into the adjoining room to introduce you to your 'new home'...",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END) {
					@Override
					public void effects() {
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Blowjob",
						"Do what's expected of you and obediently give Vengar a blowjob.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), null),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Eager blowjob",
						"Make a show of how willing you are to obediently give Vengar a blowjob.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_EAGER),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Resist blowjob",
						"Do your best to resist performing giving Vengar a blowjob.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_RESISTING),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "Having had his fun, Vengar steps back and prepares to leave...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Murk", "Murk reappears to tell you what's in store for you next...", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0"));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
						Main.game.getNpc(Vengar.class).returnToHome();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode VENGAR_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Defend", "Defend yourself against the rat-girls!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGAR_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BODYGUARDS_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Submit",
						"Tell Vengar that he can do whatever he wants with you...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"Dominate",
						"Tell Vengar that he's going to submit to you...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_AFTER_SEX = new DialogueNode("Finished", "Vengar is satisfied, and brings an end to the sex...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Tell Vengar that you'll see him again some other time before taking your leave.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT = new DialogueNode("The Gambling Den", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine() && Main.game.getPlayer().isFeminine()) {
					return new Response("Threesome", "[axel.Name] is only attracted to masculine people, and as such, is unwilling to have sex with you...", null);
				}
				return new ResponseSex(
						"Threesome",
						"Join Vengar in having dominant sex with [axel.name]...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(
										Main.game.getNpc(Axel.class)),
								null,
								null){
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_THREESOME", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"Watch",
						"Watch Vengar fuck [axel.name]...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getNpc(Axel.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_WATCH", getGuards(true)));
				
			} else if(index==3) {
				return new Response("Leave", "Decide against doing anything lewd with Vengar and [axel.name], and instead leave the two of them to it...", PlaceType.SUBMISSION_GAMBLING_DEN.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_LEAVE", getGuards(true)));
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).calculateGenericSexEffects(true, true, Main.game.getNpc(Axel.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT_AFTER_SEX = new DialogueNode("Finished", "Vengar is satisfied, and brings an end to the sex...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "You're left alone with [axel.name] back in the Gambling Den's main entrance.", PlaceType.GAMBLING_DEN_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	// After captivity enforcer raid:
	
	public static final DialogueNode POST_CAPTIVITY_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
			Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
			Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
		}
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 2*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_DEFEAT_SWORD_RAID", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Wait", "Wait with Silence for Shadow to return.", POST_DEFEAT_SWORD_RAID_FINISH) {
//					@Override
//					public void effects() {
//						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
//						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
//					}
//				};
//			}
//			return null;
//		}
//	};
//	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID_FINISH = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 10*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID_FINISH", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Follow", "You don't have much choice but to follow Claire out of the Rat Warrens...", SWORD_RAID_EXIT) {
//					@Override
//					public void effects() {
//						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
//					}
//				};
//			}
//			return null;
//		}
//	};
}
