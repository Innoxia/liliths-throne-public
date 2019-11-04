package com.lilithsthrone.game.dialogue.places.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensDialogue {
	
	public static void init() {
		// Spawn guard rats if missing:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				
				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(8);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Spawn humans:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM).isEmpty()) {
			try {
				for(int i=0;i<4;i++) { //TODO creampies from generic sex in hourly update
					NPC human = new RatWarrensCaptive(Gender.F_V_B_FEMALE);
					Main.game.addNPC(human, false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
	}
	
	/**
	 * @return A list with the player's main companion in index 0, with gang members present being in slots after that.
	 */
	private static List<GameCharacter> getGuards(boolean includeCompanion) { //TODO test sort by level
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE));
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc));
		Collections.sort(guards, (a, b)->a.getLevel()-b.getLevel());
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			guards.add(Main.game.getPlayer().getMainCompanion());
		}
		return guards;
	}
	
	private static void banishGuards() {
		for(GameCharacter guard : getGuards(false)) {
			Main.game.banishNPC((NPC) guard);
		}
	}
	
	private static void spawnGuards(boolean withLeader) {
		try {
			if(withLeader) {
				NPC leader = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(leader, false);
				leader.setLevel(10);
				leader.setLocation(Main.game.getPlayer(), true);
			} else {
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
			}
			for(int i=0;i<(withLeader?5:4);i++) {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(i==1, i==2));
				Main.game.addNPC(rat, false);
				rat.setLevel(5+Util.random.nextInt(5));
				rat.setLocation(Main.game.getPlayer(), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static GameCharacter getGuardLeader() {
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
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY = new DialogueNode("Entrance", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_HOSTILE", getGuards(true));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_WHORE", getGuards(true));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_REPEAT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INITIAL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) { // Have to fight them again:
				if(index==1) {
					return new Response("Get ready",
							"The gang members have recognised you, and so you're forced to defend yourself!<br/>[style.italicsBad(Be prepared for a significant amount of gang members arriving as backup!)]",
							ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_FIGHT_REPEAT"));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) { // They want sex again:
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) { // They let you in again:
				if(index==1) {
					return new Response("Continue", "Continue onwards into the Rat Warrens.", ENTRANCE_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_CONTINUE"));
						}
					};
				}
			}
			
			if(index==1) {
				return new Response("Axel", "Tell the gang members that you're here to discuss the matter of Axel's payments with Vengar.", ENTRANCE_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntry, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_AXEL"));
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
						spawnGuards(false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_FIGHT"));
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
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "RAT_WARREN_INITIAL_ENTRY_WHORE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse", "Tell the gang members that Vengar would be upset with them if he didn't get to be the first to fuck you.", ENTRANCE_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "RAT_WARREN_INITIAL_ENTRY_WHORE_NO_SEX"));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"Agree (solo)"
							:"Agree",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Tell the gang members that you'd be happy to show them what you're capable of, but that [npc.name] is going to save [npc.herself] for Vengar...")
							:"Tell the gang members that you'd be happy to show them what you're capable of...",
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
						UtilText.parseFromXMLFile("places/submission/ratWarrens", "RAT_WARREN_INITIAL_ENTRY_WHORE_SOLO", getGuards(true)));
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedTo(getGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
					return new Response(UtilText.parse(companion, "Agree (both)"),
							UtilText.parse(getGuards(true), "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name] or [npc3.name], and as [npc.sheIs] not your slave, you can't force [npc.herHim] into it..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Agree (both)"),
							UtilText.parse(getGuards(true), "Tell the gang members that both you and [npc.name] would be happy to show them what you're capable of."),
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companion),
									null,
									null),
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "RAT_WARREN_INITIAL_ENTRY_WHORE_BOTH", getGuards(true)));
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedTo(getGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
					return new Response(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getGuards(true), "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.sheIs] not your slave, you can't force [npc2.herHim] to have sex..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMainCompanion(), "Tell the gang members that you'd be happy to let [npc.name] show them what [npc.sheIs] capable of, but that you're going to save yourself for Vengar..."),
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "RAT_WARREN_INITIAL_ENTRY_WHORE_COMPANION", getGuards(true)));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_ENTRANCE_SEX = new DialogueNode("Finished", "The gang members have finished with you...", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(Sex.getSubmissiveSpectators().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_COMPANION_WATCHING");
				} else if(Sex.getSubmissiveSpectators().contains(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_PLAYER_WATCHING");
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_BOTH");
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_SOLO");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue onwards into the Rat Warrens.", ENTRANCE_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_ENTRANCE_SEX_CONTINUE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_FIGHT = new DialogueNode("Entrance", "", true) {
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
						"It's time to give these gang members a taste of their own medicine!<br/>[style.italicsBad(Be prepared for a significant amount of gang members arriving as backup!)]",
						(NPC)getGuardLeader(),
						getGuards(false),
						new HashMap<>());
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY");
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
			} else if(index==1) {
				return "Inventories";
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Scare off", "Tell the gang members to get out of here while they still can...", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(true)));
							banishGuards();
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
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX", getGuards(true)));
					
				} else if (index == 3) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo sex (Gentle)"
								:"Sex (Gentle)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the gang members. (Start sex in the gentle pace.)")
								:"Have sex with the gang members. (Start sex in the gentle pace.)",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_GENTLE", getGuards(true)),
							ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
					
				} else if (index == 4) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Solo sex (Rough)"
								:"Sex (Rough)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the gang members. (Start sex in the rough pace.)")
								:"Have sex with the gang members. (Start sex in the rough pace.)",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_ROUGH", getGuards(true)),
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
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_SUBMIT", getGuards(true)));
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response("Group sex",
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
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
								UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_GROUP", getGuards(true)));
					}
					
				} else if (index == 7 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response("Group submission",
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
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
								UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_GROUP_SUBMIT", getGuards(true)));
					}
					
				} else if (index == 8 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, "[npc.Name] is not interested in having sex with the gang members, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
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
								UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_SEX_GIVE_TO_COMPANION", getGuards(true)));
					}
					
				} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getGuardLeader()) && ((!companion.isSlave() && !(companion instanceof Elemental)) || !Main.game.isNonConEnabled())) {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the gang members, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
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
								UtilText.parseFromXMLFile("places/submission/ratWarrens", "IMP_COMBAT_VICTORY_OFFER_COMPANION", getGuards(true))) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getGuardLeader()) && Main.game.isNonConEnabled()) {
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
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Thrown out", "The gang members unceremoniously throw you back out into Submission's tunnels.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							exit();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_THROWN_OUT", getGuards(true)));
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new ResponseSex("Sex",
							"Allow the gang members to move you into position...",
							false,
							false,
							getGuards(false),
							Main.game.getPlayer().getParty(),
							null,
							null,
							GUARD_COMBAT_DEFEAT_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_SEX", getGuards(true)));
					
				} else if (index == 2) {
					return new ResponseSex("Eager sex",
							"Eagerly allow yourself to be moved into position by the gang members...",
							false,
							false,
							getGuards(false),
							Main.game.getPlayer().getParty(),
							null,
							null,
							GUARD_COMBAT_DEFEAT_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_SEX_EAGER", getGuards(true)),
							ResponseTag.START_PACE_PLAYER_SUB_EAGER);
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist sex",
							"Try to resist as the gang members move you into position...",
							false,
							false,
							getGuards(false),
							Main.game.getPlayer().getParty(),
							null,
							null,
							GUARD_COMBAT_DEFEAT_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_SEX_RESIST", getGuards(true)),
							ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY_AFTER_SEX = new DialogueNode("Step back", "Now that you've had your fun, you step back and wonder what to do with the gang members...", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_VICTORY_AFTER_SEX", getGuards(true));
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
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Scare off", "Scare the gang members off and continue on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							banishGuards();
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARD_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};

	public static final DialogueNode GUARD_COMBAT_DEFEAT_AFTER_SEX = new DialogueNode("Collapse", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "You're completely worn out from sex with the gang members...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thrown out", "The gang members unceremoniously throw you back out into Submission's tunnels.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						exit();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "GUARD_COMBAT_DEFEAT_AFTER_SEX_THROWN_OUT", getGuards(true)));
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
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Tell the gang members that you're leaving, and head back out into Submission's tunnels.", SubmissionGenericPlaces.RAT_WARREN) {
					@Override
					public void effects() {
						exit();
					}
				};
				
			} else if(index==6) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_FIGHT"));
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
					spawnGuards(false);
					
				} else {
					spawnGuards(true);
				}
				
			} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					spawnGuards(false);
					
				} else {
					spawnGuards(true);
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
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CHECKPOINT_LEFT");
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CORRIDOR_CLEARED");
				}
				
			} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CHECKPOINT_RIGHT");
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CORRIDOR_CLEARED");
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CORRIDOR_CLEARED");
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "CORRIDOR");
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
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "DORMITORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_FIGHT"));
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
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "DICE_DEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			DicePokerTable table = DicePokerTable.COPPER;
			int buyIn = table.getInitialBet()+table.getRaiseAmount();
			
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=buyIn) {
					return new ResponseEffectsOnly("[style.colourMasculine(Rat-boy)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
							"Start playing dice poker with one of the rat-boys. The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
						@Override
						public void effects() {
							NPC gambler = new RatGangMember(Gender.getGenderFromUserPreferences(Femininity.MASCULINE));
							try {
								Main.game.addNPC(gambler, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
							gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
							Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN)));
						}
					};
					
				} else {
					return new Response("[style.colourMasculine(Rat-boy)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
							"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
							+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play with the rat-boy!",
							null);
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()>=buyIn) {
					return new ResponseEffectsOnly("[style.colourFeminine(Rat-girl)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
							"Start playing dice poker with one of the rat-boys. The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
						@Override
						public void effects() {
							NPC gambler = new RatGangMember(Gender.getGenderFromUserPreferences(Femininity.FEMININE));
							try {
								Main.game.addNPC(gambler, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
							gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
							Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_NO_CONTENT))); //TODO Fix dialogue //TODO weighted dice
						}
					};
					
				} else {
					return new Response("[style.colourMasculine(Rat-boy)] ("+UtilText.formatAsMoney(buyIn, "span")+")",
							"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
							+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play with the rat-boy!",
							null);
				}
				
			} else if(index==3){
				return new Response("Rules", "Ask one of the gang members what the rules of dice poker are.", DICE_DEN_RULES);
				
			} else if(index==6) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_FIGHT"));
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
	
	public static final DialogueNode DICE_DEN_RULES = new DialogueNode("Dice Den", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "GAMBLING_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Rules", "You are already asking one of the gang members what the rules of dice poker are.", DICE_DEN_RULES);
			}
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_NO_CONTENT = new DialogueNode("Dice Den", "", false) {
		@Override
		public void applyPreParsingEffects() {
			banishGuards();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("Milking Room", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "MILKING_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> milkers = new ArrayList<>();
			for(GameCharacter milker : Main.game.getCharactersPresent()) {
				if(milker instanceof RatWarrensCaptive && !Main.game.getPlayer().getCompanions().contains(milker)) {
					milkers.add(milker);
				}
			}
			if(index>=1 && index<=4) {
				GameCharacter milker = milkers.get(index-1);
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					if(Main.game.getPlayer().getMoney()<500) {
						return new Response(
								"Use "+milker.getName(true)+" ("+UtilText.formatAsMoney(500, "span")+")",
								UtilText.parse(milker, "You don't have enough flames to pay for sex with [npc.name]..."),
								null);
					}
					return new ResponseSex(
							"Use "+milker.getName(true)+" ("+UtilText.formatAsMoney(500)+")",
							UtilText.parse(milker, "Pay the gang member 500 flames to have sex with [npc.name]..."),
							true,
							false,
							new SMStocks(
									true, true, true,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(milker, SexSlotStocks.LOCKED_IN_STOCKS))),
							Main.game.getPlayer().getParty(),
							null,
							AFTER_MILKER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "MILKING_ROOM_SEX_PAID"));
					
				} else {
					return new ResponseSex(
							"Use "+milker.getName(true),
							UtilText.parse(milker, "Now that you've defeated the gang members in this area, there's nobody to stop you from having sex with [npc.name]..."),
							true,
							false,
							new SMStocks(
									true, true, true,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(milker, SexSlotStocks.LOCKED_IN_STOCKS))),
							Main.game.getPlayer().getParty(),
							null,
							AFTER_MILKER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens", "MILKING_ROOM_SEX_FREE"));
				}
				
			} else if(index==5) { //TODO They don't want to leave... Have to defeat Vengar first and then take the time to convince them
				return new Response(
						"Free captives",
						"Now that you've defeated the gang members in this area, there's nobody to stop you from freeing the captive humans...",
						null);
				
			} else if(index==6) {
				return new Response("Challenge", "Tell the gang members that you're here to fight.<br/>[style.italicsBad(This will undoubtedly result in a significant amount of gang members arriving as backup!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens", "ENTRANCE_INTITIAL_ENTRY_FIGHT"));
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
	
	
	public static final DialogueNode AFTER_MILKER_SEX = new DialogueNode("Finished", "Step back and decide what to do now that you've had your fun...", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "AFTER_MILKER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL = new DialogueNode("Vengar's Hall", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "VENGARS_HALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				return VENGARS_HALL_APPROACH.getResponse(responseTab, index);
			}
			if(index==1) {
				return new Response("Approach", "Approach Vengar and introduce yourself to him.", VENGARS_HALL_APPROACH);
				
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "VENGARS_HALL_APPROACH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				// Fight
				return new Response("Challenge", "", VENGARS_HALL_CHALLENGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if(index==2) {
				// Join
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
					
				}
				// Persuade
				
			} else if(index==4) {
				// Resonance stone
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHALLENGE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "VENGARS_HALL_CHALLENGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Silence.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Shadow.class), ""),
								new Value<>(Main.game.getNpc(Silence.class), "")));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM = new DialogueNode("Vengar's Bedroom", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens", "VENGARS_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
