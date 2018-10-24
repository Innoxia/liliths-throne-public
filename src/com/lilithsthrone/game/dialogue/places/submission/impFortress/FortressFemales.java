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
public class FortressFemales {
	
	public static void clearFortress() {
		Main.game.getDialogueFlags().impFortressFemalesPacifiedTime = Main.game.getMinutesPassed();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, true);

		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES) {
				character.returnToHome();
			}
		}
	}
	
	public static void resetFortress() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, false);
		banishImpGuards();
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
			imp.setGenericName("alpha-imp leader");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
			Main.game.addNPC(imp, false);
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
			imp.setGenericName("sultry alpha-imp");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
			Main.game.addNPC(imp, false);
			impGroup.add(imp);
			
			for(GameCharacter impCharacter : impGroup) {
				impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE);
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
	}
	
	public static List<GameCharacter> getImpGuards() {
		List<GameCharacter> impGuards = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE)) {
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "ENTRANCE"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Head back out into the tunnels.", PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "LEAVE_FORTRESS"));
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "KEEP_AFTER_COMBAT_VICTORY"));
			
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "KEEP_AFTER_COMBAT_DEFEAT"));
			
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "COURTYARD"));
			
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "KEEP"));
			
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "KEEP_AFTER_COMBAT_VICTORY"));
			
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressFemales", "KEEP_AFTER_COMBAT_DEFEAT"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

}
