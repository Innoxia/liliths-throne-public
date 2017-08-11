package com.base.game.dialogue.utils;

import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.combat.Combat;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.69.9
 * @version 0.1.79
 * @author Innoxia
 */
public enum ParserTarget {
	
	STYLE(Util.newArrayListOfValues(
			new ListValue<>("style")),
			"Returns the same as 'pc', but should be used for style methods such as 'bold' or 'italics'.") {
				@Override
				public GameCharacter getCharacter() {
					return Main.game.getPlayer();
				}
			},
	
	PC(Util.newArrayListOfValues(
			new ListValue<>("pc"),
			new ListValue<>("player")),
			"The player character.") {
				@Override
				public GameCharacter getCharacter() {
					return Main.game.getPlayer();
				}
			},
	
	NPC(Util.newArrayListOfValues(
			new ListValue<>("npc")),
			"The currently 'active' NPC.</br>"
			+ "If in <b>combat</b>, it returns your opponent.</br>"
			+ "If in <b>sex</b>, it returns your partner.</br>"
			+ "<b>Otherwise</b>, it returns the most important NPC in the scene.") {
				@Override
				public GameCharacter getCharacter() {
					if(UtilText.getSpecialNPC()!=null) {
						return UtilText.getSpecialNPC();
						
					} else if(Main.game.isInCombat()) {
						return Combat.getOpponent();
						
					} else if (Main.game.isInSex()) {
						return Sex.getPartner();
						
					} else if (Main.game.getCurrentDialogueNode()!=null) {
						if(Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS) {
							return CharactersPresentDialogue.characterViewed;
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
	
	//NPCambiguous
	NPC_MALE(Util.newArrayListOfValues(
			new ListValue<>("NPCmale"),
			new ListValue<>("maleNPC")), ""){
		public String getDescription() {
			return Main.game.getGenericMaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
			return Main.game.getGenericAndrogynousNPC();
		}
	},
	
	LILAYA(Util.newArrayListOfValues(
			new ListValue<>("lilaya"),
			new ListValue<>("aunt")), ""){
		public String getDescription() {
			return Main.game.getLilaya().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getLilaya();
		}
	},
	
	ROSE(Util.newArrayListOfValues(
			new ListValue<>("rose")), ""){
		public String getDescription() {
			return Main.game.getRose().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getRose();
		}
	},
	
	KATE(Util.newArrayListOfValues(
			new ListValue<>("kate")), ""){
		public String getDescription() {
			return Main.game.getKate().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getKate();
		}
	},
	
	RALPH(Util.newArrayListOfValues(
			new ListValue<>("ralph")), ""){
		public String getDescription() {
			return Main.game.getRalph().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getRalph();
		}
	},
	
	NYAN(Util.newArrayListOfValues(
			new ListValue<>("nyan")), ""){
		public String getDescription() {
			return Main.game.getNyan().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getNyan();
		}
	},
	
	VICKY(Util.newArrayListOfValues(
			new ListValue<>("vicky")), ""){
		public String getDescription() {
			return Main.game.getVicky().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getVicky();
		}
	},
	
	PIX(Util.newArrayListOfValues(
			new ListValue<>("pix")), ""){
		public String getDescription() {
			return Main.game.getPix().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getPix();
		}
	},
	
	BRAX(Util.newArrayListOfValues(
			new ListValue<>("brax")), ""){
		public String getDescription() {
			return Main.game.getBrax().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getBrax();
		}
	},
	
	CANDI(Util.newArrayListOfValues(
			new ListValue<>("candi")), ""){
		public String getDescription() {
			return Main.game.getCandi().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getCandi();
		}
	},
	
	SCARLETT(Util.newArrayListOfValues(
			new ListValue<>("scarlett")), ""){
		public String getDescription() {
			return Main.game.getScarlett().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getScarlett();
		}
	},
	
	ALEXA(Util.newArrayListOfValues(
			new ListValue<>("alexa")), ""){
		public String getDescription() {
			return Main.game.getAlexa().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
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
		public GameCharacter getCharacter() {
			return Main.game.getHarpyNymphoCompanion();
		}
	},
	
	PAZU(Util.newArrayListOfValues(
			new ListValue<>("pazu")), ""){
		public String getDescription() {
			return Main.game.getPazu().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getPazu();
		}
	},
	
	NIKKIE(Util.newArrayListOfValues(
			new ListValue<>("nikki")), ""){
		public String getDescription() {
			return Main.game.getNikki().getDescription();
		}

		@Override
		public GameCharacter getCharacter() {
			return Main.game.getNikki();
		}
	},
	
//	ARTHUR("arthur", ""){
//		public String getDescription() {
//			return Main.game.getArthur().getDescription();
//		}
//	},
	
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
	
	public abstract GameCharacter getCharacter();
}
