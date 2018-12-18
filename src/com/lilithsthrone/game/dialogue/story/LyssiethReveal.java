package com.lilithsthrone.game.dialogue.story;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressDemonLeader;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public class LyssiethReveal {

	public static final DialogueNodeOld ENTRANCE_WITH_ELIZABETH = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "ENTRANCE_WITH_ELIZABETH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Let Elizabeth lead you towards Lysseth's throne room.", FORWARDS_1) {
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
	
	public static final DialogueNodeOld FORWARDS_1 = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "FORWARDS_1");
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
	
	public static final DialogueNodeOld FORWARDS_2 = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "FORWARDS_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Almost there", "It looks like there's an intersection in the corridor up ahead, with a large wooden door directly in front of you. Surely this is the entrance to Lyssieth's throne room?",
						FORWARDS_3) {
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
	
	public static final DialogueNodeOld FORWARDS_3 = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "FORWARDS_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "Allow [siren.name] to announce your presence, before finally coming face-to-face with Lilaya's mother.", OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
						Main.game.getNpc(FortressDemonLeader.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld OFFICE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Defend", "It looks like Lyssieth is about to attack you! Defend yourself!", OFFICE_REACTION);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld OFFICE_REACTION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "OFFICE_REACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Teleport", "Allow Lyssieth to teleport you and [siren.name] to Lilaya's laboratory.", OFFICE_TELEPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld OFFICE_TELEPORT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "OFFICE_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Arrival", "The three of you arrive in Lyssieth's laboratory.", OFFICE_TELEPORT_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(FortressDemonLeader.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld OFFICE_TELEPORT_ARRIVE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "OFFICE_TELEPORT_ARRIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Arthur", "Hear Arthur's theory.", LAB_ARTHUR_THEORY);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_ARTHUR_THEORY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ARTHUR_THEORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("What?!", "Express your alarm at what Lyssieth has just said.", LAB_WORLD_REVEAL);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_WORLD_REVEAL = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_WORLD_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lilaya", "Lilaya has something to say.", LAB_LILAYA_ANGERY);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_LILAYA_ANGERY = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_LILAYA_ANGERY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Calm", "Calm Lilaya down by reassuring her that she's still fundamentally the same person you've always known.", LAB_LILAYA_CALMED_DOWN);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_LILAYA_CALMED_DOWN = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_LILAYA_CALMED_DOWN");
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
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_WORLD"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked1, true);
						}
					};
				}
				
			} if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)) {
					return new Response("Lilin", "You've already asked Lyssieth about the other lilin.", null);
				} else {
					return new Response("Lilin", "Ask Lyssieth where the rest of the lilin are, and why she has chosen to betray Lilith.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_LILIN"));
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
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_PEOPLE"));
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
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_SPELL"));
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
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_REVERSAL"));
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
					return new Response("Continue", "Ask Lyssieth what should be done about all this.", LAB_QUESTION_END);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_QUESTION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
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
	
	public static final DialogueNodeOld LAB_QUESTION_END = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_QUESTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Liberate", "Tell everyone that you want to defeat Lilith and end her tyrannical rule.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_LIBERATE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Usurp", "Say that you intend to defeat Lilith, and take her place as the ruler of all Dominion.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_USURP"));
					}
				};
				
			} else if(index==3) {
				return new Response("Join", "Say that you want to join Lilith.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_JOIN"));
					}
				};
				
			} else if(index==4) {
				return new Response("Nothing",
						"You really don't care about saving the world or any of that nonsense. It's much safer if you just decide to do nothing and leave the fate of the world to people with more enthusiasm.",
						LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_NOTHING"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_ENDING = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Return", "Return with Lyssieth to her office.", LAB_ENDING_RETURN) {
					@Override
					public void effects() {//TODO change
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_THRONE_ROOM);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_ENDING_RETURN = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_RETURN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {//TODO need Lyssieth special orgasm actions - Need leg lock
			if(index==1) {
				return new Response("Refuse", "Tell Lyssieth that you don't want to have sex, and that if this is necessary, she'll just have to masturbate.", LAB_ENDING_RETURN_MASTURBATE);
				
			} else if(index==2) {
				return new ResponseSex("Pussy",
						"Tell Lyssieth that you want to use her pussy.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_SUB)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_DOM))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("misc/lyssiethReveal", "SEX_PUSSY"));
				
			} else if(index==3) {
				return new ResponseSex("Cock",
						"Tell Lyssieth that you want her to grow a cock and fuck you.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("misc/lyssiethReveal", "SEX_COCK")) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.HUMAN);
					}
				};
			}
			//TODO demon TF
			return null;
		}
	};
	
	public static final DialogueNodeOld LAB_ENDING_RETURN_MASTURBATE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_RETURN_MASTURBATE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Power", "At the moment of Lyssieth's orgasm, you feel a surge of power rushing into your aura.", LAB_ENDING_RETURN) {
					@Override
					public void effects() {
						//TODO effects & quest
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "LAB_ENDING_RETURN_MASTURBATE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				//TODO make this special orgasm - it makes you orgasm at the same time
				return new Response("Power", "At the moment of Lyssieth's orgasm, you feel a surge of power rushing into your aura.", LAB_ENDING_RETURN) {
					@Override
					public void effects() {
						//TODO effects & quest
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld POWER_EXPLANATION = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "POWER_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Step outside Lyssieth's office so that she can fetch [siren.name].", END) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						Main.game.getNpc(FortressDemonLeader.class).setLocation(Main.game.getNpc(Lyssieth.class), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld END = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/lyssiethReveal", "END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
}
