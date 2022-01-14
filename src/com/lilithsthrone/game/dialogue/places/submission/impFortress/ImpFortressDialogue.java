package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.9
 * @author Innoxia
 */
public class ImpFortressDialogue {

	public static final String FORTRESS_ALPHA_CLEAR_TIMER_ID = "fortress_alpha_clear";
	public static final String FORTRESS_FEMALES_CLEAR_TIMER_ID = "fortress_females_clear";
	public static final String FORTRESS_MALES_CLEAR_TIMER_ID = "fortress_males_clear";

	private static boolean isAlphaFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA;
	}

	private static boolean isFemalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_FEMALES;
	}

	private static boolean isMalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES;
	}

	public static boolean isDarkSirenDefeated() {
		return Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL);
	}

	private static boolean isOralAvailable() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}

	private static boolean isOralAvailableCompanion() {
		return getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	private static AbstractPlaceType getSubmissionFortress() {
		if(isAlphaFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA;
		} else if(isFemalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES;
		} else if(isMalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_MALES;
		}
		return null;
	}

	public static boolean isGuardsDefeated() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation()).isEmpty();
	}
	
	private static boolean isGuardsPacified() {
		if(isPacified()) {
			return true;
		}
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesGuardsPacified);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesGuardsPacified);
		}
	}
	
	private static void setGuardsPacified() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsPacified, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsPacified, true);
		}
	}
	
	private static boolean isGuardsPacifiedBySex() {
		return isGuardsPacified() && !getImpGuards().isEmpty() && Main.game.getPlayer().getTotalTimesHadSex(getImpGuards().get(0))>0;
	}
	
	private static boolean isPacified() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified);
		}
	}
	
	private static void setPacified() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, true);
		}
	}
	
	private static boolean isDefeated() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
		}
	}
	
	private static void setBossEncountered() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesBossEncountered, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesBossEncountered, true);
		}
	}
	
	private static boolean isBossEncountered() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaBossEncountered);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesBossEncountered);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesBossEncountered);
		}
	}

	private static void clearBossGuards() {
		clearBossGuards(Main.game.getPlayer().getWorldLocation());
	}
	
	private static void clearBossGuards(AbstractWorldType fortress) {
		for(GameCharacter character : getImpBossGroup(fortress, true)) {
			if(!character.equals(getBoss(fortress))) {
				Main.game.banishNPC(character.getId());
			}
		}
	}
	
	private static void clearFortress() {
		clearFortress(Main.game.getPlayer().getWorldLocation());
	}
	
	public static void clearFortress(AbstractWorldType fortress) {
		
		banishImpGuards(fortress);
		
		clearBossGuards(fortress);
		
		((NPC) getBoss(fortress)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));
		
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
			getBoss(fortress).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			
		} else {
			getBoss(fortress).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_ALPHA_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_FEMALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_MALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	}
	
	public static void resetFortress(AbstractWorldType fortress) {
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);

			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, false);
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
//				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setGenericName("alpha-imp archer");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP);
			Main.game.getNpc(FortressAlphaLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
						for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
							if(!Main.game.getPlayer().getCompanions().contains(character)) {
								character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
								character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
							}
						}
					}
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, false);
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
//				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setGenericName("alpha-imp archer");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP);
			Main.game.getNpc(FortressFemalesLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
						for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
							if(!Main.game.getPlayer().getCompanions().contains(character)) {
								character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
								character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
							}
						}
					}
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, false);
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP);
			Main.game.getNpc(FortressMalesLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
						for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
							if(!Main.game.getPlayer().getCompanions().contains(character)) {
								character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
								character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
							}
						}
					}
				}
			}
			
		}
	}
	
	private static List<GameCharacter> getImpBossGroup(boolean includeBoss) {
		return getImpBossGroup(Main.game.getPlayer().getWorldLocation(), includeBoss);
	}
	
	public static List<GameCharacter> getImpBossGroup(AbstractWorldType fortress, boolean includeBoss) {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressAlphaLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressFemalesLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressMalesLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
		}
		
		bossGroup.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return bossGroup;
	}

	private static List<GameCharacter> getImpGuards() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation());
	}
		
	public static List<GameCharacter> getImpGuards(AbstractWorldType fortress) {
		
		List<GameCharacter> impGuards = new ArrayList<>();

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
		}
		
		impGuards.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return impGuards;
	}

	private static GameCharacter getBoss() {
		return getBoss(Main.game.getPlayer().getWorldLocation());
	}
	
	public static GameCharacter getBoss(AbstractWorldType fortress) {
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			return Main.game.getNpc(FortressAlphaLeader.class);

		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			return Main.game.getNpc(FortressFemalesLeader.class);

		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			return Main.game.getNpc(FortressMalesLeader.class);
		}
		
		return null;
	}

	private static ImpAttacker getImpGuardLeader() {
		return getImpGuardLeader(Main.game.getPlayer().getWorldLocation());
	}
	
	public static ImpAttacker getImpGuardLeader(AbstractWorldType fortress) {
		return (ImpAttacker) getImpGuards(fortress).get(0);
	}

	private static void banishImpGuards() {
		banishImpGuards(Main.game.getPlayer().getWorldLocation());
	}

	public static void banishImpGuards(AbstractWorldType fortress) {
		for(GameCharacter imp : getImpGuards(fortress)) {
			if(!imp.isSlave() && imp.getPartyLeader()==null) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	public static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static List<GameCharacter> getPartyForSex() {
		if(isCompanionDialogue()) {
			return Util.newArrayListOfValues(Main.game.getPlayer(), getMainCompanion());
		} else {
			return Util.newArrayListOfValues(Main.game.getPlayer());
		}
	}
	
	public static List<GameCharacter> getAllCharacters() {
		// There's a reason I can't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			allCharacters.addAll(getImpGuards(Main.game.getPlayer().getWorldLocation()));
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_KEEP)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_KEEP)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_KEEP)) {
			allCharacters.add(getBoss());
			allCharacters.addAll(getImpBossGroup(Main.game.getPlayer().getWorldLocation(), false));
		}
		
		return allCharacters;
	}
	
	public static void resetGuards(AbstractWorldType fortress) {
		List<String> impAdjectives = new ArrayList<>();
		List<GameCharacter> impGroup = new ArrayList<>();

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("alpha-imp leader");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("alpha-imp archer");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, false);
			}

		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
				imp.setGenericName("alpha-imp leader");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsPacified, false);
			}

		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				imp.setGenericName("alpha-imp leader");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				imp.setGenericName("alpha-imp brawler");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsPacified, false);
			}
		}
	}
	
	public static String getDialogueEncounterId() {
		StringBuilder idSB = new StringBuilder();
		
		if(isAlphaFortress()) {
			// Alpha imp group encounter:
			idSB.append("Alpha");
			
		} else if(isFemalesFortress()) {
			// Female imps encounter:
			idSB.append("Females");
			
		} else {
			// Male imps encounter:
			idSB.append("Males");
		}
		
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		return idSB.toString();
	}
	
	public static String getGuardsDialogueEncounterId() {
		StringBuilder idSB = new StringBuilder();
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		return idSB.toString();
	}
	
	private static List<GameCharacter> getKeepDominantSpectators(SexManagerInterface manager) {
		List<GameCharacter> spectators = new ArrayList<>();
		if(manager.getDominants().containsKey(Main.game.getPlayer())) {
			for(GameCharacter character : getPartyForSex()) {
				if(!manager.getDominants().containsKey(character)) {
					spectators.add(character);
				}
			}
			
		} else {
			for(GameCharacter character : getImpBossGroup(true)) {
				if(!manager.getDominants().containsKey(character)) {
					spectators.add(character);
				}
			}
		}
		return spectators;
	}
	
	private static List<GameCharacter> getKeepSubmissiveSpectators(SexManagerInterface manager) {
		List<GameCharacter> spectators = new ArrayList<>();
		if(manager.getSubmissives().containsKey(Main.game.getPlayer())) {
			for(GameCharacter character : getPartyForSex()) {
				if(!manager.getSubmissives().containsKey(character)) {
					spectators.add(character);
				}
			}
			
		} else {
			for(GameCharacter character : getImpBossGroup(true)) {
				if(!manager.getSubmissives().containsKey(character)) {
					spectators.add(character);
				}
			}
		}
		return spectators;
	}
	
	public static boolean isAlphaBossWantingOral(GameCharacter character) {
		return character.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}

	private static SexManagerInterface getAlphaSexManager(GameCharacter sub1, ResponseTag... tags) {
		return getAlphaSexManager(sub1, null, tags);
	}
	
	private static SexManagerInterface getAlphaSexManager(GameCharacter sub1, GameCharacter sub2, ResponseTag... tags) {
		HashMap<GameCharacter, SexSlot> doms = new HashMap<>();
		HashMap<GameCharacter, SexSlot> subs = new HashMap<>();
		
		boolean doggy = true;
		
		if(sub2!=null) {
			if(isAlphaBossWantingOral(sub1)) {
				if(isAlphaBossWantingOral(sub2)) {
					doggy = false;
					subs = Util.newHashMapOfValues(
							new Value<>(sub1, SexSlotStanding.PERFORMING_ORAL),
							new Value<>(sub2, SexSlotStanding.PERFORMING_ORAL_TWO));
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotStanding.STANDING_DOMINANT));
					
				} else {
					subs = Util.newHashMapOfValues(
							new Value<>(sub1, SexSlotAllFours.ALL_FOURS),
							new Value<>(sub2, SexSlotAllFours.ALL_FOURS_TWO));
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotAllFours.IN_FRONT),
							new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
							new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.IN_FRONT_TWO),
							new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.BEHIND_TWO));
				}
					
			} else if(isAlphaBossWantingOral(sub2)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotAllFours.IN_FRONT_TWO),
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.IN_FRONT),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.BEHIND));
				
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.HUMPING));
			}
			
		} else {
			if(isAlphaBossWantingOral(sub1)) {
				doggy = false;
				subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotStanding.PERFORMING_ORAL));
				doms = Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotStanding.STANDING_DOMINANT));
					
			} else {
				subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotAllFours.ALL_FOURS));
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.HUMPING));
			}
		}
		
		if(doggy) {
			return new SMAllFours(doms, subs) {
				@Override
				public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
					if(getDominants().containsKey(getBoss())) {
						Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
						map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS));
						return map;
					}
					return super.exposeAtStartOfSexMap();
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						for(ResponseTag tag : tags) {
							if(tag!=null) {
								switch(tag) {
									case START_PACE_PLAYER_DOM_GENTLE:
										return SexPace.DOM_GENTLE;
									case START_PACE_PLAYER_DOM_ROUGH:
										return SexPace.DOM_ROUGH;
									case START_PACE_PLAYER_SUB_RESISTING:
										return SexPace.SUB_RESISTING;
									case START_PACE_PLAYER_SUB_EAGER:
										return SexPace.SUB_EAGER;
									case PREFER_ORAL:
									case PREFER_MISSIONARY:
									case PREFER_DOGGY:
									case PREFER_COW_GIRL:
									case DISABLE_POSITIONING:
										break;
								}
							}
						}
					}
					return null;
				}
			};
			
		} else {
			return new SMStanding(doms, subs) {
				@Override
				public boolean isPositionChangingAllowed(GameCharacter character) {
					return false;
				}
				@Override
				public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
					if(getDominants().containsKey(getBoss())) {
						Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
						map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS));
						return map;
					}
					return super.exposeAtStartOfSexMap();
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						for(ResponseTag tag : tags) {
							if(tag!=null) {
								switch(tag) {
									case START_PACE_PLAYER_DOM_GENTLE:
										return SexPace.DOM_GENTLE;
									case START_PACE_PLAYER_DOM_ROUGH:
										return SexPace.DOM_ROUGH;
									case START_PACE_PLAYER_SUB_RESISTING:
										return SexPace.SUB_RESISTING;
									case START_PACE_PLAYER_SUB_EAGER:
										return SexPace.SUB_EAGER;
									case PREFER_ORAL:
									case PREFER_MISSIONARY:
									case PREFER_DOGGY:
									case PREFER_COW_GIRL:
									case DISABLE_POSITIONING:
										break;
								}
							}
						}
					}
					return null;
				}
			};
		}
	}
	
	
	
	public static boolean isMaleBossWantingToBreed(GameCharacter character) {
		return !character.isVisiblyPregnant() && character.hasVagina() && character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}

	private static SexManagerInterface getMalesSexManager(GameCharacter sub1, ResponseTag... tags) {
		return getMalesSexManager(sub1, null, tags);
	}
	
	private static SexManagerInterface getMalesSexManager(GameCharacter sub1, GameCharacter sub2, ResponseTag... tags) {
		HashMap<GameCharacter, SexSlot> doms = new HashMap<>();
		HashMap<GameCharacter, SexSlot> subs = new HashMap<>();
		
		if(sub2!=null) {
			subs = Util.newHashMapOfValues(
					new Value<>(sub1, SexSlotLyingDown.LYING_DOWN),
					new Value<>(sub2, SexSlotLyingDown.LYING_DOWN_TWO));
			
			if(isMaleBossWantingToBreed(sub1)) {
				if(isMaleBossWantingToBreed(sub2)) {
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY));
				} else {
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
							new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY_TWO),
							new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
							new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
				}
				
			} else if(isMaleBossWantingToBreed(sub2)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY_TWO),
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
					
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING));
			}
			
		} else {
			subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotLyingDown.LYING_DOWN));
			
			if(isMaleBossWantingToBreed(sub1)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY));
				
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
			}
		}
		
		return new SMLyingDown(doms, subs) {
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					for(ResponseTag tag : tags) {
						if(tag!=null) {
							switch(tag) {
								case START_PACE_PLAYER_DOM_GENTLE:
									return SexPace.DOM_GENTLE;
								case START_PACE_PLAYER_DOM_ROUGH:
									return SexPace.DOM_ROUGH;
								case START_PACE_PLAYER_SUB_RESISTING:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
								case PREFER_COW_GIRL:
								case DISABLE_POSITIONING:
									break;
							}
						}
					}
				}
				return null;
			}
		};
	}
	
	
	
	// Dialogues:
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Gateway", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return !isGuardsPacified() && !isGuardsDefeated();
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isGuardsDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DESERTED", getAllCharacters()));
				if(!isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DESERTED_GUARD_RETURN_WARNING", getAllCharacters()));
				}
				
			} else if(isGuardsPacifiedBySex()) {
				if(Objects.equals(Main.game.getPlayer().getSubspeciesOverride(), Subspecies.DEMON)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_SEX_DEMON", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_SEX", getAllCharacters()));
				}
				
			} else if(isGuardsPacified()) {
				if(Objects.equals(Main.game.getPlayer().getSubspeciesOverride(), Subspecies.DEMON)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_DEMON", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED", getAllCharacters()));
				}
				
			} else if(Objects.equals(Main.game.getPlayer().getSubspeciesOverride(), Subspecies.DEMON)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DEMON", getAllCharacters()));
				
			} else if(Main.game.getPlayer().isElementalSummoned()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL",
						Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuardsPacified() || isGuardsDefeated()) {
				if (index == 1) {
					return new Response("Leave", "Head back out into the tunnels.", getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							if(isGuardsDefeated() && !isDefeated()) {
								resetGuards(Main.game.getPlayer().getWorldLocation());
							}
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters()));
						}
					};
	
				} else if(!isGuardsDefeated()) {
					if(index==2) {
						if(!isOralAvailable()) {
							return new Response(isCompanionDialogue()?"Offer oral (solo)":"Offer oral", "As you are unable to access your mouth, you cannot offer to perform oral sex on the imps!", null);
						}
						return new ResponseSex(isCompanionDialogue()?"Offer oral (solo)":"Offer oral",
								isGuardsPacifiedBySex()
									?"Agree with the imp's taunts, and offer to perform oral sex on them again."
									:"Offer to perform oral sex on the imps.",
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
								GUARDS_AFTER_ORAL_FOR_ENTRY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_PACIFIED", getAllCharacters()),
								ResponseTag.PREFER_ORAL,
								ResponseTag.DISABLE_POSITIONING) {
							@Override
							public void effects() {
								setGuardsPacified();
							}
						};
						
					} else if(index==3 && !isGuardsDefeated() && isCompanionDialogue()) {
						if(!isOralAvailable()) {
							return new Response("Offer oral (both)", "As you are unable to access your mouth, you cannot offer to perform oral sex on the imps!", null);
						}
						if(!isOralAvailableCompanion()) {
							return new Response("Offer oral (both)", UtilText.parse(getMainCompanion(), "As [npc.nameIsFull] unable to access [npc.her] mouth, [npc.she] cannot perform oral sex on the imps!"), null);
						}
						if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
							return new Response("Offer oral (both)",
									UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in performing oral sex on the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex("Offer oral (both)",
									isGuardsPacifiedBySex()
										?UtilText.parse(getMainCompanion(), "Agree with the imp's taunts, and tell them that you and [npc.name] are willing to perform oral sex on them again.")
										:UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] want to perform oral sex on them."),
									true,
									false,
									getImpGuards(),
									getPartyForSex(),
									null,
									null,
									GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
									UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_PACIFIED_WITH_COMPANION", getAllCharacters()),
									ResponseTag.PREFER_ORAL,
									ResponseTag.DISABLE_POSITIONING) {
								@Override
								public void effects() {
									setGuardsPacified();
								}
							};
						}
						
					} else if(isCompanionDialogue()?index==4:index==3) {
						return new ResponseCombat("Attack", "Change your mind about not wanting to fight the imps, and decide to teach them a lesson!", getImpGuardLeader(), getImpGuards(), null);
					}
				}
				return null;
				
			} else {
				if (index == 1) {
					if(Objects.equals(Main.game.getPlayer().getSubspeciesOverride(), Subspecies.DEMON)) {
						return new Response("Command",
								"The imps seem incredibly nervous at the prospect of being confronted by a demon. Use this to your advantage and order them to step aside.",
								ENTRANCE_DEMONIC_COMMAND) {
							@Override
							public void effects() {
								setGuardsPacified();
							}
						};
					} else {
						return new Response("Command",
								"If you were a demon, perhaps you'd be able to intimidate the imps. As you're not, however, it looks like you're going to have to fight them...",
								null);
					}
	
				} else if(index==2) {
					if(Main.game.getPlayer().isElementalSummoned()) {
						return new Response("Elemental",
								UtilText.parse("Intimidate the imps by drawing attention to the fact that you are powerful enough to have a summoned elemental with you..."), ENTRANCE_ELEMENTAL) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_INTIMIDATE",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								setGuardsPacified();
							}
						};
						
					} else if((Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer()))) {
						return new Response("Elemental", UtilText.parse(getBoss(), "Intimidate the imps by summoning your elemental in front of them."), ENTRANCE_ELEMENTAL) {
							@Override
							public void effects() {
								List<Spell> elementalSpells = Util.newArrayListOfValues(Spell.ELEMENTAL_AIR, Spell.ELEMENTAL_ARCANE, Spell.ELEMENTAL_FIRE, Spell.ELEMENTAL_EARTH, Spell.ELEMENTAL_WATER);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_SUMMON",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								for(Spell spell : elementalSpells) {
									if(Main.game.getPlayer().hasSpell(spell) && Main.game.getPlayer().getMana()>=spell.getModifiedCost(Main.game.getPlayer())) {
										Main.game.getTextStartStringBuilder().append(spell.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
										break;
									}
								}
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_SUMMON_END",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								setGuardsPacified();
							}
						};
						
					} else {
						return new Response("Elemental", UtilText.parse(getBoss(), "You'd need to have an elemental summoned, or know such a spell and have enough aura to cast it, if you wanted to intimidate [npc.name]..."), null);
					}
					
				} else if(index==3) {
					return new ResponseCombat("Attack", "Defend yourself against the imps!", getImpGuardLeader(), getImpGuards(), null);
					
				} else if(index==4) {
					if(!isOralAvailable()) {
						return new Response(isCompanionDialogue()?"Offer oral (solo)":"Offer oral", "As you are unable to access your mouth, you cannot offer to perform oral sex on the imps!", null);
					}
					return new ResponseSex(isCompanionDialogue()?"Offer oral (solo)":"Offer oral",
							"Offer to perform oral sex on the imps in exchange for them letting you into the fortress.",
							true,
							false,
							getImpGuards(),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							GUARDS_AFTER_ORAL_FOR_ENTRY,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL", getAllCharacters()),
							ResponseTag.PREFER_ORAL,
							ResponseTag.DISABLE_POSITIONING) {
						@Override
						public void effects() {
							setGuardsPacified();
						}
					};
					
				} else if(index==5 && isCompanionDialogue()) {
					if(!isOralAvailable()) {
						return new Response("Offer oral (both)", "As you are unable to access your mouth, you cannot offer to perform oral sex on the imps!", null);
					}
					if(!isOralAvailableCompanion()) {
						return new Response("Offer oral (both)", UtilText.parse(getMainCompanion(), "As [npc.nameIsFull] unable to access [npc.her] mouth, [npc.she] cannot perform oral sex on the imps!"), null);
					}
					if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response("Offer oral (both)",
								UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in performing oral sex on the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex("Offer oral (both)",
								UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] will perform oral sex on them in exchange for being let into the fortress."),
								true,
								false,
								getImpGuards(),
								getPartyForSex(),
								null,
								null,
								GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_WITH_COMPANION", getAllCharacters()),
								ResponseTag.PREFER_ORAL,
								ResponseTag.DISABLE_POSITIONING) {
							@Override
							public void effects() {
								setGuardsPacified();
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_ORAL_FOR_ENTRY = new DialogueNode("Finished", ".", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION = new DialogueNode("Finished", ".", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_DEMONIC_COMMAND = new DialogueNode("", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DEMONIC_COMMAND", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode ENTRANCE_ELEMENTAL = new DialogueNode("Keep", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
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

	
	public static final DialogueNode GUARDS_AFTER_COMBAT_VICTORY = new DialogueNode("Victory", ".", true) {

		@Override
		public String getDescription() {
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			if(getImpGuards().isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY_ALL_ENSLAVED", getAllCharacters());
			}
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGuards().isEmpty()) {
				if(index==0) {
					return "Interactions";
					
				} else if(index==1) {
					return "Inventories";
					
				} else if(index==2) {
					return "Transformations";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGuards().isEmpty()) {
				if(index==1) {
					return new Response("Continue", "As you've enslaved the imp guards, there's nothing left to do but continue on your way into the fortress...", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", "Tell the imps to get out of here while they still can.", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Submit",
								"You're not really sure what to do now... Perhaps it would be best to let the imps choose what to do next...",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								getPartyForSex(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {

				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", "Tell the imps to get out of here while they still can...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Solo submission",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you submit to the imps, allowing them to have dominant sex with you."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group sex",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, "Have dominant sex with the imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									getPartyForSex(),
									getImpGuards(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									getImpGuards(),
									getPartyForSex(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the imps while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGuards(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "Hand [npc.name] over to the imps, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									getImpGuards(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpGuardLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};

	public static final DialogueNode GUARDS_AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", ".", true) {

		@Override
		public int getSecondsPassed() {
			return Main.game.isNonConEnabled()?1:15*60;
		}
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			if(Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_THROWN_OUT", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"Allow the imps to move you into position...",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"Eagerly allow yourself to be moved into position by the gang of imps...",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"Try to resist as the gang of imps move you into position...",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
				}
				
			} else {
				if (index == 1) {
					return new Response("Thrown out", "The imps abandon you and return to their fortress...", getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
						}
					};
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImpGuards();
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", getSubmissionFortress().getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COURTYARD = new DialogueNode("Courtyard", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "COURTYARD", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly",
							(isDefeated()
									|| isPacified())
								?"As you're able to simply exit peacefully through the front gate, there's no need to do this unless you were just trying to show off..."
								:"Fly over the wall of the fortress, thereby avoiding a confrontation with the imp guards at the gate.",
							getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							if(isGuardsDefeated() && !isDefeated()) {
								resetGuards(Main.game.getPlayer().getWorldLocation());
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "IMP_FORTRESS_FLY_EXIT"));
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
						}
					};
				} else {
					return new Response("Fly", "Either you or a member of your party is unable to fly, so you're unable to access the fortress by flying over the walls!", null);
				}	
			}
			return null;
		}
	};

	public static final DialogueNode KEEP = new DialogueNode("Keep", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_DEFEATED", getAllCharacters()));
			} else if(isPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isDefeated()) {
					return new Response("Enter", "The keep is deserted, and there's nothing of value inside...", null);
				} else {
					return new Response("Enter", "Push open the doors of the keep and step inside.", KEEP_ENTRY) {
						@Override
						public void effects() {
							getBoss().setPlayerKnowsName(true);
						}
					};
				}
			}
			return null;
		}
	};

	private static AbstractWeapon getSuitableWeaponForCutting() {
		AbstractWeapon suitableWeapon = null;
		
		for(AbstractWeapon weapon : Main.game.getPlayer().getMainWeaponArray()) {
			if(weapon!=null
					&& weapon.getWeaponType().getItemTags().contains(ItemTag.WEAPON_BLADE)
					&& Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, weapon)
						>Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))) {
				suitableWeapon = weapon;
				break;
			}
		}
		if(suitableWeapon==null) {
			for(AbstractWeapon weapon : Main.game.getPlayer().getOffhandWeaponArray()) {
				if(weapon!=null
						&& weapon.getWeaponType().getItemTags().contains(ItemTag.WEAPON_BLADE)
						&& Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, weapon)
							>Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))) {
					suitableWeapon = weapon;
					break;
				}
			}
		}
		
		return suitableWeapon;
	}
	
	public static final DialogueNode KEEP_ENTRY = new DialogueNode("Keep", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED", getAllCharacters()));
				if(isAlphaFortress()) {
					if(isCompanionDialogue()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							if(isAlphaBossWantingOral(getMainCompanion())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_ORAL", getAllCharacters()));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_ORAL_COMPANION_IMPS", getAllCharacters()));
							}
						} else if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS_COMPANION_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_IMPS", getAllCharacters()));
						}
					} else {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS", getAllCharacters()));
						}
					}
					
				} else if(isMalesFortress()) {
					if(isCompanionDialogue()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							if(isMaleBossWantingToBreed(getMainCompanion())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_BREEDING", getAllCharacters()));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BREEDING_COMPANION_IMPS", getAllCharacters()));
							}
						} else if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS_COMPANION_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_IMPS", getAllCharacters()));
						}
					} else {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS", getAllCharacters()));
						}
					}
				}

				
			} else if(isBossEncountered()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_RETURN", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPacified()) {
				if(index==1) {
					return new Response("Leave", UtilText.parse(getBoss(), "Tell [npc.name] that you were just checking up on [npc.herHim], before quickly taking your leave."), KEEP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_LEAVE", getAllCharacters()));
						}
					};
					
				} else if(index==2) {
					String title = "Sex";
					
					if(isAlphaFortress()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							title = isCompanionDialogue()?"Suck cock (solo)":"Suck cock";
						} else {
							title = isCompanionDialogue()?"Imp sex (solo)":"Imp sex";
						}
						
					} else if(isFemalesFortress()) {
						title = isCompanionDialogue()?"Plaything (solo)":"Plaything";
						
					} else if(isMalesFortress()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							title = isCompanionDialogue()?"Get bred (solo)":"Get bred";
						} else {
							title = isCompanionDialogue()?"Imp sex (solo)":"Imp sex";
						}
					}
					
					if(isAlphaFortress()) {
						SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
						return new ResponseSex(title,
								UtilText.parse(getBoss(), "Do as [npc.name] commands, and submit to [npc.herHim] again..."),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_PACIFIED_REPEAT_SEX":"KEEP_PACIFIED_REPEAT_SEX_IMPS", getAllCharacters()));
						
					} else if(isMalesFortress()) {
						SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
						return new ResponseSex(title,
								UtilText.parse(getBoss(), "Do as [npc.name] commands, and submit to [npc.herHim] again..."),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_PACIFIED_REPEAT_SEX":"KEEP_PACIFIED_REPEAT_SEX_IMPS", getAllCharacters()));
					}
					return new ResponseSex(title,
							UtilText.parse(getBoss(), "Do as [npc.name] commands, and prepare to have submissive sex with [npc.herHim] again..."),
							true,
							false,
							new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
										new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
										new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
										new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							Main.game.getPlayer().getCompanions(),
							KEEP_AFTER_SEX_PACIFIED,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_SEX", getAllCharacters())){
						@Override
						public void effects() {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					};
					
				} else if(index==3 && isCompanionDialogue()) {
					String title = "Sex";
					
					if(isAlphaFortress()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							title = "Suck cock (both)";
						} else {
							title = "Imp sex (both)";
						}
						
					} else if(isFemalesFortress()) {
						title = "Plaything (both)";
						
					} else if(isMalesFortress()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							title = "Get bred (both)";
						} else {
							title = "Imp sex (both)";
						}
					}
					
					
					if(!getMainCompanion().isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(title,
								UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in having sex with [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
							return new ResponseSex(title,
									UtilText.parse(getBoss(), getMainCompanion(), "Do as [npc.name] commands, and have [npc2.name] submit to [npc.herHim] alongside you..."),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_PACIFIED,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_ORAL"
												:"KEEP_PACIFIED_REPEAT_SEX_ORAL_COMPANION_IMPS"
											:isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_IMPS_COMPANION_ORAL"
												:"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_IMPS",
										getAllCharacters()));
							
						} else if(isMalesFortress()) {
							SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
							return new ResponseSex(title,
									UtilText.parse(getBoss(), getMainCompanion(), "Do as [npc.name] commands, and have [npc2.name] submit to [npc.herHim] alongside you..."),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_PACIFIED,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isMaleBossWantingToBreed(Main.game.getPlayer())
											?isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_BREEDING"
												:"KEEP_PACIFIED_REPEAT_SEX_BREEDING_COMPANION_IMPS"
											:isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_IMPS_COMPANION_BREEDING"
												:"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_IMPS",
										getAllCharacters()));
						}
						return new ResponseSex(title,
								UtilText.parse(getMainCompanion(), getBoss(), "Do as [npc2.name] commands, and prepare for both you and [npc.name] to have submissive sex with [npc2.herHim] again..."),
								true,
								false,
								new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
								null,
								null,
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_SEX_WITH_COMPANION", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				boolean darkSirenActionAvailable = !isDarkSirenDefeated()
						&& (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelEncountered) || Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));

				if (index == 1) {
					if(isAlphaFortress()) {
						if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.UNARMED_TRAINING)) {
							return new Response(Util.capitaliseSentence(Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())),
									UtilText.parse(getBoss(),
											"Seize this fleeting opportunity to provoke [npc.name] into trying to punch you,"
													+ " relying on your skill as a <b style='color:"+PresetColour.TRAIT.toWebHexString()+";'>"+Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())+"</b>"
															+ " to humiliate [npc.herHim] in front of [npc.her] imp followers."),
									KEEP_ALPHA_BRAWLER) {
								@Override
								public void effects() {
									try {
										getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.TORSO_OVER), true, getBoss());
									} catch(Exception ex) {
									}
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
									clearBossGuards();
									setBossEncountered();
								}
							};
							
						} else {
							return new Response(Util.capitaliseSentence(Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())),
									UtilText.parse(getBoss(),
											"You're not competent enough at fighting to try and humiliate [npc.name] in front of [npc.her] gang...</br>"
													+ "(Requires '"+Perk.UNARMED_TRAINING.getName(Main.game.getPlayer())+"' perk.)"),
									null);
						}
						
					} else if(isFemalesFortress()) {
						if(Main.game.getPlayer().hasTraitActivated(Perk.NYMPHOMANIAC)) {
							return new Response("True nympho",
									UtilText.parse(getBoss(),
											"Seize this fleeting opportunity to undermine [npc.namePos] authority by describing to [npc.her] gang how much fun"
													+ " <b style='color:"+PresetColour.TRAIT.toWebHexString()+";'>Nymphomaniacs</b> can have out in Submission's tunnels."),
									KEEP_FEMALES_NYMPHO) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO", getAllCharacters()));
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
									clearBossGuards();
									setBossEncountered();
								}
							};
							
						} else {
							return new Response("True nympho",
									UtilText.parse(getBoss(), "You're not as sex-crazed as [npc.name], so you can't undermine [npc.her] authority in front of [npc.her] imps...</br>(Requires 'Nymphomaniac' trait.)"),
									null);
						}
						
					} else {
						if(getSuitableWeaponForCutting()!=null) {
							return new Response("Tameshigiri",
									UtilText.parse(getBoss(),
											"Seize this fleeting opportunity to 'test cut' the largest bamboo trunk behind [npc.name], thereby demonstrating your superiority in front of [npc.her] imp followers."),
									KEEP_MALES_TAMESHIGIRI_MAIN) {
								@Override
								public void effects() {
									setBossEncountered();
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
								}
							};
							
						} else {
							return new Response("Tameshigiri",
									UtilText.parse(getBoss(), "You don't think you can match [npc.namePos] demonstration with your weapons...</br>"
											+ "(Requires you to have an equipped bladed weapon with a maximum damage greater than <b>"
												+Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))+"</b>.)"),
									null);
						}
					}
					
				} else if (index == 2 && darkSirenActionAvailable) {

					if((isAlphaFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY))
							|| (isMalesFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2))
							|| (isFemalesFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))) {
						return new Response("Dark Siren",
								UtilText.parse(getBoss(), "You've already gained a key from [npc.name]!"),
								null);
					}
					return new Response("Dark Siren",
							UtilText.parse(getBoss(), "Tell [npc.name] that you want to meet 'The Dark Siren', and ask [npc.herHim] what it would take to gain an audience with them."),
							KEEP_AUDIENCE) {
						@Override
						public void effects() {
							setBossEncountered();
						}
					};

				} else if(darkSirenActionAvailable?index==3:index==2) {
					return new ResponseCombat("Attack", UtilText.parse(getBoss(), "Defend yourself against [npc.name] and [npc.her] minions!"),
							(NPC) getBoss(),
							getImpBossGroup(true), null) {
						@Override
						public void effects() {
							setBossEncountered();
						}
					};

				} else if(darkSirenActionAvailable?index==4:index==3) {
					if(isAlphaFortress()) {
						SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
						return new ResponseSex("Surrender",
								isCompanionDialogue()
									?UtilText.parse(getMainCompanion(), getBoss(), "Surrender both yourself and [npc.name] to [npc2.name], allowing [npc2.herHim] and [npc2.her] imps to do what they please with you.")
									:UtilText.parse(getBoss(), "Surrender your body to [npc.name] and [npc.her] imps in exchange for being allowed to leave without a fight."),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_DEFEAT,
								isCompanionDialogue()
									?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_ENTRY_OFFER_SEX_DOUBLE_ORAL"
													:"KEEP_ENTRY_OFFER_SEX_ORAL_COMPANION_IMPS"
												:isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_ENTRY_OFFER_SEX_IMPS_COMPANION_ORAL"
													:"KEEP_ENTRY_OFFER_SEX_DOUBLE_IMPS",
											getAllCharacters())
									:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?"KEEP_ENTRY_OFFER_SEX"
												:"KEEP_ENTRY_OFFER_SEX_IMPS",
											getAllCharacters())){
							@Override
							public void effects() {
								setBossEncountered();
							}
						};
						
					} else if(isMalesFortress()) {
						SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
						return new ResponseSex("Surrender",
										isCompanionDialogue()
											?UtilText.parse(getMainCompanion(), getBoss(), "Surrender both yourself and [npc.name] to [npc2.name], allowing [npc2.herHim] and [npc2.her] imps to do what they please with you.")
											:UtilText.parse(getBoss(), "Surrender your body to [npc.name] and [npc.her] imps in exchange for being allowed to leave without a fight."),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_DEFEAT,
										isCompanionDialogue()
											?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
													isMaleBossWantingToBreed(Main.game.getPlayer())
														?isMaleBossWantingToBreed(getMainCompanion())
															?"KEEP_ENTRY_OFFER_SEX_DOUBLE_BREEDING"
															:"KEEP_ENTRY_OFFER_SEX_BREEDING_COMPANION_IMPS"
														:isMaleBossWantingToBreed(getMainCompanion())
															?"KEEP_ENTRY_OFFER_SEX_IMPS_COMPANION_BREEDING"
															:"KEEP_ENTRY_OFFER_SEX_DOUBLE_IMPS",
													getAllCharacters())
											:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
													isMaleBossWantingToBreed(Main.game.getPlayer())
														?"KEEP_ENTRY_OFFER_SEX"
														:"KEEP_ENTRY_OFFER_SEX_IMPS",
													getAllCharacters())){
							@Override
							public void effects() {
								setBossEncountered();
							}
						};
					}
					return new ResponseSex("Surrender",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "Surrender both yourself and [npc.name] to [npc2.name], allowing [npc2.herHim] and [npc2.her] imps to do what they please with you.")
								:UtilText.parse(getBoss(), "Surrender your body to [npc.name] and [npc.her] imps in exchange for being allowed to leave without a fight."),
							true,
							false,
							isCompanionDialogue()
								?new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO)))
								:new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							null,
							KEEP_AFTER_SEX_DEFEAT,
							isCompanionDialogue()
								?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_OFFER_SEX_WITH_COMPANION", getAllCharacters())
								:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_OFFER_SEX", getAllCharacters())) {
						@Override
						public void effects() {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
								
							setBossEncountered();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode KEEP_ALPHA_BRAWLER = new DialogueNode("Keep", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scare off",
						UtilText.parse(getBoss(), "Let [npc.name] escape, and shout after [npc.herHim] to keep clear of this place."),
						KEEP_ALPHA_BRAWLER_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
					
			} else if(index==2 && Main.game.isNonConEnabled()) {
				return new ResponseSex(isCompanionDialogue()?"Rape (solo)":"Rape",
						UtilText.parse(getBoss(), "Push [npc.name] down and force yourself on [npc.herHim]."),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						Main.game.getPlayer().getCompanions(),
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
				
			} else if(index==3 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isWillingToRape() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("Rape (companion)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in raping [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
				}
				return new ResponseSex("Rape (companion)",
						UtilText.parse(getBoss(), getMainCompanion(), "Push [npc.name] down and tell [npc2.name] that [npc2.she] can do what [npc2.she] likes with [npc.herHim]."),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
				
			} else if(index==4 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isWillingToRape() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("Rape (both)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in raping [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
				}
				return new ResponseSex("Rape (both)",
						UtilText.parse(getBoss(), getMainCompanion(), "Push [npc.name] down and force [npc.herHim] to have sex with both you and [npc2.name]."),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND),
										new Value<>(getMainCompanion(), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX_WITH_BOTH", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_ALPHA_BRAWLER_SCARED_OFF = new DialogueNode("Keep", ".", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_ALPHA_FORCED = new DialogueNode("Finished", "", true, true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "You've had your fun with [npc.name].");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_ALPHA_FORCED", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scare off",
						UtilText.parse(getBoss(), "Let [npc.name] escape, and shout after [npc.herHim] to keep clear of this place."),
						KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF = new DialogueNode("Keep", ".", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_FEMALES_NYMPHO = new DialogueNode("Keep", ".", true, true) {

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
				return new Response("Scare off",
						UtilText.parse(getBoss(), "Let [npc.name] escape, and shout after [npc.herHim] to keep clear of this place."),
						KEEP_FEMALES_NYMPHO_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
					
			} else if(index==2 && Main.game.isNonConEnabled()) {
				return new ResponseSex(isCompanionDialogue()?"Sex (solo)":"Sex",
						UtilText.parse(getBoss(), "Do as [npc.name] asks and have sex with [npc.herHim]."),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						Main.game.getPlayer().getCompanions(),
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX", getAllCharacters()));
				
			} else if(index==3 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("Sex (companion)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in having sex with [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
				}
				return new ResponseSex("Sex (companion)",
						UtilText.parse(getBoss(), getMainCompanion(), "Tell [npc2.name] that [npc2.she] can have sex with [npc.name], before stepping back to watch."),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX_WITH_COMPANION", getAllCharacters()));
				
			} else if(index==4 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("Sex (both)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in having sex with [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
				}
				return new ResponseSex("Sex (both)",
						UtilText.parse(getBoss(), getMainCompanion(), "Do as [npc.name] asks and get [npc2.name] to join you in having sex with [npc.herHim]."),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY),
										new Value<>(getMainCompanion(), SexSlotLyingDown.FACE_SITTING)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX_WITH_BOTH", getAllCharacters()));
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_FEMALES_NYMPHO_SCARED_OFF = new DialogueNode("Keep", ".", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_FEMALES_NYMPHO = new DialogueNode("Finished", "", true, true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "You've had your fun with [npc.name].");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_FEMALES_NYMPHO", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scare off",
						UtilText.parse(getBoss(), "Let [npc.name] escape, and shout after [npc.herHim] to keep clear of this place."),
						KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF = new DialogueNode("Keep", ".", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_MALES_TAMESHIGIRI_MAIN = new DialogueNode("Keep", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(getSuitableWeaponForCutting().getName(), true);
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_MAIN", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stand aside",
						UtilText.parse(getBoss(), "Stand aside to allow [npc.name] and [npc.her] imps to leave without a fight."),
						KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE", getAllCharacters()));
						clearFortress();
					}
				};
				
			} else if(index==2) {
				return new ResponseCombat("Fight", UtilText.parse(getBoss(), "Don't let [npc.name] and [npc.her] imps escape so easily!"),
						(NPC) getBoss(),
						getImpBossGroup(true), null);
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE = new DialogueNode("Keep", ".", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AUDIENCE = new DialogueNode("Keep", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE", getAllCharacters()));
			
			// Demands are based on preference availability:
			if(isAlphaFortress()) {
				if(isCompanionDialogue()) {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_ORAL_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isAlphaBossWantingOral(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS_COMPANION_ORAL", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_ORAL", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS", getAllCharacters()));
					}
				}
				
			} else if(isMalesFortress()) {
				if(isCompanionDialogue()) {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_BREEDING_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isMaleBossWantingToBreed(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS_COMPANION_BREEDING", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_BREEDING", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS", getAllCharacters()));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Refuse", UtilText.parse(getBoss(), "You aren't prepared to go that far! Tell [npc.name] as such, and prepare to defend yourself!"),
						(NPC) getBoss(),
						getImpBossGroup(true), null);
				
			} else if(index==2) {
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
					return new ResponseSex(isCompanionDialogue()?"Agree (solo)":"Agree",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, after telling [npc.name] to stand aside and keep watch, submit to [npc2.herHim]...")
								:UtilText.parse(getBoss(), "Decide to do as [npc.name] demands, and submit to [npc.herHim]..."),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_AUDIENCE_SEX":"KEEP_AUDIENCE_SEX_IMPS", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
						}
					};
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
					return new ResponseSex(isCompanionDialogue()?"Agree (solo)":"Agree",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, after telling [npc.name] to stand aside and keep watch, submit to [npc2.herHim]...")
								:UtilText.parse(getBoss(), "Decide to do as [npc.name] demands, and submit to [npc.herHim]..."),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_AUDIENCE_SEX":"KEEP_AUDIENCE_SEX_IMPS", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
						}
					};
				}
				return new ResponseSex(isCompanionDialogue()?"Agree (solo)":"Agree",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, after telling [npc.name] to stand aside and keep watch, submit to [npc2.herHim]...")
							:UtilText.parse(getBoss(), "Decide to do as [npc.name] demands, and submit to [npc.herHim]..."),
						true,
						false,
						new SMLyingDown(
							Util.newHashMapOfValues(
									new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
									new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
									new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
									new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						Main.game.getPlayer().getCompanions(),
						KEEP_AFTER_SEX_AUDIENCE,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_SEX", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					};
					
			} else if(index==3 && isCompanionDialogue()) {
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
					return new ResponseSex("Agree (both)",
							UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, along with [npc.name], submit to [npc2.herHim]..."),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
								isAlphaBossWantingOral(Main.game.getPlayer())
									?isAlphaBossWantingOral(getMainCompanion())
										?"KEEP_AUDIENCE_SEX_DOUBLE_ORAL"
										:"KEEP_AUDIENCE_SEX_ORAL_COMPANION_IMPS"
									:isAlphaBossWantingOral(getMainCompanion())
										?"KEEP_AUDIENCE_SEX_IMPS_COMPANION_ORAL"
										:"KEEP_AUDIENCE_SEX_DOUBLE_IMPS",
								getAllCharacters())){
						@Override
						public void effects() {
							setPacified();
						}
					};
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
					return new ResponseSex("Agree (both)",
									UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, along with [npc.name], submit to [npc2.herHim]..."),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_AUDIENCE,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isMaleBossWantingToBreed(Main.game.getPlayer())
											?isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_AUDIENCE_SEX_DOUBLE_BREEDING"
												:"KEEP_AUDIENCE_SEX_BREEDING_COMPANION_IMPS"
											:isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_AUDIENCE_SEX_IMPS_COMPANION_BREEDING"
												:"KEEP_AUDIENCE_SEX_DOUBLE_IMPS",
										getAllCharacters())){
						@Override
						public void effects() {
							setPacified();
						}
					};
				}
				return new ResponseSex("Agree (both)",
						UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, along with [npc.name], submit to [npc2.herHim]..."),
						true,
						false,
						new SMLyingDown(
							Util.newHashMapOfValues(
									new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
									new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
									new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
									new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
									new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
						null,
						Main.game.getPlayer().getCompanions(),
						KEEP_AFTER_SEX_AUDIENCE,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_SEX_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						setPacified();
						((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_AUDIENCE = new DialogueNode("Finished", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "[npc.Name] seems happy enough with your submissive performance.");
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Take key", UtilText.parse(getBoss(), "Take the key from [npc.name] and leave the keep."), KEEP_AFTER_SEX_AUDIENCE_KEY) {
					@Override
					public void effects() {
						AbstractItemType keyType = ItemType.IMP_FORTRESS_ARCANE_KEY;
						if(isMalesFortress()) {
							keyType = ItemType.IMP_FORTRESS_ARCANE_KEY_2;
						} else if(isFemalesFortress()) {
							keyType = ItemType.IMP_FORTRESS_ARCANE_KEY_3;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(keyType), false));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_AUDIENCE_KEY = new DialogueNode("Step back", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE_KEY", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_VICTORY = new DialogueNode("Keep", ".", true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "You have defeated [npc.name] and [npc.her] imps!");
		}

		@Override
		public String getContent() {
			return ""; // Set in the leader's endCombat() method.
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Interactions";
				
			} else if(index==1) {
				return "Inventories";
				
			} else if(index==2) {
				return "Transformations";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", UtilText.parse(getBoss(), "Tell [npc.name] and [npc.her] gang to get out of here, before you change your mind..."), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang."),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'gentle' pace.)"),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'rough' pace.)"),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
							return new ResponseSex("Submit",
									UtilText.parse(getBoss(), "Feeling sorry for [npc.name] and [npc.her] imps, you decide to let them have some fun with your body before you force them to flee their fortress..."),
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null,
									Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
									null,
									null,
									null,
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
							
						} else if(isMalesFortress()) {
								SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
								return new ResponseSex("Submit",
										UtilText.parse(getBoss(), "Feeling sorry for [npc.name] and [npc.her] imps, you decide to let them have some fun with your body before you force them to flee their fortress..."),
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
										null,
										Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
										null,
										null,
										null,
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										null,
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
								
							}
						return new ResponseSex("Submit",
								UtilText.parse(getBoss(), "Feeling sorry for [npc.name] and [npc.her] imps, you decide to let them have some fun with your body before you force them to flee their fortress..."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc2.name] and [npc2.her] gang to get out of here, before you and [npc.name] change your minds..."), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
							return new ResponseSex("Solo submission",
									UtilText.parse(getMainCompanion(), getBoss(),
											"Tell [npc.name] to stand to one side, and then let [npc2.name] and [npc2.her] gang have some fun with your body, before forcing them to flee their fortress..."),
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null,
									Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
									null,
									null,
									null,
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
							
						} else if(isMalesFortress()) {
								SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
								return new ResponseSex("Solo submission",
										UtilText.parse(getMainCompanion(), getBoss(),
												"Tell [npc.name] to stand to one side, and then let [npc2.name] and [npc2.her] gang have some fun with your body, before forcing them to flee their fortress..."),
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
										null,
										Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
										null,
										null,
										null,
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
								
							}
						return new ResponseSex("Solo submission",
								UtilText.parse(getMainCompanion(), getBoss(),
										"Tell [npc.name] to stand to one side, and then let [npc2.name] and [npc2.her] gang have some fun with your body, before forcing them to flee their fortress..."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group sex",
									UtilText.parse(companion, getBoss(),
											"[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, getBoss(), "Have dominant sex with [npc2.name] and [npc2.her] imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									getPartyForSex(),
									getImpBossGroup(true),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group submission",
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							if(isAlphaFortress()) {
								SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
								return new ResponseSex("Group submission",
										UtilText.parse(getMainCompanion(), getBoss(), "Get [npc.name] to join you in submitting to [npc2.name] and [npc2.her] imps, allowing them to have dominant sex with the two of you."),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_APOLOGY", getAllCharacters())
										+ UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_ORAL"
													:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_ORAL_COMPANION_IMPS"
												:isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_IMPS_COMPANION_ORAL"
													:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_IMPS",
											getAllCharacters()));
								
							} else if(isMalesFortress()) {
									SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
									return new ResponseSex("Group submission",
											UtilText.parse(getMainCompanion(), getBoss(), "Get [npc.name] to join you in submitting to [npc2.name] and [npc2.her] imps, allowing them to have dominant sex with the two of you."),
											true,
											false,
											manager,
											getKeepDominantSpectators(manager),
											getKeepSubmissiveSpectators(manager),
											KEEP_AFTER_SEX_VICTORY,
											UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_APOLOGY", getAllCharacters())
											+ UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_BREEDING"
														:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_BREEDING_COMPANION_IMPS"
													:isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_IMPS_COMPANION_BREEDING"
														:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_IMPS",
												getAllCharacters()));
								}
							return new ResponseSex("Group submission",
									UtilText.parse(companion, getBoss(), "Get [npc.name] to join you in submitting to [npc2.name] and [npc2.her] imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters())){
								@Override
								public void effects() {
									((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
								}
							};
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] and [npc2.her] imps while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpBossGroup(true),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(),
											"You can tell that [npc.name] isn't at all interested in having sex with [npc2.name] and [npc2.her] imps, and you can't force [npc.herHim] to do so..."),
									null);
							
						} else { 
							if(isAlphaFortress()) {
								SexManagerInterface manager = getAlphaSexManager(getMainCompanion());
								return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
										UtilText.parse(companion, getBoss(), "Hand [npc.name] over to [npc2.name] and [npc2.her] imps, and watch as they have sex with [npc.herHim], before making them flee their fortress."),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_COMBAT_VICTORY_OFFER_COMPANION"
												:"KEEP_COMBAT_VICTORY_OFFER_COMPANION_IMPS",
											getAllCharacters()));
								
							} else if(isMalesFortress()) {
									SexManagerInterface manager = getMalesSexManager(getMainCompanion());
									return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
											UtilText.parse(companion, getBoss(), "Hand [npc.name] over to [npc2.name] and [npc2.her] imps, and watch as they have sex with [npc.herHim], before making them flee their fortress."),
											true,
											false,
											manager,
											getKeepDominantSpectators(manager),
											getKeepSubmissiveSpectators(manager),
											KEEP_AFTER_SEX_VICTORY,
											UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_OFFER_COMPANION"
													:"KEEP_COMBAT_VICTORY_OFFER_COMPANION_IMPS",
												getAllCharacters()));
								}
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(), "Hand [npc.name] over to [npc2.name] and [npc2.her] imps, and watch as they have sex with [npc.herHim], before making them flee their fortress."),
									true,
									false,
									new SMLyingDown(
											Util.newHashMapOfValues(
													new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
													new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
													new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
													new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
											Util.newHashMapOfValues(
													new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN))),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
										
									if(!companion.isAttractedTo(getBoss()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			}
		}
	};

	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT = new DialogueNode("Keep", ".", true) {
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc2.name] and [npc2.her] imps!";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isAlphaFortress()) {
				if(isCompanionDialogue()) {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_ORAL_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isAlphaBossWantingOral(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS_COMPANION_ORAL", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_ORAL", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS", getAllCharacters()));
					}
				}
				
			} else if(isMalesFortress()) {
				if(isCompanionDialogue()) {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_BREEDING_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isMaleBossWantingToBreed(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS_COMPANION_BREEDING", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_BREEDING", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS", getAllCharacters()));
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 || index == 2 || (index == 3 && Main.game.isNonConEnabled())) {
				
				String title = "Sex";
				String appendPace = "";
				String description = UtilText.parse(getBoss(), "Let [npc.name] and [npc.her] imps have sex with you.");
				ResponseTag tag = null;
				if(index==2) {
					title = "Eager sex";
					appendPace = "_EAGER";
					description = UtilText.parse(getBoss(), "Eagerly encourage [npc.name] and [npc.her] imps have sex with you.");
					tag = ResponseTag.START_PACE_PLAYER_SUB_EAGER;
				}
				if(index==3) {
					title = "Resist sex";
					appendPace = "_RESIST";
					description = UtilText.parse(getBoss(), "Struggle against [npc.name] and [npc.her] imps and do your best to resist having sex with them.");
					tag = ResponseTag.START_PACE_PLAYER_SUB_RESISTING;
				}
				
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion(), tag);
					return new ResponseSex(title,
							description,
							false,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_DEFEAT,
							(isCompanionDialogue()
								?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_ORAL"+appendPace
												:"KEEP_AFTER_COMBAT_DEFEAT_SEX_ORAL_COMPANION_IMPS"+appendPace
											:isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS_COMPANION_ORAL"+appendPace
												:"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_IMPS"+appendPace,
										getAllCharacters())
								:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?"KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace
											:"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS"+appendPace,
										getAllCharacters()))
							);
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion(), tag);
					return new ResponseSex(title,
									description,
									false,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_DEFEAT,
									isCompanionDialogue()
										?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_BREEDING"+appendPace
														:"KEEP_AFTER_COMBAT_DEFEAT_SEX_BREEDING_COMPANION_IMPS"+appendPace
													:isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS_COMPANION_BREEDING"+appendPace
														:"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_IMPS"+appendPace,
												getAllCharacters())
										:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?"KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace
													:"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS"+appendPace,
												getAllCharacters()));
				}
				return new ResponseSex(title,
						description,
						false,
						false,
						isCompanionDialogue()
							?new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO)))
							:new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace, getAllCharacters())){
					@Override
					public void effects() {
						((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_PACIFIED = new DialogueNode("Finished", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "[npc.Name] seems happy enough with your performance, and orders you to get away from [npc.herHim].");
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_PACIFIED_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_PACIFIED", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", UtilText.parse(getBoss(), "Do as [npc.name] commands and leave the keep."), KEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "Now that you've had your fun, you can step back and leave [npc.name] and [npc.her] imps to flee their fortress and disperse into the tunnels of Submission.");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Scare off", UtilText.parse(getBoss(), "Tell [npc.name] and [npc.her] imps to get out of here, and not come back."), KEEP_AFTER_SEX_VICTORY_SCARE_OFF) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_VICTORY_SCARE_OFF", getAllCharacters()));
							clearFortress();
						}
					};
				}
				
			} else if(responseTab==1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_VICTORY_SCARE_OFF = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.");
		}

		@Override
		public String getContent() {
			return ""; // Set in leader's endSex() method.
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(isAlphaFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ORAL_GIVING, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_DOMINANT, TFPotency.DRAIN, 0));
							
							FortressAlphaLeader boss = (FortressAlphaLeader) Main.game.getNpc(FortressAlphaLeader.class);
							
							if((boss).isAbleToEquipGag(Main.game.getPlayer())) {
								AbstractClothing ringGag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, effects);
								ringGag.setName(UtilText.parse(boss,"[npc.NamePos] 'Cock-Sucker' Ring gag"));
								Main.game.getPlayer().equipClothingFromNowhere(ringGag, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced you to wear:<br/>"
										+Main.game.getPlayer().getClothingInSlot(ringGag.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& (boss).isAbleToEquipGag(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing ringGag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_BROWN_DARK, PresetColour.CLOTHING_BLACK_STEEL, effects);
								ringGag.setName(UtilText.parse(boss,"[npc.NamePos] 'Cock-Sucker' Ring gag"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(ringGag, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced "
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+" to wear:<br/>"
											+ImpFortressDialogue.getMainCompanion().getClothingInSlot(ringGag.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
						} else if(isFemalesFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASTURBATION, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_EXHIBITIONIST, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_ASS, TFPotency.BOOST, 0));

							FortressFemalesLeader boss = (FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class);
							
							if(boss.isAbleToEquipButtPlug(Main.game.getPlayer())) {
								AbstractClothing buttPlug = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"),
										PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK_LIGHT, effects);
								buttPlug.setName(UtilText.parse(boss,"[npc.NamePos] 'Public Playtoy' Butt plug"));
								Main.game.getPlayer().equipClothingFromNowhere(buttPlug, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced you to wear:<br/>"
										+Main.game.getPlayer().getClothingInSlot(buttPlug.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& boss.isAbleToEquipButtPlug(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing buttPlug = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"),
										PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PERIWINKLE, PresetColour.CLOTHING_PERIWINKLE, effects);
								buttPlug.setName(UtilText.parse(boss,"[npc.NamePos] 'Public Playtoy' Butt plug"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(buttPlug, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced "
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+" to wear:<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(buttPlug.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
						} else if(isMalesFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_MALES)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_PREGNANCY, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, HipSize.FIVE_VERY_WIDE.getValue()),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));

							FortressMalesLeader boss = (FortressMalesLeader) Main.game.getNpc(FortressMalesLeader.class);
							
							if(boss.isAbleToEquipThong(Main.game.getPlayer())) {
								AbstractClothing thong = Main.game.getItemGen().generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, PresetColour.CLOTHING_RED_DARK, effects);
								thong.setName(UtilText.parse(boss,"[npc.NamePos] 'Breeder' Crotchless thong"));
								Main.game.getPlayer().equipClothingFromNowhere(thong, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced you to wear:<br/>"
										+Main.game.getPlayer().getClothingInSlot(thong.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							if(boss.isAbleToEquipDildo(Main.game.getPlayer())) {
								AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_vagina_insertable_dildo", PresetColour.CLOTHING_BLACK,
										Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0)));
								Main.game.getPlayer().equipClothingFromNowhere(dildo, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced you to wear:<br/>"
										+Main.game.getPlayer().getClothingInSlot(dildo.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& boss.isAbleToEquipThong(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing thong = Main.game.getItemGen().generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, PresetColour.CLOTHING_PINK_LIGHT, effects);
								thong.setName(UtilText.parse(boss,"[npc.NamePos] 'Breeder' Crotchless thong"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(thong, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced "
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+" to wear:<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(thong.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							if(ImpFortressDialogue.getMainCompanion()!=null && boss.isAbleToEquipDildo(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_vagina_insertable_dildo", PresetColour.CLOTHING_WHITE,
										Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0)));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(dildo, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+" has forced "
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+" to wear:<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(dildo.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
