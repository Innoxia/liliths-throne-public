package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69.9
 * @version 0.1.95
 * @author Innoxia
 */
public enum ParserTarget {
	
	STYLE(Util.newArrayListOfValues(
			new ListValue<>("style")),
			"Returns the same as 'pc', but should be used for style methods such as 'bold' or 'italics'.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	PC(Util.newArrayListOfValues(
			new ListValue<>("pc"),
			new ListValue<>("player")),
			"The player character.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	NPC(Util.newArrayListOfValues(
			new ListValue<>("npc"),
			new ListValue<>("npc1"),
			new ListValue<>("npc2"),
			new ListValue<>("npc3"),
			new ListValue<>("npc4"),
			new ListValue<>("npc5"),
			new ListValue<>("npc6")),
			"The currently 'active' NPC.</br>"
			+"<b>The tag 'npc' can be extended with a number, starting at 1, to signify which npc in the scene it is referring to!</b> e.g. 'npc1' is the first npc, 'npc2' is the second, etc.</br>"
			+ "If in <b>combat</b>, it returns your opponent.</br>"
			+ "If in <b>sex</b>, it returns your partner.</br>"
			+ "<b>Otherwise</b>, it returns the most important NPC in the scene.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					if(!UtilText.getSpecialNPCList().isEmpty()) {
						if(tag.equalsIgnoreCase("npc")) {
							return UtilText.getSpecialNPCList().get(0);
						} else {
							return UtilText.getSpecialNPCList().get(Math.max(0, Integer.parseInt(tag.substring(3))-1));
						}
						
					} else if(Main.game.isInCombat()) {
						return Combat.getActiveNPC();
						
					} else if (Main.game.isInSex()) {
						return Sex.getActivePartner();
						
					} else if (Main.game.getCurrentDialogueNode()!=null) {
						if(Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS) {
							return CharactersPresentDialogue.characterViewed;
							
						} else if(Main.game.getActiveNPC()!=null) {
							return Main.game.getActiveNPC();
							
						} else if (!Main.game.getCharactersPresent().isEmpty()) {
							return Main.game.getCharactersPresent().get(0);
							
						} else {
							throw new NullPointerException();
						}
						
					} else {
						throw new NullPointerException();
					}
				}
			},
	
	PROLOGUE_MALE(Util.newArrayListOfValues(
			new ListValue<>("prologueMale")), ""){
		public String getDescription() {
			return Main.game.getPrologueMale().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPrologueMale();
		}
	},
	
	PROLOGUE_FEMALE(Util.newArrayListOfValues(
			new ListValue<>("prologueFemale")), ""){
		public String getDescription() {
			return Main.game.getPrologueFemale().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPrologueFemale();
		}
	},
	
	NPC_MALE(Util.newArrayListOfValues(
			new ListValue<>("NPCmale"),
			new ListValue<>("maleNPC")), ""){
		public String getDescription() {
			return Main.game.getGenericMaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericMaleNPC();
		}
	},
	
	NPC_FEMALE(Util.newArrayListOfValues(
			new ListValue<>("NPCfemale"),
			new ListValue<>("femaleNPC")), ""){
		public String getDescription() {
			return Main.game.getGenericFemaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericFemaleNPC();
		}
	},
	
	NPC_ANDROGYNOUS(Util.newArrayListOfValues(
			new ListValue<>("NPCandrogynous"),
			new ListValue<>("androgynousNPC"),
			new ListValue<>("NPCambiguous"),
			new ListValue<>("ambiguousNPC")), ""){
		public String getDescription() {
			return Main.game.getGenericAndrogynousNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericAndrogynousNPC();
		}
	},
	
	TEST_NPC(Util.newArrayListOfValues(
			new ListValue<>("testNPC"),
			new ListValue<>("test")), ""){
		public String getDescription() {
			return Main.game.getTestNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getTestNPC();
		}
	},
	
	LILAYA(Util.newArrayListOfValues(
			new ListValue<>("lilaya"),
			new ListValue<>("aunt")), ""){
		public String getDescription() {
			return Main.game.getLilaya().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getLilaya();
		}
	},
	
	ROSE(Util.newArrayListOfValues(
			new ListValue<>("rose")), ""){
		public String getDescription() {
			return Main.game.getRose().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getRose();
		}
	},
	
	KATE(Util.newArrayListOfValues(
			new ListValue<>("kate")), ""){
		public String getDescription() {
			return Main.game.getKate().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKate();
		}
	},
	
	RALPH(Util.newArrayListOfValues(
			new ListValue<>("ralph")), ""){
		public String getDescription() {
			return Main.game.getRalph().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getRalph();
		}
	},
	
	NYAN(Util.newArrayListOfValues(
			new ListValue<>("nyan")), ""){
		public String getDescription() {
			return Main.game.getNyan().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getNyan();
		}
	},
	
	VICKY(Util.newArrayListOfValues(
			new ListValue<>("vicky")), ""){
		public String getDescription() {
			return Main.game.getVicky().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getVicky();
		}
	},
	
	PIX(Util.newArrayListOfValues(
			new ListValue<>("pix")), ""){
		public String getDescription() {
			return Main.game.getPix().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPix();
		}
	},
	
	BRAX(Util.newArrayListOfValues(
			new ListValue<>("brax")), ""){
		public String getDescription() {
			return Main.game.getBrax().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getBrax();
		}
	},
	
	CANDI(Util.newArrayListOfValues(
			new ListValue<>("candi")), ""){
		public String getDescription() {
			return Main.game.getCandi().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getCandi();
		}
	},
	
	SCARLETT(Util.newArrayListOfValues(
			new ListValue<>("scarlett")), ""){
		public String getDescription() {
			return Main.game.getScarlett().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getScarlett();
		}
	},
	
	ALEXA(Util.newArrayListOfValues(
			new ListValue<>("alexa")), ""){
		public String getDescription() {
			return Main.game.getAlexa().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAlexa();
		}
	},
	
	HARPY_BIMBO(Util.newArrayListOfValues(
			new ListValue<>("brittany"),
			new ListValue<>("bimboHarpy"),
			new ListValue<>("harpyBimbo")), ""){
		public String getDescription() {
			return Main.game.getHarpyBimbo().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyBimbo();
		}
	},
	
	HARPY_BIMBO_COMPANION(Util.newArrayListOfValues(
			new ListValue<>("lauren"),
			new ListValue<>("bimboHarpyCompanion"),
			new ListValue<>("harpyBimboCompanion")), ""){
		public String getDescription() {
			return Main.game.getHarpyBimboCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyBimboCompanion();
		}
	},
	
	HARPY_DOMINANT(Util.newArrayListOfValues(
			new ListValue<>("diana"),
			new ListValue<>("dominantHarpy"),
			new ListValue<>("harpyDominant")), ""){
		public String getDescription() {
			return Main.game.getHarpyDominant().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyDominant();
		}
	},
	
	HARPY_DOMINANT_COMPANION(Util.newArrayListOfValues(
			new ListValue<>("harley"),
			new ListValue<>("dominantHarpyCompanion"),
			new ListValue<>("harpyDominantCompanion")), ""){
		public String getDescription() {
			return Main.game.getHarpyDominantCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyDominantCompanion();
		}
	},
	
	HARPY_NYMPHO(Util.newArrayListOfValues(
			new ListValue<>("lexi"),
			new ListValue<>("nymphoHarpy"),
			new ListValue<>("harpyNympho")), ""){
		public String getDescription() {
			return Main.game.getHarpyNympho().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyNympho();
		}
	},
	
	HARPY_NYMPHO_COMPANION(Util.newArrayListOfValues(
			new ListValue<>("max"),
			new ListValue<>("nymphoHarpyCompanion"),
			new ListValue<>("harpyNymphoCompanion")), ""){
		public String getDescription() {
			return Main.game.getHarpyNymphoCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyNymphoCompanion();
		}
	},
	
	PAZU(Util.newArrayListOfValues(
			new ListValue<>("pazu")), ""){
		public String getDescription() {
			return Main.game.getPazu().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPazu();
		}
	},
	
	FINCH(Util.newArrayListOfValues(
			new ListValue<>("finch")), ""){
		public String getDescription() {
			return Main.game.getFinch().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getFinch();
		}
	},
	
	ZARANIX(Util.newArrayListOfValues(
			new ListValue<>("zaranix")), ""){
		public String getDescription() {
			return Main.game.getZaranix().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getZaranix();
		}
	},
	
	AMBER(Util.newArrayListOfValues(
			new ListValue<>("amber")), ""){
		public String getDescription() {
			return Main.game.getAmber().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAmber();
		}
	},
	
	ARTHUR(Util.newArrayListOfValues(
			new ListValue<>("arthur")), ""){
		public String getDescription() {
			return Main.game.getArthur().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getArthur();
		}
	},
	
	KELLY(Util.newArrayListOfValues(
			new ListValue<>("kelly")), ""){
		public String getDescription() {
			return Main.game.getKelly().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKelly();
		}
	},
	
	KATHERINE(Util.newArrayListOfValues(
			new ListValue<>("katherine")), ""){
		public String getDescription() {
			return Main.game.getKatherine().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKatherine();
		}
	},
	
	ASHLEY(Util.newArrayListOfValues(
			new ListValue<>("ashley")), ""){
		public String getDescription() {
			return Main.game.getAshley().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAshley();
		}
	},
	
	WOLFGANG(Util.newArrayListOfValues(
			new ListValue<>("wolfgang"),
			new ListValue<>("supplierLeader")), ""){
		public String getDescription() {
			return Main.game.getSupplierLeader().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getSupplierLeader();
		}
	},
	
	KARL(Util.newArrayListOfValues(
			new ListValue<>("karl"),
			new ListValue<>("supplierPartner")), ""){
		public String getDescription() {
			return Main.game.getSupplierPartner().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getSupplierPartner();
		}
	},
	;
	
	
	
	private String description;
	private List<String> tags;
	private ParserTarget(List<String> tags, String description) {
		this.tags=tags;
		this.description=description;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public String getDescription() {
		return description;
	}
	
	public abstract GameCharacter getCharacter(String tag);
}
