package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.5.5
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
					return new Response("Meet with Helena", "Helena's flock is taking shelter in the buildings below her nest. You'll have to come back after the arcane storm has passed.", null);
					
				} else if(!Main.game.isExtendedWorkTime()) {
					return new Response("Meet with Helena", "Both Helena and her flock are sleeping in the buildings below her nest. You'll have to come back during the day if you want to speak with her.", null);
					
				} else {
					if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
						return new Response("Meet with Helena", "Walk over to the tall platform.", HELENAS_NEST);
						
					} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)) {
						if(Main.game.getNpc(Helena.class).getLocation().equals(Main.game.getPlayer().getLocation())) {
							return new Response("Meet with Helena", "You'll be able to interact with Helena again later!", null);
							
						} else {
							return new Response("Meet with Helena", "Helena has flown off to Slaver Alley! You'll have to find her there.", null);
						}
						
					}else {
						return new Response("Meet with Helena", "You have no reason to talk to Helena.", null);
					}
				}
				
			} else {
				return null;
			}
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
				
			} else {
				return null;
			}
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
				
			} else {
				return null;
			}
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
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_ENDURE = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_ENDURE");
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
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_STRUGGLE");
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
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_TAKE_PUNISHMENT_ENJOY = new DialogueNode("Helena's nest", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TAKE_PUNISHMENT_ENJOY");
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
				
			} else {
				return null;
			}
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
	
}
