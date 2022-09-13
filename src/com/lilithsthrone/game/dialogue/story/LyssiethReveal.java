package com.lilithsthrone.game.dialogue.story;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.4
 * @author Innoxia
 */
public class LyssiethReveal {

	public static final DialogueNode ENTRANCE_WITH_ELIZABETH = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "ENTRANCE_WITH_ELIZABETH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Let Elizabeth lead you towards Lyssieth's throne room.", FORWARDS_1) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_1 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_1");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Onwards", "Carry on following Elizabeth.", FORWARDS_2) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_2 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "You ready yourself for meeting Lyssieth, and follow Elizabeth through the open doors.",
						FORWARDS_3) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_3 = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lyssieth's office", "Allow [siren.name] to introduce you to Lyssieth.", OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Defend", "It looks like Lyssieth is about to attack you! Defend yourself!", OFFICE_REACTION);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Resist", "Don't give in! Resist Lyssieth's spell and try to remain on your [pc.feet].", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_RESIST"));
					}
				};
				
			} else if(index==2) {
				return new Response("Submit", "Yes... Kneel... Just give in...", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_SUBMIT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_BETRAYAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_BETRAYAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stand", "Do as Lyssieth says and push yourself to your feet.", OFFICE_REACTION_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Forwards", "Move up beside Lyssieth so that she's able to teleport you and [siren.name] to Lilaya's home.", OFFICE_TELEPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Arrival", "The three of you arrive in Lyssieth's laboratory.", OFFICE_TELEPORT_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT_ARRIVE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT_ARRIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Arthur", "Hear Arthur's theory.", LAB_ARTHUR_THEORY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ARTHUR_THEORY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ARTHUR_THEORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("What?!", "Express your alarm at what Lyssieth has just said.", LAB_WORLD_REVEAL);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_WORLD_REVEAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_WORLD_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lilaya", "While [siren.name] and Arthur seem to be taking this news well, Lilaya looks incredibly distressed, and it appears as though she has something to say.", LAB_LILAYA_ANGERY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_ANGERY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_ANGERY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Calm", "Calm Lilaya down by reassuring her that she's still fundamentally the same person you've always known.", LAB_LILAYA_CALMED_DOWN);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_CALMED_DOWN = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_CALMED_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)) {
					return new Response("World", "You've already asked Lyssieth about the rest of the world.", null);
				} else {
					return new Response("World", "Ask Lyssieth why the rest of the world hasn't done something already.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_WORLD"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked1, true);
							AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
						}
					};
				}
				
			} if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)) {
					return new Response("Betrayal", "You've already asked Lyssieth about why she chose to betray Lilith.", null);
				} else {
					return new Response("Betrayal", "Ask Lyssieth why she has chosen to betray Lilith.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_BETRAYAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked2, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)) {
					return new Response("People", "Lilaya has already asked Lyssieth about people's transformations.", null);
				} else {
					return new Response("People", "Lilaya wants to ask about the way in which people were changed into different version of themselves.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_PEOPLE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked3, true);
						}
					};
				}
				
			} else if(index==4) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)) {
					return new Response("Spell", "[siren.Name] has already asked Lyssieth about Lilith's reality-altering spell.", null);
				} else {
					return new Response("Spell", "[siren.Name] wants to ask about Lilith's reality-altering spell.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_SPELL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked4, true);
						}
					};
				}
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("Reversal", "Arthur has already asked Lyssieth about the possibility of reversing Lilith's spell.", null);
				} else {
					return new Response("Reversal", "Arthur wants to ask about the possibility of reversing Lilith's spell.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_REVERSAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked5, true);
						}
					};
				}
				
			} else if(index==6) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("Continue", "You need to ask Lyssieth about the spell before continuing.", null);
				} else {
					return new Response("Continue", "Lyssieth has no more time for questions.", LAB_QUESTION_END);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LAB_QUESTION = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_LILAYA_CALMED_DOWN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LAB_QUESTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Liberate", "Tell everyone that you want to defeat Lilith and end her tyrannical rule.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_LIBERATE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Usurp", "Say that you intend to defeat Lilith, and take her place as the ruler of all Dominion.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_USURP"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionUsurp, true);
					}
				};
				
			} else if(index==3) {
				return new Response("Join", "Say that you want to join Lilith.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_JOIN"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -20));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionJoin, true);
					}
				};
				
			} else if(index==4) {
				return new Response("Nothing",
						"You really don't care about saving the world or any of that nonsense. It's far less effort to just decide to do nothing and leave the fate of the world to people with more enthusiasm.",
						LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_NOTHING"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionNothing, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Impossible", "Ask Lyssieth how you're meant to fight an elder lilin and her army of demonic centaurs.", LAB_ENDING_MINOTALLYS);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_MINOTALLYS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_MINOTALLYS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Youko?", "Ask Meraxis who the youko are.", LAB_ENDING_SIREN_HELP);
			}
			return null;
		}
	};

	public static final DialogueNode LAB_ENDING_SIREN_HELP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Takahashi.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_SIREN_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Return", "Return with Lyssieth to her office, leaving Lilaya and [siren.name] behind.", LAB_ENDING_RETURN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).returnToHome();
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_RETURN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getRace()==Race.HUMAN) {
					return new Response("Refuse sex",
							"Tell Lyssieth that you don't want to have sex, and if it's necessary for her to orgasm, she'll just have to masturbate.",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX_HUMAN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				} else {
					return new Response("Refuse sex",
							"Tell Lyssieth that you're not interested in having sex with her, and allow her to infuse her power into your aura.",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				}
				
			} else if(index==2) {
				return new ResponseSex("Pussy",
						"Tell Lyssieth that you want to have sex with her, and that you want to dominantly use her pussy.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_PUSSY"));
				
			} else if(index==3) {
				return new ResponseSex("Cock",
						"Tell Lyssieth that you want to have sex with her, and that she should grow a cock and dominantly fuck you.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_COCK")) {
					@Override
					public void effects() {
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};
			}
			return null;
		}
	};
	
//	private static void setPlayerAsLyssieth() {
//		PlayerCharacter player = new PlayerCharacter(
//				new NameTriplet("Lyssieth"),
//				1000,
//				null,
//				Gender.F_V_B_FEMALE,
//				Subspecies.DEMON,
//				RaceStage.GREATER,
//				WorldType.LYSSIETH_PALACE,
//				PlaceType.LYSSIETH_PALACE_OFFICE);
//		player.setSurname("Lilithmartuilani");
//		player.setDescription("One of the seven elder Lilin, you are one of the most powerful beings in existence.");
//		player.setSubspeciesOverride(Subspecies.ELDER_LILIN);
//		player.getBody().calculateRace(player);
//		player.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
//		player.setAttribute(Attribute.MAJOR_ARCANE, 100);
//		player.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
//		Main.game.setPlayer(player);
//	}
	
	public static final DialogueNode LAB_ENDING_RETURN_DECLINE_SEX = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Vision", "You have a strange vision, in which you are Lyssieth...", POWER_VISION) {
					@Override
					public void effects() {
//						setPlayerAsLyssieth();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_VISION = new DialogueNode("Vision", "You have a strange vision, in which you are Lyssieth...", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_VISION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake up", "You regain consciousness in Lyssieth's office.", POWER_EXPLANATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lap pillow", "Take up Lyssieth's offer, and continue resting your head on her lap as you tell her about the vision you saw.", POWER_EXPLANATION_CONTINUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_LAP"));
					}
				};
				
			} else if(index==2) {
				return new Response("Stand up", "You don't feel comfortable like this. Stand up and tell her about the vision you saw.", POWER_EXPLANATION_CONTINUE) { // But why
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_STAND_UP"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION_CONTINUE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step out", "Step outside Lyssieth's office so that she can fetch [siren.name].", END_SIREN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_SIREN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_SIREN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Map", "Take the world map that is being given to you.", END_FINAL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_3_ELIS));
						Main.game.getTextEndStringBuilder().append(
								"<div class='container-full-width' style='text-align:center;'>"
										+ "[style.colourExcellent(You have unlocked the world map!)]<br/>"
										+ "<i>It can be viewed either through your phone's map menu, or by travelling to one of Dominion's exit tiles and accessing the 'World travel' menu.</i>"
								+ "</div>");
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_FINAL = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_FINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false).getResponse(responseTab, index);
		}
	};
}
