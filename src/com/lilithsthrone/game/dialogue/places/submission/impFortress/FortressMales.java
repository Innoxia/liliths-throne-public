package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
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
public class FortressMales {
	
	public static void clearFortress() {
		Main.game.getDialogueFlags().impFortressMalesPacifiedTime = Main.game.getMinutesPassed();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, true);

		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_MALES) {
				character.returnToHome();
			}
		}
	}
	
	public static void resetFortress() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, false);
		banishImpGuards();
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
			imp.setGenericName("alpha-imp leader");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
			imp.setGenericName("alpha-imp brawler");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
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
	
	public static List<GameCharacter> getImpGuards() {
		List<GameCharacter> impGuards = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE)) {
			if(character instanceof ImpAttacker && !character.isSlave()) {
				impGuards.add(character);
			}
		}
		impGuards.sort((imp1, imp2) -> imp1.getLevel()-imp2.getLevel());
		return impGuards;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) getImpGuards().get(0);
	}

	public static void banishImpGuards() {
		for(GameCharacter imp : getImpGuards()) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}

	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Gateway", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressMales", "ENTRANCE"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Head back out into the tunnels.", PlaceType.SUBMISSION_IMP_FORTRESS_MALES.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressMales", "LEAVE_FORTRESS"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_AFTER_COMBAT_VICTORY"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_AFTER_COMBAT_DEFEAT"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressMales", "COURTYARD"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressMales", "KEEP"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressMales", "KEEP_AFTER_COMBAT_VICTORY"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressMales", "KEEP_AFTER_COMBAT_DEFEAT"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
