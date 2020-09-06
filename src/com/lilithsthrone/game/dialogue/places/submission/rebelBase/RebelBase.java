package com.lilithsthrone.game.dialogue.places.submission.rebelBase;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.8.9
 * @version 0.3.9
 * @author DSG
 */
public class RebelBase {
	
	public static final DialogueNode REBEL_BASE_ENTRANCE = new DialogueNode("Cave Entrance", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ENTRANCE");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1 && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
                            return new Response("[style.colourBad(Exit)]", "This place looks seriously unstable, it could collapse at any moment. It might be best to leave whatever secrets are in here and leave while you still can.", REBEL_BASE_COLLAPSE);
                    } else if (index == 1) {
                            return new Response("[style.colourGood(Exit)]", "You've had a look through everything that you could find. It might be best to leave while you still can.", REBEL_BASE_COLLAPSE);
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_COLLAPSE = new DialogueNode("Uh oh...", "", true) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_COLLAPSE");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Run!", "Run for your life!", PlaceType.BAT_CAVERN_DARK.getDialogue(false))
                            {
                                @Override
                                public void effects() {                                    
                                    Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_ESCAPE"));
                                    if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)){
                                        Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.SIDE_UTIL_COMPLETE));
                                    } else {
                                        Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_FAILED));
                                    }
                                    Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR, true);
                                    Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERN_DARK);
                                    Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERN_DARK.getName());
                                }
                            };
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_CORRIDOR = new DialogueNode("Artificial Cave", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "CORRIDOR");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    return null;
                };
        };
        
        public static final DialogueNode REBEL_BASE_SLEEPING_AREA = new DialogueNode("Abandoned Sleeping Area", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open footlockers", "Open the footlockers.", REBEL_BASE_SLEEPING_AREA_SEARCHED){
                                    @Override
                                    public void effects() {
                                            Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_CACHE_OPEN"));
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbooniehat", false), 2, false, true));
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtunic", false), 2, false, true));
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtrousers", false), 2, false, true));
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_vcboots", false), 2, false, true));
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbrassard", false), 5, false, true));
                                            Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED);
                                            Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED.getName());
                                    }
                            };
                    } else if (index ==2) {
                            return new Response("Read journal", "See what the journal contains.", REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN);
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN = new DialogueNode("Crumbling Journal", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_JOURNAL_OPEN");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1 && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.REBEL_BASE_SLEEPING_AREA)) {
                            return new Response("Close", "You've seen enough.", REBEL_BASE_SLEEPING_AREA){
                                    @Override
                                    public void effects() {
                                        if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE));
                                        }
                                        
                                    }
                            };
                    } else if (index == 1 && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED)) {
                            return new Response("Close", "You've seen enough.", REBEL_BASE_SLEEPING_AREA_SEARCHED) {
                                    @Override
                                    public void effects() {
                                        if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
                                            Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE));
                                        }

                                    }
                            };
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_SLEEPING_AREA_SEARCHED = new DialogueNode("Abandoned Sleeping Area", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_SEARCHED");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open footlockers", "You already opened the footlockers.", null);
                    } else if (index ==2) {
                    return new Response("Read journal", "See what the journal contains.", REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN);
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_COMMON_AREA = new DialogueNode("Abandoned Common Area", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open cabinet", "Open the metal cabinet.", REBEL_BASE_COMMON_AREA_SEARCHED){
                                    @Override
                                    public void effects() {
                                            Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA_CACHE_OPEN"));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rwebbing", false), 3, false, true));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_sbandana", false), 1, false, true));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbandolier", false), 3, false, true));
                                            Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_COMMON_AREA_SEARCHED);
                                            Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_COMMON_AREA_SEARCHED.getName());
                                    }
                            };
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_COMMON_AREA_SEARCHED = new DialogueNode("Abandoned Common Area", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA_SEARCHED");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open cabinet", "You've already opened the cabinet.", null);
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_ARMORY = new DialogueNode("Partly Caved-in Room", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open bags", "Open the plastic bags.", REBEL_BASE_ARMORY_SEARCHED){
                                    @Override
                                    public void effects() {
                                            Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY_CACHE_OPEN"));    
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbsmg"), 3, false, true));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pboltrifle"), 2, false, true));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbrevolver"), 5, false, true));
                                            Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_gbshotgun"), 1, false, true));
//                                          Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbomb"), 10, false, true));
                                            Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_ARMORY_SEARCHED);
                                            Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_ARMORY_SEARCHED.getName());
                                    }
                            };
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_ARMORY_SEARCHED = new DialogueNode("Partly Caved-in Room", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY_SEARCHED");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    if (index == 1) {
                            return new Response("Open bags", "There's nothing left in this rubble worth taking.", null);
                    } else {
                            return null;
                    }
                };
        };
        
        public static final DialogueNode REBEL_BASE_CAVED_IN_ROOM = new DialogueNode("Mostly Caved-in Room", "", false) {
		
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_CAVED_IN_ROOM");
		}

		@Override
                public Response getResponse(int responseTab, int index) {
                    return null;
                };
        };
}