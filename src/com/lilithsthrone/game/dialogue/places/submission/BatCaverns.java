package com.lilithsthrone.game.dialogue.places.submission;

import java.time.DayOfWeek;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.BatCavernsEncounterDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCaverns {
	
	private static Response getElleSearchResponse() {
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_1) {
			if(Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY || Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15) {
				return new Response("Search for Elle",
						"You're only going to be able to find Elle on a "
						+ (Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY
							?"[style.italicsBad(Wednesday)]"
							:"[style.italicsGood(Wednesday)]")
						+ " between "
						+ ((Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15)
								?"[style.italicsBad([units.time(12)]-[units.time(15)])]"
								:"[style.italicsGood([units.time(12)]-[units.time(15)])]")
						+ "!",
						null);
				
			} else {
				return new Response("Search for Elle", "Start searching for any sign of Elle down here in the Bat Caverns...", WesQuest.ELLE_SEARCH);
			}
		}
		return null;
	}
	
	public static final DialogueNode STAIRCASE = new DialogueNode("Winding Staircase", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "STAIRCASE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Submission", "Head back up to Submission.", PlaceType.SUBMISSION_BAT_CAVERNS.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_BAT_CAVERNS, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("Dominion", "As your party members are unable to fly, you cannot use the shaft to travel up to Dominion...", null);
						
					} else {
						return new Response("Dominion", "Fly up the shaft to return to Dominion.", SHAFT_FLY_UP) {
							@Override
							public void effects() {
								if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS)==null) {
									Cell referenceCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_WAREHOUSES);
									Cell shaftCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(referenceCell.getLocation().getX()+2, referenceCell.getLocation().getY()));
									shaftCell.getPlace().setPlaceType(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS);
									shaftCell.getPlace().setName(PlaceType.BAT_CAVERN_SHAFT.getName());
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP_FIRST_TIME"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP"));
								}
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS, false);
							}
						};
					}
					
				} else {
					return new Response("Dominion", "As you are unable to fly, you cannot use the shaft to travel up to Dominion...", null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT_FLY_UP = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DominionPlaces.CITY_EXIT_BAT_CAVERNS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAVERN_DARK = new DialogueNode("Dark Cavern", "", false) {
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() { // If this is going to be changed, bear in mind that this is called in the REBEL_BASE DialogueNodes below
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_DARK"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the cavern's dark depths. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2) {
				return getElleSearchResponse();
				
			} else if(index==3
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseDarkPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("Search for password", 
										"Peer into the darkness and search harder for the password to the mysterious door.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							    
							} else {
							    return new Response("Search for password", 
										"Peer into the darkness and search harder for the rest of the password to the mysterious door.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("Search for password", 
									"Peer into the darkness and search harder for the password to the mysterious door.", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					    
					} else {
					    return new Response("Search for password", 
							    "You've already found the password in this area.", 
							    null);
					}
					
			} else if(index==4
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("I'm a busy [pc.man]!", 
							    "This is such a waste of time."
								    + "<br/>[style.boldBad(This will skip all content and rewards for the Grave Robbing quest!)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAVERN_LIGHT = new DialogueNode("Bioluminescent Cavern", "", false) {
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_LIGHT"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the bioluminescent forest. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2) {
				return getElleSearchResponse();
				
			} else if(index==3
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseLightPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("Search for password", 
										"Poke around the mushrooms and search harder for the password to the mysterious door.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							} else {
							    return new Response("Search for password", 
										"Poke around the mushrooms and search harder for the rest of the password to the mysterious door.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("Search for password", 
									"Poke around the mushrooms and search harder for the password to the mysterious door.", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					} else {
					    return new Response("Search for password", 
							    "You've already found the password in this area.", 
							    null);
					}
			} else if(index==4
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("I'm a busy [pc.man]!", 
							    "This is such a waste of time."
								    + "<br/>[style.boldBad(This will skip all content and rewards for the Grave Robbing quest!)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER = new DialogueNode("Underground River", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the river. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_BRIDGE = new DialogueNode("Mushroom Bridge", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_BRIDGE"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the bridge's surroundings. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_END = new DialogueNode("Underground River", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_END"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the river. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE = new DialogueNode("Slime Lake", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE"));
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_UNKNOWN"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_KNOWLEDGE"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_GUESS"));
				
			}

			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			});
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly(
						"Explore",
						"Explore the sides of the lake. Although you don't think you're any more or less likely to find anything by doing this, at least you won't have to keep travelling back and forth..."){
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Island by boat", "You could use the boat to travel across the lake and reach the island.", SLIME_LAKE_ISLAND) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT"));
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
						}
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly to island", "Fly across to the island.", SLIME_LAKE_ISLAND) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY"));
							if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
							}
						}
					};
				} else {
					return new Response("Fly to island", "You aren't able to fly. It looks like you'll have to use the boat...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE_ISLAND = new DialogueNode("Slime Lake", "", true) {
		
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
				return new Response("Explore", "You'd need to be on the other side of the lake in order to explore this area!", null);
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Return by boat", "Return to the other side of the lake by boat.", SLIME_LAKE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT_RETURN"));
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Return by flying", "Return to the other side of the lake by flying.", SLIME_LAKE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY_RETURN"));
						}
					};
					
				} else {
					return new Response("Return by flying", "You aren't able to fly. It looks like you'll have to use the boat...", null);
				}
				
			} else if (index==4) {
				return new ResponseEffectsOnly(
						"Open Door",
						"Push open the tower's front door and step inside."){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE);
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Walking up to the base of the stone tower, you place [pc.a_hand] on the iron-barred oaken door, and with a firm shove, push it open and step inside."
										+ "</p>");
								if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR));
								}
								Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							}
						};
			}
			return null;
		}
	};
		
	public static final DialogueNode REBEL_BASE_ENTRANCE_HANDLE = new DialogueNode("Strange Handle", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_HANDLE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE)) {
					return new Response("Pull the handle", "What could possibly go wrong?", BatCavernsEncounterDialogue.REBEL_BASE_DOOR_NO_PASS);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					return new Response("Pull the handle", "The handle won't budge. Looks like you really do need the password.", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE)) {
					return new Response("Pull the handle", "You don't have the complete password!", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
					return new Response("Pull the handle", "You have the complete password now and can therefore try to pull the handle again... if you really want to.", REBEL_BASE_DOOR_OPENED) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
						}
					};
				}
			} else {
				return CAVERN_DARK.getResponse(responseTab, index-1);
			}
			return null;
		}
	};
			
	public static final DialogueNode REBEL_BASE_DOOR_OPENED = new DialogueNode("Hidden Doorway", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_OPENED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "This cave is not a natural formation. Someone built it, so it must lead somewhere.", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
					}
				};
				
			} else if (index == 2) {
				return new Response("Don't enter", "No telling what's in that cave...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_ENTRANCE_EXTERIOR = new DialogueNode("Hidden Cave", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			
			if (Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) || Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR_COLLAPSED"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) 
					&& !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
				if (index == 1) {
					return new Response("Enter", "This cave is not a natural formation. Someone built it, so it must lead somewhere.", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
						}
					};
				}
				
			} else { // If cave is cleared, give normal responses
				return CAVERN_DARK.getResponse(responseTab, index);
			}
			return null;
		}
	};
		
}
