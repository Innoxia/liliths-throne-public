/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 *
 * @author dustland.crow
 */
public class FeliciaApartment {
    
    public static final DialogueNode ENTRANCE_HALL = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ENTRANCE_HALL");
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
    
}
