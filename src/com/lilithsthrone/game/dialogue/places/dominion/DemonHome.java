package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
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
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area known as Demon Home."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			}

			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>October;</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month:</b><br/>"
						+ "Orange, black, and purple flags fly from almost every window, and you look up to see that large banners have been hung across the street, each one bearing a different slogan celebrating Lilith's rule."
						+ " The occasional demon that you see is usually dressed up in a Halloween-esque costume for the occasion, which does nothing to help alleviate the eerie atmosphere."
					+ "</p>");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Snow:</b><br/>"
						+ "The reindeer-morph workers are doing a good job of keeping Dominion's streets clear from the snow, but the rooftops, trees, and tops of lamp posts are all home to a thick layer of white."
						+ " You see your breath exiting your mouth in a little cloud of condensation, but despite the clear evidence of the air's freezing temperature, your arcane aura protects your body from feeling the cold."
					+ "</p>");
			}
			
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

			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Arcane Storm:</b><br/>"
							+ "The arcane storm that's raging overhead has brought out a heavy presence of demon Enforcers in this area."
							+ " Unaffected by the arousing power of the storm's thunder, these elite Enforcers keep a close watch on you as you pass through the all-but-deserted area known as Demon Home."
							+ " There's no way anyone would be able to assault you while under their watchful gaze, allowing you continue on your way in peace..."
						+ "</p>");
			}

			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>October;</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Lilith's Month:</b><br/>"
						+ "Orange, black, and purple flags fly from almost every window, and you look up to see that large banners have been hung across the street, each one bearing a different slogan celebrating Lilith's rule."
						+ " The occasional demon that you see is usually dressed up in a Halloween-esque costume for the occasion, which does nothing to help alleviate the eerie atmosphere."
					+ "</p>");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				UtilText.nodeContentSB.append(
					"<p>"
						+ "<b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Snow:</b><br/>"
						+ "The reindeer-morph workers are doing a good job of keeping Dominion's streets clear from the snow, but the rooftops, trees, and tops of lamp posts are all home to a thick layer of white."
						+ " You see your breath exiting your mouth in a little cloud of condensation, but despite the clear evidence of the air's freezing temperature, your arcane aura protects your body from feeling the cold."
					+ "</p>");
			}
			
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
					return new Response("Arthur's Apartment", "Find Arthur's apartment using the instructions Lilaya gave to you.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Arthur's Apartment", "Head over to Arthur's apartment building.", DEMON_HOME_ARTHURS_APARTMENT);
					
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
		public String getLabel() {
			return "Arthur's Apartment Building";
		}

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME)
				return "<p>" + "Following Lilaya's instructions, you soon find yourself standing outside the building which houses Arthur's apartment."
                                             + "Carved into the stone edifice just above the entrance are the words 'Sawlty Towers' which you assume must be the name of this building"
						+ " Although it's just as impressive as most of the other buildings in Demon Home, it's nothing compared to Lilaya's house, and as you walk up to the entrance,"
						+ " you find yourself reflecting on how lucky you were to have ended up living with this reality's version of your aunt Lily." + "</p>" + "<p>"
						+ "The front door is unlocked, and as you step into the foyer, you see that this place looks more like a five-star hotel than an apartment building."
						+ " The luxurious carpeting, fine paintings and crystal chandeliers all contribute to giving the impression that the apartments here are very exclusive, and very expensive."
						+ " Although there's a front desk, the place seems to be deserted, but fortunately there's a sign on a nearby wall that lists the building's occupants" + " and their respective room numbers."
						+ " You see that Arthur lives on the first floor, in room five." + "</p>";
			else
				return "<p>" + "You soon find yourself standing outside Sawlty Towers, the building which houses Arthur and Felicia's respective apartments."
						+ " Although it's just as impressive as most of the other buildings in Demon Home, it's nothing compared to Lilaya's house, and as you walk up to the entrance,"
						+ " you find yourself reflecting on how lucky you were to have ended up living with this reality's version of your aunt Lily." + "</p>" + "<p>"
						+ "The front door is unlocked, and as you step into the foyer, you once again take note that this place looks more like a five-star hotel than an apartment building."
						+ " The luxurious carpeting, fine paintings and crystal chandeliers all contribute to giving the impression that the apartments here are very exclusive, and very expensive."
						+ " The front desk is unmanned yet again, and you wonder what to do now that you're here." + "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Arthur's Room", "Head up to Arthur's room.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public void effects() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN));
							}
						}
					};
					
				} else {
					return null;
				}
				
			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("[felicia.Name]'s room", "Head up to [felicia.namePos] room.", DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM);
				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave the building and head back out into Demon Home.", DEMON_HOME_STREET_ARTHUR);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNode("", "-", true) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getLabel() {
			return "Arthur's Room";
		}

		@Override
		public String getContent() {
                            getFelicia().equipOutsideClothing();
                            getFelicia().setLocation(Main.game.getPlayer(), false);
                            return "<p>"
					+ "You head over to the staircase and make your way up to the first floor."
					+ " Turning down a long, straight corridor, you walk along until you find room number five."
					+ " As you approach, you see that a note has been stuck to the door, and, coming to a halt in front of it, you let out a sigh."
					+ "</p>"
					+ "<p>"
					+ "The note reads:"
					+ "</p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>NOTICE OF ARREST</h5>"
					+ "<p style='text-align:center;'>The occupant of this residence, namely the person of <i>Arthur Fairbanks</i>, has been issued with an arrest warrant.<br/>"
					+ "These premises are therefore under investigation by Dominion's Enforcer Department, and any unauthorised entry beyond this point is in violation of the law."
					+ "<br/><br/>"
					+ "Officer in charge of issuing warrant: <i>[brax.fullName]</i>"
					+ "<br/><br/>"
					+ "Any complaints or inquiries should be made in person at Dominion's Enforcer HQ. Thank you for your understanding.</p>"
					+ "<br/>"
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerThought("Great... So he's been arrested...")
					+ " you think, letting out another sigh."
					+ "</p>"
					+ "<p>"
					+ "As you focus on reading the notice for a second time, you feel a light tapping on your [pc.arm]."
					+ " When you turn around, you spot a small, white ball of fluff. A second later, you notice it's a dog-girl, whose [felicia.eyesFullDescription]"
                                        + " are looking at you with curiosity."
					+ "</p>"
					+ "<p>"
					+ "[felicia.speech(Are you looking for Arthur?)] Her head tilts to the left, with her right ear pointed up."
					+ "</p>"
					+ "<p>"
					+ "[pc.speech(Yes, do you have any idea what happened?)]"
					+ "</p>"
					+ "<p>"
					+ "You notice [felicia.eyes] slightly light up and her [felicia.tail] swish behind her for a second before she straightens herself up and speaks."
					+ "</p>"
					+ "<p>"
					+ "[felicia.speech(All I know is some Enforcers came and took him away last night. I heard them from my place, something about plotting against Lilith.)]"
					+ " Her right ear suddenly droops and a small expression of sadness shows on the dog-girl's face."
                                        + " [felicia.speech(I've known Arthur since he moved in. He'd need a hidden room to keep a secret like that from me. It's not something he'd do.)]"
                                        + " Her ears somehow both fall even lower than they already were, the girl feeling pretty down about the whole situation"            
					+ "</p>"
					+ "<p>"
					+ " Feeling slightly awkward, you decide to cheer her up. [pc.speech(Don't worry, I'll get him back.)]"
                                        + "</p>"
                                        + "<p>"
					+ "At that, the dog-girl almost explodes with excitement, both ears more erect than an imp, and her tail wagging at full speed. [felicia.speech(You mean it?)] You nod."
                                        + " [felicia.speech(Yes! Please get him back safe!)] Realising how energetic she had just become, the girl takes a few deep breaths and calms down. "
                                        + " [felicia.speech(He's the best friend I've had in a while, I'm real worried.)]"                          
					+ "</p>"
                                        + "<p>"
                                        + "You assure her that you'll do everything you can before preparing to head off. The arrest warrant mentioned that any inquiries need to be made in person"
                                        + " at the Enforcers' HQ, so you'll need to make that your next stop. Before you can leave, Felicia grabs your arm. [felicia.speech(My room is next to his, come over if you want to know anything about him.)]"
                                        + " She then walks back to her room, her tail wagging slightly, obviously trying to stay inconspicious."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "You've done all you can here. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR){
                                    @Override
                                    public void effects() {
                                        getFelicia().equipInsideClothing();
                                        getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, true);
                                    }
                                };
				
			} else {
				return null;
			}
		}

	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM = new DialogueNode("", "", true) {
                public int h;
                
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
                    String s = "";
                    if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
                            h = Main.game.getDateNow().getHour(); 
                            s = "<p> You returned to Sawlty Towers, deciding to visit the dog girl wondering where Arthur was."
                                + " Her room wasn't hard to find, being directly to the left of Arthur's. You knock on the door a few times and wait. </p>";
                            if(h >= 6 && h <= 8) {
                                getFelicia().setLocation(Main.game.getPlayer(), false);
                                getFelicia().setIntroducedToPlayer(true);
                                s = s.concat("<p>Inside, you can hear footsteps getting closer before the door unlocks and opens to reveal a white furball, dripping wet; not in that way."
                                        + " The toweled dog looked at you with her natural puppy eyes and spoke [felicia.speech(Ah, I didn't expect you to come over so early, I would have picked up.)]"
                                        + " As she finished, she motioned to let you in.</p>"
                                );
                            } else if (h >= 9 && h <= 14) {
                                getFelicia().setLocation(Main.game.getPlayer(), false);
                                getFelicia().setIntroducedToPlayer(true);
                                s = s.concat("<p>You hear a sharp [felicia.speech(Ah! Coming!)] seconds before the door swings open, revealing a white ball of floof, wearing an oversized, brown sweater."
                                        + " The dog-girl stares at you in excitement when seeing you, speaking sharply,"
                                        + " [felicia.speech(Come in, I just finished lunch.)]</p>"
                                );
                            } else {
                                s = s.concat("<p>After a few moments of silence, you decide to knock again, but there doesn't seem to be anyone home at the moment."
                                        + " Sighing, you give up and decide to come back another time. </p>"
                                );
                            }
                    } else {
                            s = "<p>You knock on Felicia’s door, hoping the dog girl has moved on from the incident.  There wasn’t a response; you were just about to knock again when the door opened slightly.</p>"
                                + "<p>The dog girl spots you through the opening of the door before closing it on you.  [felicia.speech(Not right now)] she said, sounding upset.</p>"
                                + "<p>[pc.speech(Bu-)] [felicia.speech(No,)] she cut you off.</p>"
                                + "<p>You stand there for a second, trying to think up something to say. Sadly, nothing came up, and you decided to leave.</p>";
                    }
                    return s;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && (h < 6 && h > 14) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
				return new Response("Leave", "Looks like [felicia.name] isn't here. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR);			
			} else if (index == 1 && (h >= 6 && h <= 14) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
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
                        } else if (index == 1 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
                                return new Response("Leave", "Looks like Felicia doesn't want to talk to you. Head back outside to Demon Home.", DEMON_HOME_STREET_ARTHUR);
                        } else {
				return null;
			}
		}
	};
}
