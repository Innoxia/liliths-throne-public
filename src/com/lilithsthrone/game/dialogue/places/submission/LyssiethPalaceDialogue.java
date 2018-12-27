package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public class LyssiethPalaceDialogue {
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Lyssieth's palace and head back out into Submission.", PlaceType.SUBMISSION_LILIN_PALACE_GATE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode WINDOWS = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "WINDOWS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROOM = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROOM_SIREN = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().getCompanions().contains(Main.game.getNpc(DarkSiren.class))) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ROOM_SIREN_AS_COMPANION");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ROOM_SIREN");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode HALL = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "HALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode STAIRCASE = new DialogueNode("", "", false) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "STAIRCASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SIREN_OFFICE = new DialogueNode("", "", true) {
		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion());
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "Knock on the door and then enter Lyssieth's office.", LYSSIETH_OFFICE_ENTER) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
							}
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Leave", "Step back out of [siren.namePos] office.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LYSSIETH_OFFICE_ENTER = new DialogueNode("", "", true) {

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_COMPANION", Main.game.getPlayer().getMainCompanion());
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lunette", "Ask Lyssieth about her sister, Lunette.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_LUNETTE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Family", "Ask Lyssieth about her daughters, sisters, and mother.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_FAMILY"));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Subspecies.ELDER_LILIN, false));
					}
				};
				
			} else if(index==3) {
				return new Response("Archangels", "Ask Lyssieth about the archangels.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_ANGELS"));
					}
				};
				
			} else if(index==4) {
				return new Response("Humans", "Ask Lyssieth why she is so fond of humans.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_HUMANS"));
					}
				};
				
			} else if(index==5) {
				return new Response("Recap", "Ask Lyssieth to briefly go over the events which caused your world to be warped into this new reality.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_RECAP"));
					}
				};

			} else if(index==6) {
				return new ResponseSex("Sex (pussy)",
						"Tell Lyssieth that you want to have sex with her, and that you want to use her pussy.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_SUB))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY"));
				
			} else if(index==7) {
				return new ResponseSex("Sex (cock)",
						"Tell Lyssieth that you want to have sex with her, and that she should grow a cock and fuck you.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK")) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.HUMAN);
					}
				};
				
			} else if(index==8 && Main.game.getPlayer().getSubspeciesOverride()==null) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CORRUPTION_PERK_5)) {
					return new Response("Become a demon", "Tell Lyssieth that you want her to turn you into a demon.", DEMON_TF);
					
				} else {
					return new Response("Become a demon", "You are not corrupt enough to be turned into a demon...<br/>[style.italicsBad(Requires corruption of at least 95.)]", null);
				}
			}
			if(index==0) {
				return new Response("Leave", "Step back out of Lyssieth's office.", SIREN_OFFICE_LEAVE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode THRONE_ROOM_TALK = new DialogueNode("", "", true) {

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
			return LYSSIETH_OFFICE_ENTER.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Step back out of [siren.namePos] office.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Step back out of Lyssieth's office.", SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Insist", "Seize upon Lyssieth's hesitation to convince her to do as you ask.", DEMON_TF_CONTINUE);
				
			} else if(index==2){
				return new Response("Relent", "Don't push it any further, and tell Lyssieth that you don't want to force her to do something she doesn't want to do.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_RELENT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CONTINUE = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Certain",
						"Tell Lyssieth that you are certain about wanting to become a demon, and that you understand there's no going back."
						+ " You also understand that she will need to completely strip you of your clothes and fuck you in order to do this...",
						DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return Colour.RACE_DEMON;
					}
					@Override
					public void effects() {
						// Lyssieth strips and transforms to full lilin
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).setLilinBody();
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							c.setSealed(false);
						}
					}
				};
				
			} else if(index==2){
				return new Response("Change mind", "Tell Lyssieth that you've changed your mind, and that you don't want her to turn you into a demon.", THRONE_ROOM_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_START = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Corruption",
						"You feel Lyssieth's corruptive essence seeping into your body...",
						true,
						false,
						new SMLyssiethDemonTF(
								SexPositionType.KNEELING_ORAL,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX = new DialogueNode("Demon", "Thanks to Lyssieth, you are now a demon.", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Take surname", "Tell Lyssieth that you will take her name, and be known as '[pc.name] Lyssiethmartuilani'.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setSurname("Lyssiethmartuilani");
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_TAKE_SURNAME"));
					}
				};
				
			} else if(index==2) {
				return new Response("Keep surname", "Tell Lyssieth that you will keep your current name.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_DO_NOT_TAKE_SURNAME"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAME = new DialogueNode("", "", true) {

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
			if(index==1) {
				return new Response("Leave", "Step back out of Lyssieth's office.", SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).cleanAllDirtySlots();
						Main.game.getNpc(Lyssieth.class).cleanAllClothing();
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DEMON_TF_SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Step back out of [siren.namePos] office.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
}
