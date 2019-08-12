package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.SMDaddyDinnerOral;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexSlotOther;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DaddyDialogue {

	private static boolean isLilayaPresent() {
		return Main.game.getCharactersPresent().contains(Main.game.getNpc(Lilaya.class));
	}
	
	private static String getDialoguePrefix() {
		if(isLilayaPresent()) {
			return "LILAYA_";
		}
		return "";
	}
	
	private static void completeQuest() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.SIDE_UTIL_COMPLETE));
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.getSpellBookType(Spell.TELEKENETIC_SHOWER)), false));
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.getSpellScrollType(SpellSchool.EARTH)), 5, false));
	}
	
	// Util place dialogues:
	
	public static final DialogueNode PLACE_ENTRANCE_HALL = new DialogueNode("Entrance Hall", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_LOUNGE = new DialogueNode("Lounge", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_KITCHEN = new DialogueNode("Kitchen", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_BEDROOM = new DialogueNode("Bedroom", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BLANK_DEFAULT_DIALOGUE = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			return Main.game.getDefaultDialogueNoEncounter().getLabel();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CONVINCING_LILAYA = new DialogueNode("Bedroom", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "CONVINCING_LILAYA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Agree", "Agree to wait for Lilaya to change into her dress, and then set out for [daddy.namePos] place in Demon Home.", MEETING) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
						
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));
					}
				};
			}
			return null;
		}
	};
	
	// Main dialogues:
	
	public static final DialogueNode FIRST_ENCOUNTER = new DialogueNode("An unwelcome guest", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Correct him", "Tell [daddy.name] that you are in fact related to Lilaya, and that you live here with her.", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_ANSWER"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));
						
						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						
						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Push past", "Refuse to answer this impudent incubus's question, and simply march straight past him and enter Lilaya's house.", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_PUSH_PAST"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));

						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.mainController.moveGameWorld(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, true);

						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
					}
				};
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode MEETING = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Meeting [daddy.name]";
		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) { //TODO if refused like this, make it so the only option is agree/refuse, then proceed with regular options.
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED_AT_DINNER");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rudeToDaddy)) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RUDE");
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING");
				
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) { // First dinner:
				if(index==1) {
					return new Response("Agree",
							"It might be a good idea to get to know why [daddy.name] wants to meet Lilaya so badly before deciding upon what to tell [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 5));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==2) {
					return new Response("Agree (flirt)",
							"A date with [daddy.name] sounds like the perfect way to spend your evening!",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 10));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==3) {
					boolean returning = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
					return new Response(
							"Refuse",
							returning
								? "Tell [daddy.name] that you've changed your mind, and don't want to go out for dinner with [daddy.herHim] after all.<br/>"
									+ "[style.italicsMinorBad(You can still change your mind and return tomorrow or at any other later time.)]"
								: "Refuse to go to dinner with [daddy.name], and tell him never to bother Lilaya again.<br/>"
									+ "[style.italicsMinorBad(This will complete the quest, '"+QuestLine.SIDE_DADDY.getName()+"', but you can still return and restart it at any time.)]",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							if(!returning) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE"));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE_AFTER_RETURN"));	
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) {
				if(index==1) {
					return new Response("Accept",
							"Tell [daddy.name] that you're willing to call [daddy.herHim] [daddy.mommy], and to convince Lilaya to come and meet [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 15));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getPlayer().setPetName(Main.game.getNpc(Daddy.class), "daddy");
							
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_ACCEPTED));
						}
					};
					
				} else if(index==2) {
					return new Response("Accept (flirt)",
							"Flirtatiously tell [daddy.name] that you're willing to call [daddy.herHim] [daddy.mommy], and to convince Lilaya to come and meet [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 15));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getPlayer().setPetName(Main.game.getNpc(Daddy.class), "daddy");
							
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_ACCEPTED));
						}
					};
					
				} else if(index==3) {
					return new Response("Refuse",
								"Tell [daddy.name] that you aren't going to convince Lilaya to meet [daddy.herHim], and that you certainly aren't going to call [daddy.herHim] '[daddy.daddy]'.<br/>"
										+ "<i>This will complete the quest, '"+QuestLine.SIDE_DADDY.getName()+"', but you can still return and restart it at any time.</i>",
								BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "REFUSE_AFTER_RETURN"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
				}
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				if(index==1) {
					return new Response("Help Lilaya",
							"Help Lilaya by reminding her that she was accepting of the idea of having [daddy.name] as a father figure.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
					
				} else if(index==2) {
					return new Response("Help Lilaya (flirt)",
							"Help Lilaya by reminding her that she was accepting of the idea of having [daddy.name] as a father figure, and then go a little further and start flirting with the two of them in order to set the tone of the evening.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 30));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Dinner",
							isLilayaPresent()
								?"Tell [daddy.name] that you and Lilaya would love to go out to dinner with [daddy.herHim] again."
								:"Tell [daddy.name] that you'd love to go out to dinner with [daddy.herHim] again.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
					
				} else if(index==2) {
					return new Response("Dinner (flirt)",
							isLilayaPresent()
								?"Tell [daddy.name] in a flirtatious manner that you and Lilaya would love to go out to dinner with [daddy.herHim] again."
								:"Tell [daddy.name] in a flirtatious manner that you'd love to go out to dinner with [daddy.herHim] again.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER_FLIRTING"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
					
				} else if(index==3) {
					return new Response("Inside",
							isLilayaPresent()
								?"Ask [daddy.name] if the three of you can just go inside and spend some time with one another in [daddy.her] apartment."
								:"Ask [daddy.name] if the two of you can just go inside and spend some time with one another in [daddy.her] apartment.",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT"));
							
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
					
				} else if(index==4) {
					return new Response("Inside (flirting)",
							isLilayaPresent()
								?"Ask [daddy.name] in a flirtatious manner if the three of you can just go inside and spend some time with one another in [daddy.her] apartment."
								:"Ask [daddy.name] in a flirtatious manner if the two of you can just go inside and spend some time with one another in [daddy.her] apartment.",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT_FLIRTING"));
							
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().daddyResetTimer = Main.game.getSecondsPassed();
						}
					};
					
				}
			}
			
			return null;
		}
	};
	
	private static void applyCompanionDrinks() {
		AbstractItemType drink = ItemType.INT_INGREDIENT_GRAPE_JUICE;
		Main.game.getTextStartStringBuilder().append(drink.getEffects().get(0).applyEffect(Main.game.getNpc(Daddy.class), Main.game.getNpc(Daddy.class), 1));
		if(isLilayaPresent()) {
			drink = ItemType.INT_INGREDIENT_GRAPE_JUICE;
			Main.game.getTextStartStringBuilder().append(drink.getEffects().get(0).applyEffect(Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class), 1));
		}
	}
	
	public static final DialogueNode DINNER = new DialogueNode("Temptation", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			DialogueNode nextNode = DINNER_MID;
			boolean firstDinner = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING || Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
			if(firstDinner) {
				nextNode = FIRST_DINNER_TRANSFORM;
			}
			if(index==1) {
				return new Response("Water",
						"Refuse [daddy.namePos] offer of wine, and stick with drinking water in order to avoid getting drunk.",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WATER"));
							applyCompanionDrinks();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WATER_INITIAL"));
								completeQuest();
								applyCompanionDrinks();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WATER"));
								applyCompanionDrinks();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Wine",
						"Accept [daddy.namePos] offer of having a bottle of wine to drink this evening.",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WINE"));
							applyCompanionDrinks();
							AbstractItemType drink = ItemType.INT_INGREDIENT_GRAPE_JUICE;
							Main.game.getTextStartStringBuilder().append(drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WINE_INITIAL"));
								completeQuest();
								applyCompanionDrinks();
								AbstractItemType drink = ItemType.INT_INGREDIENT_GRAPE_JUICE;
								Main.game.getTextStartStringBuilder().append(drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WINE"));
								applyCompanionDrinks();
								AbstractItemType drink = ItemType.INT_INGREDIENT_GRAPE_JUICE;
								Main.game.getTextStartStringBuilder().append(drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode FIRST_DINNER_TRANSFORM = new DialogueNode("Temptation", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Incubus",
						"Tell [daddy.name] that [daddy.sheIs] fine the way [daddy.sheIsFull] right now.<br/>"
								+ "[style.italicsMasculine(This will get [daddy.name] to <b>permanently</b> stay as a "+GenderNames.Y_PENIS_N_VAGINA_N_BREASTS.getMasculine()+" incubus.)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_NO_TF"));
					}
					@Override
					public Colour getHighlightColour() {
						return Colour.MASCULINE;
					}
				};
				
			} else if(index==2) {
				return new Response("Succubus",
						"Convince [daddy.name] that [daddy.she] would have a far easier time seducing Lyssieth if [daddy.she] were to transform into a succubus.<br/>"
								+ "[style.italicsFeminine(This will get [daddy.name] to <b>permanently</b> transform into a "+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+" succubus.)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing(null);
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return Colour.FEMININE;
					}
				};
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				return new Response("Succubus (flirting)",
						"Convince [daddy.name] that [daddy.she] would have a far easier time seducing Lyssieth if [daddy.she] were to transform into a succubus. After getting him to transform, start flirting with her.<br/>"
								+ "[style.italicsFeminine(This will get [daddy.name] to <b>permanently</b> transform into a "+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+" succubus.)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing(null);
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return Colour.FEMININE;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FIRST_DINNER = new DialogueNode("Temptation", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Accept",
						"Tell [daddy.name] that you're willing to call [daddy.herHim] [daddy.mommy], and to convince Lilaya to come and meet [daddy.herHim].",
						DINNER_MID) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_ACCEPT"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 15));

						Main.game.getPlayer().setPetName(Main.game.getNpc(Daddy.class), "daddy");
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_ACCEPTED));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
							"Tell [daddy.name] that you aren't going to convince Lilaya to meet [daddy.herHim], and that you certainly aren't going to call [daddy.herHim] '[daddy.daddy]'.<br/>"
									+ "<i>This will complete the quest, '"+QuestLine.SIDE_DADDY.getName()+"', but you can still return and restart it at any time.</i>",
							BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_REFUSE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED_2));
					}
				};
			}
			
			return null;
		}
	};
	

	public static final DialogueNode DINNER_MID = new DialogueNode("Temptation", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Dessert",
						isLilayaPresent()
							?"Continue to tease and flirt with both [daddy.name] and Lilaya."
							:"Continue to tease and flirt with [daddy.name].",
						DINNER_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DESSERT"));
					}
				};
				
			} else if(index==2) {
				return new Response("No dessert",
						"Decide to spend the evening simply talking with [daddy.name].",
						DINNER_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_NO_DESSERT"));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
					return new Response("'Dessert'",
							isLilayaPresent()
								?"As you haven't got [daddy.name] or Lilaya into the mood by flirting with them this evening, any attempt to get some special 'dessert' from [daddy.name] would almost certainly end in embarrassed disaster."
								:"As you haven't got [daddy.herHim] into the mood by flirting with [daddy.him] this evening, any attempt to get some special 'dessert' from [daddy.name] would almost certainly end in embarrassed disaster.",
							null);
				
				} else if(!Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()) {
					return new Response("'Dessert'",
							"Due to your large lower body, there's no way you can fit under the table in order to perform oral sex on [daddy.name]...",
							null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("'Dessert'",
							"You cannot perform oral sex on [daddy.name] while your mouth is blocked!",
							null);
					
				} else {
					return new ResponseSex("'Dessert'",
							isLilayaPresent()
								?"You have a different kind of dessert in mind. Get Lilaya to drop down under the table with you and join you in sucking [daddy.namePos] cock in order to earn it."
								:"You have a different kind of dessert in mind. Drop down under the table and start sucking [daddy.namePos] cock in order to earn it.",
							true,
							true,
							new SMDaddyDinnerOral(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Daddy.class), SexSlotOther.SITTING)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotOther.PERFORMING_ORAL),
											isLilayaPresent()
												?new Value<>(Main.game.getNpc(Lilaya.class), SexSlotOther.PERFORMING_ORAL_TWO)
												:null)),
							null,
							null,
							AFTER_UNDER_TABLE_SEX,
							UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DADDYS_DESSERT")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getNpc(Daddy.class).displaceClothingForAccess(CoverableArea.PENIS);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true),
									isLilayaPresent()
										?new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getNpc(Lilaya.class), PenisMouth.BLOWJOB_START_ADDITIONAL, true, true)
										:null);
						}
					};
					
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_UNDER_TABLE_SEX = new DialogueNode("Waitress approaches", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_UNDER_TABLE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DINNER_END.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DINNER_END = new DialogueNode("Temptation", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1) {
					return new Response("Refuse",
							isLilayaPresent()
								?(Main.game.getNpc(Lilaya.class).getSubspecies().equals(Subspecies.DEMON)
										?"Tell [daddy.name] that you and Lilaya need to be getting home."
										:"Tell [daddy.name] that you're going to accompany Lilaya and head back home.")
								:"Tell [daddy.name] that you need to be getting home.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return super.getSecondsPassed();
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_REFUSE"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
					
				} else if(index==2) {
					return new Response("Accept",
							isLilayaPresent() && Main.game.getNpc(Lilaya.class).getSubspecies().equals(Subspecies.DEMON)
								?"Tell [daddy.name] that you and Lilaya would love to go back to [daddy.her] place for some more fun..."
								:"Tell [daddy.name] that you'd love to go back to [daddy.her] place for some more fun...",
							AFTER_DINNER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_ACCEPT"));
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							if(isLilayaPresent() && Main.game.getNpc(Lilaya.class).getSubspecies().equals(Subspecies.DEMON)) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Say goodbye",
							isLilayaPresent()
								?"Thank [daddy.name] for the meal and tell [daddy.herHim] that you'll see [daddy.herHim] another time."
								:"Thank [daddy.name] for the meal and tell [daddy.herHim] that you and Lilaya will see [daddy.herHim] another time.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_HOME"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_DINNER = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel() {
			return "[daddy.NamePos] apartment";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Submit",
						isLilayaPresent()
							?"Do as you're told and join Lilaya on [daddy.namePos] bed before presenting yourselves to [daddy.herHim]."
							:"Do as [daddy.name] says and get on [daddy.her] bed before presenting yourself to [daddy.herHim].",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
		
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								
								if(isLilayaPresent()) {
									map.put(Main.game.getNpc(Lilaya.class),
											Util.newArrayListOfValues(
													CoverableArea.FEET,
													CoverableArea.STOMACH,
													CoverableArea.THIGHS));
								}
								map.put(Main.game.getPlayer(),
										Util.newArrayListOfValues(
												CoverableArea.FEET,
												CoverableArea.STOMACH,
												CoverableArea.THIGHS));
								
								return map;
							}
						},
						null,
						null,
						AFTER_APARTMET_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_SEX")) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Dominate",
						isLilayaPresent()
							?"Tell [daddy.name] that that's not quite how this is going to work, before pushing [daddy.herHim] into [daddy.her] bedroom and joining Lilaya in taking [daddy.herHim] for a ride."
							:"Tell [daddy.name] that that's not quite how this is going to work, before pushing [daddy.herHim] into [daddy.her] bedroom and getting ready to take [daddy.herHim] for a ride.",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								null,
								null,
								ResponseTag.PREFER_COW_GIRL) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								
								if(isLilayaPresent()) {
									map.put(Main.game.getNpc(Lilaya.class),
											Util.newArrayListOfValues(
													CoverableArea.FEET,
													CoverableArea.STOMACH,
													CoverableArea.VAGINA));
								}
								map.put(Main.game.getPlayer(),
										Util.newArrayListOfValues(
												CoverableArea.FEET,
												CoverableArea.STOMACH,
												CoverableArea.THIGHS));
								
								return map;
							}
						},
						null,
						null,
						AFTER_APARTMET_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_DOMINATE")) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_APARTMET_SEX = new DialogueNode("Finished", "The evening of lust-filled sex comes to an end...", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMET_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Say goodbye",
						isLilayaPresent()
							?"Thank [daddy.name] for all the fun you had this evening and tell [daddy.herHim] that you'll see [daddy.herHim] another time."
							:"Thank [daddy.name] for all the fun you had this evening and tell [daddy.herHim] that you and Lilaya will see [daddy.herHim] another time.",
						BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMET_SEX_HOME"));
						if(isLilayaPresent()) {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						} else {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
						Main.game.getNpc(Daddy.class).returnToHome();
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode APARTMENT = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "[daddy.NamePos] apartment";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1 || index==2) {
					return AFTER_DINNER.getResponse(responseTab, index);
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.talkedWithDaddy)) {
						return new Response("Talk",
								"You've already spent some time talking with [daddy.name].",
								null);
					}
					return new Response("Talk",
							"Spend some time simply talking to [daddy.name] about [daddy.her] work and efforts to meet Lyssieth.",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_TALK"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Seduce",
							isLilayaPresent()
								?"Seduce [daddy.name] and Lilaya, start making out with them on the sofa, and then move on to have sex with them..."
								:"Seduce [daddy.name], start making out with [daddy.herHim] on the sofa, and then move on to have sex with [daddy.herHim]...",
							APARTMENT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						}
					};
					
				} else if(index==3) {
					return new Response("Leave",
							"Tell [daddy.name] that it was good to see [daddy.herHim] again, before getting up and taking your leave.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_LEAVE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
}
