/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 *
 * @author DSG
 */
public class FeliciaApartment {
    
    private static Felicia getFelicia() {
            return ((Felicia)Main.game.getNpc(Felicia.class));
    }
    
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
                if (index == 1) {
                        return new Response("Leave", "Leave Felicia's home.", DemonHome.DEMON_HOME_ARTHURS_APARTMENT) {
                            @Override
                            public void effects() {
                                Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR);
                            }
                        };  
                } else {
                        return null;
                }
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
                if (index == 1 && getFelicia().getLocation().equals(Main.game.getPlayer().getLocation())) {
                        return new Response("Talk", "Talk to Felicia.", FELICIA_GREETINGS_APPROACH);			
                } else {
                        return null;
                }
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
    
    public static final DialogueNode FELICIA_GREETINGS = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GREETINGS");        
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode FELICIA_GREETINGS_APPROACH = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GREETINGS_APPROACH");        
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode FELICIA_GOODBYE = new DialogueNode("", "", false) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GOODBYE");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return null;
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS = new DialogueNode("", "", true) {
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
                if (index == 1 && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
                        return new Response("No", "Tell her you haven't found him yet.", ARTHUR_WHEREABOUTS_NO);			
                } else if(index == 1 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
                        return new Response("Yes", "Tell her the good news.", FeliciaApartment.ARTHUR_WHEREABOUTS_YES) {
                            @Override
                            public void effects() {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 30));
                            }
                        };
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS_NO = new DialogueNode("", "", true, true) {
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
                if (index == 1) {
                        return new Response("Arthur...", "Ask about Arthur.", TALK_MENU_ARTHUR);
                } else if (index == 2) {
                        return new Response("About her", "Ask [felicia.name] about herself.", TALK_MENU_ABOUT_HER);
                } else if (index == 3 && getFelicia().isPlayerKnowsName()) {
                        if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaLewdTalkAborted)) {
                            return new Response("Lewd", "Ask [felicia.name] lewd questions.", TALK_LEWD);
                        } else {
                            return new Response("Lewd", "Probably not the best idea to ask about this again.", null);
                        }
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode ARTHUR_WHEREABOUTS_YES = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaToldAboutArthur, true);
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS_YES");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_MENU = new DialogueNode("", "", true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {   
            return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Arthur...", "Ask about Arthur.", TALK_MENU_ARTHUR);
                } else if (index == 2) {
                        return new Response("About her", "Ask [felicia.name] about herself.", TALK_MENU_ABOUT_HER);
                } else if (index == 3 && getFelicia().isPlayerKnowsName()) {
                        if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaLewdTalkAborted)) {
                            return new Response("Lewd", "Ask [felicia.name] lewd questions.", TALK_LEWD);
                        } else {
                            return new Response("Lewd", "Probably not the best idea to ask about this again.", null);
                        }
                } else if (index == 4) {
                        if (getFelicia().getAffection(Main.game.getPlayer()) >= AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()) {
                            return new Response("Pet", "Ask to pet [felicia.name].", TALK_PET);
                        } else {
                            return new Response("Pet", "[felicia.Name] probably wouldn't appreciate that.", null);
                        }                        
                } else if (index == 0) {
                        return new Response("Leave", "Leave Felicia be for now.", FELICIA_GOODBYE);
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_MENU_ARTHUR = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU_ARTHUR");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Personality", "What's Arthur like?", TALK_ARTHUR_PERSONALITY) {
                            @Override
                            public void effects() {
                                if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedArthurPersonality)) {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                                }
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedArthurPersonality, true);
                            }
                        };
                } else if (index == 2) {
                        return new Response("Hobbies", "What does Arthur like to do?", TALK_ARTHUR_HOBBIES) {
                            @Override
                            public void effects() {
                                if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedArthurHobbies)) {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                                }
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedArthurHobbies, true);
                            }
                        };
                } else if (index == 0) {
                        return new Response("Back", "Ask about another topic.", TALK_MENU);
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_MENU_ABOUT_HER = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU_ABOUT_HER");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1 && !getFelicia().isPlayerKnowsName()) {
                        return new Response("Name", "You realise you don't even know her name.", TALK_ABOUT_HER_NAME) {
                            @Override
                            public void effects() {
                                getFelicia().setPlayerKnowsName(true);
                                Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                            }
                        };
                } else if (index == 1 && getFelicia().isPlayerKnowsName()) {
                        return new Response("Name", "You already know her name, it'd be awkward to ask again.", null);
                } else if (index == 2) {
                        return new Response("Her place", "Why so little stuff?", TALK_ABOUT_HER_PLACE) {
                            @Override
                            public void effects() {
                                if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerPlace)) {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                                }
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerPlace, true);
                            }
                        };
                } else if (index == 3) {
                        return new Response("Fur", "How'd she get it so fluffy?", TALK_ABOUT_HER_FUR) {
                            @Override
                            public void effects() {
                                if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerFur)) {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                                }
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerFur, true);
                            }
                        };
                } else if (index == 4) {
                        return new Response("Favourite store", "Where does she like to shop?", TALK_ABOUT_HER_FAVORITE_STORE) {
                            @Override
                            public void effects() {
                                if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerFavoriteStore)) {
                                    Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
                                }
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerFavoriteStore, true);
                            }
                        };
                } else if (index == 0) {
                        return new Response("Back", "Ask about another topic.", TALK_MENU);
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_ARTHUR_PERSONALITY = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ARTHUR_PERSONALITY");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ARTHUR.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_ARTHUR_HOBBIES = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ARTHUR_HOBBIES");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ARTHUR.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_ABOUT_HER_NAME = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_NAME");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_ABOUT_HER_PLACE = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_PLACE");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_ABOUT_HER_FUR = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_FUR");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_ABOUT_HER_FAVORITE_STORE = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_FAVORITE_STORE");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
        }
    };
    
    public static final DialogueNode TALK_LEWD = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Continue", "Someone else, then?", TALK_LEWD_CONTINUE);
                } else if (index == 2) {
                        return new Response("Back off", "Take the hint and talk about something else.", TALK_MENU) {
                            @Override
                            public void effects() {
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
                            }
                        };
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_LEWD_CONTINUE = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_CONTINUE");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Push", "How can anyone in this realm be so innocent?", TALK_LEWD_PUSH) {
                            @Override
                            public void effects() {
                                Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), -5));
                            }
                        };
                } else if (index == 2) {
                        return new Response("Back off", "Take the hint and talk about something else.", TALK_MENU) {
                            @Override
                            public void effects() {
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
                            }
                        };
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_LEWD_PUSH = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_PUSH");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Dig deep", "You <i>must</i> know!", TALK_LEWD_DIG_DEEP) {
                            @Override
                            public void effects() {
                                Main.game.getTextEndStringBuilder().append(getFelicia().setAffection(Main.game.getPlayer(), -100));
                            }
                        };
                } else if (index == 2) {
                        return new Response("Back off", "Take the hint and talk about something else.", TALK_MENU) {
                            @Override
                            public void effects() {
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
                            }
                        };
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_LEWD_DIG_DEEP = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_DIG_DEEP");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                if (index == 1) {
                        return new Response("Leave", "You've done enough damage for today.", DemonHome.DEMON_HOME_ARTHURS_APARTMENT) {
                            @Override
                            public void effects() {
                                Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR);
                                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaRejectedPlayer, true);
                            }
                        };
                } else {
                        return null;
                }
        }
    };
    
    public static final DialogueNode TALK_PET = new DialogueNode("", "", true, true) {
        @Override
        public int getSecondsPassed() {
                return 1*60;
        }
        @Override
        public String getContent() {
                return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_PET");
        }
        @Override
        public Response getResponse(int responseTab, int index) {
                return TALK_MENU.getResponse(responseTab, index);
        }
    };
    
}
