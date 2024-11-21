package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DemonHome {
    
    private static Felicia getFelicia() {
        return ((Felicia)Main.game.getNpc(Felicia.class));
    }
	
    private static String getAdditionalDescriptions() {
    	StringBuilder sb = new StringBuilder();
    	
		if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
			sb.append(
					"<p>"
						+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
						+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area known as Demon Home."
						+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
					+ "</p>");
		}

		if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
			sb.append(
				"<p>"
					+ "<b style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>October;</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month:</b><br/>"
					+ "Orange, black, and purple flags fly from almost every window, and you look up to see that large banners have been hung across the street, each one bearing a different slogan celebrating Lilith's rule."
					+ " The occasional demon that you see is usually dressed up in a Halloween-esque costume for the occasion, which does nothing to help alleviate the eerie atmosphere."
				+ "</p>");
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
			sb.append(
				"<p>"
					+ "The reindeer-morph workers are doing a good job of keeping Dominion's streets clear from the snow, but the rooftops, trees, and tops of lamp posts are all home to a thick layer of white."
					+ " You see your breath exiting your mouth in a little cloud of condensation, but despite the clear evidence of the air's freezing temperature, your arcane aura protects your body from feeling the cold."
				+ "</p>");
		}
		
		return sb.toString();
    }
    
	public static final DialogueNode DEMON_HOME_GATE = new DialogueNode("Demon Home (Gates)", "Demon Home", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p>"
						+ "A set of huge, iron gates have been built across the street here, separating the regular areas of Dominion from that known as 'Demon Home' beyond."
						+ " Half a dozen elite demon Enforcers are stationed here, keeping a close eye on anyone who comes and goes."
					+ "</p>"
					+ "<p>"
						+ "As you walk forwards to pass through the gates, you see one of these demonic guards staring at you."
						+ " Ignoring their penetrating gaze, you stride forwards, breathing a sigh of relief as you get through to the other side without being stopped."
					+ "</p>");
			
			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "From the wide, marble-paved streets, to the immaculate frontages of the regency-style buildings, it's quite clear that this district of 'Demon Home' is one of the more upmarket areas of Dominion."
						+ " Numerous masterfully-carved statues, the vast majority of which depict some form of demon or another, are dotted around the area, and, considering their subject matter,"
							+ " you assume that these sculptures are what gives this area its name."
					+ "</p>"
					+ "<p>"
						+ "As you walk down the street, you pass several fenced-off private gardens; their lush splash of greenery helping to break up the monotony of the surrounding building's creamy-white stone facades."
						+ " Despite the fact that Demon Home is a little quieter than most of the other areas of Dominion, you notice that there are slightly more Enforcers patrolling the streets;"
									+ " evidence that the wealthy and influential residents of the city are afforded extra protection."
					+ "</p>");
			
			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_DADDY)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_DEMON.toWebHexString()+";'>[daddy.NamePos] residence:</b><br/>"
							+ "[daddy.NamePos] apartment is located in this particular area of Demon Home."
							+ Daddy.getAvailabilityText()
						+ "</p>");
			}

			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_ARTHUR)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+";'>Sawlty Towers:</b><br/>"
							+ "Arthur's apartment building, 'Sawlty Towers', is located in this particular area of Demon Home."
						+ "</p>");
			}

			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ARTHUR = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Sawlty Towers", "Find Arthur's apartment in the building using the instructions Lilaya gave to you.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Sawlty Towers", "Head over to the Sawlty Towers apartment building.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else {
					return null;
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ZARANIX = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
					return new Response("Zaranix's Home", "A little way down the road from Arthur's apartment building stands the home of Zaranix; the demon that Scarlett told you about.", ZaranixHomeGroundFloor.OUTSIDE);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
					return new Response("Zaranix's Home", "Pay Zaranix another visit.", ZaranixHomeGroundFloorRepeat.OUTSIDE);
				}
				return null;

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_DADDY = new DialogueNode("Demon Home", "Demon Home", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Daddy.isAvailable()) {
					return new Response("[daddy.Name]",
							Daddy.getAvailabilityText(),
							null);
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					return new Response("[daddy.Name]",
							"[style.italicsBad(You cannot meet [daddy.name] while you have companions in your party!)]",
							null);
					
				} else {
					return new Response("[daddy.Name]",
							"Head over to [daddy.namePos] apartment and knock on [daddy.her] door.",
							DaddyDialogue.MEETING) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING)) {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING));
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				}

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT = new DialogueNode("", "-", true) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Arthur's room", "Head up to Arthur's room.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public void effects() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN));
							}
						}
					};
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Arthur's room", "Arthur is no longer living here...", null);
				}
				
			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("[felicia.Name]'s room", "Head up to [felicia.namePos] room.", DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave the building and head back out into Demon Home.", DEMON_HOME_STREET_ARTHUR);
			}
			
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNode("Arthur's Room", "-", true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().equipOutsideClothing();
            getFelicia().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel() {
			return "Arthur's Room";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Question dog-girl", "Ask the dog-girl if she knows anything about Arthur's arrest.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END);
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END = new DialogueNode("Arthur's Room", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR){
                        @Override
                        public void effects() {
                            getFelicia().equipInsideClothing();
                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, true);
                        }
                    };
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM = new DialogueNode("", "", true) {
		public int h;
        @Override
        public void applyPreParsingEffects() {
            h = Main.game.getHourOfDay();
            if(h >= 6 && h <= 14) {
                getFelicia().setLocation(Main.game.getPlayer(), false);
                getFelicia().setIntroducedToPlayer(true);
            }
        }
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getLabel() {
			return "[felicia.NamePos] Room";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
	                 return new Response("Leave", "Looks like Felicia doesn't want to talk to you. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR);
				}
				if(h < 6 || h > 14) {
					return new Response("Leave", "Looks like [felicia.name] isn't here. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR);
					
				} else if(h >= 6 && h <= 14) {
	                if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaToldAboutArthur)) {
	                    return new Response("Enter", "Enter [felicia.namePos] home.", FeliciaApartment.ARTHUR_WHEREABOUTS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    };
	                    
	                } else {
	                   return new Response("Enter", "Enter [felicia.namePos] home.", FeliciaApartment.FELICIA_GREETINGS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    }; 
	                }
	                
				}
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_SEX_SHOP = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP"));
//			sb.append(getAdditionalDescriptions());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.isHourBetween(11, 23)) {
					return new Response("Lovienne's Luxuries",
							"Lovienne's Luxuries is open between [units.time(11)]-[units.time(23)], and as such is currently [style.colourBad(closed)].",
							null);
					
				} else {
					return new Response("Lovienne's Luxuries",
							"Push open the front door and enter Lovienne's Luxuries.",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_enter")) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_exit"));
						}
					};
					
				}
				
			} else if(index==2 && Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_2) {
				if(!Main.game.isHourBetween(1, 4)) {
					return new Response("Find Fia",
							"You need to wait until it's between [units.time(1)]-[units.time(4)] to break into Lovienne's Luxuries with Fia...",
							null);
					
				} else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return new Response("Find Fia",
							"Although you're here at the right time, between [units.time(1)]-[units.time(4)], Fia can't show up due to the ongoing arcane storm...",
							null);
					
				} else {
					return new Response("Find Fia",
							"Look for Fia so that the two of you can break into Lovienne's Luxuries and search for evidence of where the kidnapped people are being held."
								+"<br/>[style.italicsCombat(Be prepared, for this will start a lengthy section of the side quest during which there may be difficult fights!)]",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_factory_meet_fia_start"));
				}
			}
			return null;
		}
	};
}
