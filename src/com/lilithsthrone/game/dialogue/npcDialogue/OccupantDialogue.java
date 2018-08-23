package com.lilithsthrone.game.dialogue.npcDialogue;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Innoxia
 */
public class OccupantDialogue {
	
	private static NPC occupant() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean hasJob() {
		return !occupant().getHistory().isLowlife();
	}
	
	private static String getFooterText() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		AffectionLevel al = occupant().getAffectionLevel(Main.game.getPlayer());
		switch(al) {
			case NEGATIVE_FIVE_LOATHE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("Even though [npc.name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>loathe</i> you, you can tell that [npc.sheIs] still attracted to you.");
				} else {
					sb.append("[npc.Name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>loathe</i> you.");
				}
				break;
			case NEGATIVE_FOUR_HATE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("Even though [npc.name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>hate</i> you, you can tell that [npc.sheIs] still attracted to you.");
				} else {
					sb.append("[npc.Name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>hate</i> you.");
				}
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("Even though [npc.name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>strongly dislike</i> you, you can tell that [npc.sheIs] still attracted to you.");
				} else {
					sb.append("[npc.Name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>strongly dislike</i> you.");
				}
				break;
			case NEGATIVE_TWO_DISLIKE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("Even though [npc.name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>dislike</i> you, you can tell that [npc.sheIs] still attracted to you.");
				} else {
					sb.append("[npc.Name] seems to <i style='color:"+al.getColour().toWebHexString()+";'>dislike</i> you.");
				}
				break;
			case NEGATIVE_ONE_ANNOYED:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("Even though [npc.name] seems to be <i style='color:"+al.getColour().toWebHexString()+";'>annoyed</i> with you, you can tell that [npc.sheIs] still attracted to you.");
				} else {
					sb.append("[npc.Name] seems to be <i style='color:"+al.getColour().toWebHexString()+";'>annoyed</i> with you.");
				}
				break;
			case ZERO_NEUTRAL:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] is acting in an <i style='color:"+al.getColour().toWebHexString()+";'>amicable, flirtatious</i> manner towards you.");
				} else {
					sb.append("[npc.Name] is acting in an <i style='color:"+al.getColour().toWebHexString()+";'>amicable</i> manner towards you.");
				}
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly, flirtatious</i> manner towards you.");
				} else {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly</i> manner towards you.");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as more than just a friend.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as a close friend.");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and is deeply attracted towards you.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and considers you to be [npc.her] best friend.");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("You can tell from the way that [npc.she] looks at you that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i>.");
				} else {
					sb.append("You can tell that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i> in a purely platonic manner.");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(occupant().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and is head-over-heels in love with you.");
				} else {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and would do almost anything you asked of [npc.herHim].");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(occupant(), sb.toString());
	}
	
	private static void applyReactionReset() {
		if(occupant().isVisiblyPregnant()){
			occupant().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(occupant(), true);
		}
		occupant().removeFlag(NPCFlagValue.occupantHasNewJob);
	}
	
	//TODO most important: add hooks to dominion tiles and set active character
	
	public static final DialogueNodeOld OCCUPANT_START = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START", occupant()));
			
			if(occupant().isVisiblyPregnant()) {
				if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_PREGNANCY_REVEAL", occupant()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_STILL_PREGNANT", occupant()));
				}
			}

			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_PLAYER_PREGNANCY", occupant()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
				}
			}
			
			if(occupant().hasFlag(NPCFlagValue.occupantHasNewJob)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_FINISH_WITH_NEW_JOB", occupant()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_START_FINISH", occupant()));
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
						return new Response("Kick out", "Tell [npc.name] that you want [npc.herHim] to leave.<br/>"
								+ "[style.italicsBad(Removes this character from the game.)]",
								OCCUPANT_KICK_OUT) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_KICK_OUT", occupant()));
								Main.game.getPlayer().removeFriendlyOccupant(occupant());
								Main.game.banishNPC(occupant());
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
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
				List<GameCharacter> companions = Main.game.getPlayer().getCompanions();
				
				if (index == 1) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex", UtilText.parse(occupant(), "[npc.Name] is not attracted to you..."), null);
						
					} else {
						return new ResponseSex("Sex", "Have sex with [npc.name].", 
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(occupant(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX,
								UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
					}
					
				} else if (index == 2) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
						
					} else {
						return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(occupant(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX,
								UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_AS_SUB_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					}
					
				} else if (index == 3) {
					if(!companions.isEmpty()) {
						if(!companions.get(0).isAttractedTo(Main.game.getPlayer()) && !occupant().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted", UtilText.parse(companions.get(0), occupant(), "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
							
						} else {
							return new ResponseSex("Spitroasted",
									UtilText.parse(companions.get(0), occupant(), "Let [npc.name] and [npc2.name] spitroast you."),
									null, null, null, null, null, null,
									true, true,
									new SMDoggy(
											Util.newHashMapOfValues(
													new Value<>(companions.get(0), SexPositionSlot.DOGGY_INFRONT),
													new Value<>(occupant(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_SPITROASTED_START", companions.get(0), occupant())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
						
					} else {
						return new Response("Spitroasted", "You'd need to bring someone along with you in order to get spitroasted...", null);
					}
				
				} else if (index == 4) {
					if(!companions.isEmpty()) {
						if(!companions.get(0).isAttractedTo(Main.game.getPlayer()) && !occupant().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Side-by-side", UtilText.parse(companions.get(0), occupant(), "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
							
						} else {
							return new ResponseSex("Side-by-side",
									UtilText.parse(companions.get(0), occupant(), "Push [npc1.name] and [npc2.name] down onto all fours and get ready to fuck them side-by-side."),
									null, null, null, null, null, null,
									true, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(companions.get(0), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(occupant(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_SIDE_BY_SIDE_START", companions.get(0), occupant())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
						
					} else {
						return new Response("Side-by-side", UtilText.parse( occupant(), "You'd need to bring someone along with you in order to fuck both them and [npc.name] at once..."), null);
					}
				
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "LEAVING", occupant()));
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
						return new Response("Perks", "Assign [npc.namePos] perk points.", OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
						
					case 7:
						if(!occupant().isAbleToSelfTransform()) {
							return new Response("Transformations", "Only demons and slimes can transform themselves on command...", null);
							
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
						
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
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
	
	public static final DialogueNodeOld OCCUPANT_TALK_LIFE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about life, family, friends, stories
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_TALK_JOB = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
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
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_JOB", occupant()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_JOB_HUNTING", occupant()));
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_TALK_LILAYA = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about Lilaya and Rose
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_LILAYA", occupant()));
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_TALK_SLAVES = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			String id = Util.randomItemFrom(Main.game.getPlayer().getSlavesOwned());
			NPC slave = (NPC) Main.game.getNPCById(id);
			
			if(slave!=null) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_SLAVES", occupant(), slave));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_TALK_SLAVES_NULL_SLAVE", occupant()));
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Finish", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Collapse down onto [npc.namePos] sofa and catch your breath.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));
				
			} else if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OCCUPANT_KICK_OUT = new DialogueNodeOld("Kicking out", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld OCCUPANT_MOVE_OUT = new DialogueNodeOld("Moving out", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_MOVE_OUT", occupant());
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
				return new Response("Remove character", "Tell [npc.name] that the [npc.she] should move on with [npc.her] life.<br/>"
						+ "[style.italicsBad(Removes this character from the game.)]",
						OCCUPANT_KICK_OUT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_MOVE_OUT_REMOVE_CHARACTER", occupant()));
						Main.game.getPlayer().removeFriendlyOccupant(occupant());
						Main.game.banishNPC(occupant());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OCCUPANT_MOVE_OUT_APARTMENT = new DialogueNodeOld("Moving out", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 60;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos] apartment");
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_MOVE_OUT_APARTMENT", occupant());
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
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT = new DialogueNodeOld("Moving out", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos] Apartment");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START", occupant()));
				
				if(occupant().isVisiblyPregnant() && occupant().isCharacterPossiblyFather(Main.game.getPlayer().getId())) {
					if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START_PREGNANCY_REVEAL", occupant()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START_STILL_PREGNANT", occupant()));
					}
				}
	
				if(Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(occupant().getId())) {
					if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START_PLAYER_PREGNANCY", occupant()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
					}
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_START_FINISH", occupant()));
				
				UtilText.nodeContentSB.append(getFooterText());
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_NOT_AT_HOME", occupant()));
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
					return new Response("Leave", "As [npc.name] is not at home right now, there's nothing left to do but head back out into Dominion.", Main.game.getDefaultDialogue());
					
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
					
					return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
							"Ask [npc.name] if you can crash on [npc.her] sofa for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
							+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
							+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
							+ " As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
							Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
							Main.game.getPlayer().setLust(0);
							if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
								Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8 * 60) + sleepTimer);
							} else {
								Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + sleepTimer);
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
					return new Response("Remove Character", "Tell [npc.name] that you need to move on, and that you won't see [npc.herHim] ever again.<br/>"
							+ "[style.italicsBad(This will remove [npc.name] from the game!)]",
							OCCUPANT_APARTMENT_REMOVE) {
						@Override
						public void effects() {
							applyReactionReset();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_REMOVE", occupant()));
							Main.game.getPlayer().removeFriendlyOccupant(occupant());
							Main.game.banishNPC(occupant());
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				List<GameCharacter> companions = Main.game.getPlayer().getCompanions();
				
				if (index == 1) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex", UtilText.parse(occupant(), "[npc.Name] is not attracted to you..."), null);
						
					} else {
						return new ResponseSex("Sex", "Have sex with [npc.name].", 
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(occupant(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								APARTMENT_AFTER_SEX,
								UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_APARTMENT_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
					}
					
				} else if (index == 2) {
					if(!occupant().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
						
					} else {
						return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(occupant(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								APARTMENT_AFTER_SEX,
								UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_APARTMENT_AS_SUB_START", occupant())) {
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					}
					
				} else if (index == 3) {
					if(!companions.isEmpty()) {
						if(!companions.get(0).isAttractedTo(Main.game.getPlayer()) && !occupant().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted", UtilText.parse(companions.get(0), occupant(), "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
							
						} else {
							return new ResponseSex("Spitroasted",
									UtilText.parse(companions.get(0), occupant(), "Let [npc.name] and [npc2.name] spitroast you."),
									null, null, null, null, null, null,
									true, true,
									new SMDoggy(
											Util.newHashMapOfValues(
													new Value<>(companions.get(0), SexPositionSlot.DOGGY_INFRONT),
													new Value<>(occupant(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
									null,
									APARTMENT_AFTER_SEX,
									UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_APARTMENT_SPITROASTED_START", companions.get(0), occupant())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
						
					} else {
						return new Response("Spitroasted", "You'd need to bring someone along with you in order to get spitroasted...", null);
					}
				
				} else if (index == 4) {
					if(!companions.isEmpty()) {
						if(!companions.get(0).isAttractedTo(Main.game.getPlayer()) && !occupant().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Side-by-side", UtilText.parse(companions.get(0), occupant(), "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
							
						} else {
							return new ResponseSex("Side-by-side",
									UtilText.parse(companions.get(0), occupant(), "Push [npc1.name] and [npc2.name] down onto all fours and get ready to fuck them side-by-side."),
									null, null, null, null, null, null,
									true, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(companions.get(0), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(occupant(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									null,
									APARTMENT_AFTER_SEX,
									UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "SEX_APARTMENT_SIDE_BY_SIDE_START", companions.get(0), occupant())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
						
					} else {
						return new Response("Side-by-side", UtilText.parse( occupant(), "You'd need to bring someone along with you in order to fuck both them and [npc.name] at once..."), null);
					}
				
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "APARTMENT_LEAVING", occupant()));
							applyReactionReset();
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
						return new Response("Perks", "Assign [npc.namePos] perk points.", OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant());
							}
						};
						
					case 7:
						if(!occupant().isAbleToSelfTransform()) {
							return new Response("Transformations", "Only demons and slimes can transform themselves on command...", null);
							
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
						
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogue()) {
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
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT_TALK_LIFE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT_TALK_JOB = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_TALK_JOB", occupant()));
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT_SLEEP_OVER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return sleepTimer;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_SLEEP_OVER", occupant()));
			
			UtilText.nodeContentSB.append(getFooterText());
			
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
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(!occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP_ALONE", occupant()));
			
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP", occupant()));
				UtilText.nodeContentSB.append(getFooterText());
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
					return new Response("Outside", "You find yourself back outside in the streets of Dominion.", Main.game.getDefaultDialogue());
					
				} else {
					return null;
				}
			}
			
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld OCCUPANT_APARTMENT_REMOVE = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld APARTMENT_AFTER_SEX = new DialogueNodeOld("Finish", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Collapse down onto [npc.namePos] sofa and catch your breath.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "APARTMENT_AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));
				
			} else if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "APARTMENT_AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "APARTMENT_AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Give [npc.name] some time to rest.", Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/friendlyOccupantDialogue", "APARTMENT_LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
