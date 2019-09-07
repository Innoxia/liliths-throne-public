package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.1
 * @author Innoxia
 */
public class LyssiethPalaceDialogue {
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
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
		public int getSecondsPassed() {
			return 10;
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
		public int getSecondsPassed() {
			return 10;
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
		public int getSecondsPassed() {
			return 10;
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
	
	public static final DialogueNode HALL = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
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
		public int getSecondsPassed() {
			return 10;
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
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE"));
			
			if(Main.game.getPlayer().hasCompanions()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_NO_COMPANION"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "Knock on the door and then enter Lyssieth's office.", LYSSIETH_OFFICE_ENTER) {
					@Override
					public void effects() {
						conversationIndex = 0;
						Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
							}
						}
					}
				};
				
			} else if(index==6
					&& Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER
					&& Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				return new Response("Demon",
						"Tell Meraxis that she should go through with the process of becoming a full demon.<br/>[style.italicsDemon(This will permanently transform Meraxis into a full demon!)]",
						MERAXIS_DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return Colour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.meraxisRepeatDemonTF, true);
						Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
							}
						}

						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
				
			} else if(index==0) {
				return new Response("Leave", "Step back out of [siren.namePos] office.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ready", "Having just been corrupted into loving incest, Meraxis is ready to have sex with the three of you and be turned into a demon.", MERAXIS_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("Step outside",
						"You don't want to have sex with your mother and sisters. Step outside into Meraxis's office and wait for the three of them to finish.",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
						Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_ORIFICE_SELECTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_LILAYA_DEMON_TF_SEX.getResponse(responseTab, index); // Orifice selection.
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Demonic Meraxis", "Meraxis steps out from Lyssieth's office, allowing you to see how she's changed.",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SIREN_OFFICE.getResponse(responseTab, index); // Standard actions.
		}
	};

	private static void updateLyssiethPregnancyReactions() {
		Main.game.getNpc(Lyssieth.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Lyssieth.class), true);
	}
	
	private static int conversationIndex = 0;
	
	public static final DialogueNode LYSSIETH_OFFICE_ENTER = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().hasCompanions()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_COMPANION", Main.game.getPlayer().getMainCompanion()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_MAIN"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lunette", conversationIndex==1?"You're already asking Lyssieth about her sister.":"Ask Lyssieth about her sister, Lunette.", conversationIndex==1?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 1;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_LUNETTE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Family", conversationIndex==2?"You're already asking Lyssieth about her family.":"Ask Lyssieth about her daughters, sisters, and mother.", conversationIndex==2?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 2;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_FAMILY"));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.ELDER_LILIN, false));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, false));
					}
				};
				
			} else if(index==3) {
				return new Response("Archangels", conversationIndex==3?"You're already asking Lyssieth about the archangels.":"Ask Lyssieth about the archangels.", conversationIndex==3?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 3;
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.statueTruthRevealed, true);
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_ANGELS"));
					}
				};
				
			} else if(index==4) {
				return new Response("Humans", conversationIndex==4?"You're already asking Lyssieth about why she's so fond of humans.":"Ask Lyssieth why she is so fond of humans.", conversationIndex==4?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 4;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_HUMANS"));
					}
				};
				
			} else if(index==5) {
				return new Response("Recap", conversationIndex==5?"You're already asking Lyssieth to go over the events which caused your world to be warped into this new reality "
						:"Ask Lyssieth to briefly go over the events which caused your world to be warped into this new reality.", conversationIndex==5?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 5;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_RECAP"));
					}
				};

			} else if(index==6) {
				return new ResponseSex("Sex (pussy)",
						"Tell Lyssieth that you want to have sex with her, and that you want to use her pussy.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.OVER_DESK_ON_BACK))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY")) {
					@Override
					public void effects() {
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
					}
				};
				
			} else if(index==7) {
				return new ResponseSex("Sex (cock)",
						"Tell Lyssieth that you want to have sex with her, and that she should grow a cock and fuck you.",
						true,
						true,
						new SMLyssiethSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK")) {
					@Override
					public void effects() {
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};
				
			} else if(index==8 && Main.game.getPlayer().getSubspeciesOverride()==null) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CORRUPTION_PERK_5)) {
					return new Response("Become a demon", "Tell Lyssieth that you want her to turn you into a demon.", DEMON_TF) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
						}
					};
					
				} else {
					return new Response("Become a demon", "You are not corrupt enough to be turned into a demon...<br/>[style.italicsBad(Requires corruption of at least 95.)]", null);
				}
				
			} else if(index==11) { // Teleport
			
				if(Main.game.getPlayer().getRace()==Race.DEMON && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
					return new Response("Lilaya's lab", "Lyssieth is unwilling to face her daughter until you've broken the news to her that you're now a full demon. You'll have to make your own way back to the lab...", null);
				}

				if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews))
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return new Response("Lilaya's lab", "Lyssieth is unwilling to teleport you into her daughter's lab until Lilaya's pregnancy situation is resolved...", null);
				}
				
				return new Response("Lilaya's lab", "Ask Lyssieth to teleport you back to Lilaya's laboratory.", LAB_TELEPORT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			if(index==0) {
				return new Response("Leave", "Step back out of Lyssieth's office.", SIREN_OFFICE_LEAVE) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LYSSIETH_OFFICE_TALK = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}

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
			return LYSSIETH_OFFICE_ENTER.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LAB_TELEPORT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT_COMPANION", Main.game.getPlayer().getMainCompanion());
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Lab.LAB_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
			if(Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))>=Main.game.getNpc(Lyssieth.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX_NOT_SATISFIED");
			}
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
				return new Response("Relent", "Don't push it any further, and tell Lyssieth that you don't want to force her to do something she doesn't want to do.", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_RELENT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CONTINUE = new DialogueNode("", "", true, true) {
		
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
						Main.game.getPlayer().setAllAreasKnownByCharacter(Main.game.getNpc(Lyssieth.class), true);
						Main.game.getNpc(Lyssieth.class).setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} else if(index==2){
				return new Response("Change mind", "Tell Lyssieth that you've changed your mind, and that you don't want her to turn you into a demon.", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CHANGE_MIND = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LYSSIETH_OFFICE_TALK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_TF_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Submit",
						"Do as Lyssieth says, and stroke her cock until it's fully erect, before dropping to your knees and giving her a blowjob.<br/>"
								+ "[style.italicsFeminine(Lyssieth will make you more feminine when she orgasms!)]",
						true,
						false,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Dominate",
						"Refuse to do as Lyssieth says, and force her to be the one to perform oral on you.<br/>"
						+ (!Main.game.getPlayer().isFeminine()
								?"[style.italicsMasculine(Lyssieth will make you more masculine when she orgasms!)]"
								:"[style.italicsDisabled(This will have no extra feminising effect.)]")
						+(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()
								?(Main.game.getPlayer().isFeminine()
										?"<br/>[style.italicsTfSexual(Lyssieth will give you a demonic vagina.)]"
										:"<br/>[style.italicsTfSexual(Lyssieth will give you a demonic cock.)]")
								:""),
						true,
						true,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						"") {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(Main.game.getPlayer().hasPenis() || (!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().isFeminine())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), PenisMouth.BLOWJOB_START, false, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT"));
						if(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()) {
							if(Main.game.getPlayer().isFeminine()) {
								SALyssiethSpecials.playerGrowDemonicVagina();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_VAGINA"));
							} else {
								SALyssiethSpecials.playerGrowDemonicPenis();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_PENIS"));
							}
						} else if(Main.game.getPlayer().hasPenis()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_PENIS"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_VAGINA"));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX = new DialogueNode("Finished", "Thanks to Lyssieth, you are now a demon!", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reassure", "Lyssieth seems to be a little worried about how her half-demon daughters are going to react when seeing you. Reassure her that it will all be fine.", AFTER_DEMON_TF_SEX_SURNAMES) {
					@Override
					public void effects() {
						// Resolve Lilaya pregnancy so that her reactions aren't too complicated:
						if(Main.game.getNpc(Lilaya.class).isPregnant()) {
							Main.game.getNpc(Lilaya.class).endPregnancy(true);
						}
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getNpc(Lyssieth.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAMES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_SURNAMES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Take surname", "Tell Lyssieth that you will take her name, and be known as '[pc.name] Lyssiethmartuilani'.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setSurname("Lyssiethmartuilani");
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_TAKE_SURNAME"));
					}
				};
				
			} else if(index==2) {
				return new Response("Keep surname", "Tell Lyssieth that you will keep your current name.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_DO_NOT_TAKE_SURNAME"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAME = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Meraxis's office", "Step out of Lyssieth's office and into her daughter's office-cum-waiting room.", AFTER_DEMON_TF_SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).cleanAllDirtySlots();
						Main.game.getNpc(Lyssieth.class).cleanAllClothing(true);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DEMON_TF_SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
				return new Response("Leave", "Head out into the corridors of Lyssieth's palace.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_START = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Meraxis", "Lilaya tells your other sister to step into the office.", LILAYA_DEMON_TF_MERAXIS_CHOICE) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_MERAXIS_CHOICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_CHOICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Transform both", "Convince Meraxis to go ahead with the full demon transformation.", LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
				
			} else if(index==2) {
				return new Response("Just Lilaya",
						"Tell Meraxis that she doesn't need to be transformed if she doesn't feel comfortable about it.<br>[style.italics(You can change your mind later and convince Meraxis to be transformed into a full demon.)]",
						LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isMeraxisBeingTransformed() {
		return Main.game.getNpc(DarkSiren.class).getLocationPlace().getPlaceType().equals(PlaceType.LYSSIETH_PALACE_OFFICE);
	}
	
	private static void returnCompanionsToLab() {
		if(Main.game.getPlayer().hasCompanions()) { //TODO test
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
			}
		}
	}
	
	public static final DialogueNode LILAYA_DEMON_TF_SEX_CHOICE = new DialogueNode("", "", true, true) {
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
				return new Response("Ready", "Having just been corrupted into loving incest, Lilaya is ready to have sex with her mother and yourself and be turned into a demon.", LILAYA_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("Step outside",
						isMeraxisBeingTransformed()
							?"You don't want to have sex with your mother and sisters. Step outside into Meraxis's office and wait for the three of them to finish."
							:"You don't want to have sex with your mother and sister. Step outside into Meraxis's office and wait for the two of them to finish.",
						LILAYA_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(Lilaya.class);
						Main.game.getNpc(Lilaya.class).calculateGenericSexEffects(false, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						if(isMeraxisBeingTransformed()) {
							((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						}
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						returnCompanionsToLab();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_ORIFICE_SELECTION"); // Meraxis gets embarrassed and wants to only watch
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_ORIFICE_SELECTION");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Fuck pussy", "As you are unable to access your cock, you're not able to fuck Lilaya's pussy...", null);
				}
				return new ResponseSex("Fuck pussy",
						"Tell your sister that you're going to be the one to fuck her pussy, while your mother fucks her ass from behind."
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(You will grow a demonic cock with which to fuck her.)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_PUSSY")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Fuck ass", "As you are unable to access your cock, you're not able to fuck Lilaya's ass...", null);
				}
				return new ResponseSex("Fuck ass",
						"Tell your sister that you're going to be the one to fuck her ass from behind, while your mother fucks her pussy."
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(You will grow a demonic cock with which to fuck her.)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_ASS")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==6) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Give cunnilingus", "As your mouth is blocked, you're not able to lick Lilaya's pussy...", null);
				}
				return new ResponseSex("Give cunnilingus",
						"Tell your sister that you want to lick her pussy, while your mother fucks her ass from behind.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==7) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Give blowjob", "As your mouth is blocked, you're not able to suck Lilaya's cock...", null);
				}
				return new ResponseSex("Give blowjob",
						"Tell your sister that you want her to suck her cock, while your mother fucks her pussy from behind."
								+ "<br/>[style.italicsTfSex(Lilaya will grow a demonic cock for you to suck.)]",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_BLOWJOB")) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
							Main.game.getNpc(Lilaya.class).setPenisSize(20);
							Main.game.getNpc(Lilaya.class).setPenisGirth(PenisGirth.THREE_THICK);
							Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==8 && Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Give anilingus", "As your mouth is blocked, you're not able to lick Lilaya's ass...", null);
				}
				return new ResponseSex("Give anilingus",
						"Tell your sister that you want to lick her ass, while your mother fucks her pussy.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueAnus.ANILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==11
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				return new ResponseSex("Use [pc.fingers]",
						"As your penis and mouth are blocked, you'll have to make do with fingering your sister's pussy, while your mother fucks her ass.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_LILAYA_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMeraxisBeingTransformed()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Fuck pussy", "As you are unable to access your penis, you're not able to take Meraxis's virginity...", null);
					}
					return new ResponseSex("Fuck pussy",
							"Tell your sister that you're going to be the one to take her virginity, while your mother fucks her ass from behind, and Lilaya uses her mouth."
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(You will grow a demonic cock with which to fuck her.)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_PUSSY")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Fuck ass", "As you are unable to access your penis, you're not able to fuck Meraxis's ass...", null);
					}
					return new ResponseSex("Fuck ass",
							"Tell your sister that you're going to be the one to take her anal virginity, while your mother fucks her pussy, and Lilaya uses her mouth."
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(You will grow a demonic cock with which to fuck her.)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_ASS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Receive cunnilingus", "As you are unable to access your pussy, you're not able to get Meraxis to eat you out...", null);
					}
					return new ResponseSex("Receive cunnilingus",
							"Tell your sister that you're going to use her mouth, while your mother fucks her pussy, and Lilaya pounds her ass."
									+ (!Main.game.getPlayer().hasVagina()
											?"<br/>[style.italicsTfSex(You will grow a demonic pussy for her to lick.)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_CUNNILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasVagina()) {
								Main.game.getPlayer().setVaginaType(VaginaType.DEMON_COMMON);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenisGirth.THREE_THICK);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				} else if(index==4) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Receive blowjob", "As you are unable to access your penis, you're not able to get Meraxis to give you a blowjob...", null);
					}
					return new ResponseSex("Receive blowjob",
							"Tell your sister that you're going to use her mouth, while your mother fucks her pussy, and Lilaya pounds her ass."
									+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
											?"<br/>[style.italicsTfSex(You will grow a demonic cock with which to fuck her.)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_BLOWJOB")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenisGirth.THREE_THICK);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisMouth.BLOWJOB_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				}  else if(index==5
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new ResponseSex("Kneel in front",
							"As you are unable to gain access to your genitals, you'll just have to kneel in front of Meraxis and find some other way of having fun while Lilaya and Lyssieth fuck her.",
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_KNEELING_NO_ACCESS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenisGirth.THREE_THICK);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
				} 
				
			} else {
				if(index==1) {
					return new Response("Lyssieth", "Look across at Lyssieth and see how she's reacting.", LILAYA_DEMON_TF_FINISHED_REACTION) {
							@Override
							public void effects() {
								Main.game.getNpc(Lyssieth.class).setStartingBody(false);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX_FINISHED_REACTION"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MERAXIS_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lyssieth", "Look across at Lyssieth and see how she's reacting.", LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							Main.game.getNpc(Lyssieth.class).setStartingBody(false);
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX_FINISHED_REACTION"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX_FINISHED_REACTION"));
							}
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_WAIT_IN_OFFICE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_WAIT_IN_OFFICE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isMeraxisBeingTransformed()
							?"Demonic sisters"
							:"Demonic Lilaya",
						isMeraxisBeingTransformed()
							?"Step back into Lyssieth's office and see how Lilaya and Meraxis have changed."
							:"Step back into Lyssieth's office and see how Lilaya has changed.",
						LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							if(isMeraxisBeingTransformed()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_FINISHED_REACTION_OFFICE_END"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_FINISHED_REACTION_OFFICE_END"));
							}
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
							returnCompanionsToLab();
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_FINISHED_REACTION = new DialogueNode("", "", true, true) {
		
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
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
					return new Response("Meraxis's office", "You and Meraxis head back out into the office-cum-waiting room.", LILAYA_DEMON_TF_END) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(GameCharacter offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringCharacters()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
								Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
				}
				return new Response("Lilaya's Lab", "You and Lilaya are returned to her lab.", LILAYA_DEMON_TF_END) {
						@Override
						public void effects() {
							// Reset offspring to full demons:
							if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
								for(GameCharacter offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringCharacters()) {
									offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
								}
								Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
							}

							// Reset offspring to full demons:
							if(Main.game.getNpc(Lilaya.class).isPregnant()) {
								for(GameCharacter offspring : Main.game.getNpc(Lilaya.class).getPregnantLitter().getOffspringCharacters()) {
									offspring.setBody(offspring.getGender(), Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class).getPregnantLitter().getFather());
								}
								Main.game.getNpc(Lilaya.class).getPregnantLitter().generateBirthedDescription();
							}
							
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_END = new DialogueNode("", "", false, true) {
		
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF);
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_END");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return SIREN_OFFICE.getResponse(responseTab, index);
			}
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
}
