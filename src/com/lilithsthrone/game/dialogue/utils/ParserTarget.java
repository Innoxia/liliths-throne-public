package com.lilithsthrone.game.dialogue.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.fields.Arion;
import com.lilithsthrone.game.character.npc.fields.Astrapi;
import com.lilithsthrone.game.character.npc.fields.Belle;
import com.lilithsthrone.game.character.npc.fields.Ceridwen;
import com.lilithsthrone.game.character.npc.fields.Dale;
import com.lilithsthrone.game.character.npc.fields.Daphne;
import com.lilithsthrone.game.character.npc.fields.Evelyx;
import com.lilithsthrone.game.character.npc.fields.Fae;
import com.lilithsthrone.game.character.npc.fields.Farah;
import com.lilithsthrone.game.character.npc.fields.Flash;
import com.lilithsthrone.game.character.npc.fields.Hale;
import com.lilithsthrone.game.character.npc.fields.HeadlessHorseman;
import com.lilithsthrone.game.character.npc.fields.Heather;
import com.lilithsthrone.game.character.npc.fields.Imsu;
import com.lilithsthrone.game.character.npc.fields.Jess;
import com.lilithsthrone.game.character.npc.fields.Kazik;
import com.lilithsthrone.game.character.npc.fields.Kheiron;
import com.lilithsthrone.game.character.npc.fields.Minotallys;
import com.lilithsthrone.game.character.npc.fields.Monica;
import com.lilithsthrone.game.character.npc.fields.Moreno;
import com.lilithsthrone.game.character.npc.fields.Nizhoni;
import com.lilithsthrone.game.character.npc.fields.Penelope;
import com.lilithsthrone.game.character.npc.fields.Silvia;
import com.lilithsthrone.game.character.npc.fields.Vronti;
import com.lilithsthrone.game.character.npc.fields.Yui;
import com.lilithsthrone.game.character.npc.fields.Ziva;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericTrader;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69.9
 * @version 0.4.2
 * @author Innoxia
 */
public class ParserTarget {
	
	public static AbstractParserTarget STYLE = new AbstractParserTarget(Util.newArrayListOfValues(
			"style",
			"game",
			"util"),
			"Returns the same as 'pc', but should be used for style methods such as style.bold or style.italics or conditional methods such as game.isArcaneStorm.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
					return Main.game.getPlayer();
				}
			};

	public static AbstractParserTarget UNIT = new AbstractParserTarget(Util.newArrayListOfValues(
			"unit",
			"units",
			"game"),
			"Returns the same as 'pc', but should be used for unit methods such as unit.size.") {
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getPlayer();
		}
	};
	
	public static AbstractParserTarget PC = new AbstractParserTarget(Util.newArrayListOfValues(
			"pc",
			"player"),
			"The player character.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
					return Main.game.getPlayer();
				}
			};
	
	/**
	 * The main parser tag for getting a hook on npcs when using UtilText's {@code parseFromXMLFile()} methods.
	 * <br/><b>Important note:</b> When trying to access npcs for parsing in external files, this method is likely to be unreliable and return npcs which you did not want.
	 * You should instead use the 'ncom' (standing for Non-COMpanion) parser tags to access npcs which are not members of the player's party, and 'com' (standing for COMpanion) tags for npcs which are members of the player's party.
	 * These parser tags will always return characters in the same order, so they are far safer to use than this 'npc' tag, which should only be used in the context of UtilText's {@code parseFromXMLFile()} method.
	 */
	public static AbstractParserTarget NPC = new AbstractParserTarget(Util.newArrayListOfValues(
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
						return Main.combat.getActiveNPC();
						
					} else if (Main.game.isInSex()) {
						return Main.sex.getTargetedPartner(Main.game.getPlayer());
						
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
			};
	
	/**
	 * Returns npcs which are members of the player's party.
	 * Ordering is based on the order in which companions were added to the party.
	 */
	public static AbstractParserTarget COMPANION = new AbstractParserTarget(Util.newArrayListOfValues(
			"com",
			"com1",
			"com2",
			"com3",
			"com4",
			"com5",
			"com6"),
			"The companions of the player.<br/>"
			+"<b>The tag 'com' can be extended with a number, starting at 1, to signify which companion it is referring to!</b> e.g. 'com1' is the first companion, 'com2' is the second, etc.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) throws NullPointerException {
					if(Main.game.getPlayer().getCompanions().size()>=1) {
						if(tag.equalsIgnoreCase("com")) {
							return Main.game.getPlayer().getCompanions().get(0);
							
						} else {
							int index = Integer.parseInt(tag.substring(tag.length()-1));
							if(Main.game.getPlayer().getCompanions().size()>=index) {
								return Main.game.getPlayer().getCompanions().get(Math.max(0, index-1));
							}
						}
					}
					throw new NullPointerException();
				}
			};

	/**
	 * Returns npcs which are not members of the player's party and which are present in the player's cell.
	 * Ordering is based on the npcs' id, so ordering will remain consistent across multiple parsing calls.
	 */
	public static AbstractParserTarget NON_COMPANION = new AbstractParserTarget(Util.newArrayListOfValues(
			"ncom",
			"ncom1",
			"ncom2",
			"ncom3",
			"ncom4",
			"ncom5",
			"ncom6"),
			"The non-unique npcs who are in the player's cell and who are not members of the player's party.<br/>"
			+"<b>The tag 'ncom' can be extended with a number, starting at 1, to signify which npc it is referring to!</b> e.g. 'ncom1' is the first npc, 'ncom2' is the second, etc.") {
				@Override
				public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) throws NullPointerException {
					if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
						List<NPC> npcs = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
						npcs.removeIf(npc->npc.isUnique());
						Collections.sort(npcs, (n1, n2)->n1.getId().compareTo(n2.getId()));
						if(tag.equalsIgnoreCase("ncom")) {
							return npcs.get(0);
							
						} else {
							int index = Integer.parseInt(tag.substring(tag.length()-1));
							if(npcs.size()>=index) {
								return npcs.get(Math.max(0, index-1));
							}
						}
					}
					throw new NullPointerException();
				}
			};

	public static AbstractParserTarget ELEMENTAL = new AbstractParserTarget(Util.newArrayListOfValues(
			"el",
			"elemental"),
			"The player's elemental. <b>Should only ever be used when you know for certain that the player's elemental has been created!</b>") {
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			if(!Main.game.getPlayer().hasDiscoveredElemental()) {
//				System.err.println("Warning: Player's elemental not found when accessing ParserTarget.ELEMENTAL!");
				return Main.game.getNpc(GenericAndrogynousNPC.class);
			}
			return Main.game.getPlayer().getElemental();
		}
	};
	
	public static AbstractParserTarget PROLOGUE_MALE = new AbstractParserTarget(Util.newArrayListOfValues("prologueMale"), "") {
		public String getDescription() {
			return Main.game.getNpc(PrologueMale.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(PrologueMale.class);
		}
	};
	
	public static AbstractParserTarget PROLOGUE_FEMALE = new AbstractParserTarget(Util.newArrayListOfValues("prologueFemale"), "") {
		public String getDescription() {
			return Main.game.getNpc(PrologueFemale.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(PrologueFemale.class);
		}
	};
	
	public static AbstractParserTarget NPC_MALE = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget NPC_FEMALE = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget NPC_ANDROGYNOUS = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget NPC_TRADER = new AbstractParserTarget(Util.newArrayListOfValues(
			"trader",
			"genericTrader"), "") {
		public String getDescription() {
			return Main.game.getNpc(GenericTrader.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(GenericTrader.class);
		}
	};
	
	public static AbstractParserTarget TEST_NPC = new AbstractParserTarget(Util.newArrayListOfValues(
			"testNPC",
			"test"), "") {
		public String getDescription() {
			return Main.game.getNpc(TestNPC.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(TestNPC.class);
		}
	};
	
	public static AbstractParserTarget LILAYA = new AbstractParserTarget(Util.newArrayListOfValues(
			"lilaya",
			"aunt"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lilaya.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lilaya.class);
		}
	};
	
	public static AbstractParserTarget ROSE = new AbstractParserTarget(Util.newArrayListOfValues("rose"), "") {
		public String getDescription() {
			return Main.game.getNpc(Rose.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Rose.class);
		}
	};
	
	public static AbstractParserTarget KATE = new AbstractParserTarget(Util.newArrayListOfValues("kate"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kate.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kate.class);
		}
	};
	
	public static AbstractParserTarget RALPH = new AbstractParserTarget(Util.newArrayListOfValues("ralph"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ralph.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ralph.class);
		}
	};
	
	public static AbstractParserTarget NYAN = new AbstractParserTarget(Util.newArrayListOfValues("nyan"), "") {
		public String getDescription() {
			return Main.game.getNpc(Nyan.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Nyan.class);
		}
	};
	
	public static AbstractParserTarget NYAN_MUM = new AbstractParserTarget(Util.newArrayListOfValues("nyanmum", "nyanmom", "leotie"), "") {
		public String getDescription() {
			return Main.game.getNpc(NyanMum.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(NyanMum.class);
		}
	};
	
	public static AbstractParserTarget VICKY = new AbstractParserTarget(Util.newArrayListOfValues("vicky"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vicky.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vicky.class);
		}
	};
	
	public static AbstractParserTarget PIX = new AbstractParserTarget(Util.newArrayListOfValues("pix"), "") {
		public String getDescription() {
			return Main.game.getNpc(Pix.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Pix.class);
		}
	};
	
	public static AbstractParserTarget BRAX = new AbstractParserTarget(Util.newArrayListOfValues("brax"), "") {
		public String getDescription() {
			return Main.game.getNpc(Brax.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Brax.class);
		}
	};
	
	public static AbstractParserTarget CANDI = new AbstractParserTarget(Util.newArrayListOfValues("candi"), "") {
		public String getDescription() {
			return Main.game.getNpc(CandiReceptionist.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(CandiReceptionist.class);
		}
	};
	
	public static AbstractParserTarget VANESSA = new AbstractParserTarget(Util.newArrayListOfValues("vanessa"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vanessa.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vanessa.class);
		}
	};
	
	public static AbstractParserTarget SCARLETT = new AbstractParserTarget(Util.newArrayListOfValues("scarlett"), "") {
		public String getDescription() {
			return Main.game.getNpc(Scarlett.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Scarlett.class);
		}
	};
	
	public static AbstractParserTarget HELENA = new AbstractParserTarget(Util.newArrayListOfValues("helena"), "") {
		public String getDescription() {
			return Main.game.getNpc(Helena.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Helena.class);
		}
	};
	
	public static AbstractParserTarget HARPY_BIMBO = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget HARPY_BIMBO_COMPANION = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget HARPY_DOMINANT = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget HARPY_DOMINANT_COMPANION = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget HARPY_NYMPHO = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget HARPY_NYMPHO_COMPANION = new AbstractParserTarget(Util.newArrayListOfValues(
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
	};
	
	public static AbstractParserTarget PAZU = new AbstractParserTarget(Util.newArrayListOfValues("pazu"), "") {
		public String getDescription() {
			return Main.game.getNpc(Pazu.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Pazu.class);
		}
	};
	
	public static AbstractParserTarget FINCH = new AbstractParserTarget(Util.newArrayListOfValues("finch"), "") {
		public String getDescription() {
			return Main.game.getNpc(Finch.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Finch.class);
		}
	};
	
	public static AbstractParserTarget ZARANIX = new AbstractParserTarget(Util.newArrayListOfValues("zaranix"), "") {
		public String getDescription() {
			return Main.game.getNpc(Zaranix.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Zaranix.class);
		}
	};
	
	public static AbstractParserTarget AMBER = new AbstractParserTarget(Util.newArrayListOfValues("amber"), "") {
		public String getDescription() {
			return Main.game.getNpc(Amber.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Amber.class);
		}
	};
	
	public static AbstractParserTarget FELICIA = new AbstractParserTarget(Util.newArrayListOfValues("felicia"), "") {
		public String getDescription() {
			return Main.game.getNpc(Felicia.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Felicia.class);
		}
	};
	
	public static AbstractParserTarget ARTHUR = new AbstractParserTarget(Util.newArrayListOfValues("arthur"), "") {
		public String getDescription() {
			return Main.game.getNpc(Arthur.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Arthur.class);
		}
	};
	
	public static AbstractParserTarget KELLY = new AbstractParserTarget(Util.newArrayListOfValues("kelly"), "") {
		public String getDescription() {
			return Main.game.getNpc(ZaranixMaidKelly.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(ZaranixMaidKelly.class);
		}
	};
	
	public static AbstractParserTarget KATHERINE = new AbstractParserTarget(Util.newArrayListOfValues("katherine"), "") {
		public String getDescription() {
			return Main.game.getNpc(ZaranixMaidKatherine.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(ZaranixMaidKatherine.class);
		}
	};
	
	public static AbstractParserTarget ASHLEY = new AbstractParserTarget(Util.newArrayListOfValues("ashley"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ashley.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ashley.class);
		}
	};
	
	public static AbstractParserTarget WOLFGANG = new AbstractParserTarget(Util.newArrayListOfValues(
			"wolfgang",
			"supplierLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(SupplierLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SupplierLeader.class);
		}
	};
	
	public static AbstractParserTarget KARL = new AbstractParserTarget(Util.newArrayListOfValues(
			"karl",
			"supplierPartner"), "") {
		public String getDescription() {
			return Main.game.getNpc(SupplierPartner.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SupplierPartner.class);
		}
	};
	
	public static AbstractParserTarget ANGEL = new AbstractParserTarget(Util.newArrayListOfValues("angel"), "") {
		public String getDescription() {
			return Main.game.getNpc(Angel.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Angel.class);
		}
	};
	
	public static AbstractParserTarget BUNNY = new AbstractParserTarget(Util.newArrayListOfValues("bunny"), "") {
		public String getDescription() {
			return Main.game.getNpc(Bunny.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Bunny.class);
		}
	};
	
	public static AbstractParserTarget LOPPY = new AbstractParserTarget(Util.newArrayListOfValues("loppy"), "") {
		public String getDescription() {
			return Main.game.getNpc(Loppy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Loppy.class);
		}
	};
	
	public static AbstractParserTarget LUMI = new AbstractParserTarget(Util.newArrayListOfValues("lumi"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lumi.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lumi.class);
		}
	};
	
	public static AbstractParserTarget CLAIRE = new AbstractParserTarget(Util.newArrayListOfValues("claire"), "") {
		public String getDescription() {
			return Main.game.getNpc(Claire.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Claire.class);
		}
	};
	
	public static AbstractParserTarget SLIME_QUEEN = new AbstractParserTarget(Util.newArrayListOfValues("slimeQueen"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeQueen.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeQueen.class);
		}
	};
	
	public static AbstractParserTarget SLIME_GUARD_ICE = new AbstractParserTarget(Util.newArrayListOfValues("slimeGuardIce", "slimeIce", "crystal"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeGuardIce.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeGuardIce.class);
		}
	};
	
	public static AbstractParserTarget SLIME_GUARD_FIRE = new AbstractParserTarget(Util.newArrayListOfValues("slimeGuardFire", "slimeFire", "blaze"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeGuardFire.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeGuardFire.class);
		}
	};
	
	public static AbstractParserTarget SLIME_ROYAL_GUARD = new AbstractParserTarget(Util.newArrayListOfValues("slimeRoyalGuard", "royalGuardSlime"), "") {
		public String getDescription() {
			return Main.game.getNpc(SlimeRoyalGuard.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(SlimeRoyalGuard.class);
		}
	};
	
	public static AbstractParserTarget ROXY = new AbstractParserTarget(Util.newArrayListOfValues("roxy"), "") {
		public String getDescription() {
			return Main.game.getNpc(Roxy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Roxy.class);
		}
	};
	
	public static AbstractParserTarget AXEL = new AbstractParserTarget(Util.newArrayListOfValues("axel", "lexa"), "") {
		public String getDescription() {
			return Main.game.getNpc(Axel.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Axel.class);
		}
	};
	
	public static AbstractParserTarget EPONA = new AbstractParserTarget(Util.newArrayListOfValues("epona"), "") {
		public String getDescription() {
			return Main.game.getNpc(Epona.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Epona.class);
		}
	};
	
	public static AbstractParserTarget JULES = new AbstractParserTarget(Util.newArrayListOfValues("jules"), "") {
		public String getDescription() {
			return Main.game.getNpc(Jules.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Jules.class);
		}
	};
	
	public static AbstractParserTarget KRUGER = new AbstractParserTarget(Util.newArrayListOfValues("kruger"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kruger.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kruger.class);
		}
	};
	
	public static AbstractParserTarget KALAHARI = new AbstractParserTarget(Util.newArrayListOfValues("kalahari"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kalahari.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kalahari.class);
		}
	};

	public static AbstractParserTarget RENTAL_MOMMY = new AbstractParserTarget(Util.newArrayListOfValues("rentalMommy"), "") {
		public String getDescription() {
			return Main.game.getNpc(RentalMommy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(RentalMommy.class);
		}
	};
	
	public static AbstractParserTarget DADDY = new AbstractParserTarget(Util.newArrayListOfValues("daddy", "desryth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Daddy.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Daddy.class);
		}
	};
	
	
	// Submission:
	
	public static AbstractParserTarget IMP_FORTRESS_ALPHA_LEADER = new AbstractParserTarget(Util.newArrayListOfValues("impAlphaLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressAlphaLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressAlphaLeader.class);
		}
	};
	
	public static AbstractParserTarget IMP_FORTRESS_FEMALES_LEADER = new AbstractParserTarget(Util.newArrayListOfValues("impFemalesLeader", "impFemaleLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressFemalesLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressFemalesLeader.class);
		}
	};
	
	public static AbstractParserTarget IMP_FORTRESS_MALES_LEADER = new AbstractParserTarget(Util.newArrayListOfValues("impMalesLeader", "impMaleLeader"), "") {
		public String getDescription() {
			return Main.game.getNpc(FortressMalesLeader.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(FortressMalesLeader.class);
		}
	};
	
	public static AbstractParserTarget DARK_SIREN = new AbstractParserTarget(Util.newArrayListOfValues("darkSiren", "siren", "meraxis"), "") {
		public String getDescription() {
			return Main.game.getNpc(DarkSiren.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(DarkSiren.class);
		}
	};
	
	public static AbstractParserTarget CITADEL_ARCANIST = new AbstractParserTarget(Util.newArrayListOfValues("citadelArcanist", "youkoGuide", "hitomi", "takahashi"), "") {
		public String getDescription() {
			return Main.game.getNpc(Takahashi.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Takahashi.class);
		}
	};
	
	public static AbstractParserTarget LYSSIETH = new AbstractParserTarget(Util.newArrayListOfValues("lyssieth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Lyssieth.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Lyssieth.class);
		}
	};
	
	public static AbstractParserTarget ELIZABETH = new AbstractParserTarget(Util.newArrayListOfValues("elizabeth"), "") {
		public String getDescription() {
			return Main.game.getNpc(Elizabeth.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Elizabeth.class);
		}
	};
	
	public static AbstractParserTarget VENGAR = new AbstractParserTarget(Util.newArrayListOfValues("vengar"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vengar.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vengar.class);
		}
	};
	
	public static AbstractParserTarget SHADOW = new AbstractParserTarget(Util.newArrayListOfValues("shadow"), "") {
		public String getDescription() {
			return Main.game.getNpc(Shadow.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Shadow.class);
		}
	};
	
	public static AbstractParserTarget SILENCE = new AbstractParserTarget(Util.newArrayListOfValues("silence"), "") {
		public String getDescription() {
			return Main.game.getNpc(Silence.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Silence.class);
		}
	};
	
	public static AbstractParserTarget MURK = new AbstractParserTarget(Util.newArrayListOfValues("murk"), "") {
		public String getDescription() {
			return Main.game.getNpc(Murk.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Murk.class);
		}
	};
	
	public static AbstractParserTarget SEAN = new AbstractParserTarget(Util.newArrayListOfValues("sean"), "") {
		public String getDescription() {
			return Main.game.getNpc(Sean.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Sean.class);
		}
	};
	
	public static AbstractParserTarget NATALYA = new AbstractParserTarget(Util.newArrayListOfValues("natalya"), "") {
		public String getDescription() {
			return Main.game.getNpc(Natalya.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Natalya.class);
		}
	};
	
	public static AbstractParserTarget WES = new AbstractParserTarget(Util.newArrayListOfValues("wes", "wesley"), "") {
		public String getDescription() {
			return Main.game.getNpc(Wes.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Wes.class);
		}
	};
	
	public static AbstractParserTarget ELLE = new AbstractParserTarget(Util.newArrayListOfValues("elle", "aellasys"), "") {
		public String getDescription() {
			return Main.game.getNpc(Elle.class).getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Elle.class);
		}
	};
	
	public static AbstractParserTarget KAY = new AbstractParserTarget(Util.newArrayListOfValues("kay"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kay.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kay.class);
		}
	};
	
	public static AbstractParserTarget FLASH = new AbstractParserTarget(Util.newArrayListOfValues("flash"), "") {
		public String getDescription() {
			return Main.game.getNpc(Flash.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Flash.class);
		}
	};
	
	public static AbstractParserTarget JESS = new AbstractParserTarget(Util.newArrayListOfValues("jess"), "") {
		public String getDescription() {
			return Main.game.getNpc(Jess.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Jess.class);
		}
	};
	
	public static AbstractParserTarget ASTRAPI = new AbstractParserTarget(Util.newArrayListOfValues("astrapi"), "") {
		public String getDescription() {
			return Main.game.getNpc(Astrapi.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Astrapi.class);
		}
	};
	
	public static AbstractParserTarget VRONTI = new AbstractParserTarget(Util.newArrayListOfValues("vronti"), "") {
		public String getDescription() {
			return Main.game.getNpc(Vronti.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Vronti.class);
		}
	};
	
	public static AbstractParserTarget ARION = new AbstractParserTarget(Util.newArrayListOfValues("arion"), "") {
		public String getDescription() {
			return Main.game.getNpc(Arion.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Arion.class);
		}
	};
	
	public static AbstractParserTarget MINOTALLYS = new AbstractParserTarget(Util.newArrayListOfValues("minotallys"), "") {
		public String getDescription() {
			return Main.game.getNpc(Minotallys.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Minotallys.class);
		}
	};
	
	public static AbstractParserTarget FAE = new AbstractParserTarget(Util.newArrayListOfValues("fae"), "") {
		public String getDescription() {
			return Main.game.getNpc(Fae.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Fae.class);
		}
	};
	
	public static AbstractParserTarget SILVIA = new AbstractParserTarget(Util.newArrayListOfValues("silvia"), "") {
		public String getDescription() {
			return Main.game.getNpc(Silvia.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Silvia.class);
		}
	};
	
	public static AbstractParserTarget KHEIRON = new AbstractParserTarget(Util.newArrayListOfValues("kheiron"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kheiron.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kheiron.class);
		}
	};
	
	public static AbstractParserTarget KAZIK = new AbstractParserTarget(Util.newArrayListOfValues("kazik"), "") {
		public String getDescription() {
			return Main.game.getNpc(Kazik.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Kazik.class);
		}
	};
	
	public static AbstractParserTarget YUI = new AbstractParserTarget(Util.newArrayListOfValues("yui"), "") {
		public String getDescription() {
			return Main.game.getNpc(Yui.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Yui.class);
		}
	};
	
	public static AbstractParserTarget NIZHONI = new AbstractParserTarget(Util.newArrayListOfValues("nizhoni"), "") {
		public String getDescription() {
			return Main.game.getNpc(Nizhoni.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Nizhoni.class);
		}
	};
	
	public static AbstractParserTarget MORENO = new AbstractParserTarget(Util.newArrayListOfValues("moreno"), "") {
		public String getDescription() {
			return Main.game.getNpc(Moreno.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Moreno.class);
		}
	};
	
	public static AbstractParserTarget HEATHER = new AbstractParserTarget(Util.newArrayListOfValues("heather"), "") {
		public String getDescription() {
			return Main.game.getNpc(Heather.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Heather.class);
		}
	};
	
	public static AbstractParserTarget ZIVA = new AbstractParserTarget(Util.newArrayListOfValues("ziva"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ziva.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ziva.class);
		}
	};
	
	public static AbstractParserTarget MONICA = new AbstractParserTarget(Util.newArrayListOfValues("monica"), "") {
		public String getDescription() {
			return Main.game.getNpc(Monica.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Monica.class);
		}
	};

	public static AbstractParserTarget EVELYX = new AbstractParserTarget(Util.newArrayListOfValues("evelyx"), "") {
		public String getDescription() {
			return Main.game.getNpc(Evelyx.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Evelyx.class);
		}
	};

	public static AbstractParserTarget DALE = new AbstractParserTarget(Util.newArrayListOfValues("dale"), "") {
		public String getDescription() {
			return Main.game.getNpc(Dale.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Dale.class);
		}
	};

	public static AbstractParserTarget HEADLESS_HORSEMAN = new AbstractParserTarget(Util.newArrayListOfValues("headlessHorseman", "headHorse"), "") {
		public String getDescription() {
			return Main.game.getNpc(HeadlessHorseman.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(HeadlessHorseman.class);
		}
	};

	public static AbstractParserTarget CERIDWEN = new AbstractParserTarget(Util.newArrayListOfValues("ceridwen"), "") {
		public String getDescription() {
			return Main.game.getNpc(Ceridwen.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Ceridwen.class);
		}
	};

	public static AbstractParserTarget IMSU = new AbstractParserTarget(Util.newArrayListOfValues("imsu"), "") {
		public String getDescription() {
			return Main.game.getNpc(Imsu.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Imsu.class);
		}
	};

	public static AbstractParserTarget HALE = new AbstractParserTarget(Util.newArrayListOfValues("hale"), "") {
		public String getDescription() {
			return Main.game.getNpc(Hale.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Hale.class);
		}
	};

	public static AbstractParserTarget PENELOPE = new AbstractParserTarget(Util.newArrayListOfValues("penelope"), "") {
		public String getDescription() {
			return Main.game.getNpc(Penelope.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Penelope.class);
		}
	};

	public static AbstractParserTarget BELLE = new AbstractParserTarget(Util.newArrayListOfValues("belle"), "") {
		public String getDescription() {
			return Main.game.getNpc(Belle.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Belle.class);
		}
	};

	public static AbstractParserTarget DAPHNE = new AbstractParserTarget(Util.newArrayListOfValues("daphne"), "") {
		public String getDescription() {
			return Main.game.getNpc(Daphne.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Daphne.class);
		}
	};

	public static AbstractParserTarget FARAH = new AbstractParserTarget(Util.newArrayListOfValues("farah"), "") {
		public String getDescription() {
			return Main.game.getNpc(Farah.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Farah.class);
		}
	};

	public static AbstractParserTarget CALLIE = new AbstractParserTarget(Util.newArrayListOfValues("callie"), "") {
		public String getDescription() {
			return Main.game.getNpc(Callie.class).getDescription();
		}
		@Override
		public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
			return Main.game.getNpc(Callie.class);
		}
	};
	
	
	
	/** A list of the hard-coded parser targets above. */
	private static List<AbstractParserTarget> coreParserTargets = new ArrayList<>();
	
	private static List<AbstractParserTarget> allParserTargets = new ArrayList<>();
	private static Map<AbstractParserTarget, String> parserTargetToIdMap = new HashMap<>();
	private static Map<String, AbstractParserTarget> idToParserTargetMap = new HashMap<>();

	public static List<AbstractParserTarget> getAllParserTargets() {
		return allParserTargets;
	}

	public static AbstractParserTarget getParserTargetFromId(String id) {
		return getParserTargetFromId(id, false);
	}
	
	public static AbstractParserTarget getParserTargetFromId(String id, boolean exact) {
		if (!exact) {
			id = Util.getClosestStringMatch(id, idToParserTargetMap.keySet());
		}
		return idToParserTargetMap.get(id);
	}

	public static String getIdFromParserTarget(AbstractParserTarget parserTarget) {
		return parserTargetToIdMap.get(parserTarget);
	}

	/**
	 * Adds an associated between the tag and the target for parsing.
	 */
	public static void addAdditionalParserTarget(String tag, NPC target) {
		AbstractParserTarget newParserTarget = new AbstractParserTarget(Util.newArrayListOfValues(tag), "") {
			public String getDescription() {
				return target.getDescription();
			}
			@Override
			public GameCharacter getCharacter(String tag, List<GameCharacter> specialNPCList) {
				return target;
			}
		};
		if(idToParserTargetMap.containsKey(tag)) {
//			System.err.println("Warning: Parser target of '"+tag+"' has been replaced!");
			removeAdditionalParserTarget((NPC) idToParserTargetMap.get(tag).getCharacter(null, null));
		}
		
		parserTargetToIdMap.put(newParserTarget, tag);
		idToParserTargetMap.put(tag, newParserTarget);
		allParserTargets.add(newParserTarget);

		UtilText.addNewParserTarget(tag, target); // Add this parser target to the scripting engine
	}
	
	/**
	 * Removes map references to the specified NPC.
	 */
	public static void removeAdditionalParserTarget(NPC target) {
		AbstractParserTarget targetToRemove = null;
		
		for(AbstractParserTarget parserTarget : allParserTargets) {
			if(!coreParserTargets.contains(parserTarget)) { // Do not remove core parser targets
				GameCharacter targetFound = parserTarget.getCharacter("", new ArrayList<>());
				if(targetFound!=null && targetFound.equals(target)) {
					targetToRemove = parserTarget;
					break;
				}
			}
		}
		
		if(targetToRemove!=null) {
			String idToRemove = parserTargetToIdMap.remove(targetToRemove);
			idToParserTargetMap.remove(idToRemove);
			allParserTargets.remove(targetToRemove);
			UtilText.removeParserTarget(idToRemove); // Remove this parser target from the scripting engine
		}
	}
	
	static {
		// Hard-coded parserTarget types (all those up above):
		
		Field[] fields = ParserTarget.class.getFields();
		
		for(Field f : fields) {
			if(AbstractParserTarget.class.isAssignableFrom(f.getType())) {
				AbstractParserTarget parserTarget;
				try {
					parserTarget = ((AbstractParserTarget) f.get(null));

					parserTargetToIdMap.put(parserTarget, f.getName());
					idToParserTargetMap.put(f.getName(), parserTarget);
					allParserTargets.add(parserTarget);
					coreParserTargets.add(parserTarget);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
