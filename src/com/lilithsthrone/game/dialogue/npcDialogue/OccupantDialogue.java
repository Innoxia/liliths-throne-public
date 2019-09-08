package com.lilithsthrone.game.dialogue.npcDialogue;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.3.4.5
 * @author Innoxia
 */
public class OccupantDialogue {

	private static NPC occupant;
	private static GameCharacter characterForSex;
	private static NPC characterForSexSecondary;
	private static List<NPC> charactersPresent;
	private static boolean isApartment;
	private static boolean confirmKickOut;
	private static boolean initFromCharactersPresent;
	
	public static void initDialogue(NPC targetedOccupant, boolean isApartment, boolean initFromCharactersPresent) {
//		Main.game.setActiveNPC(targetedOccupant);
		occupant = targetedOccupant;
		characterForSex = targetedOccupant;

		characterForSexSecondary = null;
		charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
		charactersPresent.removeIf((npc) -> !Main.game.getPlayer().getCompanions().contains(npc) && (!npc.isSlave() || !npc.getOwner().isPlayer()) && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		if(charactersPresent.size()>1) {
			if(charactersPresent.contains(Main.game.getPlayer().getMainCompanion()) && !occupant().equals(Main.game.getPlayer().getMainCompanion())) {
				characterForSexSecondary = (NPC) Main.game.getPlayer().getMainCompanion();
				
			} else {
				characterForSexSecondary = charactersPresent.stream().filter((npc) -> !npc.equals(occupant())).findFirst().get();
			}
		}
		
		OccupantDialogue.isApartment = isApartment;
		
		confirmKickOut = false;
		
		OccupantDialogue.initFromCharactersPresent = initFromCharactersPresent;
	}
	
	private static DialogueNode getAfterSexDialogue() {
		if(initFromCharactersPresent) {
			return CharactersPresentDialogue.AFTER_SEX;
		} else if(isApartment) {
			return APARTMENT_AFTER_SEX;
		} else {
			return AFTER_SEX;
		}
	}
	
	private static NPC occupant() {
//		return Main.game.getActiveNPC();
		return occupant;
	}
	
	private static boolean hasJob() {
		return occupant().hasJob();
	}
	
	private static void applyReactionReset() {
		if(occupant().isVisiblyPregnant()){
			occupant().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(occupant(), true);
		}
		occupant().removeFlag(NPCFlagValue.occupantHasNewJob);
		confirmKickOut = false;
	}

	public static String getTextFilePath() {
		if(occupant().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(characterForSex.isRelatedTo(Main.game.getPlayer()) || (characterForSexSecondary!=null && characterForSexSecondary.isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}
	
	private static boolean isCompanionSexPublic() {
		return !isApartment
				&& Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}
	
	public static final DialogueNode OCCUPANT_START = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START", occupant()));
			
			if(occupant().isVisiblyPregnant()) {
				if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PREGNANCY_REVEAL", occupant()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_STILL_PREGNANT", occupant()));
				}
			}
			
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PLAYER_PREGNANCY", occupant()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
				}
			}
			
			if(occupant().hasFlag(NPCFlagValue.occupantHasNewJob)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH_WITH_NEW_JOB", occupant()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Talk";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(Sex)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(Manage)]");
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
						return new Response("Life", "Ask [npc.name] about [npc.her] past life.", OCCUPANT_TALK_LIFE) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Life", "You've already talked with [npc.name] about [npc.her] past life today.", null);
					}
					
				} else if (index == 2) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
						return new Response(hasJob()?"Job":"Job hunt",
								UtilText.parse(occupant(), hasJob()?"Ask [npc.name] about [npc.her] job.":"Ask [npc.name] how [npc.her] job hunt is going."),
								OCCUPANT_TALK_JOB) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
						
					} else {
						return new Response("Job", UtilText.parse(occupant(),
								hasJob()?"You've already asked [npc.name] about [npc.her] job today.":"You've already asked [npc.name] how [npc.her] job hunt is going today."), null);
					}
					
				} else if (index == 3) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLilaya)) {
						return new Response("Lilaya", "Ask [npc.name] about [npc.her] interactions with Lilaya and Rose.", OCCUPANT_TALK_LILAYA) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLilaya);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Lilaya", "You've already talked with [npc.name] about [npc.her] interactions with Lilaya and Rose today.", null);
					}
					
				} else if (index == 4) {
					if(Main.game.getPlayer().getSlavesOwned().size()==0) {
						return new Response("Slaves", "You don't own any slaves, so there's nothing to discuss about this with [npc.name].", null);
						
					} else if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkSlaves)) {
						return new Response("Slaves", "Ask [npc.name] about [npc.her] interactions with your slaves.", OCCUPANT_TALK_SLAVES) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkSlaves);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Slaves", "You've already talked with [npc.name] about [npc.her] interactions with your slaves today.", null);
					}
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasCompanion(occupant())) {
						if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
							return new Response("Add to party",
									UtilText.parse(occupant(), "[npc.Name] cannot be added to your party!"),
									null);
								
						} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
							return new Response("Add to party",
									UtilText.parse(occupant(), "Ask [npc.name] if [npc.she] would like to accompany you for a while."),
									OCCUPANT_START){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().addCompanion(occupant());
								}
							};
						} else {
							return new Response("Add to party",
									"You are already at your party limit!",
									null);
						}
						
					} else {
						return new Response("Remove from party",
								"Command [npc.name] to leave your party.",
								OCCUPANT_START){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getPlayer().removeCompanion(occupant());
							}
						};
					}
					
				} else if (index == 10) {
					if(hasJob()) {
						return new Response("Move out", "Tell [npc.name] that you think that [npc.her] idea of moving out would be good for [npc.herHim].<br/>"
								+ "[style.italics(You get the option of saving or removing this character in the next scene.)]",
								OCCUPANT_MOVE_OUT) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
						
					} else {
						if(confirmKickOut) {
							return new Response("Confirm removal", "Tell [npc.name] that you want [npc.herHim] to leave.<br/>"
									+ "[style.italicsBad(Permanently removes this character from the game.)]",
									OCCUPANT_KICK_OUT) {
								@Override
								public Colour getHighlightColour() {
									return Colour.GENERIC_BAD;
								}
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_KICK_OUT", occupant()));
									Main.game.getPlayer().removeFriendlyOccupant(occupant());
									Main.game.banishNPC(occupant());
								}
							};
							
						} else {
							return new ResponseEffectsOnly("Kick out", "Tell [npc.name] that you want [npc.herHim] to leave.<br/>"
									+ "[style.italicsMinorBad(After choosing this action, you'll need to click again to confirm that you want this character removed from the game forever.)]") {
								@Override
								public void effects() {
									confirmKickOut = true;
								}
							};
						}
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							applyReactionReset();
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 1) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex", UtilText.parse(occupant(), "[npc.Name] is not attracted to you..."), null);
						
					} else {
						return new ResponseSex("Sex", "Have sex with [npc.name].", 
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(occupant()),
										Main.game.getPlayer().getCompanions(),
										null) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
					}
					
				} else if (index == 2) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Spitroast (front)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Spitroast (front)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast (front)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Spitroast (front)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroast (front)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
						return new Response("Spitroast (front)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to [npc2.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);

					} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
						return new Response("Spitroast (front)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] is not attracted to [npc.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);
						
					} else {
						return new ResponseSex(
								"Spitroast (front)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Move around in front of [npc.name] so that you can use [npc.her] mouth while [npc2.name] takes [npc.her] rear."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(characterForSexSecondary, Main.game.getPlayer()),
										Util.newArrayListOfValues(characterForSex),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", characterForSex, characterForSexSecondary)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 3) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Spitroast (behind)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Spitroast (behind)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast (behind)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Spitroast (behind)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroast (behind)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
						return new Response("Spitroast (front)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to [npc2.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);

					} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
						return new Response("Spitroast (front)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] is not attracted to [npc.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);
						
					} else {
						return new ResponseSex(
								"Spitroast (behind)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Move around behind [npc.name] so that you can use [npc.her] rear while [npc2.name] takes [npc.her] mouth."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
										Util.newArrayListOfValues(characterForSex),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", characterForSex, characterForSexSecondary)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 4) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Side-by-side (as dom)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Side-by-side (as dom)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Side-by-side (as dom)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Side-by-side (as dom)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Side-by-side (as dom)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else {
						return new ResponseSex("Side-by-side (as dom)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Push [npc.name] and [npc2.name] down onto all fours, before kneeling behind [npc.name], ready to fuck them both side-by-side."),
								null, null, null, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", characterForSex, characterForSexSecondary)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 6) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
						
					} else {
						return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(occupant()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(), UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					}
					
				} else if (index == 7) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Spitroasted (front)", "You'd a third person to be present in order to get spitroasted...", null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Spitroasted (front)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted (front)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Spitroasted (front)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroasted (front)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else {
						return new ResponseSex(
								"Spitroasted (front)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Get down on all fours facing [npc.name], so that [npc.she] can use your mouth while [npc2.name] takes your rear."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(characterForSexSecondary, characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSex, characterForSexSecondary)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 8) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Spitroast (behind)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Spitroasted (behind)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted (behind)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Spitroasted (behind)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroasted (behind)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else {
						return new ResponseSex(
								"Spitroasted (behind)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Get down on all fours and present your rear to [npc.name], so that [npc.she] can fuck you while [npc2.name] uses your mouth."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSexSecondary, characterForSex)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 9) {
					if(characterForSexSecondary==null || charactersPresent.size()<2) {
						return new Response("Side-by-side (as sub)", UtilText.parse(characterForSex, "You'd need a third person to be present in order to get fucked alongside either them or [npc.name]..."), null);
						
					} else if(characterForSex.isPlayer()) {
						return new Response("Side-by-side (as sub)", "You cannot target yourself for this action!", null);
						
					} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Side-by-side (as sub)", UtilText.parse(characterForSexSecondary, characterForSex, "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Side-by-side (as sub)", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Side-by-side (as sub)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
						return new Response("Side-by-side (as sub)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] is not attracted to [npc2.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);

					} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
						return new Response("Side-by-side (as sub)",
								UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] is not attracted to [npc.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);
						
					} else {
						return new ResponseSex("Side-by-side (as sub)",
								UtilText.parse(characterForSex, characterForSexSecondary, "Get down on all fours beside [npc2.name], so that [npc.name] can kneel down behind the two of you, ready to fuck you both side-by-side."),
								null, null, null, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", characterForSex, characterForSexSecondary)) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if(index==11) {
					if(characterForSexSecondary!=null) {
						return new ResponseEffectsOnly(
								UtilText.parse(characterForSex, "Target: <b style='color:"+characterForSex.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
								"Cycle the targeted character for group sex.") {
							@Override
							public void effects() {
								if(charactersPresent.size()>1) {
									for(int i=0; i<charactersPresent.size();i++) {
										if(charactersPresent.get(i).equals(characterForSex)) {
											if(i==charactersPresent.size()-1) {
												characterForSex = charactersPresent.get(0);
												if(characterForSexSecondary.equals(characterForSex)) {
													characterForSexSecondary = charactersPresent.get(1);
												}
											} else {
												characterForSex = charactersPresent.get(i+1);
												if(characterForSexSecondary.equals(characterForSex)) {
													characterForSexSecondary = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
												}
												break;
											}
										}
									}
								}
								Main.game.updateResponses();
							}
						};
						
					} else {
						return new Response(
								UtilText.parse(characterForSex, "Target: <b>[npc.Name]</b>"),
								"Cycle the targeted character for group sex.<br/>[style.italicsBad(You'd need to have a companion with you for this action to be unlocked!)]",
								null); 
					}
					
				} else if(index==12) {
					if(characterForSexSecondary!=null) {
						return new ResponseEffectsOnly(
								UtilText.parse(characterForSexSecondary, "Secondary: <b style='color:"+characterForSexSecondary.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
								"Cycle the secondary targeted character for group sex.") {
							@Override
							public void effects() {
								if(charactersPresent.size()>1) {
									for(int i=0; i<charactersPresent.size();i++) {
										if(charactersPresent.get(i).equals(characterForSexSecondary)) {
											if(i==charactersPresent.size()-1) {
												characterForSexSecondary = charactersPresent.get(0);
												if(characterForSexSecondary.equals(characterForSex)) {
													characterForSex = charactersPresent.get(1);
												}
											} else {
												characterForSexSecondary = charactersPresent.get(i+1);
												if(characterForSexSecondary.equals(characterForSex)) {
													characterForSex = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
												}
											}
											break;
										}
									}
								}
								Main.game.updateResponses();
							}
						};

					} else {
						return new Response(
								UtilText.parse(characterForSex, "Secondary: <b>[npc.Name]</b>"),
								"Cycle the secondary targeted character for group sex.<br/>[style.italicsBad(You'd need to have a companion with you for this action to be unlocked!)]",
								null);
					}

				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVING", occupant()));
							applyReactionReset();
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else  {
					return null;
				}
				
				
			} else if(responseTab == 2) {
				switch(index) {
					case 1:
						return new Response("Inspect",
								"Inspect [npc.name].",
								OccupantManagementDialogue.getSlaveryManagementInspectSlaveDialogue(occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
					case 2:
						return new Response("Job", "You cannot manage the job of a free-willed occupant. This option is only available for slaves.", null);
						
					case 3:
						return new Response("Permissions", "You cannot manage the permissions of a free-willed occupant. This option is only available for slaves.", null);
						
					case 4:
						return new ResponseEffectsOnly("Inventory",
								UtilText.parse(occupant(), "As [npc.name] is indebted to you for having saved [npc.herHim] from a life of crime, [npc.she] will happily let you choose what [npc.she] wears.")) {
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
										Main.mainController.openInventory(occupant(), InventoryInteraction.FULL_MANAGEMENT);
									}
								};
					case 5:
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
							return new Response("Send to Kate",
									"[npc.Name] trusts you enough to have you decide upon any appearance changes at Kate's beauty salon, 'Succubi's secrets'.",
									OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
										@Override
										public void effects() {
											applyReactionReset();
											Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
											BodyChanging.setTarget(occupant());
										}
									};
						} else {
							return new Response("Send to Kate", "You haven't met Kate yet!", null);
						}
						
					case 6:
						return new Response("Perk Tree", "Assign [npc.namePos] perk points.", OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
						
					case 7:
						if(!occupant().isAbleToSelfTransform()) {
							return new Response("Transformations", occupant().getUnableToTransformDescription(), null);
							
						} else {
							return new Response("Transformations",
									"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(occupant());
								}
							};
						}
						
					case 8:
						return new Response("Pet name", "Ask [npc.name] to call you by a different name.", OCCUPANT_CHOOSE_NAME);
						
					case 11:
						return new Response("Combat Moves", "Adjust the moves [npc.name] can perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(occupant(), OCCUPANT_START);
							}
						};
						
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
								
					default:
						return null;
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about life, family, friends, stories
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about either finding job, or job stories
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB", occupant()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB_HUNTING", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LILAYA = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about Lilaya and Rose
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LILAYA", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_SLAVES = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			String id = Util.randomItemFrom(Main.game.getPlayer().getSlavesOwned());
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES", occupant(), slave));

			} catch (Exception e) {
				Util.logGetNpcByIdError("OCCUPANT_TALK_SLAVES.getContent()", id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES_NULL_SLAVE", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Finish", "", true) {
		
		@Override
		public String getDescription(){
			return "Collapse down onto [npc.namePos] sofa and catch your breath.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));
				
			} else if(Sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_KICK_OUT = new DialogueNode("Kicking out", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT = new DialogueNode("Moving out", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT", occupant());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Visit apartment", "Tell [npc.name] that you'd love to see [npc.her] new apartment, and follow [npc.herHim] there.<br/>"
						+ "[style.italicsGood(Saves this character by moving them to a random street or boulevard tile in Dominion.)]",
						OCCUPANT_MOVE_OUT_APARTMENT) {
					@Override
					public void effects() {
						occupant().setRandomUnoccupiedLocation(WorldType.DOMINION, true, PlaceType.DOMINION_STREET, PlaceType.DOMINION_STREET_HARPY_NESTS, PlaceType.DOMINION_BOULEVARD);
						occupant().setHomeLocation();
						Main.game.getPlayer().setLocation(occupant().getWorldLocation(), occupant().getLocation(), false);
					}
				};
				
			} else if(index==10) {
				if(confirmKickOut) {
					return new Response("Confirm removal", "Tell [npc.name] that the [npc.she] should move on with [npc.her] life.<br/>"
							+ "[style.italicsBad(Permanently removes this character from the game.)]",
							OCCUPANT_KICK_OUT) {
						@Override
						public Colour getHighlightColour() {
							return Colour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_REMOVE_CHARACTER", occupant()));
							Main.game.getPlayer().removeFriendlyOccupant(occupant());
							Main.game.banishNPC(occupant());
							confirmKickOut = false;
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Remove character", "Tell [npc.name] that the [npc.she] should move on with [npc.her] life.<br/>"
							+ "[style.italicsMinorBad(After choosing this action, you'll need to click again to confirm that you want this character removed from the game forever.)]") {
						@Override
						public void effects() {
							confirmKickOut = true;
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT_APARTMENT = new DialogueNode("Moving out", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos] apartment");
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_APARTMENT", occupant());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};

	private static int sleepTimer = 240;
	
	public static final DialogueNode OCCUPANT_APARTMENT = new DialogueNode("Moving out", "", true) {

		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos] Apartment");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getCompanions().contains(occupant())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_AS_COMPANION", occupant()));
				
			} else if(occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START", occupant()));
				
				if(occupant().isVisiblyPregnant() && occupant().isCharacterPossiblyFather(Main.game.getPlayer().getId())) {
					if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PREGNANCY_REVEAL", occupant()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_STILL_PREGNANT", occupant()));
					}
				}
				
				if(Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(occupant().getId())) {
					if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PLAYER_PREGNANCY", occupant()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
					}
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_FINISH", occupant()));
				
				UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_NOT_AT_HOME", occupant()));
			}
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Talk";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(Sex)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(Manage)]");
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if (index == 1) {
					return new Response("Leave", "As [npc.name] is not at home right now, there's nothing left to do but head back out into Dominion.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
			
			if(responseTab == 0) {
				if (index == 1) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
						return new Response("Life", "Ask [npc.name] about [npc.her] past life.", OCCUPANT_APARTMENT_TALK_LIFE) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Life", "You've already talked with [npc.name] about [npc.her] past life today.", null);
					}
					
				} else if (index == 2) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
						return new Response("Job",
								UtilText.parse(occupant(), "Ask [npc.name] about [npc.her] job."),
								OCCUPANT_APARTMENT_TALK_JOB) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
						
					} else {
						return new Response("Job", UtilText.parse(occupant(), "You've already asked [npc.name] about [npc.her] job today."), null);
					}
					
				} else if (index == 3) {

					int minutesPassed = (int) (Main.game.getMinutesPassed() % (24 * 60));
					sleepTimer = (Main.game.isDayTime()
							? (int) ((60 * 21) - minutesPassed)
							: (int) ((60 * (minutesPassed<(60*7)?7:31)) - minutesPassed));
					
					return new Response(
							"Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
							"Ask [npc.name] if you can crash on [npc.her] sofa for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
								+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
								+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
								+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
							OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
							Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
							Main.game.getPlayer().setLustNoText(0);
							if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
								Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8*60*60) + sleepTimer);
							} else {
								Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6*60*60) + sleepTimer);
							}
						}
					};
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasCompanion(occupant())) {
						
						if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
							return new Response("Add to party",
									UtilText.parse(occupant(), "[npc.Name] cannot be added to your party!"),
									null);
								
						} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
							return new Response("Add to party",
									UtilText.parse(occupant(), "Ask [npc.name] if [npc.she] would like to accompany you for a while."),
									OCCUPANT_APARTMENT){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().addCompanion(occupant());
								}
							};
							
						} else {
							return new Response("Add to party",
									"You are already at your party limit!",
									null);
						}
						
					} else {
						return new Response("Remove from party",
								"Command [npc.name] to leave your party.",
								OCCUPANT_APARTMENT){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getPlayer().removeCompanion(occupant());
							}
						};
					}
					
				} else if (index == 10) {
					if(confirmKickOut) {
						return new Response("Confirm removal", "Tell [npc.name] that you need to move on, and that you won't see [npc.herHim] ever again.<br/>"
								+ "[style.italicsBad(This will permanently remove [npc.name] from the game!)]",
								OCCUPANT_APARTMENT_REMOVE) {
							@Override
							public Colour getHighlightColour() {
								return Colour.GENERIC_BAD;
							}
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_REMOVE", occupant()));
								Main.game.getPlayer().removeFriendlyOccupant(occupant());
								Main.game.banishNPC(occupant());
								confirmKickOut = false;
							}
						};
						
					} else {
						return new ResponseEffectsOnly("Remove character", "Tell [npc.name] that you need to move on, and that you won't see [npc.herHim] ever again.<br/>"
								+ "[style.italicsMinorBad(After choosing this action, you'll need to click again to confirm that you want this character removed from the game forever.)]") {
							@Override
							public void effects() {
								confirmKickOut = true;
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVING", occupant()));
							applyReactionReset();
						}
					};
					
				} else {
					return OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab == 2) {
				switch(index) {
					case 1:
						return new Response("Inspect",
								"Inspect [npc.name].",
								OccupantManagementDialogue.getSlaveryManagementInspectSlaveDialogue(occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
					case 2:
						return new Response("Job", "You cannot manage the job of a free-willed occupant. This option is only available for slaves.", null);
						
					case 3:
						return new Response("Permissions", "You cannot manage the permissions of a free-willed occupant. This option is only available for slaves.", null);
						
					case 4:
						return new ResponseEffectsOnly("Inventory",
								UtilText.parse(occupant(), "As [npc.name] is indebted to you for having saved [npc.herHim] from a life of crime, [npc.she] will happily let you choose what [npc.she] wears.")) {
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
										Main.mainController.openInventory(occupant(), InventoryInteraction.FULL_MANAGEMENT);
									}
								};
					case 5:
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
							return new Response("Send to Kate",
									"[npc.Name] trusts you enough to have you decide upon any appearance changes at Kate's beauty salon, 'Succubi's secrets'.",
									OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
										@Override
										public void effects() {
											applyReactionReset();
											Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
											BodyChanging.setTarget(occupant());
										}
									};
						} else {
							return new Response("Send to Kate", "You haven't met Kate yet!", null);
						}
						
					case 6:
						return new Response("Perk Tree", "Assign [npc.namePos] perk points.", OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
						
					case 7:
						if(!occupant().isAbleToSelfTransform()) {
							return new Response("Transformations", occupant().getUnableToTransformDescription(), null);
							
						} else {
							return new Response("Transformations",
									"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(occupant());
								}
							};
						}
						
					case 11:
						return new Response("Combat Moves", "Adjust the moves [npc.name] can perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(occupant(), OCCUPANT_APARTMENT);
							}
						};
						
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
								
					default:
						return null;
				}
			
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_JOB", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return sleepTimer*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wake up", "You wake up some time later....", OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(!occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP_ALONE", occupant()));
			
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP", occupant()));
				UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			}
			
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if (index == 1) {
					return new Response("Outside", "You find yourself back outside in the streets of Dominion.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
			
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_REMOVE = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode APARTMENT_AFTER_SEX = new DialogueNode("Finish", "", true) {
		
		@Override
		public String getDescription(){
			return "Collapse down onto [npc.namePos] sofa and catch your breath.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));

			} else if(Sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	// MANAGEMENT DIALOGUES:
	
	
	public static final DialogueNode OCCUPANT_CHOOSE_NAME = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You decide to ask [npc.name] to call you by a different name."
						+ " At the moment, [npc.sheIs] calling you '[npc.pcName]'."
					+ "</p>"
					
					// TODO align this properly
					
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display: inline-block; padding:0 auto; margin:0 auto;vertical-align:middle;width:100%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:32px; line-height:32px;'>[npc.Name] will call you: </p>"
							+ "<form style='float:left; padding:auto 0 auto 0;'><input type='text' id='offspringPetNameInput' value='"+ UtilText.parseForHTMLDisplay(occupant().getPetName(Main.game.getPlayer()))+ "'></form>"
							+ " <div class='SM-button' id='"+occupant().getId()+"_PET_NAME' style='float:left; width:auto; height:28px;'>"
								+ "Rename"
							+ "</div>"
						+ "</div>"
						+ "<p>"
						+ "<i>The names 'Mom'/'Dad' and 'Mommy'/'Daddy' are special, and will automatically switch to the appropriate femininity of your character.</i>"
						+ "</p>"
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(occupant().getHomeWorldLocation()==WorldType.LILAYAS_HOUSE_GROUND_FLOOR || occupant().getHomeWorldLocation()==WorldType.LILAYAS_HOUSE_FIRST_FLOOR) {
				return OCCUPANT_START.getResponseTabTitle(index);
			} else {
				return OCCUPANT_APARTMENT.getResponseTabTitle(index);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (responseTab==2 && index == 8) {
				return new Response("Pet name", "You're already asking [npc.name] to call you by a different name.", null);
				
			} else {
				if(occupant().getHomeWorldLocation()==WorldType.LILAYAS_HOUSE_GROUND_FLOOR || occupant().getHomeWorldLocation()==WorldType.LILAYAS_HOUSE_FIRST_FLOOR) {
					return OCCUPANT_START.getResponse(responseTab, index);
				} else {
					return OCCUPANT_APARTMENT.getResponse(responseTab, index);
				}
			}
		}
	};
	
}
