package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class ImpFortressDialogue {

	private static boolean isAlphaFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA;
	}

	private static boolean isFemalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_FEMALES;
	}

	private static boolean isMalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES;
	}
	
	private static PlaceType getSubmissionFortress() {
		if(isAlphaFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA;
		} else if(isFemalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES;
		} else if(isMalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_MALES;
		}
		return null;
	}

	private static boolean isGuardsDefeated() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation()).isEmpty();
	}
	
	private static boolean isGuardsPacified() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesGuardsPacified);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesGuardsPacified);
		}
	}
	
	private static boolean isGuardsPacifiedBySex() {
		return isGuardsPacified() && Main.game.getPlayer().getTotalTimesHadSex(getImpGuards().get(0))>0;
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
	
	private static boolean isDefeated() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
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
	
	private static void clearFortress() {
		clearFortress(Main.game.getPlayer().getWorldLocation());
	}
	
	public static void clearFortress(WorldType fortress) {
		
		banishImpGuards(fortress);
		
		for(GameCharacter character : getImpBossGroup(fortress)) {
			if(!character.equals(getBoss(fortress))) {
				Main.game.banishNPC(character.getId());
			}
		}
		getBoss(fortress).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			Main.game.getDialogueFlags().impFortressAlphaDefeatedTime = Main.game.getMinutesPassed();
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			Main.game.getDialogueFlags().impFortressFemalesDefeatedTime = Main.game.getMinutesPassed();
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			Main.game.getDialogueFlags().impFortressMalesDefeatedTime = Main.game.getMinutesPassed();
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_MALES) {
					character.returnToHome();
				}
			}
		}
	}
	
	public static void resetFortress(WorldType fortress) {
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
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP);
					((NPC)impCharacter).equipClothing(true, true, true, true);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getFortressAlphaLeader().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP);
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA) {
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
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP);
					((NPC)impCharacter).equipClothing(true, true, true, true);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getFortressFemalesLeader().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP);
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES) {
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
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP);
					((NPC)impCharacter).equipClothing(true, true, true, true);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getFortressMalesLeader().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP);
			
			// Move NPCs into hiding:
			Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
			for(int i=0; i< cells.length;i++) {
				for(int j=0; j< cells[i].length;j++) {
					Cell cell = cells[j][i];
					if(cell.getPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_MALES) {
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
	
	private static List<GameCharacter> getImpBossGroup() {
		return getImpBossGroup(Main.game.getPlayer().getWorldLocation());
	}
	
	public static List<GameCharacter> getImpBossGroup(WorldType fortress) {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP)) {
				if((character instanceof ImpAttacker || character instanceof FortressAlphaLeader) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP)) {
				if((character instanceof ImpAttacker || character instanceof FortressFemalesLeader) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP)) {
				if((character instanceof ImpAttacker || character instanceof FortressMalesLeader) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
		}
		
		bossGroup.sort((imp1, imp2) -> imp1.getLevel()-imp2.getLevel());
		return bossGroup;
	}

	private static List<GameCharacter> getImpGuards() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation());
	}
		
	public static List<GameCharacter> getImpGuards(WorldType fortress) {
		
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
		
		impGuards.sort((imp1, imp2) -> imp1.getLevel()-imp2.getLevel());
		return impGuards;
	}

	private static GameCharacter getBoss() {
		return getBoss(Main.game.getPlayer().getWorldLocation());
	}
	
	public static GameCharacter getBoss(WorldType fortress) {

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			return Main.game.getFortressAlphaLeader();

		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			return Main.game.getFortressFemalesLeader();

		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			return Main.game.getFortressMalesLeader();
		}
		
		return null;
	}

	private static ImpAttacker getImpGuardLeader() {
		return getImpGuardLeader(Main.game.getPlayer().getWorldLocation());
	}
	
	public static ImpAttacker getImpGuardLeader(WorldType fortress) {
		return (ImpAttacker) getImpGuards(fortress).get(0);
	}

	private static void banishImpGuards() {
		banishImpGuards(Main.game.getPlayer().getWorldLocation());
	}

	public static void banishImpGuards(WorldType fortress) {
		for(GameCharacter imp : getImpGuards(fortress)) {
			if(!imp.isSlave() && imp.getPartyLeader()==null) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		// There's a reason I cna't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_ALPHA_ENTRANCE
				|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_FEMALES_ENTRANCE
				|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_MALES_ENTRANCE) {
			allCharacters.addAll(getImpGuards(Main.game.getPlayer().getWorldLocation()));
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_ALPHA_KEEP
				|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_FEMALES_KEEP
				|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_MALES_KEEP) {
			allCharacters.addAll(getImpBossGroup(Main.game.getPlayer().getWorldLocation()));
		}

		Collections.sort(allCharacters, (c1, c2) -> c1 instanceof Elemental?(c2 instanceof Elemental?0:1):(c2 instanceof Elemental?-1:0));
		return allCharacters;
	}
	
	private static void resetGuards(WorldType fortress) {
		List<String> impAdjectives = new ArrayList<>();
		List<GameCharacter> impGroup = new ArrayList<>();

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("alpha-imp leader");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("alpha-imp archer");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE);
					((NPC)impCharacter).equipClothing(true, true, true, true);
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
				impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE);
					((NPC)impCharacter).equipClothing(true, true, true, true);
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
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				imp.setGenericName("alpha-imp brawler");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE);
					((NPC)impCharacter).equipClothing(true, true, true, true);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsPacified, false);
			}
		}
	}
	
	private static String getDialogueEncounterId() {
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
	
	// Dialogues:
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Gateway", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isTravelDisabled() {
			return !isGuardsPacified() && !isGuardsDefeated();
		}
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isGuardsPacifiedBySex()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_SEX", getAllCharacters()));
				
			} else if(isGuardsPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_PACIFIED", getAllCharacters()));
				
			} else if(isGuardsDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DESERTED", getAllCharacters()));
				if(!isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DESERTED_GUARD_RETURN_WARNING", getAllCharacters()));
				}
				
			} else if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DEMON", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
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
							if(isGuardsDefeated()) {
								resetGuards(Main.game.getPlayer().getWorldLocation());
							}
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters()));
						}
					};
	
				} else if(!isGuardsDefeated()) {
					if(index==2) {
						return new ResponseSex(isCompanionDialogue()?"Offer oral (solo)":"Offer oral",
								isGuardsPacifiedBySex()
									?"Agree with the imp's taunts, and offer to perform oral sex on them again."
									:"Offer to perform oral sex on the imps.",
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
								GUARDS_AFTER_ORAL_FOR_ENTRY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_PACIFIED", getAllCharacters()),
								ResponseTag.PREFER_ORAL) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
							}
						};
						
					} else if(index==3 && isCompanionDialogue()) {
						if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
							return new Response("Offer oral (both)",
									UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in performing oral sex on the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex("Offer oral (both)",
									isGuardsPacifiedBySex()
										?UtilText.parse(getMainCompanion(), "Agree with the imp's taunts, and tell them that you and [npc.name] are willing to perform oral sex on them again.")
										:UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] want to perform oral sex on them."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGuards(),
									null,
									GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_WITH_COMPANION_PACIFIED", getAllCharacters()),
									ResponseTag.PREFER_ORAL) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
								}
							};
						}
						
					}
					if(isCompanionDialogue()?index==4:index==3) {
						return new ResponseCombat("Attack", "Change your mind about not wanting to fight the imps, and decide to teach them a lesson!", getImpGuards(), null);
					}
				}
				return null;
				
			} else {
				if (index == 1) {
					if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
						return new Response("Command",
								"The imps seem incredibly nervous at the prospect of being confronted by a demon. Use this to your advantage and order them to step aside.",
								ENTRANCE_DEMONIC_COMMAND) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
							}
						};
					} else {
						return new Response("Command",
								"If you were a demon, perhaps you'd be able to intimidate the imps. As you're not, however, it looks like you're going to have to fight them...",
								null);
					}
	
				} else if(index==2) {
					return new ResponseCombat("Attack", "Defend yourself against the imps!", getImpGuards(), null);
					
				} else if(index==3) {
					return new ResponseSex(isCompanionDialogue()?"Give oral (solo)":"Give oral",
							"Agree to perform oral sex on the imps in exchange for them letting you into the fortress.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getImpGuards(),
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							GUARDS_AFTER_ORAL_FOR_ENTRY,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_ORAL", getAllCharacters()),
							ResponseTag.PREFER_ORAL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
						}
					};
					
				} else if(index==4 && isCompanionDialogue()) {
					if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
						return new Response("Give oral (both)",
								UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in performing oral sex on the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex("Give oral (both)",
								UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] will perform oral sex on them in exchange for being let into the fortress."),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGuards(),
								null,
								GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_WITH_COMPANION", getAllCharacters()),
								ResponseTag.PREFER_ORAL) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_ORAL_FOR_ENTRY = new DialogueNodeOld("Finished", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNodeOld GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION = new DialogueNodeOld("Finished", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_DEMONIC_COMMAND = new DialogueNodeOld("", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DEMONIC_COMMAND", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
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
						return new Response("Scare off", "Tell the imps to get out of here while they still can...", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								getImpGuards(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								getImpGuards(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
								true,
								false,
								getImpGuards(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
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
								Main.game.getPlayer().getParty(),
								getImpGuards(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
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
						return new Response("Scare off", "Tell the imps to get out of here while they still can...", Main.game.getDefaultDialogueNoEncounter()) {
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
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the gentle pace.)"),
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the rough pace.)"),
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
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
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group sex",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, "Have dominant sex with the imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									getImpGuards(),
									Main.game.getPlayer().getParty(),
									null,
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGuards(),
									null,
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the imps while you watch."),
									false,
									false,
									getImpGuards(),
									Util.newArrayListOfValues(getMainCompanion()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "Hand [npc.name] over to the imps, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGuards(),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
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

	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the imps to move you into position...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpGuards(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpGuards(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"Try to resist as the gang of imps move you into position...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpGuards(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESIST);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
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
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
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
	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
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

	public static final DialogueNodeOld COURTYARD = new DialogueNodeOld("Courtyard", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
							if(isGuardsDefeated()) {
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

	public static final DialogueNodeOld KEEP = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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

	public static final DialogueNodeOld KEEP_ENTRY = new DialogueNodeOld("Keep", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED", getAllCharacters()));
				
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
					return new Response("Leave", UtilText.parse(getBoss(), "Tell [npc.name] that you were just checking for any threats, before taking your leave."), KEEP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_LEAVE", getAllCharacters()));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, true);
						}
					};
					
				} else if(index==1) {
					return new ResponseSex(isCompanionDialogue()?"Oral (solo)":"Oral",
							UtilText.parse(getBoss(), "Respond to [npc.namePos] question in the affirmative, and tell [npc.herHim] that you were hoping to spend some time under [npc.her] table again..."),
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getImpBossGroup(),
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							KEEP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_ORAL", getAllCharacters())) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, true);
						}
					};
					
				} else if(index==2 && isCompanionDialogue()) {
					if(!getMainCompanion().isAttractedTo(getBoss()) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
						return new Response("Oral (both)",
								UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name] is not interested in performing oral on [npc2.name], and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else {
						return new ResponseSex("Oral (both)",
								UtilText.parse(getMainCompanion(), getBoss(), "Respond to [npc2.namePos] question in the positive, and tell [npc.herHim] that both you and [npc.name] were hoping to spend some time under [npc.her] table again..."),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpBossGroup(),
								null,
								KEEP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_ORAL_WITH_COMPANION", getAllCharacters())) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, true);
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Negotiate", UtilText.parse(getBoss(), "Tell [npc.name] that you've come to talk, not fight."), KEEP_TALK);
	
				} else if(index==2) {
					return new ResponseCombat("Attack", UtilText.parse(getBoss(), "Defend yourself against [npc.name] and [npc.her] minions!"), getImpBossGroup(), null);
					
				} else if(index==3) {
					return new ResponseSex(isCompanionDialogue()?"Surrender (solo)":"Surrender",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), 
										"Tell [npc.name] to stand back as you surrender your body to [npc2.name] and [npc2.her] imps in exchange for being allowed to leave without a fight.")
								:UtilText.parse(getBoss(), "Surrender your body to [npc.name] and [npc.her] imps in exchange for being allowed to leave without a fight."),
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getImpBossGroup(),
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							KEEP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL", getAllCharacters()));
					
				} else if(index==4 && isCompanionDialogue()) {
					return new ResponseSex("Surrender (both)",
							UtilText.parse(getMainCompanion(), getBoss(), "Surrender both yourself and [npc.name] to [npc2.name], allowing [npc2.herHim] and [npc.2her] imps to do what they please with you."),
							true,
							false,
							Main.game.getPlayer().getParty(),
							getImpBossGroup(),
							null,
							KEEP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters())) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
						}
					};
					
				} else {
					return null;
				}
			}
		
		}
	};

	public static final DialogueNodeOld KEEP_TALK = new DialogueNodeOld("Keep", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_TALK", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Spell> elementalSpells = Util.newArrayListOfValues(Spell.ELEMENTAL_AIR, Spell.ELEMENTAL_ARCANE, Spell.ELEMENTAL_FIRE, Spell.ELEMENTAL_EARTH, Spell.ELEMENTAL_WATER);
			
			if (index == 1) {
				if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
					return new Response("Convince",
							UtilText.parse(getBoss(), "Convince [npc.name] that you're on good terms with your Lilin mother, and that [npc.she]'ll be duly punished if [npc.she] refuses to leave this place."), KEEP_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
							clearFortress();
						}
					};
				} else {
					return new Response("Convince", UtilText.parse(getBoss(), "As you're not a demon, you can't convince [npc.name] that you're more important than [npc.she] is..."), null);
				}

			} else if (index == 2) {
				return new Response("Join gang",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc2.name] that you and [npc.name] are interested in joining [npc2.her] gang...")
							:UtilText.parse(getBoss(), "Tell [npc.name] that you were interested in joining [npc.her] gang..."),
						KEEP_JOIN);
				
			} else if (index == 3) {
				if(Main.game.getPlayer().isElementalSummoned()) {
					return new Response("Elemental",
							UtilText.parse(getBoss(), "Intimidate [npc.name] and [npc.her] gang by drawing attention to the fact that you are powerful enough to have a summoned elemental with you..."), KEEP_ELEMENTAL) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
							clearFortress();
						}
					};
					
				} else if((Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer()))) {
					return new Response("Elemental", UtilText.parse(getBoss(), "Intimidate [npc.name] and [npc.her] gang by summoning your elemental in front of them."), KEEP_ELEMENTAL_SUMMON) {
						@Override
						public void effects() {
							for(Spell spell : elementalSpells) {
								if(Main.game.getPlayer().hasSpell(spell) && Main.game.getPlayer().getMana()>=spell.getModifiedCost(Main.game.getPlayer())) {
									Main.game.getTextEndStringBuilder().append(spell.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
									break;
								}
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ELEMENTAL_SUMMON_END", getAllCharacters()));

							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
							clearFortress();
						}
					};
					
				} else {
					return new Response("Elemental", UtilText.parse(getBoss(), "You'd need to have an elemental summoned, or know such a spell and have enough aura to cast it, if you wanted to intimidate [npc.name]..."), null);
				}
				
			} else if(index==4) {
				return new ResponseCombat("Fight", UtilText.parse(getBoss(), "This clearly isn't working... Choose to fight instead of talking, and defend yourself against [npc.name] and [npc.her] minions!"), getImpBossGroup(), null);
				
			} else if(index==5) {
				return new ResponseSex(isCompanionDialogue()?"Surrender (solo)":"Surrender",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Decide that you have no chance against [npc2.name] and [npc2.her] minions, and, telling [npc.name] to stand aside and watch, surrender yourself to them.")
							:UtilText.parse(getBoss(), "Decide that you have no chance against [npc.name] and [npc.her] minions, and surrender yourself to them."),
						true,
						false,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						getImpBossGroup(),
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL", getAllCharacters()));
				
			} else if(index==6 && isCompanionDialogue()) {
				return new ResponseSex("Surrender (both)",
						UtilText.parse(getMainCompanion(), getBoss(), "Decide that you and [npc.name] have no chance against [npc2.name] and [npc2.her] minions, and surrender yourselves to them."),
						true,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters()));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld KEEP_CONVINCE = new DialogueNodeOld("Keep", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_CONVINCE", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_JOIN = new DialogueNodeOld("Keep", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			// You can't join, but she can send you to the demon fortress to ask their main boss
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_JOIN", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Refuse", UtilText.parse(getBoss(), "You aren't prepared to go that far! Tell [npc.name] as such, and prepare to defend yourself!"), getImpBossGroup(), null);
				
			} else if(index==2) {
				return new ResponseSex(isCompanionDialogue()?"Agree (solo)":"Agree",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and, after telling [npc.name] to stand aside and keep watch, get down under the table to orally service [npc2.herHim]...")
							:UtilText.parse(getBoss(), "Decide to do as [npc.name] demands, and get down under the table to orally service [npc.herHim]..."),
						true,
						false,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						getImpBossGroup(),
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_JOINING_SEX,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_JOIN_ACCEPT", getAllCharacters())) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
					}
				};
				
			} else if(index==3 && isCompanionDialogue()) {
				return new ResponseSex("Agree (both)",
						UtilText.parse(getMainCompanion(), getBoss(), "Decide to do as [npc2.name] demands, and get [npc.name] to join you as the two of you service [npc2.herHim] under the table."),
						true,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_JOINING_SEX,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_JOIN_ACCEPT_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld KEEP_ELEMENTAL = new DialogueNodeOld("Keep", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ELEMENTAL", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_ELEMENTAL_SUMMON = new DialogueNodeOld("Keep", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ELEMENTAL_SUMMON", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Keep", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "You have defeated [npc.name] and [npc.her] imps!");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
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
						return new Response("Scare off", UtilText.parse(getBoss(), "Tell [npc.name] and [npc.her] gang to get out of here, before you change your mind..."), Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang."),
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'gentle' pace.)"),
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'rough' pace.)"),
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
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
								Main.game.getPlayer().getParty(),
								getImpBossGroup(),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
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
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
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
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc2.name] and [npc2.her] gang to get out of here, before you and [npc.name] change your minds..."), Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang."),
								true,
								false,
								getImpBossGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang. (Start sex in the 'gentle' pace.)"),
								true,
								false,
								getImpBossGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang. (Start sex in the 'rough' pace.)"),
								true,
								false,
								getImpBossGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
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
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group sex",
									UtilText.parse(companion, getBoss(),
											"[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, getBoss(), "Have dominant sex with [npc2.name] and [npc2.her] imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									getImpBossGroup(),
									Main.game.getPlayer().getParty(),
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group submission",
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, getBoss(), "Get [npc.name] to join you in submitting to [npc2.name] and [npc2.her] imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpBossGroup(),
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] and [npc2.her] imps while you watch."),
									false,
									false,
									getImpBossGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(),
											"You can tell that [npc.name] isn't at all interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(), "Hand [npc.name] over to [npc2.name] and [npc2.her] imps, and watch as they have sex with [npc.herHim], before making them flee their fortress."),
									true,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpBossGroup(),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "KEEP_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
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
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
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
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
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

	public static final DialogueNodeOld KEEP_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc2.name] and [npc2.her] imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Allow [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Allow [npc.name] and [npc.her] imps to move you and [npc.name] into position..."),
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Eagerly help [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Eagerly help [npc.name] and [npc.her] imps to move you into position..."),
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Try to resist as [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Try to resist as [npc.name] and [npc.her] imps start to move you into position..."),
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESIST);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_JOINING_SEX = new DialogueNodeOld("Finished", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "[npc.Name] seems happy enough with your performance, and orders you get out from under the table.");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_JOINING_SEX", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to use the arcane key to access the imp citadel, so that you may ask the 'Dark Siren' if you can join her gang...", KEEP_AFTER_JOINING_GAIN_KEY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_JOINING_GAIN_KEY = new DialogueNodeOld("Step back", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_JOINING_GAIN_KEY", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
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
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
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
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.");
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						if(isGuardsDefeated()) {
							resetGuards(Main.game.getPlayer().getWorldLocation());
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
