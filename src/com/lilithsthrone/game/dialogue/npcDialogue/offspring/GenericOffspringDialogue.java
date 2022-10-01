package com.lilithsthrone.game.dialogue.npcDialogue.offspring;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8?
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericOffspringDialogue {
	
	private static NPCOffspring offspring() {
		return (NPCOffspring) Main.game.getActiveNPC();
	}
	
	private static String getOffspringLabel() {
		if(offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
			return UtilText.parse(offspring(), "Talking to [npc.Name]");
		} else {
			return "A familiar face";
		}
	}
	
	private static String getTextFilePath() {
		return offspring().getWorldLocation().getOffspringTextFilePath(offspring());
	}
	

	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(offspring().getPlayerRelationStatusDescription());
		
		// Time limitation:
		sb.append("<p style='text-align:center;'><i>");
		int tokens = Main.game.getDialogueFlags().offspringDialogueTokens;
		if(tokens>=1) {
			sb.append("[npc.Name] only has time for [style.italicsMinorBad("+Util.intToString(tokens)+" more thing"+(tokens>1?"s":"")+")] to discuss.");
		} else {
			sb.append("[npc.Name] [style.italicsBad(doesn't have any time left)], and needs to get back to work.");
		}
		sb.append("</i></p>");
		
		return UtilText.parse(offspring(), sb.toString());
	}
	
	public static final DialogueNode OFFSPRING_ENCOUNTER = new DialogueNode("", "You encounter someone who looks very familiar...", true) {
		@Override
		public void applyPreParsingEffects(){
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).isEmpty()) {
				Main.game.initOffspringEncounter(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlaceType());
			}
			List<GameCharacter> offspringList = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			offspringList.removeIf(c->!c.isRelatedTo(Main.game.getPlayer()));
			Main.game.setActiveNPC((NPC) offspringList.get(0));
			
			if(Main.game.getPlayer().getWorldLocation()==WorldType.BAT_CAVERNS) { // If offspring is in the bat caverns, they are a mushroom hunter
				if(offspring().getItemCount(ItemType.MUSHROOM)<5) {
					offspring().addItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), 5+Util.random.nextInt(10), false, false);
					offspring().setOccupation(Occupation.NPC_MUSHROOM_FORAGER);
				}
			}
		}
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(offspring().getAffection(Main.game.getPlayer()) < AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
				if (index == 1) {
					return new Response("Apologise", UtilText.parse(offspring(), "Apologise to [npc.name]."), OFFSPRING_ENCOUNTER_APOLOGY) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) && !offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else {
								if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
								if(offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 8) {
					return new Response("Sex", UtilText.parse(offspring(), "There's no way [npc.she]'ll consider having sex with you when [npc.sheIs] this angry."), null) {
						@Override
						public void effects() {
							setOffspringFlags();
						}
					};
					
				} else if (index == 10) {
					return new Response("Attack", UtilText.parse(offspring(), "How dare [npc.name] talk to you like that! It's time to show [npc.herHim] [npc.her] place!"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", UtilText.parse(offspring(), "Tell [npc.name] that you'll come back some other time."), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								setOffspringFlags();
								return Main.game.getDefaultDialogue(false);
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Greeting", UtilText.parse(offspring(), "Say hello to [npc.name]."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_GREETING", offspring()));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					return new Response("Hug", UtilText.parse(offspring(), "Hug [npc.name]."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_HUG", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 3) {
					return new Response("Kiss", UtilText.parse(offspring(), "Give [npc.name] a hug and a kiss."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_KISS", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 4 && Main.game.isIncestEnabled()) {
					return new Response("Passionate kiss", UtilText.parse(offspring(), "Passionately kiss [npc.name] on the lips, and feel [npc.herHim] up as you do so."), OFFSPRING_ENCOUNTER_TALKING,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FOUR_LUSTFUL,
							null,
							null,
							null) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_PASSIONATE_KISS", offspring()));
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								
							} else if(
									((Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.GYNEPHILIC)
									|| (!Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.ANDROPHILIC)
									|| (offspring().getSexualOrientation()==SexualOrientation.AMBIPHILIC))
									|| offspring().hasFetish(Fetish.FETISH_INCEST)) {
								//Incest fetish and not attracted to player, or attracted to player and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
								
							} else {
								//Not attracted to player, and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -20));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} if (index == 5) {
					return new Response("Scold [npc.herHim]",
							UtilText.parse(offspring(), "Ask [npc.name] just what [npc.she] thinks [npc.sheIs] doing!"
									+(offspring().getHistory()==Occupation.NPC_PROSTITUTE
											?" (This will voice disapproval about [npc.herHim] being a prostitute.)"
											:" (This will voice disapproval about [npc.herHim] being a mugger.)")),
							OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLDING", offspring()));
							// If masochist fetish, +5, if not, -5:
							if(offspring().hasFetish(Fetish.FETISH_MASOCHIST)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5)); 
							} else {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 10) {
					return new Response("Attack", UtilText.parse(offspring(), "How dare [npc.name] talk to you like that! It's time to show [npc.herHim] [npc.her] place!"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0 && offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
					return new Response("Leave", UtilText.parse(offspring(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_LEAVE", offspring()));
								
								setOffspringFlags();
							}	
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void setOffspringFlags() {
		if(offspring().isVisiblyPregnant()) {
			offspring().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(offspring(), true);
		}
		offspring().setFlag(NPCFlagValue.flagOffspringIntroduced, true);
		Main.game.getDialogueFlags().offspringDialogueTokens = 2;
	}
	
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APOLOGY = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APOLOGY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Give [npc.name] some time to think, and continue on your way.", OFFSPRING_ENCOUNTER_APOLOGY) {
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"You find yourself fighting your [npc.daughter]!",
						offspring());
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_TALKING = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING", offspring()));

			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().offspringDialogueTokens<=0) {
				if (index == 1) {
					return new Response("Time to go", UtilText.parse(offspring(), "[npc.Name] has started glancing at the clock on the wall, giving you a clear indication that it's time to make your exit."), OFFSPRING_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_OUT_OF_TIME", offspring()));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Background", UtilText.parse(offspring(), "Ask [npc.name] about [npc.her] background, and about what [npc.she] does for a living."), OFFSPRING_ENCOUNTER_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 2) {
					return new Response("Small talk", UtilText.parse(offspring(), "Chat about this and that with [npc.name]."), OFFSPRING_ENCOUNTER_SMALL_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 3) {
					return new Response("Encourage", UtilText.parse(offspring(), "Encourage [npc.name] to do [npc.her] best."), OFFSPRING_ENCOUNTER_ENCOURAGE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 4) {
					return new Response("Scold", UtilText.parse(offspring(), "Scold [npc.name], and tell [npc.herHim] to better [npc.herself]."), OFFSPRING_ENCOUNTER_SCOLD) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !offspring().isAffectionHighEnoughToInviteHome()) {
						return new Response("Offer room",
								UtilText.parse(offspring(), "You feel as though it would be best to spend some more time getting to know [npc.name] before inviting [npc.herHim] back to Lilaya's mansion...<br/>"
								+ "[style.italics(Requires [npc.name] to have at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" affection towards you.)]"),
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Offer room",
								UtilText.parse(offspring(), "You'll need to get Lilaya's permission before inviting [npc.name] back to her mansion..."),
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Offer room",
								UtilText.parse(offspring(), "You don't have a suitable room prepared for [npc.name] to move in to. Upgrade one of the empty rooms in Lilaya's house to a 'Guest Room' first."),
								null);
						
					} else {
						return new Response("Offer room", UtilText.parse(offspring(), "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion."), OFFSPRING_OFFER_ROOM) {
							@Override
							public void effects() {
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								if(offspring().getAffection(Main.game.getPlayer())<0) {
									Main.game.getTextEndStringBuilder().append(offspring().setAffection(Main.game.getPlayer(), 25));
								} else {
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								}
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								offspring().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							}
						};
					}

				} else if (index == 6) {
					return new Response("Pet name", UtilText.parse(offspring(), "Ask [npc.name] to call you by a different name."), OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				}  else if (index == 7) {
					if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
						return new Response("Give Present", UtilText.parse(offspring(), "Give [npc.name] the present that you're carrying."), OFFSPRING_PRESENT) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 15));
								
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								Main.game.getDialogueFlags().offspringDialogueTokens--;
							}
						};
					} else {
						return null;
					}
					
				} else if (index == 8 && Main.game.isIncestEnabled()) {
					return new Response("Sex", UtilText.parse(offspring(), "Tell [npc.name] that you want to have sex with [npc.herHim]."), OFFSPRING_ENCOUNTER_SEX,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else if(offspring().getHistory()!=Occupation.NPC_PROSTITUTE){
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							}
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}	
					};
					
				} else if (index == 10) {
					return new Response("Attack", UtilText.parse(offspring(), "It's time to show [npc.herHim] [npc.her] true place in this family!"), OFFSPRING_ENCOUNTER_APARTMENT_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
					};
					
				} else if (index == 11) {
					if(!offspring().isAllowingPlayerToManageInventory()) {
						return new Response("Inventory", UtilText.parse(offspring(), "[npc.Name] doesn't like you enough to allow you to choose what [npc.she] wears, or what [npc.she] eats and drinks."), null);
					} else {
						return new ResponseEffectsOnly("Inventory", UtilText.parse(offspring(), "Manage [npc.namePos] inventory.")) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_INVENTORY", offspring()));
								Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Leave", UtilText.parse(offspring(), "Tell [npc.name] that you'll catch up with [npc.herHim] some other time."), OFFSPRING_ENCOUNTER) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_LEAVE", offspring()));
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							}
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_BACKGROUND = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() { // TODO use offspring().flagBackgroundProgress
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_BACKGROUND", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Background", "You've just talked about [npc.namePos] background.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SMALL_TALK = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SMALL_TALK", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Small talk", "You've just had some small talk with [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_ENCOURAGE = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_ENCOURAGE", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Encourage", "You've just encouraged [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SCOLD = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLD", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Scold", "You've just scolded [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM = new DialogueNode("Offer room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Show to room", "Take [npc.name] to [npc.her] new room.", OFFSPRING_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						offspring().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(offspring());
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM_BACK_HOME = new DialogueNode("New Room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM_BACK_HOME", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Give [npc.name] some time to get settled in [npc.her] new room. You can come back at any time to talk with [npc.herHim].", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

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
							+ "<form style='float:left; padding:auto 0 auto 0;'><input type='text' id='offspringPetNameInput' value='"+ UtilText.parseForHTMLDisplay(offspring().getPetName(Main.game.getPlayer()))+ "'></form>"
							+ " <div class='SM-button' id='"+offspring().getId()+"_PET_NAME' style='float:left; width:auto; height:28px;'>"
								+ "Rename"
							+ "</div>"
						+ "</div>"
						+ "<p>"
						+ "<i>The names 'Mom'/'Dad' and 'Mommy'/'Daddy' are special, and will automatically switch to the appropriate femininity of your character.</i>"
						+ "</p>"
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Pet name", "You're already asking [npc.name] to call you by a different name.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_PRESENT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_PRESENT", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Give Present", "You're already giving [npc.name] a present.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SEX = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SEX", offspring()));

//			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Incestuous sex",
							"It's time to show your [npc.daughter] what [npc.her] [pc.mother] can do!",
							true, true,
							new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(offspring()),
								null,
								null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else if (index == 2) {
					return new ResponseSex("Submissive sex",
							"It's time to let your [npc.daughter] show you what [npc.she] can do!",
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else {
					return null;
				}
				
			} else if(offspring().getHistory()==Occupation.NPC_PROSTITUTE){
				if (index == 8) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Incestuous sex ("+UtilText.formatAsMoney(100, "span")+")",
								"Pay your [npc.daughter] 100 flames to get what you want!",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(offspring()),
										null,
										null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("Pay "+UtilText.formatAsMoneyUncoloured(100, "span"), "You don't have enough money...", null);
					}
					
				} else if (index == 9) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Submissive sex ("+UtilText.formatAsMoney(100, "span")+")",
								"Pay your [npc.daughter] 100 flames to get what you want!",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("Pay "+UtilText.formatAsMoneyUncoloured(100, "span"), "You don't have enough money...", null);
					}
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
				
				
			} else {
				if (index == 8 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
					return new Response("Sex", "You've just asked [npc.name] to have sex with you...", null);
					
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
			}
			
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APARTMENT_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APARTMENT_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting your [npc.daughter]!", offspring());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index == 1) {
				return new Response("Apologise", "Maybe you went too far... Perhaps you should apologise?", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_APOLOGISE", offspring()));
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
						offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
					}
				};
				
			} else if (index == 2 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, Fetish.FETISH_INCEST.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX", offspring()));
					
				} else {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 3 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_GENTLE", offspring()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_GENTLE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 4 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_ROUGH", offspring()));
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)",
							"[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_ROUGH", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 5 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...<br/>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SUBMIT", offspring())) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
				} else {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.sheHasFull] no interest in having sex with you!",
							null);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						UtilText.parse(offspring(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						AFTER_COMBAT_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Now that you've taught [npc.name] a lesson, you can be on your way...", AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_LEAVE", offspring()));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {

		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "You're left to continue on your way...", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue(){
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_CONSENSUAL = new DialogueNode("Post-sex", "", true) {
		
		@Override
		public String getDescription(){
			return "You've satisfied your lust for your [npc.daughter]... For now...";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_CONSENSUAL", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_CONSENSUAL){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_LEAVING", offspring()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BACK_ALLEYS)) {
				return new Response(
						"Remove character",
						UtilText.parse(offspring(), "Scare [npc.name] away."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
						AFTER_COMBAT_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_DEFEAT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
