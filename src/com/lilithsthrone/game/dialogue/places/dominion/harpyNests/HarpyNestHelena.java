package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class HarpyNestHelena {
	
	public static final DialogueNode HELENAS_NEST_EXTERIOR = new DialogueNode("Helena's nest", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_STORM");
			} else if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_SLEEPING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
					return new Response("Meet with Helena",
							"Helena's flock is taking shelter in the buildings below her nest. You'll have to come back after the arcane storm has passed.",
							null);
					
				} else if(!Main.game.isExtendedWorkTime()) {
					return new Response("Meet with Helena",
							Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)
								?"Both Helena and her flock are sleeping in the buildings below her nest. If you wanted to speak with Helena, you should go to her shop in Slaver Alley."
								:"Both Helena and her flock are sleeping in the buildings below her nest. You'll have to come back during the day if you want to speak with her.",
							null);
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
						return new Response("Meet with Helena", "Walk over to the tall platform.", HELENAS_NEST);
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)) {
						if(Main.game.getNpc(Helena.class).getLocation().equals(Main.game.getPlayer().getLocation())) {
							return new Response("Meet with Helena", "You'll be able to interact with Helena again later!", null);
							
						} else {
							return new Response("Meet with Helena", "Helena has flown off to Slaver Alley! You'll have to find her there.", null);
						}
						
					} else {
						return new Response("Meet with Helena", "You have no reason to talk to Helena.", null);
					}
				}
				
			} else if(index==2) {
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
						return new Response("Scarlett",
								"As there's an arcane storm currently raging overhead, Scarlett and the rest of the nest's inhabitants are sheltering in the buildings below her nest."
										+ " You'll have to come back once the storm has passed if you want to speak with her.",
								null);
						
					} else if(!Main.game.isExtendedWorkTime()) {
						return new Response("Scarlett",
								"Both Scarlett and the rest of Helena's flock are sleeping in the buildings below her nest. You'll have to come back during the day if you want to speak with her.",
								null);
						
					} else {
						return new Response("Scarlett", "Head over to where Scarlett is sitting and say hello.", HELENAS_NEST_MEETING_SCARLETT);
						
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST = new DialogueNode("Helena's nest", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scarlett's woe", "Tell Helena about Scarlett's failure to run her slavery business.", HELENAS_NEST_SCARLETT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETT = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("No punishment", "Don't take Scarlett's punishment for her.", HELENAS_NEST_NO_PUNISHMENT) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==2) {
				return new Response("Take punishment", "Offer to take Scarlett's punishment for her.", HELENAS_NEST_TAKE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.punishedByHelena);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_NO_PUNISHMENT = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_NO_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Endure it", "Try and keep quiet and endure your punishment.", HELENAS_NEST_TAKE_PUNISHMENT_ENDURE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==2) {
				return new Response("Struggle", "Start struggling and crying out in discomfort.", HELENAS_NEST_TAKE_PUNISHMENT_STRUGGLE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
				
			} else if(index==3) {
				return new Response("Beg for more", "Beg to be punished.", HELENAS_NEST_TAKE_PUNISHMENT_ENJOY,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_ENDURE = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_ENDURE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_STRUGGLE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_ENJOY = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_ENJOY"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_LEAVING"));
						
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_TAKE_FLIGHT);
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Fly after her", "As your companion is unable to fly, you'll have to travel to Slaver Alley by foot...", null);
					} else {
						return new Response("Fly after her", "As you are unable to fly, you'll have to travel to Slaver Alley by foot...", null);
					}
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_FLIGHT = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_FLIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Scarlett's Shop", "You arrive at Scarlett's Shop.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT = new DialogueNode("", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			if(index==0) {
				return new Response("Step back", "Scarlett is clearly not interested in talking to you, and so there's nothing to do but step back and take your leave..", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK"));
					}
				};
			}
			if(Main.game.isAnalContentEnabled()) {
				if(Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					responses.add(new Response("Offer ass", "Scarlett is not attracted to you, and so is unwilling to have sex with you.", null));
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					responses.add(new Response("Offer ass", "Scarlett is only interested in fucking your ass, and as you can't get access to it, she's not interested in having sex with you.", null));
					
				} else {
					return new ResponseSex("Offer ass",
							"Tell Scarlett that if that's what she wants, then of course she can fuck your ass.",
							true, false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									return getMainSexPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(Main.game.getNpc(Scarlett.class))) {
										return new SexType(SexParticipantType.SELF, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
									return character.getMainSexPreference(targetedCharacter);
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.equals(Main.game.getNpc(Scarlett.class));
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
									return map;
								}
							},
							null,
							null,
							AFTER_SCARLETT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX")) {
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				responses.add(new Response("Helena", "Tell Scarlett that Helena is requesting her presence back at her shop in Slaver Alley.", HELENAS_NEST_MEETING_SCARLETT_TO_SHOP) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SEX = new DialogueNode("Finished", "Scarlett has had enough for now...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Do as Scarlett says and leave the nest...", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT_TO_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step back", "Scarlett is clearly not interested in talking to you, and so there's nothing to do but step back and take your leave.", HELENAS_NEST_EXTERIOR);
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Scarlett.", ScarlettsShop.HELENAS_SHOP) {
						@Override
						public void effects() {
							// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
							Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
							Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
							Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP_FLY_AFTER"));
						}
					};
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Fly after her", "As your companion is unable to fly, you cannot fly after Scarlett...", null);
					} else {
						return new Response("Fly after her", "As you are unable to fly, you cannot fly after Scarlett...", null);
					}
				}
			}
			return null;
		}
	};
	
}
