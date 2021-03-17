/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 *
 * @author DSG
 */
public class FeliciaApartment {
    
    public static final DialogueNode ENTRYWAY = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ENTRYWAY");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };
    
    public static final DialogueNode FELICIA_BEDROOM = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_BEDROOM");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };
    
    public static final DialogueNode BATHROOM = new DialogueNode("", "", false) {
            @Override
            public int getSecondsPassed() {
                    return 1*60;
            }
            @Override
            public String getContent() {
                    return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "BATHROOM");
            }
            @Override
            public Response getResponse(int responseTab, int index) {
                    return null;
            }
    };

    public static final DialogueNode KITCHEN = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "KITCHEN");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };

    public static final DialogueNode DINING_AREA = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "DINING_AREA");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };

    public static final DialogueNode LIVING_AREA = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "LIVING_AREA");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };
    
    public static final DialogueNode HALLWAY = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "HALLWAY");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 0 && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
                        return new Response("No", "Tell her you haven't found him yet.", ARTHUR_WHEREABOUTS_NO);			
                } else if(index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
                        return new Response("Yes", "Tell her the good news.", FeliciaApartment.ARTHUR_WHEREABOUTS_YES);
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS_NO = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS_NO");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 0) {
                        return new Response("Leave", "Looks like Felicia isn't here. Head back outside to Demon Home.", null);			
                } else if(index == 0) {
                        return new Response("Enter", "Enter Felicia's home.", FeliciaApartment.ARTHUR_WHEREABOUTS);
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS_YES = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS_YES");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 0) {
                        return new Response("Leave", "Looks like Felicia isn't here. Head back outside to Demon Home.", null);			
                } else if(index == 0) {
                        return new Response("Enter", "Enter Felicia's home.", FeliciaApartment.ARTHUR_WHEREABOUTS);
                } else {
                        return null;
                }
        }
    };
    
}
