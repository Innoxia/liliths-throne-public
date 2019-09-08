package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Alexa;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.SubmissionCitadelArcanist;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69.9
 * @version 0.2.11
 * @author Innoxia
 */
public enum ParserTarget {
	
	STYLE(Util.newArrayListOfValues(
			"style",
			"game"),
			"Returns the same as 'pc', but should be used for style methods such as style.bold or style.italics or conditional methods such as game.isArcaneStorm.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
					return Main.game.getPlayer();
				}
			},

	UNIT(Util.newArrayListOfValues(
			"unit",
			"game"),
			"Returns the same as 'pc', but should be used for unit methods such as unit.size.") {
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getPlayer();
		}
	},
	
	PC(Util.newArrayListOfValues(
			"pc",
			"player"),
			"The player character.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
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
			"The currently 'active' NPC.<br/>"
			+"<b>The tag 'npc' can be extended with a number, starting at 1, to signify which npc in the scene it is referring to!</b> e.g. 'npc1' is the first npc, 'npc2' is the second, etc.<br/>"
			+ "If in <b>combat</b>, it returns your opponent.<br/>"
			+ "If in <b>sex</b>, it returns your partner.<br/>"
			+ "<b>Otherwise</b>, it returns the most important NPC in the scene.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) throws NullPointerException {
					if(specialNPCList!=null && !specialNPCList.isEmpty()) {
						if(tag.equalsIgnoreCase("npc")) {
							return specialNPCList.get(0);
						} else {
							return specialNPCList.get(Math.max(0, Integer.parseInt(tag.substring(3))-1));
						}
						
					} else if(Main.game.isInCombat()) {
						return Combat.getActiveNPC();
						
					} else if (Main.game.isInSex()) {
						return Sex.getTargetedPartner(Main.game.getPlayer());
						
					} else if (Main.game.getCurrentDialogueNode()!=null) {
						if(Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS_CHARACTER) {
							return CharactersPresentDialogue.characterViewed;
							
						} else if(Main.game.getActiveNPC()!=null) {
							return Main.game.getActiveNPC();
							
						} else if (!Main.game.getCharactersPresent().isEmpty()) {
							List<NPC> charactersPresent = Main.game.getCharactersPresent();
							if(tag.equalsIgnoreCase("npc")) {
								return charactersPresent.get(0);
							} else {
								return charactersPresent.get(Math.min(charactersPresent.size()-1, Math.max(0, Integer.parseInt(tag.substring(3))-1)));
							}
							
						} else {
							throw new NullPointerException();
						}
						
					} else {
						throw new NullPointerException();
					}
				}
			},
	
	COMPANION(Util.newArrayListOfValues(
			"companion",
			"companion1",
			"companion2",
			"companion3",
			"companion4",
			"companion5",
			"companion6"),
			"The companions of the player.<br/>"
			+"<b>The tag 'companion' can be extended with a number, starting at 1, to signify which companion it is referring to!</b> e.g. 'companion1' is the first companion, 'companion2' is the second, etc.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) throws NullPointerException {
					if(Main.game.getPlayer().getCompanions().size()>=1) {
						if(tag.equalsIgnoreCase("companion")) {
							return Main.game.getPlayer().getCompanions().get(0);
						} else {
							int index = Integer.parseInt(tag.substring(9));
							if(Main.game.getPlayer().getCompanions().size()>=index) {
								return Main.game.getPlayer().getCompanions().get(Math.max(0, index-1));
							}
						}
					}
					throw new NullPointerException();
				}
			},
	
	PROLOGUE_MALE(Util.newArrayListOfValues("prologueMale"), "") {
		public String getDescription() {
			return Main.game.getNpc(PrologueMale.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(PrologueMale.class);
		}
	},
	
	PROLOGUE_FEMALE(Util.newArrayListOfValues("prologueFemale"), "") {
		public String getDescription() {
			return Main.game.getNpc(PrologueFemale.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(PrologueFemale.class);
		}
	},
	
	NPC_MALE(Util.newArrayListOfValues(
			"NPCmale",
			"maleNPC",
			"genericMale",
			"maleGeneric"), "") {
		public String getDescription() {
			return Main.game.getNpc(GenericMaleNPC.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(GenericMaleNPC.class);
		}
	},
	
	NPC_FEMALE(Util.newArrayListOfValues(
			"NPCfemale",
			"femaleNPC",
			"genericFemale",
			"femaleGeneric"), "") {
		public String getDescription() {
			return Main.game.getNpc(GenericFemaleNPC.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(GenericFemaleNPC.class);
		}
	},
	
	NPC_ANDROGYNOUS(Util.newArrayListOfValues(
			"NPCandrogynous",
			"androgynousNPC",
			"NPCambiguous",
			"ambiguousNPC"), "") {
		public String getDescription() {
			return Main.game.getNpc(GenericAndrogynousNPC.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(GenericAndrogynousNPC.class);
		}
	},
	
	TEST_NPC(Util.newArrayListOfValues(
			"testNPC",
			"test"), "") {
		public String getDescription() {
			return Main.game.getNpc(TestNPC.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(TestNPC.class);
		}
	},
	
	LILAYA(Util.newArrayListOfValues(
			"lilaya",
			"aunt"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lilaya.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lilaya.class);
		}
	},
	
	ROSE(Util.newArrayListOfValues("rose"), "") {
		public String getDescription() {
			return Main.game.getNpc(Rose.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Rose.class);
		}
	},
	
	KATE(Util.newArrayListOfValues("kate"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kate.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kate.class);
		}
	},
	
	RALPH(Util.newArrayListOfValues("ralph"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ralph.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ralph.class);
		}
	},
	
	NYAN(Util.newArrayListOfValues("nyan"), "") {
		public String getDescription() {
			return Main.game.getNpc(Nyan.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Nyan.class);
		}
	},
	
	VICKY(Util.newArrayListOfValues("vicky"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vicky.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vicky.class);
		}
	},
	
	PIX(Util.newArrayListOfValues("pix"), "") {
		public String getDescription() {
			return Main.game.getNpc(Pix.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Pix.class);
		}
	},
	
	BRAX(Util.newArrayListOfValues("brax"), "") {
		public String getDescription() {
			return Main.game.getNpc(Brax.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Brax.class);
		}
	},
	
	CANDI(Util.newArrayListOfValues("candi"), "") {
		public String getDescription() {
			return Main.game.getNpc(CandiReceptionist.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(CandiReceptionist.class);
		}
	},
	
	VANESSA(Util.newArrayListOfValues("vanessa"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vanessa.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vanessa.class);
		}
	},
	
	SCARLETT(Util.newArrayListOfValues("scarlett"), "") {
		public String getDescription() {
			return Main.game.getNpc(Scarlett.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Scarlett.class);
		}
	},
	
	ALEXA(Util.newArrayListOfValues("alexa"), "") {
		public String getDescription() {
			return Main.game.getNpc(Alexa.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Alexa.class);
		}
	},
	
	HARPY_BIMBO(Util.newArrayListOfValues(
			"brittany",
			"bimboHarpy",
			"harpyBimbo"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyBimbo.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyBimbo.class);
		}
	},
	
	HARPY_BIMBO_COMPANION(Util.newArrayListOfValues(
			"lauren",
			"bimboHarpyCompanion",
			"harpyBimboCompanion"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyBimboCompanion.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyBimboCompanion.class);
		}
	},
	
	HARPY_DOMINANT(Util.newArrayListOfValues(
			"diana",
			"dominantHarpy",
			"harpyDominant"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyDominant.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyDominant.class);
		}
	},
	
	HARPY_DOMINANT_COMPANION(Util.newArrayListOfValues(
			"harley",
			"dominantHarpyCompanion",
			"harpyDominantCompanion"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyDominantCompanion.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyDominantCompanion.class);
		}
	},
	
	HARPY_NYMPHO(Util.newArrayListOfValues(
			"lexi",
			"nymphoHarpy",
			"harpyNympho"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyNympho.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyNympho.class);
		}
	},
	
	HARPY_NYMPHO_COMPANION(Util.newArrayListOfValues(
			"max",
			"nymphoHarpyCompanion",
			"harpyNymphoCompanion"), "") {
		public String getDescription() {
			return Main.game.getNpc(HarpyNymphoCompanion.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HarpyNymphoCompanion.class);
		}
	},
	
	PAZU(Util.newArrayListOfValues("pazu"), "") {
		public String getDescription() {
			return Main.game.getNpc(Pazu.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Pazu.class);
		}
	},
	
	FINCH(Util.newArrayListOfValues("finch"), "") {
		public String getDescription() {
			return Main.game.getNpc(Finch.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Finch.class);
		}
	},
	
	ZARANIX(Util.newArrayListOfValues("zaranix"), "") {
		public String getDescription() {
			return Main.game.getNpc(Zaranix.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Zaranix.class);
		}
	},
	
	AMBER(Util.newArrayListOfValues("amber"), "") {
		public String getDescription() {
			return Main.game.getNpc(Amber.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Amber.class);
		}
	},
	
	ARTHUR(Util.newArrayListOfValues("arthur"), "") {
		public String getDescription() {
			return Main.game.getNpc(Arthur.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Arthur.class);
		}
	},
	
	KELLY(Util.newArrayListOfValues("kelly"), "") {
		public String getDescription() {
			return Main.game.getNpc(ZaranixMaidKelly.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(ZaranixMaidKelly.class);
		}
	},
	
	KATHERINE(Util.newArrayListOfValues("katherine"), "") {
		public String getDescription() {
			return Main.game.getNpc(ZaranixMaidKatherine.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(ZaranixMaidKatherine.class);
		}
	},
	
	ASHLEY(Util.newArrayListOfValues("ashley"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ashley.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ashley.class);
		}
	},
	
	WOLFGANG(Util.newArrayListOfValues(
			"wolfgang",
			"supplierLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(SupplierLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SupplierLeader.class);
		}
	},
	
	KARL(Util.newArrayListOfValues(
			"karl",
			"supplierPartner"), "") {
		public String getDescription() {
			return Main.game.getNpc(SupplierPartner.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SupplierPartner.class);
		}
	},
	
	ANGEL(Util.newArrayListOfValues("angel"), "") {
		public String getDescription() {
			return Main.game.getNpc(Angel.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Angel.class);
		}
	},
	
	BUNNY(Util.newArrayListOfValues("bunny"), "") {
		public String getDescription() {
			return Main.game.getNpc(Bunny.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Bunny.class);
		}
	},
	
	LOPPY(Util.newArrayListOfValues("loppy"), "") {
		public String getDescription() {
			return Main.game.getNpc(Loppy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Loppy.class);
		}
	},
	
	LUMI(Util.newArrayListOfValues("lumi"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lumi.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lumi.class);
		}
	},
	
	CLAIRE(Util.newArrayListOfValues("claire"), "") {
		public String getDescription() {
			return Main.game.getNpc(Claire.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Claire.class);
		}
	},
	
	SLIME_QUEEN(Util.newArrayListOfValues("slimeQueen"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeQueen.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeQueen.class);
		}
	},
	
	SLIME_GUARD_ICE(Util.newArrayListOfValues("slimeGuardIce", "slimeIce", "crystal"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeGuardIce.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeGuardIce.class);
		}
	},
	
	SLIME_GUARD_FIRE(Util.newArrayListOfValues("slimeGuardFire", "slimeFire", "blaze"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeGuardFire.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeGuardFire.class);
		}
	},
	
	SLIME_ROYAL_GUARD(Util.newArrayListOfValues("slimeRoyalGuard", "royalGuardSlime"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeRoyalGuard.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeRoyalGuard.class);
		}
	},
	
	ROXY(Util.newArrayListOfValues("roxy"), "") {
		public String getDescription() {
			return Main.game.getNpc(Roxy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Roxy.class);
		}
	},
	
	AXEL(Util.newArrayListOfValues("axel"), "") {
		public String getDescription() {
			return Main.game.getNpc(Axel.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Axel.class);
		}
	},
	
	EPONA(Util.newArrayListOfValues("epona"), "") {
		public String getDescription() {
			return Main.game.getNpc(Epona.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Epona.class);
		}
	},
	
	JULES(Util.newArrayListOfValues("jules"), "") {
		public String getDescription() {
			return Main.game.getNpc(Jules.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Jules.class);
		}
	},
	
	KRUGER(Util.newArrayListOfValues("kruger"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kruger.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kruger.class);
		}
	},
	
	KALAHARI(Util.newArrayListOfValues("kalahari"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kalahari.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kalahari.class);
		}
	},

	RENTAL_MOMMY(Util.newArrayListOfValues("rentalMommy"), "") {
		public String getDescription() {
			return Main.game.getNpc(RentalMommy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(RentalMommy.class);
		}
	},
	
	DADDY(Util.newArrayListOfValues("daddy", "desryth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Daddy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Daddy.class);
		}
	},
	
	
	// Submission:
	
	IMP_FORTRESS_ALPHA_LEADER(Util.newArrayListOfValues("impAlphaLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressAlphaLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressAlphaLeader.class);
		}
	},
	
	IMP_FORTRESS_FEMALES_LEADER(Util.newArrayListOfValues("impFemalesLeader", "impFemaleLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressFemalesLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressFemalesLeader.class);
		}
	},
	
	IMP_FORTRESS_MALES_LEADER(Util.newArrayListOfValues("impMalesLeader", "impMaleLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressMalesLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressMalesLeader.class);
		}
	},
	
	DARK_SIREN(Util.newArrayListOfValues("darkSiren", "siren", "meraxis"), "") {
		public String getDescription() {
			return Main.game.getNpc(DarkSiren.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(DarkSiren.class);
		}
	},
	
	CITADEL_ARCANIST(Util.newArrayListOfValues("citadelArcanist", "youkoGuide", "hitomi", "takahashi"), "") {
		public String getDescription() {
			return Main.game.getNpc(SubmissionCitadelArcanist.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SubmissionCitadelArcanist.class);
		}
	},
	
	LYSSIETH(Util.newArrayListOfValues("lyssieth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lyssieth.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lyssieth.class);
		}
	},
	
	ELIZABETH(Util.newArrayListOfValues("elizabeth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Elizabeth.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Elizabeth.class);
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
	
	public abstract GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) throws NullPointerException;
}
