package com.lilithsthrone.game.dialogue.companions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.3.5.1
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
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
		
		if(isApartment) {
			CompanionManagement.initManagement(OCCUPANT_APARTMENT, 2, targetedOccupant);
			
		} else if(targetedOccupant.isAtHome()) {
			CompanionManagement.initManagement(OCCUPANT_START, 2, targetedOccupant);
		}
		
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
	
	
	private static void exitDialogue() {
		Main.game.getDialogueFlags().setManagementCompanion(null);
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
						return new Response("Life", UtilText.parse(occupant(), "Ask [npc.name] about [npc.her] past life."), OCCUPANT_TALK_LIFE) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Life", UtilText.parse(occupant(), "You've already talked with [npc.name] about [npc.her] past life today."), null);
					}
					
				} else if (index == 2) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
						return new Response(hasJob()
									?"Job"
									:(occupant().getDesiredJobs().isEmpty()
										?"Unemployment"
										:"Job hunt"),
								UtilText.parse(occupant(),
										hasJob()
										?"Ask [npc.name] about [npc.her] job."
										:(occupant().getDesiredJobs().isEmpty()
											?"Ask [npc.name] if [npc.she] happy to remain unemployed."
											:"Ask [npc.name] how [npc.her] job hunt is going.")),
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
						return new Response("Lilaya", UtilText.parse(occupant(), "Ask [npc.name] about [npc.her] interactions with Lilaya and Rose."), OCCUPANT_TALK_LILAYA) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLilaya);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Lilaya", UtilText.parse(occupant(), "You've already talked with [npc.name] about [npc.her] interactions with Lilaya and Rose today."), null);
					}
					
				} else if (index == 4) {
					if(Main.game.getPlayer().getSlavesOwned().size()==0) {
						return new Response("Slaves", "You don't own any slaves, so there's nothing to discuss about this with [npc.name].", null);
						
					} else if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkSlaves)) {
						return new Response("Slaves", UtilText.parse(occupant(), "Ask [npc.name] about [npc.her] interactions with your slaves."), OCCUPANT_TALK_SLAVES) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkSlaves);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Slaves", UtilText.parse(occupant(), "You've already talked with [npc.name] about [npc.her] interactions with your slaves today."), null);
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
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
								UtilText.parse(occupant(), "Command [npc.name] to leave your party."),
								OCCUPANT_START){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getPlayer().removeCompanion(occupant());
							}
						};
					}
					
				} else if (index == 6) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantHugged)) {
						if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
							return new Response("Hug",
									UtilText.parse(occupant(),
											"[npc.Name] does not like you enough to want to give you a hug."
											+ "<br/>[style.italicsMinorBad(Requires [npc.name] to have an affection of at least '"+AffectionLevel.POSITIVE_THREE_CARING.getName()+"' towards you.)]"),
									null);
						}
						
						return new Response("Hug", UtilText.parse(occupant(), "Give [npc.name] a big hug."), OCCUPANT_HUG) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantHugged);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Hug", UtilText.parse(occupant(), "You've already hugged [npc.name] today."), null);
					}
					
				} else if (index == 7) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantPet)) {
						if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_THREE_CARING)) {
							return new Response("Pettings",
									UtilText.parse(occupant(),
											"[npc.Name] does not like you enough to allow you to pet [npc.herHim]."
											+ "<br/>[style.italicsMinorBad(Requires [npc.name] to have an affection of at least '"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"' towards you.)]"),
									null);
						}
						return new Response("Pettings", UtilText.parse(occupant(), "Give [npc.name] some loving pettings."), OCCUPANT_PETTINGS) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantPet);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Pettings", UtilText.parse(occupant(), "You've already given [npc.name] some pettings today."), null);
					}
					
				} else if (index == 10) {
					if(hasJob()) {
						return new Response("Move out",
								UtilText.parse(occupant(),
										"Tell [npc.name] that you think that [npc.her] idea of moving out would be good for [npc.herHim].<br/>"
										+ "[style.italics(You will have the option to save or remove this character in the next scene.)]"),
								OCCUPANT_MOVE_OUT) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
						
					} else {
						if(confirmKickOut) {
							return new Response("Confirm removal",
									UtilText.parse(occupant(),
											"Tell [npc.name] that you want [npc.herHim] to leave.<br/>"
											+ "[style.italicsBad(Permanently removes this character from the game.)]"),
									OCCUPANT_KICK_OUT) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.GENERIC_NPC_REMOVAL;
								}
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getDialogueFlags().setManagementCompanion(null);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_KICK_OUT", occupant()));
									Main.game.getPlayer().removeFriendlyOccupant(occupant());
									Main.game.banishNPC(occupant());
								}
							};
							
						} else {
							return new ResponseEffectsOnly("Kick out",
									UtilText.parse(occupant(),
											"Tell [npc.name] that you want [npc.herHim] to leave.<br/>"
												+ "[style.italicsMinorBad(After choosing this action, you'll need to click again to confirm that you want this character removed from the game forever.)]")) {
								@Override
								public void effects() {
									confirmKickOut = true;
								}
							};
						}
					}
					
				} else if (index == 11) {
					return new Response(
							hasJob()
								?"Suggest job change"
								:"Suggest job",
							UtilText.parse(occupant(),
									hasJob()
										?"Tell [npc.name] that you think [npc.she] should look for a new job."
										:"Tell [npc.name] what sort of job you think [npc.she] should be looking for."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};
					
				} else if(index==12 && occupant().hasJob()) {
					return new Response("[style.colourBad(Quit job)]",
							UtilText.parse(occupant(), "Tell [npc.name] to quit [npc.her] job as [npc.a_job], and to remain unemployed until you suggest something else for [npc.herHim]."),
							OCCUPANT_JOB_QUIT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_QUIT", occupant()));
							occupant().setHistory(Occupation.NPC_UNEMPLOYED);
							occupant().clearDesiredJobs();
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", UtilText.parse(occupant(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 1) {
					if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex", UtilText.parse(characterForSex, "[npc.Name] is not attracted to you..."), null);
						
					} else {
						return new ResponseSex("Sex",
								UtilText.parse(characterForSex, "Have sex with [npc.name]."), 
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(characterForSex),
										Main.game.getPlayer().getCompanions(),
										null) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
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
					if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex",
								UtilText.parse(characterForSex, "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom..."),
								null);
						
					} else {
						return new ResponseSex("Submissive sex",
								UtilText.parse(characterForSex, "Have submissive sex with [npc.name]."), 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								getAfterSexDialogue(),
								UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", characterForSex)) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
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
					return new Response("Leave", UtilText.parse(occupant(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVING", occupant()));
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
					
				} else  {
					return null;
				}
				
				
			} else if(responseTab == 2) {
				return CompanionManagement.getManagementResponses(index);
				
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
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
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
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
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
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
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
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
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

	public static final DialogueNode OCCUPANT_HUG = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Hugging [npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_HUG", occupant()));
			
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

	public static final DialogueNode OCCUPANT_PETTINGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(), "Petting [npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_PETTINGS", occupant()));
			
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

	public static final DialogueNode OCCUPANT_JOB_SUGGESTION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_CHANGE", occupant()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION", occupant()));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_MECHANICS", occupant()));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Occupation> availableOccuaptions = new ArrayList<>();
			for(Occupation occ : Occupation.values()) {
				if(!occ.isAvailableToPlayer()
						&& occ.isAvailable(occupant())
						&& occ!=Occupation.NPC_UNEMPLOYED
						&& !occ.isLowlife()) {
					availableOccuaptions.add(occ);
				}
			}
			
			if(index==0) {
				return new Response("Back",
						UtilText.parse(occupant(), "Decide against telling [npc.name] what job you think [npc.she] should have."),
						isApartment
							?OCCUPANT_APARTMENT
							:OCCUPANT_START);
				
			} else if(index==1) {
				return new Response("[style.colourGood(Select all)]",
						UtilText.parse(occupant(), "Select all job types for [npc.name] to search for."),
						OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						for(Occupation occ : availableOccuaptions) {
							occupant().addDesiredJob(occ);
						}
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourBad(Select none)]",
						occupant().hasJob()
							?UtilText.parse(occupant(), "Select no job types for [npc.name] to search for, causing [npc.herHim] to remain employed as [npc.a_job].")
							:UtilText.parse(occupant(), "Select no job types for [npc.name] to search for, causing [npc.herHim] to remain unemployed."),
							OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						occupant().clearDesiredJobs();
					}
				};
				
			} else if(index-3<availableOccuaptions.size()) {
				Occupation job = availableOccuaptions.get(index-3);
				String jobName = job.getName(occupant());
				if(occupant().getHistory().equals(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "[npc.Name] is already employed as "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							null);
					
				} else if(occupant().getDesiredJobs().contains(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "Tell [npc.name] to stop looking for a job as "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							occupant().removeDesiredJob(job);
						}
					};
					
				} else {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "Tell [npc.name] that [npc.she] should look for a job as "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							occupant().addDesiredJob(job);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OCCUPANT_JOB_QUIT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Talking with [npc.Name]");
		}

		@Override
		public String getContent() {
			return "";
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
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));
				
			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogue(false)) {
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
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
						OccupantDialogue.isApartment = true;
						Main.game.getDialogueFlags().setManagementCompanion(occupant());
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
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
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

	private static int sleepTimeInMinutes = 240;
	
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
			if(occupant().isAtHome()) {
				if(index == 0) {
					return "Talk";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(Sex)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(Manage)]");
				}
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if(index==1) {
					return new Response("Leave",
							UtilText.parse(occupant(), "As [npc.name] is not at home right now, there's nothing left to do but head back out into Dominion."),
							Main.game.getDefaultDialogue(false));
				}
				return null;
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
					
				}
				else if (index == 3) {
					return new Response("Rest",
							"Ask [npc.name] if you can crash on [npc.her] sofa for four hours."
							+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
							OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = 240;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 4) {
					int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
					LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
					return new Response("Rest until " + (Main.game.isDayTime() ? "Sunset" : "Sunrise"),
							"Ask [npc.name] if you can crash on [npc.her] sofa for " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " hours " : " ")
								+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " minutes" : "")
								+ (Main.game.isDayTime()
										? " until five minutes past sunset ("+Units.time(sunriseSunset[1].plusMinutes(5))+")."
										: " until five minutes past sunrise ("+Units.time(sunriseSunset[0].plusMinutes(5))+").")
								+ " As well as replenishing your "+Attribute.HEALTH_MAXIMUM.getName()+" and "+Attribute.MANA_MAXIMUM.getName()+", you will also get the 'Well Rested' status effect.",
								OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilChange;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
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
					
				} else if (index == 6) {
					return new Response(
							hasJob()
								?"Suggest job change"
								:"Suggest job",
							UtilText.parse(occupant(),
									hasJob()
										?"Tell [npc.name] that you think [npc.she] should look for a new job."
										:"Tell [npc.name] what sort of job you think [npc.she] should be looking for."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};

				} else if (index == 8) {
					return new Response("Set alarm", "Set the alarm on your phone, so that you can wake at a specific time.", RoomPlayer.ROOM_SET_ALARM) {
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
						}
					};

				} else if (index == 9) {
					long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
					if(alarmTime >= 0) {
						String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
						int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime-1)+1; // -1+1 is so we get 1440 instead of 0
						return new Response("Rest until alarm (" + alarmTimeStr + ")",
								"Ask [npc.name] if you can crash on [npc.her] sofa for " + (timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + " hours, " : "")
										+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + " minutes, " : "")
										+ " until your alarm goes off. As well as replenishing your " + Attribute.HEALTH_MAXIMUM.getName() + " and " + Attribute.MANA_MAXIMUM.getName() + ", you will also get the 'Well Rested' status effect.",
								OCCUPANT_APARTMENT_SLEEP_OVER) {
							@Override
							public void effects() {
								sleepTimeInMinutes = timeUntilAlarm;
								RoomPlayer.applySleep(sleepTimeInMinutes);
							}
						};
					} else {
						return new Response("Rest until alarm (unset)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Your alarm is unset!</span>", null);
					}
					
				} else if (index == 10) {
					if(confirmKickOut) {
						return new Response("Confirm removal", UtilText.parse(occupant(), "Tell [npc.name] that you need to move on, and that you won't see [npc.herHim] ever again.<br/>"
								+ "[style.italicsBad(This will permanently remove [npc.name] from the game!)]"),
								OCCUPANT_APARTMENT_REMOVE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
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
						return new ResponseEffectsOnly("Remove character", UtilText.parse(occupant(), "Tell [npc.name] that you need to move on, and that you won't see [npc.herHim] ever again.<br/>"
								+ "[style.italicsMinorBad(After choosing this action, you'll need to click again to confirm that you want this character removed from the game forever.)]")) {
							@Override
							public void effects() {
								confirmKickOut = true;
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Leave", UtilText.parse(occupant(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 0) {
					return new Response("Leave", UtilText.parse(occupant(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVING", occupant()));
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab == 2) {
				return CompanionManagement.getManagementResponses(index);
				
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

//		@Override
//		public int getSecondsPassed() {
//			return sleepTimeInMinutes*60;
//		}
		
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
					return new Response("Outside", "You find yourself back outside in the streets of Dominion.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							exitDialogue();
						}
					};
				}
				return null;
			}
			
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_REMOVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			exitDialogue();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode APARTMENT_AFTER_SEX = new DialogueNode("Finish", "", true) {
		
		@Override
		public String getDescription(){
			return "Collapse down onto [npc.namePos] sofa and catch your breath.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));

			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVE_AFTER_SEX", occupant()));
						exitDialogue();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
