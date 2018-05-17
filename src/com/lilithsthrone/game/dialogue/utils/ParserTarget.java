package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69.9
 * @version 0.2.5
 * @author Innoxia
 */
public enum ParserTarget {
	
	STYLE(Util.newArrayListOfValues(
			"style",
			"game"),
			"Returns the same as 'pc', but should be used for style methods such as style.bold or style.italics or conditional methods such as game.isArcaneStorm.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	PC(Util.newArrayListOfValues(
			"pc",
			"player"),
			"The player character.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	NPC(Util.newArrayListOfValues(
			"npc",
			"npc1",
			"npc2",
			"npc3",
			"npc4",
			"npc5",
			"npc6"),
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
						if(Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS_CHARACTER) {
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
	
	PROLOGUE_MALE(Util.newArrayListOfValues("prologueMale"), "") {
		public String getDescription() {
			return Main.game.getPrologueMale().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPrologueMale();
		}
	},
	
	PROLOGUE_FEMALE(Util.newArrayListOfValues("prologueFemale"), "") {
		public String getDescription() {
			return Main.game.getPrologueFemale().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPrologueFemale();
		}
	},
	
	NPC_MALE(Util.newArrayListOfValues(
			"NPCmale",
			"maleNPC",
			"genericMale",
			"maleGeneric"), "") {
		public String getDescription() {
			return Main.game.getGenericMaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericMaleNPC();
		}
	},
	
	NPC_FEMALE(Util.newArrayListOfValues(
			"NPCfemale",
			"femaleNPC",
			"genericFemale",
			"femaleGeneric"), "") {
		public String getDescription() {
			return Main.game.getGenericFemaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericFemaleNPC();
		}
	},
	
	NPC_ANDROGYNOUS(Util.newArrayListOfValues(
			"NPCandrogynous",
			"androgynousNPC",
			"NPCambiguous",
			"ambiguousNPC"), "") {
		public String getDescription() {
			return Main.game.getGenericAndrogynousNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericAndrogynousNPC();
		}
	},
	
	TEST_NPC(Util.newArrayListOfValues(
			"testNPC",
			"test"), "") {
		public String getDescription() {
			return Main.game.getTestNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getTestNPC();
		}
	},
	
	LILAYA(Util.newArrayListOfValues(
			"lilaya",
			"aunt"), "") {
		public String getDescription() {
			return Main.game.getLilaya().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getLilaya();
		}
	},
	
	ROSE(Util.newArrayListOfValues("rose"), "") {
		public String getDescription() {
			return Main.game.getRose().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getRose();
		}
	},
	
	KATE(Util.newArrayListOfValues("kate"), "") {
		public String getDescription() {
			return Main.game.getKate().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKate();
		}
	},
	
	RALPH(Util.newArrayListOfValues("ralph"), "") {
		public String getDescription() {
			return Main.game.getRalph().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getRalph();
		}
	},
	
	NYAN(Util.newArrayListOfValues("nyan"), "") {
		public String getDescription() {
			return Main.game.getNyan().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getNyan();
		}
	},
	
	VICKY(Util.newArrayListOfValues("vicky"), "") {
		public String getDescription() {
			return Main.game.getVicky().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getVicky();
		}
	},
	
	PIX(Util.newArrayListOfValues("pix"), "") {
		public String getDescription() {
			return Main.game.getPix().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPix();
		}
	},
	
	BRAX(Util.newArrayListOfValues("brax"), "") {
		public String getDescription() {
			return Main.game.getBrax().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getBrax();
		}
	},
	
	CANDI(Util.newArrayListOfValues("candi"), "") {
		public String getDescription() {
			return Main.game.getCandi().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getCandi();
		}
	},
	
	SCARLETT(Util.newArrayListOfValues("scarlett"), "") {
		public String getDescription() {
			return Main.game.getScarlett().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getScarlett();
		}
	},
	
	ALEXA(Util.newArrayListOfValues("alexa"), "") {
		public String getDescription() {
			return Main.game.getAlexa().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAlexa();
		}
	},
	
	HARPY_BIMBO(Util.newArrayListOfValues(
			"brittany",
			"bimboHarpy",
			"harpyBimbo"), "") {
		public String getDescription() {
			return Main.game.getHarpyBimbo().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyBimbo();
		}
	},
	
	HARPY_BIMBO_COMPANION(Util.newArrayListOfValues(
			"lauren",
			"bimboHarpyCompanion",
			"harpyBimboCompanion"), "") {
		public String getDescription() {
			return Main.game.getHarpyBimboCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyBimboCompanion();
		}
	},
	
	HARPY_DOMINANT(Util.newArrayListOfValues(
			"diana",
			"dominantHarpy",
			"harpyDominant"), "") {
		public String getDescription() {
			return Main.game.getHarpyDominant().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyDominant();
		}
	},
	
	HARPY_DOMINANT_COMPANION(Util.newArrayListOfValues(
			"harley",
			"dominantHarpyCompanion",
			"harpyDominantCompanion"), "") {
		public String getDescription() {
			return Main.game.getHarpyDominantCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyDominantCompanion();
		}
	},
	
	HARPY_NYMPHO(Util.newArrayListOfValues(
			"lexi",
			"nymphoHarpy",
			"harpyNympho"), "") {
		public String getDescription() {
			return Main.game.getHarpyNympho().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyNympho();
		}
	},
	
	HARPY_NYMPHO_COMPANION(Util.newArrayListOfValues(
			"max",
			"nymphoHarpyCompanion",
			"harpyNymphoCompanion"), "") {
		public String getDescription() {
			return Main.game.getHarpyNymphoCompanion().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getHarpyNymphoCompanion();
		}
	},
	
	PAZU(Util.newArrayListOfValues("pazu"), "") {
		public String getDescription() {
			return Main.game.getPazu().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPazu();
		}
	},
	
	FINCH(Util.newArrayListOfValues("finch"), "") {
		public String getDescription() {
			return Main.game.getFinch().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getFinch();
		}
	},
	
	ZARANIX(Util.newArrayListOfValues("zaranix"), "") {
		public String getDescription() {
			return Main.game.getZaranix().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getZaranix();
		}
	},
	
	AMBER(Util.newArrayListOfValues("amber"), "") {
		public String getDescription() {
			return Main.game.getAmber().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAmber();
		}
	},
	
	ARTHUR(Util.newArrayListOfValues("arthur"), "") {
		public String getDescription() {
			return Main.game.getArthur().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getArthur();
		}
	},
	
	KELLY(Util.newArrayListOfValues("kelly"), "") {
		public String getDescription() {
			return Main.game.getKelly().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKelly();
		}
	},
	
	KATHERINE(Util.newArrayListOfValues("katherine"), "") {
		public String getDescription() {
			return Main.game.getKatherine().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getKatherine();
		}
	},
	
	ASHLEY(Util.newArrayListOfValues("ashley"), "") {
		public String getDescription() {
			return Main.game.getAshley().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAshley();
		}
	},
	
	WOLFGANG(Util.newArrayListOfValues(
			"wolfgang",
			"supplierLeader"), "") {
		public String getDescription() {
			return Main.game.getSupplierLeader().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getSupplierLeader();
		}
	},
	
	KARL(Util.newArrayListOfValues(
			"karl",
			"supplierPartner"), "") {
		public String getDescription() {
			return Main.game.getSupplierPartner().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getSupplierPartner();
		}
	},
	
	ANGEL(Util.newArrayListOfValues("angel"), "") {
		public String getDescription() {
			return Main.game.getAngel().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getAngel();
		}
	},
	
	BUNNY(Util.newArrayListOfValues("bunny"), "") {
		public String getDescription() {
			return Main.game.getBunny().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getBunny();
		}
	},
	
	LOPPY(Util.newArrayListOfValues("loppy"), "") {
		public String getDescription() {
			return Main.game.getLoppy().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getLoppy();
		}
	},
	
	LUMI(Util.newArrayListOfValues("lumi"), "") {
		public String getDescription() {
			return Main.game.getLumi().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getLumi();
		}
	},
	
	CLAIRE(Util.newArrayListOfValues("claire"), "") {
		public String getDescription() {
			return Main.game.getClaire().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getClaire();
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
