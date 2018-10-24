package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
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
public class FortressAlpha {
	
	public static void clearFortress() {
		Main.game.getDialogueFlags().impFortressAlphaPacifiedTime = Main.game.getMinutesPassed();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);

		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA) {
				character.returnToHome();
			}
		}
	}
	
	public static void resetFortress() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, false);
		banishImpGuards();
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
			imp.setGenericName("alpha-imp leader");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
			imp.setGenericName("alpha-imp archer");
			imp.setLevel(8+Util.random.nextInt(5)); // 8-12
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
	}
	
	//TODO check level sorting
	public static List<GameCharacter> getImpBossGroup() {
		List<GameCharacter> impGuards = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP)) {
			if((character instanceof ImpAttacker || character instanceof FortressAlphaLeader) && !character.isSlave()) {
				impGuards.add(character);
			}
		}
		impGuards.sort((imp1, imp2) -> imp1.getLevel()-imp2.getLevel());
		return impGuards;
	}
	
	public static List<GameCharacter> getImpGuards() {
		List<GameCharacter> impGuards = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE)) {
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
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_ALPHA_ENTRANCE) {
			allCharacters.addAll(getImpGuards());
		}

		Collections.sort(allCharacters, (c1, c2) -> c1 instanceof Elemental?(c2 instanceof Elemental?0:1):(c2 instanceof Elemental?-1:0));
		return allCharacters;
	}
	
	private static void resetGuards() {
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsDefeated, false);
		}
	}
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Gateway", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsDefeated);
		}
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified)) {
				if(Main.game.getPlayer().getTotalTimesHadSex(getImpGuards().get(0))==0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_PACIFIED"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_PACIFIED_BY_SEX"));
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_DESERTED"));
				
			} else if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_DEMON"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsDefeated)) {
				if (index == 1) {
					return new Response("Leave", "Head back out into the tunnels.", PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA.getDialogue(false)) {
						@Override
						public void effects() {
							resetGuards();
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "LEAVE_FORTRESS"));
						}
					};
	
				} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsDefeated)) {
					return new ResponseCombat("Attack", "Change your mind about not wanting to fight the imps, and decide to teach them a lesson!", getImpGuards(), null);
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
						return new Response("Command",
								"The imps seem incredibly nervous at the prospect of being confronted by a demon. Use this to your advantage and order them to step aside.",
								ENTRANCE_PACIFIED) {
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
					return new ResponseSex(isCompanionDialogue()?"Offer oral (solo)":"Offer oral",
							"Offer to perform oral sex on the imps in exchange for them letting you into the fortress.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getImpGuards(),
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							GUARDS_AFTER_ORAL_FOR_ENTRY,
							UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL", getAllCharacters()),
							ResponseTag.PREFER_ORAL);
					
				} else if(index==4 && isCompanionDialogue()) {
					return new ResponseSex("Offer oral (both)",
							UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] will perform oral sex on them in exchange for being let into the fortress."),
							true,
							false,
							Main.game.getPlayer().getParty(),
							getImpGuards(),
							null,
							GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
							UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters()),
							ResponseTag.PREFER_ORAL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_ORAL_FOR_ENTRY = new DialogueNodeOld("", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_ORAL_FOR_ENTRY"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNodeOld GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION = new DialogueNodeOld("", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_PACIFIED = new DialogueNodeOld("", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_PACIFIED"));
			
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
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
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
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
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
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								getImpGuards(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
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
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
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
								GUARDS_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
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
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
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

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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
						
						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
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
									if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
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
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the gang of imps move you into position...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpGuards(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpGuards(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
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
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
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
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
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
				return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGuards()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "COURTYARD"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld KEEP = new DialogueNodeOld("Keep", ".", false) { //TODO dialogue from here
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_PACIFIED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
					return new Response("Enter", "The keep is deserted, and there's nothing of value inside...", null);
				} else {
					return new Response("Enter", "Push open the doors of the keep and step inside.", KEEP_ENTRY);
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
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_ENTRY"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Negotiate", "Tell the succubus that you've come to talk, not fight.", KEEP_TALK);

			} else if(index==2) {
				return new ResponseCombat("Attack", "Defend yourself against the succubus and her minions!", getImpBossGroup(), null);
				
			} else if(index==3) {
				return new ResponseSex(isCompanionDialogue()?"Surrender (solo)":"Surrender",
						"Surrender to the succubus, allowing her and her imps to do what they please with you.",
						true,
						false,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						getImpBossGroup(),
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL", getAllCharacters()));
				
			} else if(index==4 && isCompanionDialogue()) {
				return new ResponseSex("Surrender (both)",
						UtilText.parse(getMainCompanion(), "Surrender both yourself and [npc.name] to the succubus, allowing her and her imps to do what they please with you."),
						true,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
					}
				};
				
			} else {
				return null;
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_TALK"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Spell> elementalSpells = Util.newArrayListOfValues(Spell.ELEMENTAL_AIR, Spell.ELEMENTAL_ARCANE, Spell.ELEMENTAL_FIRE, Spell.ELEMENTAL_EARTH, Spell.ELEMENTAL_WATER);
			
			if (index == 1) {
				if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
					return new Response("Convince", "Convince the succubus that you're on good terms with your Lilin mother, and that she'll be duly punished if she dares to stand against you.", KEEP_CONVINCE) {
						@Override
						public void effects() {
							//TODO get key and banish
						}
					};
				} else {
					return new Response("Convince", "As you're not a demon, this succubus isn't interested in anything you have to say...", null);
				}

			} else if (index == 2) {
				return new Response("Join", "Tell the succubus that you were interested in joining her gang...", KEEP_JOIN);
				
			} else if (index == 3) {
				if(Main.game.getPlayer().isElementalSummoned()) {
					return new Response("Elemental", "Intimidate the succubus and her gang by drawing attention to the fact that you are powerful enough to have a summoned elemental with you...", KEEP_ELEMENTAL) {
						@Override
						public void effects() {
							//TODO get key and banish
						}
					};
					
				} else if((Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer()))
						|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer()))) {
					return new Response("Elemental", "Intimidate the succubus and her gang by summoning your elemental in front of them.", KEEP_ELEMENTAL_SUMMON) {
						@Override
						public void effects() {
							for(Spell spell : elementalSpells) {
								if(Main.game.getPlayer().hasSpell(spell) && Main.game.getPlayer().getMana()>=spell.getModifiedCost(Main.game.getPlayer())) {
									Main.game.getTextEndStringBuilder().append(spell.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
									break;
								}
								//TODO get key and banish
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_ELEMENTAL_SUMMON_END"));
						}
					};
					
				} else {
					return new Response("Elemental", "You'd need to have an elemental summoned, or know such a spell and have enough aura to cast it, if you wanted to intimidate the succubus...", null);
				}
				
			} else if(index==4) {
				return new ResponseCombat("Fight", "This clearly isn't working... Choose to fight instead of talking, and defend yourself against the succubus and her minions!", getImpBossGroup(), null);
				
			} else if(index==5) {
				return new ResponseSex(isCompanionDialogue()?"Surrender (solo)":"Surrender",
						"Decide that you have no chance against this succubus and her minions, and surrender yourself to them.",
						true,
						false,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						getImpBossGroup(),
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL", getAllCharacters()));
				
			} else if(index==6 && isCompanionDialogue()) {
				return new ResponseSex("Surrender (both)",
						UtilText.parse(getMainCompanion(), "Decide that you and [npc.name] have no chance against this succubus and her minions, and surrender yourselves to them."),
						true,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
					}
				};
				
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_CONVINCE"));
			
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
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_JOIN"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "You aren't prepared to go that far! Tell the succubus as such, and prepare to defend yourself!", getImpBossGroup(), null);
				
			} else if(index==2) {
				return new ResponseSex(isCompanionDialogue()?"Agree (solo)":"Agree",
						"Decide to do as the succubus demands, and get down under the table to orally service her.",
						true,
						false,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						getImpBossGroup(),
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_JOINING_SEX,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL", getAllCharacters()));
				
			} else if(index==3 && isCompanionDialogue()) {
				return new ResponseSex("Agree (both)",
						UtilText.parse(getMainCompanion(), "Decide to do as the succubus demands, and get [npc.name] to join you as the two of you service her under the table."),
						true,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						KEEP_AFTER_JOINING_SEX,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_ELEMENTAL"));
			
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressAlpha", "KEEP_ELEMENTAL_SUMMON"));
			
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
		public String getDescription() {
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
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
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
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
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()),
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()),
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
								getImpBossGroup(),
								null,
								GUARDS_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
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
						return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
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
								getImpBossGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the gentle pace.)"),
								true,
								false,
								getImpBossGroup(),
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
								getImpBossGroup(),
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
								getImpBossGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group sex",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, "Have dominant sex with the imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									getImpBossGroup(),
									Main.game.getPlayer().getParty(),
									null,
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpBossGroup(),
									null,
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the imps while you watch."),
									false,
									false,
									getImpBossGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "Hand [npc.name] over to the imps, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpBossGroup(),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortressAlphaCompanions", "GUARDS_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
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
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the gang of imps move you into position...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"Try to resist as the gang of imps move you into position...",
						false,
						false,
						Main.game.getPlayer().getParty(),
						getImpBossGroup(),
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESIST);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_JOINING_SEX = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
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
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
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
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
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
				return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortressAlpha", "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpBossGroup()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
